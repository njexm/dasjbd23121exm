package com.proem.exm.controller.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proem.exm.controller.BaseController;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.utils.GoodFileCompare;
import com.proem.exm.entity.utils.PurchaseGoodsCompare;
import com.proem.exm.entity.utils.WGPOrderCompare;
import com.proem.exm.entity.warehouse.CheckGoodsItems;
import com.proem.exm.entity.warehouse.ZcStorehouse;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;
import com.proem.exm.service.system.NoticeService;
import com.proem.exm.utils.ConstantExportExcelName;

/**
 * 
 * @author psl
 * 
 * @com proem
 */
@Controller
@RequestMapping("/exportExcel")
public class ExportExcelController extends BaseController {
	private static final String OBLIQUE_LINE = "/";
	private static final String WEBPOSITION = "webapps";
	private static final String WTPPWEBAPPS = "wtpwebapps";
	public static final String SBPATH = "ExcelExport/";
	String storeHouserId = "";
	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/CheckItemExport", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@SuppressWarnings("deprecation")
	public String outExcel(String pandianhao, String storeHouse, String id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map result = new HashMap<Object, Object>();
		storeHouse = new String(storeHouse.getBytes("iso8859-1"), "utf-8");
		if (ConstantExportExcelName.STOREHOUSE2.equals(storeHouse)) {
			storeHouserId = ConstantExportExcelName.STOREHOUSE2_ID;
		} else {
			storeHouserId = ConstantExportExcelName.STOREHOUSE1_ID;
		}
		HSSFWorkbook wb = new HSSFWorkbook(); // --->创建了一个excel文件
		HSSFSheet sheet = wb.createSheet(ConstantExportExcelName.PANDIANHAO); // --->创建了一个工作簿
		HSSFDataFormat format = wb.createDataFormat(); // --->单元格内容格式
		sheet.setColumnWidth((short) 3, 20 * 256); // ---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
		sheet.setColumnWidth((short) 4, 20 * 256); // --->第一个参数是指哪个单元格，第二个参数是单元格的宽度
		sheet.setDefaultRowHeight((short) 300); // ---->有得时候你想设置统一单元格的高度，就用这个方法

		// 样式1
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style1 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style2 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style4 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style5 = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		// 设置标题字体格式
		Font font = wb.createFont();
		Font font1 = wb.createFont();
		// 设置字体样式
		font.setFontHeightInPoints((short) 20); // --->设置字体大小
		font.setFontName("Courier New"); // ---》设置字体，是什么类型例如：宋体
		font1.setItalic(true); // --->设置是否是加粗
		style1.setFont(font1); // --->将字体格式加入到style1中
		// style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
		// style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色
		style1.setWrapText(true); // 设置是否能够换行，能够换行为true
		style1.setBorderBottom((short) 1); // 设置下划线，参数是黑线的宽度
		style1.setBorderLeft((short) 1); // 设置左边框
		style1.setBorderRight((short) 1); // 设置有边框
		style1.setBorderTop((short) 1); // 设置下边框
		style4.setDataFormat(format.getFormat("￥#,##0")); // --->设置为单元格内容为货币格式

		style5.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%")); // --->设置单元格内容为百分数格式

		// 表格第一行
		HSSFRow row1 = sheet.createRow(0); // --->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 5));
		row1.setHeightInPoints(35);
		HSSFCell cell1 = row1.createCell((short) 0); // --->创建一个单元格

		cell1.setCellStyle(style);
		cell1.setCellValue(ConstantExportExcelName.PANDIANHAO);

		// 表格第二行
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 5));
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeightInPoints(25);
		HSSFCell cell2 = row2.createCell((short) 0);
		cell2.setCellValue("单号：" + pandianhao);
		cell2.setCellStyle(style2);

		// 表格第三行
		sheet.addMergedRegion(new Region(2, (short) 0, 2, (short) 5));
		HSSFRow row3 = sheet.createRow(2);
		row3.setHeightInPoints(25);
		HSSFCell cell3 = row3.createCell((short) 0);
		cell3.setCellValue("仓库：" + storeHouse);
		cell3.setCellStyle(style2);

		// 表格第四行
		sheet.addMergedRegion(new Region(3, (short) 0, 3, (short) 0));
		HSSFRow row4 = sheet.createRow(3);
		row4.setHeightInPoints((short) 30);

		HSSFCell cell4_1 = row4.createCell((short) 0);
		cell4_1.setCellStyle(style1);
		cell4_1.setCellValue("货号");

		HSSFCell cell4_2 = row4.createCell((short) 1);
		cell4_2.setCellStyle(style1);
		cell4_2.setCellValue("品名");

		HSSFCell cell4_3 = row4.createCell((short) 2);
		cell4_3.setCellStyle(style1);
		cell4_3.setCellValue("规格");

		HSSFCell cell4_4 = row4.createCell((short) 3);
		cell4_4.setCellStyle(style1);
		cell4_4.setCellValue("单位");

		HSSFCell cell4_5 = row4.createCell((short) 4);
		cell4_5.setCellStyle(style1);
		cell4_5.setCellValue("系统库存");

		HSSFCell cell4_6 = row4.createCell((short) 5);
		cell4_6.setCellStyle(style1);
		cell4_6.setCellValue("实际盘点数量");

		// //第五行
		// sheet.addMergedRegion(new Region(4, (short)0, 4, (short)2));
		// HSSFRow row5 = sheet.createRow(4);
		// HSSFCell cell5 = row5.createCell((short)0);
		// HSSFCell cell5_1 = row5.createCell((short)1);
		// cell5_1.setCellStyle(style2);
		// HSSFCell cell5_2 = row5.createCell((short)2);
		// cell5_2.setCellStyle(style2);
		// cell5.setCellValue("投资资产合计");
		// cell5.setCellStyle(style2);
		//
		// //第六行
		// sheet.addMergedRegion(new Region(5, (short)0, 5, (short)2));
		// HSSFRow row6 = sheet.createRow(5);
		// HSSFCell cell6 = row6.createCell((short)0);
		// HSSFCell cell6_1 = row6.createCell((short)1);
		// cell6_1.setCellStyle(style2);
		// HSSFCell cell6_2 = row6.createCell((short)2);
		// cell6_2.setCellStyle(style2);
		// cell6.setCellValue("2、股票");
		// cell6.setCellStyle(style2);
		//
		// //第七行
		// sheet.addMergedRegion(new Region(6, (short)0, 6, (short)2));
		// HSSFRow row7 = sheet.createRow(6);
		// HSSFCell cell7 = row7.createCell((short)0);
		// HSSFCell cell7_1 = row7.createCell((short)1);
		// cell7_1.setCellStyle(style2);
		// HSSFCell cell7_2 = row7.createCell((short)2);
		// cell7_2.setCellStyle(style2);
		// cell7.setCellValue("2.1、境内A股");
		// cell7.setCellStyle(style2);
		//
		// //第八行
		// sheet.addMergedRegion(new Region(7, (short)0, 7, (short)2));
		// HSSFRow row8 = sheet.createRow(7);
		// HSSFCell cell8 = row8.createCell((short)0);
		// HSSFCell cell8_1 = row8.createCell((short)1);
		// cell8_1.setCellStyle(style2);
		// HSSFCell cell8_2 = row8.createCell((short)2);
		// cell8_2.setCellStyle(style2);
		// cell8.setCellValue("非限售股");
		// cell8.setCellStyle(style2);
		//
		List<CheckGoodsItems> checkGoodsItemList = noticeService.getListByObj(
				CheckGoodsItems.class, "warehouse_id='" + id + "' ");
		if (checkGoodsItemList != null && checkGoodsItemList.size() > 0) {
			GoodFileCompare compare = new GoodFileCompare();
			Collections.sort(checkGoodsItemList, compare);
			for (int i = 0; i < checkGoodsItemList.size(); i++) {
				HSSFRow rowN = sheet.createRow(4 + i);
				CheckGoodsItems goodsItems = checkGoodsItemList.get(i);
				HSSFCell cellN1 = rowN.createCell((short) 0);
				HSSFCell cellN2 = rowN.createCell((short) 1);
				HSSFCell cellN3 = rowN.createCell((short) 2);
				HSSFCell cellN4 = rowN.createCell((short) 3);
				HSSFCell cellN5 = rowN.createCell((short) 4);
				HSSFCell cellN6 = rowN.createCell((short) 5);
				cellN1.setCellStyle(style);
				cellN2.setCellStyle(style);
				cellN3.setCellStyle(style);
				cellN4.setCellStyle(style);
				cellN5.setCellStyle(style);
				cellN6.setCellStyle(style);
				cellN1.setCellValue(goodsItems.getGoodsFile().getSerialNumber());
				cellN2.setCellValue(goodsItems.getGoodsFile().getGoods_name());
				cellN3.setCellValue(goodsItems.getGoodsFile()
						.getGoods_specifications());
				cellN4.setCellValue(goodsItems.getGoodsFile().getGoods_unit());
				String goodId = goodsItems.getGoodsFile().getId();
				List<ZcStorehouse> zcStorehouse = noticeService.getListByObj(
						ZcStorehouse.class, "branch_id='" + storeHouserId
								+ "' and goodsfile_id='" + goodId + "'");
				if (zcStorehouse != null && zcStorehouse.size() > 0) {
					cellN5.setCellValue(zcStorehouse.get(0).getStore());
				} else {
					cellN5.setCellValue("0.00");
				}
				cellN6.setCellValue("null".equals(goodsItems
						.getActualCheckNumber()) ? "0.00" : goodsItems
						.getActualCheckNumber());
			}
		}
		String filePath = saveExcelFile(request, wb, pandianhao);
		FileOutputStream fileOut = null;
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
		result.put("msg", "success");
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject.toString();
	}

	/**
	 * 保存文件至服务器
	 */
	private String saveExcelFile(HttpServletRequest request, HSSFWorkbook wb,
			String fileName) {
		String path = "";
		// 文件保存路径
		try {
			String tomcatPath = request.getSession().getServletContext()
					.getRealPath("/");
			tomcatPath = tomcatPath.replace("\\", "/");
			StringBuffer destFName = new StringBuffer();
			destFName.append(getRealDir(tomcatPath).replace("web/", "/"))
					.append(SBPATH);
			System.out.println("得到的地址是：" + destFName);
			String filePath = destFName.toString().replace("\\", "/");

			path = filePath + fileName + ".xls";
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
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
	 * 采购订单导出
	 * 
	 * @param purchaseId
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/outPurchaseOrderExcel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@SuppressWarnings("deprecation")
	public String outPurchaseOrderExcel(String purchaseId, String id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// AjaxResult ajaxResult = null;
		Map result = new HashMap<Object, Object>();
		// providerId = new String(providerId.getBytes("iso8859-1"), "utf-8");
		HSSFWorkbook wb = new HSSFWorkbook(); // --->创建了一个excel文件
		HSSFSheet sheet = wb.createSheet(ConstantExportExcelName.PUCHASEORDER); // --->创建了一个工作簿
		HSSFDataFormat format = wb.createDataFormat(); // --->单元格内容格式
		sheet.setColumnWidth((short) 0, 20 * 256); // ---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
		sheet.setColumnWidth((short) 1, 20 * 256); // --->第一个参数是指哪个单元格，第二个参数是单元格的宽度
		sheet.setColumnWidth((short) 3, 20 * 256);
		sheet.setColumnWidth((short) 4, 20 * 256);
		sheet.setDefaultRowHeight((short) 300); // ---->有得时候你想设置统一单元格的高度，就用这个方法

		// 样式1
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style1 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style4 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style5 = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		// 设置标题字体格式
		Font font = wb.createFont();
		Font font1 = wb.createFont();
		// 设置字体样式
		font.setFontHeightInPoints((short) 20); // --->设置字体大小
		font.setFontName("Courier New"); // ---》设置字体，是什么类型例如：宋体
		font1.setItalic(true); // --->设置是否是加粗
		style1.setFont(font1); // --->将字体格式加入到style1中
		// style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
		// style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色
		style1.setWrapText(true); // 设置是否能够换行，能够换行为true
		style1.setBorderBottom((short) 1); // 设置下划线，参数是黑线的宽度
		style1.setBorderLeft((short) 1); // 设置左边框
		style1.setBorderRight((short) 1); // 设置有边框
		style1.setBorderTop((short) 1); // 设置下边框
		style4.setDataFormat(format.getFormat("￥#,##0")); // --->设置为单元格内容为货币格式

		style5.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%")); // --->设置单元格内容为百分数格式

		// 表格第一行
		HSSFRow row1 = sheet.createRow(0); // --->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new Region(0, (short) 0, 2, (short) 5));
		row1.setHeightInPoints(25);
		HSSFCell cell1 = row1.createCell((short) 0); // --->创建一个单元格

		cell1.setCellStyle(style);
		cell1.setCellValue(ConstantExportExcelName.PUCHASEORDER);

		// //表格第二行
		// sheet.addMergedRegion(new Region(1,(short)0,1,(short)15));
		// HSSFRow row2 = sheet.createRow(1);
		// HSSFCell cell2 = row2.createCell((short)0);
		// cell2.setCellValue("供应商：" + purchaseId);
		// cell2.setCellStyle(style2);
		//
		// //表格第三行
		// sheet.addMergedRegion(new Region(2,(short)0,2,(short)15));
		// HSSFRow row3 = sheet.createRow(2);
		// HSSFCell cell3 = row3.createCell((short)0);
		// cell3.setCellValue("采购仓库：" + branchId);
		// cell3.setCellStyle(style2);

		// 表格第四行
		sheet.addMergedRegion(new Region(3, (short) 0, 3, (short) 0));
		HSSFRow row4 = sheet.createRow(3);
		row4.setHeightInPoints((short) 25);

		HSSFCell cell4_1 = row4.createCell((short) 0);
		cell4_1.setCellStyle(style1);
		cell4_1.setCellValue("货号");

		HSSFCell cell4_2 = row4.createCell((short) 1);
		cell4_2.setCellStyle(style1);
		cell4_2.setCellValue("商品名称");

		HSSFCell cell4_3 = row4.createCell((short) 2);
		cell4_3.setCellStyle(style1);
		cell4_3.setCellValue("规格");

		HSSFCell cell4_4 = row4.createCell((short) 3);
		cell4_4.setCellStyle(style1);
		cell4_4.setCellValue("单位");

		HSSFCell cell4_5 = row4.createCell((short) 4);
		cell4_5.setCellStyle(style1);
		cell4_5.setCellValue("采购量");

		HSSFCell cell4_6 = row4.createCell((short) 5);
		cell4_6.setCellStyle(style1);
		cell4_6.setCellValue("单价");

		List<PurchaseOrderGoodsItems> purchaseOrderGoodsList = noticeService
				.getListByObj(PurchaseOrderGoodsItems.class, "PURCHASEID='"
						+ id + "' ");
		if (purchaseOrderGoodsList != null && purchaseOrderGoodsList.size() > 0) {
			PurchaseGoodsCompare compare = new PurchaseGoodsCompare();
			Collections.sort(purchaseOrderGoodsList, compare);
			for (int i = 0; i < purchaseOrderGoodsList.size(); i++) {
				HSSFRow rowN = sheet.createRow(4 + i);
				PurchaseOrderGoodsItems pOGoodsItems = purchaseOrderGoodsList
						.get(i);
				HSSFCell cellN1 = rowN.createCell((short) 0);
				HSSFCell cellN2 = rowN.createCell((short) 1);
				HSSFCell cellN3 = rowN.createCell((short) 2);
				HSSFCell cellN4 = rowN.createCell((short) 3);
				HSSFCell cellN5 = rowN.createCell((short) 4);
				HSSFCell cellN6 = rowN.createCell((short) 5);
				cellN1.setCellStyle(style);
				cellN2.setCellStyle(style);
				cellN3.setCellStyle(style);
				cellN4.setCellStyle(style);
				cellN5.setCellStyle(style);
				cellN6.setCellStyle(style);
				cellN1.setCellValue(pOGoodsItems.getGoodsFile()
						.getSerialNumber());
				cellN2.setCellValue(pOGoodsItems.getGoodsFile().getGoods_name());
				cellN3.setCellValue(pOGoodsItems.getGoodsFile()
						.getGoods_specifications());
				cellN4.setCellValue(pOGoodsItems.getUnit());
				cellN5.setCellValue(pOGoodsItems.getActualNeed());
				cellN6.setCellValue(pOGoodsItems.getGoodsFile()
						.getGoods_price());
			}
		}
		String filePath = saveExcelFile(request, wb, purchaseId);
		FileOutputStream fileOut = null;
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
		result.put("msg", "success");
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject.toString();
	}

	/**
	 * 团购订单导出
	 * 
	 * @param orderNumber
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/outWGPOrderExcel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@SuppressWarnings("deprecation")
	public String outWGPOrderExcel(String orderNumber, String id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// AjaxResult ajaxResult = null;
		Map result = new HashMap<Object, Object>();
		// providerId = new String(providerId.getBytes("iso8859-1"), "utf-8");
		HSSFWorkbook wb = new HSSFWorkbook(); // --->创建了一个excel文件
		HSSFSheet sheet = wb.createSheet(ConstantExportExcelName.WGPORDER); // --->创建了一个工作簿
		HSSFDataFormat format = wb.createDataFormat(); // --->单元格内容格式
		sheet.setColumnWidth((short) 0, 20 * 256); // ---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
		sheet.setColumnWidth((short) 1, 20 * 256); // --->第一个参数是指哪个单元格，第二个参数是单元格的宽度
		sheet.setColumnWidth((short) 3, 20 * 256);
		sheet.setColumnWidth((short) 4, 20 * 256);
		sheet.setDefaultRowHeight((short) 300); // ---->有得时候你想设置统一单元格的高度，就用这个方法

		// 样式1
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style1 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style4 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style5 = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		// 设置标题字体格式
		Font font = wb.createFont();
		Font font1 = wb.createFont();
		// 设置字体样式
		font.setFontHeightInPoints((short) 20); // --->设置字体大小
		font.setFontName("Courier New"); // ---》设置字体，是什么类型例如：宋体
		font1.setItalic(true); // --->设置是否是加粗
		style1.setFont(font1); // --->将字体格式加入到style1中
		// style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
		// style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色
		style1.setWrapText(true); // 设置是否能够换行，能够换行为true
		style1.setBorderBottom((short) 1); // 设置下划线，参数是黑线的宽度
		style1.setBorderLeft((short) 1); // 设置左边框
		style1.setBorderRight((short) 1); // 设置有边框
		style1.setBorderTop((short) 1); // 设置下边框
		style4.setDataFormat(format.getFormat("￥#,##0")); // --->设置为单元格内容为货币格式

		style5.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%")); // --->设置单元格内容为百分数格式

		// 表格第一行
		HSSFRow row1 = sheet.createRow(0); // --->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new Region(0, (short) 0, 2, (short) 5));
		row1.setHeightInPoints(25);
		HSSFCell cell1 = row1.createCell((short) 0); // --->创建一个单元格

		cell1.setCellStyle(style);
		cell1.setCellValue(ConstantExportExcelName.WGPORDER);

		// //表格第二行
		// sheet.addMergedRegion(new Region(1,(short)0,1,(short)15));
		// HSSFRow row2 = sheet.createRow(1);
		// HSSFCell cell2 = row2.createCell((short)0);
		// cell2.setCellValue("供应商：" + purchaseId);
		// cell2.setCellStyle(style2);
		//
		// //表格第三行
		// sheet.addMergedRegion(new Region(2,(short)0,2,(short)15));
		// HSSFRow row3 = sheet.createRow(2);
		// HSSFCell cell3 = row3.createCell((short)0);
		// cell3.setCellValue("采购仓库：" + branchId);
		// cell3.setCellStyle(style2);

		// 表格第四行
		sheet.addMergedRegion(new Region(3, (short) 0, 3, (short) 0));
		HSSFRow row4 = sheet.createRow(3);
		row4.setHeightInPoints((short) 25);

		HSSFCell cell4_1 = row4.createCell((short) 0);
		cell4_1.setCellStyle(style1);
		cell4_1.setCellValue("商品编号");

		HSSFCell cell4_2 = row4.createCell((short) 1);
		cell4_2.setCellStyle(style1);
		cell4_2.setCellValue("商品名");

		HSSFCell cell4_3 = row4.createCell((short) 2);
		cell4_3.setCellStyle(style1);
		cell4_3.setCellValue("规格");

		HSSFCell cell4_4 = row4.createCell((short) 3);
		cell4_4.setCellStyle(style1);
		cell4_4.setCellValue("单价");

		HSSFCell cell4_5 = row4.createCell((short) 4);
		cell4_5.setCellStyle(style1);
		cell4_5.setCellValue("数量");

		HSSFCell cell4_6 = row4.createCell((short) 5);
		cell4_6.setCellStyle(style1);
		cell4_6.setCellValue("税率");

		// HSSFCell cell4_7 = row4.createCell((short)6);
		// cell4_7.setCellStyle(style1);
		// cell4_7.setCellValue("小计金额");

		List<WholesaleGroupPurchaseOrderItem> WGPOrderItemList = noticeService
				.getListByObj(WholesaleGroupPurchaseOrderItem.class,
						"ORDER_ID='" + id + "' ");
		if (WGPOrderItemList != null && WGPOrderItemList.size() > 0) {
			WGPOrderCompare compare = new WGPOrderCompare();
			Collections.sort(WGPOrderItemList, compare);
			for (int i = 0; i < WGPOrderItemList.size(); i++) {
				HSSFRow rowN = sheet.createRow(4 + i);
				WholesaleGroupPurchaseOrderItem wGPOrderItem = WGPOrderItemList
						.get(i);
				HSSFCell cellN1 = rowN.createCell((short) 0);
				HSSFCell cellN2 = rowN.createCell((short) 1);
				HSSFCell cellN3 = rowN.createCell((short) 2);
				HSSFCell cellN4 = rowN.createCell((short) 3);
				HSSFCell cellN5 = rowN.createCell((short) 4);
				HSSFCell cellN6 = rowN.createCell((short) 5);
				// HSSFCell cellN7 = rowN.createCell((short)6);
				cellN1.setCellStyle(style);
				cellN2.setCellStyle(style);
				cellN3.setCellStyle(style);
				cellN4.setCellStyle(style);
				cellN5.setCellStyle(style);
				cellN6.setCellStyle(style);
				// cellN7.setCellStyle(style);
				cellN1.setCellValue(wGPOrderItem.getGoodsFile()
						.getSerialNumber());
				cellN2.setCellValue(wGPOrderItem.getGoodsFile().getGoods_name());
				cellN3.setCellValue(wGPOrderItem.getGoodsFile()
						.getGoods_specifications());
				cellN4.setCellValue(wGPOrderItem.getOrderPrice());
				cellN5.setCellValue(wGPOrderItem.getNums());
				cellN6.setCellValue(wGPOrderItem.getRate());
				// cellN7.setCellValue(wGPOrderItem.getWithoutAmount());
			}
		}
		String filePath = saveExcelFile(request, wb, orderNumber);
		FileOutputStream fileOut = null;
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
		result.put("msg", "success");
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject.toString();
	}

	// public String filePath() throws ClassNotFoundException,
	// InstantiationException, IllegalAccessException,
	// UnsupportedLookAndFeelException {
	// String path = null;
	// //设置界面风格
	// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	// JFileChooser fileChooser = new JFileChooser();
	// //设置选择路径模式
	// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	// //设置对话框标题
	// fileChooser.setDialogTitle("请选择路径");
	// if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null))
	// {//用户点击了确定
	// path = fileChooser.getSelectedFile().getAbsolutePath();//取得路径选择
	// }
	// System.out.println(path);
	// return path;
	// }

	/**
	 * 采购订单(新增页面)导出
	 * 
	 * @param purchaseId
	 * @param id
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/exportExcelAdd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	@SuppressWarnings("deprecation")
	public String exportExcelAdd(String odd, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// AjaxResult ajaxResult = null;
		Map result = new HashMap<Object, Object>();
		HSSFWorkbook wb = new HSSFWorkbook(); // --->创建了一个excel文件
		HSSFSheet sheet = wb.createSheet(ConstantExportExcelName.PUCHASEORDER); // --->创建了一个工作簿
		HSSFDataFormat format = wb.createDataFormat(); // --->单元格内容格式
		sheet.setColumnWidth((short) 0, 20 * 256); // ---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
		sheet.setColumnWidth((short) 1, 20 * 256); // --->第一个参数是指哪个单元格，第二个参数是单元格的宽度
		sheet.setColumnWidth((short) 3, 20 * 256);
		sheet.setColumnWidth((short) 4, 20 * 256);
		sheet.setDefaultRowHeight((short) 300); // ---->有得时候你想设置统一单元格的高度，就用这个方法

		// 样式1
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style1 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style4 = wb.createCellStyle(); // 样式对象
		HSSFCellStyle style5 = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		// 设置标题字体格式
		Font font = wb.createFont();
		Font font1 = wb.createFont();
		// 设置字体样式
		font.setFontHeightInPoints((short) 20); // --->设置字体大小
		font.setFontName("Courier New"); // ---》设置字体，是什么类型例如：宋体
		font1.setItalic(true); // --->设置是否是加粗
		style1.setFont(font1); // --->将字体格式加入到style1中
		// style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
		// style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色
		style1.setWrapText(true); // 设置是否能够换行，能够换行为true
		style1.setBorderBottom((short) 1); // 设置下划线，参数是黑线的宽度
		style1.setBorderLeft((short) 1); // 设置左边框
		style1.setBorderRight((short) 1); // 设置有边框
		style1.setBorderTop((short) 1); // 设置下边框
		style4.setDataFormat(format.getFormat("￥#,##0")); // --->设置为单元格内容为货币格式
		style5.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%")); // --->设置单元格内容为百分数格式
		// 表格第一行
		HSSFRow row1 = sheet.createRow(0); // --->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new Region(0, (short) 0, 2, (short) 5));
		row1.setHeightInPoints(25);
		HSSFCell cell1 = row1.createCell((short) 0); // --->创建一个单元格

		cell1.setCellStyle(style);
		cell1.setCellValue(ConstantExportExcelName.PUCHASEORDER);

		// //表格第二行
		// sheet.addMergedRegion(new Region(1,(short)0,1,(short)15));
		// HSSFRow row2 = sheet.createRow(1);
		// HSSFCell cell2 = row2.createCell((short)0);
		// cell2.setCellValue("供应商：" + purchaseId);
		// cell2.setCellStyle(style2);
		//
		// //表格第三行
		// sheet.addMergedRegion(new Region(2,(short)0,2,(short)15));
		// HSSFRow row3 = sheet.createRow(2);
		// HSSFCell cell3 = row3.createCell((short)0);
		// cell3.setCellValue("采购仓库：" + branchId);
		// cell3.setCellStyle(style2);

		// 表格第四行
		sheet.addMergedRegion(new Region(3, (short) 0, 3, (short) 0));
		HSSFRow row4 = sheet.createRow(3);
		row4.setHeightInPoints((short) 25);

		HSSFCell cell4_1 = row4.createCell((short) 0);
		cell4_1.setCellStyle(style1);
		cell4_1.setCellValue("货号");

		HSSFCell cell4_2 = row4.createCell((short) 1);
		cell4_2.setCellStyle(style1);
		cell4_2.setCellValue("商品名称");

		HSSFCell cell4_3 = row4.createCell((short) 2);
		cell4_3.setCellStyle(style1);
		cell4_3.setCellValue("规格");

		HSSFCell cell4_4 = row4.createCell((short) 3);
		cell4_4.setCellStyle(style1);
		cell4_4.setCellValue("单位");

		HSSFCell cell4_5 = row4.createCell((short) 4);
		cell4_5.setCellStyle(style1);
		cell4_5.setCellValue("实际需求量");

		HSSFCell cell4_6 = row4.createCell((short) 5);
		cell4_6.setCellStyle(style1);
		cell4_6.setCellValue("单价");

		CtpUser ctpUser = (CtpUser) request.getSession().getAttribute("user");
		String purchaseId = request.getParameter("odd");
		String ids = request.getParameter("idStr");
		String nums = request.getParameter("num");
		String goodsPrices = request.getParameter("goodsPrice");
		String[] idStr = ids.split(",");
		String[] num = nums.split(",");
		String[] goodsPrice = goodsPrices.split(",");
		if (idStr != null && idStr.length > 0) {
			for (int i = 0; i < goodsPrice.length; i++) {
				PurchaseOrderGoodsItems purchaseOrderGoodsItems = (PurchaseOrderGoodsItems) noticeService
						.getObjById(idStr[i], "PurchaseOrderGoodsItems");
				purchaseOrderGoodsItems.setActualNeed(num[i]);
				purchaseOrderGoodsItems.setGoodsPrice(Double
						.valueOf(goodsPrice[i]));
				noticeService.updateObj(purchaseOrderGoodsItems);
			}
		}
		List<PurchaseOrderGoodsItems> purchaseOrderGoodsList = noticeService
				.getListByObj(PurchaseOrderGoodsItems.class,
						"PURCHASEID is null and CTPUSER_ID='" + ctpUser.getId()
								+ "'");
		if (purchaseOrderGoodsList != null && purchaseOrderGoodsList.size() > 0) {
			PurchaseGoodsCompare compare = new PurchaseGoodsCompare();
			Collections.sort(purchaseOrderGoodsList, compare);
			for (int i = 0; i < purchaseOrderGoodsList.size(); i++) {
				HSSFRow rowN = sheet.createRow(4 + i);
				PurchaseOrderGoodsItems pOGoodsItems = purchaseOrderGoodsList
						.get(i);
				HSSFCell cellN1 = rowN.createCell((short) 0);
				HSSFCell cellN2 = rowN.createCell((short) 1);
				HSSFCell cellN3 = rowN.createCell((short) 2);
				HSSFCell cellN4 = rowN.createCell((short) 3);
				HSSFCell cellN5 = rowN.createCell((short) 4);
				HSSFCell cellN6 = rowN.createCell((short) 5);
				cellN1.setCellStyle(style);
				cellN2.setCellStyle(style);
				cellN3.setCellStyle(style);
				cellN4.setCellStyle(style);
				cellN5.setCellStyle(style);
				cellN6.setCellStyle(style);
				cellN1.setCellValue(pOGoodsItems.getGoodsFile()
						.getSerialNumber());
				cellN2.setCellValue(pOGoodsItems.getGoodsFile().getGoods_name());
				cellN3.setCellValue(pOGoodsItems.getGoodsFile()
						.getGoods_specifications());
				cellN4.setCellValue(pOGoodsItems.getGoodsFile().getGoods_unit());
				cellN5.setCellValue(pOGoodsItems.getActualNeed());
				cellN6.setCellValue(pOGoodsItems.getGoodsFile()
						.getGoods_purchase_price());
			}
		}
		String filePath = saveExcelFile(request, wb, purchaseId);
		FileOutputStream fileOut = null;
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			result.put("msg", "fail");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject.toString();
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
		result.put("msg", "success");
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject.toString();
	}

}
