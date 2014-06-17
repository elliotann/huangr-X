package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.model.Site;
import com.easysoft.framework.db.dbsolution.IInstaller;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * User: andy
 * Date: 13-8-15
 * Time: 下午12:52
 *
 * @since:
 */
@Service("siteInstaller")
@Scope("prototype")
public class SiteInstaller
        implements IInstaller
{
    private ISiteManager siteManager;

    private boolean setProperty(Site site, String name, String value)
    {
        try
        {
            BeanUtils.setProperty(site, name, value);
            return true; } catch (Exception e) {
        }
        return false;
    }

    public void install(String productId, Node fragment)
    {
        Site site = EsfContext.getContext().getCurrentSite();

        NodeList nodeList = fragment.getChildNodes();
        int i = 0; for (int len = nodeList.getLength(); i < len; i++) {
        Node node = nodeList.item(i);
        if (node.getNodeType() == 1) {
            Element element = (Element)node;
            String name = element.getAttribute("name");
            String value = element.getAttribute("value");
            setProperty(site, name, value);
        }
    }
        this.siteManager.edit(site);
    }

    public ISiteManager getSiteManager() {
        return this.siteManager;
    }

    public void setSiteManager(ISiteManager siteManager) {
        this.siteManager = siteManager;
    }
}