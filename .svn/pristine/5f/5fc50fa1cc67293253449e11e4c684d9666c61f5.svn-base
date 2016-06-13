package com.proem.exm.controller.menuManage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.model.gen.Module;
import com.proem.exm.controller.BaseController;
import com.proem.exm.service.menuManage.MenuManageService;

@Controller
@RequestMapping(value = "/menuManage")
public class MenuManageController extends BaseController{
	
	@Resource
	private MenuManageService menuManageService;
	
	@RequestMapping(value = "/init")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		return createSingleView("menuManage/menuManage");
	}

	@RequestMapping(value = "/saveOrUpdateMenu")
	@ResponseBody
	public String saveOrUpdateMenu(Module module,HttpServletRequest request, HttpServletResponse response) {
		boolean flag = menuManageService.saveOrUpdateMenu(module);
		if(flag == true){
			return "success";
		}
		return "false";
	}
	
	@RequestMapping(value = "/deleteMenuById")
	@ResponseBody
	public String deleteMenuById(Module module,HttpServletRequest request, HttpServletResponse response){
		boolean flag = menuManageService.deleteMenuById(module);
		if(flag == true){
			return "success";
		}
		return "false";
	}
	
	@RequestMapping(value = "/getTreeNodes")
	@ResponseBody
	public List<Module> getTreeNodes(HttpServletRequest request,
			HttpServletResponse response){
		List<Module> list = menuManageService.getMenuList();
		return list;
	}
	
	@RequestMapping(value = "/selectMenuDialog")
	public ModelAndView selectMenuDialog(HttpServletRequest request,
			HttpServletResponse response){
		return createSingleView("menuManage/selectMenuDialog");
	}
}
