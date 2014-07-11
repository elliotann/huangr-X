<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/7/8
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>

    <script src="../../js/common/jquery-1.8.3.js"></script>
    <script src="../../js/common/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="../../adminthemes/default/css/style.css"/>
</head>
<body style="background-color: #EEEEEE;">


<form method="post" action="save.do" id="form" novalidate="novalidate">

    <div class="form_default">
        <fieldset>
            <legend>增加管理员</legend>

            <p>
                <label for="username">用户名</label>

                <input type="text" class="sf" id="username" name="username">
            </p>
            <p>
                <label for="password">密码</label>

                <input type="password" class="sf" id="password" name="password">
            </p>
            <p>
                <label for="realName">真实姓名</label>
                <input type="text" class="sf" id="realName" name="realName">
            </p>

            <p>
                <label for="email">邮箱</label>
                <input type="text" class="sf" id="email" name="email">
            </p>

            <p>
                <label class="nopadding" for="status">状态</label>
                <input type="radio" value="INACTIVE" name="status"> 禁用 &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="ACTIVE" name="status" CHECKED> 激活
            </p>
            <p>
                <button onclick="submitForm()">Submit</button>

            </p>

        </fieldset>
    </div><!--form-->



</form>
<script>
    $(function(){
        $("#form").validate({
            rules: {
                name: "required",
                password: {
                    required: true
                },
                email: {
                    required: true
                }
            },
            messages: {
                name: "请输入用户名",
                password: "请输入密码"
            }
        });
    });

    function submitForm(){

        $("#form").submit();
    }
</script>

</body>
</html>
