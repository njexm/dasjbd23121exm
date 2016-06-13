package com.proem.exm.utils;

import java.util.Date;

import net.sf.json.JsonConfig;

public class HelpConfig extends JsonConfig {

	public HelpConfig() {
		super();
//		setJsonPropertyFilter(new PropertyFilter() {
//			public boolean apply(Object source, String name, Object value) {
//				if (name.equals("project") || name.equals("mailGroups")
//						|| name.equals("histories") || name.equals("parent")) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		});
		registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(
				"yyyy-MM-dd HH:mm:ss"));
	}
}
