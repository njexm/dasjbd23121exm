package com.proem.exm.dao.menuManage;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;

import com.cisdi.ctp.dao.BaseDao;

public interface MenuManageDao extends BaseDao<T, Serializable>{

	public void saveOrUpdate();
}
