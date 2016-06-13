package com.proem.exm.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.cisdi.ctp.utils.GlobalConfigHolder;

/**
 * @author Peter
 * @version 2013-11-19
 */
public class StartupListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("系统初始化中....");
		//ServletContext context = event.getServletContext();
		try {
			//initSys();
		} catch (Exception e) {
			System.out.println("启动触发器出错！");
			e.printStackTrace();
		}
		System.out.println("系统初始化完成.");
	}

	private void initSys() throws IOException {
		//GlobalConfigHolder.init();
	}

}
