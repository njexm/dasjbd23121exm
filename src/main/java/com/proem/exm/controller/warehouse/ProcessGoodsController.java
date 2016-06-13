package com.proem.exm.controller.warehouse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.proem.exm.dao.warehouse.ProcessGoodsItemsDao;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.OrdersSorte;
import com.proem.exm.entity.settlement.AdvanceDetail;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.warehouse.ZcCostRecordItems;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.warehouse.process.ProcessGoods;
import com.proem.exm.entity.warehouse.process.ProcessGoodsItems;
import com.proem.exm.service.warehouse.ProcessGoodsItemsService;
import com.proem.exm.service.warehouse.ProcessGoodsService;
import com.proem.exm.utils.AjaxResult;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

/**
 * 加工单控制器
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("wareHouse/processGoods")
public class ProcessGoodsController extends BaseController {
	@Autowired
	ProcessGoodsService processGoodsService;
	@Autowired
	ProcessGoodsItemsService processGoodsItemsService;
	@Autowired
	ProcessGoodsItemsDao processGoodsItemsDao;

	@InitBinder("processGoods")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("processGoods.");
	}

	/**
	 * 打开初始化跳转页面
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
			fasonName = "商品加工";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "加工单";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_list");
		return view;
	}

	/**
	 * 初始化待加工商品列表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initWaittingGoods")
	public ModelAndView initWaittingGoods(HttpServletRequest request,
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
			fasonName = "商品加工";
		} else {
			fasonName = new String(fasonName.getBytes("iso8859-1"), "utf-8");
		}
		String sonName = request.getParameter("sonName");
		if (StringUtils.isBlank(sonName)) {
			sonName = "待加工商品汇总";
		} else {
			sonName = new String(sonName.getBytes("iso8859-1"), "utf-8");
		}
		String title = "  " + faName + " > " + fasonName + " > " + sonName + "";
		model.addAttribute("title", title);
		ModelAndView view = createIframeView("warehouse/processGoods/waitProcessGoods_list");
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
	@RequestMapping(value = "listProcessGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProcessGoodsJson(
			@ModelAttribute ProcessGoods processGoods,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = processGoodsService.getPagedDataGridObj(page,
					processGoods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
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
	@RequestMapping(value = "chooseSorteList", produces = "application/json")
	@ResponseBody
	public DataGrid chooseSorteList(String sorteOdd,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = processGoodsService.getChooseSorteList(page, sorteOdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 查看明细打开页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openDetail")
	public ModelAndView openDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID获取加工单对象
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		model.addAttribute("processGoods", processGoods);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_detail");
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
	@RequestMapping("openEditProcessGoods")
	public ModelAndView openEditProcessGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		List<ProcessGoodsItems> processGoodsItemsList = processGoodsService
				.getListByObj(ProcessGoodsItems.class,
						"DELFLAG='1' and PROCESSGOODSID='" + id + "'");
		if (processGoodsItemsList != null && processGoodsItemsList.size() > 0) {
			for (int i = 0; i < processGoodsItemsList.size(); i++) {
				ProcessGoodsItems processGoodsItems = processGoodsItemsList
						.get(i);
	//			processGoodsService.deleteObjById(processGoodsItems.getId(),
	//					ProcessGoodsItems.class.getName());
			}
		}
		model.addAttribute("processGoods", processGoods);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_edit");
		return view;
	}

	/**
	 * 打开添加成品页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openAddproduct")
	public ModelAndView openAddproduct(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		List<ProcessGoodsItems> processGoodsItemsList = processGoodsService
				.getListByObj(ProcessGoodsItems.class,
						"DELFLAG='1' and PROCESSGOODSID='" + id + "'");
		if (processGoodsItemsList != null && processGoodsItemsList.size() > 0) {
			for (int i = 0; i < processGoodsItemsList.size(); i++) {
				ProcessGoodsItems processGoodsItems = processGoodsItemsList
						.get(i);
				processGoodsService.deleteObjById(processGoodsItems.getId(),
						ProcessGoodsItems.class.getName());
			}
		}
		model.addAttribute("processGoods", processGoods);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_addProduct");
		return view;
	}

	/**
	 * 新增一条空白加工单
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "newAddProcessGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult newAddProcessGoods(
			@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String str = formatDate.format(date);
		try {
			Thread.sleep(1);// 等待1毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			ProcessGoods processGoods = new ProcessGoods();
			processGoods.setId(UuidUtils.getUUID());
			processGoods.setOdd("JGD" + str);
			processGoods.setCreateMan(userInfo.getCtpUser());
			processGoods.setStatue(Constant.CHECK_STATUS_UNDO);
			processGoods.setGoodsTotalNum("0");
			processGoods.setUseTotalNum("0");
			processGoodsService.saveObj(processGoods);
			logManageService.insertLog(request, "新增了一条空白加工单", "加工单");
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
	 * 加工单用料列表展示
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listProcessUseGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProcessUseGoodsJson(
			@ModelAttribute ProcessGoodsItems processGoodsItems,
			String processGoodsId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = processGoodsService.getlistProcessUseGoodsJson(page,
					processGoodsItems, processGoodsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 加工单成品商品列表展示
	 * 
	 * @param zcOrderItem
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listProcessProductGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listProcessProductGoodsJson(
			@ModelAttribute ProcessGoodsItems processGoodsItems,
			String processGoodsId, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = processGoodsService.getlistProcessProductGoodsJson(page,
					processGoodsItems, processGoodsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 打开新增商品时选择商品的面板
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openChoseGoods")
	public ModelAndView openChoseGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String processGoodsId = request.getParameter("processGoodsId");
		String ids = request.getParameter("ids");
		String nums = request.getParameter("num");
		if (nums != null && nums != "" && ids != null && ids != "") {
			String[] num = nums.split(",");
			String[] idStr = ids.split(",");
			for (int i = 0; i < idStr.length; i++) {
				ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsService
						.getObjById(idStr[i], "ProcessGoodsItems");
				processGoodsItems.setUseNum(num[i]);
				processGoodsService.updateObj(processGoodsItems);
			}
		}
		model.addAttribute("processGoodsId", processGoodsId);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_choseGoods");
		return view;
	}

	/**
	 * 选择商品页面商品列表展示
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "listWaitGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid listWaitGoodsJson(@ModelAttribute GoodsFile goodsFile,
			String serialNumber, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		try {
			dataGrid = processGoodsItemsService.getlistWaitGoodsJson(page,
					goodsFile, serialNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 选择商品页面商品列表展示
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "waittingProcessGoodsJson", produces = "application/json")
	@ResponseBody
	public DataGrid waittingProcessGoodsJson(
			@ModelAttribute GoodsFile goodsFile, String serialNumber,
			HttpServletRequest request, HttpServletResponse response, Page page) {
		DataGrid dataGrid = null;
		DecimalFormat df = new DecimalFormat(".##");
		try {
			dataGrid = processGoodsItemsService.getWaittingProcessJson(page,
					goodsFile, serialNumber);
			// 获取页面行
			List<Map<String, Object>> list = dataGrid.getRows();
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 获取页面的商品ID
					String goodsId = (String) list.get(i).get("GOODSFILE_ID");
					// 获取页面的订单数量
					BigDecimal orderNeedsNums = (BigDecimal) list.get(i).get(
							"NUMS");
					// 库存为0
					double stock = 0;
					// 需要加工的数量
					double needProcessNums = 0;
					List<GoodsFile> goodsList = processGoodsItemsService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ goodsId + "'");
					if (goodsList != null && goodsList.size() > 0) {
						String goodsFileId = goodsList.get(0).getId();
						List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
								.getListByObj(ZcStorehouse.class,
										"BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5' and GOODSFILE_ID='"
												+ goodsFileId + "'");
						String useNums = "";
						if (zcStorehouseList != null
								&& zcStorehouseList.size() > 0) {
							String goodsUseNums = zcStorehouseList.get(0)
									.getStore();
							useNums = goodsUseNums;
						} else {
							useNums = "0";
						}
						list.get(i).put("GOODSUSENUMS", useNums);
					}
					List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
							.getListByObj(ZcStorehouse.class,
									"BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5' and GOODSFILE_ID='"
											+ goodsId + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						String nums = zcStorehouseList.get(0).getStore();
						if (StringUtils.isBlank(nums)) {
							nums = "0";
						}
						double num = Double.valueOf(nums);
						// 库存=实际库存量
						stock = num;
					}
					// 需要加工的量=订单需求量-库存量
					needProcessNums = orderNeedsNums.doubleValue() - stock;
					if (needProcessNums <= 0) {
						// 需要加工份数为0
						list.get(i).put("WAITPROCESSNUMS", "0");
					} else {
						// 需要加工份数为需要加工的量红色标记
						list.get(i).put(
								"WAITPROCESSNUMS",
								"<span style='color:red;font-weight:700 '>"
										+ needProcessNums + "</span>");
					}
					List<GoodsFile> goodsFileList = processGoodsItemsService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ goodsId + "'");
					if (goodsFileList == null || goodsFileList.size() <= 0) {
						list.get(i).put("USENEEDSNUMS", "0");
					} else {
						GoodsFile good = (GoodsFile) processGoodsItemsService
								.getObjById(goodsId, "GoodsFile");
						String wastStr = good.getWasteRate();
						String str2 = "";
						String guige = good.getGoods_specifications();
						if (guige != null && guige != "") {
							for (int z = 0; z < guige.length(); z++) {
								if ((guige.charAt(z) >= '0' && guige.charAt(z) <= '9')
										|| guige.charAt(z) == '.') {
									str2 += guige.charAt(z);
								}
							}
						}
						double spe = Double.valueOf(str2);
						double wast = Double.valueOf(wastStr);
						double storeNums = 0;
						String id = goodsFileList.get(0).getId();
						List<ZcStorehouse> storeUseList = processGoodsItemsService
								.getListByObj(ZcStorehouse.class,
										"goodsfile_id='" + id + "'");
						if (storeUseList != null && storeUseList.size() > 0) {
							for (int a = 0; a < storeUseList.size(); a++) {
								String num = storeUseList.get(a).getStore();
								if (StringUtils.isBlank(num)) {
									num = "0";
								}
								double nums = Double.valueOf(num);
								storeNums = storeNums + nums;
							}
							if (needProcessNums <= 0) {
								list.get(i).put("USENEEDSNUMS", "0");
							} else {
								double needNum = needProcessNums * spe
										* (1 + wast / 100);
								list.get(i).put("USENEEDSNUMS",
										df.format(needNum));
							}
						} else {
							double needNum = needProcessNums * spe
									* (1 + wast / 100);
							list.get(i).put("USENEEDSNUMS", df.format(needNum));
						}
						newList.add(list.get(i));
					}
					list.get(i).put("STOCKNUMS", stock);
				}
				Long total = (long) newList.size();
				return new DataGrid(total, newList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteProcessGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult deleteProcessGoods(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				processGoodsService.deleteObjById(ids[i],
						ProcessGoods.class.getName());
				List<ProcessGoodsItems> processGoodsItemsList = processGoodsItemsService
						.getListByObj(ProcessGoodsItems.class,
								"PROCESSGOODSID='" + ids[i] + "'");
				if (processGoodsItemsList != null
						&& processGoodsItemsList.size() > 0) {
					for (int j = 0; j < processGoodsItemsList.size(); j++) {
						ProcessGoodsItems processGoodsItems = processGoodsItemsList
								.get(j);
						processGoodsItemsService.deleteObjById(
								processGoodsItems.getId(),
								ProcessGoodsItems.class.getName());
					}
				}
			}
			logManageService.insertLog(request, "删除了勾选的加工单", "加工单");
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
	 * 根据商品表勾选保存商品到原料单中
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveCheck(@ModelAttribute ProcessGoodsItems processGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String useNums = request.getParameter("useNums");
		String processGoodsId = request.getParameter("processGoodsId");
		String[] idStr = ids.split(",");
		String[] useNum = useNums.split(",");
		Branch branch = (Branch) processGoodsItemsService.getObjById(
				"596BA834-0618-4902-BCDF-2A70499C43B5", "Branch");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ProcessGoodsItems processGoodsItems = new ProcessGoodsItems();
					String id = idStr[i];
					List<GoodsFile> goodsFileList = processGoodsItemsService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ id + "'");
					if (goodsFileList == null || goodsFileList.size() <= 0) {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.FAIL, AjaxResult.INFO);
						return ajaxResult;
					} else {
						GoodsFile goodsFile = goodsFileList.get(0);
						String goodsId = goodsFile.getId();
						Long count = processGoodsItemsService.getCountByObj(
								ProcessGoodsItems.class, "PROCESSGOODSID='"
										+ processGoodsId
										+ "' and GOODSFILE_ID='" + goodsId
										+ "'");
						if (count != 0) {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.ERROR, AjaxResult.INFO);
							return ajaxResult;
						} else {
							GoodsFile goods = (GoodsFile) processGoodsItemsService
									.getObjById(goodsId, "GoodsFile");
							Long counts = processGoodsItemsService
									.getCountByObj(
											ZcStorehouse.class,
											"goodsFile_id='"
													+ goodsId
													+ "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5'");
							if (counts == 0) {
								ZcStorehouse zcStorehouse = new ZcStorehouse();
								zcStorehouse.setId(UuidUtils.getUUID());
								zcStorehouse.setGoodsFile(goods);
								zcStorehouse.setBranch(branch);
								zcStorehouse.setStore("0");
								processGoodsItemsService.saveObj(zcStorehouse);
							}
							 List<ZcStorehouse> zcStorehouseList = processGoodsItemsService.getListByObj(ZcStorehouse.class, "goodsFile_id='"
													+ goodsId
													+ "' and branch_id='596BA834-0618-4902-BCDF-2A70499C43B5'");
							 if (zcStorehouseList.size()!=0) {
								 ZcStorehouse zcStorehouse = zcStorehouseList.get(0);
								 System.out.println(zcStorehouse.getWeight());
								 System.out.println(zcStorehouse.getStore());
								 if((zcStorehouse.getWeight()==null ||zcStorehouse.getWeight().equals("")||zcStorehouse.getWeight().equals("0")) 
										 && (zcStorehouse.getStore()==null||zcStorehouse.getStore().equals("")||zcStorehouse.getStore().equals("0"))){
									 
								 }else{
								 ProcessGoodsItems productGoodsItems = new ProcessGoodsItems();
								 
								 GoodsFile goodsFileProduct= (GoodsFile)processGoodsItemsService.getObjById(id, "GoodsFile");
								 productGoodsItems.setId(UuidUtils.getUUID());
								 productGoodsItems.setProcessGoodsId(processGoodsId);
								 productGoodsItems.setGoodsFile(goodsFileProduct);
								 productGoodsItems.setSerialNumber(goodsFileProduct
											.getSerialNumber());
								 productGoodsItems.setGoodsName(goodsFileProduct
											.getGoods_name());
								 productGoodsItems.setUnit(goodsFileProduct.getGoods_unit());
								 productGoodsItems.setSpecifications(goodsFileProduct
											.getGoods_specifications());
								 productGoodsItems.setTypeFlag("2");
								 productGoodsItems.setDelFlag("1");
								 if(zcStorehouse.getWeight()==null || zcStorehouse.getWeight().equals("")){
									 productGoodsItems.setGoodsWeight(zcStorehouse.getWeight());
								 }else{
									 productGoodsItems.setGoodsWeight("-"+zcStorehouse.getWeight());
								 }
								 
								 if(zcStorehouse.getStore()==null || zcStorehouse.getStore().equals("")){
									 productGoodsItems.setGoodsNum(zcStorehouse.getStore());
								 }else{
									 productGoodsItems.setGoodsNum("-"+zcStorehouse.getStore());
								 }
								 processGoodsItemsService.saveObj(productGoodsItems);
								 }
							}
							processGoodsItems.setId(UuidUtils.getUUID());
							processGoodsItems.setProcessGoodsId(processGoodsId);
							processGoodsItems.setGoodsFile(goods);
							processGoodsItems.setSerialNumber(goods
									.getSerialNumber());
							processGoodsItems.setGoodsName(goods
									.getGoods_name());
							processGoodsItems.setUnit(goods.getGoods_unit());
							processGoodsItems.setSpecifications(goods
									.getGoods_specifications());
							processGoodsItems.setTypeFlag("1");
							processGoodsItems.setDelFlag("1");
							processGoodsItems.setUseNum(useNum[i]);
							processGoodsItemsService.saveObj(processGoodsItems);
							
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到加工原料", "加工单");
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
	 * 移除商品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeUseGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeUseGoods(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String processGoodsId = request.getParameter("processGoodsId");
			ProcessGoods processGoods = (ProcessGoods) processGoodsService
					.getObjById(processGoodsId, "ProcessGoods");
			String[] ids = id.split(",");
			double useNums = Double.valueOf(processGoods.getUseTotalNum());
			for (int i = 0; i < ids.length; i++) {
				ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsItemsService
						.getObjById(ids[i], "ProcessGoodsItems");
				if ("1".equals(processGoodsItems.getTypeFlag())) {
					useNums -= Double.valueOf(processGoodsItems.getUseNum());
					if (useNums < 0) {
						useNums = 0;
					}
					processGoods.setUseTotalNum(String.valueOf(useNums));
					processGoodsService.updateObj(processGoods);
					processGoodsItemsService.deleteObjById(ids[i],
							ProcessGoodsItems.class.getName());
				}
			}
			logManageService.insertLog(request, "移除了一条商品", "加工单");
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
	 * 保存修改
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateProcessUseGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateProcessUseGoods(
			@ModelAttribute ProcessGoods processGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String useNum = request.getParameter("useNum");
		String processGoodsId = request.getParameter("processGoodsId");
		String[] idStr = ids.split(",");
		String[] useNums = useNum.split(",");
		double totalUseNums = 0;
		double store = 0;
		ProcessGoods process = (ProcessGoods) processGoodsService.getObjById(
				processGoodsId, "ProcessGoods");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsItemsService
							.getObjById(idStr[i], "ProcessGoodsItems");
					List<ZcStorehouse> zcStorehouseList = processGoodsService
							.getListByObj(ZcStorehouse.class,
									"BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5' and GOODSFILE_ID='"
											+ processGoodsItems.getGoodsFile()
													.getId() + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						ZcStorehouse zcStorehouse = zcStorehouseList.get(0);
						store = Double.valueOf(zcStorehouse.getStore());
					}
					if (Double.valueOf(useNums[i]) > store) {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.FAIL, AjaxResult.INFO);
						return ajaxResult;
					}
					if ("1".equals(processGoodsItems.getTypeFlag())) {
						processGoodsItems.setUseNum(useNums[i]);
						processGoodsItems.setDelFlag("");
						processGoodsItemsService.updateObj(processGoodsItems);
					}
				}
			}
			if (useNums != null && useNums.length > 0) {
				for (int i = 0; i < useNums.length; i++) {
					if (!StringUtils.isBlank(useNums[i])) {
						totalUseNums += Double.valueOf(useNums[i]);
					}
				}
			}
			process.setUseTotalNum(String.valueOf(totalUseNums));
			processGoodsService.updateObj(process);
			logManageService.insertLog(request, "保存了加工单商品信息", "加工单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,AjaxResult.INFO);
		}
		return ajaxResult;
	}

	
	/**
	 * 关闭修改页面自动保存
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value = "updateProcessUseGoodsno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateProcessUseGoodsno(
			@ModelAttribute ProcessGoods processGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String useNum = request.getParameter("useNum");
		String processGoodsId = request.getParameter("processGoodsId");
		String[] idStr = ids.split(",");
		String[] useNums = useNum.split(",");
		double totalUseNums = 0;
		double store = 0;
		ProcessGoods process = (ProcessGoods) processGoodsService.getObjById(
				processGoodsId, "ProcessGoods");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsItemsService
							.getObjById(idStr[i], "ProcessGoodsItems");
					List<ZcStorehouse> zcStorehouseList = processGoodsService
							.getListByObj(ZcStorehouse.class,
									"BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5' and GOODSFILE_ID='"
											+ processGoodsItems.getGoodsFile()
													.getId() + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						ZcStorehouse zcStorehouse = zcStorehouseList.get(0);
						store = Double.valueOf(zcStorehouse.getStore());
					}
					if ("1".equals(processGoodsItems.getTypeFlag())) {
						processGoodsItems.setUseNum(useNums[i]);
						processGoodsItems.setDelFlag("");
						processGoodsItemsService.updateObj(processGoodsItems);
					}
				}
			}
			if (useNums != null && useNums.length > 0) {
				for (int i = 0; i < useNums.length; i++) {
					if (!StringUtils.isBlank(useNums[i])) {
						totalUseNums += Double.valueOf(useNums[i]);
					}
				}
			}
			process.setUseTotalNum(String.valueOf(totalUseNums));
			processGoodsService.updateObj(process);
			logManageService.insertLog(request, "保存了加工单商品信息", "加工单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}*/

	/**
	 * 结束加工
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "finishProcessGoods", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult finishProcessGoods(@ModelAttribute ProcessGoods process,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		List<ProcessGoodsItems> processGoodsItemList = processGoodsItemsService
				.getListByObj(ProcessGoodsItems.class, "PROCESSGOODSID='"
						+ processGoods.getId() + "'");
		try {
			if (processGoodsItemList != null && processGoodsItemList.size() > 0) {
				for (int i = 0; i < processGoodsItemList.size(); i++) {
					ProcessGoodsItems processGoodsItems = processGoodsItemList
							.get(i);
					if ("1".equals(processGoodsItems.getTypeFlag())) {
						// 加工配送库减少原材料库存
						String goodsId = processGoodsItems.getGoodsFile()
								.getId();
						List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
								.getListByObj(
										ZcStorehouse.class,
										"GOODSFILE_ID='"
												+ goodsId
												+ "' and BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5'");
						if (zcStorehouseList.size() > 0
								&& zcStorehouseList != null) {
							ZcStorehouse storeHouse = zcStorehouseList.get(0);
							// 库存中原材料成本单价
							double goodsprice = Double.valueOf(storeHouse
									.getStoreMoney())
									/ Double.valueOf(storeHouse.getStore());
							double stock = Double.valueOf(processGoodsItems
									.getUseNum());
							double oldStock = Double.valueOf(storeHouse
									.getStore());
							storeHouse.setStoreMoney((Double.valueOf(storeHouse
									.getStoreMoney()) - Double
									.valueOf(processGoodsItems.getUseNum())
									* goodsprice)
									+ "");
							storeHouse.setStore(String
									.valueOf(oldStock - stock));
							storeHouse.setWeight(processGoodsItems.getGoodsWeight());
							processGoodsItemsService.updateObj(storeHouse);
						} else {
							ZcStorehouse storeHouse = new ZcStorehouse();
							Branch branch = (Branch) processGoodsItemsService
									.getObjById(
											"596BA834-0618-4902-BCDF-2A70499C43B5",
											"Branch");
							storeHouse.setBranch(branch);
							storeHouse.setGoodsFile(processGoodsItems
									.getGoodsFile());
							storeHouse.setStore("-"
									+ processGoodsItems.getUseNum());
							storeHouse.setWeight(processGoodsItems.getGoodsWeight());
							processGoodsItemsService.saveObj(storeHouse);
						}
					} else {
						// 加工配送库添加成品库存
						String goodsId = processGoodsItems.getGoodsFile()
								.getId();
						double goodsprice = 0;
						double useNums = 0;
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String processTime = dateFormat.format(processGoods
								.getCreateTime());
						List<ZcCostRecordItems> zcCostRecordItemsList = processGoodsItemsService
								.getListByObj(
										ZcCostRecordItems.class,
										"PRODUCTGOODS='"
												+ goodsId
												+ "' and to_char(PROCESSTIME,'YYYY-MM-DD')='"
												+ processTime + "'");
						if (zcCostRecordItemsList != null
								&& zcCostRecordItemsList.size() > 0) {
							ZcCostRecordItems zcCostRecordItems = zcCostRecordItemsList
									.get(0);
							// 取该成品对应的原材料对象，继而获取在本加工单中原材料的重量等属性
							List<GoodsFile> goodsFilesList = processGoodsItemsService
									.getListByObj(GoodsFile.class,
											"PRODUCTGOODSID='" + goodsId + "'");
							if (goodsFilesList != null
									&& goodsFilesList.size() > 0) {
								GoodsFile goodsFile = goodsFilesList.get(0);
								List<ProcessGoodsItems> processMaterialItemsList = processGoodsItemsService
										.getListByObj(
												ProcessGoodsItems.class,
												"TYPEFLAG=1 and GOODSFILE_ID='"
														+ goodsFile.getId()
														+ "' and PROCESSGOODSID='"
														+ processGoods.getId()
														+ "'");
								if (processMaterialItemsList != null
										&& processMaterialItemsList.size() > 0) {
									// 获取到本加工单中成品对应的原材料对象
									ProcessGoodsItems goodsItems = processMaterialItemsList
											.get(0);
									List<ZcStorehouse> zcStorehousesList = processGoodsItemsService
											.getListByObj(
													ZcStorehouse.class,
													"GOODSFILE_ID='"
															+ goodsFile.getId()
															+ "' and BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5'");
									if (zcStorehousesList != null
											&& zcStorehousesList.size() > 0) {
										ZcStorehouse zcStorehouse = zcStorehousesList
												.get(0);
										goodsprice = Double
												.valueOf(zcStorehouse
														.getStoreMoney())
												/ Double.valueOf(zcStorehouse
														.getStore());
									} else {
										goodsprice = Double.valueOf(goodsFile
												.getGoods_price() + "");
									}
									zcCostRecordItems
											.setWastePercent(((Double.valueOf(zcCostRecordItems
													.getMaterialWeight())
													+ Double.valueOf(goodsItems
															.getUseNum()) - (Double
													.valueOf(processGoodsItems
															.getGoodsWeight()) + Double.valueOf(zcCostRecordItems
													.getProductWeight()))) / (Double.valueOf(zcCostRecordItems
													.getMaterialWeight()) + Double
													.valueOf(goodsItems
															.getUseNum())))
													+ "");
									zcCostRecordItems
											.setMaterialWeight((Double.valueOf(zcCostRecordItems
													.getMaterialWeight()) + Double
													.valueOf(goodsItems
															.getUseNum()))
													+ "");
									zcCostRecordItems
											.setCostingMoney((Double
													.valueOf(zcCostRecordItems
															.getCostingMoney()) + Double
													.valueOf(goodsItems
															.getUseNum())
													* goodsprice)
													+ "");
									useNums = Double.valueOf(goodsItems
											.getUseNum());
								}
							}
							List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
									.getListByObj(
											ZcStorehouse.class,
											"GOODSFILE_ID='"
													+ goodsId
													+ "'and BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5'");
							if (zcStorehouseList != null
									&& zcStorehouseList.size() > 0) {
								ZcStorehouse zcStorehouse = zcStorehouseList
										.get(0);
								double oldStock = Double.valueOf(zcStorehouse
										.getStore());
								double stock = Double.valueOf(processGoodsItems
										.getGoodsNum());
								double storemoney = Double.valueOf(zcStorehouse
										.getStoreMoney())
										+ goodsprice
										* useNums;
								zcStorehouse.setStoreMoney(storemoney + "");
								zcStorehouse.setWeight(processGoodsItems.getGoodsWeight());
								zcStorehouse.setStore(String.valueOf(oldStock
										+ stock));
								processGoodsItemsService
										.updateObj(zcStorehouse);
							} else {
								ZcStorehouse storeHouse = new ZcStorehouse();
								Branch branch = (Branch) processGoodsItemsService
										.getObjById(
												"596BA834-0618-4902-BCDF-2A70499C43B5",
												"Branch");
								storeHouse.setBranch(branch);
								storeHouse.setGoodsFile(processGoodsItems
										.getGoodsFile());
								storeHouse.setStoreMoney((goodsprice * useNums)
										+ "");
								// 保存份数
								storeHouse.setStore(processGoodsItems
										.getGoodsNum());
								storeHouse.setWeight(processGoodsItems.getGoodsWeight());
								processGoodsItemsService.saveObj(storeHouse);
							}
							zcCostRecordItems.setProductGoods(processGoodsItems
									.getGoodsFile());
							zcCostRecordItems.setGoodsName(processGoodsItems
									.getGoodsFile().getGoods_name());
							zcCostRecordItems.setProductCopies((Double
									.valueOf(zcCostRecordItems
											.getProductCopies()) + Double
									.valueOf(processGoodsItems.getGoodsNum()))
									+ "");
							zcCostRecordItems.setProductPrice(processGoodsItems
									.getGoodsFile().getGoods_price() + "");
							zcCostRecordItems
									.setProductWeight((Double
											.valueOf(processGoodsItems
													.getGoodsWeight()) + Double
											.valueOf(zcCostRecordItems
													.getProductWeight()))
											+ "");
							processGoodsService.updateObj(zcCostRecordItems);
						} else {
							// 新增一条加工成本记录
							ZcCostRecordItems zcCostRecordItems = new ZcCostRecordItems();
							zcCostRecordItems.setId(UuidUtils.getUUID());
							// 取该成品对应的原材料对象，继而获取在本加工单中原材料的重量等属性
							List<GoodsFile> goodsFilesList = processGoodsItemsService
									.getListByObj(GoodsFile.class,
											"PRODUCTGOODSID='" + goodsId + "'");
							if (goodsFilesList != null
									&& goodsFilesList.size() > 0) {
								GoodsFile goodsFile = goodsFilesList.get(0);
								List<ProcessGoodsItems> processMaterialItemsList = processGoodsItemsService
										.getListByObj(
												ProcessGoodsItems.class,
												"TYPEFLAG=1 and GOODSFILE_ID='"
														+ goodsFile.getId()
														+ "' and PROCESSGOODSID='"
														+ processGoods.getId()
														+ "'");
								if (processMaterialItemsList != null
										&& processMaterialItemsList.size() > 0) {
									// 获取到本加工单中成品对应的原材料对象
									ProcessGoodsItems goodsItems = processMaterialItemsList
											.get(0);
									List<ZcStorehouse> zcStorehousesList = processGoodsItemsService
											.getListByObj(
													ZcStorehouse.class,
													"GOODSFILE_ID='"
															+ goodsFile.getId()
															+ "' and BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5'");
									if (zcStorehousesList != null
											&& zcStorehousesList.size() > 0) {
										ZcStorehouse zcStorehouse = zcStorehousesList
												.get(0);
										goodsprice = Double
												.valueOf(zcStorehouse
														.getStoreMoney())
												/ Double.valueOf(zcStorehouse
														.getStore());
									} else {
										goodsprice = Double.valueOf(goodsFile
												.getGoods_price() + "");
									}
									zcCostRecordItems
											.setMaterialGoods(goodsItems
													.getGoodsFile());
									zcCostRecordItems
											.setMaterialWeight(goodsItems
													.getUseNum());
									zcCostRecordItems
											.setCostingMoney((Double
													.valueOf(goodsItems
															.getUseNum()) * goodsprice)
													+ "");
									zcCostRecordItems
											.setWastePercent(((Double
													.valueOf(goodsItems
															.getUseNum()) - Double
													.valueOf(Math.abs(Double.valueOf(processGoodsItems
															.getGoodsWeight())))) / Double
													.valueOf(goodsItems
															.getUseNum()))
													+ "");
									useNums = Double.valueOf(goodsItems
											.getUseNum());
								}
							}
							List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
									.getListByObj(
											ZcStorehouse.class,
											"GOODSFILE_ID='"
													+ goodsId
													+ "'and BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5'");
							if (zcStorehouseList != null
									&& zcStorehouseList.size() > 0) {
								ZcStorehouse zcStorehouse = zcStorehouseList
										.get(0);
								double oldStock = Math.abs(Double.valueOf(zcStorehouse
										.getStore()));
								double stock = Math.abs(Double.valueOf(processGoodsItems
										.getGoodsNum()));
								double storemoney = Double.valueOf(zcStorehouse
										.getStoreMoney())
										+ goodsprice
										* useNums;
								zcStorehouse.setStoreMoney(storemoney + "");
								zcStorehouse.setStore(String.valueOf(oldStock
										+ stock));
								zcStorehouse.setWeight(processGoodsItems.getGoodsWeight());
								processGoodsItemsService
										.updateObj(zcStorehouse);
							} else {
								ZcStorehouse storeHouse = new ZcStorehouse();
								Branch branch = (Branch) processGoodsItemsService
										.getObjById(
												"596BA834-0618-4902-BCDF-2A70499C43B5",
												"Branch");
								storeHouse.setBranch(branch);
								storeHouse.setGoodsFile(processGoodsItems
										.getGoodsFile());
								storeHouse.setStoreMoney((goodsprice * useNums)
										+ "");
								storeHouse.setStore(Math.abs(Double.valueOf(processGoodsItems
										.getGoodsNum()))+"");
								storeHouse.setWeight(processGoodsItems.getGoodsWeight());
								processGoodsItemsService.saveObj(storeHouse);
							}
							zcCostRecordItems.setProductGoods(processGoodsItems
									.getGoodsFile());
							zcCostRecordItems.setGoodsName(processGoodsItems
									.getGoodsFile().getGoods_name());
							zcCostRecordItems
									.setProductCopies(Math.abs(Double.valueOf(processGoodsItems
											.getGoodsNum()))+"");
							zcCostRecordItems.setProductPrice(processGoodsItems
									.getGoodsFile().getGoods_price() + "");
							zcCostRecordItems
									.setProductWeight(Math.abs(Double.valueOf(processGoodsItems
											.getGoodsWeight()))+"");
							zcCostRecordItems.setProcessTime(dateFormat
									.parse(dateFormat.format(processGoods
											.getCreateTime())));
							processGoodsService.saveObj(zcCostRecordItems);
						}
					}
				}
				processGoods.setStatue(Constant.CHECK_STATUS_FINISH);
				processGoodsService.updateObj(processGoods);
				logManageService.insertLog(request, "完成了一条加工单", "加工单");
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
	 * 根据商品表勾选保存商品到成品单中 (手持机扫描具体方法，重新确认)
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveProductIntoProcessGoodsItems", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult saveProductIntoProcessGoodsItems(
			@ModelAttribute ProcessGoodsItems processGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String processGoodsId = request.getParameter("processGoodsId");
		String[] idStr = ids.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ProcessGoodsItems processGoodsItems = new ProcessGoodsItems();
					String id = idStr[i];
					List<GoodsFile> goodsFileList = processGoodsItemsService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ id + "'");
					if (goodsFileList == null || goodsFileList.size() <= 0) {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.FAIL, AjaxResult.INFO);
						return ajaxResult;
					} else {
						GoodsFile goodsFile = goodsFileList.get(0);
						String goodsId = goodsFile.getId();
						Long count = processGoodsItemsService.getCountByObj(
								ProcessGoodsItems.class, "PROCESSGOODSID='"
										+ processGoodsId
										+ "' and GOODSFILE_ID='" + goodsId
										+ "'");
						if (count != 0) {
							ajaxResult = new AjaxResult(AjaxResult.SAVE,
									AjaxResult.ERROR, AjaxResult.INFO);
							return ajaxResult;
						} else {
							GoodsFile goods = (GoodsFile) processGoodsItemsService
									.getObjById(goodsId, "GoodsFile");
							processGoodsItems.setId(UuidUtils.getUUID());
							processGoodsItems.setProcessGoodsId(processGoodsId);
							processGoodsItems.setGoodsFile(goods);
							processGoodsItems.setSerialNumber(goods
									.getSerialNumber());
							processGoodsItems.setGoodsName(goods
									.getGoods_name());
							processGoodsItems.setUnit(goods.getGoods_unit());
							processGoodsItems.setSpecifications(goods
									.getGoods_specifications());
							processGoodsItems.setTypeFlag("1");
							processGoodsItems.setUseNum("0");
							processGoodsItemsService.saveObj(processGoodsItems);
						}
					}
				}
			}
			logManageService.insertLog(request, "保存了勾选的商品到加工原料", "加工单");
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
	 * 提交审核
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "commitCheck", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult commitCheck(HttpServletRequest request,
			HttpServletResponse response, String id) {
		AjaxResult ajaxResult = null;
		try {
			String processGoodsId = request.getParameter("processGoodsId");
			ProcessGoods processGoods = (ProcessGoods) processGoodsService
					.getObjById(processGoodsId, "ProcessGoods");
			processGoods.setStatue(Constant.CHECK_STATUS_WAITCHECK);
			processGoodsService.updateObj(processGoods);
			logManageService.insertLog(request, "提交了一条加工单 ", "加工单");
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
	 * 打开审核页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("checkProcessGoods")
	public ModelAndView checkProcessGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		model.addAttribute("processGoods", processGoods);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_check");
		return view;
	}

	/**
	 * 审核通过
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkPass(@ModelAttribute ProcessGoods process,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			processGoods.setStatue(Constant.CHECK_STATUS_PASS);
			processGoods.setCheckMan(userInfo.getUserName());
			processGoods.setCheckDate(df.parse(df.format(new Date())));
			processGoodsService.updateObj(processGoods);
			logManageService.insertLog(request, "审核了一条加工单，审核通过", "加工单");
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
	 * 审核不通过
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkNoPass", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult checkNoPass(String id, String type, String reason,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		ZcUserInfo userInfo = (ZcUserInfo) request.getSession().getAttribute(
				"userInfo");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ProcessGoods processGoods = (ProcessGoods) processGoodsService
					.getObjById(id, "ProcessGoods");
			processGoods.setStatue(Constant.CHECK_STATUS_NOPASS);
			processGoods.setCheckMan(userInfo.getUserName());
			processGoods.setRemark(reason);
			processGoods.setCheckDate(df.parse(df.format(new Date())));
			processGoodsService.updateObj(processGoods);
			logManageService.insertLog(request, "审核了一条加工单，审核不通过", "加工单");
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.SUCCESS,
					AjaxResult.INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(AjaxResult.SAVE, AjaxResult.FAIL,
					AjaxResult.INFO);
		}
		return ajaxResult;
	}

	@RequestMapping("gotoPrintProcessGoods")
	public ModelAndView gotoPrintProcessGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面传递的ID
		String id = request.getParameter("id");
		// 根据ID的获取订单详情
		List<ProcessGoodsItems> processUseGoodsList = processGoodsService
				.getListByObj(ProcessGoodsItems.class, "PROCESSGOODSID = '"
						+ id + "' and TYPEFLAG='1'");
		List<ProcessGoodsItems> goodsList = new ArrayList<ProcessGoodsItems>();
		String[] guige = null;
		if (processUseGoodsList != null && processUseGoodsList.size() > 0) {
			for (int i = 0; i < processUseGoodsList.size(); i++) {
				ProcessGoodsItems processGoodsItems = processUseGoodsList
						.get(i);
				if (processGoodsItems.getSpecifications() != null
						&& processGoodsItems.getSpecifications() != "") {
					guige = processGoodsItems.getSpecifications()
							.split("商品规格：");
					if (guige != null && guige.length > 1) {
						processGoodsItems.setSpecifications(guige[1]);
					} else if (guige != null && guige.length == 1) {
						processGoodsItems.setSpecifications(guige[0]);
					}
				} else {
					processGoodsItems.setSpecifications("");
				}
				goodsList.add(processGoodsItems);
			}
		}
		List<ProcessGoodsItems> processProductGoodsList = processGoodsService
				.getListByObj(ProcessGoodsItems.class, "PROCESSGOODSID = '"
						+ id + "' and TYPEFLAG='2'");
		List<ProcessGoodsItems> goods = new ArrayList<ProcessGoodsItems>();
		String[] shangpin = null;
		if (processProductGoodsList != null
				&& processProductGoodsList.size() > 0) {
			for (int i = 0; i < processProductGoodsList.size(); i++) {
				ProcessGoodsItems processGoodsItems = processProductGoodsList
						.get(i);
				if (processGoodsItems.getSpecifications() != null
						&& processGoodsItems.getSpecifications() != "") {
					shangpin = processGoodsItems.getSpecifications().split(
							"商品规格：");
					if (shangpin != null && shangpin.length > 1) {
						processGoodsItems.setSpecifications(shangpin[1]);
					} else if (shangpin != null && shangpin.length == 1) {
						processGoodsItems.setSpecifications(shangpin[0]);
					}
				} else {
					processGoodsItems.setSpecifications("");
				}
				goods.add(processGoodsItems);
			}

		}
		// 根据ID获取采购订单对象
		ProcessGoods processGoods = (ProcessGoods) processGoodsService
				.getObjById(id, "ProcessGoods");
		model.addAttribute("processGoods", processGoods);
		model.addAttribute("processUseGoodsList", goodsList);
		model.addAttribute("processProductGoodsList", goods);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_print");
		return view;
	}

	@RequestMapping("gotoPrintWaitProcessGoods")
	public ModelAndView gotoPrintWaitProcessGoods(
			@ModelAttribute GoodsFile goodsFile, String serialNumber,
			HttpServletRequest request, HttpServletResponse response,
			Model model, Page page) {
		page.setPage(1);
		page.setRows(1000);
		DataGrid dataGrid = null;
		DecimalFormat df = new DecimalFormat(".##");
		try {
			dataGrid = processGoodsItemsService.getWaittingProcessJson(page,
					goodsFile, serialNumber);
			// 获取页面行
			List<Map<String, Object>> list = dataGrid.getRows();
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					// 获取页面的商品ID
					String goodsId = (String) list.get(i).get("GOODSFILE_ID");
					// 获取页面的订单数量
					BigDecimal orderNeedsNums = (BigDecimal) list.get(i).get(
							"NUMS");
					// 库存为0
					double stock = 0;
					// 需要加工的数量
					double needProcessNums = 0;
					List<ZcStorehouse> zcStorehouseList = processGoodsItemsService
							.getListByObj(ZcStorehouse.class,
									"BRANCH_ID='596BA834-0618-4902-BCDF-2A70499C43B5' and GOODSFILE_ID='"
											+ goodsId + "'");
					if (zcStorehouseList != null && zcStorehouseList.size() > 0) {
						String nums = zcStorehouseList.get(0).getStore();
						if (StringUtils.isBlank(nums)) {
							nums = "0";
						}
						double num = Double.valueOf(nums);
						// 库存=实际库存量
						stock = num;
					}
					// 需要加工的量=订单需求量-库存量
					needProcessNums = orderNeedsNums.doubleValue() - stock;
					if (needProcessNums <= 0) {
						// 需要加工份数为0
						list.get(i).put("WAITPROCESSNUMS", "0");
					} else {
						// 需要加工份数为需要加工的量红色标记
						list.get(i).put(
								"WAITPROCESSNUMS",
								"<span style='color:red;font-weight:700 '>"
										+ needProcessNums + "</span>");
					}
					List<GoodsFile> goodsFileList = processGoodsItemsService
							.getListByObj(GoodsFile.class, "PRODUCTGOODSID='"
									+ goodsId + "'");
					if (goodsFileList == null || goodsFileList.size() <= 0) {
						list.get(i).put("USENEEDSNUMS", "0");
					} else {
						GoodsFile good = (GoodsFile) processGoodsService
								.getObjById(goodsId, "GoodsFile");
						String wastStr = good.getWasteRate();
						String str2 = "";
						String guige = good.getGoods_specifications();
						String[] shangpinguige = null;
						shangpinguige = guige.split("商品规格：");
						if (shangpinguige != null && shangpinguige.length > 1) {
							list.get(i).put("shangpinguige", shangpinguige[1]);
						} else if (shangpinguige != null
								&& shangpinguige.length == 1) {
							list.get(i).put("shangpinguige", shangpinguige[0]);
						}
						if (guige != null && guige != "") {
							for (int z = 0; z < guige.length(); z++) {
								if ((guige.charAt(z) >= '0' && guige.charAt(z) <= '9')
										|| guige.charAt(z) == '.') {
									str2 += guige.charAt(z);
								}
							}
						} else {
							list.get(i).put("GOODS_SPECIFICATIONS", "");
						}
						double spe = Double.valueOf(str2);
						double wast = Double.valueOf(wastStr);
						double storeNums = 0;
						String id = goodsFileList.get(0).getId();
						List<ZcStorehouse> storeUseList = processGoodsItemsService
								.getListByObj(ZcStorehouse.class,
										"goodsfile_id='" + id + "'");
						if (storeUseList != null && storeUseList.size() > 0) {
							for (int a = 0; a < storeUseList.size(); a++) {
								String num = storeUseList.get(a).getStore();
								if (StringUtils.isBlank(num)) {
									num = "0";
								}
								double nums = Double.valueOf(num);
								storeNums = storeNums + nums;
							}
							if (needProcessNums <= 0) {
								list.get(i).put("USENEEDSNUMS", "0");
							} else {
								double needNum = needProcessNums * spe
										* (1 + wast / 100);
								list.get(i).put("USENEEDSNUMS",
										df.format(needNum));
							}
						} else {
							double needNum = needProcessNums * spe
									* (1 + wast / 100);
							list.get(i).put("USENEEDSNUMS", df.format(needNum));
						}
						newList.add(list.get(i));
					}
					list.get(i).put("STOCKNUMS", stock);
				}
			}
			model.addAttribute("newList", newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView view = createIframeView("warehouse/processGoods/waitProcessGoods_print");
		return view;
	}

	/**
	 * 开始添加成品
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "addProduct", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult addProduct(@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		try {
			ProcessGoodsItems processGoodsItems = new ProcessGoodsItems();
			processGoodsItems.setId(UuidUtils.getUUID());
			processGoodsItems.setTypeFlag("2");
			processGoodsItems.setProcessGoodsId(id);
			processGoodsService.saveObj(processGoodsItems);
			logManageService.insertLog(request, "新增了一条加工单空白成品记录", "加工单");
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
	 * 扫码添加成品
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "productGoodsAdd", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult productGoodsAdd(
			@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		String serialNumberChar = request.getParameter("serialNumber");
		String[] serialNumber = serialNumberChar.split(",");
		try {
			if (idStr != null && idStr.length > 0) {
				if (serialNumber[0].length() == 18) {
					ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsService
							.getObjById(idStr[0], "ProcessGoodsItems");
					String goodsCode = serialNumber[0].substring(2, 7);
					String goodsWeight = serialNumber[0].substring(12, 17);
					double weight = Double.valueOf(goodsWeight) / 100;
					double goodsNums = 1;
					List<GoodsFile> goodsFileList = processGoodsService
							.getListByObj(GoodsFile.class, "serialNumber='"
									+ goodsCode + "'");
					if (goodsFileList != null && goodsFileList.size() > 0) {
						GoodsFile goodsFile = goodsFileList.get(0);
						List<ProcessGoodsItems> ProcessGoodsItemsList = processGoodsService
								.getListByObj(ProcessGoodsItems.class,
										"TYPEFLAG='2' and GOODSFILE_ID='"
												+ goodsFile.getId()
												+ "' and PROCESSGOODSID='" + id
												+ "'");
						if (ProcessGoodsItemsList != null
								&& ProcessGoodsItemsList.size() > 0) {
							ProcessGoodsItems goodsItems = ProcessGoodsItemsList
									.get(0);
							goodsItems
									.setGoodsWeight((Double.valueOf(goodsItems
											.getGoodsWeight()) + weight) + "");
							goodsItems.setGoodsNum((Double.valueOf(goodsItems
									.getGoodsNum()) + goodsNums) + "");
							processGoodsService.updateObj(goodsItems);
							ajaxResult = new AjaxResult(AjaxResult.SELECT,
									AjaxResult.SUCCESS, AjaxResult.INFO);
							return ajaxResult;
						}
						processGoodsItems
								.setGoodsWeight(String.valueOf(weight));
						processGoodsItems.setGoodsFile(goodsFile);
						processGoodsItems.setGoodsName(goodsFile
								.getGoods_name());
						processGoodsItems.setUnit(goodsFile.getGoods_unit());
						processGoodsItems.setSerialNumber(goodsFile
								.getSerialNumber());
						processGoodsItems.setSpecifications(goodsFile
								.getGoods_specifications());
						CtpUser ctpUser = (CtpUser) request.getSession()
								.getAttribute("user");
						processGoodsItems.setCtpUser(ctpUser);
						processGoodsItems.setGoodsNum(goodsNums + "");
						processGoodsItems.setProcessGoodsId(id);
						processGoodsItems.setDelFlag("1");
						processGoodsService.updateObj(processGoodsItems);
						ajaxResult = new AjaxResult(AjaxResult.SAVE,
								AjaxResult.SUCCESS, AjaxResult.INFO);
					} else {
						ajaxResult = new AjaxResult(AjaxResult.SELECT,
								AjaxResult.QUESTION, AjaxResult.INFO);
						return ajaxResult;
					}
				} else {
					ajaxResult = new AjaxResult(AjaxResult.SELECT,
							AjaxResult.FAIL, AjaxResult.INFO);
					return ajaxResult;
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
	 * 结束添加
	 * 
	 * @param goodsFile
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "endAdd", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult endAdd(@ModelAttribute AdvanceDetail advanceDetail,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String id = request.getParameter("id");
		try {
			List<ProcessGoodsItems> processGoodsItemsList = processGoodsService
					.getListByObj(ProcessGoodsItems.class,
							"GOODSFILE_ID is null and TYPEFLAG='2' and PROCESSGOODSID='"
									+ id + "'");
			if (processGoodsItemsList != null
					&& processGoodsItemsList.size() > 0) {
				for (int i = 0; i < processGoodsItemsList.size(); i++) {
					ProcessGoodsItems processGoodsItems = processGoodsItemsList
							.get(i);
					processGoodsService.deleteObjById(
							processGoodsItems.getId(),
							ProcessGoodsItems.class.getName());
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

	/**
	 * 保存成品
	 * 
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult updateProduct(@ModelAttribute ProcessGoods processGoods,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String ids = request.getParameter("ids");
		String productNums = request.getParameter("productNums");
		String processGoodsId = request.getParameter("processGoodsId");
		String[] idStr = ids.split(",");
		String[] productNum = productNums.split(",");
		double totalProductNums = 0;
		ProcessGoods process = (ProcessGoods) processGoodsService.getObjById(
				processGoodsId, "ProcessGoods");
		try {
			if (idStr != null && idStr.length > 0) {
				for (int i = 0; i < idStr.length; i++) {
					ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsItemsService
							.getObjById(idStr[i], "ProcessGoodsItems");
					if (processGoodsItems != null) {
						if ("2".equals(processGoodsItems.getTypeFlag())) {
							processGoodsItems.setDelFlag("");
							processGoodsItemsService
									.updateObj(processGoodsItems);
						}
					}
				}
			}
			if (productNum != null && productNum.length > 0) {
				for (int i = 0; i < productNum.length; i++) {
					if (!StringUtils.isBlank(productNum[i])) {
						totalProductNums += Double.valueOf(productNum[i]);
					}
				}
			}
			process.setGoodsTotalNum(String.valueOf(totalProductNums));
			processGoodsService.updateObj(process);
			logManageService.insertLog(request, "保存了加工单成品信息", "加工单");
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
	 * 移除成品
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeProduct", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult removeProduct(HttpServletRequest request,
			HttpServletResponse response, String ids) {
		AjaxResult ajaxResult = null;
		try {
			String processGoodsId = request.getParameter("processGoodsId");
			ProcessGoods processGoods = (ProcessGoods) processGoodsService
					.getObjById(processGoodsId, "ProcessGoods");
			String[] id = ids.split(",");
			double productNums = Double
					.valueOf(processGoods.getGoodsTotalNum());
			for (int i = 0; i < id.length; i++) {
				ProcessGoodsItems processGoodsItems = (ProcessGoodsItems) processGoodsItemsService
						.getObjById(id[i], "ProcessGoodsItems");
				if ("2".equals(processGoodsItems.getTypeFlag())) {
					if (processGoodsItems.getGoodsNum() != null) {
						productNums -= Double.valueOf(processGoodsItems
								.getGoodsNum());
						if (productNums < 0) {
							productNums = 0;
						}
					}
					processGoods.setGoodsTotalNum(String.valueOf(productNums));
					processGoodsService.updateObj(processGoods);
					processGoodsItemsService.deleteObjById(id[i],
							ProcessGoodsItems.class.getName());
				}
			}
			logManageService.insertLog(request, "移除了一条成品", "加工单");
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
	 * 打开选择分拣单页面
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("openChooseProcessGoods")
	public ModelAndView openChooseProcessGoods(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String processGoodsId = request.getParameter("processGoodsId");
		model.addAttribute("processGoodsId", processGoodsId);
		ModelAndView view = createIframeView("warehouse/processGoods/processGoods_choseSorte");
		return view;
	}

	/**
	 * 根据商品表勾选保存商品到原料单中
	 * 
	 * @param ordersView
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "chooseThisProcess", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public AjaxResult chooseThisProcess(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxResult ajaxResult = null;
		String processGoodsId = request.getParameter("processGoodsId");
		String sorteId = request.getParameter("sorteId");
		double goodsNumsTotal = 0;
		try {
			//加工原料详细单
			List<ProcessGoodsItems> goodsItemsList = processGoodsService
					.getListByObj(ProcessGoodsItems.class,
							"TYPEFLAG='1' and PROCESSGOODSID='"
									+ processGoodsId + "'");
			if (goodsItemsList != null && goodsItemsList.size() > 0) {
				for (int i = 0; i < goodsItemsList.size(); i++) {
					ProcessGoodsItems processGoodsItems = goodsItemsList.get(i);
					//成品商品
					GoodsFile goodsFile = (GoodsFile) processGoodsService
							.getObjById(processGoodsItems.getGoodsFile()
									.getId(), "GoodsFile");
					if (goodsFile.getProductGoodsId() != null) {
						//分拣单
						List<OrdersSorte> ordersSortesLists = processGoodsService
								.getListByObj(OrdersSorte.class, "sorteId='"
										+ sorteId + "' and goods_id='"
										+ goodsFile.getProductGoodsId() + "'");
						if (ordersSortesLists != null
								&& ordersSortesLists.size() > 0) {
							for (int j = 0; j < ordersSortesLists.size(); j++) {
								OrdersSorte ordersSorte = ordersSortesLists
										.get(j);
								List<ProcessGoodsItems> processGoodsItemsList = processGoodsService
										.getListByObj(
												ProcessGoodsItems.class,
												"TYPEFLAG='2' and PROCESSGOODSID='"
														+ processGoodsId
														+ "' and GOODSFILE_ID='"
														+ ordersSorte
																.getGoodsId()
														+ "'");
								if (processGoodsItemsList != null
										&& processGoodsItemsList.size() > 0) {
									ProcessGoodsItems goodsItems = processGoodsItemsList
											.get(0);
									if(goodsItems.getGoodsNum()==null||goodsItems.getGoodsNum().equals("")
											||goodsItems.getGoodsNum().equals("0")){
										goodsItems
										.setGoodsNum((Double
												.valueOf(0) + 1)
												+ "");
									}else{
										goodsItems
										.setGoodsNum((Double
												.valueOf(goodsItems
														.getGoodsNum()) + 1)
												+ "");
									}
									if((goodsItems.getGoodsWeight()==null||goodsItems.getGoodsWeight().equals("")||goodsItems.getGoodsWeight().equals("0"))
										&&(ordersSorte.getWeight().equals("")||ordersSorte.getWeight()==null||ordersSorte.getWeight().equals("0"))	){
										goodsItems.setGoodsWeight((Double
												.valueOf(0) + Double
												.valueOf(0))
												+ "");
									}else if((goodsItems.getGoodsWeight()==null||goodsItems.getGoodsWeight().equals("")||goodsItems.getGoodsWeight().equals("0"))
											&&(!ordersSorte.getWeight().equals("")||ordersSorte.getWeight()!=null||!ordersSorte.getWeight().equals("0"))){
										goodsItems.setGoodsWeight((Double
												.valueOf(0) + Double
												.valueOf(ordersSorte.getWeight()))
												+ "");
									}else if((goodsItems.getGoodsWeight()!=null||!goodsItems.getGoodsWeight().equals("")||!goodsItems.getGoodsWeight().equals("0"))
											&&(!ordersSorte.getWeight().equals("")||ordersSorte.getWeight()!=null||!ordersSorte.getWeight().equals("0"))){
										goodsItems.setGoodsWeight((Double
												.valueOf(goodsItems
														.getGoodsWeight()) + Double
												.valueOf(ordersSorte.getWeight()))
												+ "");
									}
									
									processGoodsService.updateObj(goodsItems);
									goodsNumsTotal += 1;
								} else {
									GoodsFile goods = (GoodsFile) processGoodsService
											.getObjById(
													ordersSorte.getGoodsId(),
													"GoodsFile");
									ProcessGoodsItems goodsItems = new ProcessGoodsItems();
									goodsItems.setId(UuidUtils.getUUID());
									goodsItems.setTypeFlag("2");
									goodsItems
											.setProcessGoodsId(processGoodsId);
									goodsItems.setGoodsNum("1");
									goodsItems.setGoodsWeight(ordersSorte
											.getWeight());
									goodsItems.setGoodsFile(goods);
									goodsItems.setGoodsName(goods
											.getGoods_name());
									goodsItems.setSpecifications(goods
											.getGoods_specifications());
									goodsItems.setUnit(goods.getGoods_unit());
									goodsItems.setSerialNumber(goods
											.getSerialNumber());
									processGoodsService.saveObj(goodsItems);
									goodsNumsTotal += 1;
								}
								//原材料的用量减少
								Double realUseNums =Double.parseDouble(processGoodsItems.getUseNum())-1;
								processGoodsItems.setUseNum(realUseNums.toString());
								processGoodsService.updateObj(processGoodsItems);
								
								
							}
						}
					}
				}
			}
			ProcessGoods processGoods = (ProcessGoods) processGoodsService
					.getObjById(processGoodsId, "ProcessGoods");
			processGoods.setGoodsTotalNum(goodsNumsTotal + "");
			processGoodsService.updateObj(processGoods);
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
