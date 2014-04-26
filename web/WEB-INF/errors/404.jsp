<%-- 
    Document   : 404
    Created on : 26.04.2014, 23:06:55
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ошибка 404. Страница отсутствует на сервере.</h1>
        <a class="other" href="<%= request.getContextPath() %>">На главную</a>
    </body>
</html>
