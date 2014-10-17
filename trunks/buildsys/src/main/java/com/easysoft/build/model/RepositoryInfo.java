 /*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.build.model;



 import com.easysoft.build.utils.SVNUtil;

 import java.io.File;
 import java.util.*;

 public class RepositoryInfo
 {
   private String name;
   private String svnUrl;
   private String svnUser;
   private String svnPassword;
   private String tagUrl;
   private String svnRoot;
   private List<String> projects;
   private File branchRoot;
   private File buildDir;
   private File buildSrcDir;
   private File deployDir;
   private File deployBackupDir;
   private File compileLibDir;
   private File compileClassDir;
   private File workspace;
   private String versionNo;
   private String versionPattern;
   private String versionSuffix;
   public Set<String> testUsers;
   public Set<String> sss;
   public Set<String> deployUsers;
   public Map<String, String> userAuthInfo;
   private File versionInfo;
   private String sqlReposRoot;
   private String sqlEncoding;
   private String[] sqlSuffixs;
   private File disPatchDownLoadDir;    

   private String spNo;
   private String createDate;  
   private String band;
   private String permitBand; 
   private String permitPatch;
   private String mainVersion;
   private String subVersion;

   private String jdkVersion;
   private String srcEncoding;//源码编码信息
   private String isWeekbug;//是否周BUG线,默认否,0否，1是


public RepositoryInfo()
   {
     this.projects = new ArrayList();
 
     this.versionNo = "";
 
     this.versionPattern = "yyMMdd";
 
     this.versionSuffix = "";
 
     this.testUsers = new HashSet();
     
     this.sss = new HashSet();
 
     this.deployUsers = new HashSet();
 
     this.userAuthInfo = new HashMap();
 
     this.sqlReposRoot = "/sql/patch/";
 
     this.sqlEncoding = "UTF-8";
 
     this.sqlSuffixs = new String[] { "_SQL2005.sql", "_ORCL.sql" };
     
     this.jdkVersion = "1.6"; //default jdk version
     
     this.srcEncoding = "UTF-8";//default
   }
 
   public String[] getProjects()
   {
     if (this.projects.size() == 0) {
       String[] ps = SVNUtil.listSVNProjects(this.getName());
       for (String p : ps) {
         if (!"sql".equalsIgnoreCase(p)) {
           this.projects.add(p);
         }
       }
     }
 
     return (String[])(String[])this.projects.toArray(new String[this.projects.size()]);
   }
 
   public void addProject(String project) {
     project = project.trim();
     if (!this.projects.contains(project))
       this.projects.add(project);
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getSvnUrl() {
     return this.svnUrl;
   }
 
   public void setSvnUrl(String svnUrl) {
     if (!svnUrl.endsWith("/"))
       svnUrl = svnUrl + "/";
     this.svnUrl = svnUrl;
   }
 
   public String getTagUrl() {
     if (this.tagUrl == null)
       this.tagUrl = this.svnUrl.replaceAll("branches", "tags");
     return this.tagUrl;
   }
 
   public void setTagUrl(String tagUrl) {
     if ((tagUrl != null) && (!tagUrl.endsWith("/")))
       tagUrl = tagUrl + "/";
     this.tagUrl = tagUrl;
   }
 
   public File getBuildDir() {
     if (this.buildDir == null)
       this.buildDir = new File(this.branchRoot, "build");
     return this.buildDir;
   }
		   public File getBuildSrcDir() {
		     if (this.buildSrcDir == null)
		       this.buildSrcDir = new File(this.branchRoot, "buildSrc");
		     return this.buildSrcDir;
		   }
 
   public File getPrivateDir()
   {
     return new File(this.branchRoot, "private");
   }
 
   public void setBuildDir(File buildDir) {
     this.buildDir = buildDir;
   }

    public void setBuildSrcDir(File buildSrcDir) {
      this.buildSrcDir = buildSrcDir;
    }
 
   public File getDeployDir() {
     if (this.deployDir == null)
       this.deployDir = new File(this.branchRoot, "deploy");
     return this.deployDir;
   }
 
   public void setDeployDir(File deployDir) {
     this.deployDir = deployDir;
   }
 
   public void setProjects(List<String> projects) {
     this.projects = projects;
   }
 
   public File getCompileLibDir() {
     if (this.compileLibDir == null)
       this.compileLibDir = new File(this.branchRoot, "lib");
     return this.compileLibDir;
   }
 
   public void setCompileLibDir(File compileLibDir) {
     this.compileLibDir = compileLibDir;
   }
 
   public File getCompileClassDir() {
     if (this.compileClassDir == null)
       this.compileClassDir = new File(this.branchRoot, "classes");
     return this.compileClassDir;
   }
 
   public void setCompileClassDir(File compileClassDir) {
     this.compileClassDir = compileClassDir;
   }
 
   public File getDeployBackupDir() {
     if (this.deployBackupDir == null)
       this.deployBackupDir = new File(this.branchRoot, "deployBack");
     return this.deployBackupDir;
   }
 
   public void setDeployBackupDir(File deployBackupDir) {
     this.deployBackupDir = deployBackupDir;
   }
 
   public File getWorkspace() {
     if (this.workspace == null)
       this.workspace = new File(this.branchRoot, "workspace");
     return this.workspace;
   }
 
   public void setWorkspace(File workspace) {
     this.workspace = workspace;
   }   
   
   public File getDisPatchDownLoadDir() {	  
	  if (this.disPatchDownLoadDir == null)
	       this.disPatchDownLoadDir = new File(this.branchRoot, "disPatchDownLoadDir");
	  return this.disPatchDownLoadDir;
   }
	
   public void setDisPatchDownLoadDir(File disPatchDownLoadDir) {
	  this.disPatchDownLoadDir = disPatchDownLoadDir;
   }
 
   public String getSvnUser() {
     return this.svnUser;
   }
 
   public void setSvnUser(String svnUser) {
     this.svnUser = svnUser;
   }
 
   public String getSvnPassword() {
     return this.svnPassword;
   }
 
   public void setSvnPassword(String svnPassword) {
     this.svnPassword = svnPassword;
   }
 
   public File getSvnWorkspace() {
     return new File(getWorkspace(), "svn");
   }
 
   public String getSqlRepos()
   {
     return this.sqlReposRoot;
   }
 
   public String getSqlEncoding()
   {
     return this.sqlEncoding;
   }
 
   public void setSqlEncoding(String sqlEncoding) {
     this.sqlEncoding = sqlEncoding;
   }
 
   public String[] getSqlSuffixs()
   {
     return this.sqlSuffixs;
   }
 
   public String getVersionNo() {
     return this.versionNo;
   }
 
   public void setVersionNo(String versionNo) {
     this.versionNo = versionNo;
   }
 
   public String getVersionPattern() {
     return this.versionPattern;
   }
 
   public void setVersionPattern(String versionPattern) {
     this.versionPattern = versionPattern;
   }
 
   public String getVersionSuffix() {
     return this.versionSuffix;
   }
 
   public void setVersionSuffix(String versionSuffix) {
     this.versionSuffix = versionSuffix;
   }
 
   public String getSvnRoot() {
     return this.svnRoot;
   }
 
   public void setSvnRoot(String svnRoot) {
     if (!svnRoot.startsWith("/"))
       svnRoot = "/" + svnRoot;
     if (!svnRoot.endsWith("/"))
       svnRoot = svnRoot + "/";
     this.svnRoot = svnRoot;
   }
 
   public File getVersionInfo() {
     if (this.versionInfo == null)
       this.versionInfo = new File(this.branchRoot, "configs/versioninfo.xml");
     return this.versionInfo;
   }
 
   public void setVersionInfo(File versionInfo) {
     this.versionInfo = versionInfo;
   }
 
   public File getBranchRoot() {
     return this.branchRoot;
   }
 
   public void setBranchRoot(File branchRoot) {
     this.branchRoot = branchRoot;
   }
 
   public void setTestUsers(String testUsers) {
     if (testUsers == null)
       return;
     String[] users = testUsers.split(";");
     for (String u : users) {
       u = u.trim();
       if (u.length() == 0)
         continue;
       if (u.indexOf('=') != -1) {
         int index = u.indexOf('=');
         String name = u.substring(0, index);
         if (name.trim().length() == 0)
           continue;
         String psw = u.substring(index + 1);
         this.testUsers.add(name.trim());
         this.userAuthInfo.put(name.trim(), psw.trim());
       } else {
         this.testUsers.add(u);
         if (!userAuthInfo.containsKey(u))//空密码
             this.userAuthInfo.put(u, "");
       }
     }
   }   
 
   public boolean isTestUser(String user) {
     return this.testUsers.contains(user);
   }
   
   public void setSss(String sss) {
	     if (sss == null)
	       return;
	     String[] users = sss.split(";");
	     for (String u : users) {
	       u = u.trim();
	       if (u.length() == 0)
	         continue;
	       if (u.indexOf('=') != -1) {
	         int index = u.indexOf('=');
	         String name = u.substring(0, index);
	         if (name.trim().length() == 0)
	           continue;
	         String psw = u.substring(index + 1);
	         this.sss.add(name.trim());
	         this.userAuthInfo.put(name.trim(), psw.trim());
	       } else {
	         this.sss.add(u);
	         if (!userAuthInfo.containsKey(u))//空密码
	             this.userAuthInfo.put(u, "");
	       }
	     }
	   }
   
   public boolean isSs(String user) {
	     return this.sss.contains(user);
	   }
 
   public void setDeployUsers(String deployUsers) {
     if (deployUsers == null)
       return;
     String[] users = deployUsers.split(";");
     for (String u : users) {
       u = u.trim();
       if (u.length() == 0)
         continue;
       if (u.indexOf('=') != -1) {
         int index = u.indexOf('=');
         String name = u.substring(0, index);
         if (name.trim().length() == 0)
           continue;
         String psw = u.substring(index + 1);
         this.deployUsers.add(name.trim());
         this.userAuthInfo.put(name.trim(), psw.trim());
       } else {
         this.deployUsers.add(u);
         if (!userAuthInfo.containsKey(u))//空密码
             this.userAuthInfo.put(u, "");
       }
     }
   }
 
   public boolean isDeployUser(String user) {
     return this.deployUsers.contains(user);
   }
 
   public File getLogRoot() {
     return new File(this.branchRoot, "logs");
   }
 
   public boolean validUser(String user, String password) {
     String psw = (String)this.userAuthInfo.get(user);
     return password.equals(psw);
   }
   
   public String getSpNo() {
		return spNo;
	}

	public void setSpNo(String spNo) {
		this.spNo = spNo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}	

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}
	
    public String getPermitBand() {
		return permitBand;
	}

	public void setPermitBand(String permitBand) {
		this.permitBand = permitBand;
	}

	public String getPermitPatch() {
		return permitPatch;
	}

	public void setPermitPatch(String permitPatch) {
		this.permitPatch = permitPatch;
	}

	public String getMainVersion() {
		return mainVersion;
	}

	public void setMainVersion(String mainVersion) {
		this.mainVersion = mainVersion;
	}

	public String getSubVersion() {
		return subVersion;
	}

	public void setSubVersion(String subVersion) {
		this.subVersion = subVersion;
	}

	public String getJdkVersion() {
		return (null == jdkVersion || "".equals(jdkVersion)) ? "1.6" : jdkVersion;
	}

	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	public String getSrcEncoding() {
		return (null == srcEncoding || "".equals(srcEncoding)) ? "UTF-8" : srcEncoding;
	}

	public void setSrcEncoding(String srcEncoding) {
		this.srcEncoding = srcEncoding;
	}

	public String getIsWeekbug() {
		return isWeekbug;
	}

	public void setIsWeekbug(String isWeekbug) {
		this.isWeekbug = isWeekbug;
	}

	/**
	 * 判断该分支是否是周BUG
	 * @return 如果是周BUG线，则返回true
	 */
	public boolean isWeekbug(){
		return "1".equals(this.isWeekbug);
	}

 }
