package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ChangeGoodsItems;
import com.proem.exm.entity.warehouse.ZcStoreChange;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.warehouse.StoreChangeService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 
 * @author zhusf 盘点号的控制层
 */
@Controller
@RequestMapping("/storeChange")
public class StoreChangeController extends BaseController {
	@Autowired
	StoreChangeService storeChangeService;
	@Autowired
	GoodsFileService goodsFileService;

	@InitBinder("zcStoreChange")
	public void initZcCheckNumber(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("zcStoreChange.");
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
		faName = new String(faName.getBytes("iso8859-1"), "utf-8");
		String fasonName = request.getParameter("fasonName");
		fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		String sonName = request.getParameter("sonName");
		sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_list");
		return view;
	}

	/**
	 * 打开填写/修改的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "gotoAddChange")
	public ModelAndView gotoAddChange(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		List<ChangeGoodsItems> changeGoodsItemsList = storeChangeService
				.getListByObj(ChangeGoodsItems.class,
						" storeChange_id is null ");
		if (changeGoodsItemsList != null && changeGoodsItemsList.size() > 0) {
			for (int i = 0; i < changeGoodsItemsList.size(); i++) {
				ChangeGoodsItems changeGoodsItems = changeGoodsItemsList.get(i);
				storeChangeService.deleteObjById(changeGoodsItems.getId(),
						ChangeGoodsItems.class.getName());
			}
		}
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_add");
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
		ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
				.getObjById(id, "ZcStoreChange");
		List<ChangeGoodsItems> changeGoodsItemsList = storeChangeService
				.getListByObj(ChangeGoodsItems.class, " editFlag='1'");
		if (changeGoodsItemsList != null && changeGoodsItemsList.size() > 0) {
			for (int i = 0; i < changeGoodsItemsList.size(); i++) {
				ChangeGoodsItems changeGoodsItems = changeGoodsItemsList.get(i);
				storeChangeService.deleteObjById(changeGoodsItems.getId(),
						ChangeGoodsItems.class.getName());
			}
		}
		model.addAttribute("storeChange", storeChange);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_edit");
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
		ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
				.getObjById(id, "ZcStoreChange");
		model.addAttribute("storeChange", storeChange);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_detail");
		return view;
	}

	@RequestMapping(value = "gotoPrint")
	public ModelAndView gotoPrint(HttpServletRequest request,
			HttpServletResponse response, Model model, Page page) {
		String id = request.getParameter("id");
		DataGrid dataGrid = null;
		page.setRows(5000);
		page.setPage(1);
		ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
				.getObjById(id, "ZcStoreChange");
		ChangeGoodsItems changeGoodsItems = new ChangeGoodsItems();
		List<Map<String, Object>> printList = null;
		try {
			dataGrid = storeChangeService.getChangeAddGoods(page, id,
					changeGoodsItems, storeChange.getCreateUser());
			printList = dataGrid.getRows();
			if (printList != null && printList.size() > 0) {
				for (int i = 0; i < printList.size(); i++) {
					String guige = (String) printList.get(i).get(
							"GOODS_SPECIFICATIONS");
					if (guige != null && guige != "") {
						String[] shangpinguige = guige.split("商品规格：");
						if (shangpinguige != null && shangpinguige.length > 1) {
							printList.get(i).put("GOODS_SPECIFICATIONS",
									shangpinguige[1]);
						} else if (shangpinguige != null
								&& shangpinguige.length == 1) {
							printList.get(i).put("GOODS_SPECIFICATIONS",
									shangpinguige[0]);
						}
					} else {
						printList.get(i).put("GOODS_SPECIFICATIONS", "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("storeChange", storeChange);
		model.addAttribute("storeChangeId", id);
		model.addAttribute("list", printList);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_print");
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
		ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
				.getObjById(id, "ZcStoreChange");
		model.addAttribute("storeChange", storeChange);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_check");
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
			@ModelAttribute ZcStoreChange zcStoreChange,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = storeChangeService.getPagedDataGridObj(page,
					zcStoreChange);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveNumber(String changeId, ZcStoreChange zcStoreChange,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResult ajaxResult = null;
		String remarks = request.getParameter("remarks");
		String changeNums = request.getParameter("changeNums");
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		String[] changeNum = changeNums.split(",");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		try {
			Long count = storeChangeService.getCountByObj(
					ZcStoreChange.class,
					"storeChange_Number = '"
							+ zcStoreChange.getStoreChange_Number() + "'");
			String str = "";
			if (count != 0) {
				SimpleDateFormat formatDate = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Date date = new Date();
				str = formatDate.format(date);
				zcStoreChange.setStoreChange_Number("TZD" + str);
			}
			changeId = UuidUtils.getUUID();
			zcStoreChange.setId(changeId);
			storeChangeService.saveObj(zcStoreChange);
			double changeMoney = 0;
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					String id = idStr[i];
					String changeNumber = changeNum[i];
					if (StringUtils.isBlank(changeNumber)) {
						changeNumber = "0.00";
					}
					// String changeAmount=(String)
					// list.get(i).get("CHANGEAMOUNT");
					ChangeGoodsItems checkGoodsItems = (ChangeGoodsItems) storeChangeService
							.getObjById(id, "ChangeGoodsItems");
					if (checkGoodsItems == null) {
						checkGoodsItems = new ChangeGoodsItems();
					}
					float price = checkGoodsItems.getGoodsFile()
							.getGoods_price();
					double amount = Double.valueOf(changeNumber) * price;
					changeMoney += amount;
					checkGoodsItems.setChangeNumber(changeNumber);
					checkGoodsItems.setChangeAmount(String.valueOf(amount));
					if (remarks != null && remarks != "") {
						String[] remark = remarks.split(",");
						checkGoodsItems.setRemark(remark[i]);
					}
					checkGoodsItems.setStoreChange(zcStoreChange);
					checkGoodsItems.setEditFlag("");
					storeChangeService.updateObj(checkGoodsItems);
				}
				zcStoreChange.setStatus(Constant.CHECK_STATUS_UNDO);
				zcStoreChange.setChangeAmount(String.valueOf(changeMoney));
				storeChangeService.updateObj(zcStoreChange);
			}
			logManageService.insertLog(request, "新增了库存调整单", "库存调整");
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
			@ModelAttribute ZcStoreChange zcStoreChange,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxResult ajaxResult = null;
		String remarks = request.getParameter("remarks");
		String changeNums = request.getParameter("changeNums");
		String ids = request.getParameter("ids");
		String[] changeNum = changeNums.split(",");
		String[] idStr = ids.split(",");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		try {
			double changeMoney = 0;
			if (idStr != null && idStr.length > 0) {
				zcStoreChange.setStatus(Constant.CHECK_STATUS_WAITCHECK);
				storeChangeService.updateObj(zcStoreChange);
				for (int i = 0; i < idStr.length; i++) {
					String id = idStr[i];
					String changeNumber = changeNum[i];
					if (StringUtils.isBlank(changeNumber)) {
						changeNumber = "0.00";
					}
					// String changeAmount=(String)
					// list.get(i).get("CHANGEAMOUNT");
					ChangeGoodsItems checkGoodsItems = (ChangeGoodsItems) storeChangeService
							.getObjById(id, "ChangeGoodsItems");
					if (checkGoodsItems == null) {
						checkGoodsItems = new ChangeGoodsItems();
					}
					float price = checkGoodsItems.getGoodsFile()
							.getGoods_price();
					double amount = Double.valueOf(changeNumber) * price;
					changeMoney += amount;
					checkGoodsItems.setChangeNumber(changeNumber);
					checkGoodsItems.setChangeAmount(String.valueOf(amount));
					if (remarks != null && remarks != "") {
						String[] remark = remarks.split(",");
						checkGoodsItems.setRemark(remark[i]);
					}
					checkGoodsItems.setEditFlag("");
					checkGoodsItems.setStoreChange(zcStoreChange);
					storeChangeService.updateObj(checkGoodsItems);
				}
				zcStoreChange.setChangeAmount(String.valueOf(changeMoney));
				storeChangeService.updateObj(zcStoreChange);

			}
			logManageService.insertLog(request, "修改了库存调整单", "库存调整");
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
			ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
					.getObjById(changeId, "ZcStoreChange");
			Branch branch = storeChange.getBranch();
			String branchId = branch.getId();
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
							String changeMoney = (String) list.get(i).get(
									"CHANGEMONEY");
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
							if ("1".equals(storeChange.getChangeType())) {
								storeResult = storeNum + changeNum;
							} else if ("2".equals(storeChange.getChangeType())) {
								storeResult = storeNum - changeNum;
								if (storeResult < 0) {
									ajaxResult = new AjaxResult(
											AjaxResult.SAVE, AjaxResult.ERROR,
											AjaxResult.INFO);
									return ajaxResult;
								}
							}
							ChangeGoodsItems checkGoodsItems = (ChangeGoodsItems) storeChangeService
									.getObjById(id, "ChangeGoodsItems");
							if (checkGoodsItems == null) {
								checkGoodsItems = new ChangeGoodsItems();
							}
							checkGoodsItems.setStore(storeNumber);
							storeChangeService.updateObj(checkGoodsItems);
							GoodsFile goodsFile = checkGoodsItems
									.getGoodsFile();
							if (goodsFile == null) {
								goodsFile = new GoodsFile();
							}
							String goodsFileId = goodsFile.getId();
							Long count = storeChangeService.getCountByObj(
									ZcStorehouse.class, "branch_id='"
											+ branchId + "' and goodsfile_id='"
											+ goodsFileId + "'");
							if (count == 0) {
								ZcStorehouse zcStorehouse = new ZcStorehouse();
								String zcStorehouseId = UuidUtils.getUUID();
								zcStorehouse.setId(zcStorehouseId);
								zcStorehouse.setBranch(branch);
								zcStorehouse.setGoodsFile(goodsFile);
								zcStorehouse.setStoreMoney(changeMoney);
								zcStorehouse.setCreateUser(ctpUser);
								zcStorehouse.setStore(String
										.valueOf(storeResult));
								zcStorehouse.setStatus(1);
								storeChangeService.saveObj(zcStorehouse);
							} else {
								List<ZcStorehouse> storehouses = storeChangeService
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
									if (zcStorehouse.getStoreMoney() == null) {
										zcStorehouse.setStoreMoney("0");
									}
									if ("1".equals(storeChange.getChangeType())) {
										zcStorehouse
												.setStoreMoney((Double.valueOf(zcStorehouse
														.getStoreMoney()) + Double
														.valueOf(changeMoney))
														+ "");
									} else if ("2".equals(storeChange
											.getChangeType())) {
										zcStorehouse
												.setStoreMoney((Double.valueOf(zcStorehouse
														.getStoreMoney()) - Double
														.valueOf(changeMoney))
														+ "");
									}
									zcStorehouse.setStore(String
											.valueOf(storeResult));
									storeChangeService.updateObj(zcStorehouse);
								}

							}
						}
					}
				}
				storeChange.setStatus(Constant.CHECK_STATUS_PASS);
				storeChange.setCheckUser(ctpUser);
				storeChange.setCheckDate(new Date());
				logManageService.insertLog(request, "库存调整单审核通过", "库存调整");
				logManageService.insertLog(request, "修改了商品库存", "库存调整");
				// 审核不通过
			} else if ("3".equals(type)) {
				storeChange.setStatus(Constant.CHECK_STATUS_NOPASS);
				if (reason != "" && reason != null) {
					storeChange.setRemark(reason);
				}
				storeChange.setCheckUser(ctpUser);
				storeChange.setCheckDate(new Date());
				logManageService.insertLog(request, "库存调整单审核未通过", "库存调整");
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
			storeChangeService.updateObj(storeChange);
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
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("openChoseGoods")
	public ModelAndView openChoseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String branchId = request.getParameter("branchId");
		String changeType = request.getParameter("changeType");
		String pyNum = request.getParameter("pyNum");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		String remarks = request.getParameter("remark");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		if (nums != null && nums != "" && ids != null && ids != ""
				&& remarks != null && remarks != "") {
			String[] num = nums.split(",");
			String[] idStr = ids.split(",");
			String[] remark = remarks.split(",");
			for (int i = 0; i < idStr.length; i++) {
				ChangeGoodsItems changeGoodsItems = (ChangeGoodsItems) storeChangeService
						.getObjById(idStr[i], "ChangeGoodsItems");
				changeGoodsItems.setChangeNumber(num[i]);
				changeGoodsItems.setRemark(remark[i]);
				storeChangeService.updateObj(changeGoodsItems);
			}
		}
		model.addAttribute("pyNum", pyNum);
		model.addAttribute("branchId", branchId);
		model.addAttribute("changeType", changeType);
		ModelAndView view = createIframeView("warehouse/storechange/storechange_choseGoods");
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
			String pyNum, String providerId, String branchId,
			String changeType, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			if ("undefined".equals(pyNum)) {
				pyNum = "";
			}
			dataGrid = goodsFileService.getPurchaseAddGoodsItems(page,
					goodsFile, pyNum, providerId, branchId, changeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 根据商品表勾选直接生成采购订单
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addGoodsToItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addGoodsToItems(
			@ModelAttribute ChangeGoodsItems changeGoodsItems, String changeId,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		String stores=changeGoodsItems.getStore();
		String[] storesStr=stores.split(",");
		CtpUser user = (CtpUser) request.getSession().getAttribute("user");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ChangeGoodsItems purchaseOrderGoodsItems = new ChangeGoodsItems();
					String id = idStr[i];
					String store=storesStr[i];
					GoodsFile goodsFile = (GoodsFile) goodsFileService
							.getObjById(id, "GoodsFile");
					String serial = goodsFile.getSerialNumber();
					Long count = null;
					if (StringUtils.isBlank(changeId)) {
						count = storeChangeService.getCountByObj(
								ChangeGoodsItems.class,
								"STORECHANGE_ID is null and goodsfile_id='"
										+ id + "'");
					} else {
						count = storeChangeService.getCountByObj(
								ChangeGoodsItems.class, "STORECHANGE_ID ='"
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
								ZcStoreChange storeChange = (ZcStoreChange) storeChangeService
										.getObjById(changeId, "ZcStoreChange");
								purchaseOrderGoodsItems
										.setStoreChange(storeChange);
							}
							purchaseOrderGoodsItems.setGoodsFile(goodsFile);
							purchaseOrderGoodsItems.setStore(store);
							purchaseOrderGoodsItems.setCreateUser(user);
							purchaseOrderGoodsItems.setEditFlag("1");
							storeChangeService.saveObj(purchaseOrderGoodsItems);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到临时库存调整表", "库存调整");
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
					storeChangeService.deleteObjById(ids[i],
							ChangeGoodsItems.class.getName());
				}
				logManageService.insertLog(request, "移除了库存调整单的商品", "库存调整");
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
			@ModelAttribute ChangeGoodsItems changeGoodsItems, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			dataGrid = storeChangeService.getChangeAddGoods(page, id,
					changeGoodsItems, ctpUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 移除新增调整单中的商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeChose", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeChose(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				storeChangeService.deleteObjById(ids[i],
						ChangeGoodsItems.class.getName());
			}
			logManageService.insertLog(request, "删除了库存调整单中的一条商品", "库存调整单");
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
	 * 删除
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
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				List<ChangeGoodsItems> changeGoodsItemsList = storeChangeService
						.getListByObj(ChangeGoodsItems.class,
								"storeChange_id='" + ids[i] + "'");
				if (changeGoodsItemsList != null
						&& changeGoodsItemsList.size() > 0) {
					for (int j = 0; j < changeGoodsItemsList.size(); j++) {
						ChangeGoodsItems changeGoodsItems = changeGoodsItemsList
								.get(j);
						storeChangeService.deleteObjById(
								changeGoodsItems.getId(),
								ChangeGoodsItems.class.getName());
					}
				}
				storeChangeService.deleteObjById(ids[i],
						ZcStoreChange.class.getName());
			}
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
