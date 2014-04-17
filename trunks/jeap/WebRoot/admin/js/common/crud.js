//增加或者修改弹出框
function addOrUpdateDialog(item,title,url,height,width)
{
    $.ligerDialog.open({
        name:'openDia',
        height:height==undefined?600:height,
        width: width==undefined?800:width,
        title : title,
        url: url,
        showMax: false,
        showToggle: true,
        showMin: false,
        isResize: true,
        slide: false,
        buttons:[ { text: '确定', onclick: btnOK }, { text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
    });
}
//确定回调函数
function btnOK(item,dialog){
    openDia.submitForm();
}