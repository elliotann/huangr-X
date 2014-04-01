package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.manager.solution.IAdminThemeInfoFileLoader;
import com.easysoft.framework.ParamSetting;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service("adminThemeInfoFileLoader")
@Scope("prototype")
public class AdminThemeInfoLoaderImpl implements IAdminThemeInfoFileLoader {

	
	public Document load(String productId, String path,
			Boolean isCommonTheme) {
		String xmlFile = null;
		if (isCommonTheme) {
			xmlFile = ParamSetting.ESF_PATH + "/adminthemes/" + path + "/themeini.xml";
		} else {
			xmlFile = ParamSetting.PRODUCTS_STORAGE_PATH+"/"+productId + "/adminthemes/" + path
					+ "/themeini.xml";//注意！现在资源ＳＤＫ未解决文件复制时的source目录问题
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			return document;
		} catch (Exception e) {
			throw new RuntimeException("load [" + productId + " , " + path + "] themeini error!FileName:"+xmlFile);
		}

	}

}
