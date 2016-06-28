package com.proem.exm.service.impl.promotion;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.promotion.SpecialPriceDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
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
				+" where 1=1 ";
		sql += joinCondition(obj);
		sql += " order by a.createTime asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = specialPriceDao.getObjPagedList(page);
		long count = specialPriceDao.getObjListCount(page);
		return new DataGrid(count, rows);
	}
	
	private String joinCondition(Object obj){
		ZcSalesPromotion promotion = (ZcSalesPromotion) obj;
		String condition = "";
		if(StringUtil.validate(promotion.getId())){
			condition += " and a.SALESPROMOTION_ID = '"+promotion.getId()+"'";
		}else
		{
			condition += " and a.SALESPROMOTION_ID is NULL";
		}
		return condition;
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

	@Override
	public DataGrid getPagedDataGridObj(Page page,
			ZcSalesPromotion zcSalesPromotion) throws Exception {
		String sql = "SELECT B.ID ,B.PROMOTION_NUMBER,B.PROMOTION_TITLE,B.PROMOTION_BEGIN_DATE,B.PROMOTION_END_DATE,B.MEMBER_LEVEL,B.STOP_MAN,B.STOP_DATE,B.CREATE_MAN ,B.check_state FROM  ZC_SALESPROMOTION B where 1=1 and b.PROMOTION_NUMBER like '%TJD%'";
		sql += joinCondition(zcSalesPromotion);
		sql += "  order by B.PROMOTION_NUMBER desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = specialPriceDao.getObjPagedList(page);
		Long total = specialPriceDao.getObjListCount(page);
		return new DataGrid(total,  rows);
	}

	private String joinCondition(ZcSalesPromotion zcSalesPromotion){
		String conditions = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.validate(zcSalesPromotion.getPromotionNumber())) {
			conditions += " and B.PROMOTION_NUMBER like '%" + zcSalesPromotion.getPromotionNumber()
					+ "%' ";
		}
		if (StringUtil.validate(zcSalesPromotion.getCheckState())) {
			conditions += " and B.CHECK_STATE like '%" + zcSalesPromotion.getCheckState()
					+ "%' ";
		}
		if (StringUtil.validate(zcSalesPromotion.getMemberLevel())) {
			conditions += " and B.MEMBER_LEVEL like '%" + zcSalesPromotion.getMemberLevel()
					+ "%' ";
		}
		if (StringUtil.validate(zcSalesPromotion.getCreateMan())) {
			conditions += " and B.CREATE_MAN like '%" + zcSalesPromotion.getCreateMan()
					+ "%' ";
		}
		if (StringUtil.validate(zcSalesPromotion.getPromotionBeginDate())) {
			conditions += " and B.PROMOTION_BEGIN_DATE>=TO_DATE('"
					+ sdf.format(zcSalesPromotion.getPromotionBeginDate())
					+ "', 'YYYY-MM-DD') ";
		}
		if (StringUtil.validate(zcSalesPromotion.getPromotionEndDate())) {
			conditions += " and B.PROMOTION_END_DATE<=TO_DATE('"
					+sdf.format(zcSalesPromotion.getPromotionEndDate())
					+ "', 'YYYY-MM-DD') ";
		}	
		return conditions;
		
	}

	@Override
	public DataGrid getPagedDataGridEidtObj(Object obj, Page page)
			throws Exception {
		String sql = "select a.id,  a.group_number,a.GOODSFILE_ID,a.BEGIN_TIME_FRAME,a.END_TIME_FRAME,a.nums,a.bargain_Price,a.full_Buy_Count,a.limit_number,"
				+" b.SERIALNUMBER,b.GOODS_NAME ,b.GOODS_PRICE,b.GOODS_PURCHASE_PRICE,b.GOODS_SPECIFICATIONS,b.GOODS_UNIT "        
				+" from ZC_SALESPROMOTIONITEM a left join ZC_GOODS_MASTER b on a.goodsFile_id = b.id "
				+ " left join zc_salespromotion z on z.id = a.SALESPROMOTION_ID "
				+" where 1=1 ";
		sql += joinEditCondition(obj);
		sql += " order by a.createTime asc";
		page.setSql(sql);
		List<Map<String, Object>> rows = specialPriceDao.getObjPagedList(page);
		long count = specialPriceDao.getObjListCount(page);
		return new DataGrid(count, rows);
	}
	
	private String joinEditCondition(Object obj){
		ZcSalesPromotion promotion = (ZcSalesPromotion) obj;
		String condition = "";
		if(StringUtil.validate(promotion.getId())){
			condition += " and a.SALESPROMOTION_ID = '"+promotion.getId()+"'";
		}
		if(StringUtil.validate(promotion.getZcCodeMode())){
			condition += " and z.ZCCODE_MODEID = '"+promotion.getZcCodeMode().getId()+"'";
		}
		return condition;
	}

}
