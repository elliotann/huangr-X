<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/validate.css" rel="stylesheet" type="text/css" />
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="addAdminForm" method="post" class="validate" >
			<table>
			
				<tr>
					<th><label class="text">菜单名称：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="name" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">动作类型：</label></th>
					<td>
						<select class="easyui-combobox combo" name="type" style="width:100px;" name="chatType" data-options="onSelect:chatTypeSelect">
							<option value="click">点击</option>
							<option value="view">链接</option>
						</select>
					</td>
				</tr>
				<tr id="roletr">
					<th><label class="text">菜单KEY值：</label></th>
					<td>
						<input type="text" name="key"  class="input_text easyui-validatebox" data-options="required:true"  />

					</td>		
				</tr>
				
			
				<tr id="menuKey" style="display:none">
					<th><label class="text">网页链接：</label></th>
					<td><input type="text" name="url" id="url" class="input_text easyui-validatebox" data-options="required:true"  /></td>
				</tr>
			
				<tr class="addtr">
					<th>上级菜单:</th>
					<td><input class="easyui-combotree combo" name="parent.id"
						data-options="url:'menu.do?menuDataGrid&ajax=true',method:'post',required:false,height:28"
						style="width: 305px;" value="${id }">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
$.extend($.fn.validatebox.methods, {  
    remove: function(jq, newposition){  
        return jq.each(function(){  
        
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
        });  
    },
    reduce: function(jq, newposition){  
        return jq.each(function(){  
           var opt = $(this).data().validatebox.options;
           $(this).addClass("validatebox-text").validatebox(opt);
        });  
    }   
});

//使用


function chatTypeSelect(rec){
	if(rec.value=="view"){
		$('#url').validatebox('reduce'); //还原
		$("#menuKey").show();
	}else if(rec.value="click"){
		$('#url').validatebox('remove'); //删除
		$("#menuKey").hide();
	}
}
</script>

