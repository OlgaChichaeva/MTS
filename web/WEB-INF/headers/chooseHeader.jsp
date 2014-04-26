
<%@page import="pack.HTMLHelper"%>
<%@page import="security.SecurityBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="currentUser" scope="session" class="objects.User" />
<%
    switch(currentUser.getIdRole()) {
        case SecurityBean.ADMIN : {
            %><jsp:include page="<%= HTMLHelper.ADMIN_HEADER %>" flush="true"/><%
            break;
        }
        case SecurityBean.CLIENT : {
            %><jsp:include page="<%= HTMLHelper.CLIENT_HEADER %>" flush="true"/><%
            break;
        }
        case SecurityBean.LEGAL_ENTITY : {
            %><jsp:include page="<%= HTMLHelper.LEGAL_HEADER %>" flush="true"/><%
            break;
        }
        default : {
            %><jsp:include page="<%= HTMLHelper.DEFAULT_HEADER %>" flush="true"/><%
            break;
        }
    }
    String messagePage = HTMLHelper.HEADERS + "/message.jsp";
%>
<jsp:include page="<%= messagePage%>" flush="true" />