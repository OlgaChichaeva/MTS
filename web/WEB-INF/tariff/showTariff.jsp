<%-- 
    Document   : showTariff
    Created on : 20.04.2014, 17:47:39
    Author     : Ivan
--%>

<%@page import="security.SecurityBean"%>
<%@page import="objects.Service"%>
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
            boolean acceptedToChange = !currentUser.getReadOnly();
        %>
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
                        <td>
                            <%-- Если юзер - админ или услуга опциональна, то разрешаем её отключить
                            (при условии, что юзер вошёл в систему) --%>
                            <%if (acceptedToChange || (service.isOptional() && currentUser.getIdRole() != SecurityBean.NOT_LOGGED)) {
                                %>
                                Отключить услугу
                                <%
                            } else {
                                out.print("<hr>");
                            }
                            %>
                        </td>
                    </tr>
                    <%
                            
                }
                if (request.getParameter("sim_id") != null) {
                                out.print("asd");
                                
                            }
            %>
        </table>
    </body>
</html>
