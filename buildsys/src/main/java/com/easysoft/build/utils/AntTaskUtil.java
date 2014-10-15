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

package com.easysoft.build.utils;

import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.FileNameMapper;

import java.io.File;
import java.util.Map;

/**
 * 使用Ant 任务来实现特定的功能
 * @author pangl
 *
 */
public abstract class AntTaskUtil {

	/**
	 * 清空临时目录
	 * @param config
	 */
	public static void deleteDir(File dir, BuildListener logger) {
		if (!dir.exists())
			return;
		Delete delete = new Delete();
		delete.setProject(new Project());
		delete.setDir(dir);
		if (logger != null)
			delete.getProject().addBuildListener(logger);
		delete.execute();
	}
	
	public static void emptyDir(BuildListener logger, File... dirs) {
		for (File dir : dirs) {
			deleteDir(dir, logger);
			dir.mkdirs();
		}
	}
	
	
	/**
	 * 压缩文件夹
	 * @param baseDir 待压缩的文件夹
	 * @param destFile 压缩的目标文件
	 */
	public static void zip(File baseDir, File destFile, BuildListener logger) {
		destFile.getParentFile().mkdirs();
		Zip zip = new Zip();
		zip.setProject(new Project());
		zip.setBasedir(baseDir);
		zip.setDestFile(destFile);
		if (logger != null)
			zip.getProject().addBuildListener(logger);
		zip.execute();
	}
	
	/**
	 * 解压缩文件到指定文件夹
	 * @param src 压缩文件
	 * @param destDir 解压缩的目标文件夹
	 */
	public static void unzip(File src, File destDir, BuildListener logger) {
		destDir.mkdirs();
		if (!src.exists())
			return;
		Expand expend = new Expand();
		expend.setProject(new Project());
		expend.setDest(destDir);
		expend.setSrc(src);
		if (logger != null)
			expend.getProject().addBuildListener(logger);
		expend.execute();
	}
	
	/**
	 * 拷贝文件
	 * @param src 源目录
	 * @param dest 目标目录
	 * @param pattern 模式
	 */
	public static void copyFiles(File src, File dest, String pattern, BuildListener logger) {
		if (!src.exists())
			return;
		if (!dest.exists())
			dest.mkdirs();
		Copy copy = new Copy();
		copy.setProject(new Project());
		copy.setTodir(dest);
		copy.setOverwrite(true);
		FileSet fs = new FileSet();
		fs.setDir(src);
		if (pattern != null) {
			fs.setIncludes(pattern);
			
		}
		copy.addFileset(fs);
		copy.setVerbose(true);
		if (logger != null)
			copy.getProject().addBuildListener(logger);
		copy.execute();
	}
	
	/**
	 * 
	 * @param srcZip 需要解压的zip
	 * @param destDir解压的目标路径
	 * @param logger 日志记录器
	 * @param fileMap 用于保存文件映射关系Map(key:文件路径名,value:srZip.getName()
	 */
	public static void unzip(final File srcZip, File destDir,
			BuildListener logger, final Map<String, String> fileMap) {
		destDir.mkdirs();
		if (!srcZip.exists()) {
			return;
		}
		final Expand expand = new Expand();
		expand.setProject(new Project());
		expand.setSrc(srcZip);
		expand.setDest(destDir);

		if (null != fileMap) {
			expand.add(new FileNameMapper() {

				public void setTo(String arg0) {
				}

				public void setFrom(String arg0) {
				}

				public String[] mapFileName(String arg0) {
					if (!arg0.endsWith("/")) {// 以/结束的是文件夹，其他的是文件
						fileMap.put(arg0, srcZip.getName());
					}
					return null;
				}
			});
		}
		if (null != logger) {
			expand.getProject().addBuildListener(logger);
		}
		expand.execute();
	}
}
