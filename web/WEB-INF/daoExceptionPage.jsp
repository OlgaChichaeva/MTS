<%-- 
    Document   : daoExceptionPage
    Created on : 24.04.2014, 17:22:36
    Author     : Ivan
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            PrintWriter pw = response.getWriter();
            exception.printStackTrace(pw);
        %>
    </body>
</html>
