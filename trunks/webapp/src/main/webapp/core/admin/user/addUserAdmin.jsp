<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>



    <script src="/jeap/statics/js/common/jquery-1.6.4.js" type="text/javascript"></script>
    <script src="/jeap/statics/js/common/jquery.validate.js" type="text/javascript"></script>
    <link href="${context }/css/form.css" rel="stylesheet"/>
    <link href="${context }/css/validate.css" rel="stylesheet"/>
    <script type="text/javascript">
        $.validator.setDefaults({
            submitHandler: function() { alert("submitted!"); }
        });

        $().ready(function() {
            $("#objForm").validate({
                rules:{
                    username:{
                        required:true,
                        minlength:3,
                        maxlength:10,
                        notnull:true,
                        remote:{
                            url:'userAdmin.do?checkNameExist&ajax=true',
                            type:'post',
                            dataType:'json',
                            data:{
                                rolename:function(){return $("#rolename").val();},
                                roleid:function(){return $("#roleid").val();}
                            }

                        }
                    }
                }
            });

            // validate signup form on keyup and submit
            /*$("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    topic: {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: "Please enter your firstname",
                    lastname: "Please enter your lastname",
                    username: {
                        required: "Please enter a username",
                        minlength: "Your username must consist of at least 2 characters"
                    },
                    password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 5 characters long"
                    },
                    confirm_password: {
                        required: "Please provide a password",
                        minlength: "Your password must be at least 5 characters long",
                        equalTo: "Please enter the same password as above"
                    },
                    email: "Please enter a valid email address",
                    agree: "Please accept our policy"
                }
            });*/

            // propose username by combining first- and lastname
            $("#username").focus(function() {
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                if(firstname && lastname && !this.value) {
                    this.value = firstname + "." + lastname;
                }
            });

            //code to hide topic selection, disable for demo
            var newsletter = $("#newsletter");
            // newsletter topics are optional, hide at first
            var inital = newsletter.is(":checked");
            var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
            var topicInputs = topics.find("input").attr("disabled", !inital);
            // show when newsletter is checked
            newsletter.click(function() {
                topics[this.checked ? "removeClass" : "addClass"]("gray");
                topicInputs.attr("disabled", !this.checked);
            });
        });
        function submitForm(){
            $("#objForm").submit();
        }
    </script>

    <style type="text/css">
        #commentForm { width: 500px; }
        #commentForm label { width: 250px; }
        #commentForm label.error, #commentForm input.submit { margin-left: 253px; }
        #signupForm { width: 670px; }
        #signupForm label.error {
            margin-left: 10px;
            width: auto;
            display: inline;
        }
        #newsletter_topics label.error {
            display: none;
            margin-left: 103px;
        }
    </style>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}

</style>
<form name="objForm" method="post"   id="objForm">

    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <c:if test="${multiSite==1}">
            <tr>
                <td align="right"><label class="Validform_label">站点：</label></td>
                <td >
                    <select name="adminUser.siteid" id="adminUserSite"/>
                </td>
            </tr>
            <script>
                $(function(){

                    $.ajax({
                        type: "GET",
                        url: "../multiSite!listJson.do",
                        data:   "ajax=yes",
                        dataType:'json',
                        success: function(result){
                            if(result.result==0){
                                $("#adminUserSite").selectTree(result.data);
                            }else{
                                alert("站点列表获取失败，请重试");
                            }
                        },
                        error:function(){
                            alert("站点列表获取失败");
                        }
                    });
                });
            </script>
        </c:if>
        <tr>
            <td align="right" class="l-table-edit-td">用户名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="username" type="text" id="username" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">密码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="password" type="password" id="password" validate="{required:true,minlength:1,maxlength:18}" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td" valign="top">类型:</td>
            <td align="left" class="l-table-edit-td">
                <input id="notSuperChk" type="radio" name="founder" value="0" checked="checked" /><label for="notSuperChk">普通管理员</label>
                <input id="superChk" type="radio" name="founder" value="1" /><label for="superChk">超级管理员</label>
            </td><td align="left"></td>
        </tr>
        <tr id="roletr">
            <td align="right" class="l-table-edit-td" valign="top">角色:</td>
            <td colspan="3" class="value">
                <c:forEach var="role" items="${roleList }">
                    &nbsp;<input id="roleids" type="checkbox" name="roleids"  value="${role.roleid }"/><label for="roleids">${role.rolename }&nbsp;</label><br />
                </c:forEach>

            </td>
        </tr>

        <c:forEach items="${htmlList }" var="html">${html }</c:forEach>
        <tr>
            <td align="right" class="l-table-edit-td" valign="top">状态:</td>
            <td align="left" class="l-table-edit-td">
                <input id="active" type="radio" name="state" value="1" checked="checked" /><label for="active">启用</label>
                <input id="inActive" type="radio" name="state" value="1" /><label for="inActive">禁用</label>
            </td><td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">姓名:</td>
            <td align="left" class="l-table-edit-td"><input name="realname" type="text" id="realname" ltype="text" class="form-control"/></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">编号:</td>
            <td align="left" class="l-table-edit-td"><input name="userno" type="text" id="userno" ltype="text" class="form-control" /></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">部门:</td>
            <td align="left" class="l-table-edit-td"><input name="userdept" type="text" id="userdept" ltype="text" class="form-control"/></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">备注:</td>
            <td align="left" class="l-table-edit-td">
                <textarea name="remark" cols="100" rows="4" class="l-textarea" id="remark" style="width:400px" class="form-control"></textarea>
            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>


<div id="main">



    <form class="cmxform" id="signupForm" method="get" action="">
        <fieldset>
            <legend>Validating a complete form</legend>
            <p>
                <label for="firstname">Firstname</label>
                <input id="firstname" name="firstname" />
            </p>
            <p>
                <label for="lastname">Lastname</label>
                <input id="lastname" name="lastname" />
            </p>
            <p>
                <label for="username">Username</label>
                <input id="username" name="username" />
            </p>
            <p>
                <label for="password">Password</label>
                <input id="password" name="password" type="password" />
            </p>
            <p>
                <label for="confirm_password">Confirm password</label>
                <input id="confirm_password" name="confirm_password" type="password" />
            </p>
            <p>
                <label for="email">Email</label>
                <input id="email" name="email" />
            </p>
            <p>
                <label for="agree">Please agree to our policy</label>
                <input type="checkbox" class="checkbox" id="agree" name="agree" />
            </p>
            <p>
                <label for="newsletter">I'd like to receive the newsletter</label>
                <input type="checkbox" class="checkbox" id="newsletter" name="newsletter" />
            </p>
            <fieldset id="newsletter_topics">
                <legend>Topics (select at least two) - note: would be hidden when newsletter isn't selected, but is visible here for the demo</legend>
                <label for="topic_marketflash">
                    <input type="checkbox" id="topic_marketflash" value="marketflash" name="topic" />
                    Marketflash
                </label>
                <label for="topic_fuzz">
                    <input type="checkbox" id="topic_fuzz" value="fuzz" name="topic" />
                    Latest fuzz
                </label>
                <label for="topic_digester">
                    <input type="checkbox" id="topic_digester" value="digester" name="topic" />
                    Mailing list digester
                </label>
                <label for="topic" class="error">Please select at least two topics you'd like to receive.</label>
            </fieldset>
            <p>
                <input class="submit" type="submit" value="Submit"/>
            </p>
        </fieldset>
    </form>
