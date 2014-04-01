package com.easysoft.core.manager.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.manager.IDomainManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.manager.IThemeManager;
import com.easysoft.core.manager.IUserManager;
import com.easysoft.core.model.Dns;
import com.easysoft.core.model.JEAPSiteDomain;
import com.easysoft.core.model.JEAPApp;
import com.easysoft.core.model.JEAPUser;
import com.easysoft.core.model.Site;
import com.easysoft.core.model.Theme;
import com.easysoft.core.solution.ISetupLoader;
import com.easysoft.core.utils.UploadUtil;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.framework.db.IDaoSupport;
import com.easysoft.framework.db.ISqlFileExecutor;
import com.easysoft.framework.db.Page;
import com.easysoft.framework.db.StringMapper;
import com.easysoft.framework.utils.DateUtil;
import com.easysoft.framework.utils.FileUtil;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IAdminUserManager;
import com.easysoft.member.backend.manager.impl.UserUtil;

/**
 * 站点管理
 * 
 * @author andy
 */
@Service("siteManagerImpl")
public class SiteManagerImpl implements ISiteManager {

	private IDaoSupport<Site> daoSupport;
	private IDomainManager domainManager;
	private IThemeManager themeManager;
	private ISqlFileExecutor sqlFileExecutor;
	private IAdminUserManager adminUserManager;
	private IAppManager appManager;
	private ISetupLoader setupLoader;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addDomain(JEAPSiteDomain eopSiteDomain) {
		// UserUtil.validUser(eopSiteDomain.getUserid());
		if (checkInDomain(eopSiteDomain.getDomain())) {
			throw new IllegalArgumentException("域名["
					+ eopSiteDomain.getDomain() + "]已存在！");
		}
		daoSupport.insert("jeap_sitedomain", eopSiteDomain);
		return daoSupport.getLastId("jeap_sitedomain");
	}

	public Boolean checkInDomain(String domain) {
		String sql = "select count(0) from jeap_sitedomain where domain = ?";
		int count = this.daoSupport.queryForInt(sql, domain);
		return count == 0 ? false : true;
	}

	public void deleteDomain(String domain) {
		String sql = "delete from eop_sitedomain where domain=?";
		this.daoSupport.execute(sql, domain);
	}

	public Integer add(JEAPUser user, Site site, String domain) {
		int userid = user.getId();
		site.setUserid(userid);

		if (site.getIcofile() == null)
			site.setIcofile(ParamSetting.IMG_SERVER_DOMAIN
					+ "/images/default/favicon.ico");
		if (site.getLogofile() == null)
			site.setLogofile(ParamSetting.IMG_SERVER_DOMAIN
					+ "/images/default/logo.gif");

		site.setPoint(1000);

		// 2010-09-12新增站点创建时间字段、最后登录时间、最后领取积分字段
		site.setCreatetime(DateUtil.getDatelineLong());
		site.setLastlogin(0); // 未登录过
		site.setLastgetpoint(DateUtil.getDatelineLong()); // 默认创建即获取了积分

		// 添加站点并得到id
		this.daoSupport.insert("jeap_site", site);
		Integer siteid = this.daoSupport.getLastId("jeap_site");

		JEAPSiteDomain eopSiteDomain = new JEAPSiteDomain();
		eopSiteDomain.setDomain(domain);
		eopSiteDomain.setSiteid(siteid);
		eopSiteDomain.setUserid(userid);

		// 为站点添加域名
		this.addDomain(eopSiteDomain);

		return siteid;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer add(Site site, String domain) {

		WebSessionContext<JEAPUser> sessonContext = ThreadContextHolder
				.getSessionContext();
        JEAPUser user = sessonContext
				.getAttribute(IUserManager.USER_SESSION_KEY);
		return this.add(user, site, domain);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDomain(Integer domainid) {
		JEAPSiteDomain domain = domainManager.get(domainid);
		UserUtil.validUser(domain.getUserid());
		int domainCount = this.domainManager.getDomainCount(domain.getSiteid());
		if (domainCount <= 1) {
			throw new RuntimeException("此站点只有一个域名不可删除，删除后将不可访问");
		}
		String sql = "delete from jeap_sitedomain where id=?";
		this.daoSupport.execute(sql, domainid);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		Site site = this.get(id);
		// UserUtil.validUser(site.getUserid());
		Integer userid = site.getUserid();
		Integer siteid = site.getId();

		// 删除表
		String sql = "show tables like '%_" + userid + "_" + siteid + "'";
		List tableList = this.daoSupport.queryForList(sql, new StringMapper());
		for (String tbName : (List<String>) tableList) {
			sql = "drop table if exists " + tbName;
			this.daoSupport.execute(sql);
		}

		// 删除数据信息
		sql = "delete from jeap_sitedomain where siteid = ?";
		this.daoSupport.execute(sql, id);
		sql = "delete from  jeap_site  where id = ?";
		this.daoSupport.execute(sql, id);

		// 删除文件
		String userTplFile = ParamSetting.ESF_PATH + "/user/" + userid + "/"
				+ siteid;
		String userStyleFile = ParamSetting.IMG_SERVER_PATH + "/user/" + userid
				+ "/" + siteid;
		FileUtil.delete(userTplFile);
		FileUtil.delete(userStyleFile);
	}

	/**
	 * 获取DNS列表 即domain -- site的对照关系列表
	 */
	public List getDnsList() {
		String sql = "select a.domain as domain,a.siteid as siteid, b.* from jeap_sitedomain a left join jeap_site b on b.id = a.siteid";
		return this.daoSupport.queryForList(sql, new DnsMapper());

	}

	private static class DnsMapper implements ParameterizedRowMapper<Dns> {

		private String getContext(Site site) {
			if ("2".equals(ParamSetting.RUNMODE)) {
				return ParamSetting.IMG_SERVER_DOMAIN + "/user/"
						+ site.getUserid() + "/" + site.getId();
			} else {
				return ParamSetting.IMG_SERVER_DOMAIN;
			}
		}

		public Dns mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dns dns = new Dns();

			dns.setDomain(rs.getString("domain"));
			Site site = new Site();
			site.setId(rs.getInt("siteid"));
			site.setAdminthemeid(rs.getInt("adminthemeid"));
			site.setThemeid(rs.getInt("themeid"));
			site.setSitename(rs.getString("sitename"));
			site.setUserid(rs.getInt("userid"));
			site.setKeywords(rs.getString("keywords"));
			site.setDescript(rs.getString("descript"));
			site.setThemepath(rs.getString("themepath"));
			site.setPoint(rs.getInt("point"));
			// 20100912新增下面字段 --andy
			site.setCreatetime(rs.getInt("createtime"));
			site.setLastgetpoint(rs.getInt("lastgetpoint"));
			site.setLastlogin(rs.getInt("lastlogin"));
			site.setLogincount(rs.getInt("logincount"));
			// andy add 20101026
			site.setCopyright(rs.getString("copyright"));
			site.setTitle(rs.getString("title"));
			site.setUsername(rs.getString("username"));
			site.setUsertel(rs.getString("usertel"));
			site.setUsermobile(rs.getString("usermobile"));
			site.setUsertel1(rs.getString("usertel1"));
			site.setUseremail(rs.getString("useremail"));
			site.setIcp(rs.getString("icp"));
			site.setAddress(rs.getString("address"));
			site.setZipcode(rs.getString("zipcode"));
			site.setSiteon(rs.getInt("siteon"));
			site.setState(rs.getInt("state"));
			site.setQq(rs.getInt("qq"));
			site.setMsn(rs.getInt("msn"));
			site.setWw(rs.getInt("ww"));
			site.setTel(rs.getInt("tel"));
			site.setWt(rs.getInt("wt"));
			site.setQqlist(rs.getString("qqlist"));
			site.setMsnlist(rs.getString("msnlist"));
			site.setWwlist(rs.getString("wwlist"));
			site.setTellist(rs.getString("tellist"));
			site.setWorktime(rs.getString("worktime"));
			site.setLinkman(rs.getString("linkman"));
			site.setLinktel(rs.getString("linktel"));
			site.setEmail(rs.getString("email"));
			site.setClosereson(rs.getString("closereson"));
			// end of andy add 20101026
			
			
			site.setRelid(rs.getString("relid"));
			site.setImptype(rs.getInt("imptype"));
			site.setSitestate(rs.getInt("sitestate"));
			site.setIsimported(rs.getInt("isimported"));
			
			
			String icofile = rs.getString("icofile");
			
			
			if (icofile != null) {

				icofile = icofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
						getContext(site));

			}
			site.setIcofile(icofile);

			String logofile = rs.getString("logofile");
			if (logofile != null) {
				logofile = logofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
						getContext(site));
			}
			site.setLogofile(logofile);

			String bklogofile = rs.getString("bklogofile");
			if (bklogofile != null) {
				bklogofile = bklogofile.replaceAll(
						ParamSetting.FILE_STORE_PREFIX, getContext(site));
			}
			site.setBklogofile(bklogofile);

			String bkloginpicfile = rs.getString("bkloginpicfile");
			if (bkloginpicfile != null) {
				bkloginpicfile = bkloginpicfile.replaceAll(
						ParamSetting.FILE_STORE_PREFIX, getContext(site));
			}
			site.setBkloginpicfile(bkloginpicfile);

			site.setMulti_site(rs.getInt("multi_site"));
			dns.setSite(site);
			
			
			return dns;
		}

	}

	private String getContext(Site site) {
		if ("2".equals(ParamSetting.RUNMODE)) {
			return ParamSetting.IMG_SERVER_DOMAIN + "/user/" + site.getUserid()
					+ "/" + site.getId();
		} else {
			return ParamSetting.IMG_SERVER_DOMAIN;
		}
	}

	public Site get(Integer id) {
		String sql = "select * from jeap_site where  id = ?";
		Site site = this.daoSupport.queryForObject(sql, Site.class, id);
		String icofile = site.getIcofile();
		if (icofile != null)
			icofile = UploadUtil.replacePath(icofile);
		site.setIcofile(icofile);

		String logofile = site.getLogofile();
		if (logofile != null)
			logofile = logofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					getContext(site));
		site.setLogofile(logofile);

		String bklogofile = site.getBklogofile();
		if (bklogofile != null) {
			bklogofile = bklogofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					getContext(site));
		}
		site.setBklogofile(bklogofile);

		String bkloginpicfile = site.getBkloginpicfile();
		if (bkloginpicfile != null) {
			bkloginpicfile = bkloginpicfile.replaceAll(
					ParamSetting.FILE_STORE_PREFIX, getContext(site));
		}
		site.setBkloginpicfile(bkloginpicfile);
		return site;
	}

	public Site get(String domain) {

		String sql = "select a.* from jeap_site a join jeap_sitedomain b on b.siteid = a.id and b.domain=?";
		List<Site> sitelist = this.daoSupport.queryForList(sql,
				Site.class, domain);
		if (sitelist == null || sitelist.isEmpty())
			return null;
		Site site = sitelist.get(0);
		String icofile = site.getIcofile();
		if (icofile != null)
			icofile = // UploadUtil.replacePath(icofile);
			icofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					ParamSetting.IMG_SERVER_DOMAIN + "/user/" + site.getUserid()
							+ "/" + site.getId());
		site.setIcofile(icofile);

		String logofile = site.getLogofile();
		if (logofile != null) {
			logofile = logofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					getContext(site));
		}
		/*	这部分为上面几行修改之前的代码，在单机运行下会导致logo路径出错 
		if (logofile != null)
			logofile = logofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					ParamSetting.IMG_SERVER_DOMAIN + "/user/" + site.getUserid()
							+ "/" + site.getId());
		// System.out.println(logofile);
		*/
		site.setLogofile(logofile);
		String bklogofile = site.getBklogofile();
		if (bklogofile != null) {
			bklogofile = bklogofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
					getContext(site));
		}
		site.setBklogofile(bklogofile);

		String bkloginpicfile = site.getBkloginpicfile();
		if (bkloginpicfile != null) {
			bkloginpicfile = bkloginpicfile.replaceAll(
					ParamSetting.FILE_STORE_PREFIX, getContext(site));
		}
		site.setBkloginpicfile(bkloginpicfile);
		return site;
	}

	/**
	 * 分页读取所有站点<br>
	 * 可以按关键字搜索
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @param keyword
	 *            关键字:模糊匹配域名站点名，用户名
	 * @return
	 */
	public Page list(String keyword, int pageNo, int pageSize) {
		String sql = "select s.*,u.username from jeap_site  s,jeap_user u where s.userid=u.id";

		if (!StringUtil.isEmpty(keyword)) {
			sql += " and (s.sitename like '%" + keyword + "%' ";
			sql += " or u.username like '%" + keyword + "%'";
			sql += " or s.id in(select siteid from jeap_sitedomain where domain like '%"
					+ keyword + "%') )";
		}
		sql += "  order by createtime desc";

		return this.daoSupport.queryForPage(sql, pageNo, pageSize);
	}

	public Page list(int pageNo, int pageSize, String order, String search) {
		Integer userid = EsfContext.getContext().getCurrentSite().getUserid();
		List<JEAPSiteDomain> listdomain = this.domainManager.listUserDomain();
		if (search == null)
			search = "";
		else
			search = " and sitename like '%" + search + "%'";
		if (order == null)
			order = "";
		else
			order = " order by " + order.replace(":", " ");

		Page page = daoSupport.queryForPage(
				"select * from jeap_site where deleteflag = 0 and userid = "
						+ userid + search + order, pageNo, pageSize);

		List<Map> listsite = (List<Map>) (page.getResult());

		for (Map site : listsite) {
			List<JEAPSiteDomain> domainList = new ArrayList<JEAPSiteDomain>();
			String logofile = site.get("logofile").toString();
			if (logofile != null)
				logofile = logofile.replaceAll(ParamSetting.FILE_STORE_PREFIX,
						ParamSetting.IMG_SERVER_DOMAIN + "/user/"
								+ site.get("userid").toString() + "/"
								+ site.get("id").toString());
			site.put("logofile", logofile);
			for (JEAPSiteDomain siteDomain : listdomain) {
				if (site.get("id").toString().equals(
						siteDomain.getSiteid().toString())) {
					domainList.add(siteDomain);
				}
			}
			site.put("eopSiteDomainList", domainList);
		}

		return page;
	}

	public void edit(Site site) {
		site.setPoint(this.get(site.getId()).getPoint());
		this.daoSupport.update("jeap_site", site, "id = " + site.getId());

	}

	public void setSiteProduct(Integer userid, Integer siteid, String productid) {
		String sql = "update jeap_site set productid=? where userid=? and id=?";
		this.daoSupport.execute(sql, productid, userid, siteid);
	}

	public void changeAdminTheme(Integer themeid) {
		Site site = EsfContext.getContext().getCurrentSite();
		String sql = "update jeap_site set adminthemeid=? where userid=? and id=?";
		this.daoSupport.execute(sql, themeid, site.getUserid(), site.getId());
		EsfContext.getContext().getCurrentSite().setAdminthemeid(themeid);

	}

	public void changeTheme(Integer themeid) {
		Site site = EsfContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(themeid);
		String sql = "update jeap_site set themeid=?,themepath=? where userid=? and id=?";
		this.daoSupport.execute(sql, themeid, theme.getPath(),
				site.getUserid(), site.getId());
		site.setThemeid(themeid);
		site.setThemepath(theme.getPath());
	}

	public List listDomain(Integer userid, Integer siteid) {
		String sql = "select * from jeap_sitedomain where userid=? and siteid=?";
		return this.daoSupport.queryForList(sql, JEAPSiteDomain.class, userid,
				siteid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePoint(Integer id, int point,int sitestate) {
		this.daoSupport.execute("update jeap_site set point=point + ?,sitestate=? where id=?",
				point, sitestate,id);
		this.daoSupport.execute("insert into jeap_pointLog (siteid, point, dateline) values (?, ?, ?)", id, point, DateUtil.getDatelineLong()); 
	}

	/**
	 * 领取积分
	 */
	public int getPoint(Integer id, int point) {
		Site site = EsfContext.getContext().getCurrentSite();
		long lastgetpoint = site.getLastgetpoint();// 上次领取积分的时间
		long now = (int) (System.currentTimeMillis() / 1000); // 当前时间
		int onemonth = 60 * 60 * 24 * 30;
		if (now - lastgetpoint > onemonth) {// 已经有一个月未领取积分
			this.daoSupport.execute(
					"update jeap_site set point=point+? where id=?", point, id); // 更新站点积分
			site.setPoint(site.getPoint() + point);
			site.setLastgetpoint(DateUtil.getDatelineLong()); // 更新上下文中最后领取积分时间
			this.daoSupport.execute(
					"update jeap_site set lastgetpoint=? where id=?", DateUtil
							.getDatelineLong(), id); // 更新数据库中最后领取积分时间

			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 初始化当前站点数据为初厂设置<br>
	 * 方式为执行当前站点的init.sql
	 */
	public void initData() {
		if ("2".equals(ParamSetting.RUNMODE)) {
			// 加载产品的setup.xml文件
			Site site = EsfContext.getContext().getCurrentSite();
			String productId = site.getProductid();
			org.dom4j.Document setupDoc = setupLoader.load(productId);

			String tablenameperfix = "";

			tablenameperfix = "_" + site.getUserid() + "_" + site.getId();

			List listClean = setupDoc.getRootElement().element("clean")
					.elements();
			for (Object o : listClean) {
				org.dom4j.Element table = (org.dom4j.Element) o;
				this.daoSupport.execute("truncate table " + table.getText()
						+ tablenameperfix);
			}
		}

		String sqlPath = ParamSetting.ESF_PATH
				+ EsfContext.getContext().getContextPath() + "/init.sql";
		File file = new File(sqlPath);
		if (file.exists()) {
			String content = FileUtil.read(sqlPath, "UTF-8");

			if ("2".equals(ParamSetting.RUNMODE)) {
				Site site = EsfContext.getContext().getCurrentSite();
				content = content.replaceAll("<userid>", String.valueOf(site
						.getUserid()));
				content = content.replaceAll("<siteid>", String.valueOf(site
						.getId()));
			} else {
				content = content.replaceAll("_<userid>", "");
				content = content.replaceAll("_<siteid>", "");
				content = content.replaceAll("/user/<userid>/<siteid>", "");
				content = content.replaceAll("<userid>", "1");
				content = content.replaceAll("<siteid>", "1");
			}
			try{
				sqlFileExecutor.execute(content);
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("初始化数据出错！");
			}
		} else
			throw new RuntimeException("本站点初始化脚本不存在");

	}

	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}

	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}

	/**
	 * 读取所有站点列表
	 */
	public List<Map> list() {
		String sql = "select * from jeap_site ";
		return this.daoSupport.queryForList(sql);
	}

	/**
	 * 读取某用户的所有站点列表
	 */
	public List<Map> list(Integer userid) {
		String sql = "select * from jeap_site where userid=?";
		List<Map> list = this.daoSupport.queryForList(sql, userid);
		for (Map site : list) {
			List<JEAPSiteDomain> domainList = this.domainManager
					.listSiteDomain(Integer.parseInt(site.get("id").toString()));
			site.put("eopSiteDomainList", domainList);
		}
		return list;
	}

	/**
	 * 得到本站点的应用列表
	 */
	public List<JEAPApp> getSiteApps() {

		if(ParamSetting.RUNMODE.equals("2")){
			List<JEAPApp>   appList  = this.listSaasApp();
			return appList;
		}else{
			List<JEAPApp>   appList  = appManager.list();
			return appList;
		}

	}
	private List<JEAPApp> listSaasApp(){
		List<JEAPApp> appList  = new ArrayList<JEAPApp>();
		// 尝试加载proflie.xml，如果不存在则返回所有app
		String xmlFile = ParamSetting.ESF_PATH + EsfContext.getContext().getContextPath() +"/profile.xml";
		if(new File(xmlFile).exists()){
			try {
			
			    DocumentBuilderFactory factory = 
			    DocumentBuilderFactory.newInstance();
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    Document document = builder.parse(xmlFile);
			    NodeList nodeList=  document.getElementsByTagName("apps");
			   if(nodeList!=null){
				   //通过xml的apps节点生成appList
				   Node appsNode = nodeList.item(0);
				   NodeList appNodeList  = appsNode.getChildNodes();
				   
				   //xml节点和EopApp的转换
				   for(int i=0;i<appNodeList.getLength();i++){
						Node node = appNodeList.item(i);
						if(node.getNodeType() == Node.ELEMENT_NODE){
							Element appNode = (Element)node; //app的xml节点 
							JEAPApp esfApp = new JEAPApp();
							esfApp.setAppid(appNode.getAttribute("id")); //app的id属性
							esfApp.setVersion( appNode.getAttribute("version"));  //app的版本属性
							appList.add(esfApp);
						}
				   }
				 
			   }
			   return appList;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("加载本站点根目录的profile.xml失败，不能导出。");
			} 	 
		}else{
			throw new RuntimeException("本站点根目录下未含有profile.xml，不能导出。");
		}
		
	}
	

	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}

	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}

	public void editDomain(String domainOld, String domainNew) {
		this.daoSupport.execute(
				"update jeap_sitedomain set domain = ? where domain = ?",
				domainNew, domainOld);

	}

	public IDaoSupport<Site> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<Site> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public IAppManager getAppManager() {
		return appManager;
	}

	public void setAppManager(IAppManager appManager) {
		this.appManager = appManager;
	}

	public static void main(String args[]) {
		int one = (int) (DateUtil.toDate("2010-09-14 12:00:01",
				"yyyy-MM-dd HH:mm:ss").getTime() / 1000);
		int two = (int) (DateUtil.toDate("2010-09-14 14:00:01",
				"yyyy-MM-dd HH:mm:ss").getTime() / 1000);
		int three = (int) (DateUtil.toDate("2010-09-15 16:00:01",
				"yyyy-MM-dd HH:mm:ss").getTime() / 1000);

		System.out.println(one / 86400);
		System.out.println(two / 86400);
		System.out.println(three / 86400);

	}

	public ISetupLoader getSetupLoader() {
		return setupLoader;
	}

	public void setSetupLoader(ISetupLoader setupLoader) {
		this.setupLoader = setupLoader;
	}

}
