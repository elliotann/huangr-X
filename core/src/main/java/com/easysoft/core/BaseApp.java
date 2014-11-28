package com.easysoft.core;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.solution.impl.SqlExportService;
import com.easysoft.core.model.Site;
import com.easysoft.framework.cache.CacheFactory;
import com.easysoft.framework.db.IDBRouter;
import com.easysoft.framework.db.ISqlFileExecutor;
import com.easysoft.jeap.sdk.App;
import org.dom4j.Document;
import org.springframework.stereotype.Service;

/**
 * base应用
 * 
 * @author andy
 */
@Service("base")
public class BaseApp extends App {
    private IDBRouter baseDBRouter;
    private SqlExportService sqlExportService;
    private ISqlFileExecutor sqlFileExecutor;

    public BaseApp()
    {
        this.tables.add("adv");
        this.tables.add("access");
        this.tables.add("adcolumn");
        this.tables.add("admintheme");
        this.tables.add("auth_action");
        this.tables.add("friends_link");
        this.tables.add("guestbook");
        this.tables.add("menu");
        this.tables.add("theme");
        this.tables.add("themeuri");

        this.tables.add("adminuser");
        this.tables.add("role");
        this.tables.add("role_auth");
        this.tables.add("settings");
        this.tables.add("site_menu");
        this.tables.add("user_role");
        this.tables.add("smtp");
    }

    public String getId()
    {
        return "base";
    }

    public String getName() {
        return "base应用";
    }

    public String getNameSpace() {
        return "/core";
    }

    public void saasInstall()
    {
        this.baseDBRouter.doSaasInstall("file:com/easysoft/core/base.xml");
    }

    @Deprecated
    public String dumpSql()
    {
        return "";
    }

    public String dumpSql(Document setup) {
        StringBuffer sql = new StringBuffer();

        sql.append(this.sqlExportService.dumpSql(this.tables, "clean", setup));
        sql.append(createBaseAppSql());
        return sql.toString();
    }

    private String createBaseAppSql()
    {
        Site site = EsfContext.getContext().getCurrentSite();
        String logofile = site.getLogofile();
        String icofile = site.getIcofile();
        String upath = ParamSetting.IMG_SERVER_DOMAIN + EsfContext.getContext().getContextPath();

        if (icofile != null) {
            icofile = icofile.replaceAll(upath, "fs:");
        }
        if (logofile != null) {
            logofile = logofile.replaceAll(upath, "fs:");
        }
        String sql = "update jeap_site set sitename='" + site.getSitename() + "',logofile='" + logofile + "',icofile='" + icofile + "',keywords='" + site.getKeywords() + "',descript='" + site.getDescript() + "' where userid=<userid> and id=<siteid>;\n";

        return sql;
    }

    public void install()
    {
        doInstall("file:com/easysoft/core/base.xml");
    }

    protected void cleanCache() {
        super.cleanCache();

        CacheFactory.getCache("widgetCache").remove("widget_" + this.userid + "_" + this.siteid);

        CacheFactory.getCache("sitemap").remove("sitemap_" + this.userid + "_" + this.siteid);

        CacheFactory.getCache("themeUriCache").remove("theme_uri_list_" + this.userid + "_" + this.siteid);

        CacheFactory.getCache("siteMenuList").remove("siteMenuList_" + this.userid + "_" + this.siteid);
    }

    public void sessionDestroyed(String seesionid, Site site)
    {
    }

    public IDBRouter getBaseDBRouter()
    {
        return this.baseDBRouter;
    }

    public void setBaseDBRouter(IDBRouter baseDBRouter) {
        this.baseDBRouter = baseDBRouter;
    }

    public IDBRouter getBaseSaasDBRouter() {
        return this.baseDBRouter;
    }

    public void setBaseSaasDBRouter(IDBRouter baseSaasDBRouter) {
        this.baseDBRouter = baseSaasDBRouter;
    }

    public ISqlFileExecutor getSqlFileExecutor() {
        return this.sqlFileExecutor;
    }

    public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
        this.sqlFileExecutor = sqlFileExecutor;
    }

    public SqlExportService getSqlExportService() {
        return this.sqlExportService;
    }

    public void setSqlExportService(SqlExportService sqlExportService) {
        this.sqlExportService = sqlExportService;
    }

}
