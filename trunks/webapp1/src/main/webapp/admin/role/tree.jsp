<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/table.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/dtree.css"/>
    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>
    <script src="/jeap/admin/admin/js/jquery-ui-1.8.4.custom.min.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/table.js"></script>
    <script src="/jeap/adminthemes/default/js/mylib/bootstrap.min.js"></script>
    <script src="/jeap/adminthemes/default/js/tree/jtree/jquery.dynatree.js"></script>


    <script>
        $(function () {
            $.jeapDefaults.table.selectRow();
        })
    </script>
</head>
<body style="background-color: #EEEEEE;">

<div class="row">
    <div id="tree_c">

        <ul class="dynatree-container" style="height: 400px">
            <li class=""><span class="dynatree-node dynatree-exp-c dynatree-ico-c"><span
                    class="dynatree-connector"></span><span class="dynatree-checkbox"></span><span
                    class="dynatree-icon"></span><a href="#" class="dynatree-title">item1</a></span></li>
            <li class=""><span
                    class="dynatree-node dynatree-folder dynatree-expanded dynatree-has-children dynatree-exp-e dynatree-ico-ef"><span
                    class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Folder with some children</a></span>
                <ul style="">
                    <li class=""><span class="dynatree-node dynatree-has-children dynatree-exp-c dynatree-ico-c"><span
                            class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                            class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item 3.1</a></span></li>
                    <li class="dynatree-lastsib"><span
                            class="dynatree-node dynatree-has-children dynatree-lastsib dynatree-exp-cl dynatree-ico-c"><span
                            class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                            class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item 3.2</a></span></li>
                </ul>
            </li>
            <li class="dynatree-lastsib"><span
                    class="dynatree-node dynatree-expanded dynatree-has-children dynatree-lastsib dynatree-exp-el dynatree-ico-e dynatree-partsel dynatree-selected"><span
                    class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Document with some children
                (expanded on init)</a></span>
                <ul style="">
                    <li class=""><span
                            class="dynatree-node dynatree-expanded dynatree-has-children dynatree-selected dynatree-partsel dynatree-active dynatree-exp-e dynatree-ico-e"><span
                            class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                            class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item 4.1 (active and
                        focus on init)</a></span>
                        <ul style="">
                            <li class=""><span
                                    class="dynatree-node dynatree-selected dynatree-exp-c dynatree-ico-c"><span
                                    class="dynatree-connector"></span><span class="dynatree-checkbox"></span><span
                                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item
                                4.1.1</a></span></li>
                            <li class="dynatree-lastsib"><span
                                    class="dynatree-node dynatree-lastsib dynatree-selected dynatree-exp-cl dynatree-ico-c"><span
                                    class="dynatree-connector"></span><span class="dynatree-checkbox"></span><span
                                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item
                                4.1.2</a></span></li>
                        </ul>
                    </li>
                    <li class="dynatree-lastsib"><span
                            class="dynatree-node dynatree-expanded dynatree-has-children dynatree-lastsib dynatree-exp-el dynatree-ico-e dynatree-selected dynatree-partsel"><span
                            class="dynatree-expander"></span><span class="dynatree-checkbox"></span><span
                            class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item 4.2</a></span>
                        <ul style="">
                            <li class=""><span
                                    class="dynatree-node dynatree-exp-c dynatree-ico-c dynatree-selected"><span
                                    class="dynatree-connector"></span><span class="dynatree-checkbox"></span><span
                                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item
                                4.2.1</a></span></li>
                            <li class="dynatree-lastsib"><span
                                    class="dynatree-node dynatree-lastsib dynatree-exp-cl dynatree-ico-c dynatree-selected"><span
                                    class="dynatree-connector"></span><span class="dynatree-checkbox"></span><span
                                    class="dynatree-icon"></span><a href="#" class="dynatree-title">Sub-item
                                4.2.2</a></span></li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <%--<div id="tree_c_echoSelection" style="padding-top:10px">Selection:
        <pre>DynaTreeNode&lt;c_id4&gt;: 'Document with some children (expanded on init)' | DynaTreeNode&lt;c_id4.1&gt;: 'Sub-item 4.1 (active and focus on init)' | DynaTreeNode&lt;c_id4.1.1&gt;: 'Sub-item 4.1.1' | DynaTreeNode&lt;c_id4.1.2&gt;: 'Sub-item 4.1.2' | DynaTreeNode&lt;c_id4.2&gt;: 'Sub-item 4.2' | DynaTreeNode&lt;c_id4.2.1&gt;: 'Sub-item 4.2.1' | DynaTreeNode&lt;c_id4.2.2&gt;: 'Sub-item 4.2.2'</pre>
    </div>--%>

</div>
<script>
    function funPerssion() {
        location.href = "funPerssion.do";
    }
    function addRole() {
        location.href = "toAdd.do";
    }
    function updateRole() {
        if ($("input[name='row_sel']:checked").length != 1) {
            alert("请选择一条记录进行操作！");
            return;
        }
        var id = 0;
        $("input[name='row_sel']:checked").each(function (i, v) {
            id = $(this).val();
        });
        location.href = "toAdd.do?id=" + id;
    }
    function delRoles() {
        if ($("input[name='row_sel']:checked").length < 1) {
            alert("请至少选择一条记录进行操作！");
            return;
        }
        var ids = [];

        $("input[name='row_sel']:checked").each(function (i, v) {
            ids.push($(this).val());
        });
        var params = {ids: ids};
        $.ajax({
            type: 'post',
            url: 'delRoles.do',
            dataType: 'json',
            data: params,
            success: function (result) {
                if (result.success) {
                    alert("操作成功！");
                    location.href = "list.do";
                }
            },
            error: function (e) {
                alert(e);
            }
        });

    }
</script>
</body>
</html>
