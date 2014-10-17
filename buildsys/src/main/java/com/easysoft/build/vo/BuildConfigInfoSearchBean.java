package com.easysoft.build.vo;

import java.util.Date;

/**
 * @author : andy.huang
 * @since :
 */
public class BuildConfigInfoSearchBean {
    private String buildFileName;
    private String status;
    private String startTester;
    private String adder;
    private Date addTimeStart;
    private Date addTimeEnd;
    private String io = "0";

    public String getBuildFileName() {
        return buildFileName;
    }

    public void setBuildFileName(String buildFileName) {
        this.buildFileName = buildFileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTester() {
        return startTester;
    }

    public void setStartTester(String startTester) {
        this.startTester = startTester;
    }

    public String getAdder() {
        return adder;
    }

    public void setAdder(String adder) {
        this.adder = adder;
    }

    public Date getAddTimeStart() {
        return addTimeStart;
    }

    public void setAddTimeStart(Date addTimeStart) {
        this.addTimeStart = addTimeStart;
    }

    public Date getAddTimeEnd() {
        return addTimeEnd;
    }

    public void setAddTimeEnd(Date addTimeEnd) {
        this.addTimeEnd = addTimeEnd;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public String getSerchCondition(){
        return "";
    }
    public String getSort() {
        return "";
    }
    public String getOrder(){
        return "";
    }
}
