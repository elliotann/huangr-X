<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>

    <script src="../../js/common/jquery-1.8.3.js"></script>
    <script src="../../js/common/jquery.validate.min.js"></script>
    <script src="../../js/common/jquery.form.js"></script>

    <link rel="stylesheet" href="../../adminthemes/default/css/style.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Aqua/css/ligerui-all.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Gray/css/all.css" />
    <script src="../../adminthemes/default/js/base.js"></script>
    <script src="../../adminthemes/default/js/ligerui/js/plugins/ligerDialog.js"></script>
</head>
<body style="background-color: #EEEEEE;">


<form method="post" id="form" novalidate="novalidate">
    <input type="hidden" name="id" id="userId" value="${adminUser.id}">
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
                <input type="radio" value="INACTIVE" name="status" <c:if test="${adminUser.status eq 'INACTIVE'}">CHECKED</c:if>> 禁用 &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="ACTIVE" name="status" <c:if test="${adminUser.status ne 'INACTIVE'}">CHECKED</c:if>> 激活
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
                username: {
                    required:true,
                    minlength:3,
                    maxlength:18,
                    remote:{
                        type:"post",
                        url:"validateUsername.do",
                        dataType:'json',
                        data:{
                            username:function(){return $("#username").val();},
                            id:function(){return $("#userId").val();}
                        }
                    }
                },
                password: {
                    required: true
                },
                email: {
                    required: true,
                    email:true,
                    remote:{
                        type:"post",
                        url:"validateEmail.do",
                        dataType:'json',
                        data:{
                            username:function(){return $("#email").val();},
                            id:function(){return $("#userId").val();}
                        }
                    }
                }
            },
            messages: {
                username: {
                    required:"用户名不能为空!",
                    remote:"此用户名已经存在!",
                    minlength:"用户名最少3位!",
                    maxlength:"用户名最长18位!"
                },
                password: "请输入1~18位密码",
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
