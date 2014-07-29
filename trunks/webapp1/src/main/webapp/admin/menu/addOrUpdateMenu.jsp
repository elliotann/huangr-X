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
            <input type="hidden" name="id" id="userId" value="${menu.id}">
            <div class="form-group">
                <label class="col-lg-2 control-label" for="name">菜单名 <span class="f_req">*</span></label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" name="name" id="name" style="width:200px" value="${menu.name}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="pid">上级菜单 <span class="f_req">*</span></label>
                <div class="col-lg-10">

                    <select class="form-control"  style="width:200px"  name="pid" id="pid">
                        <option value="0">顶级菜单</option>
                        <c:forEach items="${menus}" var="treeMenu">
                            <option value="${treeMenu.id}" <c:if test="${treeMenu.id == menu.pid}">selected="selected" </c:if>>${treeMenu.name}</option>
                        </c:forEach>
                    </select>

                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-2 control-label" for="url">URL </label>
                <div class="col-lg-10">

                    <input type="text" class="form-control" id="url" name="url" value="${menu.url}"  style="width:200px">
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
