package com.easysoft.member.backend.model;

/**
 * @author : andy.huang
 * @since : 1.0
 */
public class Employ extends Person{
    public enum EmployStatus{
        ENTRY,
        TURNOVER
    }
    private String empNo;
    private String entryDate;//入職日期
    private EmployStatus status = EmployStatus.ENTRY;
    private String deptId;
    private String compId;
    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public EmployStatus getStatus() {
        return status;
    }

    public void setStatus(EmployStatus status) {
        this.status = status;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }
}
