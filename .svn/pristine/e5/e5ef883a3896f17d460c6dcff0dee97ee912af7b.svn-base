package com.proem.exm.controller.purchase;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseShoppingCart;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.order.OrdersService;
import com.proem.exm.service.purchase.PurchaseOrderGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.service.purchase.PurchaseShoppingCartService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购订单购物车控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("purchase/purchaseShopping")
public class PurchaseShoppingCartController extends BaseController {
	@Autowired
	PurchaseShoppingCartService purchaseShoppingCartService;
	@Autowired
	GoodsFileService goodsFileService;
	@Autowired
	ProviderInfoService providerInfoService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	PurchaseOrderGoodsItemsService purchaseOrderGoodsItemsService;

	/**
	 * 点击添加进购物车
	 * 
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addToShoppingCart", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addToShoppingCart(@ModelAttribute GoodsFile goods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String goodsId = request.getParameter("id");
			String orderNum = request.getParameter("orderNum");
			String actualNum = request.getParameter("actualNums");
			String serialnumber = request.getParameter("serialnumber");
			double ordernum = Double.valueOf(orderNum);
			double actualnum = Double.valueOf(actualNum);
			int orderNumber = (int) ordernum;
			int actualNumber = (int) actualnum;
			List<PurchaseShoppingCart> purchaseShoppingCartList = purchaseShoppingCartService
					.getListByObj(PurchaseShoppingCart.class, "SERIALNUMBER='"
							+ serialnumber + "'");
			if (purchaseShoppingCartList.size() == 0) {
				if (goodsId != null) {
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(goodsId, "GoodsFile");
					if (goodsFile != null) {
						PurchaseShoppingCart purchaseShoppingCart = new PurchaseShoppingCart();
						BigDecimal price = new BigDecimal(
								String.valueOf(goodsFile.getGoods_price()));
						double goodsPrice = price.doubleValue();
						double total = orderNumber * goodsPrice;
						BigDecimal a = new BigDecimal(total);
						double totalMoney = a.setScale(2,
								BigDecimal.ROUND_HALF_UP).doubleValue();
						String cartGoodsId = UuidUtils.getUUID();
						purchaseShoppingCart.setId(cartGoodsId);
						purchaseShoppingCart.setGoodsName(goodsFile
								.getGoods_name());
						purchaseShoppingCart.setSerialNumber(goodsFile
								.getSerialNumber());
						purchaseShoppingCart.setUnit(goodsFile.getGoods_unit());
						purchaseShoppingCart.setSpecifications(goodsFile
								.getGoods_specifications());
						purchaseShoppingCart.setClassify(goodsFile
								.getGoods_class().getClassify_name());
						purchaseShoppingCart.setGoodsPrice(goodsPrice);
						purchaseShoppingCart.setOrderNum(orderNumber);
						purchaseShoppingCart.setActualNum(actualNumber);
						purchaseShoppingCart.setTotalMoney(totalMoney);
						purchaseShoppingCart.setProviderInfo(goodsFile
								.getProvider());
						purchaseShoppingCart.setGoodsFile(goodsFile);
						purchaseShoppingCartService
								.saveObj(purchaseShoppingCart);
						goodsFile.setDelflag("5");
						goodsFileService.updateObj(goodsFile);
					}
				}
			} else {
				for (int i = 0; i < purchaseShoppingCartList.size(); i++) {
					PurchaseShoppingCart purchaseShoppingCart = purchaseShoppingCartList
							.get(i);
					double totalMoney = purchaseShoppingCart.getTotalMoney()
							+ (purchaseShoppingCart.getOrderNum() + orderNumber)
							* purchaseShoppingCart.getGoodsPrice();
					purchaseShoppingCart.setOrderNum(purchaseShoppingCart
							.getOrderNum() + orderNumber);
					purchaseShoppingCart.setActualNum(purchaseShoppingCart
							.getActualNum() + actualNumber);
					purchaseShoppingCart.setTotalMoney(totalMoney);
					purchaseShoppingCartService.updateObj(purchaseShoppingCart);
				}
			}
			logManageService.insertLog(request, "添加了一条商品信息到采购订单购物车", "订单汇总");
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
	 * 拉取当前页商品进采购订单购物车
	 * 
	 * @param goods
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "pullPageGoodsToShoppingCart", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult pullPageGoodsToShoppingCart(
			@ModelAttribute GoodsFile goods, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String ids = request.getParameter("ids");
			String orderNums = request.getParameter("orderNum");
			String serialnumbers = request.getParameter("serialnumber");
			String[] idStr = ids.split(",");
			String[] orderNumStr = orderNums.split(",");
			String[] serialnumberStr = serialnumbers.split(",");
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					double ordernum = Double.valueOf(orderNumStr[i]);
					int orderNumber = (int) ordernum;
					List<PurchaseShoppingCart> purchaseShoppingCartList = purchaseShoppingCartService
							.getListByObj(PurchaseShoppingCart.class,
									"SERIALNUMBER='" + serialnumberStr[i] + "'");
					if (purchaseShoppingCartList.size() == 0) {
						if (idStr[i] != null) {
							GoodsFile goodsFile = (GoodsFile) goodsFileService
									.getObjById(idStr[i], "GoodsFile");
							if (goodsFile != null) {
								PurchaseShoppingCart purchaseShoppingCart = new PurchaseShoppingCart();
								BigDecimal price = new BigDecimal(
										String.valueOf(goodsFile
												.getGoods_price()));
								double goodsPrice = price.doubleValue();
								double total = orderNumber * goodsPrice;
								BigDecimal a = new BigDecimal(total);
								double totalMoney = a.setScale(2,
										BigDecimal.ROUND_HALF_UP).doubleValue();
								String cartGoodsId = UuidUtils.getUUID();
								purchaseShoppingCart.setId(cartGoodsId);
								purchaseShoppingCart.setGoodsName(goodsFile
										.getGoods_name());
								purchaseShoppingCart.setSerialNumber(goodsFile
										.getSerialNumber());
								purchaseShoppingCart.setUnit(goodsFile
										.getGoods_unit());
								purchaseShoppingCart
										.setSpecifications(goodsFile
												.getGoods_specifications());
								purchaseShoppingCart.setClassify(goodsFile
										.getGoods_class().getClassify_name());
								purchaseShoppingCart.setGoodsPrice(goodsPrice);
								purchaseShoppingCart.setOrderNum(orderNumber);
								purchaseShoppingCart.setActualNum(orderNumber);
								purchaseShoppingCart.setTotalMoney(totalMoney);
								purchaseShoppingCart.setProviderInfo(goodsFile
										.getProvider());
								purchaseShoppingCart.setGoodsFile(goodsFile);
								purchaseShoppingCartService
										.saveObj(purchaseShoppingCart);
								goodsFile.setDelflag("5");
								goodsFileService.updateObj(goodsFile);
							}
						}
					} else {
						for (int j = 0; j < purchaseShoppingCartList.size(); j++) {
							PurchaseShoppingCart purchaseShoppingCart = purchaseShoppingCartList
									.get(j);
							double totalMoney = purchaseShoppingCart
									.getTotalMoney()
									+ (purchaseShoppingCart.getOrderNum() + orderNumber)
									* purchaseShoppingCart.getGoodsPrice();
							purchaseShoppingCart
									.setOrderNum(purchaseShoppingCart
											.getOrderNum() + orderNumber);
							purchaseShoppingCart
									.setActualNum(purchaseShoppingCart
											.getActualNum() + orderNumber);
							purchaseShoppingCart.setTotalMoney(totalMoney);
							purchaseShoppingCartService
									.updateObj(purchaseShoppingCart);
						}
					}
				}
			}
			logManageService.insertLog(request, "添加了一条商品信息到采购订单购物车", "订单汇总");
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
	 * 打开我的采购订单购物车
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("openShoppingCart")
	public ModelAndView openShoppingCart(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("purchase/purchaseOrder/purchase_shoppingcart");
		return view;
	}

	/**
	 * 分页查询购物车中所有商品
	 * 
	 * @param purchaseShoppingCart
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listShoppingCartJson", produces = "application/json")
	@ResponseBody
	public DataGrid listShoppingCartJson(
			@ModelAttribute PurchaseShoppingCart purchaseShoppingCart,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseShoppingCartService.getPagedDataGridObj(page,
					purchaseShoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
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
	@RequestMapping(value = "createPurchase", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createPurchase(
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
		int totalNum = 0;
		List<PurchaseShoppingCart> purchaseShoppingCartList = purchaseShoppingCartService
				.getListByObj(PurchaseShoppingCart.class, "");
		try {
			if (purchaseShoppingCartList != null
					&& purchaseShoppingCartList.size() > 0) {
				for (int i = 0; i < purchaseShoppingCartList.size(); i++) {
					PurchaseShoppingCart cart = purchaseShoppingCartList.get(i);
					String cartProvider = cart.getProviderInfo().getId();
					for (int j = 0; j < purchaseOrderList.size(); j++) {
						PurchaseOrder purchase = purchaseOrderList.get(j);
						String orderProvider = purchase.getProviderInfo()
								.getId();
						if (cartProvider.equals(orderProvider)) {
							// 创建新的采购订单商品对象
							PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
							String purchaseGoodsId = UuidUtils.getUUID();
							purchaseOrderGoodsItems.setId(purchaseGoodsId);
							purchaseOrderGoodsItems.setPurchaseId(purchase
									.getId());
							purchaseOrderGoodsItems.setSerialNumber(cart
									.getSerialNumber());
							purchaseOrderGoodsItems.setGoodsName(cart
									.getGoodsName());
							purchaseOrderGoodsItems.setUnit(cart.getUnit());
							purchaseOrderGoodsItems.setGoodsPrice(cart
									.getGoodsPrice());
							purchaseOrderGoodsItems.setSpecifications(cart
									.getSpecifications());
							purchaseOrderGoodsItems.setOrderNum(cart
									.getOrderNum());
							purchaseOrderGoodsItems.setGoodsMoney(cart
									.getTotalMoney());
							purchaseOrderGoodsItemsService
									.saveObj(purchaseOrderGoodsItems);
							totalNum += cart.getTotalMoney();
						}

					}
				}
			}
			double price = 0;
			// 获取当前登录用户
			ZcUserInfo userInfo = (ZcUserInfo) request.getSession()
					.getAttribute("userInfo");
			// 采购订单插入
			if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
				for (int i = 0; i < purchaseOrderList.size(); i++) {
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
							price += Double.valueOf(purchaseOrderGoodsItems
									.getGoodsMoney());

						}
						String orderNum = "CGDD" + str;
						order.setMoney(price);
						order.setState(Constant.CHECK_STATUS_UNDO);
						order.setTotalNum(totalNum + "");
						order.setPurchaseMan(userInfo.getUserName());
						order.setOdd("CGDD" + str);
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
			logManageService.insertLog(request, "根据购物车一键生成了采购订单", "采购订单");
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
	 * 删除采购订单购物车商品并修改页面标记颜色
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
				PurchaseShoppingCart purchaseShoppingCart = (PurchaseShoppingCart) purchaseShoppingCartService
						.getObjById(ids[i], "PurchaseShoppingCart");
				String goodsId = purchaseShoppingCart.getGoodsFile().getId();
				GoodsFile goodsFile = (GoodsFile) goodsFileService.getObjById(
						goodsId, "GoodsFile");
				goodsFile.setDelflag("");
				goodsFileService.updateObj(goodsFile);
				purchaseShoppingCartService.deleteObjById(ids[i],
						PurchaseShoppingCart.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的购物车商品", "订单汇总");
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
	 * 单个删除采购订单购物车商品并修改页面标记颜色
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteShoppingCart", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteShoppingCart(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = request.getParameter("id");
			PurchaseShoppingCart purchaseShoppingCart = (PurchaseShoppingCart) purchaseShoppingCartService
					.getObjById(id, "PurchaseShoppingCart");
			String goodsId = purchaseShoppingCart.getGoodsFile().getId();
			GoodsFile goodsFile = (GoodsFile) goodsFileService.getObjById(
					goodsId, "GoodsFile");
			goodsFile.setDelflag("");
			goodsFileService.updateObj(goodsFile);
			purchaseShoppingCartService.deleteObjById(id,
					PurchaseShoppingCart.class.getName());
			logManageService.insertLog(request, "删除了一条购物车商品", "订单汇总");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping("gotoPrintWaitPurchaseGoods")
	public ModelAndView gotoPrintWaitPurchaseGoods(
			@ModelAttribute GoodsFile goodsFile, HttpServletRequest request,
			HttpServletResponse response, Model model, Page page) {
		DataGrid dataGrid = null;
		page.setPage(1);
		page.setRows(10000);
		DecimalFormat df = new DecimalFormat(".##");
		try {
			dataGrid = purchaseOrderService.getHandleDataGrid(page, goodsFile);
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String goodId = (String) list.get(i).get("GOODSFILE_ID");
					BigDecimal totalNum = (BigDecimal) list.get(i).get("NUMS");
					double needNums = 0;
					String guige = (String) list.get(i).get(
							"GOODS_SPECIFICATIONS");
					String str2 = "";
					String[] shangpinguige = null;
					if (guige != null && guige != "") {
						shangpinguige = guige.split("商品规格：");
						if (shangpinguige != null && shangpinguige.length > 1) {
							list.get(i).put("shangpinguige", shangpinguige[1]);
						} else if (shangpinguige != null
								&& shangpinguige.length == 1) {
							list.get(i).put("shangpinguige", shangpinguige[0]);
						}
					}
					if (guige != null && guige != "") {
						for (int z = 0; z < guige.length(); z++) {
							if ((guige.charAt(z) >= '0' && guige.charAt(z) <= '9')
									|| guige.charAt(z) == '.') {
								str2 += guige.charAt(z);
							}
						}
					}
					double spe = Double.valueOf(str2);
					double store = 0;
					List<ZcStorehouse> storeList = purchaseOrderService
							.getListByObj(ZcStorehouse.class, "goodsfile_id='"
									+ goodId + "'");
					// 库存数量
					if (storeList != null && storeList.size() > 0) {
						for (int a = 0; a < storeList.size(); a++) {
							String num = storeList.get(a).getStore();
							if (StringUtils.isBlank(num)) {
								num = "0";
							}
							double nums = Double.valueOf(num);
							store = store + nums;
						}
					}
					needNums = totalNum.doubleValue() - store;
					if (needNums <= 0) {
						list.get(i).put("lossStore", "0");
					} else {
						list.get(i).put(
								"lossStore",
								"<span style='color:red;font-weight:700 '>"
										+ needNums + "</span>");
					}
					// 判断商品为成品还是原材料
					List<GoodsFile> goodsFileList = purchaseOrderService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ goodId + "'");
					if (goodsFileList == null || goodsFileList.size() == 0) {
						list.get(i).put("WASTERATE", "0");
						list.get(i).put("needNums", "0");
						list.get(i).put("storeNums", "0");
						list.get(i).put("storeneedNums", "0");
					} else {
						GoodsFile good = goodsFileList.get(0);
						String wasterate = good.getWasteRate();
						double wast = Double.valueOf(wasterate);
						list.get(i).put("WASTERATE", good.getWasteRate());
						double storeNums = 0;
						String goodsId = good.getId();
						List<ZcStorehouse> storeCailiaoList = purchaseOrderService
								.getListByObj(ZcStorehouse.class,
										"goodsfile_id='" + goodsId + "'");
						if (storeCailiaoList != null
								&& storeCailiaoList.size() > 0) {
							for (int a = 0; a < storeCailiaoList.size(); a++) {
								String num = storeCailiaoList.get(a).getStore();
								if (StringUtils.isBlank(num)) {
									num = "0";
								}
								double nums = Double.valueOf(num);
								storeNums = storeNums + nums;
							}
							if (needNums <= 0) {
								list.get(i).put("needNums", "0");
								list.get(i).put("storeNums", "0");
								list.get(i).put("storeneedNums", "0");
							} else {
								double needNum = needNums * spe
										* (1 + wast / 100);
								list.get(i).put("needNums", df.format(needNum));
								list.get(i).put("storeNums",
										df.format(storeNums));
								double storeneedNums = needNum - storeNums;
								if (storeneedNums <= 0) {
									list.get(i).put("storeneedNums", "0");
								} else {
									list.get(i).put(
											"storeneedNums",
											"<span style='color:red;font-weight:700 '>"
													+ df.format(storeneedNums)
													+ "</span>");
								}
							}
						} else {
							if (needNums <= 0) {
								list.get(i).put("needNums", "0");
								list.get(i).put("storeNums", "0");
								list.get(i).put("storeneedNums", "0");
							} else {
								double needNum = needNums * spe
										* (1 + wast / 100);
								list.get(i).put("needNums", df.format(needNum));
								list.get(i).put("storeNums", "0");
								double storeneedNums = needNum - storeNums;
								if (storeneedNums <= 0) {
									list.get(i).put("storeneedNums", "0");
								} else {
									list.get(i).put(
											"storeneedNums",
											"<span style='color:red;font-weight:700 '>"
													+ df.format(storeneedNums)
													+ "</span>");
								}
							}
						}
					}
					list.get(i).put("store", store);
				}
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = createIframeView("purchase/purchaseGoods/orders_handle_print");
		return view;
	}
}
