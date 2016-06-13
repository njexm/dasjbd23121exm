package com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnDao;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturn;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.wholesaleGroupPurchaseReturn.WGPurchaseReturnService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 团购退货单
 * 
 * @author ZuoYM
 * @com proem
 */
@Service("WGPurchaseReturnService")
public class WGPurchaseReturnServiceImpl extends BaseServiceImpl implements
		WGPurchaseReturnService {
	@Autowired
	private WGPurchaseReturnDao wGPurchaseReturnDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		// String sql = "select * from ZC_WGPURCHASE_RETURN  where 1=1 ";
		String sql = "select a.createtime,a.id,a.returnMoney,a.wholeReturnId,a.statue,b.branch_code,b.branch_name,c.name "
				+ "from ZC_WGPURCHASE_RETURN a left join zc_branch_info b on a.branch_id=b.id "
				+ "left join ZC_CUSTOMER_INFO c on c.id=a.customerinfo_id where 1=1 ";
		sql += joinCondition(obj);
		sql += " order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPurchaseReturnDao
				.getObjPagedList(page);
		Long total = wGPurchaseReturnDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			// String sql = "select * from ZC_WGPURCHASE_RETURN  where 1=1 ";
			String sql = "select a.createtime,a.id,a.returnMoney,a.totalMoney,a.wholeReturnId,a.statue,b.branch_code,b.branch_name,c.name "
					+ "from ZC_WGPURCHASE_RETURN a left join zc_branch_info b on a.branch_id=b.id "
					+ "left join ZC_CUSTOMER_INFO c on c.id=a.customerinfo_id where 1=1 ";
			// WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		// WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) obj;
		String conditions = "";

		return conditions;
	}

	/**
	 * 获取团购退货单信息列表
	 * 
	 * @return
	 */
	@Override
	public List<WGPurchaseReturn> getAllObject() {
		return baseDataMng.getAllObj(WGPurchaseReturn.class);
	}

	/**
	 * 团购退货单明细查询
	 */
	@Override
	public DataGrid getWGPurchaseReturnItem(Page page, Object obj,
			String serialNumber) throws Exception {
		WGPurchaseReturn wGPurchaseReturn = (WGPurchaseReturn) obj;
		String sql = "select a.id,b.serialnumber,b.goods_name,a.orderPrice,a.nums,a.rate,a.rateAmount,a.amount,b.goods_unit,b.goods_specifications,a.returnNums,a.returnAmount "
				+ "from ZC_WGPURCHASE_RETURN_ITEM a left join zc_goods_master b on b.id=a.goodsFile_id left join ZC_WGPURCHASE_RETURN c on c.id=a.wGPurchaseReturn_id left join ZC_PROVIDER_INFO d on d.id=a.providerInfo_ID  "
				+ "where a.wGPurchaseReturn_id='"
				+ wGPurchaseReturn.getId()
				+ "'";
		sql += condition(obj, serialNumber);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wGPurchaseReturnDao
				.getObjPagedList(page);
		Long total = wGPurchaseReturnDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * Items页面查询条件拼接
	 * 
	 * @param obj
	 * @param state
	 * @return
	 */
	private String condition(Object obj, String serialNumber) {
		String conditions = "";
		if (StringUtil.validate(serialNumber)) {
			conditions += " and b.serialnumber like'%" + serialNumber + "%' ";
		}
		return conditions;
	}

}
