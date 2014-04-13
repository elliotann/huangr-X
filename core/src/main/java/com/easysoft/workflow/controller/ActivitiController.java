package com.easysoft.workflow.controller;


import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.utils.WorkflowUtils;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.workflow.vo.DefAndDeployVo;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 流程管理控制器
 *
 * @author HenryYan
 */
@Controller
@RequestMapping(value = "/core/admin/workflow")
public class ActivitiController {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;



    protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();

    @Autowired
    ProcessEngineFactoryBean processEngine;

    /**
     * 流程定义列表
     *
     * @return
     */
    @RequestMapping(params = {"process-list"})
    public ModelAndView processList(HttpServletRequest request) {
        return new ModelAndView("workflow/process-list");
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List dataList= new ArrayList();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(0,100);
        DefAndDeployVo defAndDeployVo = null;
        for (ProcessDefinition processDefinition : processDefinitionList) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            defAndDeployVo = new DefAndDeployVo();
            defAndDeployVo.setDefId(processDefinition.getId());
            defAndDeployVo.setDeploymentId(processDefinition.getDeploymentId());
            defAndDeployVo.setDefName(processDefinition.getName());
            defAndDeployVo.setDefKey(processDefinition.getKey());
            defAndDeployVo.setDefVersion(processDefinition.getVersion());
            defAndDeployVo.setResourceName(processDefinition.getResourceName());
            defAndDeployVo.setDiagramResourceName(processDefinition.getDiagramResourceName());
            defAndDeployVo.setDeploymentTime(deployment.getDeploymentTime());
            dataList.add(defAndDeployVo);
        }
        DataGridReturn dataGridReturn = new DataGridReturn(dataList.size(),dataList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    /**
     * 流程定义列表
     *
     * @return
     */
    @RequestMapping(params = {"toDeploy"})
    public ModelAndView toDeploy() {
        return new ModelAndView("workflow/toDeploy");
    }
    @RequestMapping(params = {"deploy"})
    public String deploy(@Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir, @RequestParam(value = "file", required = false) MultipartFile file) {

        String fileName = file.getOriginalFilename();

        try {
            InputStream fileInputStream = file.getInputStream();
            Deployment deployment = null;

            String extension = FilenameUtils.getExtension(fileName);
            if (extension.equals("zip") || extension.equals("bar")) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else {
                deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            }

            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
            }

        } catch (Exception e) {
            logger.error("error on deploy process, because of file input stream", e);
        }

        return "redirect:/workflow/process-list";
    }

    @RequestMapping(params = {"delete"})
    @ResponseBody
    public AjaxJson delete(String id) throws Exception {
        AjaxJson json = new AjaxJson();
        try {
            repositoryService.deleteDeployment(id, true);
            json.setMsg("删除成功");
        } catch (RuntimeException e) {
            json.setMsg("删除失败:" + e.getMessage());
            json.setSuccess(false);
        }

        return json;
    }

    /**
     * 待办任务--Portlet
     */
    @RequestMapping(params = {"taskTodoList"})
    @ResponseBody
    public List<Map<String, Object>> todoList() throws Exception {
        AdminUser user = UserServiceFactory.getUserService().getCurrentUser();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        // 已经签收的任务
        List<Task> todoList = taskService.createTaskQuery().taskAssignee(user.getUserid().toString()).active().list();
        for (Task task : todoList) {
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);

            Map<String, Object> singleTask = packageTaskInfo(sdf, task, processDefinition);
            singleTask.put("status", "todo");
            result.add(singleTask);
        }

        // 等待签收的任务
        List<Task> toClaimList = taskService.createTaskQuery().taskCandidateUser(user.getUserid().toString()).active().list();
        for (Task task : toClaimList) {
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);

            Map<String, Object> singleTask = packageTaskInfo(sdf, task, processDefinition);
            singleTask.put("status", "claim");
            result.add(singleTask);
        }

        return result;
    }

    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE.get(processDefinitionId);
        if (processDefinition == null) {
            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
            PROCESS_DEFINITION_CACHE.put(processDefinitionId, processDefinition);
        }
        return processDefinition;
    }
    private Map<String, Object> packageTaskInfo(SimpleDateFormat sdf, Task task, ProcessDefinition processDefinition) {
        Map<String, Object> singleTask = new HashMap<String, Object>();
        singleTask.put("id", task.getId());
        singleTask.put("name", task.getName());
        singleTask.put("createTime", sdf.format(task.getCreateTime()));
        singleTask.put("pdname", processDefinition.getName());
        singleTask.put("pdversion", processDefinition.getVersion());
        singleTask.put("pid", task.getProcessInstanceId());
        return singleTask;
    }
}