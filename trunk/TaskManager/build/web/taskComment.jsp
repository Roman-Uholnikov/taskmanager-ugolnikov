<%-- 
    Document   : taskComment
    Created on : 13.11.2012, 14:12:33
    Author     : admin4eg
--%>

<%@page import="Model.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Task task = (Task)request.getAttribute("task");
            String priority;
            switch (task.getPriority()){
                              case 0 : priority = "LowPriority"; break;
                              case 1 : priority = "MiddlePriority"; break;
                              default: priority = "HightPriority";
            if (task.getCloseDate()!=null){
                priority = "ClosedPriority";
            }
            } 
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<%=WebEngine.APPLICATION_PATH%>style.css" rel="stylesheet" type="text/css">
    </head>
    <body><br>
        <form action="xxx" method="GET">
        <%@ include file="navigationBar.jsp" %>
        <table class="xxx" width="1000" align="center" border="3" cellpadding="10">
            <tr class="xxx">
                <td colspan="2" class="<%=priority%>">
                    <br><h3>заявка №<i><%=task.getId()%></i> <%=task.getTitle()%> </h3>
                    <h3><%=task.getCreateDate().toLocaleString()%> <a class="xxx" title="<%=task.getCostumer()%>"><%=task.getCostumer().getName()%></a>.</h3>
                    
                    <% if(task.getCloseDate() != null){ %>
                    
                    <p><h3>закрыта  <%=task.getCloseDate().toLocaleString() %> </h3>
                    
                    <% } %>
                    
                    <p><%=task.getText()%></p>
                </td>
            </tr>
            <tr class="xxx">
                <td colspan="2">
                    <pre class="comment"><%=task.getComment()%></pre>
                </td>
                
            </tr>
            <tr>
                <td>
                    <a class="xxx1"><%=new java.util.Date().toLocaleString()%></a>
                </td>
                <td>
                    <textarea rows="6" cols="60" name="commenttext"></textarea><br>
                    <input type="submit" name="close" value="close">
                    <input type="submit" name="add" value="add">
                    <input type="submit" name="forward" value="переслать на">
                    <select name="reciver">
                        <option value="254345">sdcxgfdghfghfghf</option>
                        <option value="254345">sdfghfghgfhfghghgf</option>
                        <option value="254345">sdghghghghf</option>
                    </select> 
                    <a href="Users" target="blank"><img src="images/users.JPG" alt="найти пользователя"></a>
                </td>
            </tr>
        </table>
        </form>
    </body>
</html>
