package com.proem.exm.service.impl.warehouse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.dao.warehouse.CheckNumberDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.CheckNumberService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 
 * @author zhusf
 * 
 */
@Service("CheckNumberService")
public class CheckNumberServiceImpl extends BaseServiceImpl implements
		CheckNumberService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	CheckNumberDao checknumberdao;
	@Autowired
	GoodsFileDao goodsFileDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.branch_name from zc_checknumber a LEFT JOIN ZC_BRANCH_INFO B ON A.BRANCH_ID=b.id where 1=1";
		sql += joinCondition(obj);
		sql += "order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = checknumberdao.getObjPagedList(page);
		Long total = checknumberdao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcCheckNumber zcCheckNumber = (ZcCheckNumber) obj;
		String conditions = "";
		if (StringUtil.validate(zcCheckNumber.getCheck_number())) {
			conditions += "and a.CHECK_NUMBER like '%"
					+ zcCheckNumber.getCheck_number() + "%'";
		}
		// if
		// (StringUtil.validate(checknumber.getWarehouse_code().getWarehouse_name()))
		// {
		// conditions += " WAREHOUSE_NAME like '%"
		// + checknumber.getWarehouse_code().getWarehouse_name() + "%'";
		// }
		return conditions;
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_checknumber order by createTime desc";
			ZcCheckNumber zcCheckNumber = (ZcCheckNumber) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DataGrid getGoodList(Page page, String type, String classifyIds,
			GoodsFile goodsFile, String houseId) throws Exception {
		String sql = "";
		// 全局盘点
		if ("0".equals(type)) {
			sql = "select d.store as houseStore ,a.*,b.classify_name as className,c.classify_name as brandName from zc_goods_master a left join zc_classify_info b on b.id=a.goods_class_id left join zc_classify_info c on c.id=a.goods_brand_id left join zc_storehouse d on d.goodsfile_id=a.id and d.branch_id='"
					+ houseId + "' where 1=1 ";
		} else
		// 单品盘点
		if ("1".equals(type)) {
			sql = "select d.store as houseStore ,a.*,b.classify_name as className,c.classify_name as brandName from zc_goods_master a left join zc_classify_info b on b.id=a.goods_class_id left join zc_classify_info c on c.id=a.goods_brand_id left join zc_storehouse d on d.goodsfile_id=a.id and d.branch_id='"
					+ houseId + "' where 1=1 ";
		} else
		// 类别盘点
		if ("2".equals(type)) {
			if (!StringUtils.isBlank(classifyIds)) {
				String[] classifyIdStr = classifyIds.split(",");
				if (classifyIdStr != null && classifyIdStr.length > 0) {
					sql = "select d.store as houseStore ,a.*,b.classify_name as className,c.classify_name as brandName from zc_goods_master a left join zc_classify_info b on b.id=a.goods_class_id left join zc_classify_info c on c.id=a.goods_brand_id left join zc_storehouse d on d.goodsfile_id=a.id and d.branch_id='"
							+ houseId + "'  where 1=1 ";
					for (int i = 0; i < classifyIdStr.length; i++) {
						if (classifyIdStr.length > 1) {
							if (i == 0) {
								sql += "and (a.goods_class_id='"
										+ classifyIdStr[i] + "'";
							} else if (i + 1 == classifyIdStr.length) {
								sql += "or a.goods_class_id='"
										+ classifyIdStr[i] + "')";
							} else {
								sql += "or a.goods_class_id='"
										+ classifyIdStr[i] + "'";
							}
						} else {
							sql += "and (a.goods_class_id='" + classifyIdStr[i]
									+ "')";
						}

					}
				}
			}
		} else
		// 品牌盘点
		if ("3".equals(type)) {
			if (!StringUtils.isBlank(classifyIds)) {
				String[] classifyIdStr = classifyIds.split(",");
				if (classifyIdStr != null && classifyIdStr.length > 0) {
					sql = "select d.store as houseStore ,a.*,b.classify_name as className,c.classify_name as brandName from zc_goods_master a left join zc_classify_info b on b.id=a.goods_class_id left join zc_classify_info c on c.id=a.goods_brand_id left join zc_storehouse d on d.goodsfile_id=a.id and d.branch_id='"
							+ houseId + "' where 1=1 ";
					for (int i = 0; i < classifyIdStr.length; i++) {
						if (classifyIdStr.length > 1) {
							if (i == 0) {
								sql += "and (a.goods_brand_id='"
										+ classifyIdStr[i] + "'";
							} else if (i + 1 == classifyIdStr.length) {
								sql += "or a.goods_brand_id='"
										+ classifyIdStr[i] + "')";
							} else {
								sql += "or a.goods_brand_id='"
										+ classifyIdStr[i] + "'";
							}
						} else {
							sql += "and (a.goods_brand_id='" + classifyIdStr[i]
									+ "')";
						}

					}
				}
			}
		}
		sql += joinConditionGooods(goodsFile);
		sql += " order by a.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinConditionGooods(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += "and serialNumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += "and goods_name like '%" + goodsFile.getGoods_name()
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

	@Override
	public DataGrid getCheckGoodList(Page page, String id, GoodsFile goodsFile,
			String houseId) throws Exception {
		String sql = "select a.id,a.actualchecknumber,a.warehouse_id,b.serialnumber,b.goods_name,c.classify_name as className,d.classify_name as brandname,b.goods_specifications,b.goods_unit,f.store,b.goods_code,b.goods_price from zc_check_items a left join zc_goods_master b on b.id=a.goodsfile_id left join zc_classify_info c on c.id=b.goods_class_id left join zc_classify_info d on d.id=b.goods_brand_id  left join zc_storehouse f on f.goodsfile_id=b.id and f.branch_id='"
				+ houseId + "' where  1=1 and  a.warehouse_id='" + id + "'";
		sql += conditionGooods(goodsFile);
		sql += "order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String conditionGooods(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += "and b.serialNumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += "and b.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		return conditions;
	}

}
