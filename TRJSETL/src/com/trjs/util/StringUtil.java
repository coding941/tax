package com.trjs.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:字符串工具类<br>
 * Description:字符串工具类，提供常用字符串快捷操作。<br>
 * Created:2013-12-20
 * 
 * @author zangjinyu
 * @version 1.0.0
 * @since 1.0.0(2013-12-20)
 * 
 */
public final class StringUtil {
	
	private static final Logger log = LoggerFactory.getLogger(Class.class);
	
	/**
	 * 左填充
	 */
	public static final int LEFT_PADDING = 0;// 左填充
	/**
	 * 右填充
	 */
	public static final int RIGHT_PADDING = 1;// 右填充
	/**
	 * 默认分割符
	 */
	public static final String DEFAULT_SEPARATOR = ",";// 默认分割符
	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";// 空字符串

	/**
	 * 判断一个字符序列是否包含数字
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsNumber(CharSequence cs) {
		return Pattern.matches("^.*\\d+.*$", cs);
	}

	/**
	 * 判断一个字符序列是否仅包含数字
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsNumberOnly(CharSequence cs) {
		return Pattern.matches("^\\d+$", cs);
	}

	/**
	 * 判断一个字符序列是否包含英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnLetter(CharSequence cs) {
		return Pattern.matches("^.*[a-zA-Z]+.*$", cs);
	}

	/**
	 * 判断一个字符序列是否仅包含英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnLetterOnly(CharSequence cs) {
		return Pattern.matches("^[a-zA-Z]+$", cs);
	}

	/**
	 * 判断一个字符序列是否包含中文汉字
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsCnLetter(CharSequence cs) {
		return Pattern.matches("^.*[\u4e00-\u9fa5]+.*$", cs);
	}

	/**
	 * 判断一个字符序列是否仅包含中文汉字
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsCnLetterOnly(CharSequence cs) {
		return Pattern.matches("^[\u4e00-\u9fa5]+$", cs);
	}

	/**
	 * 判断一个字符序列是否包含小写英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnLowerLetter(CharSequence cs) {
		return Pattern.matches("^.*[a-z]+.*$", cs);
	}

	/**
	 * 判断一个字符序列是否仅包含小写英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnLowerLetterOnly(CharSequence cs) {
		return Pattern.matches("^[a-z]+$", cs);
	}

	/**
	 * 判断一个字符序列是否包含大写英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnUpperLetter(CharSequence cs) {
		return Pattern.matches("^.*[A-Z]+.*$", cs);
	}

	/**
	 * 判断一个字符序列是否仅包含大写英文字母
	 * 
	 * @param cs
	 *            源字符串
	 * @return 若是返回true，否则返回false
	 */
	public static boolean containsEnUpperLetterOnly(CharSequence cs) {
		return Pattern.matches("^[A-Z]+$", cs);
	}

	/**
	 * 通过分割符，将一个字符串分割为一个List
	 * 
	 * @param dest
	 *            要分割的字符串
	 * @param spliter
	 *            使用的分割符
	 * @return 分割后的字符串List
	 */
	public static List<String> asList(String dest, String spliter) {
		if (dest == null) {
			throw new RuntimeException("要分割的字符串不可为空");
		}
		String[] array = dest.split(spliter);
		return Arrays.asList(array);
	}

	/**
	 * 判断一个字符序列是否为空，注意：空白会被认为是空
	 * 
	 * @param cs
	 *            要判断的字符序列
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断一个字符序列是否不为空，注意：空白会被认为是空
	 * 
	 * @param cs
	 *            要判断的字符序列
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * 判断一个字符序列是否为空，注意：空白不被认为是空<br>
	 * ("") or (null) 返回true
	 * 
	 * @param cs
	 *            要判断的字符序列
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 判断一个字符串是否不为空，注意：空白不被认为是空<br>
	 * 
	 * @param cs
	 *            要判断的字符序列
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * 字符串为空则默认将字符串替换成""
	 * 
	 * @param str
	 *            要判断的字符串
	 * @return 替换处理之后的字符串
	 */
	public static String defaultIfNull(String str) {
		return str == null ? EMPTY : str;
	}

	/**
	 * 字符串为空则默认进行替换，可以指定替换成哪个字符串
	 * 
	 * @param str
	 *            要判断的字符串
	 * @param defaultStr
	 *            默认被替换成的字符串
	 * @return 替换处理之后的字符串
	 */
	public static String defaultIfNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * 字符串为空白则默认进行替换，可以指定替换成哪个字符串
	 * 
	 * @param <T>
	 *            字符序列或其子类
	 * @param str
	 *            要判断的字符串
	 * @param defaultStr
	 *            默认被替换成的字符串
	 * @return 替换处理之后的字符串
	 */
	public static <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}

	/**
	 * 字符串为空则默认进行替换，可以指定替换成哪个字符串
	 * 
	 * @param <T>
	 *            字符序列或其子类
	 * @param str
	 *            要判断的字符串
	 * @param defaultStr
	 *            默认被替换成的字符串
	 * @return 替换处理之后的字符串
	 */
	public static <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
		return isEmpty(str) ? defaultStr : str;
	}

	/**
	 * 将一个字符串填充成指定长度的字符串，type表示字符串填充类型，左填充还是右填充，ch是要填充的字符
	 * 
	 * @param str
	 *            要操作的字符串
	 * @param length
	 *            操作后字符串的长度
	 * @param ch
	 *            要填充的字符
	 * @param type
	 *            填充的类型
	 * @return 填充后的字符串
	 */
	public static String padding(String str, int length, char ch, int type) {
		String astr = new String(str);
		if (astr.length() > length) {
			throw new RuntimeException("要处理的字符串的长度大于指定的填充后长度");
		}
		int len = length - astr.length();
		for (int i = 0; i <= len; i++) {
			if (type == LEFT_PADDING) {
				astr = ch + astr;
			} else if (type == RIGHT_PADDING) {
				astr = astr + ch;
			}
		}
		return astr;
	}

	/**
	 * 判断某源字符串是否以某个子字符串开头，忽略大小写
	 * 
	 * @param str
	 *            要判断的字符串
	 * @param prefix
	 *            子字符串
	 * @return 若是，则返回true，否则返回false
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return str == null && prefix == null;
		}
		if (prefix.length() > str.length()) {
			return false;
		}
		return str.regionMatches(true, 0, prefix, 0, prefix.length());
	}

	/**
	 * 连接一个数组中的元素
	 * 
	 * @param array
	 *            要连接的数组
	 * @return 连接后的字符串
	 */
	public static String join(Object[] array) {
		return join(array, null);
	}

	/**
	 * 连接一个数组中的元素
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            连接分割符
	 * @return 连接后的字符串
	 */
	public static String join(Object[] array, String separator) {
		return join(array, separator, 0, array.length);
	}

	/**
	 * 连接一个数组中的元素
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            连接分割符
	 * @return 连接后的字符串
	 */
	public static String join(Object[] array, char separator) {
		return join(array, separator, 0, array.length);
	}

	/**
	 * 连接一个数组中的元素
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            连接分割符
	 * @param startIndex
	 *            连接的数组开始索引
	 * @param endIndex
	 *            连接的数组结束索引
	 * @return 连接后的字符串
	 */
	public static String join(Object[] array, char separator, int startIndex,
			int endIndex) {
		if (array == null || array.length == 0) {
			return null;
		}
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return EMPTY;
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex]
				.toString().length()) + 1);
		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 
	 * 连接一个数组中的元素
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            连接分割符
	 * @param startIndex
	 *            起始索引
	 * @param endIndex
	 *            结束索引
	 * @return 连接后的字符串
	 */
	public static String join(Object[] array, String separator, int startIndex,
			int endIndex) {
		if (array == null) {
			return null;
		}

		if (separator == null) {
			separator = DEFAULT_SEPARATOR;
		}

		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return EMPTY;
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex]
				.toString().length()) + separator.length());

		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 将字符串数组以英文逗号进行连接，每个数组元素使用单引号包裹
	 * 
	 * @param array
	 *            要连接的字符数组
	 * @return 返回连接后的字符串
	 */
	public static String joinWidthSingleQuote(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append("'" + array[i] + "',");
		}
		sb.substring(0, sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 将单词首字母大写
	 * 
	 * @param str
	 *            要操作的字符串
	 * @return 处理之后的字符串
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(
				Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	/**
	 * 去掉字符串中所有空格
	 * 
	 * @param str
	 *            要操作的字符串
	 * @return 处理之后的字符串
	 */
	public static String trimAllWhitespace(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * 判断一个字符串是否在一个字符串数组中
	 * 
	 * @param source
	 *            要判断的字符串
	 * @param dest
	 *            目标字符数组
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isInArray(String source, String[] dest) {
		if (source == null) {
			throw new RuntimeException("要判断的源字符串不可为空");
		}
		for (int i = 0; i < dest.length; i++) {
			if (source.equals(dest[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个字符串是否在一个字符串数组中
	 * 
	 * @param source
	 *            要判断的字符串
	 * @param dest
	 *            目标字符数组
	 * @return 若是返回true，否则返回false
	 */
	public static boolean isInArrayIgnoreCase(String source, String[] dest) {
		if (source == null) {
			throw new RuntimeException("字符串不可为空");
		}
		for (int i = 0; i < dest.length; i++) {
			if (source.equalsIgnoreCase(dest[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static void testArray(int validFlag){
//		ArrayBlockingQueue<String> abq=new ArrayBlockingQueue<String>(1<<4);
		
		
	}
	
	public static void main(String[] ar){
		String s="VKHBSLJNGC";
		s.startsWith("VKHBSLJNGC");
		log.info("contains:"+(s.indexOf("A")>-1));
	}
	
	
}
