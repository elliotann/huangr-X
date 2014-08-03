<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link href="/jeap/adminthemes/default/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="/jeap/adminthemes/default/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="/jeap/adminthemes/default/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/adminthemes/default/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="/jeap/adminthemes/default/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="/jeap/adminthemes/default/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="/jeap/adminthemes/default/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="/jeap/adminthemes/default/js/mylib/crud.js" type="text/javascript"></script>
</head>
<body >


<div id="tableTest" style="background-color: #EEEEEE;"></div>
<script>

    $(function(){
        $("#tableTest").ligerGrid({ columns: [
            { display: '主键', name: 'id', align: 'center', width: 100, minWidth: 60 },
            { display: '用户名', name: 'username', minWidth: 120 },
            { display: '真实姓名', name: 'realName', minWidth: 140 },
            { display: '状态', name: 'status' }
        ],
        url:'dataGrid.do',
            toolbar: { items: [
                { text: '增加', click: addAdminUser, icon: 'add' },
                { line: true },
                { text: '修改', click: addAdminUser, icon: 'modify' },
                { line: true },
                { text: '删除', click: delAdminUser, icon: 'delete' }
            ]
            }
        })

    })

    function addAdminUser(item){
        addOrUpdateDialog(item,'增加管理员','toAdd.do',500,700);
    }
    function delAdminUser(userid){
        $.jeapDefaults.dialog.confirm("确认删除","确认删除?",function(){
            $.ajax({
                type:'post',
                url:'delAdminUser.do',
                dataType:'json',
                data:'id='+userid,
                success:function(result){
                    if(result.success){
                        $.jeapDefaults.dialog.waiting('操作成功');
                        setTimeout(function ()
                        {
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
        });
    }
</script>
</body>
</html>
