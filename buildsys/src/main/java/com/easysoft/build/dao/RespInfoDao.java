package com.easysoft.build.dao;


import com.easysoft.build.model.RepInfo;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RespInfoDao extends HibernateGenericDao<RepInfo,Long> {
	
	public void delRespInfo(RepInfo ri){		
		if(ri!=null){
			this.deleteById(ri.getId());
		}
	}
	
	
	public RepInfo getRespInfoById(long id){
		String hql = " from RepInfo ri where ri.id = :id";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByName(String name){
		String hql = " from RepInfo ri where ri.name = :name  ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByNameForUpdate(String name,Long id){
		String hql = " from RepInfo ri where ri.name = :name  and ri.id<>"+id;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", name);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoBySvnUrl(String svnUrl){
		String hql = " from RepInfo ri where ri.svnUrl = :svnUrl ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("svnUrl", svnUrl);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoBySvnUrlForUpdate(String svnUrl,Long id){
		String hql = " from RepInfo ri where ri.svnUrl = :svnUrl and ri.id<>"+id;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("svnUrl", svnUrl);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoBySvnRoot(String svnRoot){
		String hql = " from RepInfo ri where ri.svnRoot = :svnRoot ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("svnRoot", svnRoot);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoBySvnRootForUpdate(String svnRoot,Long id){
		String hql = " from RepInfo ri where ri.svnRoot = :svnRoot and ri.id<>"+id;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("svnRoot", svnRoot);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByBranchRoot(String branchRoot){
		String hql = " from RepInfo ri where ri.branchRoot = :branchRoot ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("branchRoot", branchRoot);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByBranchRootForUpdate(String branchRoot,Long id){
		String hql = " from RepInfo ri where ri.branchRoot = :branchRoot and ri.id<>"+id;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("branchRoot", branchRoot);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByVersionNo(String versionNo){
		String hql = " from RepInfo ri where ri.versionNo = :versionNo ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("versionNo", versionNo);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public RepInfo getRespInfoByVersionNoForUpdate(String versionNo,Long id){
		String hql = " from RepInfo ri where ri.versionNo = :versionNo and ri.id<>"+id;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("versionNo", versionNo);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (RepInfo) list.get(0);
		}else{
			return null;
		}
	}
	
  /* public List<RepInfo> getReposList(RespInfoSearchBean searchBean, PageControlData pgd){
	  List<RepInfo> list = new ArrayList<RepInfo>();	
	  String pageHql = " from RepInfo ri where 1=1   "+searchBean.getSerchCondition();
	  String hql = pageHql + " order by " + searchBean.getSort() + " " + searchBean.getOrder();
	  return searchByHql(pgd, hql, pageHql, searchBean.getSerchParam());
   }*/
	
	public void saveRespInfo(RepInfo ri){
		saveOrUpdate(ri);
	}

}
