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
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturn;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnItem;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSell;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSellGoodsItems;
import com.proem.exm.service.wholesaleGroupPurchase.customer.WholeSellService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购销售单控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("wholesaleGroupPurchase/wholeSell")
public class WholeSellController extends BaseController {
	@Autowired
	WholeSellService wholeSellService;

	@InitBinder("wholeSell")
	public void initWholeSell(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("wholeSell.");
	}

	@InitBinder("wholeSellGoodsItems")
	public void initWholeSellGoodsItems(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("wholeSellGoodsItems.");
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
			fasonName = "团购销售单管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "团购销售单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesell/wholesell_list");
		return view;
	}

	/**
	 * 团购销售单List页面查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listWholeSellJson", produces = "application/json")
	@ResponseBody
	public DataGrid listWholeSellJson(@ModelAttribute WholeSell wholeSell,
			String startDate, String endDate, String state,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wholeSellService.getWholeSellListObj(page, wholeSell,
					startDate, endDate, state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openEdit")
	public ModelAndView openEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		model.addAttribute("wholeSell", wholeSell);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesell/wholesell_edit");
		return view;
	}

	/**
	 * 团购销售单Items页面查询
	 * 
	 * @param purchaseReceiveIeItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listWholeSellItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listWholeSellItemsJson(@ModelAttribute WholeSell wholeSell,
			String serialNumber, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wholeSellService.getWholeSellItems(page, wholeSell,
					serialNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 查看明细打开页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openWholeSellDetail")
	public ModelAndView openWholeSellDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		model.addAttribute("wholeSell", wholeSell);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesell/wholesell_detail");
		return view;
	}

	/**
	 * 保存修改
	 * 
	 * @param wholeSell
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateWholeSell", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateWholeSell(@ModelAttribute WholeSell wholeSell,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WholeSell sells = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		Branch branch = (Branch) wholeSellService.getObjById(wholeSell
				.getBranch().getId(), "Branch");
		CustomerInfo customerInfo = (CustomerInfo) wholeSellService.getObjById(
				wholeSell.getCustomerInfo().getId(), "CustomerInfo");
		sells.setBranch(branch);
		sells.setCustomerInfo(customerInfo);
		sells.setRemark(wholeSell.getRemark());
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String rates = request.getParameter("rates");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] rate = rates.split(",");
		double money = 0;
		try {
			for (int i = 0; i < num.length; i++) {
				WholeSellGoodsItems wholeSellGoodsItems = (WholeSellGoodsItems) wholeSellService
						.getObjById(idStr[i], "WholeSellGoodsItems");
				wholeSellGoodsItems.setNums(num[i]);
				wholeSellGoodsItems.setRate(rate[i]);
				wholeSellGoodsItems.setAmount(Double.valueOf(num[i])
						* Double.valueOf(wholeSellGoodsItems.getOrderPrice())
						/ (1 + Double.valueOf(rate[i])) + "");
				wholeSellGoodsItems.setRateAmount(Double
						.valueOf(wholeSellGoodsItems.getAmount())
						* Double.valueOf(rate[i]) + "");
				wholeSellService.saveObj(wholeSellGoodsItems);
				money += Double.valueOf(wholeSellGoodsItems.getAmount());
			}
			sells.setTotalMoney(money + "");
			sells.setStatue(Constant.CHECK_STATUS_UNDO);
			wholeSellService.updateObj(sells);
			logManageService.insertLog(request, "保存了实际团购销售数量", "团购销售单");
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
	 * @param wholeSell
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "submitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult submitCheck(@ModelAttribute WholeSell wholeSell,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WholeSell sells = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		try {
			sells.setStatue(Constant.CHECK_STATUS_WAITCHECK);
			wholeSellService.updateObj(sells);
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
	 * 移除团购销售单中的商品
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
		try {
			String idOld = request.getParameter("idStr");
			// 获取页面填写的采购订单商品需要更新的数量
			String nums = request.getParameter("num");
			String rates = request.getParameter("rates");
			String[] idStr = idOld.split(",");
			String[] num = nums.split(",");
			String[] rate = rates.split(",");
			String wholeSellId = "";
			for (int i = 0; i < idStr.length; i++) {
				WholeSellGoodsItems wholeSellGoodsItems = (WholeSellGoodsItems) wholeSellService
						.getObjById(idStr[i], "WholeSellGoodsItems");
				wholeSellId = wholeSellGoodsItems.getWholeSell().getId();
				wholeSellGoodsItems.setNums(num[i]);
				wholeSellGoodsItems.setRate(rate[i]);
				wholeSellGoodsItems.setAmount(Double.valueOf(num[i])
						* Double.valueOf(wholeSellGoodsItems.getOrderPrice())
						/ (1 + Double.valueOf(rate[i])) + "");
				wholeSellGoodsItems.setRateAmount(Double
						.valueOf(wholeSellGoodsItems.getAmount())
						* Double.valueOf(rate[i]) + "");
				wholeSellService.saveObj(wholeSellGoodsItems);
				money += Double.valueOf(wholeSellGoodsItems.getAmount());
			}
			WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(
					wholeSellId, "WholeSell");
			wholeSell.setTotalMoney(money + "");
			wholeSell.setStatue(Constant.CHECK_STATUS_UNDO);
			wholeSellService.updateObj(wholeSell);
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				WholeSellGoodsItems wholeSellGoodsItems = (WholeSellGoodsItems) wholeSellService
						.getObjById(ids[i], "WholeSellGoodsItems");
				String sellId = wholeSellGoodsItems.getWholeSell().getId();
				WholeSell sells = (WholeSell) wholeSellService.getObjById(
						sellId, "WholeSell");
				sells.setTotalMoney((Double.valueOf(sells.getTotalMoney()) - Double
						.valueOf(wholeSellGoodsItems.getAmount())) + "");
				wholeSellService.deleteObjById(ids[i],
						WholeSellGoodsItems.class.getName());
				wholeSellService.updateObj(sells);
			}
			logManageService.insertLog(request, "移除了勾选的团购销售单中的商品", "团购销售单");
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
	 * 删除团购销售单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteWholeSell", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteWholeSell(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<WholeSellGoodsItems> wholeSellGoodsItemsList = wholeSellService
						.getListByObj(WholeSellGoodsItems.class,
								"WHOLESELL_ID='" + ids[i] + "'");
				if (wholeSellGoodsItemsList != null
						&& wholeSellGoodsItemsList.size() > 0) {
					for (int j = 0; j < wholeSellGoodsItemsList.size(); j++) {
						WholeSellGoodsItems wholeSellGoodsItems = wholeSellGoodsItemsList
								.get(j);
						wholeSellService.deleteObjById(
								wholeSellGoodsItems.getId(),
								WholeSellGoodsItems.class.getName());
					}
				}
				wholeSellService.deleteObjById(ids[i],
						WholeSell.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的团购销售单", "团购销售单");
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
	 * 打开审核团购销售单页面
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
		WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		model.addAttribute("wholeSell", wholeSell);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesell/wholesell_check");
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
		String goodsAmounts = request.getParameter("goodsAmount");
		String[] goodsAmount = goodsAmounts.split(",");
		String[] idStr = ids.split(",");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
					"WholeSell");
			String branchId = wholeSell.getBranch().getId();
			String goodsFileId = "";
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					WholeSellGoodsItems wholeSellGoodsItems = (WholeSellGoodsItems) wholeSellService
							.getObjById(idStr[i], "WholeSellGoodsItems");
					if (!StringUtils.isBlank(wholeSellGoodsItems.getGoodsFile()
							.getId())) {
						goodsFileId = wholeSellGoodsItems.getGoodsFile()
								.getId();
					}
					List<ZcStorehouse> zcStorehouseList = wholeSellService
							.getListByObj(ZcStorehouse.class, "BRANCH_ID='"
									+ branchId + "' and GOODSFILE_ID='"
									+ goodsFileId + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						for (int j = 0; j < zcStorehouseList.size(); j++) {
							ZcStorehouse zcStorehouse = zcStorehouseList.get(j);
							double num = Double
									.valueOf(zcStorehouse.getStore())
									- Double.valueOf(wholeSellGoodsItems
											.getNums());
							double moneys = Double
									.valueOf(zcStorehouse.getStoreMoney() == null
											|| "".equals(zcStorehouse
													.getStoreMoney()) ? "0"
											: zcStorehouse.getStoreMoney())
									- Double.valueOf(goodsAmount[i]);
							zcStorehouse.setStore(num + "");
							zcStorehouse.setStoreMoney(moneys + "");
							wholeSellService.updateObj(zcStorehouse);
						}
					}
				}
			}
			wholeSell.setStatue(Constant.CHECK_STATUS_PASS);
			wholeSell.setCheckMan(userInfo.getCtpUser());
			wholeSell.setCheckDate(df.parse(df.format(new Date())));
			wholeSellService.updateObj(wholeSell);
			logManageService.insertLog(request, "审核了团购销售单", "团购销售单");
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
			WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
					"WholeSell");
			wholeSell.setStatue(Constant.CHECK_STATUS_NOPASS);
			wholeSell.setCheckMan(userInfo.getCtpUser());
			if (wholeSell.getReason() != null
					&& wholeSell.getReason().length() > 0) {
				wholeSell.setReason(wholeSell.getReason() + ";" + reason);
			} else {
				wholeSell.setReason(reason);
			}
			wholeSell.setCheckDate(df.parse(df.format(new Date())));
			wholeSellService.updateObj(wholeSell);
			logManageService.insertLog(request, "审核了团购销售单", "团购销售单");
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
	 * 打开退货页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAddReturn")
	public ModelAndView openAddReturn(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(id,
				"WholeSell");
		model.addAttribute("wholeSell", wholeSell);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesell/wholesell_return");
		return view;
	}

	/**
	 * 新增团购退货单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveLineToReturn", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveLineToReturn(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String returnNums = request.getParameter("returnNum");
		String goodsPrices = request.getParameter("goodsPrices");
		String rates = request.getParameter("rates");
		String wholeSellId = request.getParameter("wholeSellId");
		WholeSell wholeSell = (WholeSell) wholeSellService.getObjById(
				wholeSellId, "WholeSell");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] returnNum = returnNums.split(",");
		String[] goodsPrice = goodsPrices.split(",");
		String[] rate = rates.split(",");
		WGPurchaseReturn wgPurchaseReturn = new WGPurchaseReturn();
		wgPurchaseReturn.setId(UuidUtils.getUUID());
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String str = formatDate.format(date);
		try {
			Thread.sleep(1);// 等待1毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String orderNumberString = "TGTHD" + str;
		wgPurchaseReturn.setWholeReturnId(orderNumberString);
		Long countNum = wholeSellService.getCountByObj(WGPurchaseReturn.class,
				" wholeReturnId='" + orderNumberString + "'");
		wgPurchaseReturn.setStatue(Constant.CHECK_STATUS_UNDO);
		wgPurchaseReturn.setCreateMan(userInfo.getCtpUser());
		wgPurchaseReturn.setCustomerInfo(wholeSell.getCustomerInfo());
		wgPurchaseReturn.setBranch(wholeSell.getBranch());
		wholeSellService.saveObj(wgPurchaseReturn);
		double totalMoney = 0;
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					WholeSellGoodsItems wholeSellGoodsItems = (WholeSellGoodsItems) wholeSellService
							.getObjById(idStr[i], "WholeSellGoodsItems");
					if (!"1".equals(wholeSellGoodsItems.getCreateFlag())) {
						WGPurchaseReturnItem wgPurchaseReturnItem = new WGPurchaseReturnItem();
						wgPurchaseReturnItem.setId(UuidUtils.getUUID());
						wgPurchaseReturnItem.setGoodsFile(wholeSellGoodsItems
								.getGoodsFile());
						wgPurchaseReturnItem
								.setwGPurchaseReturn(wgPurchaseReturn);
						wgPurchaseReturnItem
								.setWholeSellGoodsItems(wholeSellGoodsItems);
						wgPurchaseReturnItem.setRate(rate[i]);
						wgPurchaseReturnItem.setReturnAmount((Double
								.valueOf(returnNum[i])
								* Double.valueOf(goodsPrice[i]) / (1 + Double
								.valueOf(rate[i])))
								+ "");
						wgPurchaseReturnItem
								.setRateAmount(Double
										.valueOf(wgPurchaseReturnItem
												.getReturnAmount())
										* Double.valueOf(rate[i]) + "");
						wgPurchaseReturnItem.setOrderPrice(goodsPrice[i]);
						wgPurchaseReturnItem.setNums(num[i]);
						wgPurchaseReturnItem.setReturnNums(returnNum[i]);
						totalMoney += Double.valueOf(wgPurchaseReturnItem
								.getReturnAmount());
						wholeSellService.saveObj(wgPurchaseReturnItem);
						wholeSellGoodsItems.setCreateFlag("1");
						wholeSellService.updateObj(wholeSellGoodsItems);
					} else {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					}
				}
				wgPurchaseReturn.setReturnMoney(totalMoney + "");
				if (countNum == 0) {
					wholeSellService.updateObj(wgPurchaseReturn);
				} else {
					Date date1 = new Date();
					String str1 = formatDate.format(date1);
					wgPurchaseReturn.setWholeReturnId("TGTHD" + str1);
					wholeSellService.updateObj(wgPurchaseReturn);
				}
			}
			logManageService.insertLog(request, "生成了一条团购退货单", "采购退货");
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
