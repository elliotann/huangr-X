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
 
 import java.text.SimpleDateFormat;
import java.util.Date;

 public class ChangeLog
 {
   private String user;
   private String action;
   private long ts;
   private static SimpleDateFormat df;
 
   ChangeLog(String user, String action, long ts)
   {
     this.user = user;
     this.action = action;
     this.ts = ts;
   }
 
   ChangeLog(String log) {
     String[] ps = log.split(":");
     assert (ps.length == 3);
     this.user = ps[0].trim();
     this.action = ps[1].trim();
     this.ts = Long.parseLong(ps[2]);
   }
 
   public String getUser() {
     return this.user;
   }
   public void setUser(String user) {
     this.user = user;
   }
   public String getAction() {
     return this.action;
   }
   public void setAction(String action) {
     this.action = action;
   }
   public long getTs() {
     return this.ts;
   }
   public void setTs(long ts) {
     this.ts = ts;
   }
 
   public String toString() {
     return this.user + ":" + this.action + ":" + this.ts;
   }
 
   public String getLog()
   {
     return "[" + df.format(new Date(this.ts)) + "] user:" + this.user + ", action:" + this.action;
   }
 
   static
   {
     df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   }
 }

