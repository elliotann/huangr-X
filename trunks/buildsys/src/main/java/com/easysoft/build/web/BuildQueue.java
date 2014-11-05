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

package com.easysoft.build.web;



 import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.manager.BuildDetailiDataService;
import com.easysoft.build.model.BuildConfig;
import com.easysoft.build.model.BuildDetailData;
import com.easysoft.build.utils.PatchUtil;
import com.easysoft.framework.utils.SpringContextHolder;

import java.util.*;

 public class BuildQueue extends Thread
 {
   private static BuildQueue buildQueue = new BuildQueue();
 
   private int MAX_WAIT_SIZE = 100;
 
   private Map<String, QueueLog> logQues = new Hashtable();
 
   private List<QueueLog> completeQues = new ArrayList();
 
   private List<String> idList = new ArrayList();
 
   private Vector<BuildConfig> queue = new Vector();
 
   public QueueLog getLogQueue(String id)
   {
     QueueLog log = (QueueLog)this.logQues.get(id);
     if (log == null) {
       synchronized (this.queue) {
         log = (QueueLog)this.logQues.get(id);
         if (log == null) {
           log = new QueueLog(id);
           this.logQues.put(id, log);
           this.idList.add(id);
         }
       }
     }
     return log;
   }
 
   public static void addBuild(BuildConfig config)
     throws Exception
   {
     buildQueue.doAddBuild(config);
   }
 
   public void doAddBuild(BuildConfig config) throws Exception {
     synchronized (this.queue) {
       if (this.queue.size() > this.MAX_WAIT_SIZE) {
         throw new Exception("超过最大等待长度，无法接受新请求！");
       }
       int index = this.queue.indexOf(config);
       if (index != -1) {
         this.queue.remove(index);
         getLogQueue(config.getId()).logMessage("构建包被用户" + config.getDevelopers() + "替换");
       }
       getLogQueue(config.getId()).logMessage("等待构建");
       this.queue.add(config);
       this.queue.notify();
     }
   }
 
   public void run()
   {
     while (true) {
       BuildConfig config = null;
       synchronized (this.queue) {
         if (this.queue.size() > 0) {
           config = (BuildConfig)this.queue.remove(0);
           getLogQueue(config.getId()).logMessage("开始构建，请稍等....");
         } else {
           try {
             this.queue.wait();
           } catch (InterruptedException e) {
             e.printStackTrace();
           }
         }
       }
       if (config == null)
         continue;
       try {
         PatchUtil.buildPackage(config);
         
         BuildConfigInfoService serv = (BuildConfigInfoService)SpringContextHolder.getBean("buildConfigInfoService");
         serv.saveBcConfigInBuilding(config);
         
        /* BuildDetailiDataService bdds = new BuildDetailiDataService();
         List<BuildDetailData> lb= bdds.getBuildDetailDataListByNo(config.getVps());
         
         BtBuildPackDetailService bbpds = SpringContextHolder.getBean(BtBuildPackDetailService.class);
         bbpds.saveList(lb,config);*/
         
         //ReadProperties pro = new ReadProperties("sysconfig.properties");
        /* RtxSend rs = new RtxSend();
         String[] rtxUsers = WebAppManager.instance().getSysConfiguration().getRtxtestuser().split(",");
 		 rs.sendNotify(rtxUsers*//*pro.getPropertyArray("rtxtestuser")*//*,"构建包提交通知", "构建单号"+config.getVps()+"已提交,请测试!");  */
         getLogQueue(config.getId()).logMessage("构建完成,请测试");
       } catch (Exception e) {
           e.printStackTrace();
         getLogQueue(config.getId()).logMessage("构建出错" + e.getMessage());
       }
       QueueLog que = (QueueLog)this.logQues.remove(config.getId());
       this.idList.remove(config.getId());
       if (this.completeQues.contains(que)) {
         this.completeQues.remove(que);
       }
       this.completeQues.add(que);
     }
   }
 
   public static String[] listWaitLog()
   {
     List list = new ArrayList();
     for (int i = 0; i < buildQueue.idList.size(); ++i) {
       String id = (String)buildQueue.idList.get(i);
       QueueLog log = (QueueLog)buildQueue.logQues.get(id);
       if (log != null)
         list.add(log.getLastMessage());
     }
     return (String[])(String[])list.toArray(new String[list.size()]);
   }
 
   public static String[] listBuildLog()
   {
     List list = new ArrayList();
     for (int i = 0; i < buildQueue.completeQues.size(); ++i) {
       QueueLog log = (QueueLog)buildQueue.completeQues.get(i);
       if (log != null)
         list.add(log.getLastMessage());
     }
     return (String[])(String[])list.toArray(new String[list.size()]);
   }
 
   static
   {
     buildQueue.setDaemon(true);
     buildQueue.start();
   }
 }

