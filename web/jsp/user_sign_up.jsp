<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:500,600" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800&amp;subset=cyrillic" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/sign_in.css">
    <meta charset="UTF-8">
    <title><fmt:message key="registrationTitle"/></title>
</head>
<body>
<c:set var="current_page" value="jsp/user_sign_up.jsp" scope="session"/>
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
    <div class="flex-conteiner">
        <div class="sign_in_form">
            <div class="form">
                <h1 class="title_1"><fmt:message key="registrationTitle"/> </h1>

                <form action="/controller?command=user_sign_up" method="post">
                    <fieldset class="signin_info">
                        <label>
                            <fmt:message key="email"/>
                            <input type="email" name="email" placeholder=<fmt:message key="emailPlaceholder"/> required>
                        </label>
                        <label>
                            <fmt:message key="fullName"/>
                            <input type="text" name="name" placeholder=<fmt:message key="fullNamePlaceholder"/> required>
                        </label>
                        <label>
                            <fmt:message key="password"/>
                            <input type="password" name="password" placeholder=<fmt:message key="passwordPlaceholder"/> required>
                        </label>
                        ${message}
                    </fieldset>
                    <fieldset class="signin_action">
                        <input class="btn_fill" type="submit" name="submit" value=<fmt:message key="enter"/>>
                    </fieldset>
                </form>
            </div>

        </div>

        <div class="signin_picture"></div>
    </div>

</main>
</body>
</html>







<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Sign Up</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Sign Up</h1>--%>
<%--<a href="/controller?command=user_sign_up">Enter</a>--%>

<%--<form action="/controller?command=user_sign_up" method="post">--%>
    <%--<fieldset class="signup_info">--%>
        <%--<label>--%>
            <%--Ваш Email Адрес--%>
            <%--<input type="email" name="email" placeholder="your@mail.com" required>--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--Ваше полное имя--%>
            <%--<input type="text" name="name" placeholder="Benjamin Franklin" required>--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--Ваш пароль--%>
            <%--<input type="password" name="pass" placeholder="5+ symbols" required>--%>
            <%--${message}--%>
        <%--</label>--%>
    <%--</fieldset>--%>
    <%--<fieldset class="signup_action">--%>
        <%--<input class="btn_fill" type="submit" name="submit" value="Зарегистрироваться">--%>
    <%--</fieldset>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
