<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${title }</title>
    <script type="text/javascript" src="menu.do"></script>
    <script>
        var founder= ${user.founder};
    </script>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/main1.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/menu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
    <script src="${context}/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="${context}/js/ligerui/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script src="${context}/js/ligerui/js/plugins/ligerTab.js" type="text/javascript"></script>
    <script src="${context}/js/ligerui/js/plugins/ligerAccordion.js" type="text/javascript"></script>
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
<body>
<input id="context" value="${context}" type="hidden"/>
<div class="header">
    <!-- logo -->
    <a href=""><img alt="Logo" src="images/logo2.png"></a>
    <div class="topheader">
        <ul class="notebutton">
            <li class="note">
                <a class="messagenotify" href="pages/message.html">
                    <span class="wrap">

                        <span class="thicon msgicon"></span>
                        <span class="count">1</span>
                    </span>
                </a>
            </li>
            <li class="note">
                <a class="alertnotify" href="pages/info.html">
                	<span class="wrap">

                    	<span class="thicon infoicon"></span>
                        <span class="count">5</span>
                    </span>
                </a>
            </li>
        </ul>
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
            <h3>${sessionScope.admin_user_key.username}</h3>
            <small>${sessionScope.admin_user_key.email}</small>
            <p>
                <a href="">账号设置</a> <a href="logout.do">退出</a>

            </p>
        </div>
        <!-- info -->
    </div>
    <!-- accountinfo -->
</div>
<!--左侧菜单开始-->
<div class="sidebar" style="height: 100%">
    <div id="accordion">

    </div>
</div>
<%--
<div id="layout1">
    <div position="left" id="leftMenus">

    </div>
    <div position="center" id="right_content">
        <div id="navtab1" style="width: 100%;height:100%;overflow:hidden; border:1px solid #A3C0E8; ">
            <div tabid="home" title="桌面" lselected="true"  style="height: 100%">
                <iframe frameborder="0" name="showmessage" src="../core/admin/index.do?list"></iframe>
            </div>

        </div>
    </div>
</div>
--%>
</body>
</html>

</html>