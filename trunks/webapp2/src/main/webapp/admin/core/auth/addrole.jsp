<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="addAdminForm" method="post" class="validate" >
		 	<input type="hidden" name="id" id="roleid" value="${role.id==null?0:role.id }" />
		 	 <input type="hidden" name="ajax"  value="true" />
			<table>
			
				<tr>
					<th><label class="text">角色名称：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="rolename" data-options="required:true" value="${role.rolename}"/></td>
				</tr>
				
				<tr>
					<th><label class="text">备注：</label></th>
					<td>
						<textarea style="width:300px;height:150px;" name="rolememo" class="input_text"> ${role.rolememo} </textarea>
					</td>
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

