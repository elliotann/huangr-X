<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>小黄人后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9" > </meta> 
<link rel="shortcut icon" href=" ../favicon.ico" /> 
<link type="image/x-icon" href="" rel="bookmark" />

<script type="text/javascript" src="${context }/js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/gray/easyui.css"/>    
<link rel="stylesheet" type="text/css" href="${context }/js/easyui/themes/icon.css"/>  
<script type="text/javascript" src="${context }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context }/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${context }/js/main.js"></script>
<script type="text/javascript" src="${context }/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="${context }/js/jquery.loading.js"></script>
<script type="text/javascript" src="${context }/js/jquery-Slider.js"></script>
<script type="text/javascript" src="${context }/js/jquery.timers-1.2.js"></script>
<script type="text/javascript" src="${context }/js/short-msg.js"></script>
<link href="${context }/css/main.css" rel="stylesheet" type="text/css" />
<!--PNG透明-->
<!--[if lte IE 6]>
<script src="../adminthemes/new/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
<script type="text/javascript">
    DD_belatedPNG.fix('*');
</script>
<![endif]-->
<script>
var referer = undefined;
<#if referer?exists >
referere='${referer}';
</#if>
</script> 

</head>
<body class="easyui-layout layout">
	<input type="hidden" id="hidout" value="0" />
    <div region="north" border="true" class="cs-north">
		<div class="header">
            <div class="lineOne">
                <div class="logo"></div>
                <div class="downArrow">
                    <div class="downArrowIcon"></div>
                    <div class="downArrowContent">
                        <span id="mm-tabupdate">刷新当前页</span>
                        <span id="mm-tabcloseall">关闭全部</span>
                        <span id="mm-tabcloseother">关闭其他</span>
                    </div>
                </div>
                <div class="info">
                	<div class="sysmenu">  
                		<ul>
                			<li class="message">
								<a href="javascript:;">消息
									<span class="num"></span> 
								</a>
								 <div class="msglist" >
								 	<div class="triangle"></div>
								 	 <ul>
								 	 </ul>
								 </div>
							</li>
							<li class="logout">
								<a href='javascript:;' id='logout_btn'><div class="icon"></div>退出</a>
							</li>
						
                		</ul>
                	</div>
                </div>
            </div>
        </div>
	</div>
	<!-- header end -->
    <div region="west" border="true" split="true" class="cs-west" >
    	<div class="leftMenu" style="width:110px;margin-right:9px;height:100%;" id="leftMenu" >
	        <div class="lmenuPrev">
	             <a href="javascript:;" id="btnDown">向下</a>
	        </div>
	        <div class="lmenu fl">
				<ul>
					<c:forEach items="${menuList }" var="menu" varStatus="varMenu">
						<c:if test="${menu.hasChildren }">
						
							<li id="parent${menu.id}" class="parentMenu">
								<a style="cursor: pointer">
				                     <div class="cover"></div>
				                     <div class="icon">
				                     	<c:if test="${menu.ico ne '' }">
				                     		<img src="${ctx}${menu.ico}" />
				                     	</c:if>
				                     	<c:if test="${menu.ico eq '' }">
				                     		<img src="${ctx}adminthemes/new/images/menu_default.gif" />
				                     	</c:if>
				                     </div>
				                     <div index="tfun2" style="display:none;" class="newFunction"></div>
				                     <div topvalue="2" topname="" class="text">${menu.title}</div>
								</a>
								<div id="${menu.id}" class="secondFloat secondFLoat${varMenu.index+1} <c:if test="${fn:length(menu.children) gt 4}"> secondFloatBig </c:if>">
									<div class="second">
										<ul>
											<c:forEach items="${menu.children }" var="child">
												<c:if test="${child.hasChildren }">
												
													<li>
														<div class="title">${child.title}</div>
														<ul>
															<c:forEach items="${child.children }" var="son">
													
																<li>
																	<div class="newFunction" style="margin-top: 10px; *margin-left: -20px;display:none;" index="tfun${son.id}"></div>
																	<a onclick="OpenWindow(this)" style="cursor:pointer" index="${son.id}" src="${ctx}${son.url}" class="cs-navi-tab">${son.title}</a>
																</li>
															</c:forEach>
														</ul>
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
									<!-- second -->
								</div>
							</li>
						</c:if>
					</c:forEach>	
				</ul>
			</div>
			<div class="lmenuNext">
	             <a href="javascript:;" id="btnUp">向上</a>
	        </div>
        </div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="Home">
                <div class="cs-home-remark">
                    &nbsp;
                </div>
            </div>
        </div>
    </div>
    <div id="mm" class="easyui-menu cs-tab-menu">
    </div>
</body>
</html>
	<script>
		var index = 0;
		$(function(){
			$(".lmenu").Scroll();
			$(".sysmenu .msglist").hide();
			
			$(".sysmenu .message").mouseover(function(){
				if($(".sysmenu .msglist ul>li").length>0){
				    $(".sysmenu .msglist").show();
				};
			});
			
			$(".sysmenu .message").mouseout(function(){
			    $(".sysmenu .msglist").hide();
			});
			$("#logout_btn").click(function(){
	
				var options = {
					url : "../core/admin/login.do?logout&ajax=true",
					type : "POST",
					dataType : 'json',
					success : function(result) {				
						if(result.success){
							var url = "${ctx}/admin";
							location.href=url;
						}else{
							$.Loading.error(result.message);
						}
					},
					error : function(e) {
						$.Loading.error("出现错误，请重试");
					}
				};
				$.ajax(options);		
			})
		});   
		
		 function reloadTabGrid(title){
	          if ($("#tabs" ).tabs('exists', title)) {
	               $('#tabs').tabs('select' , title);
	               window.top.reload_Abnormal_Monitor.call();
	         }
	     }
	</script>
</body>

</html>
