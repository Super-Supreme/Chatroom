package com.zkai.chat.util;
/**
 * 
 * @ClassName: StringUtil  
 * @Description: �ж��ַ����Ƿ�Ϊ��  
 * @author Kai  
 * @date 2018��12��9��
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
