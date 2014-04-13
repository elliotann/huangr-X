package com.easysoft.component.oa.service.impl;

import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.oa.service.LeaveServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.component.oa.entity.LeaveEntity;

import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import java.util.List;
@Service("leaveService")
@Transactional
public class LeaveServiceImpl extends GenericService<LeaveEntity> implements LeaveServiceI {
    private  static final Log logger = LogFactory.getLog(LeaveServiceImpl.class);
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
 	public <T> void delete(T entity) {
 		super.delete(entity);

 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);

 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);

 	}
    public List list( ){
        return null;
    }

    /**
     * 启动流程
     *
     * @param entity
     */
    public ProcessInstance startWorkflow(LeaveEntity entity, Map<String, Object> variables) {


        String businessKey = entity.getSid().toString();

        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId(UserServiceFactory.getUserService().getCurrentUser().getUserid().toString());

            processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);
            String processInstanceId = processInstance.getId();
            entity.setProcessInstanceId(processInstanceId);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processInstance;
    }
}