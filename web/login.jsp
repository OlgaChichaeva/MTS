<%-- 
    Document   : login
    Created on : 15.04.2014, 11:24:23
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="static pack.PathConstants.*"%>
<!DOCTYPE html>
<% 
    // Делаем logout
    request.getSession(true).setAttribute("currentUser", null);
%>
<jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT)%>
    </head>
    <body>
 
            
            <jsp:useBean id="message" class="pack.MessageBean" scope="request" />
            <font color="red"><jsp:getProperty name="message" property="message" /></font>
            <form class="fillform" name="Login Form" action="<%= ROOT%><%= LOGIN%>" method="POST">
                <fieldset class="fillform">
                Логин:<br>
                <input class="fillform" type="text" name="username" value="" autofocus=""/><br>
                Пароль:<br>
                <input class="fillform" type="password" name="password" value="" /><br>
                <input type="submit" value="Войти" />
                </fieldset>
            </form>

    </body>
</html>
