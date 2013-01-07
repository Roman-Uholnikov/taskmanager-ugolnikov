/*
 * форма пользователь
 * 
 */
package Control.Servlets;

import Control.Exceptions.UserAutentificationException;
import Control.Exceptions.UserInputException;
import Control.WebEngine;
import Model.DAO;
import Model.Group;
import Model.Task;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin4eg
 */
public class Users extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* создаем диспетчеры перенаправления*/
        RequestDispatcher dispatcherJsp = request.getRequestDispatcher("/users.jsp");
        RequestDispatcher dispatcherLogin = request.getRequestDispatcher("/LoginPage.jsp");
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<Task> currentTasks;
        String search = null;
        User user;
        /* парсинг введенных данных*/
        try{
            //парсинг
            String phone = "";
            String room = "";
            String loginname = "";
            String password = "";
            
            int rights = 1; // уровень пользователя
            
            if((request.getParameter("addname")!=null)&(request.getParameter("addgroup")!=null)
                    &(request.getParameter("loginname")!=null)&(request.getParameter("password")!=null)){
                    String name = request.getParameter("addname");
                    String group = request.getParameter("addgroup");
                    if (request.getParameter("addphone")!=null) phone = request.getParameter("addphone");
                    if (request.getParameter("loginname")!=null) loginname = request.getParameter("loginname");
                    if (request.getParameter("password")!=null) password = request.getParameter("password");
                    if (request.getParameter("addroom")!=null) room = request.getParameter("addroom");
                    if (request.getParameter("addcontroller")!= null) rights = 2; // уровень координатора
                    if (name.equalsIgnoreCase("")|loginname.equalsIgnoreCase("")|password.equalsIgnoreCase("")){
                        throw new UserInputException("заполите поле Имя,Пароль,Логин");
                    }
                    int groupId = Integer.valueOf(group);
                    //добавление
                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setGroupId(groupId);
                    newUser.setPhone(phone);
                    newUser.setLocation(room);
                    newUser.setRights(rights);
                    
                    DAO.getInstance().addUser(newUser, loginname, password);
            }
        }catch(UserInputException e){
            request.setAttribute("userException", e);
        }
        
        
        /* формируем ответ*/
        try{
            
            //пробуем создать пользователя
            user = User.getUser(WebEngine.getUserId(request));
            //пользователь.Получить список заданий
            
            if(request.getParameter("search")!= null){
                search = request.getParameter("search");
            } 
            
            //получаем всех пользователй относительн заданного(или не заданного)
            //параметра поиска
            List<User> users = DAO.getInstance().getUsers(search);
            List<Group> groups = DAO.getInstance().getGroups();
            
            //помещяем данные в транспортный бин
            request.setAttribute("users", users);           
            request.setAttribute("groups", groups);           
            
            //перенаправление в jsp
            if (dispatcherJsp != null){
               dispatcherJsp.forward(request, response);
            }
        }catch(UserAutentificationException e){        
            WebEngine.caseUserAutentificationException(request, response, e);
        }catch(Exception e){
            WebEngine.caseException(request, response, e);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
