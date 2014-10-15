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
 
 public class ChangedSVNFiles
 {
   private String[] paths;
   private String comments;
 
   public ChangedSVNFiles(String[] paths, String comments)
   {
     this.paths = paths;
     this.comments = comments;
   }
 
   public String[] getPaths() {
     return this.paths;
   }
 
   public void setPaths(String[] paths) {
     this.paths = paths;
   }
 
   public String getComments() {
     return this.comments;
   }
 
   public void setComments(String comments) {
     this.comments = comments;
   }
 }

