package com.proem.exm.controller.promotion;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotionItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.SwitchGoodsItems;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.promotion.DiscountService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 促销折扣
 * @author xuehr
 *
 */
@Controller
@RequestMapping("discount/discount")
public class DiscountController extends BaseController {
	
	@Autowired
	private DiscountService discountService ;
	
	@Autowired
	private GoodsFileService goodsFileService ;
	
	@Autowired
	private CommodityClassifyService commodityClassifyService;
	
	@InitBinder("zcSalesPromotion")
	public void initZcSalesPromotion(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcSalesPromotion.");
	}
	
	@InitBinder("branchTotal")
	public void initBranchTotal(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchTotal.");
	}
	
	@InitBinder("goodsFile")
	public void initgoodsFile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}
	
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "促销管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "促销管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "折扣";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("promotion/Discount_list");
		return view;
	}

	@RequestMapping(value = "listZcSalesPromotionJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPurchaseReceiveJson(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, 
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = discountService.getPagedDataGridObj(page,
					zcSalesPromotion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 打开新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "gotoAddPromotion")
	public ModelAndView gotoAddPromotion(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		List<ZcSalesPromotionItem> zcSalesPromotionItemList = discountService
				.getListByObj(ZcSalesPromotionItem.class,
						" SalesPromotion_id is null ");
		if (zcSalesPromotionItemList != null && zcSalesPromotionItemList.size() > 0) {
			for (int i = 0; i < zcSalesPromotionItemList.size(); i++) {
				discountService.deleteObjById(zcSalesPromotionItemList.get(i)
						.getId(), ZcSalesPromotionItem.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("promotion/Discount_add_2");
		return view;
	}
	
	@RequestMapping(value = "listPromotionItemsNullOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionItemsNullOrderJson(
			@ModelAttribute ZcSalesPromotionItem zcSalesPromotionItem, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = discountService.getPromotionAddGoods(page, id,
					zcSalesPromotionItem, ctpUser);
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
	 * @throws Exception
	 */
	@RequestMapping("openChoseGoods")
	public ModelAndView openChoseGoods(String rows, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String zcCodeScope = request.getParameter("zcCodeScope");
		ModelAndView view =null ;
		if (Double.parseDouble(zcCodeScope)==2) {
			view = createIframeView("promotion/Discount_choseClass");
		}
		if (Double.parseDouble(zcCodeScope)==3) {
			view = createIframeView("promotion/Discount_choseBrand");
		}
		if (Double.parseDouble(zcCodeScope)==4) {
			view = createIframeView("promotion/Discount_choseGoods");
		}
		return view;
	}
	/**
	 * 打开分店信息
	 * @param rows
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openChoseBranch")
	public ModelAndView openChoseBranch(String rows, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		ModelAndView view = createIframeView("promotion/Discount_choseBranch");
		return view;
	}
	
	/**
	 * 分页查询分店表所有信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchJson(@ModelAttribute BranchTotal branchTotal,
			String switchOutBranch, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = discountService.getPagedDataGridBranch(page,
					branchTotal);
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
	@RequestMapping(value = "listGoodsMasterJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsMasterJson(@ModelAttribute GoodsFile goodsFile,
			String switchOutBranch, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		String zcCodeScope = goodsFile.getRemark();
		DataGrid dataGrid = null;
		try {
			//类别
			if (Double.parseDouble(zcCodeScope)==2) {
				dataGrid = discountService.getPagedDataGridBrand(page,
						goodsFile,zcCodeScope);
			}
			//品牌
			if (Double.parseDouble(zcCodeScope)==3) {
				dataGrid = discountService.getPagedDataGridBrand(page,
						goodsFile,zcCodeScope);
			}
			if (Double.parseDouble(zcCodeScope)==4) {
				dataGrid = discountService.getPagedDataGridGoods(page,
						goodsFile);
			}
			
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
	@RequestMapping(value = "addDiscountGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addDiscountGoodsToItems(
			@ModelAttribute ZcSalesPromotionItem zcSalesPromotionItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String zcCodeScope = request.getParameter("zcCodeScope");
		String[] idStr = ids.split(",");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		
		/*这个应该是获取用户信息*/
		try {
			if (zcCodeScope != null && !zcCodeScope.equals("")) {
				if (Double.parseDouble(zcCodeScope) ==1) {
					
					ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
					zcSalesPromotionItems.setId(UuidUtils.getUUID());
					zcSalesPromotionItems.setCreateTime(now);
					zcSalesPromotionItems.setUpdateTime(now);
					discountService.saveObj(zcSalesPromotionItems);
				} else
				if (Double.parseDouble(zcCodeScope) ==2) {
					
					for (int i = 0; i < idStr.length; i++) {
						String classFilyId = idStr[i];
						CommodityClassify commodityClassify = (CommodityClassify)commodityClassifyService.getObjById(classFilyId, "CommodityClassify");
						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						zcSalesPromotionItems.setId(UuidUtils.getUUID());
						zcSalesPromotionItems.setCreateTime(now);
						zcSalesPromotionItems.setUpdateTime(now);
						zcSalesPromotionItems.setClassClassify(commodityClassify);
						discountService.saveObj(zcSalesPromotionItems);
					}
				}else
				if (Double.parseDouble(zcCodeScope) ==3) {
					for (int i = 0; i < idStr.length; i++) {
						String brandFilyId = idStr[i];
						CommodityClassify commodityClassify = (CommodityClassify)commodityClassifyService.getObjById(brandFilyId, "CommodityClassify");
						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						zcSalesPromotionItems.setId(UuidUtils.getUUID());
						zcSalesPromotionItems.setCreateTime(now);
						zcSalesPromotionItems.setUpdateTime(now);
						zcSalesPromotionItems.setBrandClassify(commodityClassify);
						discountService.saveObj(zcSalesPromotionItems);
					}
				}else
				if (Double.parseDouble(zcCodeScope) ==4) {
					for (int i = 0; i < idStr.length; i++) {
						String goodsFileId = idStr[i];
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goodsFileId, "GoodsFile");
						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						zcSalesPromotionItems.setId(UuidUtils.getUUID());
						zcSalesPromotionItems.setCreateTime(now);
						zcSalesPromotionItems.setUpdateTime(now);
						zcSalesPromotionItems.setGoodsFile(goodsFile);
						discountService.saveObj(zcSalesPromotionItems);
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的临时促销表", "折扣促销单");
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
	 * 一键生成当前商品的促销折扣订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createZcSalesPromotion", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createZcSalesPromotion(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, String orderId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		String ids = request.getParameter("ids");
		String allDiscounts = request.getParameter("allDiscount");
		String fullBuyMoneys = request.getParameter("fullBuyMoney");
		String fullBuyCounts = request.getParameter("fullBuyCount");
		String discounts = request.getParameter("discount");	
		String chkValue = request.getParameter("chkValue");
		String branchCodes = request.getParameter("branchCode");
		//String zcCodeModeId = request.getParameter("zcCodeMode");
		//String zcCodeScopeId = request.getParameter("zcCodeScope");
//		if (zcCodeModeId!=null && !zcCodeModeId.equals("")) {
//			Code zcCodeMode = (Code)discountService.getObjById(zcCodeModeId, "Code");
//			zcSalesPromotion.setZcCodeMode(zcCodeMode);
//		}
//		if (zcCodeScopeId!=null && !zcCodeScopeId.equals("")) {
//			Code zcCodeScope = (Code)discountService.getObjById(zcCodeScopeId, "Code");
//			zcSalesPromotion.setZcCodeScope(zcCodeScope);
//		}
		System.out.println(zcSalesPromotion.getZcCodeMode().getId());
		System.out.println(zcSalesPromotion.getZcCodeScope().getId());
		List<Code> listScope = discountService.getListByObj(Code.class, "CODETYPE = 'SaleScope' and CODEVALUE='"+zcSalesPromotion.getZcCodeScope().getId()+"'");
		if (listScope!=null && listScope.size()>0) {
			Code zcCodeScope =listScope.get(0);
			zcSalesPromotion.setZcCodeScope(zcCodeScope);
		}
		List<Code> listMode = discountService.getListByObj(Code.class, "CODETYPE = 'DiscountType' and CODEVALUE='"+zcSalesPromotion.getZcCodeMode().getId()+"'");
		if (listMode!=null && listMode.size()>0) {
			Code zcCodeMode =listMode.get(0);
			zcSalesPromotion.setZcCodeMode(zcCodeMode);
		}
		String[] branchCode = branchCodes.split("\\|");
		for (int i = 0; i < branchCode.length; i++) {
			List<BranchTotal> list =discountService.getListByObj(BranchTotal.class, "branch_Code ='"+branchCode[i]+"'");
			if (list!=null &&list.size()>0) {
				zcSalesPromotion.setBranchTotalList(list);
			}
		}
		String[] idStr = ids.split(",");
		String[] allDiscount = allDiscounts.split(",");
		String[] fullBuyMoney = fullBuyMoneys.split(",");
		String[] fullBuyCount = fullBuyCounts.split(",");
		String[] discount = discounts.split(",");
		orderId = UuidUtils.getUUID();
		zcSalesPromotion.setId(orderId);
		zcSalesPromotion.setCreateTime(new Date());
		zcSalesPromotion.setUpdateTime(new Date());
		zcSalesPromotion.setCreateMan(userInfo.getUserName());
		zcSalesPromotion.setPromotionDays(chkValue);
		zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_UNDO);
		
		try {
			Long count = discountService.getCountByObj(ZcSalesPromotion.class,
					"Promotion_Number = '" + zcSalesPromotion.getPromotionNumber() + "'");
			String bianhao = "";
			if (count != 0) {
				SimpleDateFormat formatDate = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Date date = new Date();
				bianhao = formatDate.format(date);
				zcSalesPromotion.setPromotionNumber("ZKD" + bianhao);
			}
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
					String id = idStr[i];
					ZcSalesPromotionItem zcSalesPromotionItem = (ZcSalesPromotionItem) discountService
							.getObjById(id, "ZcSalesPromotionItem");
					if (zcSalesPromotionItem != null) {
						zcSalesPromotionItem.setSalesPromotionId(zcSalesPromotion.getId());
						if (allDiscount[i]!=null && !allDiscount.equals("")) {
							zcSalesPromotionItem.setAllDiscount(allDiscount[i]);
						}
						if (fullBuyMoney[i]!=null && !fullBuyMoney[i].equals("")) {
							zcSalesPromotionItem.setFullBuyMoney(Double.parseDouble(fullBuyMoney[i]));
						}
						if (fullBuyCount[i]!=null && !fullBuyCount[i].equals("")) {
							zcSalesPromotionItem.setFullBuyCount(Double.parseDouble(fullBuyCount[i]));
						}
						if (discount[i]!=null && !discount[i].equals("")) {
							zcSalesPromotionItem.setDiscount(discount[i]);
						}
						
						discountService.updateObj(zcSalesPromotionItem);
					}
				}
			}
			discountService.saveObj(zcSalesPromotion);
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
	
	
	// 删除
		@RequestMapping(value = "deleteChose", method = RequestMethod.POST, produces = "application/json")
		@ResponseBody
		public AjaxResult deleteChose(HttpServletRequest request,
				HttpServletResponse response, String id) {
			AjaxResult ajaxResult = null;
			try {
				String idOld = request.getParameter("idStr");
				// 获取页面填写的采购订单商品需要更新的数量
				String allDiscounts = request.getParameter("allDiscount");
				String fullBuyMoneys = request.getParameter("fullBuyMoney");
				String fullBuyCounts = request.getParameter("fullBuyCount");
				String discounts = request.getParameter("discount");
				String[] allDiscount = allDiscounts.split(",");
				String[] fullBuyMoney = fullBuyMoneys.split(",");
				String[] idStr = idOld.split(",");
				String[] fullBuyCount = fullBuyCounts.split(",");
				String[] discount = discounts.split(",");
				for (int i = 0; i < idStr.length; i++) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ZcSalesPromotionItem zcSalesPromotionItem = (ZcSalesPromotionItem) discountService
							.getObjById(idStr[i], "ZcSalesPromotionItem");
					if (allDiscount[i]!=null && !allDiscount.equals("")) {
						zcSalesPromotionItem.setAllDiscount(allDiscount[i]);
					}
					if (fullBuyMoney[i]!=null && !fullBuyMoney[i].equals("")) {
						zcSalesPromotionItem.setFullBuyMoney(Double.parseDouble(fullBuyMoney[i]));
					}
					if (fullBuyCount[i]!=null && !fullBuyCount[i].equals("")) {
						zcSalesPromotionItem.setFullBuyCount(Double.parseDouble(fullBuyCount[i]));
					}
					if (discount[i]!=null && !discount[i].equals("")) {
						zcSalesPromotionItem.setDiscount(discount[i]);
					}
					// 保存采购订单商品对象
					discountService.saveObj(zcSalesPromotionItem);
				}
				String[] ids = id.split(",");
				for (int i = 0; i < ids.length; i++) {
					discountService.deleteObjById(ids[i],
							ZcSalesPromotionItem.class.getName());
				}
				logManageService.insertLog(request, "移除了促销订单", "促销订单");
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
