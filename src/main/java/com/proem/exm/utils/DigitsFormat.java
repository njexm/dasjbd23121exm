package com.proem.exm.utils;

public class DigitsFormat {
	
	/**
	 * 修改数值保留小数位数
	 * @param id 小数位数
	 * @param needChangeValue 需要修改的值
	 * @return changeValue 修改后的值
	 */
	public static String changeValue(String id,double needChangeValue) {
		String con1="1";
		String con2="2";
		String con3="3";
		String con4="4";
		String con5="5";
		String con6="6";
		String con7="7";
		String con8="8";
		String con9="9";
		String changeValue="";
		if (id.equals(con9)) {
			System.out.println("id9:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					"#");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con1)) {
			System.out.println("id1:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".0");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con2)) {
			System.out.println("id2:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".00");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con3)) {
			System.out.println("id3:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".000");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con4)) {
			System.out.println("id4:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".0000");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con5)) {
			System.out.println("id5:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".00000");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con6)) {
			System.out.println("id6:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".000000");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con7)) {
			System.out.println("id7:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".0000000");
			changeValue = df.format(needChangeValue);
		}
		if (id.equals(con8)) {
			System.out.println("id8:"+id);
			java.text.DecimalFormat df = new java.text.DecimalFormat(
					".00000000");
			changeValue = df.format(needChangeValue);
		}
		return changeValue;
}

}
