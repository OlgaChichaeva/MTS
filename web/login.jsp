<%-- 
    Document   : login
    Created on : 15.04.2014, 11:24:23
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="Login Form" action='<%= request.getContextPath() + "/Login/"%>' method="POST">
            Введите логин:<br>
            <input type="text" name="username" value="" /><br>
            Введите пароль:<br>
            <input type="password" name="password" value="" /><br>
            <input type="submit" value="Войти" />
        </form>
    </body>
</html>
