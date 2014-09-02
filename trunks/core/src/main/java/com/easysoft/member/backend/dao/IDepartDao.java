package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Company;
import com.easysoft.member.backend.model.Depart;

import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
public interface IDepartDao extends IGenericDao<Depart,Integer> {

    public List<Depart> queryByOrgId(Integer orgId);

    public Depart queryByQry(Map<String,Object> condition);
}
