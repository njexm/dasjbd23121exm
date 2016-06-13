package com.proem.exm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON日期处理
 * 
 * @author Denny
 * 
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

	private String format;

	public JsonDateValueProcessor(String format) {
		super();
		if (format == null || format.trim().length() == 0) {
			this.format = "yyyy-MM-dd HH:mm:ss";
		} else {
			this.format = format;
		}
	}

	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}

	/**
	 * 转换
	 * 
	 * @param value
	 * @return
	 */
	private Object process(Object value) {

		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}
}