package com.proem.exm.entity.utils;

import java.util.Comparator;

import com.proem.exm.entity.wholesaleGroupPurchase.WholesaleGroupPurchaseOrderItem;

public class WGPOrderCompare  implements Comparator<WholesaleGroupPurchaseOrderItem>{

	@Override
	public int compare(WholesaleGroupPurchaseOrderItem o1, WholesaleGroupPurchaseOrderItem o2) {
		if(Integer.valueOf(o1.getGoodsFile().getSerialNumber())> Integer.valueOf(o2.getGoodsFile().getSerialNumber())){

			   return 1;
		}else if(Integer.valueOf(o1.getGoodsFile().getSerialNumber()) == Integer.valueOf(o2.getGoodsFile().getSerialNumber())){
			return 0;
		}else{
			return -1;
		}
			  
	}

}
