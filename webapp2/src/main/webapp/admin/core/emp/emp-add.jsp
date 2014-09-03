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
                    username:{
                        required:true,
                        minlength:3,
                        maxlength:10,
                        remote:{
                            url:'userAdmin.do?checkNameExist&ajax=true',
                            type:'post',
                            dataType:'json',
                            data:{
                                rolename:function(){return $("#rolename").val();},
                                roleid:function(){return $("#roleid").val();}
                            }

                        }
                    },
                    password:{
                        required:true,
                        minlength:6,
                        maxlength:18
                    }
                },

                submitHandler: function ()
                {

                    $("#objForm").ajaxSubmit({
                        url :"userAdmin.do?addSave&ajax=true",
                        type : "POST",
                        dataType:"json",
                        success : function(result) {

                            if(result.success){
                                $.ligerDialog.waitting('增加成功');
                                setTimeout(function ()
                                {
                                    $.ligerDialog.closeWaitting();

                                }, 1000);
                                window.parent.listgrid.loadData();
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



