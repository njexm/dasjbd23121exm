package com.proem.exm.service.wholesaleGroupPurchase.customer.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.wholesaleGroupPurchase.customer.CustomerInfoDao;
import com.proem.exm.entity.wholesaleGroupPurchase.customer.CustomerInfo;
import com.proem.exm.service.wholesaleGroupPurchase.customer.CustomerInfoService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

/**
 * 客户档案
 * @author ZuoYM 
 * @com proem
 */
@Service("customerInfoService")
public class CustomerInfoServiceImpl extends BaseServiceImpl implements CustomerInfoService {
	
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
	
	@Override
	public Map<String, Object> getAllByExcel(String path) {
		Map returnMap = new HashMap();
		String returnAnwer = "";
		List<CustomerInfo> list = new ArrayList<CustomerInfo>();
		
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(path));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;// 对应excel的行
			HSSFCell cell = null;// 对应excel的列
			int totalRow = sheet.getLastRowNum();
			for (int i = 1; i <= totalRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					CustomerInfo obj = new CustomerInfo();
					HSSFCell obj_id = row.getCell(0);
					if(getValue(obj_id) != null){
						obj.setId(getValue(obj_id));
					}
					HSSFCell obj_code = row.getCell(1);
					obj.setCode(getValue(obj_code) == null ? "" : getValue(obj_code));
					HSSFCell obj_name = row.getCell(2);
					obj.setName(getValue(obj_name) == null ? "" : getValue(obj_name));
					HSSFCell obj_memory_code = row.getCell(3);
					obj.setMnemonicCode(getValue(obj_memory_code) == null ? "" : getValue(obj_memory_code));
					HSSFCell defaultPrice = row.getCell(4);
					//obj.setDefaultPrice(getValue(defaultPrice) == null ? "" : getValue(defaultPrice));
					obj.setDefaultPrice("WHOLESALE_PRICE");
					HSSFCell discount = row.getCell(5);
					obj.setDiscount(getValue(discount) == null ? "" : getValue(discount));
					HSSFCell ownedStores = row.getCell(6);
					obj.setOwnedStores(getValue(ownedStores) == null ? "" : getValue(ownedStores));
					HSSFCell preferentialWay = row.getCell(7);
					obj.setPreferentialWay(getValue(preferentialWay)== null ? "" : getValue(preferentialWay));
					HSSFCell settlementcycle = row.getCell(8);
					obj.setSettlementcycle(getValue(settlementcycle)== null ? "" : getValue(settlementcycle));
					HSSFCell settlementdate = row.getCell(9);
					obj.setSettlementdate(getValue(settlementdate) == null ? "" : getValue(settlementdate));
					HSSFCell settlementway = row.getCell(10);
					//obj.setSettlementway(getValue(settlementway)== null ? "" : getValue(settlementway));
					obj.setSettlementway("指定帐期");
					HSSFCell creditLimit = row.getCell(11);
					obj.setCreditLimit(getValue(creditLimit) == null ? "" : getValue(creditLimit));
					HSSFCell saleman = row.getCell(12);
					obj.setSaleman(getValue(saleman) == null ? "" : getValue(saleman));
					HSSFCell area = row.getCell(13);
					//obj.setArea(getValue(area) == null ? "" : getValue(area));
					obj.setArea("本地");
					HSSFCell type = row.getCell(14);
					obj.setType(getValue(type) == null ? "" :getValue(type));
					HSSFCell linkman = row.getCell(15);
					obj.setLinkman(getValue(linkman) == null ? "" : getValue(linkman));
					HSSFCell address = row.getCell(16);
					obj.setAddress(getValue(address) == null ? "" : getValue(address));
					HSSFCell mail = row.getCell(17);
					obj.setMail(getValue(mail) == null ? "" : getValue(mail));
					HSSFCell telephone = row.getCell(18);
					obj.setTelephone(getValue(telephone) == null ? "" : getValue(telephone));
					HSSFCell bank = row.getCell(19);
					obj.setBank(getValue(bank) == null ? "" : getValue(bank));
					HSSFCell mobilephone = row.getCell(20);
					obj.setMobilephone(getValue(mobilephone) == null ? "" : getValue(mobilephone));
					HSSFCell taxregistration = row.getCell(21);
					obj.setTaxregistration(getValue(taxregistration) == null ? "" : getValue(taxregistration));
					HSSFCell fax = row.getCell(22);
					obj.setFax(getValue(fax) == null ? "" : getValue(fax));
					HSSFCell license = row.getCell(23);
					obj.setLicense(getValue(license) == null ? "" : getValue(license));
					HSSFCell postcode = row.getCell(24);
					obj.setPostcode(getValue(postcode) == null ? "" : getValue(postcode));
					HSSFCell account = row.getCell(25);
					obj.setAccount(getValue(account) == null ? "" : getValue(account));
					HSSFCell frozen = row.getCell(26);
					obj.setFrozen(getValue(frozen) == null ? "" : getValue(frozen));
					HSSFCell remark = row.getCell(27);
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

	@Autowired
	private CustomerInfoDao pustomerInfoDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_CUSTOMER_INFO  where 1=1 ";
		sql += joinCondition(obj);
		sql+=" order by createtime desc ";
		page.setSql(sql);
		List<Map<String, Object>> rows = pustomerInfoDao.getObjPagedList(page);
		Long total = pustomerInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_CUSTOMER_INFO where 1=1 ";
//			CustomerInfo customerInfo = (CustomerInfo) obj;
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询条件拼接
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		CustomerInfo customerInfo = (CustomerInfo) obj;
		String conditions = "";
		if (StringUtil.validate(customerInfo.getArea())) {
			conditions += " and area like '%" + customerInfo.getArea()
					+ "%'";
		}
		return conditions;
	}
	
	/**
	 * 获取客户信息列表
	 * @return
	 */
	@Override
	public List<CustomerInfo> getAllObject() {
		return baseDataMng.getAllObj(CustomerInfo.class);
	}
	
	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getlistJson(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_CUSTOMER_INFO where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
