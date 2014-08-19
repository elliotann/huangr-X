package com.easysoft.core.manager.solution.impl;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.manager.solution.InstallerFactory;
import com.easysoft.core.model.JEAPProduct;
import com.easysoft.core.model.Site;
import com.easysoft.core.solution.IProfileLoader;
import com.easysoft.core.solution.ISetupLoader;
import com.easysoft.core.solution.ISolutionInstaller;
import com.easysoft.core.solution.factory.DBSolutionFactory;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.db.IDaoSupport;
import com.easysoft.framework.db.dbsolution.IDBSolution;
import com.easysoft.framework.db.dbsolution.IInstaller;
import com.easysoft.framework.spring.SpringContextHolder;
import com.easysoft.framework.utils.FileUtil;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * 解决方案安装器实现
 *
 * @author andy
 */
@Service("solutionInstaller")
public class SolutionInstaller implements ISolutionInstaller {
    protected final Logger logger = Logger.getLogger(getClass());
    private IDaoSupport<JEAPProduct> daoSupport;
    private ISiteManager siteManager;
    private IProfileLoader profileLoader;
    private InstallerFactory installerFactory;
    private ISetupLoader setupLoader;

    @Transactional(propagation = Propagation.REQUIRED)
    public void install(Integer userid, Integer siteid, String productId) {

        // 将对应的productid写入到jeap_site表的productid字段中
        if (!productId.toUpperCase().equals("core")
                && !productId.startsWith("temp_")) {
            siteManager.setSiteProduct(userid, siteid, productId);
        }

        final String[] types = {InstallerFactory.TYPE_APP,
                InstallerFactory.TYPE_MENU, InstallerFactory.TYPE_ADMINTHEME,
                InstallerFactory.TYPE_THEME, InstallerFactory.TYPE_URL,
                InstallerFactory.TYPE_WIDGET, InstallerFactory.TYPE_INDEX_ITEM, InstallerFactory.TYPE_SITE};

        org.w3c.dom.Document proFileDoc = this.profileLoader.load(productId);

        for (String type : types) {
            install(type, proFileDoc, productId);
        }

        org.dom4j.Document setupDoc = this.setupLoader.load(productId);

        if (this.logger.isDebugEnabled()) {
            this.logger.debug("execute setup.xml...");
        }

        String tablenameperfix = "";
        if ("2".equals(ParamSetting.RUNMODE)) {
            Site site = EsfContext.getContext().getCurrentSite();
            tablenameperfix = "_" + site.getUserid() + "_" + site.getId();
        }

        List listRecreate = setupDoc.getRootElement().element("recreate").elements();

        IDBSolution dbsolution = DBSolutionFactory.getDBSolution();
        Connection conn = null;
        try {
            conn = DBSolutionFactory.getConnection(null);
            dbsolution.setConnection(conn);
            for (Iterator i$ = listRecreate.iterator(); i$.hasNext(); ) {
                Object o = i$.next();
                Element table = (Element) o;
                dbsolution.dropTable(table.getText() + tablenameperfix);
            }


            IInstaller installer = (IInstaller) SpringContextHolder.getBean("exampleDataInstaller");

            installer.install(productId, null);

            installer = (IInstaller) SpringContextHolder.getBean("adminUserInstaller");
            installer.install(productId, null);

            install(InstallerFactory.TYPE_COMPONENT, proFileDoc, productId);

            if (ParamSetting.RUNMODE.equals("2")) {
                if (!"base".equals(productId))
                    FileUtil.copyFile(ParamSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/profile.xml", ParamSetting.ESF_PATH + EsfContext.getContext().getContextPath() + "/profile.xml");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
    }

    private void install(String type, org.w3c.dom.Document proFileDoc, String productId) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("install [" + type + "]");
        }

        NodeList nodeList = proFileDoc.getElementsByTagName(type);
        if ((nodeList == null) || (nodeList.getLength() <= 0)) {
            return;
        }
        if (nodeList != null) {
            IInstaller installer = this.installerFactory.getInstaller(type);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("user installer [" + installer + "]");
            }
            installer.install(productId, nodeList.item(0));
        }
    }

    public IDaoSupport<JEAPProduct> getDaoSupport() {
        return daoSupport;
    }

    public void setDaoSupport(IDaoSupport<JEAPProduct> daoSupport) {
        this.daoSupport = daoSupport;
    }

    public ISiteManager getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(ISiteManager siteManager) {
        this.siteManager = siteManager;
    }

    public IProfileLoader getProfileLoader() {
        return profileLoader;
    }

    public void setProfileLoader(IProfileLoader profileLoader) {
        this.profileLoader = profileLoader;
    }

    public InstallerFactory getInstallerFactory() {
        return installerFactory;
    }

    public void setInstallerFactory(InstallerFactory installerFactory) {
        this.installerFactory = installerFactory;
    }

    public ISetupLoader getSetupLoader() {
        return setupLoader;
    }

    public void setSetupLoader(ISetupLoader setupLoader) {
        this.setupLoader = setupLoader;
    }

    public Logger getLogger() {
        return logger;
    }

}
