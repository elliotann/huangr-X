<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/table.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/button.css"/>
</head>
<body style="background-color: #FFFFFF;">
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="btn-group sepH_b">
            <button class="btn dropdown-toggle btn-default btn-sm" data-toggle="dropdown">Action <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li><a data-tableid="smpl_tbl" class="delete_rows_simple" href="#"><i class="icon-trash"></i> Delete</a>
                </li>
                <li><a href="javascript:void(0)">Lorem ipsum</a></li>
                <li><a href="javascript:void(0)">Lorem ipsum</a></li>
            </ul>
        </div>
        <table id="smpl_tbl" class="table table-bordered table-striped">
            <thead>
            <tr>
                <th class="table_checkbox"><input type="checkbox" data-tableid="smpl_tbl" class="select_rows"
                                                  name="select_rows"></th>
                <th>id</th>
                <th>Customer</th>
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

    </div>
</div>


</body>
</html>
