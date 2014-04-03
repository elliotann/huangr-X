package com.easysoft.component.controller.cms;
import com.easysoft.component.entity.cms.ProvideLoanInfoEntity;
import com.easysoft.component.service.cms.ProvideLoanInfoServiceI;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.framework.utils.BeanUtils;
import com.easysoft.framework.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
 * @Title: Controller
 * @Description: 借款信息
 * @author onlineGenerator
 * @date 2014-04-03
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/core/admin/provideLoanInfo")
public class ProvideLoanInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProvideLoanInfoController.class);

	@Autowired
	private ProvideLoanInfoServiceI provideLoanInfoService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 借款信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "provideLoanInfo")
	public ModelAndView provideLoanInfo(HttpServletRequest request) {
		return new ModelAndView("admin/component/cms/provideLoanInfoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ProvideLoanInfoEntity provideLoanInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	}

	/**
	 * 删除借款信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ProvideLoanInfoEntity provideLoanInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		provideLoanInfo = provideLoanInfoService.get(ProvideLoanInfoEntity.class, provideLoanInfo.getId());
		message = "借款信息删除成功";
		try{
			provideLoanInfoService.delete(provideLoanInfo);

		}catch(Exception e){
			e.printStackTrace();
			message = "借款信息删除失败";

		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除借款信息
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
	 * 添加借款信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProvideLoanInfoEntity provideLoanInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "借款信息添加成功";
		try{
			provideLoanInfoService.save(provideLoanInfo);

		}catch(Exception e){
			e.printStackTrace();
			message = "借款信息添加失败";

		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新借款信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProvideLoanInfoEntity provideLoanInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "借款信息更新成功";
		ProvideLoanInfoEntity t = provideLoanInfoService.get(ProvideLoanInfoEntity.class, provideLoanInfo.getId());
		try {
            BeanUtils.copyBeanNotNull2Bean(provideLoanInfo, t);
			provideLoanInfoService.saveOrUpdate(t);

		} catch (Exception e) {
			e.printStackTrace();
			message = "借款信息更新失败";

		}
		j.setMsg(message);
		return j;
	}
	


	/**
	 * 借款信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ProvideLoanInfoEntity provideLoanInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(provideLoanInfo.getId())) {
			provideLoanInfo = provideLoanInfoService.getEntity(ProvideLoanInfoEntity.class, provideLoanInfo.getId());
			req.setAttribute("provideLoanInfoPage", provideLoanInfo);
		}
		return new ModelAndView("admin/component/cms/provideLoanInfo-add");
	}
	/**
	 * 借款信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ProvideLoanInfoEntity provideLoanInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(provideLoanInfo.getId())) {
			provideLoanInfo = provideLoanInfoService.getEntity(ProvideLoanInfoEntity.class, provideLoanInfo.getId());
			req.setAttribute("provideLoanInfoPage", provideLoanInfo);
		}
		return new ModelAndView("admin/component/cms/provideLoanInfo-update");
	}
}
