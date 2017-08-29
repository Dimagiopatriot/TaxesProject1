<%@ page import="controller.utils.TaxRetrieve" %>
<%@ page import="static controller.utils.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 21.08.2017
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сторінка адміністратора</title>
</head>
<body>
<%
    TaxRetrieve retrieve = new TaxRetrieve();
%>
<form action="Admin" method="post">
    Встановити розмір податків у відсотках:<br/><br/>
    Робота: <input type="number" min="0" step="0.01" name="work"
                   value="<%=retrieve.retrieveTaxPercentFromDatabase(WORK_TAX_NAME)%>"/><br/><br/>
    Авторська винагорода: <input type="number" min="0" step="0.01" name="reward"
                                 value="<%=retrieve.retrieveTaxPercentFromDatabase(REWARD_TAX_NAME)%>"/><br/><br/>
    Продаж майна: <input type="number" step="0.01" min="0" name="property"
                         value="<%=retrieve.retrieveTaxPercentFromDatabase(PROPERTY_TAX_NAME)%>"/><br/><br/>
    Подарунки у вигляді грошей або майна: <input type="number" step="0.01" min="0" name="gifts"
                                                 value="<%=retrieve.retrieveTaxPercentFromDatabase(GIFTS_TAX_NAME)%>"/><br/><br/>
    Переказ коштів з-за кордону: <input type="number" min="0" step="0.01" name="transfer"
                                        value="<%=retrieve.retrieveTaxPercentFromDatabase(TRANSFER_TAX_NAME)%>"/><br/><br/>
    Пільги на дітей: <input type="number" min="0" step="0.01" name="children_privileges"
                            value="<%=retrieve.retrieveTaxPercentFromDatabase(CHILDREN_PRIVILEGES_TAX_NAME)%>"/><br/><br/>
    Матеріальна допомога: <input type="number" min="0" step="0.01" name="material_aid"
                                 value="<%=retrieve.retrieveTaxPercentFromDatabase(MATERIAL_AID_TAX_NAME)%>"/><br/><br/>
    <input type="submit" value="Змінити податки"/><br/><br/>
    <a href="main_user.jsp">Перейти на сторінку для розрахунку податків</a>
</form>
</body>
</html>
