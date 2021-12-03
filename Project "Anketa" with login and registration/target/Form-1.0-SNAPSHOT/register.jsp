
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h3>Please enter your information:</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p><b>Login </b><input type="text" name="newLogin"></p>
    <p><b>Password </b><input type="text" name="newPassword"></p>
    <input type="hidden" name="register" value="new">
    <p><input type="submit"></p>

</form>
</body>
</html>
