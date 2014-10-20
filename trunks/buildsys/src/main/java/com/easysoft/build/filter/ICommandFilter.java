package com.easysoft.build.filter;


import com.easysoft.build.command.BuildCommand;

public abstract interface ICommandFilter
{
  public abstract boolean beforeExecute(BuildCommand paramBuildCommand)
    throws Exception;

  public abstract boolean onError(BuildCommand paramBuildCommand, Throwable paramThrowable);

  public abstract boolean afterExecute(BuildCommand paramBuildCommand);
}

