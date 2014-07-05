package com.easysoft.jeap.controller.wap;

import com.easysoft.jeap.framework.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 签到
 * Created by huangxa on 2014/7/4.
 */
@Controller
@RequestMapping("/wap")
public class AttendanceController {
    /**
     * 跳转到签到
     * @return
     */
    @RequestMapping("/attendance")
    public ModelAndView attendanceOn(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("needReg",false);
        params.put("attendTime", DateUtil.formatDate2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return new ModelAndView("wap/bussiness/attendance",params);
    }

    /**
     * 跳转到登记
     * @return
     */
    @RequestMapping("/checkIn")
    public ModelAndView checkIn(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("needReg",false);
        params.put("attendTime", DateUtil.formatDate2String(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return new ModelAndView("wap/bussiness/check_in",params);
    }
}
