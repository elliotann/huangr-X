package com.easysoft.build.command;



import com.easysoft.build.filter.ICommandFilter;

import java.util.*;

public class BuildCommandManager {
	public static final String FILE_CONFIG = "/com/easysoft/build/command/action_command.properties";
	public static final String CONFIG_KEY_ACTIONS = "actions";
	public static final String ACTIONS_SEPARATOR = ",";
	public static final String FILTER_SEPARATOR = ";";
	public static final String FILTER_SUFFIX = ".filter";
	static Map<String, BuildCommand> CommandRegister = new HashMap();

	static void init() {
		Properties prop = new Properties();
		try {
			prop.load(BuildCommand.class
					.getResourceAsStream("/com/easysoft/build/command/action_command.properties"));
			String actions = prop.getProperty("actions");
			if (actions != null) {
				String[] actionList = actions.split(",");

				for (String action : actionList) {
					String commandCls = prop.getProperty(action);
					BuildCommand command = (BuildCommand) Class.forName(
							commandCls).newInstance();

					String filters = prop.getProperty(action + ".filter");
					if (filters != null) {
						String[] filterList = filters.split(";");
						List oFilterList = new ArrayList();
						for (String filter : filterList) {
							ICommandFilter filterObj = (ICommandFilter) Class.forName(filter).newInstance();
							oFilterList.add(filterObj);
						}
						command.setFilters((ICommandFilter[]) (ICommandFilter[]) oFilterList.toArray(new ICommandFilter[oFilterList.size()]));
					}
					CommandRegister.put(action, command);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static BuildCommand getCommand(CommandContext context) {
		BuildCommand command = (BuildCommand) CommandRegister.get(context.getAction());
		command = command.clone();
		command.setContext(context);
		return command;
	}

	static {
		init();
	}
}
