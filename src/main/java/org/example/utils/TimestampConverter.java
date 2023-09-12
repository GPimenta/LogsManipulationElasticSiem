package org.example.utils;

import org.example.model.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimestampConverter {

    public static LocalDateTime converTimestampToLocalDateTime(String timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }

    public static String converTimestampToString(LocalDateTime timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return timestamp.format(formatter);
    }

    public static LogEntry resetHour(LogEntry logEntry){
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

    public static LocalDateTime addHour(LocalDateTime dateTime) {
        return dateTime.plusHours(1);
    }

    public static LocalDateTime addDay(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
    
    public static List<LogEntry> addHourToLogList(List<LogEntry> logEntryList) {
        for (LogEntry logEntry: logEntryList) {
            LocalDateTime dateTimeForHour = TimestampConverter.converTimestampToLocalDateTime(logEntry.getTimestamp());
            LocalDateTime addHour = TimestampConverter.addHour(dateTimeForHour);
            String timestampAddedHour = TimestampConverter.converTimestampToString(addHour);
            logEntry.setTimestamp(timestampAddedHour);
        }
        return DuplicateList.createCopyList(logEntryList);
    }

    public static List<LogEntry> addDayToLogList(List<LogEntry> logEntryList) {
        for (LogEntry logEntry: logEntryList) {
            LocalDateTime dateTimeForDay = TimestampConverter.converTimestampToLocalDateTime(logEntry.getTimestamp());
            LocalDateTime addDay = TimestampConverter.addDay(dateTimeForDay);
            String timestampAddedHour = TimestampConverter.converTimestampToString(addDay);
            logEntry.setTimestamp(timestampAddedHour);
        }
        return DuplicateList.createCopyList(logEntryList);
    }



}
