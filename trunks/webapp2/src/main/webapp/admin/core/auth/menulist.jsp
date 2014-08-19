<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>


<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
<link href="${context }/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${ctx}/admin/js/common/crud.js" type="text/javascript"></script>
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

    $(function ()
    {
        window.dialog = $.ligerDialog.open({
            isResize: true,
            isHidden: true,
            target: $("<div id='message' class='message'></div>"),
            buttons: [
                { text: '关闭', onclick: function (i, d) { d.hide(); } }
            ]
        });
        dialog.hide();
        window.alert = function (message) {
            $("#message").html(message.toString());
            dialog.show();
        }

        /*listgrid = $("#maingrid").ligerGrid({
         height:'99%',
         width: '100%', usePager:false,
         url: 'menu.do?dataGrid&ajax=yes', alternatingRow: false, tree: {
         columnId: 'title',
         idField: 'id',
         parentIDField: 'pid'
         },
         columns: [
         { display: '名称', name: 'title', id: 'title',  align: 'left',width:250 },
         { display: 'url', name: 'url', id: 'url', width: 400, align: 'left' },
         { display: '类型', name: 'menutype', id:'menutype',width: 100, type: 'int', align: 'center',render:function(rowdata,index,value){
         if(value==1){
         return "系统菜单";
         }else{
         return "应用菜单";
         }
         } },
         { display: 'target', name: 'target', align: 'left',width: 50 },
         { display: '排序', name: 'sorder', align: 'center',width:100 },
         { display: '图标', name: 'sorder', align: 'left',width:400 }

         ], toolbar: { items: [
         { text: '增加', click: addMenu, icon: 'add' },
         { line: true },
         { text: '修改', click: updateMenu, icon: 'modify' },
         { line: true },
         { text: '删除', click: delMenu, img: '${context }/js/ligerui/skins/icons/delete.gif' },
     { text: '增加按钮', click: addBtn, icon: 'add' }
     ]
     }
     }
     );*/
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
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }

        delObj(item,"menu.do?delete&id=",row.id);
    }



    function addMenu(item){
        addOrUpdateDialog(item,'增加菜单','menu.do?add',400,400);

    }

    function addBtn(item){
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据操作!');
            return;
        }
        addOrUpdateDialog(item,'增加按钮','oper.do?add&menuId='+row.id,400,400);

    }

    function updateMenu(item){
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        addOrUpdateDialog(item,'修改菜单','menu.do?edit&id='+row.id,500,700);

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

<grid:dataGrid action="menu.do?dataGrid&ajax=yes" height="99%" usePager="false"  width="100%" tree="true">
    <grid:column title="名称" field="title" align="left" width="250" id="title"/>
    <grid:column title="url" field="url"  width="400" align="left" id="url"/>
    <grid:column title="类型" field="menutype"  width="100" align="center" sortType="int" renderFun="getMenuType" id="menutype"/>
    <grid:column title="target" field="target" align="left"  width="50" id="target"/>
    <grid:column title="排序" field="sorder" align="center"  width="100"/>
    <grid:column title="图标" field="ico" align="center"  width="400" renderFun="showIco"/>
    <grid:toolbar title="增加" clickFun="addMenu" icon="add"/>
    <grid:toolbar title="修改" clickFun="updateMenu" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delMenu" icon="delete"/>
    <grid:toolbar title="增加按钮" clickFun="addBtn" icon="add"/>
</grid:dataGrid>


