<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="/jeap1.0/js/admin/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="/jeap1.0/adminthemes/default/css/button.css" rel="stylesheet" type="text/css" />
<link href="/jeap1.0/adminthemes/default/css/global.css" rel="stylesheet" type="text/css" />
<script src="/jeap1.0/js/commons/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/core/base.js" type="text/javascript"></script>

<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="../CustomersData.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script type="text/javascript">
    function delUser(userId)
    {
        alert(item.text);
    }
    var listgrid;
        $(function ()
        {
        	listgrid =  $("#maingrid4").ligerGrid({
                 height:'100%',
                 columns: [
                 { display: '用户名', name: 'username', align: 'center', width: 100, minWidth: 60 },
                 { display: '姓名', name: 'realName', align: 'center', width: 100, minWidth: 60 },
                 { display: '邮箱', name: 'email', minWidth: 120 },
                 { display: '办公电话', name: 'tel', minWidth: 120 },
                 { display: '最后登录时间', name: 'lastLoginTime', minWidth: 140 },
                 { display: '登录次数', name: 'loginCount' },
                 { display: '所属公司', name: 'companyId' },
                 { display: '状态', name: 'status',render:function(item){
                	 if(item.status=='ACTIVE'){
                		 return "激活";
                	 }else{
                		 return "禁用";
                	 }
                 } },
                 { display: '操作', name: '',width:'auto',render:function(item){
                	 return "<a href='#'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delUser("+item.id+")'>删除</a>";
                 } }
                 ], url:'user.do?datalist',  pageSize:30 ,rownumbers:true
             });
        });
        function addUser()
        {
            $.ligerDialog.open({name:'openDiag', title:'增加用户',url: 'user.do?toAdd', height: 300, width: null, buttons: [
                { text: '确定', onclick: function (item, dialog) { openDiag.submitForm(); },cls:'l-dialog-btn-highlight' },
                { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
             ], isResize: true,width:600,height:500
            });
        }
    </script>
</head>
<body style="padding: 3px; overflow: hidden;">
	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="btn btn-primary" onclick="addUser()">增加</a><span
			style="float: right;"><span id="simpleSearch"><input
				id="32131" class="form-control" type="text" value="" size="30"
				style="width: 200px;" placeholder="栽植" name="313"><a
					href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true" onclick="">搜索</a></span><a
			href="javascript:void(0)" class="button" data-options="plain:true"
			id="aAdvanced">高级搜索</a></span>
	</div>
	<div style="display: block;" class="searchAdvanced"><input id="Advanced" name="Advanced" type="hidden" value="0"/><table width="98%" border="0" cellspacing="0" cellpadding="8">
	<tr><th width="70" align="right">234234</th><td><input type="text"/></td>
	<div id="maingrid4" style="margin: 0; padding: 0"></div>
</body>
</html>
