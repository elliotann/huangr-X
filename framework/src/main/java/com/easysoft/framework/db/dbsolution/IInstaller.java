package com.easysoft.framework.db.dbsolution;

import org.w3c.dom.Node;

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 13-7-9
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public interface IInstaller {
    /**
     * 方案安装器
     * @param productId
     * @param fragment
     */
    public void install(String productId, Node fragment);
}
