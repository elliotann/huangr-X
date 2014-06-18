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
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>

<script src="${context }/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
<script src="/jeap/admin/js/common/crud.js" type="text/javascript"></script>
<script type="text/javascript" src="js/Auth.js"></script>
<script type="text/javascript">
    var dialog = frameElement.dialog;
    $(function (){

        $.validator.addMethod(
                "notnull",
                function (value, element, regexp)
                {
                    if (!value) return true;
                    return !$(element).hasClass("l-text-field-null");
                },
                "不能为空"
        );
        $.metadata.setType("attr", "validate");
        var v = $("form").validate({
            debug: true,
            rules:{
                rolename:{
                    required:true,
                    minlength:3,
                    maxlength:10,
                    notnull:true,
                    remote:{
                        url:'role.do?checkNameExist&ajax=true',
                        type:'post',
                        dataType:'json',
                        data:{
                            rolename:function(){return $("#rolename").val();},
                            roleid:function(){return $("#roleid").val();}
                        }

                    }
                }
            },

            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.addClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().addClass("l-text-invalid");
                }
                $(element).removeAttr("title").ligerHideTip();
                $(element).attr("title", lable.html()).ligerTip();
            },
            success: function (lable)
            {
                var element = $("#" + lable.attr("for"));
                if (element.hasClass("l-textarea"))
                {
                    element.removeClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().removeClass("l-text-invalid");
                }
                $(element).removeAttr("title").ligerHideTip();
            },
            submitHandler: function ()
            {
                $("form .l-text,.l-textarea").ligerHideTip();
                var roleid = $("#roleid").val();
                var url = "role.do?saveAdd";
                if(roleid!=0){
                    url = "role.do?saveEdit";
                }

                $("#objForm").ajaxSubmit({
                    url :url,
                    type : "POST",
                    dataType:"json",
                    success : function(result) {
                        if(result.success){
                            $.ligerDialog.waitting('增加成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                grid.loadData();
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
                rolename:{
                    required:"Please enter your username",
                    remote:"角色名已经存在!"
                }
            }
        });
        $("form").ligerForm();

        AuthAction.init();
    });

    function submitForm(){
        $("#objForm").submit();
    }

</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<form name="objForm" method="post"   id="objForm">
    <input type="hidden" name="roleid" id="roleid" value="${role.roleid==null?0:role.roleid }" />
    <input type="hidden" name="ajax"  value="true" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">角色名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="rolename" type="text" id="rolename" ltype="text" nullText="不能为空!" value="${role.rolename}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">描述：</td>
            <td align="left" class="l-table-edit-td">
                <textarea name="rolememo" cols="100" rows="4" class="l-textarea" id="rolememo" style="width:400px">${role.rolememo}</textarea>
            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>