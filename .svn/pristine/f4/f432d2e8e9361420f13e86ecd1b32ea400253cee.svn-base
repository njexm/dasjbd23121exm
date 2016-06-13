package com.proem.exm.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (!StringUtils.hasText(text)) {
			// Treat empty String as null value.
		}
		else
		{
		try {
			date = format.parse(text);
		} catch (ParseException e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(text);
			} catch (ParseException e1) {
				format = new SimpleDateFormat("HH:mm:ss");
				try {
					date = format.parse(text);
				} catch (ParseException e2) {
					format = new SimpleDateFormat("HH");
					try {
						date = format.parse(text);
					} catch (ParseException e3) {
						e3.printStackTrace();
					}
				}
			}
		}
		}
		setValue(date);
	}
}