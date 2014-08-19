package com.easysoft.core.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Menu;

import java.util.Map;

/**
 * User: andy
 * Date: 14-3-14
 * Time: 上午11:03
 *
 * @since:
 */
public interface IMenuDao extends IGenericDao<Menu,Integer> {
    public Menu queryMenuByCondition(Map<String, Object> conditions);
    public void deleteMenuByCondition(Map<String, Object> conditions);
}
