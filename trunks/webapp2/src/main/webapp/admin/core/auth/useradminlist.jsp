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
	<form id="orderform">
		<div class='buttonArea'>
			<div style="float:left;">
			 <a href="javascript:void(0)" class="button" data-options="plain:true" onclick="append()">添加</a>
			</div>
			 <span style="float: right;height:28px;"> 
			 	<a href="javascript:void(0)" class="button b_fr" data-options="plain:true" id="aAdvanced" >高级搜索</a>
			 	<a href="javascript:void(0)" class="button b_fr" id="search" data-options="plain:true" onclick="searchOrder()">搜索</a>
				<input id="searchKeyword" class="input_text b_fr mr5" type="text" value="" size="30" placeholder="请用户名" name="searchKeyWord" />
			</span>
		</div>

		<div style="display: block;" class="searchAdvanced">
			<input id="Advanced" name="Advanced" type="hidden" value="0" />
			<table width="98%" border="0" cellspacing="0" cellpadding="8">
				<tr>
					<td width="70" align="right">用户名</td>
					<td><input type="text" value="" id="username" class="input_text">
					</td>

					<td width="70" align="right"></td>
					<td></td>

					<td width="70" align="right"></td>
					<td></td>

					<td width="70" align="right"></td>
					<td></td>
				</tr>
				
				<tr>
					<td width="70" align="right"></td>
					<td colspan="7" align="center"><a id="searchAdvance"
						class="button blueButton" onclick="searchOrder()"
						href="javascript:;">开始搜索</a></td>
				</tr>
			</table>
		</div>

		<div class="clear height10"></div>
		<div class="shadowBoxWhite tableDiv">
			<table class="easyui-datagrid"
				data-options="url:'userAdmin.do?dataGrid&ajax=yes',pageList: [5,10,15,20],pageSize:30,fitColumns:'true',queryParams:{'complete':''}"
				pagination="true" width="width" id="orderdata" sortName="order_id" sortOrder="desc">
				<thead>
					<tr>
						<th data-options="field:'order_id',checkbox:true,width:100"></th>
						<th data-options="field:'username',width:50,align:'center'">用户名</th>
						<th data-options="field:'realname',width:50,align:'center'">姓名</th>
						<th data-options="field:'email',width:50,align:'center'">邮箱</th>
						<th data-options="field:'tel',width:50,align:'center'">办公电话</th>
						<th data-options="field:'lastLoginTime',width:50,align:'center'">最后登录时间</th>
						<th data-options="field:'loginCount',width:50,align:'center'">登录次数</th>
						<th data-options="field:'state',width:50,align:'center'" formatter="forStruts">状态</th>
						<th data-options="field:'action',width:100,align:'center'" formatter="formatAction">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	function forsn(value,row,index){
		var val="<a href='#' onclick=\"newTab('查看订单详细','${ctx}/shop/admin/order!detail.do?orderId=&sn=&logi_no=&uname=&ship=&status=')\">"+1+"</a>"
		return val;
	}
	function forMoney(value, row, index) {
		var val = "￥" + value;
		return val;
	}
	function formatDate(value,row,index){
		if(value==null){
			return "";
		}
		else{
			return getFormatDateByLong(value, "yyyy-MM-dd");
		}
		
	}
	
	//订单状态
	function forStruts(value, row, index) {
		
		if(value==1){
			return "启用";
		}
		
		return "禁用";
	}
	
	function append(){
		$("#useradmininfo").show();
	　　	$('#useradmininfo').dialog({
	　　		title: '添加管理员',		
	  		top:60,
	　　		width: 550,
	  		height:450,
	　　		closed: false,
	　　		cache: false,
	　　		href: 'userAdmin.do?add', 	
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
	　　				 $('#addadminForm')[0].reset() ;
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
				url : "userAdmin.do?addSave&ajax=true",
				type : "POST",
				dataType : "json",
				success : function(result) {
					$.Loading.success(result.message);
					$("#useradmininfo").dialog('close');
					$('#useradmindata').datagrid('reload');
					savebtn.linkbutton("enable");
			 		},
				 error : function(e) {
					 $.Loading.error("出现错误 ，请重试");	
					 savebtn.linkbutton("enable");
					}
			};
			$('#addAdminForm').ajaxSubmit(options);	
		}
	}
	
	function getType(exMap,value){
		var val;
		$.each(exMap,function(key,values){ 
		    if(value==key){
		    	val=values;
		    }
		});
		return val;
	}
	
	function del() {
		var rows = $('#orderdata').datagrid("getSelections");
		if (rows.length < 1) {
			$.Loading.error("请选择要放入回收站的订单");
			return;
		}
		if (!confirm("确认要将这些订单放入回收站？")) {
			return;
		}
		var options = {
			url : "order!delete.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			cache:false,
			success : function(result) {
				if (result.result == 1) {
					$('#orderdata').datagrid("reload");
					$.Loading.success(result.message);
				}
				if (result.result == 0) {
					$.Loading.error(result.message);
				}
			},
			error : function(e) {
				$.Loading.error("出现错误 ，请重试");
			}
		};

		$('#orderform').ajaxSubmit(options);	
}
	
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
	text: '清空',
	handler: function(target){
		 $('#start_time').datebox('setValue',"");
	}
	});
	
	var e_buttons = $.extend([], $.fn.datebox.defaults.buttons);
	e_buttons.splice(1, 0, {
	text: '清空',
	handler: function(target){
		 $('#end_time').datebox('setValue',"");
	}
	});
    
    
function formatAction(value,row,index){
	var val="<a class='delete' title='删除' href='javascript:void(0);' onclick='del("+row.userid +")'></a><a class='edit' title='修改' href='javascript:void(0);' onclick='edit("+row.userid +")'></a><a class='view' title='查看' href='#' onclick=\"newTab('查看订单详细','')\"></a>";
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

	function formatTime(value,row,index){
		var val = getFormatDateByLong(value, "yyyy-MM-dd");
		return val;
	}
</script>