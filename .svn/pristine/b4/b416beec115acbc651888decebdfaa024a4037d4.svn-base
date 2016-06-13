package com.proem.exm.controller.basic.goodsFile;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdi.ctp.utils.common.StringUtils;
import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.basic.associator.WorkStation;
import com.proem.exm.service.basic.CommodityClassify.GoodsTypeService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 工位属性控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("goodsFile/workStation")
public class WorkStationController extends BaseController {
	@Autowired
	GoodsTypeService goodsTypeService;

	@InitBinder("workStation")
	public void initWorkStation(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("workStation.");
	}

	/**
	 * 初始化跳转页面
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
			sonName = "工位配置";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/provider/workStation_list");
		return view;
	}

	/**
	 * 列表展示所有工位信息
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listWorkStationJson", produces = "application/json")
	@ResponseBody
	public Object listGoodsTypeJson(@ModelAttribute WorkStation workStation,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsTypeService.getWorkStationObj(page, workStation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开新增工位信息页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "openAddWorkStation")
	public ModelAndView openAddWorkStation(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView view = createIframeView("basic/provider/workStation_add");
		return view;
	}

	/**
	 * 新增工位信息
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveNewWorkStation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNewWorkStation(
			@ModelAttribute WorkStation workStation,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			workStation.setId(id);
			goodsTypeService.saveObj(workStation);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "新增了一条工位信息", "工位配置");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 打开审核采购订单页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openEditWorkStation")
	public ModelAndView openEditWorkStation(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		WorkStation workStation = (WorkStation) goodsTypeService.getObjById(id,
				"WorkStation");
		model.addAttribute("workStation", workStation);
		ModelAndView view = createIframeView("basic/provider/workStation_edit");
		return view;
	}

	/**
	 * 更新数据
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateWorkStation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateWorkStation(
			@ModelAttribute WorkStation workStation,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			goodsTypeService.updateObj(workStation);
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "修改了勾选的工位属性", "工位配置");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteWorkStation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteGoodsType(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				goodsTypeService.deleteObjById(ids[i],
						WorkStation.class.getName());
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "删除了勾选的工位配置信息", "工位配置");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}
}
