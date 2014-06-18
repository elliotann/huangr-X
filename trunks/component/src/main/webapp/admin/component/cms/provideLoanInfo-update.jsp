<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>借款信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="provideLoanInfoController.do?doUpdate" tiptype="1">
					<input id="sid" name="sid" type="hidden" value="${provideLoanInfoPage.sid }">
					<input id="itemName" name="itemName" type="hidden" value="${provideLoanInfoPage.itemName }">
					<input id="createDate" name="createDate" type="hidden" value="${provideLoanInfoPage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								主键:
							</label>
						</td>
						<td class="value">
						      		<input id="sid" name="sid" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${provideLoanInfoPage.sid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;"></label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								名称:
							</label>
						</td>
						<td class="value">
						      		<input id="itemName" name="itemName" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${provideLoanInfoPage.itemName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;"></label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建日期:
							</label>
						</td>
						<td class="value">
						      		<input id="createDate" name="createDate" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${provideLoanInfoPage.createDate}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;"></label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/easysoft/component/cms/provideLoanInfo.js"></script>		