package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Company;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
public interface ICompanyDao extends IGenericDao<Company,Integer> {
    public Company queryByQry(Map<String,Object> condition);
}
