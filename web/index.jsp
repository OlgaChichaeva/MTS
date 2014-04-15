<%-- 
    Document   : index
    Created on : 15.03.2014, 17:43:16
    Author     : Ольга
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER %>" />
        <h1>Service</h1>
    </body>
</html>
