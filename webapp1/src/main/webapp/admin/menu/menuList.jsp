<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/table.css"/>
    <link rel="stylesheet" href="../../adminthemes/blue/lib/smoke/smoke.css"/>

    <link rel="stylesheet" href="/jeap/js/ludo-jquery-treetable-73690a9/css/jquery.treetable.css"/>
    <%--<link rel="stylesheet" href="/jeap/js/ludo-jquery-treetable-73690a9/css/jquery.treetable.theme.default.css"/>--%>
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/table.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/bootstrap.min.js"></script>
    <script src="/jeap/adminthemes/blue/lib/smoke/smoke.js"></script>



    <script src="/jeap/js/ludo-jquery-treetable-73690a9/jquery.treetable.js"></script>
</head>
<body style="background-color: #EEEEEE;">


<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="btn-group sepH_b">
            <button class="btn dropdown-toggle btn-default btn-sm" data-toggle="dropdown">操作 <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a data-tableid="smpl_tbl" class="delete_rows_simple" href="#" onclick="addAdminUser()"><i class="icon-trash"></i> 增加</a>
                </li>
                <li><a href="javascript:void(0)">修改</a></li>
                <li><a href="javascript:void(0)">删除</a></li>
            </ul>
        </div>
        <table id="example-advanced" class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>名称</th>
            <th>URL</th>
            <th>图标</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${menuList}" var="menu">
            <c:if test="${fn:length(menu.children)<1 }">
                <tr data-tt-id='${menu.id}' data-tt-parent-id='${menu.pid}'>
                    <td><span class='file'>${menu.name}</span></td>
                    <td>File</td>
                    <td>480.95 KB</td>
                </tr>
            </c:if>
            <c:if test="${fn:length(menu.children)>0 }">
                <tr data-tt-id='${menu.id}' data-tt-parent-id='${menu.pid}'>
                    <td><span class='folder'>${menu.name}</span></td>
                    <td>File</td>
                    <td>480.95 KB</td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
        </table>

    </div>
</div>
<script>
    function addAdminUser(){
        location.href = "toAdd.do";
    }
    function delAdminUser(userid){
        smoke.confirm("确定删除?", function(yes){

            if (yes){
                $.ajax({
                    type:'post',
                    url:'delAdminUser.do',
                    dataType:'json',
                    data:'id='+userid,
                    success:function(result){
                        if(result.success){
                            $.ligerDialog.waitting('操作成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                location = "list.do";
                            }, 1000);
                        }else{
                            alert("删除出错!");
                        }
                    },
                    error:function(e){
                        alert("删除出错!"+e);
                    }
                });
            }
        }, {
            ok: "确定",
            cancel: "取消",
            classname: "custom-class",
            reverseButtons: true
        });

    }
    $("#example-advanced").treetable({ expandable: true });

    // Highlight selected row
    $("#example-advanced tbody").on("mousedown", "tr", function () {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected");
    });
    $("form#reveal").submit(function () {
        var nodeId = $("#revealNodeId").val()

        try {
            $("#example-advanced").treetable("reveal", nodeId);
        }
        catch (error) {
            alert(error.message);
        }

        return false;
    });
</script>
</body>
</html>
