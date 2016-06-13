package com.proem.exm.controller.settlement;

import java.io.UnsupportedEncodingException;
import java.util.Date;

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
import com.proem.exm.entity.settlement.TaxManager;
import com.proem.exm.service.settlement.TaxManagerService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 税务管理控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("settlement/taxManager")
public class TaxManagerController extends BaseController {
	@Autowired
	TaxManagerService taxManagerService;

	@InitBinder("taxManager")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("taxManager.");
	}

	/**
	 * 打开初始化跳转页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
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
			fasonName = "发票管理";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "发票管理";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("settlement/taxManager/taxManager_list");
		return view;
	}

	/**
	 * 打开新增发票信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("openTest")
	public ModelAndView openTest(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("settlement/taxManager/taxManager");
		return view;
	}

	/**
	 * 打开新增发票信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("openAdd")
	public ModelAndView openAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("settlement/taxManager/taxManager_add");
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
	@RequestMapping(value = "listTaxManagerJson", produces = "application/json")
	@ResponseBody
	public DataGrid listTaxManagerJson(@ModelAttribute TaxManager taxManager,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = taxManagerService.getPagedDataGridObj(page, taxManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveTaxManager", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveTaxManager(@ModelAttribute TaxManager taxManager,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String datevalue = request.getParameter("datevalue");
		Date createDate = java.sql.Date.valueOf(datevalue);
		try {
			String id = UuidUtils.getUUID();
			taxManager.setId(id);
			taxManager.setCreateDate(createDate);
			taxManagerService.saveObj(taxManager);
			logManageService.insertLog(request, "新增了一条发票信息", "发票管理");
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
	 * 删除选中的发票信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteTaxManager", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteTaxManager(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				taxManagerService.deleteObjById(ids[i],
						TaxManager.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的发票信息", "发票管理");
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
	 * 打开发票管理明细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openTaxManagerDetail")
	public ModelAndView openTaxManagerDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		TaxManager taxManager = (TaxManager) taxManagerService.getObjById(id,
				"TaxManager");
		model.addAttribute("taxManager", taxManager);
		ModelAndView view = createIframeView("settlement/taxManager/taxManager_detail");
		return view;
	}

	/**
	 * 打开采发票管理编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openTaxManagerEdit")
	public ModelAndView openTaxManagerEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		TaxManager taxManager = (TaxManager) taxManagerService.getObjById(id,
				"TaxManager");
		model.addAttribute("taxManager", taxManager);
		ModelAndView view = createIframeView("settlement/taxManager/taxManager_edit");
		return view;
	}

	/**
	 * 新增数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateTaxManager", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateTaxManager(@ModelAttribute TaxManager taxManager,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = request.getParameter("id");
			taxManagerService.deleteObjById(id, TaxManager.class.getName());
			// tax.setTaxCode(taxManager.getTaxCode());
			// tax.setTaxNumber(taxManager.getTaxNumber());
			// tax.setTaxType(taxManager.getTaxType());
			// tax.setRegisterNum(taxManager.getRegisterNum());
			// tax.setDetail(taxManager.getDetail());
			taxManager.setId(id);
			taxManagerService.saveObj(taxManager);
			logManageService.insertLog(request, "更新了一条发票信息", "发票管理");
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
