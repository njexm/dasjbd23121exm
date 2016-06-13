package com.proem.exm.service.impl.basic.provider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.basic.provider.ProviderInfoDao;
import com.proem.exm.entity.basic.provider.ProviderInfo;
import com.proem.exm.service.basic.provider.ProviderInfoService;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;
import com.proem.exm.views.basic.ProviderInfoView;

/**
 * 供应商基本信息逻辑业务实现类
 * 
 * @author jingbc
 * 
 * @com proem
 */
@Service("providerInfoService")
public class ProviderInfoServiceImpl extends BaseServiceImpl implements
		ProviderInfoService {
	@Autowired
	private ProviderInfoDao providerInfoDao;

	@Override
	public void updateObj(Object obj) {
		// ProviderInfoView providerInfoView = (ProviderInfoView) obj;
		// List<Object> paramList = new ArrayList<Object>();
		// String sql = "update ZC_PROVIDER_INFO set ";
		// if (StringUtil.validate(providerInfoView.getLinkman())) {
		// sql += " PROVIDER_LINKMAN='" + providerInfoView.getLinkman()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getTelephone())) {
		// sql += " PROVIDER_TELEPHONE='" + providerInfoView.getTelephone()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getMobilephone())) {
		// sql += " PROVIDER_MOBILEPHONE='"
		// + providerInfoView.getMobilephone() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getFax())) {
		// sql += " PROVIDER_FAX='" + providerInfoView.getFax() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getMail())) {
		// sql += " PROVIDER_MAIL='" + providerInfoView.getMail() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getPostcode())) {
		// sql += " PROVIDER_POSTCODE='" + providerInfoView.getPostcode()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getAddress())) {
		// sql += " PROVIDER_ADDRESS='" + providerInfoView.getAddress()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getArea())) {
		// sql += " PROVIDER_AREA='" + providerInfoView.getArea() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getBank())) {
		// sql += " PROVIDER_BANK='" + providerInfoView.getBank() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getEnterprisetype())) {
		// sql += " PROVIDER_ENTERPRISETYPE='"
		// + providerInfoView.getEnterprisetype() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getAccount())) {
		// sql += " PROVIDER_ACCOUNT='" + providerInfoView.getAccount()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getTaxregistration())) {
		// sql += " PROVIDER_TAXREGISTRATION='"
		// + providerInfoView.getTaxregistration() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getLicense())) {
		// sql += " PROVIDER_LICENSE='" + providerInfoView.getLicense()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getFrozen())) {
		// sql += " PROVIDER_FROZEN='" + providerInfoView.getFrozen() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getNickname())) {
		// sql += " PROVIDER_NICKNAME='" + providerInfoView.getNickname()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getSaleman())) {
		// sql += " PROVIDER_SALEMAN='" + providerInfoView.getSaleman()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getDeliverycycle())) {
		// sql += " PROVIDER_DELIVERYCYCLE='"
		// + providerInfoView.getDeliverycycle() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getPractice())) {
		// sql += " PROVIDER_PRACTICE='" + providerInfoView.getPractice()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getSettlement())) {
		// sql += " PROVIDER_SETTLEMENT='" + providerInfoView.getSettlement()
		// + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getSettlementway())) {
		// sql += " PROVIDER_SETTLEMENTWAY='"
		// + providerInfoView.getSettlementway() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getSettlementcycle())) {
		// sql += " PROVIDER_SETTLEMENTCYCLE='"
		// + providerInfoView.getSettlementcycle() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getSettlementdate())) {
		// sql += " PROVIDER_SETTLEMENTDATE='"
		// + providerInfoView.getSettlementdate() + "', ";
		// }
		// if (StringUtil.validate(providerInfoView.getRemark())) {
		// sql += " REMARK='" + providerInfoView.getRemark() + "' ";
		// }
		// sql += " where ID=?";
		// paramList.add(providerInfoView.getId());
		// try {
		// baseDataMng.querySqlUD(sql, paramList);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 字段转换成和views类中对应的
	 * 
	 * @return
	 */
	private String fieldMap() {
		String fields = "ID, " + "CREATETIME, " + "UPDATETIME, "
				+ "PROVIDER_NICKNAME, " + "PROVIDER_DELIVERYCYCLE, "
				+ "PROVIDER_PRACTICE, " + "PROVIDER_SETTLEMENT, "
				+ "PROVIDER_SETTLEMENTWAY, " + "PROVIDER_SALEMAN, "
				+ "PROVIDER_SETTLEMENTCYCLE, " + "PROVIDER_SETTLEMENTDATE, "
				+ "PROVIDER_AREA, " + "REMARK";
		return fields;
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinCondition(Object obj) {
		ProviderInfoView providerInfo = (ProviderInfoView) obj;
		String conditions = "";
		if (StringUtil.validate(providerInfo.getArea())) {
			conditions += " and PROVIDER_AREA like '%" + providerInfo.getArea()
					+ "%'";
		}
		return conditions;
	}

	/**
	 * 分页查询
	 */
	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,d.areaname as province,e.areaname as city,f.areaname as county,c.street as street from zc_provider_info a left join zc_zoning c on c.id=a.zoning_id left join zc_area d on d.id=c.province left join zc_area e on e.id=c.city left join zc_area f on f.id=c.county where 1=1 ";
		sql += joinCondition(obj);
		page.setSql(sql);
		List<Map<String, Object>> rows = providerInfoDao.getObjPagedList(page);
		Long total = providerInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_PROVIDER_INFO where 1=1 ";
			ProviderInfo providerInfo = (ProviderInfo) obj;
			if (StringUtil.validate(providerInfo.getArea())) {
				sql += " and PROVIDER_AREA like '%" + providerInfo.getArea()
						+ "%'";
			}
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList1(Object obj) {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todayStartTime = df.format(today) + " 00:00:00";
		String todayEndTime = df.format(today) + " 23:59:59";
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select a.id,a.provider_nickname from ZC_PROVIDER_INFO a left join zc_purchase_order b on b.provider_id=a.id left join zc_purchase_order_items c on c.purchaseid=b.id where c.sendflag is null and b.UPDATETIME between to_date('"
					+ todayStartTime
					+ "','YYYY-MM-DD HH24:MI:SS') and to_date('"
					+ todayEndTime
					+ "','YYYY-MM-DD HH24:MI:SS') group by a.id,a.provider_nickname ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<String, Object> getObjById(Object obj) {
		// TODO 自动生成的方法存根
		return null;
	}

}
