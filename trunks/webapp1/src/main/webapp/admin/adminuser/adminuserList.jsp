<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/table.css"/>
    <link rel="stylesheet" href="../../adminthemes/blue/lib/smoke/smoke.css"/>

    <link rel="stylesheet" type="text/css" href="/jeap/adminthemes/blue/dialog/jquery.confirm/jquery.confirm.css" />
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/table.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/dialog.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/bootstrap.min.js"></script>
    <script src="/jeap/adminthemes/blue/lib/smoke/smoke.js"></script>
    <script src="/jeap/adminthemes/blue/dialog/jquery.confirm/jquery.confirm.js"></script>



    <style>
        .tableactive{
            background-color: #000000;
        }
    </style>
</head>
<body style="background-color: #EEEEEE;">


<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="btn-group sepH_b">
            <button class="btn dropdown-toggle btn-default btn-sm" data-toggle="dropdown">操作 <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a data-tableid="smpl_tbl" class="delete_rows_simple" href="#" onclick="addAdminUser()"><i class="icon-trash"></i> 增加</a>
                </li>
                <li><a href="javascript:void(0)">修改</a></li>
                <li><a href="javascript:void(0)">删除</a></li>
            </ul>
        </div>
<table:table items="pageOption">
    <table:header>
        <table:td style="width: 245px;">用户编号</table:td>
        <table:td style="width: 368px;">用户名</table:td>
        <table:td style="width: 339px;">真实姓名</table:td>
        <table:td style="width: 209px;">状态</table:td>
        <table:td style="width: 150px;">操作</table:td>
    </table:header>
    <table:body item="adminUser">
        <table:td>${adminUser.id}</table:td>
        <table:td>${adminUser.username}</table:td>
        <table:td>${adminUser.realName}</table:td>
        <table:td><span class="label label-success"> ${adminUser.status}</span></table:td>
        <table:td><a href="toAdd.do?id=${adminUser.id}">修改</a> <a href="javascript:void(0)" onclick="delAdminUser('${adminUser.id}')">删除</a></table:td>
    </table:body>
</table:table>

    </div>
    </div>
<script>

    $(function(){
        $.jeapDefaults.table.changeColor();

    })

    function addAdminUser(){
        location.href = "toAdd.do";
    }
    function delAdminUser(userid){
        alert($.jeapDefaults.dialog.confirm("确认删除","确认删除?"));
        smoke.confirm("确定删除?", function(yes){

            if (yes){
                $.ajax({
                    type:'post',
                    url:'delAdminUser.do',
                    dataType:'json',
                    data:'id='+userid,
                    success:function(result){
                        if(result.success){
                            $.ligerDialog.waitting('操作成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                location = "list.do";
                            }, 1000);
                        }else{
                            alert("删除出错!");
                        }
                    },
                    error:function(e){
                        alert("删除出错!"+e);
                    }
                });
            }
        }, {
            ok: "确定",
            cancel: "取消",
            classname: "custom-class",
            reverseButtons: true
        });

    }
</script>
</body>
</html>
