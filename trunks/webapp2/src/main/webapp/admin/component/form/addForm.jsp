<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>ligerui表格表单设计器</title>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
   <link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${staticserver }/js/common/jquery-1.6.4.js"></script> 
    <script src="${ctx }/admin/component/form/js/AllProductData.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/ligerui.min.js" type="text/javascript"></script> 
    <script src="${context }/js/ligerui/js/plugins/ligerForm.js" type="text/javascript"></script>
    <script src="${ctx }/admin/component/form/js/ligerGrid.showFilter.js" type="text/javascript"></script>
    <link href="${ctx }/admin/component/form/css/lab.css" rel="stylesheet" type="text/css" />
    <script src="${ctx }/admin/component/form/js/lab.js" type="text/javascript"></script>
    <script src="${ctx }/admin/component/form/js/index.js" type="text/javascript"></script>
    <script>
    var columns = [
                   {"fieldName":"ID", "isPK": true, "isInForeignKey": false, "isNullable": false, 
                	   "inputType": "digits", "isAutoKey": true, "sourceTableName": "", 
                	   "sourceTableIDField": "", "sourceTableTextField": "", "name": "ProductID", 
                	   "displayName": "主键", "type": "column", "icon": "images/table_key.png" 
                   }];
    var formData;
    function submitForm()
    {
        var d = bulidData();
        $.ajax({
        	url:'designer.do?save&ajax=true',
        	type:'post',
        	data:'data='+$.ligerui.toJSON(d),
        	success:function(result){
        		if(result.success){
    				$.ligerDialog.waitting(result.msg);
                    setTimeout(function ()
                    {
                    	
                    	$.ligerDialog.closeWaitting();
                    	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
                        dialog.close();//关闭dialog 
                        
                    }, 2000);
                    window.parent.listgrid.loadData();
    			}
        	}
        });
    }
    </script>
</head>
<body>
</body>
</html>