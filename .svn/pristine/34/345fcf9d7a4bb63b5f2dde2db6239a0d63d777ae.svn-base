package com.proem.exm.service.impl.settlement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.settlement.AdvancePaymentDao;
import com.proem.exm.entity.settlement.AdvancePayment;
import com.proem.exm.entity.settlement.AdvancePaymentItem;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.settlement.AdvancePaymentService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 预付款实现类
 * 
 * @author songcj 2015年11月25日 下午2:46:52
 */
@Service("AdvancePaymentService")
public class AdvancePaymentServiceImpl extends BaseServiceImpl implements
		AdvancePaymentService {

	@Autowired
	private AdvancePaymentDao advancePaymentDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.provider_nickname as providername from Zc_advance_payment a"
				+ " left join zc_provider_info b on a.provider_id=b.id where 1=1";
		sql += joinCondition(obj);
		sql += " order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = advancePaymentDao
				.getObjPagedList(page);
		long total = advancePaymentDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		AdvancePayment advancePayment = (AdvancePayment) obj;
		String conditions = "";
		if (StringUtil.validate(advancePayment.getProvider())) {
			if (StringUtil.validate(advancePayment.getProvider().getId())) {
				conditions += " and a.provider_id = '"
						+ advancePayment.getProvider().getId() + "'";
			}
		}
		if (StringUtil.validate(advancePayment.getRemarks())
				&& StringUtil.validate(advancePayment.getReason())) {
			conditions += " and a.payment_time between to_date('"
					+ advancePayment.getRemarks()
					+ "','yyyy-mm-dd HH24:mi:ss') and to_date('"
					+ advancePayment.getReason() + "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(advancePayment.getPaymentOrder())) {
			conditions += " and a.audit_status='"
					+ advancePayment.getPaymentOrder() + "'";
		}
		return conditions;
	}

	// 新增页面数据获取
	@Override
	public DataGrid getItemDataGridObj(Page page, Object object)
			throws Exception {
		String sql = "select * from Zc_advance_payment_item where advance_payment_id is null ";
		page.setSql(sql);
		List<Map<String, Object>> rows = advancePaymentDao
				.getObjPagedList(page);
		long total = advancePaymentDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getAdvancePaymentDetail(Page page, String id, Object obj)
			throws Exception {
		String sql = "select * from Zc_advance_payment_item where advance_payment_id = '"
				+ id + "'";
		page.setSql(sql);
		List<Map<String, Object>> rows = advancePaymentDao
				.getObjPagedList(page);
		Long total = advancePaymentDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
}
