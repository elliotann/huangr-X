<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>


<script src="${staticserver}/js/common/jquery.validate.js" type="text/javascript"></script>

<link href="${context }/css/form.css" rel="stylesheet"/>

<script type="text/javascript">

    var dialog = frameElement.dialog;

    $(function() {

        $("#objForm").validate({
            rules:{
                compNo:{
                    required:true,
                    minlength:4,
                    maxlength:4,
                    remote:{
                        url:'organization.do?checkDeptNoExist&ajax=true',
                        type:'post',
                        dataType:'json',
                        data:{
                            deptNo:function(){return $("#deptNo").val();},
                            id:0
                        }

                    }
                },
                name:{
                    required:true,
                    maxlength:50
                }
            },
            submitHandler: function ()
            {

                $("#objForm").ajaxSubmit({
                    url :"organization.do?addCompany&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){

                            $("#useradmininfo").dialog('close');
                           $('#useradmindata').treegrid('reload');

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
                compNo: {
                    required: "编码不能为空",
                    minlength: "编码只能是4位字符",
                    maxlength:"编码只能是4位字符",
                    remote:"编码已经存在"
                },
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

    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">公司编码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="compNo" type="text" id="compNo" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司英文简写:</td>
            <td align="left" class="l-table-edit-td">
                <input name="enShortName" type="text" id="enShortName" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="name" type="text" id="name" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>


        <tr>
            <td align="right" class="l-table-edit-td">公司地址:</td>
            <td align="left" class="l-table-edit-td">
                <input name="address" type="text" id="address" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">法人:</td>
            <td align="left" class="l-table-edit-td">
                <input name="legalPerson" type="text" id="legalPerson" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">上级公司:</td>
            <td align="left" class="l-table-edit-td">
                <input name="realname" type="text" id="realname" value="${organization.name}"  class="form-control" disabled="disabled"/>
                <input name="pid" type="hidden" id="pid" value="${organization.id}"/>
            </td>
            <td align="left"></td>
        </tr>


    </table>
</form>



