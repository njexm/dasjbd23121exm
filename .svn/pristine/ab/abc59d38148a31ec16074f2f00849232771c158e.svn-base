package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.settlement.AdvancePayment;
import com.proem.exm.entity.settlement.AdvancePaymentItem;
import com.proem.exm.entity.settlement.SupplierSettlementItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.settlement.AdvancePaymentService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 预付款controller
 * 
 * @author songcj 2015年11月25日 下午2:29:41
 */
@Controller
@RequestMapping("advancePayment/advancePaymentDo")
public class AdvancePaymentController extends BaseController {
	@Autowired
	private AdvancePaymentService advancePaymentService;

	@InitBinder("advancePayment")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("advancePayment.");
	}

	@InitBinder("advancePaymentItem")
	public void initAdvancePaymentItemView(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("advancePaymentItem.");
	}

	@InitBinder("supplierSettlementItem")
	public void initSupplierSettlementItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("supplierSettlementItem.");
	}

	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "结算管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "供应商结算";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "预付款单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/advancePayment/advance_payment_list");
		return view;
	}

	// 跳转新增页面
	@RequestMapping("gotoAddAdvancePayment")
	public ModelAndView gotoAddAdvancePayment(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<AdvancePaymentItem> advancePaymentItemList = advancePaymentService
				.getListByObj(AdvancePaymentItem.class,
						"advance_payment_id is null");
		if (advancePaymentItemList != null && advancePaymentItemList.size() > 0) {
			for (int i = 0; i < advancePaymentItemList.size(); i++) {
				AdvancePaymentItem advancePaymentItem = advancePaymentItemList
						.get(i);
				advancePaymentService.deleteObjById(advancePaymentItem.getId(),
						AdvancePaymentItem.class.getName());
			}
		}
		AdvancePaymentItem advance = new AdvancePaymentItem();
		String id = UuidUtils.getUUID();
		advance.setId(id);
		advance.setCode("YF");
		advance.setType("预付款");
		advance.setPrepaymentMoney(0.0);
		advancePaymentService.saveObj(advance);
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/advancePayment/advance_payment_add");
		return view;
	}

	// 打开详细页面
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		AdvancePayment advancePayment = (AdvancePayment) advancePaymentService
				.getObjById(id, "AdvancePayment");
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("advancePayment", advancePayment);
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/advancePayment/advance_payment_detail");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		AdvancePayment advancePayment = (AdvancePayment) advancePaymentService
				.getObjById(id, "AdvancePayment");
		model.addAttribute("advancePayment", advancePayment);
		ModelAndView view = createIframeView("settlement/advancePayment/advance_payment_edit");
		return view;
	}

	// 打开审核页面
	@RequestMapping("gotoCheck")
	public ModelAndView gotoCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		AdvancePayment advancePayment = (AdvancePayment) advancePaymentService
				.getObjById(id, "AdvancePayment");
		model.addAttribute("advancePayment", advancePayment);
		ModelAndView view = createIframeView("settlement/advancePayment/advance_payment_check");
		return view;
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "listAdvancePaymentJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listAdvancePaymentJosn(
			@ModelAttribute AdvancePayment advancePayment,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = advancePaymentService.getPagedDataGridObj(page,
					advancePayment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 获取详情数据
	 */
	@RequestMapping(value = "listAdvancePaymentDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsNullOrderJson(
			@ModelAttribute AdvancePaymentItem advancePaymentItem, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = advancePaymentService.getAdvancePaymentDetail(page, id,
					advancePaymentItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增页面展示
	 */
	@RequestMapping(value = "listAdvancePaymentMoneyJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listAdvancePaymentJosn(
			@ModelAttribute AdvancePaymentItem advancePaymentItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = advancePaymentService.getItemDataGridObj(page,
					advancePaymentItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 新增一条预付款
	@RequestMapping(value = "addRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addRecord(
			@ModelAttribute AdvancePaymentItem advancePaymentItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String moneys = request.getParameter("money");
		String remarks = request.getParameter("remark");
		String advancePaymentId = request.getParameter("advancePaymentId");
		String[] idStr = null;
		String[] money = null;
		String[] remark = null;
		if (ids != null && ids != "" && moneys != null && moneys != ""
				&& remarks != null && remarks != "") {
			idStr = ids.split(",");
			money = moneys.split(",");
			remark = remarks.split(",");
		}
		try {
			if (StringUtils.isBlank(advancePaymentId)) {
				AdvancePaymentItem advance = new AdvancePaymentItem();
				String id = UuidUtils.getUUID();
				advance.setId(id);
				advance.setCode("YF");
				advance.setType("预付款");
				advance.setPrepaymentMoney(0.0);
				advancePaymentService.saveObj(advance);
			} else {
				AdvancePayment advancePayment = (AdvancePayment) advancePaymentService
						.getObjById(advancePaymentId, "AdvancePayment");
				AdvancePaymentItem advance = new AdvancePaymentItem();
				String id = UuidUtils.getUUID();
				advance.setId(id);
				advance.setCode("YF");
				advance.setType("预付款");
				advance.setPrepaymentMoney(0.0);
				advance.setAdvancePayment(advancePayment);
				advancePaymentService.saveObj(advance);
			}
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					advancePaymentItem = (AdvancePaymentItem) advancePaymentService
							.getObjById(idStr[i], "AdvancePaymentItem");
					Double num = Double.valueOf(money[i]);
					advancePaymentItem.setPrepaymentMoney(num);
					advancePaymentItem.setRemark(remark[i]);
					advancePaymentService.updateObj(advancePaymentItem);
				}
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

	// 数据新增
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute AdvancePayment advancePayment,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, Exception {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String moneys = request.getParameter("money");
		String remarks = request.getParameter("remark");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		String advancePaymentId = request.getParameter("id");
		String[] idStr = ids.split(",");
		String[] money = moneys.split(",");
		String[] remark = remarks.split(",");
		String id = UuidUtils.getUUID();
		try {
			double total = 0;
			for (int i = 0; i < remark.length; i++) {
				Double price = Double.valueOf(money[i]);
				total += price;
			}
			if (StringUtils.isBlank(advancePaymentId)) {
				advancePayment.setId(id);
				Long count = advancePaymentService.getCountByObj(
						AdvancePayment.class, "payment_order = '"
								+ advancePayment.getPaymentOrder() + "'");
				String str = "";
				if (count != 0) {
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyyMMddHHmmssSSS");
					Date date = new Date();
					str = formatDate.format(date);
					advancePayment.setPaymentOrder("FKD" + str);
				}
				advancePayment.setPrepayment(total);
				CtpUser user = (CtpUser) request.getSession().getAttribute(
						"user");
				advancePayment.setMakeMen(user);
				advancePayment.setAuditStatus(Constant.CHECK_STATUS_UNDO);
				advancePaymentService.saveObj(advancePayment);
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			} else {
				ProviderInfo provider = (ProviderInfo) advancePaymentService
						.getObjById(advancePayment.getProvider().getId(),
								"ProviderInfo");
				CtpUser ctuUser = (CtpUser) advancePaymentService.getObjById(
						advancePayment.getMakeMen().getId(), "CtpUser");
				CtpUser auditMen = (CtpUser) advancePaymentService.getObjById(
						advancePayment.getAuditMen().getId(), "CtpUser");
				advancePayment.setProvider(provider);
				advancePayment.setMakeMen(ctuUser);
				advancePayment.setAuditMen(auditMen);
				advancePayment.setPrepayment(total);
				advancePayment.setAuditStatus(Constant.CHECK_STATUS_UNDO);
				advancePaymentService.updateObj(advancePayment);
				ajaxResult = new AjaxResult(AjaxResult.UPDATE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			}
			for (int i = 0; i < idStr.length; i++) {
				AdvancePaymentItem advancePaymentItem = (AdvancePaymentItem) advancePaymentService
						.getObjById(idStr[i], "AdvancePaymentItem");
				advancePaymentItem.setRemark(remark[i]);
				Double price = Double.valueOf(money[i]);
				advancePaymentItem.setPrepaymentMoney(price);
				advancePaymentItem.setAdvancePayment(advancePayment);
				advancePaymentService.updateObj(advancePaymentItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 更新数据
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute AdvancePayment advancePayment,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			advancePaymentService.updateObj(advancePayment);
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "deleteItem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteItem(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				advancePaymentService.deleteObjById(ids[i],
						AdvancePaymentItem.class.getName());
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<AdvancePaymentItem> aItems = advancePaymentService
						.getListByObj(AdvancePaymentItem.class,
								"advance_payment_id = '" + ids[i] + "'");
				if (aItems != null && aItems.size() > 0) {
					for (int j = 0; j < aItems.size(); j++) {
						AdvancePaymentItem advancePaymentItem = aItems.get(j);
						advancePaymentService.deleteObjById(
								advancePaymentItem.getId(),
								AdvancePaymentItem.class.getName());
					}
				}
				advancePaymentService.deleteObjById(ids[i],
						AdvancePayment.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的预付款单", "预付款");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 审核通过
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute AdvancePayment advancePayment,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			advancePayment = (AdvancePayment) advancePaymentService.getObjById(
					id, "AdvancePayment");
			advancePayment.setAuditMen(user);
			advancePayment.setAuditStatus(Constant.CHECK_STATUS_PASS);
			advancePayment.setAuditTime(df.parse(df.format(new Date())));
			advancePaymentService.updateObj(advancePayment);
			logManageService.insertLog(request, "审核预付款单通过", "预付款");
			SupplierSettlementItem supplierSettlementItem = new SupplierSettlementItem();
			String itemId = UuidUtils.getUUID();
			Double money = advancePayment.getPrepayment();
			String payableMoney = String.valueOf(money);
			supplierSettlementItem.setId(itemId);
			supplierSettlementItem.setPayableMoney(payableMoney);
			supplierSettlementItem.setActualMoney("0.00");
			supplierSettlementItem.setDiscountMoney("0.00");
			supplierSettlementItem.setFavorableMoney("0.00");
			supplierSettlementItem.setPaidMoney("0.00");
			supplierSettlementItem.setTax("0.00");
			// 获取当前日期的加上30天作为约定付款默认时间
			Date date = new Date();
			date.setMonth(date.getMonth() + 1);
			supplierSettlementItem.setAgreedTime(date);
			supplierSettlementItem.setUnpaidMoney(payableMoney);
			supplierSettlementItem.setCode(advancePayment.getPaymentOrder());
			supplierSettlementItem.setProvider(advancePayment.getProvider());
			supplierSettlementItem.setMoney("0");
			advancePaymentService.saveObj(supplierSettlementItem);
			logManageService.insertLog(request, "对供应商预付款单进行审核，审核通过后系统生成供应商结算单",
					"采购收货单");
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 审核不通过
	@RequestMapping(value = "check", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult check(@ModelAttribute AdvancePayment advancePayment,
			String id, String type, String reason, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			advancePayment = (AdvancePayment) advancePaymentService.getObjById(
					id, "AdvancePayment");
			advancePayment.setAuditMen(user);
			advancePayment.setReason(reason);
			advancePayment.setAuditStatus(Constant.CHECK_STATUS_NOPASS);
			advancePayment.setAuditTime(df.parse(df.format(new Date())));
			advancePaymentService.updateObj(advancePayment);
			logManageService.insertLog(request, "审核预付款单不通过", "预付款");
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 提交审核
	@RequestMapping(value = "toCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult toCheck(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			AdvancePayment advancePayment = (AdvancePayment) advancePaymentService
					.getObjById(id, "AdvancePayment");
			advancePayment.setAuditStatus(Constant.CHECK_STATUS_WAITCHECK);
			advancePaymentService.updateObj(advancePayment);
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
