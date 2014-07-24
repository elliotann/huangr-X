<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>

    <script src="../../js/common/jquery-1.8.3.js"></script>
    <script src="../../js/common/jquery.validate.min.js"></script>
    <script src="../../js/common/jquery.form.js"></script>

    <link rel="stylesheet" href="../../adminthemes/default/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/style1.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/blue.css"/>
    <link rel="stylesheet" href="../../adminthemes/default/css/form.css"/>

    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>

    <script src="/jeap/adminthemes/default/js/mylib/bootstrap.min.js"></script>
   <%-- <script src="/jeap/adminthemes/blue/js/gebo_validation.js"></script>--%>

</head>
<body style="background-color: #EEEEEE;">
<div class="row">

    <div class="col-sm-12 col-md-12">
        <h3 class="heading">增加管理员</h3>
        <form class="form_validation_reg" novalidate="novalidate" id="form">
            <div class="formSep">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <label>用户名 <span class="f_req">*</span></label>
                        <input type="text" class="form-control" name="reg_first_name" style="width:200px">
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <label>密码 <span class="f_req">*</span></label>
                        <input type="password" class="form-control" name="reg_last_name"  style="width:200px">
                    </div>
                </div>
            </div>
            <div class="formSep">
                <div class="row">
                    <div class="col-sm-6 col-md-6">
                        <label>City <span class="f_req">*</span></label>
                        <input type="text" class="form-control" name="reg_city">
                    </div>
                    <div class="col-sm-6 col-md-6">
                        <label>Zip Code</label>
                        <input type="text" class="form-control" name="reg_zip">
                    </div>
                </div>
            </div>
            <div class="formSep">
                <label>Your message <span class="f_req">*</span></label>
                <textarea class="form-control" rows="3" cols="10" id="reg_your_message" name="reg_your_message"></textarea>
            </div>
            <div class="formSep">
                <label><span class="error_placement">Checkboxes</span> <span class="f_req">*</span></label>
                <br>
                <label class="checkbox-inline">
                    <input type="checkbox" name="reg_days" value="option1"> Monday
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="reg_days" value="option2"> Tuesday
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="reg_days" value="option3"> Wednesday
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="reg_days" value="option4"> Thursday
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="reg_days" value="option5"> Friday
                </label>
            </div>
            <div class="formSep">
                <label><span class="error_placement">Gender</span> <span class="f_req">*</span></label>
                <br>
                <label class="radio-inline">
                    <input type="radio" name="reg_gender" value="option6">
                    Male
                </label>
                <label class="radio-inline">
                    <input type="radio" name="reg_gender" value="option7">
                    Female
                </label>
            </div>
        </form></div>
    <div class="form-actions">
        <button type="button" class="btn btn-default" onclick="submitForm()">Save changes</button>
        <button class="btn btn-default">Cancel</button>
    </div>

</div>

<%--<form method="post" id="form" novalidate="novalidate">
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



</form>--%>
<script>
    $(function(){
         $('.form_validation_reg').validate({
            onkeyup: false,
            errorClass: 'error',
            validClass: 'valid',
            highlight: function(element) {
                $(element).closest('div').addClass("f_error");
            },
            unhighlight: function(element) {
                $(element).closest('div').removeClass("f_error");
            },
            errorPlacement: function(error, element) {
                $(element).closest('div').append(error);
            },
            rules: {
                reg_first_name: { required: true, minlength: 3 },
                reg_last_name: { required: true, minlength: 3 },
                reg_your_message: { required: true, minlength: 20 },
                reg_days: { required: true, minlength: 2 },
                reg_gender: { required: true },
                reg_address2: { required: true, minlength: 5 },
                reg_city: { required: true, minlength: 2 },
                reg_state: { required: true, minlength: 3 }
            },
            invalidHandler: function(form, validator) {
               /* $.sticky("There are some errors. Please corect them and submit again.", {autoclose : 5000, position: "top-right", type: "st-error" });*/
            }
        })
       /* $("#form").validate({
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
        */
    });

    function submitForm(){
        $("#form").submit();
    }
</script>

</body>
</html>
