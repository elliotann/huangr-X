package com.easysoft.core.dispatcher.processor.facade;


import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.widget.facade.IWidgetCfgHtmlParser;
import com.easysoft.framework.utils.RequestUtil;
import com.easysoft.framework.utils.SpringContextHolder;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 挂件设置页面处理器
 * 
 * @author andy
 * @version 1.0
 */
public class WidgetSettingProcessor implements Processor {
	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * 响应挂件配置显示的请求<br/>
	 * <li>由httpRequest中获取挂件参数</li>
	 * <li>将挂件参数传递 给 
	 * {@link com.easysoft.base.widget.facade.IWidgetCfgHtmlParser }
	 * ,并解析出挂件配置的html</li>
	 * @param mode
	 *            本地：0 远程：1
	 * @param httpResponse
	 * @param httpRequest
	 * 
	 */
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {

		Map<String,String> params = RequestUtil.paramToMap(httpRequest);
		IWidgetCfgHtmlParser widgetCfgParser = SpringContextHolder
				.getBean("localWidgetCfgPaser");

		String content = widgetCfgParser.pase(params);
		Response response = new StringResponse();
		response.setContent(content);
		return response;

		/*
		 * Widget widget = WidgetFactory.build(httpRequest); mode =
		 * widget.getApp().getDeployment();
		 * 
		 * //2009-12-24新增挂件的操作类型 String act = httpRequest.getParameter("act");
		 * String id = widget.getAttribute("id"); if(act!=null &&
		 * act.equals("create")){ id =
		 * UUID.randomUUID().toString().replaceAll("-", "");
		 * widget.setAttribute("id", id); }
		 * 
		 * Request request = getRequest(mode); request = new
		 * ConfigDialogWrapper(widget, request);
		 * 
		 * //3.0架构时暂不考虑远程 // String uri =
		 * RequestUtil.getRequestUrl(httpRequest); // uri = getUri(widget);
		 * request.setExecuteParams(widget.getAtributes()); Response response =
		 * request.execute(null, httpResponse, httpRequest);
		 * 
		 * return response;
		 */
	}

	/*
	 * private Request getRequest(int mode){ Request request=null;
	 * if(mode==ConnectType.remote) request = new RemoteRequest();
	 * 
	 * if(mode==ConnectType.local) request = new IWidgetCfgParser(); return
	 * request; }
	 * 
	 * 
	 * private String getUri(Widget widget) {
	 * 
	 * try { IWidgetBundleManager bundleManager = EopApiFactory
	 * .getWidgetBundleManager(); WidgetBundle widgetBundle =
	 * bundleManager.getWidgetBundle(widget .getSite().getUserid(),
	 * widget.getWidgetType());
	 * 
	 * EsfApp app = widget.getApp();
	 * 
	 * if (app == null) throw new EopException("挂件初始化异常:app未正常初始化");
	 * 
	 * String uri = widgetBundle.getSettingurl();
	 * 
	 * 
	 * // uri = uri.indexOf('?')>0?uri+"&":uri+"?";
	 * //uri+="id="+widget.getAttribute("id");
	 * 
	 * if(logger.isDebugEnabled()){ logger.debug("connect setting uri : "+ uri);
	 * } return uri; } catch (RuntimeException ex) { ex.printStackTrace(); throw
	 * new EopException("挂件setting页面获取失败"); }
	 * 
	 * }
	 * 
	 * 
	 * public static void main(String[] args){ String uri ="afdfa?dfaf=1"; uri =
	 * uri.indexOf('?')>0?uri+"&":uri+"?"; System.out.println(uri); }
	 */

}