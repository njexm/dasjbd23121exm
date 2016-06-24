package com.proem.exm.service.impl.promotion;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.promotion.BuyFullSendDao;
import com.proem.exm.entity.basic.branch.BranchTotal;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.salesPromotion.ZcSalesPromotion;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.promotion.BuyFullSendService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service("buyFullSendService")
public class BuyFullSendServiceImpl extends BaseServiceImpl implements BuyFullSendService{

	@Autowired BuyFullSendDao buyFullSendDao ;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		@Override
		public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
			String sql = "SELECT B.ID ,B.PROMOTION_NUMBER,B.PROMOTION_TITLE,B.PROMOTION_BEGIN_DATE,B.PROMOTION_END_DATE,B.MEMBER_LEVEL,B.STOP_MAN,B.STOP_DATE,B.CREATE_MAN ,B.check_state FROM  ZC_SALESPROMOTION B where 1=1 AND B.PROMOTION_NUMBER LIKE '%MMS%' ";
			sql += joinCondition(obj);
			sql += "  order by B.PROMOTION_NUMBER desc";
			page.setSql(sql);
			List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
			Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}

		private String joinCondition(Object obj){
			ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) obj;
			String conditions = "";
			if (StringUtil.validate(zcSalesPromotion.getPromotionNumber())) {
				conditions += " and B.PROMOTION_NUMBER like '% " + zcSalesPromotion.getPromotionNumber()
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
						+ "', 'YYYY-MM-DD HH24:MI:SS') ";
			}
			if (StringUtil.validate(zcSalesPromotion.getPromotionEndDate())) {
				conditions += " and B.PROMOTION_END_DATE<=TO_DATE('"
						+ sdf.format(zcSalesPromotion.getPromotionEndDate())
						+ "', 'YYYY-MM-DD HH24:MI:SS') ";
			}	
			return conditions;
			
		}

		@Override
		public DataGrid getPromotionAddGoods(Page page, String id, Object obj,
				CtpUser ctpUser) throws Exception {
			String sql = "";
				sql =" select a.*,b.id as goodsFiles_id,b.serialnumber,b.GOODS_CODE,b.GOODS_NAME,b.GOODS_PRICE,b.GOODS_PURCHASE_PRICE,b.GOODS_UNIT,b.GOODS_SPECIFICATIONS , "
						+" c.classify_name as className , c.classify_code as classCode,d.classify_name as brandName , d.classify_code as brandCode "
						+" from ZC_SALESPROMOTIONITEM a  "
						+" LEFT JOIN ZC_GOODS_MASTER b on a.GOODSFILE_ID = b.id "
						+" LEFT JOIN ZC_CLASSIFY_INFO c on c.id = a.class_classify_id "
						+" LEFT JOIN ZC_CLASSIFY_INFO d on d.id = a.brand_classify_id "
						+" where 1=1 "
						+" and a.SALESPROMOTION_ID is null ";
			sql += " order by b.serialnumber asc";
			page.setSql(sql);
			List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
			Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total, rows);
			
		}

		@Override
		public DataGrid getPagedDataGridBranch(Page page, Object obj)
				throws Exception {
			String sql = "SELECT B.ID,B.BRANCH_CODE,B.BRANCH_NAME  FROM  ZC_BRANCH_TOTAL B where 1=1 ";
			sql += joinBranchCondition(obj);
			sql += "  order by B.BRANCH_CODE asc";
			page.setSql(sql);
			List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
			Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}
		private  String joinBranchCondition(Object obj){
			BranchTotal branchTotal = (BranchTotal) obj;
			String conditions = "";
			if (StringUtil.validate(branchTotal.getBranch_code())) {
				conditions += " and B.BRANCH_CODE like '%" + branchTotal.getBranch_code()
						+ "%' ";
			}
			if (StringUtil.validate(branchTotal.getBranch_name())) {
				conditions += " and B.BRANCH_NAME like '%" + branchTotal.getBranch_name()
						+ "%' ";
			}
			return conditions;
			
		}

		@Override
		public DataGrid getPagedDataGridBrand(Page page, Object obj,String zcCodeScope)
				throws Exception {
			String sql="";
			if (Double.parseDouble(zcCodeScope) ==2) {
				 sql = "SELECT B.* FROM ZC_CLASSIFY_INFO B  where 1=1 and B.PARENTID is not NULL  ";
			}
			if (Double.parseDouble(zcCodeScope) ==3) {
				sql = "SELECT B.* FROM ZC_CLASSIFY_INFO B LEFT JOIN ZC_GOODS_MASTER C on  C.GOODS_BRAND_ID =B.ID where 1=1 and B.PARENTID is  NULL  ";
			}
			sql += joinGoodsCondition(obj,zcCodeScope);
			sql += "  order by B.CLASSIFY_CODE asc ";
			page.setSql(sql);
			List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
			Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}
		
		@Override
		public DataGrid getPagedDataGridGoods(Page page, Object obj)
				throws Exception {
			String sql = "SELECT B.*  FROM  ZC_GOODS_MASTER B where 1=1 ";
			sql += joinGoodsCondition(obj,"");
			sql += "  order by B.GOODS_CODE asc";
			page.setSql(sql);
			List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
			Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}
		
		private  String joinGoodsCondition(Object obj,String zcCodeScope){
			GoodsFile goodsFile = (GoodsFile) obj;
			String conditions = "";
			if (StringUtil.validate(goodsFile.getSerialNumber())) {
				if (zcCodeScope==null||zcCodeScope.equals("")) {
					conditions += " and B.SerialNumber like '%" + goodsFile.getSerialNumber()
							+ "%' ";
				}else
				if (Double.parseDouble(zcCodeScope)==2) {
					conditions += " and B.CLASSIFY_CODE like '%" + goodsFile.getSerialNumber()
							+ "%' ";
				}else
				if (Double.parseDouble(zcCodeScope)==3) {
					conditions += " and B.CLASSIFY_CODE like '%" + goodsFile.getSerialNumber()
							+ "%' ";
				}
				
			}
			if (StringUtil.validate(goodsFile.getGoods_name())) {
				if (zcCodeScope==null||zcCodeScope.equals("")) {
					conditions += " and B.Goods_name like '%" + goodsFile.getGoods_name()
							+ "%' ";
				}else
				if (Double.parseDouble(zcCodeScope)==2) {
					conditions += " and B.CLASSIFY_NAME like '%" + goodsFile.getGoods_name()
							+ "%' ";
				}else
				if (Double.parseDouble(zcCodeScope)==3) {
					conditions += " and B.CLASSIFY_NAME like '%" + goodsFile.getGoods_name()
							+ "%' ";
				}
				
			}
			return conditions;
			
		}
		
		@Override
		public DataGrid getPromotionEditGoods(Page page, Object obj)
				throws Exception {
			String sql =" select a.*,b.id as goodsFiles_id,b.serialnumber,b.GOODS_CODE,b.GOODS_NAME,b.GOODS_PRICE,b.GOODS_PURCHASE_PRICE,b.GOODS_UNIT,b.GOODS_SPECIFICATIONS , "
					+" c.classify_name as className , c.classify_code as classCode,d.classify_name as brandName , d.classify_code as brandCode "
					+" from ZC_SALESPROMOTIONITEM a  "
					+" LEFT JOIN ZC_GOODS_MASTER b on a.GOODSFILE_ID = b.id "
					+" LEFT JOIN ZC_CLASSIFY_INFO c on c.id = a.class_classify_id "
					+" LEFT JOIN ZC_CLASSIFY_INFO d on d.id = a.brand_classify_id "
					+" LEFT JOIN ZC_SALESPROMOTION f on f.id = a.SALESPROMOTION_id "
					+" where 1=1 ";
				sql += joinEidtConditions(obj);	
				page.setSql(sql);
				List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
				Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}
		
		public String joinEidtConditions(Object obj ){
			ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) obj;
			String conditions = "";
			if (StringUtil.validate(zcSalesPromotion.getId())) {
				conditions += " and f.id like '%" + zcSalesPromotion.getId()
						+ "%' ";
			}
			if (StringUtil.validate(zcSalesPromotion.getZcCodeMode().getId())) {
				conditions += " and f.zccode_modeid like '%" + zcSalesPromotion.getZcCodeMode().getId()
						+ "%' ";
			}
			if (StringUtil.validate(zcSalesPromotion.getZcCodeScope().getId())) {
				conditions += " and f.zccode_scopeid like '%" + zcSalesPromotion.getZcCodeScope().getId()
						+ "%' ";
			}
			return conditions;
		}
		
		@Override
		public DataGrid getPromotionDeailGoods(Page page, Object obj)
				throws Exception {
			String sql =" select a.*,b.id as goodsFiles_id,b.serialnumber,b.GOODS_CODE,b.GOODS_NAME,b.GOODS_PRICE,b.GOODS_PURCHASE_PRICE,b.GOODS_UNIT,b.GOODS_SPECIFICATIONS , "
					+" c.classify_name as className , c.classify_code as classCode,d.classify_name as brandName , d.classify_code as brandCode "
					+" from ZC_SALESPROMOTIONITEM a  "
					+" LEFT JOIN ZC_GOODS_MASTER b on a.GOODSFILE_ID = b.id "
					+" LEFT JOIN ZC_CLASSIFY_INFO c on c.id = a.class_classify_id "
					+" LEFT JOIN ZC_CLASSIFY_INFO d on d.id = a.brand_classify_id "
					+" LEFT JOIN ZC_SALESPROMOTION f on f.id = a.SALESPROMOTION_id "
					+" where 1=1 ";
				sql += joinDetailConditions(obj);	
				page.setSql(sql);
				List<Map<String, Object>> rows = buyFullSendDao.getObjPagedList(page);
				Long total = buyFullSendDao.getObjListCount(page);
			return new DataGrid(total,  rows);
		}
		
		public String joinDetailConditions(Object obj ){
			ZcSalesPromotion zcSalesPromotion = (ZcSalesPromotion) obj;
			String conditions = "";
			if (StringUtil.validate(zcSalesPromotion.getId())) {
				conditions += " and f.id like '%" + zcSalesPromotion.getId()
						+ "%' ";
			}
			if (StringUtil.validate(zcSalesPromotion.getPromotionNumber())) {
				conditions += " and f.Promotion_number like '%" + zcSalesPromotion.getPromotionNumber()
						+ "%' ";
			}
			
			return conditions;
		}

}
