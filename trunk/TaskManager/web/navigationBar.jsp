<%-- 
    Document   : navigationBar
    Created on : 29.11.2012, 14:54:25
    Author     : roman
--%>

<%@page import="Control.WebEngine"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="1000" align="center">
    <tr>
        <td align="right">
            <%=User.getUser(WebEngine.getUserId(request)).getName()%> :: 
            <a href="<%=WebEngine.TASKS_PATH%>" title="Список заданий">задания</a>|
            <a href="<%=WebEngine.USERS_PATH%>" title="Список пользователей">пользователи</a>|
            <a href="<%=WebEngine.GROUPS_PATH%>" title="Список групп">группы</a>|
            <a href="<%=WebEngine.LOGIN_PATH%>?action=logout" title="выход">выход</a>
        </td>
    </tr>
</table>