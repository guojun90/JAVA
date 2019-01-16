package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;

public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String dateFormat(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String dateTimeFormat(Date date) {
		return format(date, DATE_TIME_PATTERN);
	}

	public static String timeFormat(Date date) {

		String dateTimeStr = dateTimeFormat(date);
		return dateTimeStr.substring(11, dateTimeStr.length());

	}

	public static Date parshDateTimeFormat(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			logger.error("日志格式转换异常", e);
			// e.printStackTrace();
		}

		return date;
	}

	public static Date parshDateFormat(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			logger.error("日志格式转换异常", e);
			// e.printStackTrace();
		}

		return date;
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	public static boolean isValidTime(String str, String patten) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(patten);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static long compairWithDate(Date date) {

		Date now = new Date();
		long between = date.getTime() - now.getTime();

		return between;

	}

//	/**
//	 * 获取指定格式Date
//	 * @param date
//	 * @param pattern
//	 * @return
//	 */
//	public Date getDateFormat(Date date,String pattern) {
//		SimpleDateFormat df = new SimpleDateFormat(pattern);
//		String dateStr = df.format(date);
//	}
	/**
	 * 与当前日期比较，返回字符串差
	 * 
	 * @param date
	 * @return
	 */
	public static int compareWithNowDate(Date date) {
		Date now = new Date();
		String nowStr = format(now, "yyyy-MM-dd").toString();
		String dateStr = format(date, "yyyy-MM-dd").toString();
		return dateStr.compareTo(nowStr);

	}

	/**
	 * @param date
	 * @return 返回比较时间 - 当前时间的毫秒差
	 */
	public static long compairWithTime(Date date) {

		Date now = new Date();
		String nowDateStr = DateUtils.dateFormat(now);
		String timeStr = DateUtils.timeFormat(date);

		Date comparieDate = DateUtils.parshDateTimeFormat(nowDateStr + " " + timeStr);

		long between = comparieDate.getTime() - now.getTime();

		return between;

	}

//	public static boolean isReadyToStart(Date startDate, Date startTime, Date endDate, Date endTime, int aheadMinues) {
//
//		if (DateUtils.compareWithNowDate(startDate) <= 0 && DateUtils.compareWithNowDate(endDate) >= 0) {
//			if (DateUtils.compairWithTime(startTime) < aheadMinues * 60 * 1000
//					&& DateUtils.compairWithTime(endTime) > 0) {
//				return true;
//			}
//		}
//		return false;
//
//	}

	public static boolean isReadyToStart(Date startDate, Date startTime, Date endDate, Date endTime, int aheadMinutes) {

		boolean ret = false;
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");
		engine.put("startDate", startDate);
		engine.put("startTime", startTime);
		engine.put("endDate", endDate);
		engine.put("endTime", endTime);
		engine.put("aheadMinutes", aheadMinutes);
		// script:将startDate -minutes
		String setOffectedTimeScript = "var DateUtils = Java.type('com.cmcczj.api.bid.util.DateUtils');"
				+ "(DateUtils.inDateRange(startDate, endDate) && DateUtils.inTimeRange(startTime, endTime,aheadMinutes));";
		try {
			ret = (boolean) engine.eval(setOffectedTimeScript);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 当前日期是否在范围内
	 * 
	 * @param start
	 * @param end
	 * @param pattern 传入的Date格式
	 * @return
	 */
	public static boolean inDateRange(Date start, Date end) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowStr = df.format(now);
		String startStr = df.format(start);
		String endStr = df.format(end);

		if (nowStr.compareTo(startStr) >= 0 && nowStr.compareTo(endStr) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean inTimeRange(Date start, Date end) {
		if (compairWithTime(start) <= 0 && compairWithTime(end) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean inTimeRange(Date start, Date end,int aheadMinutes) {
		Date offsetedStartTime = new Date(start.getTime()-aheadMinutes*60*1000);
		if (compairWithTime(offsetedStartTime) <= 0 && compairWithTime(end) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String getStr() {
		return "hello";
	}

	public static void main(String[] args) {

		Date startDate = DateUtils.parshDateTimeFormat("2018-09-05 00:05:03");
		Date endDate = DateUtils.parshDateTimeFormat("2018-09-07 00:00:00");
		Date startTime = DateUtils.parshDateTimeFormat("2018-09-13 12:50:00");
		Date endTime = DateUtils.parshDateTimeFormat("2018-07-01 19:20:20");

//		System.out.println(inDateRange(startDate, endDate, "yyyy-MM-dd"));
		System.out.println(isReadyToStart(startDate, startTime, endDate, endTime, 5));

		/*
		 * if (isReadyToStart(startDate, startTime, endDate, endTime, 5)) {
		 * System.out.println("pass!"); } else { System.out.println("false"); }
		 */
//		System.out.println(DateUtils.compairWithDate(d));
//		System.out.println(DateUtils.compairWithTime(d1));
//		
//		System.out.println("现在的日期大："+nowBigger(d));

	}

}