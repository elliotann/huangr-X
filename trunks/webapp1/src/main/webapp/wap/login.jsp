<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<header class="inside-header">
    <a data-bind="click: app.goBack" href="javascript:;" class="back">返回</a>
    <h1>用户登录</h1>
    <a class="home-r" href="javascript:;" data-bind="click: app.goHome">首页</a>
    <a href="tel_3A8888-888-8888" class="call" id="call">8888-888-8888</a>
</header>
<!--登陆主体-->
<section class="login-box">
    <form action="/Account/Login?returnUrl=%2FMember" data-bind="submit:onSubmit" method="post">        <ul class="login-con">
        <li>
            <input id="UserName" name="UserName" placeholder="手机号/卡号/邮箱" type="text" value="" />

        </li>
        <li>
            <input id="Password" name="Password" placeholder="密码（6-20位字符）" type="password" />

        </li>
    </ul>
        <div class="btn-wrap">
            <button type="submit" class="login-btn">登录</button>
            <div class="or">如需帮助</div>
            <a href="tel_3A400-678-6633" class="tel-btn">请致电：400-678-6633</a>
        </div>
    </form>    <aside class="reg clearfix">
    <a href="Register.html" class="f-l">免费注册</a>

</aside>
    <aside class="integral">注册即可获得<span>500</span>积分</aside>
</section>

<script>
    app.setViewModel(AccountLoginViewModel);
</script>
</body>
</html>
