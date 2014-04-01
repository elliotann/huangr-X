package com.easysoft.core.jms;

import com.easysoft.core.context.EsfContext;

import java.util.HashMap;
import java.util.Map;

public class EmailModel {
	private Map data = new HashMap();

	private String title = "";

	private String to = "";

	private String template = "";
	private EsfContext esfContext;

	public EmailModel() {
		this.esfContext = EsfContext.getContext();
	}

	public EmailModel(Map data, String title, String to, String template) {
		this.data = data;
		this.title = title;
		this.to = to;
		this.template = template;
	}

	public Map getData() {
		return this.data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public EsfContext getEsfContext() {
		return this.esfContext;
	}
}
