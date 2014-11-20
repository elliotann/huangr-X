<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script src="${staticserver}/js/common/jquery.validate.js"
	type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet" />
<link href="${context }/js/easyui/themes/icon.css" rel="stylesheet" />

<script type="text/javascript">

        var dialog = frameElement.dialog;
        $(function() {
            $("#objForm").validate({
                rules:{
                	name:{
                        required:true,
                        minlength:2,
                        maxlength:10
                    },
                    code:{
                        required:true,
                        minlength:2,
                        maxlength:10
                    }
                },

                submitHandler: function ()
                {

                    $("#objForm").ajaxSubmit({
                        url :"oper.do?saveAdd&ajax=true",
                        type : "POST",
                        dataType:"json",
                        success : function(result) {

                            if(result.success){
                                $.Loading.show('操作成功!');

                                setTimeout(function ()
                                {
                                    $.Loading.hide();
                                    $("#dialogInfo").dialog('close');
                                    $('#dataGrid').datagrid('reload');


                                }, 1000);

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
                	name: {
                        required: "名称不能为空",
                        minlength: "名称最少2个字符",
                        maxlength:"名称最大18个字符"
                    },
                    code:{
                        required:"编码不能为空",
                        minlength:"编码最少2位",
                        maxlength:"编码最长10位"
                    }
                }
            });


          

        });
        function submitForm(){
            $("#objForm").submit();
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
</style>
<form name="objForm" method="post" id="objForm">
	<input type="hidden" value="${menuId}" id="menuId" name="menuId"/>
	<table cellpadding="0" cellspacing="0" class="l-table-edit">
	
		 <tr>
            <td align="right" class="l-table-edit-td">名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="name" type="text" id="btnName" ltype="text"  validate="{required:true,maxlength:60}" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">编码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="code" type="text" id="code" ltype="text"  validate="{required:true,maxlength:60}" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
		<tr>
            <td align="right" class="l-table-edit-td">状态:</td>
            <td align="left" class="l-table-edit-td">
				<input id="active"
				type="radio" name="btnStatus" value="1" checked="checked"
				style="margin-left: 7px;" /><label for="active">有效</label> <input
				id="inActive" type="radio" name="btnStatus" value="0" /><label
				for="inActive">无效</label>
            </td>
            <td align="left"></td>
        </tr>

	</table>
</form>



