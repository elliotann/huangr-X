package com.easysoft.tag.webapp.taglib.html;

import com.easysoft.tag.webapp.taglib.HtmlTaglib;

public class GridHeaderTaglib extends HtmlTaglib {
	
	
	protected String postStart() {
		return "<thead><tr>";
	}
	
	
	protected String postEnd() {
		return "</tr></thead>";
	}
}
