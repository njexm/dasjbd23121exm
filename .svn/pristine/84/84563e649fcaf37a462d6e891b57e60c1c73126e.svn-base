package com.proem.exm.utils;


import java.text.ParseException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class UpdateNotificationInterval {
		
			/** 
	       * 自定义定时器调度时间
	       *  @param  triggerName 触发器名称
	        *  @throws  Exception 
	        */ 
	       public static  void  updateNotificationInterval(String triggerName, String triggerId,String cronExpression)
	                throws  SchedulerException, ParseException  {
	    	   WebApplicationContext webContext = ContextLoaderListener.getCurrentWebApplicationContext();
	    	  // SchedulerFactory schedulerFactory=new StdSchedulerFactory();
	           // 得到trigger 
//				Scheduler scheduler = schedulerFactory.getScheduler("SpringJobSchedulerFactoryBean");
				StdScheduler scheduler = (StdScheduler)webContext.getBean("SpringJobSchedulerFactoryBean");
	           CronTriggerBean trigger  =  (CronTriggerBean) scheduler.getTrigger(
	                 triggerName, Scheduler.DEFAULT_GROUP);
	          // 得到cron expression      
//	          String cronExpression  =  schedulerDAO.getCronExpression(triggerId);
	          // 设置trigger的时间规则 
	          trigger.setCronExpression(cronExpression);
	          // 重置job 
	          scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, trigger);
	     }
	       
	       
}
