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
                    
                	file:{
                        required:true
                    }
                },

                submitHandler: function ()
                {

                    $("#objForm").ajaxSubmit({
                        url :"workflow.do?deploy&ajax=true",
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
                    
                    file:{
                        required:"密码不能为空"
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
<form name="objForm" method="post" id="objForm" enctype="multipart/form-data">
	<input type="hidden" name="founder" value="0" />
	<table cellpadding="0" cellspacing="0" class="l-table-edit">
		
		<tr>
			<td align="right" class="l-table-edit-td">支持文件格式：zip、bar、bpmn、bpmn20.xml</td>
			<td align="left" class="l-table-edit-td"><input name="file" type="file" id="file" ltype="file" /></td>
			<td align="left"></td>
		</tr>
		

	</table>
</form>



