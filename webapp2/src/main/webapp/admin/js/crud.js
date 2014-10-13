//增加或者修改弹出框
function addOrUpdateDialog(title,url,height,width)
{
    $("#dialogInfo").show();
    $('#dialogInfo').dialog({
        title:title,
        top: 60,
        width: width==undefined?800:width,
        height: height==undefined?600:height,
        closed: false,
        cache: false,
        href:url,
        modal: true,
        buttons: [
            {
                text: '保存',
                iconCls: 'icon-ok',
                handler: function () {
                    var savebtn = $(this);
                    var disabled = savebtn.hasClass("l-btn-disabled");
                    if (!disabled) {
                        addForm(savebtn);
                    }
                }
            },
            {text: '取消', handler: function () {
                $('#dialogInfo').dialog('close');
            }}
        ]});

}
//确定回调函数
function addForm(savebtn){
    $("#objForm").submit();
}


function delObj(url,id)
{
   url += "&ajax=true&rmd="+ new Date().getTime();
    var options = {
        url : url,
        type : "POST",
        dataType : 'json',
        success : function(result) {
            if(result.success){
                alert("删除成功");
                $('#dataGrid').datagrid('reload');
            }

        },
        error : function(e) {
            $.Loading.error("出现错误 ，请重试");
        }
    };
    $('#dataGridform').ajaxSubmit(options);
}

function delObj4Tree(url,id)
{
    url += "&ajax=true&rmd="+ new Date().getTime();
    var options = {
        url : url,
        type : "POST",
        dataType : 'json',
        success : function(result) {
            if(result.success){
                alert("删除成功");
                $('#dataGrid').treegrid('reload');
            }

        },
        error : function(e) {
            $.Loading.error("出现错误 ，请重试");
        }
    };
    $('#dataGridform').ajaxSubmit(options);
}