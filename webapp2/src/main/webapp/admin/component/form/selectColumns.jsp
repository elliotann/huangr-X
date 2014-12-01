<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script src="${staticserver}/js/common/jquery.validate.js"
	type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/admin/component/form/css/jquery.ui.css">


<link href="${context }/css/form.css" rel="stylesheet" />
<link href="${context }/js/easyui/themes/icon.css" rel="stylesheet" />
<script type="text/javascript">
	var groupicon = "${context }/js/ligerui/skins/icons/communication.gif";

	var tab = null;

	$(function() {

		var v = $("form").validate({
			debug : true,
			errorPlacement : function(lable, element) {
				if (element.hasClass("l-textarea")) {
					element.addClass("l-textarea-invalid");
				} else if (element.hasClass("l-text-field")) {
					element.parent().addClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
				$(element).attr("title", lable.html()).ligerTip();
			},
			success : function(lable) {
				var element = $("#" + lable.attr("for"));
				if (element.hasClass("l-textarea")) {
					element.removeClass("l-textarea-invalid");
				} else if (element.hasClass("l-text-field")) {
					element.parent().removeClass("l-text-invalid");
				}
				$(element).removeAttr("title").ligerHideTip();
			},
			submitHandler : function() {
				$.Loading.show('操作成功!');

                setTimeout(function ()
                {
                    $.Loading.hide();
                    $("#dialogInfo").dialog('close');
                   


                }, 1000);

			}
		});

	});

	function addForm() {
		var output = document.getElementById("output");
		var resultJson = [];
		for(var i=0;i<output.length;i++){
			var col = {                        
					field: output[i].value,                        
					title: output[i].text,                        
					align: 'center',                       
					width: 100                  
				};  
			

			resultJson.push(col);
		}
		refresh(resultJson);
		$("#objForm").submit();
	}
	
	function addIt() {
		var input = document.getElementById("inputFrom");
		var output = document.getElementById("output");
		
		var tempInput = input;
		for (i = 0; i < tempInput.length; i++) {
	
			if (tempInput[i].selected == true) {

				if (output.length == 0) {
					var option = new Option();
					option.value = tempInput[i].value;
					option.text = tempInput[i].text;
					output.add(option);
			
				}

				var isExist = false;
				for (j = 0; j < output.length; j++) {
					if (output[j].text == tempInput[i].text) {
						isExist = true;
						break;
					}
				}

				if (isExist == false) {
					var option = new Option();
					option.text = tempInput[i].text;
					option.value = tempInput[i].value;
					output.add(option);
				}
			}
		}
	}

	function deleteIt() {
		var output = document.getElementById("output");
		for (i = 0; i < output.length; i++) {
			if (output[i].selected == true) {
				output.options.remove(i--);
			}
		}

	}
</script>
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<body>
	<form name="objForm" method="post" id="objForm">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">

			<tr>
				<td align="right" class="l-table-edit-td">表单选择:</td>
				<td align="left" class="l-table-edit-td"><input
					name="tableName" type="text" id="tableName"
					validate="{required:true,maxlength:30}" class="form-control"
					value="${formEntity.formName }" /></td>
				<td align="right" class="l-table-edit-td"></td>
				<td align="left" class="l-table-edit-td"></td>
			</tr>

		</table>
		<br />

		<table width="80%" border="0" align="center" style="margin-top: 20px;">
			<tr>
				<td align="right">
				<select name="input" size="10"
					multiple="multiple" id="inputFrom"
					style="width: 200px; font-size: 16px">
						<c:forEach items="${formEntity.fields }" var="formField">
						<option value="${formField.fieldName }">${formField.displayName }</option>
						</c:forEach>
						

				</select></td>
				<td align="center">
					<p>
						<input type="button" name="Submit" value="增 加" onclick="addIt()" />
					</p>
					<p>
						<input type="button" name="Submit2" value="删 除"
							onclick="deleteIt()" />
					</p>
				</td>
				<td><select name="output" size="10" multiple="multiple"
					id="output" style="width: 200px; font-size: 16px">
				</select></td>
			</tr>
		</table>
	</form>
</body>

