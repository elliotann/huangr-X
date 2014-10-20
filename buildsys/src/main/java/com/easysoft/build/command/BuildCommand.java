package com.easysoft.build.command;


import com.easysoft.build.filter.ICommandFilter;
import com.easysoft.build.model.BuildFile;
import com.easysoft.member.backend.model.UserRole;

public abstract class BuildCommand implements Cloneable {
	private static final ICommandFilter[] EMPTY_FILTER = new ICommandFilter[0];

	protected CommandContext context = null;

	protected String commandId = null;

	protected String oldXML = null;

	protected ICommandFilter[] filters = EMPTY_FILTER;

	void setFilters(ICommandFilter[] filters) {
		if (filters != null)
			this.filters = filters;
	}

	void setContext(CommandContext context) {
		this.context = context;
	}

	public final void execute() throws Exception {
		try {
			doBeforeExecute();
			for (ICommandFilter filter : this.filters) {
				if (!filter.beforeExecute(this))
					break;
			}
			doExcute();
		} catch (Exception ex) {
			for (ICommandFilter filter : this.filters) {
				if (!filter.onError(this, ex)) {
					break;
				}
			}
			doAfterError(ex);
		}

		doAfterExecute();
		for (ICommandFilter filter : this.filters)
			if (!filter.afterExecute(this))
				return;
	}

	private void valid() throws Exception {
		String fileName = this.context.getFileName();
		if ((fileName.indexOf('/') != -1) || (fileName.indexOf('\\') != -1)) {
			throw new Exception("文件名不合法：" + fileName);
		}

		UserRole role = getExecuteRole();
		/*if ((role != null)
				&& (!UserUtil.checkRole(this.context.getBranch(),
						this.context.getUser(), role))) {
			throw new Exception("action " + getName() + " need role: " + role
					+ " to execute.");
		}*/
		doValid();
	}

	protected void doValid() throws Exception {
	}

	protected abstract void doExcute() throws Exception;

	protected void doBeforeExecute() throws Exception {
		valid();
		this.oldXML = this.context.getBuildFile().getConfig().toXML();
	}

	protected void doAfterExecute() {
		BuildFile buildFile = this.context.getBuildFile();
		if (!this.oldXML.equals(buildFile.getConfig().toXML())) {
			buildFile.storeConfig();
		}
		buildFile.addChangeLog(this.context.getUser(), getName());
	}

	protected void doAfterError(Exception ex) throws Exception {
		if (this.oldXML != null) {
			this.context.getBuildFile().updateConfig(this.oldXML);
		}
		throw ex;
	}

	public abstract String getName();

	protected abstract UserRole getExecuteRole();

	protected BuildCommand clone() {
		BuildCommand command = null;
		try {
			command = (BuildCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return command;
	}

	public CommandContext getContext() {
		return this.context;
	}


}
