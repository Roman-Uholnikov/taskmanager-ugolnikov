/*
 * Авторизация на сайте
 */
package Control.Servlets;

import Control.WebEngine;
import Control.Exceptions.UserAutentificationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Group;

/**
 *
 * @author admin4eg
 */
public class Login extends HttpServlet {

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
        StringBuilder errorMesage = new StringBuilder();
        //response.setContentType("text/html; charset=Windows-1251");
        /* создаем диспетчер перенаправления*/
        RequestDispatcher dispatcherLogin = request.getRequestDispatcher("/LoginPage.jsp");
        RequestDispatcher dispatcherError = request.getRequestDispatcher("/applicationError.jsp");
        String requedUrl;
        Group g =  new Group();
        requedUrl = WebEngine.TASKS_PATH;
        try{ 
            if(request.getParameter("action")!= null){
                if  (request.getParameter("action").equalsIgnoreCase("logout")){
                    //нажата кнопка "выход"
                    WebEngine.logout(request);
                    throw new UserAutentificationException("до скорых встреч!");
                } 
            }
            
            // попытка авторизации
            if (!(request.getMethod().equalsIgnoreCase("POST"))
                    |(request.getParameter("name") == null)
                    |(request.getParameter("password") == null)){
                throw new UserAutentificationException();
            }
            WebEngine.autorizzare(request, response);
            
            //авторизация прошла успешно.загаловок аштмл переадрисация
            response.sendRedirect(requedUrl);
            
        }catch(UserAutentificationException error){
            if (dispatcherLogin != null){
                request.setAttribute("Exception", error);
                dispatcherLogin.forward(request, response);
           }
        }catch(Exception error){        
            if (dispatcherError != null){
                 request.setAttribute("Exception", error);
               dispatcherError.forward(request, response);
            }
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
