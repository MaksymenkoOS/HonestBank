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
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/cabinet.css">
    <link rel="stylesheet" href="../css/admin_cabinet.css">
</head>
<body>

<header>
    <div>
        <img src="../img/HB.svg" alt="logo">
    </div>
    <div>
        <h1>Admin Cabinet</h1>
    </div>
    <div>
        <div>Admin: ${admin.getEmail()}</div>
        <a href="/controller?command=sign_out">Sign Out</a>
    </div>
</header>


<h3>Accounts Opening Requests</h3>
<div class="requests">
    <table>
        <tr>
            <th>ID</th>
            <th>TYPE</th>
            <th>USER</th>
            <th>DATE</th>
            <th>RATE</th>
            <th>LIMIT(only for credit)</th>
        </tr>
        <c:forEach items="${requests}" var="item">
            <tr>
                <td>${item.getIdInDb()}</td>
                <td>${item.getAccountType()}</td>
                <td>${item.getUser().getEmail()}</td>
                <td>${item.getDate()}</td>
                <td>${item.getRate()}%</td>
                <td>${item.getLimit()}</td>
                    <%--<form action="/controller?command=confirm_request&request_id=${item.getIdInDb()}">--%>
                    <%--<select name="rate" id="rate_id">--%>
                    <%--<option value="5">5</option>--%>
                    <%--<option value="10">10</option>--%>
                    <%--<option value="15">15</option>--%>
                    <%--<option value="20">20</option>--%>
                    <%--<option value="25">25</option>--%>
                    <%--<option value="30">30</option>--%>
                    <%--</select>--%>
                    <%--<select name="limit" id="limit_id">--%>
                    <%--<option value="0">0</option>--%>
                    <%--<option value="1000">1000</option>--%>
                    <%--<option value="5000">5000</option>--%>
                    <%--<option value="10000">10000</option>--%>
                    <%--<option value="50000">50000</option>--%>
                    <%--<option value="100000">100000</option>--%>
                    <%--</select>--%>
                    <%--<input type="submit" value="Confirm">--%>
                    <%--</form>--%>
                <td><a href="/controller?command=accept_request&request_id=${item.getIdInDb()}">
                    <button>Accept</button>
                </a></td>
                <td><a href="/controller?command=decline_request&request_id=${item.getIdInDb()}">
                    <button>Decline</button>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="active_account">
    <h3>Active Accounts</h3>
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
                <td>${item.getRate()}%</td>
                <td>${item.getValidityFrom()}</td>
                <td>${item.getValidityTo()}</td>
            </tr>
        </c:forEach>
    </table>

</div>

<div class="transactions">
    <h4>All Transactions</h4>
    <div class="block">
        <table>
            <tr>
                <th>ID</th>
                <th>TIME STAMP</th>
                <th>AMOUNT</th>
                <th>SENDER ACCOUNT</th>
                <th>RECIPIENT ACCOUNT</th>
            </tr>
            <c:forEach items="${all_transactions}" var="item">
                <tr>
                    <td>${item.getIdInDb()}</td>
                    <td>${item.getTime_stamp()}</td>
                    <td>${item.getAmount()}</td>
                    <td>${item.getSender().getIdInDb()}</td>
                    <td>${item.getRecipient().getIdInDb()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="message">
    <h5>Messages:</h5>
    ${message}
</div>

</body>
</html>
