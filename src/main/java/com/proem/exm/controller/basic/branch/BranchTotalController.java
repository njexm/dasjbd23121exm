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
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.entity.utils.AreaService;
import com.proem.exm.service.branch.BranchTotalService;
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
@RequestMapping("branchTotal")
public class BranchTotalController extends BaseController {

	@Autowired
	BranchTotalService branchTotalService;
	@Autowired
	ZcUserInfoService zcUserInfoService;
	@Autowired
	AreaService areaService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	LogManageService logManageService;

	@InitBinder("branchTotal")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchTotal.");
	}
	
	@InitBinder("branchsView")
	public void initBranchsView(WebDataBinder binder) {
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
			 faName="基础档案";
		 }else{
		 faName= new String(faName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String fasonName=request.getParameter("fasonName");
		 if(StringUtils.isBlank(fasonName)){
			 fasonName="档案管理";
		 }else{
		fasonName= new String(fasonName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String sonName=request.getParameter("sonName");
		 if(StringUtils.isBlank(sonName)){
			 sonName="分店管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/branch/branch_list");
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
				"basic/branch/branch_add", initLoadPage());
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
		BranchTotal bv = (BranchTotal) branchTotalService.getObjById(id, "BranchTotal");
		model.addAttribute("branchsView", bv);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/branch/branch_edit", initLoadPage());
		return view;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	private Map initLoadPage() {
		Map returnMap = new HashMap();
		returnMap.put("UserList", zcUserInfoService.getUserInfoList());
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
			dataGrid = branchTotalService.getPagedDataGridObj(page, branchsView);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}
	@RequestMapping(value = "selectBranchListJson", produces = "application/json")
	@ResponseBody
	public List selectBranchListJson(BranchTotal branchTotal, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		
		List dataGrid = null;
		try {			
			dataGrid = branchTotalService.getObjList1(branchTotal);
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
	public AjaxResult save(@ModelAttribute BranchTotal branchTotal,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			branchTotal.setId(id);
			zcZoningService.saveObj(branchTotal.getZcZoning());
			// String userId=branchsView.getBranch_person_name();
			// ZcUserInfo userInfo=(ZcUserInfo)
			// zcUserInfoService.getObjById(userId,"ZcUserInfo");
			// branch.setCustomer(userInfo);
			branchTotal.setDelFlag("0");
			// 负责人暂未处理
			branchTotalService.saveObj(branchTotal);
			logManageService.insertLog(request, "添加了一条分店信息", "分店管理");
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
	public AjaxResult update(@ModelAttribute BranchTotal branchTotal,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String zcZoningId = branchTotal.getZcZoning().getId();
			if (zcZoningId == null || StringUtils.isBlank(zcZoningId)) {
				branchTotal.getZcZoning().setId(UuidUtils.getUUID());
				zcZoningService.saveObj(branchTotal.getZcZoning());
			} else {
				zcZoningService.updateObj(branchTotal.getZcZoning());
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
			branchTotal.setDelFlag("0");
			branchTotalService.updateObj(branchTotal);
			logManageService.insertLog(request, "修改了一条分店信息", "分店管理");
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
					BranchTotal branch = (BranchTotal) branchTotalService.getObjById(Id,
							"BranchTotal");
					if (branch == null) {
						branch = new BranchTotal();
					}
					String zcZoningId = (branch.getZcZoning() == null ? new ZcZoning()
							: branch.getZcZoning()).getId() == null ? ""
							: (branch.getZcZoning() == null ? new ZcZoning()
									: branch.getZcZoning()).getId();
					branchTotalService.deleteObjById(Id, "BranchTotal");
					zcZoningService.deleteObjById(zcZoningId, "ZcZoning");
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult = new AjaxResult(AjaxResult.DELETE,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}

			}

			logManageService.insertLog(request, "删除了分店信息", "分店管理");
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
			dataGrid = branchTotalService.getObjList(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	@RequestMapping(value = "country", produces = "application/json")
	@ResponseBody
	public List country(HttpServletRequest request, HttpServletResponse response) {
		List dataGrid = null;
		String id = request.getParameter("id");
		try {
			dataGrid = branchTotalService.getCountryList(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
}
