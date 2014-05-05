package com.easysoft.workflow.vo;

import java.util.List;

/**
 * 流程定义模板数据
 * User: Administrator
 * Date: 14-5-2
 * Time: 上午9:30
 */
public class FlowDefTlp {
    private String key;
    private String name;
    private String desc;
    private List<UserNode> nodes;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<UserNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<UserNode> nodes) {
        this.nodes = nodes;
    }
}
