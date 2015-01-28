/**
 * 列表面通用的操作，增加、修改、删除操作
 */
(function(){
	CRUD = {
				addOrUpdateDialog:addOrUpdateDialog,
				delObj:delObj,
				delObj4Tree:delObj4Tree
			};
	//增加或者修改弹出框
	function addOrUpdateDialog(title,url,height,width)
	{
		
		$.ligerDialog.open({name:'openDiag',title:title,url: url, height: height, width: width, 
			buttons: [
				   { text: '确定', onclick: function (item, dialog) { openDiag.submitForm(); },cls:'l-dialog-btn-highlight' },
				   { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
				    ], isResize: true,width:600,height:500
				   });
	}
	//确定回调函数
	function addForm(savebtn){
	
	    $("#objForm").submit();
	}


	function delObj(url,id)
	{
		url += "&ajax=true&rmd="+ new Date().getTime();
		$.ligerDialog.confirm('确认删除?', function (yes) { 
    		if(yes){
    			$.ajax({
    				url:url,
    				type:'post',
    				data:'id='+id,
    				dataType:'json',
    				success:function(result){
    					if(result.success){
    						$.ligerDialog.waitting(result.msg);
    						setTimeout(function ()
    	                            {
    	                            	$.ligerDialog.closeWaitting();
    	                                listgrid.loadData();
    	                            }, 1000);
    					}
    				}
    			});
    		}
    	});
	}

	function delObj4Tree(url,id)
	{
	    url += "&ajax=true&rmd="+ new Date().getTime();
	    var options = {
	        url : url,
	        type : "POST",
	        dataType : 'json',
	        success : function(result) {
	            if(result.success){
	                alert("删除成功");
	                $('#dataGrid').treegrid('reload');
	            }

	        },
	        error : function(e) {
	            $.Loading.error("出现错误 ，请重试");
	        }
	    };
	    $('#dataGridform').ajaxSubmit(options);
	}
}());

