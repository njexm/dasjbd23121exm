package com.proem.exm.controller.warehouse;

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
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.order.ZcProcessOrder;
import com.proem.exm.entity.order.ZcTransitOrder;
import com.proem.exm.service.warehouse.DistributionService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping("distribution/distributionDo")
public class DistributionController extends BaseController{
	
	@Autowired
	DistributionService distributionService;
	
	@InitBinder("zcOrder")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrder.");
	}
	
	@InitBinder("zcOrderItem")
	public void zcOrderItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcOrderItem.");
	}

	
	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "仓库管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "分拣配送";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "配送订单查询";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/distribution/distribution_list");
		return view;
	}
	
		
	@RequestMapping(value = "listOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersJson(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = distributionService.getPagedDataGridObj(page, zcOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping("itemList")
	public ModelAndView itemList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ZcTransitOrder zcOrder = (ZcTransitOrder) distributionService.getObjById(id, "ZcTransitOrder");
		model.addAttribute("zcOrder", zcOrder);
		model.addAttribute("orderId", id);
		ModelAndView view = createIframeView("warehouse/distribution/distribution_detail");
		return view;
	}
	
	@RequestMapping(value = "listOrdersItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersItemJson(@ModelAttribute ZcOrderItem zcOrderItem,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = distributionService.getItemDataGridObj(page, zcOrderItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
