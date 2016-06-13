package com.proem.exm.controller.roleManage;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.auth.po.Role;
import com.proem.exm.controller.BaseController;
import com.proem.exm.service.roleManage.RoleManageService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.HelpConfig;
import com.proem.exm.utils.JsonUtil;
import com.proem.exm.utils.SharePager;

@Controller
@RequestMapping(value = "/roleManage")
public class RoleManageController extends BaseController{
	
	@Resource
	private RoleManageService roleManageService;
	
	@RequestMapping(value = "/init")
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response){
		return createSingleView("roleManage/roleManage");
	}
	
	@RequestMapping(value = "/openAddRoleDlg")
	public ModelAndView openAddRoleDlg(HttpServletRequest request,HttpServletResponse response){
		return createSingleView("roleManage/editRole");
	}
	
	@RequestMapping(value = "/openMenuDlg")
	public ModelAndView openMenuDlg(HttpServletRequest request,HttpServletResponse response,String roleId){
		ModelAndView view = new ModelAndView("roleManage/distributeMenuDlg");
		List<String> menuIdList = roleManageService.getMenuIdListByRoleId(roleId);
		String menuIdjsonStr = JsonUtil.listToJson(menuIdList);
		menuIdjsonStr = menuIdjsonStr.replaceAll("\"", "'");
		view.addObject("menuIdjsonStr", menuIdjsonStr);
		return view;
	}
	
	@RequestMapping(value = "/openUpdateRoleDlg")
	public ModelAndView openUpdateRoleDlg(HttpServletRequest request,HttpServletResponse response,String id){
		Role role = roleManageService.getRoleById(id);
		ModelAndView view = new ModelAndView("roleManage/editRole");
		view.addObject("role", role);
		return view;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request,HttpServletResponse response,Role role){
		boolean flag = roleManageService.saveOrUpdate(role);
		if(flag == true){
			return "success";
		}else{
			return "false";
		}
	}
	
	@RequestMapping(value = "/getRoleDataGrid")
	public void getRoleDataGrid(HttpServletRequest request,HttpServletResponse response,
			Role role,SharePager sharePager){
		DataGrid dataGrid = roleManageService.getRoleDataGrid(role, sharePager);
		JsonConfig config = new HelpConfig();
		JSONObject jsonObject = JSONObject.fromObject(dataGrid,config);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteRoles")
	@ResponseBody
	public String deleteRoles(HttpServletRequest request,HttpServletResponse response,String ids){
		boolean flag = roleManageService.deleteRoles(ids);
		if(flag == true){
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value = "/saveTheCheckedMenu")
	@ResponseBody
	public String saveTheCheckedMenu(HttpServletRequest request,HttpServletResponse response,
			String roleId,String menuJsonStr){
		boolean flag = roleManageService.saveTheCheckedMenu(roleId, menuJsonStr);
		if(flag == true){
			return "success";
		}else{
			return "error";
		}
	}
	
}
