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
    <script src="${pageContext.request.contextPath}/static/js/ajaxUtil.js"></script>
</head>
<body>
    <%--<div class="layui-row" style="text-align: center;">--%>
        <%--<h1>潍坊兼职</h1>--%>
    <%--</div>--%>
    <%--<div class="layui-container">--%>
        <%--<div class="layui-row">--%>
            <%--<div class="layui-col-md6 layui-col-md-offset3">--%>
                <%--<form class="layui-form" action="">--%>
                    <%--<div class="layui-form-item">--%>
                        <%--<label class="layui-form-label">用户名</label>--%>
                        <%--<div class="layui-input-block">--%>
                            <%--<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="layui-form-item">--%>
                        <%--<label class="layui-form-label">密码</label>--%>
                        <%--<div class="layui-input-inline">--%>
                            <%--<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
                        <%--</div>--%>
                        <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                    <%--</div>--%>
                    <%--<div class="layui-form-item">--%>
                        <%--<div class="layui-input-block">--%>
                            <%--<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<script>--%>
        <%--//Demo--%>
        <%--layui.use('form', function(){--%>
            <%--var form = layui.form;--%>

            <%--//监听提交--%>
            <%--form.on('submit(formDemo)', function(data){--%>
                <%--layer.msg(JSON.stringify(data.field));--%>
                <%--return false;--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>
    <div>
        <from>
            用户名<input id="username" type="text" name="username" />
            <br/>
            密码<input id="password" type="password" name="password" />
            <button id="login-btn" type="button">登录</button>
            <button id="register-btn" type="button">注册</button>
        </from>
    </div>
    <script type="text/javascript">
        document.getElementById('login-btn').addEventListener('click',()=>{
            let user = new Object();
            user.username = document.getElementById('username').value;
            user.password = document.getElementById('password').value;
            window.ajaxUtil.ajaxRequest('Post', '/user/login', (xhr)=>{
                let jsonData = JSON.parse(xhr.responseText);
                if(jsonData.code == '200'){
                    window.location.href = '/view/admin';
                } else{
                    alert(jsonData.msg);
                }
            }, user);
        },false);
        document.getElementById('register-btn').addEventListener('click', ()=>{
            window.location.href = '/view/register';
        }, false);
    </script>
</body>
</html>
