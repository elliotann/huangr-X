package com.easysoft.member.backend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.OperationBtn;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class OperationBtnDao extends HibernateGenericDao<OperationBtn,Integer> implements IOperationBtnDao {
    
    public List<OperationBtn> queryOperationsByMenuId(Integer menuId) {
    	String hql = "from OperationBtn o where o.menuId=:menuId";
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("menuId", menuId.toString());
        return this.queryForHQL(hql, params);
    }
}
