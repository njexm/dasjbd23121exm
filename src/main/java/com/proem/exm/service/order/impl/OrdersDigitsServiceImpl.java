package com.proem.exm.service.order.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proem.exm.dao.order.OrdersDigitsDao;
import com.proem.exm.entity.order.ZcOrderDigits;
import com.proem.exm.service.impl.BaseServiceImpl;
import com.proem.exm.service.order.OrdersDigitsService;

@Service("ordersDigitsService")
public class OrdersDigitsServiceImpl extends BaseServiceImpl implements OrdersDigitsService {

	@Autowired
	public OrdersDigitsDao ordersDigitsDao;
	
	public void getDigitsDecimalFormat(HttpServletRequest request,ZcOrderDigits zcOrderDigits){
//		try{
//		String changeValue = "";
//		while(rs.next){
//			
//			double needChangeValue=zcOrderDigits.getZcOrder().getOrderAmount();	
//			if(request.getParameter("id").equals(9)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat("#");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(1)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".0");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(2)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".00");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(3)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".000");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(4)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".0000");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(5)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".00000");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(6)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".000000");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(7)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".0000000");
//				changeValue=df.format(needChangeValue);
//			}
//			if(request.getParameter("id").equals(8)){
//				java.text.DecimalFormat df = new java.text.DecimalFormat(".00000000");
//				changeValue=df.format(needChangeValue);
//			}
//			
//			zcOrderDigits.setDigitsAmount(changeValue);	
//		}
//		}catch(Exception e){
//			e.printStackTrace();
//		}

	}

	@Override
	public void updateDigitsObj(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
}
