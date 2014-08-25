<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="login_page" lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员登录</title>

    <!-- Bootstrap framework -->
    <link rel="stylesheet" href="/jeap/install/css/bootstrap.min.css">
    <!-- theme color-->
    <link rel="stylesheet" href="/jeap/install/css/blue.css">

    <!-- main styles -->
    <link rel="stylesheet" href="/jeap/install/css/style.css">
    <link rel="stylesheet" href="/jeap/adminthemes/default/css/login/login.css">
    <link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />

    <!-- favicon -->
    <link rel="shortcut icon" href="favicon.ico">
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
    <script type="text/javascript" src="${staticserver }/js/admin/sso.js"></script>
    <SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
    <!--[if lt IE 9]>
    <script src="js/ie/html5.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div style="margin-top: -187.5px;" class="login_box">

    <form novalidate="novalidate" method="post" id="login_form">
        <div class="top_b">后台登录</div>
        <div class="alert alert-info alert-login">
            请输入正确的用户名和密码!
        </div>
        <div class="cnt_b">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-user"></i></span>
                    <input class="form-control input-sm" id="username" name="username" placeholder="用户名"
                           value="" type="text">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control input-sm" id="password" name="password" placeholder="密码"
                           value="" type="password">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control input-sm" id="valid_code" name="valid_code" placeholder="验证码"
                           value="" type="text" style="width:150px"><img id="code_img" class="code_img" />
                </div>
            </div>
            <div class="form-group">
                <label class="checkbox-inline"><input type="checkbox"> 记住我</label>
            </div>
        </div>
        <div class="btm_b clearfix">
            <button class="btn btn-default btn-sm pull-right" type="button" id="login_btn">登录</button>

        </div>
    </form>

</div>



<script src="${staticserver}/js/common/jquery.validate.js" type="text/javascript"></script>

<script>

</script>




</body>
</html>