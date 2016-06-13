package com.proem.exm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.model.gen.Module;
import com.cisdi.ctp.utils.common.StringUtils;

@Controller
public class IndexController extends BaseController {
	
	/*@RequestMapping( value = "/" )
	public ModelAndView indexK(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/index");
		return mv;
	}*/

	@RequestMapping( value = "index_left" )
	public ModelAndView index_with_left(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = createLayoutView("common/index");
		String type = request.getParameter("type");
		view.addObject("type", type);
		return view;
	}
	
	
	
	
	
	@RequestMapping( value = "index" )
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = createSessionLayoutView("common/index_layout","common/index_layout",request,null);
		return view;
	}
	
	@RequestMapping( value = "index_menu" )
	public ModelAndView index_menu(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		Object security=session.getAttribute("security");
		List<Module> list=(List<Module>) security;
		List<Module> menuList=new ArrayList<Module>();
		
		String parentId = request.getParameter("parentId");
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Module module=new Module();
				String parent=list.get(i).getId();
				if(parent.equals(parentId)){
					 module=list.get(i);
					String name=module.getModuleName();
					if("首页".equals(name)){
						ModelAndView view = createSessionLayoutView("common/index_layout","common/index_layout",request,null);
						return view;
					}else{
						menuList.add(module);
					}
				}
			}
		}
		ModelAndView view = createSessionLayoutView("common/index_layout",null,request,menuList.get(0));
		view.addObject("type", parentId);
//		view.addObject("moduleFa", module);
		return view;
	}
	
	
	
	protected ModelAndView createSessionLayoutView(String path, String layout,HttpServletRequest request,Object object) {
		HttpSession session=request.getSession();
		ModelAndView view = new ModelAndView();
		if (layout == null){
			view.setViewName("common/layout");
		}else{
			view.setViewName(layout);
		}
		if (object == null){
			view.addObject("securitySon", object);
			session.setAttribute("securitySon", object);
		}else{
			view.addObject("securitySon", object);
			session.setAttribute("securitySon", object);
		}
		Object user=session.getAttribute("user");
		Object userInfo=session.getAttribute("userInfo");
		Object security=session.getAttribute("security");
		
		view.addObject("security", security);
		view.addObject("user", user);
		view.addObject("userInfo", userInfo);
		view.addObject("resource_path", "common/resource.vm");
		view.addObject("header_path", "common/header.vm");
		view.addObject("left_path", "common/left.vm");
		view.addObject("footer_path", "common/footer.vm");
		view.addObject("init_path", "common/init.vm");
		view.addObject("content_path", path);
		return view;
	}
	

	@RequestMapping("indexLayout")
	public ModelAndView indexLayout(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		Object securitySon=session.getAttribute("securitySon");
		if(securitySon!=null ){
			Module list=(Module) securitySon;
			if(list!=null){
				Module module=list.getChildren().get(0);
				String path="";
				if(StringUtils.isBlank(module.getChildren().get(0).getTarget())){
					path="redirect:"+"/order/orders/init";
				}else{
					path="redirect:"+module.getChildren().get(0).getTarget()+"";
				}
				ModelAndView view = createIframeView(path);
				return view;
			}
		}
		ModelAndView view = createIframeView("common/index");
		return view;
	}
	
	@RequestMapping(  value = "/" )
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView view = createLayoutView("login/login.vm");
		ModelAndView view = new ModelAndView("login/login");
		view.addObject("resource_path", "common/resource.vm");
		view.addObject("init_path", "common/init.vm");
		return view;
	}

}
