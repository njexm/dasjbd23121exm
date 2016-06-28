package com.proem.exm.controller.promotion;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.UuidUtils;
import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotionItem;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.service.promotion.SpecialPriceService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 特价
 * 
 * @author xuehr
 * 
 */
@Controller
@RequestMapping("specialPrice")
public class SpecialPriceController extends BaseController {

	@Autowired
	private SpecialPriceService specialPriceService;

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

	/**
	 * 初始化页面特价加载
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
			sonName = "特价";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("promotion/specialprice_list");
		return view;
	}

	/**
	 * 新增特价单
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<ZcSalesPromotionItem> zcSalesPromotionItemList = specialPriceService
				.getListByObj(ZcSalesPromotionItem.class,
						" SalesPromotion_id is null ");
		if (zcSalesPromotionItemList != null
				&& zcSalesPromotionItemList.size() > 0) {
			for (int i = 0; i < zcSalesPromotionItemList.size(); i++) {
				specialPriceService.deleteObjById(
						zcSalesPromotionItemList.get(i).getId(),
						ZcSalesPromotionItem.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("promotion/specialprice_add");
		return view;
	}

	/**
	 * 新增页面datagrid数据加载
	 * 
	 * @param promotion
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPromotionItemsNullJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionNullJson(
			@ModelAttribute ZcSalesPromotion promotion,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = specialPriceService.getPagedDataGridObj(promotion, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 删除切换促销模式之前新增的商品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteAddPromotions", produces = "application/json")
	@ResponseBody
	public AjaxResult deleteAddPromotions(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult result = null;
		try {
			List<ZcSalesPromotionItem> zcSalesPromotionItemList = specialPriceService
					.getListByObj(ZcSalesPromotionItem.class,
							" SalesPromotion_id is null ");
			if (zcSalesPromotionItemList != null
					&& zcSalesPromotionItemList.size() > 0) {
				for (int i = 0; i < zcSalesPromotionItemList.size(); i++) {
					specialPriceService.deleteObjById(zcSalesPromotionItemList
							.get(i).getId(), ZcSalesPromotionItem.class
							.getName());
				}
			}
		} catch (Exception ex) {
			result = new AjaxResult(AjaxResult.DELETE, AjaxResult.ERROR,
					AjaxResult.ERROR);
			return result;
		}
		result = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
				AjaxResult.INFO);
		return result;
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
		String zcSalesPromotionId = request.getParameter("zcSalesPromotionId");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		String type = request.getParameter("type");
		String ids = request.getParameter("ids");
		String groups = request.getParameter("groups");
		String numbers = request.getParameter("numbers");
		String prices = request.getParameter("prices");
		String nums = request.getParameter("nums");
		String fulls = request.getParameter("fulls");
		String begins = request.getParameter("begins");
		String ends = request.getParameter("ends");
		String[] idArray = null ;
		String[] groupArray =null;
		String[] numberArray =null;
		String[] priceArray =null;
		String[] numArray =null;
		String[] fullArray =null;
		String[] beginArray =null;
		String[] endArray =null;
		if (ids!=null && !ids.equals("")) {
			 idArray = ids.split(",");
		}
		if (groups!=null && !groups.equals("")) {
			groupArray = groups.split(",");
		}
		if (numbers!=null && !numbers.equals("")) {
			numberArray = numbers.split(",");
		}
		if (prices!=null && !prices.equals("")) {
			priceArray = prices.split(",");
		}
		if (nums!=null && !nums.equals("")) {
			numArray = nums.split(",");
		}
		if (fulls!=null && !fulls.equals("")) {
			fullArray = fulls.split(",");
		}
		if (begins!=null && !begins.equals("")) {
			beginArray = begins.split(",");
		}
		if (ends!=null && !ends.equals("")) {
			endArray = ends.split(",");
		}
		try {
			if (idArray!=null && idArray.length>0) {
				for (int i = 0; i < idArray.length; i++) {
					ZcSalesPromotionItem item = (ZcSalesPromotionItem)specialPriceService.getObjById(idArray[i], ZcSalesPromotionItem.class.getName());
					item.setBargainPrice(Double.valueOf(priceArray[i]));
					if ("1".equals(type)) {
						item.setLimitNumber(Double.valueOf(numberArray[i]));
					} else if ("2".equals(type)) {
						item.setFullBuyCount(Double.valueOf(fullArray[i]));
					} else if ("3".equals(type)) {
					} else if ("4".equals(type)) {
						item.setBeginTimeFrame(sdf2.parse(beginArray[i]));
						item.setEndTimeFrame(sdf2.parse(endArray[i]));
					} else if ("5".equals(type)) {
						item.setGroupNumber(groupArray[i]);
						item.setNums(numArray[i]);
					}
					specialPriceService.updateObj(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (zcSalesPromotionId != null && !zcSalesPromotionId.equals("")) {
			model.addAttribute("zcSalesPromotionId", zcSalesPromotionId);
		}
		ModelAndView view = createIframeView("promotion/specialprice_chooseGoods");
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
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = specialPriceService.getGoodsInfo(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 添加商品到促销单中
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String idsString = request.getParameter("ids");
		String zcSalesPromotionId = request.getParameter("zcSalesPromotionId");
		String[] ids = idsString.split(",");
		try {
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					String id = ids[i];
					List<ZcSalesPromotionItem> itemList = specialPriceService
							.getListByObj(ZcSalesPromotionItem.class,
									"salesPromotion_Id is null and goodsFile_id = '"
											+ id + "'");
					if (itemList != null && itemList.size() > 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						GoodsFile goodsFile = (GoodsFile) specialPriceService
								.getObjById(id, GoodsFile.class.getName());
						if (goodsFile != null) {
							ZcSalesPromotionItem obj = new ZcSalesPromotionItem();
							if (zcSalesPromotionId != null
									&& !zcSalesPromotionId.equals("") && !zcSalesPromotionId.equals("undefined")) {
								obj.setSalesPromotionId(zcSalesPromotionId);
							}
							obj.setGoodsFile(goodsFile);
							specialPriceService.saveObj(obj);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到特价促销单", "特价促销单");
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
	@RequestMapping(value = "deleteChoose", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteChoose(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
			String type = request.getParameter("type");
			String idstring = request.getParameter("idStr");
			String groups = request.getParameter("groups");
			String numbers = request.getParameter("numbers");
			String prices = request.getParameter("prices");
			String nums = request.getParameter("nums");
			String fulls = request.getParameter("fulls");
			String begins = request.getParameter("begins");
			String ends = request.getParameter("ends");
			String[] idArray = idstring.split(",");
			String[] groupArray = groups.split(",");
			String[] numberArray = numbers.split(",");
			String[] priceArray = prices.split(",");
			String[] numArray = nums.split(",");
			String[] fullArray = fulls.split(",");
			String[] beginArray = begins.split(",");
			String[] endArray = ends.split(",");
			if (idArray != null && idArray.length > 0) {
				for (int i = 0; i < idArray.length; i++) {
					String idStr = idArray[i];
					ZcSalesPromotionItem item = (ZcSalesPromotionItem) specialPriceService
							.getObjById(idStr,
									ZcSalesPromotionItem.class.getName());
					item.setBargainPrice(Double.valueOf(priceArray[i]));
					if ("1".equals(type)) {
						item.setLimitNumber(Double.valueOf(numberArray[i]));
					} else if ("2".equals(type)) {
						item.setFullBuyCount(Double.valueOf(fullArray[i]));
					} else if ("3".equals(type)) {
					} else if ("4".equals(type)) {
						item.setBeginTimeFrame(sdf2.parse(beginArray[i]));
						item.setEndTimeFrame(sdf2.parse(endArray[i]));
					} else if ("5".equals(type)) {
						item.setGroupNumber(groupArray[i]);
						item.setNums(numArray[i]);
					}
					specialPriceService.updateObj(item);
				}
			}
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				specialPriceService.deleteObjById(ids[i],
						ZcSalesPromotionItem.class.getName());
			}
			logManageService.insertLog(request, "移除了特价促销单商品", "特价促销单");
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
	 * 新增特价单
	 * 
	 * @param promotion
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createItems(@ModelAttribute ZcSalesPromotion promotion,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		String ids = request.getParameter("ids");
		String groups = request.getParameter("groups");
		String numbers = request.getParameter("numbers");
		String prices = request.getParameter("prices");
		String nums = request.getParameter("nums");
		String fulls = request.getParameter("fulls");
		String begins = request.getParameter("begins");
		String ends = request.getParameter("ends");
		String branchs = request.getParameter("branchs");
		String chkValues = request.getParameter("chkValue");
		String[] idArray = ids.split(",");
		String[] groupArray = groups.split(",");
		String[] numberArray = numbers.split(",");
		String[] priceArray = prices.split(",");
		String[] numArray = nums.split(",");
		String[] fullArray = fulls.split(",");
		String[] beginArray = begins.split(",");
		String[] endArray = ends.split(",");
		// String[] branchArray = branchs.split("\\|");
		promotion.setId(UuidUtils.getUUID());
		promotion.setPromotionDays(chkValues);
		// List<BranchTotal> list = new ArrayList<BranchTotal>();
		// for (int i = 0; i < branchArray.length; i++) {
		// BranchTotal branchtotal =(BranchTotal)
		// specialPriceService.getObjByCondition(BranchTotal.class,
		// "branch_Code ='"+branchArray[i]+"'");
		// if (branchtotal!=null) {
		// list.add(branchtotal);
		// }
		// }
		// promotion.setBranchTotalList(list);
		promotion.setBranchs(branchs);
		Code scope = (Code) specialPriceService.getObjByCondition(Code.class,
				"CODETYPE = 'SaleScope' and CODEVALUE='4'");
		// 只有商品一种模式
		promotion.setZcCodeScope(scope);
		Code mode = (Code) specialPriceService.getObjByCondition(Code.class,
				"codeType = 'SpecialPriceType' and codevalue= '"
						+ promotion.getZcCodeMode().getId() + "'");
		promotion.setZcCodeMode(mode);

		try {
			if (idArray != null && idArray.length > 0) {
				for (int i = 0; i < idArray.length; i++) {
					String id = idArray[i];
					ZcSalesPromotionItem item = (ZcSalesPromotionItem) specialPriceService
							.getObjById(id,
									ZcSalesPromotionItem.class.getName());
					item.setBargainPrice(Double.valueOf(priceArray[i]));
					item.setSalesPromotionId(promotion.getId());
					if ("1".equals(mode.getCodeValue())) {
						item.setLimitNumber(Double.valueOf(numberArray[i]));
					} else if ("2".equals(mode.getCodeValue())) {
						item.setFullBuyCount(Double.valueOf(fullArray[i]));
					} else if ("3".equals(mode.getCodeValue())) {
					} else if ("4".equals(mode.getCodeValue())) {
						item.setBeginTimeFrame(sdf2.parse(beginArray[i]));
						item.setEndTimeFrame(sdf2.parse(endArray[i]));
					} else if ("5".equals(mode.getCodeValue())) {
						item.setGroupNumber(groupArray[i]);
						item.setNums(numArray[i]);
					}
					specialPriceService.updateObj(item);
				}

				specialPriceService.saveObj(promotion);
				logManageService.insertLog(request, "新增特价促销单", "特价促销单");
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
	 * list页面查询
	 * 
	 * @param zcSalesPromotion
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listZcSalesPromotionJson", produces = "application/json")
	@ResponseBody
	public DataGrid listZcSalesPromotionJson(
			@ModelAttribute ZcSalesPromotion zcSalesPromotion,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			ZcUserInfo zcUserInfo = null ;
			 List<ZcUserInfo> zcUserInfoList = specialPriceService.getListByObj(ZcUserInfo.class, " USER_ID ='"+zcSalesPromotion.getCreateMan()+"'");
			if (zcUserInfoList!=null && zcUserInfoList.size()>0) {
				zcUserInfo = zcUserInfoList.get(0);
				if (zcUserInfo!=null && !zcUserInfo.equals("")) {
					zcSalesPromotion.setCreateMan(zcUserInfo.getUserName());
				}
			}
			dataGrid = specialPriceService.getPagedDataGridObj(page,
					zcSalesPromotion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "openDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		ZcSalesPromotion promotion = (ZcSalesPromotion) specialPriceService
				.getObjById(id, ZcSalesPromotion.class.getName());
		List<Code> listMember = specialPriceService.getListByObj(
				Code.class,
				"CODETYPE = 'memberLevel' and CODEVALUE='"
						+ promotion.getMemberLevel() + "'");
		String memberLevel = "";
		if (listMember != null && listMember.size() > 0) {
			Code member = listMember.get(0);
			memberLevel = member.getCodeName();
		}
		String promotionDays = promotion.getPromotionDays();
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
		model.addAttribute("promotion", promotion);
		model.addAttribute("week", week);
		model.addAttribute("memberLevel", memberLevel);
		ModelAndView view = createIframeView("promotion/specialprice_detail");
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
	@RequestMapping("gotoEditspecialPriceEdit")
	public ModelAndView gotoEditspecialPriceEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) specialPriceService
				.getObjById(id, "ZcSalesPromotion");
		// List<BranchTotal> branchTotalList = zcSalesPromotion
		// .getBranchTotalList();
		String branchTotal = "";
		// for (int i = 0; i < branchTotalList.size(); i++) {
		// branchTotal += branchTotalList.get(i).getBranch_code() + "|";
		// }
		branchTotal = zcSalesPromotion.getBranchs();
		model.addAttribute("promotion", zcSalesPromotion);
		model.addAttribute("id", id);
		model.addAttribute("branchTotal", branchTotal);
		ModelAndView view = createIframeView("promotion/specialprice_edit");
		return view;
	}

	/**
	 * 编辑页面datagrid数据加载
	 * 
	 * @param promotion
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listPromotionItemsEidtJson", produces = "application/json")
	@ResponseBody
	public DataGrid listPromotionItemsEidtJson(
			@ModelAttribute ZcSalesPromotion promotion,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			ZcSalesPromotion zcSalesPromotionNew = new ZcSalesPromotion();
			if (promotion != null) {
				String ids = promotion.getId();
				zcSalesPromotionNew.setId(ids);
				List<Code> listMode = specialPriceService.getListByObj(
						Code.class,
						"CODETYPE = 'SpecialPriceType' and CODEVALUE='"
								+ promotion.getZcCodeMode().getCodeValue()
								+ "'");
				if (listMode != null && listMode.size() > 0) {
					Code zcCodeMode = listMode.get(0);
					zcSalesPromotionNew.setZcCodeMode(zcCodeMode);
				}
			}
			dataGrid = specialPriceService.getPagedDataGridEidtObj(
					zcSalesPromotionNew, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 编辑特价单
	 * 
	 * @param promotion
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "eidtItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult editItems(@ModelAttribute ZcSalesPromotion promotion,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		String ids = request.getParameter("ids");
		String groups = request.getParameter("groups");
		String numbers = request.getParameter("numbers");
		String prices = request.getParameter("prices");
		String nums = request.getParameter("nums");
		String fulls = request.getParameter("fulls");
		String begins = request.getParameter("begins");
		String ends = request.getParameter("ends");
		String branchs = request.getParameter("branchs");
		String chkValues = request.getParameter("chkValue");
		String[] idArray = ids.split(",");
		String[] groupArray = groups.split(",");
		String[] numberArray = numbers.split(",");
		String[] priceArray = prices.split(",");
		String[] numArray = nums.split(",");
		String[] fullArray = fulls.split(",");
		String[] beginArray = begins.split(",");
		String[] endArray = ends.split(",");
		// String[] branchArray = branchs.split("\\|");
		ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) specialPriceService
				.getObjById(promotion.getId(), ZcSalesPromotion.class.getName());
		if (chkValues!=null && !chkValues.equals("")) {
			zcSalesPromotion.setPromotionDays(chkValues);
		}
		
		// List<BranchTotal> list = new ArrayList<BranchTotal>();
		// for (int i = 0; i < branchArray.length; i++) {
		// BranchTotal branchtotal =(BranchTotal)
		// specialPriceService.getObjByCondition(BranchTotal.class,
		// "branch_Code ='"+branchArray[i]+"'");
		// if (branchtotal!=null) {
		// list.add(branchtotal);
		// }
		// }
		// promotion.setBranchTotalList(list);
		zcSalesPromotion.setBranchs(branchs);
		Code scope = (Code) specialPriceService.getObjByCondition(Code.class,
				"CODETYPE = 'SaleScope' and CODEVALUE='4'");
		// 只有商品一种模式
		zcSalesPromotion.setZcCodeScope(scope);
		Code mode = (Code) specialPriceService.getObjByCondition(Code.class,
				"codeType = 'SpecialPriceType' and codevalue= '"
						+ promotion.getZcCodeMode().getCodeValue() + "'");
		zcSalesPromotion.setZcCodeMode(mode);

		zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_WAITCHECK);
		if (promotion.getMemberLevel() != null
				&& !promotion.getMemberLevel().equals("")) {
			zcSalesPromotion.setMemberLevel(promotion.getMemberLevel());
		}
		if (promotion.getPromotionBeginDate() != null
				&& !promotion.getPromotionBeginDate().equals("")) {
			zcSalesPromotion.setPromotionBeginDate(promotion
					.getPromotionBeginDate());
		}
		if (promotion.getPromotionEndDate() != null
				&& !promotion.getPromotionEndDate().equals("")) {
			zcSalesPromotion.setPromotionEndDate(promotion
					.getPromotionEndDate());
		}
		if (promotion.getPromotionRemark() != null
				&& !promotion.getPromotionRemark().equals("")) {
			zcSalesPromotion.setPromotionRemark(promotion.getPromotionRemark());
		}
		if (promotion.getPromotionTitle() != null
				&& !promotion.getPromotionTitle().equals("")) {
			zcSalesPromotion.setPromotionTitle(promotion.getPromotionTitle());
		}
			try {
				if (idArray != null && idArray.length > 0) {
					for (int i = 0; i < idArray.length; i++) {
						String id = idArray[i];
						ZcSalesPromotionItem item = (ZcSalesPromotionItem) specialPriceService
								.getObjById(id,
										ZcSalesPromotionItem.class.getName());
						item.setBargainPrice(Double.valueOf(priceArray[i]));
						item.setSalesPromotionId(promotion.getId());
						if ("1".equals(mode.getCodeValue())) {
							item.setLimitNumber(Double.valueOf(numberArray[i]));
						} else if ("2".equals(mode.getCodeValue())) {
							item.setFullBuyCount(Double.valueOf(fullArray[i]));
						} else if ("3".equals(mode.getCodeValue())) {
						} else if ("4".equals(mode.getCodeValue())) {
							item.setBeginTimeFrame(sdf2.parse(beginArray[i]));
							item.setEndTimeFrame(sdf2.parse(endArray[i]));
						} else if ("5".equals(mode.getCodeValue())) {
							item.setGroupNumber(groupArray[i]);
							item.setNums(numArray[i]);
						}
						specialPriceService.updateObj(item);
					}

					specialPriceService.updateObj(zcSalesPromotion);
					logManageService.insertLog(request, "新增特价促销单", "特价促销单");
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.SUCCESS, AjaxResult.INFO);
					return ajaxResult;
				} else {
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}
			} catch (Exception e) {
				e.printStackTrace();
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
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
					
					List<ZcSalesPromotionItem> zcSalesPromotionItemList = specialPriceService
							.getListByObj(ZcSalesPromotionItem.class,
									"SalesPromotion_ID='" + ids[i] + "'");
					if (zcSalesPromotionItemList != null
							&& zcSalesPromotionItemList.size() > 0) {
						for (int j = 0; j < zcSalesPromotionItemList.size(); j++) {
							ZcSalesPromotionItem zcSalesPromotionItem = zcSalesPromotionItemList
									.get(j);
							specialPriceService.deleteObjById(
									zcSalesPromotionItem.getId(),
									ZcSalesPromotionItem.class.getName());
						}
					}
					
					specialPriceService.deleteObjById(ids[i],
							ZcSalesPromotion.class.getName());
				}
				logManageService.insertLog(request, "删除勾选中的促销特价单", "促销特价单");
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
			ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) specialPriceService
					.getObjById(id, "ZcSalesPromotion");
//			List<BranchTotal> branchTotalList = zcSalesPromotion
//					.getBranchTotalList();
			String branchTotal = "";
//			for (int i = 0; i < branchTotalList.size(); i++) {
//				branchTotal += branchTotalList.get(i).getBranch_code() + "|";
//			}
			branchTotal = zcSalesPromotion.getBranchs();
			String zcCodeScope = "";
			String zcCodeScopeCode = "";
			Code codeScope = (Code) specialPriceService.getObjById(zcSalesPromotion
					.getZcCodeScope().getId(), "Code");
			if (codeScope != null) {
				zcCodeScope = codeScope.getCodeName();
				zcCodeScopeCode = codeScope.getCodeValue();
			}
			String zcCodeMode = "";
			String zcCodeModeCode = "";
			Code codeMode = (Code) specialPriceService.getObjById(zcSalesPromotion
					.getZcCodeMode().getId(), "Code");
			if (codeMode != null) {
				zcCodeMode = codeMode.getCodeName();
				zcCodeModeCode = codeMode.getCodeValue();
			}
			List<Code> listMember = specialPriceService.getListByObj(
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
			ModelAndView view = createIframeView("promotion/specialprice_check");
			return view;
		}
		
		
		// 审核通过
				@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
				@ResponseBody
				public AjaxResult checkPass(HttpServletRequest request,
						HttpServletResponse response) {
					AjaxResult ajaxResult = null;
					try {
						String id = request.getParameter("id");
//						
						ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
								"userInfo");
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) specialPriceService
								.getObjById(id, "ZcSalesPromotion");
							
							
						zcSalesPromotion.setCheckDate(new Date());
						zcSalesPromotion.setCheckMan(user.getUserName());
						zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_PASS);
						
						specialPriceService.updateObj(zcSalesPromotion);
							logManageService.insertLog(request, "审核促销单通过", "促销特价");
						
						ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
								AjaxResult.INFO);
					} catch (Exception e) {
						e.printStackTrace();
						ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
								AjaxResult.INFO);
					}
					return ajaxResult;
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
						zcSalesPromotion = (ZcSalesPromotion) specialPriceService
								.getObjById(id, "ZcSalesPromotion");
						zcSalesPromotion.setCheckMan(user.getUserName());
						zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_NOPASS);
						zcSalesPromotion.setCheckDate(df.parse(df.format(new Date())));
						specialPriceService.updateObj(zcSalesPromotion);
						logManageService.insertLog(request, "审核促销单不通过", "促销特价");
						ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
								AjaxResult.INFO);
					} catch (Exception e) {
						e.printStackTrace();
						ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
								AjaxResult.INFO);
					}
					return ajaxResult;
				}
				
				// 终止
				@RequestMapping(value = "stop", method = RequestMethod.POST, produces = "application/json")
				@ResponseBody
				public AjaxResult stop(HttpServletRequest request,
						HttpServletResponse response, String id) {
					AjaxResult ajaxResult = null;
					ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
							"userInfo");
					try {
						String[] ids = id.split(",");
						for (int i = 0; i < ids.length; i++) {
							ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion)specialPriceService.getObjById(ids[i], ZcSalesPromotion.class.getName());
							zcSalesPromotion.setStopDate(new Date());
							zcSalesPromotion.setStopMan(user.getUserName());
							zcSalesPromotion.setCheckState(Constant.CHECK_STATUS_FINISH);
							specialPriceService.updateObj(zcSalesPromotion);
						}
						logManageService.insertLog(request, "终止勾选中的促销特价单", "促销特价单");
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
