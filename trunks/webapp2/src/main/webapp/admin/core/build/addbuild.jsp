<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp"%>

<%--
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${context}/js/build/pub.js"></script>
</head>
<body>
<div class="main">
    <form action="build.do?toConfirm" method="post" id="addForm">
        <table style="border:0; width:100%; text-align:left; margin-bottom:10px;">
            <tr>
                <td class="tl">*VP单列表:</td>
                <td class="tv tips_bottom">
                    <div help_id="vps_help"><input type="text"
                                                   name="keyword" id="keyword" value="${keyword}" size="100"/></div>
                    <div id="vps_help">多个VP单之间用分号";"连接，构建包会以第一个VP单名称进行命名</div>
                </td>
                <td class="tv" style="text-align:right;">
                    <button type="button" onclick="submitForm()" rowspan="2">下一步</button>
                </td>
            </tr>
        </table>
        <table id="tb_package">
            <tr class="head">
                <td align="left" style="width:47%">
                    <span help_id="all_proj_help" style="font-weight:bold;">所有工程列表：</span>

                    <div id="all_proj_help">当前分支的所有工程列表，双击条目移到已选择搜索工程列表当中</div>
                </td>
                <td align="left" style="width:47%">
                    <span help_id="sel_proj_help" style="font-weight:bold;">已选择搜索工程：</span>

                    <div id="sel_proj_help">目标代码所在的工程，系统将仅会在这些工程中查找相应的文件，
                        如果不指定任何工程则将执行比较费时的全范围查找，即在所有工程中查找。
                        注意不需要选择SQL工程，因为SQL文件会根据VP单列表自动添加进来
                    </div>
                </td>
            </tr>
            <tr>
                <td align="left" valign="top" class="tips_right">
                    <select id="allProjects" size="25" multiple="multiple" style="width:100%; border:#69C solid 1px;"
                            ondblclick="moveOptions('allProjects', 'selectedProjects')">
                        <c:forEach var="proj" items="${repos.projects}">
                            <option value="${proj}">${proj}</option>
                        </c:forEach>
                    </select>
                </td>
                <td align="left" valign="top">
                    <select id="selectedProjects" name="selectedProjects" size="25" multiple="multiple"
                            style="width:100%"
                            ondblclick="moveOptions('selectedProjects','allProjects')">
                        <c:forEach var="proj" items="${projects}">
                            <option value="${proj}">${proj}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="hidden" name="projects" id="projects"/>
    </form>
</div>
<script type="text/javascript" src="${contextPath}/js/fn/1.5/fn.js?use=widget"></script>
<script>
    setCurrentPage("addbuild");
    <c:if test = "${message != null}" > alert("${message}") </c:if>
    function submitForm() {
        var keyword = document.getElementById("keyword");
        if ($.trim(keyword.value) == "") {
            alert("必须输入VP单信息！");
            return;
        }

        if (existChinese(keyword.value)) {
            alert("VP单信息不能包含中文！");
            return;
        }



        var project = document.getElementById("selectedProjects");
        var options = project.options;
        //if ($("[name='onlysql']").attr("checked")) {
        //} else {
        if (options.length == 0) {
            if (!confirm("没有选中工程将需要更多时间搜索，是否继续？")) {
                return;
            }
        }
        //}

        var value = "";

        for (var i = 0; i < options.length; i++) {
            value += options[i].value + ";";
        }
        document.getElementById("projects").value = value;
        document.getElementById("addForm").submit();
    }

    function existChinese(s) {
        return !!((s || '') + '').match(/[\u4E00-\u9FA5]/);
    }
</script>
</body>
</html>
--%>
<style>
    .ms-container{
        background: transparent url('/jeap/adminthemes/default/images/switch.gif') no-repeat 50% 40%;
    }

    .ms-container:after{
        content: "."; display: block; height: 0; line-height: 0; font-size: 0; clear: both; min-height: 0; visibility: hidden;
    }

    .ms-container .ms-selectable, .ms-container .ms-selection {
        background: #fff;
        color: #333;
        float: left;
        width: 45%;
    }

    .ms-container .ms-list{
        -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
        -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
        -ms-transition: border linear 0.2s, box-shadow linear 0.2s;
        -o-transition: border linear 0.2s, box-shadow linear 0.2s;
        transition: border linear 0.2s, box-shadow linear 0.2s;
        border: 1px solid #ddd;
    }

    .ms-selected{
        display:none;
    }
    .ms-container .ms-selectable{
        margin-right: 10%;
    }

    .ms-container .ms-list.ms-focus{
        border-color: #5ca9e4;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
        -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
        outline: 0;
        outline: thin dotted \9;
    }

    .ms-container ul{
        margin: 0;
        padding:0;
        list-style-type: none;
    }

    .ms-container .ms-optgroup-container{
        width: 100%;
    }

    .ms-container ul.ms-list{
        height: 200px;
        padding: 0px;
        overflow-y: auto;
    }
    .ms-container .custom-header {
        background: #efefef;
        color: #555;
        text-align: center;
        font-size: 11px;
        line-height: 26px;
        -webkit-border-radius: 4px 4px 0px 0px;
        border-radius: 4px 4px 0px 0px;
        border-width: 1px 1px 0 1px;
        border-style: solid;
        border-color: #ddd;
        font-weight: 700;
        text-transform: uppercase;
    }
    .ms-container ul.ms-list{
        height: 200px;
        padding: 0px;
        overflow-y: auto;
    }

    .ms-container .ms-list{
        -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
        -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
        -ms-transition: border linear 0.2s, box-shadow linear 0.2s;
        -o-transition: border linear 0.2s, box-shadow linear 0.2s;
        transition: border linear 0.2s, box-shadow linear 0.2s;
        border: 1px solid #ddd;
    }
    .ms-container ul{
        margin: 0;
        padding:0;
        list-style-type: none;
    }
    .main_content li{line-height:22px}
    .ms-container .ms-selectable li.ms-elem-selectable,
    .ms-container .ms-selection li.ms-elem-selection{
        border-bottom: 1px #f2f2f2 solid;
        padding: 4px 10px 3px;
        color: #555;
        font-size: 12px;
        line-height: 16px;
        -webkit-overflow-scrolling: touch;
    }
    .ms-container li.ms-elem-selectable:not(.disabled).ms-hover,
    .ms-container .ms-selection li:not(.disabled).ms-hover{
        cursor: pointer;
        color: #ffffff;
        text-decoration: none;
        background-color: #48A6D2;
        border-color: #48A6D2;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        -ms-box-shadow: none;
        box-shadow: none;
    }

</style>
<script type="text/javascript" src="/jeap/adminthemes/default/switch.js"></script>
<div id="ms-custom-headers" class="ms-container">
    <div class="ms-selectable">
        <div class="custom-header">Selectable item</div>
        <ul class="ms-list">
            <li id="_f_r_-selectable" class="ms-elem-selectable ms-hover"><span>France</span></li>
            <li id="_u_k_-selectable" class="ms-elem-selectable"><span>United Kingdom</span></li>
            <li id="_u_s_-selectable" class="ms-elem-selectable"><span>United States</span></li>
            <li id="_c_h_-selectable" class="ms-elem-selectable"><span>China</span></li>
        </ul>
    </div>
    <div class="ms-selection">
        <div class="custom-header">Selected items</div>
        <ul class="ms-list">
            <li style="display: none;" id="_f_r_-selection" class="ms-elem-selection"><span>France</span></li>
            <li style="display: none;" id="_u_k_-selection" class="ms-elem-selection"><span>United Kingdom</span></li>
            <li style="display: none;" id="_u_s_-selection" class="ms-elem-selection"><span>United States</span></li>
            <li style="display: none;" id="_c_h_-selection" class="ms-elem-selection"><span>China</span></li>
        </ul>
    </div>
</div>