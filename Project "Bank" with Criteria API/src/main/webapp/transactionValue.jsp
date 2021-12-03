
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions XML in value range</title>
</head>
<body>
<h3>Choose value range: </h3>
<form action="${pageContext.request.contextPath}/xmlValue" method="post">
    <p>Currency send:
        <input type="radio" name="currencyName" value="UAH"> UAH |
        <input type="radio" name="currencyName" value="USD"> USD |
        <input type="radio" name="currencyName" value="EUR"> EUR |
    </p>
    <p>From: <input type="number" name="fromValue"></p>
    <p>To: <input type="number" name="toValue"></p>
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
