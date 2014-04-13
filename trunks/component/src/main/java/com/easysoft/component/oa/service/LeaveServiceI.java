package com.easysoft.component.oa.service;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.component.oa.entity.LeaveEntity;
import org.activiti.engine.runtime.ProcessInstance;

import java.io.Serializable;
import java.util.Map;


public interface LeaveServiceI extends IGenericService<LeaveEntity>{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);

    /**
     * 启动流程
     *
     * @param entity
     */
    public ProcessInstance startWorkflow(LeaveEntity entity, Map<String, Object> variables) ;
}
