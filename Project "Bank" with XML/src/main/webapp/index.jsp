<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%
        HttpSession session1 = request.getSession();
        String login = (String) session1.getAttribute("login");
    %>
    <title>Welcome to the B-A-N-K!</title>
</head>
<body>
<%if (login == null || login.equals("")) {%>
<h3>Hello! Please login to your account!</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p>Username <input type="text" name="username"></p>
    <p>Password <input type="password" name="password"></p>
    <p><input type="submit"></p>
</form>
<p><a href="register.jsp">Register</a></p>
<%
    } else {
        response.sendRedirect("userRoom.jsp");
    }
%>
</body>
</html>