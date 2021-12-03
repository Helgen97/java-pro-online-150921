<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Money</title>
</head>
<body>
<h3>Enter the information </h3>
<form action="${pageContext.request.contextPath}/send" method="post">
    <p>From account:
        <input type="radio" name="currencyName" value="UAH"> UAH |
        <input type="radio" name="currencyName" value="USD"> USD |
        <input type="radio" name="currencyName" value="EUR"> EUR |
    </p>
    <p>Enter the value to send: <input type="number" name="value"></p>
    <p>Enter the name of receiver: <input type="text" name="receiver"></p>
    <p>To account:
        <input type="radio" name="currencyNameReceiver" value="UAH"> UAH |
        <input type="radio" name="currencyNameReceiver" value="USD"> USD |
        <input type="radio" name="currencyNameReceiver" value="EUR"> EUR |
    </p>
    <p>Enter comment: <input type="text" name="comment"></p>
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
