<%@ page import="controller.utils.TaxRetrieve" %>
<%@ page import="static controller.utils.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 18.08.2017
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TaxRetrieve taxRetrieve = new TaxRetrieve();
%>
<html>
<head>
    <title>Головна</title>
</head>
<body>
    <h1>Розрахунок ваших податків</h1>
    Тут ви можете розрахувати розмір своїх податків відповідно до нижче приведеного їх відсоткового співвідношення до
    доходів.
    <br/>
    <br/>
    Таблиця податків:
    <table border="1">
        <tr>
            <td>Робота</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(WORK_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Авторська винагорода</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(REWARD_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Продаж майна</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(PROPERTY_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Подарунки у вигляді грошей або майна</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(GIFTS_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Переводи з-за кордону</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(TRANSFER_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Пільги на дітей</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(CHILDREN_PRIVILEGES_TAX_NAME)+ " %"%></td>
        </tr>
        <tr>
            <td>Матеріальна допомога</td>
            <td><%=taxRetrieve.retrieveTaxPercentFromDatabase(MATERIAL_AID_TAX_NAME)+ " %"%></td>
        </tr>
    </table>
    <br/>
    <br/>
    <a href="register.html">Для розрахування податків вам потрібно зареєструватися.</a>
</body>
</html>
