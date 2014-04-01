package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IComponentDao;
import com.easysoft.framework.component.ComponentView;
import org.springframework.stereotype.Repository;

/**
 * User: andy
 * Date: 14-3-5
 * Time: 下午12:54
 *
 * @since:
 */
@Repository
public class ComponentDao  extends GenericDAO<ComponentView> implements IComponentDao {
}
