<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<%--
  ~ /*
  ~  * Copyright 1999-2011 jeap Group Holding Ltd.
  ~  *
  ~  * Licensed under the Apache License, Version 2.0 (the "License");
  ~  * you may not use this file except in compliance with the License.
  ~  * You may obtain a copy of the License at
  ~  *
  ~  *      http://www.apache.org/licenses/LICENSE-2.0
  ~  *
  ~  * Unless required by applicable law or agreed to in writing, software
  ~  * distributed under the License is distributed on an "AS IS" BASIS,
  ~  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~  * See the License for the specific language governing permissions and
  ~  * limitations under the License.
  ~  */
  --%>

<link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
<link href="${context}/css/style1.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${context}/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script src="/jeap/admin/js/crud.js" type="text/javascript"></script>
<link href="${context }/css/form.css" rel="stylesheet"/>
<link href="${context }/css/button.css" rel="stylesheet"/>

<script type="text/javascript">
    var listgrid;
    $(function() {

        $(".searchAdvanced").hide();
        //高级查询按钮
        $("#aAdvanced").click(function () {
            if ($("#Advanced").val() == "0") {
                $("#Advanced").val(1);
                $("#simpleSearch").hide();
                //$("#aAdvanced").text("简单搜索")
                $("#aAdvanced").addClass("searchAdvancedS");
            } else {
                $("#Advanced").val(0);
                $("#simpleSearch").show();
                //$("#aAdvanced").text("高级搜索");
                $("#aAdvanced").removeClass("searchAdvancedS");
            }
            $(".searchAdvanced").slideToggle("slow");
        });
    });

    function deploy(item){
        addOrUpdateDialog(item,'部署流程','workflow.do?toDeploy',600,900);

    }
    function addProcess(item){
        addOrUpdateDialog(item,'新增流程','workflow.do?toFlowDesigner',600,900);

    }


    function btnOK(item,dialog){
        openDia.submitForm();
    }
    function delDeploy(item)
    {
        var row = listgrid.getSelectedRow();
        if(row==null){
            $.ligerDialog.error('请选择数据删除!');
            return;
        }
        $.ligerDialog.confirm('确定删除？', function (yes) {
            if(yes){
                $.ajax({
                    type: "GET",
                    url: "workflow.do?delete&id="+row.deploymentId,
                    data:"ajax=true&rmd="+ new Date().getTime(),
                    dataType:"json",
                    success: function(result){
                        if(result.success){
                            $.ligerDialog.alert('删除成功!', '提示', type);
                            listgrid.loadData();
                        }else{
                            $.ligerDialog.alert(result.msg, '提示', type);

                        }
                    },error:function(e){
                        $.ligerDialog.alert('出错了!', '提示', type)

                    }
                });
            }
        });
    }

    function formatXml(rowdata,index,value){
        return "<a target='_blank' href='${ctx }/core/admin/workflow.do?readResouce&processDefinitionId="+rowdata.defId+"&resourceType=xml'>"+value+"</a>";
    }
    function formatImage(rowdata,index,value){
        return "<a target='_blank' href='${ctx }/core/admin/workflow.do?readResouce&ajax=true&processDefinitionId="+rowdata.defId+"&resourceType=image'>"+value+"</a>";
    }
    </script>


<grid:dataGrid action="workflow.do?dataGrid&ajax=yes" height="100%"  rownumbers="true" hasSearchBar="true" style="easyui">

    <grid:column title="定义Id" field="defId"  minWidth="100"/>
    <grid:column title="部署Id" field="deploymentId" align="center" width="100" minWidth="60"/>
    <grid:column title="名称" field="defName"  minWidth="100"/>
    <grid:column title="KEY" field="defKey"  minWidth="140"/>
    <grid:column title="版本号" field="defVersion" />
    <grid:column title="XML" field="resourceName" renderFun="formatXml"/>
    <grid:column title="图片" field="diagramResourceName" renderFun="formatImage"/>
    <grid:column title="部署时间" field="deploymentTime" />
    <grid:column title="是否挂起" field="suspended" />
    <grid:toolbar title="部署流程" clickFun="deploy" icon="add"/>
    <grid:toolbar title="新增流程" clickFun="addProcess" icon="add"/>
    <grid:toolbar title="删除" clickFun="delDeploy" icon="add"/>
</grid:dataGrid>
