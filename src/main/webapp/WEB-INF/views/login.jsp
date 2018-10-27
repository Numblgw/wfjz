<%--
  Created by IntelliJ IDEA.
  User: Numb
  Date: 2018/10/22
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery-3.3.1.min.js"></script>
    <script>

    </script>
</head>
<body>
    <div class="div_user_login">
        <form action="/user/login" method="post">
            <div class="div_username">
                <label for="username">用户名</label>
                <input id="username" name="username" type="text"/>
            </div>
            <div class="div_password">
                <label for="password">密码</label>
                <input id="password" name="password" type="password"/>
            </div>
            <div class="div_login_button">
                <input id="submit" type="button" value="登录" />
            </div>
        </form>
    </div>
</body>
</html>
