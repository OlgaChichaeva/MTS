<%-- 
    Document   : header
    Created on : 26.03.2014, 11:16:01
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
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
        <jsp:useBean id="currentUser" class="objects.User" scope="session" />
        <table class="headerlink">
            <tr>
                <td style="padding-right: 3px;" width="1px">
                    <img src="<%= ROOT%>/pics/boring.jpg" alt=":("/>
                </td>
                <td width="80%">
                    <a class="headerlink" href="<%= ROOT%>/">Home</a>                
                    <a class="headerlink" href="<%= ROOT%><%= SELECT_ALL_SERVICE%>">Услуги</a>                 
                    <a class="headerlink" href="<%= ROOT%><%= SELECT_ALL_TARIFF%>">Тарифы</a>              
                </td>
                <td>
                    <p align="right">
                        <%
                            if (currentUser.getIdRole() == SecurityBean.NOT_LOGGED) {
                                %>
                                    <a class="other" href="<%= ROOT%>/login.jsp">Войти</a>
                                <%
                            } else {
                                %>
                                    Здравствуйте, <%= currentUser.getUserName()%>
                                    <a class="other" href="<%= ROOT%><%= LOGOUT%>">Выйти</a>
                                <%
                            }
                        %>
                    </p>
                </td>
            </tr>
        </table> 
                    <hr>
    </body>
</html>
