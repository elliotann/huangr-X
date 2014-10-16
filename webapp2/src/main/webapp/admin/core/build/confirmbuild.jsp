<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="main">
    <form action="build.do?doBuild" id="confForm" method="POST">
        <table id="tb_package">
            <tr>
                <td style="width:500px;" class="tv tips_bottom" >
                    <div help_id="file_help"><input type="text" id="file_name" style="width:450px"/></div>
                    <div id="file_help">将包含在构建包中的文件列表。文件路径不带SVN目录，即从项目名称开始。注意不要将SQL文件加入该列表。SQL文件会根据VP单列表自动加到最终构建包当中</div>
                </td>
                <td class="tv" style="text-align:right;">
                    <button type="button" onclick="window.location='<%=request.getContextPath()%>/manage/addbuild.jsp';">上一步</button>
                    <button type="button" onclick="doNext()">提交构建</button>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <textarea id="build_files" name="build_files" multiple="multiple" style="width:100%; height:400px;">
                        <c:forEach var="path" items="${changedFiles.paths}">${path};
                        </c:forEach>
                    </textarea>
                </td>
            </tr>
            <tr>
                <td>构建说明<br/>
                    <textarea rows="10" cols="80" style="width:100%; border:#69C solid 1px;" name="comment">${changedFiles.comments}</textarea>
                </td>
            </tr>
        </table>
        <input type="hidden" name="build_files" id="build_files"/>
    </form>
</div>


<script type="text/javascript">
    function changeSelected(select) {
        value = select.options[select.selectedIndex].value;
        document.getElementById("file_name").value = value;
        document.getElementById("btn_del_file").disabled = false;
    }

    function remove() {
        var select = document.getElementById("file_list");
        select.remove(select.selectedIndex);
    }

    function add() {
        var value = document.getElementById("file_name").value;
        if (value == "") {
            alert("请指定SVN文件全路径");
            return;
        }
        var select = document.getElementById("file_list");
        var options = select.options;
        for (var i=0; i<options.length; i++) {
            if (options[i].value == value) {
                alert("指定的文件路径已存在！");
                return;
            }
        }
        var newOpt1 = new Option(value, value);
        select.options[options.length] = newOpt1;
        document.getElementById("file_name").value = "";
    }

    function doNext() {
        //var select = document.getElementById("file_list");
        //var options = select.options;
        //var value = "";
        //for (var i=0; i<options.length; i++) {
        //	value += options[i].text + ";"
        //}
        //document.getElementById("build_files").value = value;
        if($("#build_files").text().match(/[^\x00-\xff]+/igm)){
            alert('文件名中包含中文会导致构建失败，请修改。');
            return fasle;
        }
        document.getElementById("confForm").submit();
    }

    function removeAll() {
        $("#file_list").empty();
    }
</script>