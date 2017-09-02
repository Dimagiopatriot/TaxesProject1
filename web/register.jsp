<%@ page import="controller.utils.TaxesInit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Реєстрація</title>
</head>
<body>
<form action="/Register" method="post">

    <%TaxesInit.createDefaultTaxesInDatabase();%>

    Email: <input type="email" required name="userEmail"/><br/><br/>
    Пароль: <input type="password" required name="userPass"/><br/><br/>
    <input type="submit" value="Зареєструватися"/>

    <br/><br/>
    <a href="login.jsp">Уже зареєстровані? Зайти в систему</a>

    <br/><br/>
    <a href="main_guest.jsp">Увійти, як гість</a>
    <br/><br/>
    ${sessionScope.get("errorUserRegisterMessage")}
</form>
</body>
</html>