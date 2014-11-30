<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script src="${staticserver}/js/common/jquery.validate.js"
	type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet" />
<link href="${context }/js/easyui/themes/icon.css" rel="stylesheet" />
<script type="text/javascript">

    var groupicon = "${context }/js/ligerui/skins/icons/communication.gif";
    var dialog = frameElement.dialog;
    var tab = null;


    $(function ()
    {

        var v = $("form").validate({
            debug: true,
            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.addClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().addClass("l-text-invalid");
                }
                $(element).removeAttr("title").ligerHideTip();
                $(element).attr("title", lable.html()).ligerTip();
            },
            success: function (lable)
            {
                var element = $("#" + lable.attr("for"));
                if (element.hasClass("l-textarea"))
                {
                    element.removeClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().removeClass("l-text-invalid");
                }
                $(element).removeAttr("title").ligerHideTip();
            },
            submitHandler: function ()
            {
                $("form .l-text,.l-textarea").ligerHideTip();
                if(outjson()){
                    window.parent.listgrid.loadData();
                    dialog.close();//关闭dialog
                }
            }
        });

    });

    function subForm(){
        $("#form1").submit();
    }
    function outjson()
    {

        var manager = $("#dbColumnGrid").ligerGetGridManager();
        var data = manager.getData();

        var jsonData = JSON.stringify(data);
        var tableName = document.getElementById("tableName").value; //调用父页面的元素
        var tableTitle = document.getElementById("tableTitle").value; //调用父页面的元素
        var formId =0;
        if(parent.document.getElementById("formId")!=null) {
            formId = parent.document.getElementById("formId").value
        }

        var jsonData = "{\"tableName\":\""+tableName+"\",\"tableTitle\":\""+tableTitle+"\",\"fields\":"+jsonData+"}";
        $.ajax({
            type: "post",
            url: "designer.do?save",
            data:"ajax=yes&rmd="+ new Date().getTime()+"&data="+jsonData+"&formId="+formId,
            dataType:"json",
            async:false,
            success: function(html){
                return true;
            },error:function(e){
                alert(e);
                return false;
            }
        });

        return true;
    }
    var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#fromDataGrid').datagrid('validateRow', editIndex)){
			var ed = $('#fromDataGrid').datagrid('getEditor', {index:editIndex,field:'productid'});
			var productname = $(ed.target).combobox('getText');
			$('#fromDataGrid').datagrid('getRows')[editIndex]['productname'] = productname;
			$('#fromDataGrid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
    function append(){
    	
		if (endEditing()){
			alert("here");
			$('#fromDataGrid').datagrid('appendRow',{status:'P'});
			editIndex = $('#fromDataGrid').datagrid('getRows').length-1;
			$('#fromDataGrid').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<body>
<form name="form1" method="post"   id="form1">
    <div>
    </div>
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
                <input name="tableTitle" type="text" id="tableTitle"  validate="{required:true,maxlength:60}" class="form-control"/>
            </td>
        </tr>
    </table>
    <br />
    <div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:600px;height:400px">
		
		<div title="数据库字段" data-options="closable:true" style="padding:10px">
			<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
			</div>
			<table id="fromDataGrid" class="easyui-datagrid" data-options="fit:true,singleSelect:true,rownumbers:true" style="width:600px;height:400px">
				<thead>
					<tr>
						<th data-options="field:'f1',width:100,editor:'textbox'">字段名</th>
						<th data-options="field:'f2',width:100">显示名</th>
						<th data-options="field:'f3',width:100,editor:{type:'checkbox',options:{on:'P',off:''}}">是否主键</th>
						<th data-options="field:'f3',width:100">允许空值</th>
						<th data-options="field:'f3',width:100">数据类型</th>
						<th data-options="field:'f3',width:100,editor:{type:'numberbox',options:{precision:1}}">长度</th>
						<th data-options="field:'f3',width:100">小数点</th>
					</tr>
				</thead>
		
			</table>
			
		</div>
	</div>
</form>
</body>

