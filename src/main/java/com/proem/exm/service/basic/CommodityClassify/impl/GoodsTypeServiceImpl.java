package com.proem.exm.service.basic.CommodityClassify.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.CommodityClassify.GoodsTypeDao;
import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.service.basic.CommodityClassify.GoodsTypeService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl extends BaseServiceImpl implements
		GoodsTypeService {
	@Autowired
	GoodsTypeDao goodsTypeDao;

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getGoodsTypeObj(Page page, Object obj) throws Exception {
		String sql = "select * from zc_goodstype_info where 1=1";
		sql += joinCondition(obj);
		sql += " order by UPDATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsTypeDao.getObjPagedList(page);
		Long total = goodsTypeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		GoodsType goodsType = (GoodsType) obj;
		String conditions = "";
		if (StringUtil.validate(goodsType.getTimeLength())) {
			conditions += " and TIMELENGTH ='" + goodsType.getTimeLength()
					+ "'";
		}
		return conditions;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_GOODSTYPE_INFO where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public DataGrid getWorkStationObj(Page page, Object obj) throws Exception {
		String sql = "select * from ZC_WORKSTATION order by CREATETIME desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = goodsTypeDao.getObjPagedList(page);
		Long total = goodsTypeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

}
