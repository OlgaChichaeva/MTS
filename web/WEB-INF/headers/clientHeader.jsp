<%-- 
    Document   : userHeader
    Created on : 12.04.2014, 12:11:26
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="objects.User"%>
<%@page import="static pack.PathConstants.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentClient" scope="session" class="objects.Client" />
        <table class="headerlink">
            <tr>
                <td style="padding-right: 3px;" width="1px">
                    <img src="<%= ROOT%>/pics/boring.jpg" alt=":("/>
                </td>
                <td width="70%">
                    <a class="headerlink" href="<%= ROOT%><%=CLIENT_HOME%>">Home</a>
                    <a class="headerlink" href="<%= ROOT%><%=SELECT_ALL_SERVICE%>">Услуги</a>                 
                    <a class="headerlink" href="<%= ROOT%><%=SELECT_ALL_TARIFF%>">Тарифы</a>  
                </td>
                <td>
                    <p align="right">
                        <%= currentClient.getLastname() + " " + currentClient.getFirstname() + " " + currentClient.getMiddlename()%>
                        <a class="other" href="<%= ROOT%><%=LOGOUT%>">Выйти</a>
                    </p>
                </td>
            </tr>
        </table>
                    <hr>
    </body>
</html>
