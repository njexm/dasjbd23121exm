package com.proem.exm.entity.utils;

import java.util.Comparator;

import com.proem.exm.entity.warehouse.CheckGoodsItems;

public class GoodFileCompare  implements Comparator<CheckGoodsItems>{

	@Override
	public int compare(CheckGoodsItems o1, CheckGoodsItems o2) {
		if(Integer.valueOf(o1.getGoodsFile().getSerialNumber()).intValue()> Integer.valueOf(o2.getGoodsFile().getSerialNumber()).intValue()){
			   return 1;
		}else if(Integer.valueOf(o1.getGoodsFile().getSerialNumber()).intValue() == Integer.valueOf(o2.getGoodsFile().getSerialNumber()).intValue()){
			return 0;
		}else{
			return -1;
		}
			  
	}

}
