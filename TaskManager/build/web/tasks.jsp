<%-- 
    Document   : tasks
    Created on : 17.11.2012, 13:42:01
    Author     : admin
--%>

<%@page import="Model.Task"%>
<%@page import="java.util.List"%>
<% List<Task> tasks = (List<Task>)request.getAttribute("tasks");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Задания XXX</title>
    <link href="<%=WebEngine.APPLICATION_PATH%>style.css" rel="stylesheet" type="text/css">
    </head>
    <body class="body1"><br>
    
        <%@ include file="navigationBar.jsp" %>
        <table class="xxx" width="1000" align="center" border="3" cellpadding="10">
            <form action="Tasks" method="GET">
            <tr>
                <td colspan="7">
                    <h1>Окно заголовка</h1>
                    <% if(true) {
                        out.print("<H3>текст ошибки</H3>");
                    }
                    %>
                    
                </td>
            </tr>
            
            <tr>
                <td>
                    <a href="Tasks?order=custumer" title="сортировать по инициатору">инициатор</a>,
                    <a href="Tasks?order=date" title="сортировать по дате">дата</a>
                </td>
                <td>
                    тема
                </td>
                <td>
                    локация
                </td>
                <td>
                    тип задания
                </td>
                <td>
                    коментарии
                </td>
                <td>
                    <a  href="Tasks?showdone=true" title="показывать закрытые заявки">закрыта</a>
                </td>
                <td>
                    применить к
                </td>
            </tr>
            <% if ((tasks.size() == 0)){ %> <!-- может будет надо добавить еще проверку на количество -->
            <tr><td colspan="7"><h2>Очередь заявок пуста</h2></td></tr>
            
            <% }else{
            for(Task task: tasks){ 
            String priority;
            switch (task.getPriority()){
                              case 0 : priority = "LowPriority"; break;
                              case 1 : priority = "MiddlePriority"; break;
                              default: priority = "HightPriority";
            if (task.getCloseDate()!=null){
                priority = "ClosedPriority";
            }
            } %>
            <tr>
                <td class="<%=priority%>">
                    <%=task.getCreateDate().toLocaleString()%>
                    <a class="lec_list" title="<%=task.getCostumer()%>"><%=task.getCostumer()%></a>
                </td>
                <td>
                    <a class="xxx" href="Task?id=<%=task.getId()%>"><b><%=task.getTitle()%></b></a>
                    <% if(task.getText().length() < 36){ %>
                    <%=task.getText()%>
                    <% }else{ %>
                    <%=task.getText().substring(0, 35)%>
                    <% } %>
                </td>
                <td>
                    <%=task.getLocalization()%>
                </td>
                
                    <% if(task.isGrouptype()){ %>
                    <td class="grouptype">групповое
                    <% }else{ %>
                    <td class="individualtype">индивидуальное
                    <% } %>
                </td>
                <td><pre><%=task.getComment()%></pre>
                </td>
                <td>
                    <% if (task.getCloseDate() != null){ %>
                    <%=task.getCloseDate().toLocaleString()%>
                    <% }else{%>
                    &nbsp;
                    <% } %>
                </td>
                <td>
                    <input type="checkbox" name="item" value="<%=String.valueOf(task.getId())%>">
                </td>
            </tr>
            
            
            <%   } %>
            
            <tr>
                <td colspan="2">
                    груповое действие
                    <select name="action">
                        <option value="addComent">добавить коментарий</option>
                        <option value="forward"> переслать</option>
                        <option value="close"> закрыть </option>
                    </select>
                    <br>
                    получатель
                    <select name="reciver">
                        <option value="-1"></option>
                        <option value="3457">Угльников Роман</option>
                        <option value="3567"> Лобода Юлия</option>
                        <option value="5235"> Пользоватлеь 1</option>
                    </select>
                    </td>
                    <td>
                        <input type="submit" value="действие">
                    </td> 
                    <td colspan="4">
                        группой комментарий<p><textarea rows="2" cols="60" name="commenttext"></textarea>
                    </td> 
            </tr>
            <% } %>
            </form>
        </table>
    </body>
</html>