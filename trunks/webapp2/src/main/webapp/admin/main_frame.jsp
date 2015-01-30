<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript"	src="${staticserver }/js/common/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="${staticserver }/js/admin/jeap.js"></script>
	<script type="text/javascript" src="${ctx }/admin/js/My97DatePicker/WdatePicker.js"></script>
	<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />

	
	<title>网站后台管理</title>
	
</head>
	<script type="text/javascript">
		var app_path="<%=path%>";
		var mainpage = false;
	</script>
<body>
	${content }
</body>
</html>