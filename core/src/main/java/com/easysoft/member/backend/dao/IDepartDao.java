package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Depart;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public interface IDepartDao extends IGenericDao<Depart,Integer> {

    public List<Depart> queryByOrgId(Integer orgId);
}
