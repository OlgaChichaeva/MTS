<%-- 
    Document   : message
    Created on : 26.04.2014, 18:08:07
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="pack.MessageBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT)%>
    </head>
    <body>
        <%
            MessageBean message = (MessageBean) request.getAttribute(MessageBean.ATTR_NAME);
            if (message == null) {
                return;
            }
        %>
        <label class="infoMessage">
            <%= message.getMessage()%>
        </label>
    </body>
</html>
