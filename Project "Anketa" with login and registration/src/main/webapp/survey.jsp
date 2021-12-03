<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<h1>Cafe rating</h1>
<h2>${login}, please answer a few questions about our cafe.</h2>
<form action="${pageContext.request.contextPath}/stats" method="post">
    <p><b>Enter your information:</b></p>
    <p>Name <input name="userName" type="text"></p>
    <p>Surname <input name="userSurname" type="text"></p>
    <p>Age <input name="userAge" type="text"></p>
    <p><b>1. How often do you visit our cafe?</b></p>
    <p><input name="visitsOften" type="radio" value="everyDay"> Every day</p>
    <p><input name="visitsOften" type="radio" value="fewInWeek"> Few times a week</p>
    <p><input name="visitsOften" type="radio" value="oneInWeek"> Once a week</p>
    <p><input name="visitsOften" type="radio" value="never"> Never</p>
    <p><b>2. How would you rate the staff of our cafe?</b></p>
    <p><input name="staffRate" type="radio" value="staff1"> 1*</p>
    <p><input name="staffRate" type="radio" value="staff2"> 2*</p>
    <p><input name="staffRate" type="radio" value="staff3"> 3*</p>
    <p><input name="staffRate" type="radio" value="staff4"> 4*</p>
    <p><input name="staffRate" type="radio" value="staff5"> 5*</p>
    <p><b>3. How would you rate our cafe?</b></p>
    <p><input name="cafeRate" type="radio" value="cafe1"> 1*</p>
    <p><input name="cafeRate" type="radio" value="cafe2"> 2*</p>
    <p><input name="cafeRate" type="radio" value="cafe3"> 3*</p>
    <p><input name="cafeRate" type="radio" value="cafe4"> 4*</p>
    <p><input name="cafeRate" type="radio" value="cafe5"> 5*</p>
    <p><b>4. Could you recommend our cafe to your friends?</b></p>
    <p><input name="cafeRecom" type="radio" value="yes"> Yes</p>
    <p><input name="cafeRecom" type="radio" value="no"> No</p>
    <p><b>5.Choose your gender: </b></p>
    <p><input name="gender" type="radio" value="male"> Male</p>
    <p><input name="gender" type="radio" value="female"> Female</p>
    <p><input type="submit" value="Submit"></p>
</form>
<h5>Made by Helgen</h5>
</body>
</html>
