<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/7/8
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/admin/commons/taglibs.jsp"%>
<html>
<head>
    <title></title>

    <script src="/jeap/js/common/jquery-1.8.3.js"></script>
    <script src="/jeap/js/common/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

    <link rel="stylesheet" href="/jeap/js/common/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <SCRIPT type="text/javascript" >
        <!--
        var zTree;
        var demoIframe;

        var setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: ""
                }
            },
            callback: {
                beforeClick: function(treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj("tree");
                    if (treeNode.isParent) {
                        zTree.expandNode(treeNode);
                        demoIframe.attr("src","toAdd.do?id="+treeNode.id);
                        return false;
                    } else {
                        demoIframe.attr("src","toAdd.do?id="+treeNode.id);
                        return true;
                    }
                }
            }
        };


        var menus =${menus};
        $(document).ready(function(){
            var t = $("#tree");
            t = $.fn.zTree.init(t, setting, menus);
            demoIframe = $("#testIframe");
            demoIframe.bind("load", loadReady);
            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.selectNode(zTree.getNodeByParam("id", 101));

        });

        function loadReady() {
            var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
                    htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
                    maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
                    h = demoIframe.height() >= maxH ? minH:maxH ;
            if (h < 530) h = 530;
            demoIframe.height(h);
        }

        //-->
    </SCRIPT>
</head>
<body style="background-color: #EEEEEE;">
<TABLE border=0 height=200px align=left>
    <TR>
        <TD width="150px" align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
            <ul id="tree" class="ztree" style="width:150px; overflow:auto;"></ul>
        </TD>
        <TD width=850px align=left valign=top><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px SRC="core/standardData.html"></IFRAME></TD>
    </TR>
</TABLE>
</body>
</html>
