package com.proem.exm.service.impl.testmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.testmodel.TestDao;
import com.proem.exm.entity.TestTable;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.testmodel.TestService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Service("testServiceImpl")
public class TestServiceImpl extends BaseServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Override
	public List<TestTable> getObjList() {
		return baseDataMng.getAllObj(TestTable.class);
	}

	@Override
	public void updateObj(TestTable testTable) {
		String sql = "update TEST_TABLE a set a.FIELD1=? , a.FIELD2=? , a.FIELD3= ? where a.id=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(testTable.getField1());
		paramList.add(testTable.getField2());
		paramList.add(testTable.getField3());
		paramList.add(testTable.getId());
		try {
			baseDataMng.querySqlUD(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Map<String, Object> paramMap) {
		String sql = "select * from TEST_TABLE a where a.id = ?";
		if (null != paramMap.get("userName")) {
			sql += " and c.actualName like '%"
					+ paramMap.get("userName").toString() + "%'";
		}
		page.setSql(sql);

		List<Map<String, Object>> rows = testDao.getObjPagedList(page);
		Long total = testDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public Map<String, Object> getObjById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> paramList = new ArrayList<Object>();
		try {
			String sql = "select * from TEST_TABLE a where a.id = ?";
			paramList.add(id);
			List<Map<String, Object>> list = baseDataMng.querySql(sql, paramList);
			if(list != null && list.size() > 0){
				map = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
