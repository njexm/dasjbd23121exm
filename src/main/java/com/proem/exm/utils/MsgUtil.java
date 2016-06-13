package com.proem.exm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MsgUtil {

	public static Map<String, String> map = null;

	public static final String MSG_KEY = "errorMsg";
	public static final String MSG_SUCCESS = "success";

	public static String getMsg(String str) {
		if (map == null) {
			map = new HashMap<String, String>();
			Properties prop = new Properties();
			InputStream in = MsgUtil.class
					.getResourceAsStream("/message.properties");
			try {
				prop.load(in);
				Set<String> codeSet = prop.stringPropertyNames();
				for (String code : codeSet) {
					map.put(code, prop.getProperty(code));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map.get(str);
	}
	
	public static void main(String[] args) {
		System.out.println(MsgUtil.getMsg("E001"));
		
	}

}
