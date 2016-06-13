package com.proem.exm.service.system;

import com.proem.exm.entity.system.ZcZoning;
import com.proem.exm.service.BaseService;
import com.proem.exm.utils.DataGrid;
import com.proem.exm.utils.SharePager;

public interface ZcZoningService extends BaseService{
	
	public DataGrid getZoningDataGrid(ZcZoning zcZoning,SharePager sharePager);
	
	public ZcZoning getZoningById(String id);

	public boolean saveOrUpdate(ZcZoning zcZoning);
	
	public boolean deleteZoning(String ids);
	
	public String getZoningNameById(String id);
	
	
}
