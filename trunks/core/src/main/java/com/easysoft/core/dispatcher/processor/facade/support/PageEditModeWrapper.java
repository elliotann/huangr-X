package com.easysoft.core.dispatcher.processor.facade.support;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.PageWrapper;
import com.easysoft.core.dispatcher.page.IPageParser;
import com.easysoft.core.model.Site;
import com.easysoft.core.utils.HtmlUtil;
import com.easysoft.framework.ParamSetting;
import com.easysoft.core.freemarker.utils.FreeMarkerUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台页面编辑模式包装器
 * @author andy
 */
public class PageEditModeWrapper extends PageWrapper {

	public PageEditModeWrapper(IPageParser parser) {
		super(parser);
	}

	
	public String parse(String url) {
		String content  = super.parse(url);
		String script= this.getToolBarScript();
		String html = this.getToolBarHtml();
		
		content =wrapPageMain(content);
		content = HtmlUtil.appendTo(content, "head", script);
		content =HtmlUtil.insertTo(content, "body", html);
		//System.out.println(content);
		return content;
	}
	
	private String getToolBarScript(){
		String eopFld= ParamSetting.ESF_PATH +"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", ParamSetting.IMG_SERVER_DOMAIN) ;
			data.put("ctx", ParamSetting.CONTEXT_PATH) ;
			Site site = EsfContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_tool_script.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	private String getToolBarHtml(){
		//widget_toolbar.html
		String eopFld= ParamSetting.ESF_PATH +"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", ParamSetting.IMG_SERVER_DOMAIN) ;
			data.put("ctx", ParamSetting.CONTEXT_PATH) ;
			Site site = EsfContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_toolbar.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String wrapPageMain(String content){
		content = HtmlUtil.insertTo(content, "body", "<div id=\"pagemain\">");
		content =HtmlUtil.appendTo(content, "body", "</div>");
		return content;
	}

}
