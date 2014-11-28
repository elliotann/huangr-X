package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 操作按钮-->功能,如增、删、改、查、导出
 * User: andy
 * Date: 14-5-9
 * Time: 上午10:09
 *
 * @since:
 */
@Entity
@Table(name="t_operation_btn")
public class OperationBtn extends IdEntity {

    private String code;
    private String name;
    private String ico;
    private int status;
    private String operType;
    private String menuId;

    @Column(name="code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }
    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }
    @Column(name="menuId")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
