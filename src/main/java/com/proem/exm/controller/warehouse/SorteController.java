package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.ZcOrderProcessItem;
import com.proem.exm.entity.order.ZcOrderTransitItem;
import com.proem.exm.entity.order.ZcProcessOrder;
import com.proem.exm.entity.order.ZcTransitOrder;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.Sorte;
import com.proem.exm.entity.warehouse.SorteItem;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.service.warehouse.SorteService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Controller
@RequestMapping("sorte/sorteDo")
public class SorteController extends BaseController {

	@Autowired
	SorteService sorteService;

	@InitBinder("goodsFile")
	public void initGoods(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("goodsFile.");
	}

	@InitBinder("sorte")
	public void initSorte(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sorte.");
	}

	@InitBinder("sorteItem")
	public void initSorteItem(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sorteItem.");
	}

	// 初始化跳转页面
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
			fasonName = "分拣配送";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "待分拣商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_goods_list");
		return view;
	}

	// 初始化跳转页面
	@RequestMapping("initMaster")
	public ModelAndView initMaster(HttpServletRequest request,
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
			fasonName = "分拣配送";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "分拣单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_list");
		return view;
	}

	// 跳转新增页面
	@RequestMapping("gotoAdd")
	public ModelAndView gotoAdd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<SorteItem> sorteItemList = sorteService.getListByObj(
				SorteItem.class, " sorte_id is null");
		if (sorteItemList != null && sorteItemList.size() > 0) {
			for (int i = 0; i < sorteItemList.size(); i++) {
				SorteItem sorteItem = sorteItemList.get(i);
				sorteService.deleteObjById(sorteItem.getId(),
						SorteItem.class.getName());
			}
		}
		ZcUserInfo user = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		model.addAttribute("user", user);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_add");
		return view;
	}

	// 跳转分店选择页面
	@RequestMapping("openChoseBranch")
	public ModelAndView openChoseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ModelAndView view = createIframeView("warehouse/sorte/store_choose_branch");
		return view;
	}

	/**
	 * 跳转到订单商品详细页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openGoodsDetail")
	public ModelAndView openGoodsDetail(HttpServletRequest request,
			String areaId, String status, HttpServletResponse response,
			Model model) {
		model.addAttribute("areaId", areaId);
		model.addAttribute("status", status);
		ModelAndView view = createIframeView("warehouse/sorte/store_goods_detail");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoEdit")
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
		model.addAttribute("sorte", sorte);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_edit");
		return view;
	}

	// 打开审核页面
	@RequestMapping("gotoCheck")
	public ModelAndView gotoCheck(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
		model.addAttribute("sorte", sorte);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_check");
		return view;
	}

	// 打开编辑页面
	@RequestMapping("gotoDetail")
	public ModelAndView gotoDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
		model.addAttribute("sorte", sorte);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_detail");
		return view;
	}

	@RequestMapping("gotoSorting")
	public ModelAndView gotoSorting(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
		model.addAttribute("sorte", sorte);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_sorting");
		return view;
	}

	@RequestMapping(value = "listSorteJson", produces = "application/json")
	@ResponseBody
	public DataGrid listSorteJson(@ModelAttribute GoodsFile goodsFile,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = sorteService.getSortePage(page, goodsFile);
			List<Map<String, Object>> list = dataGrid.getRows();
			double store = 0;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String goodId = (String) list.get(i).get("GOODSFILE_ID");
					List<ZcStorehouse> storeList = sorteService
							.getListByObj(
									ZcStorehouse.class,
									"goodsfile_id='"
											+ goodId
											+ "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
					// 库存数量
					if (storeList != null && storeList.size() > 0) {
						String num = storeList.get(0).getStore();
						if (StringUtils.isBlank(num)) {
							num = "0";
						}
						list.get(i).put("STORE", num);
					} else {
						list.get(i).put("STORE", "0");
					}
				}
				dataGrid.setRows(list);
			}
			return dataGrid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 分页查询
	 */
	@RequestMapping(value = "listSorteMasterJson", produces = "application/json")
	@ResponseBody
	public DataGrid listSorteMasterJson(@ModelAttribute Sorte sorte,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = sorteService.getPagedDataGridObj(page, sorte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	@RequestMapping(value = "listAddJosn", produces = "application/json")
	@ResponseBody
	public DataGrid listAddJosn(@ModelAttribute Sorte sorte, String id,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = sorteService.getPagedDataGridAdd(page, sorte, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 选择分店保存到从表
	 * 
	 * @param processGoods
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveCheck(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult(null);
		String ids = request.getParameter("ids");
		String sorteId = request.getParameter("sorteId");
		Sorte sorte = (Sorte) sorteService.getObjById(sorteId, "Sorte");
		String[] idStr = ids.split(",");
		Long count = null;
		try {
			if (idStr != null && idStr.length > 0) {
				// 判断从表分店是否已存在
				for (int i = 0; i < idStr.length; i++) {
					if (sorteId == null || sorteId == "") {
						count = sorteService.getCountByObj(SorteItem.class,
								"branch_total_id = '" + idStr[i]
										+ "' and sorte_id is null");
					} else {
						count = sorteService.getCountByObj(SorteItem.class,
								"branch_total_id = '" + idStr[i]
										+ "' and sorte_id ='" + sorteId + "'");
					}
					if (count != 0 && count != null) {
						ajaxResult.setResult(false);
						return ajaxResult;
					}
				}
				for (int i = 0; i < idStr.length; i++) {
					// DataGrid dataGrid = null;
					// Page page = new Page();
					// page.setPage(1);
					// page.setRows(10000);
					// String areaId = branchList.getZcZoning().getStreet();
					// dataGrid = sorteService.getPagedDataGridGoodsDetail(page,
					// null, areaId, null);
					// List<Map<String, Object>> list = dataGrid.getRows();
					// double store = 0;
					// if (list != null && list.size() > 0) {
					// for (int a = 0; a < list.size(); a++) {
					// String goodId = (String) list.get(a).get(
					// "GOODSFILE_ID");
					// BigDecimal decimal = (BigDecimal) list.get(a).get(
					// "NUMS");
					// double needNum = decimal.doubleValue();
					// List<ZcStorehouse> storeList = sorteService
					// .getListByObj(
					// ZcStorehouse.class,
					// "goodsfile_id='"
					// + goodId
					// +
					// "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
					// // 库存数量
					// if (storeList != null && storeList.size() > 0) {
					// String num = storeList.get(0).getStore();
					// if (StringUtils.isBlank(num)) {
					// num = "0";
					// }
					// double storeNum = Double.valueOf(num);
					// if (needNum > storeNum) {
					// ajaxResult.setResult(true);
					// return ajaxResult;
					// } else {
					// list.get(a).put("STORE", num);
					// }
					// } else {
					// if (needNum > 0) {
					// ajaxResult.setResult(true);
					// return ajaxResult;
					// } else {
					// list.get(a).put("STORE", "0");
					// }
					// }
					// }
					// }
					// dataGrid.setRows(list);
					String id = idStr[i];
					BranchTotal branchList = (BranchTotal) sorteService
							.getObjById(id, "BranchTotal");
					SorteItem sorteItem = new SorteItem();
					sorteItem.setId(UuidUtils.getUUID());
					sorteItem.setSortStatus(1);
					sorteItem.setBranchTotalId(branchList);
					sorteItem.setAddress(branchList.getZcZoning());
					sorteItem.setCustomer(branchList.getCustomer());
					String area = branchList.getZcZoning().getStreet();
					sorteItem.setAreaId(area);
					// 判断订单表中是否有该分店订单
					count = sorteService.getCountByObj(ZcProcessOrder.class,
							" branchid ='" + area + "'");
					if (count != 0) {
						sorteService.saveObj(sorteItem);
					} else {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.SUCCESS, AjaxResult.INFO);
						return ajaxResult;
					}
				}
				logManageService.insertLog(request, "保存了勾选的分店到分拣单", "分拣单");
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
	 * 新增、修改保存
	 * 
	 * @param sorte
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult save(@ModelAttribute Sorte sorte,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, UnsupportedEncodingException {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String sorteId = request.getParameter("id");
		String remarks = request.getParameter("remark");
		String[] idStr = ids.split(",");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		remarks = java.net.URLDecoder.decode(remarks, "UTF-8");
		String id = UuidUtils.getUUID();
		try {
			Long count = sorteService.getCountByObj(Sorte.class, "code = '"
					+ sorte.getCode() + "'");
			String str = "";
			if (StringUtils.isBlank(sorteId)) {
				sorte.setId(id);
				if (count != 0) {
					SimpleDateFormat formatDate = new SimpleDateFormat(
							"yyyyMMddHHmmssSSS");
					Date date = new Date();
					str = formatDate.format(date);
					sorte.setCode("FJD" + str);
				}
				CtpUser user = (CtpUser) request.getSession().getAttribute(
						"user");
				sorte.setMakeMen(user);
				sorte.setAuditStatus(Constant.CHECK_STATUS_UNDO);
				sorteService.saveObj(sorte);
			} else {
				CtpUser ctuUser = (CtpUser) sorteService.getObjById(sorte
						.getMakeMen().getId(), "CtpUser");
				sorte.setMakeMen(ctuUser);
				sorteService.updateObj(sorte);
			}
			for (int i = 0; i < idStr.length; i++) {
				SorteItem sorteItem = (SorteItem) sorteService.getObjById(
						idStr[i], "SorteItem");
				sorteItem.setSorteId(sorte);
				if (remarks != null && remarks != "") {
					String[] remark = remarks.split(",");
					sorteItem.setRemark(remark[i]);
				}
				sorteService.updateObj(sorteItem);
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

	// 删除
	@RequestMapping(value = "deleteItem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteItem(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				sorteService.deleteObjById(ids[i], SorteItem.class.getName());
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
	 * 新增、编辑全部
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "chooseAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult chooseAll(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		page.setPage(1);
		page.setRows(10000);
		AjaxResult ajaxResult = new AjaxResult(null);
		DataGrid dataGrid = null;
		String goodId = "";
		String sorteId = request.getParameter("id");
		String ids = request.getParameter("ids");
		String[] sorteItemId = ids.split(",");
		String statu = "1";
		String area = "";
		double count = 0;
		List<BranchTotal> branchTotalList = sorteService.getListByObj(
				BranchTotal.class, " 1=1 ");
		try {
			if (branchTotalList != null && branchTotalList.size() > 0) {
				// 判断从表分店是否已存在
				if (sorteId == null || sorteId == "") {
					for (int i = 0; i < sorteItemId.length; i++) {
						sorteService.deleteObjById(sorteItemId[i],
								SorteItem.class.getName());
					}
					for (int i = 0; i < branchTotalList.size(); i++) {
						// dataGrid = sorteService.getPagedDataGridGoodsDetail(
						// page, null, branchTotalList.get(i)
						// .getZcZoning().getStreet(), statu);
						// List<Map<String, Object>> list = dataGrid.getRows();
						// if (list != null && list.size() > 0) {
						// for (int j = 0; j < list.size(); j++) {
						// if (list.get(j) == null) {
						// break;
						// }
						// goodId = (String) list.get(j).get(
						// "GOODSFILE_ID");
						// // BigDecimal decimal = (BigDecimal)
						// // list.get(j).get("NUMS");
						// // double needNum = decimal.doubleValue();
						// // List<ZcStorehouse> storeList = sorteService
						// // .getListByObj(
						// // ZcStorehouse.class,
						// // "goodsfile_id='"
						// // + goodId
						// // +
						// //
						// "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
						// // if (storeList != null && storeList.size() >
						// // 0) {
						// // String num = storeList.get(0).getStore();
						// // if (StringUtils.isBlank(num)) {
						// // num = "0";
						// // }
						// // // double storeNum = Double.valueOf(num);
						// // // if (needNum > storeNum) {
						// // // ajaxResult.setResult(true);
						// // // return ajaxResult;
						// // // }
						// // } else {
						// // ajaxResult.setResult(true);
						// // return ajaxResult;
						// // }
						// }
						// }
						SorteItem sorteItem = new SorteItem();
						sorteItem.setId(UuidUtils.getUUID());
						sorteItem.setSortStatus(1);
						sorteItem.setBranchTotalId(branchTotalList.get(i));
						sorteItem.setAddress(branchTotalList.get(i)
								.getZcZoning());
						sorteItem.setCustomer(branchTotalList.get(i)
								.getCustomer());
						area = branchTotalList.get(i).getZcZoning().getStreet();
						sorteItem.setAreaId(area);
						// 判断订单表中是否有该分店订单
						count = sorteService.getCountByObj(
								ZcProcessOrder.class, " branchid ='" + area
										+ "'");
						if (count != 0) {
							sorteService.saveObj(sorteItem);
						}
					}
				} else {
					Sorte sorte = (Sorte) sorteService.getObjById(sorteId,
							"Sorte");
					for (int i = 0; i < sorteItemId.length; i++) {
						sorteService.deleteObjById(sorteItemId[i],
								SorteItem.class.getName());
					}
					for (int i = 0; i < branchTotalList.size(); i++) {
						// dataGrid = sorteService.getPagedDataGridGoodsDetail(
						// page, null, branchTotalList.get(i)
						// .getZcZoning().getStreet(), statu);
						// List<Map<String, Object>> list = dataGrid.getRows();
						// if (list != null && list.size() > 0) {
						// for (int j = 0; j < list.size(); j++) {
						// if (list.get(j) == null) {
						// break;
						// }
						// goodId = (String) list.get(j).get(
						// "GOODSFILE_ID");
						// BigDecimal decimal = (BigDecimal) list.get(j)
						// .get("NUMS");
						// double needNum = decimal.doubleValue();
						// List<ZcStorehouse> storeList = sorteService
						// .getListByObj(
						// ZcStorehouse.class,
						// "goodsfile_id='"
						// + goodId
						// +
						// "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
						// if (storeList != null && storeList.size() > 0) {
						// String num = storeList.get(0).getStore();
						// if (StringUtils.isBlank(num)) {
						// num = "0";
						// }
						// double storeNum = Double.valueOf(num);
						// if (needNum > storeNum) {
						// ajaxResult.setResult(true);
						// return ajaxResult;
						// }
						// } else {
						// ajaxResult.setResult(true);
						// return ajaxResult;
						// }
						// }
						// }
						SorteItem sorteItem = new SorteItem();
						sorteItem.setId(UuidUtils.getUUID());
						sorteItem.setSorteId(sorte);
						sorteItem.setSortStatus(1);
						sorteItem.setBranchTotalId(branchTotalList.get(i));
						sorteItem.setAddress(branchTotalList.get(i)
								.getZcZoning());
						sorteItem.setCustomer(branchTotalList.get(i)
								.getCustomer());
						area = branchTotalList.get(i).getZcZoning().getStreet();
						sorteItem.setAreaId(area);
						// 判断订单表中是否有该分店订单
						count = sorteService.getCountByObj(
								ZcProcessOrder.class, " branchid ='" + area
										+ "'");
						if (count != 0) {
							sorteService.saveObj(sorteItem);
						}
					}
				}
				logManageService.insertLog(request, "保存了所有的分店到分拣单", "分拣单");
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

	// 删除
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult delete(HttpServletRequest request,
			HttpServletResponse response, String id, Page page) {
		AjaxResult ajaxResult = null;
		try {
			List<SorteItem> aItems = sorteService.getListByObj(SorteItem.class,
					"sorte_id = '" + id + "'");
			if (aItems != null && aItems.size() > 0) {
				for (int j = 0; j < aItems.size(); j++) {
					SorteItem sorteItem = aItems.get(j);
					sorteService.deleteObjById(sorteItem.getId(),
							SorteItem.class.getName());
				}
			}
			sorteService.deleteObjById(id, Sorte.class.getName());
			logManageService.insertLog(request, "删除了勾选的分拣单", "分拣单");
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.DELETE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	// 提交审核
	@RequestMapping(value = "toCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult toCheck(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
			sorte.setAuditStatus(Constant.CHECK_STATUS_WAITCHECK);
			sorteService.updateObj(sorte);
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.UPDATE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	/**
	 * 展示某分店中的订单商品
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listgoodsItemJson", produces = "application/json")
	@ResponseBody
	public DataGrid listOrdersItemJson(String areaId, String status,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, Object> result = new HashMap();
		DataGrid dataGrid = null;
		try {
			dataGrid = sorteService.getPagedDataGridGoodsDetail(page, null,
					areaId, status);
			List<Map<String, Object>> list = dataGrid.getRows();
			double store = 0;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String goodId = (String) list.get(i).get("GOODSFILE_ID");
					List<ZcStorehouse> storeList = sorteService
							.getListByObj(
									ZcStorehouse.class,
									"goodsfile_id='"
											+ goodId
											+ "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
					// 库存数量
					if (storeList != null && storeList.size() > 0) {
						String num = storeList.get(0).getStore();
						if (StringUtils.isBlank(num)) {
							num = "0";
						}
						list.get(i).put("STORE", num);
					} else {
						list.get(i).put("STORE", "0");
					}
				}
				dataGrid.setRows(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 审核分拣单
	 * 
	 * @param type
	 * @param sorteId
	 * @param reason
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkDifference(String type, String sorteId,
			String reason, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			Sorte sorte = (Sorte) sorteService.getObjById(sorteId, "Sorte");
			CtpUser ctpUser = (CtpUser) request.getSession().getAttribute(
					"user");
			// 审核通过
			if ("2".equals(type)) {
				sorte.setAuditStatus(Constant.CHECK_STATUS_PASS);
				sorte.setAuditMen(ctpUser);
				sorte.setAuditTime(new Date());
				// 审核不通过
			} else if ("3".equals(type)) {
				sorte.setAuditStatus(Constant.CHECK_STATUS_NOPASS);
				sorte.setAuditMen(ctpUser);
				sorte.setRemrks(reason);
				sorte.setAuditTime(new Date());
			} else {
				ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
						AjaxResult.INFO);
				return ajaxResult;
			}
			sorteService.updateObj(sorte);
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
	 * 开始分拣
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "startSort", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult startSort(String id, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			SorteItem sorteItem = (SorteItem) sorteService.getObjById(id,
					"SorteItem");

			if (sorteItem != null) {
				sorteItem.setSortStatus(2);
			}
			sorteService.updateObj(sorteItem);
			logManageService.insertLog(request, "对分店进行了分拣", "分拣单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping(value = "endSort", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult endSort(String id, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		try {
			SorteItem sorteItem = (SorteItem) sorteService.getObjById(id,
					"SorteItem");
			if (sorteItem != null) {
				sorteItem.setSortStatus(3);
				String areaId = sorteItem.getAreaId();
				// 把订单信息移到配送订单表中
				List<ZcProcessOrder> processOrderList = sorteService
						.getListByObj(ZcProcessOrder.class, "branchid='"
								+ areaId + "'");
				if (processOrderList != null && processOrderList.size() > 0) {
					ZcTransitOrder transitOrder = new ZcTransitOrder();
					for (int i = 0; i < processOrderList.size(); i++) {
						ZcProcessOrder processOrder = processOrderList.get(i);
						String orderId = processOrder.getId();
						// 获取此条订单中的所有商品
						List<ZcOrderProcessItem> itemList = sorteService
								.getListByObj(ZcOrderProcessItem.class,
										"order_id='" + orderId + "'");
						if (itemList != null && itemList.size() > 0) {
							for (int a = 0; a < itemList.size(); a++) {
								ZcOrderProcessItem orderProcessItem = itemList
										.get(a);
								orderProcessItem
										.setGoodsState(Constant.ORDER_STATUS_DISTRIBUTION);
								sorteService.updateObj(orderProcessItem);
								ZcOrderTransitItem orderTransitItem = new ZcOrderTransitItem();
								orderTransitItem
										.setId(orderProcessItem.getId());
								orderTransitItem.setOrder_id(orderProcessItem
										.getOrder_id());
								orderTransitItem.setObj_id(orderProcessItem
										.getObj_id());
								orderTransitItem.setProduct_id(orderProcessItem
										.getProduct_id());
								orderTransitItem.setGoodsFile(orderProcessItem
										.getGoodsFile());
								orderTransitItem.setType_id(orderProcessItem
										.getType_id());
								orderTransitItem
										.setBn(orderProcessItem.getBn());
								orderTransitItem.setName(orderProcessItem
										.getName());
								orderTransitItem.setCost(orderProcessItem
										.getCost());
								orderTransitItem.setPrice(orderProcessItem
										.getPrice());
								orderTransitItem.setG_price(orderProcessItem
										.getG_price());
								orderTransitItem.setAmount(orderProcessItem
										.getAmount());
								orderTransitItem.setScore(orderProcessItem
										.getScore());
								orderTransitItem.setWeight(orderProcessItem
										.getWeight());
								orderTransitItem.setNums(orderProcessItem
										.getNums());
								orderTransitItem.setSendNum(orderProcessItem
										.getSendNum());
								orderTransitItem.setAddon(orderProcessItem
										.getAddon());
								orderTransitItem.setItem_type(orderProcessItem
										.getItem_type());
								orderTransitItem
										.setProviderInfo(orderProcessItem
												.getProviderInfo());
								orderTransitItem.setGoodsState(orderProcessItem
										.getGoodsState());
								sorteService.saveObj(orderTransitItem);
								sorteService.deleteObjById(
										orderProcessItem.getId(),
										"ZcOrderProcessItem");
							}
						}
						if ("全部完成".equals(processOrder.getPullFlag())) {
							processOrder
									.setOrderStatus(Constant.ORDER_STATUS_DISTRIBUTION);
							sorteService.updateObj(processOrder);
							sorteService.deleteObjById(processOrder.getId(),
									"ZcProcessOrder");
							Long count = sorteService.getCountByObj(
									ZcTransitOrder.class,
									"id='" + processOrder.getId() + "'");
							if (count == 0) {
								transitOrder.setId(processOrder.getId());
								transitOrder.setCreateTime(processOrder
										.getCreateTime());
								transitOrder.setUpdateTime(processOrder
										.getUpdateTime());
								transitOrder.setOrderNum(processOrder
										.getOrderNum());
								transitOrder.setOrderTotalValue(processOrder
										.getOrderTotalValue());
								transitOrder.setOrderStatus(processOrder
										.getOrderStatus());
								transitOrder.setOrderDate(processOrder
										.getOrderDate());
								transitOrder.setOrderCome(processOrder
										.getOrderCome());
								transitOrder.setConsignee(processOrder
										.getConsignee());
								transitOrder.setCansignPhone(processOrder
										.getCansignPhone());
								transitOrder.setZcZoning(processOrder
										.getZcZoning());
								transitOrder.setOrderAmount(processOrder
										.getOrderAmount());
								transitOrder.setOrderReduceAmount(processOrder
										.getOrderReduceAmount());
								transitOrder
										.setIsGift(processOrder.getIsGift());
								transitOrder.setMemberCardNumber(processOrder
										.getMemberCardNumber());
								transitOrder.setGoodsNum(processOrder
										.getGoodsNum());
								transitOrder.setPullFlag(processOrder
										.getPullFlag());
								transitOrder.setAssociator(processOrder
										.getAssociator());
								transitOrder.setBranchId(processOrder
										.getBranchId());
								sorteService.saveObj(transitOrder);
							} else {
								sorteService.deleteObjById(orderId,
										"ZcTransitOrder");
								transitOrder.setId(processOrder.getId());
								transitOrder.setCreateTime(processOrder
										.getCreateTime());
								transitOrder.setUpdateTime(processOrder
										.getUpdateTime());
								transitOrder.setOrderNum(processOrder
										.getOrderNum());
								transitOrder.setOrderTotalValue(processOrder
										.getOrderTotalValue());
								transitOrder.setOrderStatus(processOrder
										.getOrderStatus());
								transitOrder.setOrderDate(processOrder
										.getOrderDate());
								transitOrder.setOrderCome(processOrder
										.getOrderCome());
								transitOrder.setConsignee(processOrder
										.getConsignee());
								transitOrder.setCansignPhone(processOrder
										.getCansignPhone());
								transitOrder.setZcZoning(processOrder
										.getZcZoning());
								transitOrder.setOrderAmount(processOrder
										.getOrderAmount());
								transitOrder.setOrderReduceAmount(processOrder
										.getOrderReduceAmount());
								transitOrder
										.setIsGift(processOrder.getIsGift());
								transitOrder.setMemberCardNumber(processOrder
										.getMemberCardNumber());
								transitOrder.setGoodsNum(processOrder
										.getGoodsNum());
								transitOrder.setPullFlag(processOrder
										.getPullFlag());
								transitOrder.setAssociator(processOrder
										.getAssociator());
								transitOrder.setBranchId(processOrder
										.getBranchId());
								sorteService.saveObj(transitOrder);
							}
						}
					}
				}
			}
			sorteService.updateObj(sorteItem);
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping("gotoPrintSorteGoods")
	public ModelAndView gotoPrintSorteGoods(
			@ModelAttribute GoodsFile goodsFile, HttpServletRequest request,
			HttpServletResponse response, Model model, Page page) {
		page.setPage(1);
		page.setRows(1000);
		DataGrid dataGrid = null;
		try {
			dataGrid = sorteService.getSortePage(page, goodsFile);
			List<Map<String, Object>> list = dataGrid.getRows();
			double store = 0;
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String goodId = (String) list.get(i).get("GOODSFILE_ID");
					List<ZcStorehouse> storeList = sorteService
							.getListByObj(
									ZcStorehouse.class,
									"goodsfile_id='"
											+ goodId
											+ "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' ");
					// 库存数量
					if (storeList != null && storeList.size() > 0) {
						String num = storeList.get(0).getStore();
						if (StringUtils.isBlank(num)) {
							num = "0";
						}
						list.get(i).put("STORE", num);
					} else {
						list.get(i).put("STORE", "0");
					}
				}
				dataGrid.setRows(list);
			}
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = createIframeView("warehouse/sorte/sorte_goods_print");
		return view;
	}

	@RequestMapping("gotoSortePrint")
	public ModelAndView gotoSortePrint(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		List<SorteItem> sorteItemsList = sorteService.getListByObj(
				SorteItem.class, "sorte_id = '" + id + "'");
		Sorte sorte = (Sorte) sorteService.getObjById(id, "Sorte");
		model.addAttribute("sorte", sorte);
		model.addAttribute("sorteItemsList", sorteItemsList);
		ModelAndView view = createIframeView("warehouse/sorte/sorte_print");
		return view;
	}

}
