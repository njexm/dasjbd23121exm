package com.proem.exm.controller.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.proem.exm.entity.basic.CommodityClassify.GoodsType;
import com.proem.exm.entity.basic.code.Code;
import com.proem.exm.entity.order.ZcOrder;
import com.proem.exm.entity.order.ZcOrderItem;
import com.proem.exm.entity.order.ZcOrderProcessItem;
import com.proem.exm.entity.order.ZcProcessOrder;
import com.proem.exm.service.order.OrdersItemService;
import com.proem.exm.utils.Constant;

/**
 * 定时任务:每个小时把待处理商品生成带采购商品
 * @author acer
 *
 */
public class TimeController  {
		@Autowired
		private OrdersItemService ordersItemService;
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    protected void execute() throws Exception  {
	    	//获取当前时间
	    	Date date=new Date();
	    	int hour=date.getHours();
	    	String hourStr=String.valueOf(hour);
	    	List<Code> codes=ordersItemService.getListByObj(Code.class, "codetype='StartTime' and codename='"+hourStr+"'");
	    	if(codes!=null && codes.size()>0){
	    		Code code=codes.get(0);
	    		//得到当前时间点中所有的商品类型
	    		List<GoodsType> types=ordersItemService.getListByObj(GoodsType.class, "STARTTIME='"+code.getCodeValue()+"'");
	    		if(types!=null && types.size()>0){
	    			//根据每一个时间类型获取对应商品
	    			for(int i=0;i<types.size();i++){
	    				GoodsType goodsType=types.get(i);
	    				//查询属于属于此商品类型的所有商品
	    				List<Map<String, Object>> list=ordersItemService.getOrderItemListBycon(goodsType);
	    				if(list!=null && list.size()>0){
	    					for(int a=0;a<list.size();a++){
	    						String id=(String) list.get(a).get("ID");
	    						Object object=null;
	    						ZcOrderItem orderItem=(ZcOrderItem) ordersItemService.getObjById(id, "ZcOrderItem");
	    						orderItem.setGoodsState(Constant.ORDER_STATUS_PURCHASE);
	    						ordersItemService.updateObj(orderItem);
	    						String orderId=orderItem.getOrder_id();
	    						ZcOrderProcessItem orderProcessItem=new ZcOrderProcessItem();
	    						orderProcessItem.setId(orderItem.getId());
	    						orderProcessItem.setOrder_id(orderItem.getOrder_id());
	    						orderProcessItem.setObj_id(orderItem.getObj_id());
	    						orderProcessItem.setProduct_id(orderItem.getProduct_id());
	    						orderProcessItem.setGoodsFile(orderItem.getGoodsFile());
	    						orderProcessItem.setType_id(orderItem.getType_id());
	    						orderProcessItem.setBn(orderItem.getBn());
	    						orderProcessItem.setName(orderItem.getName());
	    						orderProcessItem.setCost(orderItem.getCost());
	    						orderProcessItem.setPrice(orderItem.getPrice());
	    						orderProcessItem.setG_price(orderItem.getG_price());
	    						orderProcessItem.setAmount(orderItem.getAmount());
	    						orderProcessItem.setScore(orderItem.getScore());
	    						orderProcessItem.setWeight(orderItem.getWeight());
	    						orderProcessItem.setNums(orderItem.getNums());
	    						orderProcessItem.setSendNum(orderItem.getSendNum());
	    						orderProcessItem.setAddon(orderItem.getAddon());
	    						orderProcessItem.setItem_type(orderItem.getItem_type());
	    						orderProcessItem.setProviderInfo(orderItem.getProviderInfo());
	    						orderProcessItem.setGoodsState(orderItem.getGoodsState());
	    						ordersItemService.saveObj(orderProcessItem);
	    						ordersItemService.deleteObjById(orderItem.getId(), "ZcOrderItem");
	    						//获取此条订单中的所有商品
	    						List<ZcOrderItem> itemList=ordersItemService.getListByObj(ZcOrderItem.class, "order_id='"+orderId+"'");
	    						//判断该订单中的商品是否全部截单
	    						if(itemList!=null && itemList.size()>0){
	    							//获取订单对象
		    						ZcOrder order=(ZcOrder) ordersItemService.getObjById(orderId, "ZcOrder");
	    									//判断未截单
	    									order.setPullFlag("部分完成");
	    									ordersItemService.updateObj(order);
	    									Long count=ordersItemService.getCountByObj(ZcProcessOrder.class, "id='"+orderId+"'");
	    									if(count==0){
	    									ZcProcessOrder processOrder=new ZcProcessOrder();
	    									processOrder.setId(order.getId());
	    									processOrder.setCreateTime(order.getCreateTime());
	    									processOrder.setUpdateTime(order.getUpdateTime());
	    									processOrder.setOrderNum(order.getOrderNum());
	    									processOrder.setOrderTotalValue(order.getOrderTotalValue());
	    									processOrder.setOrderStatus(order.getOrderStatus());
	    									processOrder.setOrderDate(order.getOrderDate());
	    									processOrder.setOrderCome(order.getOrderCome());
	    									processOrder.setConsignee(order.getConsignee());
	    									processOrder.setCansignPhone(order.getCansignPhone());
	    									processOrder.setZcZoning(order.getZcZoning());
	    									processOrder.setOrderAmount(order.getOrderAmount());
	    									processOrder.setOrderReduceAmount(order.getOrderReduceAmount());
	    									processOrder.setIsGift(order.getIsGift());
	    									processOrder.setMemberCardNumber(order.getMemberCardNumber());
	    									processOrder.setGoodsNum(order.getGoodsNum());
	    									processOrder.setPullFlag(order.getPullFlag());
	    									processOrder.setAssociator(order.getAssociator());
	    									processOrder.setBranchId(order.getBranchId());
	    									ordersItemService.saveObj(processOrder);
	    									}else{
	    										ordersItemService.deleteObjById(orderId, "ZcProcessOrder");
	    										object=order;
	    										ZcProcessOrder processOrder=new ZcProcessOrder();
	    										processOrder.setId(order.getId());
		    									processOrder.setCreateTime(order.getCreateTime());
		    									processOrder.setUpdateTime(order.getUpdateTime());
		    									processOrder.setOrderNum(order.getOrderNum());
		    									processOrder.setOrderTotalValue(order.getOrderTotalValue());
		    									processOrder.setOrderStatus(order.getOrderStatus());
		    									processOrder.setOrderDate(order.getOrderDate());
		    									processOrder.setOrderCome(order.getOrderCome());
		    									processOrder.setConsignee(order.getConsignee());
		    									processOrder.setCansignPhone(order.getCansignPhone());
		    									processOrder.setZcZoning(order.getZcZoning());
		    									processOrder.setOrderAmount(order.getOrderAmount());
		    									processOrder.setOrderReduceAmount(order.getOrderReduceAmount());
		    									processOrder.setIsGift(order.getIsGift());
		    									processOrder.setMemberCardNumber(order.getMemberCardNumber());
		    									processOrder.setGoodsNum(order.getGoodsNum());
		    									processOrder.setPullFlag(order.getPullFlag());
		    									processOrder.setAssociator(order.getAssociator());
		    									processOrder.setBranchId(order.getBranchId());
	    										ordersItemService.saveObj(processOrder);
	    									}
	    							}else{
    								//获取订单对象
    	    						ZcOrder order=(ZcOrder) ordersItemService.getObjById(orderId, "ZcOrder");
    	    						ordersItemService.deleteObjById(orderId, "ZcOrder");
	    							//如果全部截单的情况
	    							Long count=ordersItemService.getCountByObj(ZcProcessOrder.class, "id='"+orderId+"'");
	    							order.setPullFlag("全部完成");
									if(count==0){
									ZcProcessOrder processOrder=new ZcProcessOrder();
									processOrder.setId(order.getId());
									processOrder.setCreateTime(order.getCreateTime());
									processOrder.setUpdateTime(order.getUpdateTime());
									processOrder.setOrderNum(order.getOrderNum());
									processOrder.setOrderTotalValue(order.getOrderTotalValue());
									processOrder.setOrderStatus(order.getOrderStatus());
									processOrder.setOrderDate(order.getOrderDate());
									processOrder.setOrderCome(order.getOrderCome());
									processOrder.setConsignee(order.getConsignee());
									processOrder.setCansignPhone(order.getCansignPhone());
									processOrder.setZcZoning(order.getZcZoning());
									processOrder.setOrderAmount(order.getOrderAmount());
									processOrder.setOrderReduceAmount(order.getOrderReduceAmount());
									processOrder.setIsGift(order.getIsGift());
									processOrder.setMemberCardNumber(order.getMemberCardNumber());
									processOrder.setGoodsNum(order.getGoodsNum());
									processOrder.setPullFlag(order.getPullFlag());
									processOrder.setAssociator(order.getAssociator());
									processOrder.setBranchId(order.getBranchId());
									ordersItemService.saveObj(processOrder);
									}else{
										ordersItemService.deleteObjById(orderId, "ZcProcessOrder");
										ZcProcessOrder processOrder=new ZcProcessOrder();
										processOrder.setId(order.getId());
    									processOrder.setCreateTime(order.getCreateTime());
    									processOrder.setUpdateTime(order.getUpdateTime());
    									processOrder.setOrderNum(order.getOrderNum());
    									processOrder.setOrderTotalValue(order.getOrderTotalValue());
    									processOrder.setOrderStatus(order.getOrderStatus());
    									processOrder.setOrderDate(order.getOrderDate());
    									processOrder.setOrderCome(order.getOrderCome());
    									processOrder.setConsignee(order.getConsignee());
    									processOrder.setCansignPhone(order.getCansignPhone());
    									processOrder.setZcZoning(order.getZcZoning());
    									processOrder.setOrderAmount(order.getOrderAmount());
    									processOrder.setOrderReduceAmount(order.getOrderReduceAmount());
    									processOrder.setIsGift(order.getIsGift());
    									processOrder.setMemberCardNumber(order.getMemberCardNumber());
    									processOrder.setGoodsNum(order.getGoodsNum());
    									processOrder.setPullFlag(order.getPullFlag());
    									processOrder.setAssociator(order.getAssociator());
    									processOrder.setBranchId(order.getBranchId());
										ordersItemService.saveObj(processOrder);
									}
	    						}
	    					}
	    				}else{
	    					//TODO用来处理多余的ORDER
	    					
	    				}
	    			}
	    		}
	    	}
	    	
	    }

}
