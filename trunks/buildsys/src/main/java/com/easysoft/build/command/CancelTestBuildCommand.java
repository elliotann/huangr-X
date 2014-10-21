package com.easysoft.build.command;


import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.model.BuildFile;
import com.easysoft.framework.spring.SpringContextHolder;
import com.easysoft.member.backend.model.UserRole;

public class CancelTestBuildCommand extends BuildCommand {
	private static final String COMMAND_NAME = "取消测试";

	protected void doExcute() throws Exception {
		BuildFile buildFile = this.context.getBuildFile();
		buildFile.getConfig().setTesters(null);
		buildFile.getConfig().setPassTS(-1);
		
		BuildConfigInfoService serv = SpringContextHolder.getBean("buildConfigInfoService");
		String tester = this.context.getUser();
	    serv.saveBcConfigInCancelTesting(buildFile.getConfig(),tester);
	    
	    String deper = buildFile.getSubmiter();
		String fname = buildFile.getFileName().substring(0, buildFile.getFileName().indexOf(".zip"));

	}

	public String getName() {
		return "取消测试";
	}

	protected UserRole getExecuteRole() {
		return null;
	}

	protected void doValid() throws Exception {
		BuildFile buildFile = this.context.getBuildFile();
		if (buildFile.hasTester()) {
			String currentTester = buildFile.getTester();
			if (!currentTester.equals(this.context.getUser())) {
				throw new Exception("当前构建包【" + this.context.getFileName()
						+ "】被正在由用户" + currentTester + "测试，无法取消测试");
			}
		}
		super.doValid();
	}
}
