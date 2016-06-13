package com.proem.exm.service.impl.warehouse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.dao.warehouse.CheckDifferenceDao;
import com.proem.exm.dao.warehouse.CheckNumberDao;
import com.proem.exm.dao.warehouse.StoreChangeDao;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.ChangeGoodsItems;
import com.proem.exm.entity.warehouse.ZcCheckDifference;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.entity.warehouse.ZcStoreChange;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.CheckDifferenceService;
import com.proem.exm.service.warehouse.CheckNumberService;
import com.proem.exm.service.warehouse.StoreChangeService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
/**
 * 
 *
 */
@Service("StoreChangeService")
public class StoreChangeServiceImpl extends BaseServiceImpl implements
		StoreChangeService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	StoreChangeDao storeChangeDao;
	@Autowired
	GoodsFileDao goodsFileDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.branch_code,b.branch_name,c.username,d.store from ZC_STORECHANGE a "
				+ "left join zc_branch_info b on b.id=a.branch_id "
				+ "left join zc_user_info c on c.user_id=a.createuser_id "
				+ "left join ZC_STOREHOUSE d on d.branch_id=b.id "
				+ "where 1=1 ";
		sql += joinCondition(obj);
		sql +="order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = storeChangeDao.getObjPagedList(page);
		Long total = storeChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcStoreChange zcStoreChange= (ZcStoreChange) obj;
		String conditions = "";
//		if (StringUtil.validate(checknumber.getWarehouse_code().getWarehouse_name())) {
//			conditions += " WAREHOUSE_NAME like '%"
//					+ checknumber.getWarehouse_code().getWarehouse_name() + "%'";
//		}
		return conditions;
	}
	
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_checknumber";
			ZcCheckNumber zcCheckNumber = (ZcCheckNumber) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DataGrid getPagedDataGridObjById(Page page, Object obj, String id)
			throws Exception {
		String sql = "select a.id,f.branch_code,f.branch_name,h.classify_name as brandName,g.classify_name as className,e.serialnumber,e.goods_code,e.goods_name,e.goods_price,a.differencereason,a.actualchecknumber,a.store from zc_check_items a "+
					 "left join zc_goods_master e on e.id=a.goodsfile_id "+
					 " left join zc_classify_info g on g.id=e.goods_class_id "+
					 "left join zc_classify_info h on h.id=e.goods_brand_id "+
					 "left join zc_warehouse b on a.warehouse_id=b.id "+
					 "left join zc_checknumber c on c.id=b.checknumber_id "+
					 "left join zc_storehouse j on j.branch_id=c.branch_id and j.goodsfile_id=e.id "+
					 "left join zc_branch_info f on f.id=c.branch_id "+
					 "left join zc_checkdifference d on d.checknumber_id=c.id "+
					 "where d.id='"+id+"'";
		page.setSql(sql);
		List<Map<String, Object>> rows = storeChangeDao.getObjPagedList(page);
		Long total = storeChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页展示采购订单商品表中采购订单为空的数据
	 */
	@Override
	public DataGrid getChangeAddGoods(Page page, String id,Object obj, CtpUser ctpUser)
			throws Exception {
		String sql = "";
		if(StringUtils.isBlank(id)){
			sql = "select a.*,b.goods_code,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price,b.goods_unit from zc_change_items a left join zc_goods_master b on a.goodsfile_id=b.id where STORECHANGE_ID is null and CREATEUSER_ID='"
					+ ctpUser.getId() + "'";
		}else{
			ZcStoreChange storeChange=(ZcStoreChange) this.getObjById(id, "ZcStoreChange");
			Branch branch=storeChange.getBranch();
			if(storeChange.getStatus()==Constant.CHECK_STATUS_PASS){
				sql = "select d.changetype,a.id,a.changeNumber,a.remark,a.changeAmount as changemoney,a.store,b.goods_code,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price,b.goods_unit from zc_change_items a left join zc_storechange d on d.id=a.storechange_id left join zc_goods_master b on a.goodsfile_id=b.id  left join zc_storehouse c on c.branch_id='"+branch.getId()+"' and c.goodsfile_id=b.id where STORECHANGE_ID='"+id+"'";
			}else{
				sql = "select d.changetype,a.id,a.changeNumber,a.remark,a.changeAmount as changemoney,c.store,b.goods_code,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price,b.goods_unit from zc_change_items a left join zc_storechange d on d.id=a.storechange_id left join zc_goods_master b on a.goodsfile_id=b.id  left join zc_storehouse c on c.branch_id='"+branch.getId()+"' and c.goodsfile_id=b.id where STORECHANGE_ID='"+id+"'";	
			}
		}
		sql += conditions(obj);
		sql +="order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = storeChangeDao
				.getObjPagedList(page);
		Long total = storeChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		ChangeGoodsItems orders = (ChangeGoodsItems) obj;
		String conditions = "";
//		if (StringUtil.validate(orders.getSerialNumber())) {
//			conditions += " and SERIALNUMBER like '%"
//					+ orders.getSerialNumber() + "%'";
//		}
		return conditions;
	}

	


}
