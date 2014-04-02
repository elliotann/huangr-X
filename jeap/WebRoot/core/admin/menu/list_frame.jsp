<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>

<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.8.3.js"></script>
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTab.js" type="text/javascript"></script>
<script type="text/javascript">
    var tab = null;
    $(function ()
    {

        $("#navtab1").ligerTab();
        tab = $("#framecenter").ligerGetTabManager();
    });
</script>

<div id="navtab1" style="width: 100%;overflow:hidden; border:1px solid #A3C0E8;height: 100% ">
    <div tabid="home" title="菜单管理" lselected="true"  style="height:470px" >
        <iframe frameborder="0" name="showmessage" src="menu.do?list"></iframe>
    </div>
</div>