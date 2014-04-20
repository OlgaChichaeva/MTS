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
                    <a class="headerlink" href="<%= ROOT%>/SelectAllService/">Услуги</a>                 
                    <a class="headerlink" href="<%= ROOT%>/SelectAllTariff/">Тарифы</a>               
                    <a class="headerlink" href="<%= ROOT%>/showService/">Type service</a>                 
                    <a class="headerlink" href="<%= ROOT%>/showService/">Sim</a>                 
                    <a class="headerlink" href="<%= ROOT%>/showService/">Traffic</a>                
                    <a class="headerlink" href="<%= ROOT%>/showService/">Tariff List</a>                             
                    <a class="headerlink" href="<%= ROOT%>/showService/">Sim contract</a>                      
                    <a class="headerlink" href="<%= ROOT%>/showService/">Phone number</a>             
                    <a class="headerlink" href="<%= ROOT%>/showService/">Legal entity</a>
                    <a class="headerlink" href="<%= ROOT%>/showService/">Legal contract</a>
                    <a class="headerlink" href="<%= ROOT%>/showService/">Client</a>
                    <a class="headerlink" href="<%= ROOT%>/showService/">Client contract</a>
                </td>
                <td>
                    <p align="right">
                        Здравствуйте, <%= currentUser.getUserName()%>
                        <a class="other" href="<%= ROOT%>/Logout/">Выйти</a>
                    </p>
                </td>
            </tr>
        </table>   
                    <hr>
    </body>
</html>
