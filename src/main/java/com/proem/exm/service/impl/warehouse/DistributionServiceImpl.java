package com.proem.exm.service.impl.warehouse;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.warehouse.DistributionDao;
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.DistributionService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("DistributionService")
public class DistributionServiceImpl extends BaseServiceImpl implements DistributionService{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired 
	private DistributionDao distributionDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.province,b.city,b.county,b.street,c.associator_cardnumber as cardnumber "
				+ " from zc_order_transit a "
				+ " left join zc_zoning b on b.id= a.zczoning_id "
				+ " left join zc_associator_info c on c.id=a.member_id "
				+ " left join zc_area_nanjing d on d.id=a.branchid  "
				+ " where 1=1";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = distributionDao.getObjPagedList(page);
		Long total = distributionDao.getObjListCount(page);
 	return new DataGrid(total, rows);
	}
	
	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcOrder orders = (ZcOrder) obj;
		String conditions = "";
		if (StringUtil.validate(orders.getOrderNum())) {
			conditions += " and A.ORDERNUM like '%" + orders.getOrderNum()
					+ "%'";
		}
		if (StringUtil.validate(orders.getOrderStatus())) {
			conditions += " and A.ORDERSTATUS='" + orders.getOrderStatus()
					+ "'";
		}
		if (StringUtil.validate((orders.getAssociator()==null ? new Associator():orders.getAssociator()).getAssociator_CardNumber())) {
			conditions += " and C.associator_CardNumber like '%"
					+(orders.getAssociator()==null ? new Associator():orders.getAssociator()).getAssociator_CardNumber() + "%'";
		}
		if (StringUtil.validate(orders.getConsignee())) {
			conditions += " and A.CONSIGNEE like '%" + orders.getConsignee()
					+ "%'";
		}
		if (StringUtil.validate(orders.getCansignPhone())) {
			conditions += " and A.CANSIGNPHONE like '%"
					+ orders.getCansignPhone() + "%'";
		}
		if (StringUtil.validate(orders.getOrderCome())) {
			conditions += " and A.ORDERCOME='" + orders.getOrderCome() + "'";
		}
		if (StringUtil.validate(orders.getOrderDate())) {
			conditions += " and A.ORDERDATE>=TO_DATE('"
					+ sdf.format(orders.getOrderDate())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(orders.getUpdateTime())) {
			conditions += " and A.ORDERDATE<=TO_DATE('"
					+ sdf.format(orders.getUpdateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate((orders.getZcZoning() == null ? new ZcZoning()
				: orders.getZcZoning()).getProvince())) {
			conditions += " and b.PROVINCE='"
					+ (orders.getZcZoning() == null ? new ZcZoning() : orders
							.getZcZoning()).getProvince() + "'";
		}
		if (StringUtil.validate((orders.getZcZoning() == null ? new ZcZoning()
				: orders.getZcZoning()).getCity())) {
			conditions += " and b.CITY='"
					+ (orders.getZcZoning() == null ? new ZcZoning() : orders
							.getZcZoning()).getCity() + "'";
		}
		if (StringUtil.validate((orders.getZcZoning() == null ? new ZcZoning()
				: orders.getZcZoning()).getCounty())) {
			conditions += " and d.parentid='"
					+ (orders.getZcZoning() == null ? new ZcZoning() : orders
							.getZcZoning()).getCounty() + "'";
		}
		if (StringUtil.validate((orders.getZcZoning() == null ? new ZcZoning()
				: orders.getZcZoning()).getStreet())) {
			conditions += " and a.branchid = '"
					+ (orders.getZcZoning() == null ? new ZcZoning() : orders
							.getZcZoning()).getStreet() + "'";
		}
		return conditions;
	}

	@Override
	public DataGrid getItemDataGridObj(Page page, Object obj)throws Exception {
		String sql = "select a.*,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark "
				+ " from zc_order_transit_item a "
				+ " left join zc_order_transit b on b.id= a.order_id "
				+ " left join zc_goods_master c on c.id=a.goodsFile_id "
				+ " where 1=1 ";
		sql += joinCondition1(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = distributionDao.getObjPagedList(page);
		Long total = distributionDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinCondition1(Object obj){
		ZcOrderItem orders = (ZcOrderItem) obj;
		String conditions = "";
		if (StringUtil.validate(orders.getOrder_id())) {
			conditions += " and A.ORDER_ID = '" + orders.getOrder_id() + "'";
		}
		if(StringUtil.validate(orders.getGoodsFile())){
			if(StringUtil.validate(orders.getGoodsFile().getSerialNumber())){
				conditions += " and C.serialNumber like '%" + orders.getGoodsFile().getSerialNumber() + "%'";
			}
		}
		if(StringUtil.validate(orders.getName())){
			conditions += " and A.NAME like '%" + orders.getName() + "%'";
		}
		return conditions;
	}
	
}
