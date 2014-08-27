$.ligerDefaults.Grid.editors['text'] = {
    create: function (container, editParm)
    {
        var input = $("<input type='text' style='border:1px solid #d3d3d3;'/>");
        if(editParm.record.fieldName=="id"){
            input = $("<input type='text' style='border:1px solid #d3d3d3;' readonly/>");
        }
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
var dataTypes = [
    {value:'INTEGER',text:'INTEGER'},
    { value:'VARCHAR', text:'VARCHAR' },
    { value:'INT', text:'INT' },
    { value:'DATE', text:'DATE' }

];
var fieldTypeData = [
    { value:'text', text:'文本框' },
    { value:'textarea', text:'多行文本框' },
    { value:'date', text:'日期控件' },
    { value:'select', text:'下拉框' },
    { value:'digits', text:'整数输入框' },
    { value:'number', text:'浮点数输入框' },
    { value:'hidden', text:'隐藏控件'}
];
var columns;
function init()
{
    bulidMainGrid();

}

function bulidMainGrid(){
    var formId=0;
    if(document.getElementById("formId")!=null){
        formId = document.getElementById("formId").value;
    }
    var rows = [];
    $.ajax({
        type:'post',
        url:'designer.do?getColumns&ajax=true&id='+formId,
        dataType:'json',
        async:false,
        success:function(result){

            columns = result;
        },
        error:function(e){
            alert("出错了!");
        }

    });
    $(columns).each(function ()
    {
        var row = {
            fieldName: this.fieldName,
            labelName: this.labelName,
            dataTypeLength:this.dataTypeLength,
            listwidth: 180,
            type: this.displayType,
            width: 220,
            labelwidth: 100,
            space: 30,
            newline: this.newline,
            search_newline : false,
            inlist: this.inlist,
            insearch: false,
            inform: this.inform,
            SourceTableName: this.sourceTableName,
            SourceTableIDField: this.sourceTableIDField,
            SourceTableTextField: this.sourceTableTextField
        };

        row.isNullable = this.isNullable ? true : false;
        row.ispk = this.ispk?true:false;
        row.displayType = this.displayType;
        row.dataType = this.dataType;
        if (this.isAutoKey || this.isInForeignKey)
        {
            row.inlist = false;
            row.type = "hidden";
        }
        if (row.SourceTableName)
        {
            row.type = "select";
        }
        if (this.isAutoKey)
        {
            row.insearch = false;
        }
        rows.push(row);
    });
    $("#dbColumnGrid").ligerGrid({
        columns: [
            {display: '字段名', name: 'fieldName',align: 'center', width: 75, minWidth: 30,editor: { type: 'text'} } ,
            { display: '显示名', name: 'labelName', width: 150,editor:{type:'text'} },
            { display: '是否主键', name: 'ispk', width: 75, align: 'left',render: checkboxRender },
            { display: '允许空值', name: 'isNullable', width: 75,render:checkboxRender },
            { display: '数据类型', name: 'dataType',width:75, editor: { type: 'select', data: dataTypes }, render: dataTypeRender },
            { display: '长度', name: 'dataTypeLength', align: 'right', width: 50, minWidth: 30, editor: { type: 'numberbox'} }
        ],data: { rows: rows }, usePager: false, toolbar: createGridToolbar(),
        enabledEdit: true, clickToEdit: true, fixedCellHeight: false, inWindow: true, rownumbers: true,
        width: '98%', height: '100%',heightDiff:-14, rowHeight: 24
    });

    $("#listPageGrid").ligerGrid({
        columns: [
            {display: '字段名', name: 'fieldName', align: 'center', width: 75 } ,
            { display: '显示名', name: 'labelName', width: 75 },
            { display: '列表显示', name: 'inlist', width: 55, render: checkboxRender },
            { display: '列表宽度', name: 'listwidth', align: 'right', width: 50, minWidth: 30, editor: { type: 'numberbox'} },
            { display: '搜索显示', name: 'insearch', width: 55, render: checkboxRender },
            { display: '是否新行', name: 'search_newline', width: 55, render: checkboxRender }
        ],data: { rows: rows }, usePager: false,
        enabledEdit: true, clickToEdit: true, fixedCellHeight: false, inWindow: true, rownumbers: true,
        width: '98%', height: '100%',heightDiff:-14, rowHeight: 24
    });

    $("#formPageGrid").ligerGrid({
        columns: [
            {display: '字段名', name: 'fieldName', align: 'center', width: 75 } ,
            { display: '显示名', name: 'labelName', width: 75 },
            {display: '表单显示', name: 'inform', align: 'left', width: 75,render:checkboxRender } ,
            { display: '控件类型', name: 'displayType', width: 75, editor: { type: 'select', data: fieldTypeData }, render: fieldTypeRender },
            { display: '标签宽度', name: 'labelwidth', width: 75, align: 'left',editor: { type: 'numberbox'} },
            { display: '控件宽度', name: 'width', width: 75, editor: { type: 'numberbox'}  },
            { display: '间隔宽度', name: 'space',width:75, editor: { type: 'numberbox'}  },
            { display: '是否新行', name: 'newline',width:75,render:checkboxRender },
            { display: '关联表单', name: 'group', width: 100, editor: { type: 'text'}}
        ], data: { rows: rows }, usePager: false,
        enabledEdit: true, clickToEdit: true, fixedCellHeight: false, inWindow: true, rownumbers: true,
        width: '98%', height: '100%',heightDiff:-14, rowHeight: 24
    });
}

function createGridToolbar()
{
    var items = [];
    items.push({ text: '新增行', click: addRow, ico: "/jeap/admin/component/form/images/add.gif" });
    items.push({ text: '删除行', click: deleteRow, img: "/jeap/admin/component/form/images/add.gif" });
    items.push({ text: '上移', click: moveup, img: "/jeap/admin/component/form/images/sign_up.gif" });
    items.push({ text: '下移', click: movedown, img: "/jeap/admin/component/form/images/arrow_down.gif" });
    return { items: items };

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
        for (var i = 0, l = window.grid.rows.length; i < l; i++)
        {
            var name = window.grid.rows[i].name;
            if (name == id) return true;
        }
        return false;
    }

    function preview()
    {

        clear();

        var o = bulidData();

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
                clear();
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

    function moveup(){
        var manager = $("#dbColumnGrid").ligerGetGridManager();
        var selected = manager.getSelected();
        if (!selected) return;
        var manager = $("#dbColumnGrid").ligerGetGridManager();
        manager.up(selected);
    }
    function movedown(){
        var manager = $("#dbColumnGrid").ligerGetGridManager();
        var selected = manager.getSelected();
        if (!selected) return;

        manager.down(selected);
    }
    function addRow(){
        var manager = $("#dbColumnGrid").ligerGetGridManager();
        //alert(JSON.stringify(manager.getData()));
        manager.addRow({
        });
    }
    function deleteRow()
    {
        var manager = $("#dbColumnGrid").ligerGetGridManager();
        manager.deleteSelectedRow();
    }
}
//是否类型的模拟复选框的渲染函数
function checkboxRender(rowdata, rowindex, value, column)
{

    var iconHtml = '<div class="chk-icon';

    if(rowdata.ispk&&column.name=="isNullable"){
        iconHtml += " chk-icon-display";
    }else if(value){
        iconHtml += " chk-icon-selected";
    }
    iconHtml += '"';
    iconHtml += ' rowid = "' + rowdata['__id'] + '"';
    iconHtml += ' gridid = "' + this.id + '"';
    iconHtml += ' columnname = "' + column.name + '"';
    iconHtml += '></div>';
    return iconHtml;
}

//字段类型渲染器
function dataTypeRender(r, i, value)
{
    for (var i = 0, l = dataTypes.length; i < l; i++)
    {
        var o = dataTypes[i];
        if (o.value == value) return o.text || o.name;
    }
    return "INTEGER";
}
//表单字段类型渲染器
function fieldTypeRender(r, i, value)
{

    for (var i = 0; i < fieldTypeData.length; i++)
    {
        var o = fieldTypeData[i];
        if (o.value == value) return o.text || o.name;
    }
    return "文本框";
}

//是否类型的模拟复选框的点击事件
$("div.chk-icon").live('click', function ()
{

    var grid = $.ligerui.get($(this).attr("gridid"));
    var rowdata = grid.getRow($(this).attr("rowid"));
    var columnname = $(this).attr("columnname");
    var checked = rowdata[columnname];
    if(rowdata.fieldName=="id"){
        return;
    }
    grid.updateCell(columnname, !checked, rowdata);
    if(columnname=="ispk"){
        grid.updateCell("isNullable", false, rowdata);
    }

});

$(function (){

    init();
});
