<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 27.08.2020
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>
        Successfully! Select a product</title>
</head>
<body>
<%@ page import="model.User" %>
<% User user = (User) session.getAttribute("user"); %>
<p> Hello, <%= user.getFirstName()%> <%= user.getLastName()%>
</p>
<br>
<form action="item" method="get">
    <input type="text" name="action" value="items" hidden>
    <input type="submit"  value="Check available items">
</form>
<form action="item" method="get">
    <input type="text" name="action" value="cart" hidden>
    <input type="submit"  value="go to my cart">
</form>
<form action="user" method="get">
    <input type="text" name="action" value="exit" hidden>
    <input type="submit"  value="exit from cabinet">
</form>
</body>
</html>
