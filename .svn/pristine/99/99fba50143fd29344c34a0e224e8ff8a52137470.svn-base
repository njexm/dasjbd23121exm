package com.proem.exm.service.wholesaleGroupPurchase.customer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.customer.CustomerInfoDao;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.wholesaleGroupPurchase.customer.CustomerInfoService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 客户档案
 * @author ZuoYM 
 * @com proem
 */
@Service("customerInfoService")
public class CustomerInfoServiceImpl extends BaseServiceImpl implements CustomerInfoService {
	@Autowired
	private CustomerInfoDao pustomerInfoDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_CUSTOMER_INFO  where 1=1 ";
		sql += joinCondition(obj);
		sql+=" order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = pustomerInfoDao.getObjPagedList(page);
		Long total = pustomerInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_CUSTOMER_INFO where 1=1 ";
//			CustomerInfo customerInfo = (CustomerInfo) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		CustomerInfo customerInfo = (CustomerInfo) obj;
		String conditions = "";
		if (StringUtil.validate(customerInfo.getArea())) {
			conditions += " and area like '%" + customerInfo.getArea()
					+ "%'";
		}
		return conditions;
	}
	
	/**
	 * 获取客户信息列表
	 * @return
	 */
	@Override
	public List<CustomerInfo> getAllObject() {
		return baseDataMng.getAllObj(CustomerInfo.class);
	}
	
	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getlistJson(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_CUSTOMER_INFO where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
