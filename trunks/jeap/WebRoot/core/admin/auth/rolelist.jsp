<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>




<script type="text/javascript" src="${staticserver }/js/common/jquery-1.10.js"></script>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />

<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="/jeap/admin/js/common/crud.js" type="text/javascript"></script>

<script type="text/javascript">
    var listgrid;
    function addRole(item)
    {
        addOrUpdateDialog(item,'增加角色','role.do?add',400,600);
    }
    function modifyUser(item)
    {

        var row = grid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        $.ligerDialog.open({
            height:600,
            width: 800,
            title : '修改管理员',
            url: 'userAdmin.do?edit&id='+row.userid,
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
    function delUser(item)
    {
        var row = grid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }
        $.ligerDialog.confirm('确定删除？', function (yes) {
            if(yes){
                $.ajax({
                    type: "GET",
                    url: "userAdmin.do?delete&id="+row.userid,
                    data:"ajax=true&rmd="+ new Date().getTime(),
                    dataType:"json",
                    success: function(result){
                        if(result.success){
                            $.ligerDialog.waitting('正在保存中,请稍候...');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                grid.loadData();
                            }, 1000);

                        }else{
                            $.ligerDialog.alert(result.msg, '提示', type);

                        }
                    },error:function(e){
                        $.ligerDialog.alert('出错了!', '提示', type)

                    }
                });
            }
        });



    }
    $(function ()
    {
        listgrid =
                $("#maingrid").ligerGrid({
                    height:'99%',
                    columns: [
                        { display: 'id', name: 'userid', align: 'left', width: 100, minWidth: 60 },
                        { display: '角色名称', name: 'username', minWidth: 120 },
                        { display: '描述', name: 'realname', minWidth: 140 }
                    ], url:'role.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
                    toolbar: { items: [
                        { text: '增加', click: addRole, icon: 'add' },
                        { line: true },
                        { text: '修改', click: modifyUser, icon: 'modify' },
                        { line: true },
                        { text: '删除', click: delUser, img: '${context }/js/ligerui/skins/icons/delete.gif' }
                    ]
                    }
                });


        $("#pageloading").hide();
    });

    function deleteRow()
    {
        g.deleteSelectedRow();
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

<div style="display:none;">

</div>