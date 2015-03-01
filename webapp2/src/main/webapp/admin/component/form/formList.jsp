<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/css/button.css" rel="stylesheet" type="text/css" />
<link href="${context }/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
 <script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${ctx }/admin/js/crud.js" type="text/javascript"></script>
<style type="text/css">
	a{text-decoration:none;}
/* 搜索框 */
.searchtitle{ padding-left:28px; position:relative;}
.searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
.searchtitle span{ font-size:14px; font-weight:bold;}
.searchtitle .togglebtn{ position:absolute; top:6px; right:15px; background:url(images/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
.searchtitle .togglebtn-down{ background-position:0px -10px;}
/* 一条线 导航线 */
.navline{ height:1px; line-height:1px; width:100%;border-bottom:1px solid #f5f5f5; background-color:#D9D9D9;width:100%; }

 .jloading{ position:absolute; right:0; top:0; padding:4px; color:White; background:#6287AC; z-index:99999; border-left:1px solid #53779D; border-bottom:1px solid #53779D;filter:alpha(opacity=70);opacity:0.70;}
 
 
.chk-icon{ background:url(../lib/ligerUI/skins/Aqua/images/controls/checkbox.gif) 0px 0px; height:13px; line-height:13px; width:13px; margin:4px 20px; display:block; cursor:pointer;}
.chk-icon-selected{ background-position:0px -13px;}

/* 按钮 */
.button{line-height:25px;height:25px;background:url('images/button1.gif') repeat-x; position:relative; margin-top:8px; margin-bottom:8px;color:#2C69A2; width:120px; cursor:pointer;}
.button-l,.button-r{ width:2px;height:25px;background:url('images/button1.gif') no-repeat; position:absolute; }
.button-l{ left:0px; background-position:0px -25px;}
.button-r{ right:0px;background-position:0px -50px;}
.button-icon{ position:absolute; left:7px; top:6px;}
.button img{ width:16px; height:16px;}
.button span{ display:block; margin-left:30px;text-align: center; } 
.buttonnoicon span{ margin-left:4px; } 
 
 
.button2,.button2 .button-l,.button2 .button-r{background-image:url('images/button2.gif'); line-height:23px; height:23px; color:#333;}
.button2 .button-icon{   top:4px;}
.button2 .button-l,.button2 .button-r{ background-position:0px -23px; width:1px;}
</style>
<script type="text/javascript">
	var listgrid;
	$(function (){
     	listgrid =  $("#maingrid").ligerGrid({
              height:'100%',
              columns: [
              { display: '表单名', name: 'formName', align: 'center', width: 100, minWidth: 60 },
              { display: '表单编码', name: 'code', align: 'center', width: 100, minWidth: 60 },
              { display: '创建时间', name: 'createTime', minWidth: 120 },
              { display: '创建人', name: 'createBy', minWidth: 120 },
              { display: '是否同步', name: 'isSynDB', minWidth: 140,render:function(item){
            	  if(item.isSynDB==1){
            		  return '是';
            	  }else{
            		  return '否';
            	  }
              } },
              { display: '表单版本', name: 'version' },
              { display: '操作', name: '',width:'auto',render:function(item){
             	 return "<a href='javascript:void(0)' onclick='toEdit("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delUser("+item.id+")'>删除</a>";
              } }
              ], url:'designer.do?dataGrid&ajax=true',  pageSize:30 ,rownumbers:true,
              toolbar:{ items: [
                                         { text: '增加', click: addForm, icon: 'add' },
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
	function addForm()
    {
		 CRUD.addOrUpdateDialog('增加表单','designer.do?toDesigner',400,800);
    }
    function delUser(userId)
    {
    	CRUD.delObj('user.do?delete',userId);
    }
    function toEdit(userId){
    	CRUD.addOrUpdateDialog('修改表单','designer.do?modify&id='+userId,300,null);
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
				<span>搜索</span><img src="/jeap1.0/admin/images/icons/searchtool.gif" />
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
