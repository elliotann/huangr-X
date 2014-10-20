 package com.easysoft.build.filter;


 import com.easysoft.build.command.BuildCommand;
 import com.easysoft.build.command.CommandContext;
 import org.apache.log4j.Logger;

 public class BuildLogFilter extends CommandFilterAdapter
 {
   protected static final Logger logger = Logger.getLogger(BuildCommand.class);
 
   public boolean beforeExecute(BuildCommand command) throws Exception
   {
     CommandContext context = command.getContext();
     logger.info(context.getUser() + " execute " + context.getAction() + " on " + context.getFileName() + "(" + context.getBranch() + ")" + " from " + context.getRemoteAddr() + " [" + context.getContextId() + "]");
 
     return super.beforeExecute(command);
   }
 
   public boolean onError(BuildCommand command, Throwable t)
   {
     CommandContext context = command.getContext();
     logger.error("execute fail on action: " + context.getAction() + " by " + context.getUser() + "[" + context.getContextId() + "]", t);
 
     return super.onError(command, t);
   }
 
   public boolean afterExecute(BuildCommand command)
   {
     CommandContext context = command.getContext();
     logger.info("execute success on action: " + context.getAction() + " by " + context.getUser() + "[" + context.getContextId() + "]");
 
     return super.afterExecute(command);
   }
 }

