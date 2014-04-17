<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.10.js"></script>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />

<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script type="text/javascript">
    var listgrid;
    $(function() {

        listgrid =
                $("#maingrid").ligerGrid({
                    height:'99%',
                    width:'100%',
                    columns: [
                        { display: '定义Id', name: 'defId', align: 'center', width: 100, minWidth: 60 },
                        { display: '部署Id', name: 'deploymentId', minWidth: 60,width: 90 },
                        { display: '名称', name: 'defName', minWidth: 100 },
                        { display: 'KEY', name: 'defKey',width:50},
                        { display: '版本号', name: 'defVersion',width:30},
                        { display: 'XML', name: 'resourceName',render:function(rowdata,index,value){
                            return "<a target='_blank' href='${ctx }/core/admin/workflow.do?readResouce&processDefinitionId="+rowdata.defId+"&resourceType=xml'>"+value+"</a>";
                        }},
                        { display: '图片', name: 'diagramResourceName',render:function(rowdata,index,value){
                            return "<a target='_blank' href='${ctx }/core/admin/workflow.do?readResouce&ajax=true&processDefinitionId="+rowdata.defId+"&resourceType=image'>"+value+"</a>";
                        }},
                        { display: '部署时间', name: 'deploymentTime',type:'date'},
                        { display: '是否挂起', name: 'suspended',width:50}
                    ], url:'workflow.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
                    toolbar: { items: [
                        { text: '部署流程', click: deploy, icon: 'add' },
                        { line: true },
                        { text: '新增流程', click: addProcess, icon: 'add' },
                        { line: true },
                        { text: '删除', click: delDeploy, img: '${context }/js/ligerui/skins/icons/delete.gif' }
                    ]
                    }
                });


    });
    function deploy(item){
        $.ligerDialog.open({
            height:300,
            width: 600,
            title : '部署流程',
            url: 'workflow.do?toDeploy',
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            buttons:[ { text: '确定', onclick: btnOK }, { text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
        });
    }
    function addProcess(item){
        $.ligerDialog.open({
            name:'openDia',
            height:600,
            width: 900,
            title : '新增流程',
            url: 'workflow.do?toFlowDesigner',
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            buttons:[ { text: '确定', onclick: btnOK }, { text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
        });
    }

    function btnOK(item,dialog){
        openDia.submitForm();
    }
    function delDeploy(item)
    {
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }
        $.ligerDialog.confirm('确定删除？', function (yes) {
            if(yes){
                $.ajax({
                    type: "GET",
                    url: "workflow.do?delete&id="+row.deploymentId,
                    data:"ajax=true&rmd="+ new Date().getTime(),
                    dataType:"json",
                    success: function(result){
                        if(result.success){
                            $.ligerDialog.alert('删除成功!', '提示', type);
                            listgrid.loadData();
                        }else{
                            $.ligerDialog.alert(result.msg, '提示', type);

                        }
                    },error:function(e){
                        $.ligerDialog.alert('出错了!', '提示', type)

                    }
                });
            }
        });
    }
    </script>
<div class="grid">
    <div id="maingrid"></div>
</div>
<div style="display:none;">