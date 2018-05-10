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
<h3>Operations</h3>
<div class="account_operations">
    <a href="/controller?command=replenishment_page">Replenish</a>
    <a href="/controller?command=user_cabinet_page">Cabinet</a>
    <a href="/controller?command=new_account">New</a>
</div>

<h3>Account Info</h3>
<div class="account_info">

</div>

</body>
</html>
