<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script src="${ctx }/statics/js/common/jquery.validate.js" type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>

<script type="text/javascript">
    var dialog = frameElement.dialog;
    $(function (){
        $("#objForm").validate({
            rules:{
                rolename:{
                    required:true,
                    minlength:3,
                    maxlength:10,
                    remote:{
                        url:'role.do?checkNameExist&ajax=true',
                        type:'post',
                        dataType:'json',
                        data:{
                            rolename:function(){return $("#rolename").val();},
                            roleid:function(){return $("#roleid").val();}
                        }

                    }
                }
            },
            submitHandler: function ()
            {

                var roleid = $("#roleid").val();
                var url = "role.do?saveAdd";
                if(roleid!=0){
                    url = "role.do?saveEdit";
                }

                $("#objForm").ajaxSubmit({
                    url :url,
                    type : "POST",
                    dataType:"json",
                    success : function(result) {
                        if(result.success){
                            $("#dialogInfo").dialog('close');
                            $('#dataGrid').datagrid('reload');
                             
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
                rolename:{
                    required:"请输入角色名称",
                    remote:"角色名已经存在!",
                    minlength:"角色名称最小3个字符",
                    maxlength:"角色名称最长10个字符"
                }
            }
        });


        //AuthAction.init();
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
<form name="objForm" method="post"   id="objForm">
    <input type="hidden" name="id" id="roleid" value="${role.id==null?0:role.id }" />
    <input type="hidden" name="ajax"  value="true" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">角色名称:</td>
            <td align="left" class="l-table-edit-td">
                <input name="rolename" type="text" id="rolename" value="${role.rolename}" class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">描述：</td>
            <td align="left" class="l-table-edit-td">
                <textarea name="rolememo" cols="100" rows="3" class="form-control" id="rolememo" style="width:400px;height: 150px;">${role.rolememo}</textarea>
            </td>
            <td align="left"></td>
        </tr>

    </table>
</form>