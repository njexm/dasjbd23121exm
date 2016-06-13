package com.proem.exm.controller.settlement;

import java.util.List;

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

import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.settlement.AdvanceDetail;
import com.proem.exm.entity.settlement.BranchAdvance;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.settlement.AdvanceDetailService;
import com.proem.exm.service.settlement.BranchAdvanceService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 分店预收款控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("settlement/branchAdvance")
public class BranchAdvanceController extends BaseController {
	@Autowired
	BranchAdvanceService branchAdvanceService;
	@Autowired
	AdvanceDetailService advanceDetailService;

	@InitBinder("branchAdvance")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchAdvance.");
	}

	/**
	 * 初始化分店预付款页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "initAdvance")
	public ModelAndView initAdvance(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("settlement/advance/advance_list");
		return view;
	}

	/**
	 * 列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchAdvanceJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchAdvanceJson(
			@ModelAttribute BranchAdvance branchAdvance,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = branchAdvanceService.getPagedDataGridObj(page,
					branchAdvance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 初始化分店预付款页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "openWrite")
	public ModelAndView openWrite(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<AdvanceDetail> advanceDetailList = advanceDetailService
				.getListByObj(AdvanceDetail.class, "BRANCHADVANCE_ID is null");
		if (advanceDetailList != null && advanceDetailList.size() > 0) {
			for (int i = 0; i < advanceDetailList.size(); i++) {
				AdvanceDetail advanceDetail = advanceDetailList.get(i);
				advanceDetailService.deleteObjById(advanceDetail.getId(),
						AdvanceDetail.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/advance/advance_write");
		return view;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute Branch branch,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = branchAdvanceService.getObjList(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 删除预收款单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteWrite", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteWrite(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<AdvanceDetail> advanceDetailList = advanceDetailService
						.getListByObj(AdvanceDetail.class,
								" BRANCHADVANCE_ID = '" + ids[i] + "'");
				if (advanceDetailList != null && advanceDetailList.size() > 0) {
					for (int j = 0; j < advanceDetailList.size(); j++) {
						AdvanceDetail advanceDetail = advanceDetailList.get(j);
						advanceDetailService.deleteObjById(
								advanceDetail.getId(),
								AdvanceDetail.class.getName());
					}
				}
				branchAdvanceService.deleteObjById(ids[i],
						BranchAdvance.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的预收款单", "预收款");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
}
