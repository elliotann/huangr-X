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
    <link rel="stylesheet" href="/jeap/adminthemes/blue/lib/sticky/sticky.css"/>
    <script src="/jeap/adminthemes/default/js/mylib/jeap.js"></script>

    <script src="/jeap/adminthemes/blue/lib/sticky/sticky.js"></script>
   <%-- <script src="/jeap/adminthemes/blue/js/gebo_validation.js"></script>--%>

</head>
<body style="background-color: #EEEEEE;">
<div class="row">
    <div class="col-sm-6 col-md-6">
        <p class="sepH_c">增加角色</p>
        <form role="form" class="form-horizontal"  id="form">
            <input type="hidden" name="id" id="userId" value="${role.id}">
            <div class="form-group">
                <label class="col-lg-2 control-label" for="name">角色名称 <span class="f_req">*</span></label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" name="name" id="name" style="width:200px" value="${role.name}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="reg_your_message">描述 </label>
                <div class="col-lg-10">
                    <textarea name="reg_your_message" id="reg_your_message" cols="10" rows="3" class="form-control"></textarea>
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
                 name: {
                     required:true,
                     minlength:3,
                     maxlength:50
                 },

             messages: {
                 name: {
                     required:"角色名称不能为空!",
                     minlength:"角色名称最少3位!",
                     maxlength:"用户名最长50位!"
                 }
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
