<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register form</title>
</head>
<body>
<h4>Please enter your information</h4>
<form action="${pageContext.request.contextPath}/register" method="post">
    <p>Username <input type="text" name="username"></p>
    <p>Password <input type="password" name="password"></p>
    <p><input type="submit"></p>
</form>
</body>
</html>
