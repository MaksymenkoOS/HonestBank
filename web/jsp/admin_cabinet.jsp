<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Cabinet</title>
</head>
<body>
<h1>Admin Cabinet</h1>

<header>
    <div>Admin: ${admin.getEmail()}</div>
    <a href="/controller?command=sign_out">Sign Out</a>
</header>
<h3>Accounts Opening Requests</h3>
<div class="requests">
    <table>
        <tr>
            <th>ID</th>
            <th>TYPE</th>
            <th>USER</th>
        </tr>
        <c:forEach items="${requests}" var="item">
            <tr>
                <td>${item.getIdInDb()}</td>
                <td>${item.getAccountType()}</td>
                <td>${item.getUser().getEmail()}</td>
                <td>${item.getDate()}</td>
                <td>
                    <a href="/controller?command=confirm_request&request_id=${item.getIdInDb()}">
                        <input type="button" value="Confirm">
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="message">
    <h4>Messages</h4>
    ${message}
</div>

<div class="active_account">
    <h4>Active Accounts</h4>
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
        <c:forEach items="${active_accounts}" var="item">
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

</body>
</html>
