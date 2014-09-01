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
    <script>
        $(function(){
            $('#tt').tree({
                url:'organization.do?dataGrid&ajax=true',
                onClick:onClick
            });
        });
        function onClick(node){
            orgframe.location.href="organization.do?view&id="+node.id;
        }
    </script>
</head>
<body class="easyui-layout">

<div data-options="region:'west',split:true,title:'组织结构'" style="width:200px;padding:10px;">
    <ul id="tt"></ul>
</div>
<div data-options="region:'center'">
    <iframe src="abc" height="100%" width="100%" id="orgframe" name="orgframe" frameborder="0"></iframe>
</div>
</body>
</body>
</html>