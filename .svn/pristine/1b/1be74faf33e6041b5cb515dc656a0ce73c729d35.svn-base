package com.proem.exm.controller.order;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.proem.exm.entity.Customer;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.CustomerService;
import com.proem.exm.entity.order.ZcHistoryOrder;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.order.ZcOrderDigits;
import com.proem.exm.entity.order.ZcOrderHistoryItem;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.order.ZcOrderProcessItem;
import com.proem.exm.entity.order.ZcOrderRefund;
import com.proem.exm.entity.order.ZcProcessOrder;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.basic.associator.AssociatorService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.order.OrdersDigitsService;
import com.proem.exm.service.order.OrdersItemService;
import com.proem.exm.service.order.OrdersService;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.CronExpConversion;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.DigitsFormat;
import com.proem.exm.utils.JdbcUtil;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.utils.UpdateNotificationInterval;

@Controller
// (模块名/类名)-小写
@RequestMapping("order/orders")
public class OrdersController extends BaseController {
	private static final Customer Customer = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	OrdersService ordersService;
	@Autowired
	OrdersItemService ordersItemService;
	@Autowired
	OrdersDigitsService ordersDigitsService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	GoodsFileService goodsFileService;
	@Autowired
	AssociatorService associatorService;
	@Autowired
	LogManageService logManageService;

	@InitBinder("zcOrder")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrder.");
	}

	@InitBinder("goodsFile")
	public void initGoods(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	@InitBinder("zcOrderItem")
	public void zcOrderItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderItem.");
	}

	@InitBinder("zcOrderDigits")
	public void zcOrderDigits(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderDigits.");
	}

	@InitBinder("goodsType")
	public void goodsType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsType.");
	}

	@InitBinder("zcHistoryOrder")
	public void zcHistoryOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcHistoryOrder.");
	}

	@InitBinder("zcOrderHistoryItem")
	public void zcOrderHistoryItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderHistoryItem.");
	}

	@InitBinder("zcProcessOrder")
	public void zcProcessOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcProcessOrder.");
	}

	@InitBinder("zcOrderProcessItem")
	public void zcOrderProcessItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderProcessItem.");
	}

	@InitBinder("zcOrderRefund")
	public void zcOrderRefund(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderRefund.");
	}

	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
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
			sonName = "订单查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_list");
		return view;
	}

	// 初始化跳转页面
	@RequestMapping("initProcess")
	public ModelAndView initProcess(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单信息";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "截单订单汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/order_process");
		return view;
	}

	// 初始化跳转今日订单页面
	@RequestMapping("initTodayOrder")
	public ModelAndView initTodayOrder(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单信息";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "接收订单明细";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_today_list");
		return view;
	}

	// 初始化跳转今日订单商品汇总页面
	@RequestMapping("initService")
	public ModelAndView initService(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单客服";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "订单客服";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_service_list");
		return view;
	}

	// 初始化跳转今日订单商品汇总页面
	@RequestMapping("initGoodsTotal")
	public ModelAndView initGoodsTotal(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单信息";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "接收订单汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_todayTotal_list");
		return view;
	}

	// 初始化跳转订单商品汇总页面
	@RequestMapping("initTotal")
	public ModelAndView initTotal(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
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
			sonName = "订单汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/order_total_list");
		return view;
	}

	// 打开新增页面
	@RequestMapping("gotoAddOrders")
	public ModelAndView gotoAddOrders(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("order/orders/orders_add");
		return view;
	}

	@RequestMapping("pushOrders")
	public ModelAndView pushOrders(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("order/orders/orders_push");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEditOrders")
	public ModelAndView gotoEditOrders(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("order/orders/orders_edit");
		return view;
	}

	// 打开退款客服处理页面
	@RequestMapping("gotoEditRefund")
	public ModelAndView gotoEditRefund(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String orderId = request.getParameter("orderId");
		ZcOrderRefund zcOrderRefund = (ZcOrderRefund) ordersService.getObjById(
				id, "ZcOrderRefund");
		ZcHistoryOrder zcOrder = (ZcHistoryOrder) ordersService.getObjById(
				orderId, "ZcHistoryOrder");
		model.addAttribute("id", id);
		model.addAttribute("orderId", orderId);
		model.addAttribute("zcOrderRefund", zcOrderRefund);
		model.addAttribute("zcOrder", zcOrder);
		ModelAndView view = createIframeView("order/orders/orders_refund_edit");
		return view;
	}

	@RequestMapping("itemList1")
	public ModelAndView itemList1(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
		model.addAttribute("zcOrder", zcOrder);
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("order/orders/orders_item_list");
		return view;
	}

	@RequestMapping("RefundItem")
	public ModelAndView RefundItem(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcOrderRefund zcOrderRefund = (ZcOrderRefund) ordersService.getObjById(
				id, "ZcOrderRefund");
		ZcHistoryOrder zcOrder = (ZcHistoryOrder) ordersService.getObjById(
				zcOrderRefund.getOrder_id(), "ZcHistoryOrder");
		model.addAttribute("zcOrderRefund", zcOrderRefund);
		model.addAttribute("zcOrder", zcOrder);
		ModelAndView view = createIframeView("order/orders/orders_refund_detail");
		return view;
	}

	@RequestMapping("itemList")
	public ModelAndView itemList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcHistoryOrder zcHistoryOrder = (ZcHistoryOrder) ordersService
				.getObjById(id, "ZcHistoryOrder");
		model.addAttribute("zcHistoryOrder", zcHistoryOrder);
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("order/orders/ordersItem_List");
		return view;
	}

	@RequestMapping("processItemList")
	public ModelAndView processItemList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
		model.addAttribute("zcOrder", zcOrder);
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("order/orders/order_processItem");
		return view;
	}

	// 打开详细页面
	@RequestMapping("gotoDetailOrders")
	public ModelAndView gotoDetailOrders(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
		model.addAttribute("zcOrder", zcOrder);
		ModelAndView view = createIframeView("order/orders/orders_detail");
		return view;
	}

	// 打开待处理订单详细
	@RequestMapping("openDetail")
	public ModelAndView openDetail(HttpServletRequest request, String id,
			HttpServletResponse response, Model model) {
		// String id = request.getParameter("id");
		// ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
		// model.addAttribute("zcOrder", zcOrder);
		model.addAttribute("goodfileId", id);
		ModelAndView view = createIframeView("order/orders/orders_handle_detail");
		return view;
	}

	/**
	 * 当日订单详情打开订单页面
	 * 
	 * @param request
	 * @param id
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openOrdersDetail")
	public ModelAndView openOrdersDetail(HttpServletRequest request, String id,
			HttpServletResponse response, Model model) {
		model.addAttribute("goodfileId", id);
		ModelAndView view = createIframeView("order/orders/orders_todayTotal_detail");
		return view;
	}

	// 打开客服订单详细
	@RequestMapping("openServiceDetail")
	public ModelAndView openServiceDetail(HttpServletRequest request,
			String id, HttpServletResponse response, Model model) {
		model.addAttribute("goodfileId", id);
		ModelAndView view = createIframeView("order/orders/orders_service_detail");
		return view;
	}

	// 打开客服订单商品详细
	@RequestMapping("showOrders")
	public ModelAndView showOrders(HttpServletRequest request, String id,
			HttpServletResponse response, Model model) {
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("order/orders/orders_service_ordersgoods");
		return view;
	}

	// 打开客服订单详细
	@RequestMapping("gotoEditService")
	public ModelAndView gotoEditService(HttpServletRequest request, String id,
			HttpServletResponse response, Model model) {
		model.addAttribute("goodfileId", id);
		ModelAndView view = createIframeView("order/orders/orders_service_edit");
		return view;
	}

	// 打开数值精度选择
	@RequestMapping("gotoOrdersDigits")
	public ModelAndView gotoOrdersDigits(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = "123456789";
		ZcOrderDigits zcOrderDigits = (ZcOrderDigits) ordersService.getObjById(
				id, "ZcOrderDigits");
		model.addAttribute("zcOrderDigits", zcOrderDigits);
		ModelAndView view = createIframeView("order/orders/orders_Digits");
		return view;
	}

	// 跳转待采购订单页面
	@RequestMapping("gotoHandle")
	public ModelAndView gotoHandle(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单信息";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "截单汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_handle_list");
		return view;
	}

	// 跳转待采购订单页面
	@RequestMapping("gotoRefund")
	public ModelAndView gotoRefund(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "订单管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单客服";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "退款客服";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("order/orders/orders_refund_list");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersJson(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getPagedDataGridObj(page, zcOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listTodayOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid listTodayOrdersJson(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getPagedDataGridObjTodayOrders(page,
					zcOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 获取今日订单商品数据
	@RequestMapping(value = "listTodayTotalJson", produces = "application/json")
	@ResponseBody
	public DataGrid listTodayTotalJson(@ModelAttribute GoodsFile goodsFile,
			String startTime, String endTime, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if (StringUtil.validate(goodsFile.getGoodsType())) {
				String typeId = goodsFile.getGoodsType().getId();
				GoodsType goodsType = (GoodsType) ordersService.getObjById(
						typeId, "GoodsType");
			}
			dataGrid = ordersService.getPagedDataGridObjTodayTotal(page,
					goodsFile, startTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;

	}

	// 获取订单商品数据
	@RequestMapping(value = "listTotalJson", produces = "application/json")
	@ResponseBody
	public DataGrid listTotalJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getPagedDataGridObjTotal(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;

	}

	/**
	 * 跳转待采购订单汇总
	 */
	@RequestMapping(value = "listHandleJson", produces = "application/json")
	@ResponseBody
	public DataGrid listHandleJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getHandleDataGrid(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转订单客服页面
	 */
	@RequestMapping(value = "listServiceJson", produces = "application/json")
	@ResponseBody
	public DataGrid listServiceJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getServiceDataGrid(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 订单退货客服页面数据来源
	 */
	@RequestMapping(value = "listRefundServiceJson", produces = "application/json")
	@ResponseBody
	public DataGrid listRefundServiceJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getRefundServiceDataGrid(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 提交客服处理
	 * 
	 * @param id
	 * @param nums
	 * @param actualNums
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "operation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult operation(String id, String nums, String actualNums,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			ZcOrderProcessItem item = null;
			List<ZcOrderProcessItem> items = ordersService.getListByObj(
					ZcOrderProcessItem.class, "goodsfile_id ='" + id + "'");
			GoodsFile goodsFile = (GoodsFile) ordersService.getObjById(id,
					"GoodsFile");
			if (goodsFile != null) {
				CustomerService customerService = new CustomerService();
				customerService.setGoodsFile(goodsFile);
				String guiGe = goodsFile.getGoods_specifications();
				String str = "";
				if (guiGe != null && guiGe != "") {
					for (int j = 0; j < guiGe.length(); j++) {
						if ((guiGe.charAt(j) >= '0' && guiGe.charAt(j) <= '9')
								|| guiGe.charAt(j) == '.') {
							str += guiGe.charAt(j);
						}
					}
				}
				double actualNeed = Double.valueOf(str);
				double nums1 = Double.valueOf(nums);
				double number = actualNeed * nums1;
				actualNums = String.valueOf(number);
				customerService.setNumbers(actualNums);
				goodsFile.setDelflag("9");
				if (items != null && items.size() > 0) {
					for (int i = 0; i < items.size(); i++) {
						item = items.get(i);
						ZcProcessOrder processOrder = (ZcProcessOrder) ordersService
								.getObjById(item.getOrder_id(),
										"ZcProcessOrder");
						processOrder
								.setOrderStatus(Constant.ORDER_STATUS_SERVICE);
						ordersService.updateObj(processOrder);
					}
				}
				ordersService.saveObj(goodsFile);
				customerService.setNums(nums);
				customerService.setCome("1");
				ordersService.saveObj(customerService);
				logManageService.insertLog(request, "选择包含选中商品的订单到订单客服", "订单客服");
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			}
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 处理通过，订单状态改为待处理
	@RequestMapping(value = "adopt", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult adopt(String ids, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			String goodfileId = request.getParameter("goodfileId");
			String[] id = ids.split(",");
			// ZcOrderProcessItem processItem = new ZcOrderProcessItem();
			CustomerService customerService = new CustomerService();
			if (id.length > 0 && id != null) {
				for (int i = 0; i < id.length; i++) {
					// 点击处理通过按钮，删掉选中订单中的待客服商品
					// List<ZcOrderProcessItem> list =
					// ordersService.getListByObj(ZcOrderProcessItem.class,
					// " order_id = '"+ id[i]
					// +"' and goodsFile_id='"+goodfileId+"'");
					// if(list != null && list.size() > 0){
					// processItem = list.get(0);
					// ordersService.deleteObjById(processItem.getId(),
					// ZcOrderProcessItem.class.getName());
					// }
					// 修改订单状态
					ZcProcessOrder processOrder = (ZcProcessOrder) ordersService
							.getObjById(id[i], "ZcProcessOrder");
					processOrder.setOrderStatus(Constant.ORDER_STATUS_HANDLE);
					ordersService.updateObj(processOrder);
					List<CustomerService> customerServiceList = ordersService
							.getListByObj(CustomerService.class,
									" goodsFile_id = '" + goodfileId + "'");
					if (customerServiceList != null
							&& customerServiceList.size() > 0) {
						for (int j = 0; j < customerServiceList.size(); j++) {
							customerService = customerServiceList.get(j);
							ordersService.deleteObjById(
									customerService.getId(),
									CustomerService.class.getName());
							logManageService.insertLog(request,
									"删除了选中的客服订单中的待客服处理商品", "订单客服");
						}
					}
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.SUCCESS, AjaxResult.INFO);
				}
			}
			GoodsFile file = (GoodsFile) ordersService.getObjById(goodfileId,
					"GoodsFile");
			file.setDelflag("1");
			ordersService.updateObj(file);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 客服撤销选中商品
	@RequestMapping(value = "revoke", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult revoke(String ids, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			String[] id = ids.split(",");
			ZcOrderProcessItem orderProcessItem = new ZcOrderProcessItem();
			GoodsFile goodsFile = new GoodsFile();
			ZcProcessOrder processOrder = new ZcProcessOrder();
			CustomerService customerService = new CustomerService();
			if (id.length > 0 && id != null) {
				for (int i = 0; i < id.length; i++) {
					List<ZcOrderProcessItem> goodsList = ordersService
							.getListByObj(ZcOrderProcessItem.class,
									"goodsfile_id = '" + id[i] + "'");
					goodsFile = (GoodsFile) ordersService.getObjById(id[i],
							"GoodsFile");
					goodsFile.setDelflag("0");
					ordersService.updateObj(goodsFile);
					if (goodsList != null && goodsList.size() > 0) {
						for (int j = 0; j < goodsList.size(); j++) {
							orderProcessItem = goodsList.get(j);
							processOrder = (ZcProcessOrder) ordersService
									.getObjById(orderProcessItem.getOrder_id(),
											"ZcProcessOrder");
							processOrder
									.setOrderStatus(Constant.ORDER_STATUS_HANDLE);
							ordersService.updateObj(processOrder);
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.SUCCESS, AjaxResult.INFO);
						}
					}
					List<CustomerService> customerServiceList = ordersService
							.getListByObj(CustomerService.class,
									"goodsFile_id = '" + id[i] + "'");
					if (customerServiceList != null
							&& customerServiceList.size() > 0) {
						for (int j = 0; j < customerServiceList.size(); j++) {
							customerService = customerServiceList.get(j);
							ordersService.deleteObjById(
									customerService.getId(),
									CustomerService.class.getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 客服撤销选中商品
	@RequestMapping(value = "removed", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removed(String ids, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			String[] id = ids.split(",");
			ZcOrderProcessItem orderProcessItem = new ZcOrderProcessItem();
			ZcProcessOrder processOrder = new ZcProcessOrder();
			String str = "";
			if (id.length > 0 && id != null) {
				for (int i = 0; i < id.length; i++) {
					orderProcessItem = (ZcOrderProcessItem) ordersService
							.getObjById(id[i], "ZcOrderProcessItem");
					GoodsFile file = orderProcessItem.getGoodsFile();
					file.setDelflag("1");
					ordersService.updateObj(file);
					str = orderProcessItem.getOrder_id();
					processOrder = (ZcProcessOrder) ordersService.getObjById(
							str, "ZcProcessOrder");
					double count = ordersService.getCountByObj(
							ZcOrderProcessItem.class, "id = '" + id[i] + "'");
					// 判断该商品否截单，>0 则已截单，反之该商品还在zc_order_item表中
					if (count > 0) {
						ordersService.deleteObjById(id[i],
								ZcOrderProcessItem.class.getName());
						processOrder
								.setOrderStatus(Constant.ORDER_STATUS_HANDLE);
						ordersService.updateObj(processOrder);
					} else {
						ordersService.deleteObjById(id[i],
								ZcOrderItem.class.getName());
					}
					// 判断删除商品所在的订单中是否还有待客服的商品
					List<ZcOrderProcessItem> processOrderItemList = ordersService
							.getListByObj(ZcOrderProcessItem.class,
									"order_id = '" + str + "'");
					if (processOrderItemList != null
							&& processOrderItemList.size() > 0) {
						for (int j = 0; j < processOrderItemList.size(); j++) {
							orderProcessItem = processOrderItemList.get(j);
							// 如果该订单中还有待客服的商品，订单状态应改为待客服
							if (orderProcessItem.getGoodsFile().getDelflag() == "9") {
								processOrder
										.setOrderStatus(Constant.ORDER_STATUS_SERVICE);
								ordersService.updateObj(processOrder);
							}
						}
					}
					ajaxResult = new AjaxResult(AjaxResult.DELETE,
							AjaxResult.SUCCESS, AjaxResult.INFO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 与客户确认之后，订单作废
	@RequestMapping(value = "invalid", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult invalid(String ids, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		AjaxResult ajaxResult = null;
		try {
			String[] id = ids.split(",");
			if (id.length > 0 && id != null) {
				for (int i = 0; i < id.length; i++) {
					ZcProcessOrder processOrder = (ZcProcessOrder) ordersService
							.getObjById(id[i], "ZcProcessOrder");
					processOrder.setOrderStatus(Constant.ORDER_STATUS_INVALID);
					ordersService.updateObj(processOrder);
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.SUCCESS, AjaxResult.INFO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 历史订单商品
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listOrdersItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersItemJson(
			@ModelAttribute ZcOrderHistoryItem zcOrderHistoryItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, Object> result = new HashMap();
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersItemService.getPagedDataGridObj(page,
					zcOrderHistoryItem);
			String id = zcOrderHistoryItem.getOrder_id() == null ? ""
					: zcOrderHistoryItem.getOrder_id();
			ZcHistoryOrder zcHistoryOrder = (ZcHistoryOrder) ordersService
					.getObjById(id, "ZcHistoryOrder");
			if (zcHistoryOrder == null) {
				zcHistoryOrder = new ZcHistoryOrder();
			}
			int nums = zcHistoryOrder.getGoodsNum();
			double amount = zcHistoryOrder.getOrderAmount();
			String orderAmount = df.format(amount);
			result.put(
					"NUMS",
					"<span style='font-family:华文中宋; color:blue;font-weight:901 '>&nbsp;&nbsp;&nbsp;&nbsp;"
							+ nums + "</span>");
			result.put("ITEM_TYPE", "");
			result.put(
					"SERIALNUMBER",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计</span>");
			result.put("GOODS_UNIT",
					"<span style='color:blue;font-weight:700 '>数量:</span>");
			result.put(
					"PRICE",
					"<span style='color:blue;font-weight:700 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额:</span>");
			result.put(
					"AMOUNT",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ orderAmount + "</span>");
			List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
			totalList.add(result);
			dataGrid.setFooter(totalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listServiceOrdersItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listServiceOrdersItemJson(
			@ModelAttribute ZcOrderProcessItem zcOrderProcessItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersItemService.getServiceDataGridObj(page,
					zcOrderProcessItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 接收订单明细商品
	 * 
	 * @param zcOrderHistoryItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listOrdersItemJson1", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersItemJson1(
			@ModelAttribute ZcOrderItem zcOrderItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, Object> result = new HashMap();
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersItemService
					.getPagedDataGridObj1(page, zcOrderItem);
			String id = zcOrderItem.getOrder_id() == null ? "" : zcOrderItem
					.getOrder_id();
			ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
			if (zcOrder == null) {
				zcOrder = new ZcOrder();
			}
			int nums = zcOrder.getGoodsNum();
			double amount = zcOrder.getOrderAmount();
			String orderAmount = df.format(amount);
			result.put(
					"NUMS",
					"<span style='font-family:华文中宋; color:blue;font-weight:901 '>&nbsp;&nbsp;&nbsp;&nbsp;"
							+ nums + "</span>");
			result.put("ITEM_TYPE", "");
			result.put(
					"SERIALNUMBER",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计</span>");
			result.put("GOODS_UNIT",
					"<span style='color:blue;font-weight:700 '>数量:</span>");
			result.put(
					"PRICE",
					"<span style='color:blue;font-weight:700 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额:</span>");
			result.put(
					"AMOUNT",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ orderAmount + "</span>");
			List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
			totalList.add(result);
			dataGrid.setFooter(totalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listRefundItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listRefundItemJson(
			@ModelAttribute ZcOrderRefund zcOrderRefund,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersItemService.getRefundDataGrid(page, zcOrderRefund);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listProcessItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProcessItemJson(
			@ModelAttribute ZcOrderItem zcOrderItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, Object> result = new HashMap();
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersItemService.getOrderProcessDataGridObj(page,
					zcOrderItem);
			String id = zcOrderItem.getOrder_id() == null ? "" : zcOrderItem
					.getOrder_id();
			ZcOrder zcOrder = (ZcOrder) ordersService.getObjById(id, "ZcOrder");
			if (zcOrder == null) {
				zcOrder = new ZcOrder();
			}
			int nums = zcOrder.getGoodsNum();
			double amount = zcOrder.getOrderAmount();
			String orderAmount = df.format(amount);
			result.put(
					"NUMS",
					"<span style='font-family:华文中宋; color:blue;font-weight:901 '>&nbsp;&nbsp;&nbsp;&nbsp;"
							+ nums + "</span>");
			result.put("ITEM_TYPE", "");
			result.put(
					"SERIALNUMBER",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计</span>");
			result.put("GOODS_UNIT",
					"<span style='color:blue;font-weight:700 '>数量:</span>");
			result.put(
					"PRICE",
					"<span style='color:blue;font-weight:700 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额:</span>");
			result.put(
					"AMOUNT",
					"<span style='font-family:华文中宋; color:blue;font-weight:900 '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ orderAmount + "</span>");
			List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
			totalList.add(result);
			dataGrid.setFooter(totalList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	// 新增数据
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {

			String id = UuidUtils.getUUID();
			zcOrder.setId(id);
			ordersService.saveObj(zcOrder);
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
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getObjById", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getObjById(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = null;
		try {
			// map = ordersService.getObjById(zcOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	// 修改数据
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {

			if (StringUtil.validate(zcOrder.getId())) {
				ordersService.updateObj(zcOrder);
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

	// 拉取任务
	@RequestMapping(value = "pushGetInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushGetInfo(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		AjaxResult ajaxResult = null;
		Connection conn = JdbcUtil.getConnection();
		try {
			selectFromJDBC(conn, joinCondition(zcOrder), request);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		} finally {
			conn.close();
		}
		return ajaxResult;
	}

	/**
	 * 修改数值保留小数位数
	 * 
	 * @param zcOrderDigits
	 * @param request
	 * @param response
	 * @throws SQLException
	 */
	@RequestMapping(value = "getOrderDigitsDecimalFormat", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void getOrderDigitsDecimalFormat(
			@ModelAttribute ZcOrderDigits zcOrderDigits,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		try {
			String id = zcOrderDigits.getCount_odsi() == null ? "9"
					: zcOrderDigits.getCount_odsi();
			System.out.println("传入的id:" + id);
			selectNeedChangeValue(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存小数精度
	 */
	@RequestMapping(value = "saveDigits", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveDigits(@ModelAttribute ZcOrderDigits zcOrderDigits1,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String numVal = request.getParameter("numVal");
			List<ZcOrderDigits> list = ordersService.getListByObj(
					ZcOrderDigits.class, " id = '123456789' ");
			ZcOrderDigits zcOrderDigits = list.get(0);
			if (numVal != "" && numVal != null) {
				zcOrderDigits.setCount_odsi(numVal);
				zcOrderDigits.setMoneyAccuracy(numVal);
			}
			ordersService.updateObj(zcOrderDigits);
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
	 * 修改数值保留小数位数——订单总额
	 * 
	 * @param id
	 *            小数位数
	 */
	public void selectNeedChangeValue(String id) {
		List<ZcOrder> orderList = ordersService.getListByObj(ZcOrder.class, "");
		if (orderList != null && orderList.size() > 0) {
			ZcOrderDigits zcOrderDigits = new ZcOrderDigits();

			double needChangeValue = 0;
			for (int i = 0; i < orderList.size(); i++) {
				ZcOrder order = orderList.get(i);
				needChangeValue = order.getOrderAmount();
				String changeValue = DigitsFormat.changeValue(id,
						needChangeValue);
				zcOrderDigits.setDigitsAmount(changeValue);
				zcOrderDigits.setOrderAmount(Double.toString(needChangeValue));
				zcOrderDigits.setCount_odsi(id);
				System.out.println("need:" + needChangeValue + "   change:"
						+ changeValue);
				ordersService.saveObj(zcOrderDigits);
			}
			System.out.println("修改的精度：" + zcOrderDigits.getCount_odsi()
					+ "     change:" + zcOrderDigits.getDigitsAmount()
					+ "   need:" + zcOrderDigits.getOrderAmount());
		}
	}

	// /**
	// * 修改数值保留小数位数
	// * @param id 小数位数
	// * @param needChangeValue 需要修改的值
	// * @return changeValue 修改后的值
	// */
	// public String changeValue(String id,double needChangeValue) {
	// String con1="1";
	// String con2="2";
	// String con3="3";
	// String con4="4";
	// String con5="5";
	// String con6="6";
	// String con7="7";
	// String con8="8";
	// String con9="9";
	// String changeValue="";
	// if (id.equals(con9)) {
	// System.out.println("id9:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// "#");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con1)) {
	// System.out.println("id1:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".0");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con2)) {
	// System.out.println("id2:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".00");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con3)) {
	// System.out.println("id3:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".000");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con4)) {
	// System.out.println("id4:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".0000");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con5)) {
	// System.out.println("id5:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".00000");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con6)) {
	// System.out.println("id6:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".000000");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con7)) {
	// System.out.println("id7:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".0000000");
	// changeValue = df.format(needChangeValue);
	// }
	// if (id.equals(con8)) {
	// System.out.println("id8:"+id);
	// java.text.DecimalFormat df = new java.text.DecimalFormat(
	// ".00000000");
	// changeValue = df.format(needChangeValue);
	// }
	// return changeValue;
	// }

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcOrder orders = (ZcOrder) obj;
		String conditions = "";
		// if (StringUtil.validate(orders.getOrderCome())) {
		// conditions += " and a.source='" + orders.getOrderCome() + "'";
		// }
		if (StringUtil.validate(orders.getOrderDate())) {
			String startDate = sdf.format(orders.getOrderDate());
			conditions += " and a.createtime >= UNIX_TIMESTAMP('" + startDate
					+ "')";
		}
		if (StringUtil.validate(orders.getUpdateTime())) {
			String endDate = sdf.format(orders.getUpdateTime());
			conditions += " and a.createtime <= UNIX_TIMESTAMP('" + endDate
					+ "')";
		}
		return conditions;
	}

	/**
	 * 送数据库中读取订单数据进行拉取
	 * 
	 * @param con
	 * @param conditionStr
	 * @param request
	 */
	public void selectFromJDBC(Connection con, String conditionStr,
			HttpServletRequest request) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try

		{

			String sql = "SELECT a.order_id,a.final_amount,a.status,FROM_UNIXTIME(a.createtime,'%Y-%m-%d %H:%i:%s'), a.source,a.ship_name,a.ship_mobile,a.ship_addr, a.total_amount,a.discount, a.pmt_order ,a.member_id,a.itemnum ,a.ship_area FROM sdb_b2c_orders  a where 1=1 and a.status !='dead'";
			sql += conditionStr;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			int i = 1;
			while (rs.next()) {
				String orderNum = rs.getString(1);
				String condition = "orderNum='" + orderNum + "'";
				Long count = ordersService.getCountByObj(ZcOrder.class,
						condition);
				if (count == 0) {
					InsertItem(1, con, orderNum);
					ZcOrder zcOrder = new ZcOrder();
					ZcZoning zcZoning = new ZcZoning();
//					String zoningId = UuidUtils.getUUID();
//					zcZoning.setId(zoningId);
					zcZoning.setStreet(rs.getString(8));
					zcZoningService.saveObj(zcZoning);
					String id = UuidUtils.getUUID();
					zcOrder.setId(rs.getString(1));
					zcOrder.setOrderNum(rs.getString(1));
					zcOrder.setOrderTotalValue(rs.getFloat(2));
					zcOrder.setOrderStatus("1");
					Date orderDate = sdf.parse(rs.getString(4));
					zcOrder.setOrderDate(orderDate);
					zcOrder.setOrderCome("1");
					zcOrder.setConsignee(rs.getString(6));
					zcOrder.setCansignPhone(rs.getString(7));
					zcOrder.setZcZoning(zcZoning);
					zcOrder.setOrderAmount(rs.getFloat(9));
					zcOrder.setOrderReduceAmount(rs.getFloat(10));
					zcOrder.setIsGift(rs.getString(11));
					String memberId = rs.getString(12);
					Long countMember = associatorService.getCountByObj(
							Associator.class, "id='" + memberId + "'");
					if (countMember == 0) {
						InsertItem(2, con, rs.getString(12));
						Associator associator = (Associator) associatorService
								.getObjById(memberId, "Associator");
						zcOrder.setAssociator(associator);
					} else {
						Associator associator = (Associator) associatorService
								.getObjById(memberId, "Associator");
						zcOrder.setAssociator(associator);
					}
					zcOrder.setMemberCardNumber(rs.getString(12));
					zcOrder.setGoodsNum(rs.getInt(13));
					String area = rs.getString(14);
					String[] areaStr = area.split(":");
					String areaId ="";
					if(areaStr!=null && areaStr.length==3){
						areaId= areaStr[2];
					}
					zcOrder.setBranchId(areaId);
					ordersService.saveObj(zcOrder);
					System.out.println("------------" + i);
				} else {
					// TODO 如果重复的是保存还是更新
				}
				i++;
			}
			logManageService.insertLog(request, "拉取了订单", "订单管理");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void InsertItem(int type, Connection con, String orderId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (type == 1) {
				sql = " SELECT item_id,obj_id,product_id,goods_id,type_id,bn,name,cost,price,g_price,amount,score,weight,nums,sendnum,addon,item_type  FROM sdb_b2c_order_items  where order_id='"
						+ orderId + "' ";
			}
			if (type == 2) {
				sql = "SELECT b.value,a.member_id,a.name,a.member_lv_id,a.point_history,a.point_freeze,a.point,a.advance,a.advance_freeze , "
						+ "FROM_UNIXTIME(a.regtime,'%Y-%m-%d %H:%i:%s'),a.sex,a.wedlock,(a.`b_year`+a.`b_month`+a.`b_day`) AS birthday,a.tel,a.mobile,a.email,c.addr,a.zip,a.remark FROM sdb_b2c_members a "
						+ "LEFT JOIN sdb_dbeav_meta_value_varchar b ON b.pk=a.member_id  "
						+ "LEFT JOIN sdb_b2c_member_addrs c ON c.member_id=a.member_id where a.member_id='"
						+ orderId + "'";
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				if (type == 1) {
					ZcOrderItem zcOrderItem = new ZcOrderItem();
					zcOrderItem.setId(rs.getString(1));
					zcOrderItem.setOrder_id(orderId);
					zcOrderItem.setObj_id(rs.getString(1));
					zcOrderItem.setProduct_id(rs.getString(3));
					String goodId = rs.getString(6);
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(goodId, "GoodsFile");
					zcOrderItem.setGoodsFile(goodsFile);
					zcOrderItem.setType_id(rs.getString(5));
					zcOrderItem.setBn(rs.getString(6));
					zcOrderItem.setName(rs.getString(7));
					zcOrderItem.setCost(rs.getString(8));
					zcOrderItem.setPrice(rs.getString(9));
					zcOrderItem.setG_price(rs.getString(10));
					zcOrderItem.setAmount(rs.getString(11));
					zcOrderItem.setScore(rs.getString(12));
					zcOrderItem.setWeight(rs.getString(13));
					zcOrderItem.setGoodsState("1");
					zcOrderItem.setNums(rs.getString(14));
					zcOrderItem.setSendNum(rs.getString(15));
					// zcOrderItem.setAddon(rs.getFloat(16));
					zcOrderItem.setItem_type(rs.getString(17));
					ordersItemService.saveObj(zcOrderItem);
				}
				if (type == 2) {
					Associator associator = new Associator();
					associator.setAssociator_CardNumber(rs.getString(1));
					associator.setId(rs.getString(2));
					associator.setAssociator_Name(rs.getString(3));
					associator.setAssociator_Category(rs.getString(4));
					associator.setAssociator_AccumulatedCredit(rs.getInt(5));
					associator.setAssociator_UsedCredit(rs.getInt(6));
					associator.setAssociator_Credit(rs.getInt(7));
					associator.setAssociator_Amount(rs.getDouble(8));
					associator.setAssociator_ConsumeAmount(rs.getDouble(9));
					Date regDate = sdf.parse(rs.getString(10));
					associator.setAssociator_AdmissionDate(regDate);
					associator.setAssociator_Sex(rs.getString(11));
					associator.setAssociator_MaritalStatus(rs.getString(12));
					// 设置生日
					// associator.setAssociator_Birthday(rs.getDate(13));
					associator.setAssociator_Telephone(rs.getString(14));
					associator.setAssociator_Mobilephone(rs.getString(15));
					associator.setAssociator_Email(rs.getString(16));
					associator.setAssociator_Address(rs.getString(17));
					associator.setAssociator_Zipcode(rs.getString(18));
					associator.setAssociator_Note(rs.getString(19));
					associatorService.saveObj(associator);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理待采购订单
	 */
	@RequestMapping(value = "handle", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult handle(HttpServletRequest request,
			HttpServletResponse response, String ids) {
		AjaxResult ajaxResult = null;
		String[] idStr = ids.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					List<ZcOrderItem> zcOrderItemList = ordersService
							.getListByObj(ZcOrderItem.class,
									" goodsfile_id = '" + idStr[i]
											+ "' and goods_state = '2' ");
					// TODO 与库存对比
					if (zcOrderItemList != null && zcOrderItemList.size() > 0) {
						for (int j = 0; j < zcOrderItemList.size(); j++) {
							ZcOrderItem orderItem = zcOrderItemList.get(j);
							orderItem
									.setGoodsState(Constant.ORDER_STATUS_PROCESSING);
							ordersService.updateObj(orderItem);
						}
					}
				}
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

	@RequestMapping(value = "handlelistOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid handlelistOrdersJson(@ModelAttribute ZcOrder zcOrder,
			String goodfileId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getPagedDataGridByHandle(page, zcOrder,
					goodfileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "todayOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid todayOrdersJson(@ModelAttribute ZcOrder zcOrder,
			String goodfileId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = ordersService.getPagedDataGridByTodayOrders(page,
					zcOrder, goodfileId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 编辑页面展示父级菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String, Object>> getTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		return ordersService.getTreeData();
	}

	// 退款通过
	@RequestMapping(value = "pass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pass(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			ZcOrderRefund orderRefund = (ZcOrderRefund) ordersService
					.getObjById(id, "ZcOrderRefund");
			orderRefund.setOrder_refund_status("2");
			ordersService.updateObj(orderRefund);
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	// 退款通过
	@RequestMapping(value = "nopass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult nopass(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			ZcOrderRefund orderRefund = (ZcOrderRefund) ordersService
					.getObjById(id, "ZcOrderRefund");
			orderRefund.setOrder_refund_status("3");
			ordersService.updateObj(orderRefund);
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	@RequestMapping(value = "setOrderTime", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pushGetInfo(@ModelAttribute ZcOrder zcOrder,
			ZcOrder zcOrder1, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		AjaxResult ajaxResult = null;
		String cronExpression = "";
		try {
			// zcZoningService.updateObj(zcOrder);
			cronExpression = CronExpConversion.convertDateToCronExp(zcOrder);
			UpdateNotificationInterval.updateNotificationInterval(
					"DailyTaskCronTriggerBean", "DailyTaskCronTriggerBean",
					cronExpression);
			logManageService.insertLog(request, "设置了订单拉取定时任务", "定时任务");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		} finally {
		}
		return ajaxResult;
	}

}
