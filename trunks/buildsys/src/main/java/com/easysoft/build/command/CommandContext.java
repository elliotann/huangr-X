 package com.easysoft.build.command;



 import com.easysoft.build.manager.BuildFileService;
 import com.easysoft.build.model.BuildFile;
 import com.easysoft.member.backend.manager.impl.UserUtil;

 import javax.servlet.http.HttpServletRequest;

 public class CommandContext
 {
   public static final String KEY_PARAM_ACTION = "action";
   private String user;
   private BuildFile buildFile;
   private String fileName;
   private String branch;
   private String action;
   private String remoteAddr;
   private String contextId;
 
   public CommandContext()
   {
     this.user = null;
 
     this.buildFile = null;
 
     this.fileName = null;
 
     this.branch = null;
 
     this.action = null;
 
     this.remoteAddr = null;
 
     this.contextId = null;
   }
 
   public void init(HttpServletRequest request)
   {
     this.action = request.getParameter("action");
     this.user = "360042212@qq.com";
     this.fileName = request.getParameter("fileName");
     this.branch = "jeap1.0";
     this.remoteAddr = request.getRemoteAddr();
 
     this.buildFile = BuildFileService.getBuildPackInfo(this.branch, this.fileName);
 
     this.contextId = (this.action + System.currentTimeMillis());
   }
 
   public String getUser() {
     return this.user;
   }
 
   public void setUser(String user) {
     this.user = user;
   }
 
   public BuildFile getBuildFile() {
     return this.buildFile;
   }
 
   public void setBuildFile(BuildFile buildFile) {
     this.buildFile = buildFile;
   }
 
   public String getFileName() {
     return this.fileName;
   }
 
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
 
   public String getBranch() {
     return this.branch;
   }
 
   public void setBranch(String branch) {
     this.branch = branch;
   }
 
   public String getAction() {
     return this.action;
   }
 
   public void setAction(String action) {
     this.action = action;
   }
 
   public String getRemoteAddr() {
     return this.remoteAddr;
   }
 
   public void setRemoteAddr(String remoteAddr) {
     this.remoteAddr = remoteAddr;
   }
 
   public String getContextId() {
     return this.contextId;
   }
 
   public void setContextId(String contextId) {
     this.contextId = contextId;
   }
 }

