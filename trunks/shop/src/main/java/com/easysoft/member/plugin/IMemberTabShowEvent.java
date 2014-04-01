package com.easysoft.member.plugin;

import com.easysoft.member.model.Member;

/**
 * User: andy
 * Date: 13-8-14
 * Time: 下午1:13
 *
 * @since:
 */
public interface IMemberTabShowEvent {
    public  String getTabName(Member paramMember);

    public  int getOrder();

    public  boolean canBeExecute(Member paramMember);

    public  String onShowMemberDetailHtml(Member paramMember);
}
