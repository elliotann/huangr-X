package com.easysoft.build.dao;


import com.easysoft.build.model.DeployPackInfoLog;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeployPackInfoLogDao extends HibernateGenericDao<DeployPackInfoLog,Long> {

	public void saveDeployPackInfoLog(DeployPackInfoLog dplog){
		saveOrUpdate(dplog);
	}	
	
	public List<DeployPackInfoLog>  getDeployPackInfoLogListById(String id,String curBranch){		
		String hql = " from DeployPackInfoLog dlog left join fetch dlog.ri r left join fetch dlog.dp d  where r.name=:curBranch " +
				" and r.isValid='2' and d.id=:id order by dlog.takeTime asc ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", Long.valueOf(id));
		params.put("curBranch", curBranch);
		return this.queryForHQL(hql, params);
		
	}
}
