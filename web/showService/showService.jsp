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
        <table border=1><tr><th>Название</th><th>Тип</th><th>Стоимость</th><th>Действия</th>
            </tr>
            <form action="/MTSweb/ServiceFilter/" method="GET">
                <tr>
                    <td>
                        <input type="text" name="name_service" value="<%= enteredName%>" />
                    </td>
                    <td>
                        <input type="text" name="ID_type" value="<%= enteredIdType%>" />
                    </td>
                    <td>
                        <input type="text" name="cost" value="<%= enteredCost%>" />
                    </td>
                    <td>
                        <input type="submit" value="Filter" />
                    </td>
                </tr>
            </form>
            <%
                for (Service service : services) {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(service.getNameService());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(service.getTypeService().getNameType());
                    out.print("</td>");
                    out.print("<td>");
                    out.print(service.getCost());
                    out.print(" / ");
                    out.print(service.getTypeService().getMeasure());
                    out.print("</td>");
            %>

            <td>
                <% if (acceptedToChange) { // Показываем кнопки только тогда, когда юзер имеет права для редактирования %>
                <%= HTMLHelper.makeUpdateAndDelete("/MTSweb/ServiceUpdateForm/", "/MTSweb/ServiceDelete/", "ID_Service", service.getIdService())%>
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
            <a href="/MTSweb/ServiceAddForm/">add</a>
        <%}%>
    </body>
</html>
