package com.easysoft.core.solution;

import org.dom4j.Document;

/**
 * 根据产品唯一标识（非数据库索引 ）加载产品包下的setup.xml并返回其document对象
 * @author andy
 * since: 1.0
 */
public interface ISetupLoader {
	
	/**
	 * 根据产品唯一标识（非数据库索引 ）加载产品包下的setup.xml并返回其document对象
	 * @param productId
	 * @return
	 */
	public Document load(String productId);
	
}
