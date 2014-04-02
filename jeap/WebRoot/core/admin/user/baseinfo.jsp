<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript">

$.Validator.options={lang:{required:'必须!'}};
</script>
<div class="input">
<%@ include file="account_header.jsp" %>
<%@ include file="info_header.jsp" %>

<form  method="post" action="baseInfo!save.do"  enctype="multipart/form-data" class="validate">
<table class="form-table">
	<tr>
		<th>
		<input type="hidden" name="JEAPUser.id" id="JEAPUser.id" value="${userid }" /><label
			class="text">用户名称：</label></th>
		<td><input type="text" name="JEAPUser.username"
			 value="${eopUser.username }" readonly="readonly" dataType="string" isrequired="true" style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">通信地址：</label></th>
		<td><input type="text" name="JEAPUser.address"
			id="JEAPUser.address" value="${eopUser.address }" dataType="string"  style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">法人代表：</label></th>
		<td><input type="text" name="JEAPUser.legalrepresentative"
			id="JEAPUser.legalrepresentative"
			value="${eopUser.legalrepresentative }" dataType="string" style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">联系人：</label></th>
		<td><input type="text" name="JEAPUser.linkman"
			id="JEAPUser.linkman" value="${eopUser.linkman }" dataType="string"  style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">联系电话：</label></th>
		<td><input type="text" name="JEAPUser.tel" id="JEAPUser.tel"
			value="${eopUser.tel }" isrequired="true" dataType="tel_num" style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">手机：</label></th>
		<td><input type="text" name="JEAPUser.mobile" id="JEAPUser.mobile"
			value="${eopUser.mobile }" style="width:260px;"/><span class="tip error|ok|note"></span></td>
	</tr>
	<tr>
		<th><label class="text">电子邮件：</label></th>
		<td><input type="text" name="JEAPUser.email" id="JEAPUser.email"
			value="${eopUser.email }" isrequired="true" dataType="email" style="width:260px;"/></td>
	</tr>
	<tr>
       <th><label class="text">企业LOGO：</label></th>
       <td><input type="file" name="cologo" id="cologo" size="45"/>
           <span class="notice-span"  id="warn_brandlogo">请上传图片，做为企业的LOGO</span></td>
     </tr>
     
     <c:if test="${eopUser.logofile!=null }">
	     <tr>
	       <th>&nbsp;</td>
	       <td> 
	       <img src="${respath}/${eopUser.logofile }"  width="207" height="56"/>&nbsp;</td>
	     </tr>
     </c:if>
     <tr>
       <th><label class="text">营业执照图片：</label></th>
       <td><input type="file" name="license" id="license" size="45"/>
           <span class="notice-span"  id="warn_brandlogo">请上传营业执照图片</span></td>
     </tr>
     
     <c:if test="${eopUser.licensefile!=null }">
	     <tr>
	       <th>&nbsp;</td>
	       <td> 
	       <img src="${respath}/${eopUser.licensefile }"  width="207" height="200"/>&nbsp;</td>
	     </tr>
     </c:if>
 
</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>  
</form>
</div>
<script type="text/javascript">
$("form.validate").validate();
</script>
