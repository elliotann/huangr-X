<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
	<link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
	<link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
	<link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
	<link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
	<title>小黄人管理系统</title>
	
</head>
	<script type="text/javascript">
		var app_path="<%=path%>";
		var mainpage = false;
	</script>
<body>
	${content }




