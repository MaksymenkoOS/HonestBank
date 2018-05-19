<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 30.04.18
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cabinet</title>
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<header>
    <div>
        <img src="../img/honest_512.png" alt="logo">
    </div>
    <div>
        <h1>Cabinet</h1>
    </div>
    <div>
        <div>User: ${user.getName()}</div>
        <a href="/controller?command=sign_out">Sign Out</a>
    </div>
</header>
<div class="footer">
    <div class="accounts">
        <h3>ACCOUNTS</h3>
        <a href="/controller?command=deposit_account">Deposit</a>
        <a href="/controller?command=credit_account">Credit</a>
    </div>

    <div class="message">
        <h4>Messages</h4>
        ${message}
    </div>
</div>

</body>
</html>
