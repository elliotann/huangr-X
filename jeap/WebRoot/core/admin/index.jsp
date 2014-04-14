<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

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
				parent.Jeap.AdminUI.load($(this));
				return false;
			});
	});
}

$(function(){
	<c:forEach items="${itemList}" var="item">
	addItem('${item.title}','${ctx}${item.url}',${item.sort});
	</c:forEach>
});
</script>


