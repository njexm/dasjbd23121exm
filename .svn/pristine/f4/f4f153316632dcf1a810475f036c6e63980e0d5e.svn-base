package com.proem.exm.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import sun.beans.editors.StringEditor;

import com.proem.exm.service.system.LogManageService;
import com.proem.exm.utils.DateEditor;
import com.proem.exm.utils.MyCustomNumberEditor;

public class BaseController {
	@Autowired
	protected
	LogManageService logManageService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());     
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	    binder.registerCustomEditor(Integer.class, new MyCustomNumberEditor(Integer.class)); 
	    binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    
	    binder.registerCustomEditor(String.class, new StringEditor());
	  
	}
	
	
	protected ModelAndView createSingleView(String path) {
		ModelAndView view = new ModelAndView();
		view.setViewName(path);
		return view;
	}

	protected ModelAndView createLayoutView(String path) {
		return createLayoutView(path, null);
	}

	protected ModelAndView createLayoutView(String path, String layout) {
		ModelAndView view = new ModelAndView();
		if (layout == null)
			view.setViewName("common/layout");
		else
			view.setViewName(layout);
		view.addObject("resource_path", "common/resource.vm");
		view.addObject("header_path", "common/header.vm");
		view.addObject("left_path", "common/left.vm");
		view.addObject("footer_path", "common/footer.vm");
		view.addObject("init_path", "common/init.vm");
		view.addObject("content_path", path);
		return view;
	}
	
	/**
	 * iframe 引用， 菜单路径访问配置，访问初始化页面路径
	 * @param initPagepath
	 * @return
	 */
	protected ModelAndView createIframeView(String initPagepath) {
		ModelAndView view = new ModelAndView(initPagepath);
		view.addObject("resource_path", "common/resource.vm");
		view.addObject("init_path", "common/init.vm");
		return view;
	}
	
	/**
	 * iframe 引用， 菜单路径访问配置，访问初始化页面路径(包涵参数)
	 * @param initPagepath
	 * @return
	 */
	protected ModelAndView createIframeViewIncludeMap(String initPagepath,Map map) {
		ModelAndView view = new ModelAndView(initPagepath,"map",map);
		view.addObject("resource_path", "common/resource.vm");
		return view;
	}
}
