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

/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author onlineGenerator
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Entity
@Table(name = "${tableName}", schema = "")
<#if cgformConfig.formEntity.pkGeneratorPolicy?if_exists?html == "SEQUENCE">
@SequenceGenerator(name="SEQ_GEN", sequenceName="${cgformConfig.formEntity.jformPkSequence}")
</#if>
@SuppressWarnings("serial")
public class ${entityName}Entity implements java.io.Serializable {
	<#list columns as po>
	/**${po.display}*/
	private ${po.type} ${po.fieldName};
	</#list>
	
	<#list columns as po>
	/**
	 *方法: 取得${po.type}
	 *@return: ${po.type}  ${po.display}
	 */
	<#if po.fieldName == jeap_table_id>
	<#if cgformConfig.formEntity.pkGeneratorPolicy?if_exists?html == "UUID">
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	<#elseif cgformConfig.formEntity.jformPkType?if_exists?html == "NATIVE">
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	<#elseif cgformConfig.formEntity.pkGeneratorPolicy?if_exists?html == "SEQUENCE">
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN")  
	<#else>
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	</#if>
	@Column(name ="${fieldMeta[po.fieldName]}",nullable=<#if po.nullable>true<#else>false</#if>)
	public ${po.type} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.type}
	 *@param: ${po.type}  ${po.display}
	 */
	public void set${po.fieldName?cap_first}(${po.type} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#list>
}
