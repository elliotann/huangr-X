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
    <input type="hidden" name="id" id="userId" value="${menu.id}">
    <div class="form_default">
        <fieldset>
            <legend>增加菜单</legend>

            <p>
                <label for="name">菜单名</label>

                <input type="text" class="sf" id="name" name="name" value="${menu.name}">
            </p>
            <p>

                <label for="pid">上级菜单</label>
                <select name="pid" id="pid">
                    <option value="0">顶级菜单</option>
                    <c:forEach items="${menus}" var="treeMenu">
                        <option value="${treeMenu.id}" <c:if test="${treeMenu.id == menu.pid}">selected="selected" </c:if>>${treeMenu.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label for="url">URL</label>
                <input type="text" class="sf" id="url" name="url" value="${menu.url}">
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
                            $.ligerDialog.waitting('操作成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();

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
