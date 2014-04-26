<%-- 
    Document   : allErrors
    Created on : 24.04.2014, 21:36:28
    Author     : Ivan
--%>
<%@page import="pack.HTMLHelper"%>
<%--
    На эту страницу происходит перенаправление,
    если исключение не отловлено и другие страницы
    типа errorPage его не обрабатывают. Здесь же 
    исключение заносится в лог.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="static pack.LogManager.LOG" %>
<!DOCTYPE html>
<html>
    <head>
        <% String ROOT = request.getContextPath();%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <%
            LOG.error("Необработанная ошибка.", exception);
        %>
        <h1>Произошла чудовищная ошибка.</h1>
        Наши специалисты уже работают над решением проблемы.
        Подробнее в логах.
        <a class="other" href="<%= ROOT%>">На главную</a>
    </body>
</html>
