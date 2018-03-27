package com.mfq.home;

import com.sun.jmx.snmp.Timestamp;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 生成页面普通展示时间 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatNormalDateString(Date date){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return DateFormatUtils.format(date, pattern);
    }


    /**
     * 日期转换为字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getShotDate(Date date) {
        if (date == null){
            return "";
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    /**
     * 日期转换为字符串 格式自定义
     *
     * @param date
     * @param f
     * @return
     */
    public static String dateStr(Date date, String f) {
        if (date == null){
            return "";
        }
        return DateFormatUtils.format(date, f);
    }


    /**
     * 获得当前日期，精确到毫秒
     * @return
     */
    public static Timestamp getNowInMillis() {
        Timestamp timeStamep = new Timestamp(new Date().getTime());
        return timeStamep;
    }

    public static Date getDateByStrTime(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return  date1>date2时结果<0,  date1=date2时结果=0, date2>date1时结果>0
     */
    public static int daysBetween(Date date1, Date date2){
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(dateStr(date1, "yyyy-MM-dd"));
            Date d2 = sdf.parse(dateStr(date2, "yyyy-MM-dd"));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的小时数
     * @param date1
     * @param date2
     * @return date1>date2时结果<0,  date1=date2时结果=0, date2>date1时结果>0
     */
    public static int hoursBetween(Date date1, Date date2) {
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr(date1, "yyyyMMddHH"));
            Date d2 = sdf.parse(DateUtil.dateStr(date2, "yyyyMMddHH"));
            cal.setTime(d1);
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 3600000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }



    /**
     * 计算两个日期字符串之间相差的小时数
     * @param date1
     * @param date2
     * @return  date1>date2时结果<0,  date1=date2时结果=0, date2>date1时结果>0
     */
    public static int hoursBetweenByDateStr(String date1, String date2 , String format) {
        DateFormat sdf=new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);

            return hoursBetween(d1,d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的分钟数
     * @param date1
     * @param date2
     * @return  date1>date2时结果<0,  date1=date2时结果=0, date2>date1时结果>0
     */
    public static int minuteBetweenByDateStr(Date date1, Date date2) {
        DateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr(date1,"yyyyMMddHHmm"));
            Date d2 = sdf.parse(DateUtil.dateStr(date2,"yyyyMMddHHmm"));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf(((time2 - time1) / 60000L)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的秒数
     * @param date1
     * @param date2
     * @return  date1>date2时结果<0,  date1=date2时结果=0, date2>date1时结果>0
     */
    public static int secondBetweenByDateStr(Date date1, Date date2) {
        DateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr(date1, "yyyyMMddHHmmss"));
            Date d2 = sdf.parse(DateUtil.dateStr(date2, "yyyyMMddHHmmss"));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf(((time2 - time1) / 1000L)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
