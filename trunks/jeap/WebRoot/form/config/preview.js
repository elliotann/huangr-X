
$(init);

$.extend($.ligerDefaults.Grid, {
    rowHeight: 24,
    fixedCellHeight: false,
    frozen: false,
    async: true,
    headerRowHeight: 30,
    allowUnSelectRow: true
});
//去掉  大于小于包括,并改变顺序
$.ligerDefaults.Filter.operators['string'] =
    $.ligerDefaults.Filter.operators['text'] =
    ["like", "equal", "notequal", "startwith", "endwith"];

//扩展一个 数字输入 的编辑器
$.ligerDefaults.Grid.editors['numberbox'] = {
    create: function (container, editParm)
    {

        var column = editParm.column;
        var precision = column.editor.precision;
        var input = $("<input type='text' style='text-align:right' class='l-text' />");
        input.bind('keypress', function (e)
        {
            var keyCode = window.event ? e.keyCode : e.which;
            return keyCode >= 48 && keyCode <= 57 || keyCode == 46 || keyCode == 8;
        });
        input.bind('blur', function ()
        {
            var value = input.val();
            input.val(parseFloat(value).toFixed(precision));
        });
        container.append(input);
        return input;
    },
    getValue: function (input, editParm)
    {
        return parseFloat(input.val());
    },
    setValue: function (input, value, editParm)
    {
        var column = editParm.column;
        var precision = column.editor.precision;
        if(value==undefined){
            value=0;
        }
        input.val(value.toFixed(precision));
    },
    resize: function (input, width, height, editParm)
    {
        input.width(width).height(21);
    }
}; 
$.ligerDefaults.Grid.editors['string'] =
$.ligerDefaults.Grid.editors['text'] = {
    create: function (container, editParm)
    {
        var input = $("<input type='text' style='border:1px solid #d3d3d3;'/>");
        container.append(input);
        return input;
    },
    getValue: function (input, editParm)
    {
        return input.val();
    },
    setValue: function (input, value, editParm)
    {
        input.val(value);
    },
    resize: function (input, width, height, editParm)
    {
        input.width(width).height(21);
    }
};
$.ligerDefaults.Grid.editors['select'] =
{
    create: function (container, editParm)
    {
        var column = editParm.column;
        var input = $("<select></select");
        container.append(input);
        var data = column.editor.data;
        if (!data) return input;
        $(data).each(function ()
        {
            input.append('<option value="' + this.value + '">' + (this.text || this.name) + '</option>');
        });
        return input;
    },
    getValue: function (input, editParm)
    {
        return input.val();
    },
    setValue: function (input, value, editParm)
    {
        if (value)
            input.val(value);
    },
    resize: function (input, width, height, editParm)
    {
        input.css({ width: width, height: 22 });
    }
};


var root = "../../";
var fieldTypeData = [{ value: 'text', text: '文本框' }, { value: 'textarea', text: '多行文本框' }, { value: 'date', text: '日期控件' }, { value: 'select', text: '下拉框' }, { value: 'digits', text: '整数输入框' }, { value: 'number', text: '浮点数输入框' }, { value: 'hidden', text: '隐藏控件'}];
  




function createGridToolbar(tName)
{





    function showDetail(fields, data)
    {
        var form = $('<form></form> ');
        form.ligerForm({ fields: fields });
        $.ligerDialog.open({
            title: '预览 明细 界面',
            target: form,
            width: 850, height: 400, isResize: true,
            buttons: [{ text: '关闭', onclick: function (item, dialog) { dialog.hide(); } }]
        });
        lab.loadForm(form, data);
    }
   


    function translate()
    { 
        var list = [];
        for (var i = 0, l = grid.rows.length; i < l; i++)
        {
            var o = grid.rows[i];
            list.push(o.name);
        }
        lab.ajax({
            loading:'正在翻译中...',
            url: root + "handle/translate.ashx",
            data: { data: list.join(',') },
            success: function (results)
            {
                for (var i = 0, l = grid.rows.length; i < l; i++)
                {
                    grid.rows[i].display = results[i];
                }
                grid.reRender({ column: grid.columns[1] });
            }
        }); 
    }
    function moveup()
    { 
        var selected = grid.getSelected();
        if (!selected) return;
        grid.up(selected);
    }
    function movedown()
    { 
        var selected = grid.getSelected();
        if (!selected) return;
        grid.down(selected);
    }
    function addRow(){
        var manager = $("#gridP").ligerGetGridManager();
        manager.addRow({

        });


    }
}



//获取 表单和表格 结构 所需要的数据
function buildData(id)
{ 
    var griddata = [], searchdata= [], formdata= [];
    var rows = [];
    $.ajax({
        type:'post',
        url:'designer.do?getColumns&ajax=true',
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
            griddata.push({ display: o.display, name: o.name, width: o.listwidth });
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

//字段类型渲染器
function fieldTypeRender(r, i, value)
{
    for (var i = 0, l = fieldTypeData.length; i < l; i++)
    {
        var o = fieldTypeData[i];
        if (o.value == value) return o.text || o.name;
    }
    return "文本框";
}
//是否类型的模拟复选框的渲染函数
function checkboxRender(rowdata, rowindex, value, column)
{

    var iconHtml = '<div class="chk-icon';
    if (value) iconHtml += " chk-icon-selected";
    iconHtml += '"';
    iconHtml += ' rowid = "' + rowdata['__id'] + '"';
    iconHtml += ' gridid = "' + this.id + '"';
    iconHtml += ' columnname = "' + column.name + '"';
    iconHtml += '></div>';
    return iconHtml;
}
//是否类型的模拟复选框的点击事件
$("div.chk-icon").live('click', function ()
{
    var grid = $.ligerui.get($(this).attr("gridid"));
    var rowdata = grid.getRow($(this).attr("rowid"));
    var columnname = $(this).attr("columnname");
    var checked = rowdata[columnname];
     
    grid.updateCell(columnname, !checked, rowdata);
});

//搜索框 收缩/展开
$(".searchtitle .togglebtn").live('click', function ()
{
    if ($(this).hasClass("togglebtn-down")) $(this).removeClass("togglebtn-down");
    else $(this).addClass("togglebtn-down");
    var searchbox = $(this).parent().nextAll("div.searchbox:first");
    searchbox.slideToggle('fast');
});




function preview(id)
{

    //clear();

    var o = buildData(id);

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

    var searchform = $("form:first", listPanle);
    searchform.ligerForm({ fields: o.search });
    var listgrid = $(".listgrid:first", listPanle).ligerGrid({
        columns: o.grid,
        toolbar: listToolbar(), data: $.extend(true, {}, AllProductData),
        width: '98%', height: 400, checkbox: false
    });
    //搜索 按钮
    lab.appendSearchButtons(searchform, listgrid, false, function ()
    {
        listgrid.options.data = $.extend(true, {}, AllProductData);
    });

    $.ligerDialog.open({
        title: '预览 列表 界面',
        target: listPanle,
        width: 950, height: 530, isResize: true,
        buttons: [{ text: '关闭', onclick: function (item, dialog) { dialog.hide(); } }]
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

            showDetail(o.form);
        }

        function grid_edit()
        {
            clear();
            var selected = listgrid.getSelected();
            if (!selected) { lab.tip('请选择行'); return; }
            showDetail(o.form, selected);
        }

        function grid_delete()
        {
            listgrid.deleteSelectedRow();
        }

        function grid_search()
        {
            listgrid.options.data = $.extend(true, {}, AllProductData);
            listgrid.showFilter();
        }
    }
}




function init(){
    preview(7);

}