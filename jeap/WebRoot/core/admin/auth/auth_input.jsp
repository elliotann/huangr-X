<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerRadio.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.validate.min.js"></script>
<script src="${context }/js/plug-in/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${context }/js/plug-in/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
<script src="/jeap/admin/js/common/crud.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
<script type="text/javascript" src="/jeap/admin/menu.do"></script>
<script type="text/javascript">
    var dialog1 = frameElement.dialog;
    var manager = null;
    $(function (){

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
                $("#objForm").ajaxSubmit({
                    url :"userAdmin.do?addSave&ajax=true",
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

        manager = $("#tree1").ligerGetTreeManager();
        createTree();
        $("#tree1").ligerTree({onAfterAppend:function(){collapseAll();} });
    });

    function createTree(){
        $.each(menu.app,function(k,v){
            var li = createNode(v);
            var ul = createChildren(v.children);
            li.append(ul);

            $("#tree1").append(li);
        });

    }

    function submitForm(){
        $("#objForm").submit();
    }

    /**
     根据menu json创建菜单节点
     */
    function createNode(v){
        var li = $("<li><span>"+v.text+"</span></li>");
        return li;
    }

    function createChildren(menuAr){
        var ul=$("<ul></ul>");
        var self = this;
        $.each(menuAr,function(k,v){
            var li = self.createNode(v);

            //如果有子则递归
            var children =v.children;
            if(children && children.length>0){
                li.append(self.createChildren(children));
            }
            ul.append(li);
        });
        return ul;
    }

</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>


<form name="objForm" method="post"   id="objForm">
    <input type="hidden" name="isEdit" id="isEdit" value="${isEdit }" />
    <input type="hidden" name="authid" value="${auth.actid}" />
    <input type="hidden" id="objvalue" value="${auth.objvalue }" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >

        <tr>
            <td align="right" class="l-table-edit-td">菜单：</td>
            <td align="left" class="l-table-edit-td" >

                    <ul id="tree1">

                    </ul>


            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>




