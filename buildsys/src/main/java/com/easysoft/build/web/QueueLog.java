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
 
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

 public class QueueLog
 {
   private String id;
   private List<String> messages = new ArrayList();
 
   public QueueLog(String id) {
     this.id = id;
   }
 
   public String getLastMessage() {
     if (this.messages.size() == 0)
       return "";
     return (String)this.messages.get(this.messages.size() - 1);
   }
 
   public void logMessage(String msg) {
     this.messages.add("[" + this.id + "]" + getTS() + ": " + msg);
   }
 
   private String getTS() {
     return new SimpleDateFormat("MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
   }
 
   public boolean equals(Object obj)
   {
     if (this == obj)
       return true;
     if (obj == null)
       return false;
     if (!(obj instanceof QueueLog))
       return false;
     QueueLog lg = (QueueLog)obj;
     return this.id.endsWith(lg.id);
   }
 }

