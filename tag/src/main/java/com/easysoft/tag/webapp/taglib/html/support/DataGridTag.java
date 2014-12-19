package com.easysoft.tag.webapp.taglib.html.support;

import com.easysoft.tag.webapp.taglib.vo.DataGridColumn;
import com.easysoft.tag.webapp.taglib.vo.SearchControl;
import com.easysoft.tag.webapp.taglib.vo.ToolBar;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-5-25.
 */
public class DataGridTag extends BodyTagSupport{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
    private String style="easyui";
    private String height;
    private boolean usePager = true;//是否分页
    private String width;//宽度
    private String tree;
    private boolean rownumbers;//是否显示行号
    private boolean hasSearchBar = false;//是否有搜索栏
    private String treeField = "title";
    private String onLoadSuccess;

    private List<DataGridColumn> columns = new ArrayList<DataGridColumn>();
    private List<ToolBar> toolBars = new ArrayList<ToolBar>();
    private List<SearchControl> searchControls = new ArrayList<SearchControl>();//搜索栏
    public void setAction(String action) {
        this.action = action;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setUsePager(boolean usePager) {
        this.usePager = usePager;
    }

    public void setRownumbers(boolean rownumbers) {
        this.rownumbers = rownumbers;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHasSearchBar(boolean hasSearchBar) {
        this.hasSearchBar = hasSearchBar;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public String getTreeField() {
		return treeField;
	}

	public void setTreeField(String treeField) {
		this.treeField = treeField;
	}

	@Override
    public int doStartTag() throws JspException {
        columns.clear();
        toolBars.clear();
        searchControls.clear();
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if("ligerui".equals(style)){
               
            }else if("html".equals(style)){
                
            }
            else if("easyui".equals(style)){
                if(StringUtils.isNotEmpty(tree)&&"true".equals(tree)){
                    out.write(endEasyUI4Tree());
                }else{
                    out.write(endEasyUI());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;

    }


    //treegrid
    private String endEasyUI4Tree(){
        StringBuilder sb = new StringBuilder();
        //div开始
        sb.append(buildGridStartDiv());
        //工具栏
        sb.append(buildToolBar());
        //列
        sb.append(buildGrid());
        //div结束
        sb.append(buildGridEndDiv());
        return sb.toString();
    }
    //datagrid
    public String endEasyUI(){
        StringBuilder sb = new StringBuilder();
        sb.append(buildGridStartDiv());
 
        sb.append("<div id=\"tb\" style=\"height: auto\">");
        for(ToolBar toolBar : toolBars){
        	sb.append("<a href=\"javascript:void(0)\" class=\"btn btn-primary\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\""+toolBar.getClickFun()+"()\">"+toolBar.getTitle()+"</a> ");

        }
        
        sb.append("<span style=\"float: right;\">");
        sb.append("<span id=\"simpleSearch\">");
        List<SearchControl> advances = new ArrayList<SearchControl>();
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
        if(StringUtils.isNotEmpty(tree)&&"true".equals(tree)){
            sb.append("class=\"easyui-treegrid\"");
        }else{
            sb.append("class=\"easyui-datagrid\"");
        }
        sb.append(" pagination=\"true\"  sortName=\"member_id\" sortOrder=\"desc\" id=\"dataGrid\"");
        sb.append(" data-options=\"url:'"+action+"',pageList: [5,10,15,20],fitColumns:'true',singleSelect:true,idField: 'id',treeField: 'name'\"");
        sb.append(">");
        sb.append(" <thead><tr>");

        for(DataGridColumn column : columns){
            sb.append("<th data-options=\"field:'"+column.getField()+"',width:"+column.getWidth()+",align:'"+column.getAlign()+"'\" ");
            if(StringUtils.isNotEmpty(column.getRenderFun())){
                sb.append("formatter=\""+column.getRenderFun()+"\"");
            }
            sb.append(">");
            sb.append(column.getTitle());
            sb.append("</th>");
        }
        sb.append("</tr></thead>");
        sb.append("</table>");
        sb.append("</div>");
        

        sb.append(buildGridEndDiv());
        return sb.toString();
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

    /**
     * 构建工具条
     * @return
     */
    private String buildToolBar(){
        StringBuilder sb = new StringBuilder("<div class=\"buttonArea\">");
        for(ToolBar toolBar : toolBars){
        	sb.append("<a href=\"javascript:void(0)\" class=\"btn btn-primary\" data-options=\"iconCls:'icon-add',plain:true\" onclick=\""+toolBar.getClickFun()+"()\">"+toolBar.getTitle()+"</a> ");
        }
        sb.append("</div>");
        return sb.toString();
    }
    private String buildGrid(){
        StringBuilder sb = new StringBuilder("<form action=\"\" id=\"dataGridform\">");
        sb.append("<table class=\"easyui-treegrid\" id=\"dataGrid\"");
        sb.append("data-options=\"url:'");
        sb.append(action+"?dataGrid&ajax=yes'");
        sb.append(",fitColumns:'true',idField: 'id',treeField: '"+treeField+"'");
        if(StringUtils.isNotEmpty(onLoadSuccess)){
        	sb.append(",onLoadSuccess:"+onLoadSuccess);
        }
        sb.append("\">");
        sb.append("<thead>");
        sb.append("<tr>");
        for(DataGridColumn column : columns){
            sb.append("<th data-options=\"field:'"+column.getField()+"',width:"+column.getWidth()+",align:'"+column.getAlign()+"'\" ");
            if(StringUtils.isNotEmpty(column.getRenderFun())){
                sb.append("formatter=\""+column.getRenderFun()+"\"");
            }
            sb.append(">");
            sb.append(column.getTitle());
            sb.append("</th>");
        }

        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("</table>");
        sb.append("</form>");
        return sb.toString();
    }
    



    

    
    public void setColumns(DataGridColumn column){
        columns.add(column);
    }
    public void setToolBars(ToolBar toolBar){
        toolBars.add(toolBar);
    }

    public void setSearchControls(SearchControl searchControl){
        searchControls.add(searchControl);
    }

    
    
}
