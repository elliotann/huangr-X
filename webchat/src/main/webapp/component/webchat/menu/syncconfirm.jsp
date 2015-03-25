<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/validate.css" rel="stylesheet" type="text/css" />
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="addAdminForm" method="post" class="validate" >
			<input type="hidden" name="menuIds" value="${menuIds }"/>
			<table>
				<tr class="addtr">
					<th>账号选择:</th>
					<td><input class="easyui-combotree combo" name="accountId"
						data-options="url:'config.do?menuDataGrid&ajax=true',method:'post',required:false,height:28"
						style="width: 305px;">
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

