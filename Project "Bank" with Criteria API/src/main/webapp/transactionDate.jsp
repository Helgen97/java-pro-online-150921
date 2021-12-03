<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions XML in date range</title>
</head>
<body>
<h3>Choose date range: </h3>
<form action="${pageContext.request.contextPath}/xmlDate" method="post">
    <p>From: <input type="date" name="fromDate"></p>
    <p>To: <input type="date" name="toDate"></p>
    <p><input type="submit"></p>
</form>
<p><a href="userRoom.jsp">Back</a></p>
</body>
</html>
