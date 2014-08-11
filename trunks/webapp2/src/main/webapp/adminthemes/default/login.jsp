<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="shortcut icon" href="resources/fc/images/icon/favicon.ico">
    <!--[if lt IE 9]>

    <![endif]-->
    <!--[if lt IE 7]>
    <script src="plug-in/login/js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
    </script>
    <![endif]-->
    <link href="plug-in/login/css/zice.style.css" rel="stylesheet" type="text/css" />
    <link href="plug-in/login/css/buttons.css" rel="stylesheet" type="text/css" />
    <link href="plug-in/login/css/icon.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="plug-in/login/css/tipsy.css" media="all" />
    <link href="${context}/css/login.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        html {
            background-image: none;
        }

        label.iPhoneCheckLabelOn span {
            padding-left: 0px
        }

        #versionBar {
            background-color: #212121;
            position: fixed;
            width: 100%;
            height: 35px;
            bottom: 0;
            left: 0;
            text-align: center;
            line-height: 35px;
            z-index: 11;
            -webkit-box-shadow: black 0px 10px 10px -10px inset;
            -moz-box-shadow: black 0px 10px 10px -10px inset;
            box-shadow: black 0px 10px 10px -10px inset;
        }

        .copyright {
            text-align: center;
            font-size: 10px;
            color: #CCC;
        }

        .copyright a {
            color: #A31F1A;
            text-decoration: none
        }

        .on_off_checkbox {
            width: 0px;
        }

        #login .logo {
            width: 500px;
            height: 51px;
        }
    </style>
</head>
<body>
<div id="alertMessage"></div>
<div id="successLogin"></div>
<div class="text_success"><img src="plug-in/login/images/loader_green.gif" alt="Please wait" /> <span>登陆成功!请稍后....</span></div>
<div id="login">
    <div class="ribbon" style="background-image: url(plug-in/login/images/typelogin.png);"></div>
    <div class="inner">
        <div class="logo"></div>
        <div class="formLogin">
            <form name="formLogin" id="formLogin" action="login.do" check="loginController.do?checkuser" method="post">
                <div class="tip"><input class="userName" name="username" type="text" id="userName" title="用户名" iscookie="true" value="${username}" nullmsg="请输入用户名!" /></div>
                <div class="tip"><input class="password" name="password" type="password" id="password" title="密码" value="${password}" nullmsg="请输入密码!" /></div>
                <div class="tip"><input type="text" id="valid_code" name="valid_code" class="txtCode" nullmsg="请输入验证码!"/> <img id="code_img" class="code_img" /></div>
                <div class="loginButton">
                    <div style="float: left; margin-left: -9px;"><input type="checkbox" id="on_off" name="remember_login_name" checked="ture" class="on_off_checkbox" value="1" /> <span class="f_help">是否记住用户名 ?</span></div>

                    <div style="float: right; padding: 3px 0; margin-right: -12px;">
                        <div>
                            <ul class="uibutton-group">
                                <li><a class="uibutton normal" href="#" id="login_btn">登陆</a></li>
                                <li><a class="uibutton normal" href="#" id="forgetpass">重置</a></li>
                            </ul>
                        </div>
                        <div style="float: left; margin-left: 30px;"><a href="init.jsp"><span class="f_help">是否初始化数据</span></a></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="shadow"></div>
</div>
<!--Login div-->
<div class="clear"></div>
<div id="versionBar">
    <div class="copyright">&copy; 版权所有 <span class="tip"><a href="#" title="jeap开发平台">jeap</a> (推荐使用IE8+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="#" title="JEAP开发平台">jeap</a></span></div>
</div>
<!-- Link JScript-->
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery-jrumble.js"></script>
<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
<script type="text/javascript" src="plug-in/login/js/login.js"></script>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/sso.js"></script>
<SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
</body>
</html>