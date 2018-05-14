<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 26.04.18
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to HonestBank</title>
</head>
<body>
<h1>Hello, I'm a BANK! HONEST BANK!</h1>

<a href="/controller?command=user_sign_up_page">Registration</a>
<br>
<br>
<form action="/controller?command=user_sign_in" method="post">
    <fieldset class="signin_info">
        <label>
            Ваш Email Адрес
            <input type="email" name="email" placeholder="your@mail.com" required>
        </label>
        <label>
            Ваш пароль
            <input type="password" name="password" placeholder="5+ символов" required>
            ${message}
        </label>
    </fieldset>
    <fieldset class="signin_action">
        <input class="btn_fill" type="submit" name="submit" value="Войти">
        <label class="container">Запомнить
            <input type="checkbox" checked="checked">
            <span class="checkmark"></span>
        </label>
    </fieldset>
</form>

<a href="/controller?command=ADMIN_SIGN_IN_PAGE">Enter for Admins</a>

</body>
</html>
