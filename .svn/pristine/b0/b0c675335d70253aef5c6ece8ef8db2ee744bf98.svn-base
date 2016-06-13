package com.proem.exm.controller.system;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.associator.WorkStation;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.system.CtpRole;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.entity.utils.Area;
import com.proem.exm.entity.utils.AreaService;
import com.proem.exm.service.branch.BranchTotalService;
import com.proem.exm.service.system.CtpRoleService;
import com.proem.exm.service.system.CtpUserService;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.MD5Util;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.Result;
import com.proem.exm.utils.Result.Status;

/**
 * 用户管理
 * 
 * @author psl
 * 
 * @com proem
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	ZcUserInfoService zcUserInfoService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	CtpUserService ctpUserService;
	@Autowired
	CtpRoleService ctpRoleService;
	@Autowired
	AreaService areaService;
	@Autowired
	ZcUserInfoService zaInfoService;
	@Autowired
	LogManageService logManageService;
	@Autowired
	BranchTotalService branchTotalService;

	@InitBinder("zcUserInfo")
	public void initZcUserInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcUserInfo.");
	}

	@InitBinder("ctpRole")
	public void initRole(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ctpRole.");
	}

	@InitBinder("ctpUser")
	public void initLoginUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ctpUser.");
	}

	@InitBinder("zcZoning")
	public void initZoning(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcZoning.");
	}

	@InitBinder("branchTotal")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchTotal.");
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
			sonName = "用户管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("system/userinfo/userinfo_list");
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
		ModelAndView view = createIframeViewIncludeMap(
				"system/userinfo/userinfo_add", initLoadPage());
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
		String userId = request.getParameter("id");
		ZcUserInfo zcUserInfo = (ZcUserInfo) zcUserInfoService.getObjById(
				userId, "ZcUserInfo");
		model.addAttribute("zcUserInfo", zcUserInfo);
		ModelAndView view = createIframeViewIncludeMap(
				"system/userinfo/userinfo_edit", initLoadPage());

		return view;
	}

	@RequestMapping(value = "editDetail")
	public ModelAndView editDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcUserInfo zcUserInfoSession = (ZcUserInfo) request.getSession()
				.getAttribute("userInfo");
		String userId = zcUserInfoSession.getId();
		ZcUserInfo zcUserInfo = (ZcUserInfo) zcUserInfoService.getObjById(
				userId, "ZcUserInfo");
		model.addAttribute("zcUserInfo", zcUserInfo);
		ModelAndView view = createIframeView("system/userinfo/userinfo_edit");
		return view;
	}

	/**
	 * 修改权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "openAuthority")
	public ModelAndView editAuthority(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String userId = request.getParameter("id");
		ZcUserInfo zcUserInfo = (ZcUserInfo) zcUserInfoService.getObjById(
				userId, "ZcUserInfo");
		model.addAttribute("zcUserInfo", zcUserInfo);
		ModelAndView view = createIframeView("system/userinfo/userinfo_openAuthority");
		return view;
	}

	@RequestMapping(value = "importArea")
	public void importArea(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> listResult = getAllByExcel();
		List<Area> listSupply = (List<Area>) listResult.get("listArea");
		for (int i = 0; i < listSupply.size(); i++) {
			Area supplyForecast = listSupply.get(i);
			areaService.saveObj(supplyForecast);
		}
		logManageService.insertLog(request, "导入了区域", "区域内容");
	}

	@SuppressWarnings("deprecation")
	private Map<String, Object> getAllByExcel() {
		Map returnMap = new HashMap();
		List<Area> list = new ArrayList<Area>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					"D:/Downloads/importArea.xls"));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 2; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// System.out.println(row.getCell(0).toString());
				if (row != null) {
					Area area = new Area();
					// 品名
					HSSFCell id = row.getCell(0);
					area.setId(getValue(id) == null ? "" : getValue(id));
					HSSFCell name = row.getCell(1);
					area.setAreaName(getValue(name) == null ? ""
							: getValue(name));
					HSSFCell parentId = row.getCell(2);
					area.setParentId(getValue(parentId) == null ? ""
							: getValue(parentId));
					HSSFCell shortName = row.getCell(3);
					area.setShortName(getValue(shortName) == null ? ""
							: getValue(shortName));
					HSSFCell levelType = row.getCell(4);
					area.setLevelType(getValue(levelType) == null ? ""
							: getValue(levelType));
					HSSFCell cityCode = row.getCell(5);
					area.setCityCode(getValue(cityCode) == null ? ""
							: getValue(cityCode));
					HSSFCell zipCode = row.getCell(6);
					area.setZipCode(getValue(zipCode) == null ? ""
							: getValue(zipCode));
					HSSFCell quancheng = row.getCell(7);
					area.setQuanCheng(getValue(quancheng) == null ? ""
							: getValue(quancheng));
					HSSFCell lng = row.getCell(8);
					area.setLng(getValue(lng) == null ? "" : getValue(lng));
					HSSFCell lat = row.getCell(9);
					area.setLat(getValue(lat) == null ? "" : getValue(lat));
					HSSFCell pinyin = row.getCell(10);
					area.setPinyin(getValue(pinyin) == null ? ""
							: getValue(pinyin));
					area.setCreateTime(new Date());
					area.setUpdateTime(new Date());
					list.add(area);
				}
			}
			returnMap.put("listArea", list);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell != null) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				// 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} else {
			return null;
		}
	}

	private Map initLoadPage() {
		Map returnMap = new HashMap();
		returnMap.put("RoleList", ctpRoleService.getRoleInfoList());
		return returnMap;
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
	public DataGrid listBranchsJson(@ModelAttribute ZcUserInfo zcUserInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = zcUserInfoService.getPagedDataGridObj(page, zcUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 按用户名查询
	 * 
	 * @param zcUserInfo
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listOfJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchsOfJson(@ModelAttribute ZcUserInfo zcUserInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			// String providerId = request.getParameter("providerId");
			dataGrid = zaInfoService.getPagedDataOfGridObj(page, zcUserInfo);
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
	public AjaxResult save(@ModelAttribute ZcUserInfo zcUserInfo,
			ZcZoning zcZoning, CtpRole ctpRole, CtpUser ctpUser,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String branchId = zcUserInfo.getBranch_name().getId();
			BranchTotal branchTotal = (BranchTotal) zcUserInfoService
					.getObjById(branchId, "BranchTotal");
			String zoNingID = String.valueOf(UUID.randomUUID())
					.replace("-", "");
			zcUserInfo.getZcZoning().setId(zoNingID);
			String UserId = String.valueOf(UUID.randomUUID()).replace("-", "");
			ctpUser.setId(UserId);
			String UserInfoId = String.valueOf(UUID.randomUUID()).replace("-",
					"");
			zcUserInfo.setId(UserInfoId);
			//
			zcUserInfo.setBranch_name(branchTotal);
			zcZoningService.saveObj(zcUserInfo.getZcZoning());
			ctpUser.setPassword(MD5Util.stringToMD5("888888"));
			ctpUserService.saveObj(ctpUser);
			// zcZoningService.saveOrUpdate(zcZoning);
			// ctpUserService.saveOrUpdate(ctpUser);
			// zcUserInfo.setZcZoning(zcZoning);
			zcUserInfo.setCtpUser(ctpUser);
			// zcUserInfo.getZcZoning().setId(zcZoning.getId());
			// zcUserInfo.getCtpUser().setId(ctpUser.getId());
			zcUserInfoService.saveObj(zcUserInfo);
			logManageService.insertLog(request, "添加了用户信息", "用户管理");
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
	public AjaxResult updateUserInfo(@ModelAttribute ZcUserInfo zcUserInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			zcZoningService.updateObj(zcUserInfo.getZcZoning());
			zcUserInfo.setZcZoning(zcUserInfo.getZcZoning());
			zcUserInfo.setCtpUser(zcUserInfo.getCtpUser());
			// zcUserInfo.getZcZoning().setId(zcZoning.getId());
			// zcUserInfo.getCtpUser().setId(ctpUser.getId());
			zcUserInfoService.updateObj(zcUserInfo);
			logManageService.insertLog(request, "修改了用户信息", "用户管理");
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
		String userId = request.getParameter("id");
		ZcUserInfo zcUserInfo = (ZcUserInfo) zcUserInfoService.getObjById(
				userId, "ZcUserInfo");
		String zoningId = (zcUserInfo.getZcZoning() == null ? new ZcZoning()
				: zcUserInfo.getZcZoning()).getId() == null ? "" : (zcUserInfo
				.getZcZoning() == null ? new ZcZoning() : zcUserInfo
				.getZcZoning()).getId();
		String loginUserId = (zcUserInfo.getCtpUser() == null ? new CtpUser()
				: zcUserInfo.getCtpUser()).getId() == null ? ""
				: (zcUserInfo.getCtpUser() == null ? new CtpUser() : zcUserInfo
						.getCtpUser()).getId();
		AjaxResult ajaxResult = null;
		try {

			// ctpUser.setPassword(MD5Util.stringToMD5("888888"));
			// zcUserInfo.getZcZoning().setId(zcZoning.getId());
			// zcUserInfo.getCtpUser().setId(ctpUser.getId());
			zcUserInfoService.deleteObjById(userId, "ZcUserInfo");
			zcZoningService.deleteObjById(zoningId, "ZcZoning");
			ctpUserService.deleteObjById(loginUserId, "CtpUser");
			logManageService.insertLog(request, "删除了用户", "用户管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	/**
	 * 根据用户ID获取已分配角色
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "getUserRolesByUserId", produces = "application/json")
	@ResponseBody
	public List<Root> getUserRolesByUserId(String userId) {
		List<Root> userRoles = ctpUserService.getUserRoles(userId);
		return userRoles;
	}

	// user表和role表想关联
	@RequestMapping(value = "updateUserRole", produces = "application/json")
	@ResponseBody
	public AjaxResult updateUserRole(String userId, String roleId) {
		AjaxResult ajaxResult = null;
		try {
			ctpUserService.updateUserRole(userId, roleId);
			ajaxResult = new AjaxResult(AjaxResult.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.FAIL);
		}
		return ajaxResult;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userName
	 * @return
	 */
	// @RequestMapping(value="/reSetPassword",method = RequestMethod.GET)
	// @ResponseBody
	// public Result reSetPassword(@ModelAttribute ZcUserInfo zcUserInfo,Model
	// model)
	// {
	// String idList=zcUserInfo.getId();
	// String[] ids=idList.split(",");
	// try {
	// //获取登录用户信息
	// if(ids!=null && ids.length>0){
	// for(int i=0;i<ids.length;i++){
	// String memberid=ids[i];
	// Member memberNew=memberService.selectById(memberid);
	// if(memberNew==null){
	// memberNew=new Member();
	// memberNew.setUser(new User());
	// }
	// if(memberNew.getUser()==null){
	// memberNew.setUser(new User());
	// }
	// String
	// id=memberNew.getUser().getId()==null?"":memberNew.getUser().getId();
	// User user=new User();
	// user.setId(id);
	// user.setUpdateTime(new Date());
	// user.setUpdateUserId(currUser.getId());
	// user.setPassword(MD5Util.stringToMD5("888888"));
	// int l = userService.updateByIdSelective(user);
	// if (l < 0 || l==0) {
	// return new Result(Status.ERROR,"");
	// }
	// }
	// return new Result(Status.OK, "");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return new Result(Status.ERROR,"");
	// }

	/**
	 * 
	 * 方法说明：(验证修改密码时输入密码的正确性)
	 * 
	 * @param 参数名称
	 *            ：参数含义
	 * @return Result
	 * @Exception 异常对象
	 * 
	 * @author:acer
	 * @date:2015年8月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/validateInitPass", method = RequestMethod.GET)
	public Result validateInitPass(String oldpassword,
			HttpServletRequest request) {
		Result result = new Result();
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		String testPass = MD5Util.stringToMD5(oldpassword);
		String passWord = ctpUser.getPassword();
		if (passWord.equals(testPass)) {
			result = new Result(Status.OK, "");
		} else {
			result = new Result(Status.ERROR, "");
		}
		return result;
	}

	/**
	 * 
	 * 方法说明：(重置用户密码)
	 * 
	 * @param 参数名称
	 *            ：参数含义
	 * @return Result
	 * @Exception 异常对象
	 * 
	 * @author:acer
	 * @date:2015年8月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public Result resetPassword(String password, HttpServletRequest request) {
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			ctpUser.setPassword(MD5Util.stringToMD5(password));
			ctpUserService.updateObj(ctpUser);
			return new Result(Status.OK, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(Status.ERROR, "");
	}

	/**
	 * 
	 * 方法说明：(通过系统管理重置用户密码)
	 * 
	 * @param userId
	 *            ：用户id
	 * 
	 * @author:WT
	 * @date:2015年8月24日
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPasswordByAdmin", method = RequestMethod.POST)
	public Result resetPasswordByAdmin(String userId) {
		Result result = new Result();
		result.setStatus(Status.ERROR);
		try {
			if (StringUtils.isNotEmpty(userId)) {
				// User user = userService.selectById(userId);
				// if (null != user) {
				// user.setPassword(MD5Util.stringToMD5("888888"));
				// user.setUpdateTime(new Date());
				// user.setUpdateUserId(userId);
				// userService.updateByIdSelective(user);
				result.setStatus(Status.OK);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "selectListJson", produces = "application/json")
	@ResponseBody
	public List selectListJson(String type, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = zcUserInfoService.getUserInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listCheckManJson", produces = "application/json")
	@ResponseBody
	public List listCheckManJson(@ModelAttribute ZcUserInfo zcUserInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = zcUserInfoService.getObjList1(zcUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listWorkStation", produces = "application/json")
	@ResponseBody
	public List listWorkStation(@ModelAttribute WorkStation workStation,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = zcUserInfoService.getObjList2(workStation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "selectBranchListJson", produces = "application/json")
	@ResponseBody
	public List selectBranchListJson(String type, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		// CommodityClassify commodityClassify = new CommodityClassify();
		BranchTotal branchTotal = new BranchTotal();
		List dataGrid = null;
		try {
			// commodityClassify.setClassify_type(type);
			// dataGrid =
			// commodityClassifyService.getObjList(commodityClassify);
			dataGrid = branchTotalService.getObjList(branchTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
