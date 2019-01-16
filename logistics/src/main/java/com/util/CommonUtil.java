package com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 通用辅助类
 * 
 * @author CZ255009
 * @date 2018年5月15日
 */
public class CommonUtil {

	public static String CHINESE_REG = "[\\u4e00-\\u9fa5]";
	public static String NO_CHINESE_REG = "[^\u4e00-\u9fa5]";

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * 判断字符串是否为空或null
	 * 
	 * @author CZ255009
	 * @date 2018年5月15日
	 * @param str
	 *            目标串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	/**
	 * 将字符串参数转换为URL UTF-8格式输出
	 * 
	 * @author CZ255009
	 * @date 2018年5月17日
	 * @param str
	 * @return
	 */
	public static String urlEncoderUtf8(String str) {
		if (CommonUtil.isEmpty(str))
			return "";
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持的编码异常", e);
			// e.printStackTrace();
			return str;
		}
	}

	/**
	 * 获取字符串中的中文字符串
	 * 
	 * @author CZ255009
	 * @date 2018年5月18日
	 * @param str
	 * @return
	 */
	public static String getChineseString(String str) {
		try {
			if (isEmpty(str))
				return str;
			return str.replaceAll(NO_CHINESE_REG, "");
		} catch (Exception e) {
			logger.error("字符串替换异常<获取字符串中的中文字符串>", e);
			// e.printStackTrace();
			return str;
		}
	}

	/**
	 * 将一个int类型的字符串转化为int(默认0)
	 * 
	 * @author CZ255009
	 * @date 2018年5月23日
	 * @param obj
	 * @return
	 */
	public static int parseInt(Object obj) {
		if (obj == null)
			return 0;
		int in = 0;
		try {
			in = Integer.parseInt(obj.toString());
		} catch (NumberFormatException e) {
			logger.error("将字符串转化为int时异常", e);
			// e.printStackTrace();
		}
		return in;
	}

	/**
	 * 日志处理信息处理，去除特殊字符，防止日志伪造攻击
	 * 
	 * @author CZ255009
	 * @date 2018年5月31日
	 * @param log
	 * @return
	 */
	public static String vaildLog(String log) {
		List<String> list = new ArrayList<String>();
		list.add("%0d");
		list.add("\r");
		list.add("%0a");
		list.add("\n");
		String encode = Normalizer.normalize(log, Normalizer.Form.NFKC);
		for (int i = 0, len = list.size(); i < len; i++) {
			encode = encode.replace(list.get(i), "\t");
		}
		return encode;
	}

	/**
	 * 打印异常的基本信息
	 * 
	 * @author CZ255009
	 * @date 2018年5月31日
	 * @param e
	 * @return
	 */
	public static String loggerException(Exception e) {
		if (e == null) {
			return "未知异常: e == null";
		}
		return e.getClass().getName() + ": " + e.getMessage();
	}

}
