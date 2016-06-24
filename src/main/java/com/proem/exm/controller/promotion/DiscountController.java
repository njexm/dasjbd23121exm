package com.proem.exm.controller.promotion;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
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
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotionItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.promotion.DiscountService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 促销折扣
 * 
 * @author xuehr
 * 
 */
@Controller
@RequestMapping("discount/discount")
public class DiscountController extends BaseController {

	@Autowired
	private DiscountService discountService;

	@Autowired
	private GoodsFileService goodsFileService;

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

	// 打开详细页面
	@RequestMapping("gotoDetailDiscount")
	public ModelAndView gotoDetailDiscount(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) discountService
				.getObjById(id, "ZcSalesPromotion");
//		List<BranchTotal> branchTotalList = zcSalesPromotion
//				.getBranchTotalList();
		String branchTotal = "";
//		for (int i = 0; i < branchTotalList.size(); i++) {
//			branchTotal += branchTotalList.get(i).getBranch_code() + "|";
//		}
		branchTotal = zcSalesPromotion.getBranchs();
		String zcCodeScope = "";
		String zcCodeScopeCode = "";
		Code codeScope = (Code) discountService.getObjById(zcSalesPromotion
				.getZcCodeScope().getId(), "Code");
		if (codeScope != null) {
			zcCodeScope = codeScope.getCodeName();
			zcCodeScopeCode = codeScope.getCodeValue();
		}
		String zcCodeMode = "";
		String zcCodeModeCode = "";
		Code codeMode = (Code) discountService.getObjById(zcSalesPromotion
				.getZcCodeMode().getId(), "Code");
		if (codeMode != null) {
			zcCodeMode = codeMode.getCodeName();
			zcCodeModeCode = codeMode.getCodeValue();
		}
		List<Code> listMember = discountService.getListByObj(
				Code.class,
				"CODETYPE = 'memberLevel' and CODEVALUE='"
						+ zcSalesPromotion.getMemberLevel() + "'");
		String memberLevel = "";
		if (listMember != null && listMember.size() > 0) {
			Code member = listMember.get(0);
			memberLevel = member.getCodeName();
		}
		String promotionDays = zcSalesPromotion.getPromotionDays();
		String[] promotionDay ;
		String week = "";
		if (promotionDays!=null && !promotionDays.equals("")) {
			 promotionDay = promotionDays.split(",");
			 week="星期";
			 for (int i = 0; i < promotionDay.length; i++) {
					double num = Double.parseDouble(promotionDay[i]);
					if (num == 1) {
						week += "一,";
					}
					if (num == 2) {
						week += "二,";
					}
					if (num == 3) {
						week += "三,";
					}
					if (num == 4) {
						week += "四,";
					}
					if (num == 5) {
						week += "五,";
					}
					if (num == 6) {
						week += "六,";
					}
					if (num == 7) {
						week += "日,";
					}
				}
		}
		Date date = zcSalesPromotion.getPromotionBeginDate();
		Date date2 = zcSalesPromotion.getPromotionEndDate();
		Date date3 = zcSalesPromotion.getStopDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate="";
		String endDate = "";
		String stopDate ="";
		if (date!=null) {
			 beginDate = sdf.format(date);
		}
		if (date2!=null) {
			 endDate = sdf.format(date2);
		}
		if (date3!=null) {
			 stopDate = sdf.format(date3);
		}
		model.addAttribute("zcSalesPromotion", zcSalesPromotion);
		model.addAttribute("zcSalesPromotionId", id);
		model.addAttribute("branchTotal", branchTotal);
		model.addAttribute("zcCodeScope", zcCodeScope);
		model.addAttribute("zcCodeMode", zcCodeMode);
		model.addAttribute("zcCodeScopeCode", zcCodeScopeCode);
		model.addAttribute("zcCodeModeCode", zcCodeModeCode);
		model.addAttribute("memberLevel", memberLevel);
		model.addAttribute("week", week);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("stopDate", stopDate);
		ModelAndView view = createIframeView("promotion/Discount_detail");
		return view;
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
		if (zcSalesPromotionItemList != null
				&& zcSalesPromotionItemList.size() > 0) {
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

	/**
	 * 打开编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoEditDiscountEdit")
	public ModelAndView gotoEditDiscountEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) discountService
				.getObjById(id, "ZcSalesPromotion");
//		List<BranchTotal> branchTotalList = zcSalesPromotion
//				.getBranchTotalList();
		String branchTotal = "";
//		for (int i = 0; i < branchTotalList.size(); i++) {
//			branchTotal += branchTotalList.get(i).getBranch_code() + "|";
//		}
		branchTotal=zcSalesPromotion.getBranchs();
		model.addAttribute("zcSalesPromotion", zcSalesPromotion);
		model.addAttribute("id", id);
		model.addAttribute("branchTotal", branchTotal);
		ModelAndView view = createIframeView("promotion/Discount_edit");
		return view;
	}

	@RequestMapping(value = "listPromotionItemsNullOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionItemsNullOrderJson(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = discountService.getPromotionAddGoods(page, id,
					zcSalesPromotion, ctpUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listPromotionItemsEditOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionItemsEditOrderJson(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			ZcSalesPromotion zcSalesPromotionNew = new ZcSalesPromotion();
			if (zcSalesPromotion != null) {
				String ids = zcSalesPromotion.getId();
				zcSalesPromotionNew.setId(ids);
				List<Code> listScope = discountService.getListByObj(Code.class,
						"CODETYPE = 'SaleScope' and CODEVALUE='"
								+ zcSalesPromotion.getZcCodeScope()
										.getCodeValue() + "'");
				if (listScope != null && listScope.size() > 0) {
					Code zcCodeScope = listScope.get(0);
					zcSalesPromotionNew.setZcCodeScope(zcCodeScope);
				}
				List<Code> listMode = discountService.getListByObj(Code.class,
						"CODETYPE = 'DiscountType' and CODEVALUE='"
								+ zcSalesPromotion.getZcCodeMode()
										.getCodeValue() + "'");
				if (listMode != null && listMode.size() > 0) {
					Code zcCodeMode = listMode.get(0);
					zcSalesPromotionNew.setZcCodeMode(zcCodeMode);
				}
			}
			dataGrid = discountService.getPromotionEditGoods(page,
					zcSalesPromotionNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listPromotionItemsDetailOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionItemsDetailOrderJson(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = discountService.getPromotionDeailGoods(page,
					zcSalesPromotion);
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
		ModelAndView view = null;
		String idExists = request.getParameter("idStr");
		String allDiscounts = request.getParameter("allDiscount");
		String fullBuyMoneys = request.getParameter("fullBuyMoney");
		String fullBuyCounts = request.getParameter("fullBuyCount");
		String discounts = request.getParameter("discount");
		
		if (idExists!=null && !idExists.equals("")) {
			String[] idExist = idExists.split(",");
			String[] allDiscount = allDiscounts.split(",");
			String[] fullBuyMoney = fullBuyMoneys.split(",");
			String[] fullBuyCount = fullBuyCounts.split(",");
			String[] discount = discounts.split(",");
			for (int i = 0; i < idExist.length; i++) {
				ZcSalesPromotionItem zcSalesPromotionItemExist = (ZcSalesPromotionItem)discountService.getObjById(idExist[i], ZcSalesPromotionItem.class.getName());
				zcSalesPromotionItemExist.setAllDiscount(allDiscount[i]);
				zcSalesPromotionItemExist.setDiscount(discount[i]);
				zcSalesPromotionItemExist.setFullBuyCount(Double.parseDouble(fullBuyCount[i]));
				zcSalesPromotionItemExist.setFullBuyMoney(Double.parseDouble(fullBuyMoney[i]));
				discountService.updateObj(zcSalesPromotionItemExist);
			}
		}
		if (Double.parseDouble(zcCodeScope) == 2) {
			view = createIframeView("promotion/Discount_choseClass");
		}
		if (Double.parseDouble(zcCodeScope) == 3) {
			view = createIframeView("promotion/Discount_choseBrand");
		}
		if (Double.parseDouble(zcCodeScope) == 4) {
			view = createIframeView("promotion/Discount_choseGoods");
		}
		return view;
	}

	/**
	 * 打开分店信息
	 * 
	 * @param rows
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openChoseBranch")
	public ModelAndView openChoseBranch(String rows,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
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
			dataGrid = discountService
					.getPagedDataGridBranch(page, branchTotal);
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
			// 类别
			if (Double.parseDouble(zcCodeScope) == 2) {
				dataGrid = discountService.getPagedDataGridBrand(page,
						goodsFile, zcCodeScope);
			}
			// 品牌
			if (Double.parseDouble(zcCodeScope) == 3) {
				dataGrid = discountService.getPagedDataGridBrand(page,
						goodsFile, zcCodeScope);
			}
			if (Double.parseDouble(zcCodeScope) == 4) {
				dataGrid = discountService.getPagedDataGridGoods(page,
						goodsFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 根据商品表勾选直接生成促销订单
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		/* 这个应该是获取用户信息 */
		try {
			if (zcCodeScope != null && !zcCodeScope.equals("")) {
				if (Double.parseDouble(zcCodeScope) == 1) {

					ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
					zcSalesPromotionItems.setId(UuidUtils.getUUID());
					zcSalesPromotionItems.setCreateTime(now);
					zcSalesPromotionItems.setUpdateTime(now);
					discountService.saveObj(zcSalesPromotionItems);
				} else if (Double.parseDouble(zcCodeScope) == 2) {

					for (int i = 0; i < idStr.length; i++) {
						String classFilyId = idStr[i];
						CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
								.getObjById(classFilyId, "CommodityClassify");
						Long count = discountService.getCountByObj(
								ZcSalesPromotionItem.class,
								"SalesPromotion_id is null and CLASS_CLASSIFY_ID='"
										+ commodityClassify.getId() + "'");
						if (count != 0) {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.ERROR, AjaxResult.INFO);
							return ajaxResult;
						} else {
							ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
							zcSalesPromotionItems.setId(UuidUtils.getUUID());
							zcSalesPromotionItems.setCreateTime(now);
							zcSalesPromotionItems.setUpdateTime(now);
							zcSalesPromotionItems
									.setClassClassify(commodityClassify);
							discountService.saveObj(zcSalesPromotionItems);
						}
					}
				} else if (Double.parseDouble(zcCodeScope) == 3) {
					for (int i = 0; i < idStr.length; i++) {
						String brandFilyId = idStr[i];
						CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
								.getObjById(brandFilyId, "CommodityClassify");
						Long count = discountService.getCountByObj(
								ZcSalesPromotionItem.class,
								"SalesPromotion_id is null and brand_CLASSIFY_ID='"
										+ commodityClassify.getId() + "'");
						if (count != 0) {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.ERROR, AjaxResult.INFO);
							return ajaxResult;
						} else {
							ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
							zcSalesPromotionItems.setId(UuidUtils.getUUID());
							zcSalesPromotionItems.setCreateTime(now);
							zcSalesPromotionItems.setUpdateTime(now);
							zcSalesPromotionItems
									.setBrandClassify(commodityClassify);
							discountService.saveObj(zcSalesPromotionItems);
						}
					}
				} else if (Double.parseDouble(zcCodeScope) == 4) {
					for (int i = 0; i < idStr.length; i++) {
						String goodsFileId = idStr[i];
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goodsFileId, "GoodsFile");
						Long count = discountService.getCountByObj(
								ZcSalesPromotionItem.class,
								"SalesPromotion_id is null and GOODSFILE_ID='"
										+ goodsFile.getId() + "'");
						if (count != 0) {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.ERROR, AjaxResult.INFO);
							return ajaxResult;
						} else {
							ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
							zcSalesPromotionItems.setId(UuidUtils.getUUID());
							zcSalesPromotionItems.setCreateTime(now);
							zcSalesPromotionItems.setUpdateTime(now);
							zcSalesPromotionItems.setGoodsFile(goodsFile);
							discountService.saveObj(zcSalesPromotionItems);
						}
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
		// String zcCodeModeId = request.getParameter("zcCodeMode");
		// String zcCodeScopeId = request.getParameter("zcCodeScope");
		// if (zcCodeModeId!=null && !zcCodeModeId.equals("")) {
		// Code zcCodeMode = (Code)discountService.getObjById(zcCodeModeId,
		// "Code");
		// zcSalesPromotion.setZcCodeMode(zcCodeMode);
		// }
		// if (zcCodeScopeId!=null && !zcCodeScopeId.equals("")) {
		// Code zcCodeScope = (Code)discountService.getObjById(zcCodeScopeId,
		// "Code");
		// zcSalesPromotion.setZcCodeScope(zcCodeScope);
		// }
		System.out.println(zcSalesPromotion.getZcCodeMode().getId());
		System.out.println(zcSalesPromotion.getZcCodeScope().getId());
		List<Code> listScope = discountService.getListByObj(Code.class,
				"CODETYPE = 'SaleScope' and CODEVALUE='"
						+ zcSalesPromotion.getZcCodeScope().getId() + "'");
		if (listScope != null && listScope.size() > 0) {
			Code zcCodeScope = listScope.get(0);
			zcSalesPromotion.setZcCodeScope(zcCodeScope);
		}
		List<Code> listMode = discountService.getListByObj(Code.class,
				"CODETYPE = 'DiscountType' and CODEVALUE='"
						+ zcSalesPromotion.getZcCodeMode().getId() + "'");
		if (listMode != null && listMode.size() > 0) {
			Code zcCodeMode = listMode.get(0);
			zcSalesPromotion.setZcCodeMode(zcCodeMode);
		}
//		String[] branchCode = branchCodes.split("\\|");
//		List<BranchTotal> listBranch = new ArrayList<BranchTotal>();
//		for (int i = 0; i < branchCode.length; i++) {
//			List<BranchTotal> list = discountService.getListByObj(
//					BranchTotal.class, "branch_Code ='" + branchCode[i] + "'");
//			if (list != null && list.size() > 0) {
//				BranchTotal branchTotalObj = list.get(0);
//				listBranch.add(branchTotalObj);
//				
//			}
//		}
		zcSalesPromotion.setBranchs(branchCodes);;
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
		zcSalesPromotion.setStopDate(zcSalesPromotion.getPromotionEndDate());
		try {
			Long count = discountService.getCountByObj(
					ZcSalesPromotion.class,
					"Promotion_Number = '"
							+ zcSalesPromotion.getPromotionNumber() + "'");
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
						zcSalesPromotionItem
								.setSalesPromotionId(zcSalesPromotion.getId());
						if (allDiscount[i] != null && !allDiscount.equals("")) {
							zcSalesPromotionItem.setAllDiscount(allDiscount[i]);
						}
						if (fullBuyMoney[i] != null
								&& !fullBuyMoney[i].equals("")) {
							zcSalesPromotionItem.setFullBuyMoney(Double
									.parseDouble(fullBuyMoney[i]));
						}
						if (fullBuyCount[i] != null
								&& !fullBuyCount[i].equals("")) {
							zcSalesPromotionItem.setFullBuyCount(Double
									.parseDouble(fullBuyCount[i]));
						}
						if (discount[i] != null && !discount[i].equals("")) {
							zcSalesPromotionItem.setDiscount(discount[i]);
						}

						discountService.updateObj(zcSalesPromotionItem);
					}
				}
			}
			discountService.saveObj(zcSalesPromotion);
			logManageService.insertLog(request, "勾选商品信息生成促销订单", "促销订单");
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
	 * 修改当前商品的促销折扣订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "editZcSalesPromotion", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult editZcSalesPromotion(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion, String orderId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcSalesPromotion zcSalesPromotionHave =new ZcSalesPromotion();
		if (zcSalesPromotion!=null) {
			zcSalesPromotionHave =(ZcSalesPromotion)discountService.getObjById(zcSalesPromotion.getId(), ZcSalesPromotion.class.getName());
		}
		 

		String ids = request.getParameter("ids");
		String allDiscounts = request.getParameter("allDiscount");
		String fullBuyMoneys = request.getParameter("fullBuyMoney");
		String fullBuyCounts = request.getParameter("fullBuyCount");
		String discounts = request.getParameter("discount");
		String chkValue = request.getParameter("chkValue");
		String branchCodes = request.getParameter("branchCode");
		// String zcCodeModeId = request.getParameter("zcCodeMode");
		// String zcCodeScopeId = request.getParameter("zcCodeScope");
		// if (zcCodeModeId!=null && !zcCodeModeId.equals("")) {
		// Code zcCodeMode = (Code)discountService.getObjById(zcCodeModeId,
		// "Code");
		// zcSalesPromotion.setZcCodeMode(zcCodeMode);
		// }
		// if (zcCodeScopeId!=null && !zcCodeScopeId.equals("")) {
		// Code zcCodeScope = (Code)discountService.getObjById(zcCodeScopeId,
		// "Code");
		// zcSalesPromotion.setZcCodeScope(zcCodeScope);
		// }
		System.out.println(zcSalesPromotion.getZcCodeMode().getId());
		System.out.println(zcSalesPromotion.getZcCodeScope().getId());
		List<Code> listScope = discountService.getListByObj(Code.class,
				"CODETYPE = 'SaleScope' and CODEVALUE='"
						+ zcSalesPromotion.getZcCodeScope().getId() + "'");
		if (listScope != null && listScope.size() > 0) {
			Code zcCodeScope = listScope.get(0);
			zcSalesPromotionHave.setZcCodeScope(zcCodeScope);
		}
		List<Code> listMode = discountService.getListByObj(Code.class,
				"CODETYPE = 'DiscountType' and CODEVALUE='"
						+ zcSalesPromotion.getZcCodeMode().getId() + "'");
		if (listMode != null && listMode.size() > 0) {
			Code zcCodeMode = listMode.get(0);
			zcSalesPromotionHave.setZcCodeMode(zcCodeMode);
		}
//		String[] branchCode = branchCodes.split("\\|");
//		List<BranchTotal> listBranch = new ArrayList<BranchTotal>();
//		for (int i = 0; i < branchCode.length; i++) {
//			List<BranchTotal> list = discountService.getListByObj(
//					BranchTotal.class, "branch_Code ='" + branchCode[i] + "'");
//			if (list != null && list.size() > 0) {
//				BranchTotal branchTotalObj = list.get(0);
//				listBranch.add(branchTotalObj);
//				
//			}
//		}
		zcSalesPromotionHave.setBranchs(branchCodes);;
		zcSalesPromotionHave.setMemberLevel(zcSalesPromotion.getMemberLevel());
		zcSalesPromotionHave.setPromotionBeginDate(zcSalesPromotion
				.getPromotionBeginDate());
		zcSalesPromotionHave.setPromotionDays(zcSalesPromotion
				.getPromotionDays());
		zcSalesPromotionHave.setPromotionEndDate(zcSalesPromotion
				.getPromotionEndDate());
		if (zcSalesPromotion.getPromotionRemark() != null
				&& !zcSalesPromotion.getPromotionRemark().equals("")) {
			zcSalesPromotionHave.setPromotionRemark(zcSalesPromotion
					.getPromotionRemark());
		}
		if (zcSalesPromotion.getPromotionTitle() != null
				&& !zcSalesPromotion.getPromotionTitle().equals("")) {
			zcSalesPromotionHave.setPromotionTitle(zcSalesPromotion
					.getPromotionTitle());
		}
		if (zcSalesPromotion.getStopMan() != null
				&& !zcSalesPromotion.getStopMan().equals("")) {
			zcSalesPromotionHave.setStopMan(zcSalesPromotion.getStopMan());
		}

		zcSalesPromotionHave
				.setStopDate(zcSalesPromotion.getPromotionEndDate());
		String[] idStr = ids.split(",");
		String[] allDiscount = allDiscounts.split(",");
		String[] fullBuyMoney = fullBuyMoneys.split(",");
		String[] fullBuyCount = fullBuyCounts.split(",");
		String[] discount = discounts.split(",");

		try {
			if (zcSalesPromotion.getId() != null
					&& !zcSalesPromotion.getId().equals("")) {
//				zcSalesPromotionHave = (ZcSalesPromotion) discountService
//						.getObjById(zcSalesPromotion.getId(),
//								ZcSalesPromotion.class.getName());
				zcSalesPromotionHave.setUpdateTime(new Date());
				zcSalesPromotionHave.setPromotionDays(chkValue);
				zcSalesPromotionHave
						.setCheckState(Constant.CHECK_STATUS_WAITCHECK);
			}
			List<ZcSalesPromotionItem> list = discountService.getListByObj(
					ZcSalesPromotionItem.class, "SalesPromotion_id='"
							+ zcSalesPromotion.getId() + "'");
			if (list != null && list.size() > 0) {
				if (idStr != null && idStr.length > 0) {
					for (int i = 0; i < idStr.length; i++) {
						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						String id = idStr[i];
						ZcSalesPromotionItem zcSalesPromotionItem = (ZcSalesPromotionItem) discountService
								.getObjById(id, "ZcSalesPromotionItem");
						if (zcSalesPromotionItem != null) {
							if (allDiscount[i] != null
									&& !allDiscount.equals("")) {
								zcSalesPromotionItem
										.setAllDiscount(allDiscount[i]);
							}
							if (fullBuyMoney[i] != null
									&& !fullBuyMoney[i].equals("")) {
								zcSalesPromotionItem.setFullBuyMoney(Double
										.parseDouble(fullBuyMoney[i]));
							}
							if (fullBuyCount[i] != null
									&& !fullBuyCount[i].equals("")) {
								zcSalesPromotionItem.setFullBuyCount(Double
										.parseDouble(fullBuyCount[i]));
							}
							if (discount[i] != null && !discount[i].equals("")) {
								zcSalesPromotionItem.setDiscount(discount[i]);
							}

							discountService.updateObj(zcSalesPromotionItem);
						}
					}
				}
			} else {
				if (idStr != null && idStr.length > 0) {
					for (int i = 0; i < idStr.length; i++) {
						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						String id = idStr[i];
						ZcSalesPromotionItem zcSalesPromotionItem = (ZcSalesPromotionItem) discountService
								.getObjById(id, "ZcSalesPromotionItem");
						if (zcSalesPromotionItem != null) {
							zcSalesPromotionItem
									.setSalesPromotionId(zcSalesPromotion
											.getId());
							if (allDiscount[i] != null
									&& !allDiscount.equals("")) {
								zcSalesPromotionItem
										.setAllDiscount(allDiscount[i]);
							}
							if (fullBuyMoney[i] != null
									&& !fullBuyMoney[i].equals("")) {
								zcSalesPromotionItem.setFullBuyMoney(Double
										.parseDouble(fullBuyMoney[i]));
							}
							if (fullBuyCount[i] != null
									&& !fullBuyCount[i].equals("")) {
								zcSalesPromotionItem.setFullBuyCount(Double
										.parseDouble(fullBuyCount[i]));
							}
							if (discount[i] != null && !discount[i].equals("")) {
								zcSalesPromotionItem.setDiscount(discount[i]);
							}

							discountService.updateObj(zcSalesPromotionItem);
						}
					}
				}
			}

			discountService.updateObj(zcSalesPromotionHave);
			logManageService.insertLog(request, "勾选商品信息修改促销订单", "促销订单");
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
				if (allDiscount[i] != null && !allDiscount.equals("")) {
					zcSalesPromotionItem.setAllDiscount(allDiscount[i]);
				}
				if (fullBuyMoney[i] != null && !fullBuyMoney[i].equals("")) {
					zcSalesPromotionItem.setFullBuyMoney(Double
							.parseDouble(fullBuyMoney[i]));
				}
				if (fullBuyCount[i] != null && !fullBuyCount[i].equals("")) {
					zcSalesPromotionItem.setFullBuyCount(Double
							.parseDouble(fullBuyCount[i]));
				}
				if (discount[i] != null && !discount[i].equals("")) {
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

	// 新增时改变方式删除
	@RequestMapping(value = "deleteChoseChange", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteChoseChange(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String idStrs = request.getParameter("idStr");
			String[] ids = idStrs.split(",");
			for (int i = 0; i < ids.length; i++) {
				discountService.deleteObjById(ids[i],
						ZcSalesPromotionItem.class.getName());
			}

			logManageService.insertLog(request, "移除了新增时改变促销方式的订单详情", "促销订单详情");
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
	 * 打开编辑促销时选择商品的面板
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
		String ids = request.getParameter("ids");
		String allDiscounts = request.getParameter("allDiscount");
		String fullBuyMoneys = request.getParameter("fullBuyMoney");
		String fullBuyCounts = request.getParameter("fullBuyCount");
		String discounts = request.getParameter("discount");
		String[] allDiscount = allDiscounts.split(",");
		String[] fullBuyMoney = fullBuyMoneys.split(",");
		String[] fullBuyCount = fullBuyCounts.split(",");
		String[] discount = discounts.split(",");
		if (ids != null && ids != "") {
			String[] idStr = ids.split(",");
			for (int i = 0; i < idStr.length; i++) {
				ZcSalesPromotionItem zcSalesPromotionItem = (ZcSalesPromotionItem) discountService
						.getObjById(idStr[i], "ZcSalesPromotionItem");
				if (allDiscount[i] != null && !allDiscount.equals("")) {
					zcSalesPromotionItem.setAllDiscount(allDiscount[i]);
				}
				if (fullBuyMoney[i] != null && !fullBuyMoney[i].equals("")) {
					zcSalesPromotionItem.setFullBuyMoney(Double
							.parseDouble(fullBuyMoney[i]));
				}
				if (fullBuyCount[i] != null && !fullBuyCount[i].equals("")) {
					zcSalesPromotionItem.setFullBuyCount(Double
							.parseDouble(fullBuyCount[i]));
				}
				if (discount[i] != null && !discount[i].equals("")) {
					zcSalesPromotionItem.setDiscount(discount[i]);
				}
				// 保存采购订单商品对象
				discountService.updateObj(zcSalesPromotionItem);
			}
		}
		String zcSalesPromotionId = request.getParameter("zcSalesPromotionId");
		String zcCodeScope = request.getParameter("zcCodeScope");
		model.addAttribute("zcSalesPromotionId", zcSalesPromotionId);
		ModelAndView view = null;
		if (Double.parseDouble(zcCodeScope) == 2) {
			view = createIframeView("promotion/Discount_choseClass");
		}
		if (Double.parseDouble(zcCodeScope) == 3) {
			view = createIframeView("promotion/Discount_choseBrand");
		}
		if (Double.parseDouble(zcCodeScope) == 4) {
			view = createIframeView("promotion/Discount_choseGoods");
		}
		return view;
	}

	/**
	 * 根据商品表勾选直接生成促销订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addDiscountGoodsToEditItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addDiscountGoodsToEditItems(
			@ModelAttribute ZcSalesPromotionItem zcSalesPromotionItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String zcCodeScope = request.getParameter("zcCodeScope");
		String[] idStr = ids.split(",");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String zcSalesPromotionId = request.getParameter("zcSalesPromotionId");
		/* 这个应该是获取用户信息 */
		try {
			if (zcSalesPromotionId != null && !zcSalesPromotionId.equals("")) {
				if (zcCodeScope != null && !zcCodeScope.equals("")) {
					if (Double.parseDouble(zcCodeScope) == 1) {

						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						zcSalesPromotionItems.setId(UuidUtils.getUUID());
						zcSalesPromotionItems.setCreateTime(now);
						zcSalesPromotionItems.setUpdateTime(now);
						zcSalesPromotionItems
								.setSalesPromotionId(zcSalesPromotionId);

						discountService.saveObj(zcSalesPromotionItems);
					} else if (Double.parseDouble(zcCodeScope) == 2) {

						for (int i = 0; i < idStr.length; i++) {
							String classFilyId = idStr[i];
							CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
									.getObjById(classFilyId,
											"CommodityClassify");
							Long count = discountService.getCountByObj(
									ZcSalesPromotionItem.class,
									"SalesPromotion_id = '"
											+ zcSalesPromotionId
											+ "' and CLASS_CLASSIFY_ID='"
											+ commodityClassify.getId() + "'");
							if (count != 0) {
								ajaxResult = new AjaxResult(AjaxResult.SAVE,
										AjaxResult.ERROR, AjaxResult.INFO);
								return ajaxResult;
							} else {
								ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
								zcSalesPromotionItems
										.setId(UuidUtils.getUUID());
								zcSalesPromotionItems.setCreateTime(now);
								zcSalesPromotionItems.setUpdateTime(now);
								zcSalesPromotionItems
										.setClassClassify(commodityClassify);
								zcSalesPromotionItems
										.setSalesPromotionId(zcSalesPromotionId);
								discountService.saveObj(zcSalesPromotionItems);
							}
						}
					} else if (Double.parseDouble(zcCodeScope) == 3) {
						for (int i = 0; i < idStr.length; i++) {
							String brandFilyId = idStr[i];
							CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
									.getObjById(brandFilyId,
											"CommodityClassify");
							Long count = discountService.getCountByObj(
									ZcSalesPromotionItem.class,
									"SalesPromotion_id = '"
											+ zcSalesPromotionId
											+ "' and brand_CLASSIFY_ID='"
											+ commodityClassify.getId() + "'");
							if (count != 0) {
								ajaxResult = new AjaxResult(AjaxResult.SAVE,
										AjaxResult.ERROR, AjaxResult.INFO);
								return ajaxResult;
							} else {
								ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
								zcSalesPromotionItems
										.setId(UuidUtils.getUUID());
								zcSalesPromotionItems.setCreateTime(now);
								zcSalesPromotionItems.setUpdateTime(now);
								zcSalesPromotionItems
										.setBrandClassify(commodityClassify);
								zcSalesPromotionItems
										.setSalesPromotionId(zcSalesPromotionId);
								discountService.saveObj(zcSalesPromotionItems);
							}
						}
					} else if (Double.parseDouble(zcCodeScope) == 4) {
						for (int i = 0; i < idStr.length; i++) {
							String goodsFileId = idStr[i];
							GoodsFile goodsFile = (GoodsFile) goodsFileService
									.getObjById(goodsFileId, "GoodsFile");
							Long count = discountService.getCountByObj(
									ZcSalesPromotionItem.class,
									"SalesPromotion_id = '"
											+ zcSalesPromotionId
											+ "' and GOODSFILE_ID='"
											+ goodsFile.getId() + "'");
							if (count != 0) {
								ajaxResult = new AjaxResult(AjaxResult.SAVE,
										AjaxResult.ERROR, AjaxResult.INFO);
								return ajaxResult;
							} else {
								ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
								zcSalesPromotionItems
										.setId(UuidUtils.getUUID());
								zcSalesPromotionItems.setCreateTime(now);
								zcSalesPromotionItems.setUpdateTime(now);
								zcSalesPromotionItems.setGoodsFile(goodsFile);
								zcSalesPromotionItems
										.setSalesPromotionId(zcSalesPromotionId);
								discountService.saveObj(zcSalesPromotionItems);
							}
						}
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
	 * 编辑时全场折扣增加一行
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addDiscountScopeWhole", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addDiscountScopeWhole(
			@ModelAttribute ZcSalesPromotionItem zcSalesPromotionItem,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String zcCodeScope = request.getParameter("zcCodeScope");
		String zcCodeMode = request.getParameter("zcCodeMode");
		String[] idStr = ids.split(",");
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String zcSalesPromotionId = request.getParameter("zcSalesPromotionId");
		/* 这个应该是获取用户信息 */
		try {
			if (zcSalesPromotionId != null && !zcSalesPromotionId.equals("")) {
				ZcSalesPromotion zcSalesPromotionExist = (ZcSalesPromotion) discountService
						.getObjById(zcSalesPromotionId,
								ZcSalesPromotion.class.getName());
				List<Code> listScope = discountService.getListByObj(Code.class,
						"CODETYPE = 'SaleScope' and CODEVALUE='" + zcCodeScope
								+ "'");
				if (listScope != null && listScope.size() > 0) {
					Code zcCodeScopeNow = listScope.get(0);
					zcSalesPromotionExist.setZcCodeScope(zcCodeScopeNow);
				}
				List<Code> listMode = discountService.getListByObj(Code.class,
						"CODETYPE = 'DiscountType' and CODEVALUE='"
								+ zcCodeMode + "'");
				if (listMode != null && listMode.size() > 0) {
					Code zcCodeModeNow = listMode.get(0);
					zcSalesPromotionExist.setZcCodeMode(zcCodeModeNow);
				}
				discountService.updateObj(zcSalesPromotionExist);
				if (zcCodeScope != null && !zcCodeScope.equals("")) {
					if (Double.parseDouble(zcCodeScope) == 1) {

						ZcSalesPromotionItem zcSalesPromotionItems = new ZcSalesPromotionItem();
						zcSalesPromotionItems.setId(UuidUtils.getUUID());
						zcSalesPromotionItems.setCreateTime(now);
						zcSalesPromotionItems.setUpdateTime(now);
						zcSalesPromotionItems
								.setSalesPromotionId(zcSalesPromotionId);

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

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				
				List<ZcSalesPromotionItem> zcSalesPromotionItemList = discountService
						.getListByObj(ZcSalesPromotionItem.class,
								"SalesPromotion_ID='" + ids[i] + "'");
				if (zcSalesPromotionItemList != null
						&& zcSalesPromotionItemList.size() > 0) {
					for (int j = 0; j < zcSalesPromotionItemList.size(); j++) {
						ZcSalesPromotionItem zcSalesPromotionItem = zcSalesPromotionItemList
								.get(j);
						discountService.deleteObjById(
								zcSalesPromotionItem.getId(),
								ZcSalesPromotionItem.class.getName());
					}
				}
				
				discountService.deleteObjById(ids[i],
						ZcSalesPromotion.class.getName());
			}
			logManageService.insertLog(request, "删除勾选中的促销折扣单", "促销折扣单");
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
	 * 打开审核促销单页面
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
		ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) discountService
				.getObjById(id, "ZcSalesPromotion");
//		List<BranchTotal> branchTotalList = zcSalesPromotion
//				.getBranchTotalList();
		String branchTotal = "";
//		for (int i = 0; i < branchTotalList.size(); i++) {
//			branchTotal += branchTotalList.get(i).getBranch_code() + "|";
//		}
		branchTotal = zcSalesPromotion.getBranchs();
		String zcCodeScope = "";
		String zcCodeScopeCode = "";
		Code codeScope = (Code) discountService.getObjById(zcSalesPromotion
				.getZcCodeScope().getId(), "Code");
		if (codeScope != null) {
			zcCodeScope = codeScope.getCodeName();
			zcCodeScopeCode = codeScope.getCodeValue();
		}
		String zcCodeMode = "";
		String zcCodeModeCode = "";
		Code codeMode = (Code) discountService.getObjById(zcSalesPromotion
				.getZcCodeMode().getId(), "Code");
		if (codeMode != null) {
			zcCodeMode = codeMode.getCodeName();
			zcCodeModeCode = codeMode.getCodeValue();
		}
		List<Code> listMember = discountService.getListByObj(
				Code.class,
				"CODETYPE = 'memberLevel' and CODEVALUE='"
						+ zcSalesPromotion.getMemberLevel() + "'");
		String memberLevel = "";
		if (listMember != null && listMember.size() > 0) {
			Code member = listMember.get(0);
			memberLevel = member.getCodeName();
		}
		String promotionDays = zcSalesPromotion.getPromotionDays();
		String[] promotionDay ;
		String week = "";
		if (promotionDays!=null && !promotionDays.equals("")) {
			 promotionDay = promotionDays.split(",");
			 week="星期";
			 for (int i = 0; i < promotionDay.length; i++) {
					double num = Double.parseDouble(promotionDay[i]);
					if (num == 1) {
						week += "一,";
					}
					if (num == 2) {
						week += "二,";
					}
					if (num == 3) {
						week += "三,";
					}
					if (num == 4) {
						week += "四,";
					}
					if (num == 5) {
						week += "五,";
					}
					if (num == 6) {
						week += "六,";
					}
					if (num == 7) {
						week += "日,";
					}
				}
		}
		
		
		Date date = zcSalesPromotion.getPromotionBeginDate();
		Date date2 = zcSalesPromotion.getPromotionEndDate();
		Date date3 = zcSalesPromotion.getStopDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate="";
		String endDate = "";
		String stopDate ="";
		if (date!=null) {
			 beginDate = sdf.format(date);
		}
		if (date2!=null) {
			 endDate = sdf.format(date2);
		}
		if (date3!=null) {
			 stopDate = sdf.format(date3);
		}
		model.addAttribute("zcSalesPromotion", zcSalesPromotion);
		model.addAttribute("zcSalesPromotionId", id);
		model.addAttribute("branchTotal", branchTotal);
		model.addAttribute("zcCodeScope", zcCodeScope);
		model.addAttribute("zcCodeMode", zcCodeMode);
		model.addAttribute("zcCodeScopeCode", zcCodeScopeCode);
		model.addAttribute("zcCodeModeCode", zcCodeModeCode);
		model.addAttribute("memberLevel", memberLevel);
		model.addAttribute("week", week);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("stopDate", stopDate);
		ModelAndView view = createIframeView("promotion/Discount_check");
		return view;
	}
	
	// 审核不通过
		@RequestMapping(value = "checkNoPass", method = RequestMethod.POST, produces = "application/json")
		@ResponseBody
		public AjaxResult checkNoPass(
				@ModelAttribute ZcSalesPromotion zcSalesPromotion, String id,
				String type, String reason, HttpServletRequest request,
				HttpServletResponse response) {
			AjaxResult ajaxResult = null;
			ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
					"userInfo");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				zcSalesPromotion = (ZcSalesPromotion) discountService
						.getObjById(id, "ZcSalesPromotion");
				zcSalesPromotion.setCheckMan(user.getUserName());
				zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_NOPASS);
				zcSalesPromotion.setCheckDate(df.parse(df.format(new Date())));
				discountService.updateObj(zcSalesPromotion);
				logManageService.insertLog(request, "审核促销单不通过", "促销折扣");
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
//				String ids = request.getParameter("ids");
//				String allDiscounts = request.getParameter("allDiscount");
//				String fullBuyMoneys = request.getParameter("fullBuyMoney");
//				String fullBuyCounts = request.getParameter("fullBuyCount");
//				String discounts = request.getParameter("discount");
//				String[] allDiscount = allDiscounts.split(",");
//				String[] fullBuyMoney = fullBuyMoneys.split(",");
//				String[] fullBuyCount = fullBuyCounts.split(",");
//				String[] discount = discounts.split(",");
				ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
						"userInfo");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) discountService
						.getObjById(id, "ZcSalesPromotion");
					
					
				zcSalesPromotion.setCheckDate(new Date());
				zcSalesPromotion.setCheckMan(user.getUserName());
				zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_PASS);
				
				discountService.updateObj(zcSalesPromotion);
					logManageService.insertLog(request, "审核促销单通过", "促销折扣");
				
				ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
						AjaxResult.INFO);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
						AjaxResult.INFO);
			}
			return ajaxResult;
		}
}
