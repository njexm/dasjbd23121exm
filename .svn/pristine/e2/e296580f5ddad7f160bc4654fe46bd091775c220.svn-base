/**
 * 
 */
package com.proem.exm.utils;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @create 2015-09-14
 * @discription 将String类型转换为BigDecimal类型
 */
public class EBigDecimalEditor extends PropertyEditorSupport {

	public String getAsText() {
		BigDecimal value = (BigDecimal) getValue();
		if (value == null) {
			value = new BigDecimal(0);
		}
		return value.toString();
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) {
			setValue(null);
		} else {
			setValue(NumberUtils.createBigDecimal(text));
		}
	}

}