<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<script type="text/javascript" src="../js/PasswordOperator.js"></script>
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/easyui.css">
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/demo.css">
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/icon.css">
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.10.js"></script>
<script type="text/javascript" src="/esf/adminthemes/default/js/jquery.easyui.min.js"></script>
<div class="grid">
    <div class="toolbar">
        <ul>
            <li><a href="javascript:void(0)" onclick="addDialog()">添加</a></li>
        </ul>
    </div>
    <grid:grid from="webpage">
        <grid:header>
            <grid:cell width="110px">
                <input type="checkbox" id="toggleChk" />
            </grid:cell>
            <grid:cell width="110px">表描述</grid:cell>
            <grid:cell width="200px">表名</grid:cell>
            <grid:cell width="180px">操作</grid:cell>
        </grid:header>
        <grid:body item="table">
            <grid:cell>
                <input type="checkbox" name="id" value="${table.tableComment}" />
            </grid:cell>
            <grid:cell>&nbsp;${table.tableComment}
            </grid:cell>
            <grid:cell>&nbsp;${table.tableName} </grid:cell>
            <grid:cell>&nbsp;

                <a href="userAdmin!edit.do?id=${table.tableComment}"><img class="modify"  src="../images/transparent.gif"></a>
                &nbsp;<a  href="userAdmin!delete.do?id=${userAdmin.userid }" onclick="javascript:return confirm('确认删除此表吗？')"><img class="delete" src="../images/transparent.gif"></a>

            </grid:cell>
        </grid:body>
    </grid:grid></div>



<DIV style="clear:both;margin-top:260px;"></DIV>
</div>


<div id="dlg" class="easyui-dialog" title="Table编辑" style="width:800px;height:500px;padding:5px"
     data-options="
				iconCls: 'icon-save',
				toolbar: '#dlg-toolbar',
				buttons: '#dlg-buttons'
			">

</div>
<div id="dlg-toolbar" style="padding:2px 0">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td style="padding-left:40px">
                表名:
            </td>
            <td style="padding-left:2px">
                <input  name="tableName" type="text" style="width:150px" id="tableName"></input>
            </td>
            <td style="text-align:left;padding-right:2px">
                注释:
            </td>
            <td style="text-align:left;padding-right:2px">
                <input name="tableComment" type="text" style="width:150px" id="tableComment"></input>
            </td>
        </tr>
    </table>
    <div style="margin:10px 0;"></div>
    <div class="easyui-tabs" style="width:750px;height:320px;padding:15px">
        <div title="列信息" style="padding:0px">
            <table id="dg" class="easyui-datagrid" style="width:750px;height:auto"
                   data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				url: 'datagrid_data1.json',
				method:'get',
				onClickCell: onClickCell">
                <thead>
                <tr>
                    <th data-options="field:'columnName',width:100,editor:'text'">列名</th>
                    <th data-options="field:'dataType',width:100,editor:'text'">数据类型</th>
                    <th data-options="field:'listprice',width:100,align:'right',editor:{type:'checkbox',options:{on:'1',off:''}}">不允许为空</th>
                    <th data-options="field:'unitcost',width:100,align:'right',editor:'numberbox'">缺省值</th>
                    <th data-options="field:'attr1',width:250,editor:'text'">注释</th>
                    <th data-options="field:'status',width:90,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">自动增长</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="表选项" style="padding:10px">
            <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
        </div>
        <div title="高级选项" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
            This is the help content.
        </div>
    </div>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $('#dlg').dialog('close')
    });

    function addDialog(){
        $('#dlg').dialog({vcenter:true})
    }
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell(index, field){
        if (endEditing()){
            $('#dg').datagrid('selectRow', index)
                    .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    }

    function save(){
        var rows = $("#dg").datagrid('getData');
        $.ajax({
            type: "POST",
            url: "userSite!createTable.do?ajax=yes",
            dataType:"json",
            data: "tableName="+$("#tableName").val()+"&tableComment="+$("#tableComment").val()+"&data="+JSON.stringify(rows),
            success:function(result){

                if(result.result==1){
                    alert("初始化成功，请重新启动以便请除缓存！");
                }else{
                    alert(result.message);
                }
            },
            error:function(e){

                alert("error:"+e);
            }
        });

    }
</script>
