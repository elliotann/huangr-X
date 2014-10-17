<%--
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
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/jeap/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<link href="${context }/css/button.css" rel="stylesheet"/>
<script type="text/javascript">
    $(function () {
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
    });
    var listgrid;
    function addUser()
    {
        addOrUpdateDialog('增加管理员','userAdmin.do?add',500,700);
    }
    function customSearch()
    {
        listgrid.options.data = $.extend(true,{}, CustomersData);
        listgrid.showFilter();
    }
    function modifyUser()
    {

        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];
        addOrUpdateDialog('修改管理员','userAdmin.do?edit&id='+row.id,500,700);

    }

    function delUser()
    {

        var rows = $('#dataGrid').datagrid("getSelections");
        if (rows.length < 1||rows.length>1) {
            alert("请选择要删除的会员");
            return;
        }
        if (!confirm("确认要将删除会员吗？")) {
            return;
        }
        var row = rows[0];
        delObj("userAdmin.do?delete&id="+row.id);

    }
    function getStatusName(value, row, index) {
        if(value==1){
            return "启用";
        } else{
            return "禁用";
        }
    }

    function searchMember(){

        var username = $("#username").val();
        $("#dataGrid").datagrid('load', {
            username:username,
            page:1
        });
    }


</script>
<grid:dataGrid action="buildQuery.do?dataGrid&ajax=yes" height="100%"  rownumbers="true" hasSearchBar="true" style="easyui">
    <grid:search label="构建包名称" name="buildFileName" shortSearch="true"/>
    <grid:search label="构建包名称" name="buildFileName"/>
    <grid:search label="状 态" name="status"/>
    <grid:search label="提交人员" name="adder"/>
    <grid:search label="开始测试人员" name="testering"/>
    <grid:search label="构建时间" name="testering"/>


    <grid:column title="构建包名称" field="buildFileName" align="center" width="100" minWidth="60"/>
    <grid:column title="提交人员" field="adder" align="center" width="100" minWidth="60"/>
    <grid:column title="构建时间" field="addTime"  minWidth="100"/>
    <grid:column title="测试员" field="testering"  minWidth="140"/>
    <grid:column title="SQL" field="hasSql"/>
    <grid:column title="测试通过 " field="passTester" />
    <grid:column title="依赖" field="buildDepends"/>
    <grid:column title="状态" field="status" renderFun="getStatusName"/>
    <grid:column title="所属补丁包" field="ssbdb" />
    <grid:toolbar title="导出" clickFun="addUser" icon="add"/>

</grid:dataGrid>

