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
    <link rel="stylesheet" href="/jeap/adminthemes/blue/lib/sticky/sticky.css"/>
    <script src="/jeap/adminthemes/blue/lib/sticky/sticky.js"></script>
   <%-- <script src="/jeap/adminthemes/blue/js/gebo_validation.js"></script>--%>

</head>
<body style="background-color: #EEEEEE;">
<div class="row">
    <div class="col-sm-6 col-md-6">
        <p class="sepH_c">增加管理员</p>
        <form role="form" class="form-horizontal"  id="form">
            <input type="hidden" name="id" id="userId" value="${adminUser.id}">
            <div class="form-group">
                <label class="col-lg-2 control-label" for="username">用户名 <span class="f_req">*</span></label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" name="username" id="username" style="width:200px" value="${adminUser.username}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="password">密码 <span class="f_req">*</span></label>
                <div class="col-lg-10">
                    <input type="password" class="form-control" name="password" id="password"  style="width:200px" value="${adminUser.password}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="realName">真实姓名 </label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="realName" name="realName" value="${adminUser.realName}"  style="width:200px">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="email">邮箱 <span class="f_req">*</span> </label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="email" name="email" value="${adminUser.email}"  style="width:200px">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                        <label>
                            <input type="radio" name="status" value="INACTIVE" <c:if test="${adminUser.status eq 'INACTIVE'}">CHECKED</c:if>>
                            禁用
                        </label>
                    <label>
                        <input type="radio" name="status" value="ACTIVE" <c:if test="${adminUser.status ne 'INACTIVE'}">CHECKED</c:if>>
                        激活
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="button" class="btn btn-default" onclick="submitForm()">提交</button>
                    <button class="btn btn-default">返回</button>
                </div>
            </div>
        </form>

    </div>
</div>

<script>
    $(function(){
         $('#form').validate({
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
                             $.sticky("操作成功!.", {autoclose : 500, position: "top-right", type: "st-success" });

                             setTimeout(function ()
                             {

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
             },
            invalidHandler: function(form, validator) {
               $.sticky("验证失败!", {autoclose : 5000, position: "top-right", type: "st-error" });
            }
        });


    });

    function submitForm(){
        $("#form").submit();
    }
</script>

</body>
</html>
