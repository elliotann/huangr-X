<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<form id="authForm" method="post" class="validate" >
	<input type="hidden" id="roleId" value="${roleId }"/>
	<div style="padding: 10px 0 10px 60px">
		<table>
			
			<tr>
				<td>功能:</td>
				<td>
					<ul id="tt" class="easyui-tree" data-options="cascadeCheck:true,url:'auth.do?dataGrid&ajax=yes&roleId=${roleId}',method:'get',animate:true,checkbox:true"></ul>
			    </td>
			</tr>
		</table>
	</div>
</form>



