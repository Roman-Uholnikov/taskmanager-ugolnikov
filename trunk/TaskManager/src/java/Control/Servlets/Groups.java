/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Servlets;

import Control.Exceptions.UserAutentificationException;
import Control.Exceptions.UserInputException;
import Control.WebEngine;
import Model.DAO;
import Model.Group;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Groups extends HttpServlet {

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
        RequestDispatcher dispatcherJsp = request.getRequestDispatcher("/groups.jsp");
        
        
        /* проверка на введеные данные*/
        try{
            //парсинг
            String name = request.getParameter("groupname");
            String title = request.getParameter("grouptitle");
            String manager = request.getParameter("groupmanager");
            if((name!=null)&(title!=null)&(manager!=null)){
                    if (name.equalsIgnoreCase("")|title.equalsIgnoreCase("")){
                        throw new UserInputException("заполните все поля");
                    }
                    int managerId;
                    if (manager.equalsIgnoreCase("empty")){
                        managerId = -1;
                    }else{
                        managerId = Integer.valueOf(manager);
                    }
                try {
                    //добавление
                    Group.add(name, title, managerId);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Groups.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(UserInputException e){
            request.setAttribute("userException", e);
        }
        
        
        /* формирование ответа */
        response.setContentType("text/html;charset=UTF-8");
        List<Group> groups;
        List<User> users;
        
        try{
            groups = DAO.getInstance().getGroups();
            users = DAO.getInstance().getUsers(null);
            request.setAttribute("groups", groups);
            request.setAttribute("users", users);
        
        
            if (dispatcherJsp != null){
               dispatcherJsp.forward(request, response);
            }
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
