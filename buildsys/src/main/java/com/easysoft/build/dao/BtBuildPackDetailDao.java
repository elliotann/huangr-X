package com.easysoft.build.dao;


import com.easysoft.build.model.BtBuildPackDetail;
import com.easysoft.build.model.RepInfo;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BtBuildPackDetailDao extends HibernateGenericDao<BtBuildPackDetail,Long> {

	public void saveBasePackageFile(BtBuildPackDetail bbpd){
		saveOrUpdate(bbpd);
	}
	
	public BtBuildPackDetail getBtBuildPackDetail(long id){
		String hql = " from BtBuildPackDetail bbpd left join fetch bbpd.ri rInfo  where bbpd.id = :id  ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (BtBuildPackDetail) list.get(0);
		}else{
			return null;
		}
	}
	
	public BtBuildPackDetail getBtBuildPackDetailByNo(String no){
		String hql = " from BtBuildPackDetail bbpd left join fetch bbpd.ri rInfo  where bbpd.buildPackName = :no  ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("no", no);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (BtBuildPackDetail) list.get(0);
		}else{
			return null;
		}
	}
	
	public BtBuildPackDetail getBtBuildPackDetailByNoRid(String no,RepInfo ri){
		String hql = " from BtBuildPackDetail bbpd left join fetch bbpd.ri rInfo  where bbpd.buildPackName = :no and rInfo.id=:id ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("no", no);
		params.put("id", ri.getId());
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (BtBuildPackDetail) list.get(0);
		}else{
			return null;
		}
	}
	
	public List<BtBuildPackDetail> getBtBuildPackDetailByRidArray(List<String> vpnames,RepInfo ri){
		if(vpnames == null || vpnames.size()==0){
			return new ArrayList<BtBuildPackDetail>();
		}
		String hql = " from BtBuildPackDetail bbpd left join fetch bbpd.ri rInfo  where  rInfo.id=:id and bbpd.buildPackName in (:ba) ";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong("id", ri.getId().longValue());
		query.setParameterList("ba", vpnames);
		return query.list();
	}
	
	
}
