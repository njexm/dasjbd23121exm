package com.proem.exm.controller.wholesaleGroupPurchase.customer;

import java.io.UnsupportedEncodingException;
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
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.service.wholesaleGroupPurchase.customer.CustomerInfoService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 客户档案
 * 
 * @author ZuoYM
 * @com proem
 */
@Controller
@RequestMapping("customer/customerinfo")
public class CustomerInfoController extends BaseController {

	@Autowired
	CustomerInfoService customerInfoService;
	@Autowired
	ZcZoningService zcZoningService;

	@InitBinder("customerInfo")
	public void initCust(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("customerInfo.");
	}

	@InitBinder("zcZoning")
	public void initZoning(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcZoning.");
	}

	/**
	 * 打开初始化跳转页面
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
			faName = "批发团购";
		} else {
			faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		}
		String fasonName = request.getParameter("fasonName");
		if (StringUtils.isBlank(fasonName)) {
			fasonName = "团购客户管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "客户管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/customer/customerinfo_list");
		return view;
	}

	/**
	 * 打开新增客户基本信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addBranch(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("wholesaleGroupPurchase/customer/customerinfo_add");
		return view;
	}

	/**
	 * 打开编辑客户基本信息页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CustomerInfo customerInfo = (CustomerInfo) customerInfoService
				.getObjById(id, "CustomerInfo");
		model.addAttribute("customerInfo", customerInfo);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/customer/customerinfo_edit");
		return view;
	}

	/**
	 * 打开客户基本信息明细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CustomerInfo customerInfo = (CustomerInfo) customerInfoService
				.getObjById(id, "CustomerInfo");
		model.addAttribute("customerInfo", customerInfo);
		ModelAndView view = createIframeView("wholesaleGroupPurchase/customer/customerinfo_detail");
		return view;
	}

	/**
	 * 新增客户基本信息
	 * 
	 * @param customerInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			customerInfo.setId(id);
			customerInfo.getZcZoning().setId(UuidUtils.getUUID());
			zcZoningService.saveObj(customerInfo.getZcZoning());
			customerInfo.setDelFlag("0");
			customerInfoService.saveObj(customerInfo);
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
	 * 分页查询
	 * 
	 * @param customerInfo
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCustomerJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCustomerJson(@ModelAttribute CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = customerInfoService.getPagedDataGridObj(page,
					customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 删除客户基本信息
	 * 
	 * @param customerInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteCustomerInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteCustomerInfo(
			@ModelAttribute CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = request.getParameter("id");
			if (StringUtil.validate(id)) {
				customerInfoService.deleteObjById(id,
						CustomerInfo.class.getName());
				logManageService.insertLog(request, "删除了选中的供应商", "供应商档案");
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listCustomer", produces = "application/json")
	@ResponseBody
	public List<CustomerInfo> listJson(HttpServletRequest request,
			HttpServletResponse response) {
		List<CustomerInfo> list = null;
		try {
			list = customerInfoService.getAllObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 修改客户基本信息
	 * 
	 * @param customerInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String zcZoningId = customerInfo.getZcZoning().getId();
			if (zcZoningId == null || StringUtils.isBlank(zcZoningId)) {
				customerInfo.getZcZoning().setId(UuidUtils.getUUID());
				zcZoningService.saveObj(customerInfo.getZcZoning());
			} else {
				zcZoningService.updateObj(customerInfo.getZcZoning());
			}
			customerInfo.setDelFlag("0");
			customerInfoService.updateObj(customerInfo);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute CustomerInfo customerInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = customerInfoService.getlistJson(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

}
