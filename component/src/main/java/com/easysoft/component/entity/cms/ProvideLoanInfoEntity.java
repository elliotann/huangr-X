package com.easysoft.component.entity.cms;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 借款信息
 * @author onlineGenerator
 * @date 2014-04-03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cms_provide_loan_info", schema = "")
@SuppressWarnings("serial")
public class ProvideLoanInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer uid;
	/**名称*/
	private java.lang.String name;
	/**借款金额*/
	private java.lang.String money;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID",nullable=false)
	public java.lang.Integer getUid(){
		return this.uid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setUid(java.lang.Integer id){
		this.uid = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	@Column(name ="NAME",nullable=false)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借款金额
	 */
	@Column(name ="MONEY",nullable=false)
	public java.lang.String getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款金额
	 */
	public void setMoney(java.lang.String money){
		this.money = money;
	}
}
