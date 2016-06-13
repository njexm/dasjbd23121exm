package com.proem.exm.service.order.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.order.OrdersItemDao;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.order.ZcOrderHistoryItem;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.order.ZcOrderProcessItem;
import com.proem.exm.entity.order.ZcOrderRefund;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.order.OrdersItemService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("ordersItemService")
public class OrdersItemServiceImpl extends BaseServiceImpl implements OrdersItemService {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private OrdersItemDao ordersItemDao;
	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj){
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

	/**
	 * 分页查询
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception{
		String sql = "select a.*,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark from zc_order_history_item a left join zc_order_history b on b.id= a.order_id left join zc_goods_master c on c.id=a.goodsFile_id where 1=1 ";
		sql += joinCondition1(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		Long total = ordersItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	@Override
	public DataGrid getServiceDataGridObj(Page page, Object obj) throws Exception{
		String sql = "select b.id as order_id,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark,a.nums,a.g_price,a.id,c.goods_name"
				+ " from zc_order_process_item a "
				+ " left join zc_order_process b on b.id= a.order_id "
				+ " left join zc_goods_master c on c.id=a.goodsFile_id "
				+ " where 1=1 ";
		sql += joinCondition2(obj);
		sql +=    " union select b.id as order_id,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark,a.nums,a.g_price,a.id,c.goods_name"
				+ " from zc_order_item a "
				+ " left join zc_order b on b.id= a.order_id "
				+ " left join zc_goods_master c on c.id=a.goodsFile_id"
				+ " where 1=1 ";
		sql += joinCondition2(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		Long total = ordersItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinCondition2(Object obj) {
		ZcOrderProcessItem orderProcessItem = (ZcOrderProcessItem) obj;
		String conditions = "";
		if (StringUtil.validate(orderProcessItem.getOrder_id())) {
			conditions += " and b.id = '" + orderProcessItem.getOrder_id() + "'";
		}
		if(StringUtil.validate(orderProcessItem.getGoodsFile())){
			if(StringUtil.validate(orderProcessItem.getGoodsFile().getSerialNumber())){
				conditions += " and c.serialNumber like '%" + orderProcessItem.getGoodsFile().getSerialNumber() + "%'";
			}
		}
		if(StringUtil.validate(orderProcessItem.getName())){
			conditions += " and a.name like '%" + orderProcessItem.getName() + "%'";
		}
		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridObj1(Page page, Object obj) throws Exception{
		String sql = "select a.*,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark from ZC_ORDER_ITEM a left join ZC_ORDER b on b.id= a.order_id left join zc_goods_master c on c.id=a.goodsFile_id where 1=1 ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		Long total = ordersItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinCondition1(Object obj){
		ZcOrderHistoryItem orders = (ZcOrderHistoryItem) obj;
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

	
	@Override
	public DataGrid getOrderProcessDataGridObj(Page page, Object obj) throws Exception{
		String sql = "select a.*,c.serialnumber,c.goods_unit,c.goods_specifications,c.remark from zc_order_process_item a left join zc_order_process b on b.id= a.order_id left join zc_goods_master c on c.id=a.goodsFile_id where 1=1 ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		Long total = ordersItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getOrderItemListBycon(Object obj) throws Exception {
		String sql = "select a.* from ZC_ORDER_ITEM a left join zc_order b on b.id= a.order_id left join zc_goods_master c on c.id=a.goodsFile_id left join zc_goodstype_info d  on d.id=c.goodstype_id where 1=1 and a.goods_state='1'";
		sql += joinQueryByCondition(obj);
		Page page=new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		return rows;
	}

	private String joinQueryByCondition(Object obj) throws ParseException{
		GoodsType goodsType=(GoodsType) obj;
		String conditions="";
		if (StringUtil.validate(goodsType.getStartTime())) {
			int time=Integer.valueOf(goodsType.getStartTime())-10;
			conditions += " and d.startTime = '" +goodsType.getStartTime() + "'";
			if (StringUtil.validate(goodsType.getTimeLength())) {
				int timeLength=Integer.valueOf(goodsType.getTimeLength())-10;
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - timeLength);
				Date endDate = sdf.parse(sdf.format(date.getTime()));
				conditions += " and b.createtime >= to_date('"+sdf.format(endDate)+"', 'YYYY-MM-DD HH24:MI:SS')";
				conditions += " and b.createtime <= to_date('"+sdf.format(new Date())+"', 'YYYY-MM-DD HH24:MI:SS')";
			}
		}
		return conditions;
	}

	@Override
	public DataGrid getRefundDataGrid(Page page, Object obj) throws Exception{
		String sql = "select a.*,b.GOODS_NAME,b.GOODS_SPECIFICATIONS,b.GOODS_UNIT,c.classify_name,d.provider_nickname"
				+ " from ZC_ORDER_REFUND_ITEM a  "
				+ " left join ZC_GOODS_MASTER b on a.goodsfile_id = b.id"
				+ " left join zc_classify_info c on b.goods_class_id = c.id"
				+ " left join zc_provider_info d on b.goods_supplier_id = d.id"
				+ " where 1=1 ";
		sql += joinConditionRefund(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersItemDao.getObjPagedList(page);
		Long total = ordersItemDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	
	private String joinConditionRefund(Object obj){
		ZcOrderRefund orders = (ZcOrderRefund) obj;
		String conditions = "";
		if (StringUtil.validate(orders.getId())) {
			conditions += " and A.refund_id = '" + orders.getId() + "'";
		}
		return conditions;
	}
}
