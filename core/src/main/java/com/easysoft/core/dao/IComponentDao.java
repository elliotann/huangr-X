package com.easysoft.core.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.framework.component.ComponentView;

import java.util.Map;

/**
 * User: andy
 * Date: 14-3-5
 * Time: 下午12:54
 *
 * @since:
 */
public interface IComponentDao extends IGenericDao<ComponentView,Integer> {
    public ComponentView queryComponentByCompId(String componentId);
    public void updateByCondition(Map<String,Object> params);
}
