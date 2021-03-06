<%-- 
    Document   : service
    Created on : 25.03.2014, 15:41:41
    Author     : Ольга
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="objects.TypeService"%>
<%@page import="java.util.List"%>
<%@page import="objects.Service"%>
<%@page import="static pack.PathConstants.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование услуги</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER %>" flush="true"/>
        <h2>Редактировать услугу</h2>
        <%
            Object o = request.getAttribute("TypeServiceList");
            Object serv = request.getAttribute("serviceToUpdate");
            if (o == null || serv == null) {
                out.print("asd");
                return;
            }
            Service serviceToUpdate = (Service) serv;
            List<TypeService> TypeServices = (List<TypeService>) o;
        %>

        <form class="fillform" name="Data Input Form" action="<%= ROOT%><%= SERVICE_UPDATE%>" method="POST">
            <fieldset class="fillform">
            Название улуги:<br>
            <input type="hidden" name="ID_Service" value="<%= serviceToUpdate.getIdService()%>" />
            <input class="fillform" type="text" name="name_service" value="<%= serviceToUpdate.getNameService()%>" autofocus/>
            <br>Стоимость услуги:<br>
            <input class="fillform" type="text" name="cost" value="<%= serviceToUpdate.getCost()%>" />
            <br>
            Тип услуги:<br>
            <%
                out.print("<select class=\"fillform\" name=\"ID_type\">");
                int id = serviceToUpdate.getIdType();

                for (TypeService S : TypeServices) {

                    out.print("<option value =" + S.getIdType());
                    if (S.getIdType() == id) {
                        out.print(" selected");
                    }
                    out.print(">" + S.getNameType() + "</option>");
                }
                out.print("</select>");
            %>
            <br>
            <input class="fillform" type="checkbox" name="optional" value="true" <%= serviceToUpdate.isOptional()? "checked" : ""%>/>Опционально
            <br>
            <input type="submit" value="Применить изменения" />
        </fieldset>
        </form>
    </body>
</html>
