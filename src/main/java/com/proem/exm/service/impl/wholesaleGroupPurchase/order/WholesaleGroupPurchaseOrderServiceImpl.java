package com.proem.exm.service.impl.wholesaleGroupPurchase.order;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.dao.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderDao;
import com.proem.exm.entity.purchase.PurchaseReceive;
import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrder;
import com.proem.exm.entity.wholesaleGroupPurchase.wholesell.WholeSell;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.wholesaleGroupPurchase.order.WholesaleGroupPurchaseOrderService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("wholeGroupService")
public class WholesaleGroupPurchaseOrderServiceImpl extends BaseServiceImpl implements
		WholesaleGroupPurchaseOrderService {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private WholesaleGroupPurchaseOrderDao wholesaleGroupPurchaseDao;

	@Override
	public DataGrid getPagedDataGridObj(String className, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj,
			String startDate, String endDate, String state) throws Exception {
		String sql = "select a.id, a.ORDERNUMBER, b.branch_code ,b.BRANCH_NAME, c.code,c.name, e.name as username, a.CREATETIME, a.ORDERAMOUNT, a.STATUS from ZC_WHOLE_ORDER a left join ZC_BRANCH_INFO b on a.BRANCH_ID = b.id "
				+ " left join ZC_CUSTOMER_INFO c on a.CUSTOMEINFO_ID = c.id left join ZC_USER_INFO d on a.zcuserinfo_id = d.id left join CTP_USER  e on "
				+ " d.user_id = e.id where 1=1 ";
		sql += JoinCondition(obj, startDate, endDate, state);
		sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wholesaleGroupPurchaseDao
				.getObjPagedList(page);
		Long total = wholesaleGroupPurchaseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	// 采购收货单插叙条件拼接
	private String JoinCondition(Object obj, String startDate, String endDate,
			String state) {
		WholesaleGroupPurchaseOrder whole = (WholesaleGroupPurchaseOrder) obj;
		String conditions = "";
		if (StringUtil.validate(state)) {
			conditions += " and a.STATUS ='" + state + "' ";
		}
		if (StringUtil.validate(whole.getOrderNumber())) {
			conditions += " and a.ORDERNUMBER like'%" + whole.getOrderNumber()
					+ "%' ";
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

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
