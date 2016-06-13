package com.proem.exm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import antlr.Utils;

import com.cisdi.ctp.utils.common.StringUtils;
import com.proem.exm.entity.order.ZcOrder;

/** 
  * 页面设置转为UNIX cron expressions 转换类
  * CronExpConversion
   */ 
public class CronExpConversion {
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		
	   /** 
	       * 页面设置转为UNIX cron expressions 转换算法
	      *  @param  everyWhat
	      *  @param  commonNeeds 包括 second minute hour
	      *  @param  monthlyNeeds 包括 第几个星期 星期几
	      *  @param  weeklyNeeds  包括 星期几
	      *  @param  userDefinedNeeds  包括具体时间点
	      *  @return  cron expression
	       */ 
	      @SuppressWarnings("deprecation")
		public  static  String convertDateToCronExp(ZcOrder zcOrder)  {
	    	  String cronEx  =   "" ;
	    	  Date startDate=new Date();
	    	  Date endDate=new Date();
	    	  Date OrderDate=zcOrder.getOrderDate();
	    	  String pushType=zcOrder.getPullFlag();
	    	  String pushHz=zcOrder.getOrderNum();
	    	  //拉取开始时间
	    	  Date createTime=zcOrder.getCreateTime();
	    	  //拉取结束时间
	    	  Date updateTime=zcOrder.getUpdateTime();
	    	  if(!StringUtils.isBlank(zcOrder.getOrderCome())){
	    		  Constant.ZC_ORDER.setOrderCome(zcOrder.getOrderCome());
	    	  }
	    	  if("1".equals(pushType)){
	    		  if(OrderDate==null){
		    		  startDate.setHours(8);
		    		  startDate.setDate(new Date().getDay()-1);
		    		  endDate.setHours(8);
		    		  cronEx="0 0 8 * * ?";
		    	  }else{
		    		  int hour=OrderDate.getHours();
		    		  int min=OrderDate.getMinutes();
		    		  int sec=OrderDate.getSeconds();
		    		  startDate.setDate(new Date().getDate()-1);
		    		  startDate.setHours(hour);
		    		  startDate.setMinutes(min);
		    		  startDate.setSeconds(sec);
		    		  endDate.setHours(hour);
		    		  endDate.setMinutes(min);
		    		  endDate.setSeconds(sec);
		    		  cronEx=""+sec;
		    		  cronEx+= " ";
		    		  cronEx+=min;
		    		  cronEx+= " ";
		    		  cronEx+=hour;
		    		  cronEx+= " ";
		    		  cronEx+="* * ?";
		    	  }
	    	  }else if("2".equals(pushType)){
	    		  cronEx+= " 0 0 */";
	    		  cronEx+=pushHz;
	    		  cronEx+= " * * ?";
	    		  System.out.println(cronEx);
	    	  }
	    	  Constant.ZC_ORDER.setPullFlag(pushType);
	    	  Constant.ZC_ORDER.setOrderNum(pushHz);
	    	  Constant.ZC_ORDER.setOrderDate(createTime);
	    	  Constant.ZC_ORDER.setCreateTime(OrderDate);
    		  Constant.ZC_ORDER.setUpdateTime(updateTime);
	          return  cronEx;
	     } 
}
