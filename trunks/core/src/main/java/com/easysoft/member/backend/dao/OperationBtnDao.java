package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.OperationBtn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class OperationBtnDao extends HibernateGenericDao<OperationBtn,Integer> implements IOperationBtnDao {
    @Override
    public List<OperationBtn> queryOperationsByMenuId(Integer menuId) {
        return null;
    }
}
