<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <title>管理员登陆</title>
    <script src="/static/admin/js/jquery-2.1.3.min.js"></script>
    <script src="/static/admin/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/static/admin/css/bootstrap-clearmin.css"/>
    <link rel="stylesheet" href="/static/admin/css/login.css"/>
    <link rel="stylesheet" href="/static/admin/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/admin/css/bootstrap-fileinput.css"/>
</head>
<body id="particles">
    <h1 align="center">Excel导入</h1>
    <div  style="width: 1400px;height: 200px;margin: 100px auto ;" >


        <!--导航栏-->
        <ul class="list-unstyled">

            <li class=" ">
                <form class="form-horizontal"  role="form" action="uploadDeal" method="post" enctype="multipart/form-data" >
                    <div class="col-xs-1">
                        <h4><i>导入学生</i></h4>
                        <input class="form-control"  type="file"  name="file">
                        <input  class="form-control" type="submit" id="but" value="导入学生">
                    </div>
                </form>
            </li>

            <li class=" ">
                <form class="form-horizontal"  role="form" action="uploadDeal2" method="post" enctype="multipart/form-data" >
                    <div class="col-xs-1">
                        <h4><i>导入成绩</i></h4>
                        <input class="form-control"  type="file"  name="file">
                        <input  class="form-control" type="submit" id="but" value="导入成绩">
                    </div>
                </form>
            </li>
            <li class=" ">
                <form class="form-horizontal"  role="form" action="uploadDeal3" method="post" enctype="multipart/form-data" >
                    <div class="col-xs-1">
                        <h4><i>导入课程</i></h4>
                        <input class="form-control"  type="file"  name="file">
                        <input  class="form-control" type="submit" id="but" value="导入课程">
                    </div>
                </form>
        </li>
        </ul>
    </div>













</body>
</html>