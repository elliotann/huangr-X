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
                    url :"menu.do?saveEdit&ajax=true",
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


    function submitForm(){
        $("#form1").submit();
    }
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<form name="form1" method="post"   id="form1" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${menu.id}" />
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
                <select  id="menutype" name="menutype" ltype="select">
                    <option value="2" <c:if test="${menu.menutype==2}">selected="true"</c:if>>应用</option>
                    <option value="1" <c:if test="${menu.menutype==1}">selected="true"</c:if>>系统</option>
                </select>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">上级菜单:</td>
            <td align="left" class="l-table-edit-td">
                <select  id="pid" name="pid" ltype="select">
                    <option value="0">顶级菜单</option>
                    <c:forEach var="menu1" items="${menuList }">

                        <option value="${menu1.id}" <c:if test="${menu.pid == menu1.id}">selected="true"</c:if> >${menu1.title}</option>
                    </c:forEach>
                </select>
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
                <input name="icoFile" type="file" id="icoFile" ltype="file" validate="{required:true}" value="0"/>
            </td>
            <td align="left"></td>
        </tr>
    </table>
</form>

