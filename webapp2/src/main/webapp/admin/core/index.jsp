<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${context }/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${context }/js/jquery-form-2.33.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/gray/easyui.css"/>    
	<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/icon.css"/>  
	<script type="text/javascript" src="${context }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${context }/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${context }/js/jquery.loading.js"></script>
	<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${context }/css/style.css" rel="stylesheet" type="text/css" />
<div class="main">
	<div class="index_nav">欢迎您登录管理后台</div>

	<div id="template" class="indexitem">
		<div class="title"><h3></h3></div>
		<div class="body"></div>
	</div>
	
	<!-- 首页项  -->
	<div id="index_box">
	    <div id="item1" class="item"></div>
	    <div id="item2" class="item"></div>
	    <div id="item3" class="item" style="clear:both"></div>
	    <div id="item4" class="item"></div>
	    <div id="item5" class="item"></div>
	    <div id="item6" class="item"></div>
	</div>
	<div style="clear:both"></div>
</div>



<script>
function addItem(title,itemurl,sort){
	$.ajax({
		url:itemurl,
		type:'POST',
		data:'ajax=yes',
		dataType:'html',
		success:function(item_html){
			createItem(item_html,title,sort);
		},
		error:function(e){
			alert("error:"+e);
		}
	});
}

function createItem(item_html,title,sort){
	var newitem = $("#template").clone();
	newitem.removeAttr("id");
	newitem.find(".title>h3").append(title);
	newitem.find(".body").append(item_html);
	$("#item"+sort).append(newitem).find("a").each(function(){
		$(this).click(function(){
			//alert($(this).attr("href"));
			//parent.OpenWindow($(this));
		});
	});
}

$(function(){
	<c:forEach items="${itemList}" var="item">
		addItem('${item.title}','${ctx}${item.url}',${item.sort});
	</c:forEach>

});
</script>