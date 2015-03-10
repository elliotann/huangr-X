<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

	<script type="text/javascript" src="${context }/js/jquery-form-2.33.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/gray/easyui.css"/>    
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/icon.css"/>  
	<script type="text/javascript" src="${context }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${context }/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.loading.js"></script>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="addAdminForm" method="post" class="validate" >
			<table>
			
				<tr>
					<th><label class="text">用户名：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="adminUser.username" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">密码：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="adminUser.password" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">邮箱：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="adminUser.email" data-options="required:true" /></td>
				</tr>
				<tr id="roletr">
					<th><label class="text">角色：</label></th>
					<td>
						<ul style="width:100%" id="rolesbox">
							<c:forEach items = "${roleList }" var="role">
							<li style="width:33%;display:block"><input autocomplete="off" type="checkbox" name="roleids" value="${role.id }"  />
								${role.rolename }&nbsp;</li>
							</c:forEach>
							
						</ul>
					</td>		
				</tr>
				
				<tr>
					<th><label class="text">状态：</label></th>
					<td>
						<input type="radio"  name="adminUser.state" value="1" checked=true>启用&nbsp;&nbsp;
						<input type="radio"  name="adminUser.state" value="0">禁用 
					</td>
				</tr>
				<tr>
					<th><label class="text">姓名：</label></th>
					<td><input type="text" name="adminUser.realname"  class="input_text easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th><label class="text">编号：</label></th>
					<td><input type="text" name="adminUser.userno" class="input_text"  /></td>
				</tr>
				<tr>
					<th><label class="text">部门：</label></th>
					<td><input type="text" name="adminUser.userdept"  class="input_text" /></td>
				</tr>
				<tr>
					<th><label class="text">备注：</label></th>
					<td><input type="text" name="adminUser.remark" class="input_text"  /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#notSuperChk").click(function(){
		if(this.checked)
		$("#roletr").show();
	});
	$("#superChk").click(function(){
		if(this.checked)
		$("#roletr").hide();
	});	
});	

