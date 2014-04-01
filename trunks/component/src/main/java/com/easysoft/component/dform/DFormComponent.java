package com.easysoft.component.dform;

import com.easysoft.core.manager.IMenuManager;
import com.easysoft.member.backend.model.Menu;
import com.easysoft.framework.component.IComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 动态表单组件
 * User: andy
 * Date: 13-8-11
 *
 * @since:
 */
@Component
public class DFormComponent implements IComponent {
    @Autowired
    private IMenuManager menuManager;
    @Override
    public void install() {
        //安装菜单
        Menu pmenu = new Menu();
        pmenu.setTitle("表单配置");
        pmenu.setUrl("");
        pmenu.setPid(32);
        pmenu.setMenutype(2);
        pmenu.setSorder(50);
        pmenu.setSelected(0);
        pmenu.setDeleteflag(0);
        Integer pid = menuManager.add(pmenu);

        Menu menu = new Menu();
        menu.setTitle("动态表单");
        menu.setUrl("/form/config/dFormController.do?formList");
        menu.setPid(pid);
        menu.setMenutype(2);
        menu.setSorder(50);
        menu.setSelected(0);
        menu.setDeleteflag(0);
        menuManager.add(menu);
    }

    @Override
    public void unInstall() {

    }
}
