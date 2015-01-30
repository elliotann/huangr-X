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
                    },
                    email:{
                    	required:true,
                    	email:true,
                    	remote:{
                    		url:'userAdmin.do?checkEmailExist&ajax=true',
                    		type:'post',
                    		dataType:'json',
                    		data:{
                    			email:function(){return $("#email").val();}
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
                	$("#objForm").ajaxSubmit({
                        url :"userAdmin.do?addSave&ajax=true",
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
	<input type="hidden" name="founder" value="0" />
	<table cellpadding="0" cellspacing="0" class="l-table-edit">
		<c:if test="${multiSite==1}">
			<tr>
				<td align="right"><label class="Validform_label">站点：</label></td>
				<td><select name="adminUser.siteid" id="adminUserSite" /></td>
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
			<td align="left" class="l-table-edit-td"><input name="username"
				type="text" id="username" class="form-control" /></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">密码:</td>
			<td align="left" class="l-table-edit-td"><input name="password"
				type="password" id="password"
				validate="{required:true,minlength:1,maxlength:18}"
				class="form-control" /></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">邮箱:</td>
			<td align="left" class="l-table-edit-td"><input name="email"
				type="text" id="email"
				class="form-control" /></td>
			<td align="left"></td>
		</tr>
		<tr id="roletr">
			<td align="right" class="l-table-edit-td" valign="top">角色:</td>
			<td colspan="3" class="value"><c:forEach var="role"
					items="${roleList }" varStatus="roleSta">
					<input type="checkbox" name="roleids" value="${role.id }"
						style="margin-left: 11px" />
					<label>${role.rolename }&nbsp;</label>
					<c:if test="${(roleSta.index+1)%4==0}">
						<br />
					</c:if>
				</c:forEach></td>
		</tr>

		<c:forEach items="${htmlList }" var="html">${html }</c:forEach>
		<tr>
			<td align="right" class="l-table-edit-td" valign="top">状态:</td>
			<td align="left" class="l-table-edit-td"><input id="active"
				type="radio" name="state" value="1" checked="checked"
				style="margin-left: 7px;" /><label for="active">启用</label> <input
				id="inActive" type="radio" name="state" value="1" /><label
				for="inActive">禁用</label></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">姓名:</td>
			<td align="left" class="l-table-edit-td"><input name="realname"
				type="text" id="realname" ltype="text" class="form-control" /></td>
			<td align="left"></td>
		</tr>

		<tr>
			<td align="right" class="l-table-edit-td">所属公司:</td>
			<td align="left" class="l-table-edit-td"><select id="compId"
				name="userCorp" class="easyui-combotree combo"
				data-options="url:'../organization.do?queryForTree&ajax=true',method:'get'"
				style="width: 206px; height: 30px;"></select></td>
			<td align="left"></td>
		</tr>

		<tr>
			<td align="right" class="l-table-edit-td">所属部门:</td>
			<td align="left" class="l-table-edit-td"><select id="userdept"
				name="userdept" class="easyui-combotree combo"
				style="width: 206px; height: 30px;"></select></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">备注:</td>
			<td align="left" class="l-table-edit-td"><textarea name="remark"
					cols="100" rows="4" class="form-control" id="remark"
					style="width: 400px; height: 100px;"></textarea></td>
			<td align="left"></td>
		</tr>

	</table>
</form>


    
