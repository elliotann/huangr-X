package com.easysoft.core.bpm;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-4-10
 * Time: 下午3:29
 *
 * @since:
 */
public class BPMTest {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("com/easysoft/core/bpm/VacationRequest.bpmn20.xml").deploy();
        System.out.println(repositoryService.createDeploymentQuery().count());


        Map<String,Object> variables = new HashMap<String,Object>();
        variables.put("employeeName","andy");
        variables.put("numberOfDays",new Integer(4));
        variables.put("vacationMotivation","I am really tired");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest",variables);
        System.out.println(runtimeService.createProcessInstanceQuery().count());
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for(Task task : tasks){
            System.out.println("Task name is " + task.getName());
        }
        Task task = tasks.get(0);
        Map<String,Object> taskVaris = new HashMap<String, Object>();
        taskVaris.put("vacationApproved","false");
        taskVaris.put("managerMotivation","We have a tight deadline!");
        taskService.complete(task.getId(),taskVaris);
        ManagementService managementService = processEngine.getManagementService();
        IdentityService identityService = processEngine.getIdentityService();
        HistoryService historyService = processEngine.getHistoryService();
        FormService formService = processEngine.getFormService();

    }
}
