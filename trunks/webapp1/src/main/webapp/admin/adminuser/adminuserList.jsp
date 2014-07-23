<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/style.css"/>
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="../../adminthemes/default/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="../../adminthemes/default/js/jquery.flot.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Aqua/css/ligerui-all.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Gray/css/all.css" />
    <script src="../../adminthemes/default/js/base.js"></script>
    <script src="../../adminthemes/default/js/ligerui/js/plugins/ligerDialog.js"></script>
</head>
<body style="background-color: #EEEEEE;">


<!-- START WIDGET LIST -->
<ul class="widgetlist">
    <button class="button button_blue" onclick="addAdminUser()">增加</button>
    &nbsp;
</ul>
<!-- END WIDGET LIST -->

<div class="clear"></div>
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

<br>
<script>
    function addAdminUser(){
        location.href = "toAdd.do";
    }
    function delAdminUser(userid){
        $.ligerDialog.confirm('确定删除', function (yes)
        {
            if(yes){
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
        });
    }
</script>
</body>
</html>
