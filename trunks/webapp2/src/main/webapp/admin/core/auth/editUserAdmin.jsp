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
        	<c:forEach var="userRole" items="${userRoles }">
        		$("#roleids${userRole.roleid}").attr("checked",true);
        	</c:forEach>
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
                   
                    email:{
                    	required:true,
                    	email:true,
                    	remote:{
                    		url:'userAdmin.do?checkEmailExist&ajax=true',
                    		type:'post',
                    		dataType:'json',
                    		data:{
                    			email:function(){return $("#email").val();},
                    			userId:function(){return $("#userId").val();}
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
                        url :"userAdmin.do?editSave&ajax=true",
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
	<input type="hidden" name="id" value="${adminUser.id }" id="userId" />
    <input type="hidden" name="ajax" value="true"/>
    <input  type="hidden" name="founder" value="${adminUser.founder }"/>
    <input  type="hidden" name="password" value="${adminUser.password }"/>
	<table cellpadding="0" cellspacing="0" class="l-table-edit">
		<tr>
			<td align="right" class="l-table-edit-td">用户名:</td>
			<td align="left" class="l-table-edit-td">
				<input name="username" type="text" id="username" class="form-control" value="${adminUser.username }"/>
			</td>
			<td align="left">
			</td>
		</tr>
		
		<tr>
			<td align="right" class="l-table-edit-td">邮箱:</td>
			<td align="left" class="l-table-edit-td"><input name="email" ype="text" id="email"
				class="form-control" value="${adminUser.email }"/></td>
			<td align="left"></td>
		</tr>
		<tr id="roletr">
			<td align="right" class="l-table-edit-td" valign="top">角色:</td>
			<td colspan="3" class="value">
			<c:forEach var="role" items="${roleList }" varStatus="roleSta">
                <input id="roleids${role.id }" type="checkbox" name="roleids"  value="${role.id }"  style="margin-left: 11px"/><label for="roleids${role.id }">${role.rolename }&nbsp;</label>
                <c:if test="${(roleSta.index+1)%4==0}">
                    <br/>
                </c:if>
            </c:forEach>
				</td>
		</tr>

		<c:forEach items="${htmlList }" var="html">${html }</c:forEach>
		<tr>
			<td align="right" class="l-table-edit-td" valign="top">状态:</td>
			<td align="left" class="l-table-edit-td">
			 	<input id="active" type="radio" name="state" value="1" <c:if test="${adminUser.state==1}">checked="checked"</c:if> style="margin-left: 7px"/><label for="active">启用</label>
                <input id="inActive" type="radio" name="state" value="0" <c:if test="${adminUser.state==0}">checked="checked"</c:if>/><label for="inActive">禁用</label>
				</td>
			<td align="left"></td>
		</tr>
		<tr>
			<td align="right" class="l-table-edit-td">姓名:</td>
			<td align="left" class="l-table-edit-td"><input name="realname"
				type="text" id="realname" ltype="text" class="form-control" value="${adminUser.realname}"/></td>
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
					style="width: 400px; height: 100px;">${adminUser.remark }</textarea></td>
			<td align="left"></td>
		</tr>

	</table>
</form>


    
