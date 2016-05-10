package com.trjs.swing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date to_Date(String datestr, String formate) {
		SimpleDateFormat dateformate = new SimpleDateFormat(formate);
		try {
			return dateformate.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}
	public static String to_Char(Date date, String formate){
		SimpleDateFormat dateformate = new SimpleDateFormat(formate);
		return dateformate.format(date);
	}
	public static Date addDays(Date date, int number){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, number);
		return ca.getTime();
	}
}
