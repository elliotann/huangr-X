<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/admin/js/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script src="${ctx }/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet" />
<link href="${context }/css/button.css" rel="stylesheet" />
<script src="${ctx}/admin/component/form/js/jquery.clever.listbox.js"
	type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/admin/component/form/css/jquery.ui.css">


</head>
<body class="easyui-layout">

	<div id="dialogInfo" style="display: none;"></div>
	<div data-options="region:'east',split:true,collapsed:false,title:'操作区'"
		style="width: 200px; padding: 10px;">3123</div>

	<div data-options="region:'center',title:'设计区-${formEntity.formName }'">


		<div>
			<script type="text/javascript">
    $(function () {
        $(".searchAdvanced").hide();
        //高级查询按钮
        $("#aAdvanced").click(function () {
            if ($("#Advanced").val() == "0") {
                $("#Advanced").val(1);
                $("#simpleSearch").hide();
                //$("#aAdvanced").text("简单搜索")
                $("#aAdvanced").addClass("searchAdvancedS");
            } else {
                $("#Advanced").val(0);
                $("#simpleSearch").show();
                //$("#aAdvanced").text("高级搜索");
                $("#aAdvanced").removeClass("searchAdvancedS");
            }
            $(".searchAdvanced").slideToggle("slow");
        });
    });

    function addUser()
    {
        addOrUpdateDialog('增加管理员','userAdmin.do?add',500,700);
    }
    function customSearch()
    {
        listgrid.options.data = $.extend(true,{}, CustomersData);
        listgrid.showFilter();
    }
    function modifyUser()
    {

        if($('#dataGrid').datagrid('getSelections').length<1||$('#dataGrid').datagrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').datagrid('getSelections')[0];
        addOrUpdateDialog('修改管理员','userAdmin.do?edit&id='+row.id,500,700);
        

    }

    function delUser()
    {

        var rows = $('#dataGrid').datagrid("getSelections");
        if (rows.length < 1||rows.length>1) {
            alert("请选择要删除的会员");
            return;
        }
        if (!confirm("确认要将删除会员吗？")) {
            return;
        }
        var row = rows[0];
        delObj("userAdmin.do?delete&id="+row.id);

    }
    function getStatusName(value, row, index) {
        if(value==1){
            return "启用";
        } else{
            return "禁用";
        }
    }

    function simpleSearch(){

        var username = $("#username").val();
        $("#dataGrid").datagrid('load', {
            username:username,
            page:1
        });
    }


</script>
			<grid:dataGrid action="userAdmin.do?dataGrid&ajax=yes" height="100%"
				rownumbers="true" hasSearchBar="true" style="easyui">
				<grid:search label="用户名" name="username" shortSearch="true" />
				<grid:column title="ID" field="id" align="center" width="100"
					minWidth="60" />
				<grid:column title="用户名" field="username" minWidth="100" />
				<grid:column title="email" field="email" minWidth="100" />
				<grid:column title="姓名" field="realname" minWidth="140" />
				<grid:column title="状态" field="state" renderFun="getStatusName" />
				<grid:toolbar title="增加" clickFun="addUser" icon="add" />
				<grid:toolbar title="修改" clickFun="modifyUser" icon="modify" />
				<grid:toolbar title="删除" clickFun="delUser" icon="delete" />
			</grid:dataGrid>
		</div>
		<input type="hidden" id="formId" value="${formEntity.id }"/>
<button onclick="saveSubmit()">保存</button>
	</div>
	<script type="text/javascript">
	 var cols = ${cols};
    function addForm(formId)
    {
    	addOrUpdateDialog('选择字段','designer.do?selectColumns&formId='+formId,500,700);
    }
	function saveForm(formId){
		alert(JSON.stringify(cols));
		
	}
   
	$(document).ready(function () {
		init();
		drag();//绑定datagrid，绑定拖拽

	});
	function init() {    

		$('#dataGrid').datagrid({
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
						width: cols[i].width,
						fieldId:cols[i].fieldId
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
	 
	 function saveSubmit(){
		 var formId = $("#formId").val();
		 $.ajax({
			 url:'designer.do?saveColumns',
			 type:'post',
			 data:'ajax=true&formId='+formId+"&data="+JSON.stringify(cols),
			 dataType:'json',
			 success:function(result){
				 
			 },
			 error:function(e){
				 
			 }
		 });

	 }
</script>

</body>
</html>