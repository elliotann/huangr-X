<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<link href=" ${context}/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href=" ${context}/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=" ${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src=" ${context}/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>

<script src=" ${context}/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
<script src=" ${context}/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src=" ${context}/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src=" ${staticserver}/js/admin/jeap.js"></script>
<script src="/jeap/form/config/lab.js" type="text/javascript"></script>

<script type="text/javascript">
    var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
    var dialog = frameElement.dialog;
    var fields;
    $(function ()
    {
        $.metadata.setType("attr", "validate");
        var fields;
        $.ajax({
            type:'post',
            url:'designer.do?getDisColumns&ajax=true&type=form',
            data:'id='+7,
            dataType:'json',
            async:false,
            success:function(result){
                fields = result;
            },
            error:function(){
                alert("出错了!~"+e);
            }
        });

        //创建表单结构
        var mainform = $("form");
        mainform.ligerForm({
            inputWidth: 170, labelWidth: 90, space: 40,
            fields:fields
        });
        var selected = parent.listgrid.getSelected();
        lab.loadForm(mainform, selected);
        var validator = $("form").validate({
            //调试状态，不会提交数据的
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
                $("#form1").ajaxSubmit({
                    url :"organizatiOn.do?doUpdate&ajax=true",
                    type : "POST",
                    dataType:"json",
                    success : function(result) {

                        if(result.success){
                            alert("增加成功!");
                            window.parent.listgrid.loadData();
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
    });

    function formSubmit(){
        $("#form1").submit();
    }
</script>



<form name="form1" method="post"   id="form1">


</form>

