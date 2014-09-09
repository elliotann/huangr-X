<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>


    <link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>

</head>

<body style="padding: 4px">
<div class="main">
    <input type="hidden" id="roleId" value="${roleId}"/>
    <form action="" id="catform">
        <table class="easyui-treegrid" id="authDataGrid"
               data-options="url:'auth.do?dataGrid&ajax=yes&roleId=${roleId}',fitColumns:'true',idField: 'id',treeField: 'title',singleSelect:false,onClickRow:onCheck">
            <thead>
            <tr>
                <th data-options="field:'checked',width:50,checkbox:'true'">ID</th>
                <th data-options="field:'id',width:100">ID</th>
                <th data-options="field:'title',width:100">名称</th>
                <th data-options="field:'add',width:100,align:'center'" formatter="formatAuth">操作</th>


            </tr>
            </thead>
        </table>
    </form>

    <div id="divdia" style="display: none;"></div>
</div>

<script type="text/javascript">

    function formatAuth(value, row, index) {
        var  html="";
        if(row.hasChildren){
            return html;
        }
        $.ajax({
            url:'auth.do?getBtnByMenuId&ajax=yes&id='+row.id+'&roleId='+${roleId},
            type:'post',
            dataType:'json',
            async:false,
            success:function(result){
                html = result;
            }

        });
        html += "<input type='hidden' id='menu"+row.id+"' value='"+row.__id+"'/>";
        return html;
    }
    function onCheck(row){
        $("#authDataGrid").treegrid('cascadeCheck',{
            id:row.id, //节点ID
            deepCascade:true //深度级联
        });

    }
</script>
<script type="text/javascript" src="${context}/js/easyui/extend/treegrid.plugin.js"></script>
</body>
</html>

