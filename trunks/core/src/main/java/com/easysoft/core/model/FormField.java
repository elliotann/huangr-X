package com.easysoft.core.model;

import com.easysoft.core.annotation.JsonInvisible;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: andy
 * Date: 14-3-31
 * Time: 下午12:29
 *
 * @since:
 */
@Entity
@Table(name = "t_form_field")
public class FormField implements Serializable {
    private Integer id;
    private FormEntity form;
    private String fieldName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonInvisible("List")
    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
