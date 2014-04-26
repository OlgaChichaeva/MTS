<%-- 
    Document   : notReady
    Created on : 26.04.2014, 23:02:41
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Мы работаем над этим</title>
        <% String ROOT = request.getContextPath();%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <h1>Страницы пока нет. Мы работаем над этим.</h1>
        <a class="other" href="<%= request.getContextPath() %>">На главную</a>
    </body>
</html>
