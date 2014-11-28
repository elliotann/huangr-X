<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx }/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<link href="${context }/css/button.css" rel="stylesheet"/>
<script src="${ctx}/admin/component/form/js/jquery.clever.listbox.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/admin/component/form/css/jquery.ui.css">


</head>
<body class="easyui-layout">

	<div id="dialogInfo" style="display: none;"></div>
	<div data-options="region:'east',split:true,collapsed:true,title:'操作区'" style="width:200px;padding:10px;">3123
		
	</div>

	<div data-options="region:'center',title:'设计区-${formEntity.formName }'">
		
		<div style="margin-bottom:5px;background-color:yellow;">
			<a href="javascript:void(0);" class="button blueButton" data-options="iconCls:'icon-add',plain:true" onclick="addForm(${formEntity.id})">数据选择</a>
			<a href="javascript:void(0);" class="button blueButton" data-options="iconCls:'icon-add',plain:true" onclick="addForm(${formEntity.id})">保存</a>
		</div>
		<div>
			<div style="margin:0px 0;"></div>
	
			<table id="dg"></table>
		</div>

	</div>
	<script type="text/javascript">
	 var cols = ${cols};
    function addForm(formId)
    {
    	addOrUpdateDialog('选择字段','designer.do?selectColumns&formId='+formId,500,700);
    }

   
	$(document).ready(function () {
		init();
		drag();//绑定datagrid，绑定拖拽

	});
	function init() {    

		$('#dg').datagrid({
			url:'#',
			columns:[cols],
			onLoadSuccess: function (data) {                       
				drag();
			}
		});
	}
	//拖动drag和drop都是datagrid的头的datagrid-cell 
	function drag() {
		
		$('.datagrid-header-inner .datagrid-cell').draggable({
			revert: true,
			proxy: 'clone'

		}).droppable({
			accept: '.datagrid-header-inner .datagrid-cell',
			onDrop: function (e, source) {
				//取得拖动源的html值                
				var src = $(e.currentTarget.innerHTML).html();
				var sou = $(source.innerHTML).html();
			
				var tempcolsrc;//拖动后源和目标列交换                
				var tempcolsou;               
				var tempcols=[];
				for (var i = 0; i < cols.length; i++) {                    
					if (cols[i].title == sou) {                        
						tempcolsrc = cols[i];
						//循环读一遍列把源和目标列都记下来                    
					}else if (cols[i].title == src) { 
						tempcolsou = cols[i];                    
					}                
				}
				for (var i = 0; i < cols.length; i++) {                    
				//再循环一遍，把源和目标的列对换                   
					var col = {                        
						field: cols[i].field,                        
						title: cols[i].title,                        
						align: cols[i].align,                       
						width: cols[i].width                   
					};                    
					if (cols[i].title == sou) {                       
						col = tempcolsou;                    
					}                   
					else if (cols[i].title == src) {                        
						col = tempcolsrc;                    
					}                     
				tempcols.push(col);  
				
				}
				cols = tempcols;
				timeid = setTimeout("init()", 0);
				timeid = setTimeout("drag()", 0);
			}
		});
	}
	
	 function refresh(resultJson){
		 cols = resultJson;
		 timeid = setTimeout("init()", 0);
		 timeid = setTimeout("drag()", 0);
		
	} 
</script>
</body>
</html>