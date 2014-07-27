package com.easysoft.jeap.controller.admin.filter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author :andy.huang
 * @since :
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getServletPath();
        //如果访问后台,验证是否已经登录
        if(uri.startsWith("/admin")&&!uri.equals("/adminthtmes/default/login.jsp")){
            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("admin_username");
            if(StringUtils.isEmpty(username)){
                response.sendRedirect("../login.jsp");
            }else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
