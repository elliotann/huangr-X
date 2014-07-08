<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/7/8
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../default/css/style.css"/>
    <script src="../default/js/jquery-1.8.3.js"></script>
    <script src="../default/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="../default/js/jquery.flot.min.js" type="text/javascript"></script>
    <script src="../default/js/general.js" type="text/javascript"></script>
    <script src="../default/js/dashboard.js" type="text/javascript"></script>
</head>
<body style="background-color: #EEEEEE;">



    <!-- START WIDGET LIST -->
    <ul class="widgetlist">
        <button class="button button_blue">增加</button> &nbsp;

    </ul>
    <!-- END WIDGET LIST -->

    <div class="clear"></div>

    <div class="dataTables_wrapper" id="example_wrapper"><div id="example_length" class="dataTables_length"><label>Show <select name="example_length" size="1"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div><div class="dataTables_filter" id="example_filter"><label>Search: <input type="text"></label></div><table cellspacing="0" cellpadding="0" border="0" id="example" class="dyntable">
        <thead>

        <tr><th class="head0 sorting_asc" rowspan="1" colspan="1" style="width: 245px;">Rendering engine</th><th class="head1 sorting" rowspan="1" colspan="1" style="width: 368px;">Browser</th><th class="head0 sorting" rowspan="1" colspan="1" style="width: 339px;">Platform(s)</th><th class="head1 sorting" rowspan="1" colspan="1" style="width: 209px;">Engine version</th><th class="head0 sorting" rowspan="1" colspan="1" style="width: 150px;">CSS grade</th></tr>
        </thead>
        <colgroup>
            <col class="con0">
            <col class="con1">
            <col class="con0">
            <col class="con1">
            <col class="con0">
        </colgroup>



        <tr><th class="head0" rowspan="1" colspan="1">Rendering engine</th><th class="head1" rowspan="1" colspan="1">Browser</th><th class="head0" rowspan="1" colspan="1">Platform(s)</th><th class="head1" rowspan="1" colspan="1">Engine version</th><th class="head0" rowspan="1" colspan="1">CSS grade</th></tr>
        </tfoot>
        <tbody><tr class="gradeA odd">
            <td class=" sorting_1">Gecko</td>
            <td>Firefox 1.0</td>
            <td>Win 98+ / OSX.2+</td>
            <td class="center">1.7</td>

            <td class="center">A</td>
        </tr><tr class="gradeA even">
            <td class=" sorting_1">Gecko</td>
            <td>Firefox 1.5</td>
            <td>Win 98+ / OSX.2+</td>
            <td class="center">1.8</td>

            <td class="center">A</td>
        </tr><tr class="gradeA odd">
            <td class=" sorting_1">Gecko</td>
            <td>Firefox 2.0</td>
            <td>Win 98+ / OSX.2+</td>
            <td class="center">1.8</td>

            <td class="center">A</td>
        </tr><tr class="gradeA even">
            <td class=" sorting_1">Gecko</td>
            <td>Firefox 3.0</td>
            <td>Win 2k+ / OSX.3+</td>
            <td class="center">1.9</td>

            <td class="center">A</td>
        </tr><tr class="gradeA odd">
            <td class=" sorting_1">Gecko</td>
            <td>Camino 1.0</td>
            <td>OSX.2+</td>
            <td class="center">1.8</td>

            <td class="center">A</td>
        </tr><tr class="gradeA even">
            <td class=" sorting_1">Gecko</td>
            <td>Camino 1.5</td>
            <td>OSX.3+</td>
            <td class="center">1.8</td>

            <td class="center">A</td>
        </tr><tr class="gradeA odd">
            <td class=" sorting_1">Gecko</td>
            <td>Netscape 7.2</td>
            <td>Win 95+ / Mac OS 8.6-9.2</td>
            <td class="center">1.7</td>

            <td class="center">A</td>
        </tr><tr class="gradeA even">
            <td class=" sorting_1">Gecko</td>
            <td>Netscape Browser 8</td>
            <td>Win 98SE+</td>
            <td class="center">1.7</td>

            <td class="center">A</td>
        </tr><tr class="gradeA odd">
            <td class=" sorting_1">Gecko</td>
            <td>Netscape Navigator 9</td>
            <td>Win 98+ / OSX.2+</td>
            <td class="center">1.8</td>

            <td class="center">A</td>
        </tr><tr class="gradeA even">
            <td class=" sorting_1">Gecko</td>
            <td>Mozilla 1.0</td>
            <td>Win 95+ / OSX.1+</td>
            <td class="center">1</td>

            <td class="center">A</td>
        </tr></tbody></table><div class="dataTables_info" id="example_info">Showing 1 to 10 of 51 entries</div><div class="dataTables_paginate paging_full_numbers" id="example_paginate"><span class="first paginate_button paginate_button_disabled" id="example_first">First</span><span class="previous paginate_button paginate_button_disabled" id="example_previous">Previous</span><span><span class="paginate_active">1</span><span class="paginate_button">2</span><span class="paginate_button">3</span><span class="paginate_button">4</span><span class="paginate_button">5</span></span><span class="next paginate_button" id="example_next">Next</span><span class="last paginate_button" id="example_last">Last</span></div></div>
    <br>

</body>
</html>
