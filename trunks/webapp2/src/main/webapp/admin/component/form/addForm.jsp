<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>增加表单</title>
	<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
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
	<div id="formTabs" style="width:100%;height:250px" >
		<div title="数据库字段" style="padding:0px">
			<table id="dg" class="easyui-datagrid"
				style="width: auto; height: auto"
				data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb1',
				url: 'designer.do?getColumns&ajax=true&id=0',
				method: 'get',
				onClickRow: onClickRow
			">
				<thead>
					<tr>
						<th data-options="field:'fieldName',width:250,editor:'textbox'">字段名</th>
						<th data-options="field:'displayName',width:250,editor:'textbox'">显示名称</th>
						<th
							data-options="field:'dataType',width:100,
						formatter:function(value,row){
							return row.dataType;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'dataType',
								textField:'dataType',
								method:'get',
								url:'products.json',
								required:true
							}
						}">数据类型</th>
						<th
							data-options="field:'dataTypeLength',width:80,align:'right',editor:{type:'numberbox',options:{precision:2}}">长度</th>
						<th
							data-options="field:'isPK',width:60,align:'center',editor:{type:'checkbox',options:{on:'是',off:'否'}}">是否主键</th>
						<th
							data-options="field:'isNullable',width:60,align:'center',editor:{type:'checkbox',options:{on:'是',off:'否'}}">允许空值</th>



					</tr>
				</thead>
			</table>
		</div>
		<div title="列表页" style="padding:0px">
			<table id="listDg"
				style="width: auto; height: auto">
				
			</table>
		</div>
		<div title="表单" data-options="" style="padding:0px">
			<table id="formDg" 
				style="width: auto; height: auto"
				>
				
			</table>
		</div>
	</div>
	

	<div id="tb1" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加行</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除行</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">上移</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">下移</a>
	</div>
	<form id="objForm" name="objForm" action="designer.do?save&ajax=yes" method="post">
		<input type="hidden" name="data" value="" id="datas"/>
		<button onclick="saveSubmit()">保存</button>
	</form>
	<script type="text/javascript">
		var dgdatas = {
				tableName:'',
				formName:'',
				fields:null,
				pageMetas:null,
				addFormPageMetas:null
		}

		var editIndex = undefined;
		var editIndex1 = undefined;
		var editIndex2 = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'dataType'});
				var productname = $(ed.target).combobox('getText');
				$('#dg').datagrid('getRows')[editIndex]['dataType'] = productname;
				$('#dg').datagrid('endEdit', editIndex);
				dgdatas.fields = $('#dg').datagrid('getData').rows;
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function endEditing1(){
			
			if (editIndex1 == undefined){return true}
			if ($('#listDg').datagrid('validateRow', editIndex1)){
				var ed = $('#listDg').datagrid('getEditor', {index:editIndex1,field:'dataType'});
			
				$('#listDg').datagrid('endEdit', editIndex1);
				dgdatas.fields = $('#listDg').datagrid('getData').rows;
				editIndex1 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function endEditing2(){
			
			if (editIndex2 == undefined){return true}
			if ($('#formDg').datagrid('validateRow', editIndex2)){
				var ed = $('#formDg').datagrid('getEditor', {index:editIndex2,field:'dataType'});
			
				$('#formDg').datagrid('endEdit', editIndex2);
				dgdatas.fields = $('#formDg').datagrid('getData').rows;
				editIndex2 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					if(index==0) return;
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
					var editors = $('#dg').datagrid('getEditors', editIndex);
					$(editors[4].target).bind('click',function(){  
						$(editors[5].target).checked=true;
					});
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		
		function onClickRowList(index){
			
			if (editIndex1 != index){
				if (endEditing1()){
					$('#listDg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex1 = index;
				} else {
					$('#listDg').datagrid('selectRow', editIndex);
				}
			}
		}
		
		function onClickRowForm(index){
			
			if (editIndex2 != index){
				if (endEditing2()){
					$('#formDg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex2 = index;
				} else {
					$('#formDg').datagrid('selectRow', editIndex);
				}
			}
		}
		
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
				var editors = $('#dg').datagrid('getEditors', editIndex);
		
				$(editors[4].target).bind('click',function(){  
					$(editors[5].target).checked=true;
				});
				
				editIndex1 = $('#listDg').datagrid('getRows').length-1;
				editIndex2 = $('#formDg').datagrid('getRows').length-1;
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
				$('#listDg').datagrid('acceptChanges');
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
			var myData = $('#dg').datagrid('getData').rows;
			dgdatas.fields = myData;
			var tableName = $("#tableName").val();
			var formName = $("#formName").val();
			var rows = $('#dg').datagrid('getRows');
			dgdatas.tableName = tableName;
			dgdatas.formName = formName;
			$("#datas").val(JSON.stringify(dgdatas));
			$("#objForm").submit();
			
		
		}
	</script>
</body>
</html>