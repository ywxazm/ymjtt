package com.ymjtt.common.util.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author  ywx
 * @date    2018/11/14 13:44
 */
public class DateUtils {

	/** yyyy:年 */
	public static final String DATE_YEAR = "yyyy";

	/** MM：月 */
	public static final String DATE_MONTH = "MM";

	/** DD：日 */
	public static final String DATE_DAY = "dd";

	/** HH：时 */
	public static final String DATE_HOUR = "HH";

	/** mm：分 */
	public static final String DATE_MINUTE = "mm";

	/** ss：秒 */
	public static final String DATE_SECONDES = "ss";

	/** yyyy-MM-dd hh:mm:ss */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** yyyy-MM-dd */
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/** yyyyMMddhhmmss */
	public static final String TIME_NOFUll_FORMAT = "yyyyMMddHHmmss";

	/** yyyyMMdd */
	public static final String DATE_NOFUll_FORMAT = "yyyyMMdd";


	/**
	 * 获取当前日期时间
	 * @author  ywx
	 * @date    2018/11/14 11:02
	 * @param   [format]
	 * @return  java.lang.String
	 */
	public static LocalDateTime getCurrentDateTime(){
		return LocalDateTime.now();
	}

	/**
	 * 获取当前日期
	 * @author  ywx
	 * @date    2018/11/14 13:56
	 * @param   []
	 * @return  java.time.LocalDateTime
	 */
	public static LocalDate getCurrentDate(){
		return LocalDate.now();
	}

	/**
	 * 获取当前时间
	 * @author  ywx
	 * @date    2018/11/14 13:56
	 * @param   []
	 * @return  java.time.LocalDateTime
	 */
	public static LocalTime getCurrentTime(){
		return LocalTime.now();
	}

	/**
	 * LocalDateTime 转 LocalDate
	 * @author  ywx
	 * @date    2018/11/14 14:00
	 * @param   [localDateTime]
	 * @return  java.time.LocalDate
	 */
	public static LocalDate LocalDateTime2LocalDate(LocalDateTime localDateTime) {
		return localDateTime.toLocalDate();
	}

	/**
	 * LocalDateTime 转 LocalTime
	 * @author  ywx
	 * @date    2018/11/14 14:00
	 * @param   [localDateTime]
	 * @return  java.time.LocalTime
	 */
	public static LocalTime LocalDateTime2LocalTime(LocalDateTime localDateTime) {
		return localDateTime.toLocalTime();
	}

	/**
	 * LocalDateTime 转 Date
	 * @author  ywx
	 * @date    2018/11/14 14:11
	 * @param   [localDateTime]
	 * @return  java.util.Date
	 */
	public static Date LocalDateTime2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		return Date.from(zdt.toInstant());
	}

	/**
	 * Date 转 LocalDateTime
	 * @author  ywx
	 * @date    2018/11/14 14:11
	 * @param   [date]
	 * @return  java.time.LocalDateTime
	 */
	public static LocalDateTime Date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		return instant.atZone(zoneId).toLocalDateTime();
	}

	/**
	 * LocalDateTime 转 String
	 * @author  ywx
	 * @date    2018/11/14 14:11
	 * @param   [localDateTime]
	 * @return  java.lang.String
	 */
	public static String LocalDateTime2String(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return dtf.format(localDateTime);
	}

	/**
	 * LocalDateTime 转 String, 默认格式"yyyy-MM-dd HH:mm:ss"
	 * @author  ywx
	 * @date    2018/11/14 14:11
	 * @param   [localDateTime]
	 * @return  java.lang.String
	 */
	public static String LocalDateTime2String(LocalDateTime localDateTime) {
		return LocalDateTime2String(localDateTime, DATETIME_FORMAT);
	}

	/**
	 * String 转 LocalDateTime
	 * @author  ywx
	 * @date    2018/11/14 14:26
	 * @param   [dateString, pattern]
	 * @return  java.time.LocalDateTime
	 */
	public static LocalDateTime String2LocalDateTime(String dateString, String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(dateString, dtf);
	}

	/**
	 * String 转 LocalDateTime, 默认格式"yyyy-MM-dd HH:mm:ss"
	 * @author  ywx
	 * @date    2018/11/14 14:26
	 * @param   [dateString]
	 * @return  java.time.LocalDateTime
	 */
	public static LocalDateTime String2LocalDateTime(String dateString) {
		return String2LocalDateTime(dateString, DATETIME_FORMAT);
	}

	/**
	 * 时间戳 转 LocalDateTime
	 * @author  ywx
	 * @date    2018/11/14 14:41
	 * @param   [timestamp]
	 * @return  java.time.LocalDateTime
	 */
	public static LocalDateTime timestamp2LocalDateTime(Long timestamp) {
		return LocalDateTime.ofEpochSecond(timestamp,0, ZoneOffset.ofHours(8));
	}

	/**
	 * LocalDateTime 转 时间戳
	 * @author  ywx
	 * @date    2018/11/14 14:41
	 * @param   [localDateTime]
	 * @return  java.lang.Long
	 */
	public static Long LocalDateTime2Timestamp(LocalDateTime localDateTime) {
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return instant.toEpochMilli();
	}

}
