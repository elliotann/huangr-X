<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${title }</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="image/x-icon" href="${ico}" rel="icon" />
<link type="image/x-icon" href="${ico}" rel="bookmark" />
<script type="text/javascript" src="menu.do"></script>
<script>
var founder= ${user.founder};
</script>

    <script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
    <link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
    <link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${context}/js/index.js"></script>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="${context }/js/ligerui/js/ligerui.all.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerTab.js"></script>
</head>
<body>
	<div id="head">
		<div class="logo"><a href="#"><img src="${bklogo}" style="width:100px"/></a><span class="version">v${version}</span></div>
		<div id="short_msg"  >
			您好${user.username }, <span>您没有新短消息</span>
			 <div class="msglist">
			 	 <ul></ul>
			 </div>
		</div>
		<div class="top">

			<div class="sysmenu">

				<ul><!-- li><a href='javascript:;' id='check_new_btn'>检查更新</a></li-->
					<li><a href='javascript:;' id='cache_btn'></a></li>
				</ul>
			</div>
		</div>
		<div class="appmenu">
			<ul>
			</ul>

			<div class="desktop"><a href="../core/admin/index.do">桌面</a></div>

		</div>
	</div>
	<div id="leftMenus" >
		<dl></dl>

	</div>

    <div id="right_content" >
        <div tabid="home" title="我的主页" style="height:300px" >
            <iframe frameborder="0" name="home" id="home" src="welcome.htm"></iframe>
        </div>
	</div>
</body>
</html>