<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
      <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script> 
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
    <script src="${ctx }/statics/js/common/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="${ctx }/statics/js/common/jquery.metadata.js" type="text/javascript"></script>
    <script src="${ctx }/statics/js/common/messages_cn.js" type="text/javascript"></script>
     <script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>

    <script type="text/javascript">
        var eee;
        $(function ()
        {
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
                //调试状态，不会提交数据的
                debug: true,
                rules:{
                    rolename:{
                        required:true,
                        minlength:3,
                        maxlength:10,
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
                				$.ligerDialog.waitting(result.msg);
                                setTimeout(function ()
                                {
                                	
                                	$.ligerDialog.closeWaitting();
                                	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
                                    dialog.close();//关闭dialog 
                                    
                                }, 2000);
                                window.parent.listgrid.loadData();
                			}
                        },
                        error : function(e) {
                            alert("出错啦:(");
                        }
                    });
                },
                messages:{
                    rolename:{
                        required:"请输入角色名称",
                        remote:"角色名已经存在!",
                        minlength:"角色名称最小3个字符",
                        maxlength:"角色名称最长10个字符"
                    }
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#txtName")));
            });
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



    <form name="objForm" method="post" id="objForm">
	 <input type="hidden" name="id" id="roleid" value="${role.id==null?0:role.id }" />
    <input type="hidden" name="ajax"  value="true" />
	 <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">角色名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="rolename" type="text" id="rolename" value="${role.rolename}" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">描述：</td>
            <td align="left" class="l-table-edit-td">
                <textarea name="rolememo" cols="100" rows="3" class="form-control" id="rolememo" style="width:400px;height: 150px;">${role.rolememo}</textarea>
            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>


    
