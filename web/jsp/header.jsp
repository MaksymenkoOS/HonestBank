<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="lang" value="${empty lang ? 'en' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="localization/front" scope="session"/>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../css/header.css">
</head>
<body>
<div class="logo">
    <a href="/controller?command=index">
        <img src="../img/HB.svg" alt="logo">
    </a>
</div>
<div class="title">
    <p>TITLE</p>
</div>
<%--<div class="user">--%>
    <%--<div class="dropdown-content">--%>
        <%--<a href="/controller?command=sign_out">Выйти</a>--%>
    <%--</div>--%>
<div class="dropdown">
    <button class="dropbtn">${user.getName()}</button>
    <div class="triangle-right"></div>
    <div class="dropdown-content">
        <a href="/controller?command=sign_out">Sign Out</a>
        <a href="/?command=change_lang&lang=en"> English </a>
        <a href="/?command=change_lang&lang=ru"> Русский </a>
    </div>
</div>
</body>
</html>
