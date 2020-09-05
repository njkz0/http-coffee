<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 01.09.2020
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Available items</title>
</head>
<body>
<table>
    Available items:
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>PRICE</th>
    </tr>
    <c:forEach items="${allItems}" var="item">
        <form action="item" method="post">
            <tr>
                <td><c:out value="${item.id}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td><input type="text" name="action"
                           value="add-item-in-cart" hidden></td>

                <input type="text" name="userID" value="${user.id}" hidden>
                <input type="text" name="itemID" value="${item.id}" hidden>
                </td>
                <td><input type="number" size="2" name="amount" placeholder="Input amount" required>
                    <br></td>

                <td><input type="submit" value="Add to cart"></td>
            </tr>
        </form>
    </c:forEach>
</table>
<form action="user" method="get">
    <input type="text" name="action" value="cabinet" hidden>
    <input type="submit"  value="Go to my cabinet">
</form>
</body>
</html>
