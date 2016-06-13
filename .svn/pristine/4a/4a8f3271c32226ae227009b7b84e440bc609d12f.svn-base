package com.proem.exm.service.impl.purchase;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.purchase.PurchaseReceiveDao;
import com.proem.exm.dao.purchase.PurchaseReceiveItemDao;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.purchase.PurchaseReceiveService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 采购收货实现类
 * 
 * @author songcj
 * 
 *         2015年11月6日下午3:45:16
 */
@Service("PurchaseReceiveService")
public class PurchaseReceiveServiceImpl extends BaseServiceImpl implements
		PurchaseReceiveService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private PurchaseReceiveDao purchaseReceiveDao;
	@Autowired
	protected PurchaseReceiveItemDao purchaseReceiveItemDao;

	/**
	 * 修改已经存在的数据 - 单纯单表操作
	 */
	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj, String state)
			throws Exception {
		String sql = "select a.*,b.PROVIDER_NICKNAME from zc_purchase_receive a left join zc_provider_info b on b.id=a.provider_id where 1=1 ";
		sql += JoinCondition(obj, state);
		sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseReceiveDao
				.getObjPagedList(page);
		Long total = purchaseReceiveDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	// 采购收货单插叙条件拼接
	private String JoinCondition(Object obj, String state) {
		PurchaseReceive purchaseReceive = (PurchaseReceive) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseReceive.getProviderInfo())) {
			if (StringUtil.validate(purchaseReceive.getProviderInfo().getId())) {
				conditions += " and a.provider_id = '"
						+ purchaseReceive.getProviderInfo().getId() + "'";
			}
		}
		if (StringUtil.validate(purchaseReceive.getPurchaseReceiveOdd())) {
			conditions += " and a.purchase_receive_odd = '"
					+ purchaseReceive.getPurchaseReceiveOdd() + "'";
		}
		if (StringUtil.validate(state)) {
			conditions += " and a.audit_status = '" + state + "'";
		}
		if (StringUtil.validate(purchaseReceive.getCreateTime())) {
			conditions += " and a.createtime >= TO_DATE('"
					+ sdf.format(purchaseReceive.getCreateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(purchaseReceive.getUpdateTime())) {
			conditions += " and a.createtime <= TO_DATE('"
					+ sdf.format(purchaseReceive.getUpdateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		return conditions;
	}

	@Override
	public List<Map<String, Object>> getClassInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询明细
	 */
	@Override
	public DataGrid getPagedDataGridGoods(Page page, Object obj,
			String serialNumber) throws Exception {
		String sql = "select a.productDate,a.CREATEFLAG,a.id as ID,a.actual_num,a.createTime,a.purchase_receive_id,a.GOODSMONEY,b.serialnumber,b.goods_name as GOODSNAME,b.goods_unit as UNIT,b.goods_specifications as SPECIFICATIONS,a.goodsPrice as GOODSPRICE from zc_purchase_receive_item a left join zc_goods_master b on b.id=a.goodsfile_id where 1=1 ";
		sql += JoinItemCondition(obj, serialNumber);
		sql+=" order by b.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseReceiveItemDao
				.getObjPagedList(page);
		Long total = purchaseReceiveItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String JoinItemCondition(Object obj, String serialNumber) {
		PurchaseReceive purchaseReceive = (PurchaseReceive) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseReceive.getId())) {
			conditions += " and a.purchase_receive_id = '"
					+ purchaseReceive.getId() + "'";
		}
		if (StringUtil.validate(serialNumber)) {
			conditions += " and b.serialnumber like '%" + serialNumber + "%'";
		}
		return conditions;
	}

	private String JoinItemConditions(Object obj, String serialNumber) {
		PurchaseReceive purchaseReceive = (PurchaseReceive) obj;
		String conditions = "";
		if (StringUtil.validate(purchaseReceive.getId())) {
			conditions += " and purchase_receive_id = '"
					+ purchaseReceive.getId() + "'";
		}
		if (StringUtil.validate(serialNumber)) {
			conditions += " and b.SERIALNUMBER like '%" + serialNumber + "%'";
		}
		return conditions;
	}

	/**
	 * 分页展示采购订单商品表中采购订单为空的数据
	 */
	@Override
	public DataGrid getPurchaseAddGoods(Page page, Object obj, CtpUser ctpUser,
			String providerId) throws Exception {
		String sql = "select a.productDate,a.id as ID,a.actual_num,a.purchase_receive_id,a.CTPUSER_ID,b.goods_supplier_id,b.serialnumber,b.goods_name,b.goods_unit,b.goods_specifications,a.goodsPrice as goods_purchase_price from ZC_PURCHASE_RECEIVE_ITEM a left join zc_goods_master b on b.id=a.goodsfile_id where a.purchase_receive_id is null and a.CTPUSER_ID='"
				+ ctpUser.getId() + "'";
		sql += conditions(obj, providerId);
		sql+=" order by b.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = purchaseReceiveItemDao
				.getObjPagedList(page);
		Long total = purchaseReceiveItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj, String providerId) {
		String conditions = "";
		if (StringUtil.validate(providerId)) {
			conditions += " and b.goods_supplier_id= '" + providerId + "'";
		}
		return conditions;
	}
}