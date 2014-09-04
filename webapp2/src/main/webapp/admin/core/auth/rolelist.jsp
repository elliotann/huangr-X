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

    function modifyUser()
    {
        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];

        $("#dialogInfo").show();
        $('#dialogInfo').dialog({
            title: '修改角色',
            top: 60,
            width: 600,
            height: 350,
            closed: false,
            cache: false,
            href:'role.do?edit&roleid='+row.roleid,
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var savebtn = $(this);
                        var disabled = savebtn.hasClass("l-btn-disabled");
                        if (!disabled) {
                            addForm(savebtn);
                        }
                    }
                },
                {text: '取消', handler: function () {
                    $('#dialogInfo').dialog('close');
                }}
            ]});

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
        var options = {
            url : "role.do?delete&id="+row.roleid,
            type : "POST",
            dataType : 'json',
            data:"ajax=true&rmd="+ new Date().getTime(),
            success : function(result) {
                if(result.success){
                    alert("删除成功");
                    $('#useradmindata').datagrid('reload');
                }

            },
            error : function(e) {
                $.Loading.error("出现错误 ，请重试");
            }
        };
        $('#dataGridform').ajaxSubmit(options);



    }

    function setAuth(){
        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];

        $("#dialogInfo").show();
        $('#dialogInfo').dialog({
            title: '设置权限',
            top: 60,
            width: 600,
            height: 350,
            closed: false,
            cache: false,
            href:'auth.do?add&ajax=yes&roleId='+row.roleid,
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var savebtn = $(this);
                        var disabled = savebtn.hasClass("l-btn-disabled");
                        if (!disabled) {
                            addForm(savebtn);
                        }
                    }
                },
                {text: '取消', handler: function () {
                    $('#dialogInfo').dialog('close');
                }}
            ]});

    }
    function query(){

        listgrid.loadServerData("username="+$("#usernameQry").val());
        return false;
    }
    function addRole() {

        $("#dialogInfo").show();
        $('#dialogInfo').dialog({
            title: '增加角色',
            top: 60,
            width: 600,
            height: 350,
            closed: false,
            cache: false,
            href: 'role.do?add',
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var savebtn = $(this);
                        var disabled = savebtn.hasClass("l-btn-disabled");
                        if (!disabled) {
                            addForm(savebtn);
                        }
                    }
                },
                {text: '取消', handler: function () {
                    $('#dialogInfo').dialog('close');
                }}
            ]});
    }

        function addForm(savebtn){
            $("#objForm").submit();

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