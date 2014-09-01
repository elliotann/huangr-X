package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * 部门
 * @author : andy.huang
 * @since :
 */
public class Depart extends Organization {
    private String deptNo;//部門編碼
    private int compId;//所屬公司

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
