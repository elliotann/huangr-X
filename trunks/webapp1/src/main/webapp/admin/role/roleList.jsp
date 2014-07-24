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
<body style="background-color: #FFFFFF;">
<div class="row">
    <div class="col-sm-12 col-md-12">
        <a class="btn btn-default btn-sm" href="#modal_form" data-toggle="modal">Open modal with some elements</a>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="btn-group sepH_b">
            <button class="btn dropdown-toggle btn-default btn-sm" data-toggle="dropdown">操作 <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a data-tableid="smpl_tbl" class="delete_rows_simple" href="#"><i class="icon-trash"></i> 增加</a>
                </li>
                <li><a href="javascript:void(0)">修改</a></li>
                <li><a href="javascript:void(0)">删除</a></li>
            </ul>
        </div>

        <table id="smpl_tbl" class="table table-bordered table-striped">
            <thead>
            <tr>
                <th class="table_checkbox"><input type="checkbox" data-tableid="smpl_tbl" class="select_rows"
                                                  name="select_rows"></th>
                <th>id</th>
                <th class="sorting_asc">Customer</th>
                <th>Status</th>
                <th>Date Added</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="checkbox" class="select_row" name="row_sel"></td>
                <td>134</td>
                <td>Summer Throssell</td>
                <td>Pending</td>
                <td>24/04/2012</td>
                <td>$120.23</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="row_sel"></td>
                <td>133</td>
                <td>Declan Pamphlett</td>
                <td>Pending</td>
                <td>23/04/2012</td>
                <td>$320.00</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="row_sel"></td>
                <td>132</td>
                <td>Erin Church</td>
                <td>Pending</td>
                <td>23/04/2012</td>
                <td>$44.00</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="row_sel"></td>
                <td>131</td>
                <td>Koby Auld</td>
                <td>Pending</td>
                <td>22/04/2012</td>
                <td>$180.20</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="row_sel"></td>
                <td>130</td>
                <td>Anthony Pound</td>
                <td>Pending</td>
                <td>20/04/2012</td>
                <td>$610.42</td>
            </tr>
            </tbody>
        </table>

        <div>
            <div class="pagedetail">
                显示 1 至5总共 5 条
            </div>
            <div class="dataTables_paginate paging_bootstrap">
                <ul class="pagination pagination-sm">
                    <li class="prev disabled"><a href="#">首页</a></li>
                    <li class="prev disabled"><a href="#">上一页</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li class="active"><a href="#">2</a></li>
                    <li class="next disabled"><a href="#">下一页</a></li>
                    <li class="next disabled"><a href="#">尾页</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
