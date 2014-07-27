<%--
  Created by IntelliJ IDEA.
  User: huangxa
  Date: 2014/7/22
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="login_box" style="margin-top: -187.5px;">

    <form id="login_form" method="post" action="index.php?uid=1&amp;page=dashboard" novalidate="novalidate">
        <div class="top_b">Sign in to Gebo Admin</div>
        <div class="alert alert-info alert-login">
            Clear username and password field to see validation.
        </div>
        <div class="cnt_b">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text" value="John Smith" placeholder="Username" name="username" id="username" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" value="password" placeholder="Password" name="password" id="password" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <label class="checkbox-inline"><input type="checkbox"> Remember me</label>
            </div>
        </div>
        <div class="btm_b clearfix">
            <button type="submit" class="btn btn-default btn-sm pull-right">Sign In</button>
            <span class="link_reg"><a href="#reg_form">Not registered? Sign up here</a></span>
        </div>
    </form>

    <form style="display:none" id="pass_form" method="post" action="index.php?uid=1&amp;page=dashboard">
        <div class="top_b">Can't sign in?</div>
        <div class="alert alert-info alert-login">
            Please enter your email address. You will receive a link to create a new password via email.
        </div>
        <div class="cnt_b">
            <div class="formRow clearfix">
                <div class="input-group">
                    <span class="input-group-addon input-sm">@</span>
                    <input type="text" class="form-control input-sm" placeholder="Your email address">
                </div>
            </div>
        </div>
        <div class="btm_b tac">
            <button type="submit" class="btn btn-default">Request New Password</button>
        </div>
    </form>

    <form style="display:none" id="reg_form" method="post" action="index.php?uid=1&amp;page=dashboard">
        <div class="top_b">Sign up to Gebo Admin</div>
        <div class="alert alert-warning alert-login">
            By filling in the form bellow and clicking the "Sign Up" button, you accept and agree to <a href="#terms" data-toggle="modal">Terms of Service</a>.
        </div>
        <div class="modal fade" id="terms">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h3 class="modal-title">Terms and Conditions</h3>
                    </div>
                    <div class="modal-body">
                        <p>
                            Nulla sollicitudin pulvinar enim, vitae mattis velit venenatis vel. Nullam dapibus est quis lacus tristique consectetur. Morbi posuere vestibulum neque, quis dictum odio facilisis placerat. Sed vel diam ultricies tortor egestas vulputate. Aliquam lobortis felis at ligula elementum volutpat. Ut accumsan sollicitudin neque vitae bibendum. Suspendisse id ullamcorper tellus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vestibulum at augue lorem, at sagittis dolor. Curabitur lobortis justo ut urna gravida scelerisque. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam vitae ligula elit.
                            Pellentesque tincidunt mollis erat ac iaculis. Morbi odio quam, suscipit at sagittis eget, commodo ut justo. Vestibulum auctor nibh id diam placerat dapibus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Suspendisse vel nunc sed tellus rhoncus consectetur nec quis nunc. Donec ultricies aliquam turpis in rhoncus. Maecenas convallis lorem ut nisl posuere tristique. Suspendisse auctor nibh in velit hendrerit rhoncus. Fusce at libero velit. Integer eleifend sem a orci blandit id condimentum ipsum vehicula. Quisque vehicula erat non diam pellentesque sed volutpat purus congue. Duis feugiat, nisl in scelerisque congue, odio ipsum cursus erat, sit amet blandit risus enim quis ante. Pellentesque sollicitudin consectetur risus, sed rutrum ipsum vulputate id. Sed sed blandit sem. Integer eleifend pretium metus, id mattis lorem tincidunt vitae. Donec aliquam lorem eu odio facilisis eu tempus augue volutpat.
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default btn-sm" type="button">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="cnt_b">

            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text" value="" placeholder="Username" name="r_username" id="r_username" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" value="" placeholder="Password" name="r_password" id="r_password" class="form-control input-sm">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon input-sm">@</span>
                    <input type="text" value="" placeholder="Your email address" name="r_email" id="r_email" class="form-control input-sm">
                </div>
                <span class="help-block">The e-mail address is not made public and will only be used if you wish to receive a new password.</span>
            </div>
        </div>
        <div class="btm_b tac">
            <button type="submit" class="btn btn-default">Sign Up</button>
        </div>
    </form>

    <div class="links_b links_btm clearfix">
        <span class="linkform"><a href="#pass_form">Forgot password?</a></span>
        <span style="display:none" class="linkform">Never mind, <a href="#login_form">send me back to the sign-in screen</a></span>
    </div>

</div>
</body>
</html>