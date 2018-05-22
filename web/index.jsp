<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Welcome to HonestBank</title>
</head>
<body>

<%--<c:set var="lang" value="${cookie.lang.value}" scope="session"/>--%>
<%--<c:set var="lang" value="${sessionScope.lang}" scope="session"/>--%>
<%--<fmt:setLocale value="${empty sessionScope.lang ? 'en' : sessionScope.lang}"/>--%>
<%--<fmt:setBundle basename="/localization/front" var="bundle" scope="session"/>--%>
<%----%>
<c:set var="lang" value="${empty lang ? 'en' : sessionScope.lang}" scope="session"/>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="localization/front" scope="session"/>

<h1><fmt:message key="greeting"/></h1>

<a href="/controller?command=user_sign_up_page"><fmt:message key="registration"/> </a>
<br>
<br>
<form action="/controller?command=user_sign_in" method="post">
    <fieldset class="signin_info">
        <label>
            <fmt:message key="email"/>
            <input type="email" name="email" placeholder=<fmt:message key="emailPlaceholder"/> required>
        </label>
        <label>
            <fmt:message key="password"/>
            <input type="password" name="password" placeholder=<fmt:message key="passwordPlaceholder"/> required>
            ${message}
        </label>
    </fieldset>
    <fieldset class="signin_action">
        <input class="btn_fill" type="submit" name="submit" value=<fmt:message key="enter"/>>

    </fieldset>
</form>

<a href="/controller?command=ADMIN_SIGN_IN_PAGE"><fmt:message key="adminEnter"/></a>

<a href="/controller?command=CHANGE_LANG&lang=en"> English </a>
<a href="/controller?command=CHANGE_LANG&lang=ru"> Русский </a>

</body>
</html>
