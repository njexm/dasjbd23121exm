package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
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
import com.proem.exm.entity.settlement.SupplierSettlement;
import com.proem.exm.entity.settlement.SupplierSettlementItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.settlement.SupplierSettlementService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 供应商结算controller
 * 
 * @author songcj 2015年12月1日 下午6:23:45
 */
@Controller
@RequestMapping("supplierSettlement/supplierSettlementDo")
public class SupplierSettlementController extends BaseController {
	@Autowired
	private SupplierSettlementService supplierSettlementService;

	@InitBinder("supplierSettlement")
	private void initSupplierSettlement(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("supplierSettlement.");
	}

	@InitBinder("supplierSettlementItem")
	private void initSupplierSettlementItem(WebDataBinder binder) {
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
			sonName = "供应商结算";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/supplierSettlement/supplier_settlement_list");
		return view;
	}

	// 跳转新增页面
	@RequestMapping("gotoAdd")
	public ModelAndView gotoAdd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/supplierSettlement/supplier_settlement_add");
		return view;
	}

	// 打开详细页面
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierSettlement supplierSettlement = (SupplierSettlement) supplierSettlementService
				.getObjById(id, "SupplierSettlement");
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("supplierSettlement", supplierSettlement);
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("settlement/supplierSettlement/supplier_settlement_detail");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierSettlement supplierSettlement = (SupplierSettlement) supplierSettlementService
				.getObjById(id, "SupplierSettlement");
		model.addAttribute("supplierSettlement", supplierSettlement);
		ModelAndView view = createIframeView("settlement/supplierSettlement/supplier_settlement_edit");
		return view;
	}

	// 打开审核页面
	@RequestMapping("gotoCheck")
	public ModelAndView gotoCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		SupplierSettlement supplierSettlement = (SupplierSettlement) supplierSettlementService
				.getObjById(id, "SupplierSettlement");
		model.addAttribute("supplierSettlement", supplierSettlement);
		ModelAndView view = createIframeView("settlement/supplierSettlement/supplier_settlement_check");
		return view;
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "listSupplierSettlementJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listSupplierSettlementJosn(
			@ModelAttribute SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = supplierSettlementService.getPagedDataGridObj(page,
					supplierSettlement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "listSettlement", produces = "application/json")
	@ResponseBody
	public DataGrid listSettlement(
			@ModelAttribute SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = supplierSettlementService.getSettlementObj(page,
					supplierSettlement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增时判断已有的供应商结算单状态
	 */
	@RequestMapping(value = "provider", produces = "application/json")
	@ResponseBody
	public AjaxResult provider(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult("true");
		String providerId = request.getParameter("id");
		if (StringUtil.validate(providerId)) {
			List<SupplierSettlement> settlementList = supplierSettlementService
					.getListByObj(SupplierSettlement.class, "provider_id = '"
							+ providerId + "'");
			if (settlementList != null && settlementList.size() > 0) {
				for (int i = 0; i < settlementList.size(); i++) {
					if (settlementList.get(i).getAuditStatus() != Constant.CHECK_STATUS_PASS) {
						ajaxResult
								.setResult(settlementList.get(i)
										.getAuditStatus() != Constant.CHECK_STATUS_PASS);
						return ajaxResult;
					}
				}
			}
		}
		return ajaxResult;
	}

	/**
	 * 新增页面展示
	 */
	@RequestMapping(value = "listAddJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listAddJosn(
			@ModelAttribute SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		// String providerId = request.getParameter("providerId");
		try {
			dataGrid = supplierSettlementService.getItemDataGridObj(page,
					supplierSettlement);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Double money = 0.0;
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("AGREED_TIME");
					String payableMoney = (String) list.get(i).get(
							"PAYABLE_MONEY");
					money += Double.valueOf(payableMoney);
					String dateStr = sdf.format(date);
					list.get(i).put("NEW_TIME", dateStr);
				}
				dataGrid.setRows(list);
			}
			return dataGrid;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 编辑页面获取数据
	 */
	@RequestMapping(value = "listEditJson", produces = "application/json")
	@ResponseBody
	public DataGrid listEditJson(
			@ModelAttribute SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		String id = request.getParameter("id");
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = supplierSettlementService.getEdit(page, id,
					supplierSettlement);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("AGREED_TIME");
					String dateStr = sdf.format(date);
					list.get(i).put("NEW_TIME", dateStr);
				}
				dataGrid.setRows(list);
			}
			return dataGrid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 详情页面获取数据
	 */
	@RequestMapping(value = "listDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDetailJson(
			@ModelAttribute SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		String id = request.getParameter("id");
		try {
			dataGrid = supplierSettlementService.getDetail(page, id,
					supplierSettlement);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("AGREED_TIME");
					String dateStr = sdf.format(date);
					list.get(i).put("NEW_TIME", dateStr);
				}
				dataGrid.setRows(list);
			}
			return dataGrid;
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
				supplierSettlementService.deleteObjById(ids[i],
						SupplierSettlementItem.class.getName());
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

	// 数据新增
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(SupplierSettlement supplierSettlement,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, Exception {
		AjaxResult ajaxResult = null;
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String supplierSettlementId = request.getParameter("id");
		String rows = request.getParameter("rows");
		String remarks = request.getParameter("remark");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		String[] remark = remarks.split(",");
		String id = UuidUtils.getUUID();
		// SupplierSettlementItem supplierSettlementItem = new
		// SupplierSettlementItem();
		String payableMoney = "";
		String actualMoney = "";
		String favorableMoney = "";
		String discountMoney = "";
		String paidMoney = "";
		String unpaidMoney = "";
		// Double discount = 0.0;
		// Double payable = 0.0;
		Double actual = 0.0;
		Double favorable = 0.0;
		// Double paid = 0.0;
		Double unpaid = 0.0;
		String agreedTime = "";
		Date agreed = null;
		double total = 0;
		try {
			if (StringUtils.isBlank(rows)) {

			} else {
				JSONArray jsonArray = JSONArray.fromObject(rows);
				List<Map<String, Object>> list = jsonArray;
				for (int i = 0; i < list.size(); i++) {
					unpaidMoney = (String) list.get(i).get("UNPAID_MONEY");
					unpaid = Double.valueOf(unpaidMoney);
					actual = Double.valueOf((String) list.get(i).get(
							"ACTUAL_MONEY"));
					favorable = Double.valueOf((String) list.get(i).get(
							"FAVORABLE_MONEY"));
					total += unpaid - actual - favorable;
				}
				if (StringUtils.isBlank(supplierSettlementId)) {
					ProviderInfo provider = (ProviderInfo) supplierSettlementService
							.getObjById(supplierSettlement.getProvider()
									.getId(), "ProviderInfo");
					Long count = supplierSettlementService.getCountByObj(
							SupplierSettlement.class, "document_code = '"
									+ supplierSettlement.getDocumentCode()
									+ "'");
					String str = "";
					if (count != 0) {
						SimpleDateFormat formatDate = new SimpleDateFormat(
								"yyyyMMddHHmmssSSS");
						Date date = new Date();
						str = formatDate.format(date);
						supplierSettlement.setDocumentCode("GJSD" + str);
					}
					supplierSettlement.setId(id);
					supplierSettlement.setProvider(provider);
					supplierSettlement.setDocumentsMoney(total);
					CtpUser user = (CtpUser) request.getSession().getAttribute(
							"user");
					supplierSettlement.setMakeMen(user);
					supplierSettlement
							.setAuditStatus(Constant.CHECK_STATUS_UNDO);
					supplierSettlementService.saveObj(supplierSettlement);
					for (int i = 0; i < list.size(); i++) {
						SupplierSettlementItem supplierSettlementItem = new SupplierSettlementItem();
						String id1 = UuidUtils.getUUID();
						supplierSettlementItem.setId(id1);
						payableMoney = (String) list.get(i)
								.get("PAYABLE_MONEY");
						actualMoney = (String) list.get(i).get("ACTUAL_MONEY");
						favorableMoney = (String) list.get(i).get(
								"FAVORABLE_MONEY");
						discountMoney = (String) list.get(i).get(
								"DISCOUNT_MONEY");
						paidMoney = (String) list.get(i).get("PAID_MONEY");
						unpaidMoney = (String) list.get(i).get("UNPAID_MONEY");
						// discount = Double.valueOf(discountMoney);
						// payable = Double.valueOf(payableMoney);
						// actual = Double.valueOf(actualMoney);
						// favorable = Double.valueOf(favorableMoney);
						// paid = Double.valueOf(paidMoney);
						// unpaid = Double.valueOf(unpaidMoney);
						// discount += favorable; //已优惠金额+= 优惠金额
						// paid += actual; //已付金额+= 实付金额
						// unpaid = payable-paid-discount;
						// //未付金额=应付金额-已付金额-已优惠金额
						// discountMoney = String.valueOf(discount);
						// paidMoney = String.valueOf(paid);
						// unpaidMoney = String.valueOf(unpaid);
						supplierSettlementItem.setRemarks(remark[i]);
						supplierSettlementItem.setActualMoney(actualMoney);
						supplierSettlementItem
								.setFavorableMoney(favorableMoney);
						supplierSettlementItem.setUnpaidMoney(unpaidMoney);
						supplierSettlementItem.setPaidMoney(paidMoney);
						supplierSettlementItem.setDiscountMoney(discountMoney);
						supplierSettlementItem.setPayableMoney(payableMoney);
						supplierSettlementItem.setTax((String) list.get(i).get(
								"TAX"));
						supplierSettlementItem.setCode((String) list.get(i)
								.get("CODE"));
						supplierSettlementItem.setProvider(provider);
						supplierSettlementItem
								.setSupplierSettlementId(supplierSettlement);
						agreedTime = (String) list.get(i).get("NEW_TIME");
						if (agreedTime != null && agreedTime.length() >= 0) {
							agreed = fmt.parse(agreedTime);
							supplierSettlementItem.setAgreedTime(agreed);
						}
						supplierSettlementItem.setMoney("1");
						supplierSettlementService
								.saveObj(supplierSettlementItem);
						if (Double.valueOf((String) list.get(i).get(
								"UNPAID_MONEY")) != 0) {
							SupplierSettlementItem settlementItem = new SupplierSettlementItem();
							String idStr = UuidUtils.getUUID();
							settlementItem.setId(idStr);
							settlementItem.setMoney("0");
							settlementItem.setActualMoney(actualMoney);
							settlementItem.setAgreedTime(supplierSettlementItem
									.getAgreedTime());
							settlementItem
									.setDiscountMoney(supplierSettlementItem
											.getDiscountMoney());
							settlementItem.setFavorableMoney(favorableMoney);
							settlementItem.setPaidMoney(supplierSettlementItem
									.getPaidMoney());
							settlementItem
									.setPayableMoney(supplierSettlementItem
											.getPayableMoney());
							settlementItem.setProvider(supplierSettlementItem
									.getProvider());
							settlementItem.setRemarks(supplierSettlementItem
									.getRemarks());
							settlementItem
									.setSupplierSettlementId(supplierSettlementItem
											.getSupplierSettlementId());
							settlementItem.setTax((String) list.get(i).get(
									"TAX"));
							settlementItem
									.setUnpaidMoney(supplierSettlementItem
											.getUnpaidMoney());
							settlementItem.setCode(supplierSettlementItem
									.getCode());
							supplierSettlementService.saveObj(settlementItem);
						}
					}
				} else {
					supplierSettlement = (SupplierSettlement) supplierSettlementService
							.getObjById(supplierSettlementId,
									"SupplierSettlement");
					CtpUser ctuUser = (CtpUser) supplierSettlementService
							.getObjById(
									supplierSettlement.getMakeMen().getId(),
									"CtpUser");
					supplierSettlement.setMakeMen(ctuUser);
					supplierSettlement.setDocumentsMoney(total);
					supplierSettlementService.updateObj(supplierSettlement);
					for (int i = 0; i < list.size(); i++) {
						SupplierSettlementItem supplierSettlementItem = new SupplierSettlementItem();
						payableMoney = (String) list.get(i)
								.get("PAYABLE_MONEY");
						actualMoney = (String) list.get(i).get("ACTUAL_MONEY");
						favorableMoney = (String) list.get(i).get(
								"FAVORABLE_MONEY");
						discountMoney = (String) list.get(i).get(
								"DISCOUNT_MONEY");
						paidMoney = (String) list.get(i).get("PAID_MONEY");
						unpaidMoney = (String) list.get(i).get("UNPAID_MONEY");
						// discount = Double.valueOf(discountMoney);
						// payable = Double.valueOf(payableMoney);
						// actual = Double.valueOf(actualMoney);
						// favorable = Double.valueOf(favorableMoney);
						// paid = Double.valueOf(paidMoney);
						// unpaid = Double.valueOf(unpaidMoney);
						// discount += favorable; //已优惠金额+= 优惠金额
						// paid += actual; //已付金额+= 实付金额
						// unpaid = payable-paid-discount;
						// //未付金额=应付金额-已付金额-已优惠金额
						// discountMoney = String.valueOf(discount);
						// paidMoney = String.valueOf(paid);
						// unpaidMoney = String.valueOf(unpaid);
						supplierSettlementItem = (SupplierSettlementItem) supplierSettlementService
								.getObjById((String) list.get(i).get("ID"),
										"SupplierSettlementItem");
						supplierSettlementItem.setRemarks(remark[i]);
						supplierSettlementItem.setActualMoney(actualMoney);
						supplierSettlementItem
								.setFavorableMoney(favorableMoney);
						supplierSettlementItem.setUnpaidMoney(unpaidMoney);
						supplierSettlementItem.setPaidMoney(paidMoney);
						supplierSettlementItem.setDiscountMoney(discountMoney);
						supplierSettlementItem.setPayableMoney(payableMoney);
						supplierSettlementItem.setTax(supplierSettlementItem
								.getTax());
						supplierSettlementItem.setCode((String) list.get(i)
								.get("CODE"));
						supplierSettlementItem
								.setSupplierSettlementId(supplierSettlement);
						agreedTime = (String) list.get(i).get("NEW_TIME");
						if (agreedTime != null && agreedTime.length() >= 0) {
							agreed = fmt.parse(agreedTime);
							supplierSettlementItem.setAgreedTime(agreed);
						}
						supplierSettlementItem.setMoney("1");
						supplierSettlementService
								.updateObj(supplierSettlementItem);
					}
				}
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
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
			SupplierSettlement supplierSettlement = (SupplierSettlement) supplierSettlementService
					.getObjById(id, "SupplierSettlement");
			supplierSettlement.setAuditStatus(Constant.CHECK_STATUS_WAITCHECK);
			supplierSettlementService.updateObj(supplierSettlement);
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
	public AjaxResult checkPass(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = request.getParameter("id");
			String rows = request.getParameter("rows");
			JSONArray jsonArray = JSONArray.fromObject(rows);
			List<Map<String, Object>> list = jsonArray;
			CtpUser user = (CtpUser) request.getSession().getAttribute("user");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String agreedTime = "";
			String discountMoney = "";
			String paidMoney = "";
			String unpaidMoney = "";
			// String remarks = "";
			Date agreed = null;
			String payableMoney = "";
			String actualMoney = "";
			String favorableMoney = "";
			Double discount = 0.0;
			Double payable = 0.0;
			Double actual = 0.0;
			Double favorable = 0.0;
			Double paid = 0.0;
			Double unpaid = 0.0;
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String code = "";
			SupplierSettlement supplierSettlement = (SupplierSettlement) supplierSettlementService
					.getObjById(id, "SupplierSettlement");
			String providerId = supplierSettlement.getProvider().getId();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// --------------------------------------------------------------------------------------
					payableMoney = (String) list.get(i).get("PAYABLE_MONEY");
					actualMoney = (String) list.get(i).get("ACTUAL_MONEY");
					favorableMoney = (String) list.get(i)
							.get("FAVORABLE_MONEY");
					discountMoney = (String) list.get(i).get("DISCOUNT_MONEY");
					paidMoney = (String) list.get(i).get("PAID_MONEY");
					unpaidMoney = (String) list.get(i).get("UNPAID_MONEY");
					discount = Double.valueOf(discountMoney);
					payable = Double.valueOf(payableMoney);
					actual = Double.valueOf(actualMoney);
					favorable = Double.valueOf(favorableMoney);
					paid = Double.valueOf(paidMoney);
					unpaid = Double.valueOf(unpaidMoney);
					discount += favorable; // 已优惠金额+= 优惠金额
					paid += actual; // 已付金额+= 实付金额
					unpaid = payable - paid - discount; // 未付金额=应付金额-已付金额-已优惠金额
					discountMoney = String.valueOf(discount);
					paidMoney = String.valueOf(paid);
					unpaidMoney = String.valueOf(unpaid);
					// -------------------------------------------------------------------------------------
					// unpaidMoney = (String)list.get(i).get("UNPAID_MONEY");
					// paidMoney =(String)list.get(i).get("PAID_MONEY");
					// discountMoney =(String)list.get(i).get("DISCOUNT_MONEY");
					code = (String) list.get(i).get("CODE");
					// remarks = (String)list.get(i).get("REMARKS");
					List<SupplierSettlementItem> settlementItemList = supplierSettlementService
							.getListByObj(
									SupplierSettlementItem.class,
									" provider_id = '"
											+ providerId
											+ "' and supplier_settlement_id is null and code = '"
											+ code + "'");
					SupplierSettlementItem supplierSettlementItem = settlementItemList
							.get(0);
					supplierSettlementItem.setUnpaidMoney(unpaidMoney);
					agreedTime = (String) list.get(i).get("NEW_TIME");
					if (agreedTime != null && agreedTime.length() >= 0) {
						agreed = fmt.parse(agreedTime);
						supplierSettlementItem.setAgreedTime(agreed);
					}
					supplierSettlementItem.setPaidMoney(paidMoney);
					supplierSettlementItem.setDiscountMoney(discountMoney);
					// if(remarks !=null && remarks.length()>=0){
					// supplierSettlementItem.setRemarks(remarks);
					// }
					supplierSettlementItem.setRemarks("");
					supplierSettlementService.updateObj(supplierSettlementItem);
				}
				supplierSettlement.setAuditMen(user);
				supplierSettlement.setAuditStatus(Constant.CHECK_STATUS_PASS);
				supplierSettlement
						.setAuditTime(df.parse(df.format(new Date())));
				supplierSettlementService.updateObj(supplierSettlement);
				logManageService.insertLog(request, "审核供应商结算单通过", "供应商结算");
			}
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
	public AjaxResult check(
			@ModelAttribute SupplierSettlement supplierSettlement, String id,
			String type, String reason, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			supplierSettlement = (SupplierSettlement) supplierSettlementService
					.getObjById(id, "SupplierSettlement");
			supplierSettlement.setAuditMen(user);
			supplierSettlement.setAuditStatus(Constant.CHECK_STATUS_NOPASS);
			supplierSettlement.setAuditTime(df.parse(df.format(new Date())));
			supplierSettlementService.updateObj(supplierSettlement);
			logManageService.insertLog(request, "审核供应商结算单不通过", "供应商结算");
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
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id, Page page) {
		AjaxResult ajaxResult = null;
		try {
			List<SupplierSettlementItem> aItems = supplierSettlementService
					.getListByObj(SupplierSettlementItem.class,
							"supplier_settlement_id = '" + id + "'");
			if (aItems != null && aItems.size() > 0) {
				for (int j = 0; j < aItems.size(); j++) {
					SupplierSettlementItem supplierSettlementItem = aItems
							.get(j);
					supplierSettlementService.deleteObjById(
							supplierSettlementItem.getId(),
							SupplierSettlementItem.class.getName());
				}
			}
			supplierSettlementService.deleteObjById(id,
					SupplierSettlement.class.getName());
			logManageService.insertLog(request, "删除了勾选的供应商结算单", "供应商结算");
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
	 * 初始化结算报表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initSettlement")
	public ModelAndView initSettlement(HttpServletRequest request,
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
			sonName = "结算报表";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/supplierSettlement/settlement_list");
		return view;
	}

}
