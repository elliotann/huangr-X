<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>






<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
<link href="${context }/css/form.css" rel="stylesheet" type="text/css" />
<link href="${context }/css/button.css" rel="stylesheet" type="text/css" />
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>

<script type="text/javascript">
    var listgrid;
    function addUser(item)
    {
        addOrUpdateDialog(item,'增加管理员','userAdmin.do?add',500,700);
    }
    function customSearch()
    {
        listgrid.options.data = $.extend(true,{}, CustomersData);
        listgrid.showFilter();
    }
    function modifyUser(item)
    {

        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        addOrUpdateDialog(item,'修改管理员','userAdmin.do?edit&id='+row.userid,500,700);

    }

    function delUser(item)
    {
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }

        delObj(item,"userAdmin.do?delete&id=",row.userid);
    }
    function getStatusName(rowdata,index,value){
        if(value==1){
            return "启用";
        } else{
            return "禁用";
        }
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

<div id="searchbar">
    <div style=" width:100%">
        <div class="searchtitle">
            <span>搜索</span><img src="/jeap/admin/images/icons/searchtool.gif" />
            <div class="togglebtn"></div>
        </div>
        <div class="navline" style="margin-bottom:10px; margin-top:4px;"></div>
        <div class="searchbox">
            <form>
                用户名:<input type="text" class="form-control" style="height: 13px">   <button class="btn btn-info" style="height: 25px">查询</button>
            </form>
            <div class="l-clear"></div>
        </div>

    </div>

</div>

<grid:dataGrid action="userAdmin.do?dataGrid&ajax=yes" height="99%"  rownumbers="true" hasSearchBar="false">
    <grid:column title="ID" field="userid" align="center" width="100" minWidth="60"/>
    <grid:column title="用户名" field="username"  minWidth="100"/>
    <grid:column title="姓名" field="realname"  minWidth="140"/>
    <grid:column title="状态" field="state" renderFun="getStatusName"/>
    <grid:toolbar title="增加" clickFun="addUser" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyUser" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delUser" icon="delete"/>
    <grid:toolbar title="自定义搜索" clickFun="customSearch" icon="delete"/>
</grid:dataGrid>

<div style="display:none;">

</div>