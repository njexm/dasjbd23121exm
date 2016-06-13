package com.proem.exm.controller.purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
import com.proem.exm.entity.basic.provider.EmailInfo;
import com.proem.exm.entity.basic.provider.EmailService;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.order.OrdersItemService;
import com.proem.exm.service.order.OrdersService;
import com.proem.exm.service.purchase.PurchaseOrderGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.MailSenderInfo;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SimpleMailSender;

/**
 * 采购订单管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("purchase/purchaseOrder")
public class PurchaseOrderController extends BaseController {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	GoodsFileService goodsFileService;
	@Autowired
	ProviderInfoService providerInfoService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	OrdersItemService ordersItemService;
	@Autowired
	PurchaseOrderGoodsItemsService purchaseOrderGoodsItemsService;
	@Autowired
	BranchService branchService;

	@InitBinder("purchaseOrderGoodsItems")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseOrderGoodsItems.");
	}

	@InitBinder("goodsFile")
	public void initgoodsFile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	@InitBinder("emailInfo")
	public void initEmailInfo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("emailInfo.");
	}

	@InitBinder("emailService")
	public void initEmailService(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("emailService.");
	}

	@InitBinder("purchaseOrder")
	public void initPurchaseOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseOrder.");
	}

	/**
	 * 打开初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
			fasonName = "订单查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "采购订单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_list");
		return view;
	}

	/**
	 * 打开新增采购订单页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("gotoAddPurchaseOrder")
	public ModelAndView gotoAddPurchaseOrder(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemList = purchaseOrderGoodsItemsService
				.getListByObj(PurchaseOrderGoodsItems.class,
						"PURCHASEID is null ");
		if (purchaseOrderGoodsItemList != null
				&& purchaseOrderGoodsItemList.size() > 0) {
			for (int i = 0; i < purchaseOrderGoodsItemList.size(); i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoodsItemList
						.get(i);
				purchaseOrderGoodsItemsService.deleteObjById(
						purchaseOrderGoodsItems.getId(),
						PurchaseOrderGoodsItems.class.getName());
			}
		}
		List<ZcStorehouse> zcStorehousesList = purchaseOrderGoodsItemsService
				.getListByObj(ZcStorehouse.class, "status=9 ");
		if (zcStorehousesList != null && zcStorehousesList.size() > 0) {
			for (int j = 0; j < zcStorehousesList.size(); j++) {
				ZcStorehouse zcStorehouse = zcStorehousesList.get(j);
				purchaseOrderGoodsItemsService.deleteObjById(
						zcStorehouse.getId(), ZcStorehouse.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_add");
		return view;
	}

	/**
	 * 打开新增采购订单页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendTo")
	public ModelAndView sendTo(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemsList = purchaseOrderGoodsItemsService
				.getListByObj(PurchaseOrderGoodsItems.class,
						"PURCHASEID is not null and sendFlag is null");
		if (purchaseOrderGoodsItemsList != null
				&& purchaseOrderGoodsItemsList.size() > 0) {
			PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoodsItemsList
					.get(0);
			PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderGoodsItemsService
					.getObjById(purchaseOrderGoodsItems.getPurchaseId(),
							"PurchaseOrder");
			String providerId = purchaseOrder.getProviderInfo().getId();
			model.addAttribute("providerId", providerId);
		}
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_send");
		return view;
	}

	/**
	 * 打开编辑短信页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openMessage")
	public ModelAndView openMessage(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String providerId = request.getParameter("providerId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String units = request.getParameter("unit");
		String names = request.getParameter("names");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] unit = units.split(",");
		String[] name = names.split(",");
		ProviderInfo providerInfo = (ProviderInfo) purchaseOrderGoodsItemsService
				.getObjById(providerId, "ProviderInfo");
		String messages = "您有新的采购消息：";
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				int a = name[i].indexOf("（");
				if (a >= 0) {
					String[] goodsName = name[i].split("（");
					messages += goodsName[0] + ":" + num[i] + unit[i] + ";";
				} else {
					messages += name[i] + ":" + num[i] + unit[i] + ";";
				}
			}
		}
		model.addAttribute("messages", messages);
		model.addAttribute("ids", ids);
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_editMessage");
		return view;
	}

	/**
	 * 打开编辑短信页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openEmail")
	public ModelAndView openEmail(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String providerId = request.getParameter("providerId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String units = request.getParameter("unit");
		String names = request.getParameter("names");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		units = java.net.URLDecoder.decode(units, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		names = java.net.URLDecoder.decode(names, "UTF-8");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] unit = units.split(",");
		String[] name = names.split(",");
		ProviderInfo providerInfo = (ProviderInfo) purchaseOrderGoodsItemsService
				.getObjById(providerId, "ProviderInfo");
		String messages = providerInfo.getNickname() + "先生/女士您好，您有新的采购消息：";
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				messages += name[i] + ":" + num[i] + unit[i] + ";";
			}
		}
		messages += "如有不便，请致电客服中心，感谢您的配合！";
		model.addAttribute("messages", messages);
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_editEmail");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPurchaseOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseOrderJson(
			@ModelAttribute PurchaseOrder purchaseOrder, String state,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getPagedDataGridObj(page,
					purchaseOrder, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listProviderGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProviderGoodsJson(
			@ModelAttribute PurchaseOrder purchaseOrder, String providerId,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getProviderGoodsObj(page,
					purchaseOrder, providerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开采购订单商品页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("itemList")
	public ModelAndView itemList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("order/orders/ordersItem_List");
		return view;
	}

	/**
	 * 打开采购订单商品明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoDetailPurchaseOrder")
	public ModelAndView gotoDetailPurchaseOrder(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID获取采购订单对象
		PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService
				.getObjById(id, "PurchaseOrder");
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("purhaserId", id);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_detail");
		return view;
	}

	@RequestMapping("gotoPrintPurchaseOrder")
	public ModelAndView gotoPrintPurchaseOrder(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID的获取订单详情
		List<PurchaseOrderGoodsItems> orderGoodsItemList = purchaseOrderService
				.getListByObj(PurchaseOrderGoodsItems.class, "PURCHASEID = '"
						+ id + "'");
		List<PurchaseOrderGoodsItems> goodsList = new ArrayList<PurchaseOrderGoodsItems>();
		String[] guige = null;
		if (orderGoodsItemList != null && orderGoodsItemList.size() > 0) {
			for (int i = 0; i < orderGoodsItemList.size(); i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = orderGoodsItemList
						.get(i);
				if (purchaseOrderGoodsItems.getGoodsFile()
						.getGoods_specifications() != null
						&& purchaseOrderGoodsItems.getGoodsFile()
								.getGoods_specifications() != "") {
					guige = purchaseOrderGoodsItems.getGoodsFile()
							.getGoods_specifications().split("商品规格：");
					if (guige != null && guige.length > 1) {
						purchaseOrderGoodsItems.setSpecifications(guige[1]);
					} else if (guige != null && guige.length == 1) {
						purchaseOrderGoodsItems.setSpecifications(guige[0]);
					}
				} else {
					purchaseOrderGoodsItems.setSpecifications("");
				}
				goodsList.add(purchaseOrderGoodsItems);
			}
		}
		// 根据ID获取采购订单对象
		PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService
				.getObjById(id, "PurchaseOrder");
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("purhaserId", id);
		model.addAttribute("orderGoodsItemList", goodsList);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_print");
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
	@RequestMapping("gotoEditPurchaseOrder")
	public ModelAndView gotoEditGoodsFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService
				.getObjById(id, "PurchaseOrder");
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_edit");
		return view;
	}

	/**
	 * 打开审核采购订单页面
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
		PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService
				.getObjById(id, "PurchaseOrder");
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_check");
		return view;
	}

	/**
	 * 分页查询采购单所有商品信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPurchaseOrderGoodsItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseOrderGoodsItemsJson(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoodsItems,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseOrderGoodsItemsService.getPagedDataGridObj(page,
					purchaseOrderGoodsItems);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("PRODUCEDATE");
					String dateStr = "";
					if (date != null) {
						dateStr = sdf.format(date);
					} else {
						Date dates = new Date();
						dateStr = sdf.format(dates);
					}
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
	 * 分页采购商品表中采购订单ID为空的商品信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsItemsNullOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsNullOrderJson(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoodsItems,
			String providerId, String storehouseId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = purchaseOrderGoodsItemsService.getPurchaseAddGoods(page,
					purchaseOrderGoodsItems, ctpUser, providerId, storehouseId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("PRODUCEDATE");
					String dateStr = "";
					if (date != null) {
						dateStr = sdf.format(date);
					} else {
						Date dates = new Date();
						dateStr = sdf.format(dates);
					}
					list.get(i).put("PRODUCEDATE", dateStr);
				}
				dataGrid.setRows(list);
	         	List ll=dataGrid.getRows();
				System.out.println(ll.get(0));
			}
			return dataGrid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 保存修改
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveAndCommit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveAndCommit(
			@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		// 获取页面传递的采购订单商品对象ID
		String ids = request.getParameter("ids");
		// 获取页面填写的采购订单商品需要更新的数量
		String nums = request.getParameter("num");
		String produceDates = request.getParameter("produceDate");
		String goodsPrices = request.getParameter("goodsPrice");
		String[] goodsPrice = goodsPrices.split(",");
		String[] produceDate = produceDates.split(",");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		double money = 0;
		double totalNum = 0;
		try {
			for (int i = 0; i < num.length; i++) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// 根据页面获取的采购订单商品对象的ID获取商品对象
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				// 赋值
				purchaseOrderGoodsItems.setActualNeed(num[i]);
				purchaseOrderGoodsItems.setProduceDate(dateFormat
						.parse(produceDate[i]));
				purchaseOrderGoodsItems.setGoodsPrice(Double
						.valueOf(goodsPrice[i]));
				purchaseOrderGoodsItems.setGoodsMoney(Double
						.valueOf(goodsPrice[i]) * Double.valueOf(num[i]));
				// 保存采购订单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseOrderGoodsItems);
				money += purchaseOrderGoodsItems.getGoodsPrice()
						* Double.valueOf(num[i]);
				totalNum += Double.valueOf(num[i]);
			}
			// 根据获取到的采购订单ID获取采购订单对象
			// PurchaseOrder order = (PurchaseOrder) purchaseOrderService
			// .getObjById(id, "PurchaseOrder");
			// 赋值
			purchaseOrder.setMoney(money);
			purchaseOrder.setTotalNum(totalNum + "");
			// purchaseOrder.setRemark("");
			purchaseOrder.setState(Constant.CHECK_STATUS_UNDO);
			// 保存采购订单对象
			purchaseOrderService.updateObj(purchaseOrder);
			// 保存日志
			logManageService.insertLog(request, "保存了实际采购数量", "采购订单");
			// 保存ajax结果，用于JS判断
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
	 * 提交审核
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "submitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult submitCheck(@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		PurchaseOrder order = (PurchaseOrder) purchaseOrderService.getObjById(
				id, "PurchaseOrder");
		try {
			order.setState(Constant.CHECK_STATUS_WAITCHECK);
			purchaseOrderService.updateObj(order);
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
	 * 获取所有供应商信息
	 * 
	 * @return
	 */
	public List<ProviderInfo> selectProvider() {
		// 根据供应商类（对象）获取供应商集合，""中为查询条件
		List<ProviderInfo> providerInfoList = providerInfoService.getListByObj(
				ProviderInfo.class, "");
		return providerInfoList;
	}

	/**
	 * 拉取采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "pushGetPurchaseOrder", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushGetPurchaseOrder(
			@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		// 获取供应商信息集合
		List<ProviderInfo> providerInfoList = selectProvider();
		// 创建新的采购订单集合
		List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
		// 根据供应商数目，先假定采购订单数目
		if (providerInfoList != null && providerInfoList.size() > 0) {
			for (int i = 0; i < providerInfoList.size(); i++) {
				// 新增采购订单
				PurchaseOrder order = new PurchaseOrder();
				String id = UuidUtils.getUUID();
				// 存入随机ID
				order.setId(id);
				// 将供应商对象挨个存入采购订单
				order.setProviderInfo(providerInfoList.get(i));
				// 将采购订单的对象添加到采购订单的集合
				purchaseOrderList.add(order);
			}
		}
		// 获取页面信息
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String atualNums = request.getParameter("atualNums");
		// 返回一个下标从零开始的一维数组
		String[] idStr = ids.split(",");
		String[] num = null;
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					// 创建新的采购订单商品对象
					PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
					String id = idStr[i];
					GoodsFile goodsFile = new GoodsFile();
					// 获取原材料对象
					List<GoodsFile> nativeGood = goodsFileService.getListByObj(
							GoodsFile.class, "PRODUCTGOODSID='" + id + "'");
					if (nativeGood != null && nativeGood.size() > 0) {
						// 根据ID获取商品对象
						goodsFile = nativeGood.get(0);
						num = nums.split(",");
					} else {
						// 根据ID获取商品对象
						goodsFile = (GoodsFile) goodsFileService.getObjById(id,
								"GoodsFile");
						num = atualNums.split(",");
					}
					// 从商品对象中获取对应商品的供应商ID（用于判断）
					String provider = goodsFile.getProvider().getId();
					if (purchaseOrderList != null
							&& purchaseOrderList.size() > 0) {
						for (int a = 0; a < purchaseOrderList.size(); a++) {
							// 循环获取采购订单对象
							PurchaseOrder purchase = purchaseOrderList.get(a);
							// 获取采购订单中的上述已存入的供应商ID（用于判断）
							String orderId = purchase.getProviderInfo().getId();
							// 判断商品对象中的供应商ID与采购订单中的供应商ID是否相同，如果相同开始往采购订单商品对象中存值
							if (provider.equals(orderId)) {
								String purchaseId = UuidUtils.getUUID();
								String goodstableprice = goodsFile
										.getGoods_purchase_price() + "";
								double price = Double.valueOf(goodstableprice);
								String numStr = num[i];
								numStr = numStr
										.replace(
												"<span style='color:red;font-weight:700 '>",
												"");
								numStr = numStr.replace("</span>", "");
								double orderNum = Double.valueOf(numStr);
								double total = orderNum * price;
								BigDecimal moneytotal = new BigDecimal(total);
								double totalMoney = moneytotal.setScale(2,
										BigDecimal.ROUND_HALF_UP).doubleValue();
								// 计算实际采购数量
								String guiGe = goodsFile
										.getGoods_specifications();
								String str = "";
								if (guiGe != null && guiGe != "") {
									for (int j = 0; j < guiGe.length(); j++) {
										if ((guiGe.charAt(j) >= '0' && guiGe
												.charAt(j) <= '9')
												|| guiGe.charAt(j) == '.') {
											str += guiGe.charAt(j);
										}
									}
								}
								double actualNeed = Double.valueOf(str);
								actualNeed = actualNeed * orderNum;
								str = String.valueOf(actualNeed);
								// 获取当前系统日期
								Date now = new Date();
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyy-MM-dd");
								String produceDate = dateFormat.format(now);
								purchaseOrderGoodsItems.setId(purchaseId);
								purchaseOrderGoodsItems.setActualNeed(String
										.valueOf(orderNum));
								purchaseOrderGoodsItems.setPurchaseId(purchase
										.getId());
								purchaseOrderGoodsItems
										.setProduceDate(dateFormat
												.parse(produceDate));
								purchaseOrderGoodsItems.setGoodsCode(goodsFile
										.getGoods_code());
								purchaseOrderGoodsItems.setGoodsName(goodsFile
										.getGoods_name());
								purchaseOrderGoodsItems.setUnit(goodsFile
										.getGoods_unit());
								purchaseOrderGoodsItems.setGoodsFile(goodsFile);
								purchaseOrderGoodsItems
										.setSerialNumber(goodsFile
												.getSerialNumber());
								purchaseOrderGoodsItems.setGoodsPrice(price);
								purchaseOrderGoodsItems
										.setSpecifications(goodsFile
												.getGoods_specifications());
								purchaseOrderGoodsItems.setOrderNum(orderNum);
								purchaseOrderGoodsItems
										.setGoodsMoney(totalMoney);
								goodsFileService.saveObj(goodsFile);
								purchaseOrderGoodsItemsService
										.saveObj(purchaseOrderGoodsItems);
							}
						}
					}
				}
			}
			// 获取当前登录用户
			ZcUserInfo userInfo = (ZcUserInfo) request.getSession()
					.getAttribute("userInfo");
			// 采购订单插入
			if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
				for (int i = 0; i < purchaseOrderList.size(); i++) {
					double totalNum = 0;
					double price = 0;
					// 循环获取已存入供应商对象的采购订单对象
					PurchaseOrder order = purchaseOrderList.get(i);
					// 判断采购订单有无ID并获取其ID
					String id = order.getId() == null ? "" : order.getId();
					// 获取采购订单商品对象集合
					List<PurchaseOrderGoodsItems> purchaseOrderGoods = purchaseOrderGoodsItemsService
							.getListByObj(PurchaseOrderGoodsItems.class,
									"PURCHASEID='" + id + "'");
					// 根据时间格式化生成采购单号
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyyMMddHHmmssSSS");
					Date date = new Date();
					String str = formatDate.format(date);
					try {
						Thread.sleep(1);// 等待1毫秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (purchaseOrderGoods != null
							&& purchaseOrderGoods.size() > 0) {
						for (int j = 0; j < purchaseOrderGoods.size(); j++) {
							PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoods
									.get(j);
							price += purchaseOrderGoodsItems.getGoodsMoney();
							totalNum += Double.valueOf(purchaseOrderGoodsItems
									.getActualNeed());

						}
						String orderNum = "CGDD" + str;
						order.setMoney(price);
						order.setState(Constant.CHECK_STATUS_UNDO);
						order.setTotalNum(totalNum + "");
						order.setPurchaseMan(userInfo.getUserName());
						order.setOdd("CGDD" + str);
						Branch branch = (Branch) purchaseOrderService
								.getObjById(
										"D34BF1A9-58CA-4BE2-91D7-283782646DAD",
										"Branch");
						if (branch != null) {
							order.setBranch(branch);
						}
						Long countNum = ordersService.getCountByObj(
								PurchaseOrder.class, " purchase_odd='"
										+ orderNum + "'");
						if (countNum == 0) {
							purchaseOrderService.saveObj(order);
						} else {
							Date date1 = new Date();
							String str1 = formatDate.format(date1);
							order.setOdd("CGDD" + str1);
							purchaseOrderService.saveObj(order);
						}
					}
				}
			}
			logManageService.insertLog(request, "拉取今日订单汇总中商品信息", "采购订单");
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
	 * 一键生成当前商品的采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createPurchaseByGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createPurchaseByGoods(
			@ModelAttribute PurchaseOrder purchaseOrder, String orderId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String prices = request.getParameter("price");
		String branchId = request.getParameter("branchId");
		String produceDates = request.getParameter("produceDate");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] price = prices.split(",");
		String[] produceDate = produceDates.split(",");
		String providerId = purchaseOrder.getProviderInfo().getId();
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(providerId, "ProviderInfo");
		Branch branch = (Branch) branchService.getObjById(branchId, "Branch");
		List<ZcStorehouse> zcStorehouseList = providerInfoService.getListByObj(
				ZcStorehouse.class, "BRANCH_ID = '" + branchId + "'");
		orderId = UuidUtils.getUUID();
		purchaseOrder.setId(orderId);
		purchaseOrder.setPurchaseMan(userInfo.getUserName());
		purchaseOrder.setProviderInfo(providerInfo);
		purchaseOrder.setBranch(branch);
		purchaseOrder.setState(Constant.CHECK_STATUS_UNDO);
		double totalNum = 0;
		double money = 0;
		try {
			Long count = providerInfoService.getCountByObj(PurchaseOrder.class,
					"purchase_odd = '" + purchaseOrder.getOdd() + "'");
			String bianhao = "";
			if (count != 0) {
				SimpleDateFormat formatDate = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Date date = new Date();
				bianhao = formatDate.format(date);
				purchaseOrder.setOdd("CGDD" + bianhao);
			}
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
					String id = idStr[i];
					PurchaseOrderGoodsItems purchaseItems = (PurchaseOrderGoodsItems) purchaseOrderGoodsItemsService
							.getObjById(id, "PurchaseOrderGoodsItems");
					if (purchaseItems != null) {
						String goodsId = purchaseItems.getGoodsFile().getId();
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goodsId, "GoodsFile");
						if (goodsFile != null) {
							if (goodsFile.getProvider().getId() != providerInfo
									.getId()) {
								ajaxResult = new AjaxResult(AjaxResult.SAVE,
										AjaxResult.ERROR, AjaxResult.INFO);
								return ajaxResult;
							} else {
								double sum = Double.valueOf(num[i]);
								double number = sum;
								double goodsPrice = Double.valueOf(price[i]);
								String goodsItemsId = UuidUtils.getUUID();
								// 计算实际采购数量
								String guiGe = goodsFile
										.getGoods_specifications();
								String str = "";
								if (guiGe != null && guiGe != "") {
									for (int j = 0; j < guiGe.length(); j++) {
										if ((guiGe.charAt(j) >= '0' && guiGe
												.charAt(j) <= '9')
												|| guiGe.charAt(j) == '.') {
											str += guiGe.charAt(j);
										}
									}
								}
								double actualNeed = Double.valueOf(str);
								Long counts = purchaseOrderGoodsItemsService
										.getCountByObj(GoodsFile.class,
												"PRODUCTGOODSID is not null and ID='"
														+ goodsFile.getId()
														+ "'");
								double wasteRate = 0;
								if (counts > 0) {
									if (goodsFile.getWasteRate() != null) {
										wasteRate = Double.valueOf(goodsFile
												.getWasteRate());
									}
									actualNeed = actualNeed * sum
											/ ((100 - wasteRate) / 100);
									str = String.valueOf(actualNeed);
								} else {
									str = String.valueOf(sum);
								}
								double priceSum = Double.valueOf(str)
										* goodsPrice;
								money += priceSum;
								totalNum += Double.valueOf(str);
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyy-MM-dd");
								purchaseOrderGoodsItems.setId(goodsItemsId);
								purchaseOrderGoodsItems.setActualNeed(str);
								purchaseOrderGoodsItems
										.setPurchaseId(purchaseOrder.getId());
								purchaseOrderGoodsItems
										.setProduceDate(dateFormat
												.parse(produceDate[i]));
								purchaseOrderGoodsItems.setGoodsCode(goodsFile
										.getGoods_code());
								purchaseOrderGoodsItems
										.setSerialNumber(goodsFile
												.getSerialNumber());
								purchaseOrderGoodsItems.setGoodsName(goodsFile
										.getGoods_name());
								purchaseOrderGoodsItems.setUnit(goodsFile
										.getGoods_unit());
								purchaseOrderGoodsItems
										.setProviderInfo(goodsFile
												.getProvider());
								purchaseOrderGoodsItems
										.setGoodsPrice(goodsPrice);
								purchaseOrderGoodsItems
										.setSpecifications(goodsFile
												.getGoods_specifications());
								purchaseOrderGoodsItems.setGoodsFile(goodsFile);
								purchaseOrderGoodsItems.setOrderNum(number);
								purchaseOrderGoodsItems.setGoodsMoney(priceSum);
								purchaseOrderGoodsItemsService
										.deleteObjById(id,
												PurchaseOrderGoodsItems.class
														.getName());
								purchaseOrderGoodsItemsService
										.saveObj(purchaseOrderGoodsItems);
							}
						}
					}
				}
			}
			purchaseOrder.setTotalNum(totalNum + "");
			purchaseOrder.setMoney(money);
			purchaseOrderService.saveObj(purchaseOrder);
			List<ZcStorehouse> zcStorehousesList = purchaseOrderGoodsItemsService
					.getListByObj(ZcStorehouse.class, "status=9 ");
			if (zcStorehousesList != null && zcStorehousesList.size() > 0) {
				for (int j = 0; j < zcStorehousesList.size(); j++) {
					ZcStorehouse zcStorehouse = zcStorehousesList.get(j);
					purchaseOrderGoodsItemsService.deleteObjById(
							zcStorehouse.getId(), ZcStorehouse.class.getName());
				}
			}
			logManageService.insertLog(request, "勾选商品信息生成采购订单", "采购订单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			return ajaxResult;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				purchaseOrderService.deleteObjById(ids[i],
						PurchaseOrder.class.getName());
				List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemList = purchaseOrderGoodsItemsService
						.getListByObj(PurchaseOrderGoodsItems.class,
								"PURCHASEID='" + ids[i] + "'");
				if (purchaseOrderGoodsItemList != null
						&& purchaseOrderGoodsItemList.size() > 0) {
					for (int j = 0; j < purchaseOrderGoodsItemList.size(); j++) {
						PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoodsItemList
								.get(j);
						purchaseOrderGoodsItemsService.deleteObjById(
								purchaseOrderGoodsItems.getId(),
								PurchaseOrderGoodsItems.class.getName());
					}
				}
			}
			logManageService.insertLog(request, "删除了勾选的采购订单", "采购订单");
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
	 * 审核通过
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			PurchaseOrder order = (PurchaseOrder) purchaseOrderService
					.getObjById(id, "PurchaseOrder");
			order.setState(Constant.CHECK_STATUS_PASS);
			order.setCheckMan(userInfo.getUserName());
			order.setCheckDate(df.parse(df.format(new Date())));
			purchaseOrderService.updateObj(order);
			logManageService.insertLog(request, "审核通过系统自动修改审核状态为通过", "采购订单");
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
			PurchaseOrder order = (PurchaseOrder) purchaseOrderService
					.getObjById(id, "PurchaseOrder");
			order.setState(Constant.CHECK_STATUS_NOPASS);
			order.setCheckMan(userInfo.getUserName());
			if (order.getReason() != null && order.getReason().length() > 0) {
				order.setReason(order.getReason() + ";" + reason);
			} else {
				order.setReason(reason);
			}
			order.setCheckDate(df.parse(df.format(new Date())));
			purchaseOrderService.updateObj(order);
			logManageService.insertLog(request, "审核不通过系统修改审核状态待处理", "采购订单");
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
	 * 删除采购订单中的商品
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
			String purchaseId = "";
			for (int i = 0; i < idStr.length; i++) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				purchaseId = purchaseOrderGoodsItems.getPurchaseId();
				purchaseOrderGoodsItems.setActualNeed(num[i]);
				purchaseOrderGoodsItems.setProduceDate(dateFormat
						.parse(produceDate[i]));
				purchaseOrderGoodsItems.setGoodsPrice(Double
						.valueOf(goodsPrice[i]));
				purchaseOrderGoodsItems.setGoodsMoney(Double
						.valueOf(goodsPrice[i]) * Double.valueOf(num[i]));
				// 保存采购订单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseOrderGoodsItems);
				money += purchaseOrderGoodsItems.getGoodsPrice()
						* Double.valueOf(num[i]);
				totalNum += Double.valueOf(num[i]);
			}
			PurchaseOrder purchaseOrder = (PurchaseOrder) purchaseOrderService
					.getObjById(purchaseId, "PurchaseOrder");
			purchaseOrder.setMoney(money);
			purchaseOrder.setTotalNum(totalNum + "");
			purchaseOrder.setState(Constant.CHECK_STATUS_UNDO);
			purchaseOrderService.updateObj(purchaseOrder);
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) purchaseOrderGoodsItemsService
						.getObjById(ids[i], "PurchaseOrderGoodsItems");
				String purchaseOrderId = purchaseOrderGoodsItems
						.getPurchaseId();
				PurchaseOrder purchase = (PurchaseOrder) purchaseOrderService
						.getObjById(purchaseOrderId, "PurchaseOrder");
				double price = Double.valueOf(purchaseOrderGoodsItems
						.getGoodsPrice());
				int orderNum = (int) purchaseOrderGoodsItems.getOrderNum();
				double totalMoney = price * orderNum;
				purchase.setMoney(purchase.getMoney() - totalMoney);
				purchaseOrderGoodsItemsService.deleteObjById(ids[i],
						PurchaseOrderGoodsItems.class.getName());
				purchaseOrderService.updateObj(purchase);
			}
			logManageService.insertLog(request, "删除了采购订单中的一条商品", "采购订单");
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
		String storehouseId = request.getParameter("storehouseId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String prices = request.getParameter("price");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] price = prices.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) providerInfoService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				if (purchaseOrderGoodsItems != null) {
					purchaseOrderGoodsItems.setActualNeed(num[i]);
					purchaseOrderGoodsItems.setGoodsPrice(Double
							.valueOf(price[i]));
					providerInfoService.updateObj(purchaseOrderGoodsItems);
				}
			}
		}
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(providerId, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		model.addAttribute("storehouseId", storehouseId);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_choseGoods");
		return view;
	}

	/**
	 * 打开编辑商品时选择商品的面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openChooseGoods")
	public ModelAndView openChooseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String purchaseId = request.getParameter("purchaseId");
		String providerId = request.getParameter("providerId");
		String storehouseId = request.getParameter("storehouseId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String prices = request.getParameter("price");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] price = prices.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) providerInfoService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				if (purchaseOrderGoodsItems != null) {
					purchaseOrderGoodsItems.setActualNeed(num[i]);
					purchaseOrderGoodsItems.setGoodsPrice(Double
							.valueOf(price[i]));
					providerInfoService.updateObj(purchaseOrderGoodsItems);
				}
			}
		}
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(providerId, "ProviderInfo");
		model.addAttribute("purchaseId", purchaseId);
		model.addAttribute("providerInfo", providerInfo);
		model.addAttribute("storehouseId", storehouseId);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_chooseGoods");
		return view;
	}

	/**
	 * 分页查询商品表所有信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsMasterJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsMasterJson(@ModelAttribute GoodsFile goodsFile,
			String providerId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsFileService.getPurchaseAddGoodsItems(page,
					goodsFile, providerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询商品表所有信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPySameGoods", produces = "application/json")
	@ResponseBody
	public DataGrid listPySameGoods(@ModelAttribute GoodsFile goodsFile,
			String pyNum, String providerId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsFileService.getPurchaseAddPySameGoods(page,
					goodsFile, pyNum, providerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 根据商品表勾选直接生成采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		/*这是商品的id*/
		String ids = request.getParameter("ids");
		/*这是仓库的id*/
		String storehouseId = request.getParameter("storehouseId");
		/*这是采购的id（这边没有传进来）*/
		String purchaseId = request.getParameter("purchaseId");
		/*根据仓库id获取仓库*/
		Branch branch = (Branch) purchaseOrderGoodsItemsService.getObjById(
				storehouseId, "Branch");
		String[] idStr = ids.split(",");
		/*这个应该是获取用户信息*/
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
					String id = idStr[i];
					/*根据商品id获取商品信息*/
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(id, "GoodsFile");
					/*根据成品Id获取商品个数*/
					Long counts = goodsFileService.getCountByObj(
							GoodsFile.class,
							"PRODUCTGOODSID='" + goodsFile.getId() + "'");
					if (counts != 0) {
						List<GoodsFile> goodsList = goodsFileService
								.getListByObj(GoodsFile.class,
										"PRODUCTGOODSID='" + goodsFile.getId()
												+ "'");
						goodsFile = goodsList.get(0);
					}
					Long count = purchaseOrderGoodsItemsService.getCountByObj(
							ZcStorehouse.class, "goodsFile_id='" + goodsFile.getId()
									+ "' and branch_id='" + storehouseId + "'");
					if (count == 0) {
						ZcStorehouse zcStorehouse = new ZcStorehouse();
						zcStorehouse.setId(UuidUtils.getUUID());
						zcStorehouse.setBranch(branch);
						zcStorehouse.setGoodsFile(goodsFile);
						zcStorehouse.setStore("0");
						zcStorehouse.setStoreMoney("0");
						zcStorehouse.setStatus(9);
						purchaseOrderGoodsItemsService.saveObj(zcStorehouse);
					}
					String serial = goodsFile.getSerialNumber();
					List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemList = purchaseOrderGoodsItemsService
							.getListByObj(PurchaseOrderGoodsItems.class,
									"PURCHASEID is null and SERIALNUMBER='"
											+ serial + "'");
					if (purchaseOrderGoodsItemList != null
							&& purchaseOrderGoodsItemList.size() > 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						// 获取当前系统日期
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String produceDate = dateFormat.format(now);
						Float onePrice = goodsFile.getGoods_purchase_price();
						double goodsPrice = Double.parseDouble(String
								.valueOf(onePrice));
						if (goodsFile != null) {
							String goodsId = UuidUtils.getUUID();
							purchaseOrderGoodsItems.setId(goodsId);
							if (purchaseId != null) {
								purchaseOrderGoodsItems
										.setPurchaseId(purchaseId);
							}
							purchaseOrderGoodsItems.setProduceDate(dateFormat
									.parse(produceDate));
							purchaseOrderGoodsItems.setGoodsFile(goodsFile);
							purchaseOrderGoodsItems.setSerialNumber(goodsFile
									.getSerialNumber());
							purchaseOrderGoodsItems.setGoodsName(goodsFile
									.getGoods_name());
							purchaseOrderGoodsItems.setUnit(goodsFile
									.getGoods_unit());
							purchaseOrderGoodsItems.setProviderInfo(goodsFile
									.getProvider());
							purchaseOrderGoodsItems.setActualNeed("0");
							purchaseOrderGoodsItems.setGoodsPrice(goodsPrice);
							purchaseOrderGoodsItems.setPurchaseMan(user);
							purchaseOrderGoodsItems.setSpecifications(goodsFile
									.getGoods_specifications());
							purchaseOrderGoodsItemsService
									.saveObj(purchaseOrderGoodsItems);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到临时采购表", "采购订单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
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
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				purchaseOrderGoodsItems.setActualNeed(num[i]);
				purchaseOrderGoodsItems.setProduceDate(dateFormat
						.parse(produceDate[i]));
				purchaseOrderGoodsItems.setGoodsPrice(Double
						.valueOf(goodsPrice[i]));
				purchaseOrderGoodsItems.setGoodsMoney(Double
						.valueOf(goodsPrice[i]) * Double.valueOf(num[i]));
				// 保存采购订单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseOrderGoodsItems);
			}
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				purchaseOrderGoodsItemsService.deleteObjById(ids[i],
						PurchaseOrderGoodsItems.class.getName());
			}
			logManageService.insertLog(request, "移除了采购商品", "采购订单");
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
	 * 根据商品表勾选直接生成采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addPyGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addPyGoods(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoods,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		AjaxResult ajaxResult = null;
		String pyNum = request.getParameter("pyNum");
		String providerId = request.getParameter("providerId");
		String storehouseId = request.getParameter("storehouseId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String prices = request.getParameter("price");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] price = prices.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) providerInfoService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				if (purchaseOrderGoodsItems != null) {
					purchaseOrderGoodsItems.setActualNeed(num[i]);
					purchaseOrderGoodsItems.setGoodsPrice(Double
							.valueOf(price[i]));
					providerInfoService.updateObj(purchaseOrderGoodsItems);
				}
			}
		}
		Branch branch = (Branch) purchaseOrderGoodsItemsService.getObjById(
				storehouseId, "Branch");
		String provider = "";
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			List<GoodsFile> goodsFileList = goodsFileService.getListByObj(
					GoodsFile.class, "upper(GOODS_PY_CODE) like '%" + pyNum
							+ "%'");
			if (goodsFileList != null && goodsFileList.size() == 1) {
				for (int i = 0; i < goodsFileList.size(); i++) {
					GoodsFile goodsFile = goodsFileList.get(i);
					Long goodsCount = purchaseOrderGoodsItemsService
							.getCountByObj(ZcStorehouse.class, "goodsFile_id='"
									+ goodsFile.getId() + "' and branch_id='"
									+ storehouseId + "'");
					if (goodsCount == 0) {
						ZcStorehouse zcStorehouse = new ZcStorehouse();
						zcStorehouse.setId(UuidUtils.getUUID());
						zcStorehouse.setGoodsFile(goodsFile);
						zcStorehouse.setBranch(branch);
						zcStorehouse.setStore("0");
						purchaseOrderGoodsItemsService.saveObj(zcStorehouse);
					}
					String serial = goodsFile.getSerialNumber();
					Long count = purchaseOrderGoodsItemsService.getCountByObj(
							PurchaseOrderGoodsItems.class,
							"PURCHASEID is null and SERIALNUMBER='" + serial
									+ "'");
					if (count != 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						Float onePrice = goodsFile.getGoods_price();
						double goodsPrice = Double.parseDouble(String
								.valueOf(onePrice));
						provider = goodsFile.getProvider().getId();
						if (provider.equals(providerId)) {
							PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
							String goodsId = UuidUtils.getUUID();
							purchaseOrderGoodsItems.setId(goodsId);
							purchaseOrderGoodsItems.setGoodsFile(goodsFile);
							purchaseOrderGoodsItems.setSerialNumber(goodsFile
									.getSerialNumber());
							purchaseOrderGoodsItems.setGoodsName(goodsFile
									.getGoods_name());
							purchaseOrderGoodsItems.setUnit(goodsFile
									.getGoods_unit());
							purchaseOrderGoodsItems.setGoodsPrice(goodsPrice);
							purchaseOrderGoodsItems.setPurchaseMan(user);
							purchaseOrderGoodsItems.setProviderInfo(goodsFile
									.getProvider());
							purchaseOrderGoodsItems.setSpecifications(goodsFile
									.getGoods_specifications());
							purchaseOrderGoodsItemsService
									.saveObj(purchaseOrderGoodsItems);
							logManageService.insertLog(request,
									"保存了通过拼音码选择的商品到临时采购表", "采购订单");
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.SUCCESS, AjaxResult.INFO);
							return ajaxResult;
						} else {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.WARNING, AjaxResult.INFO);
							return ajaxResult;
						}
					}
				}
			} else if (goodsFileList != null && goodsFileList.size() > 1) {
				int count = 0;
				for (int i = 0; i < goodsFileList.size(); i++) {
					GoodsFile goodsFile = goodsFileList.get(i);
					if (goodsFile.getProvider() != null) {
						provider = goodsFile.getProvider().getId();
						if (!provider.equals(providerId)) {
							count += 1;
						}
					}
				}
				if (count > 0) {
					ajaxResult = new AjaxResult(AjaxResult.SELECT,
							AjaxResult.QUESTION, AjaxResult.INFO);
					return ajaxResult;
				}
				ajaxResult = new AjaxResult(AjaxResult.SELECT,
						AjaxResult.SUCCESS, AjaxResult.INFO);
				return ajaxResult;
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SELECT, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
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
	@RequestMapping("openPym")
	public ModelAndView openPym(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String pyNum = request.getParameter("pyNum");
		String providerId = request.getParameter("providerId");
		String storehouseId = request.getParameter("storehouseId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String prices = request.getParameter("price");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] price = prices.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) providerInfoService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				if (purchaseOrderGoodsItems != null) {
					purchaseOrderGoodsItems.setActualNeed(num[i]);
					purchaseOrderGoodsItems.setGoodsPrice(Double
							.valueOf(price[i]));
					providerInfoService.updateObj(purchaseOrderGoodsItems);
				}
			}
		}
		model.addAttribute("pyNum", pyNum);
		model.addAttribute("providerId", providerId);
		model.addAttribute("storehouseId", storehouseId);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchaseOrder_pySameGoods");
		return view;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute ZcStorehouse zcStorehouse,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getObjList1(zcStorehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listPinYinJson", produces = "application/json")
	@ResponseBody
	public List listPinYinJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getObjPinYinList(goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开初始化跳转商品汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initGoodsSearch")
	public ModelAndView initGoodsSearch(HttpServletRequest request,
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
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchase_searchGoods");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listSearchGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listSearchGoodsJson(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoodsItems,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getSearchGoodsDataGridObj(page,
					purchaseOrderGoodsItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 发送短信参数
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "sendCommom", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult sendCommom(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String ids = request.getParameter("ids");
			String mobilephone = request.getParameter("mobilephone");
			String messages = request.getParameter("messages");
			String[] idStr = ids.split(",");
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) purchaseOrderService
							.getObjById(idStr[i], "PurchaseOrderGoodsItems");
					purchaseOrderGoodsItems.setSendFlag("1");
					purchaseOrderService.updateObj(purchaseOrderGoodsItems);
				}
			}
			// 发送内容
			String content = messages;
			String sign = "众彩宜鲜美";
			int messageLength = content.length();
			int flag = 0;
			if (messageLength > 293) {
				int i = messageLength / 293 + 1;
				for (int j = 0; j < i; j++) {
					int a = j * 293;
					int b = j * 293 + 293;
					String message = "";
					if (j == i - 1) {
						b = messageLength;
						message = content.substring(a, b);
					} else {
						message = content.substring(a, b);
					}
					// 创建StringBuffer对象用来操作字符串
					StringBuffer sb = new StringBuffer(
							"http://sms.1xinxi.cn/asmx/smsservice.aspx?");
					// 向StringBuffer追加用户名
					sb.append("name=ymx");
					// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
					sb.append("&pwd=F7A83013B4D55E9E3C26737B4DC1");
					// 向StringBuffer追加手机号码
					sb.append("&mobile=" + mobilephone);
					// 向StringBuffer追加消息内容转URL标准码
					sb.append("&content=" + URLEncoder.encode(message, "UTF-8"));
					// 追加发送时间，可为空，为空为及时发送
					sb.append("&stime=");
					// 加签名
					sb.append("&sign=" + URLEncoder.encode(sign, "UTF-8"));
					// type为固定值pt extno为扩展码，必须为数字 可为空
					sb.append("&type=pt&extno=");
					// 创建url对象
					URL url = new URL(sb.toString());
					// 打开url连接
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					// 设置url请求方式 ‘get’ 或者 ‘post’
					connection.setRequestMethod("POST");
					// 发送
					BufferedReader in = new BufferedReader(
							new InputStreamReader(url.openStream()));
					// 返回发送结果
					String inputline = in.readLine();
					String[] input = inputline.split(",");
					// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
					System.out.println(inputline);
					if ("0".equals(input[0])) {
						flag = 0;
					} else {
						flag = 1;
					}
				}
			} else {
				// 创建StringBuffer对象用来操作字符串
				StringBuffer sb = new StringBuffer(
						"http://sms.1xinxi.cn/asmx/smsservice.aspx?");
				// 向StringBuffer追加用户名
				sb.append("name=ymx");
				// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
				sb.append("&pwd=F7A83013B4D55E9E3C26737B4DC1");
				// 向StringBuffer追加手机号码
				sb.append("&mobile=" + mobilephone);
				// 向StringBuffer追加消息内容转URL标准码
				sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
				// 追加发送时间，可为空，为空为及时发送
				sb.append("&stime=");
				// 加签名
				sb.append("&sign=" + URLEncoder.encode(sign, "UTF-8"));
				// type为固定值pt extno为扩展码，必须为数字 可为空
				sb.append("&type=pt&extno=");
				// 创建url对象
				URL url = new URL(sb.toString());
				// 打开url连接
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				// 设置url请求方式 ‘get’ 或者 ‘post’
				connection.setRequestMethod("POST");
				// 发送
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				// 返回发送结果
				String inputline = in.readLine();
				String[] input = inputline.split(",");
				// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
				System.out.println(inputline);
				if ("0".equals(input[0])) {
					flag = 0;
				} else {
					flag = 1;
				}
			}
			if (flag == 0) {
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxResult;
	}

	/**
	 * 转换返回值类型为UTF-8格式.
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb1 = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;

		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, "UTF-8");
				sb1.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb1.toString();
	}

	/**
	 * 发送短信参数
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "pushinfoEmail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushinfoEmail(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		String mail = request.getParameter("mail");
		String messages = request.getParameter("messages");
		List<EmailInfo> emailInfoList = purchaseOrderService.getListByObj(
				EmailInfo.class, "ID='10001'");
		try {
			if (emailInfoList != null && emailInfoList.size() > 0) {
				EmailInfo emailInfo = emailInfoList.get(0);
				// 设置邮件服务器信息
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setMailServerHost(emailInfo.getServiceName());
				mailInfo.setMailServerPort("25");
				mailInfo.setValidate(true);
				// 邮箱用户名
				mailInfo.setUserName(emailInfo.getSendEmail());
				// 邮箱密码
				mailInfo.setPassword(emailInfo.getPassword());
				// 发件人邮箱
				mailInfo.setFromAddress(emailInfo.getSendEmail());
				// 收件人邮箱
				mailInfo.setToAddress(mail);
				// 邮件标题
				mailInfo.setSubject("【众彩宜鲜美采购通知】");
				// 邮件内容
				StringBuffer buffer = new StringBuffer();
				buffer.append(messages);
				mailInfo.setContent(buffer.toString());
				// 发送邮件
				SimpleMailSender sms = new SimpleMailSender();
				// 发送文体格式
				if (sms.sendTextMail(mailInfo) == false) {
					ajaxResult = new AjaxResult(AjaxResult.SELECT,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}
				sms.sendTextMail(mailInfo);
				// 发送html格式
				if (SimpleMailSender.sendHtmlMail(mailInfo) == false) {
					ajaxResult = new AjaxResult(AjaxResult.SELECT,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}
				SimpleMailSender.sendHtmlMail(mailInfo);
				System.out.println("邮件发送完毕");
			} else {
				// 设置邮件服务器信息
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setMailServerHost("smtp.163.com");
				mailInfo.setMailServerPort("25");
				mailInfo.setValidate(true);
				// 邮箱用户名
				mailInfo.setUserName("jing930824@163.com");
				// 邮箱密码
				mailInfo.setPassword("xyz930824*");
				// 发件人邮箱
				mailInfo.setFromAddress("jing930824@163.com");
				// 收件人邮箱
				mailInfo.setToAddress(mail);
				// 邮件标题
				mailInfo.setSubject("【众彩宜鲜美采购通知】");
				// 邮件内容
				StringBuffer buffer = new StringBuffer();
				buffer.append(messages);
				mailInfo.setContent(buffer.toString());
				// 发送邮件
				SimpleMailSender sms = new SimpleMailSender();
				// 发送文体格式
				sms.sendTextMail(mailInfo);
				// 发送html格式
				SimpleMailSender.sendHtmlMail(mailInfo);
				System.out.println("邮件发送完毕");
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
	 * 打开初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initEmailInfo")
	public ModelAndView initEmailInfo(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<EmailInfo> emailInfoList = purchaseOrderGoodsItemsService
				.getListByObj(EmailInfo.class, "ID='10001'");
		if (emailInfoList != null && emailInfoList.size() > 0) {
			EmailInfo emailInfo = emailInfoList.get(0);
			String[] emailTails = emailInfo.getSendEmail().split("@");
			String tails = "@" + emailTails[1];
			String head = emailTails[0];
			model.addAttribute("head", head);
			model.addAttribute("tails", tails);
			model.addAttribute("emailInfo", emailInfo);
		}
		ModelAndView view = createIframeView("basic/provider/email_info");
		return view;
	}

	/**
	 * 提交审核
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "changeEmail", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult changeEmail(@ModelAttribute EmailInfo emailInfo,
			EmailService emailService, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(emailInfo.getServiceName());
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		// 邮箱用户名
		mailInfo.setUserName(emailInfo.getSendEmail()
				+ emailService.getEmailTails());
		// 邮箱密码
		mailInfo.setPassword(emailInfo.getPassword());
		// 发件人邮箱
		mailInfo.setFromAddress(emailInfo.getSendEmail()
				+ emailService.getEmailTails());
		// 收件人邮箱
		mailInfo.setToAddress(emailInfo.getSendEmail()
				+ emailService.getEmailTails());
		// 邮件标题
		mailInfo.setSubject("众彩宜鲜美邮箱配置成功");
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("众彩宜鲜美邮箱配置成功，将默认使用此邮箱作为发件箱：" + emailInfo.getSendEmail()
				+ emailService.getEmailTails());
		mailInfo.setContent(buffer.toString());
		// 发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		if (sms.sendTextMail(mailInfo) == false) {
			ajaxResult = new AjaxResult(AjaxResult.SELECT, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		try {
			emailInfo.setSendEmail(emailInfo.getSendEmail()
					+ emailService.getEmailTails());
			purchaseOrderService.updateObj(emailInfo);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listEmailServiceJson", produces = "application/json")
	@ResponseBody
	public List listEmailServiceJson(@ModelAttribute EmailService emailService,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = purchaseOrderService.getObjList3(emailService);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 下拉框数据源
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dataFrom", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String dataFrom(HttpServletRequest request,
			HttpServletResponse response, String id) {
		String pyNum = request.getParameter("pyNum");
		String json = "[";
		try {
			List<GoodsFile> goodsFileList = purchaseOrderService.getListByObj(
					GoodsFile.class, "goods_PY_code like '%" + pyNum + "%'");
			if (goodsFileList != null && goodsFileList.size() > 0) {
				for (int i = 0; i < goodsFileList.size(); i++) {
					GoodsFile goodsFile = goodsFileList.get(i);
					json += "\"" + goodsFile.getGoods_PY_code() + "\"" + ",";
				}
			}
			json = json.substring(0, json.length() - 1 > 0 ? json.length() - 1
					: 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json += "]";
		return json;
	}

	/**
	 * 根据商品表勾选直接生成采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItemsEdit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItemsEdit(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String storehouseId = request.getParameter("storehouseId");
		String purchaseId = request.getParameter("purchaseId");
		Branch branch = (Branch) purchaseOrderGoodsItemsService.getObjById(
				storehouseId, "Branch");
		String[] idStr = ids.split(",");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
					String id = idStr[i];
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(id, "GoodsFile");
					Long counts = goodsFileService.getCountByObj(
							GoodsFile.class,
							"PRODUCTGOODSID='" + goodsFile.getId() + "'");
					if (counts != 0) {
						List<GoodsFile> goodsList = goodsFileService
								.getListByObj(GoodsFile.class,
										"PRODUCTGOODSID='" + goodsFile.getId()
												+ "'");
						goodsFile = goodsList.get(0);
					}
					Long count = purchaseOrderGoodsItemsService.getCountByObj(
							ZcStorehouse.class, "goodsFile_id='" + id
									+ "' and branch_id='" + storehouseId + "'");
					if (count == 0) {
						ZcStorehouse zcStorehouse = new ZcStorehouse();
						zcStorehouse.setId(UuidUtils.getUUID());
						zcStorehouse.setBranch(branch);
						zcStorehouse.setGoodsFile(goodsFile);
						zcStorehouse.setStore("0");
						zcStorehouse.setStoreMoney("0");
						zcStorehouse.setStatus(9);
						purchaseOrderGoodsItemsService.saveObj(zcStorehouse);
					}
					String serial = goodsFile.getSerialNumber();
					List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemList = purchaseOrderGoodsItemsService
							.getListByObj(PurchaseOrderGoodsItems.class,
									"PURCHASEID='" + purchaseId
											+ "' and SERIALNUMBER='" + serial
											+ "'");
					if (purchaseOrderGoodsItemList != null
							&& purchaseOrderGoodsItemList.size() > 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						// 获取当前系统日期
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String produceDate = dateFormat.format(now);
						Float onePrice = goodsFile.getGoods_purchase_price();
						double goodsPrice = Double.parseDouble(String
								.valueOf(onePrice));
						if (goodsFile != null) {
							String goodsId = UuidUtils.getUUID();
							purchaseOrderGoodsItems.setId(goodsId);
							if (purchaseId != null) {
								purchaseOrderGoodsItems
										.setPurchaseId(purchaseId);
							}
							purchaseOrderGoodsItems.setProduceDate(dateFormat
									.parse(produceDate));
							purchaseOrderGoodsItems.setGoodsFile(goodsFile);
							purchaseOrderGoodsItems.setSerialNumber(goodsFile
									.getSerialNumber());
							purchaseOrderGoodsItems.setGoodsName(goodsFile
									.getGoods_name());
							purchaseOrderGoodsItems.setUnit(goodsFile
									.getGoods_unit());
							purchaseOrderGoodsItems.setProviderInfo(goodsFile
									.getProvider());
							purchaseOrderGoodsItems.setActualNeed("0");
							purchaseOrderGoodsItems.setGoodsPrice(goodsPrice);
							purchaseOrderGoodsItems.setPurchaseMan(user);
							purchaseOrderGoodsItems.setSpecifications(goodsFile
									.getGoods_specifications());
							purchaseOrderGoodsItemsService
									.saveObj(purchaseOrderGoodsItems);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到临时采购表", "采购订单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
}
