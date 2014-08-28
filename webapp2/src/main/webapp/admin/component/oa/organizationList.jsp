<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
<script type="text/javascript">
    var treeManager = null;
    $(function ()
    {
        $("#layout1").ligerLayout({ leftWidth: 200});
        $("#tree2").ligerTree({
            checkbox: false ,
            url:'organization.do?dataGrid&ajax=true',
            idFieldName:'id',
            textFieldName:'name',
            onClick: onClick
        });
        treeManager = $("#tree2").ligerGetTreeManager();
    });
    function onClick(note){

        orgframe.location.href="organization.do?view&id="+note.data.id;

    }
</script>
<style type="text/css">

    body{ padding:5px; margin:0; padding-bottom:15px;}
    #layout1{  width:100%;margin:0; padding:0;  }
    .l-page-top{ height:80px; background:#f8f8f8; margin-bottom:3px;}
    h4{ margin:20px;}
</style>


<div id="layout1">
    <div position="left">
        <ul id="tree2">

        </ul>
    </div>
    <div position="center">
        <iframe src="abc" height="100%" width="100%" id="orgframe" name="orgframe" frameborder="0"></iframe>
    </div>
</div>

