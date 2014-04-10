package com.easysoft.core.bpm;

import org.activiti.engine.*;

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
        TaskService taskService = processEngine.getTaskService();
        ManagementService managementService = processEngine.getManagementService();
        IdentityService identityService = processEngine.getIdentityService();
        HistoryService historyService = processEngine.getHistoryService();
        FormService formService = processEngine.getFormService();

    }
}
