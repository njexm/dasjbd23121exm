package com.proem.exm.service.impl.warehouse;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisdi.ctp.datamng.BaseDataMng;
import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.dao.basic.gooodsFile.GoodsFileDao;
import com.proem.exm.dao.warehouse.SwitchChangeDao;
import com.proem.exm.entity.basic.branch.Branch;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.warehouse.SwitchGoodsItems;
import com.proem.exm.entity.warehouse.ZcCheckNumber;
import com.proem.exm.entity.warehouse.ZcSwitchhouse;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.BaseService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.warehouse.SwitchChangeService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 
 *
 */
@Service("SwitchChangeService")
public class SwitchChangeServiceImpl extends BaseServiceImpl implements
		SwitchChangeService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	SwitchChangeDao switchChangeDao;
	@Autowired
	GoodsFileDao goodsFileDao;
	
	@Resource
	public BaseDataMng baseDataMng;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,b.branch_code as frombranch_code,b.branch_name as frombranch_name,d.branch_code as tobranch_code,d.branch_name as tobranch_name,c.username from ZC_switchhouse a left join zc_branch_info b on b.id=a.frombranch_id left join zc_branch_info d on d.id=a.tobranch_id left join zc_user_info c on c.user_id=a.createuser_id  where 1=1";
		sql += joinCondition(obj);
		sql += "order by a.createTime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = switchChangeDao.getObjPagedList(page);
		Long total = switchChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ZcSwitchhouse zcSwitchhouse = (ZcSwitchhouse) obj;
		String conditions = "";
		if (StringUtil.validate(zcSwitchhouse.getSwitch_Number())) {
			conditions += " and a.switch_Number like '%"
					+ zcSwitchhouse.getSwitch_Number() + "%'";
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
		String sql = "select a.id,f.branch_code,f.branch_name,h.classify_name as brandName,g.classify_name as className,e.serialnumber,e.goods_code,e.goods_name,e.goods_price,a.differencereason,a.actualchecknumber,a.store from zc_check_items a "
				+ "left join zc_goods_master e on e.id=a.goodsfile_id "
				+ " left join zc_classify_info g on g.id=e.goods_class_id "
				+ "left join zc_classify_info h on h.id=e.goods_brand_id "
				+ "left join zc_warehouse b on a.warehouse_id=b.id "
				+ "left join zc_checknumber c on c.id=b.checknumber_id "
				+ "left join zc_storehouse j on j.branch_id=c.branch_id and j.goodsfile_id=e.id "
				+ "left join zc_branch_info f on f.id=c.branch_id "
				+ "left join zc_checkdifference d on d.checknumber_id=c.id "
				+ "where d.id='" + id + "'";
		page.setSql(sql);
		List<Map<String, Object>> rows = switchChangeDao.getObjPagedList(page);
		Long total = switchChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 分页展示采购订单商品表中采购订单为空的数据
	 */
	@Override
	public DataGrid getChangeAddGoods(Page page, String id, Object obj,
			CtpUser ctpUser) throws Exception {
		String sql = "";
		if (StringUtils.isBlank(id)) {
			sql = "select a.*,b.goods_code,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price,b.goods_unit from zc_switch_items a left join zc_goods_master b on a.goodsfile_id=b.id where a.ZCSWITCHHOUSE_ID is null and a.CREATEUSER_ID='"
					+ ctpUser.getId() + "'";
		} else {
			ZcSwitchhouse storeChange = (ZcSwitchhouse) this.getObjById(id,
					"ZcSwitchhouse");
			Branch branch = storeChange.getFromBranch();
			sql = "select a.*,c.store,b.goods_code,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price,b.goods_unit from zc_switch_items a left join zc_goods_master b on a.goodsfile_id=b.id  left join zc_storehouse c on c.branch_id='"
					+ branch.getId()
					+ "' and c.goodsfile_id=b.id where a.ZCSWITCHHOUSE_ID='"
					+ id + "'";
		}
		sql += conditions(obj);
		sql += " order by b.serialnumber asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = switchChangeDao.getObjPagedList(page);
		Long total = switchChangeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String conditions(Object obj) {
		SwitchGoodsItems orders = (SwitchGoodsItems) obj;
		String conditions = "";
		// if (StringUtil.validate(orders.getSerialNumber())) {
		// conditions += " and SERIALNUMBER like '%"
		// + orders.getSerialNumber() + "%'";
		// }
		return conditions;
	}

	/**
	 * 分页展示添加商品
	 */
	@Override
	public DataGrid getSwitchChangeItems(Page page, Object obj,
			String switchOutBranch) throws Exception {
		String sql = "select a.store,a.branch_id,b.id as goodsfile_id,b.serialnumber,b.goods_name,b.goods_specifications,b.goods_price "
				+ "from zc_storehouse a left join zc_goods_master b on b.id=a.goodsfile_id where a.branch_id='"
				+ switchOutBranch + "' and a.store > 0";
		sql += addConditions(obj);
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
	private String addConditions(Object obj) {
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
		return conditions;
	}

	@Override
	public Map<String, Object> getAllByExcel(String path, CtpUser user) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<SwitchGoodsItems> list = new ArrayList<SwitchGoodsItems>();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();
			for (int i = 4; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					SwitchGoodsItems obj = new SwitchGoodsItems();
					HSSFCell serialNumber = row.getCell(0);
					String serail = getValue(serialNumber) == null ? "" : getValue(serialNumber);
					GoodsFile goodsFile = (GoodsFile) baseDataMng.getObjByCondition(GoodsFile.class, " serialNumber = '"+serail+"'");
					obj.setGoodsFile(goodsFile);
					obj.setCreateUser(user);
					
					HSSFCell priceStr = row.getCell(4);
					double price = getValue(priceStr) == null ? 0 : Double.valueOf(getValue(priceStr));
					HSSFCell numsStr = row.getCell(5);
					double nums = getValue(numsStr) == null ? 0 : Double.valueOf(getValue(numsStr));
					obj.setChangeNumber(getValue(numsStr) == null ? "" : getValue(numsStr));
					obj.setChangeAmount(String.valueOf(nums * price));
					HSSFCell remark = row.getCell(6);
					obj.setRemark(getValue(remark) == null ? "" : getValue(remark));
					list.add(obj);
				}
			}
			returnMap.put("returnAnwer", returnAnwer);
			returnMap.put("listSupply", list);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell != null) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				// 返回数值类型的值
				return String.valueOf(hssfCell.getNumericCellValue());
			} else {
				// 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
		} else {
			return null;
		}
	}
}
