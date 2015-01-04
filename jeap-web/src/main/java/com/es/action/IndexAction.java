package com.es.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author huangxa
 *
 */

@Controller
@RequestMapping({"/admin/index"})
public class IndexAction {
	@RequestMapping(params={"main"})
	public String list(){
		return "index";
	}
}
