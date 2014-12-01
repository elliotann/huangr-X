package ${bussiPackage}.${entityPackage}.entity;

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
 * @Description: ${ftl_description}
 * @author andy
 * @date ${ftl_create_time}
 * @since : 1.0
 *
 */
 @Entity
 @Table(name="${tableName}")
public class ${entityName}Entity extends IdEntity {
	<#list columns as po>
    <#if po.fieldName!="id">
	/**${po.displayName}*/
	private ${po.type} ${po.fieldName};
    </#if>
	</#list>
	<#list columns as po>
	<#if po.fieldName != jeap_table_id>
    public ${po.type} get${po.fieldName?cap_first}(){
        return this.${po.fieldName};
    }
    public void set${po.fieldName?cap_first}(${po.type} ${po.fieldName}){
        this.${po.fieldName} = ${po.fieldName};
    }
	</#if>

	</#list>
}
