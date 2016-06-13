package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.proem.exm.entity.settlement.SupplierQuery;
import com.proem.exm.service.settlement.SupplierQueryOrdersService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 供应商往来账款控制层
 * @author myseft
 *
 */
@Controller
@RequestMapping("supplierQueryOrders/supplierQueryOrders")
public class SupplierQueryOrdersController extends BaseController{

	@Autowired
	SupplierQueryOrdersService supplierQueryOrdersService;
	
	@InitBinder("supplierQuery")
	public void supplierQuery(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("supplierQuery.");
	}
	
	/**
	 * 打开采购单据汇总查询页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initOrderItem")
	public ModelAndView initReceipts(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "供应商往来账款管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "供应商往来账款查询";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "供应商往来账款单据汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/supplierBillsQuery/supplier_orderItems");
		return view;
	}
	
	
	@RequestMapping(value = "listOrderItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrderItemJson(
			@ModelAttribute SupplierQuery supplierQuery,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = supplierQueryOrdersService.getPagedDataGridObj(page,
					supplierQuery);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Date date = (Date) list.get(i).get("NEW_TIME");
					String dateStr = sdf.format(date);
					list.get(i).put("NEW_TIME", dateStr);
				}
				dataGrid.setRows(list);
			}
			return dataGrid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
