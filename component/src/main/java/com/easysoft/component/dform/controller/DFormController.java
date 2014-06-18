package com.easysoft.component.dform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.servlet.tag.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.component.dform.model.CgFormHeadEntity;
import com.easysoft.component.dform.service.CgFormFieldServiceI;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.utils.hql.HqlGenerateUtil;

/**
 * User: andy
 * Date: 14-3-13
 * Time: 下午1:19
 *
 * @since:
 */
@Controller
@RequestMapping("/admin/form/dFormController")
public class DFormController extends BaseController {
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
    @RequestMapping(params = {"formList"})
    public ModelAndView formList(){
        return new ModelAndView("form/config/list");
    }
    @RequestMapping(params = "datagrid")
	public void datagrid(CgFormHeadEntity cgFormHead,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

	}
}
