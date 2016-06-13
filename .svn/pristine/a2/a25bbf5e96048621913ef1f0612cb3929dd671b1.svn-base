package com.proem.exm.entity.utils;

import java.util.Comparator;

import com.proem.exm.entity.warehouse.ChangeGoodsItems;

public class ChangeGoodsItemsCompare implements Comparator<ChangeGoodsItems>{

	@Override
	public int compare(ChangeGoodsItems o1, ChangeGoodsItems o2) {
		if(Long.parseLong(o1.getGoodsFile().getSerialNumber())> Long.parseLong(o2.getGoodsFile().getSerialNumber())){

			   return 1;
		}else if(Long.parseLong(o1.getGoodsFile().getSerialNumber()) == Long.parseLong(o2.getGoodsFile().getSerialNumber())){
			return 0;
		}else{
			return -1;
		}
	}

}
