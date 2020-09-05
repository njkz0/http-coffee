<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 02.09.2020
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My cart</title>
</head>
<body>
<table>
    Items:
    <tr>
        <th>NAME   </th>
        <th>PRICE  </th>
        <th>AMOUNT  </th>
        <th>TOTAL PRICE</th>
    </tr>
    <c:forEach items="${DTOs}" var="dto">
        <form action="item" method="post">
            <tr>
                <td><c:out value="${dto.name}"/></td>
                <td><c:out value="${dto.price}"/></td>
                <td align="center"><c:out value="${dto.amount}"/></td>
                <td align="center"><c:out value="${dto.fullPrice}"/></td>
            </tr>
        </form>
    </c:forEach>

</table>
<% int total=(Integer) session.getAttribute("total");%>
<p> Total price - <%= total%> </p>
<form action="item" method="post">
    <input type="text" name="action" value="buy" hidden>
    <input type="submit"  value="buy.">
</form>
</body>
</html>
