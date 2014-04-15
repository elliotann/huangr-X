package com.easysoft.component.oa.controller;

import com.easysoft.component.oa.entity.LeaveEntity;
import com.easysoft.component.oa.service.LeaveServiceI;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.BeanUtils;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import com.easysoft.member.backend.manager.impl.UserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 请假信息
 * @author onlineGenerator
 * @date 2014-04-12
 * @since : v1.0.0
 *
 */
@Controller
@RequestMapping("/core/admin/leave")
public class LeaveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LeaveController.class);

	@Autowired
	private LeaveServiceI leaveService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 请假信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "leave")
	public ModelAndView leave(HttpServletRequest request) {
		return new ModelAndView("admin/component/oa/leaveList");
	}

	/**
	 * AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "dataGrid")
	public ModelAndView datagrid(LeaveEntity leave,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        List entityist= this.leaveService.loadAll(LeaveEntity.class);
        DataGridReturn dataGridReturn = new DataGridReturn(entityist.size(),entityist);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
	}

	/**
	 * 删除请假信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson doDel(LeaveEntity leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		leave = leaveService.get(LeaveEntity.class, leave.getSid());
		message = "请假信息删除成功";
		try{
			leaveService.delete(leave);

		}catch(Exception e){
			e.printStackTrace();
			message = "请假信息删除失败";

		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除请假信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		return j;
	}


	/**
	 * 添加请假信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LeaveEntity leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "请假信息添加成功";
		try{
			leaveService.save(leave);
            Map<String, Object> variables = new HashMap<String, Object>();
            leaveService.startWorkflow(leave,variables);
		}catch(Exception e){
			e.printStackTrace();
			message = "请假信息添加失败";

		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新请假信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LeaveEntity leave, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "请假信息更新成功";
		LeaveEntity t = leaveService.get(LeaveEntity.class, leave.getSid());
		try {
            BeanUtils.copyBeanNotNull2Bean(leave, t);
			leaveService.saveOrUpdate(t);

		} catch (Exception e) {
			e.printStackTrace();
			message = "请假信息更新失败";

		}
		j.setMsg(message);
		return j;
	}
	


	/**
	 * 请假信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LeaveEntity leave, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(leave.getSid())) {
			leave = leaveService.getEntity(LeaveEntity.class, leave.getSid());
			req.setAttribute("leavePage", leave);
		}
		return new ModelAndView("admin/component/oa/leave-add");
	}
	/**
	 * 请假信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LeaveEntity leave, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(leave.getSid())) {
			leave = leaveService.getEntity(LeaveEntity.class, leave.getSid());
			req.setAttribute("leavePage", leave);
		}
		return new ModelAndView("admin/component/oa/leave-update");
	}
    /**
     * 任务列表
     *
     * @param leave
     */
    @RequestMapping(params ={"listtask"} )
    public ModelAndView taskList(HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/oa/leave/taskList");




        return mav;
    }

    @RequestMapping(params = "taskDataGrid")
    public ModelAndView taskDataGrid(LeaveEntity leave,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String userName = UserServiceFactory.getUserService().getCurrentUser().getUsername();

        List entityist =leaveService.findTodoTasks(userName, null, null);
        DataGridReturn dataGridReturn = new DataGridReturn(entityist.size(),entityist);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
}
