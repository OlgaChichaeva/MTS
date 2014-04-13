<%-- 
    Document   : acceptLegalEntity
    Created on : 13.04.2014, 13:36:46
    Author     : Ivan
--%>

<%--
    Разрешает пользователям типа LegalEntity доступ к странице, 
    куда данная страница будет включена при помощи include
--%>
<%@page import="security.SecurityBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="security" class="security.SecurityBean" scope="request" />
<jsp:setProperty name="security" property="role" value="<%= SecurityBean.LEGAL_ENTITY%>" />
