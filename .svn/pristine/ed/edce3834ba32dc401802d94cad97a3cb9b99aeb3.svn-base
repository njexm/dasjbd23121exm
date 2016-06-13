package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.order.ZcHistoryOrder;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.service.settlement.BranchAchieveService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping("branchAchieve/branchAchieveDo")
public class BranchAchieveController extends BaseController{

	@Autowired
	BranchAchieveService branchAchieveService;
	
	// 初始化跳转页面
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "结算管理";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "数据报表";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "亭点业绩日报表";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/branchAchievement/branch_day_achievement");
		return view;
	}
	
	@RequestMapping(value = "listOrdersJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersJson(@ModelAttribute ZcOrder zcOrder,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			double todayOrders = 0;
			dataGrid = branchAchieveService.getPagedDataGridObj(page, zcOrder);
			List<Map<String , Object>> list = dataGrid.getRows();
			if(list != null && list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					todayOrders = branchAchieveService.getCountByObj(ZcHistoryOrder.class, "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
