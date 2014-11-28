function add(item)
{
    addOrUpdateDialog(item,'增加${ftl_description}','${entityName?uncap_first}.do?goAdd',400,600);
}
function modify(item)
{

    var row = listgrid.getSelectedRow();
    if(row==null){
        $.ligerDialog.error('请选择数据修改!');
        return;
    }
    addOrUpdateDialog(item,'修改${ftl_description}','${entityName?uncap_first}.do?goUpdate&id='+row.id,400,600);
}

function del(item)
{
    var row = listgrid.getSelectedRow();
    if(row==null){
        $.ligerDialog.error('请选择数据删除!');
        return;
    }
    delObj(item,'${entityName?uncap_first}.do?delete&id=',row.id);

}