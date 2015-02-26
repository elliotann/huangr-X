package com.easysoft.framework.commons;

/**
 * 树枝节点
 * @author huangxa
 *
 */
public interface Branch extends AbstractTree {
	public abstract void add(AbstractTree component);
	public abstract void remove(AbstractTree component);
}
