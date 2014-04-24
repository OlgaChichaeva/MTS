<%-- 
    Document   : userHeader
    Created on : 12.04.2014, 12:11:26
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="objects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="static pack.PathConstants.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" class="objects.User" scope="session" />
        <table class="headerlink">
            <tr>
                <td style="padding-right: 3px;" width="1px">
                    <img src="<%= ROOT%>/pics/boring.jpg" alt=":("/>
                </td>
                <td width="70%">
                    <a class="headerlink" href="<%= ROOT%>/">Home</a>
                    <a class="headerlink" href="<%= ROOT%><%= SELECT_ALL_SERVICE%>">Service</a>
                </td>
                <td>
                    <p align="right">
                        Здравствуйте, <%= currentUser.getUserName()%>
                        <a class="other" href="<%= ROOT%><%= LOGOUT%>">Выйти</a>
                    </p>
                </td>
            </tr>
        </table>
                    <hr>
    </body>
</html>
