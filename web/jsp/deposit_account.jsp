<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sandro
  Date: 03.05.18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deposit</title>
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<header>
    <div>
        <img src="../img/honest_512.png" alt="logo">
    </div>
    <div>
        <h1>Deposit Account</h1>
    </div>
    <div>
        <div>User: ${user.getName()}</div>
        <div><a href="/controller?command=sign_out">Sign Out</a></div>
        <div><a href="/controller?command=user_cabinet_page">to Cabinet</a></div>
    </div>
</header>

<div class="account_operations">
    <h3>Operations</h3>
    <div>
        <a href="/controller?command=replenishment_page">Replenishment Page</a>
        <p><b>Request For Opening An Account</b></p>
        <form action="/controller?command=replenishment" method="post">
            <fieldset>
                <label>
                    Sum
                </label>
                <input type="text" name="sum" placeholder="Enter sum">
            </fieldset>
            <fieldset class="signin_action">
                <input class="btn_fill" type="submit" name="submit" value="Confirm Replenish">
            </fieldset>
        </form>
    </div>

    <div>
        <%--<form action="/controller?command=new_account_request">--%>
            <%--<select name="rate" id="rate_id">--%>
                <%--<option value="5">5%</option>--%>
                <%--<option value="10">10%</option>--%>
                <%--<option value="15">15%</option>--%>
                <%--<option value="20">20%</option>--%>
                <%--<option value="25">25%</option>--%>
                <%--<option value="30">30%</option>--%>
            <%--</select>--%>
            <%--&lt;%&ndash;<select name="limit" id="limit_id">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="0">0</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="1000">1000</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="5000">5000</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="10000">10000</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="50000">50000</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<option value="100000">100000</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
            <%--<input type="submit" value="New Account Request">--%>
            <%--<a href="/controller?command=new_account_request">New</a>--%>
        <%--</form>--%>

        <p><b>Request For Opening An Account</b></p>
        <form action="/controller?command=new_account_request" method="post">
            <fieldset>
                <label>
                    Rate
                    <%--<input type="email" name="email" placeholder="your@mail.com" required>--%>
                </label>
                <select name="rate" id="rate_id2">
                    <option value="5">5%</option>
                    <option value="10">10%</option>
                    <option value="15">15%</option>
                    <option value="20">20%</option>
                    <option value="25">25%</option>
                    <option value="30">30%</option>
                </select>
                <%--<label>--%>
                    <%--Credit Limit--%>
                    <%--&lt;%&ndash;<input type="password" name="password" placeholder="5+ символов" required>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;${message}&ndash;%&gt;--%>
                <%--</label>--%>
                <%--<select name="limit" id="limit_id2">--%>
                    <%--<option value="1000">1000</option>--%>
                    <%--<option value="5000">5000</option>--%>
                    <%--<option value="10000">10000</option>--%>
                    <%--<option value="50000">50000</option>--%>
                    <%--<option value="100000">100000</option>--%>
                <%--</select>--%>
            </fieldset>
            <fieldset class="signin_action">
                <input class="btn_fill" type="submit" name="submit" value="New Deposit Account Request">
                <%--<label class="container">Запомнить--%>
                <%--<input type="checkbox" checked="checked">--%>
                <%--<span class="checkmark"></span>--%>
                <%--</label>--%>
            </fieldset>
        </form>

    </div>

    <div class="message">
        <h5>Messages:</h5>
        ${message}
    </div>

</div>

<div class="account_info">
    <h3>Account Info</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>TYPE</th>
            <th>USER NAME</th>
            <th>USER EMAIL</th>
            <th>BALANCE</th>
            <th>RATE</th>
            <th>OPENING DATE</th>
            <th>CLOSING DATE</th>
        </tr>
        <c:forEach items="${accounts}" var="item">
            <tr>
                <td>${item.getIdInDb()}</td>
                <td>${item.getType()}</td>
                <td>${item.getUser().getName()}</td>
                <td>${item.getUser().getEmail()}</td>
                <td>${item.getBalance()}</td>
                <td>${item.getRate()}%</td>
                <td>${item.getValidityFrom()}</td>
                <td>${item.getValidityTo()}</td>
                <td>
                    <div>
                        <form action="/controller?command=replenishment&account_id=${item.getIdInDb()}" method="post">
                            <fieldset>
                                <label>
                                    Sum
                                </label>
                                <input type="text" name="sum" placeholder="Enter sum">
                            </fieldset>
                            <fieldset class="signin_action">
                                <input class="btn_fill" type="submit" name="submit" value="Confirm Replenish">
                            </fieldset>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
