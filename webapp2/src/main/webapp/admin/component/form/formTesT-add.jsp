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
             

                submitHandler: function ()
                {

                    $("#objForm").ajaxSubmit({
                        url :"formTesT.do?doAdd&ajax=true",
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
<form:form formCode="leaveForm1" entityName="formTesT"></form:form>
