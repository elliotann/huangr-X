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
    $(function ()
    {
        /*listgrid =$("#maingrid").ligerGrid({
                    height:'99%',
                    columns: [
                        { display: 'id', name: 'roleid', align: 'center', width: 100, minWidth: 60 },
                        { display: '角色名称', name: 'rolename', minWidth: 120 },
                        { display: '描述', name: 'rolememo', minWidth: 140 }
                    ], url:'role.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
                    toolbar: { items: [
                        { text: '增加', click: addRole, icon: 'add' },
                        { line: true },
                        { text: '修改', click: modifyUser, icon: 'modify' },
                        { line: true },
                        { text: '删除', click: delUser, img: '${context }/js/ligerui/skins/icons/delete.gif' },
                        { line: true },
                        { text: '设置权限', click: setAuth, icon: 'modify' }
                    ]
                    }
                });*/


        $("#pageloading").hide();
    });

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

<grid:dataGrid action="role.do?dataGrid&ajax=yes" height="99%"  rownumbers="true" hasSearchBar="true">
    <grid:search label="角色名称:" name="rolename"/>
    <grid:column title="ID" field="roleid" align="center" width="100" minWidth="60"/>
    <grid:column title="角色名称" field="rolename"  minWidth="120"/>
    <grid:column title="描述" field="rolememo"  minWidth="140"/>
    <grid:toolbar title="增加" clickFun="addRole" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyUser" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delUser" icon="delete"/>
    <grid:toolbar title="设置权限" clickFun="setAuth" icon="modify"/>
</grid:dataGrid>