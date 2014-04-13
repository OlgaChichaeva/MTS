<%-- 
    Document   : checkAccept
    Created on : 13.04.2014, 13:37:38
    Author     : Ivan
--%>

<%--
    Проверяет, есть ли у пользователя право просмотра страницы.
    Список ролей, имеющих доступ к странице, должен быть заполнен заранее.
--%>
<%@page import="objects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="security" class="security.SecurityBean" scope="request" />
<%
    User currentUser = (User) session.getAttribute("user");
    if ( !security.isUserAccepted(currentUser)) {
        out.print("You shall no pass!");
        out.print("<a href=\"" + request.getContextPath() + "\">обрано</a>");
        return;
    }
%>
