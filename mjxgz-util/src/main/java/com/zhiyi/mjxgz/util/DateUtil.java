package com.zhiyi.mjxgz.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by WUXB on 2016-8-25.
 */
public class DateUtil {

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    /**
     * 判断字符串是否为指定的日期格式
     *
     * @param dateStr 需要判断的字符串
     * @param format 指定的日期格式
     * @return true=字符串是日期格式
     */
    public static boolean isDateFormat(String dateStr, String format) {
        try {
            new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 将Date时间转成字符串
     */
    public static String dateToStr(Date date, String format) {
        if(null == date){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将字符串时间改成Date类型
     */
    public static Date formatDate(Date date, String format) {
        Date newDate;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            newDate = simpleDateFormat.parse(dateToStr(date, format));
        } catch (ParseException e) {
            newDate = now(format);
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 将字符串时间改成Date类型
     */
    public static Date strToDate(String dateStr, String format) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串时间改成Date类型
     */
    public static String toStringDate(String dateStr, String format) {
        String result = dateStr;
        try {
            Date date = new SimpleDateFormat(format).parse(dateStr);
            result = new SimpleDateFormat(format).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     * 计算两个日期相差多少秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getTimeDelta(Date date1, Date date2) {
        long timeDelta = (date1.getTime() - date2.getTime()) / 1000; //单位是秒
        int secondsDelta = timeDelta > 0 ? (int) timeDelta : (int) Math.abs(timeDelta);
        return secondsDelta;
    }

    /**
     * 获取现在的时间
     *
     * @return Date
     */
    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 获取现在的时间并以指定格式输出
     *
     * @param format 时间格式
     * @return Date
     */
    public static Date now(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return strToDate(formatter.format(now()), format);
    }

    /**
     * 获取指定日期是星期几的代码
     *
     * @param dt
     * @return 0=星期天
     */
    public static int getWeekCodeOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w < 0 ? 0 : w;
    }

    /**
     * 获取指定日期是星期几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return weekDays[getWeekCodeOfDate(dt)];
    }

    /**
     * 得到指定时间与当前时间之间相差的天数
     *
     * @param start 指定时间
     * @return 指定时间与当前时间之间相差的天数
     */
    public static long betweenDays(Date start) {
        LocalDate startDateInclusive = dateToLocalDate(start);
        return LocalDate.now().toEpochDay() - startDateInclusive.toEpochDay();
    }

    /**
     * 得到两个之间之间相差的天数
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 开始时间与结束时间相差的天数
     */
    public static long betweenDays(Date start, Date end) {
        LocalDate startDateInclusive = dateToLocalDate(start);
        LocalDate endDateExclusive = dateToLocalDate(end);
        return endDateExclusive.toEpochDay() - startDateInclusive.toEpochDay();
    }

    /**
     * 得到两个之间之间相差的天数
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 开始时间与结束时间相差的天数
     */
    public static long betweenDays(String startDate, String endDate) {
        Date start = DateUtil.strToDate(startDate, "yyyy-MM-dd");
        Date end = DateUtil.strToDate(endDate, "yyyy-MM-dd");
        LocalDate startDateInclusive = dateToLocalDate(start);
        LocalDate endDateExclusive = dateToLocalDate(end);
        return endDateExclusive.toEpochDay() - startDateInclusive.toEpochDay();
    }

    public static LocalDate dateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获取在参考时间的基础上增加或者减少秒数后的时间
     *
     * @param date 参考时间
     * @param secondAmount 秒数，如果是减少秒数传一个负数
     * @return 格式为 yyyy-MM-dd HH:mm:ss 的新时间
     */
    public static Date getDateBySecond(Date date, int secondAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, secondAmount);
        return formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 在当前日期上增加指定的天数
     *
     * @param day 天数
     * @return 增加天数后的日期
     */
    public static Date addDay(int day) {
        return addDay(now(), day);
    }

    /**
     * 在指定日期上加指定的天数
     *
     * @param date 基础日期
     * @param day 天数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }


    /**
     * 获取两个时间之间的所有日期集合
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param formatStr     格式化方式
     * @return
     */
    public static List<String> getTwoTimeBetweenDate(Date startDate, Date endDate,String formatStr){
        List<String> list = new ArrayList<String>();
        if(DateUtils.isSameDay(startDate,endDate)){
            list.add(DateUtil.dateToStr(startDate, formatStr));
            return list;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        DateFormat df = new SimpleDateFormat(formatStr);
        while (time <= endTime) {
            Date d = new Date(time);
            list.add(df.format(d));
            time += oneDay;
        }
        return list;
    }
    /**
     * 获取两个时间之间的所有日期集合
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @return
     */
    public static List<Date> getTwoTimeBetweenDate(Date startDate, Date endDate){
        List<Date> list = new ArrayList<Date>();
        if(DateUtils.isSameDay(startDate,endDate)) {
            list.add(startDate);
            return list;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            list.add(d);
            time += oneDay;
        }
        return list;
    }

    /**
     * 返回两个时间之间的所有日期为key，value 默认为0 的 LinkedHashMap中
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param formatStr     格式化方式
     * @return
     */
    public static Map<String,Object> getTwoTimeBetweenDateForMap(Date startDate, Date endDate,String formatStr){
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> dateStrList = DateUtil.getTwoTimeBetweenDate(startDate, endDate, formatStr);
        if(dateStrList!=null && dateStrList.size()>0){
            if(dateStrList!=null && dateStrList.size()>0){
                for (String str : dateStrList) {
                    map.put(str,0);
                }
            }
        }
        return map;
    }

    /**
     * 将时间转换为时间戳
     * @param s
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 区分两个日期之间指定字段的差值
     *
     * @param time1
     *            开始时间
     * @param time2
     *            结束时间
     * @param field
     *            要比较的字段(年，月，日,...)
     * @return 如果time1>time2就反回一个正的差值,如果time1<time2则返回一个负的差值,如果相等，返回0
     */
    public static int getFieldDifference(long time1, long time2, int field) {
        if (time1 == time2) {
            return 0;
        } else if (time1 > time2) {
            return -getFieldDifference(time2, time1, field);
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setLenient(false);
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setLenient(false);
        cal2.setTimeInMillis(time2);
        for (int x = 0; x < Calendar.FIELD_COUNT; x++) {
            if (x > field) {
                cal1.clear(x);
                cal2.clear(x);
            }
        }
        time1 = cal1.getTimeInMillis();
        time2 = cal2.getTimeInMillis();

        long ms = 0;
        int min = 0, max = 1;

        while (true) {
            cal1.setTimeInMillis(time1);
            cal1.add(field, max);
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                min = max;
                break;
            } else if (ms > time2) {
                break;
            } else {
                max <<= 1;
            }
        }

        while (max > min) {
            cal1.setTimeInMillis(time1);
            int t = (min + max) >>> 1;
            cal1.add(field, t);
            ms = cal1.getTimeInMillis();
            if (ms == time2) {
                min = t;
                break;
            } else if (ms > time2) {
                max = t;
            } else {
                min = t;
            }
        }
        return -min;
    }


    /**
     * 获取指定年份月数 的第一天和最后一天
     * @param year   年
     * @param month  月
     * @return  字符串数组  ： 第一个元素为：第一天的日期；第二个元素为：最后一天的日期
     */
    public static String[] getFirstDayAndLastDayOfMonth(int year,int month){
        String[] tempArray = new String[2];
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        tempArray[1] = dateToStr(cal.getTime(), DATE_DEFAULT_FORMAT);
        //设置为1号,每月的第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        tempArray[0] = dateToStr(cal.getTime(), DATE_DEFAULT_FORMAT);
        return tempArray;
    }

    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 收集开始日期到结束日期的所有日期年月
     * @param startYm
     * @param endYm
     * @return
     */
    public static List<String>  getMonthList(String startYm,String endYm){
    	List<String> monthList = new ArrayList<String>();
    	Calendar  calendarStart =Calendar.getInstance();
      	calendarStart.setTime(DateUtil.strToDate(startYm, "yyyyMM"));
      	Calendar  calendarEnd =Calendar.getInstance();
      	calendarEnd.setTime(DateUtil.strToDate(endYm, "yyyyMM"));
      	while(calendarStart.compareTo(calendarEnd) <= 0 ){
      		monthList.add(DateUtil.dateToStr(calendarStart.getTime(), "yyyyMM"));
      		calendarStart.add(Calendar.MONTH, 1);
      	}
      	return monthList;
    }
    
    /**
     * unixTime转Date
     * @param unixTime
     * @return
     * @throws ParseException
     */
    public static Date unixTimeToDate(int unixTime) throws ParseException{  
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Long time=new Long(unixTime)*1000;    
        String d = format.format(time);  
        return format.parse(d);    
  } 
    
    public static void main(String[] args) {
        /*Date start = DateUtil.strToDate("2017-02-14 08:23:21", "yyyy-MM-dd HH:mm:ss");
        long result = DateUtil.betweenDays(start, new Date());
        System.out.println("相差：" + result + " 天");

        System.out.println(dateToStr(addDay(-7), "yyyy-MM-dd"));*/

       /* Date start = DateUtil.strToDate("02-14", "MM-dd");
        Date end = DateUtil.strToDate("03-01", "MM-dd");
        System.out.println(DateUtil.getTwoTimeBetweenDateForMap(start, end, "MM-dd"));*/

     /*   System.out.println(DateUtil.stampToDate("1491461820035"));

        System.out.println(DateUtil.dateToStr(DateUtil.getCurrYearFirst(),DATE_DEFAULT_FORMAT));
        System.out.println(DateUtil.dateToStr(DateUtil.getCurrYearLast(),DATE_DEFAULT_FORMAT));*/

/*        System.out.println(Arrays.asList(getFirstDayAndLastDayOfMonth(2016,1)));
        System.out.println(Arrays.asList(getFirstDayAndLastDayOfMonth(2016,13)));*/


       /* Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        System.out.println(year +"-- "+ month);
        System.out.println(Arrays.asList(getFirstDayAndLastDayOfMonth(year, month - 1)));
        System.out.println(Arrays.asList(getFirstDayAndLastDayOfMonth(year, month - 3)));
        System.out.println(Arrays.asList(getFirstDayAndLastDayOfMonth(year, month - 6)));
        System.out.println("==最近1个月==");
        System.out.println(getFirstDayOfMonth(year,month-1));
        System.out.println(getLastDayOfMonth(year,month-1));
        System.out.println("==最近3个月==");
        System.out.println(getFirstDayOfMonth(year,month-3));
        System.out.println(getLastDayOfMonth(year,month-1));
        System.out.println("==最近6个月==");
        System.out.println(getFirstDayOfMonth(year,month-6));
        System.out.println(getLastDayOfMonth(year,month-1));
        System.out.println("==最近1年==");
        System.out.println(getFirstDayOfMonth(year,month-12));
        System.out.println(getLastDayOfMonth(year,month-1));*/

    	try {
			System.out.println(unixTimeToDate(1506060920));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       /* Date d1 = strToDate("2018-09-22", DATE_DEFAULT_FORMAT);
        Date d2 = strToDate("2017-09-22", DATE_DEFAULT_FORMAT);
        System.out.println(DateUtils.truncatedCompareTo(d1,d2,Calendar.DATE));*/
    }
}
