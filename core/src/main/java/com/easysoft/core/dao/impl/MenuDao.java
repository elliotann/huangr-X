package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.IMenuDao;
import com.easysoft.member.backend.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class MenuDao extends HibernateGenericDao<Menu,Integer> implements IMenuDao {

    public Menu queryMenuByCondition(Map<String, Object> conditions) {
        return null;
    }


    public void deleteMenuByCondition(Map<String, Object> conditions) {

    }
}
