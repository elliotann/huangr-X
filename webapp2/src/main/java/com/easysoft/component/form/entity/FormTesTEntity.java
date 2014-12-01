package com.easysoft.component.form.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import com.easysoft.core.common.entity.IdEntity;

/**
 * @Description: 表单测试
 * @author andy
 * @date 2014-12-01
 * @since : 1.0
 *
 */
 @Entity
 @Table(name="t_form_test")
public class FormTesTEntity extends IdEntity {
	/**名称*/
	private java.lang.String name;

    public java.lang.String getName(){
        return this.name;
    }
    public void setName(java.lang.String name){
        this.name = name;
    }

}
