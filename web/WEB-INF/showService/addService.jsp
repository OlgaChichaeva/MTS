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
        <title>Добавление услуги</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>
    <body>
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER %>" flush="true"/>
        <h2>Добавить услугу</h2>
        <form class="fillform" name="Data Input Form" action="<%= ROOT%><%= SERVICE_ADD%>" method="POST">
            <fieldset class="fillform">
            Название услуги:<br>
            <input class="fillform" type="text" name="name_service" value="" autofocus/>
            <br>Стоимость услуги:<br>
            <input class="fillform" type="text" name="cost" value="" />          
            <br>           
            Тип услуги:<br>
            <%
                Object o = request.getAttribute("TypeServiceList");
                if (o == null) {
                    out.print("jkjhj");
                    return;
                }
                List<TypeService> TypeServices = (List<TypeService>) o;
                out.print("<select class=\"fillform\" name=\"ID_type\">");
                for (TypeService S : TypeServices) {
                    out.print("<option value =" + S.getIdType() + ">" + S.getNameType() + "</option>");
                }
                out.print("</select>");
            %>
            <br>
            <input class="fillform" type="checkbox" name="optional" value="true" />Опционально
            <br>
            <input type="submit" value="Добавить" />
            </fieldset>
        </form>
    </body>
</html>
