<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 05.09.2020
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Empty</title>
</head>
<body>
<p> Your cart is empty!</p>
<form action="item" method="get">
    <input type="text" name="action" value="items" hidden>
    <input type="submit"  value="Check available items">
</form>
<form action="user" method="get">
    <input type="text" name="action" value="cabinet" hidden>
    <input type="submit"  value="Go to my cabinet">
</form>
</body>
</html>
