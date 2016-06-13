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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.CheckGoodsItems;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.service.system.NoticeService;

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
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
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

}
