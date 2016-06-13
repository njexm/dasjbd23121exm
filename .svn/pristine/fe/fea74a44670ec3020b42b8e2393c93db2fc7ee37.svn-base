package com.proem.exm.service.impl.warehouse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.warehouse.WareHouseDao;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.entity.warehouse.ZcWarehouse;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.WareHouseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("wareHouseService")
public class WareHouseServiceImpl extends BaseServiceImpl implements
		WareHouseService {

	@Autowired
	WareHouseDao wareHouseDao;

	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcWarehouse zcWarehouse = (ZcWarehouse) obj;
		String conditions = "";
		if (StringUtil.validate(zcWarehouse.getWarehouseNumber())) {
			conditions += " and t.WarehouseNumber like '%"+ zcWarehouse.getWarehouseNumber() + "%'";
		}
//		if (StringUtil.validate(checknumber.getWarehouse_code().getWarehouse_name())) {
//			conditions += " WAREHOUSE_NAME like '%"
//					+ checknumber.getWarehouse_code().getWarehouse_name() + "%'";
//		}
		return conditions;
	}

	
	
	/**
	 * 分页查询盘点号列表
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select t.*,b.check_number,c.branch_code,c.branch_name,d.username,b.status as checkStatus from ZC_WAREHOUSE t left join zc_checknumber b on b.id=t.checknumber_id left join zc_branch_info c on c.id=b.branch_id left join zc_user_info d on d.user_id=t.createuser_id where 1=1";
		sql += joinCondition(obj);
		sql +="order by t.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = wareHouseDao.getObjPagedList(page);
		Long total = wareHouseDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public Map<String, Object> getObjById(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
