package com.proem.exm.controller.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.utils.GoodFileCompare;
import com.proem.exm.entity.warehouse.CheckGoodsItems;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturn;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnItem;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSellGoodsItems;
import com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnItemService;
import com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 团购退货单
 * 
 * @author ZuoYM
 */
@Controller
@RequestMapping("wholesaleGroupPurchase/return")
public class WholesaleGroupPurchaseReturnController extends BaseController {

	@Autowired
	WGPurchaseReturnService wGPurchaseReturnService;
	@Autowired
	WGPurchaseReturnItemService wGPurchaseReturnItemService;

	@InitBinder("WGPurchaseReturn")
	public void initWGPR(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("WGPurchaseReturn.");
	}

	@InitBinder("WGPurchaseReturnItem")
	public void initWGPRI(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("WGPurchaseReturnItem.");
	}

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
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
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
			fasonName = "团购退货单管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "团购退货单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesaleGroupPurchaseReturn/wGPurchaseReturn_list");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param wGPurchaseReturn
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listWGPurchaseReturnJson", produces = "application/json")
	@ResponseBody
	public DataGrid listWGPurchaseReturnJson(
			@ModelAttribute WGPurchaseReturn wGPurchaseReturn,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPurchaseReturnService.getPagedDataGridObj(page,
					wGPurchaseReturn);
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
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) wGPurchaseReturnService
				.getObjById(id, "WGPurchaseReturn");
		model.addAttribute("WGPurchaseReturn", wGPurchaseReturn);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesaleGroupPurchaseReturn/wGPurchaseReturn_edit");
		return view;
	}

	/**
	 * 打开审核页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoCheck")
	public ModelAndView gotoCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) wGPurchaseReturnService
				.getObjById(id, "WGPurchaseReturn");
		model.addAttribute("WGPurchaseReturn", wGPurchaseReturn);
		model.addAttribute("id", id);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesaleGroupPurchaseReturn/wGPurchaseReturn_check");
		return view;
	}

	/**
	 * 查看明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) wGPurchaseReturnService
				.getObjById(id, "WGPurchaseReturn");
		model.addAttribute("WGPurchaseReturn", wGPurchaseReturn);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/wholesaleGroupPurchaseReturn/wGPurchaseReturn_detail");
		return view;
	}

	/**
	 * 团购退货单明细查询
	 * 
	 * @param wGPurchaseReturnItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listReturnItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listReturnItemJson(
			@ModelAttribute WGPurchaseReturn wGPurchaseReturn,
			String serialNumber, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wGPurchaseReturnService.getWGPurchaseReturnItem(page,
					wGPurchaseReturn, serialNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 保存修改
	 * 
	 * @param wGPurchaseReturn
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateReturn", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateReturn(
			@ModelAttribute WGPurchaseReturn wGPurchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WGPurchaseReturn returns = (WGPurchaseReturn) wGPurchaseReturnService
				.getObjById(id, "WGPurchaseReturn");
		Branch branch = (Branch) wGPurchaseReturnService.getObjById(
				wGPurchaseReturn.getBranch().getId(), "Branch");
		CustomerInfo customerInfo = (CustomerInfo) wGPurchaseReturnService
				.getObjById(wGPurchaseReturn.getCustomerInfo().getId(),
						"CustomerInfo");
		returns.setBranch(branch);
		returns.setCustomerInfo(customerInfo);
		returns.setRemark(wGPurchaseReturn.getRemark());
		String ids = request.getParameter("ids");
		String returnNum = request.getParameter("returnNums");
		String[] idStr = ids.split(",");
		String[] returnNums = returnNum.split(",");
		double money = 0;
		try {
			for (int i = 0; i < returnNums.length; i++) {
				WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) wGPurchaseReturnService
						.getObjById(idStr[i], "WGPurchaseReturnItem");
				wGPurchaseReturnItem.setReturnNums(returnNums[i]);
				// 退货金额=单价*数量/（1+税率）
				String price = wGPurchaseReturnItem.getOrderPrice();
				String rate = wGPurchaseReturnItem.getRate();
				wGPurchaseReturnItem.setReturnAmount(Double
						.valueOf(returnNums[i])
						* Double.valueOf(price)
						/ (1 + Double.valueOf(rate)) + "");
				wGPurchaseReturnService.updateObj(wGPurchaseReturnItem);
				money += Double.valueOf(wGPurchaseReturnItem.getReturnAmount());
			}
			returns.setReturnMoney(money + "");
			returns.setStatue(Constant.CHECK_STATUS_UNDO);
			wGPurchaseReturnService.updateObj(returns);
			logManageService.insertLog(request, "保存了实际退货数量", "团购退货单");
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
	 * @return
	 */
	@RequestMapping(value = "submitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult submitCheck(
			@ModelAttribute WGPurchaseReturn wGPurchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		WGPurchaseReturn returns = (WGPurchaseReturn) wGPurchaseReturnService
				.getObjById(id, "WGPurchaseReturn");
		try {
			returns.setStatue(Constant.CHECK_STATUS_WAITCHECK);
			wGPurchaseReturnService.updateObj(returns);
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
	 * 审核通过
	 * 
	 * @param wGPurchaseReturn
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(
			@ModelAttribute WGPurchaseReturn wGPurchaseReturn,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		String ids = request.getParameter("ids");
		String returnNum = request.getParameter("returnNums");
		String returnAmounts = request.getParameter("returnAmount");
		String[] returnAmount = returnAmounts.split(",");
		String[] returnNums = returnNum.split(",");
		String[] idStr = ids.split(",");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			WGPurchaseReturn returns = (WGPurchaseReturn) wGPurchaseReturnService
					.getObjById(id, "WGPurchaseReturn");
			String branchId = returns.getBranch().getId();
			String goodsFileId = "";
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) wGPurchaseReturnService
							.getObjById(idStr[i], "WGPurchaseReturnItem");
					if (!StringUtils.isBlank(wGPurchaseReturnItem
							.getGoodsFile().getId())) {
						goodsFileId = wGPurchaseReturnItem.getGoodsFile()
								.getId();
					}
					List<ZcStorehouse> zcStorehouseList = wGPurchaseReturnService
							.getListByObj(ZcStorehouse.class, "BRANCH_ID='"
									+ branchId + "' and GOODSFILE_ID='"
									+ goodsFileId + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						for (int j = 0; j < zcStorehouseList.size(); j++) {
							ZcStorehouse zcStorehouse = zcStorehouseList.get(j);
							double num = Double
									.valueOf(zcStorehouse.getStore())
									+ Double.valueOf(returnNums[i]);
							double moneys = Double
									.valueOf(zcStorehouse.getStoreMoney() == null
											|| "".equals(zcStorehouse
													.getStoreMoney()) ? "0"
											: zcStorehouse.getStoreMoney())
									+ Double.valueOf(returnAmount[i]);
							zcStorehouse.setStore(num + "");
							zcStorehouse.setStoreMoney(moneys + "");
							wGPurchaseReturnService.updateObj(zcStorehouse);
						}
					}
				}
			}
			returns.setStatue(Constant.CHECK_STATUS_PASS);
			returns.setCheckMan(userInfo.getCtpUser());
			returns.setCheckDate(df.parse(df.format(new Date())));
			wGPurchaseReturnService.updateObj(returns);
			logManageService.insertLog(request, "审核了团购退货单", "团购退货单");
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
	 * @param id
	 * @param type
	 * @param reason
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
			WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) wGPurchaseReturnService
					.getObjById(id, "WGPurchaseReturn");
			wGPurchaseReturn.setStatue(Constant.CHECK_STATUS_NOPASS);
			wGPurchaseReturn.setCheckMan(userInfo.getCtpUser());
			if (wGPurchaseReturn.getReason() != null
					&& wGPurchaseReturn.getReason().length() > 0) {
				wGPurchaseReturn.setReason(wGPurchaseReturn.getReason() + ";"
						+ reason);
			} else {
				wGPurchaseReturn.setReason(reason);
			}
			wGPurchaseReturn.setCheckDate(df.parse(df.format(new Date())));
			wGPurchaseReturnService.updateObj(wGPurchaseReturn);
			logManageService.insertLog(request, "审核了团购退货单", "团购退货单");
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
	 * （修改退货单中退货状态） 移除团购退货单中的商品
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
			// 获取页面填写的退货单商品需要更新的数量
			String returnNum = request.getParameter("returnNums");
			String[] idStr = idOld.split(",");
			String[] returnNums = returnNum.split(",");
			String wholeReturnId = "";
			for (int i = 0; i < idStr.length; i++) {
				WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) wGPurchaseReturnService
						.getObjById(idStr[i], "WGPurchaseReturnItem");
				wholeReturnId = wGPurchaseReturnItem.getwGPurchaseReturn().getId();
				wGPurchaseReturnItem.setReturnNums(returnNums[i]);
				String price=wGPurchaseReturnItem.getOrderPrice();
				String rate=wGPurchaseReturnItem.getRate();
				wGPurchaseReturnItem.setReturnAmount(
						Double.valueOf(returnNums[i])*Double.valueOf(price)/(1+Double.valueOf(rate))
						+"");
				
				wGPurchaseReturnService.updateObj(wGPurchaseReturnItem);
				money += Double.valueOf(wGPurchaseReturnItem.getReturnAmount());
			}
			WGPurchaseReturn returns = (WGPurchaseReturn) wGPurchaseReturnService.getObjById(wholeReturnId,
					"WGPurchaseReturn");
			returns.setReturnMoney(money + "");
			returns.setStatue(Constant.CHECK_STATUS_UNDO);
			wGPurchaseReturnService.updateObj(returns);
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				WGPurchaseReturnItem wGPurchaseReturnItem = (WGPurchaseReturnItem) wGPurchaseReturnService
						.getObjById(idStr[i], "WGPurchaseReturnItem");
				String returnId = wGPurchaseReturnItem.getwGPurchaseReturn().getId();
				WGPurchaseReturn wGPReturns = (WGPurchaseReturn) wGPurchaseReturnService.getObjById(returnId,
						"WGPurchaseReturn");
				wGPReturns.setReturnMoney(Double.valueOf(wGPReturns.getReturnMoney()) - Double
						.valueOf(wGPurchaseReturnItem.getReturnAmount()) + "");
				//修改销售单中退货标识
				WholeSellGoodsItems wholeSellGoodsItems=wGPurchaseReturnItem.getWholeSellGoodsItems();
				wholeSellGoodsItems.setCreateFlag("");
				wGPurchaseReturnService.saveObj(wholeSellGoodsItems);
				wGPurchaseReturnService.deleteObjById(ids[i],
						WGPurchaseReturnItem.class.getName());
				wGPurchaseReturnService.updateObj(wGPReturns);
			}
			logManageService.insertLog(request, "移除了勾选的团购退货单中的商品", "团购退货单");


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
	 * 删除团购退货单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteReturn", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteReturn(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<WGPurchaseReturnItem> wgPurchaseReturnItemsList = wGPurchaseReturnService
						.getListByObj(WGPurchaseReturnItem.class,
								"WGPURCHASERETURN_ID='" + ids[i] + "'");
				if (wgPurchaseReturnItemsList != null
						&& wgPurchaseReturnItemsList.size() > 0) {
					for (int j = 0; j < wgPurchaseReturnItemsList.size(); j++) {
						WGPurchaseReturnItem wgPurchaseReturnItem = wgPurchaseReturnItemsList
								.get(j);
						WholeSellGoodsItems wholeSellGoodsItems = wgPurchaseReturnItem
								.getWholeSellGoodsItems();
						wholeSellGoodsItems.setCreateFlag("");
						wGPurchaseReturnService.deleteObjById(
								wgPurchaseReturnItem.getId(),
								WGPurchaseReturnItem.class.getName());
						wGPurchaseReturnService.updateObj(wholeSellGoodsItems);
					}
				}
				wGPurchaseReturnService.deleteObjById(ids[i],
						WGPurchaseReturn.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的团购退货单", "团购退货单");
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
