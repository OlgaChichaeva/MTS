<%-- 
    Document   : addTraffic
    Created on : 27.04.2014, 11:54:24
    Author     : Ivan
--%>

<%@page import="objects.Service"%>
<%@page import="objects.ServiceInSim"%>
<%@page import="java.util.List"%>
<%@page import="objects.ServiceInTariff"%>
<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="static pack.PathConstants.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить трафик</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <%
            List<ServiceInTariff> sitList = (List<ServiceInTariff>) request.getAttribute("serviceInTariffList");
            List<ServiceInSim> sisList = (List<ServiceInSim>) request.getAttribute("serviceInSimList");
        %>
        <h2>Добавить трафик</h2>
        <form class="fillform" name="Data Input Form" action="<%= ROOT%><%= ADD_TRAFFIC %>" method="POST">
            <fieldset class="fillform">
                Выберите услугу:<br>
                <select class="fillform" name="ID_service">
                    <%
                        for (ServiceInTariff sit : sitList) {
                            Service service = sit.getService();
                            %>
                            <option value="<%= service.getIdService() %>"><%= service.getNameService() %></option>
                            <%
                        }
                        for (ServiceInSim sis : sisList) {
                            Service service = sis.getService();
                            %>
                            <option value="<%= service.getIdService() %>"><%= service.getNameService() %></option>
                            <%
                        }
                    %>
                </select>

                <br>Введите количество:<br>
                <input class="fillform" type="text" name="amount" value=""/>

                <br>
                Дата/время пока будут текущие
                <br>
                <input type="submit" value="Добавить" />
            </fieldset>
        </form>
    </body>
</html>
