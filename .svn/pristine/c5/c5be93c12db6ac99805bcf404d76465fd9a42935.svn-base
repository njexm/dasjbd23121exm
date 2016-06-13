package com.proem.exm.service.impl.basic.goodFileServiceImpl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.basic.CommodityClassify.CommodityClassifyService;
import com.proem.exm.service.basic.goodsFileService.GoodsFileService;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.customer.CustomerInfoService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.JdbcUtil;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("GoodsFileService")
public class GoodsFileServiceImpl extends BaseServiceImpl implements
		GoodsFileService {

	@Autowired
	private GoodsFileDao goodsFileDao;
	@Autowired
	CommodityClassifyService commodityClassifyService;
	@Autowired
	ProviderInfoService providerInfoService;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Override
	public void updateObj(Object obj) {

	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		System.out.println(goodsFile.getId());
		if (StringUtil.validate(goodsFile.getId())) {
			conditions += " and goods_class_id = '" + goodsFile.getId() + "'";
		} else {
			if (StringUtil.validate(goodsFile.getGoods_class())) {
				if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
					conditions += " and b.parentid = '"
							+ goodsFile.getGoods_class().getId() + "' or goods_class_id='"+goodsFile.getGoods_class().getId()+"'";
				}
			}
			if (StringUtil.validate(goodsFile.getSerialNumber())) {
				conditions += " and serialnumber like '%"
						+ goodsFile.getSerialNumber() + "%'";
			}
			if (StringUtil.validate(goodsFile.getGoods_name())) {
				conditions += " and goods_name like'%"
						+ goodsFile.getGoods_name() + "%'";
			}
			if (StringUtil.validate(goodsFile.getGoods_brand())) {
				if (StringUtil.validate(goodsFile.getGoods_brand().getId())) {
					conditions += " and goods_brand_id = '"
							+ goodsFile.getGoods_brand().getId() + "'";
				}
			}
			if (StringUtil.validate(goodsFile.getProvider())) {
				if (StringUtil.validate(goodsFile.getProvider().getId())) {
					conditions += " and goods_supplier_id = '"
							+ goodsFile.getProvider().getId() + "'";
				}
			}
		}

		return conditions;
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
			conditions += " and goods_class_id = '"
					+ goodsFile.getGoods_class().getId() + "'";
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getProvider().getId())) {
			conditions += " and goods_supplier_id = '"
					+ goodsFile.getProvider().getId() + "'";
		}
		return conditions;
	}

	/**
	 * 供应商商品查询
	 * 
	 * @param obj
	 * @return
	 */
	private String joinConditionNullprovider(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and goods_name like'%" + goodsFile.getGoods_name()
					+ "%'";
		}
		return conditions;
	}

	/**
	 * 分页展示
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where 1=1";
		sql += joinCondition(obj);
		sql += " order by a.serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页展示
	 */
	@Override
	public DataGrid getPurchaseAddGoods(Page page, Object obj) throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where 1=1";
		sql += conditions(obj);
		sql += " order by a.serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getPurchaseAddGoodsItems(Page page, Object obj)
			throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where 1=1";
		sql += addConditions(obj);
		sql += " order by a.serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String addConditions(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and a.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		return conditions;
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getPurchaseAddGoodsItems(Page page, Object obj,
			String pyNum, String providerId, String branchId, String changeType)
			throws Exception {
		String sql = "select e.store,a.id,a.serialnumber,a.goods_name,a.goods_price,a.goods_specifications from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id left join zc_storehouse e on e.goodsfile_id=a.id and e.branch_id='"
				+ branchId + "' where 1=1 ";
		sql += addConditions(obj, providerId, pyNum);
		sql += " order by a.serialnumber asc ";
		if ("2".equals(changeType) && branchId != null) {
			sql = "select b.id,b.serialnumber,b.goods_name,b.goods_price,a.store,b.goods_specifications from zc_storehouse a left join zc_goods_master b on a.goodsfile_id=b.id left join zc_branch_info c on c.id=a.branch_id where c.id='"
					+ branchId + "' and a.store > 0 ";
			sql += storeConditions(obj);
			sql += " order by b.serialnumber asc ";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String storeConditions(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and b.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		return conditions;
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getPurchaseAddGoodsItems(Page page, Object obj,
			String providerId) throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where 1=1";
		sql += addConditions(obj, providerId);
		sql += " order by a.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String addConditions(Object obj, String id, String pyNum) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(id)) {
			conditions += " and a.GOODS_SUPPLIER_ID = '" + id + "'";
		}
		if (StringUtil.validate(pyNum)) {
			conditions += " and a.GOODS_PY_CODE like '%" + pyNum + "%'";
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and a.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		return conditions;
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String addConditions(Object obj, String id) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(id)) {
			conditions += " and a.GOODS_SUPPLIER_ID = '" + id + "'";
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and a.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.id='"
						+ goodsFile.getGoods_class().getId()
						+ "' or b.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getPurchaseAddPySameGoods(Page page, Object obj,
			String pyNum, String providerId) throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where 1=1";
		sql += addConditionss(obj, pyNum, providerId);
		sql += " order by a.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String addConditionss(Object obj, String pyNum, String providerId) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(pyNum)) {
			conditions += " and a.GOODS_PY_CODE like '%" + pyNum + "%'";
		}
		if (StringUtil.validate(providerId)) {
			conditions += " and c.ID ='" + providerId + "'";
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and a.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.id='"
						+ goodsFile.getGoods_class().getId()
						+ "' or b.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询供应商为空的商品信息
	 */
	@Override
	public DataGrid getPagedDataNullProvider(Page page, Object obj)
			throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where a.GOODS_SUPPLIER_ID is null";
		sql += joinConditionNullprovider(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页查询供应商商品
	 */
	@Override
	public DataGrid providerGoods(Page page, Object obj, String providerId)
			throws Exception {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where a.GOODS_SUPPLIER_ID='"
				+ providerId + "'";
		sql += joinConditionNullprovider(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 通过id查询详细信息
	 */
	@Override
	public Map<String, Object> getObjById(Object obj) {
		return null;
	}

	/**
	 * 无分页条件查询所有
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
					+ " left join zc_classify_info b on a.goods_class_id=b.id"
					+ " left join zc_classify_info d on a.goods_brand_id=d.id"
					+ " left join zc_provider_info c on a.goods_supplier_id=c.id"
					+ " where 1=1";
			GoodsFile goodsFile = (GoodsFile) obj;
			if (StringUtil.validate(goodsFile.getGoods_brand().getId())) {
				sql += " and goods_brand_id  = '"
						+ goodsFile.getGoods_brand().getId() + "'";
			}
			if (StringUtil.validate(goodsFile.getSerialNumber())) {
				sql += " and serialnumber = '" + goodsFile.getSerialNumber()
						+ "'";
			}
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				sql += " and goods_class_id  = '"
						+ goodsFile.getGoods_class().getId() + "'";
			}
			if (StringUtil.validate(goodsFile.getGoods_name())) {
				sql += " and goods_name like '%" + goodsFile.getGoods_name()
						+ "%'";
			}
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				sql += " and goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
			if (StringUtil.validate(goodsFile.getId())) {
				sql += " where ID=?";
				paramList.add(goodsFile.getId());
			}
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
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

			for (int i = 1; i <= totalRow; i++) {
				row = sheet.getRow(i);
				// System.out.println(row.getCell(0).toString());
				if (row != null) {
					GoodsFile goodsFile = new GoodsFile();
					goodsFile.setProvider(new ProviderInfo());
					goodsFile.setGoods_class(new CommodityClassify());
					goodsFile.setGoods_brand(new CommodityClassify());
					// 品名
					HSSFCell goods_code = row.getCell(0);
					// if (goods_code.getCellType() ==
					// goods_code.CELL_TYPE_NUMERIC) {
					// // 返回数值类型的值
					// String goods_id = getValue(goods_code) == null ? ""
					// : getValue(goods_code);
					// double goodsid = Double.valueOf(goods_id);
					// int goodsDd = (int) goodsid;
					// goodsFile.setGoods_code(goodsDd+"");
					// }else{
					// goodsFile.setGoods_code(getValue(goods_code) == null ? ""
					// : getValue(goods_code));
					// }
					// if(getValue(goods_code)==null ||
					// !StringUtils.isBlank(getValue(goods_code)) ||
					// getValue(goods_code).length()>50){
					// returnAnwer=returnAnwer+"第"+(i+1)+"行编号输入出错@";
					// }else{
					goodsFile.setGoods_code(getValue(goods_code) == null ? ""
							: getValue(goods_code).trim());
					// }
					HSSFCell goods_class_id = row.getCell(1);
					if (goods_class_id.getCellType() == goods_class_id.CELL_TYPE_NUMERIC) {
						// 返回数值类型的值
						String goods_id = getValue(goods_class_id) == null ? ""
								: getValue(goods_class_id);
						double goodsid = Double.valueOf(goods_id);
						int goodsDd = (int) goodsid;
						goodsFile.getGoods_class().setId(goodsDd + "");
					} else {
						goodsFile.getGoods_class().setId(
								getValue(goods_class_id) == null ? ""
										: getValue(goods_class_id).trim());
					}
					HSSFCell goods_price = row.getCell(2);
					String goods_priceStr = getValue(goods_price);
					if (goods_priceStr == null
							|| StringUtils.isBlank(goods_priceStr)) {
						goods_priceStr = "0";
					}
					goodsFile.setGoods_price(Float.parseFloat(goods_priceStr));
					HSSFCell goods_supplier_id = row.getCell(3);
					if (goods_supplier_id.getCellType() == goods_supplier_id.CELL_TYPE_NUMERIC) {
						// 返回数值类型的值
						String goods_id = getValue(goods_supplier_id) == null ? ""
								: getValue(goods_supplier_id);
						double goodsid = Double.valueOf(goods_id);
						int goodsDd = (int) goodsid;
						goodsFile.getProvider().setId(goodsDd + "");
					} else {
						goodsFile.getProvider().setId(
								getValue(goods_supplier_id) == null ? ""
										: getValue(goods_supplier_id).trim());
					}
					HSSFCell goods_specifications = row.getCell(4);
					HSSFCell goods_unit = row.getCell(7);
					String nums = getValue(goods_specifications) == null ? ""
							: getValue(goods_specifications).trim();
					String units = getValue(goods_unit) == null ? ""
							: getValue(goods_unit).trim();
					goodsFile.setGoods_specifications(nums + units);
					HSSFCell goods_brand_id = row.getCell(5);
					goodsFile.getGoods_brand().setId(
							getValue(goods_brand_id).trim());
					HSSFCell goods_name = row.getCell(6);
					goodsFile.setGoods_name(getValue(goods_name) == null ? ""
							: getValue(goods_name).trim());
					goodsFile.setGoods_unit(getValue(goods_unit) == null ? ""
							: getValue(goods_unit).trim());
					HSSFCell goods_origin = row.getCell(8);
					goodsFile
							.setGoods_origin(getValue(goods_origin) == null ? ""
									: getValue(goods_origin).trim());
					HSSFCell goods_purchase_price = row.getCell(9);
					String goods_purchase_priceStr = getValue(goods_purchase_price);
					if (goods_purchase_priceStr == null
							|| StringUtils.isBlank(goods_purchase_priceStr)) {
						goods_purchase_priceStr = "0";
					}
					goodsFile.setGoods_purchase_price(Float
							.parseFloat(goods_purchase_priceStr));
					HSSFCell lowest_price = row.getCell(10);
					String lowest_priceStr = getValue(lowest_price);
					if (lowest_priceStr == null
							|| StringUtils.isBlank(lowest_priceStr)) {
						lowest_priceStr = "0";
					}
					goodsFile
							.setLowest_price(Float.parseFloat(lowest_priceStr));
					HSSFCell wholesale_price2 = row.getCell(11);
					String wholesale_price2Str = getValue(wholesale_price2);
					if (wholesale_price2Str == null
							|| "NULL".equals(wholesale_price2Str)
							|| StringUtils.isBlank(wholesale_price2Str)) {
						wholesale_price2Str = "0";
					}
					goodsFile.setWholesale_price2(Float
							.parseFloat(wholesale_price2Str));
					HSSFCell wholesale_price3 = row.getCell(12);
					String wholesale_price3Str = getValue(wholesale_price3);
					if (wholesale_price3Str == null
							|| "NULL".equals(wholesale_price3Str)
							|| StringUtils.isBlank(wholesale_price3Str)) {
						wholesale_price3Str = "0";
					}
					goodsFile.setWholesale_price3(Float
							.parseFloat(wholesale_price3Str));
					HSSFCell wholesale_price4 = row.getCell(13);
					String wholesale_price4Str = getValue(wholesale_price4);
					if (wholesale_price4Str == null
							|| "NULL".equals(wholesale_price4Str)
							|| StringUtils.isBlank(wholesale_price4Str)) {
						wholesale_price4Str = "0";
					}
					goodsFile.setWholesale_price4(Float
							.parseFloat(wholesale_price4Str));
					HSSFCell wholesale_price5 = row.getCell(14);
					String wholesale_price5Str = getValue(wholesale_price5);
					if (wholesale_price5Str == null
							|| "NULL".equals(wholesale_price5Str)
							|| StringUtils.isBlank(wholesale_price5Str)) {
						wholesale_price5Str = "0";
					}
					goodsFile.setWholesale_price5(Float
							.parseFloat(wholesale_price5Str));
					HSSFCell wholesale_price6 = row.getCell(15);
					String wholesale_price6Str = getValue(wholesale_price6);
					if (wholesale_price6Str == null
							|| "NULL".equals(wholesale_price6Str)
							|| StringUtils.isBlank(wholesale_price6Str)) {
						wholesale_price6Str = "0";
					}
					goodsFile.setWholesale_price6(Float
							.parseFloat(wholesale_price6Str));
					HSSFCell wholesale_price7 = row.getCell(16);
					String wholesale_price7Str = getValue(wholesale_price7);
					if (wholesale_price7Str == null
							|| "NULL".equals(wholesale_price7Str)
							|| StringUtils.isBlank(wholesale_price7Str)) {
						wholesale_price7Str = "0";
					}
					goodsFile.setWholesale_price7(Float
							.parseFloat(wholesale_price7Str));
					HSSFCell wholesale_price8 = row.getCell(17);
					String wholesale_price8Str = getValue(wholesale_price8);
					if (wholesale_price8Str == null
							|| "NULL".equals(wholesale_price8Str)
							|| StringUtils.isBlank(wholesale_price8Str)) {
						wholesale_price8Str = "0";
					}
					goodsFile.setWholesale_price8(Float
							.parseFloat(wholesale_price8Str));
					HSSFCell remark = row.getCell(18);
					goodsFile.setRemark(getValue(remark) == null ? ""
							: getValue(remark));
					HSSFCell goodIdNoCov = row.getCell(19);
					if (goodIdNoCov.getCellType() == goodIdNoCov.CELL_TYPE_NUMERIC) {
						// 返回数值类型的值
						String goods_id = getValue(goodIdNoCov) == null ? ""
								: getValue(goodIdNoCov);
						BigDecimal db = new BigDecimal(goods_id);
						String ii = db.toPlainString();
						goodsFile.setId(ii.trim());
					} else {
						String goods_id = getValue(goodIdNoCov) == null ? ""
								: getValue(goodIdNoCov);
						goodsFile.setId(goods_id.trim());
					}
					HSSFCell typeId = row.getCell(20);
					goodsFile.setPoint_or_not(getValue(typeId));
					HSSFCell marketable = row.getCell(21);
					goodsFile.setGoods_state(getValue(marketable));
					HSSFCell huohao = row.getCell(22);
					if (huohao.getCellType() == huohao.CELL_TYPE_NUMERIC) {
						// 返回数值类型的值
						String goods_id = getValue(huohao) == null ? ""
								: getValue(huohao);
						BigDecimal db = new BigDecimal(goods_id);
						String ii = db.toPlainString();
						goodsFile.setSerialNumber(ii.trim());
					} else {
						goodsFile.setSerialNumber(getValue(huohao) == null ? ""
								: getValue(huohao).trim());
					}
					HSSFCell store = row.getCell(23);
					goodsFile.setStore(getValue(store));
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

	@Override
	public int saveByExcel(GoodsFile goodsFile) {
		try {
			// String id = UuidUtils.getUUID();
			String provider_id = goodsFile.getProvider().getId();
			if (!StringUtils.isBlank(provider_id)) {
				ProviderInfo providerInfo = (ProviderInfo) providerInfoService
						.getObjById(provider_id, "ProviderInfo");
				// 如果分类信息为空，则进行查询插入
				if (providerInfo == null) {
					InsertItem(3, provider_id);
					ProviderInfo providerInfo1 = (ProviderInfo) providerInfoService
							.getObjById(provider_id, "ProviderInfo");
					goodsFile.setProvider(providerInfo1);
				} else {
					goodsFile.setProvider(providerInfo);
				}
			} else {
				goodsFile.setProvider(null);
			}
			// 保存商品属性
			String type_Id = goodsFile.getPoint_or_not();
			if (!StringUtils.isBlank(type_Id)) {
				double d = Double.valueOf(type_Id);
				int typeId = (int) d;
				CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
						.getObjById(typeId + "", "CommodityClassify");
				// 如果分类信息为空，则进行查询插入
				if (commodityClassify == null) {
					InsertItem(1, typeId + "");
				} else {
				}
			} else {
			}
			// 保存商品类型
			String type = goodsFile.getGoods_class().getId();
			if (!StringUtils.isBlank(type)) {
				String typeId = type;
				CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
						.getObjById(typeId + "", "CommodityClassify");
				// 如果分类信息为空，则进行查询插入
				// if (commodityClassify == null) {
				// InsertItem(4, typeId + "");
				// CommodityClassify commodityClassify1 = (CommodityClassify)
				// commodityClassifyService
				// .getObjById(typeId + "", "CommodityClassify");
				// goodsFile.setGoods_class(commodityClassify1);
				// } else {
				// goodsFile.setGoods_class(commodityClassify);
				// }
				goodsFile.setGoods_class(commodityClassify);
			} else {
				goodsFile.setGoods_class(null);
			}
			// 保存品牌类型
			String brand_Id = goodsFile.getGoods_brand().getId();
			if (!StringUtils.isBlank(brand_Id)) {
				String brandId = brand_Id;
				// double brand = Double.valueOf(brand_Id);
				// int brandId = (int) brand;
				CommodityClassify commodityClassify = (CommodityClassify) commodityClassifyService
						.getObjById(brandId + "", "CommodityClassify");
				// 如果分类信息为空，则进行查询插入
				if (commodityClassify == null) {
					InsertItem(2, brandId + "");
					CommodityClassify commodityClassify1 = (CommodityClassify) commodityClassifyService
							.getObjById(brandId + "", "CommodityClassify");
					goodsFile.setGoods_brand(commodityClassify1);
				} else {
					goodsFile.setGoods_brand(commodityClassify);
				}
			} else {
				goodsFile.setGoods_brand(null);
			}
			goodsFile.setDelflag("0");
			saveObj(goodsFile);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getClassInfoList() {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id";
		Page page = new Page();
		page.setPage(1);
		page.setRows(10000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 插入商品类型
	 * 
	 * @param con
	 * @param orderId
	 */
	public void InsertItem(int clasType, String typeId) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "";
			if (clasType == 1) {
				sql = " SELECT '3' as type_id, '通用' as name  FROM dbo.t_bd_item_info  where item_no='1' ";
			}
			if (clasType == 4) {
				sql = " SELECT item_clsno,item_flag,cls_parent,display_flag,return_rate,item_clsname,comp_rate,trans_flag FROM dbo.t_bd_item_cls  where item_clsno='"
						+ typeId + "' ";
			}
			if (clasType == 2) {
				sql = " SELECT item_brandno,item_flag,brand_parent,display_flag,return_rate,item_brandname,comp_rate,trans_flag FROM dbo.t_bd_item_brand WHERE item_brandno='"
						+ typeId + "' ";
			}
			if (clasType == 3) {
				sql = " SELECT  supcust_no,supcust_flag,sup_name,region_no,sup_type,sup_man,sup_addr,zip,sup_email,sup_tel,sup_fax, "
						+ "	sup_acct_back,sup_acct_no,sup_tax_no,display_flag,check_out_flag,check_out_date, "
						+ "  check_out_day, credit_amt,sub_no,pay_flag,order_flag,purchase_day, "
						+ "po_cycle,acc_level,reg_type,oper_id,purchase_week,sale_way,shopcard,modify_date,settle_branch,com_flag,mobile,discount,trans_flag, "
						+ "vip_card_id,memo,receive_management,mbranch_no,use_way FROM dbo.t_bd_supcust_info ";
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				if (clasType == 4) {
					CommodityClassify commodityClassify = new CommodityClassify();
					commodityClassify.setId(rs.getString(1));
					commodityClassify.setClassify_name(rs.getString(6));
					commodityClassify.setClassify_code(rs.getString(1));
					commodityClassify.setParentPath(rs.getString(1));
					commodityClassify.setClassify_type("1");
					if (rs.getString(3) == null || rs.getString(3) == "") {
						commodityClassify.setParentId("");
					} else {
						commodityClassify.setParentId(rs.getString(3));
					}
					String parentPath = rs.getString(1);
					if (parentPath.length() == 2) {
						commodityClassify.setClassify_level("1");
					} else if (parentPath.length() == 4) {
						commodityClassify.setClassify_level("2");
					} else if (parentPath.length() == 6) {
						commodityClassify.setClassify_level("3");
					}
					commodityClassify.setDelFlag(rs.getString(4));
					commodityClassifyService.saveObj(commodityClassify);
				}
				if (clasType == 1) {
					CommodityClassify commodityClassify = new CommodityClassify();
					commodityClassify.setId(rs.getString(1));
					commodityClassify.setClassify_name(rs.getString(2));
					commodityClassify.setClassify_code(rs.getString(1));
					commodityClassify.setClassify_type("1");
					commodityClassify.setClassify_level("1");
					commodityClassify.setParentId("0");
					commodityClassify.setParentPath(",");
					commodityClassify.setTypeId("0");
					commodityClassify.setDelFlag("0");
					commodityClassifyService.saveObj(commodityClassify);
				}
				if (clasType == 2) {
					CommodityClassify commodityClassify = new CommodityClassify();
					commodityClassify.setId(rs.getString(1));
					commodityClassify.setClassify_name(rs.getString(6));
					commodityClassify.setClassify_code(rs.getString(1));
					commodityClassify.setClassify_type("2");
					commodityClassify.setClassify_level("1");
					commodityClassify.setDelFlag(rs.getString(4));
					commodityClassifyService.saveObj(commodityClassify);
				}
				if (clasType == 3) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					if (rs.getString(2) != "S") {
					} else {
						ProviderInfo providerInfo = new ProviderInfo();
						providerInfo.setId(rs.getString(1));
						providerInfo.setCreateTime(dateFormat.parse(dateFormat
								.format(date)));
						providerInfo.setUpdateTime(dateFormat.parse(dateFormat
								.format(date)));
						if (rs.getString(7) == "" || rs.getString(7) == null) {
							providerInfo.setAddress("");
						} else {
							providerInfo.setAddress(rs.getString(7));
						}
						if (rs.getString(13) == null || rs.getString(13) == "") {
							providerInfo.setAccount("");
						} else {
							providerInfo.setAccount(rs.getString(13));
						}
						if (rs.getString(3) == "" || rs.getString(3) == null) {
							providerInfo.setNickname("");
						} else {
							providerInfo.setNickname(rs.getString(3));
						}
						if (rs.getString(9) == "" || rs.getString(9) == null) {
							providerInfo.setMail("");
						} else {
							providerInfo.setMail(rs.getString(9));
						}
						if (rs.getString(10) == null || rs.getString(10) == "") {
							providerInfo.setTelephone("");
						} else {
							providerInfo.setTelephone(rs.getString(10));
						}
						if (rs.getString(34) == null || rs.getString(34) == "") {
							providerInfo.setMobilephone("");
						} else {
							providerInfo.setMobilephone(rs.getString(34));
						}
						if (rs.getString(12) == null || rs.getString(12) == "") {
							providerInfo.setBank("");
						} else {
							providerInfo.setBank(rs.getString(12));
						}
						if (rs.getString(4) == "1") {
							providerInfo.setArea("本地");
						} else {
							providerInfo.setArea("外地");
						}
						providerInfo.setDelFlag(rs.getString(15));
						if (rs.getString(15) == "" || rs.getString(15) == null) {
							providerInfo.setTaxregistration("");
						} else {
							providerInfo.setTaxregistration(rs.getString(15));
						}
						if (rs.getString(11) == "" || rs.getString(11) == null) {
							providerInfo.setFax("");
						} else {
							providerInfo.setFax(rs.getString(11));
						}
						if (rs.getString(8) == null || rs.getString(8) == "") {
							providerInfo.setPostcode("");
						} else {
							providerInfo.setPostcode(rs.getString(8));
						}
						if (rs.getString(6) == null || rs.getString(6) == "") {
							providerInfo.setLinkman("");
						} else {
							providerInfo.setLinkman(rs.getString(6));
						}
						if (rs.getString(5) == "1") {
							providerInfo.setEnterprisetype("国有");
						} else {
							providerInfo.setEnterprisetype("民营");
						}
						if (rs.getString(29) == "A") {
							providerInfo.setPractice("购销");
						} else {
							providerInfo.setPractice("联营");
						}
						providerInfo.setFrozen("正常");
						providerInfo.setSettlement("总部");
						if (rs.getString(23) == null || rs.getString(23) == "") {
							providerInfo.setDeliverycycle("");
						} else {
							providerInfo.setDeliverycycle(rs.getString(23));
						}
						if (rs.getString(18) == null || rs.getString(18) == "") {
							providerInfo.setSettlementcycle("");
						} else {
							providerInfo.setSettlementcycle(rs.getString(18));
						}
						if (rs.getString(17) == null || rs.getString(17) == "") {
							providerInfo.setSettlementdate("");
						} else {
							providerInfo.setSettlementdate(rs.getString(17));
						}
						if (rs.getString(21) == "0") {
							providerInfo.setSettlementway("指定账期");
						} else {
							providerInfo.setSettlementway("货到付款");
						}
						providerInfoService.saveObj(providerInfo);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 树形表格
	 */
	@Override
	public List<Map<String, Object>> getListByParent(String parentId) {
		String sql = "select a.* ,d.classify_name as goods_brand_name,b.classify_name as goods_class_name,c.provider_nickname as goods_supplier_name,c.provider_practice from zc_goods_master a"
				+ " left join zc_classify_info b on a.goods_class_id=b.id"
				+ " left join zc_classify_info d on a.goods_brand_id=d.id"
				+ " left join zc_provider_info c on a.goods_supplier_id=c.id where b.parentid = '"
				+ parentId + "' order by b.order_index asc ";
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 树形菜单
	 */
	@Override
	public List<Map<String, Object>> getTreeData() {
		List<Map<String, Object>> list = null;
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select a.id,a.classify_name as text from zc_classify_info a where a.parentid = ? order by a.order_index asc";
			paramList.clear();
			paramList.add("0");
			list = baseDataMng.querySqlToLowerCase(sql, paramList);
			List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				paramList.clear();
				paramList.add(map.get("id").toString());
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				List<Map<String, Object>> subListSon = new ArrayList<Map<String, Object>>();
				Map<String, Object> mapSon = null;
				if (subList != null && subList.size() > 0) {
					for (int a = 0; a < subList.size(); a++) {
						mapSon = subList.get(a);
						paramList.clear();
						paramList.add(mapSon.get("id").toString());
						subListSon = baseDataMng.querySqlToLowerCase(sql,
								paramList);
						List<Map<String, Object>> subListSon1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> mapSon1 = null;
						if (subListSon != null && subListSon.size() > 0) {
							for (int j = 0; j < subListSon.size(); j++) {
								mapSon1 = subListSon.get(j);
								paramList.clear();
								paramList.add(mapSon1.get("id").toString());
								subListSon1 = baseDataMng.querySqlToLowerCase(
										sql, paramList);
								mapSon1.put("children", subListSon1);
							}
						}
						mapSon.put("children", subListSon);
					}
				}
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页展示所有成品
	 */
	@Override
	public DataGrid getProductGoodsList(Page page, Object obj, String serial,
			String goodsName) throws Exception {
		String sql = "select * from ZC_GOODS_MASTER where GOODSATTRIBUTE='2' ";
		sql += joinCondition(obj, serial, goodsName);
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 成品货号查询
	 */
	private String joinCondition(Object obj, String serial, String goodsName) {
		String conditions = "";
		if (StringUtil.validate(serial)) {
			conditions += " and SERIALNUMBER like'%" + serial + "%'";
		}
		if (StringUtil.validate(goodsName)) {
			conditions += " and GOODS_NAME like'%" + goodsName + "%'";
		}
		return conditions;
	}

	/**
	 * 根据客户信息获取客户商品下的批发价
	 */
	@Override
	public DataGrid getGoodsInfo(Page page, GoodsFile goodsFile,
			String customerId) throws Exception {
		CustomerInfo customerInfo = (CustomerInfo) customerInfoService
				.getObjById(customerId, "CustomerInfo");
		String sql = "select a.id, a.SERIALNUMBER, a.GOODS_NAME, "
				+ customerInfo.getDefaultPrice()
				+ " as price,a.GOODS_SPECIFICATIONS from ZC_GOODS_MASTER a left join zc_classify_info b on a.goods_class_id=b.id where 1=1";
		sql += joinCondition1(goodsFile);
		sql += " order by a.SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition1(GoodsFile goodsFile) {
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and a.serialNumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.goods_name like '%" + goodsFile.getGoods_name()
					+ "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.id='"
						+ goodsFile.getGoods_class().getId()
						+ "' or b.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		return conditions;
	}

}
