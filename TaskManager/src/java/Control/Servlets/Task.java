/*
 * Отображает задачу
 */
package Control.Servlets;

import Control.Exceptions.UserAutentificationException;
import Control.WebEngine;
import Model.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin4eg
 */
public class Task extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        Model.User user; 
        Model.Task task;
        /* создаем диспетчер перенаправления*/
        RequestDispatcher dispatcherJsp = request.getRequestDispatcher("/taskComment.jsp");
//        RequestDispatcher dispatcherLogin = request.getRequestDispatcher("/LoginPage.jsp");
        RequestDispatcher dispatcherError = request.getRequestDispatcher("/applicationError.jsp");
        try{
            //пришол ли ИД той заявки которую нужно показать
            if(request.getParameter("id") == null){
                response.sendRedirect("Tasks");
                return;
            } 
//            //проверка сессии пользователя, куки, или както так
//            WebEngine.checkUser(request);
//            //пробуем создать пользователя
//            user = DAO.getInstance().getUser(Integer.valueOf(request.getParameter("userID")));
            //найти заявку по ИД
            int d2 = Integer.valueOf(request.getParameter("id"));
            task = DAO.getInstance().getTask(Integer.valueOf(request.getParameter("id")));
            //положить в транспортный бин
            request.setAttribute("task", task);
            
            if (dispatcherJsp != null){
               dispatcherJsp.forward(request, response);
            }
//        }catch(UserAutentificationException e){
//            WebEngine.caseUserAutentificationException(request, response, e);  
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
