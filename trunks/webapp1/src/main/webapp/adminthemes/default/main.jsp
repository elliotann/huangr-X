<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
    <link rel="stylesheet" href="../adminthemes/default/css/style.css"/>

    <script src="../adminthemes/default/js/jquery-1.8.3.js"></script>
    <script src="../adminthemes/default/js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
    <script src="../adminthemes/default/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="../adminthemes/default/js/jquery.colorbox-min.js" type="text/javascript"></script>
    <script src="../adminthemes/default/js/jquery.flot.min.js" type="text/javascript"></script>
    <script src="../adminthemes/default/js/general.js" type="text/javascript"></script>
    <script src="../adminthemes/default/js/dashboard.js" type="text/javascript"></script>


</head>

<body  class="bodygrey">
<div style="display: none;" id="cboxOverlay"></div>
<div id="colorbox" class="" style="padding-bottom: 20px; padding-right: 0px; display: none;">
    <div id="cboxWrapper">
        <div>
            <div id="cboxTopLeft" style="float: left;"></div>
            <div id="cboxTopCenter" style="float: left;"></div>
            <div id="cboxTopRight" style="float: left;"></div>
        </div>
        <div style="clear: left;">
            <div id="cboxMiddleLeft" style="float: left;"></div>
            <div id="cboxContent" style="float: left;">
                <div id="cboxLoadedContent" style="width: 0px; height: 0px; overflow: hidden; float: left;"></div>
                <div id="cboxLoadingOverlay" style="float: left;"></div>
                <div id="cboxLoadingGraphic" style="float: left;"></div>
                <div id="cboxTitle" style="float: left;"></div>
                <div id="cboxCurrent" style="float: left;"></div>
                <div id="cboxNext" style="float: left;"></div>
                <div id="cboxPrevious" style="float: left;"></div>
                <div id="cboxSlideshow" style="float: left;"></div>
                <div id="cboxClose" style="float: left;"></div>
            </div><div id="cboxMiddleRight" style="float: left;"></div>
        </div>
    </div>
    <div style="clear: left;">
        <div id="cboxBottomLeft" style="float: left;"></div>
        <div id="cboxBottomCenter" style="float: left;"></div>
        <div id="cboxBottomRight" style="float: left;"></div>
    </div>
</div>
<div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div>
</div>
<div class="headerspace"></div>
<div class="header">
    <form method="post" action="" id="search">
        <input type="text" name="keyword"> <button class="searchbutton"></button>
    </form>
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
    </div><!-- topheader -->


    <!-- logo -->
    <a href=""><img alt="Logo" src="images/logo2.png"></a>

    <div class="tabmenu">
        <ul>
            <li class="current"><a class="dashboard" href="dashboard.html"><span>系统管理</span></a></li>
            <li><a class="elements" href="elements.html"><span>系统管理</span></a>
                <ul class="subnav" style="visibility: hidden; display: block;">
                    <li><a href=""><span>Sub Menu One</span></a></li>

                    <li><a href=""><span>Sub Menu Two</span></a></li>
                    <li><a href=""><span>Sub Menu Three</span></a></li>
                </ul>
            </li>
            <li><a class="reports" href="reports.html"><span>报表统计</span></a></li>
            <li><a class="users" href="users.html"><span>开发者</span></a></li>
        </ul>

    </div><!-- tabmenu -->

    <div class="accountinfo">
        <img alt="Avatar" src="images/avatar.png">
        <div class="info">
            <h3>Admin</h3>
            <small>360042212@qq.com</small>
            <p>
                <a href="">账号设置</a> <a href="index.html">退出</a>

            </p>
        </div><!-- info -->
    </div><!-- accountinfo -->
</div>
<div class="sidebar">
    <div id="accordion">
        <h3 class="open">权限管理</h3>
        <div style="display: block;" class="content">

            <ul class="leftmenu">
                <li class="current"><a class="home" href="adminuser/list.do" target="iframepage">管理员管理</a></li>
                <li><a class="form" href="forms.html">角色管理</a></li>

            </ul>
        </div>
        <h3 class="open"></h3>

    </div>

</div>
<div class="maincontent">
    <div class="breadcrumbs">
        <a href="dashboard.html">系统管理</a>
        <a href="dashboard.html">权限管理</a>
        <span>管理员管理</span>
    </div><!-- breadcrumbs -->
    <div class="left">
        <iframe src="../adminthemes/default/user.jsp" scrolling="no" id="iframepage" onload="iFrameHeight()" name="iframepage"></iframe>
    </div><!-- left -->



    <br clear="all">

</div><!--maincontent-->

<br>
<script type="text/javascript">
    function iFrameHeight(){
        var ifm= document.getElementById("iframepage");
        var subWeb = document.frames ? document.frames["iframepage"].document:ifm.contentDocument;
        if(ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
        }
    }
</script>


</body>
</html>
