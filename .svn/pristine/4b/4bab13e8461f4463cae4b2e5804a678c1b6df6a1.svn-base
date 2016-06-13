package com.proem.exm.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cisdi.ctp.model.gen.User;
import com.cisdi.ctp.web.security.service.SecurityService;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private RequestCache requestCache;
	
	/**
	 * Spring Security 资源白名单
	 */
	private String exclusions;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
    	String[] exclusionsArray = exclusions.split(",");
    	if(null!=exclusionsArray && exclusionsArray.length>0){
    		for(String exclusion : exclusionsArray){
    			Pattern pattern = Pattern.compile(exclusion);
    	        Matcher matcher = pattern.matcher(url);
    	        if(matcher.matches()){
    	        	System.out.println("Spring Security 资源白名单 : "+exclusion);
    	        	return true;
    	        }
    		}
    	}
        
        if(null == session){
        	return false;
        }
        User user = (User) session.getAttribute("user");
        if(null == user){
        	return false;
        }
        
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            //System.out.println(savedRequest.getRedirectUrl());
            session.setAttribute("savedURL", savedRequest.getRedirectUrl());
        }
        System.out.println("url========"+url);
        
        Object object = session.getAttribute("security-calmis");
        boolean hasSecurity = false;
        if(object!=null){
        	hasSecurity = (Boolean)object;
        }
        if(!hasSecurity){
        	securityService.securityInterface(user.getName(), request, response, session);
        	session.setAttribute("security-calmis", true);
        	
        	//加载系统菜单
        	//indexService.initMenu(session);
        	// List<Module> menuList = this.getIndexUserModule(userId, "MENU");
			// session.setAttribute("menuList", JSONUtil.toJSONString(menuList));
        }
        return true;
    }
	
	public String getExclusions() {
		return exclusions;
	}
	
	@Autowired
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
}
