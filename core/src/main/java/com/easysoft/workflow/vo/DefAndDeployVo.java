package com.easysoft.workflow.vo;

import java.util.Date;

/**
 * 流程定义及部署VO
 * User: andy
 * Date: 14-4-13
 * Time: 下午12:42
 * To change this template use File | Settings | File Templates.
 */
public class DefAndDeployVo {
	
    //定义ID
    private String defId;
    //定义名称
    private String defName;
    private String defKey;
    private int defVersion;
    private String resourceName;
    private String diagramResourceName;
    //部署ID
    private String deploymentId;
    private boolean isSuspended;

    /**
     * ====部署信息==
     */
    private Date deploymentTime;

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public int getDefVersion() {
        return defVersion;
    }

    public void setDefVersion(int defVersion) {
        this.defVersion = defVersion;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
