<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>


<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/admin/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx }/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<link href="${context }/css/button.css" rel="stylesheet"/>
<style>
    .message {
        width: 99%;
        height: 100px;
        overflow:auto;
    }
    .l-dialog-win .l-dialog-content {
        overflow: hidden;
    }
</style>
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
        
        $(".install").click(function(){
    		request("component.do?install&componentid="+$(this).attr("componentid") );
    	});
    });
   function init(){
	   $(".start").click(function(){
			request("component.do?start&componentid="+$(this).attr("componentid") );
		});
		$(".stop").click(function(){
			if(confirm( "确认停用此组件吗?" )){
				request("component.do?stop&componentid="+$(this).attr("componentid") );
			}
			
		});
		
		$(".install").click(function(){
			request("component.do?install&componentid="+$(this).attr("componentid") );
		});
		
		$(".uninstall").click(function(){
			
			if(confirm( "确认卸载此组件吗?此操作可能会破坏现有数据，且无法恢复!" )){
				request("component.do?unInstall&componentid="+$(this).attr("componentid") );
			}
			
		});
   }

    function getParent()
    {
        var row = listgrid.getParent(listgrid.getSelectedRow());
        alert(JSON.stringify(row));
    }
    function getSelected()
    {
        var row = listgrid.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
        alert(JSON.stringify(row));
    }
    function getData()
    {
        var data = listgrid.getData();
        alert(JSON.stringify(data));
    }
    function hasChildren()
    {
        var row = listgrid.getSelectedRowObj();
        alert(listgrid.hasChildren(row));
    }
    function isLeaf()
    {
        var row = listgrid.getSelectedRowObj();
        alert(listgrid.isLeaf(row));
    }
    function delMenu(item){
        if($('#dataGrid').treegrid('getSelections').length<1||$('#dataGrid').treegrid('getSelections').length>1){
            alert("请选择数据删除!");
            return;
        }
        var row = $('#dataGrid').treegrid('getSelections')[0];
        if(!confirm("确定删除?")){
            return;
        }

        delObj4Tree("menu.do?delete&id="+row.id);
    }

    function request(requrl){
    	$.ajax({
    		url:requrl+"&ajax=yes",
    		dataType:"json",
    		success:function(result){
    			if(result.success){
    				alert("操作成功"); 
    				location.reload();
    			}else{
    				alert(result.message);
    			}
    		},error:function(){
    			alert("启动组件出现错误");
    		}
    	});
    }

    function addMenu(){
        addOrUpdateDialog('增加菜单','menu.do?add',500,700);
    }

    function addBtn(item){

        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据操作!');
            return;
        }
        addOrUpdateDialog(item,'增加按钮','oper.do?add&menuId='+row.id,400,400);

    }

    function updateMenu(){
        if($('#dataGrid').treegrid('getSelections').length<1||$('#dataGrid').treegrid('getSelections').length>1){
            alert("必须选择一条数据进行修改!");
            return;
        }
        var row = $('#dataGrid').treegrid('getSelections')[0];
        addOrUpdateDialog('修改菜单','menu.do?edit&id='+row.id,500,700);
    }
    function getEnable(value,row,index){
    	if(value==1){
    		return "已启用";
    	}else if(value==0){
    		return "已停用";
    	}else if(value==2){
    		return "错误";
    	}
    	return "";
    }
    function getInstallState(value,row,index){
    	if(value==1){
    		return "已安装";
    	}else if(value==0){
    		return "未安装";
    	}else if(value==2){
    		return "错误"+row.error_message;
    	}
    	return "";
       
    }
  
    function fmoption(value,row,index){
    	var val="";
    	if(row.install_state==1  ){
    		if(row.enable==0){
    			val='<a componentid="'+row.componentid+'" class="start button"  href="javascript:;">启用</a>&nbsp;&nbsp;';
    			val+='<a componentid="'+row.componentid+'" class="uninstall button"  href="javascript:;">卸载</a>'
    		}
    		if(row.enable==1){
    			val='<a componentid="'+row.componentid+'" class="stop button" href="javascript:;">停用</a>'
    		}
    	}else if(row.install_state==0){
    		val='<a  class="install button" href="javascript:;" componentid="'+row.componentid+'" installstate="'+row.install_state+'">安装</a>';
    	}
    	return val;
    }
</script>

<grid:dataGrid action="component.do" height="99%" usePager="false"  width="100%" tree="true" style="easyui" treeField="name" onLoadSuccess="init">
    <grid:column title="名称" field="name" align="left" width="100" id="title"/>
    <grid:column title="安装状态" field="install_state"  width="100" align="center" id="url" renderFun="getInstallState"/>
    <grid:column title="启用状态" field="enable"  width="100" align="center" sortType="int" renderFun="getEnable" id="menutype"/>
    <grid:column title="操作" field="option"  align="center" renderFun="fmoption"/>
</grid:dataGrid>


