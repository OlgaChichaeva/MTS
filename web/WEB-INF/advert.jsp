<%-- 
    Document   : advert
    Created on : 26.04.2014, 18:30:13
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <h1>Здесь может быть ваша реклама.</h1>
    </body>
</html>
