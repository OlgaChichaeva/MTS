<%-- 
    Document   : securityExceptionPage
    Created on : 24.04.2014, 21:51:41
    Author     : Ivan
--%>
<%@page import="pack.HTMLHelper"%>
<%--
    Сюда попадаем, если не обработано SecurityException.
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
            if (LOG.isTraceEnabled()) {
                LOG.trace("Ошибка доступа.", exception);
            }
        %>
        <h1>Ошибка доступа!</h1>
        У вас нет права просмотра этой страницы.
        <a class="other" href="<%= ROOT%>">На главную</a>
    </body>
</html>
