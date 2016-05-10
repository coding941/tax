package com.trjs.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{

	
	/**
     * 获取指定格式日期字符串 yyyy-MM-dd HH:mm:ss
     * @param Date
     * @param String
     * 
     * @return
     */
    public static String date2Str(Date dt)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }
	/**
     * 获取指定格式日期字符串 yyyy-MM-dd HH:mm:ss
     * @param Date
     * @param String
     * 
     * @return
     */
    public static String date2Str(Date dt,String pattern)
    {
        if(StringUtil.isNotBlank(pattern)){
        	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	return sdf.format(dt);
        }else{
        	return date2Str(dt);
        }
    }
    /**
     * 
     * @param str		yyyy-MM-dd HH:ss:mm
     */
    public static Date strtoDate(String dateValue)
    {
    	if(StringUtil.isBlank(dateValue))return null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            date = sdf.parse(dateValue);
        }
        catch(Exception _e)
        {
            System.out.println(_e.toString());
        }
        return date;
    }
    
    
    
}
