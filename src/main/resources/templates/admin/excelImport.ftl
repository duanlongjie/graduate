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
    <script type="text/javascript">
        // $(function(){
        //     $("#but").click(function(){
        //         // +$("#file").val()
        //         var path1=$("#file").val();
        //         var path="";
        //
        //         for(var i=0;i<path1.length;i++){
        //             if(path1[i]!="\\"){
        //                 path+=path1[i]
        //             }
        //             if(path1[i]=="\\"){
        //                 path+="/"
        //             }
        //
        //      }
        //         alert(path)
        //         $.ajax({
        //             url:'excel?path='+path,
        //             type:'get',
        //             success: function(data){
        //             },
        //             error: function (data) {
        //
        //             }
        //         })
        //     })
        // });
    </script>
</head>
<body id="particles">
    <h1 align="center">Excel导入</h1>
    <#--<input type="file" name="excel" id="file">-->
    <#--<input type="button" id="but" value="导入">-->

    <form action="uploadDeal" method="post" enctype="multipart/form-data" >
        <input type="file"  name="file">
        <input type="submit" id="but" value="导入">
    </form>

</body>
</html>