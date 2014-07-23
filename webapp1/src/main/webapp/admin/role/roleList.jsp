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
        <h3 class="heading">Table with action button</h3>

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

<div class="row">
    <div class="col-sm-12 col-md-12">
        <h3 class="heading">Gallery table view</h3>

        <div role="grid" class="dataTables_wrapper form-inline" id="dt_gal_wrapper">

            <table id="dt_gal" class="table table-bordered table-striped table_vam dataTable"
                   aria-describedby="dt_gal_info">
                <thead>
                <tr role="row">
                    <th class="table_checkbox sorting_disabled" role="columnheader" rowspan="1" colspan="1"
                        style="width: 13px;" aria-label=""><input type="checkbox" data-tableid="dt_gal"
                                                                  class="select_rows" name="select_rows"></th>
                    <th style="width:60px" class="sorting_disabled" role="columnheader" rowspan="1" colspan="1"
                        aria-label="Image">Image
                    </th>
                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="dt_gal" rowspan="1"
                        colspan="1" style="width: 498px;" aria-sort="ascending"
                        aria-label="Name: activate to sort column descending">Name
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="dt_gal" rowspan="1" colspan="1"
                        style="width: 200px;" aria-label="Size: activate to sort column ascending">Size
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="dt_gal" rowspan="1" colspan="1"
                        style="width: 277px;" aria-label="Date: activate to sort column ascending">Date
                    </th>
                    <th class="sorting_disabled" role="columnheader" rowspan="1" colspan="1" style="width: 211px;"
                        aria-label="Actions">Actions
                    </th>
                </tr>
                </thead>

                <tbody role="alert" aria-live="polite" aria-relevant="all">
                <tr class="odd">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 10" href="gallery/Image10.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image10_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image10.jpg</small>
                    </td>
                    <td class=" ">200 KB</td>
                    <td class=" ">28/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                <tr class="even">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 11" href="gallery/Image11.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image11_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image11.jpg</small>
                    </td>
                    <td class=" ">210 KB</td>
                    <td class=" ">27/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                <tr class="odd">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 12" href="gallery/Image12.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image12_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image12.jpg</small>
                    </td>
                    <td class=" ">360 KB</td>
                    <td class=" ">25/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                <tr class="even">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 13" href="gallery/Image13.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image13_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image13.jpg</small>
                    </td>
                    <td class=" ">215 KB</td>
                    <td class=" ">24/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                <tr class="odd">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 14" href="gallery/Image14.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image14_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image14.jpg</small>
                    </td>
                    <td class=" ">650 KB</td>
                    <td class=" ">24/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                <tr class="even">
                    <td class=" "><input type="checkbox" class="select_row" name="row_sel"></td>
                    <td class=" ">
                        <a class="cbox_single thumbnail cboxElement" title="Image 15" href="gallery/Image15.jpg">
                            <img style="height:50px;width:50px" src="gallery/Image15_tn.jpg" alt="">
                        </a>
                    </td>
                    <td class="  sorting_1">Lorem ipsum dolor sit<br>
                        <small>Image15.jpg</small>
                    </td>
                    <td class=" ">428 KB</td>
                    <td class=" ">23/06/2012</td>
                    <td class=" ">
                        <a title="Edit" class="sepV_a" href="#"><i class="icon-pencil"></i></a>
                        <a title="View" class="sepV_a" href="#"><i class="icon-eye-open"></i></a>
                        <a title="Delete" href="#"><i class="icon-trash"></i></a>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>

    </div>
</div>
</body>
</html>
