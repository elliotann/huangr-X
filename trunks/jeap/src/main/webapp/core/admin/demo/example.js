$(function(){
	$("#btnAdd").click(function(){
		enation.control.dialog('dlgAdd',
			{
				title:'新增域名',
				url:'/eop_test2/admin/core/user/baseInfo.do',
				resizable:true,
				bgiframe:true,
				width:800,
				height:600,
				modal:true
			}
		);
	});
	
	$("#btnDel").click(function(){
		enation.control.dialog('dlgDel',
			{
				title:'删除',
				url:'/eop_test2/admin/core/user/userDetail.do',
				width:500,
				height:500
			}
		);
	});
});