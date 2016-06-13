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
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.warehouse.ZcCheckDifference;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.CheckDifferenceService;
import com.proem.exm.service.warehouse.CheckNumberService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
/**
 * 
 *
 */
@Service("CheckDifferenceService")
public class CheckDifferenceServiceImpl extends BaseServiceImpl implements
		CheckDifferenceService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	CheckDifferenceDao checkDifferenceDao;
	@Autowired
	GoodsFileDao goodsFileDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.check_number,c.username,d.branch_code,d.branch_name from ZC_CHECKDIFFERENCE a left join zc_checknumber b on b.id=a.checknumber_id left join zc_user_info c on c.user_id=a.createuser_id left join zc_branch_info d on d.id=b.branch_id where 1=1 ";
		sql += joinCondition(obj);
		sql +=" order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = checkDifferenceDao.getObjPagedList(page);
		Long total = checkDifferenceDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcCheckNumber zcCheckNumber = (ZcCheckNumber) obj;
		String conditions = "";
		if (StringUtil.validate(zcCheckNumber.getCheck_number())) {
			conditions += " and b.check_number like '%"+ zcCheckNumber.getCheck_number() + "%'";
		}
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
		String sql="";
		ZcCheckDifference zcCheckDifference=(ZcCheckDifference) this.getObjById(id, "ZcCheckDifference");
		int status=zcCheckDifference.getStatus();
		if(status==Constant.CHECK_STATUS_PASS){
		 sql= "select a.id,f.branch_code,f.branch_name,h.classify_name as brandName,g.classify_name as className,e.serialnumber,e.goods_code,e.goods_name,e.goods_price,a.differencereason,a.actualchecknumber,a.store from zc_check_items a "+
					 "left join zc_goods_master e on e.id=a.goodsfile_id "+
					 " left join zc_classify_info g on g.id=e.goods_class_id "+
					 "left join zc_classify_info h on h.id=e.goods_brand_id "+
					 "left join zc_warehouse b on a.warehouse_id=b.id "+
					 "left join zc_checknumber c on c.id=b.checknumber_id "+
					 "left join zc_storehouse j on j.branch_id=c.branch_id and j.goodsfile_id=e.id "+
					 "left join zc_branch_info f on f.id=c.branch_id "+
					 "left join zc_checkdifference d on d.zcwarehouse_id=b.id "+
					 "where d.id='"+id+"' order by e.serialnumber asc";
		}else{
			 sql= "select a.id,f.branch_code,f.branch_name,h.classify_name as brandName,g.classify_name as className,e.serialnumber,e.goods_code,e.goods_name,e.goods_price,a.differencereason,a.actualchecknumber,j.store from zc_check_items a "+
					 "left join zc_goods_master e on e.id=a.goodsfile_id "+
					 " left join zc_classify_info g on g.id=e.goods_class_id "+
					 "left join zc_classify_info h on h.id=e.goods_brand_id "+
					 "left join zc_warehouse b on a.warehouse_id=b.id "+
					 "left join zc_checknumber c on c.id=b.checknumber_id "+
					 "left join zc_storehouse j on j.branch_id=c.branch_id and j.goodsfile_id=e.id "+
					 "left join zc_branch_info f on f.id=c.branch_id "+
					 "left join zc_checkdifference d on d.zcwarehouse_id=b.id "+
					 "where d.id='"+id+"' order by e.serialnumber asc";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = checkDifferenceDao.getObjPagedList(page);
		Long total = checkDifferenceDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	


}
