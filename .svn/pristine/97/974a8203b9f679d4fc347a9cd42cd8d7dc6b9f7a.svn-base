package com.proem.exm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cisdi.ctp.dao.HibernateTemplateManger;
import com.proem.exm.utils.Constant;

/**
 * set default hibernate database source
 */
public class DeaultDatabaseSourceListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public DeaultDatabaseSourceListener() {

	}

	public void contextInitialized(ServletContextEvent event) {
		// 设置hibernate的默认数据源
		HibernateTemplateManger.getInstance().setDefaultDB(
				Constant.DEFAULT_DATABASE_ID);
	}

	public void contextDestroyed(ServletContextEvent event) {

	}

}
