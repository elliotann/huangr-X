package com.easysoft.member.backend.manager.impl;


import com.easysoft.member.backend.dao.IOrganizatiOnDao;
import com.easysoft.member.backend.manager.OrganizatiOnServiceI;
import com.easysoft.member.backend.model.Menu;
import com.easysoft.member.backend.model.OrganizatiOnEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("organizatiOnService")
@Transactional
public class OrganizatiOnServiceImpl implements OrganizatiOnServiceI {
    @Autowired
    private IOrganizatiOnDao organizatiOnDao;

 	
 	public void save(OrganizatiOnEntity organizatiOn) {
        organizatiOnDao.save(organizatiOn);
 	}
    public void update(OrganizatiOnEntity organizatiOn){
        organizatiOnDao.update(organizatiOn);
    }
    public List<OrganizatiOnEntity> queryForList(){
        return organizatiOnDao.queryForList();
    }

    @Override
    public List<OrganizatiOnEntity> queryForTree(int orgid) {
        List<OrganizatiOnEntity> orgList  = this.queryForList();
        List<OrganizatiOnEntity> topOrgList  = new ArrayList<OrganizatiOnEntity>();
        for(OrganizatiOnEntity org :orgList){
            if(org.getPid().compareTo(orgid)==0){
                List<OrganizatiOnEntity> children = this.getChildren(orgList, org.getId());
                org.setChildren(children);
                topOrgList.add(org);
            }
        }
        return topOrgList;
    }

    /**
     * 在一个集合中查找子
     * @param menuList 所有菜单集合
     * @param parentid 父id
     * @return 找到的子集合
     */
    private List<OrganizatiOnEntity> getChildren(List<OrganizatiOnEntity> menuList ,Integer parentid){
        List<OrganizatiOnEntity> children =new ArrayList<OrganizatiOnEntity>();
        for(OrganizatiOnEntity menu :menuList){
            if(menu.getPid().compareTo(parentid)==0){
                menu.setChildren(this.getChildren(menuList, menu.getId()));
                children.add(menu);
            }
        }
        return children;
    }



    public OrganizatiOnEntity queryById(Integer id){
        return organizatiOnDao.queryById(id);
    }
    public void deleteById(Integer id){
        organizatiOnDao.deleteById(id);
    }


}