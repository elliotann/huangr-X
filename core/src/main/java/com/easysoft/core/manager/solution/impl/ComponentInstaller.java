package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.manager.IComponentManager;
import com.easysoft.framework.db.dbsolution.IInstaller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created with IntelliJ IDEA.
 * User: huangxa
 * Date: 13-6-28
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
@Service("componentInstaller")
@Scope("prototype")
public class ComponentInstaller implements IInstaller {
    private IComponentManager componentManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void install(String productId, Node fragment) {
        NodeList componentList = fragment.getChildNodes();
        int length = componentList.getLength();

        for (int i = 0; i < length; i++) {
            Node node = componentList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element componentEl = (Element) node;
                String componentid = componentEl.getAttribute("id");
                this.componentManager.install(componentid);
                this.componentManager.start(componentid);
            }
        }
    }

    public IComponentManager getComponentManager() {
        return componentManager;
    }

    public void setComponentManager(IComponentManager componentManager) {
        this.componentManager = componentManager;
    }
}
