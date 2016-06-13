package com.proem.exm.controller.warehouse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.Region;
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
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.utils.PurchaseGoodsCompare;
import com.proem.exm.entity.warehouse.ChangeGoodsItems;
import com.proem.exm.entity.warehouse.SwitchGoodsItems;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.warehouse.ZcSwitchhouse;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.warehouse.SwitchChangeService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.ConstantExportExcelName;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 
 * @author zhusf 盘点号的控制层
 */
@Controller
@RequestMapping("/switchChange")
public class SwitchChangeController extends BaseController {
	@Autowired
	SwitchChangeService switchChangeService;
	@Autowired
	GoodsFileService goodsFileService;

	@InitBinder("zcSwitchhouse")
	public void initZcCheckNumber(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcSwitchhouse.");
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
	 * @throws UnsupportedEncodingException
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
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_list");
		return view;
	}

	/**
	 * 打开新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "gotoAddChange")
	public ModelAndView gotoAddChange(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		List<SwitchGoodsItems> switchGoodsItemsList = switchChangeService
				.getListByObj(SwitchGoodsItems.class,
						" zcSwitchhouse_id is null ");
		if (switchGoodsItemsList != null && switchGoodsItemsList.size() > 0) {
			for (int i = 0; i < switchGoodsItemsList.size(); i++) {
				switchChangeService.deleteObjById(switchGoodsItemsList.get(i)
						.getId(), SwitchGoodsItems.class.getName());
			}
		}
		// Page page=new Page();
		// page.setPage(1);
		// page.setRows(10000);
		// DataGrid dataGrid = switchChangeService.getChangeAddGoods(page,null,
		// null, user.getCtpUser());
		// if(dataGrid.getRows()==null || dataGrid.getRows().size()==0){
		// SwitchGoodsItems switchGoodsItems=new SwitchGoodsItems();
		// String id=UuidUtils.getUUID();
		// switchGoodsItems.setId(id);
		// switchGoodsItems.setCreateUser(user.getCtpUser());
		// switchChangeService.saveObj(switchGoodsItems);
		// }
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_add");
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
		ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) switchChangeService
				.getObjById(id, "ZcSwitchhouse");
		model.addAttribute("zcSwitchhouse", zcSwitchhouse);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_edit");
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
		ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) switchChangeService
				.getObjById(id, "ZcSwitchhouse");
		model.addAttribute("zcSwitchhouse", zcSwitchhouse);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_detail");
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
		ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) switchChangeService
				.getObjById(id, "ZcSwitchhouse");
		model.addAttribute("zcSwitchhouse", zcSwitchhouse);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_check");
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
	@RequestMapping(value = "listJson", produces = "application/json")
	@ResponseBody
	public DataGrid listCheckNumberJson(
			@ModelAttribute ZcSwitchhouse zcSwitchhouse,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = switchChangeService.getPagedDataGridObj(page,
					zcSwitchhouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 新增保存
	 * 
	 * @param rows
	 * @param changeId
	 * @param zcSwitchhouse
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNumber(String changeId, ZcSwitchhouse zcSwitchhouse,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String remarks = request.getParameter("remarks");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		try {
			if (StringUtils.isBlank(ids)) {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
			} else {
				Long count = switchChangeService.getCountByObj(
						ZcSwitchhouse.class, "switch_Number = '"
								+ zcSwitchhouse.getSwitch_Number() + "'");
				String str = "";
				if (count != 0) {
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyyMMddHHmmssSSS");
					Date date = new Date();
					str = formatDate.format(date);
					zcSwitchhouse.setSwitch_Number("ZCD" + str);
				}
				changeId = UuidUtils.getUUID();
				zcSwitchhouse.setId(changeId);
				switchChangeService.saveObj(zcSwitchhouse);
				if (idStr != null && idStr.length > 0) {
					for (int i = 0; i < idStr.length; i++) {
						String id = idStr[i];
						String changeNumber = num[i];
						if (StringUtils.isBlank(changeNumber)) {
							changeNumber = "0.00";
						}
						SwitchGoodsItems checkGoodsItems = (SwitchGoodsItems) switchChangeService
								.getObjById(id, "SwitchGoodsItems");
						if (checkGoodsItems == null) {
							checkGoodsItems = new SwitchGoodsItems();
						}
						float price = checkGoodsItems.getGoodsFile()
								.getGoods_price();
						double amount = Double.valueOf(changeNumber) * price;
						checkGoodsItems.setChangeNumber(changeNumber);
						checkGoodsItems.setChangeAmount(String.valueOf(amount));
						if (remarks != null && remarks != "") {
							String[] remark = remarks.split(",");
							checkGoodsItems.setRemark(remark[i]);
						}
						checkGoodsItems.setZcSwitchhouse(zcSwitchhouse);
						switchChangeService.updateObj(checkGoodsItems);
					}
					zcSwitchhouse.setStatus(Constant.CHECK_STATUS_UNDO);
					switchChangeService.updateObj(zcSwitchhouse);
				}
				logManageService.insertLog(request, "新增了转仓单", "转仓查询");
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
	 * 编辑调整单
	 * 
	 * @param zcCheckNumber
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateNumber(String changeId,
			@ModelAttribute ZcSwitchhouse zcSwitchhouse,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResult ajaxResult = null;
		String remarks = request.getParameter("remarks");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("nums");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		try {
			if (StringUtils.isBlank(ids)) {

			} else {
				if (idStr != null && idStr.length > 0) {
					zcSwitchhouse.setStatus(Constant.CHECK_STATUS_WAITCHECK);
					switchChangeService.updateObj(zcSwitchhouse);
					for (int i = 0; i < idStr.length; i++) {
						String id = idStr[i];
						String changeNumber = num[i];
						if (StringUtils.isBlank(changeNumber)) {
							changeNumber = "0.00";
						}
						// String changeAmount=(String)
						// list.get(i).get("CHANGEAMOUNT");
						SwitchGoodsItems checkGoodsItems = (SwitchGoodsItems) switchChangeService
								.getObjById(id, "SwitchGoodsItems");
						if (checkGoodsItems == null) {
							checkGoodsItems = new SwitchGoodsItems();
						}
						float price = checkGoodsItems.getGoodsFile()
								.getGoods_price();
						double amount = Double.valueOf(changeNumber) * price;
						checkGoodsItems.setChangeNumber(changeNumber);
						checkGoodsItems.setChangeAmount(String.valueOf(amount));
						if (remarks != null && remarks != "") {
							String[] remark = remarks.split(",");
							checkGoodsItems.setRemark(remark[i]);
						}
						checkGoodsItems.setZcSwitchhouse(zcSwitchhouse);
						switchChangeService.updateObj(checkGoodsItems);
					}
				}
				logManageService.insertLog(request, "修改了转仓单", "转仓查询");
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
	 * 
	 * @param rows
	 * @param differenceId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkChange", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkDifference(String type, String changeId,
			String rows, String reason, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) switchChangeService
					.getObjById(changeId, "ZcSwitchhouse");
			Branch fromBranch = zcSwitchhouse.getFromBranch();
			Branch toBranch = zcSwitchhouse.getToBranch();
			String branchId = fromBranch.getId();
			String tobranchId = toBranch.getId();
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			// 审核通过
			if ("2".equals(type)) {
				// 更改仓库库存
				if (StringUtils.isBlank(rows)) {
					ajaxResult = new AjaxResult(AjaxResult.SAVE,
							AjaxResult.FAIL, AjaxResult.INFO);
				} else {
					JSONArray jsonArray = JSONArray.fromObject(rows);
					List<Map<String, Object>> list = jsonArray;
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							String id = (String) list.get(i).get("ID");
							String prices = list.get(i).get("GOODS_PRICE")
									.toString();
							double price = Double.valueOf(prices);
							String storeNumber = "";
							double storeResult = 0;
							if (list.get(i).get("STORE").equals("null")) {
								storeNumber = "0.00";
							} else {
								storeNumber = (String) (list.get(i)
										.get("STORE"));
							}
							double storeNum = Double.valueOf(storeNumber);
							String changeNumber = (String) list.get(i).get(
									"CHANGENUMBER");
							double changeNum = Double.valueOf(changeNumber);
							storeResult = storeNum - changeNum;
							if (storeResult < 0) {
								ajaxResult = new AjaxResult(AjaxResult.SAVE,
										AjaxResult.ERROR, AjaxResult.INFO);
								return ajaxResult;
							}
							SwitchGoodsItems checkGoodsItems = (SwitchGoodsItems) switchChangeService
									.getObjById(id, "SwitchGoodsItems");
							if (checkGoodsItems == null) {
								checkGoodsItems = new SwitchGoodsItems();
							}
							GoodsFile goodsFile = checkGoodsItems
									.getGoodsFile();
							if (goodsFile == null) {
								goodsFile = new GoodsFile();
							}
							String goodsFileId = goodsFile.getId();
							Long count = switchChangeService.getCountByObj(
									ZcStorehouse.class, "branch_id='"
											+ branchId + "' and goodsfile_id='"
											+ goodsFileId + "'");
							Long countTo = switchChangeService.getCountByObj(
									ZcStorehouse.class, "branch_id='"
											+ tobranchId
											+ "' and goodsfile_id='"
											+ goodsFileId + "'");
							if (count == 0) {

							} else {
								List<ZcStorehouse> storehouses = switchChangeService
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
									zcStorehouse
											.setStoreMoney((storeResult * price)
													+ "");
									zcStorehouse.setStore(String
											.valueOf(storeResult));
									switchChangeService.updateObj(zcStorehouse);
								}

							}
							if (countTo == 0) {
								ZcStorehouse zcStorehouse = new ZcStorehouse();
								String zcStorehouseId = UuidUtils.getUUID();
								zcStorehouse.setId(zcStorehouseId);
								zcStorehouse.setBranch(toBranch);
								zcStorehouse.setGoodsFile(goodsFile);
								zcStorehouse.setCreateUser(ctpUser);
								zcStorehouse.setStore(changeNumber);
								zcStorehouse.setStoreMoney((Double
										.valueOf(changeNumber) * price) + "");
								zcStorehouse.setStatus(1);
								switchChangeService.saveObj(zcStorehouse);
							} else {
								List<ZcStorehouse> storehouses = switchChangeService
										.getListByObj(
												ZcStorehouse.class,
												"branch_id='"
														+ tobranchId
														+ "' and goodsfile_id='"
														+ goodsFileId + "'");
								if (storehouses != null
										&& storehouses.size() > 0) {
									ZcStorehouse zcStorehouse = storehouses
											.get(0);
									String tostoreNumber = zcStorehouse
											.getStore() == null ? "0.00"
											: zcStorehouse.getStore();
									double tostoreNum = Double
											.valueOf(tostoreNumber);
									double totalNum = tostoreNum + changeNum;
									zcStorehouse.setStore(String
											.valueOf(totalNum));
									if (zcStorehouse.getStoreMoney() == null) {
										zcStorehouse.setStoreMoney("0");
									}
									zcStorehouse.setStoreMoney((Double
											.valueOf(zcStorehouse
													.getStoreMoney()) + (Double
											.valueOf(changeNumber) * price))
											+ "");
									switchChangeService.updateObj(zcStorehouse);
								}

							}
						}
					}
				}
				zcSwitchhouse.setStatus(Constant.CHECK_STATUS_PASS);
				zcSwitchhouse.setCheckUser(ctpUser);
				zcSwitchhouse.setCheckDate(new Date());
				logManageService.insertLog(request, "转仓单审核通过并调整了库存", "转仓查询");
				logManageService.insertLog(request, "通过转仓单修改了库存", "库存调整");
				// 审核不通过
			} else if ("3".equals(type)) {
				zcSwitchhouse.setStatus(Constant.CHECK_STATUS_NOPASS);
				zcSwitchhouse.setRemark(reason);
				zcSwitchhouse.setCheckUser(ctpUser);
				zcSwitchhouse.setCheckDate(new Date());
				logManageService.insertLog(request, "转仓单审核未通过", "转仓查询");
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
			switchChangeService.updateObj(zcSwitchhouse);
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
	 * 打开新增商品时选择商品的面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("openChoseGoods")
	public ModelAndView openChoseGoods(String rows, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(rows);
		List<Map<String, Object>> list = jsonArray;
		String remarks = request.getParameter("remarks");
		String fromBranchId = request.getParameter("fromBranchId");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String id = (String) list.get(i).get("ID");
				String changeNumber = (String) list.get(i).get("CHANGENUMBER");
				if (StringUtils.isBlank(changeNumber)) {
					changeNumber = "0.00";
				}
				SwitchGoodsItems checkGoodsItems = (SwitchGoodsItems) switchChangeService
						.getObjById(id, "SwitchGoodsItems");
				if (checkGoodsItems == null) {
					checkGoodsItems = new SwitchGoodsItems();
				}
				float price = checkGoodsItems.getGoodsFile().getGoods_price();
				double amount = Double.valueOf(changeNumber) * price;
				checkGoodsItems.setChangeNumber(changeNumber);
				checkGoodsItems.setChangeAmount(String.valueOf(amount));
				if (remarks != null && remarks != "") {
					String[] remark = remarks.split(",");
					checkGoodsItems.setRemark(remark[i]);
				}
				switchChangeService.updateObj(checkGoodsItems);
			}
		}
		model.addAttribute("fromBranchId", fromBranchId);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_choseGoods");
		return view;
	}

	/**
	 * 分页查询商品表所有信息
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listGoodsMasterJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsMasterJson(@ModelAttribute GoodsFile goodsFile,
			String switchOutBranch, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = switchChangeService.getSwitchChangeItems(page,
					goodsFile, switchOutBranch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(
			@ModelAttribute SwitchGoodsItems switchGoodsItems, String changeId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String changgeNums = request.getParameter("changgeNums");
		String[] changgeNum = changgeNums.split(",");
		String[] idStr = ids.split(",");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					SwitchGoodsItems purchaseOrderGoodsItems = new SwitchGoodsItems();
					String id = idStr[i];
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(id, "GoodsFile");
					String serial = goodsFile.getSerialNumber();
					Long count = null;
					if (StringUtils.isBlank(changeId)) {
						count = goodsFileService.getCountByObj(
								SwitchGoodsItems.class,
								"ZCSWITCHHOUSE_ID is null and goodsfile_id='"
										+ id + "'");
					} else {
						count = goodsFileService.getCountByObj(
								SwitchGoodsItems.class, "ZCSWITCHHOUSE_ID ='"
										+ changeId + "'and goodsfile_id='" + id
										+ "'");
					}
					if (count != 0) {
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.ERROR, AjaxResult.INFO);
						return ajaxResult;
					} else {
						Float onePrice = goodsFile.getGoods_price();
						BigDecimal one = new BigDecimal(
								String.valueOf(onePrice));
						double goodsPrice = one.doubleValue();
						if (goodsFile != null) {
							String goodsId = UuidUtils.getUUID();
							purchaseOrderGoodsItems.setId(goodsId);
							if (!StringUtils.isBlank(changeId)) {
								ZcSwitchhouse storeChange = (ZcSwitchhouse) goodsFileService
										.getObjById(changeId, "ZcSwitchhouse");
								purchaseOrderGoodsItems
										.setZcSwitchhouse(storeChange);
							}
							purchaseOrderGoodsItems.setGoodsFile(goodsFile);
							purchaseOrderGoodsItems.setCreateUser(user);
							purchaseOrderGoodsItems
									.setChangeNumber(changgeNum[i]);
							goodsFileService.saveObj(purchaseOrderGoodsItems);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到临时转仓表", "库存调整");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 删除
	@RequestMapping(value = "deleteChose", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteChose(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					switchChangeService.deleteObjById(ids[i],
							ChangeGoodsItems.class.getName());
				}
				logManageService.insertLog(request, "移除了装仓单的商品", "转仓查询");
				ajaxResult = new AjaxResult(AjaxResult.DELETE,
						AjaxResult.SUCCESS, AjaxResult.INFO);
			} else {
				ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
						AjaxResult.INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "listGoodsItemsNullOrderJson", produces = "application/json")
	@ResponseBody
	public DataGrid listGoodsItemsNullOrderJson(
			@ModelAttribute SwitchGoodsItems switchGoodsItems, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = switchChangeService.getChangeAddGoods(page, id,
					switchGoodsItems, ctpUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping("gotoPrintSwitchChange")
	public ModelAndView gotoPrintSwitchChange(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		List<SwitchGoodsItems> switchGoodsItemsList = switchChangeService
				.getListByObj(SwitchGoodsItems.class, "zcSwitchhouse_id = '"
						+ id + "'");
		List<SwitchGoodsItems> goodsItems = new ArrayList<SwitchGoodsItems>();
		String[] guige = null;
		if (switchGoodsItemsList != null && switchGoodsItemsList.size() > 0) {
			for (int i = 0; i < switchGoodsItemsList.size(); i++) {
				SwitchGoodsItems switchGoodsItems = switchGoodsItemsList.get(i);
				if (switchGoodsItems.getGoodsFile().getGoods_specifications() != null
						&& switchGoodsItems.getGoodsFile()
								.getGoods_specifications() != "") {
					guige = switchGoodsItems.getGoodsFile()
							.getGoods_specifications().split("商品规格：");
					if (guige != null && guige.length > 1) {
						switchGoodsItems.getGoodsFile()
								.setGoods_specifications(guige[1]);
					} else if (guige != null && guige.length == 1) {
						switchGoodsItems.getGoodsFile()
								.setGoods_specifications(guige[0]);
					}
				} else {
					switchGoodsItems.getGoodsFile().setGoods_specifications("");
				}
				goodsItems.add(switchGoodsItems);
			}
		}
		ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) switchChangeService
				.getObjById(id, "ZcSwitchhouse");
		model.addAttribute("zcSwitchhouse", zcSwitchhouse);
		model.addAttribute("switchGoodsItemsList", goodsItems);
		ModelAndView view = createIframeView("warehouse/switchchange/switchchange_print");
		return view;
	}

	/**
	 * 删除新增、修改页面的商品
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
			for (int i = 0; i < ids.length; i++) {
				switchChangeService.deleteObjById(ids[i],
						SwitchGoodsItems.class.getName());
			}
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
	 * list页面删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteOrder(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			List<SwitchGoodsItems> switchGoodsItemsList = switchChangeService
					.getListByObj(SwitchGoodsItems.class,
							" zcSwitchhouse_id = '" + id + "'");
			if (switchGoodsItemsList != null && switchGoodsItemsList.size() > 0) {
				for (int j = 0; j < switchGoodsItemsList.size(); j++) {
					SwitchGoodsItems switchGoodsItems = switchGoodsItemsList
							.get(j);
					switchChangeService.deleteObjById(switchGoodsItems.getId(),
							SwitchGoodsItems.class.getName());
				}
			}
			switchChangeService
					.deleteObjById(id, ZcSwitchhouse.class.getName());
			logManageService.insertLog(request, "删除了勾选的库存调整单", "库存调整单");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}
	
	
}
