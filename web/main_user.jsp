<%@ page import="controller.utils.IncomeRetrieve" %>
<%@ page import="static controller.utils.Constants.*" %>
<%@ page import="controller.utils.TaxRetrieve" %>
<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 21.08.2017
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TaxRetrieve taxRetrieve = new TaxRetrieve();
    IncomeRetrieve incomeRetrieve = new IncomeRetrieve((int) request.getSession().getAttribute(USER_ID));
    double d;
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
Таблиця податків <b>у відсотках</b>:
<table border="1">
    <tr>
        <td>Робота</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(WORK_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(WORK_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Авторська винагорода</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(REWARD_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(REWARD_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Продаж майна</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(PROPERTY_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(PROPERTY_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Подарунки у вигляді грошей або майна</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(GIFTS_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(GIFTS_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Переводи з-за кордону</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(TRANSFER_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(TRANSFER_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Пільги на дітей</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(CHILDREN_PRIVILEGES_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(CHILDREN_PRIVILEGES_TAX_NAME, d);%></td>
    </tr>
    <tr>
        <td>Матеріальна допомога</td>
        <td><%d = taxRetrieve.retrieveTaxPercentFromDatabase(MATERIAL_AID_TAX_NAME);%>
            <%=d%>
            <%session.setAttribute(MATERIAL_AID_TAX_NAME, d);%></td>
    </tr>
</table>
<br/>
Введіть ваші данні тут для їх обробки:
<form action="RegisteredUser" method="post">
    З/п з основної роботи в місяць: <input type="number" required min="0" step="0.01" name="work"
                                           value="<%=incomeRetrieve.retrieveIncomeValueByType(WORK_INCOME_NAME)%>"/><br/><br/>
    З/п з додаткової роботи в місяць: <input type="number" required min="0" step="0.01" name="work_add"
                                             value="<%=incomeRetrieve.retrieveIncomeValueByType(WORK_ADD_INCOME_NAME)%>"/><br/><br/>
    Сума грошей, отриманих як авторські винагороди, за рік: <input type="number" required step="0.01" min="0"
                                                                   name="reward"
                                                                   value="<%=incomeRetrieve.retrieveIncomeValueByType(REWARD_INCOME_NAME)%>"/><br/><br/>
    Сума грошей, отриманих від продажі майна, за рік: <input type="number" step="0.01" min="0"
                                                             name="property"
                                                             value="<%=incomeRetrieve.retrieveIncomeValueByType(PROPERTY_INCOME_NAME)%>"/><br/><br/>
    Сума грошей і майна у грошовому еквіваленті, отриманих як подарунок, за рік: <input type="number" required
                                                                                        step="0.01" min="0"
                                                                                        name="gifts"
                                                                                        value="<%=incomeRetrieve.retrieveIncomeValueByType(GIFTS_INCOME_NAME)%>"/><br/><br/>
    Сума грошей, отриманих як переказ з-за кордону, за рік: <input type="number" required min="0" step="0.01"
                                                                   name="transfer"
                                                                   value="<%=incomeRetrieve.retrieveIncomeValueByType(TRANSFER_INCOME_NAME)%>"/><br/><br/>
    Сума грошей, отриманих як пільги на дітей, за рік: <input type="number" required min="0" step="0.01"
                                                              name="children_privileges"
                                                              value="<%=incomeRetrieve.retrieveIncomeValueByType(CHILDREN_PRIVILEGES_INCOME_NAME)%>"/><br/><br/>
    Сума грошей, отриманих як матеріальна допомога, за рік: <input type="number" required min="0" step="0.01"
                                                                   name="material_aid"
                                                                   value="<%=incomeRetrieve.retrieveIncomeValueByType(MATERIAL_AID_INCOME_NAME)%>"/><br/><br/>
    <input type="submit" value="Розрахувати і зберегти"/>
</form>
${sessionScope.get("taxesResult")}
</body>
</html>
