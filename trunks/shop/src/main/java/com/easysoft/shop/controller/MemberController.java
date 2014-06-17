package com.easysoft.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.framework.db.Page;
import com.easysoft.member.manager.IMemberLvManager;
import com.easysoft.member.manager.IMemberManager;
import com.easysoft.member.manager.IRegionsManager;
import com.easysoft.member.model.Member;
import com.easysoft.member.model.MemberLv;
import com.easysoft.shop.member.plugin.MemberPluginBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-7
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/shop/admin/member")
public class MemberController extends BaseController {
    @Autowired
    private IMemberManager memberManager;
    @Autowired
    private IMemberLvManager memberLvManager;
    @Autowired
    private IRegionsManager regionsManager;
    @Autowired
    private MemberPluginBundle memberPluginBundle;
    @RequestMapping(params = {"memberlist"})
    public ModelAndView memberlist(String order,String name,String uname) {
        Page webpage = this.memberManager.list(order, name, uname, this.getPage(),
                this.getPageSize());
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("webpage",webpage);
        return new ModelAndView("shop/admin/member/member_list",params);

    }
    @RequestMapping(params = {"add_member"})
    public ModelAndView add_member() {
        List lvlist = this.memberLvManager.list();
        List provinceList = this.regionsManager.listProvince();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("lvlist",lvlist);
        params.put("provinceList",provinceList);
        return new ModelAndView("shop/admin/member/member_add",params);

    }
    @RequestMapping(params = {"list_lv"})
    public ModelAndView list_lv(String order) {
        Page webpage = memberLvManager.list(order, this.getPage(), this
                .getPageSize());
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("webpage",webpage);
        return new ModelAndView("shop/admin/member/lv_list",params);
    }
    @RequestMapping(params = {"add_lv"})
    public ModelAndView add_lv() {
        return new ModelAndView("shop/admin/member/lv_add");
    }
    public String saveAddLv(MemberLv lv) {
        memberLvManager.add(lv);
        return null;
    }
    @RequestMapping(params = {"detail"})
    public ModelAndView detail(Integer member_id) {
        Member member = this.memberManager.get(member_id);
        Map<Integer, String> pluginTabs = this.memberPluginBundle.getTabList(member);
        Map<Integer, String> pluginHtmls = this.memberPluginBundle.getDetailHtml(member);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pluginTabs",pluginTabs);
        params.put("pluginHtmls",pluginHtmls);
        params.put("member_id",member_id);
        return new ModelAndView("shop/admin/member/member_detail",params);
    }
}
