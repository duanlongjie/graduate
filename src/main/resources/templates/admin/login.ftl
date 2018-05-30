<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <title>管理员登陆</title>
    <link rel="stylesheet" href="/static/admin/css/bootstrap-clearmin.css"/>
    <link rel="stylesheet" href="/static/admin/css/login.css"/>
</head>
<body id="particles">
<div class="container login-page">
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-10">
            <div class="login-main">
                <ul class="nav-links new-session-tabs nav-tabs">
                    <li>
                        <a>登陆</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="login-box tab-pane active" id="sign_in_pane" role="tabpanel">
                        <div class="login-body">
                            <form action="/admin/login" method="post">
                                <div class="form-group">
                                    <label for="admin_username">用户名</label>
                                    <input class="form-control" name="username" autofocus="autofocus"
                                           required="required" type="text" id="admin_username" />
                                </div>
                                <div class="form-group">
                                    <label for="admin_password">密码</label>
                                    <input class="form-control" name="password" required="required" type="password"
                                           id="admin_password"/>
                                </div>
                                <div class="submit-container">
                                    <input type="submit" value="登陆" class=" btn btn-large btn-block btn-primary"/>
                                </div>
                                <div class="info-text">
                                    <p id="message">${error_msg!}</p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-1"></div>
    </div>
    <hr class="footer-fixed">
    <div class="container footer-container">
        <div class="footer-links">
            <p>推荐使用IE9+, Chrome, Firefox, Opera, Safari等现代浏览器访问本站点，对移动设备等小屏幕友好</p>
        </div>
    </div>
</div>
<script src="/static/admin/js/jquery-2.1.3.min.js"></script>
<script src="/static/admin/js/bootstrap.js"></script>
</body>
</html>