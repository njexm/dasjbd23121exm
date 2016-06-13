package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.CheckGoodsItems;
import com.proem.exm.entity.warehouse.ZcCheckDifference;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.warehouse.CheckNumberService;
import com.proem.exm.service.warehouse.WareHouseService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 
 * @author zhusf 盘点号的控制层
 */
@Controller
@RequestMapping("warehouse/wareHouse")
public class WareHouseController extends BaseController {
	@Autowired
	CheckNumberService checkNumberService;
	@Autowired
	CommodityClassifyService commodityClassifyService;
	@Autowired
	BranchService branchService;
	@Autowired
	WareHouseService wareHouseService;
	@Autowired
	GoodsFileService goodsFileService;

	@InitBinder("zcCheckNumber")
	public void initZcCheckNumber(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcCheckNumber.");
	}

	@InitBinder("zcWarehouse")
	public void initZcWarehouse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcWarehouse.");
	}

	@InitBinder("goodsFile")
	public void initgoodsFile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	/**
	 * 跳转盘点号的初始页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
			fasonName = "库存盘点";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "盘点号";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/stockcheck/checknumber_list");
		return view;
	}

	@RequestMapping("initClassify")
	public ModelAndView initClassify(String type, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView view = null;
		model.addAttribute("type", type);
		if ("2".equals(type)) {
			view = createIframeView("warehouse/stockcheck/Classify_list");
		} else {
			view = createIframeView("warehouse/stockcheck/commodityClassify_list");
		}
		return view;
	}

	/**
	 * 新增商品初始化
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("initGoodsItem")
	public ModelAndView initGoodsItem(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String ids = request.getParameter("ids");
		String num = request.getParameter("num");
		String[] idStr = ids.split(",");
		String[] numStr = num.split(",");
		if (idStr != null && idStr.length > 0 && numStr != null
				&& numStr.length > 0) {
			for (int i = 0; i < idStr.length; i++) {
				String goodsid = idStr[i];
				if (goodsid != "" && goodsid != null && goodsid.length() != 0) {
					CheckGoodsItems checkGoodsItems = (CheckGoodsItems) checkNumberService
							.getObjById(goodsid, "CheckGoodsItems");
					if (numStr[i] == "" || numStr[i] == null
							|| numStr[i].length() == 0) {
						numStr[i] = "0.00";
					}
					String nums = numStr[i];
					checkGoodsItems.setActualCheckNumber(nums);
					wareHouseService.updateObj(checkGoodsItems);
				}
			}
		}
		ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService.getObjById(
				id, "ZcWarehouse");
		String checkNumberId = zcWarehouse.getCheckNumber().getId();
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(checkNumberId, "ZcCheckNumber");
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		model.addAttribute("checkNumber", checkNumber);
		model.addAttribute("zcWarehouse", zcWarehouse);
		ModelAndView view = createIframeView("warehouse/checktable/goodsitem_list");
		return view;
	}

	/**
	 * 打开新增盘点号的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addNewNumber")
	public ModelAndView addNewNumber(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = createIframeView("warehouse/stockcheck/checknumber_add");
		return view;
	}

	/**
	 * 打开填写/修改的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "gotoEdit")
	public ModelAndView gotoEdit(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(id, "ZcCheckNumber");
		model.addAttribute("checkNumber", checkNumber);
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		ModelAndView view = createIframeView("warehouse/stockcheck/checknumber_edit");
		return view;
	}

	/**
	 * 添加盘点单页面
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addInventory")
	public ModelAndView addInventory(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(id, "ZcCheckNumber");
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		model.addAttribute("checkNumber", checkNumber);

		ModelAndView view = createIframeView("warehouse/stockcheck/inventory_add");
		return view;
	}

	/**
	 * 编辑盘点单
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "editCheckItem")
	public ModelAndView editCheckItem(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CheckGoodsItems> checkGoodsItemsList = checkNumberService
				.getListByObj(CheckGoodsItems.class, " warehouse_id ='" + id
						+ "' and delflag = '0'");
		if (checkGoodsItemsList.size() > 0 && checkGoodsItemsList != null) {
			for (int i = 0; i < checkGoodsItemsList.size(); i++) {
				CheckGoodsItems checkGoodsItems = checkGoodsItemsList.get(i);
				checkNumberService.deleteObjById(checkGoodsItems.getId(),
						CheckGoodsItems.class.getName());
			}
		}
		ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService.getObjById(
				id, "ZcWarehouse");
		String checkNumberId = zcWarehouse.getCheckNumber().getId();
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(checkNumberId, "ZcCheckNumber");
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		model.addAttribute("checkNumber", checkNumber);
		model.addAttribute("zcWarehouse", zcWarehouse);
		ModelAndView view = createIframeView("warehouse/checktable/checkitem_edit");
		return view;
	}

	@RequestMapping(value = "checkCheckItem")
	public ModelAndView checkCheckItem(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService.getObjById(
				id, "ZcWarehouse");
		String checkNumberId = zcWarehouse.getCheckNumber().getId();
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(checkNumberId, "ZcCheckNumber");
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		model.addAttribute("checkNumber", checkNumber);
		model.addAttribute("zcWarehouse", zcWarehouse);
		ModelAndView view = createIframeView("warehouse/checktable/checkitem_check");
		return view;
	}

	@RequestMapping(value = "detail")
	public ModelAndView detailCheckItem(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CheckGoodsItems> checkGoodsItemsList = checkNumberService
				.getListByObj(CheckGoodsItems.class, " warehouse_id ='" + id
						+ "' and delflag = '0'");
		if (checkGoodsItemsList.size() > 0 && checkGoodsItemsList != null) {
			for (int i = 0; i < checkGoodsItemsList.size(); i++) {
				CheckGoodsItems checkGoodsItems = checkGoodsItemsList.get(i);
				checkNumberService.deleteObjById(checkGoodsItems.getId(),
						CheckGoodsItems.class.getName());
			}
		}
		ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService.getObjById(
				id, "ZcWarehouse");
		String checkNumberId = zcWarehouse.getCheckNumber().getId();
		ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
				.getObjById(checkNumberId, "ZcCheckNumber");
		String classifyId = checkNumber.getGoods_classify();
		if (!StringUtils.isBlank(classifyId)) {
			String[] classifyIdStr = classifyId.split(",");
			List<CommodityClassify> commodityClassifyList = new ArrayList<CommodityClassify>();
			String classifyName = "";
			if (classifyIdStr != null && classifyIdStr.length > 0) {
				for (int i = 0; i < classifyIdStr.length; i++) {
					String classId = classifyIdStr[i];
					CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
							.getObjById(classId, "CommodityClassify");
					String name = commodityClassify.getClassify_name();
					if ("".equals(classifyName)) {
						classifyName += name;
					} else {
						classifyName = classifyName + "," + name;
					}
					commodityClassifyList.add(commodityClassify);
				}
			}
			model.addAttribute("classifyName", classifyName);
			model.addAttribute("commodityClassifyList", commodityClassifyList);
		}
		model.addAttribute("checkNumber", checkNumber);
		model.addAttribute("zcWarehouse", zcWarehouse);
		ModelAndView view = createIframeView("warehouse/checktable/checkitem_detail");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param checknumber
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCheckNumberJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCheckNumberJson(
			@ModelAttribute ZcCheckNumber zcCheckNumber,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = checkNumberService.getPagedDataGridObj(page,
					zcCheckNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增盘点号
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveNumber", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNumber(@ModelAttribute ZcCheckNumber zcCheckNumber,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			Long count = branchService
					.getCountByObj(ZcCheckNumber.class, " check_number = '"
							+ zcCheckNumber.getCheck_number() + "'");
			String str = "";
			if (count != 0) {
				SimpleDateFormat formatDate = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Date date = new Date();
				str = formatDate.format(date);
				zcCheckNumber.setCheck_number("PDH" + str);
			}
			String id = UuidUtils.getUUID();
			String branchId = zcCheckNumber.getBranch().getId();
			Branch branch = (Branch) branchService.getObjById(branchId,
					"Branch");
			CtpUser userInfo = (CtpUser) request.getSession().getAttribute(
					"user");
			zcCheckNumber.setBranch(branch);
			zcCheckNumber.setId(id);
			zcCheckNumber.setStatus(Constant.CHECK_STATUS_UNDO);
			zcCheckNumber.setUser(userInfo);
			checkNumberService.saveObj(zcCheckNumber);
			logManageService.insertLog(request, "申请了盘点号", "库存盘点");
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
	 * 新增盘点单以及盘点单的商品
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveCheckItem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveCheckItem(String ids, String num, String pandianhao,
			String checkNumberId, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String type = request.getParameter("type");
		String nullNumsIds = request.getParameter("idStr");
		String[] nullNumsId = nullNumsIds.split(",");
		String[] idStr = ids.split(",");
		String[] numStr = num.split(",");
		try {
			String id = UuidUtils.getUUID();
			ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
					.getObjById(checkNumberId, "ZcCheckNumber");
			ZcWarehouse warehouse = new ZcWarehouse();
			CtpUser user = (CtpUser) request.getSession().getAttribute("user");
			warehouse.setCheckNumber(checkNumber);
			warehouse.setCreateUser(user);
			warehouse.setStatus(Constant.CHECK_STATUS_UNDO);
			warehouse.setId(id);
			warehouse.setWarehouseNumber(pandianhao);
			warehouse.getCheckNumber().setDeFlag(0);
			wareHouseService.updateObj(warehouse.getCheckNumber());
			wareHouseService.saveObj(warehouse);
			if (idStr != null && idStr.length > 0 && numStr != null
					&& numStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String goodsid = idStr[i];
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(goodsid, "GoodsFile");
					if (numStr[i] == "" || numStr[i].length() == 0
							|| numStr[i] == null) {
						numStr[i] = "0.00";
					}
					String nums = numStr[i];
					CheckGoodsItems checkGoodsItems = new CheckGoodsItems();
					String CheckGoodsItemId = UuidUtils.getUUID();
					checkGoodsItems.setGoodsFile(goodsFile);
					checkGoodsItems.setId(CheckGoodsItemId);
					checkGoodsItems.setActualCheckNumber(nums);
					checkGoodsItems.setWarehouse(warehouse);
					checkGoodsItems.setDelflag("1");
					wareHouseService.saveObj(checkGoodsItems);
				}
			}
			if ("0".equals(type)) {
				if (nullNumsId != null && nullNumsId.length > 0) {
					for (int i = 0; i < nullNumsId.length; i++) {
						String goodsid = nullNumsId[i];
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goodsid, "GoodsFile");
						CheckGoodsItems checkGoodsItems = new CheckGoodsItems();
						checkGoodsItems.setGoodsFile(goodsFile);
						checkGoodsItems.setId(UuidUtils.getUUID());
						checkGoodsItems.setActualCheckNumber("0");
						checkGoodsItems.setWarehouse(warehouse);
						checkGoodsItems.setDelflag("1");
						wareHouseService.saveObj(checkGoodsItems);
					}
				}
			}
			logManageService.insertLog(request, "新建了盘点单", "库存盘点");
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
	 * 新增空白盘点单
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "createNullCheckTable", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult createNullCheckTable(String checkNumberId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(
					"yyyyMMddHHmmssSSS");
			Date date = new Date();
			String str = formatDate.format(date);
			String pandianhao = "PDD" + str;
			String id = UuidUtils.getUUID();
			ZcCheckNumber checkNumber = (ZcCheckNumber) checkNumberService
					.getObjById(checkNumberId, "ZcCheckNumber");
			ZcWarehouse warehouse = new ZcWarehouse();
			CtpUser user = (CtpUser) request.getSession().getAttribute("user");
			warehouse.setCheckNumber(checkNumber);
			warehouse.setCreateUser(user);
			warehouse.setStatus(Constant.CHECK_STATUS_UNDO);
			warehouse.setId(id);
			warehouse.setWarehouseNumber(pandianhao);
			warehouse.getCheckNumber().setDeFlag(0);
			wareHouseService.updateObj(warehouse.getCheckNumber());
			wareHouseService.saveObj(warehouse);
			logManageService.insertLog(request, "新建了盘点单", "库存盘点");
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
	 * 全场盘点添加商品
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addAllGoodsItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addAllGoodsItems(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		ZcWarehouse zcWarehouse = (ZcWarehouse) wareHouseService.getObjById(id,
				"ZcWarehouse");
		try {
			List<GoodsFile> goodsFileList = wareHouseService.getListByObj(
					GoodsFile.class, "");
			if (goodsFileList != null && goodsFileList.size() > 0) {
				for (int i = 0; i < goodsFileList.size(); i++) {
					GoodsFile goodsFile = goodsFileList.get(i);
					CheckGoodsItems checkGoodsItems = new CheckGoodsItems();
					checkGoodsItems.setGoodsFile(goodsFile);
					checkGoodsItems.setId(UuidUtils.getUUID());
					checkGoodsItems.setActualCheckNumber("0");
					checkGoodsItems.setWarehouse(zcWarehouse);
					checkGoodsItems.setDelflag("1");
					wareHouseService.saveObj(checkGoodsItems);
				}
			}
			logManageService.insertLog(request, "新建了盘点单", "库存盘点");
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
	 * 新增盘点单以及盘点单的商品
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addCheckItem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addCheckItem(String ids, String pandianhao,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String nums = request.getParameter("nums");
		String[] num = nums.split(",");
		String[] idStr = ids.split(",");
		try {
			ZcWarehouse warehouse = (ZcWarehouse) checkNumberService
					.getObjById(pandianhao, "ZcWarehouse");
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String goodsid = idStr[i];
					Long count = checkNumberService
							.getCountByObj(CheckGoodsItems.class,
									"goodsfile_id='" + goodsid
											+ "' and warehouse_id='"
											+ pandianhao + "'");
					if (count != 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						GoodsFile goodsFile = (GoodsFile) goodsFileService
								.getObjById(goodsid, "GoodsFile");
						CheckGoodsItems checkGoodsItems = new CheckGoodsItems();
						String CheckGoodsItemId = UuidUtils.getUUID();
						checkGoodsItems.setGoodsFile(goodsFile);
						checkGoodsItems.setId(CheckGoodsItemId);
						checkGoodsItems.setActualCheckNumber(num[i]);
						checkGoodsItems.setWarehouse(warehouse);
						checkGoodsItems.setDelflag("0");
						warehouse.getCheckNumber().setDeFlag(0);
						wareHouseService.updateObj(warehouse.getCheckNumber());
						wareHouseService.saveObj(checkGoodsItems);
					}

				}
			}
			logManageService.insertLog(request, "新建了盘点单", "库存盘点");
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
	 * 编辑盘点单以及盘点单的商品
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "editCheckItem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult editCheckItem(String ids, String pandianhao, String num,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String[] idStr = ids.split(",");
		String[] numStr = num.split(",");
		try {
			if (idStr != null && idStr.length > 0 && numStr != null
					&& numStr.length > 0) {
				CheckGoodsItems checkGoodsItems1 = (CheckGoodsItems) checkNumberService
						.getObjById(idStr[0], "CheckGoodsItems");
				ZcWarehouse warehouse = checkGoodsItems1.getWarehouse();
				if (idStr.length == numStr.length) {
					for (int i = 0; i < numStr.length; i++) {
						String goodsid = idStr[i];
						CheckGoodsItems checkGoodsItems = (CheckGoodsItems) checkNumberService
								.getObjById(goodsid, "CheckGoodsItems");
						String nums = numStr[i];
						checkGoodsItems.setWarehouse(warehouse);
						checkGoodsItems.setDelflag("1");
						checkGoodsItems.setActualCheckNumber(nums);
						wareHouseService.updateObj(checkGoodsItems);
					}
				}
				warehouse.setStatus(Constant.CHECK_STATUS_WAITCHECK);
				wareHouseService.updateObj(warehouse);
			}
			logManageService.insertLog(request, "修改了盘点单", "库存盘点");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "CheckWareHouse", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult CheckWareHouse(String id, String type, String reason,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			ZcWarehouse warehouse = (ZcWarehouse) checkNumberService
					.getObjById(id, "ZcWarehouse");
			if ("2".equals(type)) {
				warehouse.setStatus(Constant.CHECK_STATUS_PASS);
				warehouse.getCheckNumber().setDeFlag(1);
				;
				checkNumberService.updateObj(warehouse);
				logManageService.insertLog(request, "审核通过了盘点单", "库存盘点");
			} else if ("3".equals(type)) {
				warehouse.setStatus(Constant.CHECK_STATUS_NOPASS);
				warehouse.setReason(reason);
				checkNumberService.updateObj(warehouse);
				logManageService.insertLog(request, "审核了盘点单,但未通过!", "库存盘点");
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
	public AjaxResult update(@ModelAttribute ZcCheckNumber zcCheckNumber,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			checkNumberService.updateObj(zcCheckNumber);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "insertDifference", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult insertDifference(String id, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			// ZcWarehouse warehouse=(ZcWarehouse)
			// checkNumberService.getObjById(id, "ZcWarehouse");
			// String zcCheckNumberId=(warehouse.getCheckNumber()==null?new
			// ZcCheckNumber():warehouse.getCheckNumber()).getId()==null?"":(warehouse.getCheckNumber()==null?new
			// ZcCheckNumber():warehouse.getCheckNumber()).getId();
			ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService
					.getObjById(id, "ZcWarehouse");
			ZcCheckNumber zcCheckNumber = zcWarehouse.getCheckNumber();
			// List<ZcWarehouse> warehouses = checkNumberService.getListByObj(
			// ZcWarehouse.class,
			// "checkNumber_id='" + zcCheckNumber.getId() + "'");
			// if (warehouses != null && warehouses.size() > 0) {
			// for (int i = 0; i < warehouses.size(); i++) {
			// int status = warehouses.get(i).getStatus();
			// if (status != Constant.CHECK_STATUS_PASS) {
			// ajaxResult = new AjaxResult(AjaxResult.SAVE,
			// AjaxResult.FAIL, AjaxResult.INFO);
			// return ajaxResult;
			// }
			// }
			// }
			// if (zcCheckNumber.getDeFlag() != 1) {
			// ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
			// AjaxResult.INFO);
			// return ajaxResult;
			// }
			if (zcCheckNumber != null) {
				Long count = checkNumberService.getCountByObj(
						ZcCheckDifference.class, "zcWarehouse_id='"
								+ zcWarehouse.getId() + "'");
				if (count == 0) {
					ZcCheckDifference zcCheckDifference = new ZcCheckDifference();
					String difId = UuidUtils.getUUID();
					CtpUser user = (CtpUser) request.getSession().getAttribute(
							"user");
					zcCheckDifference.setId(difId);
					zcCheckDifference.setCheckNumber(zcCheckNumber);
					zcCheckDifference.setZcWarehouse(zcWarehouse);
					zcCheckDifference.setStatus(Constant.CHECK_STATUS_UNDO);
					zcCheckDifference.setCreateUser(user);
					checkNumberService.saveObj(zcCheckDifference);
					logManageService.insertLog(request, "生成了差异盘点单", "差异盘点");
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.SUCCESS, AjaxResult.INFO);
				} else {
					ajaxResult = new AjaxResult(AjaxResult.SELECT,
							AjaxResult.SUCCESS, AjaxResult.INFO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 跳转盘点表的初始页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initlist")
	public ModelAndView initlist(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		String faName = request.getParameter("faName");
		faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		String fasonName = request.getParameter("fasonName");
		fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		String sonName = request.getParameter("sonName");
		sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("/warehouse/checktable/checktable_list");
		return view;
	}

	/**
	 * 分页查询
	 * 
	 * @param checktable
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listCheckTableJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCheckTableJson(@ModelAttribute ZcWarehouse zcWarehouse,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = wareHouseService.getPagedDataGridObj(page, zcWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsJson(String type, String classifyIds,
			String houseId, @ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = checkNumberService.getGoodList(page, type, classifyIds,
					goodsFile, houseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listCheckGoodsItemsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCheckGoodsItemsJson(String id, String houseId,
			@ModelAttribute GoodsFile goodsFile, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = checkNumberService.getCheckGoodList(page, id, goodsFile,
					houseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping("gotoPrintCheckItem")
	public ModelAndView gotoPrintCheckItem(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID的获取订单详情
		List<CheckGoodsItems> checkGoodsItemList = checkNumberService
				.getListByObj(CheckGoodsItems.class, "WAREHOUSE_ID = '" + id
						+ "'");
		List<CheckGoodsItems> goodsList = new ArrayList<CheckGoodsItems>();
		String[] guige = null;
		if (checkGoodsItemList != null && checkGoodsItemList.size() > 0) {
			for (int i = 0; i < checkGoodsItemList.size(); i++) {
				CheckGoodsItems checkGoodsItems = checkGoodsItemList.get(i);
				if (checkGoodsItems.getGoodsFile().getGoods_specifications() != null
						&& checkGoodsItems.getGoodsFile()
								.getGoods_specifications() != "") {
					guige = checkGoodsItems.getGoodsFile()
							.getGoods_specifications().split("商品规格：");
					if (guige != null && guige.length > 1) {
						checkGoodsItems.getGoodsFile().setGoods_specifications(
								guige[1]);
					} else if (guige != null && guige.length == 1) {
						checkGoodsItems.getGoodsFile().setGoods_specifications(
								guige[0]);
					}
				} else {
					checkGoodsItems.getGoodsFile().setGoods_specifications("");
				}
				goodsList.add(checkGoodsItems);
			}
		}
		// 根据ID获取采购退货单对象
		ZcWarehouse zcWarehouse = (ZcWarehouse) checkNumberService.getObjById(
				id, "ZcWarehouse");
		model.addAttribute("zcWarehouse", zcWarehouse);
		model.addAttribute("checkGoodsItemList", goodsList);
		ModelAndView view = createIframeView("warehouse/checktable/checkitem_print");
		return view;
	}

	/**
	 * 删除盘点号
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				checkNumberService.deleteObjById(ids[i],
						ZcCheckNumber.class.getName());
			}
			logManageService.insertLog(request, "删除了勾选的盘点号", "库存盘点");
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
	 * 删除修改页面选中的商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeGoods(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					checkNumberService.deleteObjById(ids[i],
							CheckGoodsItems.class.getName());
				}
			}
			logManageService.insertLog(request, "删除了盘点单中的一条商品", "库存盘点");
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
	 * 删除盘点单
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteTable", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteTable(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			if (id != null && id != "") {
				List<CheckGoodsItems> checkGoodsItemsList = checkNumberService
						.getListByObj(CheckGoodsItems.class, "warehouse_id = '"
								+ id + "'");
				for (int i = 0; i < checkGoodsItemsList.size(); i++) {
					checkNumberService.deleteObjById(checkGoodsItemsList.get(i)
							.getId(), CheckGoodsItems.class.getName());
				}
				checkNumberService.deleteObjById(id,
						ZcWarehouse.class.getName());
				logManageService.insertLog(request, "删除了勾选的盘点单", "库存盘点");
				ajaxResult = new AjaxResult(AjaxResult.DELETE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
}
