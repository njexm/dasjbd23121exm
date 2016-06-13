package com.proem.exm.controller.basic.branch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.entity.utils.AreaService;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.views.branch.BranchsView;

/**
 * @author DeFei 分店管理
 */

@Controller
@RequestMapping("branch/branchDo")
public class BranchController extends BaseController {

	@Autowired
	BranchService branchService;
	@Autowired
	ZcUserInfoService zcUserInfoService;
	@Autowired
	AreaService areaService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	LogManageService logManageService;

	@InitBinder("branch")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branch.");
	}

	@InitBinder("branchsView")
	public void branchsView(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchsView.");
	}
	
	@InitBinder("zcUserInfo")
	public void initZcUserInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcUserInfo.");
	}

	@InitBinder("zcZoning")
	public void initZcZoning(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcZoning.");
	}

	/**
	 * 分店初始化页面
	 * 
	 * @param request
	 * @param response
	 * @param model 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "init")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String faName=request.getParameter("faName");
		 if(StringUtils.isBlank(faName)){
			 faName="仓库管理";
		 }else{
		 faName= new String(faName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String fasonName=request.getParameter("fasonName");
		 if(StringUtils.isBlank(fasonName)){
			 fasonName="仓库管理";
		 }else{
		fasonName= new String(fasonName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String sonName=request.getParameter("sonName");
		 if(StringUtils.isBlank(sonName)){
			 sonName="仓库管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/branchson/branchson_list");
		return view;
	}

	/**
	 * 新增分店页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addBranch(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeViewIncludeMap(
				"basic/branchson/branchson_add", initLoadPage());
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
	public ModelAndView editBranch(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Branch bv = (Branch) branchService.getObjById(id, "Branch");
		model.addAttribute("branchsView", bv);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/branchson/branchson_edit", initLoadPage());
		return view;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	private Map initLoadPage() {
		Map returnMap = new HashMap();
//		returnMap.put("UserList", zcUserInfoService.getUserInfoList());
		return returnMap;
	}

	/**
	 * 分店列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchsJson(@ModelAttribute BranchsView branchsView,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = branchService.getPagedDataGridObj(page, branchsView);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public AjaxResult save(@ModelAttribute Branch branch,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			branch.setId(id);
			zcZoningService.saveObj(branch.getZcZoning());
			// String userId=branchsView.getBranch_person_name();
			// ZcUserInfo userInfo=(ZcUserInfo)
			// zcUserInfoService.getObjById(userId,"ZcUserInfo");
			// branch.setCustomer(userInfo);
			branch.setDelFlag("0");
			branch.setApproveHouse("否");
			// 负责人暂未处理
			branchService.saveObj(branch);
			logManageService.insertLog(request, "添加了一条仓库信息", "仓库管理");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute Branch branch,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String zcZoningId = branch.getZcZoning().getId();
			if (zcZoningId == null || StringUtils.isBlank(zcZoningId)) {
				branch.getZcZoning().setId(UuidUtils.getUUID());
				zcZoningService.saveObj(branch.getZcZoning());
			} else {
				zcZoningService.updateObj(branch.getZcZoning());
			}
			// Branch branch = new Branch();
			// branch.setId(branchsView.getId());
			// branch.setBranch_code(branchsView.getBranch_code());
			// branch.setBranch_name(branchsView.getBranch_name());
			// branch.setBranch_address(branchsView.getBranch_address());
			// String userId=branchsView.getBranch_person_name();
			// ZcUserInfo userInfo=(ZcUserInfo)
			// zcUserInfoService.getObjById(userId,"ZcUserInfo");
			// branch.setCustomer(userInfo);
			branch.setDelFlag("0");
			branchService.updateObj(branch);
			logManageService.insertLog(request, "修改了一条仓库信息", "分店管理");
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
	 * 
	 * 方法说明：(公司管理删除)
	 * 
	 * @param 参数名称
	 *            ：参数含义
	 * @return 返回值类型
	 * @Exception 异常对象
	 * 
	 * @author:PSL
	 * @date:2015年6月5日
	 */
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBranch(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
					Branch branch = (Branch) branchService.getObjById(Id,"Branch");
					if (branch == null) {
						branch = new Branch();
					}
					String zcZoningId = (branch.getZcZoning() == null ? new ZcZoning(): branch.getZcZoning()).getId() == null ? "": (branch.getZcZoning() == null ? new ZcZoning(): branch.getZcZoning()).getId();
					branchService.deleteObjById(Id, "Branch");
					zcZoningService.deleteObjById(zcZoningId, "ZcZoning");
				} catch (Exception e) {
					ajaxResult = new AjaxResult(AjaxResult.DELETE,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}

			}

			logManageService.insertLog(request, "删除了仓库信息", "仓库管理");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			return ajaxResult;
		}
		ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
				AjaxResult.INFO);
		return ajaxResult;
	}

	/**
	 * 获取盘点仓库
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "selectListJson", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List selectListJson(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = branchService.getObjList(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 编辑页面展示父级菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String,Object>> getTreeData(HttpServletRequest request,HttpServletResponse response){
		return branchService.getTreeData();
	}
	
	/**
	 * 设置默认仓库
	 */
	@RequestMapping(value = "setApproveHouse", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult setApproveHouse(HttpServletRequest request,String id) {
		AjaxResult ajaxResult = null;
		if (id != null ) {
			try {
				List<Branch> branchList = branchService.getListByObj(Branch.class, " APPROVEHOUSE = '是' ");
				if(branchList!=null && branchList.size()>0){
					Branch branch = branchList.get(0);
					branch.setApproveHouse("否");
					branchService.updateObj(branch);
				}
				Branch branch = (Branch) branchService.getObjById(id, "Branch");
				branch.setApproveHouse("是");
				branchService.updateObj(branch);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult = new AjaxResult(AjaxResult.DELETE,
						AjaxResult.FAIL, AjaxResult.INFO);
				return ajaxResult;
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			return ajaxResult;
		}
		ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
				AjaxResult.INFO);
		return ajaxResult;
	}
	
}
