package com.easysoft.core.code.pojo;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:29
 *
 * @since:
 */
public class CreateFileProperty {
    private boolean actionFlag;
    private boolean serviceFlag;
    private boolean entityFlag;
    private boolean pageFlag;
    private boolean jspFlag;
    private String jspMode;

    public boolean isActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    public boolean isServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(boolean serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public boolean isEntityFlag() {
        return entityFlag;
    }

    public void setEntityFlag(boolean entityFlag) {
        this.entityFlag = entityFlag;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }



    public boolean isJspFlag() {
        return jspFlag;
    }

    public void setJspFlag(boolean jspFlag) {
        this.jspFlag = jspFlag;
    }

    public String getJspMode() {
        return jspMode;
    }

    public void setJspMode(String jspMode) {
        this.jspMode = jspMode;
    }
}
