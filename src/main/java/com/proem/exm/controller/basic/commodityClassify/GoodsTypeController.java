package com.proem.exm.controller.basic.commodityClassify;

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
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.service.basic.CommodityClassify.GoodsTypeService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping("goodsFile/goodsType")
public class GoodsTypeController extends BaseController {
	@Autowired
	GoodsTypeService goodsTypeService;

	@InitBinder("goodsType")
	public void initCommodityClassify(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsType.");
	}

	/**
	 * 初始化截单类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "init")
	public ModelAndView index(HttpServletRequest request,
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
			sonName = "截单类型";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/commodityClassify/goodsType_list");
		return view;
	}

	/**
	 * 列表展示所有商品类别信息
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsTypeJson", produces = "application/json")
	@ResponseBody
	public Object listGoodsTypeJson(@ModelAttribute GoodsType goodsType,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = goodsTypeService.getGoodsTypeObj(page, goodsType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增分店页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "openAddGoodsType")
	public ModelAndView addGoodsType(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView view = createIframeView("basic/commodityClassify/goodsType_add");
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
	@RequestMapping(value = "addGoodsType", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsType(@ModelAttribute GoodsType goodsType,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			goodsType.setId(id);
			goodsTypeService.saveObj(goodsType);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "新增了一条截单类型", "截单类型");
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
	@RequestMapping("openEdit")
	public ModelAndView openEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		GoodsType goodsType = (GoodsType) goodsTypeService.getObjById(id,
				"GoodsType");
		model.addAttribute("goodsType", goodsType);
		ModelAndView view = createIframeView("basic/commodityClassify/goodsType_edit");
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
	@RequestMapping(value = "updateGoodsType", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateGoodsType(@ModelAttribute GoodsType goodsType,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			goodsTypeService.updateObj(goodsType);
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "修改了勾选的截单类型", "截单类型");
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
	@RequestMapping(value = "deleteGoodsType", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteGoodsType(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				goodsTypeService.deleteObjById(ids[i],
						GoodsType.class.getName());
			}
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
			logManageService.insertLog(request, "删除了勾选的截单类型", "截单类型");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
			return ajaxResult;
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public List listJson(@ModelAttribute GoodsType goodsType,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		List dataGrid = null;
		try {
			dataGrid = goodsTypeService.getObjList(goodsType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
}
