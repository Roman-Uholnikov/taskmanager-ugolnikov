/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Servlets;

import Control.Exceptions.UserAutentificationException;
import Control.Exceptions.UserInputException;
import Control.WebEngine;
import Model.DAO;
import Model.Task;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Роман
 */
@WebServlet(name = "Tasks", urlPatterns = {"/Tasks"})
public class Tasks extends HttpServlet {

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
        RequestDispatcher dispatcherJsp = request.getRequestDispatcher("/tasks.jsp");
        RequestDispatcher dispatcherLogin = request.getRequestDispatcher("/LoginPage.jsp");
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User user;
        List<Task> currentTasks;
        UserInputException userException = null;
        String [] itemsString;
        int [] items;
        Task task;
        
        try{
            //проверка входных данных.
            try{
                if (request.getParameter("action")!=null){
                    if (request.getParameter("commenttext").equalsIgnoreCase("")) {
                        throw new UserInputException("поле коментария не может быть пустым");
                    }
                    if (request.getParameter("item")==null) {
                        throw new UserInputException("выбирете хотябы одну заявку");
                    }
                    
                    
                    String se = "";
                    Map paramMap = request.getParameterMap();
                    itemsString = (String[])paramMap.get("item");
                    items = new int[itemsString.length];
                    for (int i = 0; i < itemsString.length; i++) {
                        items[i] = Integer.valueOf(itemsString[i]);
                    }
                    
                    //все проверели, теперь выполняем действие
                    for (int i : items) {
                        task = DAO.getInstance().getTask(i);
                        //добавит коментарий
                        if(request.getParameter("action").equalsIgnoreCase("addComent")){
                            task.addComment(request.getParameter("commenttext"), 
                                    WebEngine.getUserId(request));
                        }
                        //переслать
                        if(request.getParameter("action").equalsIgnoreCase("addComent")){
                            task.forward(WebEngine.getUserId(request));
                            task.addComment(request.getParameter("commenttext"), 
                                    WebEngine.getUserId(request));
                        }
                        //закрыть
                        if(request.getParameter("action").equalsIgnoreCase("addComent")){
                            task.addComment(request.getParameter("commenttext"), 
                                    WebEngine.getUserId(request));
                            task.close();
                        }
                    }
                    
                    
                }
            }catch(UserInputException e){
                userException = e;
            }
            
            
            
            
            //пробуем создать пользователя
            user = User.getUser(WebEngine.getUserId(request));
            //пользователь.Получить список заданий
            boolean s = WebEngine.showDone(request);//возвращает ложьХХХ
            user.makeTasksQueue(WebEngine.showDone(request));
            
            //создаем переменную с помощью которой мы передадим данные в jsp
            currentTasks = new ArrayList<Task>(user.getTasksQueue());
            List<User> users = DAO.getInstance().getUsers(null);
            //xxx формируемм список Tasks в зависимости от условий посика и сортировки ()
            
            request.setAttribute("tasks", currentTasks); 
            request.setAttribute("users", users);
            request.setAttribute("userException", userException);
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
