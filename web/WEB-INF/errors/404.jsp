<%-- 
    Document   : 404
    Created on : 26.04.2014, 23:06:55
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ошибка 404</title>
        <% String ROOT = request.getContextPath();%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <h1>Ошибка 404. Страница отсутствует на сервере.</h1>
        <a class="other" href="<%= ROOT %>">На главную</a>
    </body>
</html>
