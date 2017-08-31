<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 20.08.2017
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Вхід</title>
</head>
<body >
<form action="Login" method="post">
    Email: <input type="email" name="userEmail"/><br/><br/>
    Пароль: <input type="password" name="userPass"/><br/><br/>
    <input type="submit" value="Увійти"/>
    <br/><br/>
    ${sessionScope.get("errorLoginMessage")}
</form>
</body>
</html>
