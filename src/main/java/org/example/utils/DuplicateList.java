package org.example.utils;

import org.example.model.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class DuplicateList {

    public static LogEntry copyLogEntry(LogEntry logEntry) {
        LogEntry logEntryCopy = new LogEntry();
        logEntryCopy.setWinlog(logEntry.getWinlog());
        logEntryCopy.setEvent(logEntry.getEvent());
        logEntryCopy.setTimestamp(logEntry.getTimestamp());
        logEntryCopy.setUser(logEntry.getUser());
        logEntryCopy.setHost(logEntry.getHost());
        logEntryCopy.setSource(logEntry.getSource());

        return logEntryCopy;
    }

    public static List<LogEntry> createCopyList(List<LogEntry> logEntries){
        List<LogEntry> logEntriesCopy = new ArrayList<>();

        for (LogEntry logEntry: logEntries) {
            logEntriesCopy.add(copyLogEntry(logEntry));
        }
        return logEntriesCopy;
    }

    public static List<LogEntry> duplicateList(List<LogEntry> logEntriesOriginal, List<LogEntry> logEntriesCopy) {
//        List<LogEntry> logEntryList = new ArrayList<>();
//        if (logEntryList.addAll(logEntriesOriginal) && logEntryList.addAll(logEntriesCopy)) {
//            return logEntryList;
//        }
//        return logEntryList;
        if (logEntriesOriginal.addAll(logEntriesCopy)) {
            return logEntriesOriginal;
        }
        return new ArrayList<>();
    }
}
