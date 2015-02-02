<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

<link href="/jeap1.0/adminthemes/default/css/button.css" rel="stylesheet" type="text/css" />
<link href="${context }/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script> 
<script src="${context }/js/ligerui/js/ligerui.all.js" type="text/javascript"></script> 

<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
 <script src="${context}/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
 <script src="${ctx }/statics/js/admin/fn.grid.js"></script>
<script src="${ctx}/admin/js/crud.js" type="text/javascript"></script>
<script type="text/javascript">
	var listgrid;
	var searchForm;
	$(function (){
     	listgrid =  $("#maingrid").ligerGrid({
              height:'99%',
              columns: [
              { display: 'ID', name: 'id', align: 'center', width: 100, minWidth: 60 },
              { display: '角色名称', name: 'rolename', align: 'center', width: 100, minWidth: 60 },
              { display: '描述', name: 'rolememo', minWidth: 120 },
              { display: '操作', name: '',width:'auto',render:function(item){
             	 return "<a href='javascript:void(0)' onclick='toEdit("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delUser("+item.id+")'>删除</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='setAuth("+item.id+")'>设置权限</a>";
              } }
              ], url:'role.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
              toolbar:{ items: [
                                         { text: '增加', click: addRole, icon: 'add' },
                                         { line: true }]
                                         }
          });
     	//搜索框 收缩/展开
     	$(".searchtitle .togglebtn").live('click', function ()
     	{
     	    if ($(this).hasClass("togglebtn-down")) $(this).removeClass("togglebtn-down");
     	    else $(this).addClass("togglebtn-down");
     	    var searchbox = $(this).parent().nextAll("div.searchbox:first");
     	    searchbox.slideToggle('fast');
     	}); 
     	var fieldsdata = [{ label: "角色名", name: "rolename", width: 170, labelWidth: 50}];
		
		GridUI.init(fieldsdata, null);
		GridUI.buildSearch();
      	$("#btn1container").click(function(){
      		  var searchBean = searchForm.getData();
      		  listgrid.loadServerData("username="+searchBean.username);
              return false;
      	});
     });
	
    function delUser(userId)
    {
    	CRUD.delObj('role.do?delete&id=',userId);
    }
    function toEdit(userId){
    	CRUD.addOrUpdateDialog('修改角色','role.do?edit&roleid='+userId,300,null);
    }
    function addRole(){
    	CRUD.addOrUpdateDialog('增加角色','role.do?add',500,700);
    } 
    function setAuth(userId){
    	CRUD.addOrUpdateDialog('修改角色','auth.do?add&ajax=yes&roleId='+userId,300,500);
    }
    </script>
 
</head>
<body style="padding: 3px; overflow: hidden;">
	<div>
		<div style="width: 98%">
			<div class="searchtitle">
				<span>搜索</span><img src="/jeap1.0/admin/images/icons/searchtool.gif">
				<div class="togglebtn"></div>
			</div>
			<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>
			<div class="searchbox">
				<form id="searchForm" >
				</form>
				
			
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</div>
</body>
</html>
