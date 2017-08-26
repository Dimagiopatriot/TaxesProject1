<%@ page import="model.dao.TaxDaoInterface" %>
<%@ page import="model.dao.impl.TaxDao" %>
<%@ page import="static controller.utils.Constants.*" %>
<%@ page import="java.util.Optional" %>
<%@ page import="model.entities.taxes.Tax" %><%--
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
    TaxDaoInterface taxDao = new TaxDao();
%>
<%!
    private double checkTaxPercent(Optional<Tax> tax){
        if (tax.isPresent()){
            return tax.get().getTaxPercent();
        }
        return 0.;
    }
%>
<form action="Admin" method="post">
    Встановити розмір податків у відсотках:<br/><br/>
    Робота: <input type="number" min="0" step="0.01" name="work"
                   value="<%=checkTaxPercent(taxDao.selectByName(WORK_TAX_NAME))%>"/><br/><br/>
    Авторська винагорода: <input type="number" min="0" step="0.01" name="reward"
                                 value="<%=checkTaxPercent(taxDao.selectByName(REWARD_TAX_NAME))%>"/><br/><br/>
    Продаж майна: <input type="number" step="0.01" min="0" name="property"
                         value="<%=checkTaxPercent(taxDao.selectByName(PROPERTY_TAX_NAME))%>"/><br/><br/>
    Подарунки у вигляді грошей або майна: <input type="number" step="0.01" min="0" name="gifts"
                                                 value="<%=checkTaxPercent(taxDao.selectByName(GIFTS_TAX_NAME))%>"/><br/><br/>
    Переказ коштів з-за кордону: <input type="number" min="0" step="0.01" name="transfer"
                                        value="<%=checkTaxPercent(taxDao.selectByName(TRANSFER_TAX_NAME))%>"/><br/><br/>
    Пільги на дітей: <input type="number" min="0" step="0.01" name="children_privileges"
                            value="<%=checkTaxPercent(taxDao.selectByName(CHILDREN_PRIVILEGES_TAX_NAME))%>"/><br/><br/>
    Матеріальна допомога: <input type="number" min="0" step="0.01" name="material_aid"
                                 value="<%=checkTaxPercent(taxDao.selectByName(MATERIAL_AID_TAX_NAME))%>"/><br/><br/>
    <input type="submit" value="Змінити податки"/><br/><br/>
    <a href="main_user.jsp">Перейти на сторінку для розрахунку податків</a>
</form>
</body>
</html>
