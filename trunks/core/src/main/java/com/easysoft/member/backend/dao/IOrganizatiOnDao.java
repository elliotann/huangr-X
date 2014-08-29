package com.easysoft.member.backend.dao;


import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Organization;

import java.util.List;

public interface IOrganizatiOnDao extends IGenericDao<Organization,Integer> {
    public List<Organization> queryByPid(int pid);
}