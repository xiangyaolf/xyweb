package com.zhst.utils;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 */
public class DateUtil {

    public static final DateTimeFormatter COMMON_DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME_HM_FORMATER = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");


    /**
     * 时间对象按照格式转时间字符串
     *
     * @param localDateTime
     * @param dateTimeFormatter
     * @return
     */
    public static String formateDateAndTime(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取两个时间之间的分钟数
     *
     * @return
     */
    public static long getMinutesByDuring(LocalDateTime beginDate, LocalDateTime endDate) {
        return Duration.between(beginDate, endDate).toMinutes();
    }

    /**
     * 两个时间相差多少个小时
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getHoursByDuring(LocalDateTime beginDate, LocalDateTime endDate) {
        return Duration.between(beginDate, endDate).toHours();
    }

    /**
     * 两个时间相差多少天
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getDaysByDuring(LocalDateTime beginDate, LocalDateTime endDate) {
        return Duration.between(beginDate, endDate).toDays();
    }

    /**
     * 时间字符串按照格式转时间对象
     *
     * @param date
     * @param dateTimeFormatter
     * @return
     */
    public static LocalDateTime parseDateFromString(String date, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    /**
     * 获取当前时间的年、月、日、小时
     *
     * @return
     */
    public static Map<String, Integer> getCurrentDay() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        Map<String, Integer> result = new HashMap<>();
        result.put("day", dayOfMonth);
        result.put("month", month);
        result.put("year", year);
        result.put("hour", hour);
        return result;
    }

    /**
     * 获取某年某月实际天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断两个时间的大小
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean isBefore(LocalDateTime beginTime, LocalDateTime endTime) {
        int result = beginTime.compareTo(endTime);
        return result < 0;
    }

    /**
     * 给时间字符串加xx小时
     * @param strDateTime
     * @param hours
     * @return
     */
    public static String addIntervalHours(String strDateTime, long hours,DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = parseDateFromString(strDateTime, dateTimeFormatter);
        LocalDateTime plusTime = localDateTime.plusHours(hours);
        return formateDateAndTime(plusTime, COMMON_DATE_TIME_FORMATER);
    }


    /**
     * 给时间字符串加xx小时
     * @param strDateTime
     * @param minutes
     * @return
     */
    public static String addIntervalMinutes(String strDateTime, long minutes,DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = parseDateFromString(strDateTime, dateTimeFormatter);
        LocalDateTime plusTime = localDateTime.plusMinutes(minutes);
        return formateDateAndTime(plusTime, dateTimeFormatter);
    }

    /**
     * 获取上个月的第一天和最后一天
     * @param date
     * @return
     */
    public static Map<String,String> getFirAndLastDayInLastMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();

        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String dayFirst = df.format(gcLast.getTime());
        dayFirst = dayFirst.concat(" 00:00:00");

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        String dayLast = df.format(calendar.getTime());
        dayLast = dayLast.concat(" 23:59:59");

        Map<String, String> map = new HashMap<>();
        map.put("first", dayFirst);
        map.put("last", dayLast);
        return map;
    }

    /**
     * 将date 转换成localDateTIme
     * @param date
     * @return
     */
    public static LocalDateTime parseDateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 按照日期字符串生成对应的日期
     * @param timeStr
     * @return
     */
    public static LocalDateTime regParseStr(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        String patternStrOne = "(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+)";
        Pattern compile = Pattern.compile(patternStrOne);
        Matcher matcher = compile.matcher(timeStr);
        String yearFormatStr = "yyyy";
        String monthFormatStr = "MM";
        String dayFormatStr = "dd";
        String hourFormatStr = "HH";
        String minuteFormatStr = "mm";
        String secondFormatStr = "ss";
        if (matcher.matches()) {
            String month = matcher.group(2);
            String day = matcher.group(3);
            String hour = matcher.group(4);
            String minute = matcher.group(5);
            String second = matcher.group(6);
            if (month.length() == 1) {
                monthFormatStr = "M";
            }
            if (day.length() == 1) {
                dayFormatStr = "d";
            }
            if (hour.length() == 1) {
                hourFormatStr = "H";
            }
            if (minute.length() == 1) {
                minuteFormatStr = "m";
            }
            if (second.length() == 1) {
                secondFormatStr = "s";
            }
        }
        String formatterString = yearFormatStr.concat("-").concat(monthFormatStr).concat("-").concat(dayFormatStr).concat(" ").concat(hourFormatStr).concat(":")
                .concat(minuteFormatStr).concat(":").concat(secondFormatStr);
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(formatterString));
    }

    private DateUtil() {

    }
}
