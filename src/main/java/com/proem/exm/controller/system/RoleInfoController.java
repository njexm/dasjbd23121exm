package com.proem.exm.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.auth.po.Role;
import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.service.system.CtpRoleService;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 权限管理
 * 
 * @author psl
 * 
 * @com proem
 */
@Controller
@RequestMapping("/roleInfo")
public class RoleInfoController extends BaseController {

	@Autowired
	CtpRoleService ctpRoleService;
	@Autowired
	LogManageService logManageService;

	@InitBinder("ctpRole")
	public void initRole(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ctpRole.");
	}

	/**
	 * 初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "系统设置";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "系统管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "权限管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("system/roleinfo/roleinfo_list");
		return view;
	}

	/**
	 * 新增供应商基本信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addUsePage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("system/roleinfo/roleinfo_add");
		return view;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "edit")
	public ModelAndView editUsePage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CtpRole ctpRole = (CtpRole) ctpRoleService.getObjById(id, "CtpRole");
		model.addAttribute("ctpRole", ctpRole);
		ModelAndView view = createIframeView("system/roleinfo/roleinfo_edit");
		return view;
	}

	/**
	 * 供应商基本信息列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchsJson(@ModelAttribute CtpRole ctpRole,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ctpRoleService.getPagedDataGridObj(page, ctpRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute CtpRole ctpRole,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		try {
			if (id != null && id != "") {
				ctpRoleService.updateObj(ctpRole);
				logManageService.insertLog(request, "修改了角色", "角色管理");
			} else {
				String roleId = String.valueOf(UUID.randomUUID()).replace("-",
						"");
				ctpRole.setId(roleId);
				ctpRoleService.saveObj(ctpRole);
				logManageService.insertLog(request, "添加了角色", "角色管理");
			}
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 修改数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateUserInfo(@ModelAttribute CtpRole ctpRole,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			ctpRoleService.updateObj(ctpRole);
			logManageService.insertLog(request, "修改了角色", "角色管理");
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 删除数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		AjaxResult ajaxResult = null;
		try {
			// ctpUser.setPassword(MD5Util.stringToMD5("888888"));
			// zcUserInfo.getZcZoning().setId(zcZoning.getId());
			// zcUserInfo.getCtpUser().setId(ctpUser.getId());
			ctpRoleService.deleteObjById(id, "CtpRole");
			logManageService.insertLog(request, "删除了角色", "角色管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	@RequestMapping(value = "allList", produces = "application/json")
	@ResponseBody
	public List<CtpRole> allList(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		List<CtpRole> list = ctpRoleService.getRoleList();
		return list;
	}

	@RequestMapping(value = "saveGrantModule")
	@ResponseBody
	public AjaxResult saveGrantModule(HttpServletRequest request,
			HttpServletResponse response, String roleId, String moduleIds) {
		AjaxResult ajaxResult = null;
		try {
			ctpRoleService.saveOrUpdateAuthRole(roleId, moduleIds);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "roleztree")
	public ModelAndView roleZtree(HttpServletRequest request,
			HttpServletResponse response, String id) {
		ModelAndView view = createLayoutView("system/role_tree.vm");
		Role role = null;
		if (!StringUtils.isBlank(id)) {
			role = (Role) ctpRoleService.getObjById(id, Role.class.getName());
		}
		view.addObject("role", role);
		return view;
	}

	@RequestMapping(value = "getGrantModuleId")
	@ResponseBody
	public List<Map<String, Object>> getGrantModuleId(
			HttpServletRequest request, HttpServletResponse response,
			String roleId) {
		List<Map<String, Object>> list = null;
		try {
			list = ctpRoleService.getGrantModuleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
}
