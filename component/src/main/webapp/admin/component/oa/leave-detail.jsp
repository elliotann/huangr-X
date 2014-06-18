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

        $("form").ligerForm();

    });

    function submitForm(){
        $.ajax({
            url :"leave.do?complete&ajax=true&keys=deptLeaderPass&values=true&types=B&taskId="+$("#taskId").val(),
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
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<form name="form1" method="post"   id="form1">
    <input type="hidden" id="taskId" value="${taskId}"/>
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">申请人:</td>
            <td align="left" class="l-table-edit-td">
                ${leave.userId}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">假种:</td>
            <td align="left" class="l-table-edit-td">
                公休
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">申请时间:</td>
            <td align="left" class="l-table-edit-td">
                ${leave.endTime}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">请假开始时间:</td>
            <td align="left" class="l-table-edit-td">
                ${leave.startTime}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">请假结束时间:</td>
            <td align="left" class="l-table-edit-td">
                ${leave.endTime}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">请假事由:</td>
            <td align="left" class="l-table-edit-td">
                ${leave.reason}
            </td>
            <td align="left"></td>
        </tr>


    </table>
</form>

