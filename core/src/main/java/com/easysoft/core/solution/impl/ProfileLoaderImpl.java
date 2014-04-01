package com.easysoft.core.solution.impl;

import com.easysoft.framework.ParamSetting;
import com.easysoft.core.solution.IProfileLoader;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
@Component("profileLoader")
@Scope("prototype")
public class ProfileLoaderImpl implements IProfileLoader {

	protected final Logger logger = Logger.getLogger(getClass());
	public Document load(String productId) {
		String xmlFile = ParamSetting.PRODUCTS_STORAGE_PATH+"/"+productId +"/profile.xml";
		try {
		    DocumentBuilderFactory factory = 
		    DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(xmlFile);
		    return document;
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new RuntimeException("load ["+productId +"] profile error" );
		} 	 
		 
	}

}
