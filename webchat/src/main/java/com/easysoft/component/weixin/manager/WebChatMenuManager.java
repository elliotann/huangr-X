package com.easysoft.component.weixin.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.weixin.dao.IWebChatMenuDao;
import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.component.weixin.vo.Button;
import com.easysoft.component.weixin.vo.CommonButton;
import com.easysoft.component.weixin.vo.ComplexButton;
import com.easysoft.component.weixin.vo.ViewButton;
import com.easysoft.component.weixin.vo.WeiXinMenu;
import com.easysoft.framework.exception.ErrorCode;
import com.easysoft.framework.utils.JsonUtils;

@Service
@Transactional
public class WebChatMenuManager implements IWebChatMenuManager {
	private enum WebChatMenuError{
		@ErrorCode(comment="一级菜单数量不能多于3个")
		ROOT_SIZE_INVLID
	}
	@Autowired
	private IWebChatMenuDao webChatMenuDao;
	public List<WebChatMenu> getAll() {
		List<WebChatMenu> webChatMenus = webChatMenuDao.queryForList();
		//查询一级菜单
		List<WebChatMenu> rootMenus = new ArrayList<WebChatMenu>();
		for(WebChatMenu tempMenu:webChatMenus){
			if(tempMenu.getParent()==null){
				rootMenus.add(tempMenu);
			}
		}
		
		//查找二级菜单
		for(WebChatMenu tempMenu:webChatMenus){
			for(WebChatMenu rootMenu :rootMenus){
				if(tempMenu.getParent()!=null&&tempMenu.getParent().getId()==rootMenu.getId()){
					rootMenu.addChild(tempMenu);
				}
			}
		}
		//查找三级菜单
		for(WebChatMenu tempMenu:webChatMenus){
			for(WebChatMenu rootMenu :rootMenus){
				if(rootMenu.getChildren()==null) continue;
				for(WebChatMenu thirdMenu : rootMenu.getChildren()){
					if(tempMenu.getParent()!=null&&tempMenu.getParent().getId()==thirdMenu.getId()){
						thirdMenu.addChild(tempMenu);
					}
				}
				
			}
		}
		return rootMenus;
		
		
	}

	public void add(WebChatMenu webChatMenu) {
		webChatMenuDao.save(webChatMenu);
		
	}

	public String syncMenu(String[] menuIds) {
		List<WebChatMenu> webChatMenus = getMemuByIds(menuIds);
		//查询一级菜单
		List<WebChatMenu> rootMenus = new ArrayList<WebChatMenu>();
		for(WebChatMenu tempMenu:webChatMenus){
			if(tempMenu.getParent()==null){
				rootMenus.add(tempMenu);
			}
		}
		if(rootMenus.size()>3){
			throw new WebChatManagerException(WebChatMenuError.ROOT_SIZE_INVLID);
		}
		//查找二级菜单
		for(WebChatMenu tempMenu:webChatMenus){
			for(WebChatMenu rootMenu :rootMenus){
				if(tempMenu.getParent()!=null&&tempMenu.getParent().getId()==rootMenu.getId()){
					rootMenu.addChild(tempMenu);
				}
			}
		}
		//查找三级菜单
		for(WebChatMenu tempMenu:webChatMenus){
			for(WebChatMenu rootMenu :rootMenus){
				if(rootMenu.getChildren()==null) continue;
				for(WebChatMenu thirdMenu : rootMenu.getChildren()){
					if(tempMenu.getParent()!=null&&tempMenu.getParent().getId()==thirdMenu.getId()){
						thirdMenu.addChild(tempMenu);
					}
				}
				
			}
		}
		WeiXinMenu weiXinMenu = new WeiXinMenu();
		List<Button> buttons = new ArrayList<Button>();
		for(WebChatMenu menu : rootMenus){
			if(menu.getChildren().size()<=0){
				if("view".equals(menu.getType())){
					ViewButton button = new ViewButton();
					button.setName(menu.getName());
					button.setType(menu.getType());
					button.setUrl(menu.getUrl());
					buttons.add(button);
				}else if("click".equals(menu.getType())){
					CommonButton button = new CommonButton();
					button.setName(menu.getName());
					button.setKey(menu.getKey());
					button.setType(menu.getType());
					buttons.add(button);
				}
			}else{
				ComplexButton parentbutton = new ComplexButton();
				parentbutton.setName(menu.getName());
				for(WebChatMenu child : menu.getChildren()){
					if("view".equals(child.getType())){
						ViewButton button = new ViewButton();
						button.setName(child.getName());
						button.setType(child.getType());
						button.setUrl(child.getUrl());
						parentbutton.addButton(button);
					}else if("click".equals(child.getType())){
						CommonButton button = new CommonButton();
						button.setName(child.getName());
						button.setKey(child.getKey());
						button.setType(child.getType());
						parentbutton.addButton(button);
					}
				}
				buttons.add(parentbutton);
			}
			
		}
		weiXinMenu.setButton(buttons);
		return JsonUtils.beanToJson(weiXinMenu);
	}

	public WebChatMenu get(Integer id) {
		
		return webChatMenuDao.queryById(id);
	}

	public void edit(WebChatMenu webChatMenu) {
		webChatMenuDao.update(webChatMenu);
	}

	private List<WebChatMenu> getMemuByIds(String[] menuIds) {
		List<WebChatMenu> results = new ArrayList<WebChatMenu>();
		for(String menuId : menuIds){
			results.add(this.get(Integer.parseInt(menuId)));
		}
		return results;
	}

	public List<WebChatMenu> getMenuTree(Integer parentId) {
		List<WebChatMenu> treeMenus = webChatMenuDao.queryMenusByPid(parentId);
		for(WebChatMenu rootMenu : treeMenus){
			List<WebChatMenu> children = webChatMenuDao.queryMenusByPid(rootMenu.getId());
			if(!children.isEmpty()){
				rootMenu.setChildren(children);
			}
		}
		return treeMenus;
	}


}
