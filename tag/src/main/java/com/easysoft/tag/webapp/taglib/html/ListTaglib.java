package com.easysoft.tag.webapp.taglib.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.component.form.model.ListPageMeta.ShowType;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.tag.webapp.taglib.HtmlTaglib;
import com.easysoft.tag.webapp.taglib.vo.SearchControl;
import com.easysoft.tag.webapp.taglib.vo.ToolBar;

/**
 * 列表标签
 * @author : andy.huang
 * @since :
 */
public class ListTaglib extends HtmlTaglib{
    private String formCode;//对应表单code

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    @Override
    protected String postStart() {
        if(StringUtils.isEmpty(formCode)){
            throw new RuntimeException("表单编码必须传入");
        }
        IFormManager fromManager = (IFormManager)SpringContextHolder.getBean("formManager");
        FormEntity formEntity  = fromManager.queryFormByCode(formCode);
        if(formEntity==null){
            throw new RuntimeException("表单不存在");
        }

      
        return endEasyUI(formEntity);
    }
    /**
     * 构建div开始标签
     * @return
     */
    private String buildGridStartDiv(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("<div class=\"main\">");
        sb.append("<div id=\"dialogInfo\" style=\"display: none;\"></div>");
        sb.append(" <form id=\"dataGridform\">");
        return sb.toString();
    }
  //datagrid
    private String endEasyUI(FormEntity formEntity){
        StringBuilder sb = new StringBuilder();
        sb.append(buildGridStartDiv());
        List<ToolBar> toolBars = new ArrayList<ToolBar>();
        sb.append("<div id=\"tb\" style=\"height: auto\">");
        sb.append(" <a href=\"javascript:void(0)\" class=\"button blueButton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"add()\">增加</a>");
        sb.append(" <a href=\"javascript:void(0)\" class=\"button blueButton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"add()\">修改</a>");
        sb.append(" <a href=\"javascript:void(0)\" class=\"button blueButton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"add()\">删除</a>");
        for(ToolBar toolBar : toolBars){
            sb.append(" <a href=\"javascript:void(0)\" class=\"button blueButton\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\"add()\">新增</a>");
        }

        sb.append("<span style=\"float: right;\">");
        sb.append("<span id=\"simpleSearch\">");
        List<SearchControl> advances = new ArrayList<SearchControl>();
       
        List<SearchControl> searchControls = new ArrayList<SearchControl>();//搜索栏
        for(ListPageMeta column : formEntity.getPageMetas()){
        	if(!column.getShowType().equals(ShowType.SHORT_SEARCH)) continue;
        	SearchControl searchControl = new SearchControl();
        	searchControl.setLabel(column.getField().getDisplayName());
        	searchControl.setName(column.getField().getFieldName());
        	searchControl.setShortSearch(true);
        	searchControls.add(searchControl);
        }
        if(!searchControls.isEmpty()){
	        for(SearchControl control : searchControls){
	            if(control.isShortSearch()){
	                sb.append(control.buildSearchControl());
	            }else{
	                advances.add(control);
	            }
	        }
	    }
        sb.append("<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"plain:true\" onclick=\"searchMember()\">搜索</a>");
        sb.append("</span>");
        if(!advances.isEmpty()){
            sb.append("<a href=\"javascript:void(0)\" class=\"button\" data-options=\"plain:true\" id=\"aAdvanced\">高级搜索</a>");
        }

        sb.append("</span>");
        sb.append("</div>");

        sb.append("<div style=\"display: block;\" class=\"searchAdvanced\">");
        sb.append(" <input id=\"Advanced\" name=\"Advanced\" type=\"hidden\" value=\"0\"/>");
        sb.append("<table width=\"98%\" border=\"0\" cellspacing=\"0\" cellpadding=\"8\">");
        for(int i=0;i<advances.size();i++){
            SearchControl control = advances.get(i);
            if(i%3==0){
                sb.append("<tr>");
            }

            sb.append("<th width=\"70\" align=\"right\">"+control.getLabel()+"</th>");
            sb.append("<td>");
            sb.append(control.buildSearchControl());
            sb.append("</td>");

            if((i+1)%3==0){
                sb.append("</tr>");
            }

        }

        sb.append("<tr>");
        sb.append("<td width=\"60\" align=\"right\"></td>");
        sb.append(" <td colspan=\"7\" align=\"center\"><a id=\"searchAdvance\" class=\"button blueButton\" onclick=\"searchMember()\" href=\"javascript:;\">开始搜索</a></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</div>");
        sb.append("<div class=\"clear height10\"></div>");
        sb.append("<div class=\"shadowBoxWhite tableDiv\">");
        sb.append("<table ");
      
            sb.append("class=\"easyui-datagrid\"");
   
        sb.append(" pagination=\"true\"  sortName=\"member_id\" sortOrder=\"desc\" id=\"dataGrid\"");
        sb.append(" data-options=\"url:'"+"/jeap1.0/core/admin/formTesT.do?dataGrid&ajax=true"+"',pageList: [5,10,15,20],fitColumns:'true',singleSelect:true,idField: 'id',treeField: 'name'\"");
        sb.append(">");
        sb.append(" <thead><tr>");

        for(ListPageMeta column : formEntity.getPageMetas()){
        	if(!column.getShowType().equals(ShowType.LIST)) continue;
            sb.append("<th data-options=\"field:'"+column.getField().getFieldName()+"',width:"+150+",align:'"+"center"+"'\" ");
        
            sb.append(">");
            sb.append(column.getField().getDisplayName());
            sb.append("</th>");
        }
        sb.append("</tr></thead>");
        sb.append("</table>");
        sb.append("</div>");
        

        sb.append(buildGridEndDiv());
        return sb.toString();
    }
    /**
     * 构建div结束标签
     * @return
     */
    private String buildGridEndDiv(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("</form>");
        sb.append("<div id=\"divdia\" style=\"display: none;\"></div>");
        sb.append("</div>");
        return sb.toString();
    }
    @Override
    protected String postEnd() {

        StringBuilder sb = new StringBuilder();
        sb.append("</div></div>");
        sb.append("<div style=\"display:none;\">");
        return sb.toString();
    }
}
