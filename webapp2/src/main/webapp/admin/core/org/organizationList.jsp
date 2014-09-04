<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

    <meta charset="UTF-8">
    <title>机构管理</title>
    <link rel="stylesheet" type="text/css" href="${context}/js/easyui/themes/gray/easyui.css">
    <script type="text/javascript" src="${context}/js/easyui/jquery.easyui.min.js"></script>
    <link href="${context}/css/stylenew.css" rel="stylesheet" type="text/css"/>

    <script>

      function formatAdd(value, row, index) {
          var val = "";
          if(row.orgType=='COMPANY'){
              val += "<a href='javascript:void(0);' onclick='addCompany(" + row.id
                      + ",\"COMPANY\")'>增加子公司</a> &nbsp;&nbsp;&nbsp;";

          }
          val += "<a href='javascript:void(0);'  onclick='addDepart(" + row.id
                  + ",\"DEPT\",\""+row.orgType+"\")'>增加部门</a>";

          return val;
      }
      function formatterType(value,row,index){
          if(value=='COMPANY'){
              return "公司";
          }else{
              return "部门";
          }
      }
      function addCompany(pid,orgType,from) {

          $("#useradmininfo").show();
          $('#useradmininfo').dialog({
              title: '添加公司',
              top: 60,
              width: 600,
              height: 350,
              closed: false,
              cache: false,
              href: 'organization.do?goAdd&pid='+pid+"&orgType="+orgType+"&from="+from,
              modal: true,
              buttons: [
                  {
                      text: '保存',
                      iconCls: 'icon-ok',
                      handler: function () {
                          var savebtn = $(this);
                          var disabled = savebtn.hasClass("l-btn-disabled");
                          if (!disabled) {
                              addadminForm(savebtn);
                          }
                      }
                  },
                  {text: '取消', handler: function () {
                      $('#useradmininfo').dialog('close');
                  }}
              ]});
      }
      function addDepart(pid,orgType,from) {

          $("#useradmininfo").show();
          $('#useradmininfo').dialog({
              title: '添加部门',
              top: 60,
              width: 600,
              height: 350,
              closed: false,
              cache: false,
              href: 'organization.do?goAdd&pid='+pid+"&orgType="+orgType+"&from="+from,
              modal: true,
              buttons: [
                  {
                      text: '保存',
                      iconCls: 'icon-ok',
                      handler: function () {
                          var savebtn = $(this);
                          var disabled = savebtn.hasClass("l-btn-disabled");
                          if (!disabled) {
                              addadminForm(savebtn);
                          }
                      }
                  },
                  {text: '取消', handler: function () {
                      $('#useradmininfo').dialog('close');
                  }}
              ]});
      }
      function addadminForm(savebtn){
          $("#objForm").submit();

      }
      function modify(){
          var row = $('#useradmindata').treegrid('getSelected');
          if(!row){
              alert("请选择数据操作!");
              return;
          }
          $("#useradmininfo").show();
          $('#useradmininfo').dialog({
              title: '修改机构',
              top: 60,
              width: 600,
              height: 350,
              closed: false,
              cache: false,
              href: 'organization.do?goUpdate&pid='+row.id+"&orgType="+row.orgType,
              modal: true,
              buttons: [
                  {
                      text: '保存',
                      iconCls: 'icon-ok',
                      handler: function () {
                          var savebtn = $(this);
                          var disabled = savebtn.hasClass("l-btn-disabled");
                          if (!disabled) {
                              addadminForm(savebtn);
                          }
                      }
                  },
                  {text: '取消', handler: function () {
                      $('#useradmininfo').dialog('close');
                  }}
              ]});
      }
      function del(){
          var row = $('#useradmindata').treegrid('getSelected');
          if(!row){
              alert("请选择数据操作!");
              return;
          }
          if(!confirm("确定删除?")){
              return;
          }
      }
    </script>

<div class="main">
    <div id="useradmininfo" style="display: none;"></div>
    <div class="buttonArea">
        <a href="javascript:void(0)" class="button blueButton"
           data-options="iconCls:'icon-add',plain:true" onclick="addCompany(0,'COMPANY')">添加公司</a>

        <a href="javascript:void(0)" class="button blueButton"
           data-options="iconCls:'icon-add',plain:true" onclick="modify()">修改组织</a>

        <a href="javascript:void(0)" class="easyui-linkbutton"
           data-options="plain:true" onclick="del()">删除</a>

    </div>
    <form action="" id="catform">
        <table class="easyui-treegrid" id="useradmindata"
               data-options="url:'organization.do?dataGrid&ajax=true',fitColumns:'true',idField: 'id',treeField: 'name'">
            <thead>
            <tr>

                <th data-options="field:'name',width:100">名称</th>
                <th data-options="field:'orgType',width:50,align:'center'" formatter="formatterType">类型</th>
                <th data-options="field:'add',width:100,align:'center'"
                    formatter="formatAdd">添加子</th>

            </tr>
            </thead>
        </table>
    </form>

    <div id="divdia" style="display: none;"></div>
</div>


