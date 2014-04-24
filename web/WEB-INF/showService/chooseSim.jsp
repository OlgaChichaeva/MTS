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
        <title>JSP Page</title>
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
                            <form name="Choose Sim Form" action="<%= ROOT %><%= ADD_SERVICE_TO_SIM%>" method="POST">
                                <input name="sim_id" type="hidden" value="<%= sim.getSimId()%>" />
                                <input name="ID_service" type="hidden" value="<%= request.getParameter("ID_service")%>" />
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
