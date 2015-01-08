<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="/jeap1.0/js/admin/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="/jeap1.0/adminthemes/default/css/button.css" rel="stylesheet" type="text/css" />
<link href="/jeap1.0/adminthemes/default/css/global.css" rel="stylesheet" type="text/css" />
<script src="/jeap1.0/js/commons/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="../CustomersData.js" type="text/javascript"></script>
<script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
 <script src="/jeap1.0/js/admin/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script type="text/javascript">
var listgrid;
var lab = {};
    function delUser(userId)
    {
    	$.ligerDialog.confirm('确认删除?', function (yes) { 
    		if(yes){
    			$.ajax({
    				url:'user.do?delete',
    				type:'post',
    				data:'id='+userId,
    				dataType:'json',
    				success:function(result){
    					if(result.success){
    						$.ligerDialog.waitting(result.msg);
    						setTimeout(function ()
    	                            {
    	                            	$.ligerDialog.closeWaitting();
    	                                listgrid.loadData();
    	                            }, 2000);
    					}
    				}
    			});
    		}
    	});
    	
    }
    function toEdit(userId){
    	$.ligerDialog.open({name:'openDiag', title:'修改用户',url: 'user.do?toEdit&id='+userId, height: 300, width: null, 
    			buttons: [
                     { text: '确定', onclick: function (item, dialog) { openDiag.submitForm(); },cls:'l-dialog-btn-highlight' },
                     { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
                  ], isResize: true,width:600,height:500
                 });
    	
    }
    
        $(function ()
        {
        	listgrid =  $("#maingrid4").ligerGrid({
                 height:'100%',
                 columns: [
                 { display: '用户名', name: 'username', align: 'center', width: 100, minWidth: 60 },
                 { display: '姓名', name: 'realName', align: 'center', width: 100, minWidth: 60 },
                 { display: '邮箱', name: 'email', minWidth: 120 },
                 { display: '办公电话', name: 'tel', minWidth: 120 },
                 { display: '最后登录时间', name: 'lastLoginTime', minWidth: 140 },
                 { display: '登录次数', name: 'loginCount' },
                 { display: '所属公司', name: 'companyId' },
                 { display: '状态', name: 'status',render:function(item){
                	 if(item.status=='ACTIVE'){
                		 return "激活";
                	 }else{
                		 return "禁用";
                	 }
                 } },
                 { display: '操作', name: '',width:'auto',render:function(item){
                	 return "<a href='javascript:void(0)' onclick='toEdit("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='delUser("+item.id+")'>删除</a>";
                 } }
                 ], url:'user.do?datalist',  pageSize:30 ,rownumbers:true,
                 toolbar:{ items: [
                                            { text: '增加', click: addUser, icon: 'add' },
                                            { line: true }]
                                            }
             });
        	//搜索框 收缩/展开
        	$(".searchtitle .togglebtn").live('click', function ()
        	{
        	    if ($(this).hasClass("togglebtn-down")) $(this).removeClass("togglebtn-down");
        	    else $(this).addClass("togglebtn-down");
        	    var searchbox = $(this).parent().nextAll("div.searchbox:first");
        	    searchbox.slideToggle('fast');
        	}); 
        	var fields=[{ display: "用户名", name: "username", type: "text" ,width:470,id:'username' }];
        	//$("#searchForm").ligerForm({fields:fields});
        	var griddata;
         	$("#btn1container").click(function(){
         		
         		/* $.ajax({
                    url: 'user.do?datalist',
                    dataType: 'json',
                    type: 'POST',
                    data:'username='+$('#username').val(),
                    success: function (result) {
                        if (result.total > 0) {
                            griddata = JSON.stringify(result);
                            alert(JSON.stringify(result));
                            
                        }
                    }
                });
         	
         		listgrid.options.data = $.extend(true,{}, griddata);
                listgrid.showFilter(); */
         		//listgrid.loadData(griddata);
         		 listgrid.loadServerData("username="+$("#username").val());
                 return false;
         	});
        });
        function addUser()
        {
            $.ligerDialog.open({name:'openDiag', title:'增加用户',url: 'user.do?toAdd', height: 300, width: null, buttons: [
                { text: '确定', onclick: function (item, dialog) { openDiag.submitForm(); },cls:'l-dialog-btn-highlight' },
                { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
             ], isResize: true,width:600,height:500
            });
        } 
  		function itemclick(item){
  			
  		}
  	//过滤属性  
        function f_getWhere() {
            //  alert(JSON.stringify(griddata));  
            if (!listgrid) return null;

           var clause = function (rowdata, rowindex) {
                
  
                return ((rowdata.UserName.indexOf(username) > -1) );
            };
            return clause;
        }  
    </script>
</head>
<body style="padding: 3px; overflow: hidden;">
	<div>
		<div style="width: 98%">
			<div class="searchtitle">
				<span>搜索</span><img src="../icons/searchtool.gif" />
				<div class="togglebtn"></div>
			</div>
			<div class="navline" style="margin-bottom: 4px; margin-top: 4px;"></div>
			<div class="searchbox">
				<form id="searchForm">
					用户名：<input type="text" value="" class="liger-textbox" id="username"/> 
				</form>
				<ul><li id="btn1container"><div class="button button2 buttonnoicon" style="width:60px"><div class="button-l"> </div><div class="button-r"> </div> <span>搜索</span></div></li></ul>
				<div class="l-clear"></div>
			</div>
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0"></div>
	</div>
</body>
</html>
