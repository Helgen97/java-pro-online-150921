<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add funds</title>
</head>
<body>

<h3>Enter the information </h3>
<form action="${pageContext.request.contextPath}/addFunds" method="post">
    <p>Enter the value: <input type="number" name="value"></p>
    <p>Currency: </p>
    <p>UAH<input type="radio" name="currencyName" value="UAH"></p>
    <p>USD<input type="radio" name="currencyName" value="USD"></p>
    <p>EUR<input type="radio" name="currencyName" value="EUR"></p>
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
