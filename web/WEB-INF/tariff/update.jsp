<%-- 
    Document   : service
    Created on : 25.03.2014, 15:41:41
    Author     : Ольга
--%>

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
        <%= HTMLHelper.includeCSS(ROOT)%>
    </head>
    <body>
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" flush="true"/>
        <jsp:useBean id="tariffToUpdate" scope="request" class="objects.Tariff"/>
        <h2>Добавить тариф</h2>
        <form class="fillform" name="Data Input Form" action="<%= ROOT%><%= TARIFF_UPDATE%>" method="POST">
            <fieldset class="fillform">
                <input type="hidden" name="ID_tariff" value="<%= tariffToUpdate.getIdTariff()%>" />
                Название тарифа:<br>
                <input class="fillform" type="text" name="name_tariff" value="<%= tariffToUpdate.getNameTariff()%>" autofocus/>

                <br>Описание тарифа:<br>
                <input class="fillform" type="text" name="description" value="<%= tariffToUpdate.getDescription()%>"/>

                <br>
                <input type="submit" value="Применить изменения" />
            </fieldset>
        </form>
    </body>
</html>
