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
</head>
<body>
<h1>Cabinet</h1>
<header>
    <div>User: ${user.getName()}</div>
    <a href="/controller?command=sign_out">Sign Out</a>
</header>
<h3>ACCOUNTS</h3>
<div class="accounts">
    <a href="/controller?command=deposit_account">Deposit</a>
    <a href="/controller?command=credit_account">Credit</a>
</div>

<div class="message">
    <h4>Messages</h4>
    ${message}
</div>

</body>
</html>
