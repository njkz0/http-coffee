<html>
<head>
    <title>My web application</title>
</head>
<body>
<%@ page import="model.User" %>
<% User user = (User) session.getAttribute("user");
    String result;
    if (user != null) {
        result = user.getFirstName() + ", registration completed successfully";
    } else result = "Hello, this is the start page"; %>
<%=result%>
<br>
<form action="user" method="post">
    <input type="text" name="action" value="login" hidden>
    <input type="text" size="40" name="login" placeholder="Input login" required>
    <br>
    <input type="password" size="40" name="password" placeholder="Input password" required>
    <br>
    <input type="submit">
</form>
<br>
<form action="user" method="get">
    <input type="text" name="action" value="registration" hidden>
    <input type="submit" value="registration">
</form>
</body>
</html>