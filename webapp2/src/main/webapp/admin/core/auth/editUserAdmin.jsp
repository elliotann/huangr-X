<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${ctx }/adminthemes/default/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/adminthemes/default/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/adminthemes/default/css/validate.css" rel="stylesheet" type="text/css" />
   <style>
#repwdtr,#pwdtr{display:none}
</style>
<div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="editAdminForm" method="post" class="validate" >
		<input type="hidden" name="id" value="${adminUser.id }" />
		<input type="hidden" name="password" value="${adminUser.password }" />
			<table>
				
				<tr>
					<th><label class="text">用户名：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="username" value="${adminUser.username }" data-options="required:true"  dataType="string" isrequired="true" /></td>
				</tr>
				<tr>
					<th><label class="text">email：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="email" value="${adminUser.email }" data-options="required:true"  dataType="string" isrequired="true" /></td>
				</tr>
				<tr id="roletr">
					<th><label class="text">角色：</label></th>
					<td>
						<ul style="width:100%" id="rolesbox">
							<c:forEach items="${roleList }" var="role">
								<li style="width:33%;display:block">
								<input type="checkbox" name="roleids" value="${role.id }" 
									<c:forEach items="${userRoles }" var="userrele">
										<c:if test="${ userrele.role.id ==role.id}">
										checked="checked"
										</c:if>
										 
									</c:forEach>>
									
								${role.rolename }&nbsp;</li>
							</c:forEach>
						</ul> 
					</td>		
				</tr>
					
				<tr>
					<th><label class="text">状态：</label></th>
					<td>
						<input type="radio"  name="state" value="1" <c:if test="${adminUser.state==1}">checked="checked"</c:if>>启用&nbsp;&nbsp;
						<input type="radio"  name="state" value="0" <c:if test="${adminUser.state==0}">checked="checked"</c:if>>禁用 
					</td>
				</tr>
				<tr>
					<th><label class="text">姓名：</label></th>
					<td><input type="text" class="input_text easyui-validatebox" name="realname" value="${adminUser.realname }"   data-options="required:true"/></td>
				</tr>
				<tr>
					<th><label class="text">办公电话：</label></th>
					<td><input type="text" name="officeTel" class="input_text easyui-validatebox"  data-options="required:true"  value="${adminUser.officeTel }"/></td>
				</tr>
				<tr>
					<th><label class="text">部门：</label></th>
					<td><input type="text" class="input_text" name="adminUser.userdept" value="${adminUser.userdept }"  /></td>
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
	$("#updatePwd").click(function(){
		if(this.checked){
			$("#pwdtr").show();
			$("#repwdtr").show();			
		}else{
			$("#pwdtr").hide();
			$("#repwdtr").hide();
		}
	});
});
</script>