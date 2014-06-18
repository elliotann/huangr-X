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
<script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-plugs/form/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">

    var groupicon = "${context }/js/ligerui/skins/icons/communication.gif";
    var dialog = frameElement.dialog;


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
            debug: true,

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
                $("form .l-text,.l-textarea").ligerHideTip();
                $("#objForm").ajaxSubmit({
                    url :"menu.do?saveEdit&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            $.ligerDialog.waitting('操作成功...');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                                window.parent.listgrid.loadData();
                                dialog.close();
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
        $("form").ligerForm();

        $("#menupid").ligerGetComboBoxManager().selectValue(${menu.pid});

        $("#notSuperChk").click(function(){

            if(!this.checked)
                $("#roletr").show();
        });
        $("#superChk").click(function(){

            if(!this.checked)
                $("#roletr").hide();
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
<form name="objForm" method="post"   id="objForm" enctype="multipart/form-data">
    <input type="hidden" name="menuId" value="${menu.id}" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="title" type="text" id="title" ltype="text" value="${menu.title}"  validate="{required:true,maxlength:60}" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">类型:</td>
            <td align="left" class="l-table-edit-td">
                <input type="text" id="menuType" name="menuType" style="width: 50px" ltype="select" ligerui="{width:178,data:[{id:'2',text:'应用'},{id:'1',text:'系统'}],initValue:'${menu.menutype}',valueFieldID:'menutype'}" validate="{required:true}" />

            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">上级菜单:</td>
            <td align="left" class="l-table-edit-td">
                <input type="text" id="menupid" name="menupid" style="width: 170px" ltype="select" ligerui="{width: 180,valueFieldID:'pid',selectBoxWidth: 200, selectBoxHeight: 200,valueField: 'id',textField:'title',treeLeafOnly:false, tree: { url: 'menu.do?addOrUpdateGrid&ajax=true', checkbox: false, ajaxType: 'get',textFieldName:'title',parentIDFieldName:'pid' }}" validate="{required:true}" />

            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">url:</td>
            <td align="left" class="l-table-edit-td">
                <input name="url" type="text" id="url" ltype="text" value="${menu.url}" validate="{required:true}" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">target:</td>
            <td align="left" class="l-table-edit-td">
                <input name="target" type="text" id="target" ltype="text" value="${menu.target}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">排序:</td>
            <td align="left" class="l-table-edit-td">
                <input name="sorder" type="text" id="sorder" ltype="text" validate="{required:true}" value="0" value="${menu.sorder}"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">图标:</td>
            <td align="left" class="l-table-edit-td">
                <input name="ico" type="hidden" id="ico" value="${menu.ico}"/>
                <input name="icoFile" type="file" id="icoFile" ltype="file" value=""/>
            </td>
            <td align="left"></td>
        </tr>
    </table>
</form>