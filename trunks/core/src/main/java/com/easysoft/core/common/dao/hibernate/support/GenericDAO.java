package com.easysoft.core.common.dao.hibernate.support;

import com.easysoft.core.annotation.JeecgEntityTitle;
import com.easysoft.core.common.dao.hibernate.IGenericDAO;
import com.easysoft.core.common.dao.hibernate.PageList;
import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.common.dao.hibernate.qbc.DetachedCriteriaUtil;
import com.easysoft.core.common.dao.hibernate.qbc.HqlQuery;
import com.easysoft.core.common.dao.jdbc.JdbcDao;
import com.easysoft.core.common.exception.BusinessException;
import com.easysoft.core.common.vo.DataTableReturn;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.Page;
import com.easysoft.framework.db.core.DBTable;
import com.easysoft.framework.db.core.common.hibernate.qbc.PagerUtil;
import com.easysoft.framework.utils.MyBeanUtils;
import com.easysoft.framework.utils.ToEntityUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * User: andy
 * Date: 14-1-22
 * Time: 下午5:54
 *
 * @since:
 */
@Repository
public class GenericDAO<T> implements IGenericDAO<T> {
    protected static final Log logger = LogFactory.getLog(GenericDAO.class);
    private Class<T> entityClass;
    public GenericDAO(){}
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    public GenericDAO(Class<T> clazz) {
        this.entityClass = clazz;

    }
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public <T> Serializable save(T entity) {
        Serializable id = getSession().save(entity);
        /*if(logger.isDebugEnabled()){
            logger.debug("保存实体【"+entityClass.getName()+"】成功！");
        }*/
        return id;
    }
    /**
     * 通过sql查询语句查找对象
     *
     * @param sql
     * @return
     */
    public List<T> findListbySql(final String sql) {
        Query querys = getSession().createSQLQuery(sql);
        return querys.list();
    }
    /**
     * 根据属性名和属性值查询. 有排序
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @param isAsc
     * @return
     */
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                             String propertyName, Object value, boolean isAsc) {
        Assert.hasText(propertyName);
        return createCriteria(entityClass, isAsc,
                Restrictions.eq(propertyName, value)).list();
    }
    /**
     * 获取分页记录CriteriaQuery 老方法final int allCounts =
     * oConvertUtils.getInt(criteria
     * .setProjection(Projections.rowCount()).uniqueResult(), 0);
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {

        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
                getSession());
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        final int allCounts = ((Long) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        // 判断是否有排序字段
        if (cq.getOrdermap() != null) {
            cq.setOrder(cq.getOrdermap());
        }
        int pageSize = cq.getPageSize();// 每页显示数
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(),
                pageSize);// 当前页
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        String toolBar = "";
        if (isOffset) {// 是否分页
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
            if (cq.getIsUseimage() == 1) {
                toolBar = PagerUtil.getBar(cq.getMyAction(), cq.getMyForm(),
                        allCounts, curPageNO, pageSize, cq.getMap());
            } else {
                toolBar = PagerUtil.getBar(cq.getMyAction(), allCounts,
                        curPageNO, pageSize, cq.getMap());
            }
        } else {
            pageSize = allCounts;
        }
        return new PageList(criteria.list(), toolBar, offset, curPageNO,
                allCounts);
    }
    public Integer executeSql(String sql, List<Object> param) {
        return this.jdbcTemplate.update(sql, param);
    }

    public Integer executeSql(String sql, Object... param) {
        return this.jdbcTemplate.update(sql, param);
    }

    public Integer executeSql(String sql, Map<String, Object> param) {
        return this.namedParameterJdbcTemplate.update(sql, param);
    }
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }
    /**
     * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
     *
     * @param sql
     * @param page
     * @param rows
     * @return
     * @throws org.springframework.dao.DataAccessException
     */
    public List<Map<String, Object>> findForJdbcParam(String sql, int page,
                                                      int rows, Object... objs) {
        // 封装分页SQL
        sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
        return this.jdbcTemplate.queryForList(sql, objs);
    }
    /**
     * 使用指定的检索标准检索数据并分页返回数据
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> List<T> findObjForJdbc(String sql, int page, int rows,
                                      Class<T> clazz) {
        List<T> rsList = new ArrayList<T>();
        // 封装分页SQL
        sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);

        T po = null;
        for (Map<String, Object> m : mapList) {
            try {
                po = clazz.newInstance();
                MyBeanUtils.copyMap2Bean_Nobig(po, m);
                rsList.add(po);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rsList;
    }

    /**
     * 返回JQUERY datatables DataTableReturn模型对象
     */
    public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
                                              final boolean isOffset) {

        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
                getSession());
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        final int allCounts = ((Long) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        // 判断是否有排序字段
        if (cq.getOrdermap() != null) {
            cq.setOrder(cq.getOrdermap());
        }
        int pageSize = cq.getPageSize();// 每页显示数
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(),
                pageSize);// 当前页
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        if (isOffset) {// 是否分页
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
        } else {
            pageSize = allCounts;
        }
        DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(), cq
                .getField().split(","), cq.getEntityClass(), false);
        return new DataTableReturn(allCounts, allCounts, cq.getDataTables().getEcho(), criteria.list());
    }
    /**
     * 返回easyui datagrid DataGridReturn模型对象
     */
    public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
                                            final boolean isOffset) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
                getSession());
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        final int allCounts = ((Long) criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (StringUtils.isNotBlank(cq.getDataGrid().getSort())) {
            cq.addOrder(cq.getDataGrid().getSort(), cq.getDataGrid().getOrder());
        }

        // 判断是否有排序字段
        if (!cq.getOrdermap().isEmpty()) {
            cq.setOrder(cq.getOrdermap());
        }
        int pageSize = cq.getPageSize();// 每页显示数
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage(),
                pageSize);// 当前页
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        if (isOffset) {// 是否分页
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
        } else {
            pageSize = allCounts;
        }
        // DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(),
        // cq.getField().split(","), cq.getClass1(), false);
        List list = criteria.list();
        cq.getDataGrid().setResults(list);
        cq.getDataGrid().setTotal(allCounts);
        return new DataGridReturn(allCounts, list);
    }
    /**
     * 创建Criteria对象，有排序功能。
     *
     * @param <T>
     * @param entityClass
     * @param isAsc
     * @param criterions
     * @return
     */
    private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc,
                                        Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc("asc"));
        } else {
            criteria.addOrder(Order.desc("desc"));
        }
        return criteria;
    }

    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        try {
            return this.jdbcTemplate.queryForMap(sql, objs);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Session getSession(){
        try{
            return sessionFactory.getCurrentSession();
        }catch (Exception e){
            logger.error("warn:this is not a current session!",e);
            return sessionFactory.openSession();
        }

    }
    /**
     * 根据实体名字获取唯一记录
     *
     * @param propertyName
     * @param value
     * @return
     */
    public <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(entityClass,
                Restrictions.eq(propertyName, value)).uniqueResult();
    }
    /**
     * 根据主键获取实体并加锁。
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    public <T> T getEntity(Class entityName, Serializable id) {

        T t = (T) getSession().get(entityName, id);
        if (t != null) {
            getSession().flush();
        }
        return t;
    }
    public  List<T> queryForAll(Class<T> entityClass) {
        Criteria criteria = createCriteria(entityClass);
        return criteria.list();
    }
    /**
     * 创建单一Criteria对象
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    private <T> Criteria createCriteria(Class<T> entityClass) {
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria;
    }
    /**
     * 创建Criteria对象带属性比较
     *
     * @param <T>
     * @param entityClass
     * @param criterions
     * @return
     */
    private <T> Criteria createCriteria(Class<T> entityClass,
                                        Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     * 通过hql查询唯一对象
     *
     * @param <T>
     * @param hql
     * @return
     */
    public <T> T singleResult(String hql) {
        T t = null;
        Query queryObject = getSession().createQuery(hql);
        List<T> list = queryObject.list();
        if (list.size() == 1) {
            getSession().flush();
            t = list.get(0);
        } else if (list.size() > 0) {
            throw new BusinessException("查询结果数:" + list.size() + "大于1");
        }
        return t;
    }

    /**
     * 通过hql 查询语句查找对象
     *
     * @param query
     * @return
     */
    public List<T> findByQueryString(final String query) {

        Query queryObject = getSession().createQuery(query);
        List<T> list = queryObject.list();
        if (list.size() > 0) {
            getSession().flush();
        }
        return list;

    }

    /**
     * 通过sql更新记录
     *
     * @param query
     * @return
     */
    public int updateBySqlString(final String query) {

        Query querys = getSession().createSQLQuery(query);
        return querys.executeUpdate();
    }


    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    public <T> void updateEntitie(T pojo) {
        getSession().update(pojo);
        getSession().flush();
    }

    /**
     * 批量保存数据
     *
     * @param <T>
     * @param entitys
     *            要持久化的临时实体对象集合
     */
    public <T> void batchSave(List<T> entitys) {
        for (int i = 0; i < entitys.size(); i++) {
            getSession().save(entitys.get(i));
            if (i % 20 == 0) {
                // 20个对象后才清理缓存，写入数据库
                getSession().flush();
                getSession().clear();
            }
        }
        // 最后清理一下----防止大于20小于40的不保存
        getSession().flush();
        getSession().clear();
    }
    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC
     */
    public Long getCountForJdbc(String sql) {
        return this.jdbcTemplate.queryForLong(sql);
    }

    /**
     * 按属性查找对象列表.
     */
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (List<T>) createCriteria(entityClass,
                Restrictions.eq(propertyName, value)).list();
    }
    /**
     * 删除全部的实体
     *
     * @param <T>
     *
     * @param entitys
     */
    public <T> void deleteAllEntitie(Collection<T> entitys) {
        for (Object entity : entitys) {
            getSession().delete(entity);
            getSession().flush();
        }
    }
    /**
     * 根据传入的实体删除对象
     */
    public <T> void delete(T entity) {
        try {
            getSession().delete(entity);
            getSession().flush();
            if (logger.isDebugEnabled()) {
                logger.debug("删除成功," + entity.getClass().getName());
            }
        } catch (RuntimeException e) {
            logger.error("删除异常", e);
            throw e;
        }
    }
    @Override
    public void executeSQL(String sql) {
        getSession().createSQLQuery(sql).executeUpdate();
    }

    @Override
    public <T> void deleteEntityById(Class clazz, Serializable id) {
        getSession().delete(get(clazz,id));
    }

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return (T)getSession().get(clazz,id);

    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public <T> T get(Integer id) {
        return (T)(getSession().get(entityClass,id));
    }

    public List<T> queryForList(String hql,Object[] params){
        if(params==null||params.length<=0){
            return this.queryForList(hql);
        }
        Query query = getSession().createQuery(hql);
        setQueryParams(query,params);
        return query.list();
    }
    protected List<T> queryForList(String hql){
        Query query = getSession().createQuery(hql);
        return query.list();
    }
    private void setQueryParams(Query query,Object[] params){
        if(params == null) return;
        for(int i=0;i<params.length;i++){
            query.setParameter(i,params[i]);
        }
    }

    @Override
    public <T> List<T> findByDetached(DetachedCriteria dc) {
        return dc.getExecutableCriteria(getSession()).list();
    }

    @Override
    public Page queryForPage(DetachedCriteria dc, int pageNo, int pageSize) {
        Criteria criteria = dc.getExecutableCriteria(getSession());
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        int totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(null);
        criteria.setFirstResult((pageNo-1)*pageSize);
        criteria.setMaxResults(pageNo*pageSize);
        return new Page(0, totalCount, pageSize, criteria.list());

    }

    public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult){
        Criteria criteria = dc.getExecutableCriteria(getSession());
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    /**
     * 获取所有数据表
     *
     * @return
     */
    public List<DBTable> getAllDbTableName() {
        List<DBTable> resultList = new ArrayList<DBTable>();
        SessionFactory factory = getSession().getSessionFactory();
        Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
        for (String key : (Set<String>) metaMap.keySet()) {
            DBTable dbTable = new DBTable();
            AbstractEntityPersister classMetadata = (AbstractEntityPersister) metaMap
                    .get(key);
            dbTable.setTableName(classMetadata.getTableName());
            dbTable.setEntityName(classMetadata.getEntityName());
            Class<?> c;
            try {
                c = Class.forName(key);
                JeecgEntityTitle t = c.getAnnotation(JeecgEntityTitle.class);
                dbTable.setTableTitle(t != null ? t.name() : "");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            resultList.add(dbTable);
        }
        return resultList;
    }

    /**
     * 获取所有数据表
     *
     * @return
     */
    public Integer getAllDbTableSize() {
        SessionFactory factory = getSession().getSessionFactory();
        Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
        return metaMap.size();
    }
    /**
     * 获取分页记录SqlQuery
     *
     * @param hqlQuery
     * @param isToEntity
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageList getPageListBySql(final HqlQuery hqlQuery,
                                     final boolean isToEntity) {

        Query query = getSession().createSQLQuery(hqlQuery.getQueryString());

        // query.setParameters(hqlQuery.getParam(), (Type[])
        // hqlQuery.getTypes());
        int allCounts = query.list().size();
        int curPageNO = hqlQuery.getCurPage();
        int offset = PagerUtil.getOffset(allCounts, curPageNO,
                hqlQuery.getPageSize());
        query.setFirstResult(offset);
        query.setMaxResults(hqlQuery.getPageSize());
        List list = null;
        if (isToEntity) {
            list = ToEntityUtil.toEntityList(query.list(),
                    hqlQuery.getClass1(), hqlQuery.getDataGrid().getField()
                    .split(","));
        } else {
            list = query.list();
        }
        return new PageList(hqlQuery, list, offset, curPageNO, allCounts);
    }
    /**
     * 根据实体模版查找
     *
     * @param entityName
     * @param exampleEntity
     * @return
     */

    public List findByExample(final String entityName,
                              final Object exampleEntity) {
        Assert.notNull(exampleEntity, "Example entity must not be null");
        Criteria executableCriteria = (entityName != null ? getSession()
                .createCriteria(entityName) : getSession().createCriteria(
                exampleEntity.getClass()));
        executableCriteria.add(Example.create(exampleEntity));
        return executableCriteria.list();
    }

    /**
     * 根据CriteriaQuery获取List
     *
     * @param cq
     * @param ispage
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByCriteriaQuery(final CriteriaQuery cq, Boolean ispage) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(
                getSession());
        // 判断是否有排序字段
        if (cq.getOrdermap() != null) {
            cq.setOrder(cq.getOrdermap());
        }
        if (ispage)
            criteria.setMaxResults(cq.getPageSize());
        return criteria.list();

    }
}
