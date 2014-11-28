package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门
 * @author : andy.huang
 * @since :
 */
@Entity
@Table(name = "t_depart")
public class Depart extends Organization {
    private String deptNo;//部門編碼
    private int compId;//所屬公司
    @Column(name = "comp_id")
    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }
    @Column(name = "dept_no")
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
