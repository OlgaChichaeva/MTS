<%--
    Document   : showService
    Created on : 21.03.2014, 12:26:01
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="objects.Service"%>
<%@page import="java.util.List"%>
<%@page import="static pack.PathConstants.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Просмотр услуг</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:useBean id="currentUser" scope="session" class="objects.User" />
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <%
            Object o = request.getAttribute("ServiceList");
            if (o == null) {
                out.print("asd");
                return;
            }
            List<Service> services = (List<Service>) o;
            String enteredName = HTMLHelper.fromNull(request.getParameter("name_service"));
            String enteredIdType = HTMLHelper.fromNull(request.getParameter("ID_type"));
            String enteredCost = HTMLHelper.fromNull(request.getParameter("cost"));
            boolean acceptedToChange = !currentUser.getReadOnly();
            
            // Узнаём, пришли ли сюда со страницы тарифа, чтобы добавить услуги к тарифу.
            String idTariffString = request.getParameter("ID_tariff");
            int idTariff = -1;
            boolean fromTariff = (idTariffString != null);
            if (fromTariff) {
                idTariff = Integer.parseInt(idTariffString);
            }
        %>
        <% if (acceptedToChange) {%>
            <a class="other" href="<%= request.getContextPath()%><%= SERVICE_ADD_FORM%>">Добавить услугу</a>
        <%}%>
        <h1>Список услуг</h1>
        <table border=1 class="select"><tr>
                <th class="select" width="25%">Название</th>
                <th class="select" width="25%">Тип</th>
                <th class="select" width="25%">Стоимость</th>
                <th class="select" width="25%">Действия</th>
            </tr>
            <form action="<%= ROOT %><%= SERVICE_FILTER%>" method="GET">
                <tr>
                    <td class="withform">
                        <input class="intable" type="text" name="name_service" value="<%= enteredName%>" />
                    </td>
                    <td class="withform">
                        <input class="intable" type="text" name="ID_type" value="<%= enteredIdType%>" />
                    </td>
                    <td class="withform">
                        <input class="intable" type="text" name="cost" value="<%= enteredCost%>" />
                    </td>
                    <td class="withform">
                        <input type="submit" value="Найти" />
                    </td>
                </tr>
            </form>
            <%
                for (Service service : services) {
                    %>
                    <tr>
                        <td class="select">
                            <%= service.getNameService()%>
                        </td>
                        <td class="select">
                            <%= service.getTypeService().getNameType()%>
                        </td>
                        <td class="select">
                            <%= service.getCost()%>
                            /
                            <%= service.getTypeService().getMeasure()%>
                        </td>
                    <%
            %>

            <td class="withform">
                <% 
                    if (acceptedToChange) { // Показываем кнопки только тогда, когда юзер имеет права для редактирования 
                        int idService = service.getIdService();
                %>
                <%= HTMLHelper.makeUpdateAndDelete(ROOT+SERVICE_UPDATE_FORM, ROOT+SERVICE_DELETE, "ID_Service", idService)%>
                <%
                    if (fromTariff) {
                        %>
                        <form name="add service to tariff" action="<%= ROOT%><%= ADD_SERVICE_TO_TARIFF%>" method="POST">
                            <input type="hidden" name="ID_tariff" value="<%= idTariff%>" />
                            <input type="hidden" name="ID_service" value="<%= idService%>" />
                            <input type="submit" value="Добавить к тарифу" />
                        </form>
                        <%
                    }
                %>
                <%
                    // Если юзер вошёл и услуга опциональная, то дать возможность подключиться.
                    } else if (currentUser.getIdRole() != SecurityBean.NOT_LOGGED && service.isOptional()) {
                        %>
                        <a class="other" href="<%= ROOT%><%= CHOOSE_SIM%>?ID_service=<%= service.getIdService()%>">Подключить услугу</a>
                        <%
                    } else {
                        out.print("<hr>");
                    }
                %>
            </td>

            <%
                    out.print("</tr>");
                }
            %>

        </table>
    </body>
</html>
