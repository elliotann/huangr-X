<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.io.File, java.lang.*,
com.byttersoft.patchbuild.*,
com.byttersoft.patchbuild.beans.*, 
com.byttersoft.patchbuild.permission.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分支列表维护</title>
<style>
	html,body{ width:950px;}
	div.fn-queryModule, div.fn-toolsModule{ width: 950px;}
	div.fn-listModule{ width: 952px;}	
	.fwb_datagrid-body .fwb_datagrid-cell a{text-decoration:none;}
</style>
</head>
<body>	

	<!--查询模块标签-->
	<div class="fn-queryModule">
		<form>			
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="list">

				<tr>
					<td>构建包名称</td>
					<td><input name="searchBean.buildFileName" /></td>
					<td>状  态</td>
					<td>
						<select name="searchBean.status" style="width: 130px;">//0 已构建  1 开始测试  2 取消测试 3 测试通过  4 取消构建  5 已发布 
							<option value="">全部</option>
							<option value="0">已构建</option>
							<option value="1">开始测试</option>	
							<option value="2">取消测试</option>
							<option value="3">测试通过 </option>
							<option value="4">取消构建 </option>	
							<option value="5">已发布 </option>	
							<option value="6">非取消构建 </option>							
						</select>
					</td>
				</tr>
				
				<tr>
					<td>提交人员</td>
					<td><input name="searchBean.adder" /></td>	
					<td>开始测试人员</td>
					<td><input name="searchBean.startTester" /></td>									
				</tr>
				
				<tr>
					<td>构建时间</td>
					<td colspan="3"><input name="searchBean.addTimeStart" class="fn-date"/> 至 <input name="searchBean.addTimeEnd" class="fn-date"/></td>													
				</tr>
				
			</table>
		</form>
	</div>
	
	<!--按钮模块-->
	<div class="fn-toolsModule">
		<!--按钮组，可自定义-->
		<button type="button" class="button" onclick="$queryModule.query();">查 询</button>&nbsp;&nbsp;	
		<button type="button" class="button" onclick="exportBuildConfig();">导 出</button>		
	</div>
	<!--弹出窗口 - 查看明细-->
	<div class="fn-viewModule" title="构建包信息及历史变更"
		url="${contextPath}/manage/buildConfigInfo.do?method=toBuildConfigInfoView" 
		iframe="true"></div>
	<!--列表模块-->
	<div class="fn-listModule">
		<table class="fn-datagrid" id="fh1"
			url="${contextPath}/manage/buildConfigInfo.do?method=doListBuildConfigInfo" 
			pagination="true"
			rownumbers="true" 
			rowStyler="rowStyler"
			>
			<thead>
				<tr>				
					<th field="buildFileName" width="120" height="30"  align="center">构建包名称</th>		
					<th field="adder" width="60" height="30"  align="center">提交人员</th>	
					<th field="addTime" width="110" height="30"  align="center" formatter="function(value){ return fn.date.format(value, 'yyyy-mm-dd h:m:s'); }">构建时间</th>				
					<th field="testering" width="50" height="30"  align="center" formatter="function(value,rowData){ 						
						if(rowData.status=='0'){
							return '';
						}else if(rowData.status=='1'){
							return rowData.startTester;
						}else if(rowData.status=='2'){
							return rowData.cancelTester;
						}else if(rowData.status=='3'){
							return rowData.passTester;
						}else if(rowData.status=='4'){
							return '';
						}else if(rowData.status=='5'){
							return '';
						}
						
					}">测试员</th>	
					<th field="hasSql" width="80" height="30"  align="center" formatter="function(value){ return value=='0'?'无':'有' }">SQL</th>	
					<th field="passTester" width="70" height="30"  align="center" formatter="function(value){ return value==''?'':'是' }">测试通过</th>
					<th field="buildDepends" width="250" height="30"  align="left">依赖</th>
					<th field="status" width="70" height="30"  align="center" formatter="function(value){ 						
						if(value=='0'){
							return '已构建';
						}else if(value=='1'){
							return '开始测试';
						}else if(value=='2'){
							return '取消测试 ';
						}else if(value=='3'){
							return '测试通过';
						}else if(value=='4'){
							return '取消构建';
						}else if(value=='5'){
							return '已发布';
						}						
					}">状态</th>		
					<th field="ssbdb" width="120" height="30"  align="center" formatter="function(value,rowData){ return rowData.bd.deployPackName}">所属补丁包</th>	
					<th field="view" width="70" align="center" formatter="formatter2">操  作</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<script type="text/javascript"	src="${contextPath}/js/fn/1.5/fn.js?use=widget"></script>
	<script type="text/javascript">
		function formatter2(i, n) {
			 var urlStr = "<a href='javascript: $viewModule.open(\"id=" + n.id + "\");'>查看</a>";
			 return urlStr;
				 
		};		
		
		function rowStyler(){
			return 'line-height:30px';
		}
		
		function exportBuildConfig(){		
			var contextPath = '${contextPath}';			
			jQuery("form").first().attr("method","post").attr("action",contextPath+"/manage/buildConfigInfo.do?method=doExportBuildConfigInfo").submit();			
		}
	
	</script>
</body>
</html>
