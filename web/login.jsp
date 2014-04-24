<%-- 
    Document   : login
    Created on : 15.04.2014, 11:24:23
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="static pack.PathConstants.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body align="center">
        <jsp:useBean id="message" class="pack.MessageBean" scope="request" />
        <font color="red"><jsp:getProperty name="message" property="message" /></font>
        <form name="Login Form" action="<%= ROOT%><%= LOGIN%>" method="POST">
            Введите логин:<br>
            <input class="other" type="text" name="username" value="" autofocus=""/><br>
            Введите пароль:<br>
            <input class="other" type="password" name="password" value="" /><br>
            <input type="submit" value="Войти" />
        </form>
    </body>
</html>
