/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.Exceptions.UserAutentificationException;
import Model.DAO;
import Model.Group;
import Model.User;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

/**
 *
 * @author admin4eg
 */
public class WebEngine {    
    /* абсолютные адреса адреса на тематические страници*/
    public static final String APPLICATION_PATH     = "/TaskManager/";
    public static final String LOGIN_PATH           = "/TaskManager/Login";
    public static final String COMMENT_PATH         = "/TaskManager/work/Comment";
    public static final String TASKS_PATH           = "/TaskManager/work/Tasks";
    public static final String USERS_PATH           = "/TaskManager/work/Users";
    public static final String GROUPS_PATH          = "/TaskManager/work/Groups";
    
    public static RequestDispatcher dispatcherError;
    
    /**
     * Попытка авторизации пользователя по логину и паролю
     * в случае успеха устанавливает в куки ИД пользователя
     * в случае неудачи выкидывает UserAutentificationException
     *
     */
    public static void autorizzare(HttpServletRequest request, HttpServletResponse response) throws UserAutentificationException, Exception{
        //request.getParameter("name"), request.getParameter("password")
        User user;
        Cookie userIDCookie;
        Cookie userShowDoneTask;

        user = User.getUser(request.getParameter("name"), request.getParameter("password"));

        if(user == null){
            throw new UserAutentificationException("логин или пароль не найдены");
        }
                
        userIDCookie = new Cookie("userID", String.valueOf(user.getUserID()));
        //отрицателное число значит что куки хранятся только на протяжении текущей сессии
        userIDCookie.setMaxAge(-1);
        response.addCookie(userIDCookie); 
        
        //default value. user can change it
        userShowDoneTask = new Cookie("ShowDoneTask", "true");
        response.addCookie(userShowDoneTask); 
    }
    
    
    /**
     * шифрует строку односторонним алгоритмом
     * @param stringToEncode - строка для шифрования
     * @return зашифрованныую строку
     */
    public static String getEncryptString(String stringToEncode){
        return stringToEncode;
    }
    
   

    public static void checkUser(HttpServletRequest request, HttpServletResponse response) throws UserAutentificationException {
        //проверка ид сессии пользователя,с ид сохраненной в куки
        int userID = -1;
        try{
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase("userID")){
                    userID = Integer.valueOf(cookie.getValue());
                }
            }
        }catch(NullPointerException e){
            throw new UserAutentificationException("необходимо авторизаваться");
        }
        
        if (userID < 0){
            throw new UserAutentificationException("Добро пожаловать");
        }
        
        String df = request.getParameter("showdone");           //ХХХ почемуто не работает.
                                                                //входит сюда только один раз при авторизации. А потом нет...
        boolean showDone = Boolean.getBoolean(request.getParameter("showdone"));
        if (showDone){
                    //default value. user can change it
             Cookie userShowDoneTask = new Cookie("ShowDoneTask", "true");
             response.addCookie(userShowDoneTask); 
        }

    }
    
    /**
     * получает ид пользователя на уровне интернет сианса (ид сохраненное в сеансе, в куках)
     * @param request
     * @return
     * @throws UserAutentificationException 
     */
    public static int getUserId(HttpServletRequest request)throws UserAutentificationException{
        int result = -1;
        try{
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equalsIgnoreCase("userID")){
                    result = Integer.valueOf(cookie.getValue());
                }
            }
        }catch(NullPointerException e){
            throw new UserAutentificationException("some exception");
        }
        if (result < 0){
            throw new UserAutentificationException("пользователь не найден.(куки в браузере должны быть включены)");
        }
        return result;
    }
    
    
    /**
     * действия выполянемые приложением в случе ошибки Exception
     * @param request
     * @param response
     * @param e
     * @throws ServletException
     * @throws IOException 
     */
    public static void caseException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException{
        dispatcherError =  request.getRequestDispatcher("/applicationError.jsp");
        if (dispatcherError != null){
               request.setAttribute("appException", e);
               dispatcherError.forward(request, response);
            }
    }

    /**
     * действия выполняемые приложением в случае UserAutentificationException исключения
     * @param request
     * @param response
     * @param e
     * @throws IOException 
     */
    public static void caseUserAutentificationException(HttpServletRequest request, HttpServletResponse response, UserAutentificationException e) throws IOException {
        request.setAttribute("passAutentification", true);
        response.sendRedirect(WebEngine.LOGIN_PATH); 
    }

    public static void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    /**
     * опция, установленая пользователем. Показывает нужно ли отображать закрытые задания
     * @param request
     * @return 
     */
    public static boolean showDone(HttpServletRequest request) {
//        String s = (String)request.getSession().getAttribute("ShowDoneTask");
//        boolean restult = Boolean.valueOf((String)request.getSession().getAttribute("ShowDoneTask"));
//        return restult;
        boolean showDone = false;
        try{
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase("ShowDoneTask")){
                    showDone = Boolean.valueOf(cookie.getValue());
                }
            }
        }catch(NullPointerException e){
        }
        return showDone;
    }

       
    
}
