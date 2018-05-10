<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h1>Sign Up</h1>
<a href="/controller?command=user_sign_up">Enter</a>

<form action="/controller?command=user_sign_up" method="post">
    <fieldset class="signup_info">
        <label>
            Ваш Email Адрес
            <input type="email" name="email" placeholder="your@mail.com" required>
        </label>
        <label>
            Ваше полное имя
            <input type="text" name="name" placeholder="Александр Сокур" required>
        </label>
        <label>
            Ваш пароль
            <input type="password" name="pass" placeholder="5+ символов" required>
            ${error_message}
        </label>
    </fieldset>
    <fieldset class="signup_action">
        <input class="btn_fill" type="submit" name="submit" value="Зарегистрироваться">
    </fieldset>
</form>
</body>
</html>
