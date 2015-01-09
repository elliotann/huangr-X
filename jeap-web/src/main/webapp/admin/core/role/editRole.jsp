<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head><title>
</title>
   <link href="/jeap1.0/js/admin/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
 
   <script src="/jeap1.0/js/commons/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerButton.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
    <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/commons/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
    <script src="/jeap1.0/js/commons/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="/jeap1.0/js/commons/jquery-validation/messages_cn.js" type="text/javascript"></script>

    <script type="text/javascript">
    var eee;
    $(function () {
        $.metadata.setType("attr", "validate");
        var v = $("form").validate({
        	
            //调试状态，不会提交数据的
            debug: true,
            errorPlacement: function (lable, element) {

                if (element.hasClass("l-textarea")) {
                    element.addClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field")) {
                    element.parent().addClass("l-text-invalid");
                }

                var nextCell = element.parents("td:first").next("td");
                nextCell.find("div.l-exclamation").remove(); 
                $('<div class="l-exclamation" title="' + lable.html() + '"></div>').appendTo(nextCell).ligerTip(); 
            },
            success: function (lable) {
                var element = $("#" + lable.attr("for"));
                var nextCell = element.parents("td:first").next("td");
                if (element.hasClass("l-textarea")) {
                    element.removeClass("l-textarea-invalid");
                }
                else if (element.hasClass("l-text-field")) {
                    element.parent().removeClass("l-text-invalid");
                }
                nextCell.find("div.l-exclamation").remove();
            },
            submitHandler: function () {
            	$.ajax({
            		url:'role.do?add',
            		type:'post',
            		dataType:'json',
            		data:$('#objForm').serialize(),
            		success:function(result){
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
            		error:function(e){
            			alert(e);
            		}
            	});
                
            }
        });
        $("form").ligerForm();
        $(".l-button-test").click(function () {
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

</head>

<body style="padding:10px">

	<form name="objForm" method="post" id="objForm">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr>
				<td align="right" class="l-table-edit-td">角色名称:</td>
				<td align="left" class="l-table-edit-td"><input name="roleName"	type="text" id="roleName" ltype="text" validate="{required:true}" value="${role.roleName }"/></td>
				<td align="left"></td>
			</tr>
			<tr>
				
				<td align="right" class="l-table-edit-td" valign="top">状态:</td>
				<td align="left" class="l-table-edit-td"><input id="rbtnl_0"
					type="radio" name="status" value="ACTIVE"  <c:if test="${role.status eq 'ACTIVE' }">checked='checked'</c:if> /><label
					for="rbtnl_0">激活</label> <input id="rbtnl_1" type="radio"
					name="status" value="INACTIVE" <c:if test="${role.status eq 'INACTIVE' }">checked='checked'</c:if>/><label for="rbtnl_1">禁用</label></td>
				<td align="left"></td>
			</tr>
		</table>

	</form>
	<div style="display: none">
		<!--  数据统计代码 -->
	</div>


</body>
</html>