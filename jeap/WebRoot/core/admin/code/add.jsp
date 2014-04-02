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
        $.metadata.setType("attr", "validate");
        var v = $("form").validate({
            debug: true,

            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] });
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("form .l-text,.l-textarea").ligerHideTip();
                $("#form1").ajaxSubmit({
                    url :"menu.do?saveAdd&ajax=true",
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
        });
        $("form").ligerForm();



        $("#notSuperChk").click(function(){

            if(!this.checked)
                $("#roletr").show();
        });
        $("#superChk").click(function(){

            if(!this.checked)
                $("#roletr").hide();
        });

    });

    function closeDig(){
        dialog.close();//关闭dialog
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
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">生成目录:</td>
            <td align="left" class="l-table-edit-td">
                <input name="projectPath" type="text" id="projectPath" ltype="file"  validate="{required:true,maxlength:60}" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">表名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tableName" type="text" id="tableName" ltype="text" value="${formEntity.tableName}"  disabled="disabled" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">描述:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tableTitle" type="text" id="tableTitle" ltype="text" value="${formEntity.tableTitle}"   disabled="disabled" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">包名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="packageName" type="text" id="packageName" ltype="text"  validate="{required:true,maxlength:60}" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">实体名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="entityName" type="text" id="entityName" ltype="text" value="${entityName}"  validate="{required:true,maxlength:60}" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td" valign="top">需要生成的代码:</td>
            <td align="left" class="l-table-edit-td" style="width:160px">
                <input id="codeTypeAction" type="checkbox" name="codeTypeAction" checked="checked" /><label for="codeTypeAction">Action</label><br />
                <input id="codeTypeJsp" type="checkbox" name="codeTypeJsp" /><label for="codeTypeJsp">Jsp</label> <br />
                <input id="codeTypeService" type="checkbox" name="codeTypeService" /><label for="codeTypeService">Service</label><br/>
                <input id="codeTypeEntity" type="checkbox" name="codeTypeEntity" /><label for="codeTypeEntity">Entity(Model)</label>
            </td><td align="left"></td>
        </tr>



    </table>
    <br />
    <input type="submit" value="提交" id="Button1" name="subBtn" class="l-button l-button-submit" />
    <input type="button" value="关闭" class="l-button l-button-test" onclick="closeDig()"/>
</form>

