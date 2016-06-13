package com.proem.exm.service.impl.warehouse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.warehouse.ProcessGoodsItemsDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.ProcessGoodsItemsService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 业务逻辑访问实现类
 * 
 * @author Administrator
 * 
 */
@Service("processGoodsItemsService")
public class ProcessGoodsItemsServiceImpl extends BaseServiceImpl implements
		ProcessGoodsItemsService {
	@Autowired
	ProcessGoodsItemsDao processGoodsItemsDao;

	/**
	 * 分页展示所有待加工的商品
	 */
	@Override
	public DataGrid getlistWaitGoodsJson(Page page, Object obj,
			String serialNumber) throws Exception {
		String sql = "select sum(nums) as nums,name,sum(g_price*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,g_price as actualnums,goodsfile_id,goods_class_id,g_price from "
				+ "(select a.goods_state,a.name,a.nums,b.goods_specifications,b.goods_unit,a.g_price,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,b.goods_class_id "
				+ "from ZC_ORDER_ITEM a "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id) "
				+ "where goods_state = '2' "
				+ joinConditionGoodsCode(obj, serialNumber)
				+ " group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,g_price,goodsfile_id,goods_class_id ";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsItemsDao
				.getObjPagedList(page);
		Long total = processGoodsItemsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 领料仓库查询
	 */
	private String joinConditionGoodsCode(Object obj, String serialNumber) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(serialNumber)) {
			conditions += " and serialnumber like'%" + serialNumber + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and classify_name like'%"
					+ goodsFile.getGoods_name() + "%'";
		}
		return conditions;
	}

	/**
	 * 分页展示所有待加工的商品
	 */
	@Override
	public DataGrid getWaittingProcessJson(Page page, Object obj,
			String serialNumber) throws Exception {
		String sql = "select sum(nums) as nums,wasterate,goods_state,name,goods_specifications,serialNumber,goodsfile_id from "
				+ "(select b.goodstype_id,b.wasterate,a.createtime,a.goods_state,a.name,a.nums,b.goods_specifications,b.id as goodsfile_id,b.serialNumber "
				+ "from ZC_ORDER_process_ITEM a "
				+ "left join ZC_ORDER_process e on e.id = a.order_id "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "where a.goods_state = '2'"
				+ joinCondition(obj, serialNumber)
				+ ")"
				+ "group by name,wasterate,goods_specifications,serialNumber,goodsfile_id,goods_state order by serialNumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = processGoodsItemsDao
				.getObjPagedList(page);
		Long total = processGoodsItemsDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 领料仓库查询
	 */
	private String joinCondition(Object obj, String serialNumber) {
		String conditions = "";
		if (StringUtil.validate(serialNumber)) {
			conditions += " and b.serialnumber like'%" + serialNumber + "%'";
		}
		return conditions;
	}
}
