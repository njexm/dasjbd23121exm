package com.proem.exm.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proem.exm.dao.system.ZcUserInfoDao;
import com.proem.exm.entity.basic.goodsFile.GoodsFile;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.ZcUserInfoService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service("zcUserInfoService")
public class ZcUserInfoServiceImpl extends BaseServiceImpl implements
		ZcUserInfoService {

	@Resource
	private ZcUserInfoDao zcUserInfoDao;

	@Transactional
	public boolean saveOrUpdate(ZcUserInfo zcUserInfo) {
		String id = zcUserInfo.getId();
		if (StringUtil.isEmpty(id)) {
			zcUserInfo
					.setId(String.valueOf(UUID.randomUUID()).replace("-", ""));
			zcUserInfo.setCreateTime(new Date());
			zcUserInfo.setUpdateTime(new Date());
			this.saveObj(zcUserInfo);
			return true;
		} else {
			zcUserInfo.setUpdateTime(new Date());
			int i = zcUserInfoDao.updateUserInfo(zcUserInfo);
			if (i == 1) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public boolean deleteUserInfo(String ids) {
		String[] idArr = ids.split(",");
		String str = "";
		for (int i = 0; i < idArr.length; i++) {
			if (StringUtil.isNotEmpty(idArr[i])) {
				str += ",'" + idArr[i] + "'";
			}
		}
		if (StringUtil.isNotEmpty(str)) {
			str = str.substring(1);
		}
		try {
			zcUserInfoDao.deleteUserInfos(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public DataGrid getUserInfoDataGrid(ZcUserInfo zcUserInfo,
			SharePager sharePager) {
		return null;
	}

	@Override
	public ZcUserInfo getUserInfoById(String id) {
		String condition = "user_id='" + id + "'";
		return baseDataMng.getObjByCondition(ZcUserInfo.class, condition);
	}

	@Override
	public String getUserInfoNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select t.*, a.name as loginName,d.areaname as province,e.areaname as city,f.areaname  as county,b.street as street,c.name as roleName from ZC_USER_INFO t left join ctp_user a on a.id=t.user_id left join zc_zoning b on b.id = t.zoning_id left join ctp_role c on c.id=t.role_id left join zc_area d on d.id=b.province left join zc_area e on e.id=b.city left join zc_area f on f.id=b.county ";
		page.setSql(sql);
		List<Map<String, Object>> rows = zcUserInfoDao.getObjPagedList(page);
		Long total = zcUserInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	@Override
	public List<Map<String, Object>> getUserInfoList() {
		String sql = "select t.*, a.name as loginName,a.id as userId,b.province as province,b.city as city,b.county  as county,b.street as street,c.name as roleName from ZC_USER_INFO t "
				+ "left join ctp_user a on a.id=t.user_id "
				+ "left join zc_zoning b on b.id = t.zoning_id "
				+ "left join ctp_role c on c.id=t.role_id";
		Page page = new Page();
		page.setPage(1);
		page.setRows(100000);
		page.setSql(sql);
		try {
			List<Map<String, Object>> rows = zcUserInfoDao
					.getObjPagedList(page);
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DataGrid getPagedDataOfGridObj(Page page, Object obj)
			throws Exception {
		String sql = "select t.*, a.name as loginName,d.areaname as province,e.areaname as city,f.areaname  as county,b.street as street,c.name as roleName from ZC_USER_INFO t left join ctp_user a on a.id=t.user_id left join zc_zoning b on b.id = t.zoning_id left join ctp_role c on c.id=t.role_id left join zc_area d on d.id=b.province left join zc_area e on e.id=b.city left join zc_area f on f.id=b.county where 1=1";
		sql += joinConditionUserName(obj);
		sql += " order by t.createtime";
		page.setSql(sql);
		List<Map<String, Object>> rows = zcUserInfoDao.getObjPagedList(page);
		Long total = zcUserInfoDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}

	/**
	 * 查询条件拼接
	 * 
	 * @param obj
	 * @return
	 */
	private String joinConditionUserName(Object obj) {
		ZcUserInfo userInfo = (ZcUserInfo) obj;
		String conditions = "";
		if (StringUtil.validate(userInfo.getUserName())) {
			conditions += " and a.name like'%" + userInfo.getUserName() + "%'";
		}
		return conditions;
	}
	
	/**
	 * 按条件查询
	 */
	@Override
	public List<Map<String, Object>> getObjList1(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_USER_INFO where 1=1 ";
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
	public List<Map<String, Object>> getObjList2(Object obj) {
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, Object>> list = null;
		try {
			String sql = "select * from ZC_WORKSTATION where 1=1 ";
			list = baseDataMng.querySql(sql, paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取用户列表
	 */
	@Override
	public List<ZcUserInfo> getAllObject() {
		return baseDataMng.getAllObj(ZcUserInfo.class);
	}

}
