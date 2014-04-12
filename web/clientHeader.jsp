<%-- 
    Document   : userHeader
    Created on : 12.04.2014, 12:11:26
    Author     : Ivan
--%>

<%@page import="objects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user.getIdRole() != 2) { // id_role=2 - клиент
        out.print("You shall no pass!");
        out.print("<a href=\"" + request.getContextPath() + "\">обрано</a>");
        return;
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="70%">
                    Тут будут ссылки для клиента
                </td>
                <td>
                    <p align="right">
                        Здравствуйте, <%= user.getUserName()%>
                    </p>
                </td>
            </tr>
        </table>
    </body>
</html>
