<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>





<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/jeap/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>

<link href="${context }/css/form.css" rel="stylesheet"/>

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
    var listgrid;
    function addUser()
    {
        addOrUpdateDialog('增加管理员','userAdmin.do?add',500,700);
    }
    function customSearch()
    {
        listgrid.options.data = $.extend(true,{}, CustomersData);
        listgrid.showFilter();
    }
    function modifyUser()
    {

        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];
        addOrUpdateDialog('修改管理员','userAdmin.do?edit&id='+row.id,500,700);

    }

    function delUser()
    {

        var rows = $('#dataGrid').datagrid("getSelections");
        if (rows.length < 1||rows.length>1) {
            alert("请选择要删除的会员");
            return;
        }
        if (!confirm("确认要将删除会员吗？")) {
            return;
        }
        var row = rows[0];
        delObj("userAdmin.do?delete&id="+row.id);

    }
    function getStatusName(rowdata,index,value){
        if(value==1){
            return "启用";
        } else{
            return "禁用";
        }
    }
    function searchMember(){

        var username = $("#username").val();
        $("#dataGrid").datagrid('load', {
            username:username,
            page:1
        });
    }


</script>
<style>


    /* 搜索框 */
    .searchtitle{ padding-left:28px; position:relative;}
    .searchtitle img{ width:22px; height:22px; position:absolute; left:0; top:0;}
    .searchtitle span{ font-size:14px; font-weight:bold;}
    .searchtitle .togglebtn{ position:absolute; top:6px; right:0px; background:url(/jeap/admin/images/icons/toggle.gif) no-repeat 0px 0px; height:10px; width:9px; cursor:pointer;}
    .searchtitle .togglebtn-down{ background-position:0px -10px;}

    /* 一条线 导航线 */
    .navline{
        height:1px;
        line-height:1px;
        width:100%;
        border-bottom:1px solid #f5f5f5;

        background-color:#D9D9D9;width:100%; }
    #searchbar{
        margin-bottom: 10px;

    }
</style>



<grid:dataGrid action="userAdmin.do?dataGrid&ajax=yes" height="100%"  rownumbers="true" hasSearchBar="true" style="easyui">
    <grid:search label="用户名" name="username" shortSearch="true"/>
    <grid:column title="ID" field="id" align="center" width="100" minWidth="60"/>
    <grid:column title="用户名" field="username"  minWidth="100"/>
    <grid:column title="姓名" field="realname"  minWidth="140"/>
    <grid:column title="状态" field="state" renderFun="getStatusName"/>
    <grid:toolbar title="增加" clickFun="addUser" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyUser" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delUser" icon="delete"/>

</grid:dataGrid>