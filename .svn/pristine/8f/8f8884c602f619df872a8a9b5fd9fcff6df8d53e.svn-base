package com.proem.exm.service.wholesaleGroupPurchase.customer.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.WholeSellDao;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSell;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.customer.WholeSellService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 团购销售单业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("wholeSellService")
public class WholeSellServiceImpl extends BaseServiceImpl implements
		WholeSellService {
	@Autowired
	WholeSellDao wholeSellDao;

	/**
	 * 团购销售单list页面查询
	 * 
	 * @param page
	 * @param obj
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataGrid getWholeSellListObj(Page page, Object obj,
			String startDate, String endDate, String state) throws Exception {
		String sql = "select a.createtime,a.id,a.totalMoney,a.wholesellodd,a.statue,b.branch_code,b.branch_name,c.name "
				+ "from zc_whole_sell a left join zc_branch_info b on a.branch_id=b.id "
				+ "left join ZC_CUSTOMER_INFO c on c.id=a.customerinfo_id where 1=1 ";
		sql += joinCondition(obj, startDate, endDate, state);
		sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wholeSellDao.getObjPagedList(page);
		Long total = wholeSellDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * list页面查询条件拼接
	 * 
	 * @param obj
	 * @param state
	 * @return
	 */
	private String joinCondition(Object obj, String startDate, String endDate,
			String state) {
		WholeSell wholeSell = (WholeSell) obj;
		String conditions = "";
		if (StringUtil.validate(state)) {
			conditions += " and a.statue ='" + state + "' ";
		}
		if (StringUtil.validate(wholeSell.getWholeSellOdd())) {
			conditions += " and a.wholesellodd like'%"
					+ wholeSell.getWholeSellOdd() + "%' ";
		}
		if (StringUtil.validate(startDate)) {
			conditions += " and a.createTime >= TO_DATE('" + startDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(endDate)) {
			conditions += " and a.createTime <= TO_DATE('" + endDate
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		return conditions;
	}

	/**
	 * 团购销售单Items页面查询
	 * 
	 * @param page
	 * @param obj
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataGrid getWholeSellItems(Page page, Object obj, String serialNumber)
			throws Exception {
		WholeSell wholeSell = (WholeSell) obj;
		String sql = "select a.createflag,a.id,b.serialnumber,b.goods_name,a.order_price,d.store,a.nums,a.rate,a.rate_amount,a.amount,b.goods_unit,b.goods_specifications "
				+ "from zc_whole_sell_items a left join zc_goods_master b on b.id=a.goods_file_id left join zc_whole_sell c on c.id=a.wholesell_id left join zc_storehouse d on d.goodsfile_id=b.id "
				+ "where a.wholesell_id='"
				+ wholeSell.getId()
				+ "' and d.branch_id='" + wholeSell.getBranch().getId() + "'";
		sql += condition(obj, serialNumber);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wholeSellDao.getObjPagedList(page);
		Long total = wholeSellDao.getObjListCount(page);
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
