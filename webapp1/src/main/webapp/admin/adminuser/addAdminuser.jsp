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
    <script src="../../js/common/jquery.form.js"></script>
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Aqua/css/ligerui-all.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Gray/css/all.css" />
    <script src="../../adminthemes/default/js/base.js"></script>
    <script src="../../adminthemes/default/js/ligerui/js/plugins/ligerDialog.js"></script>
    <link rel="stylesheet" href="../../adminthemes/default/css/style.css" />
</head>
<body style="background-color: #EEEEEE;">


<form method="post" id="form" novalidate="novalidate">
    <input type="hidden" name="id" value="${adminUser.id}">
    <div class="form_default">
        <fieldset>
            <legend>增加管理员</legend>

            <p>
                <label for="username">用户名</label>

                <input type="text" class="sf" id="username" name="username" value="${adminUser.username}">
            </p>
            <p>
                <label for="password">密码</label>

                <input type="password" class="sf" id="password" name="password" value="${adminUser.password}">
            </p>
            <p>
                <label for="realName">真实姓名</label>
                <input type="text" class="sf" id="realName" name="realName" value="${adminUser.realName}">
            </p>

            <p>
                <label for="email">邮箱</label>
                <input type="text" class="sf" id="email" name="email"  value="${adminUser.email}">
            </p>

            <p>
                <label class="nopadding" for="status">状态</label>
                <input type="radio" value="INACTIVE" name="status"> 禁用 &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="ACTIVE" name="status" CHECKED> 激活
            </p>
            <p>
                <button onclick="submitForm()">提交</button>

            </p>

        </fieldset>
    </div><!--form-->



</form>
<script>
    $(function(){
        $("#form").validate({
            rules: {
                username: "required",
                password: {
                    required: true
                },
                email: {
                    required: true,
                    email:true
                }
            },
            messages: {
                username: "请输入用户名",
                password: "请输入密码",
                email:"请输入正确格式的邮箱"
            },
            submitHandler:function(){
                $("#form").ajaxSubmit({
                    url :"save.do",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            $.ligerDialog.waitting('增加成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                location = "list.do";
                            }, 1000);

                        }else{
                            alert(result.msg)
                        }
                    },
                    error : function(e) {
                        alert("出错啦:(");
                    }
                });
            }
        });
    });

    function submitForm(){
        $("#form").submit();
    }
</script>

</body>
</html>
