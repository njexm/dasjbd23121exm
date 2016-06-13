package com.proem.exm.utils;

import java.util.Date;

public class TimeConvert {
	private String monthStr="";
	private String dayStr="";
	private String hourStr="";
	private String minuteStr="";
	private String secondStr="";
	public String formatterCompleteDateMillsecond(){
		
		Date date = new Date();
		int month = date.getMonth() + 1;
		if (month < 10)
			 monthStr = "0" + month;
		int day = date.getDate();
		if (day < 10)
			dayStr = "0" + day;
		int hour = date.getHours();
		if (hour < 10)
			hourStr = "0" + hour;
		int minute = date.getMinutes();
		if (minute < 10)
			minuteStr = "0" + minute;
		int second = date.getSeconds();
		if (second < 10)
			secondStr = "0" + second;
		return date.getYear()  + monthStr  + dayStr + hourStr + minuteStr + secondStr;
	}
}
