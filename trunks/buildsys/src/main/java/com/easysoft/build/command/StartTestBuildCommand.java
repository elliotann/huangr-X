 package com.easysoft.build.command;


 import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.model.BuildFile;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.member.backend.model.UserRole;

 public class StartTestBuildCommand extends BuildCommand
 {
   private static final String COMMAND_NAME = "开始测试";
 
   protected void doExcute()
     throws Exception
   {
     BuildFile buildFile = this.context.getBuildFile();
     buildFile.getConfig().setTesters(this.context.getUser());	
     
     BuildConfigInfoService serv = (BuildConfigInfoService)SpringContextHolder.getBean("buildConfigInfoService");
     serv.saveBcConfigInStartesting(buildFile.getConfig());
     
     String deper = buildFile.getSubmiter();
	 String fname = buildFile.getFileName().substring(0, buildFile.getFileName().indexOf(".zip"));

   }
 
   public String getName()
   {
     return "开始测试";
   }
 
   protected UserRole getExecuteRole()
   {
     return null;
   }
 
   protected void doValid() throws Exception
   {
     BuildFile buildFile = this.context.getBuildFile();
     if (buildFile.hasTester()) {
       String currentTester = buildFile.getTester();
       if (!currentTester.equals(this.context.getUser())) {
         throw new Exception("用户" + currentTester + "已经对构建包【" + this.context.getFileName() + "】进行测试");
       }
     }
     super.doValid();
   }
 }

