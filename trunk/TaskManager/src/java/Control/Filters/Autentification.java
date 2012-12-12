/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Filters;

import Control.Exceptions.UserAutentificationException;
import Control.WebEngine;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Роман
 */
public class Autentification implements Filter {

  private FilterConfig fc;

  
  public void init(FilterConfig config) throws ServletException {
    this.fc = config;
  }

  
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
  
    
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

   
    try{
        //проверка сессии пользователя, куки, или както так
        WebEngine.checkUser(request);
        //если все нормально, идем дальше к приложению
        chain.doFilter(req, resp);
    }catch(UserAutentificationException e){        
        WebEngine.caseUserAutentificationException(request, response, e);
    }catch(Exception e){
        WebEngine.caseException(request, response, e);
    }
  }


    @Override
    public void destroy() {
        //destroy
    }
}

