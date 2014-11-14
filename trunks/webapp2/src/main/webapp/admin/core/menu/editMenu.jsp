<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script src="${staticserver}/js/common/jquery.validate.js" type="text/javascript"></script>
<script src="${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<link href="${context }/js/easyui/themes/icon.css" rel="stylesheet"/>
<script type="text/javascript">



    $(function ()
    {

        $("#objForm").validate({
            rules:{
                title:{
                    required:true,
                    maxlength:60
                },
                password:{
                    required:true,
                    minlength:6,
                    maxlength:18
                }
            },

            submitHandler: function ()
            {

                $("#objForm").ajaxSubmit({
                    url :"menu.do?saveEdit&ajax=true",
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
                title: {
                    required: "标题不能为空",
                    maxlength:"标题最大60个字符"
                }
            }
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

</style>
<form name="objForm" method="post"   id="objForm" enctype="multipart/form-data">
    <input type="hidden" name="menuId" value="${menu.id}" />
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">标题:</td>
            <td align="left" class="l-table-edit-td">
                <input name="title" type="text" id="title" ltype="text" value="${menu.title}"  validate="{required:true,maxlength:60}" class="form-control" />
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">类型:</td>
            <td align="left" class="l-table-edit-td">
                <input id="appMenuType" type="radio" name="menutype" value="2" <c:if test="${menu.menutype==2}">checked="checked"</c:if>  style="margin-left: 7px;"/><label for="appMenuType">应用</label>
                <input id="sysMenuType" type="radio" name="menutype" value="1" <c:if test="${menu.menutype==1}">checked="checked"</c:if>  style="margin-left: 7px;"/><label for="sysMenuType">系统</label>


            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">上级菜单:</td>
            <td align="left" class="l-table-edit-td">
                <select id="pid" name="pid" class="easyui-combotree combo" data-options="url:'menu.do?addOrUpdateGrid&ajax=true',method:'get'" style="width:206px;height:30px;" value="${menu.pid }"></select>

            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">url:</td>
            <td align="left" class="l-table-edit-td">
                <input name="url" type="text" id="url" ltype="text" value="${menu.url}" validate="{required:true}"  class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">target:</td>
            <td align="left" class="l-table-edit-td">
                <input name="target" type="text" id="target" ltype="text" value="${menu.target}"  class="form-control"/>
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">排序:</td>
            <td align="left" class="l-table-edit-td">
                <input name="sorder" type="text" id="sorder" ltype="text" validate="{required:true}" value="0" value="${menu.sorder}"  class="form-control"/>
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