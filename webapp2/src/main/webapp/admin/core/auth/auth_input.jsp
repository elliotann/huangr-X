<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
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
                                $(${selectMenus}).each(function(i,data){
                                    if(item.id==data.id){
                                        manager.select(item);
                                    }

                                });

                                var  html="";
                                if(item.hasChildren){
                                    return html;
                                }
                                $.ajax({
                                    url:'auth.do?getBtnByMenuId&ajax=yes&id='+item.id+'&roleId='+${roleId},
                                    type:'post',
                                    dataType:'json',
                                    async:false,
                                    success:function(result){
                                        html = result;
                                    }

                                });
                                html += "<input type='hidden' id='menu"+item.id+"' value='"+item.__id+"'/>";
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
                selectParent(parent);
            }
            var rows = manager.getSelectedRows();

            var menu=[];

            $(rows).each(function () {
                menu.push(this.id);
            });
            var param = {menuIds:menu,roleId:${roleId}};

            //保存权限
            $.ajax({
                type: 'post',
                url: "auth.do?saveAuth&ajax=true",
                data:param,
                dataType:'json',
                success:function(result){

                }
            });


        }

        function selectParent(data){
            manager.select(data);
            if(data.pid!=0){
                selectParent(manager.getParent(data));
            }

        }

        function submitForm(){
            $.ligerDialog.waitting('增加成功');
            setTimeout(function ()
            {
                $.ligerDialog.closeWaitting();
                dialog.close();
            }, 1000);

        }
        function checkOperation(obj){
            //判断功能权限是否选中
            var rowid = obj.getAttribute("id").split("_")[1];
            var rowdata = manager.getRow($("#menu"+rowid).val());
            var param = {menuIds:[rowdata.id+''],roleId:${roleId},operId:obj.value,isCheck:obj.checked,ajax:'true'};
            if(manager.isSelected(rowdata)){

                //保存权限
                $.ajax({
                    type: 'post',
                    url: "auth.do?saveAuth",
                    dataType:'json',
                    data:param,
                    success:function(result){

                    }
                });
            }
        }
    </script>
</head>

<body style="padding: 4px">
<div position="center" title="功能权限">
    <div id="maingrid">

    </div>
</div>
</body>
</html>

