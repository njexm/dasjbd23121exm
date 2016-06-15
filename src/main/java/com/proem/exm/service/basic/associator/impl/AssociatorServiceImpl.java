package com.proem.exm.service.basic.associator.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.associator.AssociatorDao;
import com.proem.exm.entity.basic.CommodityClassify.CommodityClassify;
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.service.basic.associator.AssociatorService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 
 * @author ZuoYM
 * 
 */
@Service("AssociatorService")
public class AssociatorServiceImpl extends BaseServiceImpl implements
		AssociatorService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	AssociatorDao associatorDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_associator_info  where 1=1 ";
		sql += joinCondition(obj);
		sql+=" order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = associatorDao.getObjPagedList(page);
		Long total = associatorDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	 private String joinCondition(Object obj){
		 Associator associator = (Associator) obj;
			String conditions = "";
			if (StringUtil.validate(associator.getAssociator_Name())) {
				conditions += " and associator_Name like'%" + associator.getAssociator_Name() + "%'";
			}
			if (StringUtil.validate(associator.getAssociator_Mobilephone())) {
				conditions += " and associator_Mobilephone like'%" + associator.getAssociator_Mobilephone() + "%'";
			}
			if (StringUtil.validate(associator.getAssociator_CardNumber())) {
				conditions += " and associator_CardNumber like'%" + associator.getAssociator_CardNumber() + "%'";
			}
		 return conditions;
	 }

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from zc_associator_info";
			Associator associators = (Associator) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DataGrid getPageDataGridByPayInfo(Page page, Object obj)
			throws Exception {
		String sql = "SELECT B.id,B.payinfo_id,D.ID AS BRANCHID,C.ASSOCIATOR_CARDNUMBER,C.ASSOCIATOR_NAME,B.pay_mode,B.MONEY ,D.BRANCH_NAME FROM  ZC_PAYINFO_ITEM B "
					+"LEFT JOIN ZC_PAYINFO A ON A.ID = B.PAYINFO_ID "
					+"LEFT JOIN ZC_ASSOCIATOR_INFO C ON C.ID = A.MEMBERID "
					+"LEFT JOIN ZC_BRANCH_INFO D ON D.ID = A.branch_id "
					+"WHERE 1=1 ";
		sql+=conditions(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = associatorDao.getObjPagedList(page);
		Long total = associatorDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj){
		 Associator associator = (Associator) obj;
			String conditions = "";
			if (StringUtil.validate(associator.getAssociator_Category())) {
				conditions += " and B.pay_mode like'%" + associator.getAssociator_Category() + "%' ";
			}
			if (StringUtil.validate(associator.getAssociator_RegisterStore())) {
				conditions += " and D.ID like'%" + associator.getAssociator_RegisterStore() + "%' ";
			}
			if (StringUtil.validate(associator.getAssociator_Amount())) {
				if (StringUtil.validate(associator.getAssociator_ConsumeAmount())) {
				conditions += " and B.MONEY between " + associator.getAssociator_Amount() + " and "+associator.getAssociator_ConsumeAmount()+"";
			
				}
			}
		 return conditions;
	}

}
