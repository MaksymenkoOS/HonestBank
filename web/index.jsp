<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:500,600" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800&amp;subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/index_style.css">
    <meta charset="UTF-8">
    <title>Честный Банк</title>
</head>
<body>
<c:set var="current_page" value="index.jsp" scope="session"/>
<c:set var="lang" value="${empty lang ? 'en' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="localization/front" scope="session"/>

<header>
    <div class="head">
        <div class="flex-header">
            <a href="index.jsp" class="logo"></a>
            <div class="dropdown">
                <button class="dropbtn"><fmt:message key="langButton"/></button>
                <div class="dropdown-content">
                    <a href="/controller?command=CHANGE_LANG&lang=ru">Русский</a>
                    <a href="/controller?command=CHANGE_LANG&lang=en">English</a>
                </div>
            </div>
        </div>
    </div>
</header>

<main>
    <div class="flex-container">
        <div class="text_btn">
            <h1 class="title_1"><fmt:message key="indexName"/></h1>
            <h2 class="title_2"><fmt:message key="indexSlogan"/></h2>
            <a href="/controller?command=user_sign_in_page" class="btn_border"><fmt:message key="enter"/></a>
            <a href="/controller?command=user_sign_up_page" class="btn_border"><fmt:message key="registration"/></a>
        </div>
        <a href="/controller?command=ADMIN_SIGN_IN_PAGE"><fmt:message key="adminEnter"/></a>

    </div>

</main>


<%--<h1><fmt:message key="greeting"/></h1>--%>

<%--<a href="/controller?command=user_sign_up_page"><fmt:message key="registration"/> </a>--%>
<%--<br>--%>
<%--<br>--%>
<%--<form action="/controller?command=user_sign_in" method="post">--%>
    <%--<fieldset class="signin_info">--%>
        <%--<label>--%>
            <%--<fmt:message key="email"/>--%>
            <%--<input type="email" name="email" placeholder=--%>
            <%--<fmt:message key="emailPlaceholder"/> required>--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--<fmt:message key="password"/>--%>
            <%--<input type="password" name="password" placeholder=--%>
            <%--<fmt:message key="passwordPlaceholder"/> required>--%>
            <%--${message}--%>
        <%--</label>--%>
    <%--</fieldset>--%>
    <%--<fieldset class="signin_action">--%>
        <%--<input class="btn_fill" type="submit" name="submit" value=<fmt:message key="enter"/>>--%>

    <%--</fieldset>--%>
<%--</form>--%>

<%--<a href="/controller?command=ADMIN_SIGN_IN_PAGE"><fmt:message key="adminEnter"/></a>--%>

<%--<a href="/controller?command=CHANGE_LANG&lang=en"> English </a>--%>
<%--<a href="/controller?command=CHANGE_LANG&lang=ru"> Русский </a>--%>

</body>
</html>
