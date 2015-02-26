package com.easysoft.framework.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝节点
 * @author huangxa
 *
 */
public abstract class Branch implements AbstractTree {
	private List<AbstractTree> children = new ArrayList<AbstractTree>();
	public abstract void add(AbstractTree component);
	public abstract void remove(AbstractTree component);
}
