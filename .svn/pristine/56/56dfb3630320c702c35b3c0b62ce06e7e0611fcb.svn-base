package com.proem.exm.controller.purchase;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseSearch;
import com.proem.exm.service.purchase.PurchaseQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 采购查询controller
 * 
 * @author songcj 2015年12月14日 上午10:42:26
 */
@Controller
@RequestMapping("purchaseQuery/purchaseQuery")
public class PurchaseQueryController extends BaseController {

	@Autowired
	PurchaseQueryService purchaseQueryService;

	@InitBinder("purchaseSearch")
	public void purchaseSearch(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseSearch.");
	}

	/**
	 * 打开采购类别查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initClassQuery")
	public ModelAndView initClassQuery(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "采购管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "类别查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/class_query");
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
	@RequestMapping(value = "listGoodsQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsQueryJson(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoodsItems,
			String startDate, String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getPagedDataGridObj(page,
					purchaseOrderGoodsItems, startDate, endDate, provider,
					classify, serialNumber, branchId, goods_brand_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 类别查询list
	 */
	@RequestMapping(value = "listClassQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listClassQueryJson(
			@ModelAttribute PurchaseSearch purchaseSearch,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getPagedDataGridClass(page,
					purchaseSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开初始化跳转供应商汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initProviderSearch")
	public ModelAndView initProviderSearch(HttpServletRequest request,
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
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "供应商汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/purchase_searchProvider");
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
	@RequestMapping(value = "listProviderGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProviderGoodsJson(
			@ModelAttribute ProviderInfo providerInfo, String startDate,
			String endDate, String provider, String classify, String branchId,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getPagedProviderGoodsGridObj(page,
					providerInfo, startDate, endDate, provider, classify,
					branchId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开采购单据汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initReceipts")
	public ModelAndView initReceipts(HttpServletRequest request,
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
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "单据汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/purchase_searchReceipts");
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
	@RequestMapping(value = "listReceiptsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listReceiptsJson(
			@ModelAttribute PurchaseSearch purchaseSearch,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getPagedReceiptsGridObj(page,
					purchaseSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 历史进价查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initHistoryPrice")
	public ModelAndView initHistoryPrice(HttpServletRequest request,
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
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "历史进价查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/purchase_historyPrice");
		return view;
	}

	/**
	 * 历史进价查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listHistoryPriceJson", produces = "application/json")
	@ResponseBody
	public DataGrid listHistoryPriceJson(
			@ModelAttribute PurchaseSearch purchaseSearch,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getHistoryPriceDataGridObj(page,
					purchaseSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 大类汇总
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initBigClassQuery")
	public ModelAndView initBigClassQuery(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "采购管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "大类汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/purchase_bigClass");
		return view;
	}

	/**
	 * 大类汇总查询
	 */
	@RequestMapping(value = "listBigClassQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBigClassQueryJson(
			@ModelAttribute PurchaseSearch purchaseSearch,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getBigClassPage(page,
					purchaseSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 采购明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initDetail")
	public ModelAndView initDetail(HttpServletRequest request,
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
			fasonName = "采购查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "采购明细";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("purchase/purchaseQuery/purchase_detail");
		return view;
	}

	/**
	 * 采购明细分页查询
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listDetailJson(
			@ModelAttribute PurchaseOrderGoodsItems purchaseOrderGoodsItems,
			String startDate, String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = purchaseQueryService.getPagedDetailObj(page,
					purchaseOrderGoodsItems, startDate, endDate, provider,
					classify, serialNumber, branchId, goods_brand_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
