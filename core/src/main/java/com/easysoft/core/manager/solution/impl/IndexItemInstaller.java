package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.manager.IIndexItemManager;
import com.easysoft.core.model.IndexItem;
import com.easysoft.framework.db.dbsolution.IInstaller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 后台首页项安装器
 * @author andy
 */
@Service("indexItemInstaller")
@Scope("prototype")
public class IndexItemInstaller implements IInstaller {

	private IIndexItemManager indexItemManager;
	
	
	/**
	 * 根据后台首页片段安装首页项
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void install(String productId, Node fragment) {
		//this.indexItemManager.clean();
		NodeList nodeList = fragment.getChildNodes();
		int sort =1;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				 this.install((Element)node, sort);
				 sort++;
				 
			}
		}		
	}
	
	/**
	 * 根据首页项结点安装 首页项
	 * @param ele 首页项结点
	 */
	public void install(Element ele,int sort){
		 Element titleEl =this.getChild(ele, "title");
		 Element urlEl =this.getChild(ele, "url");
		 
		 IndexItem item = new IndexItem();
		/* item.setTitle(titleEl.getTextContent());
		 item.setUrl(urlEl.getTextContent());*/
		 item.setSort(sort);
		 
		 this.indexItemManager.add(item);
	}
	
	
	/**
	 * 根据节点名称 获取某个节点的子
	 * @param ele
	 * @param name
	 * @return
	 */
	private Element getChild(Element ele,String name){
		 NodeList childList  =  ele.getElementsByTagName(name);
		 Element child =(Element) childList.item(0);
		 return child;
	}

	public IIndexItemManager getIndexItemManager() {
		return indexItemManager;
	}

	public void setIndexItemManager(IIndexItemManager indexItemManager) {
		this.indexItemManager = indexItemManager;
	}
	

}
