package com.easysoft.shop.member.plugin.base;

import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.framework.plugin.AutoRegisterPlugin;
import com.easysoft.member.model.Member;
import com.easysoft.member.plugin.IMemberTabShowEvent;
import org.springframework.stereotype.Component;

/**
 * User: andy
 * Date: 13-8-14
 * Time: 下午2:50
 *
 * @since:
 */
@Component
public class MemberDetailBasePlugin extends AutoRegisterPlugin
        implements IMemberTabShowEvent
{
    public String onShowMemberDetailHtml(Member member)
    {
        FreeMarkerParser freeMarkerParser = FreeMarkerParser.getInstance();
        freeMarkerParser.putData("member", member);
        freeMarkerParser.setPageName("base");
        return freeMarkerParser.proessPageContent();
    }

    public String getTabName(Member member)
    {
        return "基本信息";
    }

    public int getOrder()
    {
        return 1;
    }

    public boolean canBeExecute(Member member)
    {
        return true;
    }
}