<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx}/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/admin/js/crud.js" type="text/javascript"></script>
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

    function modifyRole()
    {

        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];
        addOrUpdateDialog("修改角色",'role.do?edit&roleid='+row.id,350,600);


    }
    function delRole()
    {
        var rows = $('#dataGrid').datagrid("getSelections");
        if (rows.length < 1||rows.length>1) {
            alert("请选择要删除的会员");
            return;
        }
        if (!confirm("确认要将删除会员吗？")) {
            return;
        }
        var row = rows[0];
        delObj("role.do?delete&id="+row.id);



    }

    function setAuth(){
        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];

        $("#dialogInfo").show();
        $('#dialogInfo').dialog({
            title: '设置权限',
            top: 60,
            width: 700,
            height: 550,
            closed: false,
            cache: false,
            href:'auth.do?add&ajax=yes&roleId='+row.id,
            modal: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: 'icon-ok',
                    handler: function () {
                        var savebtn = $(this);
                        var disabled = savebtn.hasClass("l-btn-disabled");
                        if (!disabled) {
                            addAuthForm(savebtn);
                        }
                    }
                },
                {text: '取消', handler: function () {
                    $('#dialogInfo').dialog('close');
                }}
            ]});

    }

    function addRole() {
        addOrUpdateDialog("增加角色","role.do?add",350,600);
    }

    function addAuthForm(savebtn){
        var json="[";
        $.each($("#authDataGrid").treegrid("getChecked"),function(i,v){
            json += "{\"roleId\":"+$("#roleId").val()+",\"funId\":"+ v.id+",\"operids\":\"";
            $.each($("input[name='btn"+ v.id+"']:checked"),function(index,vl){
                json += $(this).val();
                if($("input[name='btn"+ v.id+"']:checked").length!=index+1){
                    json += ",";
                }
            });
            json +="\"}";


            if($("#authDataGrid").treegrid("getChecked").length!=i+1){
                json += ",";
            }
        });
        json += "]";
        var row = $('#dataGrid').datagrid('getSelections')[0];
        var param = {menuIds:json};
        //保存权限
        $.ajax({
            type: 'post',
            url: "auth.do?saveAuth&ajax=true",
            dataType:'json',
            data:param,
            success:function(result){
                if(result.success){
                    $('#dialogInfo').dialog('close');
                }
            },
            error:function(e){
                alert(e);
            }
        });
        $("#objForm").submit();

    }

    function searchMember(){

        var rolename = $("#rolename").val();
        $("#dataGrid").datagrid('load', {
            rolename:rolename,
            page:1
        });
    }
</script>
<grid:dataGrid action="role.do?dataGrid&ajax=yes" height="99%"  rownumbers="true" hasSearchBar="true" style="easyui">
    <grid:search label="请输入角色名称" name="rolename" shortSearch="true"/>
    <grid:column title="ID" field="id" align="center" width="100" minWidth="60"/>
    <grid:column title="角色名称" field="rolename"  minWidth="120"/>
    <grid:column title="描述" field="rolememo"  minWidth="140"/>
    <grid:toolbar title="增加" clickFun="addRole" icon="add"/>
    <grid:toolbar title="修改" clickFun="modifyRole" icon="modify"/>
    <grid:toolbar title="删除" clickFun="delRole" icon="delete"/>
    <grid:toolbar title="设置权限" clickFun="setAuth" icon="modify"/>
</grid:dataGrid>





