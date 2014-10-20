 package com.easysoft.build.filter;


 import com.easysoft.build.command.BuildCommand;

 public class CommandFilterAdapter
   implements ICommandFilter
 {
   public boolean beforeExecute(BuildCommand command)
     throws Exception
   {
     return true;
   }
 
   public boolean onError(BuildCommand command, Throwable t) {
     return true;
   }
 
   public boolean afterExecute(BuildCommand command) {
     return true;
   }
 }

