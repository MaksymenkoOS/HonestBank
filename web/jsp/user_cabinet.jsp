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
<div>User: ${user.getName()}</div>
<a href="/controller?command=deposit_account">Deposit</a>
<a href="/controller?command=credit_account">Credit</a>

<a href="/controller?command=sign_out">Sign Out</a>

</body>
</html>
