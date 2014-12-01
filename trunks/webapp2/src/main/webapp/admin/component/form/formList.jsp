<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>


<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx }/admin/js/crud.js" type="text/javascript"></script>
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
    function addForm(item)
    {
    	window.location.href="designer.do?toDesigner";

    }

    function btnOK(item,dialog){
        openDia.subForm();

    }
    function modifyForm(item)
    {

    	if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
   	 	var row = $('#dataGrid').datagrid('getSelections')[0];
   	 	window.location.href='designer.do?modify&id='+row.id;
        

    }
    function delUser(item)
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
                    url: "designer.do?delete",
                    data:"ajax=true&id="+row.id,
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


    function generatorCode(item){
    	if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
   	 	var row = $('#dataGrid').datagrid('getSelections')[0];
   	 	addOrUpdateDialog('生成代码','${ctx}//core/admin/code.do?toGenerate&formId='+row.id,600,900);
       
    }
    //同步数据库
    function synDB(item){
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据!');
            return;
        }
        $.ajax({
            type: "post",
            url: '${ctx}//core/admin/code.do?synDb',
            data:"ajax=true&formId="+row.id,
            dataType:"json",
            success: function(result){
                if(result.success){
                    $.ligerDialog.waitting('操作成功...');
                    setTimeout(function ()
                    {
                        $.ligerDialog.closeWaitting();
                        listgrid.loadData();
                        dialog.close();
                    }, 1000);

                }else{
                    $.ligerDialog.alert(result.msg, '提示', type);

                }
            },error:function(e){
                $.ligerDialog.alert('出错了!', '提示', type)

            }
        });

    }

    function isSynDB(rowdata,index,value){
        if(value==1){
            return "已同步";
        }else{
            return "未同步";
        }
    }
    
    function pageSettting(){
    	 if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
             alert("必须选择一条数据进行修改!");
             return;
         }
    	 var row = $('#dataGrid').datagrid('getSelections')[0];
    	window.location.href="designer.do?pageSetting&formId="+row.id;
    }
</script>

<grid:dataGrid action="designer.do?dataGrid&ajax=yes" height="99%">
    <grid:column title="id" field="id" align="center" width="100" minWidth="60"/>
    <grid:column title="表名" field="tableName"  minWidth="120"/>
    <grid:column title="表单名称" field="formName"  minWidth="140"/>
    <grid:column title="版本" field="version"  minWidth="100"/>
    <grid:column title="同步数据库" field="isSynDB"  minWidth="100" renderFun="isSynDB"/>
    <grid:column title="创建人" field="createBy"  minWidth="100"/>
    <grid:column title="创建时间" field="createTime"  minWidth="140"/>
    <grid:toolbar title="增加" clickFun="addForm" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyForm" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delUser" icon="del"/>
    <grid:toolbar title="同步数据库" clickFun="synDB" icon="modify"/>
    <grid:toolbar title="生成代码" clickFun="generatorCode" icon="modify"/>
    <grid:toolbar title="页面设计" clickFun="pageSettting" icon="modify"/>
</grid:dataGrid>