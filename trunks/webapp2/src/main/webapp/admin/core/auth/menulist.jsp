<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
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
<div class="main">
	<div class="buttonArea">
		<a href="javascript:void(0)" class="button blueButton"
			data-options="iconCls:'icon-add',plain:true" onclick="append()">添加菜单</a>
		<a href="javascript:void(0)" class="button" id="submitform">保存排序</a>
	</div>
	<form action="" id="catform">
		<table class="easyui-treegrid" id="catdata"
			data-options="url:'menu.do?dataGrid&ajax=true',fitColumns:'true',idField: 'id',treeField: 'title'">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,align:'center'">ID</th>
					<th data-options="field:'title',width:200">名称</th>
					<th data-options="field:'url',width:200">url</th>
					<th data-options="field:'menutype',width:80" formatter="formatType">类型</th>
					<th data-options="field:'target',width:100">target</th>
			
					<th data-options="field:'sorder',width:80"
						formatter="formatGoodscount">排序</th>
					<th data-options="field:'action',width:100,align:'center'" formatter="formatAction">操作</th>
			
				</tr>
			</thead>
		</table>
	</form>

	<div id="divdia" style="display: none;"></div>
</div>
<script type="text/javascript">
function formatAction(value,row,index){
	var val="<a class='add' title='添加子' href='javascript:void(0);' onclick='append("+row.id +",1)'></a><a class='edit' title='修改' href='javascript:void(0);' onclick='append("+row.id +",2)'></a><a class='delete' title='删除' href='javascript:void(0);' onclick='del("+row.id +")'></a>";
	return val;
		
	}
	function append(id, obj) {
		var map = {}; // Map map = new HashMap();
		if (!id) {
			map["href"] = "menu.do?add";
			map["formId"] = "#addForm";
			map["url"] = "menu.do?saveAdd&ajax=true";
			map["title"] = "添加菜单";
			map["loadshow"] = "正在添加......";
		} else {
			if (obj == 1) {
				map["href"] = "menu.do?add&id=" + id;
				map["formId"] = "#addchildrenForm";
				map["url"] = "menu.do?saveAdd&ajax=true";
				map["title"] = "添加子菜单";
				map["loadshow"] = "正在添加......";
			} else {
				map["href"] = "menu.do?edit&id=" + id;
				map["formId"] = "#editForm";
				map["url"] = "menu.do?saveEdit&ajax=true";
				map["title"] = "修改菜单";
				map["loadshow"] = "正在修改......";
			}

		}
		map["divDialog"] = "#divdia";
		map["gridreload"] = "#catdata";
		addDialog(map);
	}
	function addDialog(map) {
		$(map["divDialog"]).show();
		$(map["divDialog"]).dialog({
			title : map["title"],
			width : 520,
			height : 380,
			closed : false,
			cache : false,
			href : map["href"],
			modal : true,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					var savebtn = $(this);
	　　				var disabled=savebtn.hasClass("l-btn-disabled");
	　　				if(!disabled){
						 submitForm(map,savebtn);
					}
				}
			}, {
				text : '取消',
				handler : function() {
					$(map["divDialog"]).dialog("close");
				}
			} ]
		});
	}
	function submitForm(map,savebtn) {
		var formflag = $(map["formId"]).form().form('validate');
		if (formflag) {
			$.Loading.show("正在保存请稍后...");
			savebtn.linkbutton("disable");	
			var options = {
				url : map["url"],
				type : "POST",
				dataType : 'json',
				success : function(result) {
					if (result.success) {
						$(map["divDialog"]).dialog('close');
						$(map["gridreload"]).treegrid('reload');
						$.Loading.success(result.msg);
					}
					if (!result.success) {
						$.Loading.error(result.msg);
					}
					savebtn.linkbutton("enable");
				},
				error : function(e) {
					$.Loading.error("出现错误 ，请重试");
					savebtn.linkbutton("enable");
				}
			};
			$(map["formId"]).ajaxSubmit(options);
		}else{
			savebtn.linkbutton("enable");
			$.Loading.hide();
		}
	}

	function clearForm(map) {
		$(map["formId"]).form('clear');
	}

	function formatAdd(value, row, index) {
		var val = "<a href='javascript:void(0);' class='add' onclick='append(" + row.id
				+ ",1)'><img src='images/transparent.gif'></a>";
		return val;
	}
	function formatEdit(value, row, index) {
		var val = "<a class='edit' title='修改' href='javascript:void(0);' onclick='append("
				+ row.id + ",2)' ></a>";
		return val;
	}
	function formatDelete(value, row, index) {
		var val = '<a href="javascript:;" class="delete" onclick="del('
				+ row.id
				+ ')"><img catid="'+row.cat_id+'" src="images/transparent.gif"></a>';
		return val;
	}

	function formatGoodscount(value, row, index) {
		var val = '<input type="text" class="receiptsInputText" autocomplete="off" value="'+value+'" style="width:30px" name="cat_sorts">';
		val+='<input type="hidden" name="cat_ids" value="'+row.id+'" > '
		return val;
	}
	
	function formatType(value, row, index){
	    if(value==1){
	        return "系统菜单";
	    }else{
	        return "应用菜单";
	    }
	}

	function del(catid) {
		if (!confirm("确定要删除此类别吗？")) {
			return;
		}
		$.Loading.show("正在删除......");
		$.ajax({
			url : "cat!delete.do?ajax=yes&cat_id=" + catid,
			type : "POST",
			dataType : 'json',
			success : function(result) {
				if (result.result == 1) {
					$.Loading.success(result.message);
					$("#catdata").treegrid('reload');
				}
				if (result.result == 0) {
					$.Loading.error(result.message);
				}
			},
			error : function(e) {
				$.Loading.error("出现错误 ，请重试");
			}
		});

	}
	
	
	$(function(){
		$("#submitform").click(function(){
			$.Loading.show('正在保存排序，请稍侯...');
			var options = {
					url :"cat!saveSort.do?ajax=yes",
					type : "POST",
					dataType : 'json',
					success : function(result) {				
					 	if(result.result==1){
					 		$.Loading.success(result.message);
					 		$("#catdata").treegrid('reload');
					 	}else{
					 		alert(result.message);
					 	}
					},
					error : function(e) {
						$.Loading.hide();
						alert("出错啦:(");
	 				}
	 		};
	 
			$("#catform").ajaxSubmit(options);
		})
	})
</script>