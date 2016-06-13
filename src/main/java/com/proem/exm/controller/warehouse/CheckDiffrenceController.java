package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.branch.BranchService;
import com.proem.exm.service.warehouse.CheckDifferenceService;
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
@RequestMapping("/checkDifference")
public class CheckDiffrenceController extends BaseController {
	@Autowired
	CheckDifferenceService checkDifferenceService;
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
		faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		String fasonName = request.getParameter("fasonName");
		fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		String sonName = request.getParameter("sonName");
		sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/checkdifference/checkdifference_list");
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
		ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
				.getObjById(id, "ZcCheckDifference");
		ZcCheckNumber zcCheckNumber = checkDifference.getCheckNumber();
		String classifyId = zcCheckNumber.getGoods_classify();
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
		model.addAttribute("checkNumber", zcCheckNumber);
		model.addAttribute("checkDifference", checkDifference);
		ModelAndView view = createIframeView("warehouse/checkdifference/checkdifference_edit");
		return view;
	}

	/**
	 * 详情页面
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail")
	public ModelAndView gotoDetail(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
				.getObjById(id, "ZcCheckDifference");
		ZcCheckNumber zcCheckNumber = checkDifference.getCheckNumber();
		String classifyId = zcCheckNumber.getGoods_classify();
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
		model.addAttribute("checkNumber", zcCheckNumber);
		model.addAttribute("checkDifference", checkDifference);
		ModelAndView view = createIframeView("warehouse/checkdifference/checkdifference_detail");
		return view;
	}

	/**
	 * 审核页面
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "check")
	public ModelAndView gotoCheck(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
				.getObjById(id, "ZcCheckDifference");
		ZcCheckNumber zcCheckNumber = checkDifference.getCheckNumber();
		String classifyId = zcCheckNumber.getGoods_classify();
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
		model.addAttribute("checkNumber", zcCheckNumber);
		model.addAttribute("checkDifference", checkDifference);
		ModelAndView view = createIframeView("warehouse/checkdifference/checkdifference_check");
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
	// @RequestMapping(value = "editCheckItem")
	// public ModelAndView editCheckItem(String id,HttpServletRequest request,
	// HttpServletResponse response,Model model) {
	// ZcWarehouse zcWarehouse=(ZcWarehouse) checkNumberService.getObjById(id,
	// "ZcWarehouse");
	// String checkNumberId=zcWarehouse.getCheckNumber().getId();
	// ZcCheckNumber checkNumber=(ZcCheckNumber)
	// checkNumberService.getObjById(checkNumberId, "ZcCheckNumber");
	// String classifyId=checkNumber.getGoods_classify();
	// if(!StringUtils.isBlank(classifyId)){
	// String[] classifyIdStr=classifyId.split(",");
	// List<CommodityClassify> commodityClassifyList=new
	// ArrayList<CommodityClassify>();
	// String classifyName="";
	// if(classifyIdStr!=null && classifyIdStr.length>0){
	// for(int i=0;i<classifyIdStr.length;i++){
	// String classId=classifyIdStr[i];
	// CommodityClassify commodityClassify=(CommodityClassify)
	// commodityClassifyService.getObjById(classId, "CommodityClassify");
	// String name=commodityClassify.getClassify_name();
	// if("".equals(classifyName)){
	// classifyName+=name;
	// }else{
	// classifyName=classifyName+","+name;
	// }
	// commodityClassifyList.add(commodityClassify);
	// }
	// }
	// model.addAttribute("classifyName", classifyName);
	// model.addAttribute("commodityClassifyList", commodityClassifyList);
	// }
	// model.addAttribute("checkNumber", checkNumber);
	// model.addAttribute("zcWarehouse", zcWarehouse);
	// ModelAndView view =
	// createIframeView("warehouse/checktable/checkitem_edit");
	// return view;
	// }
	/**
	 * 分页查询
	 * 
	 * @param checknumber
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCheckNumberJson(
			@ModelAttribute ZcCheckNumber zcCheckNumber,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = checkDifferenceService.getPagedDataGridObj(page,
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
	@RequestMapping(value = "saveDifference", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNumber(String rows, String differenceId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			if (StringUtils.isBlank(rows)) {

			} else {
				ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
						.getObjById(differenceId, "ZcCheckDifference");
				ZcCheckNumber checkNumber = checkDifference.getCheckNumber();
				JSONArray jsonArray = JSONArray.fromObject(rows);
				List<Map<String, Object>> list = jsonArray;
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String id = (String) list.get(i).get("ID");
						String resason = (String) list.get(i).get(
								"DIFFERENCEREASON");
						String storeNumber = "";
						if (list.get(i).get("STORE").equals("null")) {
							storeNumber = "0.00";
						} else {
							storeNumber = (String) (list.get(i).get("STORE"));
						}
						CheckGoodsItems checkGoodsItems = (CheckGoodsItems) checkDifferenceService
								.getObjById(id, "CheckGoodsItems");
						if (checkGoodsItems == null) {
							checkGoodsItems = new CheckGoodsItems();
						}
						checkGoodsItems.setStore(storeNumber);
						checkGoodsItems.setDifferenceReason(resason);
						checkDifferenceService.updateObj(checkGoodsItems);
					}
					checkNumber.setStatus(Constant.CHECK_STATUS_WAITCHECK);
					checkDifferenceService.updateObj(checkNumber);
					checkDifference.setStatus(Constant.CHECK_STATUS_WAITCHECK);
					checkDifferenceService.updateObj(checkDifference);
				}
				logManageService.insertLog(request, "更改了盘点差异单", "差异盘点");
				ajaxResult = new AjaxResult(AjaxResult.SAVE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 审核
	 * 
	 * @param rows
	 * @param differenceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkDifference", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkDifference(String type, String differenceId,
			String rows, String reason, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			String checkAreas = request.getParameter("checkAreas");
			ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
					.getObjById(differenceId, "ZcCheckDifference");
			ZcCheckNumber checkNumber = checkDifference.getCheckNumber();
			Branch branch = checkNumber.getBranch();
			String branchId = branch.getId();
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			// 审核通过
			if ("2".equals(type)) {
				if ("0".equals(checkAreas)) {
					List<GoodsFile> goodsFilesList = checkDifferenceService
							.getListByObj(GoodsFile.class, "");
					for (int i = 0; i < goodsFilesList.size(); i++) {
						GoodsFile goodsFile = goodsFilesList.get(i);
						List<ZcStorehouse> zcStorehousesList = checkDifferenceService
								.getListByObj(ZcStorehouse.class,
										"goodsFile_id='" + goodsFile.getId()
												+ "'");
						if (zcStorehousesList == null
								|| zcStorehousesList.size() <= 0) {
							ZcStorehouse zcStorehouse = new ZcStorehouse();
							zcStorehouse.setId(UuidUtils.getUUID());
							zcStorehouse.setGoodsFile(goodsFile);
							zcStorehouse.setBranch(branch);
							zcStorehouse.setStore("0");
							zcStorehouse.setStoreMoney("0");
							checkDifferenceService.saveObj(zcStorehouse);
						}
					}
				}
				checkDifference.setStatus(Constant.CHECK_STATUS_PASS);
				Long sums = checkDifferenceService.getCountByObj(
						ZcCheckDifference.class, "checkNumber_id='"
								+ checkNumber.getId() + "' and status='" + 2
								+ "'");
				Long nums = checkDifferenceService.getCountByObj(
						ZcWarehouse.class,
						"checkNumber_id='" + checkNumber.getId() + "'");
				if (sums == nums && nums != 0 && sums != 0) {
					checkNumber.setStatus(Constant.CHECK_STATUS_PASS);
				}
				// 更改仓库库存
				if (StringUtils.isBlank(rows)) {

				} else {
					JSONArray jsonArray = JSONArray.fromObject(rows);
					List<Map<String, Object>> list = jsonArray;
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							String id = (String) list.get(i).get("ID");
							String resason = "";
							if (list.get(i).get("DIFFERENCEREASON")
									.equals("null")) {
								resason = "";
							} else {
								resason = (String) list.get(i).get(
										"DIFFERENCEREASON");
							}
							String storeNumber = "";
							if (list.get(i).get("ACTUALCHECKNUMBER")
									.equals("null")) {
								storeNumber = "0.00";
							} else {
								storeNumber = (String) list.get(i).get(
										"ACTUALCHECKNUMBER");
							}
							String storeOldNumber = "";
							if (list.get(i).get("STORE").equals("null")) {
								storeOldNumber = "0.00";
							} else {
								storeOldNumber = (String) (list.get(i)
										.get("STORE"));
							}
							CheckGoodsItems checkGoodsItems = (CheckGoodsItems) checkDifferenceService
									.getObjById(id, "CheckGoodsItems");
							if (checkGoodsItems == null) {
								checkGoodsItems = new CheckGoodsItems();
							}
							checkGoodsItems.setStore(storeOldNumber);
							checkDifferenceService.updateObj(checkGoodsItems);
							GoodsFile goodsFile = checkGoodsItems
									.getGoodsFile();
							if (goodsFile == null) {
								goodsFile = new GoodsFile();
							}
							String goodsFileId = goodsFile.getId();
							Long count = checkDifferenceService.getCountByObj(
									ZcStorehouse.class, "branch_id='"
											+ branchId + "' and goodsfile_id='"
											+ goodsFileId + "'");
							if (count == 0) {
								ZcStorehouse zcStorehouse = new ZcStorehouse();
								String zcStorehouseId = UuidUtils.getUUID();
								zcStorehouse.setId(zcStorehouseId);
								zcStorehouse.setBranch(branch);
								zcStorehouse.setGoodsFile(goodsFile);
								zcStorehouse.setCreateUser(ctpUser);
								zcStorehouse.setStore(storeNumber);
								zcStorehouse.setStoreMoney("0");
								zcStorehouse.setStatus(1);
								checkDifferenceService.saveObj(zcStorehouse);
							} else {
								List<ZcStorehouse> storehouses = checkDifferenceService
										.getListByObj(
												ZcStorehouse.class,
												"branch_id='"
														+ branchId
														+ "' and goodsfile_id='"
														+ goodsFileId + "'");
								if (storehouses != null
										&& storehouses.size() > 0) {
									ZcStorehouse zcStorehouse = storehouses
											.get(0);
									zcStorehouse.setStore(storeNumber);
									checkDifferenceService
											.updateObj(zcStorehouse);
								}

							}
						}
					}
				}
				logManageService.insertLog(request, "盘点差异单审核通过", "差异盘点");
				logManageService.insertLog(request, "修改了商品库存", "库存调整");
				// 审核不通过
			} else if ("3".equals(type)) {
				checkNumber.setStatus(Constant.CHECK_STATUS_NOPASS);
				checkNumber.setRemark(reason);
				checkDifference.setStatus(Constant.CHECK_STATUS_NOPASS);
				logManageService.insertLog(request, "盘点差异单审核未通过", "差异盘点");
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
			checkDifferenceService.updateObj(checkNumber);
			checkDifferenceService.updateObj(checkDifference);
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
	public AjaxResult update(
			@ModelAttribute ZcCheckDifference zcCheckDifference,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			checkDifferenceService.updateObj(zcCheckDifference);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listItemJson(
			@ModelAttribute ZcCheckDifference zcCheckDifference, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DecimalFormat df = new DecimalFormat("######0.00");
		DataGrid dataGrid = null;
		// 系统库存合计
		double totalstore = 0;
		// 盘点数量合计
		double totalcheck = 0;
		// 盘点金额合计
		double totalMoney = 0;
		// 库存金额合计
		double totalstoreMoney = 0;
		// 盈亏金额合计
		double addOrReduceMoney = 0;
		Map<String, Object> result = new HashMap();
		try {
			dataGrid = checkDifferenceService.getPagedDataGridObjById(page,
					zcCheckDifference, id);
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					// 库存数量
					String storeNumber = (String) map.get("STORE");
					if (StringUtils.isBlank(storeNumber)) {
						storeNumber = "0.00";
					}

					// 盘点数量
					String checkNumber = (String) map.get("ACTUALCHECKNUMBER");
					if (StringUtils.isBlank(checkNumber)) {
						checkNumber = "0.00";
					}
					// 单价
					Object object = map.get("GOODS_PRICE");
					double singlePrice = Double.parseDouble(object.toString());
					double storeNum = Double.valueOf(storeNumber);
					double checkNum = Double.valueOf(checkNumber);
					double singlePri = Double.valueOf(singlePrice);
					// 盈亏数量
					double addOrReduceNum = checkNum - storeNum;
					// 盘点金额
					double checkPrice = checkNum * singlePri;
					// 原库存金额
					double storePrice = storeNum * singlePri;
					// 盈亏金额
					double addOrReducePrice = checkPrice - storePrice;
					totalstore = totalstore + storeNum;
					totalcheck = totalcheck + checkNum;
					totalMoney = totalMoney + checkPrice;
					totalstoreMoney = totalstoreMoney + storePrice;
					addOrReduceMoney = addOrReduceMoney + addOrReducePrice;
					list.get(i)
							.put("addOrReduceNum", df.format(addOrReduceNum));
					list.get(i).put("checkPrice", df.format(checkPrice));
					list.get(i).put("storePrice", df.format(storePrice));
					list.get(i).put("addOrReducePrice",
							df.format(addOrReducePrice));
				}
				result.put("GOODS_NAME",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>合计:</span>");
				result.put("STORE",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>"
								+ df.format(totalstore) + "</span>");
				result.put("ACTUALCHECKNUMBER",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>"
								+ df.format(totalcheck) + "</span>");
				if (totalMoney >= 0) {
					result.put(
							"checkPrice",
							"<span style='color:blue;font-weight:700 '>"
									+ df.format(totalMoney) + "</span>");
				} else if (totalMoney < 0) {
					result.put(
							"checkPrice",
							"<span style='color:red;font-weight:700 '>"
									+ df.format(totalMoney) + "</span>");
				}

				result.put(
						"storePrice",
						"<span style='color:blue;font-weight:700 '>"
								+ df.format(totalstoreMoney) + "</span>");
				if (addOrReduceMoney >= 0) {
					result.put("addOrReducePrice",
							"<span style='font-family:华文中宋; color:blue;font-weight:900 '>+"
									+ df.format(addOrReduceMoney) + "</span>");
				} else if (addOrReduceMoney < 0) {
					result.put("addOrReducePrice",
							"<span style='font-family:华文中宋; color:red;font-weight:900 '>-"
									+ df.format(addOrReduceMoney) + "</span>");
				}

				List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
				totalList.add(result);
				dataGrid.setFooter(totalList);
				dataGrid.setRows(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping("gotoPrintCheckDifference")
	public ModelAndView gotoPrintCheckDifference(
			@ModelAttribute ZcCheckDifference zcCheckDifference,
			HttpServletRequest request, HttpServletResponse response,
			Model model, Page page) {
		page.setPage(1);
		page.setRows(1000);
		String id = request.getParameter("id");
		ZcCheckDifference checkDifference = (ZcCheckDifference) checkDifferenceService
				.getObjById(id, "ZcCheckDifference");
		DecimalFormat df = new DecimalFormat("######0.00");
		DataGrid dataGrid = null;
		// 系统库存合计
		double totalstore = 0;
		// 盘点数量合计
		double totalcheck = 0;
		// 盘点金额合计
		double totalMoney = 0;
		// 库存金额合计
		double totalstoreMoney = 0;
		// 盈亏金额合计
		double addOrReduceMoney = 0;
		Map<String, Object> result = new HashMap();
		try {
			dataGrid = checkDifferenceService.getPagedDataGridObjById(page,
					zcCheckDifference, id);
			List<Map<String, Object>> list = dataGrid.getRows();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					// 库存数量
					String storeNumber = (String) map.get("STORE");
					if (StringUtils.isBlank(storeNumber)) {
						storeNumber = "0.00";
					}

					// 盘点数量
					String checkNumber = (String) map.get("ACTUALCHECKNUMBER");
					if (StringUtils.isBlank(checkNumber)) {
						checkNumber = "0.00";
					}
					// 单价
					Object object = map.get("GOODS_PRICE");
					double singlePrice = Double.parseDouble(object.toString());
					double storeNum = Double.valueOf(storeNumber);
					double checkNum = Double.valueOf(checkNumber);
					double singlePri = Double.valueOf(singlePrice);
					// 盈亏数量
					double addOrReduceNum = checkNum - storeNum;
					// 盘点金额
					double checkPrice = checkNum * singlePri;
					// 原库存金额
					double storePrice = storeNum * singlePri;
					// 盈亏金额
					double addOrReducePrice = checkPrice - storePrice;
					totalstore = totalstore + storeNum;
					totalcheck = totalcheck + checkNum;
					totalMoney = totalMoney + checkPrice;
					totalstoreMoney = totalstoreMoney + storePrice;
					addOrReduceMoney = addOrReduceMoney + addOrReducePrice;
					list.get(i)
							.put("addOrReduceNum", df.format(addOrReduceNum));
					list.get(i).put("checkPrice", df.format(checkPrice));
					list.get(i).put("storePrice", df.format(storePrice));
					list.get(i).put("addOrReducePrice",
							df.format(addOrReducePrice));
				}
				result.put("GOODS_NAME",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>合计:</span>");
				result.put("STORE",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>"
								+ df.format(totalstore) + "</span>");
				result.put("ACTUALCHECKNUMBER",
						"<span style='font-family:华文中宋; color:blue;font-weight:900 '>"
								+ df.format(totalcheck) + "</span>");
				if (totalMoney >= 0) {
					result.put(
							"checkPrice",
							"<span style='color:blue;font-weight:700 '>"
									+ df.format(totalMoney) + "</span>");
				} else if (totalMoney < 0) {
					result.put(
							"checkPrice",
							"<span style='color:red;font-weight:700 '>"
									+ df.format(totalMoney) + "</span>");
				}

				result.put(
						"storePrice",
						"<span style='color:blue;font-weight:700 '>"
								+ df.format(totalstoreMoney) + "</span>");
				if (addOrReduceMoney >= 0) {
					result.put("addOrReducePrice",
							"<span style='font-family:华文中宋; color:blue;font-weight:900 '>"
									+ df.format(addOrReduceMoney) + "</span>");
				} else if (addOrReduceMoney < 0) {
					result.put("addOrReducePrice",
							"<span style='font-family:华文中宋; color:red;font-weight:900 '>"
									+ df.format(addOrReduceMoney) + "</span>");
				}

				List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();
				totalList.add(result);
				dataGrid.setFooter(totalList);
				dataGrid.setRows(list);
			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("checkDifference", checkDifference);
		ModelAndView view = createIframeView("warehouse/checkdifference/checkdifference_print");
		return view;
	}

	/**
	 * 删除盘点差异单
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
			if (id != null && id != "") {
				checkDifferenceService.deleteObjById(id,
						ZcCheckDifference.class.getName());
				logManageService.insertLog(request, "删除了勾选的盘点差异单", "盘点差异");
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
