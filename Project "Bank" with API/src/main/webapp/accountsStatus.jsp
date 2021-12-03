
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts status</title>
</head>
<body>
<h3>${username} accounts:</h3>
<p>UAH value: ${UAHValue}</p>
<p>USD value: ${USDValue}</p>
<p>EUR value: ${EURValue}</p>
<p>Sum of all money in UAH: ${SUM}</p>

<p><a href="${pageContext.request.contextPath}/status?a=exit">Back</a></p>
</body>
</html>
