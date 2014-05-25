package com.easysoft.tag.webapp.taglib.html;

import com.easysoft.tag.webapp.taglib.ListTaglibSupport;
import com.easysoft.tag.webapp.taglib.html.support.GridBodyParam;
import com.easysoft.tag.webapp.taglib.html.support.GridBodyProvider;

import javax.servlet.jsp.tagext.Tag;

public class GridBodyTaglib extends ListTaglibSupport {
	private GridBodyProvider gridBodyProvider;
	
	public GridBodyTaglib() {
		gridBodyProvider = new GridBodyProvider();
	}
	
	protected void loadProvider() {
		
		Tag tag = this.getParent();
		
		if(tag!=null){
			GridTaglibTemp gridTaglib =(GridTaglibTemp)tag;
			GridBodyParam bodyparm = new GridBodyParam();
			bodyparm.setFrom( gridTaglib.getFrom() );
			this.param =  bodyparm;
			
			this.tagProvider = this.gridBodyProvider;
		} else {
			this.print("body tag must be included in grid tag");
		}
	}
	
	
	protected String postStart() {
		return "<tr>";
	}

	
	protected String postEnd() {
		return "</tr>";
	}

	
	protected String postStartOnce() {
		return "<tbody>";
	}
	
	
	protected String postEndOnce() {
		return "</tbody>";
	}
}
