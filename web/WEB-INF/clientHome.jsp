<%-- 
    Document   : clientHome
    Created on : 18.04.2014, 9:37:27
    Author     : Ivan
--%>


<%@page import="objects.Tariff"%>
<%@page import="objects.Sim"%>
<%@page import="objects.PhoneNumber"%>
<%@page import="java.util.Map"%>
<%@page import="objects.ClientContr"%>
<%@page import="pack.HTMLHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%= HTMLHelper.ACCEPT_CLIENT%>" flush="true"/>
<jsp:include page="<%= HTMLHelper.CHECK_ACCEPT%>" flush="true"/>
<jsp:include page="<%= HTMLHelper.CHOOSE_HEADER%>" />
<jsp:useBean id="currentClient" scope="session" class="objects.Client" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% String ROOT = request.getContextPath();%>
        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>

        <%
        Map<ClientContr, PhoneNumber> phonesMap = ( Map<ClientContr, PhoneNumber>) request.getAttribute("phonesMap");
        if (phonesMap == null) {
            %>
            <body>
            Ошибка загрузки списка договоров.<br>
            <%
            return;
        } else if (phonesMap.isEmpty()) {
            %>
            <body>
            Договоров нет.<br>
            <%
            return;
        }%>
        <table class="select" border="1"><tr>
            <th class="select" width="50%">Договор</th>
            <th class="select" width="50%">Сим-карта</th>
            </tr>
            <%
                for (Map.Entry<ClientContr, PhoneNumber> entry : phonesMap.entrySet()) {
                    ClientContr contr = entry.getKey();
                    PhoneNumber number = entry.getValue();
                    Sim sim = number.getSim();
                    %>
                    <tr>
                        <td class="info">
                            Номер договора: <%= contr.getContrID()%><br>
                            Дата заключения: <%= contr.getBeginDate()%><br>
                            <a class="other" href="<%= contr.getContrDoc()%>">Скачать договор</a>
                        </td>
                        <td class="info">
                            Телефон: <%= HTMLHelper.phoneToString(number.getNumber())%><br>
                            Баланс: <%= sim.getAccount()%><br>
                            <% Tariff tariff = sim.getTariff();%>
                            Тариф: 
                            <a class="other" href="<%= ROOT %>/ShowTariff/?ID_tariff=<%= tariff.getIdTariff()%>">
                                <%= tariff.getNameTariff()%>
                            </a>
                        </td>
                    </tr>
                    <%
                }
            %>
        </table>
    </body>
</html>
