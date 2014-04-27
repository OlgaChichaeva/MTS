<%-- 
    Document   : showTraffic
    Created on : 26.04.2014, 20:11:34
    Author     : Ivan
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="objects.Service"%>
<%@page import="objects.Traffic"%>
<%@page import="java.util.List"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="static pack.PathConstants.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>История использования услуг</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <%
            boolean acceptedToChange = !currentUser.getReadOnly();
            List<Traffic> trafficList = (List<Traffic>) request.getAttribute("trafficList");
            if (trafficList == null) {
                out.print("ohh");
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy',' HH:mm:ss");
        %>
        <% if (acceptedToChange) {%>
        <a class="other" href="<%= request.getContextPath()%><%= ADD_TRAFFIC_FORM %>?sim_id=<%= request.getParameter("sim_id") %>">Добавить трафик</a>
        <%}%>
        <h1>История использования услуг.</h1>
        <table class="select" border="1">
            <tr>
                <th class="select" width="20%">Время</th>
                <th class="select" width="35%">Услуга</th>
                <th class="select" width="35%">Количество</th>
                <th class="select" width="10%">Стоимость</th>
            </tr>
        <%
            for (Traffic traffic : trafficList) {
                Service service = traffic.getService();
                %>
                <tr>
                    <td class="select">
                        <%= dateFormat.format(traffic.getDate()) %>
                    </td>
                    <td class="select">
                        <%= service.getNameService() %>
                    </td>
                    <td class="select">
                        <%= traffic.getAmount() %> <%= service.getTypeService().getMeasure() %>
                    </td>
                    <td class="select">
                        <%= traffic.getCost() %>
                    </td>
                </tr>
                            
                <%
            }
        %>
        </table>
    </body>
</html>
