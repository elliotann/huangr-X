function graphTrace(options) {

    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid')
    };
    var opts = $.extend(true, _defaults, options);

    /*// 处理使用js跟踪当前节点坐标错乱问题
    $('#changeImg').live('click', function() {
        $('#workflowTraceDialog').dialog('close');
        if ($('#imgDialog').length > 0) {
            $('#imgDialog').remove();
        }
        $('<div/>', {
            'id': 'imgDialog',
            title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点',
            html: "<img src='" + "" + '/core/admin/workflow/trace/auto/' + opts.pid + "' />"
        }).appendTo('body').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });
    });
*/
    // 获取图片资源
    var imageUrl = "/workflow/resource/process-instance?pid=" + opts.pid + "&type=image";
    $.getJSON('/jeap/core/admin/workflow.do?trace&pid=' + opts.pid, function(infos) {

        var positionHtml = "";

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
                opacity: 0,
                zIndex: $.fn.qtip.zindex - 1
            });

            // 节点边框
            var $border = $('<div/>', {
                'class': 'activity-attr-border'
            }).css({
                position: 'absolute',
                left: (v.x - 1),
                top: (v.y - 1),
                width: (v.width - 4),
                height: (v.height - 3),
                zIndex: $.fn.qtip.zindex - 2
            });

            if (v.currentActiviti) {
                $border.addClass('ui-corner-all-12').css({
                    border: '3px solid red'
                });
            }
            positionHtml += $positionDiv.outerHTML() + $border.outerHTML();
            varsArray[varsArray.length] = v.vars;
        });
        positionHtml =  '<div style="position: absolute; left: 89px; top: 79px; width: 103px; height: 53px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 89px; top: 79px; width: 101px; height: 52px; z-index: 14998; border: 3px solid red;" class="activity-attr-border ui-corner-all-12"></div><div style="position: absolute; left: 249px; top: 86px; width: 38px; height: 38px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 249px; top: 86px; width: 36px; height: 37px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 217px; top: 189px; width: 103px; height: 53px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 217px; top: 189px; width: 101px; height: 52px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 357px; top: 79px; width: 103px; height: 53px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 357px; top: 79px; width: 101px; height: 52px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 494px; top: 86px; width: 38px; height: 38px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 494px; top: 86px; width: 36px; height: 37px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 589px; top: 79px; width: 103px; height: 53px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 589px; top: 79px; width: 101px; height: 52px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 249px; top: 279px; width: 38px; height: 38px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 249px; top: 279px; width: 36px; height: 37px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 9px; top: 89px; width: 33px; height: 33px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 9px; top: 89px; width: 31px; height: 32px; z-index: 14998;" class="activity-attr-border"></div><div style="position: absolute; left: 624px; top: 282px; width: 33px; height: 33px; background-color: rgb(0, 0, 0); opacity: 0; z-index: 14999;" class="activity-attr"></div><div style="position: absolute; left: 624px; top: 282px; width: 31px; height: 32px; z-index: 14998;" class="activity-attr-border"></div>';
        $.ligerDialog.open({
            height:600,
            width: 800,
            title : '增加管理员',
            content:'fsafa',
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
        if ($('#workflowTraceDialog').length == 0) {
            $('<div/>', {
                id: 'workflowTraceDialog',
                title: '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button>',
                html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                "<div id='processImageBorder'>" +
                positionHtml +
                "</div>" +
                "</div>"
            }).appendTo('body');
        } else {
            $('#workflowTraceDialog img').attr('src', imageUrl);
            $('#workflowTraceDialog #processImageBorder').html(positionHtml);
        }

        // 设置每个节点的data
        $('#workflowTraceDialog .activity-attr').each(function(i, v) {
            $(this).data('vars', varsArray[i]);
        });

        // 打开对话框
        $('#workflowTraceDialog').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            open: function() {
                $('#workflowTraceDialog').dialog('option', 'title', '查看流程（按ESC键可以关闭）<button id="changeImg">如果坐标错乱请点击这里</button>');
                $('#workflowTraceDialog').css('padding', '0.2em');
                $('#workflowTraceDialog .ui-accordion-content').css('padding', '0.2em').height($('#workflowTraceDialog').height() - 75);

                // 此处用于显示每个节点的信息，如果不需要可以删除
                $('.activity-attr').qtip({
                    content: function() {
                        var vars = $(this).data('vars');
                        var tipContent = "<table class='need-border'>";
                        $.each(vars, function(varKey, varValue) {
                            if (varValue) {
                                tipContent += "<tr><td class='label'>" + varKey + "</td><td>" + varValue + "<td/></tr>";
                            }
                        });
                        tipContent += "</table>";
                        return tipContent;
                    },
                    position: {
                        at: 'bottom left',
                        adjust: {
                            x: 3
                        }
                    }
                });
                // end qtip
            },
            close: function() {
                $('#workflowTraceDialog').remove();
            },
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });

    });
}
