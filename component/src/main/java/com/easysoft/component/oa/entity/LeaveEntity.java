package com.easysoft.component.oa.entity;

import com.easysoft.core.annotation.JsonInvisible;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import javax.persistence.*;

/**   
 * @Title: Entity
 * @Description: 请假信息
 * @author onlineGenerator
 * @date 2014-04-12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "oa_leave", schema = "")
@SuppressWarnings("serial")
public class LeaveEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.Integer sid;
	/**请假类型*/
	private java.lang.String leaveType;
	/**开始时间*/
	private java.util.Date startTime;
	/**结束时间*/
	private java.util.Date endTime;
	/**请假原因*/
	private java.lang.String reason;
    private String processInstanceId;
	private String userId;


    //-- 临时属性 --//

    // 流程任务
    private Task task;
    // 运行中的流程实例
    private ProcessInstance processInstance;
    // 流程定义
    private ProcessDefinition processDefinition;
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="SID",nullable=false)
	public java.lang.Integer getSid(){
		return this.sid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setSid(java.lang.Integer sid){
		this.sid = sid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假类型
	 */
	@Column(name ="LEAVE_TYPE",nullable=false)
	public java.lang.String getLeaveType(){
		return this.leaveType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假类型
	 */
	public void setLeaveType(java.lang.String leaveType){
		this.leaveType = leaveType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="START_TIME",nullable=false)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="END_TIME",nullable=false)
	public java.util.Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假原因
	 */
	@Column(name ="REASON",nullable=false)
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假原因
	 */
	public void setReason(java.lang.String reason){
		this.reason = reason;
	}
    @Column
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    @JsonInvisible("List")
    @Transient
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    @JsonInvisible("List")
    @Transient
    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }
    @JsonInvisible("List")
    @Transient
    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }
}
