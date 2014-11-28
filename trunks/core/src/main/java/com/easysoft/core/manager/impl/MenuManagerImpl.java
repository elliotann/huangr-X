package com.easysoft.core.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.dao.IMenuDao;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.member.backend.manager.IFunAndOperManager;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.FunAndOper;
import com.easysoft.member.backend.model.Menu;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author andy
 */
@Service("menuManager")
public class MenuManagerImpl extends BaseSupport<Menu> implements IMenuManager {
    @Autowired
    private IMenuDao menuDao;

    @Autowired
    private IFunAndOperManager funAndOperManager;
    @Autowired
    private IRoleAuthManager roleAuthManager;
	public void clean() {
		this.baseDaoSupport.execute("truncate table menu");
	}
	public List<Menu> getMenuList() {
		return this.baseDaoSupport.queryForList("select * from menu where deleteflag = '0' order by sorder asc", Menu.class);
	}

	
	public Integer add(Menu menu) {
		if(menu.getTitle()==null) throw new IllegalArgumentException("title argument is null");
		if(menu.getPid()==null) throw new IllegalArgumentException("pid argument is null");
		if(menu.getUrl() ==null) throw new IllegalArgumentException("url argument is null");
		if(menu.getSorder() ==null) throw new IllegalArgumentException("sorder argument is null");
		menu.setDeleteflag(0);
        menuDao.save(menu);
		return menu.getId();
	}


	public List<Menu> getMenuTree(Integer menuid) {
        return getMenuTree(menuid,null);
	}

    
    public List<Menu> getMenuTree(Integer menuid, Integer roleId) {
        if(roleId==null||0==roleId.intValue()){
            if(menuid==null)throw new IllegalArgumentException("menuid argument is null");
            List<Menu> menuList  = this.getMenuList();
            List<Menu> topMenuList  = new ArrayList<Menu>();
            for(Menu menu :menuList){
                if(menu.getPid().compareTo(menuid)==0){
                    List<Menu> children = this.getChildren(menuList, menu.getId());
                    menu.setChildren(children);
                    topMenuList.add(menu);
                }
            }
            return topMenuList;
        }else{
            if(menuid==null)throw new IllegalArgumentException("menuid argument is null");
            List<Menu> menuList  = this.getMenuList();
            List<Menu> topMenuList  = new ArrayList<Menu>();
            //获取权限菜单
            List<Menu> selectMenus = this.getMenuTreeByRoleId(roleId);
            if(!selectMenus.isEmpty()){
                for(Menu menu : menuList){
                    for(Menu selectMenu : selectMenus){
                        if(menu.getId()==selectMenu.getId()){

                            menu.setChecked(true);
                        }
                    }
                }
            }
            for(Menu menu :menuList){
                if(menu.getPid().compareTo(menuid)==0){
                    List<Menu> children = this.getChildren(menuList, menu.getId());
                    menu.setChildren(children);
                    topMenuList.add(menu);
                }
            }
            return topMenuList;
        }

    }

    /**
	 * 在一个集合中查找子
	 * @param menuList 所有菜单集合
	 * @param parentid 父id
	 * @return 找到的子集合
	 */
	private List<Menu> getChildren(List<Menu> menuList ,Integer parentid){
		List<Menu> children =new ArrayList<Menu>();
		for(Menu menu :menuList){
			if(menu.getPid().compareTo(parentid)==0){
			 	menu.setChildren(this.getChildren(menuList, menu.getId()));
				children.add(menu);
			}
		}
		return children;
	}


	public Menu get(Integer id) {
		if(id==null)throw new IllegalArgumentException("ids argument is null");
		String sql ="select * from menu where id=?";
		return this.baseDaoSupport.queryForObject(sql, Menu.class, id);
	}


	public void edit(Menu menu) {
		if(menu.getId()==0) throw new IllegalArgumentException("id argument is null");
		if(menu.getTitle()==null) throw new IllegalArgumentException("title argument is null");
		if(menu.getPid()==null) throw new IllegalArgumentException("pid argument is null");
		if(menu.getUrl() ==null) throw new IllegalArgumentException("url argument is null");
		if(menu.getSorder() ==null) throw new IllegalArgumentException("sorder argument is null");
		menu.setDeleteflag(0);
		menuDao.update(menu);
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSort(Integer[] ids, Integer[] sorts) {
		if(ids==null)throw new IllegalArgumentException("ids argument is null");
		if(sorts==null)throw new IllegalArgumentException("sorts argument is null");
		if(sorts.length !=ids.length) throw new IllegalArgumentException("ids's length and sorts's length not same");
		for(int i=0;i<ids.length;i++){
			String sql ="update menu set sorder=? where id=?";
			this.baseDaoSupport.execute(sql, sorts[i],ids[i]);
		}
	}


	public void delete(Integer id) throws RuntimeException {
		if(id==null)throw new IllegalArgumentException("ids argument is null");
		String sql  ="select count(0) from menu where pid=?";
		int count  = this.baseDaoSupport.queryForInt(sql, id);
		if(count>0) throw new RuntimeException("菜单"+ id +"存在子类别,不能直接删除，请先删除其子类别。");
		sql ="delete from menu where id=?";
		this.baseDaoSupport.execute(sql, id);
	}

    public Menu get(String title) {
        String sql = "select * from menu where title=?";
        List menuList = this.baseDaoSupport.queryForList(sql, Menu.class, new Object[]{title});

        if (menuList.isEmpty()) return null;
        return (Menu) menuList.get(0);
    }

    
    public Menu getMenuByNameAndUrl(String title, String url) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("title",title);
        params.put("url",url);
        return menuDao.queryMenuByCondition(params);
    }

    public void delete(String title) {
        String sql = "delete from menu where title=?";
        this.baseDaoSupport.execute(sql, new Object[]{title});
    }

    
    public List<Menu> getMenuTreeByRoleId(Integer roleId) {
        List<Menu> results = new ArrayList<Menu>();
        List<RoleAuth> roleAuths = roleAuthManager.queryRoleAuthListByRoleId(roleId);
        for(RoleAuth roleAuth : roleAuths){
            results.add(this.get(roleAuth.getFunId()));
        }

        return results;
    }

    
    public void deleteMenuByNameAndUrl(String title, String url) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("title",title);
        params.put("url",url);
        menuDao.deleteMenuByCondition(params);
    }
}
