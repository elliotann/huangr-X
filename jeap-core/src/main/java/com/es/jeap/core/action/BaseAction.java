package com.es.jeap.core.action;
import org.springframework.web.bind.annotation.RequestMapping;
public abstract class BaseAction {
	@RequestMapping(params={"list"})
	public String list(){
		return listResult();
		
	}
	protected abstract String listResult();
}
