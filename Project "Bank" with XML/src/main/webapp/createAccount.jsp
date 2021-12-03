<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open new account</title>
</head>
<body>
<h3>Choose account type: </h3>
<form action="${pageContext.request.contextPath}/newAcc" method="post">
    <p>Currency: </p>
    <p>UAH<input type="radio" name="currencyName" value="UAH"></p>
    <p>USD<input type="radio" name="currencyName" value="USD"></p>
    <p>EUR<input type="radio" name="currencyName" value="EUR"></p>
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
