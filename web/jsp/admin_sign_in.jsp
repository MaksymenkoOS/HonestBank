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
    <title>Admin Sign In</title>
</head>
<body>
<h1>Admin Sign In</h1>
<%--<a href="/controller?command=admin_sign_in">Enter</a>--%>

<form action="/controller?command=admin_sign_in" method="post">
    <fieldset class="signin_info">
        <label>
            Ваш Email Адрес
            <input type="email" name="email" placeholder="your@mail.com" required>
        </label>
        <label>
            Ваш пароль
            <input type="password" name="password" placeholder="5+ символов" required>
            ${error_message}
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
</body>
</html>
