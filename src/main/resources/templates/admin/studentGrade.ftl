<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/bootstrap-clearmin.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/roboto.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/material-design.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/small-n-flat.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/static/admin/css/select2.min.css">
    <link href="${request.contextPath}/static/admin/css/jquery.pagination.css" rel="stylesheet"/>
    <title>云棋牌管理后台</title>
</head>
<body class="cm-no-transition cm-1-navbar cm-2-navbar">
<div id="cm-menu">
    <nav class="cm-navbar cm-navbar-primary">
        <div class="cm-flex">
            <div class="cm-logo"></div>
        </div>
        <div class="btn btn-primary md-menu-white" data-toggle="cm-menu"></div>
    </nav>
    <#--<div id="cm-menu-content">-->
        <#--<div` id="cm-menu-items-wrapper">-->
        <#--<div id="cm-menu-scroller">-->
            <#--<ul class="cm-menu-items">-->
                    <#--<#assign firstMenuList=currentAdmin.permissionList/>-->
                    <#--<#list firstMenuList as firstMenu>-->
                        <#--<#if firstMenu.preferences?size==1>-->
                        <#--<li><a href="${firstMenu.preferences[0].href}" class="${firstMenu.icon}">${firstMenu.preferences[0].title}</a></li>-->
                        <#--<#else>-->
                        <#--<li class="cm-submenu">-->
                            <#--<a class="${firstMenu.icon}">${firstMenu.title} <span class="caret"></span></a>-->
                            <#--<ul>-->
                            <#--<#assign secondMenuList=firstMenu.preferences/>-->
                            <#--<#list secondMenuList as secondMenu>-->
                                <#--<li><a href="${secondMenu.href}">${secondMenu.title}</a></li>-->
                            <#--</#list>-->
                            <#--</ul>-->
                        <#--</li>-->
                        <#--</#if>-->
                    <#--</#list>-->
            <#--</ul>-->
        <#--</div>-->
    <#--</div>-->
</div>
<header id="cm-header">
    <nav class="cm-navbar cm-navbar-primary">
        <div class="btn btn-primary md-menu-white hidden-md hidden-lg" data-toggle="cm-menu"></div>
        <div class="cm-flex"><h1>学生数据列表</h1></div>
        <div class="dropdown pull-right">
            <button class="btn btn-primary md-notifications-white" data-toggle="dropdown"><span
                    class="label label-danger">0</span></button>
            <div class="popover cm-popover bottom">
                <div class="arrow"></div>
                <div class="popover-title">当前无系统通知消息</div>
            </div>
        </div>
    </nav>
    <nav class="cm-navbar cm-navbar-default cm-navbar-slideup">
        <div class="cm-flex">
            <div class="cm-breadcrumb-container">
                <ol class="breadcrumb">
                    <li><a href="#">学生数据</a></li>
                    <li><a href="#">学生数据展示</a></li>
                </ol>
            </div>
        </div>
    </nav>
</header>


<div id="global">
    <div class="container-fluid">
        <!--完成此部分-->
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <div>
                            <span style="font-size: 19px">
                                <td>学号:</td>
                                <td>${student.studentId}</td>
                                  </span><br>
                            <span style="font-size: 19px">
                                <td>学生姓名:</td>
                                <td>${student.studentName}</td>
                                   </span>


                        </div>

                    </div>

                    <div class="table-responsive">
                        <table class="table table-condensed table-hover table-striped">
                            <thead>
                            <tr class="success">
                                <td>课程</td>
                                <td>课程类型</td>
                                <td>分数</td>
                            </tr>
                            </thead>
                            <tbody id="tbody">
                            <#list grades as g>
                            <tr>
                                <td>${g.courseName}</td>
                                <td>${g.courseType}</td>
                                <td>${g.score}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                        <center><div id="pagination3" class="page fl"></div></center>
                    </div>
                </div>
            </div>
        </div>





        <!--删除-->
        <div id="delete" class="modal fade bs-example" tabindex="-1" role="dialog" aria-labelledby="admin_add_Modal_Label">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form class="form-horizontal"  method="post" id="form">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="close"><span
                                    aria-hidden="true">&times</span></button>
                            <h4 class="modal-title" id="myModal_Label" align="center">是否删除?</h4>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">是</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>








        <footer class="cm-footer"><span class="pull-right">&copy; HAUE edu.</span></footer>
    </div>


    <script src="${request.contextPath}/static/admin/js/jquery-2.1.3.min.js"></script>
    <script src="${request.contextPath}/static/admin/js/jquery.mousewheel.min.js"></script>
    <script src="${request.contextPath}/static/admin/js/jquery.cookie.min.js"></script>
    <script src="${request.contextPath}/static/admin/js/fastclick.min.js"></script>
    <script src="${request.contextPath}/static/admin/js/bootstrap.js"></script>
    <script src="${request.contextPath}/static/admin/js/clearmin.js"></script>
    <script src="${request.contextPath}/static/admin/js/select2.min.js"></script>
    <script src="${request.contextPath}/static/admin/js/studentPager.js"></script>
    <script  type="text/javascript">
    </script>


</body>
</html>