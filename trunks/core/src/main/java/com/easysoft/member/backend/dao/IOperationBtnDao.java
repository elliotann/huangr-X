package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.OperationBtn;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface IOperationBtnDao extends IGenericDao<OperationBtn,Integer> {
    public List<OperationBtn> queryOperationsByMenuId(Integer menuId);
}
