<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/table.css"/>
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/table.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/bootstrap.min.js"></script>
    <script>
        $(function(){
            $.jeapDefaults.table.selectRow();
        })
    </script>
</head>
<body style="background-color: #EEEEEE;">

<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="btn-group sepH_b">
            <button class="btn dropdown-toggle btn-default btn-sm" data-toggle="dropdown">操作 <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a data-tableid="smpl_tbl" class="delete_rows_simple" href="#" onclick="addRole()"><i class="icon-trash"></i> 增加</a>
                </li>
                <li><a href="javascript:void(0)" onclick="updateRole()">修改</a></li>
                <li><a href="javascript:void(0)" onclick="delRoles()">删除</a></li>
            </ul>
        </div>
        <table:table items="pageOption">
            <table:header>
                <table:td clazz="table_checkbox"><input type="checkbox"
                                                        data-tableid="smpl_tbl"
                                                        class="select_rows" name="select_rows"></table:td>
                <table:td>id</table:td>
                <table:td>名称</table:td>
                <table:td>创建日期</table:td>
                <table:td>操作</table:td>
            </table:header>
            <table:body item="role">
                <table:td><input type="checkbox" class="select_row" name="row_sel" value="${role.id}"></table:td>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td>${role.createTime}</td>
                <td>查看</td>
            </table:body>
        </table:table>

    </div>
</div>
<script>
    function addRole(){
        location.href = "toAdd.do";
    }
    function updateRole(){
        if($("input[name='row_sel']:checked").length!=1){
            alert("请选择一条记录进行操作！");
            return ;
        }
        var id=0;
        $("input[name='row_sel']:checked").each(function(i,v){
            id = $(this).val();
        });
        location.href = "toAdd.do?id="+id;
    }
    function delRoles(){
        if($("input[name='row_sel']:checked").length<1){
            alert("请至少选择一条记录进行操作！");
            return ;
        }
        var ids = [];

        $("input[name='row_sel']:checked").each(function(i,v){
            ids.push($(this).val());
        });
        var params = {ids:ids};
        $.ajax({
            type:'post',
            url:'delRoles.do',
            dataType:'json',
            data:params,
            success:function(result){
                if(result.success){
                    alert("操作成功！");
                    location.href="list.do";
                }
            },
            error:function(e){
                alert(e);
            }
        });

    }
</script>
</body>
</html>
