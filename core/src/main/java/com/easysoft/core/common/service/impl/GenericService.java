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
@Service("genericService")
@Transactional
public class GenericService<T> extends BaseSupport<T> implements IGenericService<T> {
    private Class<T> entityClass;
    @Autowired
    private IGenericDAO genericDAO;

    /**
     * 加载全部实体
     * @param entityClass
     * @return
     */
    public List<T> queryForAll(final Class<T> entityClass) {
        return genericDAO.queryForAll(entityClass);
    }

    /**
     * 获取所有数据库表
     *
     * @return
     */
    public List<DBTable> getAllDbTableName() {
        return genericDAO.getAllDbTableName();
    }

    public Integer getAllDbTableSize() {
        return genericDAO.getAllDbTableSize();
    }

    @Resource
    public void setGenericDAO(IGenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }


    @Override
    public <T> Serializable save(T entity) {
        return genericDAO.save(entity);
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        genericDAO.saveOrUpdate(entity);

    }

    @Override
    public <T> void delete(T entity) {
        genericDAO.delete(entity);

    }

    /**
     * 删除实体集合
     *
     * @param <T>
     * @param entities
     */
    public <T> void deleteAllEntitie(Collection<T> entities) {
        genericDAO.deleteAllEntitie(entities);
    }

    /**
     * 根据实体名获取对象
     */
    public <T> T get(Class<T> class1, Serializable id) {
        return (T)genericDAO.get(class1, id);
    }


    /**
     * 根据实体名获取对象
     */
    public <T> T getEntity(Class entityName, Serializable id) {
        return (T)genericDAO.getEntity(entityName, id);
    }

    /**
     * 根据实体名称和字段名称和字段值获取唯一记录
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     */
    public <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {
        return (T)genericDAO.findUniqueByProperty(entityClass, propertyName, value);
    }

    /**
     * 按属性查找对象列表.
     */
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value) {

        return genericDAO.findByProperty(entityClass, propertyName, value);
    }



    public <T> T singleResult(String hql) {
        return (T)genericDAO.singleResult(hql);
    }

    /**
     * 删除实体主键ID删除对象
     *
     * @param <T>
     * @param entityName
     */
    public <T> void deleteEntityById(Class entityName, Serializable id) {
        genericDAO.deleteEntityById(entityName, id);
    }

    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    public <T> void updateEntitie(T pojo) {
        genericDAO.updateEntitie(pojo);

    }

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @param hql
     * @return
     */
    public <T> List<T> findByQueryString(String hql) {
        return genericDAO.findByQueryString(hql);
    }

    /**
     * 根据sql更新
     *
     * @param sql
     * @return
     */
    public int updateBySqlString(String sql) {
        return genericDAO.updateBySqlString(sql);
    }

    /**
     * 根据sql查找List
     *
     * @param <T>
     * @param query
     * @return
     */
    public <T> List<T> findListbySql(String query) {
        return genericDAO.findListbySql(query);
    }

    /**
     * 通过属性称获取实体带排序
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                             String propertyName, Object value, boolean isAsc) {
        return genericDAO.findByPropertyisOrder(entityClass, propertyName,
                value, isAsc);
    }

    /**
     *
     * cq方式分页
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
        return genericDAO.getPageList(cq, isOffset);
    }

    /**
     * 返回DataTableReturn模型
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
                                              final boolean isOffset) {
        return genericDAO.getDataTableReturn(cq, isOffset);
    }

    /**
     * 返回easyui datagrid模型
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
                                            final boolean isOffset) {
        return genericDAO.getDataGridReturn(cq, isOffset);
    }

    @Override
    public PageList getPageList(HqlQuery hqlQuery, boolean needParameter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     *
     * sqlQuery方式分页
     *
     * @param hqlQuery
     * @param isToEntity
     * @return
     */
    public PageList getPageListBySql(final HqlQuery hqlQuery,
                                     final boolean isToEntity) {
        return genericDAO.getPageListBySql(hqlQuery, isToEntity);
    }

    public Session getSession()

    {
        return genericDAO.getSession();
    }

    public List findByExample(final String entityName,
                              final Object exampleEntity) {
        return genericDAO.findByExample(entityName, exampleEntity);
    }

    /**
     * 通过cq获取全部实体
     *
     * @param <T>
     * @param cq
     * @return
     */
    public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
                                              Boolean ispage) {
        return genericDAO.getListByCriteriaQuery(cq, ispage);
    }

    @Override
    public void parserXml(String fileName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

 


   

   

    @Override
    public Integer executeSql(String sql, List<Object> param) {
        return genericDAO.executeSql(sql, param);
    }

    @Override
    public Integer executeSql(String sql, Object... param) {
        return genericDAO.executeSql(sql, param);
    }

    @Override
    public Integer executeSql(String sql, Map<String, Object> param) {
        return genericDAO.executeSql(sql, param);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
        return genericDAO.findForJdbc(sql, page, rows);
    }

    @Override
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return genericDAO.findForJdbc(sql, objs);
    }

    @Override
    public List<Map<String, Object>> findForJdbcParam(String sql, int page,
                                                      int rows, Object... objs) {
        return genericDAO.findForJdbcParam(sql, page, rows, objs);
    }

    @Override
    public <T> List<T> findObjForJdbc(String sql, int page, int rows,
                                      Class<T> clazz) {
        return genericDAO.findObjForJdbc(sql, page, rows, clazz);
    }

    @Override
    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        return genericDAO.findOneForJdbc(sql, objs);
    }

    @Override
    public Long getCountForJdbc(String sql) {
        return genericDAO.getCountForJdbc(sql);
    }

    @Override
    public Long getCountForJdbcParam(String sql, Object[] objs) {
        return genericDAO.getCountForJdbc(sql);
    }

    @Override
    public <T> void batchSave(List<T> entitys) {
        this.genericDAO.batchSave(entitys);
    }

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @param hql
     * @return
     */
    public <T> List<T> findHql(String hql, Object... param) {
        return this.genericDAO.queryForList(hql, param);
    }

    public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
                                int maxResult) {
        return this.genericDAO.pageList(dc, firstResult, maxResult);
    }

    @Override
    public void dropTable(String sql) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> List<T> findByDetached(DetachedCriteria dc) {
        return this.genericDAO.findByDetached(dc);
    }
}
