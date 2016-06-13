package com.proem.exm.entity.utils;

import java.util.Comparator;

import com.proem.exm.entity.purchase.PurchaseOrderGoodsItems;

public class PurchaseGoodsCompare  implements Comparator<PurchaseOrderGoodsItems>{

	@Override
	public int compare(PurchaseOrderGoodsItems o1, PurchaseOrderGoodsItems o2) {
		if(Long.parseLong(o1.getSerialNumber())> Long.parseLong(o2.getSerialNumber())){

			   return 1;
		}else if(Long.parseLong(o1.getSerialNumber()) == Long.parseLong(o2.getSerialNumber())){
			return 0;
		}else{
			return -1;
		}
			  
	}

}
