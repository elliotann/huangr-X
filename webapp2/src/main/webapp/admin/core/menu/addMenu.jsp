<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
    <div class="main" style="background-color: white;">
	<div class="easyui-panel" style="border-style: none;">
		<form id="addForm" method="post" class="validate">
			<table>
				<tr>
					<th>标题:</th>
					<td><input class="input_text easyui-validatebox" type="text"
						id="title" name="title" data-options="required:true"></input>
					</td>

				</tr>
				<tr>
					<th>类型:</th>
					<td style="padding: 2px 0 0 0"><input type="radio"
						name="menutype" value="2" checked="checked" />应用&nbsp; <input
						type="radio" name="menutype" value="1" />系统</td>
				</tr>
				
				<tr class="addtr">
					<th>上级菜单:</th>
					<td><input class="easyui-combotree combo" name="pid"
						data-options="url:'menu.do?addOrUpdateGrid&ajax=true',method:'post',required:false,height:28"
						style="width: 305px;" value="${id }">
					</td>
				</tr>
				<tr>
					<th>url:</th>
					<td><input class="input_text easyui-validatebox" type="text"
						id="url" name="url" data-options="required:true"></input>
					</td>

				</tr>
				<tr>
					<th>target:</th>
					<td><input class="input_text easyui-validatebox" type="text"
						id="target" name="target"></input>
					</td>

				</tr>
				<tr>
					<th>排序</th>
					<td><input class="input_text" type="text" id="sorder"
						name="sorder" data-options="required:true" value="50"></input>
					</td>
				</tr>
				
				<tr>
					<th>图标</th>
					<td><input class="easyui-validatebox" type="file" id="icoFile"
						name="icoFile"></input>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script>
	$(function() {
		$(".cattype").click(function() {
			if ($(this).val() == 1) {
				$(".addtr").hide();
			}
			if ($(this).val() == 0) {
				$(".addtr").show();
			}
		})
	});
</script>