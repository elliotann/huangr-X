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

package com.easysoft.build.cache;



import com.easysoft.build.model.BuildFile;

import java.io.File;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Build Package Info Cache Manager
 * 缓存系统中的BuildPackInfo对象，避免频繁读取文件
 * @author pangl
 *
 */
public abstract class BPICache {
	
	private static final int MAX_CACHE_SIZE = 500;
	
	private static final long EXPIRE_INTERVAL = 60*60*1000;
	
	private static Map<File, BuildFile> infos = new Hashtable<File, BuildFile>();
	
	/**
	 * 获取一个BuildPackInfo对象，并视条件进行缓存
	 * @param branchName
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static BuildFile getBuildPackInfo(String branchName, File file) {
		if (!file.exists()) {
			infos.remove(file);
			return null;
		}
		BuildFile info = infos.get(file);
		if (info == null) {
			synchronized (infos) {
				 info = infos.get(file);
				 if (info == null) {
					 try {
						info = new BuildFile(branchName, file);
						addInfo(file, info);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("==========" + branchName + "======" + file);
					}
				 }
			}
		}
		try {
			if (info != null) {
				info.reload();
				info.updateUseTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public static void remove(BuildFile info) {
		synchronized (infos) {
			infos.remove(info.getFile());
		}
	}
	
	/**
	 * 检查缓存情况并增加一个对象
	 * @param file
	 * @param info
	 */
	private static void addInfo(File file, BuildFile info) {
		if (infos.size() >= MAX_CACHE_SIZE) {
			synchronized (infos) {
				Set<File> removeKeys = new HashSet<File>();
				long ts = System.currentTimeMillis() - EXPIRE_INTERVAL;
				for (File f : infos.keySet()) {
					BuildFile ifo = infos.get(f);
					if (ifo.getLastUseTime() < ts) {
						removeKeys.add(f);
					}
				}
				for (File f : removeKeys) {
					infos.remove(f);
				}
			}
		}
		infos.put(file, info);
	}
}
