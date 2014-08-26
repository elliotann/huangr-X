<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>小黄人企业管理微平台系统登陆</title>
    <link rel="stylesheet" type="text/css" href="/jeap/adminthemes/default/css/login/login.css" />
    <link rel="stylesheet" type="text/css" href="/jeap/adminthemes/default/css/form.css" />
    <link rel="stylesheet" type="text/css" href="/jeap/adminthemes/default/css/login/tip.css" />
    <style type="text/css">
        .download{margin:20px 33px 10px;*margin-bottom:30px;padding:5px;border-radius:3px;-webkit-border-radius:3px;-moz-border-radius:3px;background:#e6e6e6;border:1px dashed #df0031;font-size:14px;font-family:Comic Sans MS;font-weight:bolder;color:#555}
        .download a{padding-left:5px;font-size:14px;font-weight:normal;color:#555;text-decoration:none;letter-spacing:1px}
        .download a:hover{text-decoration:underline;color:#36F}
        .download span{float:right}
    </style>
    <!--[if IE]>
    <style type="text/css"> img, #testdiv, .testbox{behavior: url(http://yourdomain.com/js/ie-css3.htc);} </style>
    <![endif]-->

</head>

<body>
<div class="main">
    <div class="header hide"> 大屏管理系统 Beta 1.0 </div>
    <div class="content">
        <div class="title hide">管理登录</div>

        <form name="login" action="#" method="post">
            <div class="alert alert-info alert-login" id="tipMsg">
                请输入正确的用户名和密码!
            </div>
            <fieldset>

                <div class="input">
                    用户名：<input class="form-control" name="username" id="username" placeholder="用户名" type="text"  maxLength="24" />
                </div>
                <div class="input">
                    密&nbsp;&nbsp;码：<input class="form-control" name="password" id="password" type="password" placeholder="密码"  maxLength="24" />
                </div>
                <div class="input">
                    验证码：<input class="form-control" style="width: 50px;" name="valid_code" id="valid_code" type="text" placeholder="验证码"  maxLength="24" /><img id="code_img" class="code_img" />
                </div>
                <div class="checkbox">
                    <input type="checkbox" name="remember" id="remember" /><label for="remember"><span>记住我</span></label>
                </div>
                <div class="enter">
                    <button class="button hide" name="submit" type="button" id="login_btn">登录</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<div class="clear"></div>
<div id="versionBar">
    <div class="copyright">&copy; 版权所有 <span class="tip"><a href="#" title="jeap开发平台">jeap</a> (推荐使用IE9+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="#" title="JEAP开发平台">jeap</a></span></div>
</div>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/sso.js"></script>
<script type="text/javascript" src="/jeap/adminthemes/default/js/login/placeholder.js"></script>
<SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
<!--[if IE 6]>
<script type="text/javascript" src="js/belatedpng.js" ></script>
<script type="text/javascript">
    DD_belatedPNG.fix("*");
</script>
<![endif]--></body>
</html>
