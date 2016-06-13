package com.proem.exm.utils;

import java.security.*;

public class MD5Util {

	/**
	 * 加密字符串
	 * @param str
	 * @return
	 */
	public static String stringToMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 解密MD5
	 * @param str
	 * @return
	 */
	public static String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	public static void main(String[] args) {
		String md5String = MD5Util.stringToMD5("99999");
		System.out.println("加密后的字符串：" + md5String);
		System.out.println("解密后的字符串：" + MD5Util.convertMD5(md5String));
	}

}
