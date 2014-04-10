<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>

<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet">
<script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
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
    var grid;




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

        grid = $("#maingrid").ligerGrid({
                    height:'99%',
                    columns: [
                        { display: '名称', name: 'title', id: 'title', width: 250, align: 'left' },
                        { display: 'url', name: 'url', id: 'url', width: 250, align: 'left' },
                        { display: '类型', name: 'menutype', id:'menutype',width: 140, type: 'int', align: 'left',render:function(rowdata,index,value){
                            if(value==1){
                                return "系统菜单";
                            }else{
                                return "应用菜单";
                            }
                        } },
                        { display: '排序', name: 'sorder', align: 'left' },
                        { display: 'target', name: 'target', align: 'left' }
                    ], width: '100%', pageSizeOptions: [5, 10, 15, 20],
                    url: 'menu.do?dataGrid&ajax=yes', alternatingRow: false, tree: {
                        columnId: 'title',
                        //columnName: 'name',
                        idField: 'id',
                        parentIDField: 'pid'
                    },toolbar: { items: [
                        { text: '增加', click: addMenu, icon: 'add' },
                        { line: true },
                        { text: '修改', click: itemclick, icon: 'modify' },
                        { line: true },
                        { text: '删除', click: itemclick, img: '${context }/js/ligerui/skins/icons/delete.gif' }
                    ]
                    }
                }
        );
    });

    function getParent()
    {
        var row = grid.getParent(grid.getSelectedRow());
        alert(JSON.stringify(row));
    }
    function getSelected()
    {
        var row = grid.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
        alert(JSON.stringify(row));
    }
    function getData()
    {
        var data = grid.getData();
        alert(JSON.stringify(data));
    }
    function hasChildren()
    {
        var row = grid.getSelectedRowObj();
        alert(grid.hasChildren(row));
    }
    function isLeaf()
    {
        var row = grid.getSelectedRowObj();
        alert(grid.isLeaf(row));
    }
    function itemclick(item){

    }



    function addMenu(item){
        $.ligerDialog.open({
            height:400,
            width: 800,
            title : '增加菜单',
            url: 'menu.do?add',
            showMax: false,
            showToggle: true,
            showMin: false,
            isResize: true,
            slide: false,
            data: {
                name: $("#txtValue").val()
            },
            //自定义参数
            myDataName: $("#txtValue").val()
        });
    }
</script>

<div class="searchBar">
    <form action="#">
        <table>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username"/></td>
                <td><input type="button" value="搜索"/></td>
            </tr>
        </table>
    </form>
</div>
<div class="grid">
<div id="maingrid"></div>
 </div>
<div>
</div>
