package com.easysoft.core.dispatcher.httpcache;

import com.easysoft.core.manager.IThemeUriManager;
import com.easysoft.core.model.ThemeUri;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.cache.CacheFactory;
import com.easysoft.framework.cache.ICache;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.spring.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;

public class HttpCacheManager
{
  private static ICache<Long> urlCache;
  private static ICache<Long> uriCache;

  public static boolean getIsCached(String url)
  {
    long now = System.currentTimeMillis();
    HttpServletRequest request = ThreadContextHolder.getHttpRequest();
    HttpServletResponse response = ThreadContextHolder.getHttpResponse();
    if (ParamSetting.HTTPCACHE == 0) {
      response.setStatus(200);
      response.setDateHeader("Last-Modified", now);
      response.setDateHeader("Expires", 0L);
      response.setHeader("Cache-Control", "max-age=0");

      return false;
    }

    if (url.equals("/")) url = "/index.html";

    response.setDateHeader("Expires", 0L);
    response.setHeader("Cache-Control", "max-age=0");

    long modifiedSince = request.getDateHeader("If-Modified-Since");

    Long lastModified = getLastModified(url);

    if (lastModified == null)
    {
      response.setStatus(200);
      response.setDateHeader("Last-Modified", now);

      return false;
    }
    if (lastModified.longValue() / 1000L == modifiedSince / 1000L) {
      response.setStatus(304);
      response.setDateHeader("Last-Modified", lastModified.longValue());
      return true;
    }
    response.setStatus(200);
    response.setDateHeader("Last-Modified", lastModified.longValue());
    return false;
  }

  public static void sessionChange()
  {
    ThreadContextHolder.getSessionContext().setAttribute("sessionchangetime", Long.valueOf(System.currentTimeMillis()));
  }

  public static void updateUriModified(String uri)
  {
    long now = System.currentTimeMillis();

    getUriCache().put(uri, Long.valueOf(now));
  }

  public static void updateUrlModified(String url)
  {
    long now = System.currentTimeMillis();

    getUrlCache().put(url, Long.valueOf(now));
  }

  private static Long getLastModified(String url)
  {
    ThemeUri themeUri = getCachedThemeUri(url);
    if (themeUri == null)
    {
      return null;
    }

    String uri = themeUri.getUri();
    Long uriLastModified = (Long)getUriCache().get(uri);
    Long urlLastModified = (Long)getUrlCache().get(url);

    Long sessionchangetime = (Long)ThreadContextHolder.getSessionContext().getAttribute("sessionchangetime");
    if (sessionchangetime != null) {
      if (urlLastModified != null) {
        if (sessionchangetime.longValue() > urlLastModified.longValue())
          return sessionchangetime;
      }
      else {
        return sessionchangetime;
      }

    }

    if (urlLastModified == null) {
      if (uriLastModified == null) {
        long now = System.currentTimeMillis();
        getUrlCache().put(url, Long.valueOf(System.currentTimeMillis()));
      }
      else
      {
        getUrlCache().put(url, uriLastModified);
      }

    }

    if (uriLastModified == null)
    {
      return urlLastModified;
    }

    if (urlLastModified == null) {
      return uriLastModified;
    }

    if (uriLastModified.longValue() > urlLastModified.longValue())
    {
      return uriLastModified;
    }

    return urlLastModified;
  }

  private static ThemeUri getCachedThemeUri(String url)
  {
    IThemeUriManager themeUriManager = (IThemeUriManager) SpringContextHolder.getBean("themeUriManager");
    List<ThemeUri> themeUriList = themeUriManager.list();
    for (ThemeUri themeUri : themeUriList) {
      Matcher m = themeUri.getPattern().matcher(url);
      if (m.find()) {
        if (themeUri.getHttpcache() == 1) {
          return themeUri;
        }
        return null;
      }

    }

    return null;
  }

  private static ICache<Long> getUrlCache()
  {
    if (urlCache == null) {
      urlCache = CacheFactory.getCache("httpUrlCache");
    }

    return urlCache;
  }

  private static ICache<Long> getUriCache() {
    if (uriCache == null) {
      uriCache = CacheFactory.getCache("httpUriCache");
    }

    return uriCache;
  }
}