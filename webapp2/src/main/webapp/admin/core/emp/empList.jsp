<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script src="/jeap/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<div class="main">
    <div id="useradmininfo" style="display: none;"></div>
    <form id="memberform">
        <div id="tb" style="height: auto">
            <a href="javascript:void(0)" class="button blueButton" data-options="iconCls:'icon-add',plain:true"
               onclick="append()">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton"
               data-options="plain:true" onclick="del()">删除</a>

			<span style="float: right;">
				<span id="simpleSearch">

					<input id="searchKeyword" class="input_text" type="text" value="" size="30" style="width: 300px;"
                           placeholder="请输入姓名，手机号" name="searchKeyWord" onFocus="WdatePicker({isShowClear:false,readOnly:true})">
					<a href="javascript:void(0)" class="easyui-linkbutton"
                       data-options="plain:true" onclick="searchMember()">搜索</a>
				</span>
				<a href="javascript:void(0)" class="button"
                   data-options="plain:true" id="aAdvanced">高级搜索</a>
			</span>
        </div>

        <div style="display: block;" class="searchAdvanced">
            <input id="Advanced" name="Advanced" type="hidden" value="0"/>
            <table width="98%" border="0" cellspacing="0" cellpadding="8">
                <tr>
                    <th width="70" align="right">姓名</th>
                    <td>
                        <input type="text" id="name" class="input_text">
                    </td>

                    <th width="70" align="right">手机</th>
                    <td>
                        <input type="text" value="" id="mobile" class="input_text">
                    </td>

                </tr>

                <tr>
                    <td width="60" align="right"></td>
                    <td colspan="7" align="center">
                        <a id="searchAdvance" class="button blueButton" onclick="searchMember()" href="javascript:;">开始搜索</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="clear height10"></div>
        <div class="shadowBoxWhite tableDiv">
            <table class="easyui-datagrid"
                   data-options="url:'emp.do?dataGrid&ajax=true',pageList: [5,10,15,20],fitColumns:'true'"
                   pagination="true" id="memberdata" sortName="member_id" sortOrder="desc" id="useradmindata">
                <thead>
                <tr>
                    <th data-options="field:'id',width:80,align:'center'">ID</th>
                    <th data-options="field:'name',width:100,align:'center'">员工姓名</th>
                    <th data-options="field:'empNo',width:80,align:'center'">员工编号</th>
                    <th data-options="field:'sex',width:80,align:'center'" formatter="formatSex">性别</th>
                    <th data-options="field:'tel',width:250,align:'center'">电话</th>
                    <th data-options="field:'mobile',width:250,align:'center'">手机</th>
                    <th data-options="field:'entryDate',width:250,align:'center'">入职日期</th>
                    <th data-options="field:'status',width:60,align:'center'" formatter="formatStatus">状态</th>
                </tr>
                </thead>
            </table>
        </div>
    </form>
    <div id="divdia" style="display: none;"></div>

</div>
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

    function formatSex(value, row, index) {
        var val;
        if (value == 'FEMALE') {
            val = "女";
        } else {
            val = "男";
        }
        return val;
    }

    function formatStatus(value, row, index) {
        if (value == 'ENTRY') {
            return "在职";
        } else {
            return "离职";
        }
    }

    function append() {
        $("#useradmininfo").show();
        $('#useradmininfo').dialog({
            title: '添加员工',
            top: 60,
            width: 600,
            height: 550,
            closed: false,
            cache: false,
            href: 'emp.do?goAdd',
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var savebtn = $(this);
                        var disabled = savebtn.hasClass("l-btn-disabled");
                        if (!disabled) {
                            addadminForm(savebtn);
                        }
                    }
                },
                {text: '取消', handler: function () {
                    $('#useradmininfo').dialog('close');
                }}
            ]});
    }
    function addadminForm(savebtn){
            var options = {
                url : "emp.do?addSave&ajax=true",
                type : "POST",
                dataType : "json",
                success : function(result) {
                    $("#useradmininfo").dialog('close');
                    $('#useradmindata').datagrid('reload');
                    savebtn.linkbutton("enable");
                },
                error : function(e) {
                    $.Loading.error("出现错误 ，请重试");
                    savebtn.linkbutton("enable");
                }
            };
            $('#objForm').ajaxSubmit(options);

    }
</script>
