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
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
 <script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
 
<script src="${ctx}/admin/js/crud.js" type="text/javascript"></script>
<script type="text/javascript">
	var listgrid;
	$(function (){
     	listgrid =  $("#maingrid").ligerGrid({
              height:'99%',
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
             	 return "<a href='javascript:void(0)' onclick='toEdit("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delUser("+item.id+")'>删除</a>";
              } }
              ], url:'userAdmin.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
              toolbar:{ items: [
                                         { text: '增加', click: addUser, icon: 'add' },
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
      	$("#btn1container").click(function(){
      		 listgrid.loadServerData("username="+$("#username").val());
              return false;
      	});
     });
    function delUser(userId)
    {
    	CRUD.delObj('user.do?delete',userId);
    }
    function toEdit(userId){
    	CRUD.addOrUpdateDialog('修改用户','user.do?toEdit&id='+userId,300,null);
    }
    function addUser(){
        CRUD.addOrUpdateDialog('增加用户','user.do?toAdd',300,null);
    } 
    </script>
 
</head>
<body style="padding: 3px; overflow: hidden;">
	<div>
		<div style="width: 100%">
			<div class="searchtitle">
				<span>搜索</span><img src="${ctx }/admin/images/icons/searchtool.gif" />
				<div class="togglebtn"></div>
			</div>
			<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>
			<div class="searchbox">
				<form id="searchForm">
					用户名：<input type="text" value="" class="liger-textbox" id="username"/> 
				</form>
				<ul><li id="btn1container"><div class="button button2 buttonnoicon" style="width:60px"><div class="button-l"></div><div class="button-r"></div> <span>搜索</span></div></li></ul>
				<div class="l-clear"></div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</div>
</body>
</html>
