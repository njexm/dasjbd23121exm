package com.proem.exm.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.proem.exm.dao.system.NoticeDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.NoticeService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.Page;

@Service
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeService {

	@Resource
	private NoticeDao noticeDao;

	@Override
	public DataGrid getPagedDataGridObj(Page page, Object obj) throws Exception {
		String sql = "select a.*,c.userName from zc_notice a left join ctp_user b on b.id=a.user_id left join zc_user_info c on c.user_id=b.id order by a.createtime desc";
		page.setSql(sql);
		List<Map<String, Object>> rows = noticeDao.getObjPagedList(page);
		Long total = noticeDao.getObjListCount(page);
		return new DataGrid(total, rows);
	}
}
