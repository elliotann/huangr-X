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

<!-- framework JS -->
<script src="../../ui/js/skin.js" type="text/javascript"></script>
		  <link href="../../ui/js/designer/designer.css" type="text/css" rel="stylesheet"/>
  			
        <!-- common, all times required, imports -->
        <SCRIPT src='../../ui/js/draw2d/wz_jsgraphics.js'></SCRIPT>          
        <SCRIPT src='../../ui/js/draw2d/mootools.js'></SCRIPT>          
        <SCRIPT src='../../ui/js/draw2d/moocanvas.js'></SCRIPT>                        
        <SCRIPT src='../../ui/js/draw2d/draw2d.js'></SCRIPT>


        <!-- example specific imports -->
        <SCRIPT src="../../ui/js/designer/MyCanvas.js"></SCRIPT>
        <SCRIPT src="../../ui/js/designer/ResizeImage.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/event/Start.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/event/End.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/connection/MyInputPort.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/connection/MyOutputPort.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/connection/DecoratedConnection.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/Task.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/UserTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/ManualTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/ServiceTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/ScriptTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/MailTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/ReceiveTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/BusinessRuleTask.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/task/CallActivity.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/gateway/ExclusiveGateway.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/gateway/ParallelGateway.js"></SCRIPT>
		<SCRIPT src="../../ui/js/designer/designer.js"></SCRIPT>	
		
		<SCRIPT src="../../ui/js/designer/form/TextInput.js"></SCRIPT>
		<link href="${context }/css/form.css" rel="stylesheet" />
		<link href="/jeap1.0/admin/component/form/css/formbuilder.css" rel="stylesheet" />
		
		
		<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
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
jq(function(){
	try{
		_task_obj = jq('#task');
		_designer = jq('#designer');
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
		_task_context_menu = jq('#task-context-menu').menu({});
//		_designer.layout('collapse','east');
		
		jq('.easyui-linkbutton').draggable({
					proxy:function(source){
						var n = jq('<div class="draggable-model-proxy"></div>');
						n.html(jq(source).html()).appendTo('body');
						return n;
					},
					deltaX:0,
					deltaY:0,
					revert:true,
					cursor:'auto',
					onStartDrag:function(){
						jq(this).draggable('options').cursor='not-allowed';
					},
					onStopDrag:function(){
						jq(this).draggable('options').cursor='auto';
					}	
		});
		jq('#paintarea').droppable({
					accept:'.easyui-linkbutton',
					onDragEnter:function(e,source){
						jq(source).draggable('options').cursor='auto';
					},
					onDragLeave:function(e,source){
						jq(source).draggable('options').cursor='not-allowed';
					},
					onDrop:function(e,source){
						//jq(this).append(source)
						//jq(this).removeClass('over');
						var wfModel = jq(source).attr('wfModel');
						var shape = jq(source).attr('iconImg');
						if(wfModel){
							var x=jq(source).draggable('proxy').offset().left;
							var y=jq(source).draggable('proxy').offset().top;
							
							var xOffset    = workflow.getAbsoluteX();
		                    var yOffset    = workflow.getAbsoluteY();
		                    var scrollLeft = workflow.getScrollLeft();
		                    var scrollTop  = workflow.getScrollTop();
		                    var text = jq(source).text();
		                    var fieldName = jq(source).attr('fieldName');
		                    
		             
		                    //addModel(wfModel,x-xOffset+scrollLeft,y-yOffset+scrollTop,shape);
		                    var trHTML =" <div class=\"fb-field-wrapper  editing\"><div class=\"subtemplate-wrapper\"><div class=\"cover\"></div>";
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
						}
					}
				});

	}catch(e){
		alert(e.message);
	};
	jq(window).unload( function () { 
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
	jq.ajax({
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
				jq.messager.alert('Info','Save Successfully!','info');
			}else{
				jq.messager.alert('Error',data.message,'error');
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
				<script>
					function parseProcessDescriptor(data){
						var descriptor = jq(data);
						
						var definitions = descriptor.find('definitions');
						var process = descriptor.find('process');
						var startEvent = descriptor.find('startEvent');
						var endEvent = descriptor.find('endEvent');
						var userTasks = descriptor.find('userTask'); //打到标准元素
						var exclusiveGateway = descriptor.find('exclusiveGateway');
						var parallelGateway = descriptor.find('parallelGateway');
						var lines = descriptor.find('sequenceFlow');
						var shapes = descriptor.find('bpmndi\\:BPMNShape');
						var edges = descriptor.find('bpmndi\\:BPMNEdge');
						
						workflow.process.category=definitions.attr('targetNamespace');
						workflow.process.id=process.attr('id');
						workflow.process.name=process.attr('name');
						var documentation = trim(descriptor.find('process > documentation').text());
						if(documentation != null && documentation != "")
							workflow.process.documentation=documentation;
						var extentsion = descriptor.find('process > extensionElements');
						if(extentsion != null){
							var listeners = extentsion.find('activiti\\:executionListener');
							var taskListeners = extentsion.find('activiti\\:taskListener');
							workflow.process.setListeners(parseListeners(listeners,"draw2d.Process.Listener","draw2d.Process.Listener.Field"));
						}
						jq.each(processDefinitionVariables,function(i,n){
								var variable = new draw2d.Process.variable();
								variable.name=n.name;
								variable.type=n.type;
								variable.scope=n.scope;
								variable.defaultValue=n.defaultValue;
								variable.remark=n.remark;
								workflow.process.addVariable(variable);
							});
						startEvent.each(function(i){
								var start = new draw2d.Start("../../js/designer/icons/type.startevent.none.png");
								start.id=jq(this).attr('id');
								start.eventId=jq(this).attr('id');
								start.eventName=jq(this).attr('name');
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==start.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addFigure(start,x,y);
										return false;
									}
								});
							});
						endEvent.each(function(i){
								var end = new draw2d.End("../../js/designer/icons/type.endevent.none.png");
								end.id=jq(this).attr('id');
								end.eventId=jq(this).attr('id');
								end.eventName=jq(this).attr('name');
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==end.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addFigure(end,x,y);
										return false;
									}
								});
							});
						
						userTasks.each(function(i){
								var task = new draw2d.UserTask();
								var tid = jq(this).attr('id');
								task.id=tid;
								var tname = jq(this).attr('name');
								var assignee=jq(this).attr('activiti:assignee');
								var candidataUsers=jq(this).attr('activiti:candidateUsers');
								var candidataGroups=jq(this).attr('activiti:candidateGroups');
								var formKey=jq(this).attr('activiti:formKey');
								if(assignee!=null&&assignee!=""){
									task.isUseExpression=true;
									task.performerType="assignee";
									task.expression=assignee;
								}else if(candidataUsers!=null&&candidataUsers!=""){
									task.isUseExpression=true;
									task.performerType="candidateUsers";
									task.expression=candidataUsers;
								}else if(candidataGroups!=null&&candidataGroups!=""){
									task.isUseExpression=true;
									task.performerType="candidateGroups";
									task.expression=candidataGroups;
								}
								if(formKey!=null&&formKey!=""){
									task.formKey=formKey;
								}
								var documentation = trim(jq(this).find('documentation').text());
								if(documentation != null && documentation != "")
									task.documentation=documentation;
								task.taskId=tid;
								task.taskName=tname;
								if(tid!= tname)
									task.setContent(tname);
								var listeners = jq(this).find('extensionElements').find('activiti\\:taskListener');
								task.setListeners(parseListeners(listeners,"draw2d.Task.Listener","draw2d.Task.Listener.Field"));
								var performersExpression = jq(this).find('potentialOwner').find('resourceAssignmentExpression').find('formalExpression').text();
								if(performersExpression.indexOf('user(')!=-1){
									task.performerType="candidateUsers";
								}else if(performersExpression.indexOf('group(')!=-1){
									task.performerType="candidateGroups";
								}
								var performers = performersExpression.split(',');
								jq.each(performers,function(i,n){
									var start = 0;
									var end = n.lastIndexOf(')');
									if(n.indexOf('user(')!=-1){
										start = 'user('.length;
										var performer = n.substring(start,end);
										task.addCandidateUser({
												sso:performer
										});
									}else if(n.indexOf('group(')!=-1){
										start = 'group('.length;
										var performer = n.substring(start,end);
										task.addCandidateGroup(performer);
									}
								});
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==task.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addModel(task,x,y);
										return false;
									}
								});
							});
						exclusiveGateway.each(function(i){
								var gateway = new draw2d.ExclusiveGateway("../../js/designer/icons/type.gateway.exclusive.png");
								var gtwid = jq(this).attr('id');
								var gtwname = jq(this).attr('name');
								gateway.id=gtwid;
								gateway.gatewayId=gtwid;
								gateway.gatewayName=gtwname;
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==gateway.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addModel(gateway,x,y);
										return false;
									}
								});
							});
						parallelGateway.each(function(i){
							var gateway = new draw2d.ExclusiveGateway("../../js/designer/icons/type.gateway.parallel.png");
							var gtwid = jq(this).attr('id');
							var gtwname = jq(this).attr('name');
							gateway.id=gtwid;
							gateway.gatewayId=gtwid;
							gateway.gatewayName=gtwname;
							shapes.each(function(i){
								var id = jq(this).attr('bpmnElement');
								if(id==gateway.id){
									var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
									var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
									workflow.addModel(gateway,x,y);
									return false;
								}
							});
						});
						lines.each(function(i){
								var lid = jq(this).attr('id');
								var name = jq(this).attr('name');
								var condition=jq(this).find('conditionExpression').text();
								var sourceRef = jq(this).attr('sourceRef');
								var targetRef = jq(this).attr('targetRef');
								var source = workflow.getFigure(sourceRef);
								var target = workflow.getFigure(targetRef);
								edges.each(function(i){
										var eid = jq(this).attr('bpmnElement');
										if(eid==lid){
											var startPort = null;
											var endPort = null;
											var points = jq(this).find('omgdi\\:waypoint');
											var startX = jq(points[0]).attr('x');
											var startY = jq(points[0]).attr('y');
											var endX = jq(points[1]).attr('x');
											var endY = jq(points[1]).attr('y');
											var sports = source.getPorts();
											for(var i=0;i<sports.getSize();i++){
												var s = sports.get(i);
												var x = s.getAbsoluteX();
												var y = s.getAbsoluteY();
												if(x == startX&&y==startY){
													startPort = s;
													break;
												}
											}
											var tports = target.getPorts();
											for(var i=0;i<tports.getSize();i++){
												var t = tports.get(i);
												var x = t.getAbsoluteX();
												var y = t.getAbsoluteY();
												if(x==endX&&y==endY){
													endPort = t;
													break;
												}
											}
											if(startPort != null&&endPort!=null){
												var cmd=new draw2d.CommandConnect(workflow,startPort,endPort);
												var connection = new draw2d.DecoratedConnection();
												connection.id=lid;
												connection.lineId=lid;
												connection.lineName=name;
												if(lid!=name)
													connection.setLabel(name);
												if(condition != null && condition!=""){
													connection.condition=condition;
												}
												cmd.setConnection(connection);
												workflow.getCommandStack().execute(cmd);
											}
											return false;
										}
									});
							});
						if(typeof setHightlight != "undefined"){
							setHightlight();
						}
					}
					function parseListeners(listeners,listenerType,fieldType){
						var parsedListeners = new draw2d.ArrayList();
						listeners.each(function(i){
							var listener = eval("new "+listenerType+"()");
							
							listener.event=jq(this).attr('event');
							var expression = jq(this).attr('expression');
							var clazz = jq(this).attr('class');
							if(expression != null && expression!=""){
								listener.serviceType='expression';
								listener.serviceExpression=expression;
							}else if(clazz != null&& clazz!=""){
								listener.serviceType='javaClass';
								listener.serviceExpression=clazz;
							}
							var fields = jq(this).find('activiti\\:field');
							fields.each(function(i){
								var field = eval("new "+fieldType+"()");
								field.name=jq(this).attr('name');
								//alert(field.name);
								var string = jq(this).find('activiti\\:string').text();
								var expression = jq(this).find('activiti\\:expression').text();
								//alert("String="+string.text()+"|"+"expression="+expression.text());
								if(string != null && string != ""){
									field.type='string';
									field.value=string;
								}else if(expression != null && expression!= ""){
									field.type='expression';
									field.value=expression;
								}
								listener.setField(field);
							});
							parsedListeners.add(listener);
						});
						return parsedListeners;
					}
				</script>
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

							            <div class="fb-field-wrapper  editing">
							                <div class="subtemplate-wrapper">
							                    <div class="cover"></div>
							                    <label>
							                          <span>
							                           		名称: 
							                          </span>
							                    </label>
							
							
							                    <input class="form-control" type="text">
							
							                      <span class="help-block">
							
							                    </span>
							
							                    <div class="actions-wrapper">
							                        <a class="js-duplicate fb-button" title="Duplicate Field"><i class="fa fa-plus-circle"></i></a>
							                        <a class="js-clear fb-button" title="Remove Field"><i class="fa fa-minus-circle"></i></a>
							                    </div>
							                </div>
							            </div>
							
							
							
							        </div>
								</div>
							</div>
							<div id="xml-area" title="json" style="width:100%;height:100%;overflow:hidden;overflow-x:hidden;overflow-y:hidden;">
								<textarea id="descriptorarea" rows="38" style="width: 100%;height:100%;padding: 0;border: none;" readonly="readonly"></textarea>
							</div>
				</div>
				<script type="text/javascript">
					
					var workflow;
					jq('#process-definition-tab').tabs({
						fit:true,
						onSelect:function(title){
							if(title=='设计'){
								
							}else if(title=='json'){
								if(formItems.length>0)
									jq('#descriptorarea').val(JSON.stringify(formItems));
							 
							}
						}
					});
					function openProcessDef(){
						jq.ajax({
							url:"../../wf/procdef/procdef!getProcessDefXML.action?procdefId="+processDefinitionId,
							type: 'POST',
							/*
							data:{
										moduleId:"${moduleId}",
										_request_json_fields:json4params
								},
							*/
							dataType:'xml',
							error:function(){
								$.messager.alert("<s:text name='label.common.error'></s:text>","System Error","error");
								return "";
							},
							success:parseProcessDescriptor	
						}); 
					}
				
					function createCanvas(disabled){
						try{
							//initCanvas();
							workflow  = new draw2d.MyCanvas("paintarea");
							workflow.scrollArea=document.getElementById("designer-area");
							if(disabled)
								workflow.setDisabled();
							if(typeof processDefinitionId != "undefined" && processDefinitionId != null &&  processDefinitionId != "null" && processDefinitionId != "" && processDefinitionId != "NULL"){
								openProcessDef();
							}else{
									
							} 
						}catch(e){
							alert(e.message);
						}
					}
				
				</script>
	</div>
	<div id="properties-panel" region="east" split="true" iconCls="properties-icon" title="属性" style="width:200px;">
		
	</div>
	
	<!-- toolbar -->
	<div id="toolbar-panel" region="north" border="false" style="height:36px;background:#E1F0F2;">
		<div style="background:#E1F0F2;padding:5px;">
			<a href="javascript:void(0)" id="sb1" class="easyui-splitbutton" menu="#edit-menu" iconCls="icon-edit">Edit</a>

			<a href="javascript:void(0)" id="mb3" class="easyui-menubutton" menu="#mm3" iconCls="icon-help">Help</a>
		</div>
		<div id="edit-menu" style="width:150px;">
		<div iconCls="icon-undo" onclick="undo()">Undo</div>
		<div iconCls="icon-redo" onclick="redo()">Redo</div>
		<div class="menu-sep"></div>

		<div iconCls="icon-save" onclick="saveProcessDef()">Save</div>
		<div><a href="#" onclick="exportProcessDef(this)">Export</a></div>

		</div>
		<div id="mm3" style="width:150px;">
			<div>Help</div>
			<div class="menu-sep"></div>
			<div>About</div>
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
<!--
	createCanvas(false);
//-->
</script>