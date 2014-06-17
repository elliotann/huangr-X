package com.easysoft.member.backend.model;

import javax.persistence.*;

/**
 * 功能操作关联
 * User: Administrator
 * Date: 14-5-10
 * Time: 下午9:02
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="t_fun_operation")
public class FunAndOper {
    private int id;
    private String name;
    private String code;

    private Menu menu;
    private String operation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="fun_oper_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "menu_id")
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
