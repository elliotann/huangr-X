package com.easysoft.member.backend.dao;


import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.OrganizatiOnEntity;

import java.util.List;

public interface IOrganizatiOnDao extends IGenericDao<OrganizatiOnEntity,Integer> {
    public List<OrganizatiOnEntity> queryByPid(int pid);
}