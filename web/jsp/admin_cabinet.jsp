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
<h3>Open Accounts Requests</h3>
<div class="requests">

</div>

<%--<h3>Account Info</h3>--%>
<%--<div class="account_info">--%>

<%--</div>--%>

</body>
</html>
