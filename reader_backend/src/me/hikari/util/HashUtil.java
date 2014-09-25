package me.hikari.util;

import java.security.MessageDigest;

public class HashUtil {
	private HashUtil() {
	};

	public static String Md5Hex(String s) {
		final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		if (s == null)
			return "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(s.getBytes());
			int len = bytes.length;
			char str[] = new char[len * 2];
			for (int i = 0, k = 0; i < len; i++) {
				byte byte0 = bytes[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				str[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
