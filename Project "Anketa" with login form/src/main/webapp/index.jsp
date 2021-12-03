<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Login page</title>
</head>
<body>
<% String logIn = (String) request.getSession().getAttribute("logIn");%>

<% if (logIn == null || "".equals(logIn)) {%>
<h2>Please login to take the survey</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p><b>Login</b> <input name="login" type="text"></p>
    <p><b>Password</b> <input name="pass" type="password"></p>
    <input type="submit" name="submit">
</form>
<%
    } else {
        response.sendRedirect("statistic.jsp");
    }
%>
</body>
</html>
