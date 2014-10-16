package com.easysoft.build.dao;


import com.easysoft.build.model.BuildConfigInfoLog;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BuildConfigInfoLogDao extends HibernateGenericDao<BuildConfigInfoLog,Long> {
	
	public void saveBuildConfigInfoLog(BuildConfigInfoLog bclog){
		saveOrUpdate(bclog);
	}
	
	public List<BuildConfigInfoLog> getBuildConfigInfoLogListByBcId(String id){	
		String hql = " from BuildConfigInfoLog blog left join fetch blog.ri left join fetch blog.bc where blog.bc.id =:id order by blog.operaterTime asc ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", Long.valueOf(id));
		return this.queryForHQL(hql, params);
	}
	
}
