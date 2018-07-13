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
    <div id="cm-menu-content">
        <div id="cm-menu-items-wrapper">
            <div id="cm-menu-scroller">
                <ul class="cm-menu-items">
                    <#assign mainMenuList=currentAdmin.mainMenuList/>
                    <#list mainMenuList as mainMenu>
                        <#if mainMenu.subMenuList?size==1>
                        <li><a href="${mainMenu.subMenuList[0].href}" class="${mainMenu.icon}">${mainMenu.subMenuList[0].name}</a></li>
                        <#else>
                        <li class="cm-submenu">
                            <a class="${mainMenu.icon}">${mainMenu.title} <span class="caret"></span></a>
                            <ul>
                            <#assign subMenuList=mainMenu.subMenuList/>
                            <#list subMenuList as submenu>
                                <li><a href="${submenu.href}">${submenu.name}</a></li>
                            </#list>
                            </ul>
                        </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>
<header id="cm-header">
    <nav class="cm-navbar cm-navbar-primary">
        <div class="btn btn-primary md-menu-white hidden-md hidden-lg" data-toggle="cm-menu"></div>
        <div class="cm-flex"><h1>课程导入</h1></div>
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
                    <li><a href="#">数据导入</a></li>
                    <li><a href="#">课程导入</a></li>
                </ol>
            </div>
        </div>
    </nav>
</header>


<div id="global">
    <div class="container-fluid">
        <!--完成此部分-->
        <div class="row">
            <div >
                <div class="panel panel-default">
                    <div class="panel-body">
                                <form class="form-horizontal"  role="form" action="courseDeal" method="post" enctype="multipart/form-data" >
                                    <div >
                                        <h4><i>导入课程</i></h4>
                                        <input    type="file"  name="file"> <br>
                                        <input    type="submit" id="but" value="导入课程">
                                    </div>
                                </form>
                    </div>

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