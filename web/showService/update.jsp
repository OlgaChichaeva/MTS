<%-- 
    Document   : service
    Created on : 25.03.2014, 15:41:41
    Author     : Ольга
--%>

<%@page import="TypeService.TypeService"%>
<%@page import="java.util.List"%>
<%@page import="Service.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Service</h1>
        <form name="Data Input Form" action="/MTSweb/ServiceUpdate/" method="POST">
            Enter name_service:<br>
            <input type="text" name="name_service" value="" />

            <br>Enter cost:<br>
            <input type="text" name="cost" value="" />
          
            <br>
            
            Enter Type_Service:<br>
            <%
                Object o = session.getAttribute("TypeServiceList");
                if (o == null) {
                    out.print("jkjhj");
                    return;
                }
                List<TypeService> TypeServices = (List<TypeService>) o;

                out.print("<select name=\"ID_type\">");

                for (TypeService S : TypeServices) {

                    out.print("<option value =" + S.getIdType() + ">" + S.getNameType() + "</option>");
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
