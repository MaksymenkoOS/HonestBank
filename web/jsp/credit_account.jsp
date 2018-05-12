<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Credit</title>
</head>
<body>

<h1>Credit Account</h1>

<header>
    <div>User: ${user.getName()}</div>
    <a href="/controller?command=sign_out">Sign Out</a>
</header>
<div class="account_operations">
    <h3>Operations</h3>

    <a href="/controller?command=transfer_page">Transfer</a>
    <a href="/controller?command=user_cabinet_page">Cabinet</a>
    <a href="/controller?command=new_account">New</a>
</div>

<div class="account_info">
    <h3>Account Info</h3>


</div>
<div class="message">
    <h4>Messages</h4>
    ${message}
</div>

</body>
</html>
