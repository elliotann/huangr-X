<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${title }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="image/x-icon" href="${ico}" rel="icon" />
<link type="image/x-icon" href="${ico}" rel="bookmark" />
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
<SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="${staticserver }/js/admin/EADP.SSO.js"></script>
<link href="${context}/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">

        <div class="Main">
            <ul>
                <li class="top"></li>
                <li class="top2"></li>
                <li class="topA"></li>
                <li class="topB"><span>
                <img src="${staticserver }/images/default/logo.gif" alt="" class="logo" />
            </span></li>
                <li class="topC"></li>
                <li class="topD">
                    <ul class="login">
                        <li><span class="left">用户名：</span> <span style="left">
                            <input type="text" id="username" name="username" class="txt" value="${username}"/>

                    </span></li>
                        <li><span class="left">密 码：</span> <span style="left">
                            <input type="password" name="password" class="txt"  value="${password}"/>

                    </span></li>
                        <li><span class="left">验证码：</span> <span style="left">
                            <input type="input" id="valid_code" name="valid_code" class="txtCode" /> <img id="code_img" class="code_img" />
                    </span>



                        </li>

                        <li>
                            <span class="left">记住我：</span>
                            <input type="checkbox" value="1" checked="" name="remember_login_name">



                        </li>

                    </ul>
                </li>
                <li class="topE"></li>
                <li class="middle_A"></li>
                <li class="middle_B"></li>
                <li class="middle_C">
            <span class="btn">

                  <input type="button" name="login_btn" id="login_btn" value="登录后台" class="loginbtnfocus" />


            </span>
                </li>
                <li class="middle_D"></li>
                <li class="bottom_A"></li>
                <li class="bottom_B">
                    jeap企业级开发平台
                </li>
            </ul>
        </div>
    </form>
<script>
$(function(){
	var bkloginpicfile = '${bkloginpicfile}';
	if(bkloginpicfile!=''){
		$(".logo").css("src","${bkloginpicfile}");
	}
	if($("#username").val()){$("#psdinput").focus();}
});
</script>
</body>
</html>
