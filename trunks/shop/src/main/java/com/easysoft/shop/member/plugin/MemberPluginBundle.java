package com.easysoft.shop.member.plugin;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.plugin.AutoRegisterPluginsBundle;
import com.easysoft.framework.plugin.IPlugin;
import com.easysoft.member.core.plugin.member.IMemberUpdatePasswordEvent;
import com.easysoft.member.model.Member;
import com.easysoft.member.plugin.IMemberEmailCheckEvent;
import com.easysoft.member.plugin.IMemberLoginEvent;
import com.easysoft.member.plugin.IMemberLogoutEvent;
import com.easysoft.member.plugin.IMemberRegisterEvent;
import com.easysoft.member.plugin.IMemberTabShowEvent;


/**
 * 会员插件桩
 *
 * @author andy
 * @since : 1.0
 */
@Component("memberPluginBundle")
public class MemberPluginBundle extends AutoRegisterPluginsBundle {


    /**
     * 激发注册事件
     */
    public void onLogout(Member member) {
        try
        {
            List<IPlugin> plugins = getPlugins();

            if (plugins != null) {
                for (IPlugin plugin : plugins)
                    if ((plugin instanceof IMemberLogoutEvent)) {
                        if (loger.isDebugEnabled()) {
                            loger.debug("调用插件 : " + plugin.getClass() + " onLogout 开始...");
                        }
                        IMemberLogoutEvent event = (IMemberLogoutEvent)plugin;
                        event.onLogout(member);
                        if (loger.isDebugEnabled())
                            loger.debug("调用插件 : " + plugin.getClass() + " onLogout 结束.");
                    }
            }
        }
        catch (RuntimeException e)
        {
            if (loger.isErrorEnabled())
                loger.error("调用会员插件注销事件错误", e);
            throw e;
        }
    }


    /**
     * 激发注册事件
     */
    public void onLogin(Member member, Long upLogintime) {
        try
        {
            List<IPlugin> plugins = getPlugins();

            if (plugins != null) {
                for (IPlugin plugin : plugins)
                    if ((plugin instanceof IMemberLoginEvent)) {
                        if (loger.isDebugEnabled()) {
                            loger.debug("调用插件 : " + plugin.getClass() + " onLogin 开始...");
                        }
                        IMemberLoginEvent event = (IMemberLoginEvent)plugin;
                        event.onLogin(member, upLogintime);
                        if (loger.isDebugEnabled())
                            loger.debug("调用插件 : " + plugin.getClass() + " onLogin 结束.");
                    }
            }
        }
        catch (RuntimeException e)
        {
            if (loger.isErrorEnabled())
                loger.error("调用会员插件登录事件错误", e);
            throw e;
        }
    }


    /**
     * 激发注册事件
     */
    public void onRegister(Member member) {
        try
        {
            List<IPlugin> plugins = getPlugins();

            if (plugins != null) {
                for (IPlugin plugin : plugins)
                    if ((plugin instanceof IMemberRegisterEvent)) {
                        if (loger.isDebugEnabled()) {
                            loger.debug("调用插件 : " + plugin.getClass() + " onRegister 开始...");
                        }
                        IMemberRegisterEvent event = (IMemberRegisterEvent)plugin;
                        event.onRegister(member);
                        if (loger.isDebugEnabled())
                            loger.debug("调用插件 : " + plugin.getClass() + " onRegister 结束.");
                    }
            }
        }
        catch (RuntimeException e)
        {
            if (loger.isErrorEnabled())
                loger.error("调用会员插件注册事件错误", e);
            throw e;
        }
    }


    /**
     * 激发邮件校验事件
     */
    public void onEmailCheck(Member member) {
        try
        {
            List<IPlugin> plugins = getPlugins();

            if (plugins != null) {
                for (IPlugin plugin : plugins)
                    if ((plugin instanceof IMemberEmailCheckEvent)) {
                        if (loger.isDebugEnabled()) {
                            loger.debug("调用插件 : " + plugin.getClass() + " onRegister 开始...");
                        }
                        IMemberEmailCheckEvent event = (IMemberEmailCheckEvent)plugin;
                        event.onEmailCheck(member);
                        if (loger.isDebugEnabled())
                            loger.debug("调用插件 : " + plugin.getClass() + " onRegister 结束.");
                    }
            }
        }
        catch (RuntimeException e)
        {
            if (loger.isErrorEnabled())
                loger.error("调用会员插件邮件验证事件错误", e);
            throw e;
        }
    }

    public void onUpdatePassword(String password, int memberid)
    {
        try
        {
            List<IPlugin> plugins = getPlugins();

            if (plugins != null) {
                for (IPlugin plugin : plugins)
                    if ((plugin instanceof IMemberUpdatePasswordEvent)) {
                        if (loger.isDebugEnabled()) {
                            loger.debug("调用插件 : " + plugin.getClass() + " onUpdatePassword 开始...");
                        }
                        IMemberUpdatePasswordEvent event = (IMemberUpdatePasswordEvent)plugin;
                        event.updatePassword(password, memberid);
                        if (loger.isDebugEnabled())
                            loger.debug("调用插件 : " + plugin.getClass() + " onUpdatePassword 结束.");
                    }
            }
        }
        catch (RuntimeException e)
        {
            if (loger.isErrorEnabled())
                loger.error("调用会员更新密码事件错误", e);
            throw e;
        }
    }
    public Map<Integer, String> getTabList(Member member) {
        List<IPlugin> plugins = getPlugins();

        Map tabMap = new TreeMap();
        if (plugins != null) {
            for (IPlugin plugin : plugins) {
                if ((plugin instanceof IMemberTabShowEvent)) {
                    IMemberTabShowEvent event = (IMemberTabShowEvent) plugin;

                    if (!event.canBeExecute(member)) {
                        continue;
                    }

                    String name = event.getTabName(member);
                    tabMap.put(Integer.valueOf(event.getOrder()), name);
                }

            }

        }

        return tabMap;
    }

    public Map<Integer, String> getDetailHtml(Member member) {
        Map htmlMap = new TreeMap();
        FreeMarkerParser freeMarkerParser = FreeMarkerParser.getInstance();
        freeMarkerParser.putData("member", member);
        List<IPlugin> plugins = getPlugins();

        if (plugins != null) {
            for (IPlugin plugin : plugins) {
                if ((plugin instanceof IMemberTabShowEvent)) {
                    IMemberTabShowEvent event = (IMemberTabShowEvent) plugin;
                    freeMarkerParser.setClz(event.getClass());

                    if (!event.canBeExecute(member)) {
                        continue;
                    }
                    String html = event.onShowMemberDetailHtml(member);
                    htmlMap.put(Integer.valueOf(event.getOrder()), html);
                }

            }

        }

        return htmlMap;
    }

    @Override
    public String getName() {
        return "会员插件桩";
    }


}
