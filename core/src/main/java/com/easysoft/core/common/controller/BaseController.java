package com.easysoft.core.common.controller;

import com.easysoft.core.interceptors.DateConvertEditor;
import com.easysoft.framework.ParamSetting;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
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
