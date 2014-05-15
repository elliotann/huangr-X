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
    <script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>

    <script src="../TreeData.js" type="text/javascript"></script>
    <script type="text/javascript">
        var dialog = frameElement.dialog;
        var manager;
        $(function ()
        {


            listgrid = $("#maingrid").ligerGrid({
                        height:400,
                        columns: [
                            { display: 'id', name: 'id', id: 'menuId',  align: 'center',width:60 },
                            { display: '名称', name: 'title', id: 'menuName',  align: 'left',width:250 },
                            { display: '操作', name: 'ico',  align: 'left',width:300,render:function(item){
                                var menuId= item.id;
                                <c:forEach var="funAndOper" items="${funAndOpers}">

                                    if("${funAndOper.menu.id}" ==menuId){
                                        this.select(item);
                                    }
                                </c:forEach>


                                var html="";
                                if(item.hasChildren){
                                    return html;
                                }
                                var ischecked = "";
                                <c:forEach var="operBtn" items="${operationBtns}">

                                    <c:forEach var="funAndOper" items="${funAndOpers}">

                                        if("${funAndOper.menu.id}" ==menuId&&ischecked==""){
                                            var oper = "${funAndOper.operation}";

                                            var opers = oper.split(',');
                                            for(var i=0;i<opers.length;i++){

                                                if(opers[i]=="${operBtn.id}"){
                                                    ischecked = "checked";

                                                    break;
                                                }
                                            }
                                        }

                                    </c:forEach>

                                    html += "<input type='checkbox' name='operBtn' value='${operBtn.code}' id='"+item.id+"|${operBtn.id}' "+ischecked+"/>${operBtn.name}&nbsp;&nbsp;";
                                    ischecked="";
                                </c:forEach>


                                return html;
                            } }
                        ], width: '100%', usePager:false, checkbox: true,
                        url: 'auth.do?dataGrid&ajax=yes', alternatingRow: false,selectRowButtonOnly:true, tree: {
                            columnId: 'menuName',
                            idField: 'id',
                            parentIDField: 'pid'
                        },onCheckRow:onCheckRow

                    }
            );
            manager = $("#maingrid").ligerGetGridManager();
        });


        function onCheckRow(checked,data,rowid,rowdata){


            var parent = manager.getParent(data);
            if(parent!=null){
                manager.select(parent);
                if(parent.pid!=0){
                    selectParent(manager.getParent(parent));
                }

            }


        }

        function selectParent(data){
            manager.select(data);
            if(data.pid!=0){
                selectParent(manager.getParent(data));
            }
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

        function submitForm(){

            var rows = manager.getSelectedRows();

            var menu = "";
            $(rows).each(function () {

                menu += "m" + this.id + ",";
            });

            var btn = "";
            $("input[type='checkbox']").each(function (i) {
                if ($(this).attr("checked")&&$(this).attr("name")) {
                    btn += $(this).attr("id") + ",";
                }
            })
            var savetext = "{role_id:'" + 1 + "',";
            savetext += "menu:'" + menu + "',";
            savetext += "btn:'" + btn + "'}";
            $.ajax({
                type: 'post',
                url: "auth.do?saveAuth&ajax=true&postdata=" + savetext + '&roleId='+${roleId},
                dataType:'json',
                success: function (result) {

                    if(result.success){
                        $.ligerDialog.waitting('增加成功');
                        setTimeout(function ()
                        {
                            $.ligerDialog.closeWaitting();
                            dialog.close();
                        }, 1000);



                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    f_error("授权失败！");
                }
            });
        }

    </script>
</head>

<body style="padding: 4px">

        <div id="maingrid">
        </div>


</body>
</html>

