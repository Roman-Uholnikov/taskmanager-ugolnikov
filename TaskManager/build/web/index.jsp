<%-- 
    Document   : index
    Created on : 13.11.2012, 13:09:38
    Author     : admin4eg
--%>

<%@page import="Control.WebEngine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task Manager</title>
        <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body><br><br>
        <table width="1000" align="center">
            <tr>
                <td>
                    <h1>Добро пожаловать в программу Task Manager</h1>
                    <p>Программа разработана в рамках дипломного проекта студента группы ИПЗ-12-оз
                        <b>Угольникова Романа</b>
                    </p>
                    <p>Тема диплома: <b>"Программная система для управления заданиями подчененных"</b></p>
                    <p>Со спец частью: <b>"Разработка программных модулей и базы данных средствами Java и MySQL"</b></p>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <a href="<%=WebEngine.TASKS_PATH%>">Вход</a>
                </td>
            </tr>
        </table>
        
    </body>
</html>
