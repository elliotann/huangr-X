package com.easysoft.core.common.service.impl;

import com.easysoft.core.common.dao.hibernate.IGenericDAO;
import com.easysoft.core.common.dao.hibernate.PageList;
import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.common.dao.hibernate.qbc.HqlQuery;
import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.common.service.IGenericService;
import com.easysoft.core.common.vo.DataTableReturn;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.core.DBTable;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-1-22
 * Time: 下午5:58
 *
 * @since:
 */
public class GenericService<T> extends BaseSupport<T> implements IGenericService<T> {
    
}
