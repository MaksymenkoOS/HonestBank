<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deposit</title>
</head>
<body>

<h1>Deposit Account</h1>

<header>
    <div>User: ${user.getName()}</div>
    <a href="/controller?command=sign_out">Sign Out</a>
</header>
<div class="account_operations">
    <h3>Operations</h3>

    <a href="/controller?command=replenishment_page">Replenish</a>
    <a href="/controller?command=user_cabinet_page">Cabinet</a>
    <a href="/controller?command=new_account">New</a>
</div>

<div class="account_info">
    <h3>Account Info</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>TYPE</th>
            <th>USER NAME</th>
            <th>USER EMAIL</th>
            <th>BALANCE</th>
            <th>RATE</th>
            <th>OPENING DATE</th>
            <th>CLOSING DATE</th>
        </tr>
        <c:forEach items="${accounts}" var="item">
            <tr>
                <td>${item.getIdInDb()}</td>
                <td>${item.getType()}</td>
                <td>${item.getUser().getName()}</td>
                <td>${item.getUser().getEmail()}</td>
                <td>${item.getBalance()}</td>
                <td>${item.getRate()}</td>
                <td>${item.getValidityFrom()}</td>
                <td>${item.getValidityTo()}</td>
            </tr>
        </c:forEach>
    </table>

</div>
<div class="message">
    <h4>Messages</h4>
    ${message}
</div>
</body>
</html>
