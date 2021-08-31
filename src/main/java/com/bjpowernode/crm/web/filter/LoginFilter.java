package com.bjpowernode.crm.web.filter;

import com.bjpowernode.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登录过的过滤器");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getServletPath();
        //不应该拦截的资源，自动放行请求
        if ("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest,servletResponse);

        //其他资源必须验证有没有登录过
        }else{
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            //如果user不为空，说明登录过
            if (user != null){
                //放行
                filterChain.doFilter(servletRequest,servletResponse);

                //没有登录过
            }else{
                //使用重定向登录页，不能使用转发，转发会保留旧地址
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        }
    }
}
