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
    <script type="text/javascript" src="/jeap/js/common/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="/jeap/js/common/zTree_v3/js/jquery.ztree.exedit-3.5.js"></script>
    <link rel="stylesheet" href="/jeap/js/common/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Aqua/css/ligerui-all.css" />
    <link rel="stylesheet" href="/jeap/adminthemes/default/js/ligerui/skins/Gray/css/all.css" />
    <script src="../../adminthemes/default/js/base.js"></script>
    <script src="../../adminthemes/default/js/ligerui/js/plugins/ligerDialog.js"></script>
    <SCRIPT type="text/javascript" >
        <!--
        var zTree;
        var demoIframe;

        var setting = {
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false,
                addHoverDom:addHoverDom,
                removeHoverDom: removeHoverDom
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: ""
                }
            },
            edit:{
                enable: true,
                showRemoveBtn: showRemoveBtn
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
                        return false;
                    }
                },
                beforeRemove: beforeRemove
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
        var newCount = 1;
        var log, className = "dark";
        function addHoverDom(treeId, treeNode){
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='增加子菜单' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                var zTree = $.fn.zTree.getZTreeObj("tree");
                //zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                demoIframe.attr("src","toAdd.do?pid="+treeNode.id);
                return false;
            });
        }
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
        };
        function showRemoveBtn(treeId, treeNode) {
            return !treeNode.isFirstNode;
        }

        function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 8) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }
        function beforeRemove(treeId, treeNode){
            className = (className === "dark" ? "":"dark");

            var zTree = $.fn.zTree.getZTreeObj("tree");
            zTree.selectNode(treeNode);
            if(confirm("确认删除菜单" + treeNode.name + " 吗？")){
                $.ajax({
                    type:'post',
                    url:'delMenu.do',
                    data:'id='+treeNode.id,
                    success:function(result){
                        if(result.success){
                            $.ligerDialog.waitting('操作成功');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                            }, 1000);
                            return true;
                        }else{
                            $.ligerDialog.waitting('操作失败,有子菜单存在！');
                            setTimeout(function ()
                            {
                                $.ligerDialog.closeWaitting();
                            }, 1000);
                            return false;
                        }
                    },
                    error:function(e){
                        $.ligerDialog.waitting(e);
                        setTimeout(function ()
                        {
                            $.ligerDialog.closeWaitting();
                        }, 1000);
                        return false;
                    }
                });

            }
            return false;

        }
        //-->
    </SCRIPT>
    <style type="text/css">
        .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
    </style>
</head>
<body style="background-color: #EEEEEE;">

<TABLE border=0 height=200px align=left>
    <TR>
        <TD width="200px" align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
            <ul id="tree" class="ztree" style="width:200px; overflow:auto;"></ul>
        </TD>
        <TD width="100%" align=left valign=top><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px SRC="core/standardData.html"></IFRAME></TD>
    </TR>
</TABLE>
</body>
</html>
