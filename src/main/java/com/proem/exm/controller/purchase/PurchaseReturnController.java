package com.proem.exm.controller.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.purchase.PurchaseReceiveItem;
import com.proem.exm.entity.purchase.PurchaseReturn;
import com.proem.exm.entity.purchase.PurchaseReturnItems;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.purchase.PurchaseOrderGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseReceiveGoodsItemsService;
import com.proem.exm.service.purchase.PurchaseReceiveService;
import com.proem.exm.service.purchase.PurchaseReturnItemsService;
import com.proem.exm.service.purchase.PurchaseReturnService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购退货单控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("purchase/purchaseReturn")
public class PurchaseReturnController extends BaseController {
	@Autowired
	PurchaseOrderGoodsItemsService purchaseOrderGoodsItemsService;
	@Autowired
	PurchaseReturnItemsService purchaseReturnItemsService;
	@Autowired
	PurchaseReturnService purchaseReturnService;
	@Autowired
	PurchaseReceiveService purchaseReceiveService;
	@Autowired
	PurchaseReceiveGoodsItemsService purchaseReceiveGoodsItemsService;

	/**
	 * 打开初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
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
			fasonName = "退货管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "采购退货";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseReturn/purchaseReturn_list");
		return view;
	}

	// 新增退货单
	@RequestMapping("gotoAddPurchaseReturn")
	public ModelAndView gotoAddPurchaseReturn(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(id, "PurchaseReceive");
		model.addAttribute("purchaseReceive", purchaseReceive);
		model.addAttribute("purchaseReceiveId", id);
		ModelAndView view = createIframeView("purchase/purchaseReceive/purchaseReturn_add");
		return view;
	}

	/**
	 * 打开明细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoDetailPurchaseReturn")
	public ModelAndView gotoDetailPurchaseReturn(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseReturn purchaseReturn = (PurchaseReturn) purchaseReturnService
				.getObjById(id, "PurchaseReturn");
		model.addAttribute("purchaseReturn", purchaseReturn);
		ModelAndView view = createIframeView("purchase/purchaseReturn/purchaseReturn_detail");
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
	@RequestMapping("gotoEditPurchaseReturn")
	public ModelAndView gotoEditPurchaseReturn(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		PurchaseReturn purchaseReturn = (PurchaseReturn) purchaseReturnService
				.getObjById(id, "PurchaseReturn");
		model.addAttribute("purchaseReturn", purchaseReturn);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseReturn/purchaseReturn_edit");
		return view;
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
		PurchaseReturn purchaseReturn = (PurchaseReturn) purchaseReturnService
				.getObjById(id, "PurchaseReturn");
		model.addAttribute("purchaseReturn", purchaseReturn);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("purchase/purchaseReturn/purchaseReturn_check");
		return view;
	}

	/**
	 * 查询明细
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPurchaseReturnItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseReturnItemsJson(
			@ModelAttribute PurchaseReturnItems purchaseReturnItems,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseReturnItemsService.getPagedDataGridObj(page,
					purchaseReturnItems);
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
	@RequestMapping(value = "listPurchaseReturnJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseReturnJson(
			@ModelAttribute PurchaseReturn purchaseReturn, String statue,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseReturnService.getPagedDataGridObj(page,
					purchaseReturn, statue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 保存实际退货单
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveAndCommit", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveAndCommit(
			@ModelAttribute PurchaseReturn purchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String id = request.getParameter("id");
		double money = 0;
		double actnum = 0;
		try {
			for (int i = 0; i < num.length; i++) {
				PurchaseReturnItems purchaseReturnItems = (PurchaseReturnItems) purchaseReturnItemsService
						.getObjById(idStr[i], "PurchaseReturnItems");
				double a = Double.valueOf(num[i]);
				double actReturn = a;
				purchaseReturnItems.setReturnNum(actReturn + "");
				purchaseReturnItemsService.updateObj(purchaseReturnItems);
				money += actReturn * purchaseReturnItems.getGoodsPrice();
				actnum += actReturn;
			}
			PurchaseReturn order = (PurchaseReturn) purchaseReturnService
					.getObjById(id, "PurchaseReturn");
			order.setStatue(Constant.CHECK_STATUS_UNDO);
			order.setTotalMoney(money);
			order.setReturnnum(actnum + "");
			purchaseReturnService.updateObj(order);
			logManageService.insertLog(request, "更新了退货单，状态修改为待审核", "采购退货");
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
	public AjaxResult submitCheck(
			@ModelAttribute PurchaseReturn purchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		PurchaseReturn returnOrder = (PurchaseReturn) purchaseReturnService
				.getObjById(id, "PurchaseReturn");
		try {
			returnOrder.setStatue(Constant.CHECK_STATUS_WAITCHECK);
			purchaseReturnService.updateObj(returnOrder);
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
	 * 根据收货单商品勾选直接生成采购退货单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createPurchaseByReceiveGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createPurchaseByReceiveGoods(
			@ModelAttribute PurchaseOrder purchaseOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		String orderid = UuidUtils.getUUID();
		String ids = request.getParameter("ids");
		String receiveNum = request.getParameter("receiveNum");
		String returnNum = request.getParameter("returnNum");
		String receiveId = request.getParameter("receiveId");
		PurchaseReceive purchaseReceive = (PurchaseReceive) purchaseReceiveService
				.getObjById(receiveId, "PurchaseReceive");
		String[] idStr = ids.split(",");
		String[] nums = returnNum.split(",");
		String[] receive = receiveNum.split(",");
		PurchaseReturn purchaseReturnOrder = new PurchaseReturn();
		purchaseReturnOrder.setId(orderid);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String str = formatDate.format(date);
		try {
			Thread.sleep(1);// 等待1毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String orderNumberString = "CGTHD" + str;
		purchaseReturnOrder.setOdd("CGTHD" + str);
		Long countNum = purchaseReturnService.getCountByObj(
				PurchaseReturn.class, " odd='" + orderNumberString + "'");
		purchaseReturnOrder.setStatue(Constant.CHECK_STATUS_UNDO);
		purchaseReturnOrder.setReturnMan(userInfo.getUserName());
		double totalreturnnum = 0;
		double totalMoney = 0;
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReceiveService
							.getObjById(idStr[i], "PurchaseReceiveItem");
					String receivesId = purchaseReceiveItem
							.getPurchaseReceiveId();
					PurchaseReceive receivesObj = (PurchaseReceive) purchaseReceiveService
							.getObjById(receivesId, "PurchaseReceive");
					purchaseReturnOrder.setProviderInfo(receivesObj
							.getProviderInfo());
					if (!"1".equals(purchaseReceiveItem.getCreateFlag())) {
						PurchaseReturnItems purchaseReturnItems = new PurchaseReturnItems();
						double returns = Double.valueOf(nums[i]);
						double returnnum = returns;
						double receives = Double.valueOf(receive[i]);
						double receivenum = receives;
						totalMoney += Double.valueOf(purchaseReceiveItem
								.getGoodsPrice()) * returnnum;
						totalreturnnum += returnnum;
						purchaseReturnItems.setId(UuidUtils.getUUID());
						purchaseReturnItems.setReturnId(orderid);
						purchaseReturnItems.setGoodsFile(purchaseReceiveItem
								.getGoodsFile());
						purchaseReturnItems.setSerialnumber(purchaseReceiveItem
								.getGoodsFile().getSerialNumber());
						purchaseReturnItems.setGoodsName(purchaseReceiveItem
								.getGoodsFile().getGoods_name());
						purchaseReturnItems.setUnit(purchaseReceiveItem
								.getGoodsFile().getGoods_unit());
						purchaseReturnItems
								.setSpecifications(purchaseReceiveItem
										.getGoodsFile()
										.getGoods_specifications());
						purchaseReturnItems.setGoodsPrice(Double
								.valueOf(purchaseReceiveItem.getGoodsPrice()));
						purchaseReturnItems.setReceiveNum(receivenum + "");
						purchaseReturnItems.setReturnNum(returnnum + "");
						purchaseReturnItems
								.setPurchaseReceiveItem(purchaseReceiveItem);
						purchaseReturnItemsService.saveObj(purchaseReturnItems);
						purchaseReturnOrder.setTotalMoney(totalMoney);
						purchaseReturnOrder.setBranch(purchaseReceive
								.getBranch());
						purchaseReturnOrder.setReturnnum(totalreturnnum + "");
						if (countNum == 0) {
							purchaseReturnService.saveObj(purchaseReturnOrder);
						} else {
							Date date1 = new Date();
							String str1 = formatDate.format(date1);
							purchaseReturnOrder.setOdd("CGTHD" + str1);
							purchaseReturnService.saveObj(purchaseReturnOrder);
						}
						purchaseReceiveItem.setCreateFlag("1");
						purchaseReceiveGoodsItemsService
								.updateObj(purchaseReceiveItem);
					} else {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					}
				}
			}
			logManageService.insertLog(request, "根据采购收货单中勾选的商品生成了一张采购退货单",
					"采购退货");
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
				purchaseReturnService.deleteObjById(ids[i],
						PurchaseReturn.class.getName());
				List<PurchaseReturnItems> purchaseReturnItemList = purchaseReturnItemsService
						.getListByObj(PurchaseReturnItems.class, "RETURNID='"
								+ ids[i] + "'");
				if (purchaseReturnItemList != null
						&& purchaseReturnItemList.size() > 0) {
					for (int j = 0; j < purchaseReturnItemList.size(); j++) {
						PurchaseReturnItems purchaseReturnItems = purchaseReturnItemList
								.get(j);
						String receiveGoodsId = purchaseReturnItems
								.getPurchaseReceiveItem().getId();
						PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReturnItemsService
								.getObjById(receiveGoodsId,
										"PurchaseReceiveItem");
						purchaseReceiveItem.setCreateFlag("");
						purchaseReturnItemsService
								.updateObj(purchaseReceiveItem);
						purchaseOrderGoodsItemsService.deleteObjById(
								purchaseReturnItems.getId(),
								PurchaseReturnItems.class.getName());
					}
				}
			}
			logManageService.insertLog(request, "删除勾选中的采购退货单", "采购退货");
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
	public AjaxResult checkPass(@ModelAttribute PurchaseReturn purchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		String ids = request.getParameter("ids");
		String moneys = request.getParameter("moneys");
		String[] money = moneys.split(",");
		String[] idStr = ids.split(",");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PurchaseReturn returnOrder = (PurchaseReturn) purchaseReturnService
				.getObjById(id, "PurchaseReturn");
		String branchId = returnOrder.getBranch().getId();
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					PurchaseReturnItems purchaseReturnItems = (PurchaseReturnItems) purchaseReturnItemsService
							.getObjById(idStr[i], "PurchaseReturnItems");
					Long count = purchaseReceiveService.getCountByObj(
							ZcStorehouse.class, "BRANCH_ID='"
									+ branchId
									+ "' and GOODSFILE_ID='"
									+ purchaseReturnItems.getGoodsFile()
											.getId() + "'");
					if (count == 0) {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.FAIL, AjaxResult.INFO);
						return ajaxResult;
					} else {
						List<ZcStorehouse> zcStorehouseList = purchaseReceiveService
								.getListByObj(ZcStorehouse.class, "BRANCH_ID='"
										+ branchId
										+ "' and GOODSFILE_ID='"
										+ purchaseReturnItems.getGoodsFile()
												.getId() + "'");
						if (zcStorehouseList != null
								&& zcStorehouseList.size() > 0) {
							for (int j = 0; j < zcStorehouseList.size(); j++) {
								ZcStorehouse zcStorehouse = zcStorehouseList
										.get(j);
								double num = Double.valueOf(zcStorehouse
										.getStore())
										- Double.valueOf(purchaseReturnItems
												.getReturnNum());
								double storeMoney = Double.valueOf(zcStorehouse
										.getStoreMoney())
										- Double.valueOf(money[i]);
								if (num < 0) {
									ajaxResult = new AjaxResult(
											AjaxResult.UPDATE, AjaxResult.FAIL,
											AjaxResult.INFO);
									return ajaxResult;
								} else if (storeMoney >= 0) {
									zcStorehouse.setStoreMoney(storeMoney + "");
									zcStorehouse.setStore(String.valueOf(num));
									purchaseReceiveService
											.updateObj(zcStorehouse);
								}
							}
						}
					}
				}
			}
			returnOrder.setStatue(Constant.CHECK_STATUS_PASS);
			returnOrder.setCheckMan(userInfo.getUserName());
			returnOrder.setCheckDate(df.parse(df.format(new Date())));
			purchaseReturnService.updateObj(returnOrder);
			logManageService.insertLog(request, "审核了采购退货单，审核通过，系统自动修改状态为完成",
					"采购退货");
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
			PurchaseReturn returnOrder = (PurchaseReturn) purchaseReturnService
					.getObjById(id, "PurchaseReturn");
			returnOrder.setStatue(Constant.CHECK_STATUS_NOPASS);
			returnOrder.setCheckMan(userInfo.getUserName());
			if (returnOrder.getRemark() != null
					&& returnOrder.getRemark().length() > 0) {
				returnOrder.setRemark(returnOrder.getRemark() + ";" + reason);
			} else {
				returnOrder.setRemark(reason);
			}
			returnOrder.setCheckDate(df.parse(df.format(new Date())));
			purchaseReturnService.updateObj(returnOrder);
			logManageService.insertLog(request, "审核不通过系统修改审核状态待处理", "采购退货");
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
	 * 删除采购退货单中的商品
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
			String goodsPrices = request.getParameter("goodsPrice");
			String[] goodsPrice = goodsPrices.split(",");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			String returnId = "";
			for (int i = 0; i < idStr.length; i++) {
				PurchaseReturnItems purchaseReturnItems = (PurchaseReturnItems) purchaseOrderGoodsItemsService
						.getObjById(idStr[i], "PurchaseReturnItems");
				returnId = purchaseReturnItems.getReturnId();
				purchaseReturnItems.setReturnNum(num[i]);
				purchaseReturnItems
						.setGoodsPrice(Double.valueOf(goodsPrice[i]));
				purchaseReturnItems.setTotalMoney((Double
						.valueOf(goodsPrice[i]) * Double.valueOf(num[i])) + "");
				// 保存采购收货单商品对象
				purchaseOrderGoodsItemsService.saveObj(purchaseReturnItems);
				money += purchaseReturnItems.getGoodsPrice()
						* Double.valueOf(num[i]);
				totalNum += Double.valueOf(num[i]);
			}
			PurchaseReturn purchaseReturn = (PurchaseReturn) purchaseOrderGoodsItemsService
					.getObjById(returnId, "PurchaseReturn");
			purchaseReturn.setTotalMoney(money);
			purchaseReturn.setReturnnum(totalNum + "");
			purchaseReturn.setStatue(Constant.CHECK_STATUS_UNDO);
			purchaseOrderGoodsItemsService.updateObj(purchaseReturn);
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				PurchaseReturnItems purchaseReturnItems = (PurchaseReturnItems) purchaseReturnItemsService
						.getObjById(ids[i], "PurchaseReturnItems");
				String returns = purchaseReturnItems.getReturnId();
				PurchaseReturn returnPurchase = (PurchaseReturn) purchaseReturnService
						.getObjById(returns, "PurchaseReturn");
				double goodsMoney = purchaseReturnItems.getGoodsPrice()
						* Double.valueOf(purchaseReturnItems.getReturnNum());
				returnPurchase.setTotalMoney(returnPurchase.getTotalMoney()
						- goodsMoney);
				returnPurchase.setReturnnum(String.valueOf(Double
						.valueOf(returnPurchase.getReturnnum())
						- Double.valueOf(purchaseReturnItems.getReturnNum())));
				purchaseReturnItemsService.deleteObjById(ids[i],
						PurchaseReturnItems.class.getName());
				String receiveGoodsId = purchaseReturnItems
						.getPurchaseReceiveItem().getId();
				PurchaseReceiveItem purchaseReceiveItem = (PurchaseReceiveItem) purchaseReturnItemsService
						.getObjById(receiveGoodsId, "PurchaseReceiveItem");
				purchaseReceiveItem.setCreateFlag("");
				purchaseReturnItemsService.updateObj(purchaseReceiveItem);
				purchaseReturnService.updateObj(purchaseReturn);
			}
			logManageService.insertLog(request, "删除了采购退货单中的一条商品", "采购退货");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping("gotoPrintPurchaseReturn")
	public ModelAndView gotoPrintPurchaseReturn(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID的获取订单详情
		List<PurchaseReturnItems> purchaseReturnItemList = purchaseReturnItemsService
				.getListByObj(PurchaseReturnItems.class, "RETURNID = '" + id
						+ "'");
		List<PurchaseReturnItems> goodsList = new ArrayList<PurchaseReturnItems>();
		String[] guige = null;
		if (purchaseReturnItemList != null && purchaseReturnItemList.size() > 0) {
			for (int i = 0; i < purchaseReturnItemList.size(); i++) {
				PurchaseReturnItems purchaseReturnItems = purchaseReturnItemList
						.get(i);
				if (purchaseReturnItems.getGoodsFile()
						.getGoods_specifications() != null
						&& purchaseReturnItems.getGoodsFile()
								.getGoods_specifications() != "") {
					guige = purchaseReturnItems.getGoodsFile()
							.getGoods_specifications().split("商品规格：");
					if (guige != null && guige.length > 1) {
						purchaseReturnItems.setSpecifications(guige[0]);
					} else if (guige != null && guige.length == 1) {
						purchaseReturnItems.setSpecifications(guige[0]);
					}
				} else {
					purchaseReturnItems.setSpecifications("");
				}
				double totalMoney = purchaseReturnItems.getGoodsPrice()
						* Double.valueOf(purchaseReturnItems.getReturnNum());
				purchaseReturnItems.setTotalMoney(totalMoney + "");
				purchaseReturnItemsService.updateObj(purchaseReturnItems);
				goodsList.add(purchaseReturnItems);
			}
		}
		// 根据ID获取采购退货单对象
		PurchaseReturn purchaseReturn = (PurchaseReturn) purchaseReturnItemsService
				.getObjById(id, "PurchaseReturn");
		model.addAttribute("purchaseReturn", purchaseReturn);
		model.addAttribute("purchaseReturnItemList", goodsList);
		ModelAndView view = createIframeView("purchase/purchaseReturn/purchaseReturn_print");
		return view;
	}

}
