<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer money</title>
</head>
<body>
<h3>Enter the information </h3>
<form action="${pageContext.request.contextPath}/convert" method="post">
    <p>From account:
        <input type="radio" name="currencyName" value="UAH"> UAH |
        <input type="radio" name="currencyName" value="USD"> USD |
        <input type="radio" name="currencyName" value="EUR"> EUR |
    <p>Enter the value to convert: <input type="number" name="value"></p>
    <p>To account:
        <input type="radio" name="currencyNameReceiver" value="UAH"> UAH |
        <input type="radio" name="currencyNameReceiver" value="USD"> USD |
        <input type="radio" name="currencyNameReceiver" value="EUR"> EUR |
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
