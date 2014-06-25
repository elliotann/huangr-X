<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${context}/css/table.css" rel="stylesheet" type="text/css" />
<div class="toolbar">
    <input type="button" value="增加"/>
</div>
<grid:grid from="entityist" pager="yes">
    <grid:header>
        <grid:cell>id</grid:cell>
        <grid:cell>名称</grid:cell>
    </grid:header>
    <grid:body item="leave">
        <grid:cell>${leave.sid}</grid:cell>
        <grid:cell>${leave.reason}</grid:cell>
    </grid:body>
</grid:grid>
