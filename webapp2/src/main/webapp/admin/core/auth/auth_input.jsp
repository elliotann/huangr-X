<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css">
    <script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerSpinner.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script type="text/javascript">
    	function Auth(){
    		this.roleId = 0;
    		this.funId = 0;
    		this.type = 0;
    		this.operations  = [];
    	}
    	var myAuths = [];
        var dialog = frameElement.dialog;
        var manager;
        $(function ()
        {
            listgrid = $("#maingrid").ligerGrid({
                        height:'auto',
                        columns: [
                            { display: 'id', name: 'id', id: 'menuId',  align: 'center',width:60 },
                            { display: '名称', name: 'title', id: 'menuName',  align: 'left',width:250 },
                            { display: '类型', name: 'menutype', id: 'menutype',  align: 'center',width:215,render:function(item){
                            	if(item.menutype==5){
                            		return "操作";
                            	}else{
                            		return "菜单";
                            	}
                            } }], width: '100%', usePager:false, checkbox: true,
                        url: 'auth.do?dataGrid&ajax=yes&roleId=${roleId}', alternatingRow: true,selectRowButtonOnly:true, tree: {
                            columnId: 'menuName',
                            idField: 'id',
                            parentIDField: 'pid'
                        },onCheckRow:onCheckRow, isChecked: f_isChecked
                    }
            );
            manager = $("#maingrid").ligerGetGridManager();

        });
        function onAfterShowData(currentData){
            $(currentData.rows).each(function(i,item){
            	alert(item.title);
                    selectChildren(item);
            }); 
        }
        function selectChildren(item){
            if(item.hasAuth) {
                manager.select(item);
                if (item.hasChildren){
                    $(item.children).each(function(i,data){
                        selectChildren(data);
                    });
                }
            }
        }
        function authSelect(item){
                var  html="";
                if(item.hasChildren){
                    return html;
                }
                $.ajax({
                    url:'auth.do?getBtnByMenuId&ajax=yes&id='+item.id+'&roleId='+${roleId},
                    type:'post',
                    dataType:'json',
                    async:false,
                    success:function(result){
                        html = result;
                    }
                });
                html += "<input type='hidden' id='menu"+item.id+"' value='"+item.__id+"'/>";
                return html;

        }
        function onCheckRow(checked,data,rowid,rowdata){
		
            var parent = manager.getParent(data);
            if(parent!=null&&checked){
                selectParent(parent);
            }
            var children = manager.getChildren(data);
            if(children.length>1&&!checked){
            	for(var i=0;i<children.length;i++){
            		unSelectChild(children[i]);
            	}
            	
            }
            var rows = manager.getSelectedRows();

            var menu=[];
			var tempObj = [];
            $(rows).each(function () {
      
                var existObj = isExist(myAuths,this.id);
            	if(!existObj){
            		var auth=new Auth();
                	auth.funId = this.id;
                	auth.roleId = ${roleId};
                	tempObj.push(auth);
            	
            	}else{
            		tempObj.push(existObj);
            		
            	}
            });

            myAuths = [];
            myAuths=tempObj;
           
			alert(JSON.stringify(myAuths));

        }

        function selectParent(data){
            manager.select(data);
            if(data.pid!=0){
                selectParent(manager.getParent(data));
            }

        }
        
        function unSelectChild(data){
        	alert(JSON.stringify(data));
            manager.unselect(data);
            if(data.children.length>1){
            	for(var i=0;i<data.children.length;i++)
            		unSelectChild(manager.getChildren(data.children[i]));
            }

        }

        function submitForm(){
        	  var rows = manager.getSelectedRows();

        	myAuths = [];
        	var opers = [];
        	 $(rows).each(function () {
        		
   		 		var auth=new Auth();
               	auth.funId = this.id;
               	auth.type=this.menutype;
               	auth.roleId = ${roleId};
               	myAuths.push(auth);

             	
             });
        	 var param = JSON.stringify(myAuths);
        
        	$.ajax({
        		url:'auth.do?saveAuth&ajax=true',
        		type:'post',
        		data:'data='+param,
        		dataType:'json',
        		success:function(result){
        			 $.ligerDialog.waitting('增加成功');
        	            setTimeout(function ()
        	            {
        	                $.ligerDialog.closeWaitting();
        	                dialog.close();
        	            }, 1000);
        		},
        		error:function(e){
        			
        		}
        	});


           
        }
        function checkOperation(obj){
            //判断功能权限是否选中
            var rowid = obj.getAttribute("id").split("_")[1];
            var rowdata = manager.getRow($("#menu"+rowid).val());
            var param = {menuIds:[rowdata.id+''],roleId:${roleId},operId:obj.value,isCheck:obj.checked,ajax:'true'};
           
            if(manager.isSelected(rowdata)){
            	
            	var existObj = isExist(myAuths,rowid);
            	if(!existObj){
            		var auth=new Auth();
                	auth.funId = rowid;
                	auth.roleId = ${roleId};
                	auth.operations.push(obj.value);
            		myAuths.push(auth);
            	}else{
            		if(obj.checked)
            			existObj.operations.push(obj.value);
            		else{
            			var tempOperations = [];
            			var j=0;
            			for(var i=0;i<existObj.operations.length;i++){
            				if(obj.value!=existObj.operations[i]){
            					tempOperations[j] = existObj.operations[i];
            					j++;
            				}
            			}
            			existObj.operations = tempOperations;
            		}
            		
            	}
                
            }
            
        }
        function isExist(obj,menuId){
        	for(var i=0;i<obj.length;i++){
        		if(obj[i].funId==menuId)
        		{
        			return obj[i];
        		}
        	}
        	return null;
        }
        function f_isChecked(rowdata)
        {
     
        	var menuids = ${menuids};
       		if(!menuids) return false;
       		for(var i=0;i<menuids.length;i++){
       			if (rowdata.id == menuids[i]) {
       				return true;
       			}
       		}
           
            return false;
        }
    </script>
</head>

<body style="padding: 4px">
<div position="center" title="功能权限">
    <div id="maingrid">

    </div>
</div>
</body>
</html>

