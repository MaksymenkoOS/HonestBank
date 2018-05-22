<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check</title>
</head>

<body>
<h1>Check</h1>
<div>User: ${user.getName()}</div>

<a href="/controller?command=user_cabinet_page">Cabinet</a>
<a href="/controller?command=sign_out">Sign Out</a>

<p>${message}</p>
<p></p>
</body>
</html>
