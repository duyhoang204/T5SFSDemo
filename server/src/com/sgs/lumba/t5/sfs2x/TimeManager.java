package com.sgs.lumba.t5.sfs2x;

/**
 * Created by rypon on 2/15/16.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public final class TimeManager {
    public static String GetClientTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(GetDBCalendar().getTime());
    }

    public static String GetClientTimeString(long add) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date times = GetDBCalendar().getTime();
        times.setTime(times.getTime() + add);
        return format.format(times);
    }

    public static Timestamp GetCurrentDateTime() {
        return new Timestamp(GetDBCalendar().getTime().getTime());
    }

    public static String GetDateString() {
        Calendar calendar = GetDBCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_");
        return dateFormat.format(calendar.getTime());
    }

    public static String GetDateStringWithinFiveBeforeNow(int numMinsBefore) {
        Calendar calendar = GetDBCalendar();
        int min = calendar.get(Calendar.MINUTE);
        min = min - numMinsBefore;
        min = min - (min % 5);
        calendar.set(Calendar.MINUTE, min);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_");
        return dateFormat.format(calendar.getTime());
    }

    public static String GetDateStringWithinFiveMins() {
        Calendar calendar = GetDBCalendar();
        int min = calendar.get(Calendar.MINUTE);
        min = min - (min % 5);
        calendar.set(Calendar.MINUTE, min);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_");
        return dateFormat.format(calendar.getTime());
    }

    public static String GetDateStringWithinTenMins() {
        Calendar calendar = GetDBCalendar();
        int min = calendar.get(Calendar.MINUTE);
        min = min - (min % 5) - 10;
        calendar.set(Calendar.MINUTE, min);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_");
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar GetDBCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + offset);
        return calendar;
    }

    public static int GetDBCurrentHour() {
        return GetDBCalendar().get(Calendar.HOUR_OF_DAY);
    }

    public static int GetNumMinutesToNextDay() {
        Calendar calendar = GetDBCalendar();
        return (24 - calendar.get(Calendar.HOUR_OF_DAY) - 1) * 60 + 60
                - calendar.get(Calendar.MINUTE);
    }

    public static long GetPreviousHourMillis() {
        Calendar calendar = GetDBCalendar();
        calendar.add(Calendar.HOUR, -1);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis();
    }

    public static long GetPreviousMondayMillis() {
        Calendar calendar = GetDBCalendar();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static String GetServerDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(GetDBCalendar().getTime());
    }

    public static String GetServerDateStringWithDayChanges(int changeAmount) {
        Calendar calendar = GetDBCalendar();
        calendar.add(Calendar.DATE, changeAmount);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static String GetServerTimeString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(GetDBCalendar().getTime());
    }
    public static String GetServerTimeString(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //final long timestamp = new Date().getTime();

        // with java.util.Date/Calendar api
        final Calendar cal = GetDBCalendar();
        cal.setTimeInMillis(timestamp);

        // and here's how to get the String representation
        final String timeString = format.format(cal.getTime());

        System.out.println(timeString);

        return timeString;
    }
    public static long GetTimeInMillis() {
        return GetDBCalendar().getTimeInMillis();
    }

    public static long GetTimeMillisFromString(String timeString) {
        if (timeString == null || timeString.equals("")
                || timeString.equals("null")) {
            return 0;
        }

        Calendar calendar = GetDBCalendar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            calendar.setTime(format.parse(timeString));
        } catch (ParseException exception) {
            logger.error("GetTimeMillisFromString:Exception:"
                    + exception.toString());
        }

        return calendar.getTimeInMillis();
    }

    public static String GetYesterdayDateString() {
        Calendar calendar = GetDBCalendar();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_");
        return dateFormat.format(calendar.getTime());
    }

    public static String GetYesterdayDateStringWithinFiveMins() {
        Calendar calendar = GetDBCalendar();
        int min = calendar.get(Calendar.MINUTE);
        min = min - (min % 5);
        calendar.set(Calendar.MINUTE, min);
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_");
        return dateFormat.format(calendar.getTime());
    }

    public static void Init() {
        Timestamp now = null;
        Connection dbConnection = DBManager.getConnection();

        try {
            PreparedStatement statement = dbConnection
                    .prepareStatement("select NOW() as now");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.first()) {
                now = resultSet.getTimestamp("now");
            }

            resultSet.close();
            statement.close();
        } catch (Exception exception) {
            logger.error("Init:Exception:" + exception.toString());
            now = null;
        }

        if (now != null) {
            Calendar calendar = Calendar.getInstance();
            long localTime = calendar.getTimeInMillis();
            calendar.setTime(now);
            offset = calendar.getTimeInMillis() - localTime;
            logger.info("TimeManager:Init: " + offset);
        }

        DBManager.closeConnection(dbConnection);
    }

    public static String TimeStringFromMillis(long millis) {
        if (millis == 0) {
            return "null";
        }

        String timeString = "";
        Calendar calendar = GetDBCalendar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            calendar.setTimeInMillis(millis);
            timeString = format.format(calendar.getTime());
        } catch (Exception exception) {
            logger.error("TimeStringFromMillis:Exception:"
                    + exception.toString());
        }

        return timeString;
    }
    public static String getTimeStringFromMillisBySQLFormat(long millis) {
        if (millis == 0) {
            return "null";
        }

        String timeString = "";
        Calendar calendar = GetDBCalendar();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            calendar.setTimeInMillis(millis);
            timeString = format.format(calendar.getTime());
        } catch (Exception exception) {
            logger.error("TimeStringFromMillis:Exception:"
                    + exception.toString());
        }

        return timeString;
    }

    private final static Logger logger = Logger.getLogger(TimeManager.class);
    private static long offset = 0;
}
