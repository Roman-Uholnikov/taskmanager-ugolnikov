<%-- 
    Document   : newjsp
    Created on : 03.11.2012, 22:01:30
    Author     : Роман
--%>

<%@page import="Control.Exceptions.UserAutentificationException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page XXX</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<br><br><br><br><br><br><br><br>
        <FORM id="registration" name="registration" ACTION="Login" method="post">
  <table width="300"  height="100" border="3" align="center" bordercolor="#eeeeee" BGCOLOR="#dddddd">
        <tr>
        	<td class="login">Login XXX</td>
            <td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
        	<td class="login">Password XXX</td>
            <td><input type="password" id="password" name="password"></td>
        </tr>
        <tr><td colspan="2" align="center">
        <input type="submit" value="XXX Sign in">      
        </td></tr>
        <% if (request.getAttribute("Exception")!=null) {%>
        <tr><td colspan="2">
        <p align="center" class="data"><%=(UserAutentificationException)request.getAttribute("Exception")%></p>       
        </td></tr>
		<% } %>
        </table>
        </form>
        
</body>
</html>
