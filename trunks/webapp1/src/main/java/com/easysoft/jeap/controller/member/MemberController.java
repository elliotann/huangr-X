package com.easysoft.jeap.controller.member;

import com.easysoft.jeap.core.member.dao.IMemberDao;
import com.easysoft.jeap.core.member.entity.Member;
import com.easysoft.jeap.core.member.manager.IMemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2014/7/10.
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private IMemberManager memberManager;
    @RequestMapping("saveMember")
    public String saveMember(Member member){
        memberManager.save(member);
        return "";
    }
}
