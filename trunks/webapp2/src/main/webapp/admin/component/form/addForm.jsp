<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Row Editing in DataGrid - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${context}/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${context}/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${context}/easyui/demo/demo.css">
	<script type="text/javascript" src="${context}/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${context}/easyui/jquery.easyui.min.js"></script>
	<link href="${context }/css/form.css" rel="stylesheet" />
	<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
</head>
<body>
	<table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">表单编码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="code" type="text" id="code"   validate="{required:true,maxlength:30}" class="form-control"/>
            </td>
            <td align="right" class="l-table-edit-td"></td>
            <td align="left" class="l-table-edit-td">

            </td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">表名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tableName" type="text" id="tableName"   validate="{required:true,maxlength:30}" class="form-control"/>
            </td>
            <td align="right" class="l-table-edit-td">表单名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="formName" type="text" id="formName"  validate="{required:true,maxlength:60}" class="form-control"/>
            </td>
        </tr>
    </table>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="增加表单" style="width:auto;height:auto"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '/jeap1.0/admin/core/datagrid_data1.json',
				method: 'get',
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'fieldName',width:250,editor:'textbox'">字段名</th>
				<th data-options="field:'displayName',width:250,editor:'textbox'">显示名称</th>
				<th data-options="field:'productid',width:100,
						formatter:function(value,row){
							return row.productname;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'productid',
								textField:'productname',
								method:'get',
								url:'products.json',
								required:true
							}
						}">数据类型</th>
				<th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:2}}">长度</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'是',off:'否'}}">是否主键</th>
				<th data-options="field:'isNullable',width:60,align:'center',editor:{type:'checkbox',options:{on:'是',off:'否'}}">允许空值</th>
			
				
				
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加行</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除行</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	</div>
	<form id="objForm" name="objForm" action="designer.do?save&ajax=yes" method="post">
		<input type="hidden" name="data" value="" id="datas"/>
		<button onclick="saveSubmit()">保存</button>
	</form>
	<script type="text/javascript">
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
				var productname = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if(index==0) return;
			
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
					var editors = $('#dg').datagrid('getEditors', editIndex);
					$(editors[4].target).bind('click',function(){  
					      alert("here");
					});
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
				var editors = $('#dg').datagrid('getEditors', editIndex);
		
				$(editors[4].target).bind('click',function(){  
				      alert("here");
				});
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}
		
		function saveSubmit(){
			var tableName = $("#tableName").val();
			var formName = $("#formName").val();
			var rows = $('#dg').datagrid('getRows');
			 var jsonData = "{\"tableName\":\""+tableName+"\",\"formName\":\""+formName+"\",\"fields\":"+JSON.stringify(rows)+"}";
			
			$("#datas").val(jsonData);
			$("#objForm").submit();
			
		
		}
	</script>
</body>
</html>