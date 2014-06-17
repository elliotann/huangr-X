package com.easysoft.workflow.vo;

import java.util.Map;

/**
 * User: andy
 * Date: 14-5-5
 * Time: 下午1:13
 *
 * @since:
 */
public class UserNode {
    public enum NodeType{
        START,
        END,
        USER_TASK
    }
    private String id;
    private String name;
    private String nodeType;
    private int x;
    private int y;
    private int width;
    private int height;

    private Map<String,String> props;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }
}
