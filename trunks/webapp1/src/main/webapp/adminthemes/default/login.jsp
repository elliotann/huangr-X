<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="login_page">
<head>
    <title></title>
    <link rel="stylesheet" href="../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../adminthemes/default/css/form.css"/>


</head>
<body>
<div class="login_box" style="margin-top: -187.5px;">

    <form id="login_form" method="post" action="/jeap/admin/login.do" novalidate="novalidate">
        <div class="top_b">管理员登录</div>
        <div class="alert alert-info alert-login">
            用户名或者密码错误
        </div>
        <div class="cnt_b">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text" value="" placeholder="请输入用户名" name="username" id="username" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" value="" placeholder="请输入密码" name="password" id="password" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <label class="checkbox-inline"><input type="checkbox"> 记住我</label>
            </div>
        </div>
        <div class="btm_b clearfix">
            <button type="submit" class="btn btn-default btn-sm pull-right">登录</button>
            <span class="link_reg"><a href="#reg_form"></a></span>
        </div>
    </form>
</div>
</body>
</html>
