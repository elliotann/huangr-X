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
<style>
    .message {
        width: 99%;
        height: 100px;
        overflow:auto;
    }
    .l-dialog-win .l-dialog-content {
        overflow: hidden;
    }
</style>
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


    function getParent()
    {
        var row = listgrid.getParent(listgrid.getSelectedRow());
        alert(JSON.stringify(row));
    }
    function getSelected()
    {
        var row = listgrid.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
        alert(JSON.stringify(row));
    }
    function getData()
    {
        var data = listgrid.getData();
        alert(JSON.stringify(data));
    }
    function hasChildren()
    {
        var row = listgrid.getSelectedRowObj();
        alert(listgrid.hasChildren(row));
    }
    function isLeaf()
    {
        var row = listgrid.getSelectedRowObj();
        alert(listgrid.isLeaf(row));
    }
    function delMenu(item){
        if($('#dataGrid').treegrid('getSelections').length<1||$('#dataGrid').treegrid('getSelections').length>1){
            alert("请选择数据删除!");
            return;
        }
        var row = $('#dataGrid').treegrid('getSelections')[0];
        if(!confirm("确定删除?")){
            return;
        }

        delObj4Tree("menu.do?delete&id="+row.id);
    }



    function addMenu(){
        addOrUpdateDialog('增加菜单','menu.do?add',500,700);
    }

    function addBtn(item){

        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据操作!');
            return;
        }
        addOrUpdateDialog(item,'增加按钮','oper.do?add&menuId='+row.id,400,400);

    }

    function updateMenu(){
        if($('#dataGrid').treegrid('getSelections').length<1||$('#dataGrid').treegrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').treegrid('getSelections')[0];
        addOrUpdateDialog('修改菜单','menu.do?edit&id='+row.id,500,700);
    }
    function getMenuType(rowdata,index,value){
        if(value==1){
            return "系统菜单";
        }else{
            return "应用菜单";
        }
    }
    function showIco(rowdata,index,value){
       return "<img src='${context}/images/system/ico/default_menu.png'/>";
    }

</script>

<grid:dataGrid action="menu.do" height="99%" usePager="false"  width="100%" tree="true" style="easyui">
    <grid:column title="名称" field="title" align="left" width="100" id="title"/>
    <grid:column title="url" field="url"  width="100" align="left" id="url"/>
    <grid:column title="类型" field="menutype"  width="100" align="center" sortType="int" renderFun="getMenuType" id="menutype"/>
    <grid:column title="target" field="target" align="left"  width="50" id="target"/>
    <grid:column title="排序" field="sorder" align="center"  width="100"/>
    <grid:column title="图标" field="ico" align="center"  width="400" renderFun="showIco"/>
    <grid:toolbar title="增加" clickFun="addMenu" icon="add"/>
    <grid:toolbar title="修改" clickFun="updateMenu" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delMenu" icon="delete"/>
    <grid:toolbar title="增加按钮" clickFun="addBtn" icon="add"/>
</grid:dataGrid>


