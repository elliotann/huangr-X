function add(item)
{
    addOrUpdateDialog(item,'增加请假申请','oaLeave.do?goAdd',400,600);
}
function modify(item)
{

    var row = listgrid.getSelectedRow();
    if(row==null){
        $.ligerDialog.error('请选择数据修改!');
        return;
    }
    addOrUpdateDialog(item,'修改请假申请','oaLeave.do?goUpdate&id='+row.id,400,600);
}

function del(item)
{
    var row = listgrid.getSelectedRow();
    if(row==null){
        $.ligerDialog.error('请选择数据删除!');
        return;
    }
    delObj(item,'oaLeave.do?delete&id=',row.id);

}