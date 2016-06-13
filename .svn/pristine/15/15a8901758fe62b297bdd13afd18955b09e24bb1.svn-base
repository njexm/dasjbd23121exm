package com.proem.exm.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.order.OrdersDao;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.basic.associator.Associator;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.order.OrdersService;
import com.proem.exm.utils.Constant;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.order.OrdersView;

@Service("ordersService")
public class OrdersServiceImpl extends BaseServiceImpl implements OrdersService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private OrdersDao ordersDao;

	public void updateObj(Object obj) {
		OrdersView ordersView = (OrdersView) obj;
		List<Object> paramList = new ArrayList<Object>();
		String sql = "update TEST_ORDERS set ";
		if (StringUtil.validate(ordersView.getOrderNum())) {
			sql += " ORDER_NUM='" + ordersView.getOrderNum() + "', ";
		}
		if (StringUtil.validate(ordersView.getSku())) {
			sql += " SKU='" + ordersView.getSku() + "', ";
		}
		if (StringUtil.validate(ordersView.getOrderAddress())) {
			sql += " ORDER_ADDRESS='" + ordersView.getOrderAddress() + "', ";
		}
		if (StringUtil.validate(ordersView.getStatus())) {
			sql += " STATUS='" + ordersView.getStatus() + "', ";
		}

		sql += "UPDATETIME=? where ID=?";

		paramList.add(ordersView.getUpdateTime());
		paramList.add(ordersView.getId());
		try {
			baseDataMng.querySqlUD(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字段转换成和views类中对应的
	 * 
	 * @return
	 */
	private String fieldMap() {
		String fields = "ID as id, "
				+ "to_char(CREATETIME, 'yyyy-mm-dd') as createTime, "
				+ "to_char(UPDATETIME, 'yyyy-mm-dd') as updateTime, "
				+ "ORDER_ADDRESS as orderAddress, " + "ORDER_NUM as orderNum, "
				+ "SKU as sku, " + "CUSTOMER_ID as customerId, "
				+ "STATUS as status";
		return fields;
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
		if (StringUtil
				.validate((orders.getAssociator() == null ? new Associator()
						: orders.getAssociator()).getAssociator_CardNumber())) {
			conditions += " and C.associator_CardNumber like '%"
					+ (orders.getAssociator() == null ? new Associator()
							: orders.getAssociator())
							.getAssociator_CardNumber() + "%'";
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

	/**
	 * 分页查询
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.province,b.city,b.county,b.street,c.associator_cardnumber as cardnumber from zc_order_history a left join zc_zoning b on b.id= a.zczoning_id left join zc_associator_info c on c.id=a.member_id left join zc_area_nanjing d on d.id=a.branchid  where 1=1";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页查询今日订单
	 */
	@Override
	public DataGrid getPagedDataGridObjTodayOrders(Page page, Object obj)
			throws Exception {
		ZcOrder zcOrder = Constant.ZC_ORDER;
		String sql = "select a.*,b.province,b.city,b.county,b.street,c.associator_cardnumber as cardnumber from ZC_ORDER a "
				+ " left join zc_zoning b on b.id= a.zczoning_id "
				+ " left join zc_associator_info c on c.id=a.member_id  "
				+ " left join zc_area_nanjing d on d.id=a.branchid "
				+ " where a.orderStatus = '1' ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询详细，by ID
	 */
	// public Map<String, Object> getObjById(Object obj) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// List<Object> paramList = new ArrayList<Object>();
	// try {
	// String sql = "select "+ fieldMap() +" from TEST_ORDERS where 1=1 ";
	// OrdersView orders = (OrdersView) obj;
	// if (StringUtil.validate(orders.getOrderNum())) {
	// sql += " and ORDER_NUM like '%" + orders.getOrderNum() + "%'";
	// }
	// if(StringUtil.validate(orders.getStatus())){
	// sql += " and STATUS='" + orders.getStatus() + "'";
	// }
	// if(StringUtil.validate(orders.getId())){
	// sql += " and ID=?";
	// paramList.add(orders.getId());
	// }
	//
	// List<Map<String, Object>> list = baseDataMng.querySql(sql, paramList);
	// if(list != null && list.size() > 0){
	// map = list.get(0);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return map;
	// }

	// public Object getObjById(String id, String className) {
	// OrdersView ordersView = new OrdersView();
	// Orders orders = baseDataMng.getObj(id, className);
	// //需要ORM中Object转换成页面对于的页面视图Object,此步骤必须做，虽然很啰嗦，但是符合了MVC设计模式
	// if(StringUtil.validate(orders)){
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// if(StringUtil.validate(orders.getCreateTime())){
	// String createTime = sdf.format(orders.getCreateTime());
	// ordersView.setCreateTime(createTime);
	// }
	// if(StringUtil.validate(orders.getUpdateTime())){
	// String updateTime = sdf.format(orders.getUpdateTime());
	// ordersView.setUpdateTime(updateTime);
	// }
	// ordersView.setId(orders.getId());
	// ordersView.setOrderAddress(orders.getOrderAddress());
	// ordersView.setOrderNum(orders.getOrderNum());
	// ordersView.setSku(orders.getSku());
	// ordersView.setStatus(orders.getStatus());
	// }
	// return ordersView;
	// }

	/**
	 * 无分页条件查询所有
	 */
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select " + fieldMap()
					+ " from TEST_ORDERS where 1=1 ";
			OrdersView orders = (OrdersView) obj;
			if (StringUtil.validate(orders.getOrderNum())) {
				sql += " and ORDER_NUM like '%" + orders.getOrderNum() + "%'";
			}
			if (StringUtil.validate(orders.getStatus())) {
				sql += " and STATUS='" + orders.getStatus() + "'";
			}
			if (StringUtil.validate(orders.getId())) {
				sql += " and ID=?";
				paramList.add(orders.getId());
			}
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 订单汇总查询
	 */
	private String joinConditionGoodsCode(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String conditions = "";
		// if (StringUtil.validate(goodsFile.getSerialNumber())) {
		// conditions += " and serialnumber like'%"
		// + goodsFile.getSerialNumber() + "%'";
		// }
		// if (StringUtil.validate(goodsFile.getGoods_name())) {
		// conditions += " and classify_name like'%"
		// + goodsFile.getGoods_name() + "%'";
		// }
		if (StringUtil.validate(goodsFile.getGoodsType())) {
			String typeId = goodsFile.getGoodsType().getId();
			conditions += " and b.goodstype_id = '" + typeId + "' ";
			GoodsType goodsType = (GoodsType) getObjById(typeId, "GoodsType");
			// 起始时间和时长在数据字典中的value
			String start = goodsType.getStartTime();
			String length = goodsType.getTimeLength();
			// 获取起始时间和时长在数据字典中的name
			List<Code> codeStart = getListByObj(Code.class,
					"codetype='StartTime' and codevalue='" + start + "'");
			List<Code> codeLength = getListByObj(Code.class,
					"codetype='TimeLength' and codevalue='" + length + "'");
			if (codeStart != null && codeStart.size() > 0) {
				Code code = codeStart.get(0);
				start = code.getCodeName();
			}
			if (codeLength != null && codeLength.size() > 0) {
				Code code1 = codeLength.get(0);
				length = code1.getCodeName();
			}
			// 获取当前时间
			Date date = new Date();
			double hour = date.getHours();
			Double startDbl = Double.valueOf(start);
			Double lengthDbl = Double.valueOf(length);
			int lengthInt = (new Double(lengthDbl)).intValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Date date2 = null;
			String startTime = "";
			String endTime = "";
			if (hour < startDbl) {
				calendar.add(Calendar.DAY_OF_MONTH, -(lengthInt + 1));
				date2 = calendar.getTime();
				startTime = sp.format(date2);
				calendar.add(Calendar.DAY_OF_MONTH, +lengthInt);
				date2 = calendar.getTime();
				endTime = sp.format(date2);
				conditions += " and e.orderdate >= to_date('" + startTime + " "
						+ start + ":00:00 ', 'YYYY-MM-DD HH24:MI:SS')"
						+ " and e.orderdate <= to_date('" + endTime + " "
						+ start + ":00:00 ', 'YYYY-MM-DD HH24:MI:SS')";
			} else {
				calendar.add(Calendar.DAY_OF_MONTH, -lengthInt);
				date2 = calendar.getTime();
				startTime = sp.format(date2);
				calendar.add(Calendar.DAY_OF_MONTH, +lengthInt);
				date2 = calendar.getTime();
				endTime = sp.format(date2);
				conditions += " and e.orderdate >= to_date('" + startTime + " "
						+ start + ":00:00 ', 'YYYY-MM-DD HH24:MI:SS')"
						+ " and e.orderdate <= to_date('" + endTime + " "
						+ start + ":00:00 ', 'YYYY-MM-DD HH24:MI:SS')";
			}
		}
		return conditions;
	}

	/**
	 * 展示今日订单商品统计
	 */
	@Override
	public DataGrid getPagedDataGridObjTodayTotal(Page page, Object obj,
			String startTime, String endTime) throws Exception {
		ZcOrder zcOrder = Constant.ZC_ORDER;
		String sql = "select sum(nums) as nums,name,sum(g_price*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,g_price as actualnums,goodsfile_id,goods_class_id,g_price from "
				+ "(select a.goods_state,a.name,a.nums,b.goods_specifications,b.goods_unit,a.g_price,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,b.goods_class_id,b.goods_supplier_id "
				+ "from ZC_ORDER_ITEM a "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ "left join ZC_ORDER d on a.order_id=d.id "
				+ "where a.goods_state = '1' ";
		sql += joinConditions(obj, startTime, endTime);
		sql += ") group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,g_price,goodsfile_id,goods_class_id order by serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinConditions(Object obj, String startTime, String endTime) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id = '"
						+ goodsFile.getGoods_class().getId()
						+ "' or c.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.name like '%" + goodsFile.getGoods_name()
					+ "%'";
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		if (StringUtil.validate(startTime)) {
			conditions += " and d.orderdate >= to_date('" + startTime
					+ "','yyyy-mm-dd HH24:mi:ss') ";
		}
		if (StringUtil.validate(endTime)) {
			conditions += " and d.orderdate <= to_date('" + endTime
					+ "','yyyy-mm-dd HH24:mi:ss')";
		}
		return conditions;
	}

	private String joinConditionTodayTotal(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id = '"
						+ goodsFile.getGoods_class().getId()
						+ "' or c.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and a.name like '%" + goodsFile.getGoods_name()
					+ "%'";
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		return conditions;
	}

	@Override
	public DataGrid getPagedDataGridObjTotal(Page page, Object obj)
			throws Exception {
		ZcOrder zcOrder = Constant.ZC_ORDER;
		java.util.Date start = zcOrder.getCreateTime();
		java.util.Date end = zcOrder.getUpdateTime();
		String sql = "select sum(nums) as nums,name,sum(g_price*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,g_price as actualnums,goodsfile_id,goods_class_id,g_price from "
				+ "(select a.name,a.nums,b.goods_specifications,b.goods_unit,a.g_price,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,d.orderdate,b.goods_class_id,a.goods_state,b.goods_supplier_id "
				+ "from zc_order_history_item a "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ "left join zc_order_history d on a.order_id=d.id "
				+ "where 1=1 "
				+ joinConditionTotal(obj)
				+ " )"
				+ " group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,g_price,goodsfile_id,goods_class_id order by serialNumber asc ";
		// sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinConditionTotal(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id = '"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getCreateTime())) {
			conditions += " and d.orderdate>=to_date('"
					+ sdf.format(goodsFile.getCreateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		if (StringUtil.validate(goodsFile.getUpdateTime())) {
			conditions += " and d.orderdate<=to_date('"
					+ sdf.format(goodsFile.getUpdateTime())
					+ "', 'YYYY-MM-DD HH24:MI:SS')";
		}
		return conditions;
	}

	@Override
	public DataGrid getHandleDataGrid(Page page, Object obj) throws Exception {
		String sql = "select sum(nums) as nums,goods_state,name,sum(goods_price*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,goods_price as actualnums,goodsfile_id,goods_class_id,goods_price from "
				+ "(select b.goodstype_id,a.createtime,a.goods_state,a.name,a.nums,b.goods_specifications,b.goods_unit,b.goods_price,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,b.goods_class_id,b.goods_supplier_id,d.id as goodstype_id "
				+ "from ZC_ORDER_process_ITEM a "
				+ "left join ZC_ORDER_process e on e.id = a.order_id "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ "left join zc_goodstype_info d on b.goodstype_id = d.id"
				+ " where a.goods_state = '2' "
				+ joinConditionTodayTotal(obj)
				+ joinTree(obj)
				+ ") "
				+ " group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,goods_price,goodsfile_id,goods_class_id,goods_state order by serialNumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getHandleDataGrid1(Page page, String id) throws Exception {
		String sql = "select sum(nums) as nums,goods_state,name,sum(goods_price*nums) as totalprice,classify_name,goods_unit,delFlag,goods_specifications,serialNumber,goods_price as actualnums,goodsfile_id,goods_class_id,goods_price from "
				+ "(select b.goodstype_id,a.createtime,a.goods_state,a.name,a.nums,b.goods_specifications,b.goods_unit,b.goods_price,b.id as goodsfile_id,b.delFlag,b.serialNumber,c.classify_name,b.goods_class_id,b.goods_supplier_id,d.id as goodstype_id "
				+ "from ZC_ORDER_process_ITEM a "
				+ "left join ZC_ORDER_process e on e.id = a.order_id "
				+ "left join zc_goods_master b on a.goodsfile_id = b.id "
				+ "left join zc_classify_info c on b.goods_class_id = c.id "
				+ "left join zc_goodstype_info d on b.goodstype_id = d.id"
				+ " where a.goods_state = '2' and b.id = '"
				+ id
				+ "') "
				+ " group by name,delFlag,classify_name,goods_unit,goods_specifications,serialNumber,goods_price,goodsfile_id,goods_class_id,goods_state ";
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinTree(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getGoodsType())) {
			if (StringUtil.validate(goodsFile.getGoodsType().getId())) {
				conditions += " and d.id = '"
						+ goodsFile.getGoodsType().getId() + "'";
			}
		}
		return conditions;
	}

	/**
	 * 树形菜单
	 */
	@Override
	public List<Map<String, Object>> getTreeData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<Object> paramList = new ArrayList<Object>();
			String sql = "select id,timelength,starttime,typename as text from zc_goodstype_info where 1=1 order by createtime asc";
			paramList.clear();
			paramList.add("0");
			Map<String, Object> mapFa = new HashMap<String, Object>();
			mapFa.put("id", "1");
			mapFa.put("text", "截单类型");
			list.add(mapFa);
			List<Map<String, Object>> subList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				paramList.clear();
				subList = baseDataMng.querySqlToLowerCase(sql, paramList);
				map.put("children", subList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DataGrid getPagedDataGridByHandle(Page page, ZcOrder zcOrder,
			String goodfileId) throws Exception {
		String sql = "select a.*,b.province,b.city,b.county,b.street,c.associator_cardnumber as cardnumber from ZC_ORDER_process a left join zc_zoning b on b.id= a.zczoning_id left join zc_associator_info c on c.id=a.member_id left join zc_order_process_item d on d.order_id=a.id  where 1=1 and d.goodsfile_id='"
				+ goodfileId + "' and d.goods_state='2'";
		sql += joinCondition(zcOrder);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getPagedDataGridByTodayOrders(Page page, ZcOrder zcOrder,
			String goodfileId) throws Exception {
		String sql = "select a.*,b.province,b.city,b.county,b.street,c.associator_cardnumber as cardnumber from ZC_ORDER a left join zc_zoning b on b.id= a.zczoning_id left join zc_associator_info c on c.id=a.member_id left join ZC_ORDER_ITEM d on d.order_id=a.id  where 1=1 and d.goodsfile_id='"
				+ goodfileId + "' ";
		sql += joinCondition(zcOrder);
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public DataGrid getServiceDataGrid(Page page, Object obj) throws Exception {
		String sql = "select a.*,c.classify_name,b.goods_class_id,b.goods_supplier_id,b.delflag,b.serialnumber,b.goods_name,b.goods_price,b.goods_specifications,d.provider_nickname"
				+ " from zc_customer_service a"
				+ " left join zc_goods_master b on b.id = a.goodsfile_id"
				+ " left join zc_classify_info c on b.goods_class_id = c.id"
				+ " left join zc_provider_info d on b.goods_supplier_id = d.id where b.delflag = '9' ";
		sql += joinCondition1(obj);
		sql += " order by b.serialnumber asc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	private String joinCondition1(Object obj) {
		GoodsFile goodsFile = (GoodsFile) obj;
		String conditions = "";
		if (StringUtil.validate(goodsFile.getSerialNumber())) {
			conditions += " and b.serialnumber like '%"
					+ goodsFile.getSerialNumber() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_name())) {
			conditions += " and b.goods_name like '%"
					+ goodsFile.getGoods_name() + "%'";
		}
		if (StringUtil.validate(goodsFile.getGoods_class())) {
			if (StringUtil.validate(goodsFile.getGoods_class().getId())) {
				conditions += " and b.goods_class_id = '"
						+ goodsFile.getGoods_class().getId()
						+ "' or c.parentid='"
						+ goodsFile.getGoods_class().getId() + "'";
			}
		}
		if (StringUtil.validate(goodsFile.getProvider())) {
			if (StringUtil.validate(goodsFile.getProvider().getId())) {
				conditions += " and b.goods_supplier_id = '"
						+ goodsFile.getProvider().getId() + "'";
			}
		}
		return conditions;
	}

	@Override
	public DataGrid getRefundServiceDataGrid(Page page, Object obj)
			throws Exception {
		String sql = "select a.*,b.ORDERNUM,b.ORDERCOME,b.CANSIGNPHONE,b.CONSIGNEE,b.GOODSNUM,c.street,d.associator_cardnumber as cardnumber"
				+ " from ZC_ORDER_REFUND a  "
				+ " left join ZC_ORDER_HISTORY b on a.ORDER_ID = b.id "
				+ " left join zc_zoning c on c.id= b.zczoning_id "
				+ " left join zc_associator_info d on d.id=b.member_id ";
		sql += joinCondition1(obj);
		sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = ordersDao.getObjPagedList(page);
		Long total = ordersDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

}
