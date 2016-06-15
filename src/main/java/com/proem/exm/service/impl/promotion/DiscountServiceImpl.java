package com.proem.exm.service.impl.promotion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cisdi.ctp.model.gen.Root;
import com.proem.exm.service.promotion.DiscountService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Service("discountService")
public class DiscountServiceImpl implements DiscountService {

	@Override
	public Object getObjById(String id, String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObjByCondition(Class classType, String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObjByName(String name, Class objClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteObjById(String id, String className) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveObj(Root root) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends Root> List<T> getListByObj(Class<T> classType,
			String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateObj(Root root) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends Root> Long getCountByObj(Class<T> classType,
			String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGrid getPagedDataGridObj(String className, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
