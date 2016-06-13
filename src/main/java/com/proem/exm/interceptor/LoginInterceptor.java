package com.proem.exm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cisdi.ctp.model.gen.User;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception
    {
        String URL = request.getRequestURI().toString();
        User user = (User) request.getSession().getAttribute("user");
        
        if (URL.endsWith("/login/index"))
        {
            //request.getSession().removeAttribute("user");
        }
        
        if (user == null && !URL.contains("login") && !URL.contains("resources"))
        {
        	System.out.println("被拦截的URL-->"+URL);
            response.sendRedirect(request.getContextPath() + "/login/index");
            return false;
        }
        
        return true;
    }
}
