package com.drugmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
   public static String formatDate(Date date){
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   return format.format(date);
   }
   /**
	 * 根据指定格式，将日期转换成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatDate(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String returnDate = "";
		try {
			returnDate = df.format(date);
		} catch (Exception e) {
			returnDate = "";
		}
		return returnDate;
	}
}
