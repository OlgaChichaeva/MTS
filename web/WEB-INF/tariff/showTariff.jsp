<%-- 
    Document   : showTariff
    Created on : 20.04.2014, 17:47:39
    Author     : Ivan
--%>

<%@page import="objects.ServiceInSim"%>
<%@page import="security.SecurityBean"%>
<%@page import="objects.Service"%>
<%@page import="objects.ServiceInTariff"%>
<%@page import="java.util.List"%>
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
        <%
            boolean acceptedToChange = !currentUser.getReadOnly();
            List<ServiceInTariff> servInTarList = (List<ServiceInTariff>) request.getAttribute("servInTarList");
            if (servInTarList == null) {
                out.print("ohh");
                return;
            }
            if (acceptedToChange) {
                String idTariff = request.getParameter("ID_tariff");
                %>
                <a class="other" href="<%=ROOT + SELECT_ALL_SERVICE%>?ID_tariff=<%=idTariff%>">Добавить услуги к тарифу</a>
                <%
            }
            if (servInTarList.isEmpty()) {
                out.print("Данный тариф не содержит услуг.");
                return;
            }%>
            <h1>Услуги, входящие в тариф <%= servInTarList.get(0).getTariff().getNameTariff()%></h1>
            <table class="select" border="1">
                <th class="select" width="70%">Услуга</th>
                <th class="select" width="30%">Действия</th>
                <%
                    for (ServiceInTariff sInt : servInTarList) {
                        Service service = sInt.getService();
                        %>
                        <tr>
                            <td class="select">
                                <%= service.getNameService()%>
                                 (<%= service.getCost()%> / <%= service.getTypeService().getMeasure()%>)
                            </td>
                            <td class="withform">
                                <%-- Если юзер - админ то разрешаем её отключить --%>
                                <%if (acceptedToChange) {
                                    %>
                                    <form name="Remove Service Form" method="POST" action="<%= ROOT%><%= REMOVE_SERVICE_FROM_TARIFF%>">
                                    <input type="hidden" name="ID_tariff" value="<%= sInt.getIdTariff()%>" />
                                    <input type="hidden" name="ID_service" value="<%= sInt.getIdService()%>" />
                                    <input type="submit" value="Отключить услугу" />
                                </form>
                                    <%
                                } else {
                                    out.print("<hr>");
                                }
                                %>
                            </td>
                        </tr>
                        <%                  
                    }
                %>
                <%
                    List<ServiceInSim> sisList = (List<ServiceInSim>) request.getAttribute("sisList");
                    if (sisList != null && !sisList.isEmpty()) {
                        %>
                        <tr><td class="select" colspan="2" align="center"><b>Услуги, подключенные к данной сим-карте:</b></td></tr>
                        <%
                        for (ServiceInSim sis : sisList) {
                            Service service = sis.getService();
                            %>
                        <tr>
                            <td class="select">
                                <%= service.getNameService()%>
                                 (<%= service.getCost()%> / <%= service.getTypeService().getMeasure()%>)
                            </td>
                            <td class="withform">
                                <form name="Remove Service Form" method="POST" action="<%= ROOT%><%= REMOVE_SERVICE_FROM_SIM%>">
                                    <input type="hidden" name="sim_id" value="<%= sis.getIdSim()%>" />
                                    <input type="hidden" name="ID_service" value="<%= sis.getIdService()%>" />
                                    <input type="submit" value="Отключить услугу" />
                                </form>
                            </td>
                        </tr><%
                        }
                    }
                %>
            </table>
    </body>
</html>
