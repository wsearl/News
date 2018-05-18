<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>网站登陆</title>
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
</head>
<style>
    body,html {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: 0;
        background-color:#FFFFFF;
        z-index: -100;
    }
    .well{
        margin-left: auto;
        margin-right: auto;
        margin-top: 163px;
        width:500px;

    }
    .footer {
        margin-top: 20px;
        padding: 30px 0;
        color: #999;
        text-align: center;
    }
    .container  {
        max-width: 450px;
        text-align: center;
    }
    label{
        float: left;
    }
</style>
<body>
<div class = "well">
    <div class="container">
        <div class="form-container">
            <h1>登录 <small>没有账号？<a href="${pageContext.request.contextPath}/regist">注册</a></small></h1>
            <form action="${pageContext.request.contextPath}/loginAdmin" method="post" >
                <div class="form-group">
                    <label>用户名/手机/邮箱</label>
                    <input  name="username" type="text" class="form-control" placeholder="${message}">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input name="password" type="password" class="form-control" placeholder="${message}">
                </div>
                <p>忘记密码？</p>
                <div class="form-group">
                    <button type="submit" class="btn btn-info btn-block">登录</button>
                </div>
            </form>
        </div>
    </div>
    <div class="footer">
        © 2017 XXXX 中国互联网举报中心京ICP证1401号京ICP备125439号-3京公网安备
    </div>
</div>
<script type="text/javascript" color="0,0,0" opacity='2'
        zIndex="-99" count="150" src="js/canvas-nest.js"></script>
</body>
</html>
