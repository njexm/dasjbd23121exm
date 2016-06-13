package com.proem.exm.service.system.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proem.exm.dao.system.ZcZoningDao;
import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.system.ZcZoningService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;
import com.proem.exm.utils.StringUtil;

@Service
public class ZcZoningServiceImpl extends BaseServiceImpl implements ZcZoningService{

	@Resource
	private ZcZoningDao zcZoningDao;
	@Transactional
	public boolean saveOrUpdate(ZcZoning  zcZoning) {
		String id = zcZoning.getId();
		if(StringUtil.isEmpty(id)){
			zcZoning.setId(String.valueOf(UUID.randomUUID()).replace("-", ""));
			zcZoning.setCreateTime(new Date());
			zcZoning.setUpdateTime(new Date());
			int i = zcZoningDao.addZoning(zcZoning);
			if(i == 1){
				return true;
			}
		}else{
			zcZoning.setUpdateTime(new Date());
			int i = zcZoningDao.updateZoning(zcZoning);
			if(i == 1){
				return true;
			}
		}
		return false;
	}
	
	
	@Transactional
	public boolean deleteZoning(String ids) {
		String[] idArr = ids.split(",");
		String str = "";
		for(int i = 0;i<idArr.length;i++){
			if(StringUtil.isNotEmpty(idArr[i])){
				str+=",'"+idArr[i]+"'";
			}
		}
		if(StringUtil.isNotEmpty(str)){
			str = str.substring(1);
		}
		try {
			zcZoningDao.deleteZonings(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public DataGrid getZoningDataGrid(ZcZoning zcZoning, SharePager sharePager) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ZcZoning getZoningById(String id) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public String getZoningNameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	



}
