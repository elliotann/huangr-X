package com.easysoft.core.common.dao.hibernate;

import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.common.dao.hibernate.qbc.HqlQuery;
import com.easysoft.core.common.vo.DataTableReturn;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.Page;
import com.easysoft.framework.db.core.DBTable;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-1-22
 * Time: 下午5:53
 *
 * @since:
 */
public interface IGenericDAO<T> {
    /**
     * 加载全部实体数据
     * @param entityClass
     * @return
     */
    public List<T> queryForAll(Class<T> entityClass);
    /**保存实体**/
    public  <T> Serializable save(T entity);
    /**保存更新实体**/
    public  <T> void saveOrUpdate(T entity);
    public <T> void delete(T entity);
    /**
     * 删除实体集合
     *
     * @param <T>
     * @param entities
     */
    public <T> void deleteAllEntitie(Collection<T> entities);
    /**
     * 根据实体名字获取唯一记录
     *
     * @param propertyName
     * @param value
     * @return
     */
    public <T> T findUniqueByProperty(Class<T> entityClass,
                                      String propertyName, Object value);
    /**
     * 按属性查找对象列表.
     */
    public <T> List<T> findByProperty(Class<T> entityClass,
                                      String propertyName, Object value);
    /**
     * 通过hql查询唯一对象
     *
     * @param <T>
     * @param hql
     * @return
     */
    public <T> T singleResult(String hql);
    /**
     * 根据sql查找List
     *
     * @param <T>
     * @param query
     * @return
     */
    public <T> List<T> findListbySql(String query);

    /**
     * 执行SQL
     */
    public Integer executeSql(String sql, List<Object> param);

    /**
     * 执行SQL
     */
    public Integer executeSql(String sql, Object... param);

    /**
     * 执行SQL 使用:name占位符
     */
    public Integer executeSql(String sql, Map<String, Object> param);

    /**
     * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
     */
    public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

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
                                                      int rows, Object... objs);

    /**
     * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
     */
    public <T> List<T> findObjForJdbc(String sql, int page, int rows,
                                      Class<T> clazz);

    /**
     * 返回jquery datatables模型
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
                                              final boolean isOffset);
    /**
     * 返回easyui datagrid模型
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
                                            final boolean isOffset);

    /**
     *
     * sqlQuery方式分页
     *
     * @param hqlQuery
     * @param needParameter
     * @return
     */
    public PageList getPageListBySql(final HqlQuery hqlQuery,
                                     final boolean needParameter);

    public List findByExample(final String entityName,
                              final Object exampleEntity);

    /**
     * 通过cq获取全部实体
     *
     * @param <T>
     * @param cq
     * @return
     */
    public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
                                              Boolean ispage);


    /**
     *
     * cq方式分页
     *
     * @param cq
     * @param isOffset
     * @return
     */
    public PageList getPageList(final CriteriaQuery cq, final boolean isOffset);

    /**
     * 通过属性称获取实体带排序
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                             String propertyName, Object value, boolean isAsc);
    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    public <T> void updateEntitie(T pojo);
    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC
     */
    public Long getCountForJdbc(String sql);
    /**
     * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
     */
    public Map<String, Object> findOneForJdbc(String sql, Object... objs);
    public <T> void batchSave(List<T> entitys);
    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @param hql
     * @return
     */
    public <T> List<T> findByQueryString(String hql);

    /**
     * 根据sql更新
     *
     * @param sql
     * @return
     */
    public int updateBySqlString(String sql);
    /**
     * 执行原生SQL
     * @param sql 要执行的SQL语句
     */
    public void executeSQL(String sql);
    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    public <T> T getEntity(Class entityName, Serializable id);


    /**
     * 根据ID删除实体
     * @param clazz
     * @param id
     * @param <T>
     */
    public  <T> void deleteEntityById(Class clazz, Serializable id);

    public  <T> T get(Class<T> clazz, Serializable id);

    /**根据主键获取实体**/
    public  <T> T get(Integer id);

    public List<T> queryForList(String hql,Object[] params);

    public <T> List<T> findByDetached(DetachedCriteria dc);

    /**
     * 分页查询
     * @param dc
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page queryForPage(DetachedCriteria dc, int pageNo,
                                int pageSize);

    public List<T> pageList(DetachedCriteria dc,int firstResult,int maxResult);


    /**
     * 得到所有数据加表名
     * @return
     */
    public List<DBTable> getAllDbTableName();

    public Integer getAllDbTableSize();

    public Session getSession();
}
