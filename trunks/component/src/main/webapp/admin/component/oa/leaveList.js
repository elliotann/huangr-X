$(init);
    var listgrid;
    $.extend($.ligerDefaults.Grid, {
    rowHeight: 24,
    fixedCellHeight: false,
    frozen: false,
    async: true,
    headerRowHeight: 30,
    allowUnSelectRow: true
});

function init()
{
    bulidMainGrid();
}


function bulidMainGrid()
{
    var rows = [];
    $('<div style="margin:7px;" id="margin"></div>').appendTo('body');
    var o = bulidData(9);
    var out = [];
    out.push('<div>');
    out.push('  <div style=" width:98%">');
    out.push('      <div class="searchtitle">');
    out.push('          <span>搜索</span><img src="../icons/searchtool.gif" />');
    out.push('          <div class="togglebtn"></div> ');
    out.push('      </div>');
    out.push('      <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>');
    out.push('      <div class="searchbox">');
    out.push('          <form></form>');
    out.push('      <div class="l-clear"></div>');
    out.push('      </div>');
    out.push('  </div>');
    out.push('  <div class="listgrid"></div> ');
    out.push('</div>');
    var listPanle = $(out.join(''));
    listPanle.appendTo('body');
    var searchform = $("form:first", listPanle);
    searchform.ligerForm({ fields: o.search });
    listgrid = $(".listgrid:first", listPanle).ligerGrid({
        columns: o.grid,
        toolbar: listToolbar(), url:'leave.do?dataGrid&ajax=true',
        width: '98%', height: '96%', checkbox: false
    });
//搜索 按钮
lab.appendSearchButtons(searchform, listgrid, false, function ()
{
    listgrid.options.data = $.extend(true, {}, AllProductData);
});
function listToolbar()
{
    var items = [];
    items.push({ text: '增加', click: grid_add, img: "../icons/page_add.png" });
    items.push({ text: '修改', click: grid_edit, img: "../icons/edit.gif" });
    items.push({ text: '删除', click: grid_delete, img: "../icons/delete.png" });
    items.push({ text: '高级自定义查询', click: grid_search, icon: 'search2' });
    return { items: items };

function grid_add()
{
    clear();

    $.ligerDialog.open({
        name:'openDia',
        height:500,
        width: 1000,
        title : '增加请假信息',
        url: 'leave.do?goAdd',
        showMax: false,
        showToggle: true,
        showMin: false,
        isResize: true,
        slide: false,
        buttons:[ { text: '确定', onclick: btnOK }, { text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
    });
}
function btnOK(item,dialog){
    openDia.formSubmit();
}
function grid_edit()
{
    clear();
    var selected = listgrid.getSelected();
    if (!selected) { lab.tip('请选择行'); return; }
    $.ligerDialog.open({
        name:'openDia',
        height:500,
        width: 1000,
        title : '修改请假信息',
        url: 'leave.do?goUpdate&sid='+selected.sid,
        showMax: false,
        showToggle: true,
        showMin: false,
        isResize: true,
        slide: false,
        buttons:[ { text: '确定', onclick: btnOK }, { text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
    });
}

function grid_delete()
{
    var row = listgrid.getSelectedRow();
    if(row==null){
        $.ligerDialog.error('请选择数据删除!');
        return;
    }

    $.ligerDialog.confirm('确定删除？', function (yes) {
        if(yes){
            $.ajax({
            type: "GET",
            url: "leave.do?delete&sid="+row.sid,
            data:"ajax=true&rmd="+ new Date().getTime(),
            dataType:"json",
            success: function(result){
                if(result.success){
                    $.ligerDialog.alert('删除成功!', '提示', type);
                    listgrid.loadData();
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

function grid_search()
{
    listgrid.options.data = $.extend(true, {}, listgrid.getData());
    listgrid.showFilter();
}
}
function clear()
{
var managers = $.ligerui.find($.ligerui.controls.Input);
for (var i = 0, l = managers.length; i < l; i++)
{
if (exits(managers[i].id))
{
managers[i].destroy();
}
}
}
function exits(id)
{
for (var i = 0, l = listgrid.rows.length; i < l; i++)
{
var name = listgrid.rows[i].name;
if (name == id) return true;
}
return false;
}
}




//获取 表单和表格 结构 所需要的数据
function bulidData(id)
{
    var griddata = [], searchdata= [], formdata= [];
    var rows = [];
    $.ajax({
        type:'post',
        url:'designer.do?getDisColumns&ajax=true',
        data:'id='+id,
        dataType:'json',
        async:false,
        success:function(result){
            rows = result;
        },
        error:function(e){
            alert("出错了!~"+e);
        }
});

for (var i = 0, l = rows.length; i < l; i++)
{
    var o = rows[i];
    if (o.inlist)
        griddata.push({ display: o.labelName, name: o.fieldName, width: parseInt(o.listwidth) });
if (o.insearch)
searchdata.push(getFieldData(o, true));
if (o.inform)
    formdata.push(getFieldData(o));
}
return { grid: griddata, search: searchdata, form: formdata };

function getFieldData(o, search)
{
if (o.type == "hidden") return { name: o.name, type: o.type };
var field = {
display: o.display,
name: o.name,
newline: o.newline,
labelWidth: o.labelwidth || o.labelWidth,
width: o.width,
space: o.space,
type: o.type
};
if (!search)
{
field.validate = getValidate(o);
field.group = o.group;
field.groupicon = "../icons/communication.gif";
}
else
{
field.cssClass = "field";
field.newline = o.search_newline ? true : false;
}
return field;
}
function getValidate(o)
{
if (o.validate) return o.validate;
if (!o.allownull) return { required: true };
return null;
}
}

//搜索框 收缩/展开
$(".searchtitle .togglebtn").live('click', function ()
{
if ($(this).hasClass("togglebtn-down")) $(this).removeClass("togglebtn-down");
else $(this).addClass("togglebtn-down");
var searchbox = $(this).parent().nextAll("div.searchbox:first");
searchbox.slideToggle('fast');
}); 