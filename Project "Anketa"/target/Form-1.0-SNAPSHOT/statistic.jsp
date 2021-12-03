<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cafe rating statistic</title>
</head>
<body>
<h1>Cafe rating statistic</h1>
<h4>Name: ${userName}</h4>
<h4>Surname: ${userSurname}</h4>
<h4>Age: ${userAge}</h4>
<table border="3" cellspacing="3" cellpadding="3">
    <tr>
        <td><b>1. How often do you visit our cafe?</b></td>
    </tr>
    <tr>
        <td>Every day: ${visitsOftenEvery} votes</td>
        <td>Few times a week: ${visitsOftenFew} votes</td>
        <td>Once a week: ${visitsOftenOnce} votes</td>
        <td>Never: ${visitsOftenNever} votes</td>
    </tr>
    <tr>
        <td><b>2. How would you rate the staff of our cafe?</b></td>
    </tr>
    <tr>
        <td>1*: ${staff1} votes</td>
        <td>2*: ${staff2} votes</td>
        <td>3*: ${staff3} votes</td>
        <td>4*: ${staff4} votes</td>
        <td>5*: ${staff5} votes</td>
    </tr>
    <tr>
        <td><b>3. How would you rate our cafe?</b></td>
    </tr>
    <tr>
        <td>1*: ${cafe1} votes</td>
        <td>2*: ${cafe2} votes</td>
        <td>3*: ${cafe3} votes</td>
        <td>4*: ${cafe4} votes</td>
        <td>5*: ${cafe5} votes</td>
    </tr>
    <tr>
        <td><b>4. Could you recommend our cafe to your friends?</b></td>
    </tr>
    <tr>
        <td>Yes: ${cafeRecomYes} votes</td>
        <td>No: ${cafeRecomNo} votes</td>
    </tr>
    <tr>
        <td><b>5. Choose your gender:</b></td>
    </tr>
    <tr>
        <td>Male: ${genderMale} votes</td>
        <td>Female: ${genderFemale} votes</td>
    </tr>
</table>
<h5>Made by Helgen</h5>
</body>
</html>
