<%-- 
    Document   : index
    Created on : 15.03.2014, 17:43:16
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="static pack.PathConstants.*" %>
<%@page import="static pack.LogManager.LOG" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <%
            switch (currentUser.getIdRole()) {
                case SecurityBean.CLIENT: {
                    response.sendRedirect(request.getContextPath() + CLIENT_HOME);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Клиент на index.jsp.");
                    }
                    return;
                }
            }
        %>
         <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER %>" />
        <jsp:include page="/WEB-INF/advert.jsp" />
    </body>
</html>
