package com.proem.exm.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.purchase.PurchaseReceiveItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.ChangeGoodsItems;
import com.proem.exm.entity.warehouse.CheckGoodsItems;
import com.proem.exm.entity.warehouse.SwitchGoodsItems;
import com.proem.exm.entity.warehouse.ZcCheckDifference;
import com.proem.exm.entity.warehouse.ZcStoreChange;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.service.system.NoticeService;
import com.proem.exm.service.warehouse.SwitchChangeService;
import com.proem.exm.utils.StringUtil;

/**
 * 公告管理
 * 
 * @author psl
 * 
 * @com proem
 */
@Controller
@RequestMapping("/importExcel")
public class ImportExcelController extends BaseController {
	private static final String OBLIQUE_LINE = "/";
	private static final String WEBPOSITION = "webapps";
	private static final String WTPPWEBAPPS = "wtpwebapps";

	public static final String SBPATH = "Excel/";
	@Autowired
	NoticeService noticeService;
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private SwitchChangeService switchService;

	@InitBinder("purchaseOrderGoodsItems")
	public void initUser(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("purchaseOrderGoodsItems.");
	}

	/**
	 * 保存文件至服务器
	 */
	private String saveExcelFile(HttpServletRequest request,
			MultipartFile myfile) {
		String path = "";
		// 文件保存路径
		try {
			String tomcatPath = request.getSession().getServletContext()
					.getRealPath("/");
			tomcatPath = tomcatPath.replace("\\", "/");
			StringBuffer destFName = new StringBuffer();
			destFName.append(getRealDir(tomcatPath).replace("exm/", "/"))
					.append(SBPATH);
			System.out.println("得到的地址是：" + destFName);
			String filePath = destFName.toString().replace("\\", "/");
			// 转存文件
			String fileName = myfile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			fileName = "" + System.currentTimeMillis();
			if (fileType.equals(".xls")) {
				fileName = fileName + ".xls";
				File targetFile = new File(filePath, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs(); // 上传图片到服务器
					myfile.transferTo(targetFile);
					path = targetFile.getPath();
				}
			} else {
				path = "fail";
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 验证excel的目录
	 * 
	 * @param newFileNameRoot
	 * @return
	 * @throws Exception
	 */
	private String getRealDir(String newFileNameRoot) throws Exception {
		if (newFileNameRoot == null)
			throw new Exception("get real dir failed !");
		int dp = newFileNameRoot.lastIndexOf(OBLIQUE_LINE);
		if (dp == -1)
			throw new Exception("invalid path !");
		int dpbefore = newFileNameRoot.lastIndexOf(OBLIQUE_LINE, dp - 1);
		if (dpbefore == -1)
			throw new Exception("invalid path !");
		String needSubStr = newFileNameRoot.substring(dpbefore + 1, dp);
		String nextStr = newFileNameRoot.substring(0, dpbefore + 1);
		if (!needSubStr.trim().equals(WEBPOSITION)
				&& !needSubStr.trim().equals(WTPPWEBAPPS)) {
			return getRealDir(nextStr);
		} else
			return newFileNameRoot;
	}
	
	public Map<String, Object> getAllByExcel(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 瀵瑰簲excel鐨勮
			HSSFCell cell = null;// 瀵瑰簲excel鐨勫垪
			int totalRow = sheet.getLastRowNum();

			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// System.out.println(row.getCell(0).toString());
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
	 				HSSFCell goods_code = row.getCell(0);
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(1);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell actual_num = row.getCell(5);
					goodsFile.setGoods_state(getValue(actual_num) == null ? ""
							: getValue(actual_num).trim());
					HSSFCell store_num = row.getCell(4);
					goodsFile.setStore(getValue(store_num) == null ? ""
							: getValue(store_num).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 盘点单导入
	 * 
	 * @param myfile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveCheckItemImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveBatchImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			String wareHouseId, HttpServletRequest request) {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		ZcWarehouse warehouse = new ZcWarehouse();
		warehouse = (ZcWarehouse) noticeService.getObjById(wareHouseId,
				"ZcWarehouse");
		Map<String, Object> listResult = getAllByExcel(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					int re = saveByExcel(goodsFile, warehouse);
					if (re == 0) {
						result.put("msg", "fail");
						JSONObject jsonObject = JSONObject.fromObject(result);
						return jsonObject.toString();
					}
				}
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}

	}

	private int saveByExcel(GoodsFile goodsFile) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Object> getAllChangeByExcel(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// System.out.println(row.getCell(0).toString());
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell goods_code = row.getCell(3);
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(0);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell actual_num = row.getCell(5);
					goodsFile.setGoods_state(getValue(actual_num) == null ? ""
							: getValue(actual_num).trim());
					HSSFCell store_num = row.getCell(4);
					goodsFile.setStore(getValue(store_num) == null ? ""
							: getValue(store_num).trim());
					HSSFCell goods_price = row.getCell(8);
					if (goods_price==null||goods_price.equals("")) {
						Float.parseFloat(goods_price+"");
						float newGoodsPrice = 0;
						goodsFile.setGoods_price(newGoodsPrice);
					}
					goodsFile.setGoods_price(Float.parseFloat(goods_price+""));
					HSSFCell differenceReason = row.getCell(11);
					goodsFile.setRemark(getValue(differenceReason) == null ? ""
							: getValue(differenceReason).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell != null) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				// 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} else {
			return null;
		}
	}

	public int saveByExcel(GoodsFile goodsFile, ZcWarehouse warehouse) {
		try {
			String goodId = goodsFile.getGoods_code();
			List<CheckGoodsItems> goodsItems = noticeService.getListByObj(
					CheckGoodsItems.class, "goodsfile_id='" + goodId
							+ "' and warehouse_id='" + warehouse.getId() + "'");
			if (goodsItems != null && goodsItems.size() > 0) {
				goodsItems.get(0).setActualCheckNumber(
						goodsFile.getGoods_state());
				noticeService.updateObj(goodsItems.get(0));
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 采购订单导入
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/savePOItemImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String savePOItemImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			String purchaseId, HttpServletRequest request)
			throws ParseException {
		PurchaseOrder purchaseOrder = (PurchaseOrder) noticeService.getObjById(
				purchaseId, "PurchaseOrder");
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Map<String, Object> listResult = getItemsOnList(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								PurchaseOrderGoodsItems obj = (PurchaseOrderGoodsItems) noticeService
										.getObjById(idStr[j],
												"PurchaseOrderGoodsItems");
								if (obj != null) {
									if (obj.getSerialNumber().equals(
											goodsFile.getSerialNumber())) {
										if (Float.valueOf(obj.getActualNeed()) == 0) {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											} else {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											}
										} else {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											} else {
												obj.setActualNeed(Double
														.valueOf(goodsFile
																.getStore())
														+ "");
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											}
										}
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
										purchaseOrderGoodsItems.setId(UuidUtils
												.getUUID());
										purchaseOrderGoodsItems
												.setPurchaseId(purchaseOrder
														.getId());
										purchaseOrderGoodsItems
												.setGoodsFile(file);
										purchaseOrderGoodsItems
												.setSerialNumber(file
														.getSerialNumber());
										purchaseOrderGoodsItems
												.setGoodsName(file
														.getGoods_name());
										purchaseOrderGoodsItems
												.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
										purchaseOrderGoodsItems
												.setOrderNum(Double.valueOf(goodsFile
														.getGoods_state()));
										purchaseOrderGoodsItems
												.setActualNeed(goodsFile
														.getStore());
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date date = new Date();
										purchaseOrderGoodsItems
												.setProduceDate(sdf.parse(sdf
														.format(date)));
										purchaseOrderGoodsItems
												.setSpecifications(file
														.getGoods_specifications());
										purchaseOrderGoodsItems.setUnit(file
												.getGoods_unit());
										purchaseOrderGoodsItems
												.setPurchaseMan(ctpUser);
										noticeService
												.saveObj(purchaseOrderGoodsItems);
									}
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
									purchaseOrderGoodsItems.setId(UuidUtils
											.getUUID());
									purchaseOrderGoodsItems
											.setPurchaseId(purchaseOrder
													.getId());
									purchaseOrderGoodsItems.setGoodsFile(file);
									purchaseOrderGoodsItems
											.setSerialNumber(file
													.getSerialNumber());
									purchaseOrderGoodsItems.setGoodsName(file
											.getGoods_name());
									purchaseOrderGoodsItems
											.setGoodsPrice(Double
													.valueOf(goodsFile
															.getRemark()));
									purchaseOrderGoodsItems
											.setOrderNum(Double
													.valueOf(goodsFile
															.getGoods_state()));
									purchaseOrderGoodsItems
											.setActualNeed(goodsFile.getStore());
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									Date date = new Date();
									// String dateStr = sdf.format(date);
									purchaseOrderGoodsItems.setProduceDate(sdf
											.parse(sdf.format(date)));
									purchaseOrderGoodsItems
											.setSpecifications(file
													.getGoods_specifications());
									purchaseOrderGoodsItems.setUnit(file
											.getGoods_unit());
									purchaseOrderGoodsItems
											.setPurchaseMan(ctpUser);
									noticeService
											.saveObj(purchaseOrderGoodsItems);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemsList = noticeService
						.getListByObj(
								PurchaseOrderGoodsItems.class,
								"PURCHASEID='"
										+ purchaseOrder.getId()
										+ "'and ACTUALNEED='0' or ACTUALNEED is null ");
				if (purchaseOrderGoodsItemsList != null
						&& purchaseOrderGoodsItemsList.size() > 0) {
					for (int i = 0; i < purchaseOrderGoodsItemsList.size(); i++) {
						PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoodsItemsList
								.get(i);
						noticeService.deleteObjById(
								purchaseOrderGoodsItems.getId(),
								PurchaseOrderGoodsItems.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}

	public Map<String, Object> getPOItemAllByExcel(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// if (row == null) {
				// row = sheet.createRow(i);
				// }
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell goods_code = row.getCell(0);
					goodsFile.setSerialNumber(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(1);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell actual_num = row.getCell(3);
					for (int j = 0; j < getValue(actual_num).length(); j++) {
						System.out.println(getValue(actual_num).charAt(j));
						if (Character.isDigit(getValue(actual_num).charAt(j))) {
							goodsFile
									.setGoods_state(getValue(actual_num) == null ? ""
											: getValue(actual_num).trim());
						} else {
							goodsFile.setGoods_state("0");
						}
					}
					HSSFCell store_num = row.getCell(4);
					goodsFile.setStore(getValue(store_num) == null ? ""
							: getValue(store_num).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int savePOItemByExcel(GoodsFile goodsFile,
			PurchaseOrder purchaseOrder, String[] idStr) {
		try {
			String goodId = goodsFile.getGoods_code();
			String nums = goodsFile.getStore();
			GoodsFile file = null;
			if (nums.equals("0") || nums == "0") {
				return 1;
			}

			List<GoodsFile> goodsFilesList = noticeService.getListByObj(
					GoodsFile.class, "serialNumber='" + goodId + "'");
			if (goodsFilesList != null && goodsFilesList.size() > 0) {
				file = goodsFilesList.get(0);
			}
			if (file == null) {
				return 0;
			}
			// boolean flag = goodsChoose(purchaseOrder.getOdd(),goodId);
			PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
			PurchaseOrderGoodsItems obj = new PurchaseOrderGoodsItems();
			for (int i = 0; i < idStr.length; i++) {
				obj = (PurchaseOrderGoodsItems) noticeService.getObjById(
						idStr[i], "PurchaseOrderGoodsItems");
				if (obj == null || obj.getSerialNumber().equals("")
						|| !obj.getSerialNumber().equals(goodId)) {

					purchaseOrderGoodsItems
							.setPurchaseId(purchaseOrder.getId());
					purchaseOrderGoodsItems.setGoodsFile(file);
					purchaseOrderGoodsItems.setSerialNumber(file
							.getSerialNumber());
					purchaseOrderGoodsItems.setGoodsName(file.getGoods_name());
					purchaseOrderGoodsItems.setGoodsPrice(Double.valueOf(file
							.getGoods_purchase_price() + ""));
					purchaseOrderGoodsItems.setOrderNum(Double
							.valueOf(goodsFile.getGoods_state()));
					purchaseOrderGoodsItems.setActualNeed(goodsFile.getStore());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					// String dateStr = sdf.format(date);
					purchaseOrderGoodsItems.setProduceDate(sdf.parse(sdf
							.format(date)));
					purchaseOrderGoodsItems.setSpecifications(file
							.getGoods_specifications());
					purchaseOrderGoodsItems.setUnit(file.getGoods_unit());
					noticeService.saveObj(purchaseOrderGoodsItems);
				} else {

					obj.setGoodsPrice(Double.valueOf(file
							.getGoods_purchase_price() + ""));
					obj.setActualNeed((Double.valueOf(obj.getActualNeed()) + Double
							.valueOf(goodsFile.getGoods_state())) + "");
					noticeService.updateObj(obj);

				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean goodsChoose(String id, String goodId) {
		List<PurchaseOrderGoodsItems> purchaseOrderGoods = purchaseOrderService
				.getListByObj(PurchaseOrderGoodsItems.class, "PURCHASEID='"
						+ id + "' and GOODSFILE_ID='" + goodId + "'");
		// System.out.println("######################"+purchaseOrderGoods.toString());
		if (purchaseOrderGoods != null && purchaseOrderGoods.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 团购订单导入
	 */
	@RequestMapping(value = "/saveWGPOrderImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveWGPOrderImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			String id, HttpServletRequest request) {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		WholesaleGroupPurchaseOrder wGPOrder = new WholesaleGroupPurchaseOrder();
		wGPOrder = (WholesaleGroupPurchaseOrder) noticeService.getObjById(id,
				"WholesaleGroupPurchaseOrder");
		//
		Map<String, Object> listResult = getWGPOrderAllByExcel(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					int re = saveWGPOrderByExcel(goodsFile, wGPOrder);
					if (re == 0) {
						result.put("msg", "fail");
						JSONObject jsonObject = JSONObject.fromObject(result);
						return jsonObject.toString();
					}
				}
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}

	public Map<String, Object> getWGPOrderAllByExcel(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell goods_code = row.getCell(0);
					goodsFile.setSerialNumber(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(1);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell price = row.getCell(3);
					goodsFile.setStore(getValue(price) == null ? "" : getValue(
							price).trim());
					HSSFCell nums = row.getCell(4);
					goodsFile.setGoods_state(getValue(nums) == null ? ""
							: getValue(nums).trim());
					HSSFCell rates = row.getCell(5);
					goodsFile.setGoods_type(getValue(rates) == null ? ""
							: getValue(rates).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int saveWGPOrderByExcel(GoodsFile goodsFile,
			WholesaleGroupPurchaseOrder wGPOrder) {
		try {
			String goodId = goodsFile.getGoods_code();
			GoodsFile file = new GoodsFile();
			file = (GoodsFile) noticeService.getObjById(goodId, "GoodsFile");
			WholesaleGroupPurchaseOrderItem wGPOrderItem = new WholesaleGroupPurchaseOrderItem();
			wGPOrderItem.setOrderId(wGPOrder.getId());
			wGPOrderItem.setGoodsFile(file);
			wGPOrderItem.setOrderPrice(goodsFile.getStore());
			wGPOrderItem.setNums(goodsFile.getGoods_state());
			wGPOrderItem.setRate(goodsFile.getGoods_type());
			noticeService.saveObj(wGPOrderItem);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 新增采购单导入excel
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "importIntoAdd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String importIntoAdd(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request) throws ParseException {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Map<String, Object> listResult = getItemsOnList(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								PurchaseOrderGoodsItems obj = (PurchaseOrderGoodsItems) noticeService
										.getObjById(idStr[j],
												"PurchaseOrderGoodsItems");
								if (obj != null) {
									if (obj.getSerialNumber().equals(
											goodsFile.getSerialNumber())) {
										if (Float.valueOf(obj.getActualNeed()) == 0) {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											} else {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											}
										} else {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNeed(goodsFile
														.getStore());
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											} else {
												obj.setActualNeed(Double
														.valueOf(goodsFile
																.getStore())
														+ "");
												obj.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
												noticeService.updateObj(obj);
											}
										}
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
										purchaseOrderGoodsItems.setId(UuidUtils
												.getUUID());
										purchaseOrderGoodsItems
												.setGoodsFile(file);
										purchaseOrderGoodsItems
												.setSerialNumber(file
														.getSerialNumber());
										purchaseOrderGoodsItems
												.setGoodsName(file
														.getGoods_name());
										purchaseOrderGoodsItems
												.setGoodsPrice(Double
														.valueOf(goodsFile
																.getRemark()));
										purchaseOrderGoodsItems
												.setOrderNum(Double.valueOf(goodsFile
														.getGoods_state()));
										purchaseOrderGoodsItems
												.setActualNeed(goodsFile
														.getStore());
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date date = new Date();
										purchaseOrderGoodsItems
												.setProduceDate(sdf.parse(sdf
														.format(date)));
										purchaseOrderGoodsItems
												.setSpecifications(file
														.getGoods_specifications());
										purchaseOrderGoodsItems.setUnit(file
												.getGoods_unit());
										purchaseOrderGoodsItems
												.setPurchaseMan(ctpUser);
										noticeService
												.saveObj(purchaseOrderGoodsItems);
									}
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									PurchaseOrderGoodsItems purchaseOrderGoodsItems = new PurchaseOrderGoodsItems();
									purchaseOrderGoodsItems.setId(UuidUtils
											.getUUID());
									purchaseOrderGoodsItems.setGoodsFile(file);
									purchaseOrderGoodsItems
											.setSerialNumber(file
													.getSerialNumber());
									purchaseOrderGoodsItems.setGoodsName(file
											.getGoods_name());
									purchaseOrderGoodsItems
											.setGoodsPrice(Double
													.valueOf(goodsFile
															.getRemark()));
									purchaseOrderGoodsItems
											.setOrderNum(Double
													.valueOf(goodsFile
															.getGoods_state()));
									purchaseOrderGoodsItems
											.setActualNeed(goodsFile.getStore());
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									Date date = new Date();
									// String dateStr = sdf.format(date);
									purchaseOrderGoodsItems.setProduceDate(sdf
											.parse(sdf.format(date)));
									purchaseOrderGoodsItems
											.setSpecifications(file
													.getGoods_specifications());
									purchaseOrderGoodsItems.setUnit(file
											.getGoods_unit());
									purchaseOrderGoodsItems
											.setPurchaseMan(ctpUser);
									noticeService
											.saveObj(purchaseOrderGoodsItems);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<PurchaseOrderGoodsItems> purchaseOrderGoodsItemsList = noticeService
						.getListByObj(PurchaseOrderGoodsItems.class,
								"PURCHASEID is null and ACTUALNEED='0' or ACTUALNEED is null ");
				if (purchaseOrderGoodsItemsList != null
						&& purchaseOrderGoodsItemsList.size() > 0) {
					for (int i = 0; i < purchaseOrderGoodsItemsList.size(); i++) {
						PurchaseOrderGoodsItems purchaseOrderGoodsItems = purchaseOrderGoodsItemsList
								.get(i);
						noticeService.deleteObjById(
								purchaseOrderGoodsItems.getId(),
								PurchaseOrderGoodsItems.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}

	public Map<String, Object> getItemsOnList(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();

			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell goods_code = row.getCell(0);
					goodsFile.setSerialNumber(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(1);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell actual_num = row.getCell(3);
					for (int j = 0; j < getValue(actual_num).length(); j++) {
						System.out.println(getValue(actual_num).charAt(j));
						if (Character.isDigit(getValue(actual_num).charAt(j))) {
							goodsFile
									.setGoods_state(getValue(actual_num) == null ? ""
											: getValue(actual_num).trim());
						} else {
							goodsFile.setGoods_state("0");
						}
					}
					HSSFCell store_num = row.getCell(4);
					goodsFile.setStore(getValue(store_num) == null ? ""
							: getValue(store_num).trim());
					HSSFCell purchase_price = row.getCell(5);
					goodsFile.setRemark(getValue(purchase_price) == null ? ""
							: getValue(purchase_price).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转仓导入
	 * 
	 * @param myfile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/switchChangeAdd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String switchChangeAdd(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request) {
		Map result = new HashMap<Object, Object>();
		String switchChangeId = request.getParameter("switchChangeId");
		List<SwitchGoodsItems> list = null;
		if(StringUtil.validate(switchChangeId)){
			list = noticeService.getListByObj(SwitchGoodsItems.class, " zcSwitchhouse_id = '"+switchChangeId+"'");
		}
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Map<String, Object> listResult = switchService.getAllByExcel(path,
				ctpUser);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<SwitchGoodsItems> listSupply = (List<SwitchGoodsItems>) listResult
						.get("listSupply");
				try {
					if(list == null || list.size() == 0){
						for (int i = 0; i < listSupply.size(); i++) {
							SwitchGoodsItems obj = new SwitchGoodsItems();
							obj = listSupply.get(i);
							switchService.saveObj(obj);
						}
					}else
					{
						for (int i = 0; i < listSupply.size(); i++) {
							SwitchGoodsItems obj = listSupply.get(i);
							boolean isExist = false;
							for(int j = 0; j < list.size(); j++){
								SwitchGoodsItems oldObj = list.get(j);
								if(obj.getGoodsFile().getId().equals(oldObj.getGoodsFile().getId())){
									isExist = true;
									oldObj.setChangeNumber(obj.getChangeNumber());
									oldObj.setChangeAmount(obj.getChangeAmount());
									oldObj.setRemark(obj.getRemark());
									switchService.updateObj(oldObj);
								}
							}
							if(!isExist){
								switchService.saveObj(obj);
							}
						}
					}
				} catch (Exception e) {
					result.put("msg", "fail");
					JSONObject jsonObject = JSONObject.fromObject(result);
					return jsonObject.toString();
				}
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}
	
	public Map<String, Object> getItemsOnReceiveList(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();
			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell goods_code = row.getCell(0);
					goodsFile.setSerialNumber(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(1);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell goods_unit = row.getCell(3);
					goodsFile.setGoods_unit(getValue(goods_unit) == null ? ""
							: getValue(goods_unit).trim());
					HSSFCell actual_num = row.getCell(4);
					goodsFile.setStore(getValue(actual_num) == null ? ""
							: getValue(actual_num).trim());
					HSSFCell purchase_price = row.getCell(5);
					goodsFile.setRemark(getValue(purchase_price) == null ? ""
							: getValue(purchase_price).trim());
					HSSFCell goods_money = row.getCell(6);
					goodsFile.setGoods_origin(getValue(goods_money) == null ? ""
							: getValue(goods_money).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 新增采购收货单导入excel
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "importIntoReceive", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String importIntoReceive(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request) throws ParseException {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Map<String, Object> listResult = getItemsOnReceiveList(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								PurchaseReceiveItem obj = (PurchaseReceiveItem) noticeService
										.getObjById(idStr[j],
												"PurchaseReceiveItem");
								if (obj != null) {
									if (obj.getGoodsFile()
											.getSerialNumber()
											.equals(goodsFile.getSerialNumber())) {
										if (obj.getActualNum() == 0) {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNum(Double
														.parseDouble(goodsFile
																.getStore()));
												obj.setGoodsPrice(goodsFile
														.getRemark());
												noticeService.updateObj(obj);
											} else {
												obj.setActualNum(Double
														.parseDouble(goodsFile
																.getStore()));
												obj.setGoodsPrice(goodsFile
														.getRemark());
												noticeService.updateObj(obj);
											}
										} else {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setActualNum(Double
														.parseDouble(goodsFile
																.getStore()));
												obj.setGoodsPrice(goodsFile
														.getRemark());
												noticeService.updateObj(obj);
											} else {
												obj.setActualNum(Double
														.valueOf(goodsFile
																.getStore()));
												obj.setGoodsPrice(goodsFile
														.getRemark());
												noticeService.updateObj(obj);
											}
										}
										obj.setActualNum(Double.parseDouble(goodsFile.getStore()));
										noticeService.updateObj(obj);
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
										purchaseReceiveItem.setId(UuidUtils
												.getUUID());
										purchaseReceiveItem.setGoodsFile(file);

										purchaseReceiveItem
												.setGoodsPrice(goodsFile
														.getRemark());

										purchaseReceiveItem.setActualNum(Double
												.parseDouble(goodsFile
														.getStore()));
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date date = new Date();
										purchaseReceiveItem
												.setProductDate(date);
										String num = purchaseReceiveItem
												.getGoodsPrice();
										double newNum = Double.parseDouble(num);
										double goodsMoney = newNum
												* purchaseReceiveItem
														.getActualNum();
										purchaseReceiveItem
												.setGoodsMoney(goodsMoney);
										purchaseReceiveItem.setCtpUser(ctpUser);

										noticeService
												.saveObj(purchaseReceiveItem);
									}
									
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
									purchaseReceiveItem.setId(UuidUtils
											.getUUID());
									purchaseReceiveItem.setGoodsFile(file);
									purchaseReceiveItem.setGoodsPrice(goodsFile
											.getRemark());

									purchaseReceiveItem.setActualNum(Double
											.parseDouble(goodsFile.getStore()));
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									Date date = new Date();
									purchaseReceiveItem.setProductDate(date);
									String num = purchaseReceiveItem
											.getGoodsPrice();
									double newNum = Double.parseDouble(num);
									double goodsMoney = newNum
											* purchaseReceiveItem
													.getActualNum();
									purchaseReceiveItem
											.setGoodsMoney(goodsMoney);
									purchaseReceiveItem.setCtpUser(ctpUser);
									noticeService.saveObj(purchaseReceiveItem);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<PurchaseReceiveItem> purchaseReceiveItemList = noticeService
						.getListByObj(PurchaseReceiveItem.class,
								"purchase_receive_id is null and ACTUAL_NUM='0' or ACTUAL_NUM is null ");
				if (purchaseReceiveItemList != null
						&& purchaseReceiveItemList.size() > 0) {
					for (int i = 0; i < purchaseReceiveItemList.size(); i++) {
						PurchaseReceiveItem purchaseReceiveItem = purchaseReceiveItemList
								.get(i);
						noticeService.deleteObjById(
								purchaseReceiveItem.getId(),
								PurchaseOrderGoodsItems.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}
	
	/**
	 * 编辑采购收货订单导入
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/savePRItemImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String savePRItemImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			String purchaseReceiveId, HttpServletRequest request)
			throws ParseException {
		PurchaseReceive purchaseReceive = (PurchaseReceive) noticeService.getObjById(
				purchaseReceiveId, "PurchaseReceive");
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Map<String, Object> listResult = getItemsOnReceiveList(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								PurchaseReceiveItem obj = (PurchaseReceiveItem) noticeService
										.getObjById(idStr[j],
												"PurchaseReceiveItem");
								if (obj != null) {
									if (obj.getGoodsFile().getSerialNumber().equals(
											goodsFile.getSerialNumber())) {
										if (obj.getActualNum() == 0) {
											
												obj.setActualNum(Double.parseDouble(goodsFile
														.getGoods_state()));
												obj.setGoodsPrice(goodsFile
																.getRemark());
												double price =Double.parseDouble(goodsFile.getRemark());
												double num = Double.parseDouble(goodsFile.getGoods_state());
												double goodsMoney = price * num ;
												obj.setGoodsMoney(goodsMoney);
												noticeService.updateObj(obj);
											
										} else {
											
												obj.setActualNum(Double
														.valueOf(goodsFile
																.getGoods_state())
														);
												obj.setGoodsPrice(goodsFile
																.getRemark());
												double price =Double.parseDouble(goodsFile.getRemark());
												double num = Double.parseDouble(goodsFile.getGoods_state());
												double goodsMoney = price * num ;
												obj.setGoodsMoney(goodsMoney);
												noticeService.updateObj(obj);
											
										}
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
										purchaseReceiveItem.setId(UuidUtils
												.getUUID());
										purchaseReceiveItem
												.setPurchaseReceiveId(purchaseReceive.getId());;
										purchaseReceiveItem
												.setGoodsFile(file);
										purchaseReceiveItem
												.setActualNum(Double.parseDouble(goodsFile.getStore()));
										
										purchaseReceiveItem
												.setGoodsPrice(goodsFile
																.getRemark());
										String num = purchaseReceiveItem
												.getGoodsPrice();
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy-MM-dd");
										Date date = new Date();
										purchaseReceiveItem.setProductDate(date);
										double newNum = Double.parseDouble(num);
										double goodsMoney = newNum
												* purchaseReceiveItem
														.getActualNum();
										purchaseReceiveItem
												.setGoodsMoney(goodsMoney);
										
										//purchaseReceiveItem
											//.setCtpUser(ctpUser);
										
										noticeService
												.saveObj(purchaseReceiveItem);
									}
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									PurchaseReceiveItem purchaseReceiveItem = new PurchaseReceiveItem();
									purchaseReceiveItem.setId(UuidUtils
											.getUUID());
									purchaseReceiveItem
											.setPurchaseReceiveId(purchaseReceive.getId());;
									purchaseReceiveItem
											.setGoodsFile(file);
									purchaseReceiveItem
											.setActualNum(Double.parseDouble(goodsFile.getStore()));
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									Date date = new Date();
									purchaseReceiveItem.setProductDate(date);
									
									purchaseReceiveItem
											.setGoodsPrice(goodsFile
															.getRemark());
									//purchaseReceiveItem
										//.setCtpUser(ctpUser);
									String num = purchaseReceiveItem
											.getGoodsPrice();
									double newNum = Double.parseDouble(num);
									double goodsMoney = newNum
											* purchaseReceiveItem
													.getActualNum();
									purchaseReceiveItem
											.setGoodsMoney(goodsMoney);
									
									noticeService
											.saveObj(purchaseReceiveItem);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<PurchaseReceiveItem> purchaseReceiveItemList = noticeService
						.getListByObj(
								PurchaseReceiveItem.class,
								"purchase_receive_id='"
										+ purchaseReceive.getId()
										+ "'and ACTUAL_NUM='0' or ACTUAL_NUM is null ");
				if (purchaseReceiveItemList != null
						&& purchaseReceiveItemList.size() > 0) {
					for (int i = 0; i < purchaseReceiveItemList.size(); i++) {
						PurchaseReceiveItem purchaseReceiveItem = purchaseReceiveItemList
								.get(i);
						noticeService.deleteObjById(
								purchaseReceiveItem.getId(),
								PurchaseReceiveItem.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}
	
	/**
	 * 盘点差异单导入
	 * 
	 * @param myfile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveCheckDifferenceImport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveCheckDifferenceImport(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			String checkNumber,String id , HttpServletRequest request) {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		
		String path = saveExcelFile(request, myfile);
		ZcCheckDifference zcCheckDifference =(ZcCheckDifference) noticeService
				.getObjById(id, ZcCheckDifference.class.getName());
		String wareHouseId= zcCheckDifference.getZcWarehouse().getId();
		ZcWarehouse warehouse = new ZcWarehouse();
		warehouse = (ZcWarehouse) noticeService.getObjById(wareHouseId,
				"ZcWarehouse");
		Map<String, Object> listResult = getAllChangeByExcel(path);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					int re = saveByDifferentExcel(goodsFile, warehouse);
					if (re == 0) {
						result.put("msg", "fail");
						JSONObject jsonObject = JSONObject.fromObject(result);
						return jsonObject.toString();
					}
				}
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}

	}

	public int saveByDifferentExcel(GoodsFile goodsFile, ZcWarehouse warehouse) {
		try {
			String goodId = goodsFile.getGoods_code();
			List<CheckGoodsItems> goodsItems = noticeService.getListByObj(
					CheckGoodsItems.class, "goodsfile_id='" + goodId
							+ "' and warehouse_id='" + warehouse.getId() + "'");
			if (goodsItems != null && goodsItems.size() > 0) {
				goodsItems.get(0).setActualCheckNumber(
						goodsFile.getGoods_state());
				goodsItems.get(0).setDifferenceReason(goodsFile.getRemark());
				noticeService.updateObj(goodsItems.get(0));
			}else{
				CheckGoodsItems checkGoodsItems = new CheckGoodsItems();
				checkGoodsItems.setId(UuidUtils
						.getUUID());
				checkGoodsItems.setActualCheckNumber(goodsFile.getGoods_state());
				checkGoodsItems.setDifferenceReason(goodsFile.getRemark());
				List<GoodsFile> goodsFileItems = noticeService.getListByObj(
						GoodsFile.class, "goods_code='" + goodId
								 + "'");
				GoodsFile newGoodsFile = new GoodsFile();
				if (goodsFileItems!=null && goodsFileItems.size()>0) {
					newGoodsFile = goodsFileItems.get(0);
				}
				checkGoodsItems.setGoodsFile(newGoodsFile);
				checkGoodsItems.setStore(goodsFile.getStore());
				checkGoodsItems.setWarehouse(warehouse);
				checkGoodsItems.setDifferenceReason(goodsFile.getRemark());
				noticeService.saveObj(checkGoodsItems);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * 新增库存调整导入excel
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "importIntoStoreChange", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String importIntoStoreChange(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request,String storeChangeNumber,String branchId) throws ParseException {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String newBranchId = "";
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Branch branch = (Branch)noticeService.getObjById(branchId, Branch.class.getName());
		Map<String, Object> listResult = getStoreChangeOnList(path,newBranchId);
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								ChangeGoodsItems obj = (ChangeGoodsItems) noticeService
										.getObjById(idStr[j],
												"ChangeGoodsItems");
								if (obj != null) {
									if (obj.getGoodsFile()
											.getSerialNumber()
											.equals(goodsFile.getSerialNumber())) {
										if (obj.getChangeNumber()==null||obj.getChangeNumber().equals("")) {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setChangeNumber(goodsFile
																.getStore());
												noticeService.updateObj(obj);
											} else {
												obj.setChangeNumber(goodsFile
																.getStore());
												noticeService.updateObj(obj);
											}
										} else {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setChangeNumber(goodsFile
																.getStore());
												noticeService.updateObj(obj);
											} else {
												obj.setChangeNumber(goodsFile
																.getStore());
												noticeService.updateObj(obj);
											}
										}
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										ChangeGoodsItems changeGoodsItems = new ChangeGoodsItems();
										changeGoodsItems.setId(UuidUtils
												.getUUID());
										changeGoodsItems.setGoodsFile(file);

										changeGoodsItems.setChangeNumber(goodsFile.getStore());
										
										changeGoodsItems.setCreateUser(ctpUser);
										
										changeGoodsItems.setRemark(goodsFile.getRemark());
										List<ZcStorehouse> list = null;
										if (branch==null) {
											//list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id="+file.getId()+" and branch_id=null");
										}else{
										    list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id="+file.getId()+" and branch_id="+branch.getId());
										}
										
										if (list!=null&&list.size()>0) {
											ZcStorehouse zcStorehouse = list.get(0);
											changeGoodsItems.setStore(zcStorehouse.getStore());
										}
										
										noticeService
												.saveObj(changeGoodsItems);
									}
									
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									ChangeGoodsItems changeGoodsItems = new ChangeGoodsItems();
									changeGoodsItems.setId(UuidUtils
											.getUUID());
									changeGoodsItems.setGoodsFile(file);

									changeGoodsItems.setChangeNumber(goodsFile.getStore());
									
									changeGoodsItems.setCreateUser(ctpUser);
									
									changeGoodsItems.setRemark(goodsFile.getRemark());
									List<ZcStorehouse> list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id="+file.getId()+" and branch_id="+branch.getId());
									if (list!=null&&list.size()>0) {
										ZcStorehouse zcStorehouse = list.get(0);
										changeGoodsItems.setStore(zcStorehouse.getStore());
									}
									
									noticeService
											.saveObj(changeGoodsItems);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<ChangeGoodsItems> changeGoodsItemsItemList = noticeService
						.getListByObj(ChangeGoodsItems.class,
								"storechange_id is null and changenumber='0' or changenumber is null ");
				if (changeGoodsItemsItemList != null
						&& changeGoodsItemsItemList.size() > 0) {
					for (int i = 0; i < changeGoodsItemsItemList.size(); i++) {
						ChangeGoodsItems changeGoodsItemsItem = changeGoodsItemsItemList
								.get(i);
						noticeService.deleteObjById(
								changeGoodsItemsItem.getId(),
								ChangeGoodsItems.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}
	
	public Map<String, Object> getStoreChangeOnList(String path,String newBranchId) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();
			//HSSFRow row3= sheet.getRow(3);
			//HSSFCell branchName = row3.getCell(0);
			//List<ZcStorehouse> zcStorehouseList = noticeService.getListByObj(ZcStorehouse.class, "branch_name="+branchName);
			//ZcStorehouse  zcStorehouse = zcStorehouseList.get(0);
			//newBranchId = zcStorehouse.getBranch().getId();
			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell serialnumber = row.getCell(0);
					HSSFCell goods_code = row.getCell(1);
					goodsFile.setSerialNumber(getValue(serialnumber) == null ? ""
							: getValue(serialnumber).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(2);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell goods_specifications = row.getCell(3);
					goodsFile.setGoods_specifications(getValue(goods_specifications) == null ? ""
							: getValue(goods_specifications).trim());
					HSSFCell goods_unit = row.getCell(4);
					goodsFile.setGoods_unit(getValue(goods_unit) == null ? ""
							: getValue(goods_unit).trim());
					HSSFCell goods_price = row.getCell(5);
					
					if (goods_price==null||goods_price.equals("")) {
						Float.parseFloat(goods_price+"");
						float newGoodsPrice = 0;
						goodsFile.setGoods_price(newGoodsPrice);
					}
					goodsFile.setGoods_price(Float.parseFloat(goods_price+""));
					
					HSSFCell change_num = row.getCell(6);
					goodsFile.setStore(getValue(change_num) == null ? ""
							: getValue(change_num).trim());
					HSSFCell remark = row.getCell(7);
					goodsFile.setRemark(getValue(remark) == null ? ""
							: getValue(remark).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编辑库存调整导入excel
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "importIntoStoreChangeEdit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String importIntoStoreChangeEdit(
			@RequestParam("sourceBrocastBatch") MultipartFile myfile,
			HttpServletRequest request,String storeChangeNumber,String branchId) throws ParseException {
		Map result = new HashMap<Object, Object>();
		// 获取文件路径
		String newBranchId = "";
		String path = saveExcelFile(request, myfile);
		String ids = request.getParameter("ids");
		String[] idStr = ids.split(",");
		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		Branch branch = (Branch)noticeService.getObjById(branchId, Branch.class.getName());
		Map<String, Object> listResult = getStoreChangeEditorOnList(path,newBranchId,branch);
		List<ZcStoreChange> listStroeChange = noticeService.getListByObj(ZcStoreChange.class, "storechange_number='"+storeChangeNumber+"'");
		 ZcStoreChange  zcStoreChangeObj = null;
		if (listStroeChange!=null && listStroeChange.size()>0) {
			  zcStoreChangeObj = listStroeChange.get(0);
		}
		if (listResult != null) {
			// 判断excel数据没有错误
			if ("".equals(listResult.get("returnAnwer"))) {
				List<GoodsFile> listSupply = (List<GoodsFile>) listResult
						.get("listSupply");
				for (int i = 0; i < listSupply.size(); i++) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile = listSupply.get(i);
					GoodsFile file = null;
					int re = 0;
					// start
					if (goodsFile != null) {
						List<GoodsFile> goodsFilesList = noticeService
								.getListByObj(GoodsFile.class, "serialNumber='"
										+ goodsFile.getSerialNumber() + "'");
						if (goodsFilesList != null && goodsFilesList.size() > 0) {
							file = goodsFilesList.get(0);
							for (int j = 0; j < idStr.length; j++) {
								ChangeGoodsItems obj = (ChangeGoodsItems) noticeService
										.getObjById(idStr[j],
												"ChangeGoodsItems");
								if (obj != null) {
									if (obj.getGoodsFile()
											.getSerialNumber()
											.equals(goodsFile.getSerialNumber())) {
										if (obj.getChangeNumber()==null||obj.getChangeNumber().equals("")) {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setChangeNumber(goodsFile
																.getStore());
												obj.setStore(goodsFile.getGoods_origin());
												noticeService.updateObj(obj);
											} else {
												obj.setChangeNumber(goodsFile
																.getStore());
												obj.setStore(goodsFile.getGoods_origin());
												noticeService.updateObj(obj);
											}
										} else {
											if (goodsFile.getStore() == null
													|| "0.0".equals(goodsFile
															.getStore())
													|| "0.00".equals(goodsFile
															.getStore())
													|| "0".equals(goodsFile
															.getStore())) {
												obj.setChangeNumber(goodsFile
																.getStore());
												obj.setStore(goodsFile.getGoods_origin());
												noticeService.updateObj(obj);
											} else {
												obj.setChangeNumber(goodsFile
																.getStore());
												obj.setStore(goodsFile.getGoods_origin());
												noticeService.updateObj(obj);
											}
										}
										re = 2;
										break;
									} else {
										continue;
									}
								} else {
									if (goodsFile.getStore() == null
											|| "0.0".equals(goodsFile
													.getStore())
											|| "0.00".equals(goodsFile
													.getStore())
											|| "0".equals(goodsFile.getStore())) {
									} else {
										ChangeGoodsItems changeGoodsItems = new ChangeGoodsItems();
										changeGoodsItems.setId(UuidUtils
												.getUUID());
										changeGoodsItems.setGoodsFile(file);
										double money = goodsFile.getGoods_price()*Double.parseDouble(goodsFile.getStore()) ;
										changeGoodsItems.setChangeAmount(money+"");
										changeGoodsItems.setChangeNumber(goodsFile.getStore());
										changeGoodsItems.setStoreChange(zcStoreChangeObj);
										changeGoodsItems.setCreateUser(ctpUser);
										
										changeGoodsItems.setRemark(goodsFile.getRemark());
										List<ZcStorehouse> list = null;
										if (branch==null) {
											list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id='"+file.getId()+"' and branch_id= '"+branch.getId()+"'");
										}else{
										    list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id='"+file.getId()+"' and branch_id= '"+branch.getId()+"'");
										}
										
										if (list!=null&&list.size()>0) {
											ZcStorehouse zcStorehouse = list.get(0);
											changeGoodsItems.setStore(zcStorehouse.getStore());
										}else{
											changeGoodsItems.setStore(goodsFile.getGoods_origin());
										}
										
										noticeService
												.saveObj(changeGoodsItems);
									}
									
									re = 2;
									break;
								}
							}
							if (re == 0) {
								if (goodsFile.getStore() == null
										|| "0.0".equals(goodsFile.getStore())
										|| "0.00".equals(goodsFile.getStore())
										|| "0".equals(goodsFile.getStore())) {
								} else {
									ChangeGoodsItems changeGoodsItems = new ChangeGoodsItems();
									changeGoodsItems.setId(UuidUtils
											.getUUID());
									changeGoodsItems.setGoodsFile(file);

									changeGoodsItems.setChangeNumber(goodsFile.getStore());
									double money = goodsFile.getGoods_price()*Double.parseDouble(goodsFile.getStore()) ;
									changeGoodsItems.setChangeAmount(money+"");
									changeGoodsItems.setCreateUser(ctpUser);
									changeGoodsItems.setStoreChange(zcStoreChangeObj);
									changeGoodsItems.setRemark(goodsFile.getRemark());
									List<ZcStorehouse> list = noticeService.getListByObj(ZcStorehouse.class, "goodsfile_id="+file.getId()+" and branch_id="+branch.getId());
									if (list!=null&&list.size()>0) {
										ZcStorehouse zcStorehouse = list.get(0);
										changeGoodsItems.setStore(zcStorehouse.getStore());
									}else{
										changeGoodsItems.setStore(goodsFile.getGoods_origin());
									}
									
									noticeService
											.saveObj(changeGoodsItems);
								}
							}
						} else {
							// 未根据货号查到次商品档案 返回错误信息
							result.put("msg", "fail");
						}
					} else {
						continue;
					}
				}
				List<ChangeGoodsItems> changeGoodsItemsItemList = noticeService
						.getListByObj(ChangeGoodsItems.class,
								"storechange_id is null and changenumber='0' or changenumber is null ");
				if (changeGoodsItemsItemList != null
						&& changeGoodsItemsItemList.size() > 0) {
					for (int i = 0; i < changeGoodsItemsItemList.size(); i++) {
						ChangeGoodsItems changeGoodsItemsItem = changeGoodsItemsItemList
								.get(i);
						noticeService.deleteObjById(
								changeGoodsItemsItem.getId(),
								ChangeGoodsItems.class.getName());
					}
				}
				// end
				result.put("msg", "success");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			} else {
				result.put("resultAnwser", listResult.get("returnAnwer"));
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
		} else {
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
	}
	
	public Map<String, Object> getStoreChangeEditorOnList(String path,String newBranchId,Branch obj) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<GoodsFile> list = new ArrayList<GoodsFile>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();
//			HSSFRow row3= sheet.getRow(2);
//			HSSFCell branchName = row3.getCell(0);
//			String branchNameWow = branchName.toString();
//			String[] newBranchName = branchNameWow.split("：");
//			String branchNameReal = newBranchName[1]+"";
			//List<Branch> branchInfoList = noticeService.getListByObj(Branch.class, "branch_name='"+branchNameReal+"'");
			//Branch  branchInfo = branchInfoList.get(0);
			//System.out.println(newBranchId);
			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					HSSFCell serialnumber = row.getCell(0);
					HSSFCell goods_code = row.getCell(1);
					goodsFile.setSerialNumber(getValue(serialnumber) == null ? ""
							: getValue(serialnumber).trim());
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					HSSFCell goods_name = row.getCell(2);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					HSSFCell goods_specifications = row.getCell(3);
					goodsFile.setGoods_specifications(getValue(goods_specifications) == null ? ""
							: getValue(goods_specifications).trim());
					HSSFCell goods_unit = row.getCell(4);
					goodsFile.setGoods_unit(getValue(goods_unit) == null ? ""
							: getValue(goods_unit).trim());
					HSSFCell goods_price = row.getCell(5);
					
					if (goods_price==null||goods_price.equals("")) {
						Float.parseFloat(goods_price+"");
						float newGoodsPrice = 0;
						goodsFile.setGoods_price(newGoodsPrice);
					}
					goodsFile.setGoods_price(Float.parseFloat(goods_price+""));
					
					HSSFCell change_num = row.getCell(6);
					goodsFile.setStore(getValue(change_num) == null ? ""
							: getValue(change_num).trim());
					HSSFCell store = row.getCell(7);
					goodsFile.setGoods_origin(getValue(store) == null ? ""
							: getValue(store).trim());
					HSSFCell remark = row.getCell(8);
					goodsFile.setRemark(getValue(remark) == null ? ""
							: getValue(remark).trim());
					list.add(goodsFile);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
