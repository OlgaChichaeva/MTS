<%--
    Document   : showService
    Created on : 21.03.2014, 12:26:01
    Author     : Ольга
--%>

<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="objects.Service"%>
<%@page import="java.util.List"%>
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
        %>
        <table border=1 class="select"><tr>
                <th class="select" width="25%">Название</th>
                <th class="select" width="25%">Тип</th>
                <th class="select" width="25%">Стоимость</th>
                <th class="select" width="25%">Действия</th>
            </tr>
            <form action="<%= ROOT %>/ServiceFilter/" method="GET">
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
                        <input type="submit" value="Filter" />
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
                <% if (acceptedToChange) { // Показываем кнопки только тогда, когда юзер имеет права для редактирования %>
                <%= HTMLHelper.makeUpdateAndDelete(ROOT+"/ServiceUpdateForm/", ROOT+"/ServiceDelete/", "ID_Service", service.getIdService())%>
                <%
                    // Если юзер вошёл и услуга опциональная, то дать возможность подключиться.
                    } else if (currentUser.getIdRole() != SecurityBean.NOT_LOGGED && service.isOptional()) {
                        %>
                        <a class="other" href="<%= ROOT%>/ChooseSim/">Подключить услугу</a>
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
        <% if (acceptedToChange) {%>
            <a href="<%= request.getContextPath()%>/ServiceAddForm/">add</a>
        <%}%>
    </body>
</html>
