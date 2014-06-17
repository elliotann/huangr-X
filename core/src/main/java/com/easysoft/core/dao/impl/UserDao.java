package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IUserDao;
import com.easysoft.core.model.JEAPUser;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-23
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao extends GenericDAO<JEAPUser> implements IUserDao{
    public UserDao() {
        super(JEAPUser.class);
    }
}
