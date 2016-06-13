package com.proem.exm.controller.userManage;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.IUser;
import com.proem.exm.service.roleManage.RoleManageService;
import com.proem.exm.service.userManage.UserManageService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;

@Controller
@RequestMapping(value = "/userManage")
public class UserManageController extends BaseController{
	
	public static Logger logger = Logger.getLogger(UserManageController.class);
	
	@Resource
	private UserManageService userManageService;
	
	@Resource
	private RoleManageService roleManageService;

	@RequestMapping(value = "/init")
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response){
		return createSingleView("userManage/userManage");
	}
	
	@RequestMapping(value = "/openAddUserDlg")
	public ModelAndView openAddUserDlg(HttpServletRequest request,HttpServletResponse response){
		return createSingleView("userManage/editUser");
	}
	
	@RequestMapping(value = "/distributeRoleDlg")
	public ModelAndView distributeRoleDlg(HttpServletRequest request,HttpServletResponse response){
		return createSingleView("userManage/distributeRoleDlg");
	}
	
	
	@RequestMapping(value = "/openUpdateUserDlg")
	public ModelAndView openUpdateUserDlg(HttpServletRequest request,HttpServletResponse response,String id){
		IUser iuser = userManageService.getIUserById(id);
		ModelAndView view = new	ModelAndView("userManage/editUser");
		view.addObject("iuser", iuser);
		return view;
	}
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public String saveOrUpdate(HttpServletRequest request,HttpServletResponse response,IUser iuser){
		boolean flag = userManageService.saveOrUpdate(iuser);
		logger.debug("****************************saveOrUpdate********************************");
		if(flag == true){
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value = "/getUserDataGrid")
	public void getUserDataGrid(HttpServletRequest request,HttpServletResponse response,
			IUser iuser,SharePager sharePager){
		DataGrid dataGrid = userManageService.getUserDataGrid(iuser, sharePager);
		logger.debug("****************************getUserDataGrid********************************");
		JSONObject jsonObject = JSONObject.fromObject(dataGrid);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getExsitRoleDataGrid")
	public void getExsitRoleDataGrid(HttpServletRequest request,HttpServletResponse response,
			String userId,SharePager sharePager){
		DataGrid dataGrid = roleManageService.getExsitRoleDataGridByUserId(userId, sharePager);
		JSONObject jsonObject = JSONObject.fromObject(dataGrid);
		logger.debug("getExsitRoleDataGrid**************************");
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteUsers")
	@ResponseBody
	public String deleteUsers(HttpServletRequest request,HttpServletResponse response,String ids){
		logger.debug("****************************deleteUsers********************************");
		boolean flag = userManageService.deleteUser(ids);
		if(flag == true){
			return "success";
		}else{
			return "error";
		}
	}
	
	
	@RequestMapping(value = "/getIUserNameById")
	@ResponseBody
	public String getIUserNameById(HttpServletRequest request,HttpServletResponse response,String id){
		return userManageService.getIUserNameById(id);
	}
	
	@RequestMapping(value = "/saveDistributeRole")
	@ResponseBody
	public String saveDistributeRole(HttpServletRequest request,HttpServletResponse response,
			String roleJsonStr,String userId){
		logger.debug("****************************saveDistributeRole********************************");
		boolean flag = userManageService.saveDistributeRole(roleJsonStr, userId);
		if(flag == true){
			return "success";
		}else{
			return "error";
		}
	}
	
}
