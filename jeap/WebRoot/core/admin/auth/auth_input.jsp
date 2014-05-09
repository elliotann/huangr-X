<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>

<script src="${context }/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
<script src="/jeap/admin/js/common/crud.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
<script type="text/javascript" src="/jeap/admin/menu.do?roleId=${roleId}"></script>
<script type="text/javascript">
    var dialog1 = frameElement.dialog;
    var manager;
    var listgrid;
    $(function (){

        $("form").ligerForm();
        $("#tree1").ligerTree(
                {
                    data:menu.app,
                    idFieldName :'id',
                    parentIDFieldName :'pid',
                    nodeWidth : 200,
                    onSelect: onSelect
                });

        manager = $("#tree1").ligerGetTreeManager();
        manager.collapseAll();
        $("#layout1").ligerLayout({ leftWidth: 150, allowLeftResize: false, allowLeftCollapse: true, space: 2 });
        listgrid = $("#maingrid4").ligerGrid({
            columns: [
                { display: 'ID', name: 'id', width: 50 },
                { display: '菜单名称', name: 'title', align: 'left',width: 50},
                {
                    display: '按钮权限', name: 'Menu_icon', align: 'left',  render: function (item) {
                    //var html = "<div style='vertical-align:middle;color: #FF0000;background:yellow'>";
                    var html = "<div style='vertical-align:middle;'>";
                    var returntxt = item.Sysroler;

                    var arrstr = new Array();
                    var arrstr1 = new Array();
                    var arrid = new Array();
                    var arrname = new Array();
                    arrstr = returntxt.split(",");

                    for (var j = 0; j < arrstr.length - 1; j++) {
                        arrstr1 = arrstr[j].split("|");
                        arrid = arrstr1[0];
                        arrname = arrstr1[1];
                        html += "<input type='checkbox' ";
                        html += "value='" + arrid + "' ";
                        html += " id='b" + arrid + "'";
                        html += ">";
                        html += arrname;
                        html += "&nbsp;&nbsp;";
                    }
                    html += "</div>";
                    return html;
                }
                }

            ],
            rowid: "Menu_id",
            dataAction: 'local',
            pageSize: 30,
            pageSizeOptions: [20, 30, 50, 100],
            enabledEdit: true,
            checkbox: true,

            width: '100%', height: '100%',
            usePager: false,
            tree: { columnName: 'Menu_name' },
            heightDiff: -3
        });
    });
    function onSelect(node){
        alert(node.data.id);
        //加载数据
        var manager = $("#maingrid4").ligerGetGridManager();


        $.ajax({
            type: 'post',
            url: "auth.do?getMenuTreeById&id=" + node.data.id+"&ajax=true",
            dataType:'json',
            success: function (data) {
                alert(data.obj);
                manager.loadData(data);

            },
            error: function (e) {

            }
        });
    }
    function submitForm(){

        var notes = manager.getChecked();
        var ids = new Array();
        for (var i = 0; i < notes.length; i++)
        {
            ids.push(notes[i].data.id);

        }
        $.ajax({
            url:'auth.do?save',
            type:'post',
            dataType:'json',
            data: {
                menuids:ids,
                roleId: $("#roleId").val(),
                edit:$("#isEdit").val(),
                ajax:'true',
                actid:$("#actid").val()==''?'0':$("#actid").val()
            },
            success:function(data){
                if(data.success){
                    dialog1.close();
                }
            },error:function(e){
                alert(e);
            }
        });

    }

</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
    #layout1{  width:100%; margin:40px;  height:100%;
        margin:0; padding:0;}
</style>
<div id="layout1">
    <div position="left" title="系统目录">
        <div id="treediv1" style="width: 250px; height: 100%; margin: -1px; float: left; border: 1px solid #ccc; overflow: auto;">
            <ul id="tree1"></ul>
        </div>
    </div>
    <div position="center">


        <div id="maingrid4" style="margin: -1px;"></div>
    </div>
</div>

<%--
<form name="objForm" method="post"   id="objForm">
    <input type="hidden" name="isEdit" id="isEdit" value="${isEdit }" />
    <input type="hidden" name="roleId" value="${roleId}" id="roleId" />
    <input type="hidden" name="actid" value="${actid}" id="actid"/>
    <input type="hidden" id="objvalue" value="${auth.objvalue }" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >

        <tr>
            <td align="right" class="l-table-edit-td">菜单：</td>
            <td align="left" class="l-table-edit-td" >

                    <ul id="tree1">

                    </ul>


            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>--%>
