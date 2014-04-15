package com.easysoft.component.oa.service.impl;

import com.easysoft.component.oa.entity.LeaveEntity;
import com.easysoft.component.oa.service.LeaveServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.engine.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service("leaveService")
@Transactional
public class LeaveServiceImpl extends GenericService<LeaveEntity> implements LeaveServiceI {
    private  static final Log logger = LogFactory.getLog(LeaveServiceImpl.class);
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected RepositoryService repositoryService;
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

    @Override
    public List<LeaveEntity> findTodoTasks(String userId, Page<LeaveEntity> page, int[] pageParams) {
        List<LeaveEntity> results = new ArrayList<LeaveEntity>();
        List<Task> tasks = new ArrayList<Task>();

        // 根据当前人的ID查询
        TaskQuery todoQuery = taskService.createTaskQuery().processDefinitionKey("leave").taskAssignee(userId).active().orderByTaskId().desc()
                .orderByTaskCreateTime().desc();
        List<Task> todoList = todoQuery.listPage(1, 100);

        // 根据当前人未签收的任务
        TaskQuery claimQuery = taskService.createTaskQuery().processDefinitionKey("leave").taskCandidateUser(userId).active().orderByTaskId().desc()
                .orderByTaskCreateTime().desc();
        List<Task> unsignedTasks = claimQuery.listPage(1, 100);

        // 合并
        tasks.addAll(todoList);
        tasks.addAll(unsignedTasks);

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            LeaveEntity leave = this.get(LeaveEntity.class, new Integer(businessKey));
            leave.setTask(task);
            leave.setTaskName(task.getName());
            leave.setTaskId(task.getId());
            leave.setTaskCreateTime(DateUtils.format(task.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            leave.setProcessInstanceState(processInstance.isSuspended());
            ProcessDefinition processDefinition =   getProcessDefinition(processInstance.getProcessDefinitionId());
            leave.setProcessInstance(processInstance);
            leave.setDefVersion(processDefinition.getVersion()+"");
            leave.setProcessDefinition(processDefinition);
            leave.setAssignee(task.getAssignee());
            results.add(leave);
        }


        return results;
    }
    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
}