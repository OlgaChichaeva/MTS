<%-- 
    Document   : daoExceptionPage
    Created on : 24.04.2014, 17:22:36
    Author     : Ivan
--%>
<%@page import="pack.HTMLHelper"%>
<%--
    Сюда попадаем, если не обработано DaoException.
--%>
<%@page import="static pack.LogManager.LOG" %>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ошибка базы данных</title>
        <% String ROOT = request.getContextPath();%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <%
            LOG.error("Ошибка базы данных: " + exception.getMessage(), exception);
        %>
        <h1>Произошла ошибка базы данных.</h1>
        Подробнее в логах.
        <a class="other" href="<%= ROOT%>">На главную</a>
    </body>
</html>
