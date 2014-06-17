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


function delObj(item,url,id)
{
    url = url+id;

    $.ligerDialog.confirm('确定删除？', function (yes) {
        if(yes){
            $.ajax({
                type: "GET",
                url: url,
                data:"ajax=true&rmd="+ new Date().getTime(),
                dataType:"json",
                success: function(result){
                    if(result.success){
                        $.ligerDialog.waitting('正在删除中,请稍候...');
                        setTimeout(function ()
                        {
                            $.ligerDialog.closeWaitting();
                            listgrid.loadData();
                        }, 1000);

                    }else{
                        $.ligerDialog.alert(result.msg, '提示', type);

                    }
                },error:function(e){
                    $.ligerDialog.alert('出错了!', '提示', type)

                }
            });
        }
    });



}