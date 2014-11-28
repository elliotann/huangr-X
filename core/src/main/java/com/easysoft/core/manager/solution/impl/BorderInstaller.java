package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.IBorderManager;
import com.easysoft.core.model.Border;
import com.easysoft.framework.db.dbsolution.IInstaller;
import com.easysoft.framework.utils.FileUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 挂件边框安装器
 * @author andy
 */
@Service("borderInstaller")
@Scope("prototype")
public class BorderInstaller implements IInstaller {
	private IBorderManager borderManager;
 
	public void install(String productId,  Node fragment) {

		try {
		///	this.borderManager.clean();
			FileUtil.copyFolder(
                    ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/borders/",
                    ParamSetting.ESF_PATH + EsfContext.getContext().getContextPath() + "/borders/");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装borders出错...");
		}
		
		
		if (fragment.getNodeType() == Node.ELEMENT_NODE) {
			Element themeNode = (Element) fragment;
			 NodeList nodeList = themeNode.getChildNodes();
			 for( int i=0;i<nodeList.getLength();i++){
				 Node node  = nodeList.item(i);
				 if(node.getNodeType() ==  Node.ELEMENT_NODE){
					 Element el = (Element) node;
					 String id  = el.getAttribute("id");
					 String name  = el.getAttribute("name");
					 Border border = new Border();
					 border.setBorderid(id);
					 border.setBordername(name);
					 border.setThemepath(themeNode.getAttribute("id"));
					 borderManager.add(border);
				 }
			 }

		}
	}
	public void setBorderManager(IBorderManager borderManager) {
		this.borderManager = borderManager;
	}


}
