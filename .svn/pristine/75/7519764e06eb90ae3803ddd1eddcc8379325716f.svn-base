package com.proem.exm.controller.basic.adjustment;

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
import com.proem.exm.entity.basic.adjustment.AdjustmentDetail;
import com.proem.exm.entity.basic.adjustment.Adjustments;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.basic.adjustment.AdjustmentService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 商品调价单
 * 
 * @author ZuoYM
 * 
 */
@Controller
@RequestMapping("adjustment/adjustment")
public class adjustmentController extends BaseController {
	@Autowired
	private AdjustmentService adjustmentService;
	@Autowired
	private GoodsFileService goodsFileService;

	@InitBinder("adjustmentdetail")
	public void initAdjustmentDetail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("adjustmentdetail.");
	}

	@InitBinder("adjustments")
	public void initAdjustments(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("adjustments.");
	}

	@InitBinder("goodsFile")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	// 初始化跳转页面
	@RequestMapping(value = "init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "基础档案";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "档案管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品调教";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/adjustments/adjustment_list");
		return view;
	}

	// 打开新增调价单页面
	@RequestMapping(value = "addAdjustment")
	public ModelAndView addAdjustment(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<AdjustmentDetail> adjustmentDetailList = adjustmentService
				.getListByObj(AdjustmentDetail.class,
						"ZC_ADJUSTMENTS_ID IS NULL ");
		if (adjustmentDetailList != null && adjustmentDetailList.size() > 0) {
			for (int i = 0; i < adjustmentDetailList.size(); i++) {
				AdjustmentDetail adjustmentDetail = adjustmentDetailList.get(i);
				adjustmentService.deleteObjById(adjustmentDetail.getId(),
						AdjustmentDetail.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("basic/adjustments/adjustment_add");
		return view;
	}

	// 打开编辑调价单页面
	@RequestMapping("gotoEditAdjustment")
	public ModelAndView gotoEditAdjustment(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		System.out.println("id:---:" + id);
		Adjustments adjustments = (Adjustments) adjustmentService.getObjById(
				id, "Adjustments");
		model.addAttribute("adjustments", adjustments);
		ModelAndView view = createIframeView("basic/adjustments/adjustment_edit");
		return view;
	}

	// 打开查看调价单页面
	@RequestMapping("gotoAdjustmentsDetail")
	public ModelAndView gotoAdjustmentsDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID获取调价单对象
		Adjustments adjustments = (Adjustments) adjustmentService.getObjById(
				id, "Adjustments");
		model.addAttribute("adjustments", adjustments);
		ModelAndView view = createIframeView("basic/adjustments/adjustment_detail");
		return view;
	}

	// 打开审核调价单页面
	@RequestMapping("gotoEditCheck")
	public ModelAndView gotoEditCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Adjustments adjustments = (Adjustments) adjustmentService.getObjById(
				id, "Adjustments");
		model.addAttribute("adjustments", adjustments);
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("basic/adjustments/adjustment_check");
		return view;
	}

	/**
	 * 分页查询-调价单
	 * 
	 * @param adjustment
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsAdjustment", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsAdjustment(@ModelAttribute Adjustments adjustment,
			String audflag, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = adjustmentService.getPagedDataGridObjList(page,
					adjustment,audflag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询-调价单数据
	 * 
	 * @param adjustmentDetail
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsAdjustmentDetail", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsAdjustmentDetail(
			@ModelAttribute AdjustmentDetail adjustmentDetail,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = adjustmentService.getPagedDataGridObj(page,
					adjustmentDetail);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 列表展示调价单
	 * 
	 * @param adjustmentDetail
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsAdjustmentCheck", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsAdjustmentCheck(
			@ModelAttribute AdjustmentDetail adjustmentDetail,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = adjustmentService.getPagedDataDetailGridObj(page,
					adjustmentDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 在调价单中添加一条商品/从商品表中获取原金额数据
	 * 
	 * @param adjustment
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addAdj", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addAdj(@ModelAttribute AdjustmentDetail adjustment,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					GoodsFile goods = (GoodsFile) goodsFileService.getObjById(
							idStr[i], "GoodsFile");
					String serial = goods.getSerialNumber();
					List<AdjustmentDetail> adjustmentDetailsList = goodsFileService
							.getListByObj(AdjustmentDetail.class,
									"serialNumber='" + serial + "'");
					if (adjustmentDetailsList != null
							&& adjustmentDetailsList.size() > 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						AdjustmentDetail adjust = new AdjustmentDetail();
						String id = UuidUtils.getUUID();
						adjust.setId(id);
						adjust.setSerialNumber(goods.getSerialNumber());
						adjust.setGoods_name(goods.getGoods_name());
						adjust.setGoods_unit(goods.getGoods_unit());
						adjust.setGoods_specifications(goods
								.getGoods_specifications());

						if ("null".equals(goods.getLowest_price())
								|| goods.getLowest_price() != null) {
							adjust.setLowest_price(goods.getLowest_price()
									.toString());
							adjust.setLowest_price_2(goods.getLowest_price()
									.toString());
						} else {
							adjust.setLowest_price("0");
							adjust.setLowest_price_2("0");
						}
						if ("null".equals(goods.getGoods_purchase_price())
								|| goods.getGoods_purchase_price() != null) {
							adjust.setGoods_purchase_price(goods
									.getGoods_purchase_price().toString());
							adjust.setGoods_purchase_price_2(goods
									.getGoods_purchase_price().toString());
						} else {
							adjust.setGoods_purchase_price("0");
							adjust.setGoods_purchase_price_2("0");
						}
						if ("null".equals(goods.getWholesale_price())
								|| goods.getWholesale_price() != null) {
							adjust.setWholesale_price(goods
									.getWholesale_price().toString());
							adjust.setWholesale_price_2(goods
									.getWholesale_price().toString());
						} else {
							adjust.setWholesale_price("0");
							adjust.setWholesale_price_2("0");
						}
						if ("null".equals(goods.getWholesale_price2())
								|| goods.getWholesale_price2() != null) {
							adjust.setWholesale_price2(goods
									.getWholesale_price2().toString());
							adjust.setWholesale_price2_2(goods
									.getWholesale_price2().toString());
						} else {
							adjust.setWholesale_price2("0");
							adjust.setWholesale_price2_2("0");
						}
						if ("null".equals(goods.getWholesale_price3())
								|| goods.getWholesale_price3() != null) {
							adjust.setWholesale_price3(goods
									.getWholesale_price3().toString());
							adjust.setWholesale_price3_2(goods
									.getWholesale_price3().toString());
						} else {
							adjust.setWholesale_price3("0");
							adjust.setWholesale_price3_2("0");
						}
						if ("null".equals(goods.getWholesale_price4())
								|| goods.getWholesale_price4() != null) {
							adjust.setWholesale_price4(goods
									.getWholesale_price4().toString());
							adjust.setWholesale_price4_2(goods
									.getWholesale_price4().toString());
						} else {
							adjust.setWholesale_price4("0");
							adjust.setWholesale_price4_2("0");
						}
						if ("null".equals(goods.getWholesale_price5())
								|| goods.getWholesale_price5() != null) {
							adjust.setWholesale_price5(goods
									.getWholesale_price5().toString());
							adjust.setWholesale_price5_2(goods
									.getWholesale_price5().toString());
						} else {
							adjust.setWholesale_price5("0");
							adjust.setWholesale_price5_2("0");
						}
						if ("null".equals(goods.getWholesale_price6())
								|| goods.getWholesale_price6() != null) {
							adjust.setWholesale_price6(goods
									.getWholesale_price6().toString());
							adjust.setWholesale_price6_2(goods
									.getWholesale_price6().toString());
						} else {
							adjust.setWholesale_price6("0");
							adjust.setWholesale_price6_2("0");
						}
						if ("null".equals(goods.getWholesale_price7())
								|| goods.getWholesale_price7() != null) {
							adjust.setWholesale_price7(goods
									.getWholesale_price7().toString());
							adjust.setWholesale_price7_2(goods
									.getWholesale_price7().toString());
						} else {
							adjust.setWholesale_price7("0");
							adjust.setWholesale_price7_2("0");
						}
						if ("null".equals(goods.getWholesale_price8())
								|| goods.getWholesale_price8() != null) {
							adjust.setWholesale_price8(goods
									.getWholesale_price8().toString());
							adjust.setWholesale_price8_2(goods
									.getWholesale_price8().toString());
						} else {
							adjust.setWholesale_price8("0");
							adjust.setWholesale_price8_2("0");
						}
						if ("null".equals(goods.getGoods_price())
								|| goods.getGoods_price() != null) {
							adjust.setGoods_price(goods.getGoods_price()
									.toString());
							adjust.setGoods_price_2(goods.getGoods_price()
									.toString());
						} else {
							adjust.setGoods_price("0");
							adjust.setGoods_price_2("0");
						}
						System.out
								.println("goods.getDistribution_price()____________"
										+ goods.getDistribution_price());
						if ("null".equals(goods.getDistribution_price())
								|| goods.getDistribution_price() != null) {
							adjust.setDistribution_price(goods
									.getDistribution_price().toString());
							adjust.setDistribution_price_2(goods
									.getDistribution_price().toString());
						} else {
							adjust.setDistribution_price("0");
							adjust.setDistribution_price_2("0");
						}
						if ("null".equals(goods.getMember_price())
								|| goods.getMember_price() != null) {
							adjust.setMember_price(goods.getMember_price()
									.toString());
							adjust.setMember_price_2(goods.getMember_price()
									.toString());
						} else {
							adjust.setMember_price("0");
							adjust.setMember_price_2("0");
						}
						if ("null".equals(goods.getMember_price2())
								|| goods.getMember_price2() != null) {
							adjust.setMember_price2(goods.getMember_price2()
									.toString());
							adjust.setMember_price2_2(goods.getMember_price2()
									.toString());
						} else {
							adjust.setMember_price2("0");
							adjust.setMember_price2_2("0");
						}
						if ("null".equals(goods.getMember_price3())
								|| goods.getMember_price3() != null) {
							adjust.setMember_price3(goods.getMember_price3()
									.toString());
							adjust.setMember_price3_2(goods.getMember_price3()
									.toString());
						} else {
							adjust.setMember_price3("0");
							adjust.setMember_price3_2("0");
						}
						if ("null".equals(goods.getMember_price4())
								|| goods.getMember_price4() != null) {
							adjust.setMember_price4(goods.getMember_price4()
									.toString());
							adjust.setMember_price4_2(goods.getMember_price4()
									.toString());
						} else {
							adjust.setMember_price4("0");
							adjust.setMember_price4_2("0");
						}
						if ("null".equals(goods.getMember_price5())
								|| goods.getMember_price5() != null) {
							adjust.setMember_price5(goods.getMember_price5()
									.toString());
							adjust.setMember_price5_2(goods.getMember_price5()
									.toString());
						} else {
							adjust.setMember_price5("0");
							adjust.setMember_price5_2("0");
						}
						adjust.setRemark("无备注");
						adjustmentService.saveObj(adjust);
					}
				}
			}
			logManageService.insertLog(request, "新增了调价记录", "商品调价");
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
	 * 在调价单中删除选中的数据
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteAdj", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteAdj(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				adjustmentService.deleteObjById(ids[i],
						AdjustmentDetail.class.getName());
			}
			logManageService.insertLog(request, "删除了一条调价记录", "商品调价");
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
	 * 下拉框中获取商品信息并展示商品名
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "ListJson_name", produces = "application/json")
	@ResponseBody
	public List ListJson_name(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = adjustmentService.getObjList_name(goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 将表格中填充的商品信息更新至数据库
	 * 
	 * @param adjustment
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateAdj", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateAdj(@ModelAttribute AdjustmentDetail adjustment,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String serialnumbers = request.getParameter("serialnumber");
		String lowest_price_2s = request.getParameter("lowest_price_2");
		String goods_purchase_price_2s = request
				.getParameter("goods_purchase_price_2");//
		String wholesale_price_2s = request.getParameter("wholesale_price_2");
		String goods_price_2s = request.getParameter("goods_price_2");
		String distribution_price_2s = request
				.getParameter("distribution_price_2");
		String member_price_2s = request.getParameter("member_price_2");
		String wholesale_price2_2s = request.getParameter("wholesale_price2_2");
		String wholesale_price3_2s = request.getParameter("wholesale_price3_2");
		String wholesale_price4_2s = request.getParameter("wholesale_price4_2");
		String wholesale_price5_2s = request.getParameter("wholesale_price5_2");
		String wholesale_price6_2s = request.getParameter("wholesale_price6_2");
		String wholesale_price7_2s = request.getParameter("wholesale_price7_2");
		String wholesale_price8_2s = request.getParameter("wholesale_price8_2");
		String member_price2_2s = request.getParameter("member_price2_2");
		String member_price3_2s = request.getParameter("member_price3_2");
		String member_price4_2s = request.getParameter("member_price4_2");
		String member_price5_2s = request.getParameter("member_price5_2");
		String remarks = request.getParameter("remark");
		String ids = request.getParameter("ids");
		String[] serialnumber = serialnumbers.split(",");
		String[] lowest_price_2 = lowest_price_2s.split(",");
		String[] goods_purchase_price_2 = goods_purchase_price_2s.split(",");
		String[] wholesale_price_2 = wholesale_price_2s.split(",");
		String[] goods_price_2 = goods_price_2s.split(",");
		String[] distribution_price_2 = distribution_price_2s.split(",");
		String[] member_price_2 = member_price_2s.split(",");
		String[] wholesale_price2_2 = wholesale_price2_2s.split(",");
		String[] wholesale_price3_2 = wholesale_price3_2s.split(",");
		String[] wholesale_price4_2 = wholesale_price4_2s.split(",");
		String[] wholesale_price5_2 = wholesale_price5_2s.split(",");
		String[] wholesale_price6_2 = wholesale_price6_2s.split(",");
		String[] wholesale_price7_2 = wholesale_price7_2s.split(",");
		String[] wholesale_price8_2 = wholesale_price8_2s.split(",");
		String[] member_price2_2 = member_price2_2s.split(",");
		String[] member_price3_2 = member_price3_2s.split(",");
		String[] member_price4_2 = member_price4_2s.split(",");
		String[] member_price5_2 = member_price5_2s.split(",");
		String[] remark = remarks.split(",");
		String[] idStr = ids.split(",");
		try {
			for (int i = 0; i < idStr.length; i++) {
				AdjustmentDetail adjustment_2 = (AdjustmentDetail) adjustmentService
						.getObjById(idStr[i], "AdjustmentDetail");
				adjustment_2.setSerialNumber(serialnumber[i]);
				adjustment_2.setLowest_price_2(lowest_price_2[i]);
				adjustment_2
						.setGoods_purchase_price_2(goods_purchase_price_2[i]);
				adjustment_2.setWholesale_price_2(wholesale_price_2[i]);
				adjustment_2.setGoods_price_2(goods_price_2[i]);
				adjustment_2.setDistribution_price_2(distribution_price_2[i]);
				adjustment_2.setMember_price_2(member_price_2[i]);
				adjustment_2.setWholesale_price2_2(wholesale_price2_2[i]);
				adjustment_2.setWholesale_price3_2(wholesale_price3_2[i]);
				adjustment_2.setWholesale_price4_2(wholesale_price4_2[i]);
				adjustment_2.setWholesale_price5_2(wholesale_price5_2[i]);
				adjustment_2.setWholesale_price6_2(wholesale_price6_2[i]);
				adjustment_2.setWholesale_price7_2(wholesale_price7_2[i]);
				adjustment_2.setWholesale_price8_2(wholesale_price8_2[i]);
				adjustment_2.setMember_price2_2(member_price2_2[i]);
				adjustment_2.setMember_price3_2(member_price3_2[i]);
				adjustment_2.setMember_price4_2(member_price4_2[i]);
				adjustment_2.setMember_price5_2(member_price5_2[i]);
				adjustment_2.setRemark(remark[i]);
				adjustmentService.updateObj(adjustment_2);
			}
			logManageService.insertLog(request, "保存了调价单数据", "商品调价");
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
	 * 新增调价单
	 * 
	 * @param adjustments
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveAdjustments", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveAdjustments(@ModelAttribute Adjustments adjustments,
			String id, HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		// CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		// System.out.println("user:"+user);
		try {
			id = UuidUtils.getUUID();
			adjustments.setId(id);
			adjustments.setAudflag(Constant.CHECK_STATUS_UNDO);
			// adjustments.setMaker(user);
			adjustmentService.saveObj(adjustments);
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					AdjustmentDetail adjustmentDetail = (AdjustmentDetail) adjustmentService
							.getObjById(idStr[i], "AdjustmentDetail");
					adjustmentDetail.setAdjustments(adjustments);
					adjustmentService.updateObj(adjustmentDetail);
				}
			}
			logManageService.insertLog(request, "新增一条调价单", "商品调价");
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
	 * 删除调价单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteAdjustment", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	// @ModelAttribute Adjustments adjustments, adjustmentService
	public AjaxResult deleteAdjustment(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<AdjustmentDetail> adjustmentDetailList = adjustmentService
						.getListByObj(AdjustmentDetail.class,
								" ZC_ADJUSTMENTS_ID = '" + ids[i] + "'");
				if (adjustmentDetailList != null
						&& adjustmentDetailList.size() > 0) {
					for (int j = 0; j < adjustmentDetailList.size(); j++) {
						AdjustmentDetail adjustmentDetail = adjustmentDetailList
								.get(j);
						adjustmentService.deleteObjById(
								adjustmentDetail.getId(),
								AdjustmentDetail.class.getName());
					}
				}
				adjustmentService.deleteObjById(ids[i],
						Adjustments.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的调价单", "商品调价");
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
	 * 修改并提交调价单
	 * 
	 * @param adjustment
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateAdjustments", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateAdjustments(
			@ModelAttribute AdjustmentDetail adjustment,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String serialnumbers = request.getParameter("serialnumber");
		String lowest_price_2s = request.getParameter("lowest_price_2");
		String goods_purchase_price_2s = request
				.getParameter("goods_purchase_price_2");//
		String wholesale_price_2s = request.getParameter("wholesale_price_2");
		String goods_price_2s = request.getParameter("goods_price_2");
		String distribution_price_2s = request
				.getParameter("distribution_price_2");
		String member_price_2s = request.getParameter("member_price_2");
		String wholesale_price2_2s = request.getParameter("wholesale_price2_2");
		String wholesale_price3_2s = request.getParameter("wholesale_price3_2");
		String wholesale_price4_2s = request.getParameter("wholesale_price4_2");
		String wholesale_price5_2s = request.getParameter("wholesale_price5_2");
		String wholesale_price6_2s = request.getParameter("wholesale_price6_2");
		String wholesale_price7_2s = request.getParameter("wholesale_price7_2");
		String wholesale_price8_2s = request.getParameter("wholesale_price8_2");
		String member_price2_2s = request.getParameter("member_price2_2");
		String member_price3_2s = request.getParameter("member_price3_2");
		String member_price4_2s = request.getParameter("member_price4_2");
		String member_price5_2s = request.getParameter("member_price5_2");
		String remarks = request.getParameter("remark");
		String ids = request.getParameter("ids");
		String[] serialnumber = serialnumbers.split(",");
		String[] lowest_price_2 = lowest_price_2s.split(",");
		String[] goods_purchase_price_2 = goods_purchase_price_2s.split(",");
		String[] wholesale_price_2 = wholesale_price_2s.split(",");
		String[] goods_price_2 = goods_price_2s.split(",");
		String[] distribution_price_2 = distribution_price_2s.split(",");
		String[] member_price_2 = member_price_2s.split(",");
		String[] wholesale_price2_2 = wholesale_price2_2s.split(",");
		String[] wholesale_price3_2 = wholesale_price3_2s.split(",");
		String[] wholesale_price4_2 = wholesale_price4_2s.split(",");
		String[] wholesale_price5_2 = wholesale_price5_2s.split(",");
		String[] wholesale_price6_2 = wholesale_price6_2s.split(",");
		String[] wholesale_price7_2 = wholesale_price7_2s.split(",");
		String[] wholesale_price8_2 = wholesale_price8_2s.split(",");
		String[] member_price2_2 = member_price2_2s.split(",");
		String[] member_price3_2 = member_price3_2s.split(",");
		String[] member_price4_2 = member_price4_2s.split(",");
		String[] member_price5_2 = member_price5_2s.split(",");
		String[] remark = remarks.split(",");
		String[] idStr = ids.split(",");
		try {
			for (int i = 0; i < idStr.length; i++) {
				AdjustmentDetail adjustment_2 = (AdjustmentDetail) adjustmentService
						.getObjById(idStr[i], "AdjustmentDetail");
				adjustment_2.setSerialNumber(serialnumber[i]);
				adjustment_2.setLowest_price_2(lowest_price_2[i]);
				adjustment_2
						.setGoods_purchase_price_2(goods_purchase_price_2[i]);
				adjustment_2.setWholesale_price_2(wholesale_price_2[i]);
				adjustment_2.setGoods_price_2(goods_price_2[i]);
				adjustment_2.setDistribution_price_2(distribution_price_2[i]);
				adjustment_2.setMember_price_2(member_price_2[i]);
				adjustment_2.setWholesale_price2_2(wholesale_price2_2[i]);
				adjustment_2.setWholesale_price3_2(wholesale_price3_2[i]);
				adjustment_2.setWholesale_price4_2(wholesale_price4_2[i]);
				adjustment_2.setWholesale_price5_2(wholesale_price5_2[i]);
				adjustment_2.setWholesale_price6_2(wholesale_price6_2[i]);
				adjustment_2.setWholesale_price7_2(wholesale_price7_2[i]);
				adjustment_2.setWholesale_price8_2(wholesale_price8_2[i]);
				adjustment_2.setMember_price2_2(member_price2_2[i]);
				adjustment_2.setMember_price3_2(member_price3_2[i]);
				adjustment_2.setMember_price4_2(member_price4_2[i]);
				adjustment_2.setMember_price5_2(member_price5_2[i]);
				adjustment_2.setRemark(remark[i]);
				adjustment_2.getAdjustments().setAudflag(
						Constant.CHECK_STATUS_WAITCHECK);
				adjustmentService.updateObj(adjustment_2);
			}
			logManageService.insertLog(request, "保存了调价单数据", "商品调价");
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
	 * 调价单审核通过/将调价单中的信息更新至GoodsFile
	 * 
	 * @param adjustments
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute Adjustments adjustments,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		// CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Adjustments adj = (Adjustments) adjustmentService.getObjById(id,
					"Adjustments");
			adj.setAudflag(Constant.CHECK_STATUS_PASS);
			// System.out.println("user:----:"+user);
			// adj.setAuditor(user);
			adj.setEffecttime(df.format(new Date()));
			//
			List<AdjustmentDetail> adjustmentDetailList = adjustmentService
					.getListByObj(AdjustmentDetail.class,
							" ZC_ADJUSTMENTS_ID = '" + id + "'");
			String name = "";
			if (adjustmentDetailList != null && adjustmentDetailList.size() > 0) {
				for (int i = 0; i < adjustmentDetailList.size(); i++) {
					AdjustmentDetail adjustmentDetail = adjustmentDetailList
							.get(i);
					name = adjustmentDetail.getGoods_name();
					List goodsid = adjustmentService.getIdList(name);
					for (int j = 0; j < goodsid.size(); j++) {
						String goodid = goodsid.toString();
						String goods_id = goodid.substring(
								goodid.indexOf("=") + 1,
								goodid.lastIndexOf("}"));
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goods_id, "GoodsFile");

						float lowest_price = Float.parseFloat(adjustmentDetail
								.getLowest_price_2());
						goodsFile.setLowest_price(lowest_price);
						float goods_purchase_price = Float
								.parseFloat(adjustmentDetail
										.getGoods_purchase_price_2());
						goodsFile.setGoods_purchase_price(goods_purchase_price);
						float wholesale_price = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price_2());
						goodsFile.setWholesale_price(wholesale_price);
						float wholesale_price2 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price2_2());
						goodsFile.setWholesale_price2(wholesale_price2);
						float wholesale_price3 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price3_2());
						goodsFile.setWholesale_price3(wholesale_price3);
						float wholesale_price4 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price4_2());
						goodsFile.setWholesale_price4(wholesale_price4);
						float wholesale_price5 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price5_2());
						goodsFile.setWholesale_price5(wholesale_price5);
						float wholesale_price6 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price6_2());
						goodsFile.setWholesale_price6(wholesale_price6);
						float wholesale_price7 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price7_2());
						goodsFile.setWholesale_price7(wholesale_price7);
						float wholesale_price8 = Float
								.parseFloat(adjustmentDetail
										.getWholesale_price8_2());
						goodsFile.setWholesale_price8(wholesale_price8);
						float goods_price = Float.parseFloat(adjustmentDetail
								.getGoods_price_2());
						goodsFile.setGoods_price(goods_price);
						float distribution_price = Float
								.parseFloat(adjustmentDetail
										.getDistribution_price_2());
						goodsFile.setDistribution_price(distribution_price);
						float member_price = Float.parseFloat(adjustmentDetail
								.getMember_price_2());
						goodsFile.setMember_price(member_price);
						float member_price2 = Float.parseFloat(adjustmentDetail
								.getMember_price2_2());
						goodsFile.setMember_price2(member_price2);
						float member_price3 = Float.parseFloat(adjustmentDetail
								.getMember_price3_2());
						goodsFile.setMember_price3(member_price3);
						float member_price4 = Float.parseFloat(adjustmentDetail
								.getMember_price4_2());
						goodsFile.setMember_price4(member_price4);
						float member_price5 = Float.parseFloat(adjustmentDetail
								.getMember_price5_2());
						goodsFile.setMember_price5(member_price5);
						goodsFileService.updateObj(goodsFile);

					}
				}
			}
			String auditor = request.getParameter("auditor");
			adj.setAuditor(auditor);

			adjustmentService.updateObj(adj);
			logManageService.insertLog(request, "审核通过系统自动修改审核状态为完成", "商品调价");
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
	 * 审核不通过
	 * 
	 * @param adjustments
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkNoPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkNoPass(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		String auditor = request.getParameter("auditor");
		// System.out.println("auditor:---:"+auditor);
		// CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Adjustments adj = (Adjustments) adjustmentService.getObjById(id,
					"Adjustments");
			adj.setAudflag(Constant.CHECK_STATUS_NOPASS);
			// adj.setAuditor(user.getName());
			adj.setAuditor(auditor);
			adj.setEffecttime(df.format(new Date()));
			adjustmentService.updateObj(adj);
			logManageService.insertLog(request, "审核不通过系统自动修改审核状态为待处理", "商品调价");
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
		ModelAndView view = createIframeView("basic/adjustments/adjustment_choseGoods");
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
			dataGrid = adjustmentService.getChooseGoodsItems(page, goodsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

}
