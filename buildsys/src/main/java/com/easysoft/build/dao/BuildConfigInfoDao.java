/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.build.dao;


import com.easysoft.build.model.BuildConfigInfo;
import com.easysoft.build.model.DeployPackInfo;
import com.easysoft.build.model.RepInfo;
import com.easysoft.build.vo.BuildConfigInfoSearchBean;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.framework.db.PageOption;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BuildConfigInfoDao extends HibernateGenericDao<BuildConfigInfo,Long>{
	
	public List<BuildConfigInfo> getBuildConfigInfoByDeployId(String id,String curBranch){
		String hql = " from BuildConfigInfo bc  left join fetch bc.ri rInfo left join fetch bc.bd d where rInfo.name=:curBranch " +
				"and rInfo.isValid='2' and bc.status='5'  and d.id=:id order by bc.addTime asc ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", Long.valueOf(id));
		params.put("curBranch", curBranch);
		return this.queryForHQL(hql, params);
	}
	
	public List<BuildConfigInfo> getBuildConfigInfoByDeployListId(List<DeployPackInfo> dplist,String curBranch){
		List<Long> ids = new ArrayList<Long>();
		for(DeployPackInfo dp:dplist){
			ids.add(dp.getId());
		}
		String hql = " from BuildConfigInfo bc  left join fetch bc.ri rInfo left join fetch bc.bd d where rInfo.name=:curBranch " +
				"and rInfo.isValid='2' and bc.status='5'  and d.id in(:ids) order by bc.addTime asc ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		params.put("curBranch", curBranch);
		return this.queryForHQL(hql, params);
	}
    public List<BuildConfigInfo> getBuildConfigInfoList(BuildConfigInfoSearchBean searchBean, PageOption pageOption,String curBranch){

        List<Criterion> criterions = new ArrayList<Criterion>();
        if(StringUtils.isNotEmpty(searchBean.getBuildFileName())){
            criterions.add(Restrictions.like("buildFileName", searchBean.getBuildFileName()));

        }
        return this.queryForPage(pageOption,criterions);
        /*String pageHql = " from BuildConfigInfo bc left join bc.ri rInfo where 1=1 and bc.ri.name='"+curBranch+"' "+searchBean.getSerchCondition();
        if("1".equals(searchBean.getIo())){
            pageHql += " and bc.status <> '4' ";
        }
        String hql = "from BuildConfigInfo bc  left join fetch bc.ri rInfo left join fetch bc.bd where 1=1 and bc.ri.name='"+curBranch+"' " +searchBean.getSerchCondition();
        if("1".equals(searchBean.getIo())){
            hql += " and bc.status <> '4' ";
        }
        hql += " order by " + searchBean.getSort() + " " + searchBean.getOrder();
        return this.queryForHQL(hql,null);*/
    }
	
/*
	
	public List<BuildConfigInfo> getBuildConfigInfoExportList(BuildConfigInfoSearchBean searchBean,String curBranch){
		  List<BuildConfigInfo> list = new ArrayList<BuildConfigInfo>();		
		  String hql = "from BuildConfigInfo bc  left join fetch bc.ri rInfo left join fetch bc.bd where 1=1 and bc.ri.name='"+curBranch+"' " +searchBean.getSerchCondition();				 
		  if("1".equals(searchBean.getIo())){
			  hql += " and bc.status <> '4' ";
		  }
		  hql += " order by " + searchBean.getSort() + " " + searchBean.getOrder();
		  return queryForHQL(hql, searchBean.getSerchParam());
	 }
	*/
	
	public BuildConfigInfo getBuildConfigInfo(long id){
		String hql = " from BuildConfigInfo bc left join fetch bc.ri rInfo left join fetch bc.bd where bc.id = :id  ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){
			return (BuildConfigInfo) list.get(0);
		}else{
			return null;
		}
	}
	
	public List<BuildConfigInfo> getBuildConfigInfoByName(String buildName){
		String hql = " from BuildConfigInfo bc where bc.buildFileName = :buildName  ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("buildName", buildName);
		return this.queryForHQL(hql, params);
	}
	
	public BuildConfigInfo getBuildConfigInfoByNameNoCancel(String buildName,RepInfo ri){
		String hql = " from BuildConfigInfo bc left join fetch bc.ri left join fetch bc.bd where bc.buildFileName = :buildName and bc.status<>'4' and bc.ri.id="+ri.getId();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("buildName", buildName);
		List list = this.queryForHQL(hql, params);
		if(list!=null&&list.size()>0){ 
			return (BuildConfigInfo) list.get(0);
		}else{
			return null;  
		}
	}
	
	public List<BuildConfigInfo> getDependsBuildConfigInfoList(String buildName,Long respInfoId){
		String hql = " from BuildConfigInfo bc left join fetch bc.ri rInfo left join fetch bc.bd where rInfo.id=:respInfoId and bc.buildDepends like :buildName" +
				" and bc.status<>'4' and bc.status<>'5' ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("respInfoId", respInfoId);
		params.put("buildName", "%"+buildName+"%");
		return this.queryForHQL(hql, params);
	}
	
	public void saveBuildConfigInfo(BuildConfigInfo bc){
		saveOrUpdate(bc);
	}
	
	public void delBuildConfigInfo(BuildConfigInfo bc){		
		if(bc!=null){
			this.deleteById(bc.getId());
		}
	}

}
