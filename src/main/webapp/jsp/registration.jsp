<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 01.09.2020
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration page</h2>
<form action="user" method="post">
    <input type="text" name="action" value="registration" hidden>
    <input type="text" size="40" name="login" placeholder="Input new login" required>
    <br>
    <input type="password" size="40" name="password" placeholder="Input new password" required>
    <br>
    <input type="text" size="40" name="firstName" placeholder="Input your first name" required>
    <br>
    <input type="text" size="40" name="lastName" placeholder="Input your last name" required>
    <br>
    <input type="submit">
</form>
</body>
</html>
