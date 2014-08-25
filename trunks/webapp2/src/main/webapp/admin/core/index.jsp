<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link href="${context}/css/layout.css" rel="stylesheet" type="text/css" />
<div class="row">
    <div class="col-sm-12 tac">
        <ul class="ov_boxes">
            <li>
                <div class="p_bar_up p_canvas">
                    <span style="display: none;">2,4,9,7,12,8,16</span>
                    <canvas height="32" width="50"></canvas>
                </div>
                <div class="ov_text">
                    <strong>3 458,00</strong>
                    周访问
                </div>
            </li>
            <li>
                <div class="p_bar_down p_canvas"><span style="display: none;">20,15,18,14,10,13,9,7</span><canvas height="32" width="50"></canvas></div>
                <div class="ov_text">
                    <strong>43 567,43</strong>
                    月访问
                </div>
            </li>
            <li>
                <div class="p_line_up p_canvas"><span style="display: none;">3,5,9,7,12,8,16</span><canvas height="32" width="50"></canvas></div>
                <div class="ov_text">
                    <strong>2304</strong>
                    独立访问者数 (最近24小时)
                </div>
            </li>
            <li>
                <div class="p_line_down p_canvas"><span style="display: none;">20,16,14,18,15,14,14,13,12,10,10,8</span><canvas height="32" width="50"></canvas></div>
                <div class="ov_text">
                    <strong>30240</strong>
                    独立访问者数 (最近一周)

                </div>
            </li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <ul class="dshb_icoNav clearfix">
            <li><a href="javascript:void(0)" style="background-image: url(img/gCons/multi-agents.png)"><span class="label label-info">+10</span> 用户</a></li>
            <li><a href="javascript:void(0)" style="background-image: url(img/gCons/multi-agents.png)"><span class="label label-info">+10</span> 待办事务</a></li>
        </ul>
    </div>
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
		/*$(this).click(function(){
				parent.Jeap.AdminUI.load($(this));
				return false;
			});*/
	});
}

$(function(){
	<c:forEach items="${itemList}" var="item">
	addItem('${item.title}','${ctx}${item.url}',${item.sort});
	</c:forEach>
});
</script>


