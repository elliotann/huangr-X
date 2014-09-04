<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>






<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>

<script type="text/javascript">
    var listgrid;
    function addRole(item)
    {
        addOrUpdateDialog(item,'增加角色','role.do?add',400,600);
    }
    function modifyUser(item)
    {

        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        addOrUpdateDialog(item,'修改角色','role.do?edit&roleid='+row.roleid,400,600);


    }
    function delUser(item)
    {
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }
        delObj(item,'role.do?delete&id=',row.roleid);

    }

    function setAuth(item){
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        addOrUpdateDialog(null,'权限点','auth.do?add&ajax=yes&roleId='+row.roleid,500,700);
    }
    function query(){

        listgrid.loadServerData("username="+$("#usernameQry").val());
        return false;
    }

</script>


<grid:dataGrid action="role.do?dataGrid&ajax=yes" height="99%"  rownumbers="true" hasSearchBar="true" style="easyui">
    <grid:search label="角色名称:" name="rolename"/>
    <grid:column title="ID" field="roleid" align="center" width="100" minWidth="60"/>
    <grid:column title="角色名称" field="rolename"  minWidth="120"/>
    <grid:column title="描述" field="rolememo"  minWidth="140"/>
    <grid:toolbar title="增加" clickFun="addRole" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyUser" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delUser" icon="delete"/>
    <grid:toolbar title="设置权限" clickFun="setAuth" icon="modify"/>
</grid:dataGrid>