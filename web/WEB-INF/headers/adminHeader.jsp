<%-- 
    Document   : header
    Created on : 26.03.2014, 11:16:01
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
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
                <td width="80%">
                    <a class="headerlink" href="<%= ROOT%>/">Home</a>                
                    <a class="headerlink" href="<%= ROOT%><%= SELECT_ALL_SERVICE%>">Услуги</a>                 
                    <a class="headerlink" href="<%= ROOT%><%= SELECT_ALL_TARIFF%>">Тарифы</a> 
                    <a class="headerlink" href="<%= ROOT%><%=CHOOSE_SIM%>?forTraffic=true">История</a>
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Type service</a>                 
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Sim</a>                              
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Tariff List</a>                             
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Sim contract</a>                      
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Phone number</a>             
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Legal entity</a>
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Legal contract</a>
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Client</a>
                    <a class="headerlink" href="<%= ROOT%>/notReady.jsp">Client contract</a>
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
