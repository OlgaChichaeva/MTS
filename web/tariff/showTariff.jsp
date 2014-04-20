<%-- 
    Document   : showTariff
    Created on : 20.04.2014, 17:47:39
    Author     : Ivan
--%>

<%@page import="objects.ServiceInTariff"%>
<%@page import="java.util.List"%>
<%@page import="pack.HTMLHelper"%>
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
            List<ServiceInTariff> servInTarList = (List<ServiceInTariff>) request.getAttribute("servInTarList");
            if (servInTarList == null) {
                out.print("ohh");
                return;
            }
            if (servInTarList.isEmpty()) {
                out.print("Данный тариф не содержит услуг.");
                return;
            }
        %>
        <h1>Услуги, входящие в тариф <%= servInTarList.get(0).getTariff().getNameTariff()%></h1>
        <table class="select" border="1">
            <%
                for (ServiceInTariff sInt : servInTarList) {
                    %>
                    <tr>
                        <td class="select">
                            <%= sInt.getService().getNameService()%>
                        </td>
                    </tr>
                    <%
                }
            %>
        </table>
    </body>
</html>
