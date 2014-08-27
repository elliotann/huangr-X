<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
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
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>

<script src="${staticserver}/js/common/jquery.validate.js" type="text/javascript"></script>
<link href="/jeap/admin/component/form/css/lab.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="/jeap/admin/component/form/js/ligerGrid.showFilter.js" type="text/javascript"></script>
<script src="/jeap/admin/component/form/js/lab.js" type="text/javascript"></script>
<script src="/jeap/admin/component/form/js/formold.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<script type="text/javascript">

    var groupicon = "${context }/js/ligerui/skins/icons/communication.gif";
    var dialog = frameElement.dialog;
    var tab = null;


    $(function ()
    {

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
                if(outjson()){
                    window.parent.listgrid.loadData();
                    dialog.close();//关闭dialog
                }
            }
        });


        $("#navtab1").ligerTab({ onAfterSelectTabItem: function (tabid){
            if(tabid!="dbColumn"){
                var manager = $("#dbColumnGrid").ligerGetGridManager();
                var changeManager = $("#"+tabid+"Grid").ligerGetGridManager();
                changeManager._setData(manager.getData());
            }



        }});
        tab = $("#framecenter").ligerGetTabManager();



    });

    function subForm(){
        $("#form1").submit();
    }
    function outjson()
    {

        var manager = $("#dbColumnGrid").ligerGetGridManager();
        var data = manager.getData();

        var jsonData = JSON.stringify(data);
        var tableName = document.getElementById("tableName").value; //调用父页面的元素
        var tableTitle = document.getElementById("tableTitle").value; //调用父页面的元素
        var formId =0;
        if(parent.document.getElementById("formId")!=null) {
            formId = parent.document.getElementById("formId").value
        }

        var jsonData = "{\"tableName\":\""+tableName+"\",\"tableTitle\":\""+tableTitle+"\",\"fields\":"+jsonData+"}";
        $.ajax({
            type: "post",
            url: "designer.do?save",
            data:"ajax=yes&rmd="+ new Date().getTime()+"&data="+jsonData+"&formId="+formId,
            dataType:"json",
            async:false,
            success: function(html){
                return true;
            },error:function(e){
                alert(e);
                return false;
            }
        });

        return true;
    }
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<body>
<form name="form1" method="post"   id="form1">
    <div>
    </div>
    <table cellpadding="0" cellspacing="0" class="l-table-edit" >
        <tr>
            <td align="right" class="l-table-edit-td">表单编码:</td>
            <td align="left" class="l-table-edit-td">
                <input name="code" type="text" id="code"   validate="{required:true,maxlength:30}" class="form-control"/>
            </td>
            <td align="right" class="l-table-edit-td"></td>
            <td align="left" class="l-table-edit-td">

            </td>
        </tr>
        <tr>
            <td align="right" class="l-table-edit-td">表名:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tableName" type="text" id="tableName"   validate="{required:true,maxlength:30}" class="form-control"/>
            </td>
            <td align="right" class="l-table-edit-td">注释:</td>
            <td align="left" class="l-table-edit-td">
                <input name="tableTitle" type="text" id="tableTitle"  validate="{required:true,maxlength:60}" class="form-control"/>
            </td>
        </tr>
    </table>
    <br />
    <div id="navtab1" style="width: 100%;overflow:hidden; height: 370px ">
        <div tabid="dbColumn" title="数据库字段" lselected="true"  style="height:470px" >
            <div id="dbColumnGrid" style="margin: 0; padding: 0"></div>

        </div>
        <div tabid="listPage" title="列表页设置" lselected="true"  style="height:470px" >
            <div id="listPageGrid" style="margin: 0; padding: 0"></div>
            <%--<iframe frameborder="0" name="showmessage" src="designer.do?dbFormEle" id="showmessage"></iframe>--%>
        </div>
        <div tabid="formPage" title="表单页设置" lselected="true"  style="height:470px" >
            <div id="formPageGrid" style="margin: 0; padding: 0"></div>
        </div>
    </div>
</form>
</body>

