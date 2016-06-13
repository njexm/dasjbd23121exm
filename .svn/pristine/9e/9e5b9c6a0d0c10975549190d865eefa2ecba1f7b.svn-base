package com.proem.exm.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cisdi.ctp.utils.common.UuidUtils;
import com.proem.exm.dao.system.LogManageDao;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.system.CtpUser;
import com.proem.exm.entity.system.LogManage;
import com.proem.exm.entity.system.ZcUserInfo;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.LogManageService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;
import com.proem.exm.utils.StringUtil;

@Service
public class LogManageServiceImpl extends BaseServiceImpl implements LogManageService{

	@Resource
	private LogManageDao logManageDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,c.userName from zc_log a left join ctp_user b on b.id=a.user_id left join zc_user_info c on c.user_id=b.id where 1=1 ";
			sql += joinCondition(obj);
			sql += " order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = logManageDao.getObjPagedList(page);
		Long total = logManageDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
	private String joinCondition(Object obj) {
		String conditions = "";
		LogManage logManage = (LogManage) obj;
		if (StringUtil.validate(logManage.getUser())) {
			if (StringUtil.validate(logManage.getUser().getId())) {
				conditions += " and a.user_id = '" + logManage.getUser().getId()+ "'";
			}
		}
		return conditions;
	}
	@Override
	public void insertLog(HttpServletRequest request,String description,String moduleName) {
		LogManage logManage=new LogManage();
		logManage.setId(UuidUtils.getUUID());
		logManage.setCreateTime(new Date());
		CtpUser user=(CtpUser) request.getSession().getAttribute("user");
		ZcUserInfo userInfo=(ZcUserInfo) request.getSession().getAttribute("userInfo");
		String Ip=(String) request.getSession().getAttribute("IP");
		logManage.setUser(user);
		logManage.setDescription(""+userInfo.getUserName()+""+description+"");
		logManage.setModuleName(moduleName);
		logManage.setIpAddress(Ip);
		this.saveObj(logManage);
		
	}


}
