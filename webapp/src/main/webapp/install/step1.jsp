<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<h5>【JEAP】最终用户授权协议</h5>
<form method="post">
  <div style="height: 17em; border: 1px solid rgb(204, 204, 204); margin-bottom: 8px; padding: 5px; background: none repeat scroll 0% 0% rgb(255, 255, 255);line-height:20px; overflow-x: hidden; overflow-y: scroll;">
    <div style="float:right">easysoft工作室</div>
  </div>
  <table border="0" width="100%">
      <tbody>
        <tr>
            <td align="center">
                <input type="checkbox" id="readed"/>我已仔细阅读，并同意上述条款中的所有内容
            </td>
        </tr>
        <tr>
            <td align="center"><a id="donext" href="install.${ext }?step2">下一步：配置数据库信息</a></td>
        </tr>
      </tbody>
  </table>
</form>
<script type="text/javascript">
$(function(){
	$("#donext").click(function(){
		if(!document.getElementById("readed").checked){
			alert("您需仔细查看并同意协议内容，才能继续操作！");
			return false;
		}
	});
});
</script>
<jsp:include page="footer.jsp"></jsp:include>