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
        <script src="<%= ROOT %><%= HTMLHelper.CALENDAR %>" type="text/javascript"></script>
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
                        <%= HTMLHelper.makeDateTime(traffic.getDate()) %>
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
        </table><br>
    <center>
            <form name="PDF" action="<%= ROOT %><%= CREATE_TRAFFIC_PDF %>" method="POST" target="_blank">
                <fieldset class="fillform">
                    Получить отчёт в PDF<br>
                    <%
                        String idSim = request.getParameter("sim_id");
                    %>
                    <input type="hidden" value="<%= idSim %>" name="sim_id" />
                    Начало периода: <input class="fillform" type="text" value="" name="begin_date" 
                                   onfocus="this.select();lcs(this);"
                                    onclick="event.cancelBubble=true;this.select();lcs(this);" readonly/><br>
                    Конец периода: <input class="fillform" type="text" value="" name="end_date" 
                                   onfocus="this.select();lcs(this);"
                                   onclick="event.cancelBubble=true;this.select();lcs(this);" readonly/><br>
                    <input type="submit" value="Получить отчёт в PDF" /><br>
                </fieldset>
            </form>
     </center>
    </body>
</html>
