package com.proem.exm.entity.utils;

import java.util.Comparator;

import com.proem.exm.entity.purchase.PurchaseReceiveItem;
import com.proem.exm.entity.warehouse.ChangeGoodsItems;

public class PurchaseReceiveGoodsCompare implements Comparator<PurchaseReceiveItem>{

	@Override
	public int compare(PurchaseReceiveItem o1, PurchaseReceiveItem o2) {
		if(Long.parseLong(o1.getGoodsFile().getSerialNumber())> Long.parseLong(o2.getGoodsFile().getSerialNumber())){

			   return 1;
		}else if(Long.parseLong(o1.getGoodsFile().getSerialNumber()) == Long.parseLong(o2.getGoodsFile().getSerialNumber())){
			return 0;
		}else{
			return -1;
		}
		
	}

}
