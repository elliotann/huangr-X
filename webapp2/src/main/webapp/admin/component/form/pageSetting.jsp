<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta content="text/html; charset=UTF-8" http-equiv="content-type">
		  <title>Process Diagram</title>
		  <!-- framework CSS -->
<link href="/jeap1.0/ui/css/style.css" type="text/css" rel="stylesheet" title="blue"/>

<!-- JQuery EasyUi CSS-->
<link rel="stylesheet" type="text/css" href="${context}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${context}/easyui/themes/icon.css">

<!-- JQuery validate CSS-->
<link href="/jeap1.0/ui/js/validate/jquery.validate.extend.css" type="text/css" rel="stylesheet"/>

<!-- JQuery AutoComplete -->
<link rel="stylesheet" type="text/css" href="../../ui/js/jquery-autocomplete/jquery.autocomplete.css" />
<!--<link rel="stylesheet" type="text/css" href="../../js/jquery-autocomplete/lib/thickbox.css" />-->

<!-- JQuery-->
<script src="../../ui/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<!--<script src="../../js/jquery-1.6.min.js" type="text/javascript"></script>-->

<!-- JQuery EasyUi JS-->
<script src="../../ui/js/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
<!-- JQuery validate JS-->
<script src="../../ui/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="../../ui/js/validate/jquery.metadata.js" type="text/javascript"></script>
<script src="../../ui/js/validate/jquery.validate.method.js" type="text/javascript"></script>
<script src="../../ui/js/validate/jquery.validate.extend.js" type="text/javascript"></script>

<!-- JQuery form Plugin -->
<script src="../../ui/js/jquery.form.js" type="text/javascript"></script>

<!-- JSON JS-->
<script src="../../ui/js/json2.js" type="text/javascript"></script>

<!-- JQuery AutoComplete -->
<script type='text/javascript' src='../../ui/js/jquery-autocomplete/lib/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='../../ui/js/jquery-autocomplete/lib/jquery.ajaxQueue.js'></script>
<!--<script type='text/javascript' src='../../js/jquery-autocomplete/lib/thickbox-compressed.js'></script>-->
<script type='text/javascript' src='../../ui/js/jquery-autocomplete/jquery.autocomplete.min.js'></script>


  			


		<link href="${context }/css/form.css" rel="stylesheet" />
		<link href="/jeap1.0/admin/component/form/css/formbuilder.css" rel="stylesheet" />
		
		
		<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
    
    .drag-item{
			list-style-type:none;
			display:block;
			padding:5px;
			border:1px solid #ccc;
			margin:2px;
			width:300px;
			background:#fafafa;
			color:#444;
		}
		.indicator{
			position:absolute;
			font-size:9px;
			width:10px;
			height:10px;
			display:none;
			color:red;
		}
</style>
</head>
<script type="text/javascript">

var processDefinitionId="";
var processDefinitionName="";
var processDefinitionVariables="";
var _process_def_provided_listeners="";
var is_open_properties_panel = false;
var task;
var line;
var formItems = [];
$(function(){
	try{
		_task_obj = $('#task');
		_designer = $('#designer');
		_properties_panel_obj = _designer.layout('panel','east');
		_properties_panel_obj.panel({
			onOpen:function(){
				is_open_properties_panel = true;
			},
			onClose:function(){
				is_open_properties_panel = false;
			}
		});
		_process_panel_obj = _designer.layout('panel','center');
		_task_context_menu = $('#task-context-menu').menu({});

		var indicator = $('<div class="indicator">>></div>').appendTo('body');
		$('.drag-item').draggable({
			revert:true,
			deltaX:0,
			deltaY:0
		}).droppable({
			onDragOver:function(e,source){
				indicator.css({
					display:'block',
					left:$(this).offset().left-10,
					top:$(this).offset().top+$(this).outerHeight()-5
				});
			},
			onDragLeave:function(e,source){
				indicator.hide();
			},
			onDrop:function(e,source){
				$(source).insertAfter(this);
				indicator.hide();
			}
		});
		
		$('.easyui-linkbutton').draggable({
					proxy:function(source){
						var n = $('<div class="draggable-model-proxy"></div>');
						n.html($(source).html()).appendTo('body');
						return n;
					},
					deltaX:0,
					deltaY:0,
					revert:true,
					cursor:'auto',
					onStartDrag:function(){
						$(this).draggable('options').cursor='not-allowed';
					},
					onStopDrag:function(){
						$(this).draggable('options').cursor='auto';
					}	
		});
		$('#paintarea').droppable({
					accept:'.easyui-linkbutton',
					onDragEnter:function(e,source){
						$(source).draggable('options').cursor='auto';
					},
					onDragLeave:function(e,source){
						$(source).draggable('options').cursor='not-allowed';
					},
					onDrop:function(e,source){
						//jq(this).append(source)
						//jq(this).removeClass('over');
						var wfModel = $(source).attr('wfModel');
						var shape = $(source).attr('iconImg');
						if(wfModel){
							var x=$(source).draggable('proxy').offset().left;
							var y=$(source).draggable('proxy').offset().top;
							
					
		                    var text = $(source).text();
		                    var fieldName = $(source).attr('fieldName');
		                    
		             
		                    //addModel(wfModel,x-xOffset+scrollLeft,y-yOffset+scrollTop,shape);
		                    var trHTML =" <div class=\"fb-field-wrapper\"><div class=\"subtemplate-wrapper\"><div class=\"cover\"></div>";
		                    trHTML+="<label><span>"+text+":  </span></label>";
		                    trHTML+="<input class=\"form-control\" type=\"text\">";
		                    trHTML += "<span class=\"help-block\"></span>";
		                    trHTML+="<div class=\"actions-wrapper\">";
		                    trHTML+=" <a class=\"js-clear fb-button\" title=\"Remove Field\"><i class=\"fa fa-minus-circle\"></i></a>";
		                    trHTML+="</div></div>";
		                    
		                   
		                    var item ={
		                    		title:text,
		                    		name:fieldName
		                    }
		                    formItems.push(item);
		                    $("#myTable").append(trHTML);
		                    
		                    $('.fb-field-wrapper').draggable({
		        				revert:true,
		        				deltaX:0,
		        				deltaY:0
		        			}).droppable({
		        				onDragOver:function(e,source){
		        					indicator.css({
		        						display:'block',
		        						left:$(this).offset().left-10,
		        						top:$(this).offset().top+$(this).outerHeight()-5
		        					});
		        				},
		        				onDragLeave:function(e,source){
		        					indicator.hide();
		        				},
		        				onDrop:function(e,source){
		        					$(source).insertAfter(this);
		        					indicator.hide();
		        				}
		        			});
		        			
						}
					}
				});

	}catch(e){
		alert(e.message);
	};
	$(window).unload( function () { 
		window.opener._list_grid_obj.datagrid('reload');
	} );
});
function addModel(name,x,y,icon){
	var model = null;
	if(icon!=null&&icon!=undefined){
		model = eval("new draw2d."+name+"('"+icon+"')");
	}else{
		model = eval("new draw2d."+name+"(openTaskProperties)");
	}
	//userTask.setContent("DM Approve");
	model.generateId();

	workflow.addModel(model,x,y);
}

function openTaskProperties(t){
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	task=t;
	if(task.type=="draw2d.UserTask")
		_properties_panel_obj.panel('refresh','userTaskProperties.html');
	else if(task.type=="draw2d.ManualTask")
		_properties_panel_obj.panel('refresh','manualTaskProperties.html');
	else if(task.type=="draw2d.ServiceTask")
		_properties_panel_obj.panel('refresh','serviceTaskProperties.html');
	else if(task.type=="draw2d.ScriptTask")
		_properties_panel_obj.panel('refresh','scriptTaskProperties.html');
	else if(task.type=="draw2d.ReceiveTask")
		_properties_panel_obj.panel('refresh','receiveTaskProperties.html');
	else if(task.type=="draw2d.MailTask")
		_properties_panel_obj.panel('refresh','mailTaskProperties.html');
	else if(task.type=="draw2d.BusinessRuleTask")
		_properties_panel_obj.panel('refresh','businessRuleTaskProperties.html');
	else if(task.type=="draw2d.CallActivity")
		_properties_panel_obj.panel('refresh','callActivityProperties.html');
}
function openProcessProperties(id){
	//alert(id);
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	_properties_panel_obj.panel('refresh','processProperties.html');
}
function openFlowProperties(l){
	//alert(id);
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	line=l;
	_properties_panel_obj.panel('refresh','flowProperties.html');
}
function deleteModel(id){
	var task = workflow.getFigure(id);
	workflow.removeFigure(task);
}
function redo(){
	workflow.getCommandStack().redo();
}
function undo(){
	workflow.getCommandStack().undo();
}
function saveProcessDef(){
	var xml = workflow.toXML();
	//alert(workflow.process.getVariablesJSONObject());
	//alert(workflow.process.getVariablesJSONObject());
	//return;
	$.ajax({
		url:"designer.do?saveAddForm&ajax=true",
		type: 'POST',
		data:{
			formItems:JSON.stringify(formItems),
			formId:${formEntity.id},
			processVariables:workflow.process.getVariablesJSONObject()
		},
		dataType:'json',
		error:function(){
			//$.messager.alert("<s:text name='label.common.error'></s:text>","<s:text name='message.common.save.failure'></s:text>","error");
			return "";
		},
		success:function(data){
			if(data.result){
				$.messager.alert('Info','Save Successfully!','info');
			}else{
				$.messager.alert('Error',data.message,'error');
			}
		}	
	}); 
	
}
function exportProcessDef(obj){
	//obj.href="${ctx}/wf/procdef/procdef!exportProcessDef.action?procdefId="+processDefinitionId+"&processName="+processDefinitionName;
}

</script>

<body id="designer" class="easyui-layout">
	<div region="west" split="true" iconCls="palette-icon" title="组件库" style="width:150px;">
		<div class="easyui-accordion" fit="true" border="false">

                <div id="scLibrary" title="表单字段" icoCls="palette-menu-icon" selected="true" class="palette-menu">
                	<c:forEach items="${fields }" var="field">
                		<a href="##" class="easyui-linkbutton" plain="true" iconCls="application_add" wfModel="UserTask" fieldName="${field.field.fieldName }">${field.field.displayName }</a><br />
                	</c:forEach>
                </div>
                <div id="exLibrary" title="操作按钮" icoCls="palette-menu-icon"  class="palette-menu">
                	<a href="##" class="easyui-linkbutton" plain="true" iconCls="application_add" >新增</a><br />
                
                </div>
                
				
		</div>
	</div>
	<div id="process-panel" region="center" split="true"  iconCls="process-icon" title="桌面">
				
				 <style>
        * {
            box-sizing: border-box;
        }

        body {
            background-color: #444;
            font-family: sans-serif;
        }

        .fb-main {
            background-color: #fff;
            border-radius: 5px;
            min-height: 600px;
        }

        input[type=text] {
            height: 26px;
            margin-bottom: 3px;
        }

        select {
            margin-bottom: 5px;
            font-size: 40px;
        }

        .fb-field-wrapper{cursor:pointer;position:relative;margin-bottom:20px}
        .fb-field-wrapper input{border-radius:3px;border:thin solid #ddd}
        .fb-field-wrapper:hover .actions-wrapper,.fb-field-wrapper.editing .actions-wrapper{display:block}
        .fb-field-wrapper:hover .subtemplate-wrapper{border-color:#ddd;border-radius:3px}
        .fb-field-wrapper.editing{background-color:#ecf0f1;border-radius:3px}
        .fb-field-wrapper.editing .subtemplate-wrapper{border-color:#d9e1e3;border-style:solid;margin:0;border-radius:3px}
        .fb-field-wrapper .actions-wrapper{display:none;position:absolute;bottom:-7px;right:5px;z-index:3}
        .fb-field-wrapper .actions-wrapper a{display:inline-block;background-color:#ccc;padding:2px 8px}

        .fb-field-wrapper .actions-wrapper a.js-duplicate,.fb-edit-field-wrapper .js-add-option{background-color:#2ecc71;border:none}
        .fb-field-wrapper .actions-wrapper a.js-clear,.fb-edit-field-wrapper .js-remove-option{background-color:#e74c3c;border:none}
        .fb-field-wrapper .subtemplate-wrapper{border:1px dashed transparent;margin-bottom:10px;padding:10px;position:relative}
        .fb-field-wrapper .subtemplate-wrapper .cover{position:absolute;top:0;left:0;height:100%;width:100%;z-index:2}
        .fb-field-wrapper .subtemplate-wrapper > label{border-bottom:thin solid #eee;padding-bottom:3px;margin-bottom:7px}
        .fb-field-wrapper .subtemplate-wrapper abbr{color:#f00}

        .fb-field-wrapper .input-line .above-line{margin-top:7px}
        .fb-field-wrapper .input-line > span{display:inline-block;vertical-align:top}
        .fb-field-wrapper .input-line > span input{width:100%}
        .fb-field-wrapper .input-line > span > label{display:block;font-size:13px;margin-left:3px}
        .fb-field-wrapper .help-block{display:block;font-size:12px;margin-top:5px}


        .fb-edit-field-wrapper .fb-field-label .field-type{margin-top:.5em;display:block;font-family:'Source Sans Pro',sans-serif;font-size:1em}
        .fb-edit-field-wrapper .fb-field-label .field-type:before{content:'Type: ';color:#999}

.fa {
display: inline-block;
font-family: FontAwesome;
font-style: normal;
font-weight: 400;
line-height: 1;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}
.fa-plus-circle:before {
content: "\f055";
}


    </style>
				<div id="process-definition-tab">
							<div id="designer-area" title="设计" style="POSITION: absolute;width:100%;height:100%;padding: 0;border: none;overflow:auto;">
								<!--以下为面板, DIV中的DIV-->
								<div id="paintarea" style="POSITION: absolute;WIDTH: 100%; HEIGHT: 100%" >
								
									<!-- <table cellpadding="0" cellspacing="0" class="l-table-edit" id="myTable">
								        
								    </table> -->
								    <div class="fb-response-fields ui-sortable" id="myTable">

							           
									<ul style="margin:0;padding:0;margin-left:10px;">
										<li class="drag-item">Drag 1</li>
										<li class="drag-item">Drag 2</li>
										<li class="drag-item">Drag 3</li>
										<li class="drag-item">Drag 4</li>
										<li class="drag-item">Drag 5</li>
										<li class="drag-item">Drag 6</li>
									</ul>
							
							
							        </div>
								</div>
							</div>
							<div id="xml-area" title="json" style="width:100%;height:100%;overflow:hidden;overflow-x:hidden;overflow-y:hidden;">
								<textarea id="descriptorarea" rows="38" style="width: 100%;height:100%;padding: 0;border: none;" readonly="readonly"></textarea>
							</div>
				</div>
				<script type="text/javascript">
					
		
					$('#process-definition-tab').tabs({
						fit:true,
						onSelect:function(title){
							if(title=='设计'){
								
							}else if(title=='json'){
								if(formItems.length>0)
									$('#descriptorarea').val(JSON.stringify(formItems));
							 
							}
						}
					});
					
				
					
				
				</script>
	</div>
	<div id="properties-panel" region="east" split="true" iconCls="properties-icon" title="属性" style="width:200px;">
		
	</div>
	
	<!-- toolbar -->
	<div id="toolbar-panel" region="north" border="false" style="height:36px;background:#E1F0F2;">
		<div style="background:#E1F0F2;padding:5px;">
			<a href="javascript:void(0)" id="sb1" class="easyui-splitbutton" menu="#edit-menu" iconCls="icon-edit">Edit</a>

			<a href="javascript:void(0)" id="mb3" class="easyui-menubutton" menu="#mm3" iconCls="icon-help">页面类型</a>
		</div>
		<div id="edit-menu" style="width:150px;">
		<div iconCls="icon-undo" onclick="undo()">Undo</div>
		<div iconCls="icon-redo" onclick="redo()">Redo</div>
		<div class="menu-sep"></div>

		<div iconCls="icon-save" onclick="saveProcessDef()">Save</div>
		<div><a href="#" onclick="exportProcessDef(this)">Export</a></div>

		</div>
		<div id="mm3" style="width:150px;">
			<div>增加页面</div>
			<div class="menu-sep"></div>
			<div>修改页面</div>
			<div class="menu-sep"></div>
			<div>列表页面</div>
		</div>
	</div>
	<!-- task context menu -->
	<div id="task-context-menu" class="easyui-menu" style="width:120px;">
		<div id="properties-task-context-menu" iconCls="properties-icon" >Properties</div>
		<div id="delete-task-context-menu" iconCls="icon-remove">Delete</div>
	</div>
	<!-- form configuration window -->
	<div id="form-win" title="Form Configuration" style="width:750px;height:500px;">
	</div>
	<!-- listener configuration window -->
	<div id="listener-win" title="Listener Configuration" style="width:750px;height:500px;">
	</div>
	<!-- candidate configuration window -->
	<div id="task-candidate-win" title="" style="width:750px;height:500px;">
	</div>
</body>
</html>
<script type="text/javascript">

</script>