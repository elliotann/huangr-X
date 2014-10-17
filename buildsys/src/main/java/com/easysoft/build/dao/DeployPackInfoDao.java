package com.easysoft.build.dao;


import com.easysoft.build.model.DeployPackInfo;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
@Repository
public class DeployPackInfoDao extends HibernateGenericDao<DeployPackInfo,Long> {

	public void saveDeployPackInfo(DeployPackInfo dp){
		saveOrUpdate(dp);
	}
	
	public DeployPackInfo getDeployPackInfoByName(String patchName,Long rid){
		String hql = "from DeployPackInfo dpi left join fetch dpi.ri r where dpi.deployPackName=:patchName and dpi.ri.id=:rid ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("patchName", patchName);
		params.put("rid", rid);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (DeployPackInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DeployPackInfo> getDeployPackInfoListByName(String patchName,Long rid){
		String hql = "from DeployPackInfo dpi left join fetch dpi.ri r where dpi.deployPackName<=:patchName and dpi.ri.id=:rid ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("patchName", patchName);
		params.put("rid", rid);
		return this.queryForHQL(hql, params);
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getDeployPackList(DeployPackInfoSearchBean searchBean, PageControlData pgd,String curBranch){		
		  String tname = searchBean.getTakePersonName();
		  String sqlselect = " select a.id id,a.deploypack_name deploypack_name,a.create_time create_time,b.co co ";
		  String sqlfrom = 	 " from ( "
					  +" select distinct  bd.id,bd.deploypack_name,bd.create_time "
					  +" from BT_DEPLOYPACK bd,BT_REPINFO ri "
					  +" where  bd.respinfo_id = ri.id and ri.IS_VALID='2' and ri.NAME='"+curBranch+"' ";
		  if(!StringUtil.isBlank_new(StringUtil.trim(tname))){			  
			     sqlfrom+="  and bd.id in ( select distinct bdlog.deploypack_id from BT_DEPLOYPACK_LOG bdlog where bdlog.take_person_name like '%"+tname+"%' ) ";
		  }
					  
         sqlfrom	= sqlfrom  + searchBean.getSerchCondition()
			  +" ) a "
			  +" left join "
			  +" ( "
			  +" select bd.id,count(bd.id) co from BT_DEPLOYPACK bd,BT_BUILDCONFIG bb where bd.id = bb.bd_id group by bd.id "
			  +" ) b on  a.id = b.id ";
		  String sqlOrder =" order by "+searchBean.getSort()+searchBean.getOrder();
		  //System.out.println("sql=" + sqlselect + sqlfrom);
		  return searchBySql(pgd, sqlselect + sqlfrom + sqlOrder, " FROM (" + sqlselect + sqlfrom + sqlOrder + " ) ",
				  searchBean.getSerchParam(), new SQLQueryCallBack() {
	                    public Object doInSQLQuery(SQLQuery sqlQuery) throws HibernateException {
	                        sqlQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
	                        sqlQuery.addScalar("id", Hibernate.LONG);
	                        sqlQuery.addScalar("deploypack_name", Hibernate.STRING);
	                        sqlQuery.addScalar("create_time", Hibernate.TIMESTAMP);
	                        sqlQuery.addScalar("co", Hibernate.INTEGER);
	                        return null;
	                    }
	                });
	}
	
	public DeployPackInfo getDeployPackInfoById(String id,String curBranch){		
		String hql = "from DeployPackInfo dpi left join fetch dpi.ri r where dpi.id=:id and r.name=:curBranch and r.isValid='2' ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", Long.valueOf(id));
		params.put("curBranch", curBranch);
		List list = this.searchByHql(hql, params);
		if(list!=null&&list.size()>0){
			return (DeployPackInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	*//**
	 * 根据分支ID找到该分支下最新的补丁包
	 * @param repinfoId
	 * @return
	 *//*
	public DeployPackInfo getLatestDeployPackByRid(long repinfoId){
		String hql = "from DeployPackInfo dp left join fetch dp.ri rInfo where rInfo.id=:rid order by dp.createTime desc";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("rid", repinfoId);
		List list = this.searchByHql(hql, params);
		if(list != null && list.size() > 0){
			return (DeployPackInfo) list.get(0);
		}else{
			return null;
		}		
	}*/
}
