<%-- 
    Document   : aplicationError
    Created on : 20.11.2012, 16:25:46
    Author     : admin4eg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
Exception appException = (Exception)request.getAttribute("appException");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><br><br><br><br>
    <center><h1>Приносим свои извенения, ошибка</h1><br>
            <p>В Веб приложении произошел неожиданный сбой.
                Если данная ситуаци будет повторятся обратитесь к системному администратору.
            </p><br><br><br><br>
            <% try{ %>
            <h2>Класс ошибки
                <%=appException.getClass()%>
            </h2>
            <% }catch(Exception e){} %>
            <br> <div align="left">
            <% try{ %>
            <pre>
            <%=appException.getMessage() %>
            </pre>
            <% }catch(Exception e){} %>
            <br><% try{ %>
            <pre><%=appException.getLocalizedMessage()%>
                <% }catch(Exception e){} %></pre></div>
        </center>
    </body>
</html>
