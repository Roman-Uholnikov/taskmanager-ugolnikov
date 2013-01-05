<%-- 
    Document   : groups
    Created on : 13.11.2012, 14:12:33
    Author     : admin4eg
--%>

<%@page import="Control.Exceptions.UserInputException"%>
<%@page import="Model.DAO"%>
<%@page import="Model.Group"%>
<%@page import="java.util.List"%>
<%@page import="Control.WebEngine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Group> groups = (List<Group>)request.getAttribute("groups");
List<User> users = (List<User>)request.getAttribute("users");
UserInputException userException = (UserInputException)request.getAttribute("userException");
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
        <form action="<%=WebEngine.GROUPS_PATH%>" name="addgroup" method="GET">
        <table class="xxx" width="1000" align="center" border="3" cellpadding="10">
            <tr class="xxx">
                <td colspan="3">
                    <h1>Группы</h1>
                    <% if(userException!=null) {
                        out.print("<H3>"+userException+"</H3>");
                    }
                    %>
                </td>
            </tr>
            <tr class="xxx">
                <td>
                    <a class="xxx">группа (отдел)</a>
                </td>
                <td>
                    <a>описание</a>
                </td>
                <td>
                    <a>координатор группы</a>
                </td>
            </tr>
            <%
            for(Group group: groups){
                User manager = null;
                for(User user: users){
                    if(user.getGroupId() == group.getId()){         //если пользователь входит в группу
                        if(user.getRights() == User.MANGER_RIGHTTS){//и имеет права координатора
                            manager = user;
                        }
                    }
                }
            %>
            <tr class="xxx">
                <td>
                    <a class="XXX"><%=group.getName()%></a>
                </td>
                <td>
                    <a><%=group.getFullname()%></a>
                </td>
                <td>
                    <% if(manager != null){ %>
                    <a class="lec_list" title="<%=manager%>"><%=manager.getName()%></a>
                    <% }else{ %>
                    нет координатора
                    <% } %>
                </td>
            </tr>
            
            <% } %>
            
            <tr class="xxx">
                <td>
                    <input type="text" name="groupname" size="20">
                </td>
                <td>
                    <input type="text" size="40" name="grouptitle">
                </td>
                <td>
                    <select name="groupmanager">
                        <option value="empty"></option>
                        <%
                        for(User user : users){
                        %>
                        <option value="<%=user.getUserID()%>"><%=user.getName()%></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="3" align="center"><input type="submit" value="добавить"></td>
            </tr>
        </table>
        </form>
    </body>
</html>
