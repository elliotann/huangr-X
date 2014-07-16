package com.easysoft.jeap.core.member.manager;

import com.easysoft.jeap.core.member.dao.IMemberDao;
import com.easysoft.jeap.core.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2014/7/10.
 */
@Service("memberManager")
public class MemberManager implements IMemberManager {
    @Autowired
    private IMemberDao memberDao;
    @Override
    public void save(Member member) {
        memberDao.save(member);
    }
}
