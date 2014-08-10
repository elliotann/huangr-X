<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<script type="text/javascript" charset="utf-8" src="/esf/adminthemes/default/js/designer/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/esf/adminthemes/default/js/designer/editor_api.js"> </script>

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/esf/adminthemes/default/js/designer/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/easyui.css">
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/demo.css">
<link rel="stylesheet" type="text/css" href="/esf/adminthemes/default/css/icon.css">

<script type="text/javascript" src="/esf/adminthemes/default/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/esf/adminthemes/default/js/designer/easyui/custom/tree.js"></script>

<div class="toolbar">
    <ul>
        <li><a href="themeFile!list.do?themeid=${themeid }&folderName=${folderName}&type=${type}">返回</a></li>
    </ul>
    <div style="clear: both;"></div>
</div>
<div class="input">
    <form  action="themeFile!edit.do" method="post" class="validate" id="submitForm">
        <input type="hidden" name="name" value="${name }" />
        <input type="hidden" name="themeid" value="${themeid }" />
        <input type="hidden" name="folderName" value="${folderName }" />
        <input type="hidden" name="type" value="${type }" />
        <input type="hidden" name="themeType" value="${themeType }" />
        <input type="hidden" value="<c:out value="${content}" escapeXml="true"></c:out>" id="content" name="content"/>



    </form>
</div>
<script type="text/javascript">
    $(function(){
        //$("form.validate").validate();

        //实例化编辑器
        var ue = UE.getEditor('editor',{
            initialContent:$("#content").val()
        });
        $("#submitBtn").click(function(){
            $("#content").val(ue.getContent());
            document.getElementById("submitForm").submit();
        });

        $('#tt').tree({
            dnd:'true',
            url:'tree_data1.json',
            method:'get',
            animate:true,
            onMouseUp:function(e){
                alert("here");
            }
        });


    });
</script>
<style type="text/css">
    html,body{
        overflow:hidden;
        height:100%;
        margin:0;
        padding:0;
        font:14px/1.8 Georgia, Arial, Simsun;
    }
    html{
        _padding:110px 0;
    }
    #hd{
        position:absolute;
        top:0;
        left:0;
        width:100%;
        height:100px;
        background:#999;
    }
    #bd{
        position:absolute;
        top:110px;
        right:0;
        bottom:10px;
        left:0;
        overflow:hidden;
        width:100%;
        _height:100%;
    }
    #side{
        position:absolute;
        top:0;
        left:0;
        bottom:0;
        overflow:auto;
        width:200px;
        _height:100%;
        background:#ffffff;
    }
    #property{
        position:absolute;
        top:0;
        right:0;
        bottom:0;
        overflow:auto;
        width:240px;
        _height:100%;
        background:#ffffff;
    }
    #main{
        position:absolute;
        _position:static;
        top:0;
        right:250px;
        bottom:0;
        left:210px;
        overflow:visible;
        _overflow:hidden;
        _height:100%;
        _margin-left:210px;
        _margin-right:210px;
        background:#666;
    }
    #content{
        _overflow:auto;
        _width:100%;
        _height:100%;
    }

        /* 与布局无关，一些说明性内容样式 */
    .tit-layout{margin:30px 0 0;font-size:18px;text-align:center;}
    .copyright{font-weight:bold;text-align:center;}
    #feature{width:200%;line-height:4;}
    #feature .hd{padding:20px 15px;}
    #feature .hd h2{margin:0;font-size:16px;}
    #feature .bd ol{margin-top:0;}
    #feature .bd h3{margin:0;padding:0 15px;font-size:14px;}

</style>
<!--头部-->
<%--<div id="hd">
    <div class="submitlist" align="center">
        <table>
            <tr>
                <td><input type="button" name="submit" value="提交更改" class="submitBtn" id="submitBtn" /></td>
            </tr>
        </table>
    </div>
</div>--%>
<!--头部-->
<%--<div id="bd">
    <div id="side">
        <div class="easyui-tabs" style="width:200px;height:500px">

            <div title="组件" style="padding:10px">
                <ul id="tt" class="easyui-tree"></ul>
            </div>

        </div>
    </div>
    <div id="main">
        <script id="editor" type="text/plain" style="width:100%;height:100%;"></script>
    </div>
    <div id="property">
        <table id="pg" class="easyui-propertygrid" style="width:235px" data-options="
                title:'属性',
				url:'propertygrid_data1.json',
				method:'get',
				showGroup:true,
				scrollbarSize:0
			">
        </table>
    </div>
</div>--%>


<div class="easyui-layout" style="width:100%;height:800px;">
    <div data-options="region:'north'" style="height:50px">
        <input type="button" name="submit" value="提交更改" class="submitBtn" id="submitBtn" />

    </div>

    <div data-options="region:'east',split:true" title="属性" style="width:260px;">
        <table id="pg" class="easyui-propertygrid" style="width:250px" data-options="
				url:'propertygrid_data1.json',
				method:'get',
				showGroup:true,
				scrollbarSize:0
			">
        </table>
    </div>
    <div data-options="region:'west',split:true" title="组件" style="width:180px;">
        <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:false"></ul>
    </div>
    <div data-options="region:'center',title:'工作区',iconCls:'icon-ok'">
        <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
            <div title="设计" data-options="href:'_content.html',accept: '.tree-node'" style="padding:10px"></div>
            <div title="源码" style="padding:5px;height: 520px;">
                <script id="editor" type="text/plain" style="width:100%;height:100%;"></script>
            </div>
            <div title="预览" style="padding:5px" data-options="href:'../../adminthemes/default/js/designer/dialogs/preview/preview.html'">

            </div>
        </div>
    </div>
</div>
</div>