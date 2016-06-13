package com.proem.exm.controller.wholesaleGroupPurchase;

import java.io.UnsupportedEncodingException;
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
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSell;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSellGoodsItems;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.service.wholesaleGroupPurchase.customer.CustomerInfoService;
import com.proem.exm.service.wholesaleGroupPurchase.customer.WholeSellService;
import com.proem.exm.service.wholesaleGroupPurchase.order.WholesaleGroupPurchaseOrderItemService;
import com.proem.exm.service.wholesaleGroupPurchase.order.WholesaleGroupPurchaseOrderService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Controller
@RequestMapping("/wholesaleGroupPurchase/order")
public class WholesaleGroupPurchaseOrderController extends BaseController {

	@Autowired
	private WholesaleGroupPurchaseOrderService wholeGroupService;

	@Autowired
	private ZcUserInfoService zcUserInfoService;

	@Autowired
	private GoodsFileService GoodsFileService;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private WholesaleGroupPurchaseOrderItemService wholeGroupItemService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private WholeSellService wholeSellService;

	@InitBinder("wholesaleGroupPurchaseOrder")
	public void initWholesaleGroupPurchaseOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("wholesaleGroupPurchaseOrder.");
	}

	@InitBinder("wholesaleGroupPurchaseOrderItem")
	public void initWholesaleGroupPurchaseOrderItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("orderItem.");
	}

	@InitBinder("goodsFile")
	public void initGoodsFile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
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
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "批发团购管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "批发团购订单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_list");
		return view;
	}

	/**
	 * 跳转到添加团购订单界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<WholesaleGroupPurchaseOrderItem> list = wholeGroupItemService
				.getListByObj(WholesaleGroupPurchaseOrderItem.class,
						" order_id is null ");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				wholeGroupItemService.deleteObjById(list.get(i).getId(),
						WholesaleGroupPurchaseOrderItem.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_add");
		return view;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoEditOrder")
	public ModelAndView gotoEditOrder(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<WholesaleGroupPurchaseOrderItem> list = wholeGroupItemService
				.getListByObj(WholesaleGroupPurchaseOrderItem.class,
						" order_id is null ");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				wholeGroupItemService.deleteObjById(list.get(i).getId(),
						WholesaleGroupPurchaseOrderItem.class.getName());
			}
		}
		String id = request.getParameter("id");
		WholesaleGroupPurchaseOrder obj = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, "WholesaleGroupPurchaseOrder");
		model.addAttribute("order", obj);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_edit");
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
	@RequestMapping(value = "listWholeGroupOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseOrderJson(
			@ModelAttribute WholesaleGroupPurchaseOrder wholesaleGroupPurchaseOrder,
			String startDate, String endDate, String state,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wholeGroupService.getPagedDataGridObj(page,
					wholesaleGroupPurchaseOrder, startDate, endDate, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listGoodsItemsNullOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsNullOrderJson(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wholeGroupItemService.getPagedDataGridObj(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listUser", produces = "application/json")
	@ResponseBody
	public List<ZcUserInfo> listJson(HttpServletRequest request,
			HttpServletResponse response) {
		List<ZcUserInfo> list = null;
		try {
			list = zcUserInfoService.getAllObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
		String idOld = request.getParameter("idStr");
		String nums = request.getParameter("num");
		String rates = request.getParameter("rate");
		String goodsPrices = request.getParameter("goodsPrice");
		String[] goodsPrice = goodsPrices.split(",");
		String[] rate = rates.split(",");
		String[] idStr = idOld.split(",");
		String[] num = nums.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				WholesaleGroupPurchaseOrderItem wholesaleGroupPurchaseOrderItem = (WholesaleGroupPurchaseOrderItem) wholeGroupService
						.getObjById(idStr[i], "WholesaleGroupPurchaseOrderItem");
				if (wholesaleGroupPurchaseOrderItem != null) {
					wholesaleGroupPurchaseOrderItem.setNums(num[i]);
					wholesaleGroupPurchaseOrderItem
							.setOrderPrice(goodsPrice[i]);
					wholesaleGroupPurchaseOrderItem.setRate(rate[i]);
					wholesaleGroupPurchaseOrderItem.setAmount((Double
							.valueOf(goodsPrice[i]) * Double.valueOf(num[i]))
							+ "");
					wholeGroupService
							.updateObj(wholesaleGroupPurchaseOrderItem);
				}
			}
		}
		String customerId = request.getParameter("customerId");
		model.addAttribute("customerId", customerId);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_choseGoods");
		return view;
	}

	/**
	 * 打开编辑页面新增商品的页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openChooseGoods")
	public ModelAndView openChooseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String customerId = request.getParameter("customerId");
		String idOld = request.getParameter("idStr");
		String nums = request.getParameter("num");
		String rates = request.getParameter("rate");
		String goodsPrices = request.getParameter("goodsPrice");
		String orderId = request.getParameter("orderId");
		String[] goodsPrice = goodsPrices.split(",");
		String[] rate = rates.split(",");
		String[] idStr = idOld.split(",");
		String[] num = nums.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				WholesaleGroupPurchaseOrderItem wholesaleGroupPurchaseOrderItem = (WholesaleGroupPurchaseOrderItem) wholeGroupService
						.getObjById(idStr[i], "WholesaleGroupPurchaseOrderItem");
				if (wholesaleGroupPurchaseOrderItem != null) {
					wholesaleGroupPurchaseOrderItem.setNums(num[i]);
					wholesaleGroupPurchaseOrderItem
							.setOrderPrice(goodsPrice[i]);
					wholesaleGroupPurchaseOrderItem.setRate(rate[i]);
					wholesaleGroupPurchaseOrderItem.setAmount((Double
							.valueOf(goodsPrice[i]) * Double.valueOf(num[i]))
							+ "");
					wholeGroupService
							.updateObj(wholesaleGroupPurchaseOrderItem);
				}
			}
		}
		model.addAttribute("customerId", customerId);
		model.addAttribute("orderId", orderId);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_chooseGoods");
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
			String customerId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = GoodsFileService.getGoodsInfo(page, goodsFile,
					customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ordersId = request.getParameter("ordersId");
		String idsString = request.getParameter("ids");
		String pricesString = request.getParameter("prices");
		String[] ids = idsString.split(",");
		String[] prices = pricesString.split(",");
		try {
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					String id = ids[i];
					String price = prices[i];
					List<WholesaleGroupPurchaseOrderItem> itemList = wholeGroupItemService
							.getListByObj(
									WholesaleGroupPurchaseOrderItem.class,
									"order_id is null and goods_file_id = '"
											+ id + "'");
					if (itemList != null && itemList.size() > 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						GoodsFile goodsFile = (GoodsFile) GoodsFileService
								.getObjById(id, "GoodsFile");
						if (goodsFile != null) {
							WholesaleGroupPurchaseOrderItem obj = new WholesaleGroupPurchaseOrderItem();
							obj.setId(UuidUtils.getUUID());
							if (ordersId != null) {
								obj.setOrderId(ordersId);
							}
							if (price == null || "null".equals(price)
									|| "".equals(price)) {
								price = goodsFile.getGoods_price() + "";
							}
							if (goodsFile.getOut_tax() == null) {
								obj.setRate("0");
							} else {
								obj.setRate(goodsFile.getOut_tax() + "");
							}
							obj.setGoodsFile(goodsFile);
							obj.setOrderPrice(price);
							obj.setNums("0");
							wholeGroupItemService.saveObj(obj);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到临时批发团购表", "批发团购订单");
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
	 * 仓库信息下拉
	 * 
	 * @return
	 */
	@RequestMapping(value = "listBranch", produces = "application/json")
	@ResponseBody
	public List listBranch() {
		List list = null;
		try {
			list = branchService.getObjList(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "createWholeByGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createWholeByGoods(
			@ModelAttribute WholesaleGroupPurchaseOrder order,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String prices = request.getParameter("price");
		String rates = request.getParameter("rate");
		String branchId = request.getParameter("branchId");
		Branch branch = (Branch) branchService.getObjById(branchId, "Branch");
		String payType = request.getParameter("payType");
		String customerId = request.getParameter("customerId");
		String[] idArray = ids.split(",");
		String[] numArray = nums.split(",");
		String[] priceArray = prices.split(",");
		String[] rateArray = rates.split(",");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		order.setId(UuidUtils.getUUID());
		try {
			if (idArray != null && idArray.length > 0) {
				double amount = 0;
				for (int i = 0; i < idArray.length; i++) {
					String id = idArray[i];
					WholesaleGroupPurchaseOrderItem item = (WholesaleGroupPurchaseOrderItem) wholeGroupItemService
							.getObjById(id, "WholesaleGroupPurchaseOrderItem");
					item.setOrderPrice(priceArray[i]);
					item.setNums(numArray[i]);
					item.setRate(rateArray[i]);
					double num = Double.valueOf(numArray[i]);
					double rate = Double.valueOf(rateArray[i]);
					double price = Double.valueOf(priceArray[i]);
					double rateAmount = num * price * rate / (1 + rate);
					double withoutRateAmount = num * price / (1 + rate);
					item.setRateAmount(rateAmount + "");
					item.setWithoutAmount(withoutRateAmount + "");
					item.setOrderId(order.getId());
					wholeGroupItemService.updateObj(item);
					amount += num * price;
				}

				order.setBranch(branch);
				order.setOrderAmount(amount + "");
				order.setPayType(payType);
				CustomerInfo customerInfo = (CustomerInfo) customerInfoService
						.getObjById(customerId, "CustomerInfo");
				order.setCustomerInfo(customerInfo);
				order.setZcUserInfo(userInfo);
				order.setStatus(Constant.CHECK_STATUS_UNDO);
				wholeGroupService.saveObj(order);
				logManageService.insertLog(request, "勾选商品信息生成团购订单", "批发团购订单");
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
				return ajaxResult;
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
	}

	@RequestMapping("gotoDetailOrder")
	public ModelAndView gotoDetailPurchaseOrder(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, "WholesaleGroupPurchaseOrder");
		model.addAttribute("order", order);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_detail");
		return view;
	}

	@RequestMapping(value = "listOrderItems", produces = "application/json")
	@ResponseBody
	public DataGrid listOrderItems(
			@ModelAttribute WholesaleGroupPurchaseOrderItem orderItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wholeGroupItemService.getPagedDataGridObj(orderItem,
					page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "saveAndCommit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveAndCommit(
			@ModelAttribute WholesaleGroupPurchaseOrder wholesaleGroupPurchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(wholesaleGroupPurchaseOrder.getId(),
						"WholesaleGroupPurchaseOrder");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String prices = request.getParameter("price");
		String rates = request.getParameter("rate");
		String branchId = request.getParameter("branchId");
		Branch branch = (Branch) branchService.getObjById(branchId, "Branch");
		String customerId = request.getParameter("customerId");
		String[] idArray = ids.split(",");
		String[] numArray = nums.split(",");
		String[] priceArray = prices.split(",");
		String[] rateArray = rates.split(",");
		try {
			if (idArray != null && idArray.length > 0) {
				double amount = 0;
				for (int i = 0; i < idArray.length; i++) {
					String id = idArray[i];
					WholesaleGroupPurchaseOrderItem item = (WholesaleGroupPurchaseOrderItem) wholeGroupItemService
							.getObjById(id, "WholesaleGroupPurchaseOrderItem");
					item.setOrderPrice(priceArray[i]);
					item.setNums(numArray[i]);
					item.setRate(rateArray[i]);
					double num = Double.valueOf(numArray[i]);
					double rate = Double.valueOf(rateArray[i]);
					double price = Double.valueOf(priceArray[i]);
					double rateAmount = num * price * rate / (1 + rate);
					double withoutRateAmount = num * price / (1 + rate);
					item.setRateAmount(rateAmount + "");
					item.setWithoutAmount(withoutRateAmount + "");
					if (!StringUtil.validate(item.getOrderId())) {
						item.setOrderId(wholesaleGroupPurchaseOrder.getId());
					}
					wholeGroupItemService.updateObj(item);
					amount += num * price;
				}

				order.setBranch(branch);
				order.setOrderAmount(amount + "");
				CustomerInfo customerInfo = (CustomerInfo) customerInfoService
						.getObjById(customerId, "CustomerInfo");
				order.setCustomerInfo(customerInfo);
				order.setPayType(wholesaleGroupPurchaseOrder.getPayType());
				order.setSaleType(wholesaleGroupPurchaseOrder.getSaleType());
				order.setEffectiveDate(wholesaleGroupPurchaseOrder
						.getEffectiveDate());
				order.setRemark(wholesaleGroupPurchaseOrder.getRemark());
				order.setStatus(Constant.CHECK_STATUS_UNDO);
				wholeGroupService.updateObj(order);
				logManageService.insertLog(request, "编辑商品信息生成团购订单", "编辑批发团购订单");
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
				return ajaxResult;
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
	}

	/**
	 * 删除批发团购订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete", produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult result = null;
		String id = request.getParameter("id");
		try {
			List<WholesaleGroupPurchaseOrderItem> list = wholeGroupItemService
					.getListByObj(WholesaleGroupPurchaseOrderItem.class,
							" order_id='" + id + "'");
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					wholeGroupItemService.deleteObjById(list.get(i).getId(),
							"WholesaleGroupPurchaseOrderItem");
				}
			}
			wholeGroupService.deleteObjById(id, "WholesaleGroupPurchaseOrder");
			logManageService.insertLog(request, "删除了勾选的批发团购订单", "批发团购订单");
			result = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return result;
	}

	/**
	 * 提交批发团购订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "submitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult submitCheck(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, "WholesaleGroupPurchaseOrder");
		try {
			order.setStatus(Constant.CHECK_STATUS_WAITCHECK);
			wholeGroupService.updateObj(order);
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
	 * 编辑移除商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "removeGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeGoods(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String idOld = request.getParameter("idStr");
			String nums = request.getParameter("num");
			String rates = request.getParameter("rate");
			String goodsPrices = request.getParameter("goodsPrice");
			String[] goodsPrice = goodsPrices.split(",");
			String orderId = request.getParameter("orderId");
			String[] rate = rates.split(",");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			double totalMoney = 0;
			for (int i = 0; i < idStr.length; i++) {
				WholesaleGroupPurchaseOrderItem wholesaleGroupPurchaseOrderItem = (WholesaleGroupPurchaseOrderItem) wholeGroupService
						.getObjById(idStr[i], "WholesaleGroupPurchaseOrderItem");
				wholesaleGroupPurchaseOrderItem.setNums(num[i]);
				wholesaleGroupPurchaseOrderItem.setOrderPrice(goodsPrice[i]);
				wholesaleGroupPurchaseOrderItem.setRate(rate[i]);
				wholesaleGroupPurchaseOrderItem
						.setAmount((Double.valueOf(goodsPrice[i])
								* Double.valueOf(num[i]) / (1 + Double
								.valueOf(rate[i]))) + "");
				wholeGroupService.updateObj(wholesaleGroupPurchaseOrderItem);
				totalMoney += Double.valueOf(goodsPrice[i])
						* Double.valueOf(num[i]);
			}
			String[] ids = id.split(",");
			WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
					.getObjById(orderId, "WholesaleGroupPurchaseOrder");
			for (int i = 0; i < ids.length; i++) {
				totalMoney = totalMoney - Double.valueOf(goodsPrice[i])
						* Double.valueOf(num[i]);
				wholeGroupService.deleteObjById(ids[i],
						WholesaleGroupPurchaseOrderItem.class.getName());
			}
			order.setOrderAmount(totalMoney + "");
			order.setStatus(Constant.CHECK_STATUS_UNDO);
			wholeGroupService.updateObj(order);
			logManageService.insertLog(request, "移除了团购商品", "团购订单");
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
	 * 打开批发团购订单审核页面
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
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, "WholesaleGroupPurchaseOrder");
		model.addAttribute("order", order);
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/order/wholesaleGroupPurchaseOrder_check");
		return view;
	}

	/**
	 * 审核通过
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
					.getObjById(id, "WholesaleGroupPurchaseOrder");
			order.setStatus(Constant.CHECK_STATUS_PASS);
			order.setCheckMan(userInfo.getUserName());
			order.setCheckDate(df.parse(df.format(new Date())));
			wholeGroupService.updateObj(order);
			logManageService.insertLog(request, "审核通过系统自动修改审核状态为通过", "批发团购订单");
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
			WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
					.getObjById(id, "WholesaleGroupPurchaseOrder");
			order.setStatus(Constant.CHECK_STATUS_NOPASS);
			order.setCheckMan(userInfo.getUserName());
			if (order.getReason() != null && order.getReason().length() > 0) {
				order.setReason(order.getReason() + ";" + reason);
			} else {
				order.setReason(reason);
			}
			order.setCheckDate(df.parse(df.format(new Date())));
			wholeGroupService.updateObj(order);
			logManageService.insertLog(request, "审核不通过系统修改审核状态待处理", "批发团购订单");
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
	 * 生成批发团购销售单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createWholeSellReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createPurchaseReceive(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, WholesaleGroupPurchaseOrder.class.getName());
		List<WholesaleGroupPurchaseOrderItem> list = wholeGroupItemService
				.getListByObj(WholesaleGroupPurchaseOrderItem.class,
						" order_id='" + id + "'");
		long count = wholeSellService.getCountByObj(WholeSell.class,
				" WHOLESALEGROUPPURCHASEORDER_ID = '" + id + "'");
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

			WholeSell obj = new WholeSell();
			obj.setId(UuidUtils.getUUID());
			obj.setCustomerInfo(order.getCustomerInfo());
			obj.setBranch(order.getBranch());
			obj.setCreateMan(userInfo.getCtpUser());
			obj.setWholeSellOdd("TGXSD" + str);
			obj.setStatue(Constant.CHECK_STATUS_UNDO);
			obj.setWholesaleGroupPurchaseOrder(order);
			wholeSellService.saveObj(obj);
			try {
				double amount = 0;
				for (int i = 0; i < list.size(); i++) {
					WholesaleGroupPurchaseOrderItem orderItem = list.get(i);
					Long counts = wholeSellService.getCountByObj(
							ZcStorehouse.class, "branch_id='"
									+ order.getBranch().getId()
									+ "' and goodsFile_id='"
									+ orderItem.getGoodsFile().getId() + "'");
					if (counts == 0) {
						ZcStorehouse zcStorehouse = new ZcStorehouse();
						zcStorehouse.setId(UuidUtils.getUUID());
						zcStorehouse.setBranch(order.getBranch());
						zcStorehouse.setGoodsFile(orderItem.getGoodsFile());
						zcStorehouse.setStore("0");
						zcStorehouse.setStoreMoney("0");
						wholeSellService.saveObj(zcStorehouse);
					}
					WholeSellGoodsItems item = new WholeSellGoodsItems();
					item.setWholeSell(obj);
					item.setOrderPrice(orderItem.getOrderPrice());
					item.setNums(orderItem.getNums());
					item.setRate(orderItem.getRate());
					item.setRateAmount(orderItem.getRateAmount());
					item.setAmount(orderItem.getWithoutAmount());
					amount += Double.valueOf(item.getAmount());
					item.setGoodsFile(orderItem.getGoodsFile());
					wholeSellService.saveObj(item);
				}
				WholeSell sell = (WholeSell) wholeSellService.getObjById(
						obj.getId(), WholeSell.class.getName());
				sell.setTotalMoney(amount + "");
				wholeSellService.updateObj(sell);
				logManageService.insertLog(request, "生成了一张批发团购销售单", "团购销售单");
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
	 * 删除批发团购订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "abolish", produces = "application/json")
	@ResponseBody
	public AjaxResult abolish(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult result = null;
		String id = request.getParameter("id");
		WholesaleGroupPurchaseOrder order = (WholesaleGroupPurchaseOrder) wholeGroupService
				.getObjById(id, "WholesaleGroupPurchaseOrder");
		try {
			order.setStatus(Constant.CHECK_STATUS_THROW);
			wholeGroupService.updateObj(order);
			logManageService.insertLog(request, "已作废当前勾选的批发团购订单", "批发团购订单");
			result = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return result;
	}

	/**
	 * 移除
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
			String nums = request.getParameter("num");
			String rates = request.getParameter("rate");
			String goodsPrices = request.getParameter("goodsPrice");
			String[] goodsPrice = goodsPrices.split(",");
			String[] rate = rates.split(",");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			for (int i = 0; i < idStr.length; i++) {
				WholesaleGroupPurchaseOrderItem wholesaleGroupPurchaseOrderItem = (WholesaleGroupPurchaseOrderItem) wholeGroupService
						.getObjById(idStr[i], "WholesaleGroupPurchaseOrderItem");
				wholesaleGroupPurchaseOrderItem.setNums(num[i]);
				wholesaleGroupPurchaseOrderItem.setOrderPrice(goodsPrice[i]);
				wholesaleGroupPurchaseOrderItem.setRate(rate[i]);
				wholesaleGroupPurchaseOrderItem
						.setAmount((Double.valueOf(goodsPrice[i])
								* Double.valueOf(num[i]) / (1 + Double
								.valueOf(rate[i]))) + "");
				wholeGroupService.saveObj(wholesaleGroupPurchaseOrderItem);
			}
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				wholeGroupService.deleteObjById(ids[i],
						WholesaleGroupPurchaseOrderItem.class.getName());
			}
			logManageService.insertLog(request, "移除了团购商品", "团购订单");
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
