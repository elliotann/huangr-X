<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>jeap企业级开发平台</title>
    <script type="text/javascript" src="menu/getMenuJson.do" charset="UTF-8"></script>
    <link rel="stylesheet" href="../adminthemes/default/css/style.css"/>
    <script src="../js/common/jquery-1.8.3.js"></script>
    <script src="../adminthemes/default/js/index.js" type="text/javascript"></script>

</head>

<body class="bodygrey">
<div style="display: none;" id="cboxOverlay"></div>

<div class="headerspace"></div>
<div class="header">
    <form method="post" action="" id="search">
        <input type="text" name="keyword">
        <button class="searchbutton"></button>
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
    </div>
    <!-- topheader -->


    <!-- logo -->
    <a href=""><img alt="Logo" src="images/logo2.png"></a>
    <!-- tabmenu start-->
    <div class="tabmenu">
        <ul>

        </ul>

    </div>
    <!-- tabmenu end-->

    <div class="accountinfo">
        <img alt="Avatar" src="images/avatar.png">

        <div class="info">
            <h3>Admin</h3>
            <small>360042212@qq.com</small>
            <p>
                <a href="">账号设置</a> <a href="index.html">退出</a>

            </p>
        </div>
        <!-- info -->
    </div>
    <!-- accountinfo -->
</div>

<!--左侧菜单开始-->
<div class="sidebar">
    <div id="accordion">

    </div>
</div>

<!--左侧菜单结束-->
<div class="maincontent">
    <div class="breadcrumbs">
        <a href="dashboard.html">系统管理</a>
        <a href="dashboard.html">权限管理</a>
        <span>管理员管理</span>
    </div>
    <!-- breadcrumbs -->
    <div class="left">
        <iframe src="../adminthemes/default/welcome.jsp" scrolling="no" id="iframepage" onload="iFrameHeight()"
                name="iframepage"></iframe>
    </div>
    <!-- left -->


    <br clear="all">

</div>
<!--maincontent-->

<br>
<script type="text/javascript">
    function iFrameHeight() {
        var ifm = document.getElementById("iframepage");
        var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
        if (ifm != null && subWeb != null) {
            ifm.height = subWeb.body.scrollHeight;
        }
    }
</script>


</body>
</html>
