<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
    <title>小黄人管理系统 Beta 1.0</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/adminthemes/default/css/login/login.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/adminthemes/default/css/form.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/adminthemes/default/css/login/tip.css" />


</head>

<body>
<div class="main">
    <div class="header hide"> 小黄人管理系统 Beta 1.0 </div>
    <div class="content">
        <div class="title hide">管理登录</div>

        <form name="login" action="#" method="post">
            <div class="alert alert-info alert-login" id="tipMsg">
                请输入正确的用户名和密码!
            </div>

                <div class="input">
                    用户名：<input class="form-control" name="username" id="username" placeholder="用户名" type="text"  maxLength="24" />
                </div>
                <div class="input">
                    &nbsp;&nbsp;密码：<input class="form-control" name="password" id="password" type="password" placeholder="密码"  maxLength="24" />
                </div>
                <div class="checkbox">
                    <input type="checkbox" name="remember" id="remember" /><label for="remember"><span>记住我</span></label>
                </div>
                <div class="enter">
                    <button class="button hide" name="submit" type="button" id="login_btn">登录</button>
                </div>

        </form>
    </div>
</div>
<div class="clear"></div>
<div id="versionBar">
    <div class="copyright">&copy; 版权所有 <span class="tip"><a href="#" title="jeap开发平台">jeap</a> (推荐使用IE9+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="#" title="JEAP开发平台">jeap</a></span></div>
</div>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/sso.js"></script>
<script type="text/javascript" src="${ctx }/adminthemes/default/js/login/placeholder.js"></script>
<SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
<!--[if IE 6]>
<script type="text/javascript" src="js/belatedpng.js" ></script>
<script type="text/javascript">
    DD_belatedPNG.fix("*");
</script>
<![endif]--></body>
</html>
