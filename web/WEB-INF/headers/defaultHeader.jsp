<%-- 
    Document   : header
    Created on : 26.03.2014, 11:16:01
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:useBean id="currentUser" class="objects.User" scope="session" />
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="70%">
                    <%! private final String ROOT = HTMLHelper.ROOT;%>
                    <img src="<%= ROOT%>/boring.jpg" alt=":("/>
                    <a href="<%= ROOT%>/">Home</a>
                    <a href="<%= ROOT%>/SelectAllService/">Service</a>
                    <a href="<%= ROOT%>/showService/">Service in tariff</a>
                    <a href="<%= ROOT%>/showService/">Type service</a>
                    <a href="<%= ROOT%>/showService/">Sim</a>
                    <a href="<%= ROOT%>/showService/">Traffic</a>
                    <a href="<%= ROOT%>/showService/">Tariff List</a>
                    <a href="<%= ROOT%>/showService/">Sim contract</a>
                    <a href="<%= ROOT%>/showService/">Phone number</a>
                    <a href="<%= ROOT%>/showService/">Legal entity</a>
                    <a href="<%= ROOT%>/showService/">Legal contract</a>
                    <a href="<%= ROOT%>/showService/">Client</a>
                    <a href="<%= ROOT%>/showService/">Client contract</a> 
                </td>
                <td>
                    <p align="right">
                        <%
                            if (currentUser.getIdRole() == SecurityBean.NOT_LOGGED) {
                                %>
                                    <a href="<%= HTMLHelper.ROOT%>/login.jsp">Войти</a>
                                <%
                            } else {
                                %>
                                    Здравствуйте, <%= currentUser.getUserName()%>
                                <%
                            }
                        %>
                    </p>
                </td>
            </tr>
        </table>     
    </body>
</html>
