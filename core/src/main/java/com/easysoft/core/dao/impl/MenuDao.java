package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IMenuDao;
import com.easysoft.member.backend.model.Menu;
import org.springframework.stereotype.Repository;

/**
 * User: andy
 * Date: 14-3-14
 * Time: 上午11:08
 *
 * @since:
 */
@Repository
public class MenuDao extends GenericDAO<Menu> implements IMenuDao {
    public MenuDao(){
        super(Menu.class);
    }
}
