<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ligerui表格表单设计器</title>
    <link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${context}/js/plug-in/jquery/jquery-1.3.2.min.js"></script>
    <script src="/jeap/form/config/AllProductData.js" type="text/javascript"></script>
    <script src="${context }/js/ligerui/js/ligerui.min.js" type="text/javascript"></script>
    <script src="/jeap/form/config/ligerGrid.showFilter.js" type="text/javascript"></script>
    <link href="/jeap/form/config/lab.css" rel="stylesheet" type="text/css" />

    <script src="/jeap/form/config/lab.js" type="text/javascript"></script>
    <script src="/jeap/form/config/index.js" type="text/javascript"></script>
    <script type="text/javascript">
        function outjson()
        {

            var manager = $("#gridP").ligerGetGridManager();
            var data = manager.getData();

            var jsonData = JSON.stringify(data);
            var tableName = parent.document.getElementById("tableName").value; //调用父页面的元素
            var tableTitle = parent.document.getElementById("tableTitle").value; //调用父页面的元素
            var formId =0;
            if(parent.document.getElementById("formId")!=null) {
                formId = parent.document.getElementById("formId").value
            }

            var jsonData = "{\"tableName\":\""+tableName+"\",\"tableTitle\":\""+tableTitle+"\",\"fields\":"+jsonData+"}";
            $.ajax({
                type: "post",
                url: "designer.do?save",
                data:"ajax=yes&rmd="+ new Date().getTime()+"&data="+jsonData+"&formId="+formId,
                dataType:"json",
                async:false,
                success: function(html){
                    return true;
                },error:function(e){
                    alert(e);
                    return false;
                }
            });

            return true;
        }

    </script>
</head>
<body>

</body>
</html>