<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${title }</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <script type="text/javascript" src="menu.do"></script>
    <script>
        var founder= ${user.founder};
    </script>

    <link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>




    <script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
    <script type="text/javascript" src="${context}/js/index.js"></script>


    <style type="text/css">

        body{ padding:0px; margin:0;}

        .l-page-top{ height:80px; background:#f8f8f8; margin-bottom:3px;}
        h4{ margin:20px;}
    </style>
    <style type="text/css">
        /* 菜单列表 */
        .menulist { margin-left: 2px; margin-right: 2px; margin-top: 2px; text-align: left; color: #000; }
        .menulist li { height: 24px; line-height: 24px; padding-left: 24px; display: block; position: relative; cursor: pointer; text-align: left; }
        .menulist li img { position: absolute; left: 4px; top: 4px; width: 16px; height: 16px; }
        .menulist li.over, .menulist li.selected { background: url('${context}/images/menubg.gif') repeat-x 0px 0px; }
        .menulist li.over .menuitem-l, .menulist li.selected .menuitem-l { background: url('${context}/images/menubg.gif') repeat-x 0px -24px; width: 2px; height: 24px; position: absolute; left: 0; top: 0; }
        .menulist li.over .menuitem-r, .menulist li.selected .menuitem-r { background: url('${context}/images/menubg.gif') repeat-x -1px -24px; width: 2px; height: 24px; position: absolute; right: 0; top: 0; }
        #portrait { border-radius: 4px; box-shadow: 1px 1px 1px #111; position: absolute; width: 48px; height: 48px; right: 7px; top: 10px; background: #d2d2f2 /*url(images/icons/32X32/user.gif) no-repeat center center*/; border: 3px solid #fff; behavior: url(css/pie.htc); text-align: center; }

    </style>
</head>
<body class="bodygrey">
<input id="context" value="${context}" type="hidden"/>
<div class="header" id="top">
    <!-- logo -->
    <a href=""><img alt="Logo" src="images/logo2.png"></a>
    <div class="topheader">

    </div>
    <!-- topheader -->



    <!-- tabmenu start-->
    <div class="tabmenu">
        <ul>

        </ul>

    </div>
    <!-- tabmenu end-->

    <div class="accountinfo">
        <img alt="Avatar" src="images/avatar.png">

        <div class="info">
            <h3>${user.username }</h3>
            <small>360042212@qq.com</small>
            <p>
                <a href="">账号设置</a> <a href="../admin/logout.do" target="_self">退出</a>

            </p>
        </div>
        <!-- info -->
    </div>
    <!-- accountinfo -->
</div>
<div class="sidebar" style="height: 100%">
    <div id="accordion">

    </div>
</div>
<!--左侧菜单结束-->
<div class="maincontent">
    <%--<div class="breadcrumbs" id="nav">
        <a href="dashboard.html">系统管理</a>
        <a href="dashboard.html">权限管理</a>
        <span>管理员管理</span>
    </div>--%>
    <!-- breadcrumbs -->
    <div class="left" id="right_content" >
        <iframe src="../core/admin/index.do?list" scrolling="no" id="iframe" name="iframe" style="width: 100%;height: 800px"></iframe>
    </div>
</div>


</body>
</html>

</html>