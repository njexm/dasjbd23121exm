package com.proem.exm.service.dispatching.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.dispatching.DispatchingDao;
import com.proem.exm.entity.branch.require.BranchRequire;
import com.proem.exm.service.dispatching.DispatchingService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;


@Service("dispatchingService")
public class DispatchingServiceImpl extends BaseServiceImpl implements DispatchingService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private DispatchingDao dispatchingDao;
	
	/**
	 * 查询条件拼接
	 * @param obj
	 * @param endTime 
	 * @param startTime 
	 * @param maxAmount 
	 * @param minAmount 
	 * @return
	 */
	private String joinCondition(Object obj, String minAmount, String maxAmount, String startTime, String endTime){
		BranchRequire branchRequire = (BranchRequire) obj;
		String conditions = "";
		if(StringUtil.validate(branchRequire.getYHDNumber())){
			conditions += " and a.YHD_number like '%"+ branchRequire.getYHDNumber() +"%'";
		}
		if(StringUtil.validate(branchRequire.getBranchTotal())){
			if(StringUtil.validate(branchRequire.getBranchTotal().getId())){
				conditions += " and a.branch_id = '"+ branchRequire.getBranchTotal().getId() +"'";
			}
		}
		if(StringUtil.validate(branchRequire.getDeliverBranchTotal())){
			if(StringUtil.validate(branchRequire.getDeliverBranchTotal().getId())){
				conditions += " and a.callout_branch_id = '" + branchRequire.getDeliverBranchTotal().getId() + "'";
			}
		}
		if(StringUtil.validate(startTime)){
			conditions += " and a.createTime >= TO_DATE('" + startTime
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		if(StringUtil.validate(endTime)){
			conditions += " and a.createTime <= TO_DATE('" + endTime
					+ "','YYYY-MM-DD HH24:MI:SS')";
		}
		if(StringUtil.validate(branchRequire.getStatus())){
			conditions += " and a.status = '" + branchRequire.getStatus() + "'";
		}
		if(StringUtil.validate(minAmount)){
			conditions += " and a.money >= "+ minAmount +"";
		}
		if(StringUtil.validate(maxAmount)){
			conditions += " and a.money <= " + maxAmount + "";
		}
		return conditions;
	}
	
	@Override
	public DataGrid getPagedDataGridObjDispatching(Page page, Object obj,  String minAmount, String maxAmount, String startTime, String endTime)
			throws Exception {
		String sql = "select a.id, a.createTime, a.YHD_number, a.money , a.status ,a.remark, b.branch_name as branchName1, c.branch_name as branchName2, d.username  from zc_require a "
				+" LEFT JOIN zc_branch_total b on a.branch_id = b.id "
				+" left join zc_branch_total c on a.callout_branch_id = c.id "
				+" left join zc_user_info d on a.user_id = d.id where 1=1 ";
		sql += joinCondition(obj, minAmount, maxAmount, startTime, endTime);
		page.setSql(sql);
		List<Map<String, Object>> rows = dispatchingDao.getObjPagedList(page);
		Long total = dispatchingDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	
	@Override
	public DataGrid getStoresDataGridObjDispatching(Page page, Object obj, String serialNumber)
			throws Exception {
		String sql = "select a.id, b.SERIALNUMBER, b.GOODS_NAME as goodsName, b.GOODS_SPECIFICATIONS as SPECIFICATIONS,"
				+" b.GOODS_UNIT as unit ,b.GOODS_PRICE as goodsPrice ,a.nums, a.MONEY, a.REMARK from ZC_REQUIRE_ITEM a "
				+" left join ZC_GOODS_MASTER b on a.GOODS_FILE_ID = b.id where 1=1 ";
		sql += joinCondition1(obj, serialNumber);
		page.setSql(sql);
		List<Map<String, Object>> rows = dispatchingDao.getObjPagedList(page);
		Long total = dispatchingDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinCondition1(Object obj, String serialNumber){
		BranchRequire branchRequire = (BranchRequire) obj;
		String conditions = "";
		if(StringUtil.validate(branchRequire.getId())){
			conditions += " and a.require_id = '"+branchRequire.getId()+"'";
		}	
		if(StringUtil.validate(serialNumber)){
			conditions += " and b.serialNumber like '%"+serialNumber+"%'";
		}
		return conditions;
	}	
}
