package com.proem.exm.service.impl.warehouse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.warehouse.ProcessGoodsDao;
import com.proem.exm.entity.warehouse.process.ProcessGoods;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.ProcessGoodsService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("processGoodsService")
public class ProcessGoodsServiceImpl extends BaseServiceImpl implements
		ProcessGoodsService {
	@Autowired
	ProcessGoodsDao processGoodsDao;

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_PROCESSGOODS where 1=1 ";
		sql += joinCondition(obj);
		sql += " order by CREATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsDao.getObjPagedList(page);
		Long total = processGoodsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ProcessGoods processGoods = (ProcessGoods) obj;
		String conditions = "";
		if (StringUtil.validate(processGoods.getOdd())) {
			conditions += " and ODD like '%" + processGoods.getOdd() + "%'";
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getChooseSorteList(Page page, String sorteOdd)
			throws Exception {
		String sql = "select a.*,b.name from Zc_sorte a "
				+ "left join ctp_user b on a.make_men = b.id where 1=1 ";
		sql += joinCondition(sorteOdd);
		sql += " order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsDao.getObjPagedList(page);
		Long total = processGoodsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(String sorteOdd) {
		String conditions = "";
		if (StringUtil.validate(sorteOdd)) {
			conditions += " and a.code like '%" + sorteOdd + "%'";
		}
		return conditions;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getlistProcessUseGoodsJson(Page page, Object obj,
			String processGoodsId) throws Exception {
		String sql = "select a.*,c.store from ZC_PROCESSGOODS_ITEMS a left join zc_goods_master b on b.id=a.Goodsfile_Id "
				+ "left join ZC_STOREHOUSE c on c.goodsfile_id=b.id where a.TYPEFLAG='1' and c.branch_id='596BA834-0618-4902-BCDF-2A70499C43B5' and a.PROCESSGOODSID='"
				+ processGoodsId + "' order by a.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsDao.getObjPagedList(page);
		Long total = processGoodsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getlistProcessProductGoodsJson(Page page, Object obj,
			String processGoodsId) throws Exception {
		String sql = "select * from ZC_PROCESSGOODS_ITEMS where TYPEFLAG='2' ";
		sql += condition2(obj, processGoodsId);
		sql += " order by serialNumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsDao.getObjPagedList(page);
		Long total = processGoodsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String condition2(Object obj, String processGoodsId) {
		String conditions = "";
		if (StringUtil.validate(processGoodsId)) {
			conditions += " and PROCESSGOODSID = '" + processGoodsId + "'";
		}
		return conditions;
	}

}
