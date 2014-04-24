<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css"
          href="/jeap/workflow/js/myflow/lib/jquery-ui-1.8.4.custom/css/smoothness/jquery-ui-1.8.4.custom.css"
          rel="stylesheet" />

    <script type="text/javascript" src="/jeap/workflow/js/myflow/lib/raphael.js"></script>
    <script type="text/javascript"
            src="/jeap/workflow/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript"
            src="/jeap/workflow/js/myflow/lib/jquery-ui-1.8.4.custom/js/jquery-ui-1.8.4.custom.min.js"></script>
    <script type="text/javascript" src="/jeap/workflow/js/myflow/myflow.js"></script>
    <script type="text/javascript" src="/jeap/workflow/js/myflow/myflow.jpdl4.js"></script>
    <script type="text/javascript" src="/jeap/workflow/js/myflow/myflow.editors.js"></script>
    <link href="../../../lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link href="../../../lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
    <link href="../../../lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet"/>

    <script src="../../../lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
    <script src="../../../lib/ligerUI/js/plugins/ligerForm.js"></script>
    <script src="../../../lib/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
    <script src="../../../lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script type="text/javascript">
        var designerFlow;
        $(function() {
            designerFlow = $('#myflow')
                    .myflow(
                    {
                        basePath : "",
                        restore : eval(""),
                        tools : {
                            save : {
                                onclick : function(data) {
                                    alert(data);
                                    $.ajax({
                                        type:'post',
                                        url:'workflow.do?designerDeploy&jsonData='+data,
                                        dataType:'json',
                                        success:function(result){
                                            alert(result);
                                        }
                                    });
                                }
                            }
                        }
                    });

        });

        function submitForm(){
            $("#myflow_save").click();
        }
    </script>
    <style type="text/css">
        body {
            margin: 0;
            pading: 0;
            text-align: left;
            font-family: Arial, sans-serif, Helvetica, Tahoma;
            font-size: 12px;
            line-height: 1.5;
            color: black;
            background-image: url(img/bg.png);
        }

        .node {
            width: 70px;
            text-align: center;
            vertical-align: middle;
            border: 1px solid #fff;
        }

        .mover {
            border: 1px solid #ddd;
            background-color: #ddd;
        }

        .selected {
            background-color: #ddd;
        }

        .state {

        }

        #myflow_props table {

        }

        #myflow_props th {
            letter-spacing: 2px;
            text-align: left;
            padding: 6px;
            background: #ddd;
        }

        #myflow_props td {
            background: #fff;
            padding: 6px;
        }

        #pointer {
            background-repeat: no-repeat;
            background-position: center;
        }

        #path {
            background-repeat: no-repeat;
            background-position: center;
        }

        #task {
            background-repeat: no-repeat;
            background-position: center;
        }

        #state {
            background-repeat: no-repeat;
            background-position: center;
        }
    </style>
</head>
<body>
<div id="myflow_tools"
     style="position: absolute; top: 10; left: 10; background-color: #fff; width: 70px; cursor: default; padding: 3px;"
     class="ui-widget-content">
    <div id="myflow_tools_handle" style="text-align: center;" class="ui-widget-header">工具集</div>


    <div class="node" id="myflow_save"><img src="img/save.gif" />&nbsp;&nbsp;保存</div>
    <div>
        <hr />
    </div>
    <div class="node selectable" id="pointer"><img
            src="img/select16.gif" />&nbsp;&nbsp;选择</div>
    <div class="node selectable" id="path"><img
            src="img/16/flow_sequence.png" />&nbsp;&nbsp;转换</div>
    <div>
        <hr />
    </div>
    <div class="node state" id="start" type="start"><img
            src="img/16/start_event_empty.png" />&nbsp;&nbsp;开始</div>
    <div class="node state" id="state" type="state"><img
            src="img/16/task_empty.png" />&nbsp;&nbsp;状态</div>
    <div class="node state" id="task" type="task"><img
            src="img/16/task_empty.png" />&nbsp;&nbsp;任务</div>
    <div class="node state" id="fork" type="fork"><img
            src="img/16/gateway_parallel.png" />&nbsp;&nbsp;分支</div>
    <div class="node state" id="join" type="join"><img
            src="img/16/gateway_parallel.png" />&nbsp;&nbsp;合并</div>
    <div class="node state" id="end" type="end"><img
            src="img/16/end_event_terminate.png" />&nbsp;&nbsp;结束</div>
    <div class="node state" id="end-cancel" type="end-cancel"><img
            src="img/16/end_event_cancel.png" />&nbsp;&nbsp;取消</div>
    <div class="node state" id="end-error" type="end-error"><img
            src="img/16/end_event_error.png" />&nbsp;&nbsp;错误</div>
</div>

<div id="myflow_props"
     style="position: absolute; top: 30; right: 50; background-color: #fff; width: 220px; padding: 3px;"
     class="ui-widget-content">
    <div id="myflow_props_handle" class="ui-widget-header">属性</div>
    <table border="1" width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td>aaa</td>
        </tr>
        <tr>
            <td>aaa</td>
        </tr>
    </table>
    <div>&nbsp;</div>
</div>

<div id="myflow"></div>
</body>
</html>