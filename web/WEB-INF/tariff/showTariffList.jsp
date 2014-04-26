<%--
    Document   : showService
    Created on : 21.03.2014, 12:26:01
    Author     : Ольга
--%>

<%@page import="objects.Tariff"%>
<%@page import="security.SecurityBean"%>
<%@page import="pack.HTMLHelper"%>
<%@page import="java.util.List"%>
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
            List<Tariff> tariffList = (List<Tariff>) request.getAttribute("TariffList");
            if (tariffList == null) {
                out.print("fatal error");
                return;
            }
            String enteredName = HTMLHelper.fromNull(request.getParameter("name_tariff"));
            String enteredDescription = HTMLHelper.fromNull(request.getParameter("description"));
            boolean acceptedToChange = !currentUser.getReadOnly();
        %>
        <% if (acceptedToChange) {%>
            <a class="other" href="<%= request.getContextPath()%><%= TARIFF_ADD_FORM%>">Добавить тариф</a>
        <%}%>
        <h1>Список тарифов</h1>
        <table border=1 class="select"><tr>
                <th class="select" width="25%">Название</th>
                <th class="select" width="50%">Описание</th>
                <th class="select" width="25%">Действия</th>
            </tr>
            <form action="<%= ROOT %><%= TARIFF_FILTER %>" method="GET">
                <tr>
                    <td class="withform">
                        <input class="intable" type="text" name="name_tariff" value="<%= enteredName%>" />
                    </td>
                    <td class="withform">
                        <input class="intable" type="text" name="description" value="<%= enteredDescription%>" />
                    </td>
                    <td class="withform">
                        <input type="submit" value="Найти" />
                    </td>
                </tr>
            </form>
            <%
                for (Tariff tariff : tariffList) {
                    %>
                    <tr>
                        <td class="select">
                            <a class="other" href="<%= ROOT %><%= SHOW_TARIFF%>?ID_tariff=<%= tariff.getIdTariff()%>">
                                <%= tariff.getNameTariff()%>
                            </a>
                        </td>
                        <td class="select">
                            <%= tariff.getDescription()%>
                        </td>
                    <%
            %>

            <td class="withform">
                <% if (acceptedToChange) { // Показываем кнопки только тогда, когда юзер имеет права для редактирования %>
                <%= HTMLHelper.makeUpdateAndDelete(ROOT+TARIFF_UPDATE_FORM, ROOT+TARIFF_DELETE, "ID_tariff", tariff.getIdTariff())%>
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
