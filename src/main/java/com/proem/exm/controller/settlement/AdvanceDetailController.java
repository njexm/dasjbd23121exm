package com.proem.exm.controller.settlement;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.settlement.AdvanceDetail;
import com.proem.exm.entity.settlement.BranchAdvance;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.settlement.AdvanceDetailService;
import com.proem.exm.service.settlement.BranchAdvanceService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 预收款明细控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("settlement/advanceDetail")
public class AdvanceDetailController extends BaseController {
	@Autowired
	AdvanceDetailService advanceDetailService;
	@Autowired
	BranchAdvanceService branchAdvanceService;
	@Autowired
	BranchService branchService;

	@InitBinder("branchAdvance")
	public void initBranchAdvance(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchAdvance.");
	}

	@InitBinder("advanceDetail")
	public void initAdvanceDetail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("advanceDetail.");
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
			@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = advanceDetailService.getPagedDataGridObj(page,
					advanceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增一条记录
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "addRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addRecord(@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			AdvanceDetail advance = new AdvanceDetail();
			String id = UuidUtils.getUUID();
			advance.setId(id);
			advance.setOdd("YS");
			advance.setMoneyType("预收款");
			advanceDetailService.saveObj(advance);
			logManageService.insertLog(request, "新增了一条预收款记录", "预收款");
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
	 * 删除记录
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteRecord(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				advanceDetailService.deleteObjById(ids[i],
						AdvanceDetail.class.getName());
			}
			logManageService.insertLog(request, "删除了一条记录", "预收款");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 更新记录
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateRecord(@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String advances = request.getParameter("advance");
		String remarks = request.getParameter("remark");
		String ids = request.getParameter("ids");
		String[] advance = advances.split(",");
		String[] remark = remarks.split(",");
		String[] idStr = ids.split(",");
		try {
			for (int i = 0; i < advance.length; i++) {
				AdvanceDetail advanceDetail2 = (AdvanceDetail) advanceDetailService
						.getObjById(idStr[i], "AdvanceDetail");
				advanceDetail2.setAdvance(Double.valueOf(advance[i]));
				advanceDetail2.setRemark(remark[i]);
				advanceDetailService.updateObj(advanceDetail2);
			}
			logManageService.insertLog(request, "保存了预收款数据", "预收款");
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
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveBills", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveBills(@ModelAttribute BranchAdvance branchAdvance,
			String id, HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		double advanceTotal = 0;
		try {
			id = UuidUtils.getUUID();
			branchAdvance.setId(id);
			branchAdvance.setStatue("wait");
			branchAdvance.setAdvanceMan(user);
			branchAdvanceService.saveObj(branchAdvance);
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					AdvanceDetail advanceDetail = (AdvanceDetail) advanceDetailService
							.getObjById(idStr[i], "AdvanceDetail");
					advanceDetail.setBranchAdvance(branchAdvance);
					advanceDetailService.updateObj(advanceDetail);
					advanceTotal += advanceDetail.getAdvance();
				}
			}
			branchAdvance.setTotalAdvance(advanceTotal);
			branchAdvanceService.saveObj(branchAdvance);
			logManageService.insertLog(request, "保存了一条预付款单", "预收款");
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
	 * 打开预收款明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAdvanceDetail")
	public ModelAndView openAdvanceDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		BranchAdvance branchAdvance = (BranchAdvance) branchAdvanceService
				.getObjById(id, "BranchAdvance");
		model.addAttribute("branchAdvance", branchAdvance);
		ModelAndView view = createIframeView("settlement/advance/advance_detail");
		return view;
	}

	/**
	 * 列表展示预收款明细
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listAdvanceDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listAdvanceDetailJson(
			@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = advanceDetailService.getPagedDataDetailGridObj(page,
					advanceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开预收款编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAdvanceEdit")
	public ModelAndView openAdvanceEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		BranchAdvance branchAdvance = (BranchAdvance) branchAdvanceService
				.getObjById(id, "BranchAdvance");
		model.addAttribute("branchAdvance", branchAdvance);
		ModelAndView view = createIframeView("settlement/advance/advance_edit");
		return view;
	}

	/**
	 * 更新预收款单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateBills", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateBills(@ModelAttribute BranchAdvance branchAdvance,
			String id, HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		double advanceTotal = 0;
		try {
			branchAdvance.setStatue("active");
			branchAdvanceService.updateObj(branchAdvance);
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					AdvanceDetail advanceDetail = (AdvanceDetail) advanceDetailService
							.getObjById(idStr[i], "AdvanceDetail");
					advanceTotal += advanceDetail.getAdvance();
				}
			}
			branchAdvance.setTotalAdvance(advanceTotal);
			branchAdvanceService.updateObj(branchAdvance);
			logManageService.insertLog(request, "更新了一条预付款单", "预收款");
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
	 * 新增一条记录
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "addRecordEdit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addRecordEdit(
			@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String BranchAdId = request.getParameter("id");
		BranchAdvance branchAdvance = (BranchAdvance) branchAdvanceService
				.getObjById(BranchAdId, "BranchAdvance");
		try {
			AdvanceDetail advance = new AdvanceDetail();
			String id = UuidUtils.getUUID();
			advance.setId(id);
			advance.setOdd("YS");
			advance.setMoneyType("预收款");
			advance.setBranchAdvance(branchAdvance);
			advanceDetailService.saveObj(advance);
			logManageService.insertLog(request, "新增了一条预收款记录", "预收款");
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
	 * 打开预收款审核页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoEditCheck")
	public ModelAndView gotoEditCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		BranchAdvance branchAdvance = (BranchAdvance) branchAdvanceService
				.getObjById(id, "BranchAdvance");
		model.addAttribute("branchAdvance", branchAdvance);
		ModelAndView view = createIframeView("settlement/advance/advance_check");
		return view;
	}

	/**
	 * 审核通过
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute BranchAdvance branchAdvance,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BranchAdvance branchAd = (BranchAdvance) branchAdvanceService
					.getObjById(id, "BranchAdvance");
			branchAd.setStatue("finish");
			branchAd.setCheckMan(user);
			branchAd.setCheckDate(df.parse(df.format(new Date())));
			branchAdvanceService.updateObj(branchAd);
			logManageService.insertLog(request, "审核通过系统自动修改审核状态为完成", "预收款");
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
	 * 审核不通过
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkNoPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkNoPass(@ModelAttribute BranchAdvance branchAdvance,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BranchAdvance branchAd = (BranchAdvance) branchAdvanceService
					.getObjById(id, "BranchAdvance");
			branchAd.setStatue("dead");
			branchAd.setCheckMan(user);
			branchAd.setCheckDate(df.parse(df.format(new Date())));
			branchAdvanceService.updateObj(branchAd);
			logManageService.insertLog(request, "审核不通过系统自动修改审核状态为作废", "预收款");
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
}
