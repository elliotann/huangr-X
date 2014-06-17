<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.10.js"></script>
<link href="${context }/js/ligerui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${context }/js/ligerui/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<link href="/jeap/workflow/qtip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<script src="${context }/js/ligerui/js/core/base.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${context }/js/ligerui/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="/jeap/workflow/jquery.outerhtml.js" type="text/javascript"></script>
<script src="/jeap/workflow/qtip/jquery.qtip.js" type="text/javascript"></script>

<table cellspacing="0" cellpadding="0" border="0" width="100%">
    <tbody>
    <c:if test="${fn:length(result) eq 0}">
        <tr>
            <td>无待办任务！</td>
        </tr>
    </c:if>
    <c:if test="${fn:length(result) gt 0}">

        <c:forEach items="${result}" var="entry">
            <tr>

                <td>
                    ${entry['pdname']}->PID:${entry['pid']}->${entry['name']}V:${entry['pdversion']}
                    <a class='trace' href='javascript:void(0);' pid='" + ${entry['pid']}+ "' title='点击查看流程图' onclick="graphTrace({pid:${entry['pid']}})">跟踪</a>
                        <c:if test="${entry['status'] eq 'claim'}">
                            未签收
                        </c:if>

                </td>
            </tr>
        </c:forEach>

    </c:if>
    </tbody>
</table>
<script type="text/javascript">
    function graphTrace(options){
        var _defaults = {
            srcEle: this,
            pid: $(this).attr('pid')
        };
        var opts = $.extend(true, _defaults, options);
        // 获取图片资源
        var imageUrl = "/jeap/core/admin/workflow.do?pInstanceRes&pid=" + opts.pid + "&type=image";
        $.getJSON('/jeap/core/admin/workflow.do?trace&pid=' + opts.pid+'&ajax=true', function(infos) {

            var positionHtml =  '';
            // 生成图片
            var varsArray = new Array();
            $.each(infos, function(i, v) {
                var $positionDiv = $('<div/>', {
                    'class': 'activity-attr'
                }).css({
                            position: 'absolute',
                            left: (v.x - 1),
                            top: (v.y - 1),
                            width: (v.width - 2),
                            height: (v.height - 2),
                            backgroundColor: 'black',
                            opacity: 0
                        });
                // 节点边框
                var $border = $('<div/>', {
                    'class': 'activity-attr-border'
                }).css({
                            position: 'absolute',
                            left: (v.x - 1),
                            top: (v.y - 1),
                            width: (v.width - 4),
                            height: (v.height - 3)
                        });

                if (v.currentActiviti) {
                    $border.addClass('ui-corner-all-12').css({
                        border: '3px solid red'
                    });
                }
                positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
                varsArray[varsArray.length] = v.vars;

            });

            var contentHtml = "<div><img src='" + imageUrl + "' style='position:absolute; left:4px; top:75px;' /><div id='processImageBorder'>"+positionHtml+"</div></div>";

            $.ligerDialog.open({
                height:600,
                width: 800,
                title : '查看流程',
                content:contentHtml,
                showMax: false,
                showToggle: true,
                showMin: false,
                isResize: true,
                slide: false,
                data: {
                    name: $("#txtValue").val()
                },
                //自定义参数
                myDataName: $("#txtValue").val()
            });
        });

    }
</script>