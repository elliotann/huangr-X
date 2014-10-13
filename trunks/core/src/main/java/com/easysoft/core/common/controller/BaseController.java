package com.easysoft.core.common.controller;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.core.interceptors.DateConvertEditor;
import com.easysoft.core.interceptors.IntConvertEditor;
import com.easysoft.framework.ParamSetting;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * 
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
    protected final Logger logger = Logger.getLogger(getClass());
    protected int page;
    protected int pageSize;
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
        //binder.registerCustomEditor(Integer.class,new IntConvertEditor());
    }

    /**
     * 分页公共方法(非easyui)
     *
     * @author Alexander
     * @date 20131022
     */
    public List<?> pageBaseMethod(HttpServletRequest request,
                                  DetachedCriteria dc, IGenericService commonService, int pageRow) {
     /*   // 当前页
        // 总条数
        // 总页数

        int currentPage = 1;

        int totalRow = 0;
        int totalPage = 0;
        // 获取当前页
        String str_currentPage = request.getParameter("str_currentPage");
        currentPage = str_currentPage == null || "".equals(str_currentPage) ? 1
                : Integer.parseInt(str_currentPage);
        // 获取每页的条数
        String str_pageRow = request.getParameter("str_pageRow");
        pageRow = str_pageRow == null || "".equals(str_pageRow) ? pageRow
                : Integer.parseInt(str_pageRow);

        // 统计的总行数
        dc.setProjection(Projections.rowCount());

        totalRow = Integer.parseInt(commonService.findByDetached(dc).get(0)
                .toString());
        totalPage = (totalRow + pageRow - 1) / pageRow;

        currentPage = currentPage < 1 ? 1 : currentPage;
        currentPage = currentPage > totalPage ? totalPage : currentPage;
        // 清空统计函数
        dc.setProjection(null);
        // dc.setResultTransformer(dc.DISTINCT_ROOT_ENTITY);
        List<?> list = commonService.pageList(dc, (currentPage - 1) * pageRow,
                pageRow);

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageRow", pageRow);
        request.setAttribute("totalRow", totalRow);
        request.setAttribute("totalPage", totalPage);
        return list;*/
        return null;
    }

    public int getPageSize() {
        return Integer.valueOf(ParamSetting.BACKEND_PAGESIZE);
    }

    public int getPage() {
        page = page < 1 ? 1 : page;
        return page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public void setPage(int page) {
        this.page = page;
    }
	
}
