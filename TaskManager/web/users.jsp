<%-- 
    Document   : taskComment
    Created on : 13.11.2012, 14:12:33
    Author     : admin4eg
--%>

<%@page import="Model.DAO"%>
<%@page import="Model.Group"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<User> users = (List<User>)request.getAttribute("users");
List<Group> groups = (List<Group>)request.getAttribute("groups");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<%=WebEngine.APPLICATION_PATH%>style.css" rel="stylesheet" type="text/css">
    </head>
    <body><br>
        <%@ include file="navigationBar.jsp" %>
        <table class="xxx" width="1000" align="center" border="3"  cellpadding="10">
            <tr class="xxx">
                <td colspan="3">
                    <h1>Пользователи</h1>
                </td>
                <td colspan="2" class="search">
                    <form action="<%=WebEngine.USERS_PATH%>" name="search" method="GET">
                        <input type="text" name="search" size="20"
                               <% if (request.getParameter("search") != null){ %>
                               value="<%=request.getParameter("search")%>"
                               <% } %>
                               >
                    <input type="submit" value="поиск">
                    </form>
                </td>
            </tr>
            <tr class="xxx">
                <td>
                    <a href="<%=WebEngine.USERS_PATH%>?order=name">Имя</a>
                </td>
                <td>
                    <a href="<%=WebEngine.USERS_PATH%>?order=group">группа (отдел)</a>
                </td>
                <td>
                    <a>телефон</a>
                </td>
                <td>
                    <a>кабинет</a>
                </td>
                <td>
                    <a>координатор группы</a>
                </td>
               
            </tr>
            <%
            for(User user: users){
                Group group = null;
                for(Group curGroup: groups){
                    if(curGroup.getId() == user.getUserID()){
                        group = curGroup;
                    }
                }
            %>
            <tr class="xxx">
                <td>
                    <a><%=user.getName()%></a>
                </td>
                <td>
                    <a><% if(group != null) out.print(group.getName()); %></a>
                </td>
                <td>
                    <a><%=user.getPhone()%></a>
                </td>
                <td>
                    <a><%=user.getLocation()%></a>
                </td>
                <td align="center">
                    <% if(user.getRights() == 2){ //XXX delete magic 2 %> 
                    да
                    <% }else{ %>
                    нет
                    <% } %>
                </td>
               
            </tr>
            <% } %>
            <form action="<%=WebEngine.USERS_PATH%>" name="add" method="GET">
            <tr class="xxx">
                <td>
                    <input type="text" name="addname" size="20">
                </td>
                <td>
                    <select name="addgroup">
                        <%
                        for(Group group: groups){
                        %>
                        <option value="<%=group.getId()%>"><%=group.getName()%></option>
                        <% } %>
                    </select>
                    <a href="<%=WebEngine.GROUPS_PATH%>" target="_blank">группы</a> 
                    
                </td>
                <td>
                    <input type="text" name="addphone" size="20">
                </td>
                <td>
                    <input type="text" name="addroom" size="20">
                </td>
                <td align="center">
                    <input type="checkbox" name="addcontroller">
                </td>
            </tr>
            <tr>
                <td colspan="5" align="center">
                    <input type="submit" name="adduser" value="добавить">
                </td>
            </tr>
            </form>
        </table>
    </body>
</html>
