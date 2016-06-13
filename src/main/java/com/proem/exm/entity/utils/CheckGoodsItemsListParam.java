package com.proem.exm.entity.utils;

import java.util.Comparator;


import com.proem.exm.entity.warehouse.CheckGoodsItems;

public class CheckGoodsItemsListParam implements Comparator<CheckGoodsItems>{

	



	@Override
	public int compare(CheckGoodsItems o1, CheckGoodsItems o2) {
		if(Long.parseLong(o1.getGoodsFile().getSerialNumber())> Long.parseLong(o2.getGoodsFile().getSerialNumber())){

			   return 1;
		}else if(Long.parseLong(o1.getGoodsFile().getSerialNumber()) == Long.parseLong(o2.getGoodsFile().getSerialNumber())){
			return 0;
		}else{
			return -1;
		}
	}
	
}
