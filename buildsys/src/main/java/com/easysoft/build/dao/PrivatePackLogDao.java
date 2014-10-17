package com.easysoft.build.dao;


import com.easysoft.build.model.PrivatePackLog;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrivatePackLogDao extends HibernateGenericDao<PrivatePackLog,Long> {

	public void savePrivatePackLog(PrivatePackLog prilog){
		saveOrUpdate(prilog);
	}
	
	public List<PrivatePackLog>  getPrivatePackLogListById(String id,String curBranch){		
		String hql = " from PrivatePackLog plog left join fetch plog.ri r left join fetch plog.bc b  where r.name=:curBranch " +
				" and r.isValid='2' and b.id=:id  order by plog.takeTime asc ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", Long.valueOf(id));
		params.put("curBranch", curBranch);
		return this.queryForHQL(hql, params);
	}
	
	/*public List<PrivatePackLog> getPrivatePackList(PrivatePackSearchBean searchBean, PageControlData pgd,String curBranch){
		 List<PrivatePackLog> list = new ArrayList<PrivatePackLog>();	
		  String pageHql = " from PrivatePackLog plog left join plog.ri rInfo left join plog.bc bc where 1=1   "+searchBean.getSerchCondition();
		  String hql = " from PrivatePackLog plog  left join fetch plog.ri rInfo left join fetch plog.bc bc where 1=1 and plog.ri.name='"+curBranch+"'"
		               + searchBean.getSerchCondition()
		               + " order by " + searchBean.getSort() + " " + searchBean.getOrder();
		  return searchByHql(pgd, hql, pageHql, searchBean.getSerchParam());
	}
	*/
}
