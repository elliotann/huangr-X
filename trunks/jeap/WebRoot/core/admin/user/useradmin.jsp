<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<link href="${context}/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context}/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<link href="${context}/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=" ${context}/js/plug-in/jquery/jquery-1.3.2.min.js"></script>
<script src=" ${context }/js/ligerui/js/ligerui.all.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerFilter.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/grid/ligerGrid.showFilter.js" type="text/javascript"></script>
<script src="${context}/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="/jeap/adminthemes/default/js/ligerui/js/grid/CustomersData.js" type="text/javascript"></script>
<script src="/jeap/form/config/lab.js" type="text/javascript"></script>
<link href="/jeap/admin/component/cms/lab.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/admin/js/common/crud.js" type="text/javascript"></script>

<script type="text/javascript">
    var listgrid;
    //去掉  大于小于包括,并改变顺序
    $.ligerDefaults.Filter.operators['string'] =
            $.ligerDefaults.Filter.operators['text'] =
                    ["like" , "equal", "notequal", "startwith", "endwith" ];
    $(function ()
    {

        listgrid =
                $("#maingrid").ligerGrid({
                    height:'99%',
                    columns: [
                        { display: 'id', name: 'userid', align: 'left', width: 100, minWidth: 60 },
                        { display: '用户名', name: 'username', minWidth: 120 },
                        { display: '姓名', name: 'realname', minWidth: 140 },
                        { display: '状态', name: 'state',render:function(rowdata, index, value){
                            if(value==1){
                                return "启用";
                            } else{
                                return "禁用";
                            }
                        } }
                    ], url:'userAdmin.do?dataGrid&ajax=yes',  pageSize:10 ,rownumbers:true,pagesizeParmName:'pageSize',
                            toolbar: { items: [
                        { text: '增加', click: addUser, icon: 'add' },
                        { line: true },
                        { text: '修改', click: modifyUser, icon: 'modify' },
                        { line: true },
                        { text: '删除', click: delUser, img: '${context }/js/ligerui/skins/icons/delete.gif' },
                        { line: true },
                        { text: '高级自定义查询', click: itemclick, icon: 'search2' }
                    ]
                    }
                });
        //创建表单结构
       $("#searchForm").ligerForm({
            fields: [
                { display: "用户名", name: "username",  type: "text" }
            ]
        });
        var AllProductData;
        //搜索 按钮
        lab.appendSearchButtons($("#searchForm"), listgrid, false, function ()
        {

            listgrid.options.data = $.extend(true, {}, AllProductData);
        });
        //搜索框 收缩/展开
        $(".searchtitle .togglebtn").live('click', function ()
        {
            if ($(this).hasClass("togglebtn-down")) $(this).removeClass("togglebtn-down");
            else $(this).addClass("togglebtn-down");
            var searchbox = $(this).parent().nextAll("div.searchbox:first");
            searchbox.slideToggle('fast');
        });
    });
    function itemclick()
    {
        listgrid.options.data = $.extend(true,{}, CustomersData);
        listgrid.showFilter();
    }
    function addUser(item)
    {
        addOrUpdateDialog(item,'增加管理员','userAdmin.do?add',500,700);
    }
    function modifyUser(item)
    {

        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据修改!');
            return;
        }
        addOrUpdateDialog(item,'修改管理员','userAdmin.do?edit&id='+row.userid,500,700);

    }
    function delUser(item)
    {
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }

        delObj(item,"userAdmin.do?delete&id=",row.userid);
    }
</script>

<div class="grid">
    <div>
        <div style=" width:100%">
            <div class="searchtitle">
                <span>搜索</span><img src="/jeap/core/icons/searchtool.gif" />
                <div class="togglebtn"></div>
            </div>
            <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
            <div class="searchbox">
                <form id="searchForm">


                </form>
                <div class="l-clear"></div>
            </div>
        </div>

    </div>
    <div id="maingrid"></div>
</div>
<div style="display: none;">
</div>