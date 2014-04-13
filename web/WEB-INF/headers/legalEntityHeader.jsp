<%-- 
    Document   : userHeader
    Created on : 12.04.2014, 12:11:26
    Author     : Ivan
--%>

<%@page import="pack.HTMLHelper"%>
<%@page import="objects.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:useBean id="currentUser" class="objects.User" scope="session" />
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="70%">
                    Тут будут ссылки для клиента
                </td>
                <td>
                    <p align="right">
                        Здравствуйте, <%= currentUser.getUserName()%>
                    </p>
                </td>
            </tr>
        </table>
                    <hr>
    </body>
</html>
