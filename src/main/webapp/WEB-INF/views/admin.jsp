<%--
  Created by IntelliJ IDEA.
  User: Numb
  Date: 2018/10/31
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css"/>
</head>
<body>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">潍坊兼职后台管理</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="#userNav"><i class="layui-icon layui-icon-friends" style="font-size: 15px; color: #e2e2e2;"></i>用户管理</a></li>
                <li class="layui-nav-item"><a href="#jobNav"><i class="layui-icon layui-icon-file-b" style="font-size: 15px; color: #e2e2e2;"></i>兼职管理</a></li>
                <li class="layui-nav-item"><a href="#powerNav"><i class="layui-icon layui-icon-rate" style="font-size: 15px; color: #e2e2e2;"></i>权限管理</a></li>
                <li class="layui-nav-item"><a href="#authorizeNav"><i class="layui-icon layui-icon-rate-half" style="font-size: 15px; color: #e2e2e2;"></i>授权管理</a></li>
                <li class="layui-nav-item"><a href="#description"><i class="layui-icon layui-icon-tips" style="font-size: 15px; color: #e2e2e2;"></i>使用说明</a></li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item"><a href="javascript:;">用户名</a></li>
                <li class="layui-nav-item"><a href="">退出登录</a></li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed nav-li-left nav-li-left-userNav">
                        <a href="javascript:;">用户管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="#userList">用户列表</a></dd>
                            <dd><a href="#addUser">添加用户</a></dd>
                            <dd><a href="#searchUser">搜索用户</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed nav-li-left nav-li-left-jobNav">
                        <a href="javascript:;">兼职管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="jobList">兼职列表</a></dd>
                            <dd><a href="addJob">发布兼职</a></dd>
                            <dd><a href="checkJob">审核兼职</a></dd>
                            <dd><a href="searchJob">搜索兼职</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed nav-li-left nav-li-left-powerNav">
                        <a href="javascript:;">权限管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="powerList">权限列表</a></dd>
                            <dd><a href="addPower">添加权限</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed nav-li-left nav-li-left-authorizeNav">
                        <a href="javascript:;">授权管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="userPowerList">用户—权限列表</a></dd>
                            <dd><a href="grantPower">为已知用户授权</a></dd>
                            <dd><a href="searchAndGrant">搜索用户并授权</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div class="admin-body" style="padding: 15px;">

            </div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            <div class="layui-row">
                <div class="layui-col-md2 layui-col-md-offset5" style="text-align: center;">www.wfjz.com</div>
                <div class="layui-col-md2 layui-col-md-offset3" style="text-align: center;">Author : Numblgw</div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/spaUtil.js"></script>
    <script>
        //通过监听二级导航产生的hash值变化，显示相应的管理视图
        function showAdminView(){
            window.addEventListener('hashchange',()=>{
                let hash = window.location.hash.replace('#','');
                let insertPoint = document.querySelectorAll('.admin-body')[0];
                //由于一级导航与二级导航都会产生hash变化，为区分两个hash变化的不同，一级导航的hash值均为xxxNav
                if(hash.indexOf('Nav') == -1){
                    //通过模板字符串拼接url，在jsp中使用模板字符串需要用反斜杠转义
                    let url = `${pageContext.request.contextPath}/static/dom/\${hash}.html`;
                    window.spaUtil.getPage(url,insertPoint);
                    //需要数据列表的hash值都已 List 结尾
                    if(hash.indexOf('List') != -1){
                        //*****************##########待重构#############################################################
                    }
                }
            },false);
        }

        //程序入口，初始化页面
        function init(){
            //进入时默认显示使用说明
            window.spaUtil.getPage('${pageContext.request.contextPath}/static/dom/description.html',document.querySelectorAll('.admin-body')[0]);

            //把左边导航栏全部设置为隐藏，因为layui中默认为全部显示
            document.querySelectorAll('.nav-li-left').forEach((li)=>{
                li.style.display = 'none';
            });

            //监听点击一级导航产生的hash变化，在左侧显示相应的二级导航，"使用说明（description）"归为二级导航
            window.addEventListener('hashchange',()=>{
                let hash = window.location.hash.replace('#','');
                //由于一级导航与二级导航都会产生hash变化，为区分两个hash变化的不同，一级导航的hash值均为xxxNav
                if(hash.indexOf('Nav') != -1){
                    let showLiClassName = `nav-li-left-\${hash}`;
                    document.querySelectorAll('.nav-li-left').forEach((li)=>{
                        if(li.className.indexOf(showLiClassName) != -1){
                            li.style.display = 'block';
                        } else {
                            li.style.display = 'none';
                        }
                    });
                }
            },false);
            showAdminView();
        }

        window.addEventListener('load',init,false);

        layui.use('element', function(){
            var element = layui.element;
        });
    </script>
</body>
</html>
