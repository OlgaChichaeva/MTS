<%-- 
    Document   : clientHome
    Created on : 18.04.2014, 9:37:27
    Author     : Ivan
--%>


<%@page import="objects.ClientContr"%>
<%@page import="java.util.List"%>
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
        <!-- Подключаем библиотеку jquery-->
        <script src="<%= ROOT%><%= HTMLHelper.JQUERY%>" type="text/javascript"></script>
        <!-- Подключаем файл showSim.js, содержащий функцию showSim()-->
        <script src="<%= ROOT%><%= HTMLHelper.JS%>/client/showSim.js" type="text/javascript"></script>

        <%= HTMLHelper.includeCSS(ROOT) %>
    </head>

        <%
        List<ClientContr> contrList = (List<ClientContr>) request.getAttribute("contrList");
        if (contrList == null) {
            %>
            <body>
            Ошибка загрузки списка договоров.<br>
            <%
            return;
        } else if (contrList.isEmpty()) {
            %>
            <body>
            Договоров нет.<br>
            <%
            return;
        }%>
    <%-- Если список не null и не пуст, то зразу загружает первую сим-карту. --%>
    <body onload="showSim(<%= contrList.get(0).getSimID()%>);">
        <table class="container"><tr>
                <td>
            
        <table class="list">

            <%
                for (ClientContr contr : contrList) {
            %>
            <tr>
                <td>
                    <%-- При нажатии на надпись загружается нужная сим-карта. --%>
                    <span class="jsonpage" title="Просмотреть информацию" onclick="showSim(<%= contr.getSimID()%>);">Сим-карта</span> 
                    <br>
                    Номер договора: <%= contr.getContrID()%>
                    <br>
                    Дата заключения: <%= contr.getBeginDate()%>
                    <br>
                    <a href="<%=request.getContextPath()%>/<%= contr.getContrDoc()%>">Скачать договор</a>
                </td>
            </tr>
            <%}%>
        </table>
        </td>
        <td valign="top">
        <div id="simlayer" >
            <%-- Слой для печати в нём. --%>
        </div> 
        </td>
        </tr></table>
    </body>
</html>
