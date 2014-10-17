package com.easysoft.build.dao;


import com.easysoft.build.model.BtDeployPackRule;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BtDeployPackRuleDao extends HibernateGenericDao<BtDeployPackRule,Long> {
	
	public void saveBtDeployPackRule(BtDeployPackRule bdpr){
		saveOrUpdate(bdpr);
	}
	
	public void delBtDeployPackRule(BtDeployPackRule bdpr){
		if(bdpr!=null){
			this.deleteById(bdpr.getId());
		}
	}
	
	public List<BtDeployPackRule> getBtDeployPackRuleListByBranch(Long rid){
		String hql = "from BtDeployPackRule bdpr left join fetch bdpr.ri r where bdpr.ri.id=:rid ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("rid", rid);
		return this.queryForHQL(hql, params);
	}
	
	public BtDeployPackRule getBtDeployPackRuleByName(String deployPackName,Long rid){
		String hql = "from BtDeployPackRule bdpr left join fetch bdpr.ri r where bdpr.deployPackName=:deployPackName and bdpr.ri.id=:rid ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("deployPackName", deployPackName);
		params.put("rid", rid);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (BtDeployPackRule) list.get(0);
		}else{
			return null;
		}
	}
	
	
}
