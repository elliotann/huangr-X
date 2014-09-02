<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Full Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
    <script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/Gray2014/css/all.css" rel="stylesheet" type="text/css" />
    <script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script>
        var treeManager;


        function onClick(note){

            orgframe.location.href="organization.do?view&id="+note.data.id;

        }
        $(function(){
            treeManager = $("#tt").ligerTree({
                checkbox: false ,
                url:'organization.do?dataGrid&ajax=true',
                idFieldName:'id',
                textFieldName:'name',
                onClick: onClick
            });
            //treeManager = $("#tt").ligerGetTreeManager();
        });
        function onClick(node){
            orgframe.location.href="organization.do?view&id="+node.data.id+"&orgType="+node.data.orgType;
        }
    </script>
</head>
<body class="easyui-layout">
<input type="text" name="username" id="username" value="143"/>
<div data-options="region:'west',split:true,title:'组织结构'" style="width:200px;padding:10px;">
    <ul id="tt"></ul>
</div>
<div data-options="region:'center'">
    <iframe src="#" height="100%" width="100%" id="orgframe" name="orgframe" frameborder="0"></iframe>
</div>
</body>
</body>
</html>