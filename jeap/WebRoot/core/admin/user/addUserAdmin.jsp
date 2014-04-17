<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>




    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
    <script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>

    <script src="${context }/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
    <script src="${context }/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="${context }/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>




<script type="text/javascript">

    var groupicon = "${context }/js/ligerui/skins/icons/communication.gif";


    var dialog = frameElement.dialog;
    $(function ()
    {
        $.metadata.setType("attr", "validate");
        var v = $("form").validate({
            debug: true,

            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] });
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("form .l-text,.l-textarea").ligerHideTip();
                $("#form1").ajaxSubmit({
                    url :"userAdmin.do?addSave&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            alert("增加成功!");
                            window.parent.grid.loadData();
                            dialog.close();
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
        $("form").ligerForm();
        $(".l-button-test").click(function ()
        {
            dialog.close();//关闭dialog
        });



        $("#notSuperChk").click(function(){

            if(!this.checked)
                $("#roletr").show();
        });
        $("#superChk").click(function(){

            if(!this.checked)
                $("#roletr").hide();
        });

    });


</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<form name="form1" method="post"   id="form1">
    <div>
    </div>
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
                <input name="username" type="text" id="username" ltype="text" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">密码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="password" type="password" id="password" ltype="text" validate="{required:true,minlength:1,maxlength:18}" />
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
            <td align="left" class="l-table-edit-td"><input name="realname" type="text" id="realname" ltype="text" /></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">编号:</td>
            <td align="left" class="l-table-edit-td"><input name="userno" type="text" id="userno" ltype="text"  /></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">部门:</td>
            <td align="left" class="l-table-edit-td"><input name="userdept" type="text" id="userdept" ltype="text" /></td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">备注:</td>
            <td align="left" class="l-table-edit-td">
                <textarea name="remark" cols="100" rows="4" class="l-textarea" id="remark" style="width:400px"></textarea>
            </td>
            <td align="left"></td>
        </tr>

    </table>
    <br />
    <input type="submit" value="提交" id="Button1" name="subBtn" class="l-button l-button-submit" />
    <input type="button" value="关闭" class="l-button l-button-test"/>
</form>

