package cn.agilean.wifi;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 类说明：字符串工具类
 * 
 */
public class StringUtil {
	private static final Log logger = LogFactory.getLog(StringUtil.class);
	/**
	 * 
	 */
	private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2, 1 };
	/**
	 * 
	 */
	private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	/**
	 * 
	 */
	private static int[] ai = new int[18];
	
	private static long orderNum = 0l;  
	
    private static String date ;  

	/**
	 * 判断字符串是否为空
	 * @param string
	 * @return boolean 返回是否为空
	 */
	public static boolean isEmpty(String param) {
		return (null == param ||  "".equals(param.trim()));
	}
	
	/**
	 * 判断多个字符串是否为空
	 * @param string
	 * @return boolean 返回是否为空
	 */
	public static boolean isEmpty(String... params) {
		if (params == null || params.length == 0) {
			return true;
		}
		for (String str : params) {
			if (str == null || str.trim().equals("")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param string
	 * @return boolean 返回是否为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	
	
	/**
	 * 判断字符串是11位纯数字
	 * @param string
	 * @return boolean 
	 */
	public static boolean isNumeric(String string) {
		Pattern pattern=Pattern.compile("[0-9]*");
		Matcher isNum=pattern.matcher(string);
		if(string.length()!=11){
			return false;
		}
		if(!isNum.matches()){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 判断字符串为空
	 * 判断字符串不是纯数字
	 * @param string
	 * @return boolean 
	 */
	public static boolean isNotNumeric(String num) {
		Pattern pattern=Pattern.compile("[0-9]*");
		if(num==null || num.length()==0){
			return true;
		}
		Matcher isNum=pattern.matcher(num);
		if(!isNum.matches()){
			return true;
		}
		return false;
	}
	

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param a
	 *            设置第一个字符串
	 * @param b
	 *            设置第二个字符串
	 * @return boolean 返回比较的结构
	 */
	public static boolean compare(String a, String b) {
		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equals(b);
		else
			return false;
	}

	/**
	 * 判断两个字符串是否相等，忽略大小写
	 * 
	 * @param a
	 *            设置第一个字符串
	 * @param b
	 *            设置第二个字符串
	 * @return boolean 返回比较的结构
	 */
	public static boolean compareIgnoreCase(String a, String b) {
		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equalsIgnoreCase(b);
		else
			return false;
	}

	/**
	 * 复制字符串中从开始到指定的位数
	 * 
	 * @param src
	 * @param len
	 * @return String 返回结果
	 */
	public static String copy(String src, int len) {
		if (src == null)
			return null;
		if (src.length() > len)
			return len <= 0 ? null : src.substring(0, len);
		else
			return src;
	}

	/**
	 * 删除字符串中指定的一段字符串内容
	 * @param src
	 * @param delStr
	 * @return String 返回结果
	 */
	public static String delete(String src, String delStr) {
		if (isEmpty(src) || isEmpty(delStr))
			return src;
		StringBuffer buffer = new StringBuffer(src);
		for (int index = src.length(); (index = src.lastIndexOf(delStr, index)) >= 0; index -= delStr
				.length())
			buffer.delete(index, index + delStr.length());

		return buffer.toString();
	}

	/**
	 * 将指定的字符和位置插入到原字符串
	 * 
	 * @param src
	 *            设置原字符串
	 * @param anotherStr
	 * @param offset
	 *            位置
	 * @return String 返回结果
	 */
	public static String insert(String src, String anotherStr, int offset) {
		if (isEmpty(src) || isEmpty(anotherStr))
			return src;
		StringBuffer buffer = new StringBuffer(src);
		if (offset >= 0 && offset <= src.length())
			buffer.insert(offset, anotherStr);
		return buffer.toString();
	}

	/**
	 * 追加指定的字符串到原字符串中
	 * 
	 * @param src
	 *            设置原字符串
	 * @param appendStr
	 * @return String 返回结果
	 */
	public static String append(String src, String appendStr) {
		if (isEmpty(src) || isEmpty(appendStr)) {
			return src;
		} else {
			StringBuffer buffer = new StringBuffer(src);
			buffer.append(appendStr);
			return buffer.toString();
		}
	}

	/**
	 * 根据参数替换字符串内容
	 * 
	 * @param src
	 *            设置原字符串
	 * @param oldStr
	 *            指定替换字符
	 * @param newStr
	 *            将要替换的位置
	 * @param isCaseSensitive
	 *            是否区分大小写
	 * @return String 返回结果
	 */
	public static String replace(String src, String oldStr, String newStr,
			boolean isCaseSensitive) {
		if (isEmpty(src) || isEmpty(oldStr) || newStr == null)
			return src;
		String s = isCaseSensitive ? src : src.toLowerCase();
		String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		for (int index = s.length(); (index = s.lastIndexOf(o, index)) >= 0; index -= o
				.length())
			buffer.replace(index, index + o.length(), newStr);

		return buffer.toString();
	}

	/**
	 * 根据参数替换字符串内容功能，可指定位置
	 * 
	 * @param src
	 *            设置原字符串
	 * @param oldStr
	 *            指定替换字符
	 * @param newStr
	 *            将要替换的字符串
	 * @param isCaseSensitive
	 *            是否区分大小写
	 * @param index
	 *            指定位置
	 * @return String 返回结果
	 */
	public static String replace(String src, String oldStr, String newStr,
			boolean isCaseSensitive, int index) {
		if (src == null || src.length() == 0 || oldStr == null
				|| oldStr.length() == 0 || index <= 0)
			return src;
		if (newStr == null)
			newStr = "";
		String s = isCaseSensitive ? src : src.toLowerCase();
		String old = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		int length = old.length();
		int pos;
		for (pos = s.indexOf(old, 0); pos >= 0; pos = s.indexOf(old, pos
				+ length))
			if (--index == 0)
				break;
		if (pos >= 0 && index == 0)
			buffer.replace(pos, pos + length, newStr);
		return buffer.toString();
	}

	/**
	 * 给传入的字符串前后加双引号
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String quote(String str) {
		if (isEmpty(str))
			return "\"\"";
		StringBuffer buffer = new StringBuffer(str);
		if (!str.startsWith("\""))
			buffer.insert(0, "\"");
		if (!str.endsWith("\""))
			buffer.append("\"");
		return buffer.toString();
	}

	/**
	 * 去除字符串中的双引号
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String extractQuotedStr(String str) {
		if (isEmpty(str))
			return str;
		StringBuffer buffer = new StringBuffer(str);
		int index = str.length();
		while (buffer.charAt(buffer.length() - 1) == '"') {
			buffer.deleteCharAt(buffer.length() - 1);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		if (buffer.length() == 0)
			return "";
		while (buffer.charAt(0) == '"') {
			buffer.deleteCharAt(0);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		return buffer.toString();
	}

	/**
	 * 截取字符串中到指定的字符的内容，从左边开始
	 * 
	 * @param str
	 *            设置原字符串
	 * @param c
	 *            设置指定字符
	 * @return String 返回结果
	 */
	public static String strChar(String str, char c) {
		if (str == null || str.length() == 0)
			return null;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == c)
				return str.substring(i);

		return null;
	}

	/**
	 * 截取字符串中到指定的字符的内容，从右边开始
	 * 
	 * @param str
	 *            设置原字符串
	 * @param c
	 *            设置指定字符
	 * @return String 返回结果
	 */
	public static String strRChar(String str, char c) {
		if (str == null || str.length() == 0)
			return null;
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.charAt(i) == c)
				return str.substring(i);

		return null;
	}

	/**
	 * 将Object对象数组转成字符串数组
	 * 
	 * @param array
	 *            设置Object对象数组
	 * @return String[] 返回结果
	 */
	public static String[] toArray(Object array[]) {
		if (array == null || array.length == 0)
			return null;
		String result[] = new String[array.length];
		for (int i = 0; i < array.length; i++)
			if (array[i] != null)
				result[i] = array[i].toString();

		return result;
	}

	/**
	 * 将字符串数组复制到LIST
	 * 
	 * @param array
	 *            设置字符串数组
	 * @param list
	 *            设置LIST集合对象
	 * @param index
	 *            设置复制到LIST位置
	 * @return List 返回结果
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List copyToList(String array[], List list, int index) {
		if (array == null || array.length == 0)
			return list;
		if (list == null || index < 0)
			return list;
		for (int i = 0; i < array.length; i++)
			if (list.size() <= i + index)
				list.add(index + i, array[i]);
			else
				list.set(index + i, array[i]);

		return list;
	}

	/**
	 * 验证是否为电子邮件格式
	 * 
	 * @param theEmail
	 *            设置电子邮件地址字符串
	 * @return boolean 返回是否合法
	 */
	public static boolean isValidEmail(String theEmail) {
		boolean isEmail = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(theEmail);
			boolean isMatched = matcher.matches();
			if (isMatched) {
				isEmail = true;
			}

		} catch (Exception e) {
			
			return isEmail;
		}
		return isEmail;
	}

	/**
	 * 去除字符串左边空格
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String trimLeft(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = 0; index < length && buffer.charAt(index) == ' '; index++)
			;
		if (index == length)
			return "";
		else
			return buffer.substring(index);
	}

	/**
	 * 去除字符串右边空格
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回结果
	 */
	public static String trimRight(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		StringBuffer buffer = new StringBuffer(str);
		int index;
		for (index = length - 1; index >= 0 && buffer.charAt(index) == ' '; index--)
			;
		if (index < 0 && buffer.charAt(0) == ' ')
			return "";
		else
			return buffer.substring(0, index + 1);
	}

	/**
	 * 验证身份证的合法性
	 * 
	 * @param idcard
	 *            设置身份证字符串
	 * @return boolean 返回结果
	 */
	public static boolean idCardVerify(String idcard) {
		if (idcard.length() == 15) {
			idcard = idCardUptoeighteen(idcard);
		}
		if (idcard.length() != 18) {
			return false;
		}
		String verify = idcard.substring(17, 18);
		if (verify.equals(getIdCardVerify(idcard))) {
			return true;
		}
		return false;
	}

	/**
	 * 获得身份证的合法性
	 * 
	 * @param eightcardid
	 *            设置身份证字符串
	 * @return String 返回结果
	 */
	public static String getIdCardVerify(String eightcardid) {
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}

	/**
	 * 获得身份凭证
	 * 
	 * @param fifteencardid
	 *            设置身份证字符串
	 * @return String 返回结果
	 */
	public static String idCardUptoeighteen(String fifteencardid) {
		if (fifteencardid.length() != 15)
			return null;
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6, 15);
		eightcardid = eightcardid + getIdCardVerify(eightcardid);
		return eightcardid;
	}

	/**
	 * 验证电话号码合法格式，格式为02584555112
	 * 
	 * @param phoneCode
	 *            设置电话号码字符串
	 * @return boolean 返回结果
	 */
	public static boolean isPhoneNum(String phoneCode) {
		Pattern p = Pattern.compile("[0][1-9]{2,3}[1-9]{6,8}");
		Matcher m = p.matcher(phoneCode);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 字符数组转换为字符串,用逗号隔开
	 * 
	 * @param str
	 *            字符数组
	 * @return String
	 */
	public static String arrayToString(String[] str) {
		if (str == null)
			return "";
		StringBuffer rStr = new StringBuffer("");
		for (int i = 0; i < str.length; i++) {
			rStr.append(str[i]);
			rStr.append(",");
		}
		// 截取逗号
		if (rStr.toString().length() > 0) {
			rStr.setLength(rStr.length() - 1);
		}
		return rStr.toString();
	}

	/**
	 * 将String转换成BigDecimal
	 * 
	 * @param str
	 *            String
	 * @return BigDecimal
	 */
	public static BigDecimal asBigDecimal(String str) {
		return asBigDecimal(str, new BigDecimal(BigInteger.ZERO));
	}

	/**
	 * 将String转换成BigDecimal
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static BigDecimal asBigDecimal(String str, BigDecimal defaultValue) {
		try {
			return new BigDecimal(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		} catch (RuntimeException runtimeexception) {
			return defaultValue;
		}
	}

	/**
	 * 将String转换成BigInteger
	 * 
	 * @param str
	 *            String
	 * @return BigInteger
	 */
	public static BigInteger asBigInteger(String str) {
		return asBigInteger(str, BigInteger.ZERO);
	}

	/**
	 * 将String转换成BigInteger
	 * 
	 * @param str
	 *            String
	 * @param defaultValue
	 *            默认值
	 * @return BigInteger
	 */
	public static BigInteger asBigInteger(String str, BigInteger defaultValue) {
		try {
			return new BigInteger(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static boolean asBoolean(String str) {
		return asBoolean(str, false);
	}

	public static Boolean asBoolean(String str, Boolean defaultValue) {
		try {
			str = str.trim();
			return Integer.decode(str).intValue() == 0 ? Boolean.FALSE
					: Boolean.TRUE;
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
		}
		if (str.equals(""))
			return defaultValue;
		for (int i = 0; i < FALSE_STRINGS.length; i++)
			if (str.equalsIgnoreCase(FALSE_STRINGS[i]))
				return Boolean.FALSE;

		return Boolean.TRUE;
	}

	public static boolean asBoolean(String str, boolean defaultValue) {
		try {
			str = str.trim();
			return Integer.decode(str).intValue() != 0;
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
		}
		if (str.equals(""))
			return defaultValue;
		for (int i = 0; i < FALSE_STRINGS.length; i++)
			if (str.equalsIgnoreCase(FALSE_STRINGS[i]))
				return false;

		return true;
	}

	public static byte asByte(String str) {
		return asByte(str, (byte) 0);
	}

	public static Byte asByte(String str, Byte defaultValue) {
		try {
			return Byte.decode(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static byte asByte(String str, byte defaultValue) {
		try {
			return Byte.decode(str.trim()).byteValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static char asCharacter(String str) {
		return asCharacter(str, '\0');
	}

	public static Character asCharacter(String str, Character defaultValue) {
		try {
			return new Character(str.trim().charAt(0));
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (IndexOutOfBoundsException indexoutofboundsexception) {
			return defaultValue;
		}
	}

	public static char asCharacter(String str, char defaultValue) {
		try {
			return str.trim().charAt(0);
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (IndexOutOfBoundsException indexoutofboundsexception) {
			return defaultValue;
		}
	}
	
	public static double asDouble(String str) {
		return asDouble(str, 0.0D);
	}

	public static Double asDouble(String str, Double defaultValue) {
		try {
			return new Double(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static double asDouble(String str, double defaultValue) {
		try {
			return (new Double(str.trim())).doubleValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static float asFloat(String str) {
		return asFloat(str, 0.0F);
	}

	public static Float asFloat(String str, Float defaultValue) {
		try {
			return new Float(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static float asFloat(String str, float defaultValue) {
		try {
			return (new Float(str.trim())).floatValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}
	
	public static int asInteger(String str) {
		return asInteger(str, 0);
	}
	
	public static Integer asInteger(String str, Integer defaultValue) {
		try {
			return Integer.decode(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static int asInteger(String str, int defaultValue) {
		try {
			return Integer.decode(str.trim()).intValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static long asLong(String str) {
		return asLong(str, 0L);
	}

	public static Long asLong(String str, Long defaultValue) {
		try {
			return Long.decode(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static long asLong(String str, long defaultValue) {
		try {
			return Long.decode(str.trim()).longValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static short asShort(String str) {
		return asShort(str, (short) 0);
	}

	public static Short asShort(String str, Short defaultValue) {
		try {
			return Short.decode(str.trim());
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static short asShort(String str, short defaultValue) {
		try {
			return Short.decode(str.trim()).shortValue();
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		} catch (NumberFormatException numberformatexception) {
			return defaultValue;
		}
	}

	public static String asString(String str) {
		return asString(str, "", "");
	}

	public static String asString(String str, String defaultValue) {
		return asString(str, defaultValue, defaultValue);
	}

	public static String asString(String str, String defaultValue,
			String emptyStringValue) {
		try {
			return str.equals("") ? emptyStringValue : str;
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		}
	}

	public static Date asDate(String str) {
		return asDate(str, new Date(), null);
	}
	
	public static Date asDate(String str, Date defaultValue) {
		return asDate(str, defaultValue, null);
	}

	public static Date asDate(String str, Date defaultValue, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern != null ? pattern
				: DEFAULT_DATE_PATTTERN);
		try {
			return formatter.parse(str);
		} catch (ParseException parseexception) {
			return defaultValue;
		} catch (NullPointerException nullpointerexception) {
			return defaultValue;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object asType(Class type, String str) {
		if (type.isAssignableFrom(java.lang.String.class))
			return asString(str, "", "");
		if (type.isAssignableFrom(java.lang.Integer.class)
				|| type.equals(Integer.TYPE))
			return asInteger(str, new Integer(0));
		if (type.isAssignableFrom(java.lang.Double.class)
				|| type.equals(Double.TYPE))
			return asDouble(str, new Double(0.0D));
		if (type.isAssignableFrom(java.lang.Boolean.class)
				|| type.equals(Boolean.TYPE))
			return asBoolean(str, Boolean.FALSE);
		if (type.isAssignableFrom(java.lang.Float.class)
				|| type.equals(Float.TYPE))
			return asFloat(str, new Float(0.0F));
		if (type.isAssignableFrom(java.lang.Long.class)
				|| type.equals(Long.TYPE))
			return asLong(str, new Long(0L));
		if (type.isAssignableFrom(java.lang.Short.class)
				|| type.equals(Short.TYPE))
			return asShort(str, new Short((short) 0));
		if (type.isAssignableFrom(java.lang.Byte.class)
				|| type.equals(Byte.TYPE))
			return asByte(str, new Byte((byte) 0));
		if (type.isAssignableFrom(java.lang.Character.class)
				|| type.equals(Character.TYPE))
			return asCharacter(str, new Character('\0'));
		if (type.isAssignableFrom(java.math.BigDecimal.class))
			return asBigDecimal(str, new BigDecimal(BigInteger.ZERO));
		if (type.isAssignableFrom(java.math.BigInteger.class))
			return asBigInteger(str, BigInteger.ZERO);
		if (type.isAssignableFrom(java.util.Date.class))
			return asDate(str, new Date(), null);
		else
			return null;
	}

	/**
	 * 将String按指定的type进行解码
	 * 
	 * @param type
	 *            指定类型 （基本类型对象或Date
	 * @param str
	 *            String
	 * @param defaultValue
	 * @return Object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object asType(Class type, String str, Object defaultValue) {
		if (type.isAssignableFrom(java.lang.String.class))
			return asString(str, (String) defaultValue);
		if (type.isAssignableFrom(java.lang.Integer.class)
				|| type.equals(Integer.TYPE))
			return asInteger(str, (Integer) defaultValue);
		if (type.isAssignableFrom(java.lang.Double.class)
				|| type.equals(Double.TYPE))
			return asDouble(str, (Double) defaultValue);
		if (type.isAssignableFrom(java.lang.Boolean.class)
				|| type.equals(Boolean.TYPE))
			return asBoolean(str, (Boolean) defaultValue);
		if (type.isAssignableFrom(java.lang.Float.class)
				|| type.equals(Float.TYPE))
			return asFloat(str, (Float) defaultValue);
		if (type.isAssignableFrom(java.lang.Long.class)
				|| type.equals(Long.TYPE))
			return asLong(str, (Long) defaultValue);
		if (type.isAssignableFrom(java.lang.Short.class)
				|| type.equals(Short.TYPE))
			return asShort(str, (Short) defaultValue);
		if (type.isAssignableFrom(java.lang.Byte.class)
				|| type.equals(Byte.TYPE))
			return asByte(str, (Byte) defaultValue);
		if (type.isAssignableFrom(java.lang.Character.class)
				|| type.equals(Character.TYPE))
			return asCharacter(str, (Character) defaultValue);
		if (type.isAssignableFrom(java.math.BigDecimal.class))
			return asBigDecimal(str, (BigDecimal) defaultValue);
		if (type.isAssignableFrom(java.math.BigInteger.class))
			return asBigInteger(str, (BigInteger) defaultValue);
		if (type.isAssignableFrom(java.util.Date.class))
			return asDate(str, (Date) defaultValue);
		else
			return null;
	}

	/**
	 * 将Object按指定的type进行解码
	 * 
	 * @param type
	 *            指定类型
	 * @param obj
	 *            Object
	 * @return Object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object asType(Class type, Object obj) {
		if (!type.equals(java.lang.String.class) && type.isInstance(obj))
			return obj;
		if (obj == null || (obj instanceof String))
			return asType(type, (String) obj);
		if ((obj instanceof Date)
				&& (java.lang.String.class).isAssignableFrom(type))
			return (new SimpleDateFormat(DEFAULT_DATE_PATTTERN))
					.format((Date) obj);
		if ((obj instanceof Number)
				&& (java.lang.Number.class).isAssignableFrom(type)) {
			Number num = (Number) obj;
			if (type.isAssignableFrom(java.lang.Number.class))
				return num;
			if (type.isAssignableFrom(java.lang.Integer.class))
				return new Integer(num.intValue());
			if (type.isAssignableFrom(java.lang.Double.class))
				return new Double(num.doubleValue());
			if (type.isAssignableFrom(java.lang.Float.class))
				return new Float(num.floatValue());
			if (type.isAssignableFrom(java.lang.Long.class))
				return new Long(num.longValue());
			if (type.isAssignableFrom(java.lang.Short.class))
				return new Short(num.shortValue());
			if (type.isAssignableFrom(java.lang.Byte.class))
				return new Byte(num.byteValue());
			if (type.isAssignableFrom(java.math.BigInteger.class))
				return (new BigDecimal(num.toString())).toBigInteger();
			if (type.isAssignableFrom(java.math.BigDecimal.class))
				return new BigDecimal(num.toString());
		}
		return asType(type, obj.toString());
	}

	/**
	 * 将Object按指定的type进行解码
	 * 
	 * @param type
	 *            指定类型
	 * @param obj
	 *            Object
	 * @param defaultValue
	 * @return Object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object asType(Class type, Object obj, Object defaultValue) {
		if (!type.equals(java.lang.String.class) && type.isInstance(obj))
			return obj;
		if (obj == null || (obj instanceof String))
			return asType(type, (String) obj, defaultValue);
		if ((obj instanceof Date)
				&& (java.lang.String.class).isAssignableFrom(type))
			return (new SimpleDateFormat(DEFAULT_DATE_PATTTERN))
					.format((Date) obj);
		if ((obj instanceof Number)
				&& (java.lang.Number.class).isAssignableFrom(type)) {
			Number num = (Number) obj;
			if (type.isAssignableFrom(java.lang.Number.class))
				return num;
			if (type.isAssignableFrom(java.lang.Integer.class))
				return new Integer(num.intValue());
			if (type.isAssignableFrom(java.lang.Double.class))
				return new Double(num.doubleValue());
			if (type.isAssignableFrom(java.lang.Float.class))
				return new Float(num.floatValue());
			if (type.isAssignableFrom(java.lang.Long.class))
				return new Long(num.longValue());
			if (type.isAssignableFrom(java.lang.Short.class))
				return new Short(num.shortValue());
			if (type.isAssignableFrom(java.lang.Byte.class))
				return new Byte(num.byteValue());
			if (type.isAssignableFrom(java.math.BigInteger.class))
				return (new BigDecimal(num.toString())).toBigInteger();
			if (type.isAssignableFrom(java.math.BigDecimal.class))
				return new BigDecimal(num.toString());
		}
		return asType(type, obj.toString(), defaultValue);
	}

	/**
	 * 获取className
	 * 
	 * @param cls
	 *            Class
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getClassName(Class cls) {
		return getClassName(cls.getName());
	}

	/**
	 * 根据给定的应用全名获取类
	 * 
	 * @param fullName
	 *            应用全名
	 * @return String
	 */
	public static String getClassName(String fullName) {
		if (fullName == null) {
			return null;
		} else {
			fullName = fullName.trim();
			String className = fullName
					.substring(fullName.lastIndexOf('.') + 1).replace('$', '.')
					.trim();
			return className.equals("") ? null : className;
		}
	}

	/**
	 * 指定解析为false的字符串
	 */
	private static String FALSE_STRINGS[] = { "false", "null", "nul", "off",
			"no", "n" };
	/**
	 * 默认日期模式
	 */
	private static String DEFAULT_DATE_PATTTERN = "yyyy-M-d";

	/**
	 * 转换UTF8的字符串为Unicode
	 * 
	 * @param instr
	 *            String
	 * @return String
	 * @throws IOException
	 *             IOException
	 */
	public static String convertUTF8String2Unicode(String instr)
			throws IOException {
		// byte[] strbytes = instr.getBytes();
		int charindex = instr.length();
		int actualValue;
		int inputValue;
		StringBuffer sbtemp = new StringBuffer();

		for (int i = 0; i < charindex;) {

			actualValue = -1;
			inputValue = instr.charAt(i++);

			inputValue &= 0xff;

			if ((inputValue & 0x80) == 0) {
				actualValue = inputValue;
			} else if ((inputValue & 0xF8) == 0xF0) {
				actualValue = (inputValue & 0x1f) << 18;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 12;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xF0) == 0xE0) {
				actualValue = (inputValue & 0x1f) << 12;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F) << 6;

				nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			} else if ((inputValue & 0xE0) == 0xC0) {
				actualValue = (inputValue & 0x1f) << 6;

				int nextByte = instr.charAt(i++) & 0xff;
				if ((nextByte & 0xC0) != 0x80)
					throw new IOException("Invalid UTF-8 format");
				actualValue += (nextByte & 0x3F);
			}
			sbtemp.append((char) actualValue);
		}

		return sbtemp.toString();
	}

	/**
	 * 将Unicode字符串转换为UTF8的字节数组1�7
	 * 
	 * @param instr
	 *            String
	 * @return byte[]
	 */
	public static byte[] convertUnicode2UTF8Byte(String instr) {
		int len = instr.length();
		byte[] abyte = new byte[len << 2];
		int j = 0;
		for (int i = 0; i < len; i++) {
			char c = instr.charAt(i);

			if (c < 0x80) {
				abyte[j++] = (byte) c;
			} else if (c < 0x0800) {
				abyte[j++] = (byte) (((c >> 6) & 0x1F) | 0xC0);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			} else if (c < 0x010000) {
				abyte[j++] = (byte) (((c >> 12) & 0x0F) | 0xE0);
				abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			} else if (c < 0x200000) {
				abyte[j++] = (byte) (((c >> 18) & 0x07) | 0xF8);
				abyte[j++] = (byte) (((c >> 12) & 0x3F) | 0x80);
				abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
				abyte[j++] = (byte) ((c & 0x3F) | 0x80);
			}
		}

		byte[] retbyte = new byte[j];
		for (int i = 0; i < j; i++) {
			retbyte[i] = abyte[i];
		}
		return retbyte;
	}


	/**
	 * 将Unicode字符串转换为字节
	 * 
	 * @param s
	 *            String
	 * @return byte[]
	 */
	public static byte[] Unicode2Byte(String s) {
		int len = s.length();
		byte abyte[] = new byte[len << 1];
		int j = 0;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			abyte[j++] = (byte) (c & 0xff);
			abyte[j++] = (byte) (c >> 8);
		}

		return abyte;
	}

	/**
	 * 将字符串转换成整敄1�7
	 * 
	 * @param str
	 *            String
	 * @return int
	 */
	public static int ConvertToInt(String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 将字符串转换成整数对豄1�7
	 * 
	 * @param str
	 *            String
	 * @return Integer
	 */
	public static Integer ConvertToInteger(String str) {
		Integer num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (Exception e) {
			num = 0;
		}
		return num;
	}

	/**
	 * 将字符串转换成长整数
	 * 
	 * @param str
	 *            String
	 * @return long
	 */
	public static long ConvertToLong(String str) {
		long num = 0L;
		try {
			num = Long.parseLong(str);
		} catch (Exception e) {
			num = 0L;
		}
		return num;
	}

	/**
	 * 将字符串转换成长整数对象
	 * 
	 * @param str
	 *            String
	 * @return Long
	 */
	public static Long ConvertToLonger(String str) {
		Long num = 0L;
		try {
			num = Long.parseLong(str);
		} catch (Exception e) {
			num = 0L;
		}
		return num;
	}

	/**
	 * 
	 */
	private static final String[] LOWERCASES = { "O", "丄1�7", "亄1�7", "丄1�7", "囄1�7", "亄1�7",
			"兄1�7", "丄1�7", "兄1�7", "乄1�7", "卄1�7" };
	/**
	 * 
	 */
	private static final String[] UPPERCASES = { "雄1�7", "壄1�7", "贄1�7", "叄1�7", "肄1�7", "伄1�7",
			"附1�7", "柄1�7", "捄1�7", "玄1�7", "拄1�7" };

	/**
	 * 把字符串中的数字转换成简体大写中文的格式
	 * 
	 * @param transStr
	 *            String
	 * @return 返回结果
	 */
	public static String lowerCaseTrans(String transStr) {
		if (null == transStr) {
			return null;
		}
		StringBuffer sbTmp = new StringBuffer();
		for (int i = 0; i < transStr.length(); i++) {
			String stmp = String.valueOf(transStr.charAt(i));
			if ("0123456789".indexOf(stmp) >= 0) {
				int irec = Integer.parseInt(stmp);
				sbTmp.append(LOWERCASES[irec]);
			} else {
				sbTmp.append(stmp);
			}
		}
		return sbTmp.toString();
	}

	/**
	 * 把字符串中的数字转换成繁体大写中文的格式
	 * 
	 * @param transStr
	 *            String
	 * @return 返回结果
	 */
	public static String upperCaseTrans(String transStr) {
		if (null == transStr) {
			return null;
		}
		StringBuffer sbTmp = new StringBuffer();
		for (int i = 0; i < transStr.length(); i++) {
			String stmp = String.valueOf(transStr.charAt(i));
			if ("0123456789".indexOf(stmp) >= 0) {
				int irec = Integer.parseInt(stmp);
				sbTmp.append(UPPERCASES[irec]);
			} else {
				sbTmp.append(stmp);
			}
		}
		return sbTmp.toString();
	}



	/**
	 * 切掉字符串中从右边开始相同的字符
	 * 
	 * @param str
	 *            霄1�7要处理的字符丄1�7
	 * @param chr
	 *            霄1�7要切掉的字符
	 * @return String 切掉后的字符
	 */
	public static String removeRigthChar(String str, char chr) {
		if (str == null || str.trim().length() < 1) {
			return null;
		}

		char[] chrArray = str.toCharArray();
		int iCount = 0;
		for (int i = chrArray.length - 1; i >= 0; i--) {
			if (chrArray[i] != chr) {
				break;
			}
			++iCount;
		}

		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < chrArray.length - iCount; j++) {
			buf.append(chrArray[j]);
		}
		return buf.toString();
	}


	/**
	 * 转换大写弄1�7头字符串
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回字符丄1�7
	 */
	public static String capitalize(String str) {
		if (StringUtil.isEmpty(str)) {
			return str;
		} else {
			StringBuffer buffer = new StringBuffer(str);
			buffer.setCharAt(0, str.substring(0, 1).toUpperCase().charAt(0));
			return buffer.toString();
		}
	}

	/**
	 * 转换小写弄1�7头字符串
	 * 
	 * @param str
	 *            设置原字符串
	 * @return String 返回字符丄1�7
	 */
	public static String deCapitalize(String str) {
		if (StringUtil.isEmpty(str)) {
			return str;
		} else {
			StringBuffer buffer = new StringBuffer(str);
			buffer.setCharAt(0, str.substring(0, 1).toLowerCase().charAt(0));
			return buffer.toString();
		}
	}

	/**
	 * 查询码功能，将输入的汉字，智能转换为英文箄1�7写码提供查询使用
	 * 
	 * @param character
	 *            设置字符丄1�7
	 * @return String 返回结果
	 */
	@SuppressWarnings("unused")
	public static String getQueryCode(String character) {
		StringBuffer result = new StringBuffer("");
		int j = 0, k = 0, l = 0, q = 0;
		char zm1[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z' };
		long n, m, p;
		char firstzm;

		String Strhz[] = {
				" 锄1�7 捄1�7 嗄1�7 嗄1�7 嫄1�7 瑄1�7 暄1�7 砄1�7 锄1�7 霄1�7 谄1�7 埄1�7 揄1�7 犄1�7 庄1�7 桄1�7 铄1�7 鹄1�7 黄1�7 "
						+ " 嚄1�7 坄1�7 拄1�7 嗄1�7 岄1�7 廄1�7 遄1�7 媄1�7 骄1�7 獄1�7 聄1�7 螄1�7 鏄1�7 鳄1�7 鏄1�7 ",
				"" + " 茄1�7 菄1�7 岄1�7 灄1�7 钄1�7 粄1�7 鲄1�7 魄1�7 捄1�7 掄1�7 阄1�7 坄1�7 钄1�7 瘄1�7 癄1�7 舄1�7 蒄1�7 浄1�7 曄1�7 "
						+ " 葄1�7 孄1�7 煄1�7 鸄1�7 褄1�7 趄1�7 龄1�7 孄1�7 附1�7 還1�7 埄1�7 萄1�7 蓄1�7 呄1�7 悄1�7 碄1�7 鹄1�7 褄1�7 鐄1�7 鞄1�7 处1�7 "
						+ " 畄1�7 坄1�7 贄1�7 锄1�7 嘄1�7 甄1�7 匄1�7 俄1�7 埄1�7 芄1�7 荄1�7 荄1�7 萄1�7 薄1�7 各1�7 哄1�7 狄1�7 庄1�7 愄1�7 滄1�7 濄1�7 "
						+ " 弄1�7 妄1�7 婄1�7 嬄1�7 璄1�7 贄1�7 睄1�7 畄1�7 铄1�7 秄1�7 裄1�7 筄1�7 箄1�7 範1�7 舄1�7 襄1�7 跄1�7 髄1�7 匄1�7 弄1�7 苄1�7 "
						+ " 忄1�7 汄1�7 缄1�7 飄1�7 煄1�7 砄1�7 碄1�7 窄1�7 褄1�7 蝄1�7 笄1�7 鳄1�7 婄1�7 骄1�7 杄1�7 飄1�7 飄1�7 镄1�7 镄1�7 瘄1�7 裄1�7 "
						+ " 鳄1�7 髄1�7 蹄1�7 傄1�7 豄1�7 缄1�7 玄1�7 槄1�7 殄1�7 膄1�7 镄1�7 髄1�7 鬄1�7 禄1�7 還1�7 摄1�7 槄1�7 柄1�7 卄1�7 孄1�7 亄1�7 "
						+ " 啄1�7 饄1�7 檄1�7 掄1�7 擄1�7 礄1�7 钄1�7 鹄1�7 簄1�7 趄1�7 跄1�7 踄1�7 卄1�7 逄1�7 瓄1�7 晄1�7 钄1�7 钄1�7 醄1�7 ",
				"" + " 嚄1�7 礄1�7 孄1�7 骄1�7 璄1�7 粄1�7 黄1�7 嘄1�7 漄1�7 螄1�7 艄1�7 恄1�7 岄1�7 涄1�7 刄1�7 善1�7 嚄1�7 猄1�7 馄1�7 汄1�7 姄1�7 杄1�7 楄1�7 "
						+ " 槄1�7 檄1�7 锄1�7 镄1�7 衄1�7 侄1�7 钄1�7 瘄1�7 虄1�7 冄1�7 谄1�7 蒄1�7 廄1�7 忄1�7 潄1�7 澄1�7 孄1�7 羄1�7 婄1�7 骄1�7 规1�7 "
						+ " 禄1�7 镄1�7 蟄1�7 躄1�7 倄1�7 伄1�7 鬄1�7 苄1�7 菄1�7 径1�7 怄1�7 惄1�7 阄1�7 娄1�7 嫄1�7 昄1�7 氄1�7 鲄1�7 组1�7 剄1�7 怄1�7 "
						+ " 晄1�7 焄1�7 耄1�7 坄1�7 砄1�7 郄1�7 伄1�7 谄1�7 谄1�7 抄1�7 嗄1�7 宄1�7 琄1�7 榄1�7 碄1�7 龄1�7 骄1�7 秄1�7 丄1�7 埄1�7 噄1�7 "
						+ " 构1�7 柄1�7 塄1�7 瞄1�7 铄1�7 铄1�7 裄1�7 蛄1�7 酄1�7 傄1�7 坄1�7 墄1�7 茄1�7 叄1�7 哄1�7 啄1�7 嗄1�7 饄1�7 媄1�7 敄1�7 眄1�7 "
						+ " 鸄1�7 瘄1�7 褄1�7 蚄1�7 螄1�7 笄1�7 範1�7 豄1�7 踄1�7 魄1�7 茄1�7 忄1�7 憄1�7 铄1�7 舄1�7 艄1�7 俄1�7 帄1�7 惄1�7 瘄1�7 雄1�7 "
						+ " 畄1�7 亄1�7 刄1�7 怄1�7 憄1�7 组1�7 杄1�7 楄1�7 樄1�7 褄1�7 蜄1�7 蹄1�7 黄1�7 搄1�7 膄1�7 踄1�7 啄1�7 嘄1�7 舄1�7 遄1�7 巄1�7 "
						+ " 氄1�7 钄1�7 舄1�7 怄1�7 附1�7 棄1�7 槄1�7 茄1�7 呄1�7 祄1�7 鹄1�7 糄1�7 楄1�7 辄1�7 腄1�7 蔄1�7 径1�7 猄1�7 殄1�7 酄1�7 蹄1�7 "
						+ " 蹄1�7 攄1�7 汄1�7 撄1�7 爄1�7 镄1�7 萄1�7 啄1�7 悄1�7 璄1�7 榄1�7 毄1�7 隄1�7 忄1�7 的1�7 厄1�7 嵄1�7 脄1�7 锄1�7 矄1�7 痄1�7 瘄1�7 鹄1�7 蹄1�7 ",
				"" + " 耄1�7 哄1�7 嗄1�7 怄1�7 妄1�7 沄1�7 疄1�7 褄1�7 笄1�7 靄1�7 鞄1�7 埄1�7 甄1�7 呄1�7 岄1�7 迄1�7 骄1�7 组1�7 玄1�7 黄1�7 "
						+ " 评1�7 億1�7 萄1�7 啄1�7 澄1�7 殄1�7 资1�7 眄1�7 疄1�7 瘄1�7 聄1�7 箄1�7 谄1�7 凄1�7 菄1�7 宄1�7 砄1�7 裄1�7 叄1�7 帄1�7 忄1�7 "
						+ " 氄1�7 焄1�7 纄1�7 锄1�7 噄1�7 嶄1�7 戄1�7 磄1�7 镄1�7 簄1�7 氄1�7 籄1�7 评1�7 谄1�7 還1�7 坄1�7 荄1�7 嘄1�7 娄1�7 组1�7 柄1�7 "
						+ " 棄1�7 规1�7 砄1�7 碄1�7 睄1�7 镄1�7 羄1�7 骄1�7 嗄1�7 阄1�7 坄1�7 巄1�7 玄1�7 钄1�7 癄1�7 癄1�7 簄1�7 踄1�7 铄1�7 铄1�7 貄1�7 "
						+ " 鲄1�7 垄1�7 堄1�7 揄1�7 善1�7 牄1�7 瓄1�7 耄1�7 踄1�7 蹄1�7 鲄1�7 仄1�7 啄1�7 玄1�7 腄1�7 碄1�7 甄1�7 铄1�7 疄1�7 耄1�7 酄1�7 "
						+ " 铄1�7 垄1�7 咄1�7 岄1�7 峄1�7 氄1�7 胄1�7 胄1�7 硄1�7 鸄1�7 蔄1�7 窄1�7 蚄1�7 範1�7 芄1�7 嘄1�7 渄1�7 椄1�7 牄1�7 蠄1�7 笄1�7 "
						+ " 髄1�7 黄1�7 椄1�7 煄1�7 簄1�7 怄1�7 憄1�7 碄1�7 沄1�7 炄1�7 砄1�7 礄1�7 盄1�7 镄1�7 趄1�7 驄1�7 咄1�7 哄1�7 沄1�7 缄1�7 柄1�7 铄1�7 裄1�7 踄1�7 ",
				"" + " 噄1�7 谄1�7 垄1�7 苄1�7 莄1�7 萄1�7 呄1�7 愄1�7 屄1�7 婄1�7 轄1�7 腄1�7 锄1�7 锄1�7 鹄1�7 预1�7 鳄1�7 评1�7 蒄1�7 摄1�7 佄1�7 迄1�7 珄1�7 铄1�7 鸄1�7 鲄1�7 ",
				"" + " 垄1�7 砄1�7 蕄1�7 蘄1�7 幄1�7 梄1�7 燄1�7 畄1�7 蹄1�7 坄1�7 還1�7 彄1�7 构1�7 钄1�7 舄1�7 鲄1�7 芄1�7 狄1�7 悄1�7 淄1�7 妄1�7 组1�7 榄1�7 腄1�7 "
						+ " 斄1�7 扄1�7 镄1�7 痄1�7 蜄1�7 範1�7 翄1�7 霄1�7 鲄1�7 偄1�7 瀄1�7 玄1�7 棄1�7 鲄1�7 鼄1�7 俄1�7 酄1�7 葄1�7 唄1�7 沄1�7 砄1�7 缄1�7 "
						+ " 匄1�7 凄1�7 郄1�7 芄1�7 芄1�7 苄1�7 茄1�7 莄1�7 菄1�7 拄1�7 呄1�7 幄1�7 怄1�7 滄1�7 艄1�7 孄1�7 驄1�7 组1�7 组1�7 桄1�7 资1�7 "
						+ " 祄1�7 砄1�7 黄1�7 黄1�7 罄1�7 稄1�7 馄1�7 蚄1�7 蜄1�7 蝄1�7 蝄1�7 麄1�7 趄1�7 跄1�7 鲄1�7 鳄1�7 ",
				"" + " 射1�7 射1�7 射1�7 旄1�7 钄1�7 丄1�7 附1�7 垄1�7 戄1�7 资1�7 坄1�7 苄1�7 射1�7 擄1�7 泄1�7 淄1�7 澄1�7 组1�7 橄1�7 旄1�7 矄1�7 疄1�7 酄1�7 "
						+ " 戄1�7 罄1�7 筄1�7 睄1�7 评1�7 郄1�7 藄1�7 缄1�7 槄1�7 槄1�7 杄1�7 锄1�7 咄1�7 屄1�7 鬄1�7 仄1�7 哄1�7 圄1�7 塄1�7 嗄1�7 纄1�7 "
						+ " 搄1�7 膄1�7 硄1�7 镄1�7 袄1�7 预1�7 虄1�7 舄1�7 骄1�7 亄1�7 茄1�7 哄1�7 艄1�7 哄1�7 资1�7 组1�7 鲄1�7 珄1�7 肄1�7 蚄1�7 规1�7 "
						+ " 佄1�7 评1�7 岄1�7 遄1�7 媄1�7 缄1�7 构1�7 规1�7 彄1�7 笄1�7 範1�7 鞄1�7 嘄1�7 评1�7 菄1�7 呄1�7 崄1�7 汄1�7 梄1�7 轄1�7 牄1�7 "
						+ " 牄1�7 臄1�7 毄1�7 瞄1�7 罄1�7 钄1�7 锄1�7 鸄1�7 鹄1�7 痄1�7 蛄1�7 酄1�7 规1�7 鲄1�7 鹄1�7 卄1�7 评1�7 呄1�7 栄1�7 胄1�7 鸄1�7 "
						+ " 纄1�7 倄1�7 莄1�7 掄1�7 涄1�7 盄1�7 鹄1�7 矄1�7 鳄1�7 咄1�7 犄1�7 桄1�7 胄1�7 傄1�7 炄1�7 匄1�7 刄1�7 庄1�7 宄1�7 妄1�7 桄1�7 "
						+ " 炄1�7 晄1�7 的1�7 簄1�7 鲄1�7 鳄1�7 衄1�7 组1�7 磄1�7 鲄1�7 涄1�7 馄1�7 埄1�7 掄1�7 呄1�7 帄1�7 崄1�7 猄1�7 椄1�7 虄1�7 聄1�7 蜄1�7 蝄1�7 ",
				"" + " 蛄1�7 虄1�7 铄1�7 嘄1�7 咄1�7 嗄1�7 胄1�7 醄1�7 還1�7 菄1�7 撄1�7 阄1�7 瀄1�7 晄1�7 焄1�7 顄1�7 预1�7 蚄1�7 鼄1�7 沄1�7 组1�7 珄1�7 预1�7 镄1�7 "
						+ " 貄1�7 蒄1�7 薄1�7 嗄1�7 嚄1�7 濄1�7 灄1�7 昄1�7 的1�7 预1�7 蚄1�7 评1�7 劄1�7 壄1�7 嗄1�7 阄1�7 纄1�7 曄1�7 盄1�7 预1�7 蚄1�7 翄1�7 "
						+ " 蘄1�7 珄1�7 桄1�7 黄1�7 訄1�7 讄1�7 荄1�7 蕄1�7 薄1�7 闄1�7 泄1�7 堄1�7 径1�7 逄1�7 瘄1�7 範1�7 糄1�7 鲄1�7 骄1�7 冄1�7 唄1�7 囄1�7 "
						+ " 岄1�7 猄1�7 怄1�7 惄1�7 浄1�7 滄1�7 琄1�7 槄1�7 轄1�7 规1�7 烄1�7 煄1�7 戄1�7 扄1�7 祄1�7 瓄1�7 鹄1�7 鹄1�7 鹄1�7 笄1�7 醄1�7 斄1�7 "
						+ " 骄1�7 桄1�7 砄1�7 铄1�7 踄1�7 郄1�7 奄1�7 萄1�7 擄1�7 圄1�7 獄1�7 洄1�7 浄1�7 漄1�7 寄1�7 逄1�7 缄1�7 锄1�7 鲄1�7 鬄1�7 隄1�7 径1�7 "
						+ " 湄1�7 潄1�7 遄1�7 璄1�7 肄1�7 癄1�7 蟄1�7 範1�7 鳄1�7 茄1�7 荄1�7 蕄1�7 咄1�7 哄1�7 善1�7 隄1�7 洄1�7 浄1�7 彄1�7 缄1�7 珄1�7 桄1�7 "
						+ " 晄1�7 恄1�7 虄1�7 蟄1�7 麄1�7 评1�7 馄1�7 阄1�7 溄1�7 劄1�7 藄1�7 攄1�7 嚄1�7 处1�7 钄1�7 锄1�7 镄1�7 耄1�7 蠄1�7 ",
				"" + " 丄1�7 亄1�7 乄1�7 剄1�7 佄1�7 偄1�7 评1�7 墄1�7 芄1�7 芄1�7 荄1�7 蒄1�7 蕄1�7 掄1�7 叄1�7 咄1�7 哄1�7 唄1�7 岄1�7 嵄1�7 洄1�7 屄1�7 骄1�7 畄1�7 "
						+ " 玄1�7 楄1�7 殄1�7 戄1�7 戄1�7 资1�7 规1�7 犄1�7 齄1�7 矄1�7 羄1�7 嵄1�7 稄1�7 瘄1�7 虄1�7 笄1�7 笄1�7 暄1�7 跄1�7 跄1�7 霄1�7 鲄1�7 "
						+ " 鲄1�7 髄1�7 麄1�7 嘄1�7 伄1�7 郄1�7 葄1�7 岄1�7 浄1�7 迄1�7 珄1�7 戄1�7 胄1�7 恄1�7 铄1�7 镄1�7 痄1�7 瘄1�7 袄1�7 蛄1�7 笄1�7 "
						+ " 袄1�7 跄1�7 僄1�7 谄1�7 谄1�7 菄1�7 蒄1�7 搄1�7 囄1�7 湄1�7 蹄1�7 謄1�7 缄1�7 构1�7 楄1�7 戄1�7 戄1�7 牄1�7 犄1�7 毄1�7 腄1�7 "
						+ " 睄1�7 锄1�7 鹄1�7 裄1�7 笄1�7 翄1�7 趄1�7 踄1�7 鲄1�7 鞄1�7 各1�7 宄1�7 茄1�7 洄1�7 组1�7 缄1�7 犄1�7 礄1�7 耄1�7 糄1�7 豄1�7 "
						+ " 僄1�7 艄1�7 茄1�7 挄1�7 噄1�7 峄1�7 径1�7 湄1�7 姄1�7 敄1�7 的1�7 鹄1�7 蛄1�7 醄1�7 跄1�7 鲄1�7 偄1�7 讄1�7 评1�7 拄1�7 善1�7 "
						+ " 嗄1�7 婄1�7 孄1�7 桄1�7 碄1�7 疄1�7 预1�7 蚄1�7 羄1�7 鲄1�7 骄1�7 饄1�7 饄1�7 卄1�7 荄1�7 堄1�7 噄1�7 馄1�7 廄1�7 妄1�7 缄1�7 "
						+ " 瑄1�7 槄1�7 资1�7 规1�7 衄1�7 刄1�7 億1�7 阄1�7 菄1�7 獄1�7 憄1�7 泄1�7 迄1�7 弄1�7 婄1�7 肄1�7 胄1�7 腄1�7 旄1�7 靄1�7 迄1�7 "
						+ " 炄1�7 扄1�7 僄1�7 啄1�7 阄1�7 柄1�7 桄1�7 鸄1�7 鹄1�7 资1�7 鬄1�7 倄1�7 讄1�7 苄1�7 苄1�7 莄1�7 掄1�7 遄1�7 屄1�7 琄1�7 构1�7 "
						+ " 椄1�7 榄1�7 榄1�7 橄1�7 犄1�7 飄1�7 钄1�7 锄1�7 窄1�7 裄1�7 趄1�7 醄1�7 踄1�7 龄1�7 雄1�7 瞄1�7 鞄1�7 鄄1�7 狄1�7 涄1�7 桄1�7 "
						+ " 蠄1�7 锄1�7 镄1�7 隄1�7 厄1�7 劄1�7 谄1�7 矄1�7 蕄1�7 嗄1�7 噄1�7 噄1�7 崄1�7 獄1�7 孄1�7 珄1�7 桄1�7 橄1�7 爄1�7 镄1�7 蹄1�7 "
						+ " 规1�7 巄1�7 预1�7 捄1�7 的1�7 筄1�7 麄1�7 ",
				"" + " 佄1�7 咄1�7 胄1�7 剄1�7 垄1�7 蒄1�7 忄1�7 恄1�7 铄1�7 锄1�7 锄1�7 侄1�7 莄1�7 阄1�7 戄1�7 龄1�7 瞄1�7 伄1�7 闄1�7 钄1�7 射1�7 栄1�7 "
						+ " 犄1�7 铄1�7 嗄1�7 岄1�7 恄1�7 溄1�7 骄1�7 缄1�7 珄1�7 轄1�7 氄1�7 瞄1�7 钄1�7 锄1�7 稄1�7 疄1�7 窄1�7 预1�7 蝄1�7 髄1�7 "
						+ " 裄1�7 铄1�7 倄1�7 崄1�7 箄1�7 芄1�7 蔄1�7 叄1�7 眄1�7 筄1�7 刄1�7 堄1�7 善1�7 组1�7 骄1�7 侄1�7 侄1�7 蒄1�7 郄1�7 哄1�7 "
						+ " 狄1�7 浄1�7 脄1�7 髄1�7 评1�7 评1�7 還1�7 圄1�7 处1�7 哄1�7 纄1�7 贄1�7 馄1�7 匄1�7 处1�7 隄1�7 蒄1�7 揄1�7 善1�7 善1�7 "
						+ " 悄1�7 愄1�7 逄1�7 暄1�7 睄1�7 聄1�7 蝄1�7 範1�7 跄1�7 悄1�7 阄1�7 琄1�7 锄1�7 醄1�7 鲄1�7 髄1�7 栄1�7 蛄1�7 ",
				"" + " 剄1�7 還1�7 旄1�7 砄1�7 瘄1�7 崄1�7 径1�7 涄1�7 濄1�7 资1�7 睄1�7 铄1�7 癄1�7 籄1�7 岄1�7 漄1�7 榄1�7 斄1�7 罄1�7 镄1�7 褄1�7 "
						+ " 莄1�7 蒄1�7 啄1�7 阄1�7 锄1�7 稄1�7 螄1�7 潄1�7 唄1�7 崄1�7 栄1�7 铄1�7 铄1�7 痄1�7 耄1�7 醄1�7 仄1�7 叄1�7 泄1�7 鳄1�7 "
						+ " 羄1�7 评1�7 嘄1�7 嫄1�7 缄1�7 檄1�7 耄1�7 酄1�7 塄1�7 愄1�7 鬄1�7 俄1�7 俄1�7 郄1�7 坄1�7 苄1�7 莄1�7 蓄1�7 藄1�7 呄1�7 "
						+ " 唄1�7 善1�7 猄1�7 悄1�7 溄1�7 澄1�7 逄1�7 娄1�7 嫄1�7 骄1�7 缄1�7 构1�7 栄1�7 轄1�7 戄1�7 砄1�7 詄1�7 罄1�7 锄1�7 鹄1�7 "
						+ " 疄1�7 疄1�7 蛄1�7 蜄1�7 蠄1�7 笄1�7 範1�7 粄1�7 醄1�7 跄1�7 雄1�7 鲄1�7 鳄1�7 黄1�7 蔄1�7 奄1�7 潄1�7 濄1�7 琄1�7 楄1�7 "
						+ " 殄1�7 臄1�7 裄1�7 裄1�7 蠄1�7 鲄1�7 竄1�7 桄1�7 墄1�7 莄1�7 椄1�7 踄1�7 靄1�7 魄1�7 蓄1�7 射1�7 嘄1�7 獄1�7 寄1�7 缄1�7 "
						+ " 钄1�7 鹄1�7 冄1�7 埄1�7 捄1�7 咄1�7 洄1�7 趄1�7 躄1�7 鬄1�7 蔄1�7 啄1�7 嶄1�7 廄1�7 懄1�7 遄1�7 檄1�7 辄1�7 膄1�7 瞄1�7 "
						+ " 粄1�7 躄1�7 酄1�7 苄1�7 呄1�7 囄1�7 泄1�7 组1�7 柄1�7 棄1�7 瓄1�7 聄1�7 蛄1�7 翄1�7 鲄1�7 浄1�7 遄1�7 骄1�7 组1�7 旄1�7 "
						+ " 熄1�7 锄1�7 镄1�7 鹄1�7 鎄1�7 茄1�7 泄1�7 珄1�7 栄1�7 胄1�7 砄1�7 癄1�7 偄1�7 蒄1�7 善1�7 嵄1�7 镄1�7 瘄1�7 耄1�7 蝄1�7 "
						+ " 髄1�7 垄1�7 蓄1�7 撄1�7 噄1�7 闄1�7 泄1�7 渄1�7 漄1�7 逄1�7 璄1�7 栄1�7 榄1�7 橄1�7 轄1�7 辄1�7 辄1�7 氄1�7 胄1�7 膄1�7 "
						+ " 镄1�7 稄1�7 鸄1�7 鹄1�7 褄1�7 簄1�7 舄1�7 鲄1�7 挄1�7 孄1�7 滄1�7 脄1�7 娄1�7 栄1�7 鸄1�7 銄1�7 锄1�7 囄1�7 倄1�7 蠄1�7 "
						+ " 荄1�7 捄1�7 摄1�7 猄1�7 泄1�7 漄1�7 珄1�7 椄1�7 脄1�7 硄1�7 镄1�7 瘄1�7 雄1�7 麄1�7 ",
				"" + " 唄1�7 犄1�7 嬄1�7 杄1�7 蟄1�7 劄1�7 荄1�7 霄1�7 墄1�7 幄1�7 缄1�7 熄1�7 镄1�7 预1�7 螄1�7 鳄1�7 鞄1�7 還1�7 漄1�7 硄1�7 蟄1�7 "
						+ " 袄1�7 茄1�7 峄1�7 泄1�7 瑄1�7 昄1�7 牄1�7 耄1�7 旄1�7 懄1�7 瞄1�7 蟄1�7 髄1�7 麄1�7 莄1�7 嵄1�7 猄1�7 浄1�7 湄1�7 楄1�7 "
						+ " 镄1�7 鹄1�7 袄1�7 魄1�7 扄1�7 焄1�7 懄1�7 钄1�7 勄1�7 甄1�7 瞄1�7 懄1�7 朄1�7 礄1�7 虄1�7 蜄1�7 蠄1�7 艄1�7 艄1�7 芄1�7 "
						+ " 谄1�7 蘄1�7 咄1�7 嘄1�7 猄1�7 汄1�7 宄1�7 弄1�7 脄1�7 祄1�7 敄1�7 糄1�7 縄1�7 麄1�7 沄1�7 渄1�7 湄1�7 腄1�7 眄1�7 善1�7 "
						+ " 還1�7 缄1�7 缄1�7 杄1�7 淄1�7 眄1�7 鹄1�7 乄1�7 咄1�7 蠄1�7 範1�7 苄1�7 岄1�7 闄1�7 泄1�7 缄1�7 玄1�7 珄1�7 愄1�7 黄1�7 "
						+ " 鳄1�7 冄1�7 茄1�7 溄1�7 暄1�7 瞄1�7 酄1�7 谄1�7 茄1�7 蓄1�7 馄1�7 嫄1�7 殄1�7 镄1�7 秄1�7 瘄1�7 耄1�7 貄1�7 貄1�7 侄1�7 "
						+ " 哄1�7 缄1�7 眄1�7 蛄1�7 蝄1�7 鍄1�7 仄1�7 坄1�7 苄1�7 沄1�7 毄1�7 钄1�7 ",
				"" + " 捄1�7 肄1�7 镄1�7 衄1�7 鼄1�7 艄1�7 萄1�7 柄1�7 善1�7 囄1�7 囄1�7 楄1�7 腄1�7 蝄1�7 资1�7 攄1�7 囄1�7 馄1�7 曄1�7 孄1�7 垄1�7 呄1�7 猄1�7 "
						+ " 瑄1�7 硄1�7 铄1�7 蛄1�7 讄1�7 恄1�7 伄1�7 坄1�7 猄1�7 怄1�7 昄1�7 旄1�7 祄1�7 慄1�7 睄1�7 铄1�7 鲄1�7 廄1�7 埄1�7 辄1�7 "
						+ " 黄1�7 鲄1�7 鲄1�7 茄1�7 嬄1�7 脄1�7 袄1�7 乄1�7 附1�7 蘄1�7 嗄1�7 预1�7 臄1�7 蹄1�7 佄1�7 苄1�7 咄1�7 甄1�7 聄1�7 拄1�7 "
						+ " 狄1�7 忄1�7 妄1�7 侄1�7 哄1�7 耄1�7 弄1�7 胄1�7 孄1�7 驄1�7 恄1�7 钄1�7 衄1�7 傄1�7 搄1�7 善1�7 锄1�7 ",
				"" + " 讄1�7 怄1�7 瓄1�7 耄1�7 噄1�7",
				"" + " 葄1�7 杄1�7 钄1�7 筄1�7 俄1�7 蒄1�7 拄1�7 爄1�7 泄1�7 袄1�7 襄1�7 蟄1�7 蹄1�7 彄1�7 滄1�7 逄1�7 螄1�7 匄1�7 狄1�7 庄1�7 脄1�7 疄1�7 辄1�7 "
						+ " 帄1�7 旄1�7 锄1�7 醄1�7 霄1�7 湄1�7 堄1�7 嘄1�7 怄1�7 蟄1�7 丄1�7 仄1�7 附1�7 附1�7 還1�7 郄1�7 圄1�7 埄1�7 鼄1�7 芄1�7 "
						+ " 擄1�7 各1�7 噄1�7 庄1�7 淄1�7 媄1�7 纄1�7 构1�7 甄1�7 睄1�7 罄1�7 铄1�7 癄1�7 裄1�7 疄1�7 蚄1�7 蜄1�7 貄1�7 谄1�7 骄1�7 "
						+ " 缄1�7 犄1�7 胄1�7 翄1�7 蹄1�7 剄1�7 莄1�7 嘄1�7 嫄1�7 骄1�7 缄1�7 殄1�7 瞄1�7 螄1�7 苄1�7 氄1�7 姄1�7 嫄1�7 榄1�7 牄1�7 "
						+ " 预1�7 俄1�7 娄1�7 鲄1�7 叄1�7 附1�7 鄄1�7 泄1�7 珄1�7 攄1�7 钄1�7 钄1�7 钄1�7 的1�7 笄1�7 裄1�7 掄1�7 匄1�7 噄1�7 溄1�7 "
						+ " 濄1�7 璄1�7 氄1�7 镄1�7 镄1�7 蹄1�7 ",
				"" + " 亄1�7 亄1�7 俄1�7 圄1�7 芄1�7 芄1�7 荄1�7 萄1�7 萄1�7 葄1�7 蕄1�7 嘄1�7 屄1�7 岄1�7 岄1�7 汄1�7 淄1�7 骄1�7 组1�7 琄1�7 琄1�7 杄1�7 桄1�7 "
						+ " 槄1�7 耄1�7 欄1�7 祄1�7 憄1�7 碄1�7 预1�7 蛄1�7 蜄1�7 綄1�7 綄1�7 蹄1�7 鳄1�7 麄1�7 葄1�7 袄1�7 髄1�7 倄1�7 佄1�7 阄1�7 "
						+ " 芄1�7 芄1�7 茄1�7 荄1�7 掄1�7 岄1�7 悄1�7 慄1�7 骄1�7 搄1�7 褄1�7 缄1�7 椄1�7 犄1�7 肄1�7 愄1�7 钄1�7 虄1�7 箄1�7 箄1�7 "
						+ " 戄1�7 嫄1�7 樄1�7 戄1�7 炄1�7 锄1�7 锄1�7 镄1�7 襄1�7 蜄1�7 羄1�7 跄1�7 劄1�7 评1�7 谄1�7 荄1�7 峄1�7 愄1�7 憄1�7 缄1�7 "
						+ " 樄1�7 硄1�7 跄1�7 鞄1�7 郄1�7 惄1�7 慄1�7 妄1�7 挄1�7 锄1�7 箄1�7 芄1�7 揄1�7 各1�7 嗄1�7 噄1�7 廄1�7 溄1�7 檄1�7 锄1�7 "
						+ " 矄1�7 覄1�7 螄1�7 衄1�7 苄1�7 圄1�7 檄1�7 磄1�7 蜄1�7 罄1�7 箄1�7 綄1�7 謄1�7 鲄1�7 黄1�7 還1�7 茄1�7 穄1�7 蛄1�7 筄1�7 "
						+ " 跄1�7 銄1�7 俄1�7 巄1�7 犄1�7 湄1�7 逄1�7 遄1�7 楄1�7 资1�7 虄1�7 蚄1�7 蝄1�7 裄1�7 糄1�7 鳄1�7 鼄1�7 评1�7 劄1�7 苄1�7 "
						+ " 蕄1�7 蘄1�7 岄1�7 衄1�7 阄1�7 璄1�7 规1�7 氄1�7 朄1�7 祄1�7 磄1�7 鸄1�7 癄1�7 蛄1�7 蠄1�7 麄1�7 瞄1�7 黄1�7 评1�7 荄1�7 "
						+ " 悄1�7 组1�7 辄1�7 畄1�7 铄1�7 蜄1�7 筄1�7 鬄1�7 阄1�7 阄1�7 悄1�7 逄1�7 ",
				"" + " 苄1�7 蚄1�7 髄1�7 禄1�7 穄1�7 荄1�7 娄1�7 桄1�7 仄1�7 荄1�7 葄1�7 饄1�7 轄1�7 嵄1�7 狄1�7 榄1�7 肄1�7 蝄1�7 糄1�7 蹄1�7 鞄1�7 蓄1�7 薄1�7 "
						+ " 嚄1�7 洄1�7 溄1�7 濄1�7 缄1�7 铄1�7 襄1�7 预1�7 朄1�7 芄1�7 蕄1�7 构1�7 睄1�7 蚄1�7 偄1�7 ",
				"" + " 卄1�7 仄1�7 挄1�7 脄1�7 飄1�7 噄1�7 馄1�7 毄1�7 糄1�7 磄1�7 预1�7 埄1�7 缄1�7 缄1�7 臄1�7 瘄1�7 鳄1�7 啄1�7 铄1�7 唄1�7 嗄1�7 挄1�7 歄1�7 "
						+ " 铄1�7 痄1�7 裄1�7 霄1�7 鲄1�7 剄1�7 讄1�7 鄄1�7 埄1�7 芄1�7 潄1�7 姄1�7 嬄1�7 骄1�7 膄1�7 禄1�7 钄1�7 疄1�7 蟄1�7 舄1�7 "
						+ " 跄1�7 鳄1�7 垄1�7 组1�7 殄1�7 熄1�7 规1�7 劄1�7 苄1�7 潄1�7 杄1�7 蛄1�7 筄1�7 艄1�7 慄1�7 厄1�7 佄1�7 摄1�7 猄1�7 滄1�7 "
						+ " 歄1�7 畄1�7 麄1�7 评1�7 谄1�7 莄1�7 葄1�7 哄1�7 渄1�7 椄1�7 胄1�7 矄1�7 蜄1�7 嵄1�7 渄1�7 晄1�7 眄1�7 笄1�7 嗄1�7 噄1�7 "
						+ " 仄1�7 侄1�7 恄1�7 谄1�7 埄1�7 莄1�7 蓄1�7 弄1�7 轄1�7 贄1�7 炄1�7 铄1�7 螄1�7 舄1�7 筄1�7 酄1�7 豄1�7 鲄1�7 鲄1�7 狄1�7 "
						+ " 组1�7 艄1�7 黄1�7 倄1�7 塄1�7 菄1�7 摄1�7 沄1�7 澄1�7 姄1�7 纄1�7 毄1�7 腄1�7 殄1�7 秄1�7 唄1�7 蟄1�7 闄1�7 涄1�7 泄1�7 "
						+ " 孄1�7 蒄1�7 搄1�7 妄1�7 槄1�7 铄1�7 嗄1�7 伄1�7 巄1�7 厄1�7 俄1�7 兄1�7 厄1�7 咄1�7 汄1�7 泄1�7 澄1�7 姄1�7 驄1�7 缄1�7 "
						+ " 祄1�7 锄1�7 鸄1�7 耄1�7 蛄1�7 笄1�7 怄1�7 讄1�7 评1�7 凄1�7 菄1�7 崄1�7 嵄1�7 忄1�7 悄1�7 淄1�7 竄1�7 薄1�7 嗄1�7 嗄1�7 "
						+ " 馄1�7 溄1�7 飄1�7 瞄1�7 锄1�7 螄1�7 处1�7 谄1�7 蔄1�7 嗄1�7 愄1�7 涄1�7 簄1�7 规1�7 稄1�7 蒄1�7 狄1�7 隄1�7 组1�7 髄1�7 "
						+ " 遄1�7 隄1�7 祄1�7 谄1�7 荄1�7 濄1�7 還1�7 燄1�7 眄1�7 睄1�7 荄1�7 狄1�7 飄1�7 榄1�7 隄1�7 蓄1�7 嗄1�7 娄1�7 桄1�7 挄1�7 睄1�7 羄1�7 ",
				"" + " 獄1�7 挄1�7 蹄1�7 拄1�7 嗄1�7 闄1�7 溄1�7 漄1�7 遄1�7 榄1�7 沄1�7 铄1�7 趄1�7 鳄1�7 苄1�7 酄1�7 汄1�7 還1�7 薄1�7 呄1�7 骄1�7 肄1�7 炄1�7 "
						+ " 钄1�7 跄1�7 鲄1�7 檄1�7 痄1�7 潄1�7 谄1�7 毄1�7 袄1�7 叄1�7 郄1�7 澄1�7 昄1�7 忄1�7 钄1�7 锄1�7 镄1�7 傄1�7 帄1�7 溄1�7 "
						+ " 瑄1�7 樄1�7 铄1�7 镄1�7 耄1�7 螄1�7 螄1�7 羄1�7 醄1�7 鼄1�7 啄1�7 洄1�7 韄1�7 焄1�7 饄1�7 忄1�7 忄1�7 慄1�7 铄1�7 藄1�7 "
						+ " 誄1�7 滄1�7 锄1�7 蹄1�7 啄1�7 嚄1�7 涄1�7 剄1�7 屄1�7 倄1�7 荄1�7 悄1�7 逄1�7 组1�7 缄1�7 鹄1�7 裄1�7 醄1�7 舄1�7 恄1�7 "
						+ " 腄1�7 掄1�7 忄1�7 阄1�7 殄1�7 畄1�7 佄1�7 苄1�7 祄1�7 窄1�7 蜄1�7 笄1�7 粄1�7 龄1�7 鲄1�7 髄1�7 萄1�7 餄1�7 烄1�7 汄1�7 "
						+ " 亄1�7 艄1�7 莄1�7 葄1�7 婄1�7 梄1�7 甄1�7 铄1�7 蜄1�7 霄1�7 侄1�7 恄1�7 桄1�7 酄1�7 瞄1�7 彄1�7 捄1�7 佄1�7 仄1�7 垄1�7 "
						+ " 茄1�7 嗄1�7 峄1�7 恄1�7 潄1�7 砄1�7 钄1�7 骄1�7 堄1�7 荄1�7 菄1�7 钄1�7 酄1�7 抄1�7 彄1�7 疄1�7 蜄1�7 忄1�7 煄1�7 饄1�7 "
						+ " 暄1�7 豄1�7 乄1�7 佄1�7 坄1�7 庄1�7 沄1�7 柄1�7 柄1�7 橄1�7 砄1�7 铄1�7 箄1�7 酄1�7 跄1�7 鼄1�7  ",
				"" + " 佄1�7 娄1�7 腄1�7 烄1�7 的1�7 惄1�7 宄1�7 婄1�7 腄1�7 剄1�7 芄1�7 莄1�7 菄1�7 纄1�7 组1�7 琄1�7 脄1�7 畄1�7 蜄1�7 罄1�7 射1�7 惄1�7 辄1�7 "
						+ " 魄1�7 评1�7 隄1�7 隄1�7 圄1�7 葄1�7 薄1�7 帄1�7 帄1�7 崄1�7 嵄1�7 猄1�7 猄1�7 闄1�7 沄1�7 洄1�7 涄1�7 逄1�7 娄1�7 玄1�7 "
						+ " 韄1�7 軄1�7 炄1�7 煄1�7 痄1�7 艄1�7 鲄1�7 約1�7 刄1�7 阄1�7 汄1�7 璄1�7 雄1�7 蓄1�7 蕄1�7 斄1�7 倄1�7 莄1�7 善1�7 幄1�7 "
						+ " 渄1�7 肄1�7 硄1�7 龄1�7 兄1�7 仄1�7 阄1�7 還1�7 圄1�7 芄1�7 唄1�7 庄1�7 怄1�7 忄1�7 浄1�7 寄1�7 迄1�7 妄1�7 婄1�7 骄1�7 "
						+ " 杄1�7 牄1�7 焄1�7 鹄1�7 鹄1�7 痄1�7 蜄1�7 鋄1�7 鼄1�7 ",
				"" + " 僄1�7 兄1�7 隄1�7 郄1�7 茄1�7 菄1�7 葄1�7 蓄1�7 奄1�7 唄1�7 径1�7 饄1�7 阄1�7 浄1�7 淄1�7 屄1�7 玄1�7 樄1�7 曄1�7 规1�7 欄1�7 歄1�7 熄1�7 "
						+ " 禄1�7 禄1�7 的1�7 穄1�7 裄1�7 蜄1�7 螄1�7 蟄1�7 舄1�7 舄1�7 羄1�7 粄1�7 翄1�7 醄1�7 蹄1�7 鼄1�7 呄1�7 狄1�7 遄1�7 瑄1�7 "
						+ " 柄1�7 硄1�7 瘄1�7 罄1�7 黄1�7 冄1�7 苄1�7 莄1�7 藄1�7 岄1�7 猄1�7 暄1�7 娄1�7 氄1�7 燄1�7 祄1�7 鹄1�7 痄1�7 蚄1�7 筄1�7 "
						+ " 籄1�7 酄1�7 跄1�7 跄1�7 霄1�7 芄1�7 葄1�7 饄1�7 庄1�7 骄1�7 缄1�7 蟄1�7 鲄1�7 飄1�7 哄1�7 崄1�7 潄1�7 逄1�7 骄1�7 组1�7 "
						+ " 构1�7 构1�7 蛄1�7 筄1�7 箄1�7 魄1�7 偄1�7 亄1�7 勄1�7 燄1�7 薄1�7 撄1�7 獄1�7 廄1�7 渄1�7 瀄1�7 還1�7 组1�7 缄1�7 榄1�7 "
						+ " 榄1�7 预1�7 躄1�7 忄1�7 衄1�7 囄1�7 馄1�7 昄1�7 歄1�7 镄1�7 鑄1�7 附1�7 荄1�7 荄1�7 擄1�7 饄1�7 悄1�7 硄1�7 芄1�7 咄1�7 "
						+ " 岄1�7 馄1�7 庄1�7 溄1�7 鸄1�7 貄1�7 髄1�7 评1�7 勄1�7 圄1�7 蓄1�7 洄1�7 浄1�7 溄1�7 顄1�7 栄1�7 煄1�7 砄1�7 盄1�7 胄1�7 "
						+ " 糄1�7 醄1�7 億1�7 谄1�7 萄1�7 揄1�7 泄1�7 渄1�7 漄1�7 璄1�7 楄1�7 暄1�7 炄1�7 煄1�7 碄1�7 铄1�7 镄1�7 痄1�7 薄1�7 谄1�7 "
						+ " 噄1�7 泄1�7 踄1�7 鳄1�7 浄1�7 巄1�7 郄1�7 埄1�7 荄1�7 蕄1�7 獄1�7 恄1�7 洄1�7 浄1�7 曄1�7 窄1�7 醄1�7 鲄1�7 ",
				"" + " 垄1�7 揄1�7 岄1�7 迄1�7 娄1�7 琄1�7 桄1�7 氄1�7 砄1�7 睄1�7 痄1�7 厄1�7 资1�7 剄1�7 俄1�7 偄1�7 兄1�7 谄1�7 阄1�7 郄1�7 鄄1�7 芄1�7 菄1�7 "
						+ " 崄1�7 恄1�7 闄1�7 阄1�7 湄1�7 滄1�7 妄1�7 嫄1�7 琄1�7 檄1�7 晄1�7 胄1�7 腄1�7 焄1�7 罄1�7 筄1�7 酄1�7 魄1�7 餄1�7 鼄1�7 "
						+ " 疄1�7 炄1�7 烄1�7 恄1�7 蛄1�7 舄1�7 钄1�7 处1�7 爄1�7 各1�7 崄1�7 径1�7 幄1�7 珄1�7 杄1�7 轄1�7 曄1�7 肄1�7 铄1�7 鹄1�7 "
						+ " 窄1�7 繄1�7 鳄1�7 噄1�7 耄1�7 掄1�7 曄1�7 腄1�7 拄1�7 靄1�7 谄1�7 還1�7 揄1�7 晄1�7 烄1�7 铄1�7 铄1�7 翄1�7 组1�7 刄1�7 "
						+ " 劄1�7 仄1�7 佄1�7 佄1�7 评1�7 圄1�7 埄1�7 懄1�7 苄1�7 荄1�7 薄1�7 弄1�7 奄1�7 挄1�7 弄1�7 呄1�7 咄1�7 咄1�7 嗄1�7 噄1�7 "
						+ " 峄1�7 嶄1�7 猄1�7 饄1�7 怄1�7 怄1�7 悄1�7 殄1�7 轄1�7 贄1�7 欄1�7 旄1�7 熄1�7 眄1�7 钄1�7 镄1�7 镄1�7 痄1�7 瘄1�7 癄1�7 "
						+ " 翄1�7 蜄1�7 舄1�7 羄1�7 翄1�7 酄1�7 黄1�7 胄1�7 鄄1�7 圄1�7 垄1�7 堄1�7 茄1�7 各1�7 善1�7 狄1�7 处1�7 洄1�7 湄1�7 氄1�7 "
						+ " 铄1�7 瘄1�7 窄1�7 蚄1�7 霄1�7 龄1�7 嬄1�7 郄1�7 茄1�7 荄1�7 莄1�7 萄1�7 蓄1�7 撄1�7 嘄1�7 膄1�7 滄1�7 潄1�7 瀄1�7 瑄1�7 "
						+ " 璄1�7 楄1�7 媄1�7 鹄1�7 瘄1�7 预1�7 罄1�7 臄1�7 痄1�7 雄1�7 踄1�7 咄1�7 泄1�7 恄1�7 俄1�7 壄1�7 墄1�7 善1�7 慄1�7 還1�7 "
						+ " 镄1�7 甄1�7 鳄1�7 饄1�7 釄1�7 卄1�7 攄1�7 侄1�7 莄1�7 莄1�7 莄1�7 射1�7 呄1�7 囄1�7 宄1�7 柄1�7 猄1�7 牄1�7 铄1�7 疄1�7 "
						+ " 蚄1�7 蚄1�7 蝄1�7 蝄1�7 繄1�7 鱄1�7 黄1�7 鼄1�7 禄1�7 毄1�7 伄1�7 俄1�7 谄1�7 谄1�7 萄1�7 菄1�7 蓄1�7 揄1�7 圄1�7 圄1�7 "
						+ " 嵄1�7 狄1�7 饄1�7 馄1�7 庄1�7 阄1�7 鬄1�7 妄1�7 妄1�7 纄1�7 瑄1�7 昄1�7 规1�7 腄1�7 腄1�7 欄1�7 斄1�7 煄1�7 熄1�7 燄1�7 "
						+ " 聄1�7 钄1�7 鹄1�7 鹄1�7 瘄1�7 瘄1�7 窄1�7 窄1�7 蜄1�7 蝄1�7 竄1�7 臄1�7 舄1�7 雄1�7 龄1�7 垄1�7 塄1�7 芄1�7 掄1�7 圄1�7 "
						+ " 沄1�7 媄1�7 瑄1�7 橄1�7 爄1�7 眄1�7 鸄1�7 螄1�7 箄1�7 鼄1�7 龄1�7 瀄1�7 栄1�7 樄1�7 刄1�7 钄1�7 郄1�7 附1�7 蕄1�7 酄1�7 "
						+ " 晄1�7 韄1�7 郄1�7 芄1�7 狄1�7 恄1�7 愄1�7 纄1�7 韄1�7 殄1�7 昄1�7 氄1�7 熄1�7 ",
				"" + " 咄1�7 甄1�7 拄1�7 瓄1�7 昄1�7 簄1�7 糄1�7 趄1�7 錄1�7 奄1�7 驄1�7 臄1�7 唄1�7 仄1�7 资1�7 啄1�7 帄1�7 迄1�7 昄1�7 笄1�7 箄1�7 舄1�7 谄1�7 "
						+ " 缄1�7 甄1�7 罄1�7 锄1�7 揄1�7 各1�7 哄1�7 善1�7 楄1�7 砄1�7 痄1�7 蚄1�7 齄1�7 砄1�7 瘄1�7 谄1�7 搄1�7 旄1�7 瘄1�7 仄1�7 "
						+ " 鄄1�7 幄1�7 嶄1�7 獄1�7 嫄1�7 璄1�7 蟄1�7 肄1�7 评1�7 啄1�7 棄1�7 钄1�7 笄1�7 锄1�7 蔄1�7 谄1�7 摄1�7 柄1�7 辄1�7 磄1�7 "
						+ " 鹄1�7 褄1�7 蜄1�7 资1�7 甄1�7 砄1�7 臄1�7 贄1�7 侄1�7 构1�7 疄1�7 圄1�7 蓄1�7 浄1�7 溄1�7 缄1�7 桄1�7 椄1�7 榄1�7 轄1�7 "
						+ " 资1�7 胄1�7 朄1�7 祄1�7 畄1�7 稄1�7 鸄1�7 箄1�7 帄1�7 评1�7 峄1�7 径1�7 钄1�7 卄1�7 附1�7 郄1�7 埄1�7 芄1�7 摄1�7 帄1�7 "
						+ " 径1�7 忄1�7 彄1�7 咄1�7 骄1�7 栄1�7 构1�7 栄1�7 桄1�7 轄1�7 轄1�7 贄1�7 胄1�7 膄1�7 祄1�7 祄1�7 黄1�7 雄1�7 鸄1�7 痄1�7 "
						+ " 蛄1�7 組1�7 酄1�7 跄1�7 踄1�7 踄1�7 豄1�7 规1�7 冄1�7 忄1�7 锄1�7 螄1�7 舄1�7 踄1�7 荄1�7 啄1�7 妄1�7 纄1�7 组1�7 胄1�7 "
						+ " 碄1�7 籄1�7 繄1�7 酄1�7 伄1�7 侄1�7 還1�7 茄1�7 洄1�7 渄1�7 潄1�7 杄1�7 槄1�7 橄1�7 炄1�7 铄1�7 疄1�7 瘄1�7 褄1�7 竄1�7 "
						+ " 箄1�7 舄1�7 翄1�7 躄1�7 麄1�7 挄1�7 曄1�7 拄1�7 範1�7 啄1�7 馄1�7 沄1�7 预1�7 僄1�7 奄1�7 骄1�7 缄1�7 倄1�7 评1�7 擄1�7 "
						+ " 浄1�7 涄1�7 濄1�7 棄1�7 焄1�7 禄1�7 斄1�7 镄1�7 茄1�7 呄1�7 嵄1�7 姄1�7 孄1�7 缄1�7 梄1�7 辄1�7 资1�7 恄1�7 眄1�7 锄1�7 "
						+ " 秄1�7 耄1�7 笄1�7 粄1�7 趄1�7 规1�7 訄1�7 龄1�7 鲄1�7 髄1�7 偄1�7 构1�7 腄1�7 粄1�7 评1�7 附1�7 鄄1�7 驄1�7 鲄1�7 评1�7 "
						+ " 俄1�7 菄1�7 镄1�7 攄1�7 缄1�7 躄1�7 蕄1�7 撄1�7 樄1�7 鳄1�7 柄1�7 阄1�7 唄1�7 嘄1�7 怄1�7 胄1�7 祄1�7 笄1�7 " };

		int len = character.getBytes().length;
		int i = 0;
		k = -1;
		while (i < len) {
			j = character.getBytes()[i] & 0xFF;
			if (j < 128) {
				k++;
				result.append((char) character.getBytes()[i]);
				i++;
			} else {
				k++;
				n = character.getBytes()[i] & 0xFF;
				n = n * 256;
				m = character.getBytes()[i + 1] & 0xFF;
				p = n + m;
				if (p == 41891) {
					firstzm = '#';
				} else if (p == 41892) {
					firstzm = '$';
				} else if (p == 41901) {
					firstzm = '-';
				} else if (p == 41458) {
					firstzm = '@';
				} else if (p == 41407) {
					firstzm = ']';
				} else if (p == 41406) {
					firstzm = '[';
				} else if (p == 41399) {
					firstzm = '>';
				} else if (p == 41398) {
					firstzm = '<';
				} else if (p == 41279) {
					firstzm = '.';
				}

				else if (p == 41896) {
					firstzm = '(';
				} else if (p == 41897) {
					firstzm = ')';
				} else if (p == 41387) {
					firstzm = '~';
				} else if (p == 41893) {
					firstzm = '%';
				} else if (p == 41465) {
					firstzm = '&';
				} else if (p == 41915) {
					firstzm = ';';
				} else if (p == 41900) {
					firstzm = ',';
				} else if (p == 41889) {
					firstzm = '?';
				} else if (p == 41919) {
					firstzm = '!';
				}

				else if (p == 41904) {
					firstzm = '0';
				} else if (p == 41905) {
					firstzm = '1';
				} else if (p == 41906) {
					firstzm = '2';
				} else if (p == 41907) {
					firstzm = '3';
				} else if (p == 41908) {
					firstzm = '4';
				} else if (p == 41909) {
					firstzm = '5';
				} else if (p == 41910) {
					firstzm = '6';
				} else if (p == 41911) {
					firstzm = '7';
				} else if (p == 41912) {
					firstzm = '8';
				} else if (p == 41913) {
					firstzm = '9';
				}

				else if (p == 41921) {
					firstzm = 'A';
				} else if (p == 41922) {
					firstzm = 'B';
				} else if (p == 41923) {
					firstzm = 'C';
				} else if (p == 41924) {
					firstzm = 'D';
				} else if (p == 41925) {
					firstzm = 'E';
				} else if (p == 41926) {
					firstzm = 'F';
				} else if (p == 41927) {
					firstzm = 'G';
				} else if (p == 41928) {
					firstzm = 'H';
				} else if (p == 41929) {
					firstzm = 'I';
				} else if (p == 41930) {
					firstzm = 'G';
				} else if (p == 41931) {
					firstzm = 'K';
				} else if (p == 41932) {
					firstzm = 'L';
				} else if (p == 41933) {
					firstzm = 'M';
				} else if (p == 41934) {
					firstzm = 'N';
				} else if (p == 41935) {
					firstzm = 'O';
				} else if (p == 41936) {
					firstzm = 'P';
				} else if (p == 41937) {
					firstzm = 'Q';
				} else if (p == 41938) {
					firstzm = 'R';
				} else if (p == 41939) {
					firstzm = 'S';
				} else if (p == 41940) {
					firstzm = 'T';
				} else if (p == 41941) {
					firstzm = 'U';
				} else if (p == 41942) {
					firstzm = 'V';
				} else if (p == 41943) {
					firstzm = 'W';
				} else if (p == 41944) {
					firstzm = 'X';
				} else if (p == 41945) {
					firstzm = 'Y';
				} else if (p == 41946) {
					firstzm = 'Z';
				}

				else if (p >= 45217 && p <= 45252) {
					firstzm = 'A';
				} else if (p >= 45253 && p <= 45760) {
					firstzm = 'B';
				} else if (p >= 45761 && p <= 46317) {
					firstzm = 'C';
				} else if (p >= 46318 && p <= 46825) {
					firstzm = 'D';
				} else if (p >= 46826 && p <= 47009) {
					firstzm = 'E';
				} else if (p >= 47010 && p <= 47296) {
					firstzm = 'F';
				} else if (p >= 47297 && p <= 47613) {
					firstzm = 'G';
				} else if (p >= 47614 && p <= 48118) {
					firstzm = 'H';
				} else if (p >= 48119 && p <= 49061) {
					firstzm = 'J';
				} else if (p >= 49062 && p <= 49323) {
					firstzm = 'K';
				} else if (p >= 49324 && p <= 49895) {
					firstzm = 'L';
				} else if (p >= 49896 && p <= 50370) {
					firstzm = 'M';
				} else if (p >= 50371 && p <= 50613) {
					firstzm = 'N';
				} else if (p >= 50614 && p <= 50621) {
					firstzm = 'O';
				} else if (p >= 50622 && p <= 50905) {
					firstzm = 'P';
				} else if (p >= 50906 && p <= 51386) {
					firstzm = 'Q';
				} else if (p >= 51387 && p <= 51445) {
					firstzm = 'R';
				} else if (p >= 51446 && p <= 52217) {
					firstzm = 'S';
				} else if (p >= 52218 && p <= 52697) {
					firstzm = 'T';
				} else if (p >= 52698 && p <= 52979) {
					firstzm = 'W';
				} else if (p >= 52980 && p <= 53688) {
					firstzm = 'X';
				} else if (p >= 53689 && p <= 54480) {
					firstzm = 'Y';
				} else if (p >= 54481 && p <= 55289) {
					firstzm = 'Z';
				} else {
					firstzm = '*';
				}

				if (firstzm == '*') {
					byte[] tmp = new byte[2];
					tmp[0] = character.getBytes()[i];
					tmp[1] = character.getBytes()[i + 1];
					String s = new String(tmp);
					for (l = 0; l < 23; l++) {
						if (Strhz[l].indexOf(s) >= 0) {
							result.append(zm1[l]);
							break;
						}
					}
					if (l == 23)
						result.append('*');
				} else {
					result.append(firstzm);
				}
				i++;
				i++;
			}

		}
		return result.toString();
	}

	/**
	 * 判断丄1�7个字符串是否有�1�7�，空格也不算有倄1�7
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean availableStr(String str) {
		return (str != null && !"".equals(str));
	}

	/**
	 * 
	 * 将字符串中html标记去掉，可以用于防止html代码注入
	 * 
	 * @param inputString
	 *            包含html的字符串
	 * @return 去掉html标记的字符串
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{戄1�7script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{戄1�7style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			logger.error("html2Text is faild.",e);
		}

		return textStr;// 返回文本字符丄1�7
	}

	/**
	 * 按sql对需要转义的字符串进行转义�1�7�用于SQL
	 * 
	 * @param str
	 *            霄1�7要转义的字符串，必须有�1�7�1�7
	 * @param escapeCh
	 *            自定义转义字符，丄1�7般为'\'
	 * @return 在字符串前加'%;后加%';并进行特殊字符转义；如：a%c返回＄1�7"'%a\%c%'";
	 */
	public static String transferSql(String str, char escapeCh) {
		if (str != null && !"".equals(str)) {
			StringBuffer sbf = new StringBuffer(str.length());
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if (ch == '%' || ch == '_' || ch == escapeCh) {
					sbf.append(escapeCh).append(ch);
				} else
					sbf.append(ch);
			}
			return sbf.toString();
		} else{
			throw new java.lang.IllegalArgumentException("传入的字符串必须有�1�7�！");
		}
	}

	/**
	 * 截取给定字符两端空格
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String trim(String str) {
		if (str == null)
		{
			return null;
		}
		str = str.trim();
		if (str.length() == 0){
			return null;
		}else{
			return str;
		}
	}

	/**
	 * 中日文字符校骄1�7
	 * 
	 * <ul>
	 * <li>@param value 字符丄1�7
	 * <li>@return boolean
	 * </ul>
	 */
	public static boolean isHasCn(String value) {
		if (value == null){
			return false;
		}
		if (value.equals("")){
			return false;
		}
		char[] cs = value.toCharArray();

		for (int i = 0; i < cs.length; i++) {
			if (Character.getType(cs[i]) != 5){
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否是整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str == null){
			return true;
		}
		return str.matches("^[-\\+]?\\d+$");
	}

	/**
	 * 判断字符串是否是浮点敄1�7
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str) {
		if (str == null){
			return true;
		}
		return str
				.matches("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$")
				|| isInteger(str);
	}

	/**
	 * 判断字符串中的大括号、中括号、小括号是否匹配
	 * 调用的例子：StringUtil.analyiz("fewe{f(sdd(f)a[j]sdk)j}f",0);
	 * 
	 * @param text
	 * @param ip
	 * @return
	 */
	private static LinkedList<Character> stack = new LinkedList<Character>();

	public static boolean analyiz(String text, int ip) {
		char temp = text.charAt(ip);
		char a;
		if (temp == '(' || temp == '[' || temp == '{') {
			stack.add(temp);
		} else if (temp == ')') {
			a = stack.getLast();
			if (a == '(') {
				stack.removeLast();
			}
		} else if (temp == ']') {
			a = stack.getLast();
			if (a == '[') {
				stack.removeLast();
			}
		} else if (temp == '}') {
			a = stack.getLast();
			if (a == '{') {
				stack.removeLast();
			}
		}
		if (stack.size() == 0 && ip == text.length() - 1) {
			return true;
		} else if (stack.size() != 0 && ip == text.length() - 1) {
			return false;
		} else {
			return analyiz(text, ip + 1);
		}
	}
	
	public static String boldString(String src)
	{
		StringBuffer strbuf =new StringBuffer("");
		strbuf.append("<f");
		return strbuf.toString();
	}
	
	public static String quotationMarkString(String src)
	{
		StringBuilder strbuf =new StringBuilder("");
		String targetStr=null;
		if(src!=null)
		{
			int length =src.indexOf(',');
			if(length==-1)
			{
				
				strbuf.append("'");
				strbuf.append(src);
				strbuf.append("'");
				targetStr =strbuf.toString();
			}
			else 
			{
				String[] tmpString =src.split(",");
				for(int i=0;i<tmpString.length;i++)
				{
					strbuf.append("'");
					strbuf.append(tmpString[i]);
					strbuf.append("',");
					
				}
				String tmp =strbuf.toString();
				
				targetStr=tmp.substring(0, tmp.length()-1);
				
			}
		}
	
		return targetStr;
	}
	
	
	public static String[] splitString(String src)
    {
	    String[] targetStr =src.split(",");
        return targetStr;
    }
	
	/**
	 * 生成Token
	 * 
	 * @return
	 */
	public static String getToken() {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(30);
		for (int i = 0; i < 30; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
	
	
	
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyzABCDEFGHIJKMNPQRSTUVWXYZ";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	
	/**
	 * 生成以中心点为中心的四方形经纬度
	 * 
	 * @param lat
	 *            纬度
	 * @param lon
	 *            精度
	 * @param raidus
	 *            半径
	 * @return
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

	

	private static final double EARTH_RADIUS = 6378137;
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lng1) - rad(lng2);
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +  Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s;
    }
	
	public static String truncationStr(String source) {
		String targetStr="";
		if(source.length()<25)
		{
			targetStr=source;
		}
		else
		{
			targetStr =source.substring(0, 25);
			targetStr+="......";
		}
		
		return targetStr;
	}
	
	/** 
     * 生成订单编号 
     * @return 
     */  
    public static synchronized String getOrderNo() {  
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());  
        if(date==null||!date.equals(str)){  
            date = str;  
            orderNum  = 0l;  
        }  
        orderNum++;  
        long orderNo = Long.parseLong((date)) * 100000;  
        orderNo += orderNum;;  
        return orderNo+"";  
    }  
    
    public static String getPercent(int x,int total){  
	   String result="";
	   double x_double=x*1.0;  
	   double tempresult=x_double/total;  
	   DecimalFormat df1 = new DecimalFormat("0.00%");
	   result= df1.format(tempresult);    
	   return result;  
	}
    
    public static String subTimeString(String time){  
 	   String result= time.substring(0, time.length()-2);
 	   return result;  
 	}
    
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    public static String randommobile(){
    	String[] a = {"3" , "5" , "8"};
    	Random rd = new Random(System.currentTimeMillis());
    	int idx = rd.nextInt(a.length);
    	String mobile = "1" + a[idx] + Createnonce.nonce(9);
    	return mobile;
    }
}
