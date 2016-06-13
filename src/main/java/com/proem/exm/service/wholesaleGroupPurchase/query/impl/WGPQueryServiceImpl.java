package com.proem.exm.service.wholesaleGroupPurchase.query.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.query.WGPQueryDao;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleQuery;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.query.WGPQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 团购销售查询
 * 
 * @author ZuoYM
 * @com proem
 */
@Service("WGPQueryService")
public class WGPQueryServiceImpl extends BaseServiceImpl implements
		WGPQueryService {
	@Autowired
	private WGPQueryDao wGPQueryDao;

	/**
	 * 分页查询数据（商品汇总）
	 */
	@Override
	public DataGrid getGoodsQuery(Page page, Object obj)
			throws Exception {
		String sql="select g.classify_name,g.classify_code,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount,sum(a.rate_amount) as SELLRATEAMOUNT ,sum(c.rateamount) as RETURNRATEAMOUNT ,sum(a.amount) as SELLNULLRATEAMOUNT,sum(c.returnamount) as RETURNNULLRATEAMOUNT "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1 ";
		sql += GoodsCondition(obj);
		sql += "  group by g.classify_name,g.classify_code,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER "
				+ " order by d.SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（商品汇总）
	 * @param obj
	 * @return
	 */
	private String GoodsCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（类别汇总）
	 */
	@Override
	public DataGrid getClassifyQueryGoods(Page page, Object obj)
			throws Exception {
		String sql="select g.classify_name,g.classify_code,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount,sum(a.rate_amount) as SELLRATEAMOUNT ,sum(c.rateamount) as RETURNRATEAMOUNT ,sum(a.amount) as SELLNULLRATEAMOUNT,sum(c.returnamount) as RETURNNULLRATEAMOUNT "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1 ";
		sql += ClassifyConditions(obj);
		sql += "  group by g.classify_name,g.classify_code "
				+ " order by g.classify_code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（类别汇总）
	 * @param obj
	 * @return
	 */
	private String ClassifyConditions(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（批发明细）
	 */
	@Override
	public DataGrid getDetailQuery(Page page, Object obj) throws Exception {
		String sql = "select e.statue,e.wholeSellOdd,a.order_price,a.rate,a.createtime,h.id as HID,h.BRANCH_NAME,g.id as CID,g.classify_name,g.classify_code,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER, f.id as PID,f.code as CUST_CODE,f.name as CUST_NAME,a.nums,(a.order_price*a.nums)as sellAmount,c.returnNums,(c.returnnums*c.orderprice)as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a  "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1  ";

		sql += DetailCondition(obj);
		sql += " order by e.wholeSellOdd,f.code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（批发明细）
	 * @param obj
	 * @return
	 */
	private String DetailCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（客户汇总）
	 */
	@Override
	public DataGrid getCustomerQuery(Page page, Object obj) throws Exception {
		String sql="select f.code,f.name,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount,sum(a.rate_amount) as SELLRATEAMOUNT ,sum(c.rateamount) as RETURNRATEAMOUNT ,sum(a.amount) as SELLNULLRATEAMOUNT,sum(c.returnamount) as RETURNNULLRATEAMOUNT "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1 ";
		sql += CustomerCondition(obj);
		sql += "  group by f.code,f.name "
				+ " order by f.code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（客户汇总）
	 * @param obj
	 * @return
	 */
	private String CustomerCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（客户-商品销售汇总）
	 */
	@Override
	public DataGrid getCustAndGoodsQuery(Page page, Object obj) throws Exception {
		String sql="select f.code,f.name,g.classify_name,g.classify_code,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER,d.goods_price,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount,sum(a.rate_amount) as SELLRATEAMOUNT ,sum(c.rateamount) as RETURNRATEAMOUNT ,sum(a.amount) as SELLNULLRATEAMOUNT,sum(c.returnamount) as RETURNNULLRATEAMOUNT "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1 ";
		sql += CustAndGoodsCondition(obj);
		sql += "  group by f.code,f.name,g.classify_name,g.classify_code,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER,d.goods_price "
				+ " order by f.code,d.SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（客户-商品销售汇总）
	 * @param obj
	 * @return
	 */
	private String CustAndGoodsCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（业务员-客户汇总）
	 */
	@Override
	public DataGrid getCustAndCreQuery(Page page, Object obj) throws Exception {
		String sql=" select USERNAME,code,name,classify_name,sum(nums) as sellNums,sum(order_price*nums)as sellAmount,sum(returnNums) as RETURALLNNUMS ,sum(returnnums*order_price)as returnAmount "
				+ " from "
				+ " ( "
				+ " select i.name as USERNAME,f.code,f.name,g.classify_name,a.nums,a.order_price, c.giftnums as returnNums"
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join CTP_USER i on i.id=e.createMan_id  "
				+ " where 1=1 ";
		sql += CustAndCreCondition(obj);
		sql += " union all"
				+ " select i.name as USERNAME,f.code,f.name,g.classify_name,c.giftnums,c.orderprice as returnorder,c.returnNums"
				+ " from  ZC_WGPURCHASE_RETURN_ITEM c"
				+ " left join zc_goods_master d on d.id=c.goodsFile_ID "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join ZC_WHOLE_SELL_ITEMS a on c.wholeSellGoodsItems_id=a.id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join CTP_USER i on i.id=e.createMan_id  "
				+ " where 1=1 ";
		sql += CustAndCreCondition(obj);
		sql += " ) "
				+ " group by USERNAME,code,name,classify_name,returnNums "
				+ " order by sellNums,USERNAME,code,classify_name asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（业务员-客户汇总）
	 * @param obj
	 * @return
	 */
	private String CustAndCreCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（品牌销售汇总）
	 */
	@Override
	public DataGrid getBrandQuery(Page page, Object obj) throws Exception {
		String sql="select h.branch_code,h.branch_name,i.classify_name,i.classify_code,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += BrandCondition(obj);
		sql += "  group by h.branch_code,h.branch_name,i.classify_name,i.classify_code "
				+ " order by h.branch_code,i.classify_code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（品牌销售汇总）
	 * @param obj
	 * @return
	 */
	private String BrandCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBrand())) {
			if (StringUtil.validate(wholesaleQuery.getBrand().getId())) {
				conditions += " and i.id='"
						+ wholesaleQuery.getBrand().getId()
								+ "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（销售单据汇总）
	 */
	@Override
	public DataGrid getDocumentQuery(Page page, Object obj) throws Exception {
		String sql = "select i.name as USERNAME,j.name as CHECKNAME,h.branch_code,h.branch_name,e.statue,e.wholeSellOdd,e.createtime,e.checkDate,f.code as CUST_CODE,f.name as CUST_NAME,sum(a.order_price*a.nums)as sellAmount,sum(c.returnnums*c.orderprice)as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a  "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join CTP_USER i on i.id=e.createMan_id "
				+ " left join CTP_USER j on j.id=e.checkMan_id "
				+ " where 1=1  ";
		sql += DocumentCondition(obj);
		sql += "  group by i.name,j.name,h.branch_code,h.branch_name,e.statue,e.wholeSellOdd,e.createtime,e.checkDate,f.code,f.name "
				+ " order by e.statue,e.wholeSellOdd,h.branch_code,f.code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（销售单据汇总）
	 * @param obj
	 * @return
	 */
	private String DocumentCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（客户-商品销售明细）
	 */
	@Override
	public DataGrid getCAGDetailQuery(Page page, Object obj) throws Exception {
		String sql="select e.wholeSellOdd,i.CLASSIFY_CODE as CLASSIFY_CODE_B,i.CLASSIFY_NAME as CLASSIFY_NAME_B,f.area,f.code,f.name,h.branch_code,h.branch_name,g.classify_name,a.ORDER_PRICE as SELL_PRIACE,g.classify_code,d.goods_name,d.goods_specifications,d.SERIALNUMBER,d.goods_price,e.createtime,a.REMARK,e.REMARK as ITEM_REMARK,a.nums as sellNums,a.order_price*a.nums as sellAmount,c.returnNums as RETURALLNNUMS ,c.returnnums*c.orderprice as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += CAGDetailCondition(obj);
		sql += " order by e.wholeSellOdd,f.area,f.code,h.branch_code,d.SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（客户-商品销售明细）
	 * @param obj
	 * @return
	 */
	private String CAGDetailCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}
	
	/**
	 * 分页查询数据（区域销售汇总）
	 */
	@Override
	public DataGrid getAreaQueryGoods(Page page, Object obj) throws Exception {
		String sql="select f.area,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " where 1=1 ";
		sql += AreaConditions(obj);
		sql += "  group by f.area "
				+ " order by f.area asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（区域销售汇总）
	 * @param obj
	 * @return
	 */
	private String AreaConditions(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getBranchArea())) {
			if (StringUtil.validate(wholesaleQuery.getBranchArea().getId())) {
				conditions += " and f.area='"
						+ wholesaleQuery.getBranchArea().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（商品毛利汇总）
	 */ 
	@Override
	public DataGrid getGProfitQuery(Page page, Object obj) throws Exception { //
		String sql="select g.classify_name,g.classify_code,i.CLASSIFY_CODE as CLASSIFY_CODE_B,i.CLASSIFY_NAME as CLASSIFY_NAME_B,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += GProfitCondition(obj);
		sql += "  group by g.classify_name,g.classify_code,i.CLASSIFY_CODE ,i.CLASSIFY_NAME,d.goods_name,d.goods_specifications,d.goods_unit,d.SERIALNUMBER "
				+ " order by d.SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（商品毛利汇总）
	 * @param obj
	 * @return
	 */
	private String GProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（类别毛利汇总）
	 */
	@Override
	public DataGrid getCProfitQuery(Page page, Object obj) throws Exception {
		String sql="select g.classify_name,g.classify_code,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += CProfitCondition(obj);
		sql += "  group by g.classify_name,g.classify_code "
				+ " order by g.classify_code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（类别毛利汇总）
	 * @param obj
	 * @return
	 */
	private String CProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（品牌毛利汇总）
	 */
	@Override
	public DataGrid getBProfitQuery(Page page, Object obj) throws Exception {
		String sql="select i.CLASSIFY_CODE as CLASSIFY_CODE_B,i.CLASSIFY_NAME as CLASSIFY_NAME_B,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
//		String sql="select h.branch_code,h.branch_name,g.classify_name,g.classify_code,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(c.returnNums) as RETURALLNNUMS ,sum(c.returnnums*c.orderprice)as returnAmount "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += BProfitCondition(obj);
		sql += "  group by i.CLASSIFY_CODE,i.CLASSIFY_NAME "
				+ " order by i.CLASSIFY_CODE asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（品牌毛利汇总）
	 * @param obj
	 * @return
	 */
	private String BProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBrand())) {
			if (StringUtil.validate(wholesaleQuery.getBrand().getId())) {
				conditions += " and i.id='"
						+ wholesaleQuery.getBrand().getId()
								+ "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（客户毛利汇总）
	 */
	@Override
	public DataGrid getCustProfitQuery(Page page, Object obj) throws Exception {
		String sql="select f.code,f.name,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_brand_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += CustProfitCondition(obj);
		sql += "  group by f.code,f.name "
				+ " order by f.code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（客户毛利汇总）
	 * @param obj
	 * @return
	 */
	private String CustProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（分店/仓库毛利汇总）
	 */
	@Override
	public DataGrid getBranchProfitQuery(Page page, Object obj) throws Exception {
		String sql="select h.branch_code,h.branch_name,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " where 1=1 ";
		sql += BranchProfitCondition(obj);
		sql += "  group by h.branch_code,h.branch_name "
				+ " order by h.branch_code asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（分店/仓库毛利汇总）
	 * @param obj
	 * @return
	 */
	private String BranchProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（区域毛利汇总）
	 */
	@Override
	public DataGrid getAreaProfitQuery(Page page, Object obj) throws Exception {
		String sql="select f.area,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " left join CTP_USER j on j.id=e.createMan_id "
				+ " where 1=1 ";
		sql += AreaProfitCondition(obj);
		sql += "  group by f.area "
				+ " order by f.area asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（区域毛利汇总）
	 * @param obj
	 * @return
	 */
	private String AreaProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（业务员毛利汇总）
	 */
	@Override
	public DataGrid getCreProfitQuery(Page page, Object obj) throws Exception {
		String sql="select j.name as USERNAME,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " left join CTP_USER j on j.id=e.createMan_id "
				+ " where 1=1 ";
		sql += CreProfitCondition(obj);
		sql += "  group by j.name "
				+ " order by j.name asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（业务员毛利汇总）
	 * @param obj
	 * @return
	 */
	private String CreProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（单据毛利汇总）
	 */
	@Override
	public DataGrid getDocuProfitQuery(Page page, Object obj) throws Exception {
		String sql="select h.branch_code,h.branch_name,e.createtime,e.wholeSellOdd,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " left join CTP_USER j on j.id=e.createMan_id "
				+ " where 1=1 ";
		sql += DocuProfitCondition(obj);
		sql += "  group by h.branch_code,h.branch_name,e.createtime,e.wholeSellOdd "
				+ " order by h.branch_code,e.createtime,e.wholeSellOdd asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（单据毛利汇总）
	 * @param obj
	 * @return
	 */
	private String DocuProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据（日毛利汇总）
	 */
	@Override
	public DataGrid getDateProfitQuery(Page page, Object obj) throws Exception {
		String sql="select to_char(e.createtime,'yyyy-mm-dd') as createtime,sum(a.nums) as sellNums,sum(a.order_price*a.nums)as sellAmount,sum(d.goods_purchase_price*a.nums) as COSTAMOUNT, sum((a.order_price-d.goods_purchase_price)*a.nums) as PROFITAMOUNT  "
				+ " from  ZC_WHOLE_SELL_ITEMS a "
				+ " left join ZC_WGPURCHASE_RETURN_ITEM c on c.wholeSellGoodsItems_id=a.id "
				+ " left join zc_goods_master d on d.id=a.goods_file_id "
				+ " left join ZC_WHOLE_SELL e on e.id=a.WHOLESELL_ID "
				+ " left join zc_customer_info f on f.id=e.customerinfo_id "
				+ " left join zc_classify_info g on g.id=d.goods_class_id "
				+ " left join zc_branch_info h on h.id=e.branch_id "
				+ " left join zc_classify_info i on i.id=d.goods_brand_id "
				+ " left join CTP_USER j on j.id=e.createMan_id "
				+ " where 1=1 ";
		sql += DateProfitCondition(obj);
		sql += "  group by to_char(e.createtime,'yyyy-mm-dd') "
				+ " order by to_char(e.createtime,'yyyy-mm-dd') asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPQueryDao.getObjPagedList(page);
		Long total = wGPQueryDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接（日毛利汇总）
	 * @param obj
	 * @return
	 */
	private String DateProfitCondition(Object obj) {
		WholesaleQuery wholesaleQuery = (WholesaleQuery) obj;
		String conditions = "";
		if (StringUtil.validate(wholesaleQuery.getSerialNumber())) {
			conditions += " and d.SERIALNUMBER like '%"
					+ wholesaleQuery.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(wholesaleQuery.getClassify())) {
			if (StringUtil.validate(wholesaleQuery.getClassify().getId())) {
				conditions += " and g.id='"
						+ wholesaleQuery.getClassify().getId()
						+ "' or g.parentid='"
						+ wholesaleQuery.getClassify().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getCustomerInfo())) {
			if (StringUtil.validate(wholesaleQuery.getCustomerInfo().getId())) {
				conditions += " and f.id='"
						+ wholesaleQuery.getCustomerInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(wholesaleQuery.getBranch())) {
			if (StringUtil.validate(wholesaleQuery.getBranch().getId())) {
				conditions += " and h.id='"
						+ wholesaleQuery.getBranch().getId() + "'";
			}
		}
		return conditions;
	}
	
}
