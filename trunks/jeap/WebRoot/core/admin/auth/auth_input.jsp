<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
    <script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>

    <script src="../TreeData.js" type="text/javascript"></script>
    <script type="text/javascript">

        function f_renderAmount(rowdata, index, value)
        {
            if (!this.hasChildren(rowdata))
            {
                return value;
            }
            else
            {
                var children = this.getChildren(rowdata, true);
                var sum = 0;
                for (var i = 0, l = children.length; i < l; i++)
                {
                    sum += children[i].amount || 0;
                }
                return sum;
            }
        }


        var manager;
        $(function ()
        {


            listgrid = $("#maingrid").ligerGrid({
                        height:400,
                        columns: [
                            { display: 'id', name: 'menuId', id: 'menuId',  align: 'center',width:60 },
                            { display: '名称', name: 'menuName', id: 'menuName',  align: 'left',width:250 },
                            { display: '操作', name: 'ico',  align: 'left',width:300,render:function(item){
                                var html="${operationBtns}";

                                return html;
                            } }
                        ], width: '100%', usePager:false, checkbox: true,
                        url: 'auth.do?dataGrid&ajax=yes', alternatingRow: false,selectRowButtonOnly:true, tree: {
                            columnId: 'menuName',
                            //columnName: 'name',
                            idField: 'menuId',
                            parentIDField: 'pid'
                        }
                    }
            );
        });

        function getParent()
        {
            var row = manager.getParent(manager.getSelectedRow());
            alert(JSON.stringify(row));
        }
        function getSelected()
        {
            var row = manager.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getData()
        {
            var data = manager.getData();
            alert(JSON.stringify(data));
        }
        function hasChildren()
        {
            var row = manager.getSelectedRowObj();
            alert(manager.hasChildren(row));
        }
        function isLeaf()
        {
            var row = manager.getSelectedRowObj();
            alert(manager.isLeaf(row));
        }

    </script>
</head>

<body style="padding: 4px">

        <div id="maingrid">
        </div>



</body>
</html>

