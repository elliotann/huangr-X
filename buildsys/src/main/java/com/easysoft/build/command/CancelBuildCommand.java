package com.easysoft.build.command;



import com.easysoft.build.cache.BPICache;
import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.manager.BuildFileService;
import com.easysoft.build.manager.BuildReposManager;
import com.easysoft.build.model.BuildConfig;
import com.easysoft.build.model.BuildFile;
import com.easysoft.build.model.RepositoryInfo;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.member.backend.model.UserRole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CancelBuildCommand extends BuildCommand {
	private static final String COMMAND_NAME = "取消构建";

	protected void doExcute() throws Exception {
		BuildFile buildFile = this.context.getBuildFile();
		RepositoryInfo repos = BuildReposManager.getByName(buildFile
                .getBranchName());
		BuildConfig conf = buildFile.getConfig();
		File xmlFile = new File(repos.getWorkspace(), "deleted/" + conf.getId()
				+ "_" + System.currentTimeMillis() + ".xml");
		try {
			File delFile = new File(repos.getWorkspace(), "deleted/");
			if (!delFile.exists()) {
				delFile.mkdir();
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile));
			writer.write(conf.toXML());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		buildFile.getFile().delete();
		BuildFileService.removeDepend(buildFile);

		//删除源文件zip包
		File srcFile = new File(repos.getBuildSrcDir(), conf.getId() + ".zip");
		if(srcFile.exists()){
			srcFile.delete();
		}
		
		String deper = buildFile.getSubmiter();
		String fname = buildFile.getFileName().substring(0, buildFile.getFileName().indexOf(".zip"));

		BPICache.remove(buildFile);
		
		BuildConfigInfoService serv = (BuildConfigInfoService)SpringContextHolder.getBean("buildConfigInfoService");
		//取消构建包并更新依赖该包的依赖
		serv.saveBcConfigInCancelBuilding(conf,this.context.getUser());
		

		
	}

	public String getName() {
		return "取消构建";
	}

    @Override
    protected UserRole getExecuteRole() {
        return null;
    }


    protected void doValid() throws Exception {
		BuildFile buildFile = this.context.getBuildFile();
		if (buildFile.hasTester()) {
			String currentTester = buildFile.getTester();
			if (!currentTester.equals(this.context.getUser())) {
				throw new Exception("当前构建包【" + this.context.getFileName()
						+ "】正由用户" + currentTester + "测试！");
			}
		}
		super.doValid();
	}
}
