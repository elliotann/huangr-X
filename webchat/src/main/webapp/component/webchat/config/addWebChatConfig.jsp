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
					<th><label class="text">公众号名称：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="title" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label class="text">微信号：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="weixinNo" data-options="required:true" /></td>
				</tr>
			
				<tr id="roletr">
					<th><label class="text">公众号类型：</label></th>
					<td>
						<select class="easyui-combobox" name="state" style="width:100px;" name="chatType">
							<option value="AL">订阅号</option>
							<option value="AK">服务号</option>
							<option value="AK">企业号</option>
						</select>

					</td>		
				</tr>
				
		
				<tr>
					<th><label class="text">公众账号APPID：</label></th>
					<td><input type="text" name="appId"  class="input_text easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th><label class="text">公众账号appsecret：</label></th>
					<td><input type="text" name="appsecret" class="input_text"  /></td>
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

