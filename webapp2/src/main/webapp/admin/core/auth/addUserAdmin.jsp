<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />	

	
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="addAdminForm" method="post" class="validate" >
			<table>
			
				<tr>
					<th><label class="text">用户名：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="username" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">密码：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="password" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">邮箱：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="email" data-options="required:true" /></td>
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
						<input type="radio"  name="state" value="1" checked=true>启用&nbsp;&nbsp;
						<input type="radio"  name="state" value="0">禁用 
					</td>
				</tr>
				<tr>
					<th><label class="text">姓名：</label></th>
					<td><input type="text" name="realname"  class="input_text easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th><label class="text">编号：</label></th>
					<td><input type="text" name="userno" class="input_text"  /></td>
				</tr>
				<tr>
					<th><label class="text">部门：</label></th>
					<td><input type="text" name="userdept"  class="input_text" /></td>
				</tr>
				<tr>
					<th><label class="text">备注：</label></th>
					<td><input type="text" name="remark" class="input_text"  /></td>
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

