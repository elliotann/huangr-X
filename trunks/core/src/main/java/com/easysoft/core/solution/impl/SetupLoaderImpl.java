package com.easysoft.core.solution.impl;

import com.easysoft.core.solution.ISetupLoader;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.utils.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
@Service("setupLoader")
@Scope("prototype")
public class SetupLoaderImpl implements ISetupLoader {

	
	public Document load(String productId) {
		String xmlFile = ParamSetting.PRODUCTS_STORAGE_PATH+"/"+productId +"/setup.xml";
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			if (FileUtil.exist(xmlFile)) {
				document = saxReader.read(new File(xmlFile));
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} 	
		return document;
		 
	}

}
