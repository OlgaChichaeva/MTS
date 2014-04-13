<%-- 
    Document   : service
    Created on : 25.03.2014, 15:41:41
    Author     : Ольга
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="objects.TypeService"%>
<%@page import="java.util.List"%>
<%@page import="objects.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="<%= HTMLHelper.CHECK_ACCEPT %>" flush="true"/>
        <jsp:include page="<%= HTMLHelper.CHOOSE_HEADER %>" flush="true"/>
        <h1>Service</h1>
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

        <form name="Data Input Form" action="/MTSweb/ServiceUpdate/" method="POST">
            Enter name_service:<br>
            <input type="hidden" name="ID_Service" value="<%= serviceToUpdate.getIdService()%>">
            <input type="text" name="name_service" value="<%= serviceToUpdate.getNameService()%>" />

            <br>Enter cost:<br>
            <input type="text" name="cost" value="<%= serviceToUpdate.getCost()%>" />

            <br>

            Enter Type_Service:<br>
            <%
                


                out.print("<select name=\"ID_type\">");
                int id = serviceToUpdate.getIdType();

                for (TypeService S : TypeServices) {

                    out.print("<option value =" + S.getIdType());
                    if (S.getIdType() == id) {
                        out.print(" selected");
                    }
                    out.print(">" + S.getNameType() + "</option>");
                    //out.print("<option value =" + S.getMeasua()+ ">" + S.getMeasua() + "</option>");

                }

                out.print("</select>");


            %>
            <br>
            <br>
            <input type="submit" value="OK" />

        </form>
    </body>
</html>
