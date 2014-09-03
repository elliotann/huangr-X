<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link href=" ${context}/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href=" ${context}/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
<script src="/jeap/statics/js/common/jquery-1.6.4.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src=" ${context}/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="/jeap/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/jeap/statics/js/common/jquery.validate.js" type="text/javascript"></script>
<script src=" ${staticserver}/js/admin/jeap.js" type="text/javascript"></script>
<link href=" ${context}/css/form.css" rel="stylesheet"/>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">

<script type="text/javascript">


    var dialog = frameElement.dialog;

    $(function (){


        $("#toolbar").ligerToolBar({ items: [
            { text: '增加公司', click: add , icon:'add'},
            { text: '修改公司', click: update , icon:'modify'},
          { text: '增加部门', click: addDepart , icon:'add'}
                ]});
    });
    function add(item){
        addOrUpdateDialog(item,'增加公司','organization.do?goAdd&pid='+$("#pid").val()+"&orgType=COMPANY",500,700);
    }
    function update(item){
        addOrUpdateDialog(item,'修改公司','organization.do?goUpdate&pid='+$("#pid").val()+"&orgType=COMPANY",500,700);
    }
    function addDepart(item){
        addOrUpdateDialog(item,'增加部门','organization.do?goAdd&pid='+$("#pid").val()+"&orgType=DEPT&from=COMPANY",500,700);
    }


</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<div id="toolbar"></div>
<div>
    <input type="hidden" name="pid" value="${pid}" id="pid"/>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">公司编码:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.compNo}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司英文简写:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.enShortName}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司名称:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.name}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">公司地址:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.address}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">法人:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.legalPerson}
            </td>
            <td align="left"></td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">电话:</td>
            <td align="left" class="l-table-edit-td">
                ${organization.tel}
            </td>
            <td align="left"></td>
        </tr>

    </table>
</div>
