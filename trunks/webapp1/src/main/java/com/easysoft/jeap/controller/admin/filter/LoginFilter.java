package com.easysoft.jeap.controller.admin.filter;
import com.easysoft.jeap.core.member.entity.AdminUser;
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
        if(!isContain(uri)){
            filterChain.doFilter(request,response);
            return;
        }
        HttpSession session = request.getSession();
        AdminUser adminUser = (AdminUser)session.getAttribute("admin_user_key");
        if(adminUser!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //如果访问后台,验证是否已经登录
        if(uri.startsWith("/admin")&&!uri.equals("/admin/toLogin.do")&&!uri.equals("/admin/login.do")&&isContain(uri)){

            if(adminUser==null){
                response.sendRedirect("/jeap/admin/toLogin.do");
            }else{
                filterChain.doFilter(request,response);
                return;
            }
        }else{
            filterChain.doFilter(request,response);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isContain(String uri){
        if(uri.endsWith(".css")||uri.endsWith(".js")||uri.endsWith(".html")){
            return false;
        }
        return true;
    }
}
