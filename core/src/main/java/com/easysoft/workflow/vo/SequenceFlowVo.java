package com.easysoft.workflow.vo;

import java.util.Map;

/**
 * User: andy
 * Date: 14-5-6
 * Time: 上午10:42
 *
 * @since: 1.0
 */
public class SequenceFlowVo {
    private String id;
    private String name;
    private String from;
    private String to;
    private Point sourcePoint;
    private Point targetPoint;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Point getSourcePoint() {
        return sourcePoint;
    }

    public void setSourcePoint(Point sourcePoint) {
        this.sourcePoint = sourcePoint;
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(Point targetPoint) {
        this.targetPoint = targetPoint;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }
}
