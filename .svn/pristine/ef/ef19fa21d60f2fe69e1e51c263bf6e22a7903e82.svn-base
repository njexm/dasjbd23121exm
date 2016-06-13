package com.proem.exm.service.impl.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.warehouse.WareHouseDao;
import com.proem.exm.entity.warehouse.WarehouseQuery;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.WarehouseQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 仓库查询实现类
 * 
 * @author songcj 2015年12月15日 下午2:01:46
 */
@Service("warehouseQueryService")
public class WarehouseQueryServiceImpl extends BaseServiceImpl implements
		WarehouseQueryService {

	@Autowired
	WareHouseDao wareHouseDao;

	@Override
	public DataGrid getPagedDataGridGoods(Page page, Object obj)
			throws Exception {
		String sql = "select a.storemoney,a.store,b.id as branch_id,c.serialnumber,b.branch_name as branchnames,c.goods_name,c.goods_specifications,c.goods_unit,c.goods_purchase_price,c.goods_price,"
				+ " d.classify_code,d.classify_name,d.id as classId,e.classify_name as brand_name,e.id as brand_id,f.provider_nickname,f.id as provider_id,"
				+ " g.branch_name,g.id as branch_total_id,g.branch_code,(c.goods_price - c.goods_purchase_price) as difference"
				+ " from zc_storehouse a "
				+ " left join zc_branch_info b on a.branch_id = b.id"
				+ " left join zc_goods_master c on a.goodsfile_id = c.id"
				+ " left join zc_classify_info d on c.goods_class_id = d.id"
				+ " left join zc_classify_info e on c.goods_brand_id = e.id"
				+ " left join zc_provider_info f on c.goods_supplier_id = f.id"
				+ " left join zc_branch_total g on b.branchtotal_id = g.id where a.store>0 ";
		sql += joinGoodsCondition(obj);
		sql += " order by c.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinGoodsCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and b.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and d.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or d.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand())) {
				if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
					conditions += " and e.id='"
							+ warehouseQuery.getBrand().getId() + "'";
				}
			}
		}
		if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
			if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
				if (StringUtil.validate(warehouseQuery.getProviderInfo()
						.getId())) {
					conditions += " and f.id='"
							+ warehouseQuery.getProviderInfo().getId() + "'";
				}
			}
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and c.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and c.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		return conditions;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_AREA_NANJING where leveltype='2' ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getClassifyQueryGoods(Page page, Object obj)
			throws Exception {
		String sql = "select e.id as EID,sum(a.store)as stock,e.classify_name,c.street,b.id as BID,b.branch_code,b.branch_name,d.serialnumber,d.goods_name,sum(d.goods_purchase_price*a.store)as goodsmoney,sum(d.lowest_price*a.store)as sellmoney,(sum(d.lowest_price*a.store)-sum(d.goods_purchase_price*a.store))as differencemoney from zc_storehouse a "
				+ "left join zc_branch_info b on b.id=a.branch_id "
				+ "left join zc_zoning c on c.id=b.zoning_id "
				+ "left join zc_goods_master d on d.id=a.goodsfile_id "
				+ "left join zc_classify_info e on e.id=d.goods_class_id where 1=1 ";
		sql += conditions(obj);
		sql += " group by e.id,b.id,e.classify_name,c.street,b.branch_code,b.branch_name,d.serialnumber,d.goods_name";
		sql += " order by d.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and b.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and e.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or e.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getGoodsTotal(Page page, Object obj) throws Exception {
		String sql = "select a.createtime,g.id as GID,f.id as FID,h.id as HID,d.id as DID,e.street,d.branch_code,d.branch_name,b.serialnumber,b.goods_name, "
				+ "b.goods_specifications,f.classify_code,f.classify_name,c.changetype, "
				+ "a.changenumber,a.changenumber as number1,b.goods_price as average, "
				+ "b.goods_price as average1,(a.changenumber*b.goods_price) as changeamount,(a.changenumber*b.goods_price) as amount1 "
				+ "from ZC_CHANGE_ITEMS a left join zc_goods_master b on b.id=a.goodsfile_id "
				+ "left join ZC_STORECHANGE c on c.id=a.storechange_id left join zc_branch_info d on d.id=c.branch_id left join zc_zoning e on e.id=d.zoning_id left join zc_classify_info f on f.id=b.goods_brand_id left join zc_classify_info g on g.id=b.goods_class_id left join zc_provider_info h on h.id=b.goods_supplier_id where 1=1 ";
		sql += conditionsAdd(obj);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditionsAdd(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getStartTime())
				&& StringUtil.validate(warehouseQuery.getEndTime())) {
			conditions += " and a.CREATETIME between to_date('"
					+ warehouseQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ warehouseQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and d.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
				conditions += " and f.id='" + warehouseQuery.getBrand().getId()
						+ "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
			if (StringUtil.validate(warehouseQuery.getProviderInfo().getId())) {
				conditions += " and h.id='"
						+ warehouseQuery.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		return conditions;
	}

	/**
	 * 区域商品汇总
	 */
	@Override
	public DataGrid getRegionalGoods(Page page, Object obj) throws Exception {
		String sql = "select sum(store)as store,goods_name,serialnumber,goods_specifications,goods_unit,goods_purchase_price,goods_price,branchId,classId,brandId,"
				+ " providerId,county,sum((store*goods_purchase_price)) as chengben,SUM(CAST(store*goods_purchase_price/1.17 as decimal(10,2)))as nontaxcost,"
				+ " SUM((store*goods_price)) as amountprice,sum(((store*goods_price) - (store*goods_purchase_price))) as difference,areaname,classify_code,"
				+ " brandcode,classify_name,brandName,provider_nickname from"
				+ " (select a.store,b.goods_name,b.serialnumber,b.goods_specifications,b.goods_unit,b.goods_purchase_price,b.goods_price,"
				+ " c.id as branchId,e.id as classId,f.id as brandId,d.id as providerId,g.county,e.classify_name,f.classify_name as brandName,"
				+ " e.classify_code,f.classify_code as brandcode,h.areaname,d.provider_nickname"
				+ " from ZC_STOREHOUSE a"
				+ " left join zc_goods_master b on a.goodsfile_id = b.id"
				+ " left join zc_branch_info c on a.branch_id = c.id"
				+ " left join zc_provider_info d on b.goods_supplier_id = d.id"
				+ " left join zc_classify_info e on b.goods_class_id = e.id"
				+ " left join zc_classify_info f on b.goods_brand_id = f.id"
				+ " left join zc_zoning g on c.zoning_id = g.id"
				+ " left join zc_area h on g.county = h.id" + " where 1=1";
		sql += RegionalGoods(obj);
		sql += ")"
				+ " group by serialnumber,branchId,store,goods_name,goods_specifications,goods_unit,goods_purchase_price,goods_price,classId,"
				+ " brandId,providerId,county,areaname,brandcode,classify_code,classify_name,brandName,provider_nickname"
				+ " order by serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 区域商品汇总条件拼接
	 */
	private String RegionalGoods(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and c.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and e.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or e.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
				conditions += " and f.id='" + warehouseQuery.getBrand().getId()
						+ "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
			if (StringUtil.validate(warehouseQuery.getProviderInfo().getId())) {
				conditions += " and d.id='"
						+ warehouseQuery.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getStartTime())) {
			conditions += " and h.areaname = '" + warehouseQuery.getStartTime()
					+ "'";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and b.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		return conditions;
	}

	/**
	 * 出入库明细查询
	 */
	@Override
	public DataGrid getGoodsDetail(Page page, Object obj) throws Exception {
		String sql = "select g.id as GID,f.id as FID,h.id as HID,d.id as DID,c.changetype,e.street,d.branch_code,d.branch_name,b.serialnumber,b.goods_name,b.goods_specifications,f.classify_code,f.classify_name,a.createtime,b.goods_purchase_price, "
				+ " a.changenumber as inputnumber,(a.changenumber*b.goods_purchase_price)as inputmoney,CAST(a.changenumber*b.goods_purchase_price/1.17 as decimal(10,2))as withouttaxmoney,b.input_tax as inputtax,CAST(a.changenumber*b.goods_purchase_price/1.17*0.17 as decimal(10,2))as inputtaxmoney, "
				+ " a.changenumber as outputnumber,(a.changenumber*b.goods_purchase_price)as outputmoney,b.out_tax as outputtax,(a.changenumber*b.goods_price*0.17)as selltaxmoney,(a.changenumber*b.goods_price)as sellmoney,(a.changenumber*b.goods_price-a.changenumber*b.goods_purchase_price)as selldifference, "
				+ " c.storechange_number from ZC_CHANGE_ITEMS a "
				+ " left join zc_goods_master b on b.id=a.goodsfile_id "
				+ " left join ZC_STORECHANGE c on c.id=a.storechange_id "
				+ " left join zc_branch_info d on d.id=c.branch_id "
				+ " left join ZC_ZONING e on e.id=d.zoning_id "
				+ " left join zc_classify_info f on f.id=b.goods_brand_id "
				+ " left join zc_classify_info g on g.id=b.goods_class_id left join zc_provider_info h on h.id=b.goods_supplier_id where 1=1 ";
		sql += detailCondition(obj);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String detailCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getStartTime())
				&& StringUtil.validate(warehouseQuery.getEndTime())) {
			conditions += " and a.CREATETIME between to_date('"
					+ warehouseQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ warehouseQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and d.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
				conditions += " and f.id='" + warehouseQuery.getBrand().getId()
						+ "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
			if (StringUtil.validate(warehouseQuery.getProviderInfo().getId())) {
				conditions += " and h.id='"
						+ warehouseQuery.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and b.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getBigClassifyQueryGoods(Page page, Object obj)
			throws Exception {
		String sql = "select e.id as EID,b.id as BID,c.street,b.branch_code,b.branch_name,e.classify_code,e.classify_name,sum(a.store)as stock,sum(a.store*d.goods_purchase_price)as goodsmoney, "
				+ "CAST(sum(a.store*d.goods_purchase_price)/1.17 as decimal(10,2))as nonetaxmoney,sum(a.store*d.goods_price-a.store*d.goods_purchase_price)as differencemoney, "
				+ "sum(a.store*d.goods_price)as sellmoney from zc_storehouse a "
				+ "left join zc_branch_info b on b.id=a.branch_id "
				+ "left join zc_zoning c on c.id=b.zoning_id "
				+ "left join zc_goods_master d on d.id=a.goodsfile_id "
				+ "left join zc_classify_info e on e.id=d.goods_class_id where 1=1 ";
		sql += bigClassConditions(obj);
		sql += "group by c.street,b.branch_code,b.branch_name,e.classify_code,e.classify_name,e.id,b.id ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String bigClassConditions(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and b.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and e.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or e.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getBrandQueryGoods(Page page, Object obj) throws Exception {
		String sql = "select e.id as EID,b.id as BID,c.street,b.branch_code,b.branch_name,e.classify_code,e.classify_name,sum(a.store)as stock,sum(a.store*d.goods_purchase_price)as goodsmoney, "
				+ "CAST(sum(a.store*d.goods_purchase_price)/1.17 as decimal(10,2))as nonetaxmoney,sum(a.store*d.goods_price-a.store*d.goods_purchase_price)as differencemoney, "
				+ "sum(a.store*d.goods_price)as sellmoney from zc_storehouse a "
				+ "left join zc_branch_info b on b.id=a.branch_id "
				+ "left join zc_zoning c on c.id=b.zoning_id "
				+ "left join zc_goods_master d on d.id=a.goodsfile_id "
				+ "left join zc_classify_info e on e.id=d.goods_brand_id where 1=1 ";
		sql += brandConditions(obj);
		sql += "group by c.street,b.branch_code,b.branch_name,e.classify_code,e.classify_name,e.id,b.id ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String brandConditions(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and b.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
				conditions += " and e.id='" + warehouseQuery.getBrand().getId()
						+ "'";
			}
		}
		return conditions;
	}

	/**
	 * 出入库明细查询
	 */
	@Override
	public DataGrid getGoodsDistribution(Page page, Object obj)
			throws Exception {
		String sql = "select a.id as AID,b.id as BID,c.id as CID,d.id as DID,e.id as EID,f.id as FID,b.serialnumber,b.goods_name,b.goods_unit,b.goods_specifications,b.goods_purchase_price,b.goods_price,c.branch_name,sum(a.store)as stock "
				+ "from zc_storehouse a left join zc_goods_master b on b.id=a.goodsfile_id "
				+ "left join zc_branch_info c on c.id=a.branch_id "
				+ "left join zc_classify_info d on d.id=b.goods_class_id "
				+ "left join zc_classify_info e on e.id=b.goods_brand_id "
				+ "left join zc_provider_info f on f.id=b.goods_supplier_id where 1=1 ";
		sql += distributionCondition(obj);
		sql += "group by a.id,b.id,b.serialnumber,b.goods_name,b.goods_unit,b.goods_specifications,b.goods_purchase_price,b.goods_price,c.id,d.id,e.id,f.id,c.branch_name ";
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String distributionCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getBranch())) {
			if (StringUtil.validate(warehouseQuery.getBranch().getId())) {
				conditions += " and c.id='"
						+ warehouseQuery.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getClassify())) {
			if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
				conditions += " and d.id='"
						+ warehouseQuery.getClassify().getId()
						+ "' or d.parentid='"
						+ warehouseQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getBrand())) {
			if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
				conditions += " and e.id='" + warehouseQuery.getBrand().getId()
						+ "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getProviderInfo())) {
			if (StringUtil.validate(warehouseQuery.getProviderInfo().getId())) {
				conditions += " and f.id='"
						+ warehouseQuery.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and b.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		return conditions;
	}

	/**
	 * 成本记录汇总查询
	 */
	@Override
	public DataGrid getGoodsCost(Page page, Object obj) throws Exception {
		String sql = "select a.*,c.goods_name from zc_zccostrecord_items a "
				+ "left join zc_goods_master b on b.id=a.productgoods "
				+ "left join zc_goods_master c on c.id=a.materialgoods where 1=1 ";
		sql += goodsCostCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String goodsCostCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getStartTime())
				&& StringUtil.validate(warehouseQuery.getEndTime())) {
			conditions += " and a.CREATETIME between to_date('"
					+ warehouseQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ warehouseQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and goodsName like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		return conditions;
	}

	/**
	 * 成本记录汇总查询
	 */
	@Override
	public DataGrid getOrdersFloat(Page page, Object obj) throws Exception {

/*		String sql = "select a.storemoney,a.store,b.id as branch_id,c.serialnumber,b.branch_name as branchnames,c.goods_name,c.goods_specifications,c.goods_unit,c.goods_purchase_price,c.goods_price,"
				+ " d.classify_code,d.classify_name,d.id as classId,e.classify_name as brand_name,e.id as brand_id,f.provider_nickname,f.id as provider_id,"
				+ " g.branch_name,g.id as branch_total_id,g.branch_code,(c.goods_price - c.goods_purchase_price) as difference"
				+ " from zc_storehouse a "
				+ " left join zc_branch_info b on a.branch_id = b.id"
				+ " left join zc_goods_master c on a.goodsfile_id = c.id"
				+ " left join zc_classify_info d on c.goods_class_id = d.id"
				+ " left join zc_classify_info e on c.goods_brand_id = e.id"
				+ " left join zc_provider_info f on c.goods_supplier_id = f.id"
				+ " left join zc_branch_total g on b.branchtotal_id = g.id where a.store>0 ";
		sql += joinGoodsCondition(obj);
		sql += " order by c.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);*/
	
		String sql = "select e.classify_name,c.id as zoning_id,e.id as brand_id,a.id,a.goods_name,b.serialnumber,d.branch_name,d.id as total_id,a.createtime,a.weight,b.goods_price,b.goods_specifications "
				+ "from ZC_ORDERS_SORTE a left join zc_goods_master b on b.id=a.goods_id "
				+ "left join zc_zoning c on c.street=a.address "
				+ "left join zc_branch_total d on d.zoning_id=c.id "
	//			+ "left join zc_branch_total g on g.branch_code=a.address "
				+ "left join zc_classify_info e on b.goods_brand_id=e.id "
				+ "left join zc_classify_info f on b.goods_class_id=f.id "
				+ "where 1=1 ";
		
		sql += ordersFloatCondition(obj);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String ordersFloatCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getStartTime())) {
			conditions += " and a.CREATETIME >= to_date('"
					+ warehouseQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') ";
		}
		if (StringUtil.validate(warehouseQuery.getEndTime())) {
			conditions += " and a.CREATETIME <= to_date('"
					+ warehouseQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and a.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getBranchTotal().getBranch_code())) {
			conditions += " and d.branch_code='"+warehouseQuery.getBranchTotal().getBranch_code()+"'";
		}
		/*if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and e.classify_name like '%"
					+ warehouseQuery.getClassify().getClassify_name() + "%'";
		}*/		
		
		if (StringUtil.validate(warehouseQuery.getBrand().getId())) {
			conditions += " and e.id='"+ warehouseQuery.getBrand().getId() + "'";
		}
		if (StringUtil.validate(warehouseQuery.getClassify().getId())) {
			conditions += " and f.id='"
					+ warehouseQuery.getClassify().getId()
					+ "' or f.parentid='"
					+ warehouseQuery.getClassify().getId() + "'";
		}
		
		return conditions;
	}

	/**
	 * 成本记录汇总查询
	 */
	@Override
	public DataGrid getBranchSorte(Page page, Object obj) throws Exception {
		String sql = "select a.goods_name,b.serialnumber,d.branch_name,sum(a.weight)as weight,b.goods_price,b.goods_specifications,"
				+ "e.createtime,(f.costingmoney/f.materialweight)as costingmoney,b.goods_price*sum(a.weight)as sellmoney,f.costingmoney/f.materialweight*sum(a.weight)as costtotal,(b.goods_price*sum(a.weight)-f.costingmoney/f.materialweight*sum(a.weight))as goodsvalue "
				+ "from ZC_ORDERS_SORTE a "
				+ "left join zc_goods_master b on b.id=a.goods_id "
				+ "left join zc_zoning c on c.street=a.address "
				+ "left join zc_branch_total d on d.zoning_id=c.id "
				+ "left join zc_sorte e on e.id=a.sorteid "
				+ "left join zc_zccostrecord_items f on f.productgoods=b.id "
				+ "where to_char(e.createtime,'yyyy-mm-dd')=to_char(f.processtime,'yyyy-mm-dd') ";
		sql += branchSorteCondition(obj);
		sql += "group by a.goods_name,b.serialnumber,d.branch_name,b.goods_price,b.goods_specifications,e.createtime,(f.costingmoney/f.materialweight) "
				+ "order by b.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String branchSorteCondition(Object obj) {
		WarehouseQuery warehouseQuery = (WarehouseQuery) obj;
		String conditions = "";
		if (StringUtil.validate(warehouseQuery.getStartTime())) {
			conditions += " and e.CREATETIME >= to_date('"
					+ warehouseQuery.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') ";
		}
		if (StringUtil.validate(warehouseQuery.getEndTime())) {
			conditions += " and e.CREATETIME <= to_date('"
					+ warehouseQuery.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(warehouseQuery.getGoodsName())) {
			conditions += " and a.goods_name like '%"
					+ warehouseQuery.getGoodsName() + "%'";
		}
		if (StringUtil.validate(warehouseQuery.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ warehouseQuery.getSerialNumber() + "%'";
		}
		return conditions;
	}
}
