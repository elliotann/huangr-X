 package com.easysoft.build.command;


 import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.model.BuildFile;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.member.backend.model.UserRole;

 public class TestPassBuildCommand extends BuildCommand
 {
   private static final String COMMAND_NAME = "测试通过";
 
   protected void doExcute()
     throws Exception
   {
     BuildFile buildFile = this.context.getBuildFile();
     buildFile.getConfig().setTesters(this.context.getUser());
     buildFile.getConfig().setPassTS(System.currentTimeMillis());
     
     BuildConfigInfoService serv = SpringContextHolder.getBean("buildConfigInfoService");
     serv.saveBcConfigInPasstesting(buildFile.getConfig());
     
     String deper = buildFile.getSubmiter();
	 String fname = buildFile.getFileName().substring(0, buildFile.getFileName().indexOf(".zip"));

   }
 
   public String getName()
   {
     return "测试通过";
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
         throw new Exception("当前构建包【" + this.context.getFileName() + "】正由用户" + currentTester + "测试！");
       }
     }
     super.doValid();
   }
 }

