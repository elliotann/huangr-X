<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/jeap/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<script type="text/javascript">

    $(function () {
        $(".searchAdvanced").hide();
        //高级查询按钮
        $("#aAdvanced").click(function () {
            if ($("#Advanced").val() == "0") {
                $("#Advanced").val(1);
                $("#simpleSearch").hide();
                //$("#aAdvanced").text("简单搜索")
                $("#aAdvanced").addClass("searchAdvancedS");
            } else {
                $("#Advanced").val(0);
                $("#simpleSearch").show();
                //$("#aAdvanced").text("高级搜索");
                $("#aAdvanced").removeClass("searchAdvancedS");
            }
            $(".searchAdvanced").slideToggle("slow");
        });
    });


    function searchMember(){

        var operator = $("#operator").val();
        $("#dataGrid").datagrid('load', {
            operator:operator,
            page:1
        });
    }
</script>
<grid:dataGrid action="buslog.do?dataGrid&ajax=yes" height="99%"  rownumbers="true" hasSearchBar="true" style="easyui">
    <grid:search label="操作员" name="operator" shortSearch="true"/>
    <grid:column title="ID" field="id" align="center" width="100" minWidth="60"/>
    <grid:column title="操作员" field="operator"  minWidth="120"/>
    <grid:column title="操作时间" field="logTime"  minWidth="140"/>
    <grid:column title="系统" field="system"  minWidth="140"/>
    <grid:column title="操作内容" field="message"  minWidth="140"/>
</grid:dataGrid>





