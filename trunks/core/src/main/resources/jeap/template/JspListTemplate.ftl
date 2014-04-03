<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="${entityName?uncap_first}List" checkbox="true" fitColumns="false" title="${ftl_description}" actionUrl="${entityName?uncap_first}Controller.do?datagrid" idField="id" fit="true" queryMode="group">
  <#list columns as po>
   <t:dgCol title="${po.display}"  field="${po.fieldName}" <#if po.type?index_of("datetime")!=-1>formatter="yyyy-MM-dd hh:mm:ss"<#else><#if po.type?index_of("date")!=-1>formatter="yyyy-MM-dd"</#if></#if> <#if po.isShowList?if_exists?html =='N'>hidden="false"<#else>hidden="true"</#if> <#if po.insearch>query="true"</#if>  width="${po.listwidth}"></t:dgCol>
  </#list>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="${entityName?uncap_first}Controller.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="${entityName?uncap_first}Controller.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="${entityName?uncap_first}Controller.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="${entityName?uncap_first}Controller.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="${entityName?uncap_first}Controller.do?goUpdate" funname="detail"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/${bussiPackage?replace('.','/')}/${entityPackage}/${entityName?uncap_first}List.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 		<#list columns as po>
 		<#if (po.type?index_of("datetime")!=-1 || po.type?index_of("date")!=-1) && po.queryMode=="single">
 			$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 		<#else>
 		<#if (po.type?index_of("datetime")!=-1 || po.type?index_of("date")!=-1) && po.queryMode=="group">
 			$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#${entityName?uncap_first}Listtb").find("input[name='${po.fieldName}_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 		</#if>
 		</#if>
		</#list>
 });

 </script>