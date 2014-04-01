package com.easysoft.shop.member.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.member.model.Member;
import com.easysoft.shop.member.dao.IMemberDao;
import org.springframework.stereotype.Repository;

/**
 * User: andy
 * Date: 14-3-10
 * Time: 下午3:22
 *
 * @since:
 */
@Repository
public class MemberDao extends GenericDAO<Member> implements IMemberDao {
    public MemberDao(){
        super(Member.class);
    }
}
