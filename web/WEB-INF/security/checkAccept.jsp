<%-- 
    Document   : checkAccept
    Created on : 13.04.2014, 13:37:38
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%--
    Проверяет, есть ли у пользователя право просмотра страницы.
    Список ролей, имеющих доступ к странице, должен быть заполнен заранее.
    Если доступа нет, выводит соответствующее сообщение и закрывает
    поток для вывода.
--%>
<%@page import="objects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="security" class="security.SecurityBean" scope="request" />
<jsp:useBean id="currentUser" scope="session" class="objects.User" />
<%
    if ( !security.isUserAccepted(currentUser)) {
        %>
            <jsp:include page="/WEB-INF/errors/securityExceptionPage.jsp" flush="true" />
        <%
        out.close();
        return;
    }
%>
