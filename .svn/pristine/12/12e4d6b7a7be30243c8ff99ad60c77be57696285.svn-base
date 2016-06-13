package com.proem.exm.controller.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.purchase.PurchaseReceiveItem;
import com.proem.exm.entity.settlement.SupplierSettlementItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.purchase.PurchaseOrderGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.service.purchase.PurchaseReceiveGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseReceiveService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * @author songcj
 * 
 *         2015年11月10日下午3:22:16
 */
@Controller
@RequestMapping("purchaseReceive/purchaseReceiveDo")
public class PurchaseReceiveController extends BaseController {

	@Autowired
	private PurchaseReceiveService purchaseReceiveService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private PurchaseOrderGoodsItemsService purchaseOrderGoodsItemsService;
	@Autowired
	private PurchaseReceiveGoodsItemsService purchaseReceiveGoodsItemsService;
	@Autowired
	ProviderInfoService providerInfoService;

	@InitBinder("purchaseOrder")
	public void initPurchaseOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseOrder.");
	}

	@InitBinder("purchaseReceive")
	public void initPurchaseReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseReceive.");
	}

	@InitBinder("purchaseOrderGoodsItems")
	public void initPurchaseOrderGoodsItems(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseOrderGoodsItems.");
	}

	@InitBinder("purchaseReceiveItem")
	public void initPurchaseReceiveItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseReceiveItem.");
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
			faName = "采购管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "收货管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "采购收货单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_list");
		return view;
	}

	@RequestMapping(value = "listPurchaseReceiveJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseReceiveJson(
			@ModelAttribute PurchaseReceive purchaseReceive, String state,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseReceiveService.getPagedDataGridObj(page,
					purchaseReceive, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 打开详细页面
	@RequestMapping("gotoDetailPurchaseReceive")
	public ModelAndView gotoDetailPurchaseReceive(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(id, "PurchaseReceive");
		model.addAttribute("purchaseReceive", purchaseReceive);
		model.addAttribute("purchaseReceiveId", id);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_detail");
		return view;
	}

	/**
	 * 打开编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoEditPurchaseReceiveEdit")
	public ModelAndView gotoEditGoodsFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(id, "PurchaseReceive");
		model.addAttribute("purchaseReceive", purchaseReceive);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_edit");
		return view;
	}

	/**
	 * 生成采购收货单
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createPurchaseReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createPurchaseReceive(
			@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		PurchaseOrder ordersOrder = (PurchaseOrder) purchaseOrderService
				.getObjById(id, "PurchaseOrder");
		List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemsList = purchaseOrderGoodsItemsService
				.getListByObj(PurchaseOrderGoodsItems.class, "PURCHASEID ='"
						+ id + "'");
		Long count = purchaseReceiveService.getCountByObj(
				PurchaseReceive.class,
				"PURCHASEORDERODD='" + ordersOrder.getOdd() + "'");
		if (count != 0) {
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		} else {
			ZcUserInfo userInfo = (ZcUserInfo) request.getSession()
					.getAttribute("userInfo");
			SimpleDateFormat formatDate = new SimpleDateFormat(
					"yyyyMMddHHmmssSSS");
			Date date = new Date();
			String str = formatDate.format(date);
			PurchaseReceive purchaseReceive = new PurchaseReceive();
			purchaseReceive.setId(UuidUtils.getUUID());
			purchaseReceive.setPurchaseMan(userInfo.getUserName());
			purchaseReceive.setAuditStatus(Constant.CHECK_STATUS_UNDO);
			purchaseReceive.setPurchaseOrder(ordersOrder.getId());
			purchaseReceive.setPurchaseOrderOdd(ordersOrder.getOdd());
			purchaseReceive.setBranch(ordersOrder.getBranch());
			purchaseReceive.setProviderInfo(ordersOrder.getProviderInfo());
			purchaseReceive.setPurchaseReceiveOdd("CGSHD" + str);
			try {
				Thread.sleep(1);// 等待1毫秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				double totalPrice = 0;
				if (purchaseOrderGoodsItemsList != null
						&& purchaseOrderGoodsItemsList.size() > 0) {
					for (int i = 0; i < purchaseOrderGoodsItemsList.size(); i++) {
						PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
						PurchaseOrderGoodsItems orderGoods = purchaseOrderGoodsItemsList
								.get(i);
						purchaseReceiveItem.setId(UuidUtils.getUUID());
						purchaseReceiveItem
								.setPurchaseOrderGoodsItems(orderGoods);
						purchaseReceiveItem.setGoodsMoney(orderGoods
								.getGoodsMoney());
						purchaseReceiveItem.setGoodsPrice(orderGoods
								.getGoodsPrice() + "");
						purchaseReceiveItem.setProductDate(orderGoods
								.getProduceDate());
						purchaseReceiveItem.setGoodsFile(orderGoods
								.getGoodsFile());
						purchaseReceiveItem.setActualNum(Double
								.valueOf(orderGoods.getActualNeed()));
						purchaseReceiveItem
								.setPurchaseReceiveId(purchaseReceive.getId());
						purchaseReceiveService.saveObj(purchaseReceiveItem);
						totalPrice += orderGoods.getGoodsMoney();
					}
				}
				purchaseReceive.setProviderInfo(ordersOrder.getProviderInfo());
				purchaseReceive.setPurchaseMoney(totalPrice);
				purchaseReceiveService.saveObj(purchaseReceive);
				logManageService.insertLog(request, "生成了一张采购收货单", "采购收货单");
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
			}
		}
		return ajaxResult;
	}

	/**
	 * 保存实际采购收货单
	 * 
	 * @param purchaseReceive
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "saveActualReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushGetPurchaseOrder(
			@ModelAttribute PurchaseReceive purchaseReceive,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String num = request.getParameter("num");
		String orderId = request.getParameter("id");
		String providerId = request.getParameter("providerId");
		String branchId = request.getParameter("branchId");
		String productDates = request.getParameter("productDate");
		String goodsPrices = request.getParameter("goodsPrice");
		String[] productDate = productDates.split(",");
		String[] idStr = ids.split(",");
		String[] nums = num.split(",");
		String[] goodsPrice = goodsPrices.split(",");
		ProviderInfo providerInfo = (ProviderInfo) purchaseReceiveGoodsItemsService
				.getObjById(providerId, "ProviderInfo");
		Branch branch = (Branch) purchaseReceiveGoodsItemsService.getObjById(
				branchId, "Branch");
		double purchaseMoney = 0;
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseReceiveItem receiveItem = (PurchaseReceiveItem) purchaseReceiveGoodsItemsService
							.getObjById(idStr[i], "PurchaseReceiveItem");
					double actualNum = Double.valueOf(nums[i]);
					double goodsMoney = actualNum
							* Double.valueOf(goodsPrice[i]);
					purchaseMoney += goodsMoney;
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					receiveItem
							.setProductDate(dateFormat.parse(productDate[i]));
					receiveItem.setActualNum(actualNum);
					receiveItem.setGoodsMoney(goodsMoney);
					receiveItem.setGoodsPrice(goodsPrice[i]);
					purchaseReceiveGoodsItemsService.updateObj(receiveItem);
				}
				PurchaseReceive purchase = (PurchaseReceive) purchaseReceiveService
						.getObjById(orderId, "PurchaseReceive");
				purchase.setAuditStatus(Constant.CHECK_STATUS_UNDO);
				purchase.setProviderInfo(providerInfo);
				purchase.setBranch(branch);
				purchase.setRemark(purchaseReceive.getRemark());
				purchase.setPurchaseMoney(purchaseMoney);
				purchaseReceiveService.updateObj(purchase);
			}
			logManageService.insertLog(request, "修改了实际收货数量", "采购收货单");
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
	 * 提交审核
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "submitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult submitCheck(@ModelAttribute PurchaseReceive receive,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(id, "PurchaseReceive");
		try {
			purchaseReceive.setAuditStatus(Constant.CHECK_STATUS_WAITCHECK);
			purchaseReceiveService.updateObj(purchaseReceive);
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
	 * 查询明细
	 * 
	 * @param purchaseReceiveIeItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPurchaseReceiveGoodsItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsOfProvider(
			@ModelAttribute PurchaseReceive purchaseReceive,
			String serialNumber, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseReceiveService.getPagedDataGridGoods(page,
					purchaseReceive, serialNumber);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("PRODUCTDATE");
					String dateStr = sdf.format(date);
					list.get(i).put("PRODUCEDATE", dateStr);
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
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				purchaseReceiveService.deleteObjById(ids[i],
						PurchaseReceive.class.getName());
				List<PurchaseReceiveItem> purchaseReceiveItemList = purchaseReceiveService
						.getListByObj(PurchaseReceiveItem.class,
								"PURCHASE_RECEIVE_ID='" + ids[i] + "'");
				if (purchaseReceiveItemList != null
						&& purchaseReceiveItemList.size() > 0) {
					for (int j = 0; j < purchaseReceiveItemList.size(); j++) {
						PurchaseReceiveItem purchaseReceiveItem = purchaseReceiveItemList
								.get(j);
						purchaseReceiveService.deleteObjById(
								purchaseReceiveItem.getId(),
								PurchaseReceiveItem.class.getName());
					}
				}
			}
			logManageService.insertLog(request, "删除勾选中的采购收货单", "采购收货单");
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
	 * 打开审核采购收货单页面
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
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(id, "PurchaseReceive");
		model.addAttribute("purchaseReceive", purchaseReceive);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_check");
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
	public AjaxResult checkPass(
			@ModelAttribute PurchaseReceive purchaseReceive,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		String ids = request.getParameter("ids");
		String goodsMoneys = request.getParameter("goodsMoney");
		String[] goodsMoney = goodsMoneys.split(",");
		String[] idStr = ids.split(",");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			PurchaseReceive receive = (PurchaseReceive) purchaseReceiveService
					.getObjById(id, "PurchaseReceive");
			String branchId = receive.getBranch().getId();
			String goodsFileId = "";
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReceiveService
							.getObjById(idStr[i], "PurchaseReceiveItem");
					if (!StringUtils.isBlank(purchaseReceiveItem.getGoodsFile()
							.getId())) {
						goodsFileId = purchaseReceiveItem.getGoodsFile()
								.getId();
					}
					Long count = purchaseReceiveService.getCountByObj(
							ZcStorehouse.class, "BRANCH_ID='" + branchId
									+ "' and GOODSFILE_ID='" + goodsFileId
									+ "'");
					if (count == 0) {
						ZcStorehouse zcStorehouse = new ZcStorehouse();
						zcStorehouse.setGoodsFile(purchaseReceiveItem
								.getGoodsFile());
						zcStorehouse.setStore(String
								.valueOf(purchaseReceiveItem.getActualNum()));
						zcStorehouse.setStoreMoney(goodsMoney[i]);
						zcStorehouse.setBranch(receive.getBranch());
						purchaseReceiveService.saveObj(zcStorehouse);
					} else {
						List<ZcStorehouse> zcStorehouseList = purchaseReceiveService
								.getListByObj(ZcStorehouse.class, "BRANCH_ID='"
										+ branchId + "' and GOODSFILE_ID='"
										+ goodsFileId + "'");
						if (zcStorehouseList != null
								&& zcStorehouseList.size() > 0) {
							for (int j = 0; j < zcStorehouseList.size(); j++) {
								ZcStorehouse zcStorehouse = zcStorehouseList
										.get(j);
								double num = Double.valueOf(zcStorehouse
										.getStore())
										+ purchaseReceiveItem.getActualNum();
								double moneys = Double.valueOf(zcStorehouse
										.getStoreMoney() == null
										|| "".equals(zcStorehouse
												.getStoreMoney()) ? "0"
										: zcStorehouse.getStoreMoney())
										+ Double.valueOf(goodsMoney[i]);
								zcStorehouse.setStore(String.valueOf(num));
								zcStorehouse.setStoreMoney(moneys + "");
								purchaseReceiveService.updateObj(zcStorehouse);
							}
						}
					}
				}
			}
			receive.setAuditStatus(Constant.CHECK_STATUS_PASS);
			receive.setCheckMan(userInfo.getUserName());
			receive.setCheckDate(df.parse(df.format(new Date())));
			purchaseReceiveService.updateObj(receive);
			logManageService
					.insertLog(request, "对采购收货单进行审核，审核通过，库存增加", "采购收货单");
			SupplierSettlementItem supplierSettlementItem = new SupplierSettlementItem();
			String itemId = UuidUtils.getUUID();
			Double money = receive.getPurchaseMoney();
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
			supplierSettlementItem.setCode(receive.getPurchaseReceiveOdd());
			supplierSettlementItem.setProvider(receive.getProviderInfo());
			supplierSettlementItem.setMoney("0");
			purchaseReceiveService.saveObj(supplierSettlementItem);
			logManageService.insertLog(request, "对采购收货单进行审核，审核通过后系统生成供应商结算单",
					"采购收货单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					e.toString());
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
	public AjaxResult checkNoPass(String id, String type, String reason,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			PurchaseReceive receive = (PurchaseReceive) purchaseReceiveService
					.getObjById(id, "PurchaseReceive");
			receive.setAuditStatus(Constant.CHECK_STATUS_NOPASS);
			receive.setCheckMan(userInfo.getUserName());
			if (receive.getReason() != null && receive.getReason().length() > 0) {
				receive.setReason(receive.getReason() + ";" + reason);
			} else {
				receive.setReason(reason);
			}
			receive.setCheckDate(df.parse(df.format(new Date())));
			purchaseReceiveService.updateObj(receive);
			logManageService.insertLog(request, "对采购收货单进行审核，审核不通过", "采购收货单");
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
	 * 删除采购收货单中的商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeGoods(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		double money = 0;
		double totalNum = 0;
		try {
			String idOld = request.getParameter("idStr");
			// 获取页面填写的采购订单商品需要更新的数量
			String nums = request.getParameter("num");
			String produceDates = request.getParameter("produceDate");
			String goodsPrices = request.getParameter("goodsPrice");
			String[] goodsPrice = goodsPrices.split(",");
			String[] produceDate = produceDates.split(",");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			String receiveId = "";
			for (int i = 0; i < idStr.length; i++) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseReceiveItem");
				receiveId = purchaseReceiveItem.getPurchaseReceiveId();
				purchaseReceiveItem.setActualNum(Double.valueOf(num[i]));
				purchaseReceiveItem.setProductDate(dateFormat
						.parse(produceDate[i]));
				purchaseReceiveItem.setGoodsPrice(goodsPrice[i]);
				purchaseReceiveItem.setGoodsMoney(Double.valueOf(goodsPrice[i])
						* Double.valueOf(num[i]));
				// 保存采购收货单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseReceiveItem);
				money += Double.valueOf(purchaseReceiveItem.getGoodsPrice())
						* Double.valueOf(num[i]);
				totalNum += Double.valueOf(num[i]);
			}
			PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseOrderService
					.getObjById(receiveId, "PurchaseReceive");
			purchaseReceive.setPurchaseMoney(money);
			purchaseReceive.setAuditStatus(Constant.CHECK_STATUS_UNDO);
			purchaseOrderService.updateObj(purchaseReceive);
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReceiveService
						.getObjById(ids[i], "PurchaseReceiveItem");
				String receveId = purchaseReceiveItem.getPurchaseReceiveId();
				PurchaseReceive receive = (PurchaseReceive) purchaseReceiveService
						.getObjById(receveId, "PurchaseReceive");
				double goodsMoney = Double.valueOf(purchaseReceiveItem
						.getGoodsPrice()) * purchaseReceiveItem.getActualNum();
				receive.setPurchaseMoney(purchaseReceive.getPurchaseMoney()
						- goodsMoney);
				purchaseReceiveService.deleteObjById(ids[i],
						PurchaseReceiveItem.class.getName());
				purchaseReceiveService.updateObj(receive);
			}
			logManageService.insertLog(request, "删除了采购收货单中的一条商品", "采购收货单");
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
	 * 打开新增采购订单页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("gotoAddReceive")
	public ModelAndView gotoAddReceive(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<PurchaseReceiveItem> purchaseReceiveItemsList = purchaseOrderGoodsItemsService
				.getListByObj(PurchaseReceiveItem.class,
						"PURCHASE_RECEIVE_ID is null ");
		if (purchaseReceiveItemsList != null
				&& purchaseReceiveItemsList.size() > 0) {
			for (int i = 0; i < purchaseReceiveItemsList.size(); i++) {
				PurchaseReceiveItem purchaseReceiveItem = purchaseReceiveItemsList
						.get(i);
				purchaseOrderGoodsItemsService.deleteObjById(
						purchaseReceiveItem.getId(),
						PurchaseReceiveItem.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_add");
		return view;
	}

	/**
	 * 分页采购商品表中采购订单ID为空的商品信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsItemsNullJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsNullJson(
			@ModelAttribute PurchaseReceiveItem purchaseReceiveItem,
			String providerId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = purchaseReceiveService.getPurchaseAddGoods(page,
					purchaseReceiveItem, ctpUser, providerId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("PRODUCTDATE");
					String dateStr = sdf.format(date);
					list.get(i).put("PRODUCEDATE", dateStr);
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
	 * 打开新增商品时选择商品的面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openChoseGoods")
	public ModelAndView openChoseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String providerId = request.getParameter("providerId");
		String nums = request.getParameter("num");
		String ids = request.getParameter("ids");
		if (nums != null && nums != "" && ids != null && ids != "") {
			String[] num = nums.split(",");
			String[] idStr = ids.split(",");
			for (int i = 0; i < idStr.length; i++) {
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReceiveService
						.getObjById(idStr[i], "PurchaseReceiveItem");
				purchaseReceiveItem.setActualNum(Double.valueOf(num[i]));
				purchaseReceiveService.updateObj(purchaseReceiveItem);
			}
		}
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(providerId, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_choseGoods");
		return view;
	}

	/**
	 * 勾选商品至采购收货单明细
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(
			@ModelAttribute PurchaseReceiveItem purchaseReceiveItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String id = idStr[i];
					GoodsFile goodsFile = (GoodsFile) purchaseOrderGoodsItemsService
							.getObjById(id, "GoodsFile");
					Long counts = purchaseOrderGoodsItemsService.getCountByObj(
							GoodsFile.class,
							"PRODUCTGOODSID='" + goodsFile.getId() + "'");
					if (counts != 0) {
						List<GoodsFile> goodsList = purchaseOrderGoodsItemsService
								.getListByObj(GoodsFile.class,
										"PRODUCTGOODSID='" + goodsFile.getId()
												+ "'");
						goodsFile = goodsList.get(0);
					}
					Long count = purchaseOrderGoodsItemsService.getCountByObj(
							PurchaseReceiveItem.class,
							"PURCHASE_RECEIVE_ID is null and GOODSFILE_ID='"
									+ goodsFile.getId() + "'");
					if (count != 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						// 获取当前系统日期
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String produceDate = dateFormat.format(now);
						PurchaseReceiveItem purchaseReceive = new PurchaseReceiveItem();
						purchaseReceive.setId(UuidUtils.getUUID());
						purchaseReceive.setProductDate(dateFormat
								.parse(produceDate));
						purchaseReceive.setGoodsFile(goodsFile);
						purchaseReceive.setCtpUser(user);
						purchaseReceive.setGoodsPrice(goodsFile
								.getGoods_purchase_price() + "");
						purchaseReceiveService.saveObj(purchaseReceive);
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到采购收货单商品表", "采购收货单");
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
	 * 移除商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteChose", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteChose(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String idOld = request.getParameter("idStr");
			// 获取页面填写的采购订单商品需要更新的数量
			String nums = request.getParameter("num");
			String produceDates = request.getParameter("produceDate");
			String goodsPrices = request.getParameter("goodsPrice");
			String[] goodsPrice = goodsPrices.split(",");
			String[] produceDate = produceDates.split(",");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			for (int i = 0; i < idStr.length; i++) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseReceiveItem");
				purchaseReceiveItem.setActualNum(Double.valueOf(num[i]));
				purchaseReceiveItem.setProductDate(dateFormat
						.parse(produceDate[i]));
				purchaseReceiveItem.setGoodsPrice(goodsPrice[i]);
				purchaseReceiveItem.setGoodsMoney(Double.valueOf(goodsPrice[i])
						* Double.valueOf(num[i]));
				// 保存采购订单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseReceiveItem);
			}
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				purchaseReceiveGoodsItemsService.deleteObjById(ids[i],
						PurchaseReceiveItem.class.getName());
			}
			logManageService.insertLog(request, "移除了收货的商品", "采购收货单");
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
	 * 生成采购收货单
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveNewReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNewReceive(
			@ModelAttribute PurchaseReceive purchaseReceive,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String branchId = request.getParameter("branchId");
		String providerId = request.getParameter("providerId");
		String produceDates = request.getParameter("produceDate");
		String[] produceDate = produceDates.split(",");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		purchaseReceive.setId(UuidUtils.getUUID());
		purchaseReceive.setResType("1");
		purchaseReceive.setPurchaseMan(userInfo.getUserName());
		purchaseReceive.setAuditStatus(Constant.CHECK_STATUS_UNDO);
		Branch branch = (Branch) purchaseReceiveService.getObjById(branchId,
				"Branch");
		ProviderInfo providerInfo = (ProviderInfo) purchaseReceiveService
				.getObjById(providerId, "ProviderInfo");
		purchaseReceive.setBranch(branch);
		purchaseReceive.setProviderInfo(providerInfo);
		try {
			double totalPrice = 0;
			Long count = purchaseReceiveService.getCountByObj(
					PurchaseReceive.class, "purchase_receive_odd = '"
							+ purchaseReceive.getPurchaseReceiveOdd() + "'");
			String str = "";
			if (count != 0) {
				SimpleDateFormat formatDate = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Date date = new Date();
				str = formatDate.format(date);
				purchaseReceive.setPurchaseReceiveOdd("CGSHD" + str);
			}
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseReceiveItem receive = (PurchaseReceiveItem) purchaseReceiveService
							.getObjById(idStr[i], "PurchaseReceiveItem");
					GoodsFile goodsFile = (GoodsFile) purchaseReceiveService
							.getObjById(receive.getGoodsFile().getId(),
									"GoodsFile");
					purchaseReceiveService.deleteObjById(receive.getId(),
							PurchaseReceiveItem.class.getName());
					double goodsNums = Double.parseDouble(num[i]);
					double goodsMoney = Double.parseDouble(String
							.valueOf(goodsFile.getGoods_purchase_price()))
							* goodsNums;
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
					purchaseReceiveItem.setId(UuidUtils.getUUID());
					purchaseReceiveItem.setProductDate(dateFormat
							.parse(produceDate[i]));
					purchaseReceiveItem.setGoodsFile(goodsFile);
					purchaseReceiveItem.setGoodsMoney(goodsMoney);
					purchaseReceiveItem.setActualNum(goodsNums);
					purchaseReceiveItem.setGoodsPrice(goodsFile
							.getGoods_purchase_price() + "");
					purchaseReceiveItem.setPurchaseReceiveId(purchaseReceive
							.getId());
					purchaseReceiveService.saveObj(purchaseReceiveItem);
					totalPrice += goodsMoney;
				}
			}
			purchaseReceive.setPurchaseMoney(totalPrice);
			purchaseReceiveService.saveObj(purchaseReceive);
			logManageService.insertLog(request, "生成了一张采购收货单", "采购收货单");
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
	 * 打开新增商品时选择商品的面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAddGoods")
	public ModelAndView openAddGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String nums = request.getParameter("num");
		String ids = request.getParameter("ids");
		if (nums != null && nums != "" && ids != null && ids != "") {
			String[] idStr = ids.split(",");
			String[] num = nums.split(",");
			for (int i = 0; i < idStr.length; i++) {
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReceiveService
						.getObjById(idStr[i], "PurchaseReceiveItem");
				purchaseReceiveItem.setActualNum(Double.valueOf(num[i]));
				purchaseReceiveService.updateObj(purchaseReceiveItem);
			}
		}
		String providerId = request.getParameter("providerId");
		String purchaseReceiveId = request.getParameter("purchaseReceiveId");
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(providerId, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		model.addAttribute("purchaseReceiveId", purchaseReceiveId);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_addGoods");
		return view;
	}

	/**
	 * 勾选商品至采购收货单明细
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "chooseGoodsToReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult chooseGoodsToReceive(
			@ModelAttribute PurchaseReceiveItem purchaseReceiveItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String purchaseReceiveId = request.getParameter("purchaseReceiveId");
		String[] idStr = ids.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String id = idStr[i];
					GoodsFile goodsFile = (GoodsFile) purchaseOrderGoodsItemsService
							.getObjById(id, "GoodsFile");
					Long counts = purchaseOrderGoodsItemsService.getCountByObj(
							GoodsFile.class,
							"PRODUCTGOODSID='" + goodsFile.getId() + "'");
					if (counts != 0) {
						List<GoodsFile> goodsList = purchaseOrderGoodsItemsService
								.getListByObj(GoodsFile.class,
										"PRODUCTGOODSID='" + goodsFile.getId()
												+ "'");
						goodsFile = goodsList.get(0);
					}
					Long count = purchaseOrderGoodsItemsService.getCountByObj(
							PurchaseReceiveItem.class,
							"PURCHASE_RECEIVE_ID ='" + purchaseReceiveId
									+ "' and GOODSFILE_ID='"
									+ goodsFile.getId() + "'");
					if (count != 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						// 获取当前系统日期
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String produceDate = dateFormat.format(now);
						PurchaseReceiveItem receiveItem = new PurchaseReceiveItem();
						receiveItem.setId(UuidUtils.getUUID());
						receiveItem.setProductDate(dateFormat
								.parse(produceDate));
						receiveItem.setGoodsFile(goodsFile);
						receiveItem.setGoodsPrice(goodsFile
								.getGoods_purchase_price() + "");
						receiveItem.setPurchaseReceiveId(purchaseReceiveId);
						purchaseReceiveService.saveObj(receiveItem);
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到采购收货单商品表", "采购收货单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping("gotoPrintPurchaseReceive")
	public ModelAndView gotoPrintPurchaseReceive(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID的获取订单详情
		List<PurchaseReceiveItem> orderGoodsItemList = purchaseOrderService
				.getListByObj(PurchaseReceiveItem.class,
						"PURCHASE_RECEIVE_ID = '" + id + "'");
		List<PurchaseReceiveItem> goodsList = new ArrayList<PurchaseReceiveItem>();
		String[] guige = null;
		if (orderGoodsItemList != null && orderGoodsItemList.size() > 0) {
			for (int i = 0; i < orderGoodsItemList.size(); i++) {
				PurchaseReceiveItem purchaseReceiveItem = orderGoodsItemList
						.get(i);
				if (purchaseReceiveItem.getGoodsFile()
						.getGoods_specifications() != null
						&& purchaseReceiveItem.getGoodsFile()
								.getGoods_specifications() != "") {
					guige = purchaseReceiveItem.getGoodsFile()
							.getGoods_specifications().split("商品规格：");
					if (guige != null && guige.length > 1) {
						purchaseReceiveItem.getGoodsFile()
								.setGoods_specifications(guige[1]);
					} else if (guige != null && guige.length == 1) {
						purchaseReceiveItem.getGoodsFile()
								.setGoods_specifications(guige[0]);
					}
				} else {
					purchaseReceiveItem.getGoodsFile().setGoods_specifications(
							"");
				}
				goodsList.add(purchaseReceiveItem);
			}
		}
		// 根据ID获取采购订单对象
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseOrderService
				.getObjById(id, "PurchaseReceive");
		model.addAttribute("purchaseReceive", purchaseReceive);
		model.addAttribute("receiveId", id);
		model.addAttribute("orderGoodsItemList", goodsList);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchase_receive_print");
		return view;
	}
}