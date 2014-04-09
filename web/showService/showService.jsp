<%--
    Document   : showService
    Created on : 21.03.2014, 12:26:01
    Author     : Ольга
--%>

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
        <jsp:include page="/header.jsp" flush="true"/>
        <%
            Object o = request.getAttribute("ServiceList");
            if (o == null) {
                out.print("asd");
                return;
            }
            List<Service> services = (List<Service>) o;
            String enteredName = request.getParameter("name_service");
            if (enteredName == null) {
                enteredName = "";
            }
            String enteredIdType = request.getParameter("ID_type");
            if (enteredIdType == null) {
                enteredIdType = "";
            }
            String enteredCost = request.getParameter("cost");
            if (enteredCost == null) {
                enteredCost = "";
            }
        %>
        <table border=1><tr><th>Название</th><th>Тип</th><th>Стоимость</th><th>Действия</th></tr>
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
                            out.print("</td>");
                    %>

            <td>
                <%= HTMLHelper.makeUpdateAndDelete("/MTSweb/ServiceUpdateForm/", "/MTSweb/ServiceDelete/", "ID_Service", service.getIdService()) %>
            </td>

            <%
                    out.print("</tr>");
                }
            %>

        </table>
        <a href="/MTSweb/ServiceAddForm/">add</a>
    </body>
</html>
