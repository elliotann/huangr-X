package com.easysoft.core.manager.solution;

import com.easysoft.framework.context.webcontext.ThreadContextHolder;

import java.util.ArrayList;
import java.util.List;

public class InstallUtil {
	public static void putMessaage(String msg){
		if(ThreadContextHolder.getSessionContext()!=null){
		List msgList = (List)ThreadContextHolder.getSessionContext().getAttribute("installMsg");
		if(msgList==null){
			msgList = new ArrayList();
		}
		msgList.add(msg);
		ThreadContextHolder.getSessionContext().setAttribute("installMsg", msgList);
		}
	}
}
