package com.proem.exm.service.impl.promotion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.promotion.SpecialPriceDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.promotion.SpecialPriceService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("specialPriceService")
public class SpecialPriceServiceImpl extends BaseServiceImpl implements SpecialPriceService {

	@Autowired
	private SpecialPriceDao specialPriceDao;
	
	@Override
	public DataGrid getPagedDataGridObj(Object obj, Page page) throws Exception {
		String sql = "select a.id,  a.group_number,a.GOODSFILE_ID,a.BEGIN_TIME_FRAME,a.END_TIME_FRAME,a.nums,a.bargain_Price,a.full_Buy_Count,a.limit_number,"
				+" b.SERIALNUMBER,b.GOODS_NAME ,b.GOODS_PRICE,b.GOODS_PURCHASE_PRICE,b.GOODS_SPECIFICATIONS,b.GOODS_UNIT "        
				+" from ZC_SALESPROMOTIONITEM a left join ZC_GOODS_MASTER b on a.goodsFile_id = b.id "
				+" where 1=1 and a.SALESPROMOTION_ID is NULL order by a.createTime asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = specialPriceDao.getObjPagedList(page);
		long count = specialPriceDao.getObjListCount(page);
		return new DataGrid(count, rows);
	}

	@Override
	public DataGrid getGoodsInfo(Page page, GoodsFile goodsFile)
			throws Exception {
		String sql = "select id, SERIALNUMBER, goods_name, goods_price as price , GOODS_SPECIFICATIONS from zc_goods_master where 1=1 ";
		sql += joinCondtion(goodsFile);
		sql += " order by SERIALNUMBER asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = specialPriceDao.getObjPagedList(page);
		long count = specialPriceDao.getObjListCount(page);
		return new DataGrid(count, rows);
	}

	private String joinCondtion(GoodsFile goodsFile) {
		String condition = "";
		if(StringUtil.validate(goodsFile.getSerialNumber())){
			condition += " and serialNumber like '%"+goodsFile.getSerialNumber()+"%'";
		}
		if(StringUtil.validate(goodsFile.getGoods_name())){
			condition += " and goods_name like '%"+goodsFile.getGoods_name()+"%'";
		}
		return condition;
	}

	


}
