package com.proem.exm.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 字符串工具类
 */
public class StringUtil {

	//获取唯一标识符guid
	public static String getGuid() {
		UUID guid = UUID.randomUUID();
		return guid.toString();
	}

	public static boolean validate(String str) {
		if (str == null || str == "" || str.equalsIgnoreCase("null")
				|| str.trim().length() < 1)
			return false;
		return true;
	}

	public static boolean validate(Object str) {
		if (str == null)
			return false;
		return true;
	}

	public static boolean validate(int str) {
		if (str == 0 || "null".equalsIgnoreCase(str + ""))
			return false;
		return true;
	}

	public static boolean validate(StringBuffer str) {
		if (str == null || "".equalsIgnoreCase(str.toString())
				|| str.toString().trim().length() < 1)
			return false;
		return true;
	}

	public static boolean validate(List<?> str) {
		if (str == null || str.size() == 0)
			return false;
		return true;
	}

	public static boolean validate(String[] str) {
		if (str == null || str.length == 0)
			return false;
		return true;
	}
	
	/**
	 * 截取字符串的值，如果设置的长度小于字符串本身，截取 ； 如果大于 补充 age0 到最后面 you can try
	 * @param str = 字符串
	 * @param length = 截取
	 * @param age0
	 * @param isBefore = false-从前开始 ； = true-从后开始
	 * @return
	 */
	public static String formatLengthStr(String str, int length, String age0,
			boolean isBefore) {
		String newStr = (str == null ? "" : str.trim());

		while (newStr.length() < length) {
			if (isBefore)
				newStr = age0 + newStr;
			else
				newStr = newStr + age0;
		}
		if (newStr.length() > length) {
			if (isBefore)
				newStr = newStr.substring(newStr.length() - length);
			else
				newStr = newStr.substring(0, length);
		}
		return newStr;
	}

	/**
	 * aaa,bbb,ccc,bbb --> aaa,bbb,ccc  ","分割去除重复的值
	 * @param str
	 * @return
	 */
	public static StringBuffer deleteDuplicate(StringBuffer str) {
		if (StringUtil.validate(str) == false)
			return null;
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		String tmpStr[] = str.toString().split(",");
		for (int i = 0; i < tmpStr.length; i++) {
			for (int j = i + 1; j < tmpStr.length; j++) {
				if (tmpStr[i].equalsIgnoreCase(tmpStr[j])) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				if (validate(sb) == false)
					sb.append(tmpStr[i]);
				else
					sb.append(",").append(tmpStr[i]);
			}
			flag = false;
		}
		return sb;
	}

	/**
	 * int --> String 
	 * @param str
	 * @return
	 */
	public static String formatDbColumn(int str) {
		return String.valueOf(str);
	}

	public static String formatDbColumn(Object str) {
		return StringUtil.validate(str) ? String.valueOf(str).trim() : "";
	}

	public static String formatDbColumn(String str) {
		return str != null ? str.trim() : "";
	}

	/**
	 * aaa,bbb,ccc --> bbb 判断前面字符串中是否存在后面这个字符串
	 * @param list
	 * @param str
	 * @return
	 */
	public static boolean isContaints(String list, String str) {
		if (StringUtil.validate(list) == false)
			return false;
		if (StringUtil.validate(str) == false)
			return false;
		String[] tmp = list.split(",");
		for (int i = 0; i < tmp.length; i++) {
			if (str.equalsIgnoreCase(tmp[i]))
				return true;
		}
		return false;
	}

	//大写
	public static String toUpperCase(String str) {
		if (validate(str))
			return str.trim().toUpperCase();
		return "";
	}

	//小写
	public static String toLowerCase(String str) {
		if (validate(str))
			return str.trim().toLowerCase();
		return "";
	}

	/**
	 * aaa,bbb,ccc + ddd = aaa.bbb.ccc.ddd
	 * 往一个字符串后面添加一个新字符串
	 * @param sb
	 * @param value
	 */
	public static void setStringBufferValue(StringBuffer sb, String value) {
		if (StringUtil.validate(sb) == false) {
			sb.append(value);
		} else {
			sb.append(",").append(value);
		}
	}

	/**
	 * 分割符号是自定义 字符串拼接
	 * @param sb
	 * @param value
	 * @param regex
	 */
	public static void setStringBufferValue(StringBuffer sb, String value,
			String regex) {
		if (StringUtil.validate(sb) == false) {
			sb.append(value);
		} else {
			sb.append(regex).append(value);
		}
	}

	
	public static String format(String regex, String[] value) {
		String str = regex;
		for (int i = 0; i < value.length; i++) {
			if (regex.contains("{" + i + "}")) {
				str = str.replaceAll("\\{" + i + "}", value[i]);
			}
		}
		return str;
	}

	public static String format(String regex, String value) {
		String str = regex;
		String valu[] = value.split(",");
		for (int i = 0; i < valu.length; i++) {
			if (regex.contains("{" + i + "}")) {
				str = str.replaceAll("\\{" + i + "}", valu[i]);
			}
		}
		return str;
	}

	public static String substrByBytelength(String str, int byteLeng) {
		if (str != null && str.trim().length() > 0) {
			int count = 0;
			String strA = "";
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				count += String.valueOf(c).getBytes().length;
				if (count > byteLeng) {
					return strA;
				}
				strA += String.valueOf(c);
			}
			return strA;
		}
		return "";
	}

	/**
	 * 验证字符串是否都是数字
	 * @param strInt
	 * @return
	 */
	public static boolean validateInt(String strInt) {
		try {
			if (StringUtil.validate(strInt)) {
				Integer.parseInt(strInt);
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean validateDouble(String StrDouble) {
		try {
			Double.parseDouble(StrDouble);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 验证字符串是否符合日期格式
	 * @param strDate
	 * @return
	 */
	public static boolean validateDate(String strDate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			Date d = df.parse(strDate);
			df.format(d);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 验证字符串是否符合时间格式
	 * @param strDateTime
	 * @return
	 */
	public static boolean validateTime(String strDateTime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setLenient(false);
			Date d = df.parse(strDateTime);
			df.format(d);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean validate(byte[] str) {
		if (str == null || str.length == 0)
			return false;
		return true;
	}

	/**
	 * aaa,bbb,ccc --> aaa,bbb  删除  ccc
	 * @param list
	 * @param item
	 * @return
	 */
	public static String deleteItem(String list, String item) {
		if (StringUtil.isContaints(list, item)) {
			if (list.startsWith(item + ",")) {
				list = list.substring(item.length() + 1, list.length());
			} else if (list.endsWith("," + item)) {
				list = list.substring(0, list.length() - item.length() - 1);
			} else if (list.equals(item)) {
				list = "";
			} else {
				list = list.replace(item + ",", "");
			}
		}
		return list;
	}

	public static String addItem(String list, String item) {
		StringBuffer sb = new StringBuffer();
		if (StringUtil.validate(list)) {
			sb.append(list).append(",").append(item);
		} else {
			sb = new StringBuffer(item);
		}
		return sb.toString();
	}

	public static String replace(String s, char oldSub, char newSub) {
		return replace(s, oldSub, new Character(newSub).toString());
	}

	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}

	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {
			StringBuffer sb = new StringBuffer();
			int length = oldSub.length();
			int x = 0;
			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);
				x = y + length;
				y = s.indexOf(oldSub, x);
			}
			sb.append(s.substring(x));
			return sb.toString();
		} else {
			return s;
		}
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}
		if (oldSubs.length != newSubs.length) {
			return s;
		}
		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}
		return s;
	}

	public static String[] spliceString(String s, String delimiter) {
		if (StringUtil.validate(s)) {
			return s.split(delimiter);
		}
		return null;
	}

	public static String formatStrGb2312(String str) {
		String result = "";
		try {
			if (StringUtil.validate(str))
				result = new String(str.toString().getBytes(), "gb2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result.trim();
	}

	public static String formatCurrencyNum(String symbolCurr) {
		StringBuffer retStr = new StringBuffer();
		try {
			symbolCurr = symbolCurr.trim();
			String nt_number = symbolCurr.replaceAll("\\D", "");
			if (symbolCurr.contains(".")) {
				String digits = symbolCurr.substring(
						symbolCurr.indexOf(".") + 1, symbolCurr.length());
				String integer = nt_number.substring(0,
						nt_number.lastIndexOf(digits));
				retStr.append(integer).append(".").append(digits);
			} else {
				retStr.append(nt_number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr.toString();
	}

	public static String encode(String input) {
		if (!StringUtil.validate(input))
			return input;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = input.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	private static final String chs[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆",
			"柒", "捌", "玖", "拾" };

	public static String formatToCMoney(String money) {
		StringBuffer chRMB = new StringBuffer();
		money = money.replaceAll("[,，]", "");// 去除分隔符逗号
		double t = Double.parseDouble(money);
		if (t < 0) {
			return "金额不能是负的！";
		}
		int dotPos = money.indexOf('.');
		String strz;// 截取整数部分
		if (dotPos == -1)// 没小数点的时候等于-1
		{
			strz = money;
		} else {
			strz = money.substring(0, dotPos);// 整数部分
		}
		StringBuffer sb = new StringBuffer(strz);
		while (sb.length() >= 1 && sb.charAt(0) == '0') {// 去掉数前面多余的零
			sb.deleteCharAt(0);
		}
		if (sb.length() == 0) {// 输入为‘00’的情况
			return "零";
		}
		strz = sb.toString();
		String cur = "";
		boolean zero = false;// 记录是否应该加‘零’如 1，0000，0000，0001，读作 壹万亿零壹圆整
		String temp;
		String digits;// 每次截取的最后四位数
		while (strz.length() > 0) {

			if (strz.length() >= 4) {
				digits = strz.substring(strz.length() - 4);
				strz = strz.substring(0, strz.length() - 4);
			} else {
				digits = strz;
				strz = "";

			}
			temp = fourMaxChange(digits);
			if (temp.equals("零")) {
				if (cur.equals("亿")) {
					chRMB.insert(0, "亿");
				}
				if (zero == true) {
					if (!chRMB.substring(0, 1).equals("零"))
						chRMB.insert(0, "零");
					zero = false;
				}
			} else {
				temp += cur;
				chRMB.insert(0, temp);
				if (digits.charAt(0) == '0')
					if (chRMB.length() >= 1
							&& !chRMB.substring(0, 1).equals("零"))
						chRMB.insert(0, "零");
				zero = true;

			}
			if (cur.equals("")) {// 改变单位
				cur = "万";
			} else if (cur.equals("亿")) {
				cur = "万";
			} else
				cur = "亿";

		}
		String strx;
		if (dotPos != -1) {// 有小数
			strx = money.substring(dotPos + 1);
			if (strx.length() > 2) {
				return "金额只允许两位小数！";
			}
			int xiaoshu = Integer.parseInt(strx);// 小数部分
			if (xiaoshu == 0 || strx.length() == 0) {
				chRMB.append("圆整");// 整
			} else
				chRMB.append("圆");
			if (strx.length() == 1) {
				chRMB.append(chs[xiaoshu]).append("分");
			} else {
				if (xiaoshu / 10 != 0) {
					chRMB.append(chs[xiaoshu / 10]).append("角");
				} else {
					chRMB.append("零");
				}
				if (xiaoshu % 10 != 0) {
					chRMB.append(chs[xiaoshu % 10]).append("分");
				}
			}
		} else {
			chRMB.append("圆整");
		}

		return chRMB.toString();
	}

	public static String fourMaxChange(String number)
			throws NumberFormatException {// 将最多四位整数转换成大写金额
		StringBuffer rs = new StringBuffer();
		int money;
		money = Integer.parseInt(number);
		boolean zero = false;
		if (money / 1000 != 0) {
			zero = true;
			rs.append(chs[money / 1000]).append("仟");
			money %= 1000;
		}
		if (money / 100 != 0) {
			zero = true;
			rs.append(chs[money / 100]).append("佰");
			money %= 100;
		} else if (zero == true) {
			rs.append(chs[0]);// 零
			money %= 100;
		}

		if (money / 10 != 0) {
			zero = true;
			rs.append(chs[money / 10]).append("拾");
			money %= 10;
		} else if (zero == true && !rs.substring(rs.length() - 1).equals("零")) {
			rs.append(chs[0]);// 零
			money %= 10;
		}
		if (money != 0) {
			zero = true;
			rs.append(chs[money]);
		} else if (zero == true && rs.substring(rs.length() - 1).equals("零")) {
			rs.delete(rs.length() - 1, rs.length());
		}
		if (zero == true)
			return rs.toString();
		else
			return chs[0];// 零
	}

	public static String strToDoubleW(String strValue) {
		String[] values = strValue.split("\\.");
		String value = "";
		if (values.length == 1) {
			value = strValue + ".00";
		} else if (values.length == 2) {
			if (values[1].length() == 1)
				values[1] = values[1] + "0";
			value = values[0] + "." + values[1].substring(0, 2);
		}
		return value;
	}

	public static int[] BubbleSortArray(int a[]) {
		int t;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length - 1; j++) {
				if (a[i] < a[j + 1]) {// 降序是小于 升序是大于
					t = a[i];
					a[i] = a[j + 1];
					a[j + 1] = t;
				}
			}
		}
		return a;
	}

	public static String getEllipsis(String text, int num) {
		double textLength = 0;
		char ch;
		StringBuffer newText = new StringBuffer();
		if (text.length() > num) {
			for (int i = 0; i < text.length(); i++) {
				ch = text.charAt(i);
				// 判断Unicode
				// if (CharUtil.isChinese(ch)) { TODO
				// textLength += 1.0d; // 汉字
				// }else{
				// textLength += 0.5d;
				// }
				if (textLength <= num) {
					newText.append(ch);
				} else {
					newText.append("...");
					break;
				}
			}
		} else {
			return text;
		}
		return newText.toString();
	}

	public static String getIsNullToZero(String str) {
		String tab = "0";
		if (str != null && !"".equals(str) && !"null".equals(str)) {
			tab = str;
		}
		return tab;
	}

	public static String getIsNull(String str) {
		String tab = "";
		if (str != null && !"null".equals(str)) {
			tab = str;
		}
		return tab;
	}

	public static boolean strIsNotNull(String str) {
		boolean b = true;
		if (str == null || "".equals(str) || "null".equalsIgnoreCase(str)) {
			b = false;
		}
		return b;
	}

	public static boolean strIsNotNullOrZero(String str) {
		boolean b = true;
		if (str == null || "".equals(str) || "null".equals(str)
				|| "0".equals(str)) {
			b = false;
		}
		return b;
	}

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("aaa,bbb,ccc");
		String str = StringUtil.fourMaxChange("1987");
		System.out.println(str);
	}
	
	/**
	 * 判断字符是否不为null且不是空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}
	
	/**
	 * 判断字符是否为null、空字符串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
}
