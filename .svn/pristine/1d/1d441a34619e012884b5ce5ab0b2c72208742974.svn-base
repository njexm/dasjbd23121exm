package com.proem.exm.service.impl.menuManage;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdi.ctp.model.gen.Module;
import com.proem.exm.dao.menuManage.MenuManageDao;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.menuManage.MenuManageService;
import com.proem.exm.utils.StringUtil;

@Service
public class MenuManageServiceImpl extends BaseServiceImpl implements MenuManageService{
	
	@Resource
	private MenuManageDao menuManageDao;
	
	public List<Module> getMenuList() {
		List<Module> queryList = baseDataMng.getObjListByCondition(Module.class, null);
		return queryList;
	}


	@Transactional
	public boolean saveOrUpdateMenu(Module module) {
		try {
			if(StringUtil.isEmpty(module.getParentID())){
				module.setParentID("ROOT");
			}
			if(StringUtil.isEmpty(module.getId())){
				module.setId(UUID.randomUUID().toString());
			}
			Module m = baseDataMng.getObj(module.getId(), Module.class);
		
			if(m == null){
				module.setCreateTime(new Date());
				baseDataMng.saveOrUpdateObj(module);
			}else{
				m.setId(module.getId());
				m.setParentID(module.getParentID());
				m.setIndexNum(module.getIndexNum());
				baseDataMng.saveOrUpdateObj(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean deleteMenuById(Module module) {
		try {
			List<Module> list = baseDataMng.getObjListByCondition(Module.class, "parentID = '"+module.getId()+"'");
			for(Module m : list){
				baseDataMng.deleteObj(m.getId(),Module.class);
			}
			baseDataMng.deleteObj(module);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
