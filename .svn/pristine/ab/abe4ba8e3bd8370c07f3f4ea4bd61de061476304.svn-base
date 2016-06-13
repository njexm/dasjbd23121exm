package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.proem.exm.entity.settlement.SupplierCost;
import com.proem.exm.entity.settlement.SupplierCostItem;
import com.proem.exm.entity.settlement.SupplierSettlementItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.Sorte;
import com.proem.exm.service.settlement.SupplierCostService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 供应商费用controller
 * 
 * @author songcj 2015年11月30日 上午11:18:04
 */
@Controller
@RequestMapping("supplierCost/supplierCostDo")
public class SupplierCostController extends BaseController {
	@Autowired
	private SupplierCostService supplierCostService;

	@InitBinder("supplierCost")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("supplierCost.");
	}

	@InitBinder("supplierCostItem")
	public void initSupplierCostItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("supplierCostItem.");
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
			sonName = "供应商费用";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/supplierCost/supplier_cost_list");
		return view;
	}

	// 跳转新增页面
	@RequestMapping("gotoAdd")
	public ModelAndView gotoAdd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<SupplierCostItem> supplierCostItemList = supplierCostService
				.getListByObj(SupplierCostItem.class,
						" supplier_cost_id is null");
		if (supplierCostItemList != null && supplierCostItemList.size() > 0) {
			for (int i = 0; i < supplierCostItemList.size(); i++) {
				SupplierCostItem supplierCostItem = supplierCostItemList.get(i);
				supplierCostService.deleteObjById(supplierCostItem.getId(),
						SupplierCostItem.class.getName());
			}
		}
		SupplierCostItem cost = new SupplierCostItem();
		String id = UuidUtils.getUUID();
		cost.setId(id);
		cost.setMoney("0.00");
		supplierCostService.saveObj(cost);
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/supplierCost/supplier_cost_add");
		return view;
	}

	// 打开详细页面
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierCost supplierCost = (SupplierCost) supplierCostService
				.getObjById(id, "SupplierCost");
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("supplierCost", supplierCost);
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/supplierCost/supplier_cost_detail");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierCost supplierCost = (SupplierCost) supplierCostService
				.getObjById(id, "SupplierCost");
		model.addAttribute("supplierCost", supplierCost);
		ModelAndView view = createIframeView("settlement/supplierCost/supplier_cost_edit");
		return view;
	}

	// 打开审核页面
	@RequestMapping("gotoCheck")
	public ModelAndView gotoCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierCost supplierCost = (SupplierCost) supplierCostService
				.getObjById(id, "SupplierCost");
		model.addAttribute("supplierCost", supplierCost);
		ModelAndView view = createIframeView("settlement/supplierCost/supplier_cost_check");
		return view;
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "listSupplierCostJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listSupplierCostJosn(
			@ModelAttribute SupplierCost supplierCost,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = supplierCostService.getPagedDataGridObj(page,
					supplierCost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增页面展示
	 */
	@RequestMapping(value = "listAddJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listAddJosn(
			@ModelAttribute SupplierCostItem supplierCostItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = supplierCostService.getItemDataGridObj(page,
					supplierCostItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 新增一条预付款
	@RequestMapping(value = "addRecord", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addRecord(String rows, String id,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		// String ids = request.getParameter("ids");
		// String supplierCostId = request.getParameter("id");
		// String moneys = request.getParameter("money");
		// String remarks = request.getParameter("remark");
		// String costNames = request.getParameter("costName");
		// String[] idStr = ids.split(",");
		// String[] money = moneys.split(",");
		// String[] remark = remarks.split(",");
		// String[] costName = costNames.split(",");
		try {
			if (StringUtils.isBlank(rows)) {

			} else {
				JSONArray jsonArray = JSONArray.fromObject(rows);
				List<Map<String, Object>> list = jsonArray;
				// 新增一条记录
				SupplierCostItem cost = new SupplierCostItem();
				String idStr = UuidUtils.getUUID();
				cost.setId(idStr);
				cost.setMoney("0");
				if (StringUtils.isBlank(id)) {
					supplierCostService.saveObj(cost);
				} else {
					SupplierCost supplierCost = (SupplierCost) supplierCostService
							.getObjById(id, "SupplierCost");
					cost.setCostId(supplierCost);
					supplierCostService.saveObj(cost);
				}
				// 保存之前的记录
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String itemId = (String) list.get(i).get("ID");
						String remarks = (String) list.get(i).get("REMARKS");
						String money = "";
						if (list.get(i).get("MONEY") instanceof Integer) {
							Integer money1 = (Integer) list.get(i).get("MONEY");
							money = money1.toString();
						} else {
							money = (String) list.get(i).get("MONEY");
						}
						String costName = (String) list.get(i).get("COST_NAME");
						SupplierCostItem supplierCostItem = (SupplierCostItem) supplierCostService
								.getObjById(itemId, "SupplierCostItem");
						supplierCostItem.setMoney(money);
						supplierCostItem.setRemarks(remarks);
						supplierCostItem.setCostName(costName);
						supplierCostService.updateObj(supplierCostItem);
					}
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
	public AjaxResult save(SupplierCost supplierCost,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, Exception {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String moneys = request.getParameter("money");
		String remarks = request.getParameter("remark");
		String supplierCostId = request.getParameter("id");
		String costNames = request.getParameter("costName");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		String[] idStr = ids.split(",");
		String[] money = moneys.split(",");
		String[] remark = remarks.split(",");
		String[] costName = costNames.split(",");
		String id = UuidUtils.getUUID();
		try {
			double total = 0;
			for (int i = 0; i < remark.length; i++) {
				Double price = Double.valueOf(money[i]);
				total += price;
			}
			if (StringUtils.isBlank(supplierCostId)) {
				supplierCost.setId(id);
				Long count = supplierCostService.getCountByObj(
						SupplierCost.class,
						"receipt_number = '" + supplierCost.getReceiptNumber()
								+ "'");
				String str = "";
				if (count != 0) {
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyyMMddHHmmssSSS");
					Date date = new Date();
					str = formatDate.format(date);
					supplierCost.setReceiptNumber("FYD" + str);
				}
				supplierCost.setTotalMoney(total);
				CtpUser user = (CtpUser) request.getSession().getAttribute(
						"user");
				supplierCost.setMakeMen(user);
				supplierCost.setAuditStatus(Constant.CHECK_STATUS_UNDO);
				supplierCostService.saveObj(supplierCost);
			} else {
				supplierCost = (SupplierCost) supplierCostService.getObjById(
						supplierCostId, "SupplierCost");
				ProviderInfo provider = (ProviderInfo) supplierCostService
						.getObjById(supplierCost.getProvider().getId(),
								"ProviderInfo");
				CtpUser ctuUser = (CtpUser) supplierCostService.getObjById(
						supplierCost.getMakeMen().getId(), "CtpUser");
				supplierCost.setProvider(provider);
				supplierCost.setMakeMen(ctuUser);
				supplierCost.setTotalMoney(total);
				supplierCostService.updateObj(supplierCost);
			}
			for (int i = 0; i < remark.length; i++) {
				SupplierCostItem supplierCostItem = (SupplierCostItem) supplierCostService
						.getObjById(idStr[i], "SupplierCostItem");
				supplierCostItem.setRemarks(remark[i]);
				supplierCostItem.setMoney(money[i]);
				supplierCostItem.setCostName(costName[i]);
				supplierCostItem.setCostId(supplierCost);
				supplierCostService.updateObj(supplierCostItem);
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
	 * 获取详情数据
	 */
	@RequestMapping(value = "listDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDetailJson(
			@ModelAttribute SupplierCostItem supplierCostItem, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		// String id = request.getParameter("id");
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = supplierCostService
					.getDetail(page, id, supplierCostItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
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
				supplierCostService.deleteObjById(ids[i],
						SupplierCostItem.class.getName());
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
				List<SupplierCostItem> aItems = supplierCostService
						.getListByObj(SupplierCostItem.class,
								"supplier_cost_id = '" + ids[i] + "'");
				if (aItems != null && aItems.size() > 0) {
					for (int j = 0; j < aItems.size(); j++) {
						SupplierCostItem supplierCostItem = aItems.get(j);
						supplierCostService.deleteObjById(
								supplierCostItem.getId(),
								SupplierCostItem.class.getName());
					}
				}
				supplierCostService.deleteObjById(ids[i],
						SupplierCost.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的供应商费用单", "供应商费用");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 提交审核
	@RequestMapping(value = "toCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult toCheck(@ModelAttribute SupplierCost supplierCost,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String moneys = request.getParameter("money");
		String remarks = request.getParameter("remark");
		String costNames = request.getParameter("costName");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		String[] idStr = ids.split(",");
		String[] money = moneys.split(",");
		String[] remark = remarks.split(",");
		String[] costName = costNames.split(",");
		try {
			for (int i = 0; i < remark.length; i++) {
				SupplierCostItem supplierCostItem = (SupplierCostItem) supplierCostService
						.getObjById(idStr[i], "SupplierCostItem");
				supplierCostItem.setRemarks(remark[i]);
				supplierCostItem.setMoney(money[i]);
				supplierCostItem.setCostName(costName[i]);
				supplierCostItem.setCostId(supplierCost);
				supplierCostService.updateObj(supplierCostItem);
			}
			double total = 0;
			for (int i = 0; i < money.length; i++) {
				Double price = Double.valueOf(money[i]);
				total += price;
			}
			SupplierCost supplierCost1 = (SupplierCost) supplierCostService
					.getObjById(supplierCost.getId(), "SupplierCost");
			supplierCost1.setTotalMoney(total);
			supplierCost1.setAuditStatus(Constant.CHECK_STATUS_WAITCHECK);
			supplierCost1.setProvider(supplierCost.getProvider());
			supplierCost1.setRemarks(supplierCost.getRemarks());
			supplierCost1.setPaymentMode(supplierCost.getPaymentMode());
			supplierCost1.setPaymentTime(supplierCost.getPaymentTime());
			supplierCostService.updateObj(supplierCost1);
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 审核通过
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute SupplierCost supplierCost,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			supplierCost = (SupplierCost) supplierCostService.getObjById(id,
					"SupplierCost");
			supplierCost.setAuditMen(user);
			supplierCost.setAuditStatus(Constant.CHECK_STATUS_PASS);
			supplierCost.setAuditTime(df.parse(df.format(new Date())));
			supplierCostService.updateObj(supplierCost);
			logManageService.insertLog(request, "审核供应商费用单通过", "供应商费用");
			SupplierSettlementItem supplierSettlementItem = new SupplierSettlementItem();
			String itemId = UuidUtils.getUUID();
			Double money = supplierCost.getTotalMoney();
			String payableMoney = String.valueOf(money);
			supplierSettlementItem.setId(itemId);
			supplierSettlementItem.setPayableMoney(payableMoney);
			supplierSettlementItem.setActualMoney("0.00");
			supplierSettlementItem.setDiscountMoney("0.00");
			supplierSettlementItem.setFavorableMoney("0.00");
			supplierSettlementItem.setPaidMoney("0.00");
			supplierSettlementItem.setTax("0.00");
			// 获取当前月份的加上1作为约定付款默认时间
			Date date = new Date();
			date.setMonth(date.getMonth() + 1);
			supplierSettlementItem.setAgreedTime(date);
			supplierSettlementItem.setUnpaidMoney(payableMoney);
			supplierSettlementItem.setCode(supplierCost.getReceiptNumber());
			supplierSettlementItem.setProvider(supplierCost.getProvider());
			supplierSettlementItem.setMoney("0");
			supplierCostService.saveObj(supplierSettlementItem);
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
	public AjaxResult check(String id, String type, String reason,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			SupplierCost supplierCost = (SupplierCost) supplierCostService
					.getObjById(id, "SupplierCost");
			supplierCost.setAuditMen(user);
			supplierCost.setReason(reason);
			supplierCost.setAuditStatus(Constant.CHECK_STATUS_NOPASS);
			supplierCost.setAuditTime(df.parse(df.format(new Date())));
			supplierCostService.updateObj(supplierCost);
			logManageService.insertLog(request, "审核供应商费用单不通过", "供应商费用");
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
