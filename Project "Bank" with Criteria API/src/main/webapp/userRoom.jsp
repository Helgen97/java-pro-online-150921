<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        HttpSession session1 = request.getSession();
        String login = (String) session1.getAttribute("login");
    %>
    <title>User personal menu</title>
</head>
<body>
<%
    if (login == null || login.equals("")) {
        response.sendRedirect("index.jsp");
    } else {
%>
<h3>Welcome ${username}!</h3>
<h4><a href="createAccount.jsp">1. Open new account</a></h4>
<h4><a href="addFunds.jsp">2. Add money to your account</a></h4>
<h4><a href="convert.jsp">3. Convert currency</a></h4>
<h4><a href="send.jsp">4. Send money to user</a></h4>
<h4><a href="${pageContext.request.contextPath}/status">5. Accounts status</a></h4>
<h4><a href="${pageContext.request.contextPath}/getxml">6. Get transactions XML</a></h4>
<h4><a href="transactionValue.jsp">7. Get transactions XML in value range</a></h4>
<h4><a href="transactionDate.jsp">8. Get transactions XML in date range</a></h4>

<p><a href="${pageContext.request.contextPath}/login?a=exit">Logout</a></p>
<%}%>
</body>
</html>
