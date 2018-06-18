<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <title>学业预警系统-修改密码</title>
    <!--		<link rel="stylesheet" type="text/css" href="css/base.css"/>-->
    <link rel="stylesheet" type="text/css" href="/static/student/css/specificl.css"/>
    <link rel="stylesheet" type="text/css" href="/static/student/css/loginmain.css"/>
    <link rel="stylesheet" type="text/css" href="/static/student/css/iconfont.css"/>
</head>
<body>
<header>
    <nav class="common_title">
        <div class="logo" align="center">
            <i class="iconfont icon-fanhui" onclick="javascript:history.back(-1)">返回</i>
            <img src="/static/student/img/logo01.png" onclick="javascript:window.location.href='/student/index'"/>
        </div>
        <!--<i class="iconfont icon-fanhui" onclick="javascript:history.back(-1)"></i>-->
    </nav>
</header>
<section class="l">
    <div class="topbar" align="center"><h3>修改密码</h3></div>
    <div class="details">
        <div>
            <span>学&nbsp;&nbsp;&nbsp;&nbsp;号：</span>
            <input type="text" placeholder="请输入账号（必填）" required="required" id="username"/>
        </div>
        <div>
            <span>旧密码：</span>
            <input type="password" placeholder="请输入密码（必填）" required="required" id="oldpasswords"/>
        </div>
        <div>
            <span>新密码：</span>
            <input type="password" placeholder="请输入密码（必填）" required="required" id="passwords"/>
        </div>
    </div>
    <div class="butt">
        <input type="button" value="修&nbsp;&nbsp;改" id="update"/>
    </div>
    <div class="bottombar">
        <p align="center" style="padding-right: 20px;">点击logo可以直接返回首页</p>
    </div>
</section>
<script type="text/javascript" src="/static/student/js/jquery-2.1.3.min.js"></script>
<script>
    $("#update").click(function () {
        var username = $("#username").val();
        var oldpasswords = $("#oldpasswords").val();
        var passwords = $("#passwords").val();
        $.ajax({
            type: 'post',
            url: "/student/update",
            dataType: 'JSON',
            data: {
                "username": username,
                "oldpasswords": oldpasswords,
                "passwords": passwords
            }, success: function (date) {
                if (date.resultCode == 1) {
                    alert("修改成功");
                    window.location.href="/student/index";
                } else {
                    alert(date.resultMessage);
                    document.getElementById("username").value = "";
                    document.getElementById("oldpasswords").value = "";
                    document.getElementById("passwords").value = "";
                }
            }
        });
    });

</script>
</body>
</html>
