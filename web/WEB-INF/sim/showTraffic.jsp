<%-- 
    Document   : showTraffic
    Created on : 26.04.2014, 20:11:34
    Author     : Ivan
--%>

<%@page import="objects.Service"%>
<%@page import="objects.Traffic"%>
<%@page import="java.util.List"%>
<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <%
            List<Traffic> trafficList = (List<Traffic>) request.getAttribute("trafficList");
            if (trafficList == null) {
                out.print("ohh");
                return;
            }
        %>
        <h1>История использования услуг.</h1>
        <table class="select" border="1">
            <tr>
                <th class="select" width="20%">Время</th>
                <th class="select" width="35%">Услуга</th>
                <th class="select" width="35%">Количество</th>
                <th class="select" width="10%">Стоимость</th>
            </tr>
        </table>
        <%
            for (Traffic traffic : trafficList) {
                Service service = traffic.getService();
                %>
                <tr>
                    <td class="select">
                        <%= traffic.getDate() %>
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
    </body>
</html>
