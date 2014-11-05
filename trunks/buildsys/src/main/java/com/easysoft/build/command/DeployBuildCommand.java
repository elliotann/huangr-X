 package com.easysoft.build.command;


 import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.manager.BuildFileService;
import com.easysoft.build.model.BuildFile;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.member.backend.model.UserRole;

 public class DeployBuildCommand extends BuildCommand
 {
   private static final String COMMAND_NAME = "发布构建包";
 
   protected void doExcute()
     throws Exception
   {
     BuildFile buildFile = this.context.getBuildFile();
     BuildFileService.deployPack(buildFile);
     
 	 BuildConfigInfoService serv = SpringContextHolder.getBean(BuildConfigInfoService.class);
	 // 发布构建包并更新依赖该包的依赖
	 serv.saveBcConfigInDeployPacking(buildFile.getConfig(),this.context.getUser());
   }
 
   public String getName()
   {
     return "发布构建包";
   }
 
   protected UserRole getExecuteRole()
   {
     return null;
   }
 }

