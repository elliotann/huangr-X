<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${context }/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${context }/js/jquery-form-2.33.js"></script>
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/gray/easyui.css"/>    
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/icon.css"/>  
	<script type="text/javascript" src="${context }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${context }/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.loading.js"></script>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
<style>
.input_text{
	width: 150px;
}
</style>
<div id="loading"></div>
<div class="main">
	<div id="useradmininfo" style="display: none;"></div>
	<form id="userform">
		<div class='buttonArea'>
			<div style="float:left;">
			 <a href="javascript:void(0)" class="button" data-options="plain:true" onclick="append()">添加</a>
			 <a href="javascript:void(0)" class="button" data-options="plain:true" onclick="syncWebChat()">同步到微信</a>
			</div>
			
		</div>
		
		<div class="clear height10"></div>
			<table class="easyui-treegrid" id="useradmindata"
			data-options="url:'menu.do?dataGrid&ajax=true',fitColumns:'true',idField: 'id',treeField: 'name',singleSelect:false,checkbox:true">
			<thead>
				<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'name',width:50,align:'center'">菜单名称</th>
						<th data-options="field:'type',width:50,align:'center'">动作类型</th>
						<th data-options="field:'key',width:50,align:'center'">菜单KEY值</th>
						<th data-options="field:'url',width:50,align:'center'">网页链接</th>
						<th data-options="field:'action',width:100,align:'center'" formatter="formatAction">操作</th>
			
				</tr>
			</thead>
		</table>

	</form>
</div>
<script type="text/javascript">
	
	
	//状态
	function forStruts(value, row, index) {
		
		if(value==1){
			return "启用";
		}
		
		return "禁用";
	}
	
	function append(){
		$("#useradmininfo").show();
	　　	$('#useradmininfo').dialog({
	　　		title: '添加公众号',		
	  		top:60,
	　　		width: 550,
	  		height:450,
	　　		closed: false,
	　　		cache: false,
	　　		href: 'menu.do?add', 	
	　　		modal: true,
	　　		buttons: [{					
	　　			 text:'保存',
	　　			 iconCls:'icon-ok',
	　　			 handler:function(){
	　　				  var savebtn = $(this);
		　　			  var disabled=savebtn.hasClass("l-btn-disabled");
		　　			  if(!disabled){
	　　				 		 addadminForm(savebtn);	
	　　				  }
	　　			 }
	　　			 },{
	　　			 text:'取消',
	　　			 handler:function(){
	　　				 $("#useradmininfo").dialog('close');
	　　				
	　　			 }
	　　		}]
	　　	});
	}
	
	function addadminForm(savebtn){
		var formflag= $("#addAdminForm").form().form('validate');
		if(formflag){
			$.Loading.show("正在保存请稍后...");
			savebtn.linkbutton("disable");	
			var options = {
				url : "menu.do?addSave&ajax=true",
				type : "POST",
				dataType : "json",
				success : function(result) {
					if(result.success){
						$.Loading.success(result.msg);
						$("#useradmininfo").dialog('close');
						$('#useradmindata').datagrid('reload');
						savebtn.linkbutton("enable");
					}else{
						$.Loading.error(result.msg);	
						 savebtn.linkbutton("enable");
					}
					
			 		},
				 error : function(e) {
					 $.Loading.error("出现错误 ，请重试");	
					 savebtn.linkbutton("enable");
					}
			};
			$('#addAdminForm').ajaxSubmit(options);	
		}
	}
	
	
	function del(id) {
		
		if (!confirm("确认要将这些订单放入回收站？")) {
			return;
		}
		var options = {
			url : "userAdmin.do?delete&ajax=true&id="+id,
			type : "POST",
			dataType : 'json',
			cache:false,
			success : function(result) {
				if (result.success) {
					$('#useradmindata').datagrid("reload");
					$.Loading.success(result.msg);
				}
				if (result.result == 0) {
					$.Loading.error(result.msg);
				}
			},
			error : function(e) {
				$.Loading.error("出现错误 ，请重试");
			}
		};

		$('#userform').ajaxSubmit(options);	
	}
	function edit(id) {
		$("#useradmininfo").show();
	　　	$('#useradmininfo').dialog({
	　　		title: '修改菜单',			
	　　		top:60,
	　　		width: 550,
	　　		height:450,
	　　		closed: false,
	　　		cache: false,
	　　		href: 'menu.do?edit&ajax=true&id='+id, 	 
	　　		modal: true,
	　　		buttons: [{					
	　　			 text:'保存',
	　　			 iconCls:'icon-ok',
	　　			 handler:function(){
	　　				var editbtn = $(this);
		　　			var disabled=editbtn.hasClass("l-btn-disabled");
		　　			if(!disabled){
	　　					editUseradminForm(editbtn);
	　　				}
	　　			 }
	　　			 },{
	　　			 text:'取消',
	　　			 handler:function(){
	　　				 $("#useradmininfo").dialog('close');
	　　			 }
	　　		}]
	　　	}); 
	}
	function editUseradminForm(editbtn){
		
		var formflag= $("#editMenuForm").form().form('validate');
		if(formflag){
			$.Loading.show("正在保存请稍后...");
			editbtn.linkbutton("disable");	
			var options = {
				url : "menu.do?editSave&ajax=true",
				type : "POST",
				dataType : "json",
				success : function(result) {
					$.Loading.success(result.msg);
					$("#useradmininfo").dialog('close');
					$('#useradmindata').datagrid('reload');
					editbtn.linkbutton("enable");
			 	},
			 	error : function(e) {
			 		$.Loading.error("出现错误 ，请重试");	
			 		editbtn.linkbutton("enable");
				}
			};
			$('#editMenuForm').ajaxSubmit(options);
		}
	}
	
    
    
function formatAction(value,row,index){
	var val="<a class='edit' title='修改' href='javascript:void(0);' onclick='edit("+row.id +")'></a><a class='delete' title='删除' href='javascript:void(0);' onclick='del("+row.id +")'></a>";
	return val;
		
}
	
	$(function(){
		$(".searchAdvanced").hide();
		//高级查询按钮
	    $("#aAdvanced").click(function () {
	        if ($("#Advanced").val() == "0") {
	            $("#Advanced").val(1);
	            $("#simpleSearch").hide();
	            //$("#aAdvanced").text("简单搜索")
	            $("#aAdvanced").addClass("searchAdvancedS");
	        } else {
	            $("#Advanced").val(0);
	            $("#simpleSearch").show();
	            //$("#aAdvanced").text("高级搜索");
	            $("#aAdvanced").removeClass("searchAdvancedS");
	        }
	        $(".searchAdvanced").slideToggle("slow");
	    });
	})
	
	function searchOrder(){
	var searchtype = $("#Advanced").val();
	var keyword = $("#searchKeyword").val();
	var username = $("#username").val();
	$("#orderdata").datagrid('load', {
		 stype:searchtype,
		 keyword:keyword,
		 username:username,
		 page:1
    });
}

	

	function syncWebChat(id){
		
		var rows = $('#useradmindata').treegrid("getSelections");
		if (rows.length < 1) {
			$.Loading.error("请选择要同步的菜单");
			return;
		}
		alert(rows.length);
		var menuIds = [];
		for(var i=0;i<rows.length;i++){
			menuIds[i] = rows[i].id;
		}
		$("#useradmininfo").show();
	　　	$('#useradmininfo').dialog({
	　　		title: '同步到微信',		
	  		top:60,
	　　		width: 550,
	  		height:450,
	　　		closed: false,
	　　		cache: false,
	　　		href: 'menu.do?toSync&menuIds='+menuIds.join(","), 	
	　　		modal: true,
	　　		buttons: [{					
	　　			 text:'同步',
	　　			 iconCls:'icon-ok',
	　　			 handler:function(){
	　　				  var savebtn = $(this);
		　　			  var disabled=savebtn.hasClass("l-btn-disabled");
		　　			  if(!disabled){
	　　				 		 syncWebChatForm(savebtn);	
	　　				  }
	　　			 }
	　　			 },{
	　　			 text:'取消',
	　　			 handler:function(){
	　　				 $("#useradmininfo").dialog('close');
	　　				
	　　			 }
	　　		}]
	　　	});
	}
	
	function syncWebChatForm(savebtn){
		var formflag= $("#addAdminForm").form().form('validate');
		if(formflag){
			$.Loading.show("正在同步请稍后...");
			savebtn.linkbutton("disable");	
			var options = {
				url : "menu.do?doSync&ajax=true",
				type : "POST",
				dataType : "json",
				success : function(result) {
					if(result.success){
						$.Loading.success(result.msg);
						$("#useradmininfo").dialog('close');
						$('#useradmindata').datagrid('reload');
						savebtn.linkbutton("enable");
					}else{
						$.Loading.error(result.msg);	
						 savebtn.linkbutton("enable");
					}
					
			 		},
				 error : function(e) {
					 $.Loading.error("出现错误 ，请重试");	
					 savebtn.linkbutton("enable");
					}
			};
			$('#addAdminForm').ajaxSubmit(options);	
		}
	}
	

</script>