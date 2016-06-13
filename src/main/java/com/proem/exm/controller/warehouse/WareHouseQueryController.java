package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
import com.proem.exm.entity.warehouse.WarehouseQuery;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.warehouse.WarehouseQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 仓库查询controller
 * 
 * @author songcj 2015年12月15日 下午1:59:49
 */
@Controller
@RequestMapping("wareHouseQuery/wareHouseQueryDo")
public class WareHouseQueryController extends BaseController {

	@Autowired
	WarehouseQueryService warehouseQueryService;

	@InitBinder("warehouseQuery")
	public void warehouseQuery(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("warehouseQuery.");
	}

	/**
	 * 跳转仓库商品查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initGoodsQuery")
	public ModelAndView initGoodsQuery(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "仓库查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/goods_query");
		return view;
	}

	/**
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsQueryJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getPagedDataGridGoods(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开库存查询类别汇总页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initClassQuery")
	public ModelAndView initClassQuery(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "库存管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "库存查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "类别汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_Class");
		return view;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute ZcStorehouse zcStorehouse,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getObjList(zcStorehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 类别查询汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listClassifyQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listClassifyQueryJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getClassifyQueryGoods(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开库存查询类别汇总页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initTotalGoodsQuery")
	public ModelAndView initTotalGoodsQuery(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "库存管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "库存查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "出入库汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_goodsTotal");
		return view;
	}

	/**
	 * 出入库商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsTotalJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsTotalJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService
					.getGoodsTotal(page, warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开库存查询类别汇总页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initRegionalGoodsQuery")
	public ModelAndView initRegionalGoodsQuery(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "库存管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "库存查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "区域商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/regional_goods");
		return view;
	}

	/**
	 * 区域商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listRegionalGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listRegionalGoodsJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getRegionalGoods(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转仓库商品查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initGoodsDetail")
	public ModelAndView initGoodsDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "仓库查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "出入库明细";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_goodsDetail");
		return view;
	}

	/**
	 * 出入库商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsDetailJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsDetailJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getGoodsDetail(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开大类库存汇总页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initBigClassQuery")
	public ModelAndView initBigClassQuery(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "库存管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "库存查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "大类库存汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_BigClass");
		return view;
	}

	/**
	 * 大类库存汇总查询
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBigClassifyQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBigClassifyQueryJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getBigClassifyQueryGoods(page,
					warehouseQuery);
			List<Map<String, Object>> list = dataGrid.getRows();
			double totalStock = 0;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String oldStock = list.get(i).get("STOCK").toString();
					totalStock += Double.valueOf(oldStock);
				}
				for (int i = 0; i < list.size(); i++) {
					String stock = list.get(i).get("STOCK").toString();
					double percent = Double.valueOf(stock) / totalStock * 100;
					// BigDecimal store = new BigDecimal(percent);
					// store = store.setScale(2, BigDecimal.ROUND_HALF_UP);
					list.get(i).put("STOREPERCENT", percent);
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开大类库存汇总页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initBrandQuery")
	public ModelAndView initBrandQuery(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "库存管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "库存查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "品牌库存汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_brand");
		return view;
	}

	/**
	 * 大类库存汇总查询
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBrandQueryJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBrandQueryJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getBrandQueryGoods(page,
					warehouseQuery);
			List<Map<String, Object>> list = dataGrid.getRows();
			double totalStock = 0;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String oldStock = list.get(i).get("STOCK").toString();
					totalStock += Double.valueOf(oldStock);
				}
				for (int i = 0; i < list.size(); i++) {
					String stock = list.get(i).get("STOCK").toString();
					double percent = Double.valueOf(stock) / totalStock * 100;
					// BigDecimal store = new BigDecimal(percent);
					// store = store.setScale(2, BigDecimal.ROUND_HALF_UP);
					list.get(i).put("STOREPERCENT", percent);
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 商品库存分布
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initGoodsDistribution")
	public ModelAndView initGoodsDistribution(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "仓库查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "商品库存分布";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_goodsDistribution");
		return view;
	}

	/**
	 * 出入库商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsDistributionJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsDistributionJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getGoodsDistribution(page,
					warehouseQuery);
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String branch = "";
					String storeHouseId = list.get(i).get("AID").toString();
					ZcStorehouse zcStorehouse = (ZcStorehouse) warehouseQueryService
							.getObjById(storeHouseId, "ZcStorehouse");
					String branchId = zcStorehouse.getBranch().getId();
					String goodsFileId = zcStorehouse.getGoodsFile().getId();
					List<ZcStorehouse> zcStorehouseList = warehouseQueryService
							.getListByObj(ZcStorehouse.class, "goodsFile_id='"
									+ goodsFileId + "' and branch_id !='"
									+ branchId + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						for (int j = 0; j < zcStorehouseList.size(); j++) {
							ZcStorehouse storehouse = zcStorehouseList.get(j);
							branch += storehouse.getBranch().getBranch_name()
									+ ";";
						}
					} else {
						branch = zcStorehouse.getBranch().getBranch_name();
					}
					list.get(i).put("BRANCHS", branch);
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转成本记录汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initGoodsCost")
	public ModelAndView initGoodsCost(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "仓库查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "成本记录汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/wareHouse_goodsCost");
		return view;
	}

	/**
	 * 出入库商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsCostJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsCostJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getGoodsCost(page, warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转成本记录汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initOrdersFloat")
	public ModelAndView initOrdersFloat(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单流";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "订单流查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/orders_float");
		return view;
	}

	/**
	 * 出入库商品汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listOrdersFloatJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersFloatJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getOrdersFloat(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 跳转小区分拣成本记录总查询页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("initBranchSorte")
	public ModelAndView initBranchSorte(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "订单流";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "分拣订单流查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/query/branch_sorte");
		return view;
	}

	/**
	 * 小区分拣商品成本汇总
	 * 
	 * @param purchaseSearch
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchSorteJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchSorteJson(
			@ModelAttribute WarehouseQuery warehouseQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = warehouseQueryService.getBranchSorte(page,
					warehouseQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
