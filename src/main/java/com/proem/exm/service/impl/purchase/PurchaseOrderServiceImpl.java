package com.proem.exm.service.impl.purchase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseOrderDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrder;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseOrderService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购订单业务管理实现类
 * 
 * @author Administrator
 * 
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends BaseServiceImpl implements
		PurchaseOrderService {
	@Autowired
	PurchaseOrderDao purchaseOrderDao;

	/**
	 * 修改已经存在的数据 - 单纯单表操作
	 */
	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj, String state)
			throws Exception {
		String sql = "select a.*,b.provider_nickname from zc_purchase_order a left join zc_provider_info b on a.provider_id = b. id where 1=1";
		sql += joinCondition(obj, state);
		sql += " order by a.CREATETIME desc";
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
	private String joinCondition(Object obj, String state) {
		String conditions = "";
		if (StringUtil.validate(state)) {
			conditions += " and a.PURCHASE_STATE = '" + state + "'  ";
		}
		return conditions;
	}

	/**
	 * 通过ID查询数据,可以关联多张表
	 */
	@Override
	public Map<String, Object> getObjById(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 无分页条件查询所有
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList1(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_BRANCH_INFO where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjPinYinList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_GOODS_MASTER where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList3(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_EMAIL_SERVICE where 1=1 ";
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
	public DataGrid getSearchGoodsDataGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select * from zc_purchase_order where 1=1";
		sql += conditions(obj);
		sql += " order by CREATETIME desc";
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
		PurchaseOrder purchaseOrder = (PurchaseOrder) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseOrder.getState())) {
			conditions += " and PURCHASE_STATE like '%"
					+ purchaseOrder.getState() + "%'  ";
		}
		return conditions;
	}

	@Override
	public DataGrid getHandleDataGrid(Page page, Object obj) throws Exception {
		String sql = "select sum(nums) as nums,goods_state,name,sum(GOODS_PURCHASE_PRICE*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,GOODS_PURCHASE_PRICE as actualnums,goodsfile_id,goods_class_id,GOODS_PURCHASE_PRICE,wasterate from "
				+ "(select b.goodstype_id,a.createtime,a.goods_state,b.goods_name as name,a.nums,b.goods_specifications,b.goods_unit,b.GOODS_PURCHASE_PRICE,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,b.goods_class_id,b.wasterate,b.goods_supplier_id "
				+ "from ZC_ORDER_process_ITEM a "
				+ "left join ZC_ORDER_process e on e.id = a.order_id "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ " where a.goods_state = '2' "
				+ joinCondition(obj)
				+ " )"
				+ " group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,GOODS_PURCHASE_PRICE,goodsfile_id,goods_class_id,goods_state,wasterate order by serialNumber asc ";

		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseOrderDao.getObjPagedList(page);
		Long total = purchaseOrderDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id = '"
						+ goodsFile.getGoods_class().getId()
						+ "' or c.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.name like '%" + goodsFile.getGoods_name()
					+ "%'";
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getProviderGoodsObj(Page page, Object obj, String providerId)
			throws Exception {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todayStartTime = df.format(today) + " 00:00:00";
		String todayEndTime = df.format(today) + " 23:59:59";
		String sql = "select a.id,a.createtime,sum(a.actualneed)as actualneed,sum(a.goodsmoney)as goodsmoney,a.goodsname,a.goodsprice,a.serialnumber,a.unit,a.specifications,b.provider_id "
				+ "from zc_purchase_order_items a left join zc_purchase_order b on b.id=a.purchaseid left join zc_provider_info c on c.id=b.provider_id "
				+ "where a.sendflag is null and b.updatetime between to_date('"
				+ todayStartTime
				+ "','YYYY-MM-DD HH24:MI:SS') and to_date('"
				+ todayEndTime + "','YYYY-MM-DD HH24:MI:SS') ";
		sql += condition(obj, providerId);
		sql += "group by a.id,a.goodsname,a.serialnumber,a.createtime,a.goodsprice,a.unit,a.specifications,b.provider_id order by a.CREATETIME desc";
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
	private String condition(Object obj, String providerId) {
		String conditions = "";
		if (StringUtil.validate(providerId)) {
			conditions += " and b.provider_id = '" + providerId + "'  ";
		}
		return conditions;
	}

}
