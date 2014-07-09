<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/7/8
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/style.css"/>
    <script src="../../js/common/jquery-1.8.3.js"></script>
    <script src="../../adminthemes/default/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.flot.min.js" type="text/javascript"></script>
    <script src="../default/js/general.js" type="text/javascript"></script>
    <script src="../default/js/dashboard.js" type="text/javascript"></script>
</head>
<body style="background-color: #EEEEEE;">


<!-- START WIDGET LIST -->
<ul class="widgetlist">
    <button class="button button_blue">增加</button>
    &nbsp;

</ul>
<!-- END WIDGET LIST -->

<div class="clear"></div>

<div class="dataTables_wrapper" id="example_wrapper">
    <div id="example_length" class="dataTables_length"><label>每页显示 <select name="example_length" size="1">
        <option value="10" selected="selected">10</option>
        <option value="25">25</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </select> 条</label></div>
    <div class="dataTables_filter" id="example_filter"><label>Search: <input type="text"></label></div>
    <table cellspacing="0" cellpadding="0" border="0" id="example" class="sTable2">
        <thead>

        <tr>
            <th class="head0 sorting_asc" rowspan="1" colspan="1" style="width: 245px;">用户编号</th>
            <th class="head1 sorting" rowspan="1" colspan="1" style="width: 368px;">用户名</th>
            <th class="head0 sorting" rowspan="1" colspan="1" style="width: 339px;">密码</th>
            <th class="head1 sorting" rowspan="1" colspan="1" style="width: 209px;">状态</th>
            <th class="head0 sorting" rowspan="1" colspan="1" style="width: 150px;">操作</th>
        </tr>
        </thead>
        <colgroup>
            <col class="con0">
            <col class="con1">
            <col class="con0">
            <col class="con1">
            <col class="con0">
        </colgroup>



        <tbody>
        <c:forEach items="${pageOption.data}" var="adminUser">
        <tr class="gradeA odd">
            <td class=" sorting_1" align="center">${adminUser.id}</td>
            <td align="center">${adminUser.username}</td>
            <td align="center">${adminUser.password}</td>
            <td class="center">1.7</td>

            <td class="center">A</td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
    <div class="dataTables_info" id="example_info">显示 1 至 10 总共 ${pageOption.totalCount} 条</div>
    <div class="dataTables_paginate paging_full_numbers" id="example_paginate"><span
            class="first paginate_button paginate_button_disabled" id="example_first">首页</span><span
            class="previous paginate_button paginate_button_disabled" id="example_previous">上一页</span><span><span
            class="paginate_active">1</span><span class="paginate_button">2</span><span class="paginate_button">3</span><span
            class="paginate_button">4</span><span class="paginate_button">5</span></span><span
            class="next paginate_button" id="example_next">下一页</span><span class="last paginate_button"
                                                                           id="example_last">尾页</span></div>
</div>
<br>

</body>
</html>
