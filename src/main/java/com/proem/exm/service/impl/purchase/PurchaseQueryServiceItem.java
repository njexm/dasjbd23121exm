package com.proem.exm.service.impl.purchase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseOrderDao;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.entity.purchase.PurchaseSearch;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseQueryService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购查询实现类
 * 
 * @author songcj 2015年12月14日 上午10:41:44
 */
@Service("purchaseQueryService")
public class PurchaseQueryServiceItem extends BaseServiceImpl implements
		PurchaseQueryService {
	@Autowired
	PurchaseOrderDao purchaseOrderDao;

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj,
			String startDate, String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id)
			throws Exception {
		String sql = "select a.createtime,h.id as HID,g.id as CID,g.classify_name,g.classify_type,a.GOODSNAME,a.SPECIFICATIONS,a.UNIT,a.SERIALNUMBER,b.actual_num,b.goodsmoney,c.Returnnum,(c.goodsprice*c.returnnum) as RETURNMONEY,(b.actual_num-c.Returnnum)as ACTUALPURCHASE,c.goodsprice*(b.actual_num-c.Returnnum)as ACTUALMONEY,(b.actual_num*a.goodsprice)as ORDERMONEY,d.lowest_price,(d.lowest_price-c.goodsprice)as DIFFERENCEPRICE,f.id as PID,f.provider_nickname from ZC_PURCHASE_ORDER_ITEMS a  "
				+ "left join zc_purchase_receive_item b on b.ordergoods_id=a.id "
				+ "left join ZC_PURCHASE_RETURN_ITEMS c on c.purchasereceiveitem_id=b.id "
				+ "left join zc_goods_master d on a.goodsfile_id=d.id "
				+ "left join ZC_PURCHASE_ORDER e on e.id=a.purchaseid "
				+ "left join zc_provider_info f on f.id=e.provider_id "
				+ "left join zc_classify_info g on g.id=d.goods_class_id "
				+ "left join zc_branch_info h on h.id=e.branch_id where 1=1 ";
		sql += joinCondition(obj, startDate, endDate, provider, classify,
				serialNumber, branchId, goods_brand_id);
		sql += " order by a.UPDATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj, String startDate, String endDate,
			String provider, String classify, String serialNumber,
			String branchId, String goods_brand_id) {
		String conditions = "";
		if (StringUtil.validate(startDate) && StringUtil.validate(endDate)) {
			conditions += " and a.CREATETIME between to_date('" + startDate
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('" + endDate
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(provider)) {
			conditions += " and f.id='" + provider + "'";
		}
		if (StringUtil.validate(classify)) {
			conditions += " and g.classify_type='1' and g.id='" + classify
					+ "' or g.parentid='" + classify + "'";
		}
		if (StringUtil.validate(goods_brand_id)) {
			conditions += " and g.classify_type='2' and g.id='"
					+ goods_brand_id + "'";
		}
		if (StringUtil.validate(serialNumber)) {
			conditions += " and a.SERIALNUMBER like'%" + serialNumber + "%'";
		}
		if (StringUtil.validate(branchId)) {
			conditions += " and h.id='" + branchId + "'";
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedProviderGoodsGridObj(Page page, Object obj,
			String startDate, String endDate, String provider, String classify,
			String branchId) throws Exception {
		String sql = "select provider_nickname,sum(actual_num)as ACTUAL_NUM, "
				+ "sum(goodsmoney)as GOODSMONEY,sum(returnnum)as RETURNSUM, "
				+ "sum(returnnum*goodsprice)as RETURNMONEY,(sum(actual_num)-sum(returnnum))as ACTPURCHASE, "
				+ "(sum(goodsmoney)-sum(returnnum*goodsprice))as ACTMONEY, "
				+ "(sum(SELLMONEY)-sum(goodsmoney))as SELL, "
				+ "sum(SELLMONEY)as SELLGOODS,PROVIDERID from "
				+ "(select a.provider_nickname,h.id as HID,h.CLASSIFY_TYPE as CLTYPE,g.id as GID,a.id as PROVIDERID,d.CREATETIME as CREATETIME,d.actual_num,d.goodsmoney,e.returnnum,e.goodsprice, "
				+ "(f.lowest_price*d.actual_num)as SELLMONEY "
				+ "from zc_provider_info a "
				+ "left join zc_purchase_order b on b.provider_id=a.id "
				+ "left join zc_purchase_order_items c on c.purchaseid=b.id "
				+ "left join zc_purchase_receive_item d on d.ordergoods_id=c.id "
				+ "left join zc_purchase_return_items e on e.purchasereceiveitem_id=d.id "
				+ "left join zc_goods_master f on f.id=c.goodsfile_id "
				+ "left join zc_branch_info g on g.id=b.BRANCH_ID "
				+ "left join zc_classify_info h on h.id=f.goods_class_id "
				+ "where 1=1 ";
		sql += conditions(obj, startDate, endDate, classify, branchId);
		sql += ") where 1=1 ";
		sql += joinConditionsss(obj, provider);
		sql += "group by provider_nickname,PROVIDERID";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 类别页面数据来源
	 */
	@Override
	public DataGrid getPagedDataGridClass(Page page, Object obj)
			throws Exception {
		String sql = "select provider_id,class_id,createtime,classify_code,classify_name,sum(actual_num*goods_purchase_price) as receive_money,sum(actual_num) as actual_num,sum(returnnum*goods_purchase_price) as return_money,sum(returnnum) as returnnum,sum(actual_num - returnnum) as nums,"
				+ " sum((actual_num - returnnum)*goods_purchase_price)as actual_money,sum(actual_num*(goods_price - goods_purchase_price)) as difference,sum(actual_num*goods_price)as sell_money from "
				+ " (select a.createtime,a.actual_num,a.goodsmoney,a.id,d.returnnum,b.audit_status,d.goodsfile_id,e.goods_price,e.goods_purchase_price,g.classify_code,g.classify_name,c.statue,g.id as class_id,h.id as provider_id from ZC_PURCHASE_RECEIVE_ITEM a"
				+ " left join ZC_PURCHASE_RECEIVE b on a.purchase_receive_id = b.id"
				+ " left join Zc_Purchase_Return_Items d on d.purchasereceiveitem_id = a.id"
				+ " left join Zc_Purchase_Return c on c.id = d.returnid"
				+ " left join zc_purchase_order_items f on f.id = a.ordergoods_id"
				+ " left join zc_goods_master e on e.id = f.goodsfile_id"
				+ " left join zc_classify_info g on e.goods_class_id = g.id"
				+ " left join zc_provider_info h on e.goods_supplier_id = h.id"
				+ " where audit_status = 2 ";
		sql += joinClassCondition(obj);
		sql += " )group by classify_code,createtime,classify_name,class_id,provider_id";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 类别查询条件拼接
	 */
	private String joinClassCondition(Object obj) {
		PurchaseSearch item = (PurchaseSearch) obj;
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String conditions = "";
		if (StringUtil.validate(item.getStartTime())) {
			// Date date1 = item.getStartTime();
			// String starTime=sdf.format(date1);
			conditions += " and a.createTime >= TO_DATE('"
					+ item.getStartTime() + "','YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(item.getEndTime())) {
			// Date date2 = item.getUpdateTime();
			// String endTime=sdf.format(date2);
			conditions += " and a.createTime <= TO_DATE('" + item.getEndTime()
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(item.getLeiBie())) {
			if (StringUtil.validate(item.getLeiBie().getId())) {
				conditions += " and g.id ='" + item.getLeiBie().getId()
						+ "' or g.parentid='" + item.getLeiBie().getId() + "'";
			}
		}
		if (StringUtil.validate(item.getProviderInfo())) {
			if (StringUtil.validate(item.getProviderInfo().getId())) {
				conditions += " and provider_id ='"
						+ item.getProviderInfo().getId() + "'";
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
	private String conditions(Object obj, String startDate, String endDate,
			String classify, String branchId) {
		String conditions = "";
		if (StringUtil.validate(startDate) && StringUtil.validate(endDate)) {
			conditions += " and d.CREATETIME between to_date('" + startDate
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('" + endDate
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(classify)) {
			conditions += " and h.CLASSIFY_TYPE='1' and h.id='" + classify
					+ "' or h.parentid='" + classify + "'";
		}
		if (StringUtil.validate(branchId)) {
			conditions += " and g.id='" + branchId + "'";
		}
		return conditions;
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinConditionsss(Object obj, String provider) {
		String conditions = "";
		if (StringUtil.validate(provider)) {
			conditions += " and PROVIDERID='" + provider + "'";
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedReceiptsGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select purchase_state,purchase_odd,PID,branch_name,purchase_money,createtime,purchase_purchaseman,checkdate,checkman,BID,provider_nickname from ";
		sql += "(select a.purchase_state,a.purchase_odd,c.id as PID,c.branch_name,a.purchase_money,a.createtime,a.purchase_purchaseman,a.checkdate,a.checkman,b.id as BID,b.provider_nickname "
				+ "from zc_purchase_order a left join zc_provider_info b on b.id=a.provider_id left join zc_branch_info c on c.id=a.branch_id "
				+ "union select d.audit_status,d.purchase_receive_odd,f.id as PID,f.branch_name,d.purchase_money,d.createtime,d.purchase_man,d.check_date,d.check_man,e.id as BID,e.provider_nickname "
				+ "from zc_purchase_receive d left join zc_provider_info e on e.id=d.provider_id left join zc_branch_info f on f.id=d.branch_id "
				+ "union select g.statue,g.odd,h.id as PID,h.branch_name,g.totalmoney,g.createtime,g.returnman,g.checkdate,g.checkman,i.id as BID,i.provider_nickname "
				+ "from zc_purchase_return g left join zc_branch_info h on h.id=g.branch_id left join zc_provider_info i on g.providerinfo_id=i.id)where 1=1 ";
		sql += conditions(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		PurchaseSearch purchaseSearch = (PurchaseSearch) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseSearch.getStartTime())
				&& StringUtil.validate(purchaseSearch.getEndTime())) {
			conditions += " and createtime between to_date('"
					+ purchaseSearch.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ purchaseSearch.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(purchaseSearch.getProviderInfo())) {
			if (StringUtil.validate(purchaseSearch.getProviderInfo().getId())) {
				conditions += " and BID='"
						+ purchaseSearch.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseSearch.getBranch())) {
			if (StringUtil.validate(purchaseSearch.getBranch().getId())) {
				conditions += " and PID='" + purchaseSearch.getBranch().getId()
						+ "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getHistoryPriceDataGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select c.id as CID,f.id as FID,a.createtime,f.branch_code,f.branch_name,c.provider_nickname,b.serialnumber,b.goods_name,b.goods_unit,b.goods_specifications,a.goodsprice,b.goods_purchase_price "
				+ "from zc_purchase_order_items a "
				+ "left join zc_goods_master b on b.id=a.goodsfile_id "
				+ "left join zc_provider_info c on c.id=b.goods_supplier_id "
				+ "left join zc_purchase_order e on e.id=a.purchaseid "
				+ "left join zc_branch_info f on f.id=e.branch_id where 1=1 ";
		sql += priceConditions(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String priceConditions(Object obj) {
		PurchaseSearch purchaseSearch = (PurchaseSearch) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseSearch.getStartTime())
				&& StringUtil.validate(purchaseSearch.getEndTime())) {
			conditions += " and a.createtime between to_date('"
					+ purchaseSearch.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ purchaseSearch.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(purchaseSearch.getBranch())) {
			if (StringUtil.validate(purchaseSearch.getBranch().getId())) {
				conditions += " and f.id='"
						+ purchaseSearch.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseSearch.getProviderInfo())) {
			if (StringUtil.validate(purchaseSearch.getProviderInfo().getId())) {
				conditions += " and c.id='"
						+ purchaseSearch.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseSearch.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ purchaseSearch.getSerialNumber() + "%'";
		}
		return conditions;
	}

	/**
	 * 类别页面数据来源
	 */
	@Override
	public DataGrid getBigClassPage(Page page, Object obj) throws Exception {
		String sql = "select a.createtime,e.id as EID,g.id as GID,c.id as CID,c.classify_code,c.classify_name,sum(a.actual_num)as actnum,sum(a.goodsmoney)as goodsmoney,sum(d.returnnum)as returnnum,sum(d.totalmoney)as totalmoney, "
				+ "(sum(a.actual_num)-sum(d.returnnum))as actpurchase,(sum(a.goodsmoney)-sum(d.totalmoney))as actmoney, "
				+ "sum(a.actual_num*b.goods_price)as sellmoney,(sum(a.actual_num*b.goods_price)-sum(a.actual_num*b.goods_purchase_price))as selldifference, "
				+ "CAST(sum(a.goodsmoney)/1.17*0.17 as decimal(10,2))as taxmoney "
				+ "from zc_purchase_receive_item a left join zc_goods_master b on b.id=a.goodsfile_id "
				+ "left join zc_classify_info c on c.id=b.goods_class_id "
				+ "left join zc_purchase_return_items d on a.id=d.purchasereceiveitem_id "
				+ "left join zc_provider_info e on e.id=b.goods_supplier_id left join zc_purchase_receive f on f.id=a.purchase_receive_id left join zc_branch_info g on g.id=f.branch_id where 1=1 ";
		sql += bigClassCondition(obj);
		sql += "group by a.createtime,e.id,c.id,g.id,c.classify_code,c.classify_name ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 类别查询条件拼接
	 */
	private String bigClassCondition(Object obj) {
		PurchaseSearch purchaseSearch = (PurchaseSearch) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseSearch.getStartTime())
				&& StringUtil.validate(purchaseSearch.getEndTime())) {
			conditions += " and a.createtime between to_date('"
					+ purchaseSearch.getStartTime()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ purchaseSearch.getEndTime()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(purchaseSearch.getBranch())) {
			if (StringUtil.validate(purchaseSearch.getBranch().getId())) {
				conditions += " and g.id='"
						+ purchaseSearch.getBranch().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseSearch.getProviderInfo())) {
			if (StringUtil.validate(purchaseSearch.getProviderInfo().getId())) {
				conditions += " and e.id='"
						+ purchaseSearch.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseSearch.getLeiBie())) {
			if (StringUtil.validate(purchaseSearch.getLeiBie().getId())) {
				conditions += " and c.id='"
						+ purchaseSearch.getLeiBie().getId()
						+ "' or c.parentid='"
						+ purchaseSearch.getLeiBie().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDetailObj(Page page, Object obj, String startDate,
			String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id)
			throws Exception {
		String sql = "select a.updatetime,g.classify_code as classcode,g.classify_name as classname,i.classify_code as brandcode,i.classify_name as brandname,h.BRANCH_CODE,h.BRANCH_NAME,e.PURCHASE_ODD,a.createtime,h.id as HID,g.id as CID,g.classify_name,g.classify_type,a.GOODSNAME,a.SPECIFICATIONS,a.UNIT,a.SERIALNUMBER,b.actual_num,b.goodsmoney,c.Returnnum,(c.goodsprice*c.returnnum) as RETURNMONEY,(b.actual_num-c.Returnnum)as ACTUALPURCHASE,c.goodsprice*(b.actual_num-c.Returnnum)as ACTUALMONEY,(b.actual_num*a.goodsprice)as ORDERMONEY,d.lowest_price,(d.lowest_price-c.goodsprice)as DIFFERENCEPRICE,f.id as PID,f.provider_nickname from ZC_PURCHASE_ORDER_ITEMS a  "
				+ "left join zc_purchase_receive_item b on b.ordergoods_id=a.id "
				+ "left join ZC_PURCHASE_RETURN_ITEMS c on c.purchaseitem_id=a.id "
				+ "left join zc_goods_master d on a.goodsfile_id=d.id "
				+ "left join ZC_PURCHASE_ORDER e on e.id=a.purchaseid "
				+ "left join zc_provider_info f on f.id=e.provider_id "
				+ "left join zc_classify_info g on g.id=d.goods_class_id left join zc_classify_info i on i.id=d.goods_brand_id "
				+ "left join zc_branch_info h on h.id=e.branch_id where 1=1 ";
		sql += detailCondition(obj, startDate, endDate, provider, classify,
				serialNumber, branchId, goods_brand_id);
		sql += " order by a.UPDATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String detailCondition(Object obj, String startDate,
			String endDate, String provider, String classify,
			String serialNumber, String branchId, String goods_brand_id) {
		String conditions = "";
		if (StringUtil.validate(startDate) && StringUtil.validate(endDate)) {
			conditions += " and a.CREATETIME between to_date('" + startDate
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('" + endDate
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(provider)) {
			conditions += " and f.id='" + provider + "'";
		}
		if (StringUtil.validate(classify)) {
			conditions += " and g.classify_type='1' and g.id='" + classify
					+ "' or g.parentid='" + classify + "'";
		}
		if (StringUtil.validate(goods_brand_id)) {
			conditions += " and g.classify_type='2' and g.id='"
					+ goods_brand_id + "'";
		}
		if (StringUtil.validate(serialNumber)) {
			conditions += " and a.SERIALNUMBER like'%" + serialNumber + "%'";
		}
		if (StringUtil.validate(branchId)) {
			conditions += " and h.id='" + branchId + "'";
		}
		return conditions;
	}

}
