package com.formssi.zengzl.base.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DateUtils {
    public static final String DATE_NUMBER_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_NUMBER_FORMAT = "yyyyMMddHHmmss";
    public static final String DATA_FORMAT = "yyyy-MM-dd";
    public static final String DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    // 计算阴历日期参照1900年到2049年  
	private static final Integer[] LUNAR_INFO = { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554,
			0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0,
			0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566,
			0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550,
			0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0,
			0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
			0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
			0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5,
			0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0,
			0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
			0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0,
			0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520,
			0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };
	// 允许输入的最小年份  
    private final static int MIN_YEAR = 1900;  
    // 允许输入的最大年份  
    private final static int MAX_YEAR = 2049;  
    // 当年是否有闰月  
    private static boolean isLeapYear;  
    // 阳历日期计算起点  
    private final static String START_DATE = "19000130"; 
    
    

    /**
     * 获得日期相加月份之后的第一天开始时间
     */
    public static Timestamp getMonthRangeStart(Object date, int monthsLater) {
        DateTime dateTime = new DateTime(date);
        DateTime plusMonthsDateTime = dateTime.plusMonths(monthsLater).withTime(0, 0, 0, 0);

        return new Timestamp(plusMonthsDateTime.dayOfMonth().withMinimumValue().getMillis());
    }

    /**
     * 获得日期相加月份之后的最后一天结束时间
     */
    public static Timestamp getMonthRangeEnd(Object date, int monthsLater) {
        DateTime dateTime = new DateTime(date);
        DateTime plusMonthsDateTime = dateTime.plusMonths(monthsLater).withTime(23, 59, 59, 999);

        return new Timestamp(plusMonthsDateTime.dayOfMonth().withMaximumValue().getMillis());
    }

    public static String formatDate2String(Object date, String formatString) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern(formatString);

        return fmt.print(dateTime);
    }

    /**
     * 获取两个日期区间每一天的日期
     *
     * @return List
     */
    public static List<String> getEveryDayDateFromStartDateAndEndDate(Object startDate, Object endDate, String format) throws ParseException {
        List<String> dateTimeList = new ArrayList<String>();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);

        DateTime start = new DateTime(startDate);
        DateTime end = new DateTime(endDate);
        int daysInterval = Days.daysBetween(start, end).getDays();

        for (int i = 0; i <= daysInterval; i++) {
            DateTime nextDateTime = start.plusDays(i);
            dateTimeList.add(fmt.print(nextDateTime));
        }

        return dateTimeList;
    }

    /**
     * 获取两个日期区间相差的月份数
     */
    public static int getMonthInterval(Object startDateTime, Object endDateTime) {
        DateTime start = new DateTime(startDateTime);
        DateTime end = new DateTime(endDateTime);

        return Months.monthsBetween(start, end).getMonths();
    }

    /**
     * 获取两个日期区间相差的分钟数
     */
    public static int getMinutesInterval(Object startDateTime, Object endDateTime) {
        DateTime start = new DateTime(startDateTime);
        DateTime end = new DateTime(endDateTime);

        return Minutes.minutesBetween(start, end).getMinutes();
    }

    /**
     * 获取两个日期区间相差的小时数
     */
    public static int getHoursInterval(Object startDateTime, Object endDateTime) {
        DateTime start = new DateTime(startDateTime);
        DateTime end = new DateTime(endDateTime);

        return Hours.hoursBetween(start, end).getHours();
    }

    /**
     * 时间计算（传入的时间加上分钟数，返回相加之后的时间）
     */
    public static Timestamp addMinuteToTimestamp(Object dateTime, int minutes) {
        DateTime dateTimeObj = new DateTime(dateTime);
        DateTime dateTimePlusObj = dateTimeObj.plusMinutes(minutes);

        Timestamp timestamp = new Timestamp(dateTimePlusObj.getMillis());

        return timestamp;
    }

    /**
     * 开始时间是否在结束时间之前
     */
    public static boolean isBefore(Object startDateTime, Object endDateTime) {
        DateTime start = new DateTime(startDateTime);
        DateTime end = new DateTime(endDateTime);

        return start.isBefore(end.getMillis());
    }

    /**
     * 获取日期当天结束的时间
     */
    public static DateTime getStartDateTimeOfDay(Object date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withTime(0, 0, 0, 0);

        return dateTime;
    }

    /**
     * 获取日期当天结束的时间
     */
    public static DateTime getEndDateTimeOfDay(Object date) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.withTime(23, 59, 59, 999);

        return dateTime;
    }

    public static List<Date> getDay24HList(Date startDate, Date endDate) {
        startDate = getDate(startDate, "yyyy-MM-dd HH");
        endDate = getDate(addMinute(endDate, 60), "yyyy-MM-dd HH");

        List<Date> dateList = new ArrayList<Date>();
        while (startDate.getTime() <= endDate.getTime()) {
            dateList.add(startDate);
            startDate = addMinute(startDate, 60);
        }

        return dateList;
    }

    /**
     * 一天24小时的正点时间列表
     *
     * @return
     */
    public static List<Date> getDay24HList() {
        Date date = getDate(new Date(), "yyyy-MM-dd");
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(date);
        for (int i = 0; i < 24; i++) {
            date = addMinute(date, 60);
            dateList.add(date);
        }

        return dateList;
    }

    public static Date getDate(Date date, String pattern) {
        return parse(pattern, format(date, pattern));
    }

    /**
     * 相差天数
     * example startDate = LocalDate.of(2020, 2, 1)
     */
    public static long getDayDiff(LocalDate startDate, LocalDate endDate){
        return startDate.until(endDate, ChronoUnit.DAYS);
    }


    /**
     * 相差秒
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long getSecondsDiff(Date d1, Date d2) {
        return Math.abs(d1.getTime() - d2.getTime()) / 1000L;
    }

    /**
     * 相差分钟
     *
     * @param d1
     * @param d2
     */
    public static Long getMinuteDiff(Date d1, Date d2) {
        return getSecondsDiff(d1, d2) / 60;
    }

    /**
     * 当年第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(date, "yyyy-MM-dd"));
        c.set(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    /**
     * 当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate(date, "yyyy-MM-dd"));
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 根据日期，得到这个月最后的一天
     *
     * @param date
     * @return
     */
    public static Date getLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, getMaxDayOfMonth(date));

        return c.getTime();
    }

    /**
     * 这个月最大的一天
     *
     * @param date
     * @return
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date parse(String pattern, String date) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(Date date, String pattern) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static Date addDay(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, day);
        return c.getTime();
    }

    /**
     * 加减年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }

    /**
     * 2个时间差值
     *
     * @param date
     * @param date1
     * @return
     */
    public static Long mathDateMinus(Date date, Date date1) {
        return date.getTime() - date1.getTime();
    }

    /**
     * 上个月
     *
     * @param date
     * @return
     */
    public static Date getLastDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 是否是同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean theSameDay(Date d1, Date d2) {
        String pattern = "yyyy-MM-dd";
        String s1 = format(d1, pattern);
        String s2 = format(d2, pattern);

        return s1.equals(s2);
    }

    /**
     * 取小时
     *
     * @param d
     * @return
     */
    public static Integer getHour(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getWeek(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取日期所在的月份第一天开始时间
     */
    public static DateTime getMonthFirstDayTime(Object object) {
        DateTime dateTime = new DateTime(object);
        dateTime = dateTime.withTime(0, 0, 0, 0);

        return dateTime;
    }

    /**
     * 获取日期所在的月份最后一天结束时间
     */
    public static DateTime getMonthLastDayTime(Object object) {
        DateTime dateTime = new DateTime(object);

        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int lastDay = dateTime.dayOfMonth().withMaximumValue().dayOfMonth().get();

        dateTime = new DateTime(year, month, lastDay, 23, 59, 59, 999);

        return dateTime;
    }

    /**
     * 获取当前系统时间
     */
    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    /** 
     * 计算阴历 {@code year}年闰哪个月 1-12 , 没闰传回 0 
     * @param year 阴历年 
     * @return (int)月份 
     */  
    private static int getLeapMonth(int year) {  
        return (int) (LUNAR_INFO[year - 1900] & 0xf);  
    }  
  
    /** 
     * 计算阴历{@code year}年闰月多少天 
     * @param year 阴历年 
     * @return (int)天数 
     */  
    private static int getLeapMonthDays(int year) {  
        if (getLeapMonth(year)!=0) {
            if ((LUNAR_INFO[year - 1900] & 0xf0000)==0) {
                return 29;  
            } else {
                return 30;  
            }  
        } else {
            return 0;  
        }  
    }  
  
    /** 
     * 计算阴历{@code lunarYeay}年{@code month}月的天数 
     * @param lunarYeay 阴历年 
     * @param month 阴历月 
     * @return (int)该月天数 
     * @throws Exception 
     */  
    private static int getMonthDays(int lunarYeay, int month) throws Exception {  
        if ((month > 31) || (month < 0)) {  
            throw(new Exception("月份有错！"));  
        }  
        // 0X0FFFF[0000 {1111 1111 1111} 1111]中间12位代表12个月，1为大月，0为小月  
        int bit = 1 << (16-month);  
        if (((LUNAR_INFO[lunarYeay - 1900] & 0x0FFFF)&bit)==0) {
            return 29;  
        } else {
            return 30;  
        }  
    }  
  
    /** 
     * 计算阴历{@code year}年的总天数 
     * @param year 阴历年 
     * @return (int)总天数 
     */  
    private static int getYearDays(int year) {  
        int sum = 29*12;  
        for(int i=0x8000;i>=0x8;i>>=1){  
            if ((LUNAR_INFO[year-1900]&0xfff0&i)!=0) {
                sum++;  
            }  
        }  
        return sum+getLeapMonthDays(year);  
    }  
  
    /** 
     * 计算两个阳历日期相差的天数。 
     * @param startDate 开始时间 
     * @param endDate 截至时间 
     * @return (int)天数 
     */  
    private static int daysBetween(Date startDate, Date endDate) {  
        int days = 0;  
        //将转换的两个时间对象转换成Calendar对象  
        Calendar can1 = Calendar.getInstance();  
        can1.setTime(startDate);  
        Calendar can2 = Calendar.getInstance();  
        can2.setTime(endDate);  
        //拿出两个年份  
        int year1 = can1.get(Calendar.YEAR);  
        int year2 = can2.get(Calendar.YEAR);  
        //天数  
  
        Calendar can = null;  
        //如果can1 < can2  
        //减去小的时间在这一年已经过了的天数  
        //加上大的时间已过的天数  
        if (can1.before(can2)) {
            days -= can1.get(Calendar.DAY_OF_YEAR);  
            days += can2.get(Calendar.DAY_OF_YEAR);  
            can = can1;  
        } else {
            days -= can2.get(Calendar.DAY_OF_YEAR);  
            days += can1.get(Calendar.DAY_OF_YEAR);  
            can = can2;  
        }  
        for (int i = 0; i < Math.abs(year2-year1); i++) {  
            //获取小的时间当前年的总天数  
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);  
            //再计算下一年。  
            can.add(Calendar.YEAR, 1);  
        }  
        return days;  
    }  
  
    /** 
     * 检查阴历日期是否合法 
     * @param lunarYear 阴历年 
     * @param lunarMonth 阴历月 
     * @param lunarDay 阴历日 
     * @param leapMonthFlag 闰月标志 
     * @throws Exception 
     */  
    private static void checkLunarDate(int lunarYear, int lunarMonth, int lunarDay, boolean leapMonthFlag) throws Exception {  
        if ((lunarYear < MIN_YEAR) || (lunarYear > MAX_YEAR)) {  
            throw(new Exception("非法农历年份！"));  
        }  
        if ((lunarMonth < 1) || (lunarMonth > 12)) {  
            throw(new Exception("非法农历月份！"));  
        }  
        if ((lunarDay < 1) || (lunarDay > 30)) { // 中国的月最多30天  
            throw(new Exception("非法农历天数！"));  
        }  
  
        int leap = getLeapMonth(lunarYear);// 计算该年应该闰哪个月  
        if ((leapMonthFlag == true) && (lunarMonth != leap)) {  
            throw(new Exception("非法闰月！"));  
        }  
    }  
  
    /** 
     * 阴历转换为阳历 
     * @param lunarDate 阴历日期,格式YYYYMMDD 
     * @param leapMonthFlag 是否为闰月 
     * @return 阳历日期,格式：YYYYMMDD 
     * @throws Exception 
     */  
    public static String lunarToSolar(String lunarDate, boolean leapMonthFlag) throws Exception{  
        int lunarYear = Integer.parseInt(lunarDate.substring(0, 4));  
        int lunarMonth = Integer.parseInt(lunarDate.substring(4, 6));  
        int lunarDay = Integer.parseInt(lunarDate.substring(6, 8));  
  
        checkLunarDate(lunarYear, lunarMonth, lunarDay, leapMonthFlag);  
  
        int offset = 0;  
  
        for (int i = MIN_YEAR; i < lunarYear; i++) {  
            int yearDaysCount = getYearDays(i); // 求阴历某年天数  
            offset += yearDaysCount;  
        }  
        //计算该年闰几月  
        int leapMonth = getLeapMonth(lunarYear);  
  
        if (leapMonthFlag & leapMonth != lunarMonth) {
            throw(new Exception("您输入的闰月标志有误！"));  
        }  
  
        //当年没有闰月或月份早于闰月或和闰月同名的月份  
        if (leapMonth==0|| (lunarMonth < leapMonth) || (lunarMonth==leapMonth && !leapMonthFlag)) {
            for (int i = 1; i < lunarMonth; i++) {  
                int tempMonthDaysCount = getMonthDays(lunarYear, i);  
                offset += tempMonthDaysCount;  
            }  
  
            // 检查日期是否大于最大天  
            if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {  
                throw(new Exception("不合法的农历日期！"));  
            }  
            offset += lunarDay; // 加上当月的天数  
        }else{//当年有闰月，且月份晚于或等于闰月  
            for (int i = 1; i < lunarMonth; i++) {  
                int tempMonthDaysCount = getMonthDays(lunarYear, i);  
                offset += tempMonthDaysCount;  
            }  
            if (lunarMonth>leapMonth) {  
                int temp = getLeapMonthDays(lunarYear); // 计算闰月天数  
                offset += temp; // 加上闰月天数  
  
                if (lunarDay > getMonthDays(lunarYear, lunarMonth)) {  
                    throw(new Exception("不合法的农历日期！"));  
                }  
                offset += lunarDay;  
            }else { // 如果需要计算的是闰月，则应首先加上与闰月对应的普通月的天数  
                // 计算月为闰月  
                int temp = getMonthDays(lunarYear, lunarMonth); // 计算非闰月天数  
                offset += temp;  
  
                if (lunarDay > getLeapMonthDays(lunarYear)) {  
                    throw(new Exception("不合法的农历日期！"));  
                }  
                offset += lunarDay;  
            }  
        }  
  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
        Date myDate = null;  
        myDate = formatter.parse(START_DATE);  
        Calendar c = Calendar.getInstance();  
        c.setTime(myDate);  
        c.add(Calendar.DATE, offset);  
        myDate = c.getTime();  
  
        return formatter.format(myDate);  
    }  
    
    
    /** 
     * 阳历日期转换为阴历日期 
     * @param solarDate 阳历日期,格式YYYYMMDD 
     * @return 阴历日期 
     * @throws Exception 
     */  
    public static Date solarToLunar(Date solarDate) throws Exception{  
        int i;  
        int temp = 0;  
        int lunarYear;  
        int lunarMonth; //农历月份  
        int lunarDay; //农历当月第几天  
  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
        Date myDate = null;  
        Date startDate = null;  
        try {  
            //myDate = formatter.parse(solarDate);  
        	myDate = solarDate;
            startDate = formatter.parse(START_DATE);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
  
        int offset = daysBetween(startDate,myDate);  
  
        for (i = MIN_YEAR; i <= MAX_YEAR; i++){  
            temp = getYearDays(i);  //求当年农历年天数  
            if (offset - temp < 1){  
                break;  
            }else{  
                offset -= temp;  
            }  
        }  
        lunarYear = i;  
  
        int leapMonth = getLeapMonth(lunarYear);//计算该年闰哪个月  
        //设定当年是否有闰月  
        if (leapMonth > 0){  
            isLeapYear = true;  
        }else{  
            isLeapYear = false;  
        }  
  
        for (i = 1;  i<=12; i++) {  
            if (i==leapMonth+1 && isLeapYear) {
                temp = getLeapMonthDays(lunarYear);  
                isLeapYear = false;  
                i--;  
            } else {
                temp = getMonthDays(lunarYear, i);  
            }  
            offset -= temp;  
            if (offset<=0){
                break;  
            }  
        }  
  
        offset += temp;  
        lunarMonth = i;  
        lunarDay = offset;  
        
        return formatter.parse(lunarYear + "" + lunarMonth + "" + lunarDay);
    }  
  
    /**
     * 获取星期
     * @param date
     * @return String
     */
	public static String getWeekOfDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("EEEE");
		return format.format(date);
	}
}
