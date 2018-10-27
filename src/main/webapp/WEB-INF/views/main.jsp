<%--
  Created by IntelliJ IDEA.
  User: Numb
  Date: 2018/10/23
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>
    <div class="div_main">
        <form action="/user/insert" method="post">
            <div class="div_username">
                <label for="username">用户名</label>
                <input id="username" name="username" type="text" />
            </div>
            <div class="div_password">
                <label for="password">密码</label>
                <input id="password" name="password" type="password" />
            </div>
            <div class="div_button">
                <input type="submit" value="添加用户" />
            </div>
        </form>
    </div>
</body>
</html>
