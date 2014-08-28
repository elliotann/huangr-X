<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>



<script src="${staticserver}/js/common/jquery-1.6.4.js" type="text/javascript"></script>
<script src="${staticserver}/js/common/jquery.validate.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>

<script type="text/javascript">

    var dialog = frameElement.dialog;
    $(function() {
        $("#objForm").validate({

            submitHandler: function ()
            {

                $("#objForm").ajaxSubmit({
                    url :"organization.do?doAdd&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            $.ligerDialog.waitting('增加成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();

                            }, 1000);

                            dialog.close();
                        }else{
                            alert(result.msg)
                        }
                    },
                    error : function(e) {
                        alert("出错啦:(");
                    }
                });

            },
            messages:{
                username: {
                    required: "用户名不能为空",
                    minlength: "用户名最少3个字符",
                    maxlength:"用户名最大18个字符",
                    remote:"用户名已经存在"
                },
                password:{
                    required:"密码不能为空",
                    minlength:"密码最少6位",
                    maxlength:"密码最长18位"
                }
            }
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


</style>
<form name="objForm" method="post"   id="objForm">

    <table cellpadding="0" cellspacing="0" class="l-table-edit" >

        <tr>
            <td align="right" class="l-table-edit-td">机构名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="name" type="text" id="name" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>

        <tr>
            <td align="right" class="l-table-edit-td">上级机构:</td>
            <td align="left" class="l-table-edit-td">
                <input name="realname" type="text" id="realname" value="${organizatiOnEntity.name}"  class="form-control" disabled="disabled"/>
                <input name="pid" type="hidden" id="pid" value="${organizatiOnEntity.id}"/>
            </td>
            <td align="left"></td>
        </tr>


    </table>
</form>



