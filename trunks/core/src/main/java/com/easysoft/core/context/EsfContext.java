package com.easysoft.core.context;

import com.easysoft.core.model.MultiSite;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.utils.ContextHolderUtils;

import javax.servlet.http.HttpServletRequest;

public class EsfContext {
	private static ThreadLocal<HttpServletRequest> HttpRequestHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<EsfContext> EopContextHolder = new ThreadLocal<EsfContext>();
	
	public static void setContext(EsfContext context){
		EopContextHolder.set(context);
	}
	
	public static EsfContext getContext(){
		EsfContext context =  EopContextHolder.get();
		return context;
	}
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestHolder.set(request);
	}

    public static void remove() {
        EopContextHolder.remove();
    }

    public static HttpServletRequest getHttpRequest(){
		return HttpRequestHolder.get();
	}
	
	//当前站点（主站）
	private Site currentSite;
	
	//当前子站
	private MultiSite currentChildSite;
	
	public Site getCurrentSite(){
		return currentSite;
	}
	
	public  void setCurrentSite(Site site){
		currentSite = site;
	}
	
	
	public MultiSite getCurrentChildSite() {
		return currentChildSite;
	}

	public void setCurrentChildSite(MultiSite currentChildSite) {
		this.currentChildSite = currentChildSite;
	}

	
	//得到当前站点上下文
	public String getContextPath(){
		if("2".equals(ParamSetting.RUNMODE) ){
			Site site  = this.getCurrentSite();
			StringBuffer context = new StringBuffer("/user");
			context.append("/");
			context.append(site.getUserid());
			context.append("/");
			context.append(site.getId());
			return context.toString();
		}else{
			return "";
		}
	}
    public String getResDomain()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        String domain = null;

        if (("1".equals(ParamSetting.RESOURCEMODE)) && (!ParamSetting.DEVELOPMENT_MODEL))
            domain = ParamSetting.IMG_SERVER_DOMAIN;
        else {
            domain = request.getContextPath();
        }

        if (domain.endsWith("/")) domain = domain.substring(0, domain.length() - 1);

        domain = domain + getContext().getContextPath();
        return domain;
    }

    public String getResPath()
    {
        String path = ParamSetting.IMG_SERVER_PATH;

        if (path.endsWith("/")) path = path.substring(0, path.length() - 1);

        path = path + getContext().getContextPath();
        return path;
    }

    public static String getParameter(String field) {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        return request.getParameter(field);
    }
}
