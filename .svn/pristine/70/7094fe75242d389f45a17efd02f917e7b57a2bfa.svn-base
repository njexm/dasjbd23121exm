package com.proem.exm.controller.basic.commodityClassify;

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
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * @author DeFei 分店管理
 */

@Controller
@RequestMapping("commodityClassify")
public class CommodityClassifyController extends BaseController {

	@Autowired
	CommodityClassifyService commodityClassifyService;

	@InitBinder("commodityClassify")
	public void initCommodityClassify(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("commodityClassify.");
	}

	/**
	 * 分店初始化页面
	 * 
	 * @param request
	 * @param response
	 * @param model 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "init")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String faName=request.getParameter("faName");
		 if(StringUtils.isBlank(faName)){
			 faName="基础档案";
		 }else{
		 faName= new String(faName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String fasonName=request.getParameter("fasonName");
		 if(StringUtils.isBlank(fasonName)){
			 fasonName="档案管理";
		 }else{
		fasonName= new String(fasonName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String sonName=request.getParameter("sonName");
		 if(StringUtils.isBlank(sonName)){
			 sonName="商品类别管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/commodityClassify/commodityClassify_list");
		return view;
	}

	@RequestMapping(value = "initbrand")
	public ModelAndView indexbrand(HttpServletRequest request,
			HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		String faName=request.getParameter("faName");
		 if(StringUtils.isBlank(faName)){
			 faName="基础档案";
		 }else{
		 faName= new String(faName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String fasonName=request.getParameter("fasonName");
		 if(StringUtils.isBlank(fasonName)){
			 fasonName="档案管理";
		 }else{
		fasonName= new String(fasonName.getBytes("iso8859-1"),"utf-8"); 
		 }
		String sonName=request.getParameter("sonName");
		 if(StringUtils.isBlank(sonName)){
			 sonName="商品品牌管理";
		 }else{
		sonName= new String(sonName.getBytes("iso8859-1"),"utf-8"); }
		String title="  "+faName+" > "+fasonName+" > "+sonName+"";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("basic/commodityBrand/commodityBrand_list");
		return view;
	}

	/**
	 * 新增分店页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add")
	public ModelAndView addCommodityClassify(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
				.getObjById(id, "CommodityClassify");
		model.addAttribute("CommodityClassify", commodityClassify);
		ModelAndView view = createIframeView("basic/commodityClassify/commodityClassify_add");
		return view;
	}

	@RequestMapping(value = "addbrand")
	public ModelAndView addCommodityBrand(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("basic/commodityBrand/commodityBrand_add");
		return view;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "edit")
	public ModelAndView editCommodityClassify(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
				.getObjById(id, "CommodityClassify");
		model.addAttribute("CommodityClassify", commodityClassify);
		ModelAndView view = createIframeView("basic/commodityClassify/commodityClassify_edit");
		return view;
	}

	@RequestMapping(value = "editBrand")
	public ModelAndView editBrand(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
				.getObjById(id, "CommodityClassify");
		model.addAttribute("CommodityClassify", commodityClassify);
		ModelAndView view = createIframeView("basic/commodityBrand/commodityBrand_edit");
		return view;
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	private Map initLoadPage() {
		Map returnMap = new HashMap();
		return returnMap;
	}

	/**
	 * 分店列表展示
	 * 
	 * @param branchsView
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public Object listJson(@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if ("0".equals(commodityClassify.getId())) {
				commodityClassify.setClassify_type("1");
				dataGrid = commodityClassifyService.getPagedDataGridObj(page,
						commodityClassify);
				List<Map<String, Object>> list = dataGrid.getRows();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						list.get(i).put("state", "closed");
					}
					dataGrid.setRows(list);
				}
				return dataGrid;
			} else {
				String id = commodityClassify.getId();
				List<Map<String, Object>> children = commodityClassifyService
						.getListByParent(id);
				if (children != null && children.size() > 0) {
					for (int i = 0; i < children.size(); i++) {
						if ("1".equals(children.get(i).get("CLASSIFY_LEVEL"))) {
							children.get(i).put("state", "closed");
						} else if ("2".equals(children.get(i).get(
								"CLASSIFY_LEVEL"))) {
							children.get(i).put("state", "open");
						} else {
							children.get(i).put("state", "open");
						}

					}
				}
				return children;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listBrandJson", produces = "application/json")
	@ResponseBody
	public DataGrid listBrandJson(
			@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			commodityClassify.setClassify_type("2");
			dataGrid = commodityClassifyService.getPagedDataGridObj(page,
					commodityClassify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "selectList", produces = "application/json")
	@ResponseBody
	public DataGrid selectList(String type,
			@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if ("2".equals(type)) {
				commodityClassify.setClassify_type("1");
				commodityClassify.setClassify_level("3");
			} else if ("3".equals(type)) {
				commodityClassify.setClassify_type("2");
			}

			dataGrid = commodityClassifyService.getPagedDataGridObj(page,
					commodityClassify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "selectListList", produces = "application/json")
	@ResponseBody
	public DataGrid selectListList(String type,
			@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if ("2".equals(type)) {
				commodityClassify.setClassify_type("1");
			} else if ("3".equals(type)) {
				commodityClassify.setClassify_type("2");
			}

			dataGrid = commodityClassifyService.getPagedDataGridList(page,
					commodityClassify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "selectListJson", produces = "application/json")
	@ResponseBody
	public List selectListJson(String type, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		CommodityClassify commodityClassify = new CommodityClassify();
		List dataGrid = null;
		try {
			commodityClassify.setClassify_type(type);
			dataGrid = commodityClassifyService.getObjList(commodityClassify);
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
	public AjaxResult save(@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String id = UuidUtils.getUUID();
			commodityClassify.setId(id);
			if ("1".equals(commodityClassify.getClassify_type())) {
				String parentId = commodityClassify.getParentId();
				String classify_name = commodityClassify.getClassify_name();
				List<CommodityClassify> commodityClassifyList = commodityClassifyService.getListByObj(CommodityClassify.class, " classify_name ='" + classify_name + "'");
				CommodityClassify commodityClassifyParent = (CommodityClassify) commodityClassifyList.get(0);
				if (commodityClassifyParent != null) {
					commodityClassify.setTypeId("3");
					commodityClassify.setParentId(commodityClassifyParent.getId());
					commodityClassify.setOrder_index("0");
					if ("2".equals(commodityClassifyParent.getClassify_level())) {
						CommodityClassify commodityClassify2 = (CommodityClassify) commodityClassifyService.getObjById(commodityClassifyParent.getParentId(), "CommodityClassify");
						CommodityClassify commodityClassify3 = (CommodityClassify) commodityClassifyService.getObjById(commodityClassify2.getParentId(), "CommodityClassify");
						commodityClassify.setParentPath("."+commodityClassify2.getParentId()+"."+commodityClassify3.getParentId()+".");
						commodityClassify.setClassify_level("3");
					} else if ("0".equals(commodityClassifyParent.getParentId())) {
						commodityClassify.setParentPath(".");
						commodityClassify.setClassify_level("1");
					} else if ("1".equals(commodityClassifyParent.getParentId())) {
						CommodityClassify commodityClassify2 = (CommodityClassify) commodityClassifyService.getObjById(commodityClassifyParent.getParentId(), "CommodityClassify");
						commodityClassify.setParentPath("."+commodityClassify2.getParentId()+".");
						commodityClassify.setClassify_level("2");
					} else {
						CommodityClassify commodityClassify2 = (CommodityClassify) commodityClassifyService.getObjById(commodityClassifyParent.getParentId(), "CommodityClassify");
						CommodityClassify commodityClassify3 = (CommodityClassify) commodityClassifyService.getObjById(commodityClassify2.getParentId(), "CommodityClassify");
						commodityClassify.setParentPath("."+commodityClassify2.getParentId()+"."+commodityClassify3.getParentId()+".");
						commodityClassify.setParentId(commodityClassify2.getParentId());
						commodityClassify.setClassify_level("3");
					}
				}
			}
			commodityClassify.setDelFlag("0");
			commodityClassifyService.saveObj(commodityClassify);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult update(
			@ModelAttribute CommodityClassify commodityClassify,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			if ("1".equals(commodityClassify.getClassify_type())) {
				String parentId = commodityClassify.getParentId();
				CommodityClassify commodityClassifyParent = (CommodityClassify) commodityClassifyService
						.getObjById(parentId, "CommodityClassify");
				if (commodityClassifyParent != null) {
					if ("2".equals(commodityClassifyParent.getClassify_level())) {
						commodityClassify.setClassify_level("3");
					} else if ("0"
							.equals(commodityClassifyParent.getParentId())) {
						commodityClassify.setClassify_level("1");
					} else {
						commodityClassify.setClassify_level("2");
					}
				}
			}
			commodityClassify.setDelFlag("0");
			commodityClassifyService.updateObj(commodityClassify);
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
	 * 方法说明：(公司管理删除)
	 * 
	 * @param 参数名称
	 *            ：参数含义
	 * @return 返回值类型
	 * @Exception 异常对象
	 * 
	 * @author:PSL
	 * @date:2015年6月5日
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBranch(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
					commodityClassifyService.deleteObjById(Id,CommodityClassify.class.getName());
					ajaxResult = new AjaxResult(AjaxResult.DELETE,AjaxResult.SUCCESS, AjaxResult.INFO);
					logManageService.insertLog(request, "删除了选中的商品类别", "商品类别管理");
				} catch (Exception e) {
					ajaxResult = new AjaxResult(AjaxResult.DELETE,AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}
			}
		}
		return ajaxResult;
	}
	
	@RequestMapping(value = "/delete1", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult deleteBrand(HttpServletRequest request) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				try {
					String Id = id[i];
					commodityClassifyService.deleteObjById(Id,CommodityClassify.class.getName());
					logManageService.insertLog(request, "删除了选中的商品品牌", "商品品牌管理");
					ajaxResult = new AjaxResult(AjaxResult.DELETE,AjaxResult.SUCCESS, AjaxResult.INFO);
				} catch (Exception e) {
					ajaxResult = new AjaxResult(AjaxResult.DELETE,AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
				}
			}
		}
		return ajaxResult;
	}

	/**
	 * 编辑页面展示父级菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getTreeData")
	@ResponseBody
	public List<Map<String, Object>> getTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		return commodityClassifyService.getTreeData();
	}
}
