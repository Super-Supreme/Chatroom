package com.zkai.chat.util;
/**
 * 
 * @ClassName: StringUtil  
 * @Description: 判断字符串是否为空  
 * @author Kai  
 * @date 2018年12月9日
 */
public class StringUtil {
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String nStr) {
		if (nStr != null && !"".equals(nStr.trim())) {
			return true;
		} else {
			return false;
		}
	}
}
