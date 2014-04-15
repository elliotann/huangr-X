package com.easysoft.component.oa.service;

import com.easysoft.component.oa.entity.LeaveEntity;
import com.easysoft.core.common.service.IGenericService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 查询待办任务
     * @param userId
     * @param page
     * @param pageParams
     * @return
     */
    public List<LeaveEntity> findTodoTasks(String userId, Page<LeaveEntity> page, int[] pageParams);
}
