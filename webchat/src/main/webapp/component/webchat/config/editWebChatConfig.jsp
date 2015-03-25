<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/validate.css" rel="stylesheet" type="text/css" />
  <div class="easyui-panel" style="border-style:none;" >
	<div style="padding: 10px 0 10px 60px">
		<form id="editConfigForm" method="post" class="validate" >
			<input type="hidden" name="id" value="${webChatConfig.id }"/>
			<table>
			
				<tr>
					<th><label class="text">公众号名称：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="title" data-options="required:true" value="${webChatConfig.title }"/></td>
				</tr>
				<tr>
					<th><label class="text">微信号：</label></th>
					<td><input class="easyui-validatebox input_text" type="text" name="weixinNo" data-options="required:true" ${webChatConfig.weixinNo }/></td>
				</tr>
			
				<tr id="roletr">
					<th><label class="text">公众号类型：</label></th>
					<td>
						<select class="easyui-combobox "name="chatType" style="width:100px;" name="chatType">
							<option value="SubscriptionNo" <c:if test="${webChatConfig.chatType == 'SubscriptionNo'}">selected</c:if> >订阅号</option>
							<option value="ServiceNo" <c:if test="${webChatConfig.chatType == 'ServiceNo'}">selected</c:if>>服务号</option>
							<option value="CompanyNo" <c:if test="${webChatConfig.chatType == 'CompanyNo'}">selected</c:if>>企业号</option>
						</select>
					</td>		
				</tr>
				
		
				<tr>
					<th><label class="text">公众账号APPID：</label></th>
					<td><input type="text" name="appId"  class="input_text easyui-validatebox" data-options="required:true"  value="${webChatConfig.appId }"/></td>
				</tr>
				<tr>
					<th><label class="text">公众账号appsecret：</label></th>
					<td><input type="text" name="appsecret" class="input_text"  value="${webChatConfig.appsecret }"/></td>
				</tr>
			
			</table>
		</form>
	</div>
</div>