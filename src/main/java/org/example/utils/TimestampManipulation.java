package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.LogEntry;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TimestampManipulation {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static LocalDateTime converTimestampToLocalDateTime(String timestamp){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }

    public static String converTimestampToString(LocalDateTime timestamp){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return timestamp.format(formatter);
    }

    public static LogEntry resetDateTime(LogEntry logEntry){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST,15);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        logEntry.setTimestamp(dateFormat.format(date));

        return logEntry;
    }

    public static LogEntry resetHour(LogEntry logEntry, int plusDay) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST,15 + plusDay);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        logEntry.setTimestamp(dateFormat.format(date));

        return logEntry;
    }

    private static LocalDateTime sumHour(LocalDateTime dateTime) {
        return dateTime.plusHours(1);
    }
    public static LocalDateTime randomHour(LocalDateTime dateTime) {
        Random random = new Random();
        int minHour = 9;
        int minMinute = 0;
        int minSeconds = 0;
        int maxHour = 17;
        int maxMinute = 59;
        int maxSeconds = 59;
        int hour = random.nextInt(maxHour - minHour + 1) + minHour;
        int minute = random.nextInt(maxMinute - minMinute + 1) + minMinute;
        int second = random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;

        return dateTime.withHour(hour).withMinute(minute).withSecond(second);
    }

    private static LocalDateTime sumDay(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }

    public static LocalDateTime sumWorkDays(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return dateTime.plusDays(3);
        }

        return dateTime.plusDays(1);
    }
    
//    public static List<LogEntry> addNormalWorkingHoursToLogList(List<LogEntry> logEntryList) {
//        for (LogEntry logEntry: logEntryList) {
//            LocalDateTime dateTimeForHour = TimestampManipulation.converTimestampToLocalDateTime(logEntry.getTimestamp());
//            LocalDateTime addHour = TimestampManipulation.sumHour(dateTimeForHour);
//            String timestampAddedHour = TimestampManipulation.converTimestampToString(addHour);
//            logEntry.setTimestamp(timestampAddedHour);
//        }
//        return ListManipulation.createCopyList(logEntryList);
//    }

    public static List<LogEntry> addNormalWorkingHoursToLogList(List<LogEntry> logEntryList) {
        for (LogEntry logEntry: logEntryList) {
            LocalDateTime dateTimeForHour = TimestampManipulation.converTimestampToLocalDateTime(logEntry.getTimestamp());
            LocalDateTime addHour = TimestampManipulation.randomHour(dateTimeForHour);
            String timestampAddedHour = TimestampManipulation.converTimestampToString(addHour);
            logEntry.setTimestamp(timestampAddedHour);
        }
        return ListManipulation.createCopyList(logEntryList);
    }


//    public static List<LogEntry> addNormalWorkingDaysToLogList(List<LogEntry> logEntryList) {
//        for (LogEntry logEntry: logEntryList) {
//            LocalDateTime dateTimeForDay = TimestampManipulation.converTimestampToLocalDateTime(logEntry.getTimestamp());
//            LocalDateTime addDay = TimestampManipulation.sumDay(dateTimeForDay);
//            String timestampAddedHour = TimestampManipulation.converTimestampToString(addDay);
//            logEntry.setTimestamp(timestampAddedHour);
//        }
//        return ListManipulation.createCopyList(logEntryList);
//    }

    public static List<LogEntry> addNormalWorkingDaysToLogList(List<LogEntry> logEntryList) {
        for (LogEntry logEntry: logEntryList) {
            LocalDateTime dateTimeForDay = TimestampManipulation.converTimestampToLocalDateTime(logEntry.getTimestamp());
            LocalDateTime addDay = TimestampManipulation.sumWorkDays(dateTimeForDay);
            String timestampAddedHour = TimestampManipulation.converTimestampToString(addDay);
            logEntry.setTimestamp(timestampAddedHour);
        }
        return ListManipulation.createCopyList(logEntryList);
    }

//    public static LogEntry addDayTime(String logEntry, String timeStamp) throws JsonProcessingException {
//        StringBuilder sb = new StringBuilder();
//        LogEntry newLogEntry = Parser.jsonToPojo(sb.append(logEntry));
//        newLogEntry.setTimestamp(timeStamp);
//        return newLogEntry;
//    }

//    public static LogEntry addHour(String logEntry) {
//
//    }

}
