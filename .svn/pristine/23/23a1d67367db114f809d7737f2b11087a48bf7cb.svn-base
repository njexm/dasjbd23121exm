package com.proem.exm.controller.basic.provider;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.basic.ProviderInfoView;

/**
 * 供应商基本信息控制器
 * 
 * @author jingbc
 * 
 * @com proem
 */
@Controller
@RequestMapping("provider/poviderinfo")
public class ProviderController extends BaseController {

	@Autowired
	ProviderInfoService providerInfoService;
	@Autowired
	ZcUserInfoService zcUserInfoService;
	@Autowired
	ZcZoningService zcZoningService;
	@Autowired
	GoodsFileService goodsFileService;

	@InitBinder("providerInfo")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("providerInfo.");
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
			sonName = "供应商档案";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/provider/providerinfo/providerinfo_list");
		return view;
	}

	/**
	 * 打开新增供应商基本信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addBranch(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("basic/provider/providerinfo/providerinfo_add");
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
	@RequestMapping("gotoEditProvider")
	public ModelAndView gotoEditProvider(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(id, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeViewIncludeMap(
				"basic/provider/providerinfo/providerinfo_edit", initLoadPage());
		return view;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	private Map initLoadPage() {
		Map returnMap = new HashMap();
		returnMap.put("UserList", zcUserInfoService.getUserInfoList());
		return returnMap;
	}

	/**
	 * 打开新增供应商商品信息页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAddGoods")
	public ModelAndView openAddGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(id, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("basic/provider/providerinfo/providerinfo_goods");
		return view;
	}

	/**
	 * 明细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoDetailProvider")
	public ModelAndView gotoDetailProvider(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(id, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("basic/provider/providerinfo/providerinfo_detail");
		return view;
	}

	/**
	 * 打开供应商商品页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("gotoGoodsDetail")
	public ModelAndView gotoGoodsDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProviderInfo providerInfo = (ProviderInfo) providerInfoService
				.getObjById(id, "ProviderInfo");
		model.addAttribute("providerInfo", providerInfo);
		ModelAndView view = createIframeView("basic/provider/providerinfo/providerinfo_goodstotal");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listProviderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProviderJson(
			@ModelAttribute ProviderInfoView providerInfoView,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = providerInfoService.getPagedDataGridObj(page,
					providerInfoView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute ProviderInfo providerInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = providerInfoService.getObjList(providerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listSendProviderJson", produces = "application/json")
	@ResponseBody
	public List listSendProviderJson(@ModelAttribute ProviderInfo providerInfo,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = providerInfoService.getObjList1(providerInfo);
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
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute ProviderInfo providerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			providerInfo.setId(id);
			providerInfo.getZcZoning().setId(UuidUtils.getUUID());
			zcZoningService.saveObj(providerInfo.getZcZoning());
			// providerInfo.setZcZoning(zcZoning);
			providerInfo.setDelFlag("0");
			providerInfoService.saveObj(providerInfo);
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
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getObjById", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getObjById(
			@ModelAttribute ProviderInfo providerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = null;
		try {
			map = providerInfoService.getObjById(providerInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 修改数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(@ModelAttribute ProviderInfo providerInfo,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String zcZoningId = providerInfo.getZcZoning().getId();
			if (zcZoningId == null || StringUtils.isBlank(zcZoningId)) {
				providerInfo.getZcZoning().setId(UuidUtils.getUUID());
				zcZoningService.saveObj(providerInfo.getZcZoning());
			} else {
				zcZoningService.updateObj(providerInfo.getZcZoning());
			}
			providerInfo.setDelFlag("0");
			providerInfoService.updateObj(providerInfo);
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
	 * 逻辑删除
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "deleteProvider", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteProvider(
			@ModelAttribute ProviderInfoView providerInfoView,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = request.getParameter("id");
			if (StringUtil.validate(id)) {
				providerInfoService.deleteObjById(id,
						ProviderInfo.class.getName());
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

	/**
	 * 添加供应商信息入商品
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addTo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addTo(@ModelAttribute ProviderInfoView providerInfoView,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String providerId = request.getParameter("providerId");
		ProviderInfo providerInfo = new ProviderInfo();
		providerInfo.setId(providerId);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String id = idStr[i];
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(id, "GoodsFile");
					goodsFile.setProvider(providerInfo);
					goodsFileService.updateObj(goodsFile);
				}
			}
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
}
