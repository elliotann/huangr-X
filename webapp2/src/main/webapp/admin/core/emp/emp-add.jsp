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
                    empNo:{
                        required:true,
                        minlength:1,
                        maxlength:4,
                        remote:{
                            url:'emp.do?checkEmpNoExist&ajax=true',
                            type:'post',
                            dataType:'json',
                            data:{
                                empNo:function(){return $("#empNo").val();},
                                id:0
                            }

                        }
                    },
                    name:{
                        required:true,
                        minlength:2,
                        maxlength:5
                    },
                    entryDate:{
                        required:true
                    }
                },

                submitHandler: function ()
                {

                    var options = {
                        url : "emp.do?addSave&ajax=true",
                        type : "POST",
                        dataType : "json",
                        success : function(result) {
                            if(result.success){
                                $("#useradmininfo").dialog('close');
                                $('#useradmindata').datagrid('reload');
                                savebtn.linkbutton("enable");
                            }

                        },
                        error : function(e) {
                            $.Loading.error("出现错误 ，请重试");
                            savebtn.linkbutton("enable");
                        }
                    };
                    $('#objForm').ajaxSubmit(options);

                },
                messages:{
                    empNo: {
                        required: "员工号不能为空",
                        maxlength:"员工号最大4个字符",
                        remote:"编号已经存在"
                    },
                    name:{
                        required:"姓名不能为空",
                        minlength:"姓名最少2位",
                        maxlength:"姓名最长5位"
                    },
                    entryDate:{
                        required:"入职日期不能为空"
                    }
                }
            });
        });


        function queryDeparts(corpId){
            $.ajax({
                type:'post',
                url:'../depart.do?queryDepartsByOrgId&ajax=true&orgId='+corpId,
                dataType:'html',
                success:function(result){
                    $("#userdept").html(result);

                }
            });
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
            <td align="right" class="l-table-edit-td">员工号:</td>
            <td align="left" class="l-table-edit-td">
                <input name="empNo" type="text" id="empNo" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">姓名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="name" type="text" id="name" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">性别:</td>
            <td align="left" class="l-table-edit-td">
                <select class="form-control" name="sex" id="sex">
                    <option value="MALE">男</option>
                    <option value="FEMALE">女</option>
                </select>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">电话:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tel" type="text" id="tel" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">手机:</td>
            <td align="left" class="l-table-edit-td">
                <input name="mobile" type="text" id="mobile" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">入职日期:</td>
            <td align="left" class="l-table-edit-td">
                <input name="entryDate" type="text" id="entryDate" class="form-control"  onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>
            </td>
            <td align="left"></td>
        </tr>
    </table>
</form>



