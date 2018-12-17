<%--
  Created by IntelliJ IDEA.
  User: Numblgw
  Date: 2018/12/6
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="${pageContext.request.contextPath}/static/js/ajaxUtil.js"></script>
</head>
<body>
    <from>
        用户名<input id="username" type="text" name="username" />
        <br/>
        密码<input id="password" type="password" name="password" />
        <button id="register-btn" type="button">注册</button>
        <button id="back-btn" type="button">返回</button>
    </from>
    <script type="text/javascript">
        document.getElementById('register-btn').addEventListener('click',()=>{
            let user = new Object();
            user.username = document.getElementById('username').value;
            user.password = document.getElementById('password').value;
            window.ajaxUtil.ajaxRequest('post', '/user/register', (xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                console.log(jsonData);
                if(jsonData.code == '200'){
                    alert('注册成功！');
                    window.location.href = '/view/login';
                } else{
                    alert(jsonData.msg);
                }
            }, user);
        },false);
        document.getElementById('back-btn').addEventListener('click', ()=>{
            window.location.href = '/view/login';
        }, false);
    </script>
</body>
</html>
