package com.proem.exm.service.impl.warehouse;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.warehouse.SorteDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.warehouse.Sorte;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.SorteService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("sorteService")
public class SorteServiceImpl extends BaseServiceImpl implements SorteService {

	@Autowired
	private SorteDao sorteDao;

	@Override
	public DataGrid getSortePage(Page page, Object obj) throws Exception {
		String sql = "select name,serialNumber,goodsfile_id,sum(nums) as nums,classify_name,provider_nickname from "
				+ "(select a.goods_state,a.name,a.nums,b.id as goodsfile_id,b.serialNumber,c.classify_name,d.provider_nickname "
				+ "from zc_order_process_item a "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ "left join zc_provider_info d on b.goods_supplier_id = d.id "
				+ "where a.goods_state = '2' "
				+ joinConditionGoodsCode(obj)
				+ ") group by name,serialNumber,goodsfile_id,classify_name,provider_nickname order by serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = sorteDao.getObjPagedList(page);
		Long total = sorteDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 订单汇总查询
	 */
	private String joinConditionGoodsCode(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like'%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and b.goods_name like'%"
					+ goodsFile.getGoods_name() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id ='"
						+ goodsFile.getGoods_class().getId()
						+ "' or c.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id ='"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.name from Zc_sorte a "
				+ "left join ctp_user b on a.make_men = b.id where 1=1 ";
		sql += joinCondition(obj);
		sql += " order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = sorteDao.getObjPagedList(page);
		long total = sorteDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition(Object obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Sorte sorte = (Sorte) obj;
		String conditions = "";
		if (StringUtil.validate(sorte.getCode())) {
			conditions += " and a.code like'%" + sorte.getCode() + "%'";
		}
		if (StringUtil.validate(sorte.getCreateTime())) {
			conditions += " and a.createtime >= TO_DATE('"
					+ sdf.format(sorte.getCreateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(sorte.getUpdateTime())) {
			conditions += " and a.createtime <= TO_DATE('"
					+ sdf.format(sorte.getUpdateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (sorte.getAuditStatus() != 0) {
			conditions += " and a.audits_tatus = '"
					+ (sorte.getAuditStatus() - 1) + "'";
		}
		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridAdd(Page page, Object obj, String id)
			throws Exception {
		String sql = "";
		if (StringUtils.isBlank(id)) {
			sql = "select a.*,b.branch_code,b.branch_name,c.username,c.mobilephone,d.street from Zc_sorte_item a "
					+ "left join zc_branch_total b on b.id=a.branch_total_id "
					+ "left join zc_user_info c on c.id=a.customer "
					+ "left join zc_zoning d on d.id=b.zoning_id "
					+ "where a.sorte_id is null order by a.areaid desc ";
		} else {
			sql = "select a.*,b.branch_code,b.branch_name,c.username,c.mobilephone,d.street from Zc_sorte_item a "
					+ "left join zc_branch_total b on b.id=a.branch_total_id "
					+ "left join zc_user_info c on c.id=a.customer "
					+ "left join zc_zoning d on d.id=b.zoning_id "
					+ "where a.sorte_id ='"
					+ id
					+ "' order by a.areaid desc ";
		}
		page.setSql(sql);
		List<Map<String, Object>> rows = sorteDao.getObjPagedList(page);
		long total = sorteDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataGridGoodsDetail(Page page, Object obj,
			String id, String status) throws Exception {
		String sql = "";
		if ("3".equals(status)) {
			sql = "select name,serialNumber,goodsfile_id,sum(nums) as nums,branchid from "
					+ "(select a.goods_state,c.goods_name as name,a.nums,c.id as goodsfile_id,c.serialNumber,b.branchid "
					+ " from ZC_ORDER_TRANSIT_ITEM a "
					+ " left join ZC_ORDER_TRANSIT b on b.id=a.order_id left join zc_goods_master c on c.id=a.goodsfile_id "
					+ " where branchid = '"
					+ id
					+ "' ) "
					+ "group by name,serialNumber,goodsfile_id,branchid";
		} else {
			sql = "select name,serialNumber,goodsfile_id,sum(nums) as nums,branchid from "
					+ "(select a.goods_state,c.goods_name as name,a.nums,c.id as goodsfile_id,c.serialNumber,b.branchid "
					+ " from zc_order_process_item a "
					+ " left join zc_order_process b on b.id=a.order_id left join zc_goods_master c on c.id=a.goodsfile_id "
					+ " where branchid = '"
					+ id
					+ "' ) "
					+ "group by name,serialNumber,goodsfile_id,branchid";
		}
		sql += " order by serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = sorteDao.getObjPagedList(page);
		long total = sorteDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

}
