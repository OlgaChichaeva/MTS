<%-- 
    Document   : clientHome
    Created on : 18.04.2014, 9:37:27
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%= HTMLHelper.ACCEPT_CLIENT%>" flush="true"/>
<jsp:include page="<%= HTMLHelper.CHECK_ACCEPT%>" flush="true"/>
<jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" />
<jsp:useBean id="currentUser" scope="session" class="objects.User" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
    </body>
</html>
