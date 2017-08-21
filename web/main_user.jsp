<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 21.08.2017
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <td></td>
        </tr>
        <tr>
            <td>Авторська винагорода</td>
            <td></td>
        </tr>
        <tr>
            <td>Продаж майна</td>
            <td></td>
        </tr>
        <tr>
            <td>Подарунки у вигляді грошей або майна</td>
            <td></td>
        </tr>
        <tr>
            <td>Перекази з-за кордону</td>
            <td></td>
        </tr>
        <tr>
            <td>Пільги на дітей</td>
            <td></td>
        </tr>
        <tr>
            <td>Матеріальна допомога</td>
            <td></td>
        </tr>
    </table>
    <br/>
    Введіть ваші данні тут для їх обробки:
    <form action="" method="post">
        З/п з основної роботи в місяць: <input type="number" min="0" step="0.01" name="work"/><br/><br/>
        З/п з додаткової роботи в місяць: <input type="number" min="0" step="0.01" name="workAdditional"/><br/><br/>
        Сума грошей, отриманих як авторські винагороди, за рік: <input type="number" step="0.01" min="0" name="reward"/><br/><br/>
        Сума грошей, отриманих від продажі майна, за рік: <input type="number" step="0.01" min="0" name="property"/><br/><br/>
        Сума грошей і майна у грошовому еквіваленті, отриманих як подарунок, за рік: <input type="number" step="0.01" min="0" name="gifts"/><br/><br/>
        Сума грошей, отриманих як переказ з-за кордону, за рік: <input type="number" min="0" step="0.01" name="transfer"/><br/><br/>
        Сума грошей, отриманих як пільги на дітей, за рік: <input type="number" min="0" step="0.01" name="privileges"/><br/><br/>
        Сума грошей, отриманих як матеріальна допомога, за рік: <input type="number" min="0" step="0.01" name="matAid"/><br/><br/>
        <input type="submit" value="Розрахувати і зберегти"/><br/><br/>
    </form>
</body>
</html>
