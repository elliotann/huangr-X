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
            rules:{

                name:{
                    required:true,
                    maxlength:50
                }
            },
            submitHandler: function ()
            {

                $("#objForm").ajaxSubmit({
                    url :"organization.do?updateCompany&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            $.ligerDialog.waitting('操作成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();

                            }, 1000);
                            window.parent.parent.location.reload();
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
                name:{
                    required:"公司名称不能为空",
                    maxlength:"公司名称最长50位"
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
    <input type="hidden" name="id" value="${organization.id}"/>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">公司编码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="compNo" type="text" id="compNo" class="form-control" value="${organization.compNo}" disabled/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司英文简写:</td>
            <td align="left" class="l-table-edit-td">
                <input name="enShortName" type="text" id="enShortName" class="form-control" value="${organization.enShortName}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="name" type="text" id="name" class="form-control"  value="${organization.name}"/>
            </td>
            <td align="left"></td>
        </tr>


        <tr>
            <td align="right" class="l-table-edit-td">公司地址:</td>
            <td align="left" class="l-table-edit-td">
                <input name="address" type="text" id="address" class="form-control" value="${organization.address}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">法人:</td>
            <td align="left" class="l-table-edit-td">
                <input name="legalPerson" type="text" id="legalPerson" class="form-control" value="${organization.legalPerson}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">上级公司:</td>
            <td align="left" class="l-table-edit-td">
                <input name="realname" type="text" id="realname" value="${parent.name}"  class="form-control" disabled="disabled"/>
                <input name="pid" type="hidden" id="pid" value="${parent.id}"/>
            </td>
            <td align="left"></td>
        </tr>


    </table>
</form>



