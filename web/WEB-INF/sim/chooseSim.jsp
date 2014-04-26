<%-- 
    Document   : chooseSim
    Created on : 23.04.2014, 8:44:38
    Author     : Ivan
--%>

<%@page import="objects.PhoneNumber"%>
<%@page import="java.util.Map"%>
<%@page import="objects.Sim"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="static pack.PathConstants.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Выбор сим-карты</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <h1>Выберите сим-карту:</h1>
        <%
            Map<Sim, PhoneNumber> simAndNumbers = (Map<Sim, PhoneNumber>) request.getAttribute("simAndNumbers");
            if (simAndNumbers == null) {
                return;
            }
            String pathToGo = "";
            String hiddenId = "";
            if (request.getParameter("ID_service") != null) {
                pathToGo = ADD_SERVICE_TO_SIM;
                hiddenId = "<input name=\"ID_service\" type=\"hidden\" value=\""+request.getParameter("ID_service") + "\" />";
            } else if (request.getParameter("ID_tariff") != null) {
                pathToGo = CHANGE_TARIFF;
                hiddenId = "<input name=\"ID_tariff\" type=\"hidden\" value=\""+request.getParameter("ID_tariff") + "\" />";
            } else if (request.getParameter("forTraffic") != null) {
                pathToGo = SHOW_TRAFFIC;
                //hiddenId = "<input name=\"ID_tariff\" type=\"hidden\" value=\""+request.getParameter("ID_tariff") + "\" />";
            }
        %>
        <table class="select" border="1">
            <th class="select" width="70%">Сим-карта</th>
            <th class="select" width="30%">Действия</th>
            <%
                for (Map.Entry<Sim, PhoneNumber> entry : simAndNumbers.entrySet()) {
                    Sim sim = entry.getKey();
                    %>
                    <tr>
                        <td class="info">
                            <%= HTMLHelper.phoneToString(entry.getValue().getNumber())%>
                             (№ сим-карты: <%= sim.getSimId()%>)
                        </td>
                        <td class="withform">
                            <form name="Choose Sim Form" action="<%= ROOT %><%= pathToGo%>" method="POST">
                                <input name="sim_id" type="hidden" value="<%= sim.getSimId()%>" />
                                <%= hiddenId %>
                                <input type="submit" value="Выбрать" />
                            </form>
                        </td>
                    </tr>
                    <%
                }
            %>
        </table>
    </body>
</html>
