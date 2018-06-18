<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <title>登录页面</title>
    <!--		<link rel="stylesheet" type="text/css" href="css/base.css"/>-->
    <link rel="stylesheet" type="text/css" href="/static/student/css/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/static/student/css/specificl.css"/>
    <link rel="stylesheet" type="text/css" href="/static/student/css/loginmain.css"/>
</head>
<body>
<header>
    <nav class="common_title">
        <div class="logo" align="center">
            <img src="/static/student/img/logo01.png"/>
        </div>


        <!--<i class="iconfont icon-fanhui" onclick="javascript:history.back(-1)"></i>-->
    </nav>
</header>
<div class="loginmain">
    <section class="l">
        <div class="topbar" align="center"><h3>欢迎登录学业预警系统</h3></div>
        <div class="details">
            <div>
                <span>学&nbsp;&nbsp;&nbsp;&nbsp;号：</span>
                <input type="text" placeholder="请输入账号（必填）" required="required" id="username"/>
            </div>
            <div>
                <span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
                <input type="password" placeholder="请输入密码（必填）" required="required" id="passwords"/>
            </div>
            <div class="clear">
                <span>验证码：</span>
                <input type="text" placeholder="请输入验证码" id="code" style="width: 40%"/>
                <div id="v_container" style="width: 80px;height: 15px;"></div>
            </div>
        </div>
        <div class="butt">
            <input type="button" value="登&nbsp;&nbsp;录" id="login"/>
        </div>
        <div class="reset">
            <a href="/student/updatePage">修改密码</a>
            <a href="#" >密码找回</a>
        </div>
        <div class="bottombar">
            <p align="center">注意:若验证码无法显示,请更换浏览器使用!</p>
        </div>
    </section>
</div>
<script type="text/javascript" src="/static/student/js/login.js"></script>
<script type="text/javascript" src="/static/student/js/code.js"></script>
<script type="text/javascript" src="/static/student/js/jquery-2.1.3.min.js"></script>
<script>
    var verifyCode = new GVerify("v_container");
    document.getElementById("login").onclick = function () {
        var res = verifyCode.validate(document.getElementById("code").value);
        if (res) {
            //ajax异步请求登陆信息
            var username = $("#username").val();
            var passwords = $("#passwords").val();
            $.ajax({
                type: 'post',
                url: "/student/login",
                dataType: 'JSON',
                data: {
                    "username": username,
                    "passwords": passwords
                }, success: function (date) {
                    if (date.resultCode == 1) {
                        alert(date.resultMessage);
                    } else {
                        alert(date.resultMessage);
                        document.getElementById("username").value = "";
                        document.getElementById("passwords").value = "";
                        document.getElementById("code").value = "";
                    }
                }
            });
        } else {
            alert("验证码错误");
            document.getElementById("code").value = "";
        }

    }
</script>
</body>
</html>
