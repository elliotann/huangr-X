package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.Employ;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
public interface IEmployDao extends IGenericDao<Employ,Integer>{
    public Employ queryEmployByCondition(Map<String,Object> condition);
}
