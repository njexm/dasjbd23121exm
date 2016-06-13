package com.proem.exm.service.basic.adjustment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.adjustments.AdjustmentsDao;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.entity.basic.adjustment.AdjustmentDetail;
import com.proem.exm.entity.basic.adjustment.Adjustments;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 商品调价单
 * 
 * @author ZuoYM
 * 
 */
@Service("AdjustmentService")
public class AdjustmentServiceImpl extends BaseServiceImpl implements
		AdjustmentService {

	@Autowired
	AdjustmentsDao adjustmentsDao;
	@Autowired
	GoodsFileDao goodsFileDao;

	@Override
	public void updateObj(Object obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * 分页查询信息
	 */
	@Override
	public DataGrid getPagedDataGridObjList(Page page, Object obj,
			String audflag) throws Exception {
		String sql = "select * from zc_adjustments where 1=1 ";
		// String sql =
		// "select a.*,b.* from zc_adjustment_info a left join zc_adjustments b on a.zc_adjustments_id=b.id left join zc_user_info c on b.CTP_USER_ID=c.id  where 1=1 ";
		sql += joinCondition(obj, audflag);
		sql += " order by createTime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = adjustmentsDao.getObjPagedList(page);
		Long total = adjustmentsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_adjustment_info where zc_adjustments_id is null  ";
		page.setSql(sql);
		List<Map<String, Object>> rows = adjustmentsDao.getObjPagedList(page);
		Long total = adjustmentsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj, String audflag) {
		Adjustments adjustments = (Adjustments) obj;
		String conditions = "";
		if (StringUtil.validate(adjustments.getAdjustment_id())) {
			conditions += " and adjustment_id like'%"
					+ adjustments.getAdjustment_id() + "%'";
		}
		if (StringUtil.validate(adjustments.getAuditor())) {
			conditions += " and auditor like'%" + adjustments.getAuditor()
					+ "%'";
		}
		if (StringUtil.validate(adjustments.getCreatetime_adj())) {
			conditions += " and createTime >= to_date('"
					+ adjustments.getCreatetime_adj()
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		if (StringUtil.validate(audflag)) {
			conditions += " and audflag='" + audflag + "'";
		}
		return conditions;
	}

	@Override
	public List getObjList(GoodsFile goodsFile) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		String sql = "";
		try {
			sql = "select * from Zc_goods_master order by goods_name ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List getObjList_name(GoodsFile goodsFile) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		String sql = "";
		try {
			sql = "select * from Zc_goods_master order by goods_name ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DataGrid getPagedDataDetailGridObj(Page page,
			AdjustmentDetail adjustmentDetail) {
		List<Map<String, Object>> rows = null;
		Long total = null;
		try {
			String sql = "select * from zc_adjustment_info where 1=1  ";
			// String sql =
			// "select a.*,b.adjustment_id,b.auditor,b.delflag,b.audflag,b.createtime_adj,b.effecttime from zc_adjustment_info a left join zc_adjustments b on a.zc_adjustments_id=b.id where 1=1 ";
			sql += joinConditionDetail(adjustmentDetail);
			page.setSql(sql);
			rows = adjustmentsDao.getObjPagedList(page);
			total = adjustmentsDao.getObjListCount(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinConditionDetail(Object obj) {
		AdjustmentDetail adjustmentDetail = (AdjustmentDetail) obj;
		// Adjustments adjustments = (Adjustments) obj;
		String conditions = "";
		System.out.println("adjustmentDetail.getId()-------:"
				+ adjustmentDetail.getId());
		System.out.println("adjustmentDetail.getAdjustments()-------:"
				+ adjustmentDetail.getAdjustments());
		// System.out.println("adjustments.getId()-------:"+adjustments.getId());
		if (StringUtil.validate(adjustmentDetail.getAdjustments())) {
			conditions += " and zc_adjustments_id = '"
					+ adjustmentDetail.getAdjustments().getId() + "'";
		}
		if (StringUtil.validate(adjustmentDetail.getId())) {
			conditions += " and id = '" + adjustmentDetail.getId() + "'";
		}
		return conditions;
	}

	/**
	 * 
	 */
	@Override
	public List getIdList(String name) {
		// GoodsFile goodsFile=new GoodsFile();
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		String sql = "";
		try {
			sql = "select id from Zc_goods_master where goods_name= '" + name
					+ "' ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getChooseGoodsItems(Page page, Object obj) throws Exception {
		String sql = "select * from zc_goods_master where 1=1 ";
		sql += chooseGoodsConditions(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsFileDao.getObjPagedList(page);
		Long total = goodsFileDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String chooseGoodsConditions(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and goods_name like '%" + goodsFile.getGoods_name()
					+ "%'";
		}
		return conditions;
	}
}
