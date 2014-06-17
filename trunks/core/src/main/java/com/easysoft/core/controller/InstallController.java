package com.easysoft.core.controller;

import com.easysoft.core.common.SysParamSetting;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.manager.impl.InstallManager;
import com.easysoft.framework.ParamSetting;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.framework.db.IDataSourceCreator;
import com.easysoft.framework.db.core.DBConfig;
import com.easysoft.framework.utils.FileUtil;
import com.easysoft.framework.utils.PathUtil;
import com.easysoft.member.backend.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

/**
 * User: andy
 * Date: 14-1-16
 * Time: 下午3:21
 *
 * @since:
 */
@Controller
@RequestMapping({"/install/install"})
public class InstallController extends BaseController{
    @Autowired
    @Qualifier("dynamicDataSourceCreator")
    private IDataSourceCreator dataSourceCreator;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private InstallManager installManager;

    /**
     * 第一步，显示协议
     * @return
     */
    @RequestMapping(params = {"step1"})
    public ModelAndView step1(){
        return new ModelAndView("install/step1");
    }

    /**
     * 数据库配置
     * @return
     */
    @RequestMapping(params = {"step2"})
    public ModelAndView step2(){
        return new ModelAndView("install/step2");
    }
    @RequestMapping(params={"testConnection"})
    @ResponseBody
    public AjaxJson testConnection(DBConfig dbConfig){
        boolean result = false;
        AjaxJson json = new AjaxJson();
        if("mysql".equals(dbConfig.getDbType()))
            result = this.mysqlTestConnection(dbConfig);
        else if("oracle".equals(dbConfig.getDbType()))
            result = this.oracleTestConnection(dbConfig);
        else if("sqlserver".equals(dbConfig.getDbType()))
            result = this.sqlserverTestConnection(dbConfig);

        if(result){
            json.setSuccess(true);
        }else{
            json.setSuccess(false);
        }

        return json;
    }

    /**
     * 保存存数据库设置
     * 切换至新的数据源
     * @return
     */
    @RequestMapping(params = {"step3"})
    public ModelAndView step3(DBConfig dbConfig,int resourcemode,String staticpath,String solutionpath,String staticdomain,HttpServletRequest request){
        saveParams(dbConfig.getDbType(),resourcemode,staticpath,solutionpath,staticdomain,request);
        if("mysql".equals(dbConfig.getDbType()))
            this.saveMysqlDBParams(dbConfig);
        else if("oracle".equals(dbConfig.getDbType()))
            this.saveOracleDBParams(dbConfig);
        else if("sqlserver".equals(dbConfig.getDbType()))
            this.saveSQLServerDBParams(dbConfig);

        Properties props=System.getProperties();
        String osVersion = props.getProperty("os.name")+"("+props.getProperty("os.version")+")";
        String javaVersion = props.getProperty("java.version");
        return new ModelAndView("install/step3");
    }
    /**
     * 保存Oracle数据设置
     */
    private void saveOracleDBParams(DBConfig dbConfig){
        Properties props = new Properties();
        props.setProperty("jdbc.driverClassName", "oracle.jdbc.driver.OracleDriver");
        props.setProperty("jdbc.url", "jdbc:oracle:thin:@" + dbConfig.getDbHost()+ ":" + dbConfig.getDbName());
        props.setProperty("jdbc.username", dbConfig.getUserName());
        props.setProperty("jdbc.password", dbConfig.getPassword());
        props.setProperty("validationQuery.sqlserver", "SELECT 1");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        saveProperties(props);
    }

    /**
     * 保存SQLServer数据设置
     */
    private void saveSQLServerDBParams(DBConfig dbConfig){
        Properties props = new Properties();
        props.setProperty("jdbc.driverClassName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        props.setProperty("jdbc.url", "jdbc:sqlserver://" + dbConfig.getDbHost()+ ";databaseName=" + dbConfig.getDbName());
        props.setProperty("jdbc.username", dbConfig.getUserName());
        props.setProperty("jdbc.password", dbConfig.getPassword());
        saveProperties(props);
    }
    /**
     * 保存mysql数据设置
     */
    private void saveMysqlDBParams(DBConfig dbConfig){
        Properties props = new Properties();
        props.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
        props.setProperty("jdbc.url", "jdbc:mysql://"+dbConfig.getDbHost()+"/"+dbConfig.getDbName()+"?useUnicode=true&characterEncoding=utf8");
        props.setProperty("jdbc.username", dbConfig.getUserName());
        props.setProperty("jdbc.password", dbConfig.getPassword());
        props.setProperty("validationQuery.sqlserver", "SELECT 1");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        saveProperties(props);
    }

    /**
     * 保存到jdbc.properties文件
     * @param props
     */
    private void saveProperties(Properties props){
        try {
            String path = PathUtil.getRootPath("config/jdbc.properties");
            File file  = new File(path);

            props.store(new FileOutputStream(file), "jdbc.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 保存参数配置
     */
    private void saveParams(String dbType,int resourcemode,String staticpath,String solutionpath,String staticdomain,HttpServletRequest request){
        this.prosessPath(staticpath,solutionpath,staticdomain);
        InputStream in = FileUtil.getResourceAsStream("esf.properties");
        String webroot = PathUtil.getRootPath();
        Properties props = new Properties();
        try {
            props.load(in);

            //静态资源合并
            if(resourcemode==2 ){
                props.setProperty("path.imageserver", webroot+"/statics");
                props.setProperty("domain.imageserver", "http://"+ request.getServerName()+":"+request.getLocalPort() + request.getContextPath() +"/statics"  );
            }

            //静态资源分离
            if(resourcemode==1 ){
                try{
                    FileUtil.copyFolder(webroot+"/statics", staticpath);
                    props.setProperty("path.imageserver", staticpath);
                    props.setProperty("domain.imageserver", staticdomain);
                }catch(Exception e){
                    throw new RuntimeException();
                }
            }

            //saas方式可以自定义解决方案所在目录
            if("2".equals(ParamSetting.RUNMODE ) ){
                props.setProperty("storage.app_data", solutionpath+"/commons");
                props.setProperty("storage.products", solutionpath);
            }else{
                props.setProperty("storage.app_data", webroot+"/products/commons");
                props.setProperty("storage.products", webroot+"/products");
            }

            if("mysql".equals(dbType))
                props.setProperty("dbtype", "1");
            else if("oracle".equals(dbType))
                props.setProperty("dbtype", "2");
            else if("sqlserver".equals(dbType))
                props.setProperty("dbtype", "3");
            props.setProperty("storage.EOPServer", webroot);
            props.setProperty("path.context_path", request.getContextPath());

            String path = PathUtil.getRootPath("esf.properties");
            File file  = new File(path);
            props.store(new FileOutputStream(file), "esf.properties");
            ParamSetting.init(props);
            SysParamSetting.init(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理路径最后不要以/结尾
     */
    private void prosessPath(String staticpath,String solutionpath,String staticdomain){
        if(staticpath!=null)
            staticpath = staticpath.endsWith("/")?staticpath.substring(0,staticpath.length()-1): staticpath;

        if(solutionpath!=null)
            solutionpath = solutionpath.endsWith("/")?solutionpath.substring(0,solutionpath.length()-1): solutionpath;


        if(staticdomain!=null)
            staticdomain = staticdomain.endsWith("/")?staticdomain.substring(0,staticdomain.length()-1): staticdomain;
    }
    /**
     * 测试MySQL连接
     * @return
     */

    private boolean mysqlTestConnection(DBConfig dbConfig){
        return createAndTest("com.mysql.jdbc.Driver", "jdbc:mysql://"+dbConfig.getDbHost()+"/test?useUnicode=true&characterEncoding=utf8",dbConfig.getDbType(),dbConfig.getDbHost(),dbConfig.getDbName(),dbConfig.getUserName(),dbConfig.getPassword());
    }
    /**
     * 测试Oracle连接
     * @return
     */

    private boolean oracleTestConnection(DBConfig dbConfig){
        return createAndTest("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@" + dbConfig.getDbHost() + ":" + dbConfig.getDbName(),dbConfig.getDbType(),dbConfig.getDbHost(),dbConfig.getDbName(),dbConfig.getUserName(),dbConfig.getPassword());
    }

    private boolean sqlserverTestConnection(DBConfig dbConfig){
        return createAndTest("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://" + dbConfig.getDbHost() + ";databaseName=" + dbConfig.getDbName(),dbConfig.getDbType(),dbConfig.getDbHost(),dbConfig.getDbName(),dbConfig.getUserName(),dbConfig.getPassword());
    }
    /**
     * 测试数据库连接
     * 和test库建立连接，检测用户试图创建的数据库是否存在，如果不存在则建立相应数据库。
     * 创建后返回相应的数据源给jdbctemplate
     * 然后进行一个数据库操作测试以证明数据库建立并连接成功
     * @return
     */
    private boolean createAndTest(String driver,String url,String dbtype,String dbhost,String dbname,String uname,String pwd){
        try{
            DataSource newDataSource = this.dataSourceCreator.createDataSource(driver,url,uname,pwd);

            if("mysql".equals(dbtype)) {	//	只有MySQL尝试建库
                this.jdbcTemplate.setDataSource(newDataSource);
                this.jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS `" + dbname +"` DEFAULT CHARACTER SET UTF8");
                newDataSource = this.dataSourceCreator.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://"+dbhost+"/"+dbname+"?useUnicode=true&characterEncoding=utf8", uname, pwd);
                this.jdbcTemplate.execute("use "+ dbname);
            }

            this.dataSource= newDataSource;
            this.jdbcTemplate.setDataSource(newDataSource);
            this.jdbcTemplate.execute("CREATE TABLE JAVAMALLTESTTABLE (ID INT not null)");
            this.jdbcTemplate.execute("DROP TABLE JAVAMALLTESTTABLE");

            return true;

        }catch(RuntimeException e){
            e.printStackTrace();
            this.logger.error(e.fillInStackTrace());
            return false;
        }
    }

    @RequestMapping(params={"testReady"})
    @ResponseBody
    public AjaxJson testReady(DBConfig config){
        AjaxJson json = new AjaxJson();
        try{
            if("mysql".equals(config.getDbType()))
                this.jdbcTemplate.execute("drop table if exists test");


        }catch(RuntimeException e){
            json.setSuccess(false);
        }

        return json;
    }
    @RequestMapping(params = {"doInstall"})
    @ResponseBody
    public AjaxJson doInstall(AdminUser adminUser,String productid,String domain){
        AjaxJson json = new AjaxJson();
        try{
            //saas模式可以自定义域名
            if("2".equals(ParamSetting.RUNMODE)){
                installManager.install(adminUser.getUsername(),adminUser.getPassword(),domain,productid);
            }else{
                installManager.install(adminUser.getUsername(),adminUser.getPassword(),"localhost",productid);
            }

        }catch (RuntimeException e) {
            e.printStackTrace();
            json.setSuccess(false);
        }
        return json;
    }
    @RequestMapping(params = {"installSuccess"})
    public ModelAndView installSuccess(){
        FileUtil.write(ParamSetting.ESF_PATH + "/install/install.lock", "如果要重新安装，请删除此文件，并重新起动web容器");
        ParamSetting.INSTALL_LOCK ="yes";
        return new ModelAndView("install/success");
    }
}
