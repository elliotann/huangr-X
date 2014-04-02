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
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<link href="/jeap/form/config/lab.css" rel="stylesheet" type="text/css" />
<script src="/jeap/form/config/data.js" type="text/javascript"></script>
<script src="/jeap/form/config/lab.js" type="text/javascript"></script>
<script src="/jeap/form/config/preview.js" type="text/javascript"></script>
<script src="/jeap/form/config/AllProductData.js" type="text/javascript"></script>
<script src="/jeap/form/config/ligerGrid.showFilter.js" type="text/javascript"></script>

<script type="text/javascript">
    var grid1;
    function addForm(item)
    {

        $.ligerDialog.open({
            height:500,
            width: 1000,
            title : '表单设计',
            url: 'designer.do?toDesigner',
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
    function modifyForm(item)
    {

        var row = grid1.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        $.ligerDialog.open({
            height:600,
            width: 900,
            title : '修改表单',
            url: 'designer.do?modify&id='+row.id,
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
        var row = grid1.getSelectedRow();
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
                            $.ligerDialog.alert('删除成功!', '提示', type);
                            grid1.loadData();
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
        grid1 =
                $("#maingrid").ligerGrid({
                    height:'99%',
                    columns: [
                        { display: 'id', name: 'id', align: 'left', width: 100, minWidth: 60 },
                        { display: '表名', name: 'tableName', minWidth: 120 },
                        { display: '表描述', name: 'tableTitle', minWidth: 140 },
                        { display: '版本', name: 'version', minWidth: 140 },
                        { display: '同步数据库', name: 'realname', minWidth: 140,render:function(rowdata,index,value){
                            if(value==1){
                                return "已同步";
                            }else{
                                return "未同步";
                            }
                        } },
                        { display: '创建人', name: 'createBy', minWidth: 140 },
                        { display: '创建时间', name: 'createTime', minWidth: 140 },
                        { display: '操作', name: 'realname', minWidth: 140,render:function(rowdata,index,value){

                              return "<a href='#' onclick='preview("+rowdata.id+")'>预览</a> ";

                        } }
                    ], url:'designer.do?dataGrid&ajax=yes',  pageSize:30 ,rownumbers:true,
                    toolbar: { items: [
                        { text: '增加', click: addForm, icon: 'add' },
                        { line: true },
                        { text: '修改', click: modifyForm, icon: 'modify' },
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