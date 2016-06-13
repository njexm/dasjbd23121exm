package com.proem.exm.controller.system;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.system.BranchIdInfo;
import com.proem.exm.service.system.BranchIdInfoService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 分店id绑定
 * 
 * @author ZuoYM
 * 
 */
@Controller
@RequestMapping("/branchIdInfo")
public class BranchIdInfoController extends BaseController {

	@Autowired
	BranchIdInfoService branchIdInfoService;

	@InitBinder("branchIdInfo")
	public void associator(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchIdInfo.");
	}

	@InitBinder("branchTotal")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("branchTotal.");
	}

	/**
	 * 初始化跳转页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		if (StringUtils.isBlank(faName)) {
			faName = "系统设置";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "系统管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "分店管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("system/branchinfo/branchidinfo_list");
		return view;
	}

	/**
	 * 分店id绑定关系表
	 * @param branchIdInfo
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listBranchIdOfJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBranchIdOfJson(@ModelAttribute BranchIdInfo branchIdInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			// String providerId = request.getParameter("providerId");
			dataGrid = branchIdInfoService.getPagedDataGridObj(page, branchIdInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

}
